# **Lezione 6: Progettazione di modelli di machine learning per applicazioni biometriche**

---

### **0. Panoramica della lezione**

_(slide pp. 1–4)_

La Lezione 6 prosegue la Lezione 5 passando dai concetti alla **progettazione**. L'outline:

1. **progettazione di modelli di ML**: flusso di lavoro, caratteristiche dei modelli da considerare nella scelta, consigli riassuntivi;
2. introduzione alla **Computational Intelligence** (CI);
3. **reti neurali feed-forward**;
4. **curse of dimensionality**;
5. **modelli tradizionali di AI** (non CI): classificatore ai primi vicini **kNN** e **albero decisionale**;
6. caratteristiche dei modelli di **deep learning**.

Alla lezione è associata una **dispensa** (*Introducing Deep Learning with MATLAB*, MathWorks, 15 pagine), ricostruita nel §12.

---

### **1. Il Nobel 2024 per la fisica: Hopfield e Hinton**

_(slide pp. 5–6)_

Il **Premio Nobel per la fisica 2024** è stato assegnato a **John Hopfield** e **Geoffrey Hinton** «per le scoperte e invenzioni fondamentali che consentono l'apprendimento automatico con reti neurali artificiali».

| Scienziato | Contributo (slide) |
|---|---|
| **John Hopfield** | una rete che **salva e ricrea schemi**. I nodi si possono immaginare come **pixel**. La rete sfrutta la fisica dei materiali descritti dallo **spin atomico** (ogni atomo è un piccolo magnete): la rete è descritta in modo equivalente all'**energia** di un sistema di spin, e viene addestrata trovando valori delle connessioni tali che le immagini salvate abbiano **bassa energia**. Data un'immagine **distorta o incompleta**, la rete aggiorna i nodi in modo che l'energia diminuisca e **«cade»** passo dopo passo nell'immagine salvata **più simile** |
| **Geoffrey Hinton** | ha usato la rete di Hopfield come base per la **macchina di Boltzmann**, una **rete neurale ricorrente stocastica** che impara a riconoscere elementi caratteristici nei dati, con strumenti della **fisica statistica**. Viene addestrata con esempi molto probabili durante il funzionamento; può **classificare** immagini o **generare nuovi esempi**. L'introduzione dello **strato nascosto** ha aumentato la capacità di rappresentazione, segnando la transizione verso le strutture alla base del **deep learning** moderno |

> 📌 **Rete di Hopfield come memoria associativa.** Per ogni pattern memorizzato esiste uno **stato stabile**; un'immagine in ingresso, anche rumorosa, iterando cade nel corrispondente stato stabile. Le equazioni che legano i neuroni pongono però **limiti alla capacità rappresentativa**: il numero di stati stabili, cioè di immagini di riferimento memorizzabili, è limitato.

> 💡 **Il legame con la biometria.** Recuperare il pattern memorizzato più simile a un ingresso rumoroso è esattamente il problema del riconoscimento: un campione biometrico è una versione rumorosa del template. Il limite di capacità spiega perché questi modelli non si usano direttamente per database di migliaia di identità.

<!-- SOURCE_VISUAL id="TAB06-V01" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="5" type="illustrazione" description="Rete di Hopfield: nodi come pixel, stati stabili, immagine rumorosa che cade nello stato memorizzato" reason="Illustra il funzionamento energetico della rete" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB06-V02" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="6" type="illustrazione" description="Macchina di Boltzmann con ingressi e strato nascosto" reason="Mostra l'introduzione dello strato nascosto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte A — Progettare un modello di ML**

### **2. Flusso di lavoro e scelta della tecnica**

_(slide pp. 7–14)_

#### **2.1 Il workflow del ML**

_(slide pp. 7–9)_

L'obiettivo resta quello della Lezione 5: ottenere un **modello configurato** (un algoritmo). Il **ML in pratica** segue un ciclo:

1. **comprensione del dominio**, conoscenze pregresse e obiettivi;
2. **integrazione, selezione, pulizia e pre-elaborazione** dei dati;
3. **modelli di apprendimento**;
4. **interpretazione** dei risultati;
5. **consolidamento e distribuzione** delle conoscenze scoperte;
6. **ciclo**: si ricomincia con quanto appreso.

Esistono **ambienti completi** che supportano tutti i passi 1–5 (p. 9).

<!-- SOURCE_VISUAL id="TAB06-V03" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="7" type="diagramma a flusso" description="Workflow del machine learning fino al modello configurato" reason="Il ciclo è rappresentato graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB06-V04" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="9" type="screenshot" description="Ambienti software completi per tutti i passi del workflow" reason="La slide contiene solo immagini degli ambienti" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2 Scegliere la tecnica di ML giusta**

_(slide pp. 10–11)_

- La selezione richiede una **conoscenza approfondita** sia del **problema** sia delle **tecniche** considerate.
- I problemi reali possono richiedere una **combinazione di tecniche** (AI e non AI): **approcci ibridi**.

Procedura di selezione:

1. **studiare i requisiti** del compito;
2. valutare quale tecnica li **soddisfa più completamente**;
3. **classificare** le soluzioni possibili per **applicabilità**, **costo**, **prestazioni**, …

#### **2.3 Caratteristiche del modello da considerare**

_(slide p. 12)_

