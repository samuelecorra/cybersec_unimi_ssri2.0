## **Lezione 9: Similarità nell’intelligenza artificiale — rappresentazioni, cross-correlazione, retrieval e metodi nearest-neighbour**

La lezione trasforma la nozione di similarità, introdotta nella Lezione 8 come strumento per controllare i dataset, in una **decisione operativa**. Ogni sistema che «riconosce» qualcosa segue lo stesso scheletro: **rappresenta** il dato, lo **confronta** con altri dati rappresentati allo stesso modo e **usa il punteggio** del confronto per classificare, verificare una corrispondenza o ordinare un archivio. La cross-correlazione normalizzata (NCC) dice *dove* si trova un pattern e *quanto bene* corrisponde; il retrieval dice *quali* elementi memorizzati sono più vicini a una probe; il $k$-NN collega le due cose, perché classifica proprio a partire da una ricerca dei vicini. Le stesse idee valgono per immagini, segnali di sensori e testi, e restano intatte quando l’impronta progettata a mano (un hash) viene sostituita da un embedding appreso da una rete neurale.

![](imgs/Pasted%20image%2020261123134641.png)

> **Fonte.** La lezione segue il PDF dell’edizione 2026/27 `sources_2026-27/L09.pdf` (91 pagine). I segnaposto per le figure indicano la pagina di quel PDF; il materiale dell’edizione precedente non più presente nelle slide è conservato nel §11.


### **0. Panoramica e collocazione nel corso**


La trattazione si articola in sette blocchi, che riproducono l’indice delle slide:

1. **sezione 0** — richiamo, un nuovo quadro mentale e il $k$-NN: rappresentazioni, confronto, decisioni;
2. **sezione A** — cross-correlazione: spostamento, picchi, normalizzazione e pattern periodici;
3. **sezione B** — fondamenti del retrieval: punteggio, ranking, top-$k$, soglia, $P@k$;
4. **sezione C** — leggere il codice: convenzioni di OpenCV, NumPy e MATLAB;
5. **sezione D** — «trova le differenze»: un esperimento Python riproducibile;
6. **sezione E** — stesso strumento, altri segnali: ritardo tra sensori e ispezione rispetto a un campione di riferimento;
7. **sezione F** — il retrieval al lavoro: costruire un dataset per retrieval, policy di ranking, embedding.

Chiudono la lezione i punti principali e un’appendice con la ricetta MATLAB.

**Dove si colloca la lezione.** Il percorso del corso procede per strati. Le lezioni precedenti hanno insegnato ad **acquisire, pulire e armonizzare** i dati (Lezione 6), a confrontare la **capacità del modello con i dati disponibili** evitando il leakage (Lezione 7) e a **rappresentare** i dati definendo feature, firme e distanze (Lezione 8). Questa lezione è il passo successivo: la **similarità in pratica**, cioè come usare un punteggio per fare *match*, *classificare* e *ordinare*. Più avanti nel corso arriveranno gli **embedding appresi** e i sistemi **retrieval-augmented** (Lezione 15), che riusano esattamente lo stesso scheletro.

![](imgs/Pasted%20image%2020261123143805.png)

La lezione risponde a tre domande, ciascuna con il proprio strumento:

| Domanda | Strumento |
|---|---|
| **1.** Dov’è questo pattern, e quanto bene corrisponde? | cross-correlazione **normalizzata** (NCC) |
| **2.** Che cosa è cambiato tra due versioni, o tra due sensori? | **allineare** e poi **confrontare** |
| **3.** Quali elementi memorizzati sono i più vicini alla mia probe? | **nearest neighbour** e **ranking** |

> 💡 **Il filo conduttore.** Le lezioni precedenti hanno preparato dati e rappresentazioni; questa trasforma la similarità in una decisione. Conviene leggere ogni sezione chiedendosi sempre: *che cosa rappresento? come confronto? che cosa decido con il punteggio?*

---

### **1. Richiamo: dalle feature alle rappresentazioni apprese**


#### **1.1. Dal pattern matching al ML classico**

Nel machine learning classico l’input grezzo $x$ (pixel, campioni di un segnale, campi di una tabella) **non** entra direttamente nel classificatore. Prima viene trasformato in un **vettore di feature**

$$z=\phi(x),$$

e solo dopo un modello produce la previsione

$$\hat{y}=g(z).$$

Questa scomposizione separa **due problemi di progetto** distinti: *come rappresentare* i dati ($\phi$) e *come prendere la decisione* ($g$). Nel flusso di lavoro classico l’ottimizzazione regola soprattutto $g$, per esempio i pesi di una rete con strato di input, strato nascosto e strato di output; la scelta di $\phi$ è invece spesso **ingegnerizzata dal progettista**.

![](imgs/Pasted%20image%2020261123143832.png)

> 📌 **L’estrazione delle feature definisce che cosa il classificatore può «vedere».** Se $\phi$ scarta un’informazione, nessun $g$, per quanto potente, potrà recuperarla. Viceversa, una **rappresentazione debole** (un’estrazione «banale», per esempio i pixel grezzi) **spinge la complessità nel classificatore**: per compensare servono reti più profonde, con più strati nascosti, più parametri e quindi più dati (si ricordi il bilancio tra gradi di libertà e dati della Lezione 7).

#### **1.2. Quando non sappiamo progettare le feature giuste**

Le feature progettate a mano (*handcrafted*) richiedono **conoscenza di dominio** e possono **scartare informazione utile**. L’alternativa è **imparare la rappresentazione** automaticamente, con una funzione parametrica i cui parametri $\theta$ vengono ottimizzati sui dati:

$$z=\phi_\theta(x).$$

Le **attivazioni degli strati nascosti** di una rete addestrata diventano allora un vettore di feature che può essere **riusato da un altro modello a valle**. La progettazione delle feature, però, **non è scomparsa**: è stata spostata. Oggi la rappresentazione è plasmata dalla combinazione di **dati + obiettivo di addestramento + architettura**: chi sceglie quali dati usare, quale funzione obiettivo ottimizzare e quale rete adottare sta ancora, indirettamente, decidendo quali aspetti dell’input verranno conservati.

![](imgs/Pasted%20image%2020261123143857.png)

#### **1.3. Dalle feature apprese a un confronto esplicito**

Un **encoder** addestrato trasforma un input in $z=\phi_\theta(x)$. I campioni di riferimento possono quindi essere **memorizzati come vettori** $z_i$ e confrontati con il vettore $z_q$ di una nuova **probe** (l’elemento da interrogare). Il confronto può restare **esplicito**, per esempio con la distanza euclidea

$$d(z_q,z_i)=\lVert z_q-z_i\rVert_2=\sqrt{\sum_{j=1}^{D}\big(z_{q,j}-z_{i,j}\big)^2}.$$

> 💡 **Scatola nera sì, regola di confronto no.** L’embedding appreso può restare una scatola nera (non sappiamo interpretare le singole componenti di $z$), ma la **regola di matching** — quale distanza, quale soglia, quale ordinamento — è un oggetto semplice che si può **ispezionare e testare**. È un modo concreto per recuperare un po’ della spiegabilità discussa nella Lezione 2: si separa la parte opaca (l’encoder) dalla parte verificabile (il confronto).

![](imgs/Pasted%20image%2020261123144148.png)

#### **1.4. Richiamo dalla Lezione 8: una firma e una distanza**

L’esempio più semplice di questo schema è l’**hash percettivo** visto nella Lezione 8. 

![](imgs/Pasted%20image%2020261123144216.png)

Due immagini $I_1$ e $I_2$ passano ciascuna attraverso la stessa trasformazione $T(\cdot)$: riduzione a $8\times 8$ pixel in scala di grigi e **soglia** sulla media. Ogni immagine diventa così una **firma** binaria

$$h,\;h'\in\{0,1\}^{64}.$$

L’elaborazione successiva è la **distanza di Hamming**, che conta i bit diversi:

$$d_H(h,h')=\sum_{j=1}^{64}\big[h_j\neq h'_j\big]\;\in\{0,1,\dots,64\}.$$


| | Contenuto |
|---|---|
| **Input** | due immagini $I_1$, $I_2$; ognuna diventa una firma $h,h'\in\{0,1\}^{64}$ |
| **Output** | un intero $d_H\in\{0,\dots,64\}$: il numero di bit diversi tra le due firme |

> 📌 **La trasformazione è volutamente lossy.** $T$ **perde informazione di proposito**: scarta il dettaglio per conservare soltanto le variazioni che contano per il compito (la struttura grossolana dell’immagine), e rende così irrilevanti differenze che non interessano, come una ricompressione JPEG o un leggero cambio di luminosità. Scegliere che cosa scartare equivale a scegliere le **invarianze** del sistema: è il tema che attraversa l’intera lezione.

---

### **2. Sezione 0 — Un nuovo quadro mentale: rappresentazione, confronto, decisione**


#### **2.1. Che cosa si produce e che cosa si riusa?**

Costruire o configurare un sistema intelligente può avvenire in tre modi. In tutti si sta costruendo o configurando un modello; ciò che cambia è l’**asset riutilizzabile** che resta alla fine:

![](imgs/Pasted%20image%2020261123144313.png)

| Modalità | Input | Operazione | Output (asset) |
|---|---|---|---|
| **addestrare per un solo compito** | dati etichettati | si addestra $g_\theta$ | un **modello del compito** |
| **imparare una rappresentazione** | dati + un obiettivo di apprendimento | si addestra l’encoder $\phi_\theta$ | un **encoder riutilizzabile** che produce rappresentazioni |
| **riusare un modello pre-addestrato** | un *foundation model* + un bisogno | si adatta, si usa con un prompt o si distilla | un **sistema a valle** |

> 💡 **Perché distinguere i tre casi.** Nel primo caso il valore sta nella decisione finale; nel secondo sta nello **spazio delle rappresentazioni**, che può servire molti compiti diversi (ricerca, deduplicazione, classificazione); nel terzo la conoscenza è già contenuta in un modello di base, e il lavoro consiste nel riusarla. La lezione si concentra sul secondo: una volta che si ha una buona $\phi$, molte decisioni diverse si riducono a confrontare vettori.

#### **2.2. Feature progettate ed embedding appresi**

Un input — un’immagine (IMG), un testo (TXT) o un segnale (SIGN) — può essere trasformato in due modi:

- con una **trasformazione progettata** $\phi_d$: è l’essere umano a scegliere la regola (statistiche, hash, descrittori); si ottiene un vettore $z=\phi_d(x)$;
- con un **encoder appreso** $\phi_\theta$: è l’addestramento a ottimizzare i parametri $\theta$ sui dati; si ottiene un **embedding** $z=\phi_\theta(x)$.

In entrambi i casi segue un **confronto**: similarità coseno, distanza euclidea, distanza di Hamming o prodotto scalare, che produce un punteggio di similarità $s$ o una distanza $d$.

![](imgs/Pasted%20image%2020261123144404.png)

> 📌 **Dove sta il guadagno.** Nella rappresentazione progettata la regola è **specificata dall’ingegnere**; in quella appresa è **l’addestramento a determinarne i parametri**. In molti sistemi attuali il guadagno maggiore viene dal **migliorare $\phi(\cdot)$ mantenendo semplice la regola di confronto**: la distanza resta un coseno o un’euclidea, mentre l’encoder diventa sempre più ricco.

#### **2.3. Modalità diverse possono condividere uno spazio di rappresentazione**

Un encoder per le immagini, uno per i testi e uno per i segnali possono proiettare i rispettivi input in uno **spazio allineato**. In quello spazio i vettori provenienti da modalità diverse diventano **confrontabili**, e il confronto alimenta una decisione: un *match*, una classe, un risultato di retrieval ordinato o un’altra azione.

![](imgs/Pasted%20image%2020261123144425.png)

> ⚠️ **L’allineamento non è automatico.** Due encoder diversi producono vettori comparabili solo se sono stati **addestrati insieme** perché lo siano (lo si vedrà nel §8.6 con le coppie immagine-testo). Avere la stessa dimensione non basta.

#### **2.4. La rappresentazione determina che cosa significa «simile»**

L’esempio della slide usa due oggetti, un **quadrato rosso** e un **triangolo blu**, e una query. Ogni oggetto ha due attributi, colore e forma.

- **Caso A — la rappresentazione conserva solo il colore.** Misurando la distanza lungo l’asse del colore, il quadrato rosso ha distanza **0** dalla query: è il vicino più prossimo.
- **Caso B — la rappresentazione conserva solo la forma.** Misurando lungo l’asse della forma, è il triangolo blu ad avere distanza **0**: il vicino più prossimo cambia.

![](imgs/Pasted%20image%2020261123144436.png)

> 📌 **La formula della distanza può restare identica.** Cambiare la rappresentazione cambia i **vicinati**, e quindi il **significato di «simile»**. Non esiste una similarità «in assoluto»: esiste una similarità *rispetto a una rappresentazione*. Questa osservazione spiega quasi tutti i successi e i fallimenti che si incontreranno nella lezione.

#### **2.5. Da un punteggio di confronto a tre decisioni**

Lo stesso punteggio $s(z_q,z_i)$ o la stessa distanza $d(z_q,z_i)$ possono alimentare **tre regole di decisione diverse**, a valle della stessa rappresentazione:

| Decisione | Regola | Output |
|---|---|---|
| **CLASSIFY** (classificare) | si usano punteggi di classe o i vicini | un’**etichetta di classe** |
| **MATCH** (verificare) | si applica una **soglia** | **accetta / rifiuta** |
| **RANK** (ordinare) | si **ordinano** i punteggi | una **lista ordinata**, il top-$k$ |

![](imgs/Pasted%20image%2020261123144451.png)

Le tre decisioni condividono la rappresentazione a monte e differiscono **soltanto per la regola** applicata al punteggio.