| Caratteristica | Domanda di progetto |
|---|---|
| *Accuracy* | quanto sbaglia? |
| *Explainability* | si capisce perché decide così? |
| *Response speed* | quanto è veloce in esercizio? |
| *Scalability* | regge l'aumento di dati e utenti? |
| *Compactness* | quanto occupa? |
| *Flexibility* | si adatta a varianti del problema? |
| *Embedability* | si può integrare in un dispositivo? |
| *Ease of use* | è semplice da usare? |
| *Learning curve* | quanto costa impararlo? |
| *Tolerance for complexity* | gestisce problemi complessi? |
| *Tolerance for noise in data* | resiste al rumore? |
| *Tolerance for sparse data* | funziona con pochi dati? |
| *Independence from experts* | serve un esperto di dominio? |
| *Development speed* | quanto rapidamente si sviluppa? |
| *Computing ease/difficulty* | quanto costa calcolarlo? |

> 💡 **In biometria contano in modo particolare** *embedability* e *compactness* (match on card, match on sensor, smartphone: Lezione 1), *response speed* (identificazione su grandi DB: Lezione 2) e *tolerance for noise* (acquisizioni reali). Un modello accuratissimo ma troppo pesante per il dispositivo non è una soluzione.

#### **2.4 La tecnica «migliore» e il rasoio di Occam**

_(slide pp. 13–14)_

Un problema si risolve **in molti modi**. Anche se due tecniche danno soluzioni di qualità simile, **abbinare la tecnica giusta** al problema fa risparmiare tempo e risorse. Una tecnica ottimale:

- **contiene tutte le informazioni richieste**;
- **soddisfa tutti gli altri criteri** necessari;
- **utilizza tutte le conoscenze disponibili** (utili).

> 📌 **Rasoio di Occam.** «**Si fa inutilmente con molte cose ciò che si può fare con poche cose.**»

Effetti pratici del rasoio di Occam nella progettazione:

1. **non perdere tempo**, né mentale né di elaborazione (i modelli «impressionanti» costano);
2. usare **prima i modelli classici** dei modelli neurali, per esempio **kNN** (classificatore ai primi vicini) e **DTREE** (albero decisionale);
3. se servono modelli neurali, **iniziare con pochi neuroni** e reti **non deep**;
4. **aumentare il numero di strati** se serve;
5. passare ai **modelli deep** (molti strati e molti neuroni) **solo se** i passi precedenti dimostrano che è **necessario**.

> ⚠️ **Perché partire dal semplice.** Un modello semplice fornisce una **linea di base**: se un kNN raggiunge già l'accuratezza richiesta, una rete deep aggiunge costo, tempo di allenamento e rischio di overfitting senza benefici. Se invece il modello complesso migliora di poco, la differenza potrebbe dipendere dal caso e non dal modello.

---

### **3. Usare il ML in biometria**

_(slide pp. 15–22)_

#### **3.1 Due possibilità**

_(slide p. 15)_

| Opzione | Requisiti |
|---|---|
| **1. Usare modelli già allenati** per uno scopo specifico (face detection, …) | nessun allenamento |
| **2a. Allenamento completo** | soprattutto per le reti deep servono **moltissimi dati** e **moltissima potenza di calcolo** |
| **2b. Allenamento parziale** (tipicamente possibile con i modelli deep) | **meno dati e risorse**, ma bisogna seguire **procedure corrette per la biometria** |

#### **3.2 Quattro punti importanti**

_(slide pp. 16–20)_

1. **Seguire un corretto flusso di lavoro**, per non perdere tempo o produrre risultati scadenti. Non si saltano passaggi e non si fa un solo giro: è molto meglio **pianificare** e investire tempo in queste attività (p. 17).
2. **Usare partizioni dei dati corrette per la biometria**, come la **L(NP)O**, e non solo quelle fornite dalla libreria o dal tool. Con la **L(NP)O con MIX** si **forza** il modello a usare le **caratteristiche discriminanti generali** (biometriche) tra gli individui, e non caratteristiche singole caso per caso (p. 18).
3. **Preferire soluzioni semplici** e partire dal semplice, secondo il rasoio di Occam (p. 19).
4. **Usare tecniche avanzate** come il **transfer learning**, in particolare il **fine tuning di CNN pre-allenate** (p. 20).

> ⚠️ **Il punto 2 è specifico della biometria.** Le funzioni di cross-validation delle librerie dividono gli **esempi** a caso. In biometria gli esempi della stessa persona devono stare **tutti** nel training oppure **tutti** nella validazione, come visto per LOPO nella Lezione 5, altrimenti l'errore stimato è ottimistico.

#### **3.3 I driver dell'AI e il cervello del progettista**

_(slide pp. 21–22)_

La slide p. 21 presenta i **tre principali driver** del progresso dell'AI e degli algoritmi, specialmente per il deep learning (tipicamente dati, potenza di calcolo e algoritmi; si veda anche §11.5).

> 📌 **Il quarto driver è il più importante: il cervello del progettista.** Si esprime in:
> - **conoscenza a priori**;
> - **selezione dei dati**;
> - **filtraggio e miglioramento** dei dati;
> - **scelta del modello**;
> - **scelta della tecnica di apprendimento**;
> - **progettazione degli esperimenti**;
> - **evitare la forza bruta**;
> - **sistemi ibridi**;
> - ***divide et impera***.

<!-- SOURCE_VISUAL id="TAB06-V05" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="21" type="infografica" description="I 3 principali driver dell'AI e degli algoritmi, specialmente per il deep learning" reason="I tre driver sono indicati solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte B — Modelli induttivi per il ML**

### **4. Computational Intelligence**

_(slide pp. 23–25)_

> 📌 **Computational Intelligence (IEEE Computational Intelligence Society).** *Theory, design, application, and development of biologically and linguistically motivated computational paradigms emphasizing neural networks, connectionist systems, genetic algorithms, evolutionary programming, fuzzy systems, and hybrid intelligent systems in which these paradigms are contained.*

I **sistemi connessionisti** rappresentano i fenomeni mentali o comportamentali come **processi emergenti** di reti interconnesse di **unità semplici**.

Le principali tecniche di CI:

| Tecnica | Esempi |
|---|---|
| **Reti neurali** | deep learning, reti convoluzionali |
| **Logica fuzzy** | ragionamento con gradi di verità |
| **Calcolo evolutivo** | algoritmi genetici, programmazione evolutiva |
| **Sistemi ibridi** | combinazioni delle precedenti |

La slide ricorda che questi metodi sono approfonditi nei corsi di *Artificial Intelligence* e *Intelligent Systems for Industry, Supply Chain and Environment*.

> ⚠️ **ML non significa CI.** kNN e alberi decisionali (§7–8) sono modelli **induttivi** di ML ma **non** di Computational Intelligence: non si ispirano a paradigmi biologici o linguistici.

---

### **5. Reti neurali artificiali**

_(slide pp. 26–37)_

#### **5.1 Applicazioni tipiche in biometria**

_(slide pp. 26–27)_

Le reti neurali sono **uno dei modelli di ML più applicati in biometria**. Applicazioni tipiche: **predizione del comportamento**, stima di **età e peso**, **soft biometrics**, **profilazione biometrica**, **riconoscimento biometrico**.

<!-- SOURCE_VISUAL id="TAB06-V06" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="27" type="infografica" description="Applicazioni tipiche delle reti neurali: behavior prediction, age and weight, soft biometrics, biometric profiling, recognition" reason="Mappa visiva delle applicazioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2 Dal neurone biologico a quello artificiale**

_(slide pp. 28–30)_

L'ispirazione originale era il **cervello umano**; oggi l'enfasi è sull'**utilità come strumento computazionale**.

| Rete neurale biologica | Rete neurale artificiale |
|---|---|
| soma | neurone |
| dendrite | ingresso |
| assone | uscita |
| sinapsi | peso |

> 📌 **Rete feed-forward.** Struttura **a strati** composta da elementi di elaborazione **omogenei e semplici**, di solito **non lineari**. Tutta l'elaborazione è **locale** a un elemento ed è **asincrona**.

<!-- SOURCE_VISUAL id="TAB06-V07" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="29" type="schema di confronto" description="Parallelismo tra rete neurale biologica (soma, dendrite, assone, sinapsi) e artificiale (neurone, input, output, peso)" reason="Confronto visivo delle strutture" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.3 Il modello del neurone e il percettrone**

_(slide pp. 31–33)_

Il neurone è una **semplice unità di calcolo**: riceve segnali $x_1, \dots, x_n$ attraverso pesi $w_1, \dots, w_n$, calcola la **somma pesata** e la confronta con una **soglia** $\theta$.

$$X = \sum_{i=1}^{n} x_i\, w_i \qquad\qquad Y = \begin{cases} -1 & \text{se } X < \theta \\ +1 & \text{se } X \geq \theta \end{cases}$$

- Se l'**ingresso netto** è inferiore alla soglia, l'uscita è $-1$.
- Se è **maggiore o uguale** alla soglia, il neurone si **attiva** e l'uscita è $+1$.
- Questa funzione di attivazione si chiama **sign function**.

Il **percettrone a singolo strato con due ingressi** (p. 33) è composto da un **combinatore lineare** ($x_1 w_1 + x_2 w_2$) seguito da un **hard limiter** con soglia $\theta$.

> 💡 **Interpretazione geometrica.** Con due ingressi la condizione $x_1 w_1 + x_2 w_2 = \theta$ è l'equazione di una **retta**: il percettrone separa il piano in due semipiani e classifica i punti in base al lato in cui cadono. È esattamente la regola lineare cane/gatto della Lezione 5, con i pesi al posto delle costanti scelte a mano.

> ⚠️ **Il limite del percettrone singolo.** Una retta separa solo classi **linearmente separabili**. Un problema come lo XOR (due classi disposte a scacchiera) non è risolvibile con un solo neurone: servono **strati nascosti** (§5.5).

<!-- SOURCE_VISUAL id="TAB06-V08" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="31" type="schema del neurone" description="Neurone con segnali di ingresso x1..xn, pesi w1..wn e segnali di uscita Y" reason="Schema di riferimento del neurone artificiale" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB06-V09" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="33" type="schema" description="Percettrone a singolo strato con due ingressi: combinatore lineare, soglia theta, hard limiter" reason="Mostra la struttura del percettrone" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.4 Funzioni di attivazione**

_(slide p. 34)_

**Ogni tipo di funzione di attivazione** può modificare il comportamento della **propagazione dell'informazione** e dell'**allenamento** complessivo.

> 💡 **Nota didattica (non presente nelle slide): funzioni comuni.**
>
> | Funzione | Formula | Uso |
> |---|---|---|
> | gradino / sign | $-1$ o $+1$ | percettrone classico, non derivabile |
> | sigmoide | $\sigma(x) = \frac{1}{1 + e^{-x}}$ | uscite in $(0, 1)$, reti classiche |
> | tangente iperbolica | $\tanh(x)$ | uscite in $(-1, 1)$ |
> | ReLU | $\max(0, x)$ | reti deep (Lezione 7) |
>
> Per allenare con la backpropagation serve una funzione **derivabile**: per questo la sign function è stata sostituita da sigmoide, tanh e ReLU.