##### **2.5.1. CLASSIFY — assegnare una probe a una classe**

- **Input**: una probe $x$ (immagine, testo o segnale).
- **Output**: un’etichetta tra $C$ classi; nel caso più semplice **non serve una gallery esterna**.

![](imgs/Pasted%20image%2020261123144515.png)

Il sistema addestrato converte la rappresentazione $z=\phi(x)$ in **punteggi di classe** (o la confronta con **prototipi** di classe) e restituisce una sola decisione di classe.

##### **2.5.2. MATCH — confrontare una probe con un riferimento**

- **Input**: una probe $x$ e **un** riferimento memorizzato $z_r$.
- **Output**: una decisione **binaria**, *match* oppure *no match*.

![](imgs/Pasted%20image%2020261123144525.png)

Il confronto produce $s(z_q,z_r)$ o $d(z_q,z_r)$, e una **soglia** $\tau$ converte quell’unico punteggio in una decisione binaria. Con una distanza la regola è

$$\text{match}\iff d(z_q,z_r)\le\tau,$$

con una similarità il verso si inverte ($s\ge\tau$). La soglia **va calibrata per l’applicazione**: un controllo d’accesso e un motore di ricerca di prodotti tollerano errori molto diversi.

##### **2.5.3. RANK — recuperare gli elementi memorizzati più vicini**

- **Input**: una probe $q$ e una **gallery** di $N$ vettori memorizzati $\{z_i\}_{i=1}^N$.
- **Output**: la lista **ordinata** degli $N$ elementi, oppure i suoi primi $k$.

![](imgs/Pasted%20image%2020261123144544.png)

Il retrieval **conserva la struttura del vicinato** invece di comprimerla subito in una sola decisione binaria o di classe: l’utente (o un passo successivo) vede *chi* è vicino e *quanto*.

#### **2.6. Il $k$-NN collega retrieval e classificazione**

Il metodo dei **$k$ vicini più prossimi** ($k$-*Nearest Neighbours*, $k$-NN) mostra che retrieval e classificazione sono la **stessa ricerca usata in due modi**. La probe viene confrontata con **ogni** vettore memorizzato; quelli più vicini sono i suoi **vicini**.

- **Classificazione** ($k=1$): si prende il vicino più prossimo e si assegna alla probe **la classe memorizzata con esso**. Con $k>1$ le $k$ etichette **votano**.
- **Retrieval**: si identificano i primi $k$ vicini e si **restituiscono i vicini stessi**, come lista ordinata.

![](imgs/Pasted%20image%2020261123144631.png)

##### **2.6.1. Classificare con il voto degli esempi più vicini**

Il $k$-NN ha una caratteristica che lo distingue dai modelli visti finora: **non c’è addestramento**, perché **gli esempi etichettati sono il modello**. Per un punto nuovo:

1. si calcola la distanza da **ogni** esempio memorizzato;
2. si prendono i $k$ più vicini;
3. si restituisce l’**etichetta di maggioranza**.

Dentro questo algoritmo c’è tutto ciò che si è visto finora: una **rappresentazione**, una **distanza** e un **ranking**.

- **Input**: $N$ punti etichettati $x_i\in\mathbb{R}^d$ (nell’esempio $d=2$), una query $q$ e il valore $k$.
- **Output**: un’etichetta (la maggioranza dei $k$ più vicini) e, se serve, i **conteggi dei voti**, per esempio $[1,4]$.

**Esempio minimo** ($k=3$, distanza euclidea):

| Vicino | Distanza | Etichetta |
|---|---:|:---:|
| P1 | 0,5 | A |
| P2 | 0,8 | B |
| P3 | 1,1 | B |
| P4 | 2,4 | A |

I tre più vicini sono P1, P2 e P3: i voti sono A:1 e B:2, quindi la probe è assegnata alla **classe B**. Con $k=1$ conterebbe solo P1 e la classe sarebbe **A**.

> 💡 **Il modello è la memoria.** Il $k$-NN è un apprendimento *lazy* (pigro): rimanda tutto il lavoro al momento della query. Il prezzo è il **costo della ricerca**, che cresce con $N$, e la **dipendenza totale dalla rappresentazione e dalla scala delle feature** (§2.6.3). Il metodo verrà ripreso in modo sistematico nella Lezione 19.

##### **2.6.2. La scelta di $k$**

Una query (la stella) viene etichettata dal voto dei suoi $k$ punti etichettati più vicini. Nella figura i voti tra cerchi e triangoli sono:

| $k$ | Voti (cerchi–triangoli) | Decisione |
|---:|:---:|:---:|
| 1 | 1–0 | cerchio |
| 5 | 1–4 | triangolo |
| 15 | 2–13 | triangolo |

![](imgs/Pasted%20image%2020261123145352.png)

> 📌 **$k$ piccolo segue il rumore, $k$ grande liscia la frontiera.** Con $k=1$ la decisione dipende da un singolo punto, che può essere un esempio etichettato male o un outlier; con $k$ grande la decisione diventa più stabile ma può ignorare strutture locali reali. È un primo incontro con il compromesso **bias-varianza** (Lezioni 14 e 17): $k$ piccolo = bassa distorsione e alta varianza, $k$ grande = il contrario. Nei problemi binari si sceglie spesso $k$ dispari per evitare pareggi.

##### **2.6.3. Lo scaling cambia i vicini**

Si consideri un dataset con due feature: l’**altezza in cm**, con dispersione di circa 10, e il **reddito in euro all’anno**, con dispersione di circa 10 000. In unità grezze, una differenza di 1000 euro pesa nella distanza euclidea come una differenza di 1000 cm: **il reddito decide da solo**, e l’altezza diventa irrilevante. Nella slide il voto 5-NN è **2–3 con le unità grezze** e **1–4 dopo la standardizzazione**, e i due insiemi di vicini **non condividono nemmeno un punto**.

![](imgs/Pasted%20image%2020261123145414.png)

La standardizzazione (*z-score*, ripresa nella Lezione 10) porta ogni feature a media nulla e deviazione standard unitaria:

$$x'_j=\frac{x_j-\mu_j}{\sigma_j}.$$

> ⚠️ **Lo scaler si stima solo sul training set.** Media $\mu_j$ e deviazione $\sigma_j$ devono essere calcolate **sui soli dati di addestramento** e poi applicate, invariate, ai dati di test. Calcolarle sull’intero dataset fa trapelare nel modello informazione statistica sul test (data leakage, Lezione 7). Il §5.4 mostra il codice corretto.

#### **2.7. Sistemi classici e attuali condividono un’astrazione**

| | Input | Rappresentazione | Confronto | Uso del risultato |
|---|---|---|---|---|
| **classico** | IMG / SIGN | **ingegnerizzata** | correlazione / distanza | match, classificazione o ranking |
| **attuale** | IMG / TXT / SIGN | **encoder appreso** / embedding | similarità vettoriale / ricerca | recupero, *grounding* o decisione |

![](imgs/Pasted%20image%2020261123145442.png)


- **Esempio classico**: hash dell’immagine → distanza di Hamming → ranking o soglia.
- **Esempio attuale**: embedding appreso → similarità coseno → retrieval vettoriale.

> 📌 **Lo scheletro durevole è *rappresenta → confronta → usa il risultato*.** Input e output sono **interfacce**; i blocchi sono le **funzioni** che trasformano i dati. Le tecnologie cambiano (dall’hash al Transformer), lo scheletro resta. Il termine *grounding* (ancoraggio) indica l’uso dei documenti recuperati per fondare la risposta di un modello linguistico su fonti verificabili, come nei sistemi RAG della Lezione 15.

#### **2.8. Similarità tra immagini: il confronto degli istogrammi**

Una rappresentazione progettata molto semplice è l’**istogramma dei colori**.

- **Input**: un’immagine RGB con $N$ pixel, valori da 0 a 255 (l’esempio è una foto di foresta di $3072\times 2048$ pixel).
- **Output**: un vettore di **768 numeri** ($3\times 256$).

![](imgs/Pasted%20image%2020261123145512.png)

La costruzione procede in tre passi:

1. per ogni canale $c\in\{R,G,B\}$ si conta la frazione di pixel con valore $k$:
$$h_c(k)=\frac{\#\{\text{pixel con valore }k\}}{N},\qquad k=0,\dots,255;$$
2. si concatenano i tre istogrammi: $h=[h_R,h_G,h_B]$, un vettore di 768 valori;
3. si confrontano due immagini $I$ e $J$ con la distanza

$$d(I,J)=\frac{1}{3}\sum_{c}\frac{1}{255}\sum_{k=0}^{255}\Big|F^{I}_c(k)-F^{J}_c(k)\Big|,$$

dove $F_c$ è l’**istogramma cumulativo** del canale $c$, $F_c(k)=\sum_{j\le k}h_c(j)$.

> 💡 **Perché gli istogrammi cumulativi (nota didattica).** La distanza tra istogrammi cumulativi in una dimensione coincide con la **distanza di Wasserstein** (detta anche *Earth Mover’s Distance*): misura quanta «massa» di pixel bisogna spostare, e di quanto, per trasformare una distribuzione nell’altra. A differenza del confronto bin per bin, una lieve variazione di tonalità sposta un po’ la massa e produce una distanza piccola, invece di svuotare un bin e riempirne un altro. I fattori $1/255$ e $1/3$ normalizzano il risultato in $[0,1]$. Poiché gli istogrammi sono **normalizzati** per $N$, la dimensione dell’immagine non conta.

**Pro e contro.**

| Pro | Contro |
|---|---|
| **molto veloce**: il confronto lavora su un vettore 1D corto (768 valori) | **forme e pattern non contano**! |
| **posizione, scala e rotazione non contano**: si contano solo i colori | **troppo semplicistico**: una banana e una spiaggia possono sembrare simili |

Nell’esempio della slide un **pappagallo** e un **mango** sono oggetti diversi, eppure i loro istogrammi di colore formano la **coppia più vicina**: distanza **0,077**, contro **0,164** e **0,185** rispetto alla foresta. Le statistiche di colore ignorano la forma: oggetti diversi con colori simili risultano simili.

![](imgs/Pasted%20image%2020261123145926.png)

#### **2.9. Quale strumento di confronto risponde alla domanda?**

Data una probe e dei dati di riferimento, bisogna decidere **se** corrisponde, **dove** compare un pattern, oppure **quali** elementi memorizzati sono più vicini. Ogni strumento risponde a una domanda diversa:

| Strumento | Input → output | Tollera | Di solito fallisce su |
|---|---|---|---|
| **hash dell’immagine + Hamming** | immagine → firma; due firme → $d_H$ | luminosità, colore, ricodifica, ridimensionamento | ribaltamenti, ritagli ampi, rotazioni |
| **cross-correlazione normalizzata** | scena + kernel → mappa dei punteggi e spostamento del picco | guadagno/offset di luminosità; traslazione | rotazione, forte cambio di scala, ribaltamenti |
| **ranking su una gallery** | probe + $N$ elementi memorizzati → lista ordinata / top-$k$ | qualunque cosa tolleri la rappresentazione | rappresentazione sbagliata, calibrazione scadente |

> 📌 **Progettare le invarianze e il compromesso.** Ogni strumento risponde a una domanda diversa. Si devono progettare sia le **invarianze** (che cosa deve essere considerato «uguale») sia il compromesso applicativo tra **capacità discriminativa** e **complessità computazionale**.

---

### **3. Sezione A — Cross-correlazione: la similarità come funzione dello spostamento**


#### **3.1. La cross-correlazione cerca un pattern traslato**

L’hash risponde a «quanto sono simili due immagini intere». La **cross-correlazione** risponde a una domanda più ricca: *quanto* un pattern somiglia a ogni porzione di un segnale o di un’immagine, **in funzione dello spostamento**.

- **Esempio su immagine**: una scena e un **kernel periorbitale** (la regione dell’occhio) alla stessa scala; si fa scorrere il kernel e si assegna un punteggio a ogni posizione.
- **Esempio su segnale**: un lungo segnale simile a un ECG e un singolo complesso **P–QRS–T** ritagliato; la stessa idea in una dimensione.

![](imgs/Pasted%20image%2020261123150010.png)

> 📌 **Il picco dice due cose.** La cross-correlazione restituisce un **punteggio in funzione dello spostamento**. Il **picco** indica sia **quanto bene** il pattern corrisponde (la sua altezza) sia **dove** avviene il miglior allineamento (la sua posizione).

#### **3.2. Uno spostamento fisso $\tau_0$: moltiplicare i campioni corrispondenti e sommare**

Si fissi uno spostamento $\tau_0$. Il kernel $k(t)$ si appoggia in un solo punto del segnale; si moltiplica ogni campione del kernel per il campione del segnale che gli sta sotto, e si sommano i prodotti:

$$C(\tau_0)=\sum_{t=0}^{N-1}s(t+\tau_0)\,k(t).$$

- **Input**: la finestra del segnale $s(t+\tau_0)$ e il kernel $k(t)$, di $N=29$ campioni ciascuno (uno ogni 30 ms, unità arbitrarie).
- **Output**: 29 prodotti e **un solo numero**, il punteggio $C(\tau_0)$, cioè **un punto** della curva di output.

Con $\tau_0=1{,}15$ s il kernel è fermo in un punto del segnale; $t$ è l’indice del campione nel kernel ($0,\dots,28$), e per ogni $t$ si moltiplicano $s(t+\tau_0)$ e $k(t)$.

![](imgs/Pasted%20image%2020261123150140.png)

**Esempio svolto.** In questa posizione la finestra **coincide** con il kernel, quindi ogni prodotto è un quadrato:

- per $t=6$: $0{,}133\times 0{,}133=0{,}018$;
- per $t=12$: $0{,}477\times 0{,}477=0{,}227$;
- sommando tutti i 29 prodotti: $\sum=0{,}54=C(\tau_0)$.

Poiché la finestra è uguale al kernel, ogni prodotto è un quadrato e quindi è $\ge 0$: non ci sono termini negativi che riducano la somma. Ripetendo il calcolo per ogni $\tau$ si ottiene la curva. (La **convoluzione** è la stessa somma con il kernel **ribaltato nel tempo**: §3.4.)

#### **3.3. L’output: il punteggio a ogni spostamento**

- **Input**: segnale $s$ di **1500 campioni** (3 s a 500 Hz); kernel $k$ di **435 campioni** (0,87 s).
- **Output**: una curva $\text{NCC}(\tau)$ di $1500-435+1=1066$ valori in $[-1,1]$; il suo picco è lo spostamento migliore.

Il numero di valori si spiega così: il kernel deve stare **interamente** dentro il segnale, quindi il suo primo campione può occupare le posizioni da 0 a $1500-435$, cioè $1066$ posizioni (è la modalità *valid*, §3.7). Ogni punto della curva è un punteggio come la somma del paragrafo precedente, calcolato a un altro spostamento $\tau$ e **normalizzato**. Il picco (**1,00 a $\tau=1{,}15$ s**) marca il miglior allineamento; gli **altri battiti** producono picchi più bassi, perché sono complessi cardiaci simili ma non identici a quello ritagliato.

![](imgs/Pasted%20image%2020261123150338.png)

#### **3.4. Correlazione e convoluzione sono strettamente imparentate**

Le due operazioni bidimensionali, tra un’immagine $f$ e un kernel $t$, sono:

$$\text{cross-correlazione 2D:}\qquad (f\star t)(i,j)=\sum_{x,y}f(x+i,\,y+j)\,t(x,y),$$

$$\text{convoluzione 2D:}\qquad (f* t)(i,j)=\sum_{x,y}f(i-x,\,j-y)\,t(x,y).$$

La relazione compatta tra le due è

$$f\star t=f*\tilde{t},\qquad \tilde{t}(x,y)=t(-x,-y):$$

la correlazione con $t$ equivale alla convoluzione con il kernel **ribaltato** (ruotato di 180°).

- Sono molto vicine per significato e per operazione: **entrambe fanno scorrere un kernel e accumulano prodotti**.
- La differenza è il **ribaltamento del kernel**.
- I comuni strati «convolutivi» delle CNN **implementano in realtà una cross-correlazione** e **apprendono i kernel dai dati**.

> 📌 **Teoria fondamentale — d’esame.** Nei diagrammi si userà spesso la notazione compatta $f*t$, ma la distinzione tra correlazione e convoluzione va compresa. Nelle CNN il ribaltamento è irrilevante proprio perché i pesi sono appresi: se servisse un kernel ribaltato, l’addestramento lo imparerebbe direttamente.

> 💡 **Verifica della relazione (nota didattica).** Sostituendo $\tilde t$ nella convoluzione: $(f*\tilde t)(i,j)=\sum_{x,y}f(i-x,j-y)\,t(-x,-y)$. Con il cambio di variabili $x'=-x$, $y'=-y$ la somma diventa $\sum_{x',y'}f(i+x',j+y')\,t(x',y')$, che è proprio $(f\star t)(i,j)$.

#### **3.5. Cross-correlazione normalizzata (NCC)**

Nelle immagini in cui **luminosità e contrasto variano**, ogni confronto si normalizza **sottraendo la media e dividendo per la deviazione standard**:

$$\text{NCC}(i,j)=\frac{1}{n}\sum_{x,y}\frac{\big(f(x,y)-\mu_f\big)\,\big(t(x+i,\,y+j)-\mu_t(i,j)\big)}{\sigma_f\,\sigma_t(i,j)}.$$

| Simbolo | Significato |
|---|---|
| $f(x,y)$ | il **kernel** o *template* |
| $t(x,y)$ | l’immagine o il segnale **in cui si cerca** |
| $(i,j)$ | lo **spostamento** in pixel |
| $n$ | il **numero di pixel del kernel** |
| $\mu_f,\ \sigma_f$ | media e deviazione standard del **kernel** |
| $\mu_t(i,j),\ \sigma_t(i,j)$ | media e deviazione standard della **finestra corrente**, che cambiano con lo spostamento |

**Interpretazione dei valori**:

| NCC | Significato |
|---:|---|
| $+1$ | stesso pattern, a meno di un **guadagno positivo** e di un **offset** |
| $0$ | **nessuna relazione lineare** |
| $-1$ | pattern **invertito** (in negativo) |

> 📌 **Formula fondamentale — d’esame.** La NCC è il coefficiente di correlazione di Pearson (Lezione 8) tra il kernel e la finestra, calcolato a ogni spostamento. Per questo è compresa in $[-1,1]$ e ignora qualunque trasformazione lineare della luminosità $t'=a\,t+b$ con $a>0$: la sottrazione della media elimina $b$, la divisione per $\sigma$ elimina $a$.

> ⚠️ **Attenzione ai ruoli delle lettere.** In questa slide $f$ è il **kernel** e $t$ l’immagine in cui si cerca; nella formula di correlazione del §3.4 i ruoli erano opposti ($f$ immagine, $t$ kernel). Nelle formule e nel codice conviene sempre chiedersi *quale dei due scorre*.

#### **3.6. Template matching su un esempio geometrico controllato**

Il primo esempio è costruito apposta: un’immagine contiene un **pattern triangolare** e un **quadrato** come distrattore. Il kernel (visibile nella slide) è il triangolo. La NCC ha il picco **dove il pattern triangolare è presente**; il quadrato, che ha una forma diversa, produce un punteggio più basso.

![](imgs/Pasted%20image%2020261123151030.png)

**Perché normalizzare? La luminosità può ingannare la somma grezza.** La correlazione grezza (senza normalizzazione) **premia i valori di pixel grandi e positivi**, per cui un **distrattore luminoso** può dominare la mappa anche se ha la forma sbagliata. La NCC rimuove la **media locale** e la **scala**, così è la **forma** del pattern a guidare il punteggio.

![](imgs/Pasted%20image%2020261123151044.png)

> 💡 **Perché la somma grezza sbaglia (nota didattica).** Senza normalizzazione il punteggio è $\sum f\cdot t$: una finestra uniformemente bianca (tutti valori alti) produce una somma grande con *qualunque* kernel a valori positivi, indipendentemente dalla forma. La NCC confronta invece le **deviazioni dalla media**: una finestra uniforme ha $\sigma_t\approx 0$ e nessuna struttura da correlare.

#### **3.7. Leggere una mappa NCC: valore, picco e dimensione**

- La **posizione del picco** $(i^*,j^*)=\arg\max_{i,j}\text{NCC}(i,j)$ è il miglior spostamento.
- Il **valore del picco** misura la **qualità** della similarità.
- Un **picco netto e isolato** dà una **localizzazione precisa**; un **picco largo** dà una localizzazione incerta.
- La mappa può contenere **più picchi** quando il pattern si ripete.

| Lettura della mappa | Che cosa dice |
|---|---|
| **valore del picco** | **quanto** è simile |
| **posizione del picco** | **dove** |
| **numero di picchi** | **quante** istanze |

Conta anche la **dimensione dell’output**: le modalità **valid**, **same** e **full** usano convenzioni diverse per lo spostamento.

| Modalità | Posizioni calcolate | Dimensione (immagine $M\times N$, kernel $m\times n$) |
|---|---|---|
| **valid** | solo quelle in cui il kernel è tutto dentro l’immagine | $(M-m+1)\times(N-n+1)$ |
| **same** | una per pixel dell’immagine (con *padding*) | $M\times N$ |
| **full** | ogni sovrapposizione, anche parziale | $(M+m-1)\times(N+n-1)$ |

La stessa mappa NCC può essere mostrata come **heatmap** o come **superficie 3D**. A sinistra la slide mostra l’immagine $I$ e il template $T$, cioè che cosa si sta cercando. Heatmap e superficie contengono **la stessa informazione**; la superficie rende più evidenti il **picco dominante** e il «paesaggio» delle risposte circostanti.

![](imgs/Pasted%20image%2020261123151101.png)

#### **3.8. Pattern periodici e istanze multiple**

##### **3.8.1. Correlare un pattern con se stesso: l’autocorrelazione**

- **Input**: un’immagine grigia $f$ di $537\times 623$ pixel `uint8`, con un **pattern periodico di coni** gelato.
- **Output**: la mappa $f\star f$ di $(2\cdot 623-1)\times(2\cdot 537-1)=1245\times 1073$ valori, scalata in modo che il **centro valga 1**.

Il **centro** (spostamento nullo) vale 1 **per costruzione**, perché lì l’immagine è perfettamente sovrapposta a se stessa. Gli **altri picchi** stanno ai **periodi** del pattern: spostando l’immagine di un periodo, i coni tornano a sovrapporsi ad altri coni. Un pattern periodico produce quindi un **reticolo di picchi**. La dimensione è quella *full*: con immagine e kernel uguali, $(M+M-1)\times(N+N-1)$.

![](imgs/Pasted%20image%2020261123151120.png)

##### **3.8.2. Un kernel, molte corrispondenze: un picco per ogni istanza**

- Un kernel di $125\times 210$ pixel è ritagliato dal campo di coni $537\times 623$. La mappa NCC ha **la stessa dimensione del campo**: il campo viene riempito ai bordi con la sua media (*padding*), così ogni pixel della mappa è il punteggio del kernel **centrato** lì. OpenCV da solo restituirebbe la mappa *valid*, più piccola.
- **Venti picchi separati superano 0,5**, uno per cono; la posizione esatta del ritaglio raggiunge **1,00**.
- Una **struttura ripetuta** crea **picchi ripetuti** al **periodo spaziale** del pattern.

![](imgs/Pasted%20image%2020261123151128.png)

##### **3.8.3. Un kernel che non c’è nell’immagine ha comunque un massimo**

- **Riga superiore**: il kernel è **un altro disegno** (un cono con la ciliegina, ridimensionato alla scala dei coni). Il punteggio migliore è solo **0,27**.
- **Riga inferiore**: il kernel è ritagliato dal campo. Stessa immagine, stessa scala: **venti picchi**, massimo **1,00**.

![](imgs/Pasted%20image%2020261123151139.png)

> ⚠️ **Una mappa di risposta non è mai vuota.** L’argmax esiste sempre, anche quando il pattern non c’è: la decisione *trovato / non trovato* richiede una **soglia sul valore del picco**. Le soglie si **calibrano su esempi etichettati**, non si indovinano guardando un’immagine sola. È lo stesso principio del MATCH (§2.5.2), applicato alla localizzazione.

#### **3.9. Altezza e larghezza del picco**

- Immagine lunare di $678\times 559$ pixel; il kernel è un ritaglio di $120\times 120$, mostrato alla stessa scala.
- La NCC recupera la **vera posizione** del ritaglio con punteggio **1,00**; la **somma grezza** è invece attratta dalla **regione lunare più luminosa**.
- L’**altezza del picco** dice **quanto è simile**.
- La **larghezza del picco** dice **con quanta precisione** è determinata la posizione.
- I **pattern lisci** (senza dettagli fini) possono creare **picchi larghi** anche con un massimo elevato.

![](imgs/Pasted%20image%2020261123151147.png)

> 💡 **Perché un pattern liscio dà un picco largo (nota didattica).** Se il kernel varia lentamente, spostandolo di qualche pixel la finestra resta molto simile a se stessa e la NCC cala poco: il punteggio è alto in un intorno ampio e la posizione è ambigua. Un kernel ricco di bordi e angoli cambia invece bruscamente appena lo si sposta, e il picco diventa appuntito. Per questo i punti di interesse dei descrittori locali (SIFT, Lezione 8) si scelgono proprio negli angoli.

##### **3.9.1. Un kernel ritagliato da un angolo: corrispondenza esatta ed echi più deboli**

- **Input**: scena di $1920\times 3251$ pixel; kernel di $99\times 94$ pixel (grigio), mostrato ingrandito ×4.
- **Output**: mappa NCC di $1920\times 3251$ (stessa dimensione, con padding).
- Il kernel è un estratto dell’**angolo sinistro** di un edificio: NCC $=1{,}00$ nella posizione del ritaglio (in arancione).
- **Echi**: altri angoli sinistri ottengono **0,81** e **0,76** (in verde acqua); il bordo sinistro della torre forma una **cresta**.
- Un **kernel esplorativo** di questo tipo è di fatto un **rilevatore di angoli o di blob**; una **soglia** (per esempio 0,7) decide quali risposte tenere. I filtri delle CNN fanno qualcosa di molto simile, ma **appreso**.

![](imgs/Pasted%20image%2020261123151207.png)

#### **3.10. Dalla correlazione locale alle reti neurali convoluzionali**

Ogni stadio convolutivo di una CNN fa scorrere **filtri appresi** sull’input e produce **mappe di risposta locali**. Nelle librerie comuni l’operazione implementata è la **cross-correlazione**; i pesi vengono **appresi dai dati**. I kernel appresi assomigliano ai kernel esplorativi del paragrafo precedente: **bordi, angoli, macchie di colore**.

L’esempio è il primo strato di **AlexNet**: **96 kernel** di $11\times 11\times 3$, divisi in due metà da 48 (la rete era distribuita su due GPU). In alto compaiono bordi in scala di grigi, in basso macchie di colore.

![](imgs/Pasted%20image%2020261123151219.png)

![](imgs/Pasted%20image%2020261123151234.png)

> 📌 **La CNN generalizza il template matching.** Invece di un kernel scelto a mano (il cono, l’angolo), la rete impara **centinaia di kernel** che rispondono ai pattern utili per il compito, e impila più stadi in modo che gli strati profondi rispondano a combinazioni di pattern (Lezione 21). La lettura di una feature map resta quella della mappa NCC: valore, posizione, numero di picchi.

#### **3.11. Come si legge il codice in questa lezione**

L’esame **non chiede codice esatto**. Chiede se si sa **leggere un frammento** breve e dire che cosa entra, che cosa esce e se il risultato è credibile. Per ogni frammento si applicano **quattro controlli**:

1. **Input**: tipo, forma (*shape*), intervallo dei valori;
2. **Output**: forma e significato di ogni oggetto restituito;
3. **Operazione**: normalizzazione, ribaltamento del kernel, convenzione *valid*/*full*;
4. **Validazione**: un grafico che rivelerebbe un errore.

> ⚠️ **Esercizio: leggere e rispondere.** Un collega scrive `d = a - b` su due immagini `uint8`. Che cosa può andare storto, e quale rappresentazione si dovrebbe usare per una differenza con segno?
>
> *Risposta.* In `uint8` i valori vanno da 0 a 255: una differenza negativa non è rappresentabile. NumPy la calcola **modulo 256** (wrap-around: $10-20$ diventa $246$), MATLAB e `cv2.subtract` la **saturano** a 0. In entrambi i casi si perde l’informazione su *quale* immagine contiene il dettaglio. Si deve convertire prima in un tipo **con segno**, per esempio `int16` (§6.4).

---

### **4. Sezione B — Fondamenti del retrieval: da un punteggio a una lista ordinata**


#### **4.1. La pipeline di retrieval**

Il retrieval ordina una gallery rispetto a una probe. La pipeline è:

1. la **probe** $q$ (un’immagine) viene trasformata nella sua **firma** $h(q)$;
2. si calcola la **distanza** da tutte le firme della gallery, $d_i=d\big(h(q),h(g_i)\big)$;
3. si **ordinano** le distanze in senso crescente;
4. si **tengono** i primi $k$, oppure quelli con $d\le\tau$;
5. si restituisce la **lista ordinata** (top-$k$).

Le firme della gallery $\{h(g_i)\}_{i=1}^N$ sono **calcolate una volta sola e memorizzate**.

![](imgs/Pasted%20image%2020261123151255.png)

> 📌 **Costo di una query.** Poiché la gallery è precalcolata, ogni query costa **una firma** e **$N$ distanze economiche** ($N$ = numero di immagini della gallery). Con firme di 64 bit una distanza di Hamming è uno XOR seguito da un conteggio dei bit: milioni di confronti al secondo anche su hardware modesto. Per gallery enormi si usano indici di ricerca approssimata dei vicini (ANN, Lezione 8).

#### **4.2. L’accuratezza del retrieval: precisione, recall, average precision**

**1. Il test.** Un **supervisore** (un esperto umano) giudica **in anticipo**, per ogni elemento della gallery, se è **rilevante** per la query oppure no. L’**insieme rilevante** $R$ (di $|R|$ elementi) è la *ground truth*: il sistema **non lo vede mai**, serve solo a **valutare** il ranking.

- **Input**: una lista ordinata di $N=10$ elementi e le etichette del supervisore ($|R|=4$).
- **Output**: $P@k$ e $R@k$ per $k=1,\dots,10$ (frazioni in $[0,1]$) e un solo numero, l’AP.

**2. Definizioni.**

1. **Ranking**: la gallery ordinata per distanza (output del sistema). **Top-$k$**: i suoi primi $k$ elementi.
2. $TP_k$: gli elementi del top-$k$ che il supervisore ha marcato come rilevanti.
3. **Precisione** $P@k=\dfrac{TP_k}{k}$: quanto è **pulita** la lista. **Recall** $R@k=\dfrac{TP_k}{|R|}$: quanta parte di $R$ è stata **trovata**.
4. **Average precision**:
$$AP=\frac{1}{|R|}\sum_{k\,:\ \text{elemento }k\text{ rilevante}}P@k,$$
un solo numero per l’intero ranking, che **premia gli elementi rilevanti messi per primi**.

**3. Esempio** ($N=10$, $|R|=4$).

| Rango $k$ | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
|---|---|---|---|---|---|---|---|---|---|---|
| giudizio del supervisore | ✓ | ✓ | × | ✓ | × | × | ✓ | × | × | × |
| $TP_k$ | 1 | 2 | 2 | 3 | 3 | 3 | 4 | 4 | 4 | 4 |
| $P@k$ | 1,00 | 1,00 | 0,67 | 0,75 | 0,60 | 0,50 | 0,57 | 0,50 | 0,44 | 0,40 |
| $R@k$ | 0,25 | 0,50 | 0,50 | 0,75 | 0,75 | 0,75 | 1,00 | 1,00 | 1,00 | 1,00 |

A $k=5$: $P@5=3/5=0{,}60$ e $R@5=3/4=0{,}75$. Gli elementi rilevanti sono ai ranghi 1, 2, 4 e 7, quindi

$$AP=\frac{P@1+P@2+P@4+P@7}{4}=\frac{1+1+0{,}75+0{,}57}{4}=\frac{3{,}32}{4}=0{,}83.$$

> 💡 **Come si comportano le due curve (nota didattica).** $R@k$ è **non decrescente** in $k$: allargando la lista non si perde nulla di ciò che si era trovato. $P@k$ può invece salire e scendere: cala a ogni elemento non rilevante (rango 3: da 1,00 a 0,67) e risale a ogni elemento rilevante (rango 4: 0,75). Per $k=N$ il recall vale sempre 1 e la precisione vale $|R|/N$. Un ranking perfetto (i quattro rilevanti ai primi quattro posti) avrebbe $AP=1$; la media delle AP su molte query si chiama **mAP** (*mean Average Precision*).

#### **4.3. Un buon ranking non è ancora una decisione: il margine della soglia**

- **Input**: le distanze dalla probe degli elementi che il supervisore ha giudicato **rilevanti** e di quelli **non correlati** (in bit, o adimensionali).
- **Output**: un solo numero, il **margine** (*gap*), nella stessa unità di $d$:
$$\text{gap}=\min d_{\text{non correlati}}-\max d_{\text{rilevanti}}.$$

- Se il margine è **positivo**, esiste una soglia $\tau$ che **separa** i due gruppi; se è **negativo**, i gruppi **si sovrappongono** e nessuna soglia funziona senza errori.
- Nell’esperimento #2 (§8.4) le stesse misure si comportano diversamente: **aHash** ha un margine di **+16 bit**, **dHash** di **−6 bit**.
- $P@k$, $R@k$ e AP rispondono a «**quanto è buono l’ordine**»; il margine risponde a «**posso accettare o rifiutare automaticamente**».

![](imgs/Pasted%20image%2020261123151320.png)

> 💡 **Ordine per query, soglia globale (nota didattica).** Con **una sola** probe, un margine positivo equivale a un ranking in cui tutti i rilevanti precedono tutti i non correlati; un margine negativo implica che almeno un elemento non correlato si infila davanti a un rilevante. La distinzione diventa davvero importante quando le probe sono **molte**: ogni query può avere un ordinamento perfetto, ma con distanze tipiche diverse (una probe ha i duplicati a 3 bit e gli estranei a 20, un’altra i duplicati a 18 e gli estranei a 30). Il ranking resta perfetto in entrambi i casi, mentre una **soglia unica** $\tau$ per tutte le query non esiste. Per questo $\tau$ va **calibrata su molte coppie etichettate**.

#### **4.4. Hash o embedding: stesso retrieval, firma diversa**

| | Hash | Embedding |
|---|---|---|
| **firma** | ridimensionamento, grigio, soglia → firma di **64 bit** | **rete neurale** → vettore in $\mathbb{R}^d$ |
| **distanza** | **Hamming**, in bit, da 0 a 64 | **coseno / euclidea**, adimensionale |
| **pregi** | **minuscolo, veloce, spiegabile** | **più grande, appreso** |
| **che cosa cattura** | **copie** e **modifiche lievi** | **significato simile** |

![](imgs/Pasted%20image%2020261123151336.png)

> 📌 **La logica resta identica.** Il ranking, il top-$k$ e la logica della soglia sono **gli stessi** con un hash e con un embedding: cambia soltanto il modo in cui si calcola la firma. È per questo che quanto si impara sugli hash si trasferisce direttamente ai moderni database vettoriali.

---

### **5. Sezione C — Leggere ed eseguire il codice: forme, offset e convenzioni**


#### **5.1. Leggere una chiamata di matching (OpenCV)**

```python
res = cv2.matchTemplate(field, kernel,
                        cv2.TM_CCOEFF_NORMED)           # (1)
_, max_val, _, max_loc = cv2.minMaxLoc(res)             # (2)
x, y = max_loc                                          # (3)
raw = cv2.matchTemplate(field, kernel,
                        cv2.TM_CCORR)                   # (4)
loc = res == maximum_filter(res, size=60)               # (5)
ys, xs = np.nonzero(loc & (res > 0.5))                  # (6)
print(res.shape, len(xs))                               # (414, 413) 10
```

| Riga | Che cosa fa |
|---|---|
| **(1)** | **mappa dei punteggi normalizzata**, modalità *valid*: $(623-210+1)\times(537-125+1)=414\times 413$ |
| **(2)** | il **miglior punteggio** e la **sua posizione** |
| **(3)** | `max_loc` $=(x,y)=$ (**colonna, riga**); in NumPy l’indicizzazione è invece `[riga, colonna]` |
| **(4)** | **somma grezza**: il picco deriva verso le zone luminose |
| **(5)** | tiene un pixel **solo se è il massimo** della sua finestra $60\times 60$ (soppressione dei non-massimi) |
| **(6)** | **soglia 0,5**: una coppia $(y,x)$ per ogni istanza |

- **Input**: campo $623\times 537$ e kernel $210\times 125$, entrambi `uint8` in grigio.
- **Output**: mappa $414\times 413$ in $[-1,1]$; miglior punteggio e $(x,y)$; **10 istanze** sopra 0,5 (una lista vuota significa «**non trovato**»).

> 💡 **Venti picchi o dieci istanze? (nota didattica).** Nel §3.8.2 la mappa *same*, con padding, mostrava venti picchi sopra 0,5; qui la mappa *valid* ne conta dieci. La differenza è coerente con le due convenzioni: in modalità *valid* il kernel deve stare interamente nel campo, quindi i coni troppo vicini ai bordi non hanno una posizione calcolata.

> ⚠️ **Leggere e rispondere.** Che cosa cambia con `size=300`? E con soglia 0,2?
>
> *Risposta.* Con `size=300` la finestra di soppressione è molto più grande del periodo dei coni: in ogni finestra sopravvive un solo massimo, e istanze vicine vengono **fuse**, quindi il conteggio cala. Con soglia 0,2 passano anche i picchi deboli, cioè **falsi positivi** su regioni che somigliano solo vagamente a un cono, quindi il conteggio cresce.

#### **5.2. Le librerie differiscono: verificare che cosa calcola davvero la chiamata**

| Chiamata | Normalizzazione | Dimensione dell’output | Nota |
|---|---|---|---|
| MATLAB `xcorr2(A,B)` | nessuna (grezza) | *full* | grande sulle zone luminose |
| SciPy `correlate2d` | nessuna (grezza) | *full* / *valid* / *same* | stessa somma grezza |
| OpenCV `TM_CCORR` | nessuna (grezza) | *valid* | somma grezza |
| OpenCV `TM_CCOEFF_NORMED` | media rimossa, scalata | *valid* | punteggio in $[-1,1]$ |
| scikit-image `match_template` | media rimossa, scalata | *valid* (o con padding) | stessi valori della chiamata OpenCV precedente |

**Si nomina prima la proprietà necessaria** (serve l’invarianza alla luminosità?), **poi si sceglie la chiamata**.

**Dov’è la patch?** Dimensione dell’output e origine delle coordinate cambiano con la modalità.

- **valid** ($414\times 413$, OpenCV): l’indice del picco è l’**angolo in alto a sinistra** della patch (indici da 0).
- **full** ($832\times 661$, MATLAB): $\text{angolo}=\text{picco}-\text{dimensione del kernel}+1$ (indici da 1).

**Esempio.** Una patch con angolo $(215,45)$ (da 0) dà in modalità *full* un picco **da 0** in $(424,169)$, cioè $215+210-1$ e $45+125-1$, e un picco **MATLAB** in $(425,170)$, perché MATLAB conta da 1. La dimensione *full* è $(623+210-1)\times(537+125-1)=832\times 661$.

#### **5.3. La stessa ricerca in MATLAB e in Python**

```matlab
% MATLAB (ricetta documentata; non eseguita in questa lezione)
c = normxcorr2(t, A);                  % full
[~, k] = max(abs(c(:)));
[ypeak, xpeak] = ind2sub(size(c), k);
yoff = ypeak - size(t, 1);
xoff = xpeak - size(t, 2);
```

```python
# Python (eseguito)
res = cv2.matchTemplate(A, t, cv2.TM_CCOEFF_NORMED)   # valid
yoff, xoff = np.unravel_index(res.argmax(), res.shape)
```

- Entrambe localizzano **lo stesso picco**; le convenzioni degli indici differiscono (**da 1 contro da 0**, ***full* contro *valid***).
- `max(abs(c))` in MATLAB accetta anche un **picco fortemente negativo** (pattern invertito); la riga Python no, perché `argmax` cerca solo il massimo con segno.
- L’esame chiede **quale dimensione dell’output e quale convenzione di offset** ha ciascuna chiamata, **non la sintassi**.

> 💡 **Perché i due offset coincidono (nota didattica).** In MATLAB `ypeak` è l’indice da 1 dell’angolo in basso a destra della patch nella mappa *full*; sottraendo `size(t,1)` si ottiene l’offset da 0 dell’angolo in alto a sinistra (con i numeri del §5.2: $425-210=215$). In Python `argmax` sulla mappa *valid* restituisce direttamente l’angolo in alto a sinistra da 0. `ind2sub` e `np.unravel_index` fanno la stessa cosa: convertono l’indice lineare del massimo in (riga, colonna).

#### **5.4. Scaling e leakage nel $k$-NN**

```python
from sklearn.preprocessing import StandardScaler
from sklearn.neighbors import KNeighborsClassifier
sc = StandardScaler().fit(X_train)                     # media e std SOLO dal TRAIN
knn = KNeighborsClassifier(n_neighbors=5)
knn.fit(sc.transform(X_train), y_train)
print(knn.score(sc.transform(X_test), y_test))         # accuratezza in [0,1]
```

**Leggere e rispondere.**

1. Perché lo scaler è stimato solo sul training set?
2. Che cosa cambia con `n_neighbors=1`?
3. Che cosa si controllerebbe se l’accuratezza fosse sospettosamente alta?

**Discussione.**

1. Il test set deve **restare non visto**: stimare lo scaler su tutti i dati fa trapelare le sue statistiche.
2. Decide il voto di **un solo punto**: la classificazione diventa **sensibile al rumore** (§2.6.2).
3. I **duplicati tra train e test** (Lezione 8): un esempio di test identico a uno di training ha distanza zero dal suo «gemello» e viene classificato correttamente per memoria, non per generalizzazione.

> 💡 **Il «fit» del $k$-NN (nota didattica).** `knn.fit` non stima alcun parametro: memorizza i vettori scalati e le loro etichette. Il calcolo vero avviene in `score`/`predict`, quando per ogni punto di test si cercano i 5 vicini tra i punti di training. È l’apprendimento *lazy* del §2.6.1 tradotto in codice.

---

### **6. Sezione D — Trova le differenze: una pipeline automatica**


#### **6.1. Il gioco**

Il classico gioco enigmistico presenta **due disegni quasi identici** in cui trovare tutte le differenze. Le avete trovate tutte? Ne siete sicuri? **Un programma può trovarle per noi?**

![](imgs/Pasted%20image%2020261123151410.png)

#### **6.2. Come automatizzare il compito?**

La pipeline ha cinque blocchi:

1. **input**: due immagini grigie $I_1$, $I_2$;
2. **allineare**: il picco della NCC fornisce lo spostamento;
3. **sottrarre** in un tipo **con segno**;
4. **sogliare** la differenza;
5. **raggruppare** i cambiamenti in **riquadri**, e produrre un’immagine con i riquadri da rivedere.

![](imgs/Pasted%20image%2020261123151422.png)

- Che cosa serve: il **concetto di cross-correlazione tra immagini** e poche righe di codice.
- I due disegni **non hanno la stessa dimensione**, quindi **non si possono sottrarre direttamente**.
- **Prima l’allineamento, poi il confronto**: questo ordine è tutta l’idea.

#### **6.3. Il contratto dell’intero sistema: input e output**

- **Input**: due immagini grigie `uint8`, $I_1$ ($736\times 555$) e $I_2$ ($691\times 505$), con valori 0–255 (valore del pixel, senza unità di misura); **una soglia** $T$ in livelli di grigio.
- **Output**: una **lista di regioni** in cui i disegni differiscono; **due matrici booleane** (dettagli *aggiunti* in $I_2$, dettagli *rimossi* da $I_1$); un’**immagine sovrapposta** (*overlay*).
- **Situazione iniziale**: due immagini che non si possono confrontare pixel per pixel. **Situazione finale**: **10 riquadri da rivedere** (ottenuti da 15 componenti connesse).

> ⚠️ **Tre avvertenze sul contratto.**
> - **Nomenclatura**: «aggiunto» e «rimosso» sono **relativi a $I_1$**; il programma **non sa quale disegno sia l’originale**.
> - Il programma **propone** differenze candidate; **una persona le conferma**.
> - **Che cosa può andare storto**: spostamento sbagliato, differenze di **scala** o di **rotazione**, **rumore sui bordi**, soglia inadatta. **Che cosa manca per una valutazione solida**: la **lista delle differenze vere** (*ground truth*), per contare **colpi** e **falsi allarmi**.

#### **6.4. Dati sperimentali e obiettivo**

| | Dimensione | Formato |
|---|---|---|
| **immagine 1** | $736\times 555$ | JPEG RGB → grigio `uint8` |
| **immagine 2** | $691\times 505$ | JPEG RGB → grigio `uint8` |

**Obiettivo.** Stimare la **traslazione** dell’immagine 2 dentro l’immagine 1, confrontare **solo il contenuto sovrapposto** e restituire **regioni candidate** per l’ispezione umana.

![](imgs/Pasted%20image%2020261123151502.png)

#### **6.5. Esperimento I — stimare la traslazione**

```python
score = cv2.matchTemplate(I1, I2, cv2.TM_CCOEFF_NORMED)
_, max_score, _, (dx, dy) = cv2.minMaxLoc(score)
aligned = I1[dy:dy + I2.shape[0], dx:dx + I2.shape[1]]
```

L’immagine più piccola $I_2$ fa da kernel e scorre dentro $I_1$. La mappa *valid* restituita dal codice è minuscola: $(736-691+1)\times(555-505+1)=46\times 51$ pixel, perché $I_2$ quasi riempie $I_1$. Il massimo vale **0,817** in $(dy,dx)=(18,25)$; la terza riga ritaglia da $I_1$ la finestra allineata, della stessa dimensione di $I_2$.

La slide mostra anche la **NCC completa**: tutti gli spostamenti con almeno il **25% di sovrapposizione**, una mappa di $1426\times 1059$; in grigio le zone non calcolate. Il codice restituisce solo la **finestra arancione** ($46\times 51$ pixel), mostrata ingrandita ×30.

![](imgs/Pasted%20image%2020261123151517.png)

> 💡 **Perché 0,817 e non 1 (nota didattica).** Le due immagini *non* sono uguali: proprio le differenze del puzzle abbassano la correlazione. Il picco resta comunque nettamente il massimo, perché la gran parte del disegno coincide. È lo stesso fenomeno degli «echi» del §3.9.1, letto al contrario: un punteggio alto ma non perfetto segnala un pattern quasi uguale.

#### **6.6. Esperimento II — sottrarre in un tipo con segno**

```python
delta = (aligned.astype(np.int16)
         - I2.astype(np.int16))
```

- $\Delta\in[-255,255]$: il **segno è conservato**.
- $\Delta>0$: inchiostro solo in $I_2$. $\Delta<0$: inchiostro solo in $I_1$.
- Una sottrazione in `uint8` **andrebbe in wrap-around**.

![](imgs/Pasted%20image%2020261123151531.png)

> 💡 **Perché il segno ha quel verso (nota didattica).** Nelle immagini grigie l’inchiostro nero ha valore basso e la carta bianca valore alto. Dove $I_2$ ha inchiostro e $I_1$ no, la finestra allineata di $I_1$ vale circa 255 e $I_2$ circa 0, quindi $\Delta=I_1-I_2>0$: il dettaglio è presente **solo in $I_2$**. Il caso opposto dà $\Delta<0$.

##### **6.6.1. Perché la sottrazione richiede un tipo con segno**

```python
a = np.array([10], dtype=np.uint8)
b = np.array([20], dtype=np.uint8)
print(a - b)                                     # [246]  wrap-around
print(a.astype(np.int16) - b.astype(np.int16))   # [-10]  corretto
print(cv2.subtract(a, b))                        # [0]    saturazione
```

**Leggere e rispondere.** (1) Perché 246? (2) Di quale risultato ci si fiderebbe per trovare entrambi i tipi di differenza? (3) Che cosa fa MATLAB con la sottrazione `uint8`?

**Discussione.** (1) $10-20=-10\equiv 246\pmod{256}$. (2) Quello **con segno**: il segno dice **quale immagine ha l’inchiostro**. (3) MATLAB **satura a 0**, come `cv2.subtract`; NumPy fa **wrap-around**.

> 📌 **Che cosa significa wrap-around.** Un `uint8` può memorizzare soltanto i 256 valori $0,\dots,255$. I risultati si calcolano **modulo 256**, come le ore su un orologio: sotto lo 0 si riparte da 255. Così $10-20=-10$ diventa $-10+256=246$; lo stesso accade sopra il massimo: $250+10=260$ diventa $4$. **Il risultato sembra valido ma è sbagliato, e nessun errore viene segnalato.**

![](imgs/Pasted%20image%2020261123151548.png)

> ⚠️ **Wrap-around e saturazione sono entrambi sbagliati, in modi diversi.** Il wrap-around trasforma una piccola differenza negativa in un valore **grande positivo**, quindi crea falsi «dettagli aggiunti»; la saturazione azzera tutte le differenze negative, quindi **cancella** i dettagli rimossi. Solo il tipo con segno conserva entrambe le informazioni.

#### **6.7. Esperimento III — dettagli aggiunti, dettagli rimossi**

```python
T = 127                                  # soglia, in livelli di grigio
added   = delta >  T                     # solo in I2
removed = delta < -T                     # solo in I1
print(added.sum(), removed.sum())        # 5571 3912
```

- La maggior parte dei pixel ha $\Delta=0$; i cambiamenti vivono nelle **code** della distribuzione oltre $\pm T$ (linee tratteggiate nell’istogramma).
- Il risultato sono **due matrici booleane** ($691\times 505$): **5571** pixel aggiunti e **3912** rimossi. Invertite, mostrano inchiostro nero su carta bianca.

![](imgs/Pasted%20image%2020261123151603.png)

![](imgs/Pasted%20image%2020261123151611.png)

> 💡 **Perché $T=127$ (nota didattica).** È circa metà della dinamica 0–255: una differenza deve corrispondere almeno a «mezzo passaggio» tra bianco e nero per essere considerata inchiostro. Le piccole differenze attorno allo zero vengono da **bordi** leggermente disallineati, dalla **compressione JPEG** e dal **rumore**, e devono restare sotto soglia. La scelta è empirica: va validata, come dice il §6.8.

#### **6.8. Esperimento IV — regioni candidate da rivedere**

- I parametri scelti producono **15 componenti connesse**; le componenti vicine vengono **fuse** in **10 riquadri** da rivedere.
- I **riquadri grandi** corrispondono a cambiamenti strutturali evidenti; i **riquadri piccoli** mostrano perché soglia e raggruppamento sono **scelte ingegneristiche**.
- Il programma restituisce **candidati**, non **verdetti semantici**: una persona, o un classificatore successivo, può accettarli o rifiutarli.
- Gli script completi saranno pubblicati sul sito del corso. **Soglia, raggruppamento e allineamento cambiano le regioni**: vanno validati per l’applicazione.

![](imgs/Pasted%20image%2020261123151624.png)

> ✅ **La pipeline in quattro passi.** **Allineare** con il picco NCC → **sottrarre** in un tipo con segno → **sogliare** in aggiunte e rimozioni → **raggruppare** le componenti in riquadri da rivedere. L’ordine conta: senza allineamento ogni pixel del disegno diventerebbe una falsa differenza.

---

### **7. Sezione E — Stesso strumento, altri segnali**


#### **7.1. Il ritardo tra due sensori**

- **Input**: due serie di $n=400$ campioni a **100 Hz** (1 campione = 10 ms).
- **Output**: $2n-1=799$ valori di correlazione; il **lag del picco** è il **ritardo** della serie B.

Il **rumore nasconde il ritardo**: a occhio il lag è difficile da individuare, mentre il picco della NCC lo trova: **+23 campioni = 230 ms** (dati sintetici).

![](imgs/Pasted%20image%2020261123151636.png)

**Da dove viene il suono?** Auricolari e telefoni fanno esattamente questo con i loro microfoni. In un **array di microfoni**, un fronte d’onda piano che arriva con angolo $\theta$ raggiunge il secondo microfono dopo aver percorso un tratto aggiuntivo $\Delta=d\sin\theta$, quindi con un ritardo

$$\tau=\frac{d\,\sin\theta}{c},$$

dove $d$ è la distanza tra i microfoni [m], $\theta$ l’angolo di arrivo e $c\approx 343$ m/s la velocità del suono. Con $d=0{,}10$ m e $\theta=30^\circ$:

$$\tau=\frac{0{,}10\cdot 0{,}5}{343}\approx 1{,}46\cdot 10^{-4}\ \text{s}\approx 0{,}15\ \text{ms},$$

che a **48 kHz** corrispondono a $1{,}46\cdot 10^{-4}\cdot 48\,000\approx 7$ campioni. Misurando il ritardo con la correlazione e invertendo la formula ($\theta=\arcsin(c\,\tau/d)$) si stima la **direzione di arrivo** del suono.

#### **7.2. Codice: da due serie a un ritardo in millisecondi**

```python
a = x - x.mean()
b = y - y.mean()
xc = np.correlate(b, a, "full")
lags = np.arange(-len(x) + 1, len(x))
lag = lags[xc.argmax()]
print(lag, lag * 10, "ms")          # 23 230 ms
```

- **Sottrarre le medie** è la versione 1D del passo della NCC (qui **senza scalatura**, perché interessa solo il lag, non il valore del picco).
- `"full"`: $2\cdot 400-1=799$ uscite.
- `lags`: lo spostamento di ogni uscita, da $-399$ a $+399$.
- `×10 ms`: la **conversione di unità fa parte della risposta**.

> ⚠️ **Leggere e rispondere.** Il campionamento passa a **50 Hz** e si trovano gli stessi 23 campioni. Qual è ora il ritardo?
>
> *Risposta.* $23\times 20\ \text{ms}=460\ \text{ms}$. Il lag in campioni è un **indice**; il ritardo richiede il **periodo di campionamento** $T_s=1/f_s$: $\text{ritardo}=\text{lag}\cdot T_s$.

#### **7.3. Stesso strumento, un prodotto: confronto con un campione di riferimento**

Nell’ispezione industriale il pezzo prodotto si confronta con un **campione di riferimento** (*golden sample*), un esemplare certificato come conforme. La pipeline è **la stessa del puzzle**:

1. **allineare**: picco NCC tra l’immagine di riferimento e quella del pezzo;
2. **sottrarre** in un tipo con segno;
3. **sogliare** la differenza (aggiunto / rimosso);
4. **raggruppare** le differenze vicine ed elencare le regioni.

- **Input**: immagine di riferimento e immagine del pezzo, stessa dimensione, grigio `uint8`.
- **Output**: lista delle **regioni difettose** (posizione, area in pixel) da far **confermare a una persona**.

L’esempio della slide riguarda **difetti di tessuti e maglieria**.

![](imgs/Pasted%20image%2020261123151705.png)

> ⚠️ **Il limite della variabilità naturale.** Anche tra **pezzi buoni** resta una differenza (piccole variazioni di trama, di posizione, di illuminazione). La soglia scambia **falsi allarmi** con **difetti mancati**: va scelta su **pezzi buoni e difettosi etichettati**, come ogni soglia di questa lezione. Il tema verrà ripreso nella Lezione 24 con il rilevamento di anomalie tramite autoencoder.

#### **7.4. Quando la correlazione è lo strumento sbagliato**

| Cambiamento nei dati | Picco NCC | Che cosa fare |
|---|---|---|
| traslazione, luminosità, contrasto | **sopravvive** | la NCC è costruita per questo |
| rotazione, forte cambio di scala | **crolla** | provare kernel ruotati/scalati, oppure feature apprese |
| prospettiva, deformazione | **cala e si allarga** | feature locali o modelli appresi |
| oggetto diverso, stessa classe | **picco basso** | una rappresentazione che codifichi il **significato**: gli embedding |

![](imgs/Pasted%20image%2020261123151720.png)

> 📌 **La similarità vale quanto le invarianze della rappresentazione.** Ogni riga della tabella dice la stessa cosa. **Confrontare pixel è già una rappresentazione**, con le sue invarianze (traslazione, guadagno, offset) e le sue fragilità (rotazione, scala, semantica). Le lezioni successive la sostituiscono con **rappresentazioni apprese**.

---

### **8. Sezione F — Il retrieval al lavoro**


#### **8.1. Perché il retrieval? Un contacalorie che guarda il piatto**

**La richiesta.** Una nota azienda di controllo del peso chiede di addestrare un modello: la fotocamera del telefono guarda un piatto e l’app ne restituisce le calorie.

- **Input**: una foto del piatto scattata con il telefono (pixel RGB).
- **Output**: classe del cibo o della ricetta, **quantità in grammi**, **calorie in kcal**.

![](imgs/Pasted%20image%2020261123151732.png)

Il compito si scompone in due sotto-problemi: **classificare** il cibo o la ricetta e **misurare** la quantità (g). Il deep learning può farlo, ma richiede un **modello**, una **topologia** e soprattutto un **dataset**. Ed è qui il problema: **non si hanno dati**. Servono immagini etichettate **molto specifiche** (piatti, porzioni, luci, angolazioni). Un’app semplice ha valore solo se si riesce a **costruire il dataset**. Come?

#### **8.2. Il problema: trovare molte foto di risotto**

- **Approccio classico**: scaricare un dataset etichettato, oppure costruirne uno nuovo **con il retrieval più un’etichettatura accurata**.
- Un **repository di base** (una ricerca di immagini) probabilmente **non basta**: serve l’accesso a una **collezione molto grande**, la **selezione** delle immagini necessarie e un’**etichettatura attenta**.
- Un **sistema di image retrieval** può fare la **selezione automaticamente**.
- Lo stesso vale per le **mucche**, o per la prossima applicazione.

![](imgs/Pasted%20image%2020261123151739.png)

#### **8.3. Esperimento di retrieval #1**

##### **8.3.1. Cercare le immagini di risotto**

La **probe** (una foto di risotto) passa per l’**estrazione delle feature** (aHash) e la **misura di similarità** (distanza di Hamming). La **gallery** ha il proprio **database delle feature**: l’aHash di ogni immagine, **calcolato una volta sola**. Il risultato corrispondente è `00001.jpg`, a **distanza 3 bit**.

- **Input**: una probe e una gallery di $N$ immagini (qui 12).
- **Output**: una distanza per ogni immagine della gallery; per `00001.jpg`, $d_H=3$ bit.

![](imgs/Pasted%20image%2020261123151748.png)

##### **8.3.2. Ordinare per più feature: una policy**

Un **metodo di ranking** (una **policy**) basato su **feature spiegabili** mantiene il sistema di retrieval **usabile e gestibile**. Per ogni immagine della gallery si calcolano quattro distanze dalla probe e, per ciascuna feature, si stila una classifica.

| Rango | aHash | dHash | colour hash | distanza tra istogrammi |
|---:|---|---|---|---|
| 1 | **`00001.jpg` (3)** | `23232.jpg` (2) | `12122.jpg` (3) | **`00001.jpg` (2)** |
| 2 | `93894.jpg` (10) | **`00001.jpg` (4)** | `99094.jpg` (5) | `23423.jpg` (3) |
| 3 | `34384.jpg` (13) | `34435.jpg` (8) | `34384.jpg` (7) | `9sd74.jpg` (6) |

Tra parentesi la distanza: in **bit** per gli hash, **adimensionale** per l’istogramma.

**Policy d’esempio**: scegliere il file che è **1°, 2° o 3° in almeno 3 feature**.

- **Input**: una probe e le distanze delle immagini della gallery per 4 feature.
- **Output**: un file selezionato, **`00001.jpg`**, nei primi tre posti di **tre** feature (aHash, dHash, istogramma); è la **vincitrice** del ranking.

![](imgs/Pasted%20image%2020261123151759.png)

> 💡 **Perché un voto tra feature (nota didattica).** Ogni feature ha invarianze diverse (§8.4): una foto ribaltata può sfuggire al dHash ma non all’istogramma, due piatti dello stesso colore possono avere forme diverse. Una regola di **voto** è più robusta di una sola distanza e resta **spiegabile**: si può dire perché un’immagine è stata scelta («è tra le prime tre per aHash, dHash e istogramma»). Si noti che `34384.jpg` compare due volte, ma solo in due feature: non supera la policy.

#### **8.4. Esperimento di retrieval #2: quali fattori cambiano le metriche?**

Ora ci si chiede come i **fattori** influenzano le metriche presentate: la **stessa probe**, modificata **un fattore alla volta**.

- **In verde**: modifiche della probe per fattore — **geometria** (ribaltamento, ritaglio all’80%, rotazione di 10°), **fotometria** (più luminosa, saturata), **degrado** (rumore, JPEG a qualità 10, sfocatura).
- **In rosso**: contenuti **non correlati** (un panda, altre due immagini di risotto, il puzzle).

- **Input**: una probe (un corgi), 12 immagini nella gallery (8 modifiche, 4 non correlate) e **5 misure**: aHash, dHash, pHash (Hamming, 0–64 bit), istogramma di colore (0–1), correlazione dei pixel ($1-\rho$, 0–2).
- **Output**: 5 distanze per immagine della gallery e 5 ranking.

![](imgs/Pasted%20image%2020261123151810.png)

> 💡 **Perché $1-\rho$ va da 0 a 2 (nota didattica).** La correlazione di Pearson $\rho$ tra i pixel sta in $[-1,1]$; trasformarla in $1-\rho$ la rende una **distanza** (piccola = simile): vale 0 per immagini identiche a meno di guadagno e offset, 1 per immagini scorrelate e 2 per un’immagine in negativo.

##### **8.4.1. Prevedere prima di eseguire**

Prima di eseguire lo script si compila a mano una tabella: per ogni modifica e ogni misura si scrive **S** (*sopravvive*: resta più vicina di qualunque intruso) oppure **F** (*fallisce*).

| Modifica | aHash | dHash | pHash | istogramma | pixel $\rho$ |
|---|:---:|:---:|:---:|:---:|:---:|
| più luminosa | ? | ? | ? | ? | ? |
| saturata | ? | ? | ? | ? | ? |
| ritagliata 80% | ? | ? | ? | ? | ? |
| ruotata 10° | ? | ? | ? | ? | ? |
| ribaltata | ? | ? | ? | ? | ? |
| JPEG q=10 | ? | ? | ? | ? | ? |

1. **Prevedere su carta**: scrivere S o F.
2. **Eseguire lo script e confrontare.** Spiegare ogni sorpresa: è lo scopo del gioco.

> 💡 **Su cui riflettere: perché un hash a 64 bit dovrebbe sopravvivere al JPEG ma non a un ribaltamento?** L’hash riduce l’immagine a $8\times 8$ pixel: gli artefatti JPEG sono dettagli fini, che la riduzione **media e cancella**, quindi i 64 bit cambiano poco. Il ribaltamento invece **sposta tutto il contenuto** da sinistra a destra: ogni bit confronta ora una regione diversa della scena, e la firma cambia in molte posizioni. Non è un compito di programmazione d’esame: si legge, si prevede, si spiega.

##### **8.4.2. Il nucleo dello script**

- **Input**: un’immagine (RGB, qualunque dimensione) → una firma di 64 bit; due firme → una distanza di Hamming (bit); un ranking → $P@k$.
- **Output**: la tabella delle distanze dell’esperimento e $P@k$ per ogni misura.

```python
def ahash_bits(img, size=8):
    g = img.convert("L").resize((size, size), Image.LANCZOS)
    a = np.asarray(g, dtype=float)
    return (a > a.mean()).astype(np.uint8).ravel()        # 64 bit

def hamming(b1, b2):
    return int(np.count_nonzero(b1 != b2))                # BIT diversi

def precision_at_k(m, k=8):
    top = sorted(table[m], key=table[m].get)[:k]          # prima la distanza più piccola
    return sum(t in relevant for t in top) / k
```

| Funzione | Che cosa fa |
|---|---|
| `ahash_bits` | converte in grigio (`"L"`), riduce a $8\times 8$ con filtro Lanczos, confronta ogni pixel con la media e srotola i 64 booleani |
| `hamming` | conta le posizioni in cui le due firme differiscono |
| `precision_at_k` | ordina la gallery per distanza crescente secondo la misura `m`, prende i primi $k=8$ e calcola la frazione di rilevanti (le 8 modifiche) |

L’aHash calcolato a mano **coincide con quello della libreria su 64 bit su 64**. Lo script completo sarà pubblicato sul sito del corso.

> 💡 **Perché $k=8$ (nota didattica).** La gallery contiene esattamente 8 modifiche rilevanti; con $k=|R|=8$, $P@8=1$ significa che tutte le modifiche occupano i primi otto posti, cioè un ranking perfetto. In questo caso particolare $P@8$ e $R@8$ coincidono.

##### **8.4.3. La matrice delle distanze**

- **Scuro = vicino.** Le righe verdi dovrebbero essere scure, quelle rosse chiare.
- **Ribaltata**: l’istogramma di colore vale **0,00** (ignora la geometria), mentre il dHash segna **35 bit**.
- **Più luminosa**: l’istogramma vale **0,69**, quasi come un intruso; gli hash restano bassi.

![](imgs/Pasted%20image%2020261123151839.png)

##### **8.4.4. Quale misura sopravvive?**

| Modifica della probe | aHash (bit) | dHash (bit) | pHash (bit) | istogramma (0–1) | pixel NCC ($1-\rho$) |
|---|---:|---:|---:|---:|---:|
| più luminosa (×1,5) | 3 | 7 | 8 | 0,69 | 0,04 |
| ritagliata 80% | 6 | 16 | 24 | 0,18 | 0,23 |
| ruotata 10° | 10 | 24 | 28 | 0,19 | 0,39 |
| ribaltata | 14 | 35 | 26 | 0,00 | 0,36 |
| **non correlata (minimo)** | **30** | **29** | **28** | **0,76** | **0,99** |

![](imgs/Pasted%20image%2020261123151859.png)

- **Nessuna misura è invariante a tutto**: scegliere la misura **è** scegliere le invarianze.
- **Istogramma di colore**: ribaltamento $=0$, ma **ingannato dalla luminosità**. **Hash**: l’opposto.

> 💡 **Leggere la tabella riga per riga (nota didattica).** Una modifica «sopravvive» se la sua distanza è **minore** di quella dell’intruso più vicino (ultima riga).
> - **aHash** sopravvive a tutte le modifiche riportate (massimo 14 contro 30): margine $30-14=+16$ bit, il valore citato nel §4.3.
> - **dHash** fallisce sul ribaltamento: $35>29$, margine $29-35=-6$ bit. Il dHash codifica le **differenze orizzontali** tra pixel adiacenti, cioè proprio l’informazione che il ribaltamento inverte.
> - **pHash** è al limite sulla rotazione: $28=28$, nessun margine.
> - **Istogramma**: sopravvive anche alla luminosità, ma con un margine di soli $0{,}76-0{,}69=0{,}07$; la luminosità sposta tutta la massa dell’istogramma.
> - **Pixel NCC**: la luminosità costa solo 0,04, perché la NCC ignora guadagno e offset (§3.5).
>
> La tabella riporta solo cinque righe: le modifiche «saturata», «rumore», «JPEG q=10» e «sfocatura» compaiono nella matrice della slide precedente ma non qui, quindi i margini calcolati valgono per le righe mostrate.

##### **8.4.5. Tocca a voi**

1. Prima di eseguire, scrivere quale misura sopravvive a ciascuna trasformazione.
2. Quale distanza di Hamming separa le varianti dalle immagini non correlate? Ne esiste una?
3. Aggiungere una trasformazione (per esempio una rotazione di 45°) e una nuova immagine non correlata. Che cosa si rompe per primo?
4. Che cosa renderebbe questa valutazione **solida**? **Molte probe**, **più immagini per classe**, **più semi casuali** per il rumore e una **gallery etichettata**.

> ⚠️ **Un solo corgi non è un esperimento.** Con una sola probe, ogni numero della tabella può dipendere da quella specifica foto. Le conclusioni generali (quale misura è robusta a che cosa) richiedono la ripetizione su molte probe e la stima della variabilità, esattamente come la validazione incrociata della Lezione 14.

#### **8.5. Deduplicazione per significato: gli embedding**

**Il compito.** Due record (immagini, testi) sono **duplicati candidati** quando i loro embedding sono vicini:

$$\cos(u,v)\ge\tau.$$

La soglia $\tau$ si **calibra su coppie etichettate**; il risultato è una **lista di candidati da rivedere**, non un verdetto.

La **similarità coseno** è

$$\cos(u,v)=\frac{u\cdot v}{\lVert u\rVert\,\lVert v\rVert},\qquad u\cdot v=\sum_i u_iv_i\ \ (\text{prodotto scalare}),\qquad \lVert u\rVert=\sqrt{\sum_i u_i^2}\ \ (\text{lunghezza}).$$

- **Input**: due embedding $u,v\in\mathbb{R}^d$.
- **Output**: $\cos(u,v)\in[-1,1]$ e una decisione: **duplicato** se $\ge\tau$.

Posizioni e valori di coseno della figura sono **illustrativi**, non calcolati da un modello.

![](imgs/Pasted%20image%2020261123151913.png)

> 💡 **Il coseno guarda la direzione (nota didattica).** Il coseno dipende solo dall’**angolo** tra i vettori, non dalla loro lunghezza: due embedding che puntano nella stessa direzione hanno coseno 1 anche se uno è il doppio dell’altro. Se gli embedding sono normalizzati a lunghezza unitaria, coseno e distanza euclidea danno lo stesso ordinamento, perché $\lVert u-v\rVert^2=2-2\cos(u,v)$.

##### **8.5.1. Perché non confrontare direttamente il testo?**

Tre descrizioni di prodotto:

- **A** = «*red running shoes for men*» (scarpe da corsa rosse da uomo);
- **B** = «*crimson jogging sneakers male*» (lo **stesso prodotto** di A, descritto con sinonimi);
- **C** = «*red running socks for men*» (calze da corsa rosse da uomo: **un altro prodotto**).

| Confronto | A–B (stesso prodotto) | A–C (prodotto diverso) | Ordine |
|---|---|---|---|
| **sulle lettere** (Hamming) | 26 lettere diverse | **3** lettere diverse | **sbagliato**: A sembra più vicino a C |
| **sugli embedding** | A e B **dentro** il cerchio di raggio $\tau$ | C **fuori** | **giusto** (illustrativo) |

![](imgs/Pasted%20image%2020261123151926.png)

> 📌 **Il significato non è l’ortografia.** «*shoes*» e «*socks*» differiscono in sole tre lettere (h/o, o/c, e/k), ma indicano oggetti diversi; «*shoes*» e «*sneakers*» non hanno quasi lettere in comune, ma indicano lo stesso oggetto. Lettere e parole non vedono i sinonimi; gli embedding confrontano il **significato**.

#### **8.6. Immagini e testi in uno spazio allineato**

Un **encoder di immagini** $\phi_{\text{img}}$ e un **encoder di testi** $\phi_{\text{text}}$ proiettano un’immagine $I$ e un testo $T$ in uno **spazio condiviso** $\mathbb{R}^d$. La foto di un cane e la frase «*a dog running on a beach*» finiscono **vicine** (similarità → 1); la frase «*an industrial robot welding a car*» finisce **lontana**.

- **Input**: un’immagine $I$ e un testo $T$.
- **Output**: due vettori $z_I,z_T\in\mathbb{R}^d$ e la loro similarità.

![](imgs/Pasted%20image%2020261123151938.png)



> ⚠️ **Stessa dimensione $\neq$ stesso spazio.** Sono due reti diverse, **comparabili solo perché addestrate insieme**.

**Addestramento.** Si usano coppie $(I_i,T_i)$ in cui $T_i$ descrive $I_i$; un batch contiene $N$ coppie. $S$ è una matrice $N\times N$ ($i$: immagine, $j$: testo):

$$S_{ij}=\cos\big(\phi_{\text{img}}(I_i),\,\phi_{\text{text}}(T_j)\big).$$

L’obiettivo è rendere **alta la diagonale** (le coppie vere) e **basso il resto**. Valori illustrativi, non prodotti da un modello:

![](imgs/Pasted%20image%2020261123151953.png)

> 💡 **Apprendimento contrastivo (nota didattica).** Questo schema è quello dei modelli contrastivi immagine-testo come CLIP: in ogni batch le $N$ coppie vere sono gli esempi positivi e le $N^2-N$ combinazioni sbagliate i negativi. La funzione di perdita (una cross-entropy sulle righe e sulle colonne di $S$, Lezione 22) spinge ogni immagine verso la propria descrizione e lontano dalle altre. Una volta addestrato, il sistema permette di cercare immagini con una frase, o di classificare un’immagine confrontandola con le descrizioni testuali delle classi.

##### **8.6.1. Lo spazio degli embedding: un’immagine concettuale**

La slide disegna a mano una proiezione 2D concettuale (non dati reali):

- in **verde**, i **duplicati veri** dentro il cerchio di raggio fissato da $\tau$ attorno alla query $q$;
- in **rosso**, un **vicino «vicino ma diverso»** (un prodotto diverso che però gli somiglia) che cade **anch’esso dentro** il cerchio: un **falso positivo**;
- gli embedding reali hanno **centinaia di dimensioni**; un’immagine 2D è solo un’intuizione.

![](imgs/Pasted%20image%2020261123152003.png)

#### **8.7. Quattro affermazioni sul retrieval: quali sono sbagliate?**

- **A.** Se $P@8=1{,}00$, una soglia sulla distanza classificherà correttamente ogni coppia.
- **B.** Un sistema di retrieval restituisce gli elementi più simili **nella rappresentazione scelta**, non necessariamente nel significato.
- **C.** Un hash e un embedding differiscono nel modo in cui si calcola la firma; l’ordinamento della gallery (ordinare le distanze) è lo stesso per entrambi.
- **D.** Una gallery più grande migliora sempre la lista top-$k$.

Decidere per ciascuna affermazione prima di leggere le risposte.

| | Verdetto | Perché |
|---|---|---|
| **A** | **sbagliata** | $P@8$ legge solo la **testa** del ranking. Una soglia richiede un **margine** tra le distanze della stessa classe e quelle di classi diverse: nell’esperimento #2 il margine dell’aHash era di +16 bit, quello del dHash di −6 bit. |
| **B** | **corretta** | Il sistema è **semantico quanto la sua rappresentazione**: un hash vede pixel, un embedding vede ciò che la rete ha imparato. |
| **C** | **corretta** | La pipeline ha sempre gli stessi passi: firma → distanza → ranking → soglia. Un hash dà una firma di 64 bit (Hamming), un embedding un vettore in $\mathbb{R}^d$ (distanza coseno). Ordinare le distanze è identico: non importa che cosa le ha prodotte. |
| **D** | **sbagliata** | Più elementi aggiungono più **quasi-corrispondenze** e più **costo**; la qualità dipende dalla rappresentazione e dalla policy. |

> ⚠️ **Un’incongruenza della slide di soluzione.** Per l’affermazione A la slide aggiunge che aHash e dHash «ordinano bene la gallery entrambi». Con i dati del §8.4.4, però, il ribaltamento del dHash (35 bit) è più lontano dell’intruso più vicino (29 bit): per quella probe il dHash ha almeno un intruso nei primi otto posti, quindi $P@8<1$. Il verdetto «A è sbagliata» resta corretto per la ragione del §4.3: il ranking si giudica per singola query, mentre la soglia deve funzionare per tutte le coppie contemporaneamente.

#### **8.8. Lavorare con un assistente: chiedere, eseguire, verificare**

La slide mostra due richieste a un agente di programmazione e due risposte **simulate**.

| | Richiesta | Risposta (simulata) |
|---|---|---|
| **1** | «Scrivi uno script Python che carichi la probe e le 12 immagini della gallery, calcoli aHash e dHash (64 bit) e stampi una tabella delle distanze di Hamming.» | «Ho scritto `lab.py`: `h = (a > a.mean()).ravel()` e `d = np.count_nonzero(h1 != h2)`. Ho stampato $12\times 2$ distanze.» |
| **2** | «Aggiungi una rotazione di 45° della probe e un’immagine non correlata. Riesegui e stampa $P@8$ e il margine della soglia per ogni misura.» | «Ho aggiunto le due immagini e rieseguito. Per l’aHash la copia ruotata è ora più lontana dell’immagine non correlata più vicina (margine $<0$).» |

> 📌 **Chiedere, eseguire, verificare.** Si chiede **una modifica piccola**, si **esegue lo script di persona** e si **confrontano i numeri stampati con la propria previsione**. L’assistente spiega; **i numeri decidono**. Le risposte sono simulate: il testo reale dipende dallo strumento. È la regola già vista nella Lezione 6: *AI-assisted* non significa *AI-trusted*.

#### **8.9. Riepilogo: query, gallery, punteggio, ranking**

- **Input**: una query $q$ e una gallery di $N$ elementi.
- **Output**: una lista ordinata; il top-$k$; oppure un **insieme accettato** (da 0 a $N$ elementi).

| Concetto | Definizione |
|---|---|
| **firma** $h(\cdot)$ | un hash (bit) o un embedding (vettore), calcolata **una volta per elemento** |
| **punteggio** $d_i=d\big(h(q),h(g_i)\big)$ | in bit o adimensionale; **piccolo = simile** |
| **ranking** | ordinare i $d_i$ in senso crescente; **top-$k$**: i primi $k$ elementi, **sempre $k$** |
| **soglia** $\tau$ | si accetta $g_i$ se $d_i\le\tau$: **qualunque numero di elementi, anche nessuno** |
| **insieme rilevante** $R$ | usato **solo per valutare**, **mai per ordinare** |

**Esempio** ($N=5$ elementi, distanza di Hamming in bit). Il top-2 è $\{g_1,g_4\}$. Con $\tau=5$ l’insieme accettato è lo stesso; con $\tau=9$ si aggiunge $g_5$; con $\tau=2$ è **vuoto**. Il ranking è lo stesso; **cambia la regola di decisione**.

![](imgs/Pasted%20image%2020261123152037.png)

> 💡 **Top-$k$ contro soglia (nota didattica).** Il top-$k$ restituisce **sempre** $k$ elementi, anche quando nessuno è davvero simile: è adatto a mostrare suggerimenti a una persona. La soglia restituisce un numero **variabile** di elementi, anche zero: è adatta a decisioni automatiche («esiste un duplicato?»). I due criteri si possono combinare: «i primi $k$, purché sotto $\tau$».

---

### **9. Punti principali**


> ✅ **Punti principali della lezione.**
> - **Similarità = rappresentazione + distanza + regola di decisione**; scegliere lo strumento è scegliere le **invarianze**.
> - La **cross-correlazione** restituisce una **mappa dei punteggi**: il valore (quanto è simile), la posizione (dove) e il numero di picchi (quante istanze). La **NCC ignora guadagno e offset**; le somme grezze no.
> - La mappa ha **sempre un massimo**: «trovato / non trovato» richiede una **soglia**. La **larghezza del picco** dice con quanta precisione si può collocare la corrispondenza.
> - Leggere il codice con **quattro controlli**: tipo e forma dell’input, forma dell’output, che cosa calcola davvero la chiamata (*valid* / *full*, indici da 0 o da 1), un grafico. **Sottrarre le immagini in un tipo con segno.**
> - La stessa pipeline (**allineare, sottrarre, sogliare, pulire**) trova le differenze nei puzzle e nei pezzi prodotti; la stessa correlazione trova il **ritardo tra sensori** (lag × periodo di campionamento).
> - **Il retrieval è ranking**: $P@k$, $R@k$ e AP giudicano l’**ordine**, il **margine della soglia** giudica la **decisione**; calibrare $\tau$ su coppie etichettate.
> - **$k$-NN**: scalare le feature, **stimare lo scaler solo sul training set**.
> - **Prospettiva** (non d’esame): gli **embedding sostituiscono la firma, non la logica**.

---

### **10. Appendice — La ricetta MATLAB**


#### **10.1. Il materiale (versione MATLAB)**

- MATLAB (ultima versione) e i file immagine della lezione in una cartella locale.
- Seguire il processo **passo per passo**; **non saltare alla soluzione**.
- Si incontreranno: formato e rappresentazione delle immagini, manipolazione, visualizzazione, similarità.
- Il **laboratorio Python** di questa lezione **sostituisce** il percorso MATLAB completo; i riquadri seguenti danno la **ricetta ridotta** e le differenze tra MATLAB e Python. Il percorso MATLAB completo dell’edizione precedente è riportato nel §11.2.

#### **10.2. Ricetta MATLAB: allineare, sottrarre, sogliare**

```matlab
I1 = rgb2gray(imread('puzzle1.png'));  I2 = rgb2gray(imread('puzzle2.png'));
c  = normxcorr2(I2, I1);                    % I2 fa da "template"
[~, k] = max(abs(c(:)));
[ypeak, xpeak] = ind2sub(size(c), k);
yoff = ypeak - size(I2,1);  xoff = xpeak - size(I2,2);    % spostamento da 0
A = int16(I1(yoff+1:yoff+size(I2,1), xoff+1:xoff+size(I2,2)));
d = A - int16(I2);                          % differenza con segno
mask1 = d < -127;  mask2 = d > 127;         % inchiostro in un solo disegno
```

| Riga | Ruolo |
|---|---|
| `rgb2gray(imread(...))` | carica le immagini e le converte in grigio `uint8` |
| `normxcorr2(I2, I1)` | NCC in modalità *full*; il **template** è l’immagine **più piccola**, cioè la sottoimmagine $f$ della lezione |
| `max(abs(c(:)))` | massimo del valore assoluto sulla mappa srotolata (accetta anche picchi negativi) |
| `ind2sub` | indice lineare → (riga, colonna) del picco |
| `yoff`, `xoff` | spostamento **da 0** dell’angolo in alto a sinistra |
| `I1(yoff+1:..., xoff+1:...)` | ritaglio allineato; `+1` perché MATLAB indicizza da 1 |
| `int16(...)` | conversione a un tipo con segno **prima** della sottrazione |
| `mask1`, `mask2` | maschere booleane dei pixel con inchiostro in un solo disegno, con la soglia ±127 del §6.7 |

> 💡 **Le maschere e il verso del segno (nota didattica).** Qui `d = I1_allineata - I2`, come `delta` nel §6.6: `mask2` ($d>127$) contiene l’inchiostro presente solo in $I_2$, `mask1` ($d<-127$) quello presente solo in $I_1$.

#### **10.3. MATLAB e Python: stessa idea, differenze chiave**

| Tema | MATLAB | Python (NumPy/OpenCV) |
|---|---|---|
| **gli indici partono da** | 1 | 0 |
| **ordine delle posizioni** | (riga, colonna) in `size`; i grafici usano $(x,y)$ | array `[riga, colonna]`; i punti OpenCV sono $(x,y)$ |
| **sottrazione `uint8`** | **satura** a 0 | NumPy fa **wrap-around**; `cv2.subtract` satura |
| **correlazione normalizzata** | `normxcorr2` (*full*) | `cv2.matchTemplate` (*valid*) |
| **dimensione dell’output** | $(M+m-1)\times(N+n-1)$ | $(M-m+1)\times(N-n+1)$ |

> ⚠️ **Verificare sempre la convenzione.** La posizione del picco differisce **della dimensione del template** tra *full* e *valid*: controllare sempre quale delle due restituisce la propria libreria.

---

### **11. Approfondimento — Materiale dell’edizione precedente**

Le slide 2026/27 hanno sostituito il laboratorio Colab sugli hash e il percorso MATLAB in due passi con gli esperimenti Python delle sezioni C–F e con la ricetta ridotta del §10. Il materiale precedente resta utile come esercitazione guidata e corrisponde ai file presenti nella cartella della lezione (`code/` e `images/`); i concetti sono gli stessi già trattati, per cui le figure corrispondenti sono quelle delle sezioni 3 e 6.

#### **11.1. Hash delle immagini in Colab**

Un esempio molto semplice con una libreria potente e facile da usare. I passi sono: **scaricare** le immagini, **caricarle** convertendole nello spazio colore corretto, **visualizzarle**, calcolare l’**aHash** e la **distanza di Hamming** bit a bit.

```python
!pip install imagehash
import cv2                      # OpenCV per caricare e gestire le immagini
import requests
with open('dog1.jpg', 'wb') as handler:
    handler.write(requests.get('https://scotti.di.unimi.it/img2/dog1.jpg').content)
# ... lo stesso per dog2.jpg, dog3.jpg, dog4.jpg e panda.jpg

from matplotlib import pyplot as plt
img_color1 = cv2.imread('dog1.jpg')
img_color1 = cv2.cvtColor(img_color1, cv2.COLOR_BGR2RGB)  # da BGR a RGB
plt.imshow(img_color1)
plt.show()
```

> ⚠️ **OpenCV legge in BGR (*Blue, Green, Red*, blu, verde, rosso).** `cv2.imread` restituisce i canali nell’ordine **blu, verde, rosso**, mentre matplotlib si aspetta **RGB**: senza la conversione `COLOR_BGR2RGB` le immagini appaiono con rossi e blu scambiati.

Con `imagehash` la sottrazione tra due hash restituisce direttamente la **distanza di Hamming**:

```python
import imagehash
from PIL import Image  # Python Imaging Library

hash1 = imagehash.average_hash(Image.open('dog1.jpg'))
hash2 = imagehash.average_hash(Image.open('dog2.jpg'))
print(hash1)            # 0000d0e8ffffffff
print(hash1 - hash2)    # distanza di Hamming tra i due hash
```

Il notebook ripete il calcolo con `imagehash.phash`. Risultati salvati negli output, con l’originale `dog1.jpg` confrontato con le altre:

| Confronto con l’originale | Immagine | aHash | pHash |
|---|---|---:|---:|
| **ribaltata** | `dog2.jpg` | 14 | 26 |
| **saturata** | `dog3.jpg` | 2 | 4 |
| **ritagliata** | `dog4.jpg` | 7 | 26 |
| **panda** (immagine diversa) | `panda.jpg` | 40 | 30 |

Gli hash a 64 bit dell’originale sono `0000d0e8ffffffff` (aHash) e `d94d2c351d51dccc` (pHash).

> 💡 **Leggere la tabella (nota didattica).** Su 64 bit, due immagini senza relazione differiscono in media in circa **32** bit. La **saturazione** cambia i colori ma non la struttura: entrambi gli hash restano quasi uguali (2 e 4 bit). Il **ribaltamento** e il **ritaglio** spostano il contenuto: per il pHash la distanza (26) è quasi quella del panda (30). L’aHash, in questo esempio, separa meglio le varianti (7–14) dal panda (40): è lo stesso comportamento osservato nell’esperimento #2 del §8.4.

#### **11.2. Il percorso MATLAB in due passi**

Il laboratorio precedente usava due script: `lesson_09_code_STEP1.m` (**trovare una foglia** dentro il disegno, con template `find_difference_image_1.jpg` e sottoimmagine `find_difference_image_3.jpg`) e `lesson_09_code_STEP2.m` (**trovare le differenze**, con sottoimmagine `find_difference_image_2.jpg`).

##### **11.2.1. Caricare, visualizzare, invertire**

```matlab
img_template = imread('find_difference_image_1.jpg');
img_subimage = imread('find_difference_image_3.jpg');
whos
h1 = figure;
subplot(1,4,1)
imshow(img_template, [])
axis on
title('template')

img_template_gray = rgb2gray( img_template );
img_subimage_gray = rgb2gray( img_subimage );
img_template_gray = max(img_template_gray ) - img_template_gray  ;   % inversione
img_subimage_gray = max(img_subimage_gray ) - img_subimage_gray  ;
```

| Istruzione | Effetto |
|---|---|
| `imread` | immagine **RGB** in `uint8`, intervallo 0–255 |
| `whos` | variabili del workspace con dimensioni e tipo |
| `h1 = figure` | l’**handle** del grafico, per riaprirlo e aggiungere curve |
| `imshow(img, [])` | le parentesi quadre attivano la **scala automatica** tra minimo e massimo |

**Perché invertire.** Nella correlazione grezza contribuiscono alla somma i **valori alti**; nei disegni i valori alti sono quelli dello **sfondo bianco**, la parte meno informativa. Invertendo, il disegno diventa bianco e lo sfondo nero, e il picco emerge senza la «collina» prodotta dallo sfondo. È lo stesso problema che la NCC risolve alla radice (§3.6).

> ⚠️ **`max` su una matrice.** `max(A)` restituisce il **massimo di ogni colonna**; la forma sicura per un’inversione globale è `max(A(:)) - A` oppure, per `uint8`, `255 - A`.

##### **11.2.2. Media, `xcorr2` e picco**

```matlab
img_template_gray_norm = img_template_gray - mean(mean(img_template_gray)) ;
img_subimage_gray_norm = img_subimage_gray - mean(mean(img_subimage_gray)) ;
crr = xcorr2(img_template_gray_norm , img_subimage_gray_norm);
[ssr,snd] = max( crr(:) );
[ij,ji] = ind2sub(size(crr),snd);
surf(crr); shading interp; hold on
plot3( ji, ij, ssr,'go', 'MarkerSize', 15, 'LineWidth',3)
```

Dopo la sottrazione della media l’immagine in `uint8` ha **media 20,2**, **minimo 0** e **massimo 229**: i pixel sotto la media vengono **troncati a 0**, perché `uint8` non ammette negativi (è la saturazione del §6.6.1). `xcorr2` calcola la correlazione **grezza** in modalità *full*: con template $M\times N$ e sottoimmagine $P\times Q$ produce una matrice $(M+P-1)\times(N+Q-1)$, e il picco $(ij,ji)$ indica l’**angolo in basso a destra** della sottoimmagine.

**Controllo finale e immagine delta.**

```matlab
img_diff = img_template_gray ;                    % copia, per avere i bordi
img_diff(ij:-1:ij-size(img_subimage_gray,1)+1, ji:-1:ji-size(img_subimage_gray,2)+1) = ...
    rot90(img_subimage_gray,2) ;                  % notazione "non d'esame"
img_delta = double(img_template_gray) - double(img_diff);   % cast prima di sottrarre
```

Gli indici scorrono **all’indietro** e la sottoimmagine è ruotata di **180°**: i due ribaltamenti si compensano e la sottoimmagine viene copiata dritta. Il cast a `double` permette valori negativi.

##### **11.2.3. Istogramma, soglie e compiti**

```matlab
hist( img_delta(:), [2*255]);                 % 510 intervalli: da -255 a +255
[h_y, h_x] = hist( img_delta(:), [2*255]);
semilogy(h_x, h_y);                           % scala logaritmica
additions = (img_delta > 0.5 * max(img_delta(:)) ) ;
deletions = (img_delta < 0.5 * min(img_delta(:)) );
```

| Zona dell’istogramma | Significato |
|---|---|
| valori **negativi** | pixel **cancellati** |
| valore **zero** | pixel **invariati**, la grande maggioranza |
| valori **positivi** | pixel **aggiunti** |
| valori piccoli attorno a zero | **bordi** e **rumore** |

La scala logaritmica rende visibili le poche barre lontane dallo zero; le due soglie al 50% del massimo e del minimo separano aggiunte e cancellazioni da bordi e rumore.

**Compiti per casa** (esperimenti nel file `lesson_09_code_STEP2.m`):

| Esperimento | Input di `xcorr2` | Che cosa mostra |
|---|---|---|
| `crr4` | template con **se stesso** | autocorrelazione: massimo al centro, superficie simmetrica (§3.8.1) |
| `crr2` | template contro `moon.tif` | due immagini **diverse**: nessun picco dominante |
| `crr3` | `moon.tif` contro un suo **ritaglio** | una parte dentro il tutto; il rapporto `max3/max2` quantifica il vantaggio del picco vero |
| `crr5` | `ice_creams.jpg` con se stessa | autocorrelazione di oggetti ripetuti: reticolo di picchi |
| `crr6` | `ice_creams.jpg` contro un singolo gelato | un kernel che trova più istanze (§3.8.2) |
| `crr6` | `ice_creams.jpg` contro `panda.jpg` | un kernel che **non** corrisponde a nulla, ma ha comunque un massimo (§3.8.3) |

> ⚠️ **Il file con prefisso `__no__`.** `__no__lesson_09_code_STEP2_finding_similarity.m` è una variante non di riferimento del passo 2; i file da seguire sono `STEP1` e `STEP2`. Le immagini richieste vanno nella stessa cartella del codice (nel materiale sono in `images/`).

---

### **12. Distinzioni da non confondere**


| Coppia | Differenza chiave |
|---|---|
| **feature progettata** $\phi_d$ / **embedding appreso** $\phi_\theta$ | regola scelta dall’ingegnere / parametri ottimizzati dall’addestramento |
| **CLASSIFY** / **MATCH** / **RANK** | etichetta di classe / accetta-rifiuta con soglia / lista ordinata top-$k$ |
| **$k$ piccolo** / **$k$ grande** nel $k$-NN | segue il rumore / liscia la frontiera |
| **cross-correlazione** / **convoluzione** | kernel non ribaltato / kernel ribaltato di 180°; le CNN calcolano correlazioni |
| **correlazione grezza** / **NCC** | premia i pixel luminosi / ignora guadagno e offset, valori in $[-1,1]$ |
| **altezza** / **larghezza** del picco | quanto è simile / con quanta precisione è localizzato |
| **valid** / **same** / **full** | solo sovrapposizioni complete / una posizione per pixel / ogni sovrapposizione |
| **OpenCV `(x, y)`** / **NumPy `[riga, col]`** | colonna, riga / riga, colonna |
| **wrap-around** / **saturazione** | NumPy `uint8`: $10-20=246$ / MATLAB e `cv2.subtract`: $10-20=0$ |
| **lag in campioni** / **ritardo** | indice / indice × periodo di campionamento |
| **$P@k$** / **$R@k$** | frazione pulita della lista / frazione trovata di $R$ |
| **ranking** / **margine della soglia** | qualità dell’ordine / possibilità di decidere automaticamente |
| **top-$k$** / **soglia $\tau$** | sempre $k$ elementi / numero variabile, anche nessuno |
| **hash** / **embedding** | piccolo, veloce, copie e modifiche lievi / appreso, significato simile |
| **Hamming sulle lettere** / **coseno sugli embedding** | ortografia / significato |
| **stessa dimensione** / **stesso spazio** | non basta / richiede encoder addestrati insieme |
| **probe** / **gallery** | elemento interrogato / archivio in cui cercare |