<!-- SOURCE_VISUAL id="TAB06-V10" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="34" type="grafici di funzioni" description="Tipi di funzioni di attivazione dei neuroni" reason="Le funzioni sono mostrate solo graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.5 Percettrone multistrato e allenamento**

_(slide pp. 35–37)_

Il **multilayer perceptron** con due strati nascosti ha: **strato di input**, **primo strato nascosto**, **secondo strato nascosto**, **strato di output**; i segnali fluiscono dall'ingresso all'uscita.

> 📌 **Allenamento.** Durante l'addestramento la rete neurale è **costretta ad aggiustare i propri parametri** (i pesi) in modo che la sua risposta ai dati in ingresso diventi **più vicina alla risposta desiderata**.

<!-- SOURCE_VISUAL id="TAB06-V11" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="35" type="schema di rete" description="Multilayer perceptron con input layer, due hidden layer e output layer" reason="Struttura a strati della rete" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB06-V12" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="37" type="grafico" description="Allenamento di una rete neurale: evoluzione dell'errore o della risposta durante il training" reason="La slide non ha testo oltre al titolo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Curse of dimensionality**

_(slide p. 38)_

**Premessa**: avere immagini in ingresso richiede **reti molto grandi**; ogni parametro della rete va **fissato usando i dati**, quindi servono **moltissimi dati**, anche milioni.

> 📌 **La maledizione della dimensionalità.** Aumentando gli ingressi aumenta la **dimensionalità dello spazio delle feature**, e i pochi dati disponibili risultano **molto lontani tra loro**.

La figura (Bishop, fig. 1.21) divide ogni asse in **3 unità**:

| Dimensioni $d$ | Celle $3^d$ |
|---:|---:|
| 1 | 3 |
| 2 | 9 |
| 3 | 27 |
| 10 | 59.049 |

Per avere almeno un esempio in ogni cella il numero di dati deve crescere **esponenzialmente** con $d$. Con un numero fisso di esempi, lo spazio diventa quasi vuoto.

> ⚠️ **Mai aggiungere feature senza un'attenta valutazione.** Una feature in più non è mai «gratis»: aumenta la dimensionalità e richiede più dati per mantenere la stessa accuratezza. È il motivo per cui la scelta di un template compatto (Lezione 2) è una decisione di progetto importante.

<!-- SOURCE_VISUAL id="TAB06-V13" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="38" type="figura" description="Curse of dimensionality (Bishop fig. 1.21): spazio diviso in 3 unità per dimensione in 1, 2 e 3 dimensioni" reason="La crescita delle celle è mostrata graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Metodi tradizionali: k-Nearest Neighbors**

_(slide pp. 39–47)_

#### **7.1 Idea e ingredienti**

_(slide pp. 39–42)_

I kNN sono metodi **induttivi ma non CI**. L'idea di base: «**se cammina come un'anatra e starnazza come un'anatra, allora probabilmente è un'anatra**». Si calcola la distanza del record di test dai record di training e si scelgono i $k$ record più vicini (nell'esempio: pollo, anatra, pollo, anatra, anatra).

Un classificatore ai primi vicini richiede **tre cose**:

1. l'**insieme dei record memorizzati**;
2. una **metrica di distanza** tra i record;
3. il valore di **$k$**, il numero di vicini da recuperare.

Per classificare un record sconosciuto:

1. si calcola la **distanza** rispetto ai record di addestramento;
2. si identificano i **$k$ vicini più prossimi**;
3. si usano le loro **etichette di classe** per decidere, per esempio con il **voto di maggioranza**.

> 📌 **Definizione.** I $k$ vicini più prossimi di un record $x$ sono i punti con la **distanza più piccola** da $x$. La slide p. 42 mostra i casi con 1, 2 e 3 vicini.

<!-- SOURCE_VISUAL id="TAB06-V14" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="40" type="schema" description="Nearest Neighbor: record di test, calcolo della distanza, scelta dei k record più vicini tra polli e anatre" reason="Illustra l'idea del classificatore" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB06-V15" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="42" type="grafici" description="1-nearest, 2-nearest e 3-nearest neighbor di un record x" reason="Mostra l'insieme dei vicini al variare di k" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2 Distanza e voto**

_(slide p. 43)_

1. Si calcola la distanza tra due punti, per esempio la **distanza euclidea**:

$$d(p, q) = \sqrt{\sum_{i} (p_i - q_i)^2}$$

2. Si determina la classe dalla lista dei vicini:
   - **A)** voto di **maggioranza** delle etichette tra i $k$ vicini;
   - **B)** opzionale: si **pesa il voto** in base alla distanza, con fattore $w = 1/d^2$.

> 💡 **Perché pesare con $1/d^2$.** Un vicino molto prossimo è più informativo di uno lontano. Con il peso $1/d^2$ un vicino a distanza 1 vale quattro volte uno a distanza 2: si riduce il rischio che vicini lontani, ma numerosi, ribaltino la decisione.

#### **7.3 La scelta di $k$ e overfitting**

_(slide pp. 44, 47)_

- **$k$ troppo piccolo**: il classificatore è **sensibile ai punti di rumore** → **overfitting**.
- **$k$ troppo grande**: il vicinato può **includere punti di altre classi** → **underfitting**.

Nell'esempio di p. 44 un campione incognito $x$ viene classificato come «+» per $k$ piccoli, come «−» per $k$ medi, e per $k$ grandi può cambiare ancora. La p. 47 confronta le frontiere di decisione con **$k = 1$** (frastagliata, segue ogni punto) e **$k = 5$** (più liscia).

<!-- SOURCE_VISUAL id="TAB06-V16" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="44" type="grafico" description="Campione incognito x classificato come + per k piccoli e - per k medi" reason="Mostra la dipendenza della decisione da k" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB06-V17" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="47" type="grafici" description="Frontiere di decisione kNN con k=1 (overfitting) e k=5 nel piano x1-x2" reason="Confronto visivo tra overfitting e underfitting" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.4 Il problema delle scale degli ingressi**

_(slide p. 45)_

Gli attributi potrebbero dover essere **scalati o normalizzati**, per evitare che la distanza sia **dominata** da uno di essi. Esempio con 3 ingressi:

| Attributo | Range (slide) |
|---|---|
| altezza | da 1,5 m a 1,8 m |
| peso | da 90 lb a 300 lb |
| reddito | da 10.000 $ a 1.000.000 $ |

Le **reti neurali** tendono invece ad **auto-scalare** gli ingressi, attraverso i pesi.

> 💡 **Esempio numerico (costruito sui range della slide).** Tra una persona agli estremi inferiori $(1{,}5;\ 90;\ 10.000)$ e una agli estremi superiori $(1{,}8;\ 300;\ 1.000.000)$ le differenze sono $0{,}3$, $210$ e $990.000$. La distanza euclidea vale circa $990.000$: altezza e peso non contano **nulla**. Normalizzando ogni attributo sul suo range ($x' = \frac{x - x_{min}}{x_{max} - x_{min}}$), tutte e tre le differenze valgono 1 e ogni attributo pesa allo stesso modo.

#### **7.5 Lazy learners ed eager learners**

_(slide p. 46)_

| | **Lazy learners** (pigri) | **Eager learners** |
|---|---|---|
| Esempio | **k-NN** | alberi decisionali, sistemi a regole, **reti neurali** |
| Modello | **nessun modello esplicito**: si memorizzano i dati | **modello compatto e sintetico** dei dati |
| Allenamento | nullo | più complesso |
| Classificare un ingresso | **costoso** in memoria e complessità computazionale | veloce |

> ⚠️ **Il kNN in identificazione.** Un kNN che confronta il probe con tutti i template **è** un'identificazione 1:N esaustiva: il costo cresce con il database, esattamente il problema di scalabilità della Lezione 2.

---

### **8. Metodi tradizionali: alberi decisionali**

_(slide pp. 48–49)_

Gli **alberi decisionali** sono metodi **induttivi ma non CI**. Sono **strutture di IF con soglie**: ogni nodo confronta un attributo con una soglia e sceglie un ramo, fino a una foglia che indica la classe.

> 💡 **Il pregio: l'explainability.** Un albero si può leggere come una sequenza di regole comprensibili («se l'altezza è maggiore di … e il peso minore di …»), una delle caratteristiche di modello elencate in §2.3. Una rete neurale, a parità di accuratezza, è molto meno interpretabile.

<!-- SOURCE_VISUAL id="TAB06-V18" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="49" type="diagramma ad albero" description="Albero decisionale come struttura di IF con soglie" reason="La struttura dell'albero è rappresentata graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **9. Al termine dell'apprendimento**

_(slide p. 50)_

> 📌 **Indipendentemente dal modello scelto**, al termine dell'apprendimento si ottiene **un blocco di codice completo e usabile**.

Dato un nuovo dato, segnale o immagine $X$:

```text
Classe_stimata1 = ReteFeedForward(X);
Classe_stimata2 = 5_NN(X);
Classe_stimata3 = albero(X);
```

È la stessa idea della Lezione 5: il modello allenato è una funzione $F(X)$ che si **usa come qualunque altra funzione**.

---

### **10. Le feature: dal progettista umano al deep learning**

_(slide pp. 51–56)_

#### **10.1 Feature di diverso livello**

_(slide pp. 51–53)_

La parte iniziale di ogni processo di ML richiede **feature** di diverso livello. Nel **ML tradizionale** è il **progettista** a sceglierle; nel **deep learning** la rete le impara. Capire come lavora il progettista tradizionale permette di capire che cosa le reti deep **automatizzano**.

<!-- SOURCE_VISUAL id="TAB06-V19" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="52" type="schema di confronto" description="Pipeline ML tradizionale con feature extraction manuale contro deep learning end-to-end" reason="Il confronto è mostrato graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.2 Il lavoro del progettista umano**

_(slide pp. 54–56)_

Il progettista costruisce una gerarchia di feature: **low level features** → **mid level features** → **object parts**.

| Livello | Esempi (slide) |
|---|---|
| **Low-level** | **SIFT**, *spin image*, *textons* (kernel), **SURF**, **MSER**, **LBP**, Color-SIFT, istogramma del colore, **GLOH**, **HoG**, e molti altri |
| **Mid-level** | «token» dalla *Vision* di **D. Marr**: **continuazione**, **parallelismo**, **giunzioni**, **angoli** |
| **Object parts** | parti degli oggetti (per un volto: occhi, naso, bocca) |

> 📌 **Il punto chiave della slide p. 56.** Le rappresentazioni di livello medio e le parti degli oggetti sono **difficili da ingegnerizzare**: i **kernel migliori** li si fa **trovare automaticamente** al metodo di apprendimento **deep**.

<!-- SOURCE_VISUAL id="TAB06-V20" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="55" type="collage di feature" description="Low-level features scelte dal designer: SIFT, spin image, textons, SURF, MSER, LBP, Color-SIFT, color histogram, GLOH, HoG" reason="Esempi visivi dei descrittori" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB06-V21" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="56" type="illustrazione" description="Mid-level features (token di Marr: continuation, parallelism, junctions, corners) e object parts" reason="Mostra la gerarchia di rappresentazioni costruita a mano" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **11. Caratteristiche dei modelli di deep learning**

_(slide pp. 57–70)_

#### **11.1 Che cos'è il deep learning**

_(slide pp. 57–60)_

Richiamo dalla Lezione 5: $DL \subset ML \subset AI$. Il deep learning **non è un solo metodo**, ma una **classe di algoritmi** che:

1. usano **vari livelli di unità non lineari in cascata**, dove ogni livello usa l'uscita del precedente come ingresso, per **estrarre caratteristiche** e **trasformarle**;
2. possono essere **supervisionati** (classificazione, regressione) o **non supervisionati** (analisi di pattern);
3. apprendono **livelli gerarchici multipli di caratteristiche** e di rappresentazioni: i livelli **non sono indicati dal supervisore** ma **creati automaticamente**, e le caratteristiche di livello più alto derivano da quelle di livello più basso;
4. i livelli multipli corrispondono a **diversi livelli di astrazione** e formano una **gerarchia di concetti**.

#### **11.2 Reti shallow e deep; gerarchia delle feature**

_(slide pp. 61–63)_

- La p. 61 confronta reti **shallow** (pochi strati) e **deep** (molti strati).
- Nella classificazione di immagini (p. 62) la rete impara a **estrarre le feature necessarie** e a far lavorare ogni strato sulle uscite del precedente: *pixel dell'immagine → layer 1 → layer 2 → layer 3 → classificatore semplice → «cat»*. **Tutti gli strati vengono allenati insieme**.
- Nel caso del **volto** (p. 63): il **primo strato** impara i **bordi** (*edges*), il **secondo** le **parti** (*object parts*), il **terzo** gli **oggetti** interi. Durante l'apprendimento le reti **fissano i parametri dei filtri** usati per estrarre le feature.

> 💡 **È la gerarchia del progettista umano, automatizzata.** Bordi, parti e oggetti corrispondono a low-level, mid-level e object parts del §10.2. La differenza è che nessuno sceglie i filtri: vengono appresi.

<!-- SOURCE_VISUAL id="TAB06-V22" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="61" type="schema" description="Confronto tra reti shallow e deep" reason="La slide non ha testo oltre al titolo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB06-V23" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="63" type="visualizzazione delle feature" description="Gerarchia delle feature apprese sul volto: edges, object parts, objects" reason="Visualizzazione dei filtri appresi per strato" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.3 Quando si parla di deep learning**

_(slide pp. 64–66)_

- L'**apprendimento** nel deep learning segue un concetto **molto simile** a quello delle reti tradizionali (p. 64).
- **Quando una rete è «deep»** (p. 65): la slide riporta numeri di riferimento delle reti attuali; altri autori parlano di deep già con **4–5 strati nascosti** e **centinaia o migliaia di neuroni** per strato, a seconda dell'applicazione (immagini, traduzione, riconoscimento del parlato).
- **Dove funziona meglio** (p. 66): quando lo spazio di input è **strutturato localmente**, spazialmente o temporalmente (immagini, linguaggio), rispetto a feature arbitrarie. Per capire come ragiona la rete si analizzano **quali ingressi eccitano al massimo** un neurone: nell'esempio ogni quadrato mostra l'immagine che attiva al massimo una di 100 unità.

<!-- SOURCE_VISUAL id="TAB06-V24" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="65" type="tabella o grafico" description="Numeri di riferimento di strati e neuroni delle reti attuali considerate deep" reason="I valori di riferimento sono solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB06-V25" source="Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf" page="66" type="griglia di immagini" description="Immagini di input che attivano al massimo ciascuna delle 100 unità di un livello di feature appreso" reason="Visualizzazione di cosa impara la rete" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.4 Perché tanti strati aiutano**

_(slide pp. 67–68)_

1. **Plausibilità biologica**: la **corteccia visiva** elabora le informazioni visive per strati successivi a partire dalla retina.
2. **Prova di Håstad**: i problemi rappresentabili con un numero **polinomiale** di nodi in $k$ strati potrebbero richiedere un numero **esponenziale** di nodi in $k - 1$ strati. **Aggiungere uno strato può diminuire il numero totale di nodi necessari**: tanti strati sono meglio.
3. **Condivisione delle sotto-caratteristiche**: le feature create in un'architettura profonda possono essere **condivise tra più compiti**. Per esempio, feature di uno strato che riconoscono un **occhio** possono servire a riconoscere una **bocca**.
4. **Trasferimento di conoscenze**: una rete addestrata su più compiti si può usare per compiti simili; le conoscenze iniziali si trasferiscono efficacemente, riducendo la necessità di addestrare da zero (nell'esempio: da un **dataset sintetico** a un **dataset reale**).

#### **11.5 Perché oggi si riesce ad allenare reti profonde**

_(slide pp. 69–70)_

Che cosa succede a un **MLP con molti strati nascosti** allenato con **backpropagation**? **Alcuni anni fa non era possibile**: la backpropagation era **lenta** e il **gradiente retropropagato** diventava **inutilizzabile** negli strati lontani dall'uscita.

> 💡 **Nota didattica (non presente nelle slide): il gradiente che svanisce.** Con la regola della catena il gradiente di uno strato iniziale è il **prodotto** delle derivate di tutti gli strati successivi. Con la sigmoide ogni derivata vale al massimo $0{,}25$: dopo 10 strati il fattore può scendere sotto $0{,}25^{10} \approx 10^{-6}$, e i primi strati praticamente non si aggiornano. La ReLU, con derivata 1 per ingressi positivi, attenua il problema.

I fattori che hanno permesso di allenare reti profonde (p. 70):

| Fattore | Contributo |
|---|---|
| **Hardware** | le **GPU** ad alte prestazioni rendono l'allenamento possibile in tempi ragionevoli |
| **Algoritmi di ottimizzazione** | nuovi ottimizzatori più efficienti come **Adam** e **RMSprop** fanno convergere più rapidamente |
| **Quantità di dati** | molte più fonti di dati rispetto a 10 anni fa, quindi dataset più ampi |
| **Architetture avanzate** | **CNN** per le immagini e **RNN** per le sequenze |

Le **CNN** sono il tema della Lezione 7.

---

### **12. Dispensa: Introducing Deep Learning with MATLAB**

_(dispensa pp. 1–15)_

#### **12.1 Che cos'è e perché è allo stato dell'arte**

_(dispensa pp. 1–5)_

- Il **deep learning** è un tipo di ML in cui un modello impara a eseguire compiti di classificazione **direttamente** da immagini, testo o suono. Si implementa di solito con reti neurali; «deep» si riferisce al **numero di strati**: le reti tradizionali ne hanno 2 o 3, quelle deep **anche centinaia**.
- **Esempi**: un'auto a guida autonoma rallenta vicino a un attraversamento pedonale, un **ATM rifiuta una banconota contraffatta**, uno smartphone traduce un cartello stradale. È adatto soprattutto alle applicazioni di **identificazione**: **riconoscimento del volto**, traduzione, **riconoscimento vocale**, sistemi di assistenza alla guida. Ricercatori UCLA hanno allenato una rete per identificare **cellule tumorali** da un microscopio avanzato.
- **Perché è allo stato dell'arte**: in una parola, **accuratezza**; può superare l'uomo nella classificazione di immagini, battere il miglior giocatore di **Go**, far funzionare assistenti vocali.

Tre **abilitatori tecnologici**:

| Abilitatore | Dettaglio (dispensa) |
|---|---|
| **grandi dataset etichettati** | **ImageNet**, **PASCAL VOC**, liberamente disponibili |
| **potenza di calcolo** | le **GPU** riducono il training **da settimane a ore** |
| **modelli pre-allenati** | **AlexNet**, allenata su **1,3 milioni** di immagini ad alta risoluzione per **1000 classi**, si può riallenare con il **transfer learning** anche con dataset molto più piccoli |

#### **12.2 Dentro una rete deep e dentro una CNN**

_(dispensa pp. 6–9)_

- Una **rete deep** combina **più strati di elaborazione non lineare**, con elementi semplici che operano in parallelo: uno **strato di input**, **diversi strati nascosti**, uno **strato di output**; ogni strato nascosto usa l'uscita del precedente.
- **Come impara** (p. 7): con immagini **etichettate** di quattro categorie, la rete impara le feature specifiche e le associa alle categorie; ogni strato aumenta **complessità e dettaglio**. La rete impara **direttamente dai dati**: **non si ha influenza su quali feature vengono apprese**.
- **CNN** (pp. 8–9): uno degli algoritmi più popolari per immagini e video. Gli strati di **feature detection** eseguono tre operazioni ripetute per decine o centinaia di strati:

| Operazione | Funzione |
|---|---|
| **Convolution** | l'immagine passa attraverso filtri convoluzionali, ognuno dei quali attiva certe feature |
| **Pooling** | semplifica l'uscita con un **sottocampionamento non lineare**, riducendo i parametri da apprendere |
| **ReLU** | mappa i valori **negativi a zero** e mantiene i positivi: allenamento più rapido ed efficace |

Gli strati di **classificazione**: il penultimo strato è **fully connected** e produce un vettore a **$K$ dimensioni** ($K$ = numero di classi) con le **probabilità** di ciascuna classe; l'ultimo strato usa una funzione **softmax**. **Non esiste una formula esatta** per scegliere gli strati: si provano alcune configurazioni o si usa una rete pre-allenata.

> 💡 **Nota didattica (non presente nella dispensa): softmax.** Dati i valori $z_1, \dots, z_K$ dell'ultimo strato, la softmax restituisce
>
> $$p_k = \frac{e^{z_k}}{\sum_{j=1}^{K} e^{z_j}}$$
>
> valori positivi che sommano a 1, interpretabili come probabilità di classe.

<!-- SOURCE_VISUAL id="TAB06-V26" source="Lezione_06_Deeplearning_Network_Matlab_toolobox_examples_DISPENSA.pdf" page="8" type="schema architettura CNN" description="CNN: input, convolution+ReLU, pooling ripetuti (feature learning), flatten, fully connected, softmax con classi car, van, truck, bicycle" reason="Schema di riferimento della CNN" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **12.3 Machine learning contro deep learning**

_(dispensa p. 10)_

Nel **ML** si estraggono **manualmente** le feature rilevanti; nel **DL** le immagini grezze entrano direttamente nella rete, che impara le feature da sola (*end-to-end learning*).

| **Machine learning** | **Deep learning** |
|---|---|
| + buoni risultati con **dataset piccoli** | − richiede **dataset molto grandi** (centinaia di migliaia o milioni di immagini) |
| + **veloce** da allenare | − **computazionalmente intensivo**, serve una GPU |
| − bisogna provare diverse **feature e classificatori** | + impara **feature e classificatori automaticamente** |
| − l'accuratezza **si stabilizza** (plateau) | + l'accuratezza è **illimitata** (secondo la dispensa) |

> ⚠️ **«Accuracy is unlimited».** È una semplificazione commerciale: anche il DL satura, per esempio per il rumore intrinseco dei dati o per la variabilità intraclasse del tratto. Il messaggio corretto è che il DL continua a migliorare con **molti più dati** di quanti ne sfrutti il ML classico (Lezione 5, §8.1).

<!-- SOURCE_VISUAL id="TAB06-V27" source="Lezione_06_Deeplearning_Network_Matlab_toolobox_examples_DISPENSA.pdf" page="10" type="schema di confronto" description="Machine learning con estrazione manuale delle feature contro deep learning end-to-end con CNN" reason="Confronto visivo delle due pipeline" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **12.4 Iniziare con AlexNet e il transfer learning**

_(dispensa pp. 11–13)_

- **AlexNet**, pubblicata nel **2012**, è una CNN allenata su oltre un milione di immagini che classifica in **1000 categorie** (tastiere, mouse, matite, razze di cani, gatti, cavalli, …).
- **Esempio** (p. 12): con *Neural Network Toolbox* e i pacchetti di supporto per webcam e AlexNet si scatta una foto con la webcam, la si **ridimensiona a 227×227 pixel** (la dimensione richiesta da AlexNet) e la si classifica. La sequenza di comandi è: connessione alla camera (`webcam`), caricamento della rete (`alexnet`), acquisizione (`snapshot`), ridimensionamento (`imresize`), classificazione (`classify`), visualizzazione di immagine ed etichetta.
- **Riallenare una rete esistente** (p. 13): per oggetti non presenti nell'addestramento originale si usa il **transfer learning**, applicando la conoscenza di un problema a un problema **diverso ma correlato**: si **rimuovono gli ultimi 3 strati** della rete e li si **riallena** con le proprie immagini.
- Se il transfer learning non basta, si **allena una rete da zero**: dà i risultati più accurati ma richiede **centinaia di migliaia di immagini etichettate** e **notevoli risorse di calcolo**.

> 📌 **Il codice non è materia d'esame** (Lezione 1), ma lo è il concetto: una rete pre-allenata richiede un **formato di input fisso** (227×227 per AlexNet) e si adatta a un nuovo compito **sostituendo e riallenando gli ultimi strati**.

#### **12.5 Risorse di calcolo**

_(dispensa pp. 14–15)_

L'allenamento può richiedere **ore, giorni o settimane**. Le opzioni sono tre:

| Opzione | Caratteristiche (dispensa) |
|---|---|
| **CPU** | la più semplice e disponibile; consigliata solo per esempi semplici con reti pre-allenate |
| **GPU** | riduce il training **da giorni a ore**; raccomandata una GPU NVIDIA con *compute capability* 3.0; più GPU accelerano ancora |
| **Cloud** | GPU senza acquistare l'hardware; il codice per GPU locale si estende al cloud con poche modifiche |

La p. 15 elenca risorse aggiuntive di MathWorks (introduzioni al deep learning, transfer learning con AlexNet, CNN, computer vision).

---

### **13. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Hopfield** / **Boltzmann** | memoria associativa deterministica a stati stabili / rete ricorrente stocastica con strato nascosto |
| **ML** / **CI** | apprendimento dai dati in generale / paradigmi biologici o linguistici (reti, fuzzy, evolutivi) |
| **Percettrone** / **MLP** | un neurone, frontiera lineare / strati nascosti, frontiere non lineari |
| **kNN $k$ piccolo** / **$k$ grande** | overfitting, sensibile al rumore / underfitting, vicini di altre classi |
| **Lazy** / **Eager learner** | nessun modello, classificazione costosa / modello compatto, allenamento complesso |
| **Feature manuali** / **Feature apprese** | scelte dal progettista (SIFT, HoG, LBP) / filtri appresi dalla rete |
| **Shallow** / **Deep** | pochi strati / molti strati con gerarchia di astrazione |
| **Transfer learning** / **Training da zero** | riallenare gli ultimi strati con pochi dati / centinaia di migliaia di immagini e molte risorse |

---

### **14. Sintesi della lezione**

_(slide p. 71)_

> ✅ **In sintesi.**
> - **Nobel 2024** a Hopfield e Hinton per le reti neurali artificiali.
> - **Workflow** del ML, **caratteristiche** del modello da valutare (accuratezza, explainability, embedability, …) e **rasoio di Occam**: prima kNN e alberi, poi reti piccole, deep solo se necessario.
> - In biometria: modelli pre-allenati o allenamento completo/parziale; **quattro regole** (workflow, L(NP)O con MIX, semplicità, transfer learning); il driver più importante è **il cervello del progettista**.
> - **Computational Intelligence**; **neurone** $X = \sum x_i w_i$ con sign function; percettrone e **MLP**.
> - **Curse of dimensionality**: mai aggiungere feature senza valutazione.
> - **kNN**: distanza, voto pesato $1/d^2$, scelta di $k$, **normalizzazione** degli ingressi, lazy learner; **alberi decisionali** come IF con soglie.
> - **Deep learning**: gerarchia di feature appresa (bordi, parti, oggetti), perché tanti strati aiutano, fattori abilitanti (GPU, Adam/RMSprop, dati, CNN/RNN).
> - **Dispensa MATLAB**: CNN con convolution/pooling/ReLU e softmax, ML contro DL, AlexNet 227×227 e transfer learning, risorse CPU/GPU/cloud.
