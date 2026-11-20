## **Lezione 4: Che cosa è intelligente, basi del machine learning, trasformazione digitale e simulazione d’esame**

Un sistema intelligente combina una rappresentazione del problema con dati, apprendimento e valutazione. Questa trattazione chiarisce: **quando** un sistema si può dire intelligente e **quando** conviene usare il machine learning invece della programmazione classica. Poi affronta tre temi di progetto: quanto bisogna **conoscere** un modello per usarlo in sicurezza (scatola bianca, grigia o nera), quanto deve essere **grande**, e quali sono i **tre componenti** di ogni algoritmo di apprendimento: rappresentazione, valutazione e ottimizzazione. Chiude con una panoramica delle **ere digitali** e con la **prima simulazione d’esame** commentata.


### **0. Panoramica**

Il percorso comprende:

1. **teoria**: che cosa è intelligente; **rappresentazione**, **valutazione** e **ottimizzazione**;
2. scenari attuali e futuri in cui i sistemi intelligenti hanno un ruolo rilevante: le **ere digitali**, cioè l’era **collaborativa** e l’era **autonoma**;
3. **simulazione n. 1** dell’esame, con commenti;
4. punti principali.

---

### **1. Che cosa è intelligente**

#### **1.1. AI, ML e sistemi intelligenti**

Che cosa intendiamo per sistema intelligente? Una distinzione utile separa il campo ampio dell’AI dai metodi di machine learning:

| | Definizione |
|---|---|
| **Artificial Intelligence** | il concetto **più ampio** di macchine capaci di svolgere compiti in un modo che consideriamo «intelligente» |
| **Machine Learning** | un’**applicazione attuale** dell’AI basata sull’idea di dare alle macchine **accesso ai dati** e lasciarle **imparare da sole** |

Il machine learning realizza spesso un processo induttivo: da esempi osservati si stima una funzione applicabile a casi nuovi.

> 📌 **Sistemi intelligenti artificiali**. Si può discutere all’infinito se un sistema sia intelligente. Una buona descrizione **operativa** è: sistemi software/hardware progettati per svolgere **compiti complessi** con strategie che **imitano qualche aspetto del pensiero umano**. **La chiave è l’evoluzione**: un sistema è intelligente se **può imparare**, anche solo in senso limitato, e/o **migliorare nel tempo**.

#### **1.2. Il ML va bene per ogni applicazione?**

> 📌 **Quando considerare l’AI**. Ci sono alcuni «se»:
>
> **SE**
> - la natura del calcolo richiesto dal compito **non è ben compresa**,
> - **OPPURE** ci sono **troppe eccezioni alle regole**,
> - **OPPURE** gli algoritmi noti sono **troppo complessi o inefficienti**,
>
> **ALLORA** l’AI si può considerare una soluzione possibile;
>
> **ALTRIMENTI** la **programmazione standard** va considerata come **prima opzione**.

> 💡 **Tre esempi (nota didattica).**
> - Calcolare l’IVA di una fattura: le regole sono chiare e senza eccezioni, quindi **codice classico**.
> - Riconoscere un volto in una foto: nessuno sa scrivere le regole, quindi **ML**.
> - Filtrare lo spam: le regole esistono ma hanno troppe eccezioni e cambiano di continuo, quindi **ML** (spesso affiancato da regole).

Tre proprietà guidano la scelta: rappresentazione, valutazione e ottimizzazione.

![](imgs/Pasted%20image%2020261002041228.png)

#### **1.3. Le scelte di progetto classiche**

Le opzioni di progetto sono tre:

| Scelta | Che cosa significa |
|---|---|
| **programmazione classica** | si **codificano le regole** |
| **modello addestrato con ML** | si applica un modello appreso dai dati |
| **approccio ibrido** | *divide et impera*: alcuni moduli si implementano con **codice classico**, altri con **modelli di ML addestrati** |

> 💡 **Perché l’ibrido è spesso la scelta migliore.** Nei sistemi reali il ML serve soprattutto nei punti in cui le regole non funzionano, per esempio la percezione, mentre controlli di validità, sicurezza e logica di business restano in codice classico, **verificabile** e **spiegabile**. È il principio del divide et impera applicato alla progettazione.

---

### **2. Scatola bianca, grigia o nera**

La domanda: **quale livello di conoscenza del modello** mi serve per usarlo **in sicurezza**?

Il concetto di approccio a **scatola nera, grigia o bianca** esiste già nell’**ingegneria del software**, ma si adatta perfettamente ai modelli di AI.

> 💡 **I tre livelli (nota didattica).**
> - **Scatola bianca**: si conoscono struttura e funzionamento interno, e si può seguire come ogni input diventa output (per esempio un albero di decisione piccolo).
> - **Scatola grigia**: si conosce una parte, come l’architettura o i dati di addestramento, ma non il comportamento dettagliato.
> - **Scatola nera**: si osservano solo input e output.

![](imgs/Pasted%20image%2020261002041415.png)

> 📌 **Regola di sicurezza**. Se scarichi e usi un modello di cui **non capisci il funzionamento**, allora **per te è una scatola nera**, e **devi gestirlo come una scatola nera**.

![](imgs/Pasted%20image%2020261002041429.png)

Anche **capendo il modello**, il numero di parametri può essere così alto da rendere **impossibile** capirne davvero il comportamento.

**Posso usare una scatola nera?**

![](imgs/Pasted%20image%2020261002041540.png)

| Condizione | Risposta |
|---|---|
| il **rischio è basso** | **sì** |
| l’output può essere **validato esternamente** | **sì** |
| esistono **procedure di ripiego** (fallback) | **sì** |
| applicazioni **critiche ad alto rischio** **senza controlli** | **no** |

Le tecniche XAI possono esaminare sensibilità alle feature, rappresentazioni interne, spiegazioni locali ed esempi di errore; l’analisi non sostituisce validazione, robustezza e monitoraggio.

![](imgs/Pasted%20image%2020261002041557.png)

> 💡 **Controlli a livello di sistema.** Quando il rischio applicativo è elevato, controlli esterni, supervisione umana e gestione del rischio devono accompagnare il modello.

![](imgs/Pasted%20image%2020261002041505.png)



---

### **3. Le dimensioni contano**

#### **3.1. Quanto sono grandi i modelli**

La domanda: qual è la **dimensione giusta** di un modello di ML?

**Un riferimento storico: GPT**. **GPT-3** (*Generative Pretrained Transformer 3*), sviluppato da OpenAI nel **2020**, è un modello di elaborazione del linguaggio naturale che produce articoli, riassunti di interi libri, completamento di codice e altro.

| Modello | Parametri | Dati di addestramento |
|---|---|---|
| **GPT-2** | circa **1,5 miliardi** | circa **40 GB** di testo |
| **GPT-3** | circa **175 miliardi** | circa **45 TB** di testo |

> ⚠️ **Sui 45 TB (nota didattica).** Nell’articolo di GPT-3, 45 TB è la dimensione del testo **grezzo** di Common Crawl **prima del filtraggio**; dopo il filtraggio ne restano circa 570 GB. Il salto rispetto a GPT-2 resta enorme, ma è bene non leggere il dato come testo effettivamente usato.

**Dimensioni pubblicate e non pubblicate.** I conteggi dei parametri permettono confronti solo parziali: per modelli MoE (*Mixture of Experts*, miscela di esperti) occorre distinguere parametri totali e attivi per token, mentre per molti modelli proprietari il dato non viene divulgato.

| Famiglia (dati pubblicati entro il 2026) | Dimensioni dichiarate | Che cosa mostra |
|---|---|---|
| Meta Llama 4 Scout | 109 miliardi totali, 17 miliardi attivi | modello multimodale MoE con 16 esperti |
| Meta Llama 4 Maverick | 400 miliardi totali, 17 miliardi attivi | MoE con 128 esperti |
| Google Gemma 4 | E2B, E4B, 12B, 26B-A4B (4B attivi), 31B | modelli per fasce hardware differenti; A4B indica la variante MoE con parametri attivi distinti |
| Alibaba Qwen 3.5/3.8 | da 0,8B fino a 2,4T-A95B; Flash-Next 6B attivi | famiglia che include versioni dense e MoE |
| Modelli proprietari di frontiera | conteggio spesso non divulgato | evitare stime non confermate |

**Evoluzione delle famiglie.** AlexNet (2012) aveva circa 60 milioni di parametri; Transformer Base (2017) circa 65 milioni; BERT Large (2018) 340 milioni; GPT-3 (2020) 175 miliardi. GPT-4 (2023) non ha un conteggio ufficiale pubblico. Nel 2026 la tendenza include modelli multimodali e MoE; la crescita del numero totale di parametri non basta a descrivere la capacità o il costo di inferenza.

> 📌 **Dimensione del modello e risorse.** Parametri, precisione numerica, sparsità, lunghezza del contesto, hardware e strategia di serving determinano insieme memoria, latenza, energia e costo. I conteggi totali di un MoE non coincidono con i parametri attivati per ogni token.

Al 20 novembre 2026, GPT-6.2 Astra e GPT-6.2 Sol sono disponibili dal 17 novembre. La documentazione pubblica descrive capacità e modalità d’uso ma non divulga il numero di parametri: non è quindi corretto attribuire loro stime non ufficiali. Astra è pensato per attività complesse; Sol per lavoro quotidiano e flussi agentici. [Scheda API ufficiale di GPT-6.2 Astra](https://developers.openai.com/api/docs/models/gpt-6.2-astra), [annuncio ufficiale](https://openai.com/index/introducing-gpt-6-2-astra-sol/).

Fonti primarie per i valori pubblicati: [Meta Llama](https://ai.meta.com/resources/models-and-libraries/llama-downloads/), [rilasci Google Gemma](https://ai.google.dev/gemma/docs/releases). La lista è uno snapshot: versioni e disponibilità cambiano rapidamente.

<!-- SOURCE_VISUAL id="IS04-V01" source="L04-main-web.pdf" page="17-18" type="tabella" description="Famiglie di modelli con parametri totali e attivi e confronto cronologico delle dimensioni dichiarate" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. Oligopolio e limiti dei modelli grandi**

**Un oligopolio dell’AI?**. I modelli più grandi sono in mano a **grandi aziende**: per i modelli di queste dimensioni, «**make or buy**» (costruire in casa o comprare) è ancora un’opzione praticabile?

![](imgs/Pasted%20image%2020261002042146.png)

**Più grande è meglio?** In alcuni casi **no**. La scelta della dimensione dipende dai vincoli dell’applicazione:

- i dispositivi edge hanno limiti di memoria, energia e calcolo;
- latenza, throughput, costi e disponibilità di rete condizionano l’architettura;
- complessità e opacità possono aumentare il carico di validazione;
- quantizzazione, pruning, distillazione e sparsità possono rendere praticabile l’inferenza locale;
- un modello specializzato può superare un modello generalista su un compito circoscritto.

> 💡 **Quantizzazione (nota didattica).** Significa rappresentare i pesi con meno bit, per esempio interi a 8 bit invece di numeri in virgola mobile a 32 bit. La memoria occupata scende di circa 4 volte e il calcolo accelera, con una piccola perdita di accuratezza. È una delle tecniche che portano i modelli su dispositivi embedded.

---

### **4. Basi del machine learning**

#### **4.1. Modello, addestramento, valutazione**

Il ciclo di base è: **modello → addestramento → valutazione**, e si ripete.

| Quante volte | Contesto |
|---|---|
| **una volta** | durante lo **sviluppo** |
| **ogni settimana, giorno o ora** | nelle applicazioni di **apprendimento continuo** (*continual learning*) |

![](imgs/Pasted%20image%2020261002042405.png)

![](imgs/Pasted%20image%2020261002042423.png)

#### **4.2. Esempio: classificatore cane/gatto**

Ogni animale è descritto da **peso** $W$ (kg) e **altezza** $H$ (cm).

**Programmazione classica**: il progettista scrive la regola.

$$\text{se } (W > A) \wedge (H < B) \;\Rightarrow\; \text{cane} \qquad\qquad \text{se } A\,W + B\,H + C > Z \;\Rightarrow\; \text{cane}$$

La prima regola taglia il piano con due soglie; la seconda con una **retta**. In entrambe le costanti $A, B, C, Z$ vanno scelte a mano.

**Machine learning**:

1. si parte da una **tabella di dati già classificati**;
2. si **addestra** un modello $Y = F(X)$, con $X = (W, H)$;
3. si decide **cane** se $F(X) > 0$.

> 💡 **Che cosa cambia.** La forma della decisione può essere la stessa, per esempio una retta. Nel ML però **i coefficienti li trovano i dati**, e $F$ può anche essere una funzione molto più complessa di una retta, cosa impossibile da scrivere a mano.

![](imgs/Pasted%20image%2020261002042440.png)

#### **4.3. Il ML come giardinaggio**

> 📌 **La metafora del giardinaggio**.
> - **Semi** = algoritmi
> - **Nutrienti** = dati
> - **Giardiniere** = tu
> - **Piante** = programmi

**Allora, che cos’è il machine learning?**

- **automatizzare l’automazione**;
- far sì che **i computer si programmino da soli**;
- **scrivere software** è il collo di bottiglia: **lasciamo lavorare i dati**.

**Esempio: una rete neurale**. Da qui in avanti, senza perdita di generalità, si fa riferimento alle reti **feed-forward**. Input: **età, sesso, altezza, peso**; output: **sovrappeso sì/no**.

![](imgs/Pasted%20image%2020261002042607.png)

**Sistemi di riconoscimento di pattern**. Lo schema ha due rami:

| Fase | Catena |
|---|---|
| **Addestramento** | acquisizione → **database** → estrazione delle feature → **training** |
| **Test** | acquisizione → estrazione delle feature → **matching** con il modello addestrato → **esito** (per esempio «= mela») |

> ⚠️ **La stessa estrazione nei due rami.** Le feature in fase di test devono essere estratte **esattamente come** in addestramento: se cambia l’acquisizione o l’estrazione, il modello riceve dati diversi da quelli su cui ha imparato.

![](imgs/Pasted%20image%2020261002042620.png)

#### **4.4. I tre componenti: rappresentazione, valutazione, ottimizzazione**

Esistono **decine di migliaia** di algoritmi di machine learning e **centinaia di nuovi** ogni anno.

> 📌 **Ogni algoritmo di ML ha tre componenti**:
> 1. **rappresentazione**: in che forma è il modello;
> 2. **valutazione**: come si misura quanto è buono;
> 3. **ottimizzazione**: come si cerca il modello migliore.

> 💡 **Da dove viene lo schema (nota didattica).** La scomposizione «apprendimento = rappresentazione + valutazione + ottimizzazione» è resa celebre da Pedro Domingos, *A Few Useful Things to Know about Machine Learning* (2012), da cui provengono anche gli elenchi delle, 33 e 35.

**Rappresentazione**:

- alberi di decisione;
- insiemi di regole, programmi logici;
- istanze (per esempio k-NN);
- modelli grafici (reti bayesiane e di Markov);
- reti neurali;
- *support vector machine*;
- *ensemble* di modelli;
- ecc.

![](imgs/Pasted%20image%2020261002042656.png)

![](imgs/Pasted%20image%2020261002042705.png)

Le rappresentazioni hanno **caratteristiche diverse**. Una è l’essere **autodidatta**: il modello dovrebbe potersi **aggiornare** con nuove informazioni quando diventano disponibili. La domanda da porsi: **quali caratteristiche contano per la mia applicazione?** L’elenco completo è nella figura.

![](imgs/Pasted%20image%2020261002042741.png)

---

**Valutazione**:

- accuratezza;
- precisione e recall;
- errore quadratico;
- verosimiglianza (*likelihood*);
- probabilità a posteriori;
- costo / utilità;
- margine;
- entropia;
- divergenza di Kullback-Leibler;
- ecc.

![](imgs/Pasted%20image%2020261002042904.png)

Esempio di probabilità a posteriori: dato l’input $x$, qual è la probabilità che sia un cane, cioè che valga l’ipotesi $c$? In formula $P(c \mid x)$.

> 📌 **Due misure in formula (nota didattica).** L’**entropia** di una distribuzione $p$ su $C$ classi misura l’incertezza; la **divergenza di Kullback-Leibler** misura quanto una distribuzione $q$ si discosta da $p$:
> $$H(p) = -\sum_{i=1}^{C} p_i \log p_i \qquad\qquad D_{KL}(p \,\|\, q) = \sum_{i=1}^{C} p_i \log \frac{p_i}{q_i}$$

---

**Accuratezza contro margine**. Le frontiere di decisione $H_2$ e $H_3$ hanno **entrambe accuratezza 100%** (errore nullo), ma **margini diversi**. Si vuole **aumentare il margine**: si preferisce $H_3$.

> 💡 **Perché il margine conta.** Il margine è la distanza tra la frontiera e i punti più vicini. Una frontiera che passa rasente ai dati classifica bene l’addestramento, ma basta un piccolo spostamento di un nuovo punto per sbagliare, come negli esempi avversari (trattazione degli esempi avversari). Un margine ampio dà **generalizzazione** e **robustezza**: è l’idea alla base delle *support vector machine*.

![](imgs/Pasted%20image%2020261002042928.png)

**Ottimizzazione**:

| Tipo | Esempio |
|---|---|
| **combinatoria** | ricerca **greedy** |
| **convessa** | **discesa del gradiente** |
| **vincolata** | **programmazione lineare** |

![](imgs/Pasted%20image%2020261002043021.png)

> 📌 **Discesa del gradiente (nota didattica).** Per minimizzare una funzione di errore $L(\theta)$ si aggiornano i parametri nella direzione opposta al gradiente, con passo $\eta > 0$ (*learning rate*):
> $$\theta \leftarrow \theta - \eta\, \nabla_\theta L(\theta)$$

> ✅ **Un esempio che lega i tre componenti.** In una rete neurale per il sovrappeso: la **rappresentazione** è la rete feed-forward; la **valutazione** è, per esempio, l’errore quadratico o l’accuratezza; l’**ottimizzazione** è la discesa del gradiente che cerca i pesi migliori.

---

### **5. Ere digitali e sfide recenti**

**Un mondo digitale: sfide per ambiente e industria**. Gli attori affrontano **sfide enormi**, ma nel mondo esistono iniziative rilevanti:

- **Industria 4.0** e **Industria 5.0**;
- **Internet of Things** (IoT);
- **Internet of Everything** (IoE), secondo la definizione di **Cisco**;
- **intelligenza artificiale**, con un’importanza crescente in ogni applicazione.

![](imgs/Pasted%20image%2020261002043923.png)

> 💡 **IoT contro IoE (nota didattica).** Nella definizione di Cisco, l’Internet of Everything collega non solo gli **oggetti** (IoT), ma anche **persone, processi e dati**. Industria 4.0 indica la fabbrica connessa e automatizzata; Industria 5.0 rimette al centro la collaborazione **uomo-macchina**, la sostenibilità e la resilienza.

**Sistemi autonomi in agricoltura**: un unico mezzo autonomo per irrorazione, sarchiatura, pacciamatura, lavorazione del terreno, semina, diserbo, falciatura, andanatura e voltatura del fieno.

**Sistemi autonomi per gli anziani**: robot che lavorano in **case e ambienti diversi**, per riordinare, pulire, **percepire e afferrare oggetti trasparenti**. Il gruppo *Tactile* di TRI sviluppa **sensori tattili molto cedevoli** e gli algoritmi per usarli (progetto **Punyo**).

![](imgs/Pasted%20image%2020261002044023.png)

![](imgs/Pasted%20image%2020261002044041.png)

---

### **6. Simulazione d’esame n. 1**

#### **6.1. Come funziona la prova**

La simulazione serve a **verificare la preparazione**, **non a studiare**.

> 📌 **Regole della prova a risposta multipla**.
> - **1 risposta corretta** → peso **+1**;
> - **N risposte sbagliate** → peso **0**;
> - **1 (raramente 2) risposta «molto sbagliata»** → peso **−1** (non di più).
>
> Indicazioni operative:
> 1. **non ci sono penalità** se non si risponde, **ma è meglio provare a rispondere**;
> 2. **non cercare la risposta giusta: escludere quelle sbagliate**;
> 3. in caso di **dubbio**, scegliere la risposta che **sembra meno sbagliata**.

> 💡 **Perché conviene rispondere.** Con il punteggio delil materiale una risposta a caso vale in media più di zero, a meno che quasi tutte le opzioni siano «molto sbagliate». Con 5 opzioni, di cui una corretta (+1), una molto sbagliata (−1) e tre sbagliate (0), il valore atteso di una risposta a caso è $\frac{1}{5}(+1) + \frac{1}{5}(-1) = 0$; basta escludere **una** opzione sbagliata per renderlo positivo.

#### **6.2. Domande e risposte commentate**

**D1**. Un modello di AI elabora un’**immagine RGB** per stimare l’**età in anni** del volto presente. Che tipo di modello è?
1. modello di classificazione;
2. **modello di regressione**;
3. modello di clustering;
4. modello di apprendimento per rinforzo;
5. nessuno dei precedenti.

**Risposta: 2**, evidenziata sulil materiale senza commento. L’età è una **grandezza numerica** da predire: il compito è una regressione, anche se la stima viene poi arrotondata agli anni interi.

**D2**. L’AI si può applicare ai seguenti settori: 1. robotica; 2. estrazione di informazioni; 3. **tutti i precedenti**.

**Risposta: 3.** Commento: la gamma delle applicazioni **sta esplodendo**, e comprende sia la 1 sia la 2.

**D3**. Le reti neurali artificiali sono in grado di **apprendere i bias umani**.
1. Falso: la complessità delle reti è così lontana da quella del cervello da rendere impossibile imitare questa caratteristica.
2. Falso: i bias umani non sono né riproducibili né misurabili.
3. **Vero.**

**Risposta: 3.** Commento: è vero perché **sono gli esseri umani a preparare i dataset**, e la **distribuzione statistica** dei campioni tra etichette e classi può influenzare fortemente l’apprendimento dei modelli.

**D4**. I modelli di AI recenti risolvono analogie come «**Parigi sta alla Francia come Tokyo sta a ?**» rispondendo correttamente «Giappone». 1. **Vero**; 2. Falso.

**Risposta: 1.** Commento: i cosiddetti **word embedding**, spesso usati come input delle reti neurali, sono appresi da grandi raccolte di testi e **risolvono le analogie**.

> 💡 **Come funziona l’analogia (nota didattica).** Ogni parola è un vettore; le relazioni diventano **differenze tra vettori**, per cui
> $$\vec{v}(\text{Tokyo}) - \vec{v}(\text{Parigi}) + \vec{v}(\text{Francia}) \approx \vec{v}(\text{Giappone})$$
> La parola il cui vettore è più vicino al risultato è la risposta. È un esempio concreto dello «spazio delle feature» (spazio delle rappresentazioni interne).

**D5**. Secondo il **grafico dello spettro dati-conoscenza** discusso in aula, la **quantità minima di dati** serve quando:
1. non si ha alcuna conoscenza del modello che genera i dati;
2. è disponibile un modello statistico, anche limitato, del processo;
3. **è disponibile un modello matematico del processo**.

**Risposta: 3.** Commento: bisogna solo **regolare o stimare alcuni parametri** del modello matematico, quindi servono meno dati che per addestrare un modello completo senza informazioni a priori (sezione sulle regole europee).

**D6**. Si può pensare al singolo dato in input a una rete neurale come a un **punto nello «spazio di input»** del modello, sia che l’input sia un singolo valore, un vettore N-dimensionale o un’immagine. 1. **Vero**; 2. Falso.

**Risposta: 1.** Commento: è proprio la rappresentazione corretta, che permette di capire i modelli di AI **in un quadro comune** (feature 1…4, oggetti *i, j, k*: sezione normativa e interpretabilità, §3.2).

![](imgs/Pasted%20image%2020261002044511.png)

**D7**. È corretto dire che una delle caratteristiche chiave di un sistema intelligente artificiale è la **capacità di imparare** (anche solo in senso limitato) e/o **migliorare nel tempo**. 1. **Vero**; 2. Falso.

**Risposta: 1.** Commento: si può discutere all’infinito se un sistema sia intelligente, ma la capacità di imparare è **una delle più importanti** (§1.1).

**D8**. Secondo la definizione di intelligenza computazionale di **Andries Engelbrecht**, quale delle seguenti **non** è inclusa?
1. reti neurali artificiali;
2. calcolo evolutivo;
3. swarm intelligence;
4. sistemi immunitari artificiali;
5. sistemi fuzzy;
6. **sono tutte incluse**.

**Risposta: 6** (definizione di sistema di AI).

**D9**. Secondo la discussione in aula sulla **Gestalt**, quale frase è più corretta?
1. la Gestalt è una caratteristica presente per progetto nelle reti neurali classiche;
2. la Gestalt è una caratteristica presente per progetto nelle reti di deep learning;
3. **la Gestalt è una caratteristica tipicamente umana, non (ancora) ben imitata dalle reti artificiali attuali**.

**Risposta: 3.** Il commento rimanda a un esempio visivo (esempio di Gestalt).

![](imgs/Pasted%20image%2020261002044728.png)

**D10**. Le attività a) **selezione** dei dati, b) **filtraggio** dei dati, c) **miglioramento** (*enhancing*) dei dati:
1. fanno parte del lavoro normale dello specialista di AI;
2. contribuiscono a **ridurre la complessità** del compito di apprendimento;
3. **tutte le precedenti (1 e 2)**;
4. appartengono agli approcci classici di ML e (giustamente) non si usano più nel deep learning.

**Risposta: 3.** Commento: a), b) e c) sono **estremamente importanti** per il comportamento finale del modello e per la complessità dell’addestramento (ruolo delle scelte progettuali).

**D11**. L’**errore quadratico medio** (MSE) compare tipicamente in quale fase del progetto? 1. rappresentazione; 2. **valutazione**.

**Risposta: 2**, evidenziata sulil materiale senza commento. L’errore quadratico è nell’elenco delle misure di **valutazione** (§4.4).

![](imgs/Pasted%20image%2020261002044844.png)

**D12**. Oggi l’uso dei metodi classici di **estrazione delle feature** e di **analisi dei dati** è superato, perché il deep learning li ha resi obsoleti e non sono più presenti nella pratica comune. 1. Vero; 2. **Falso**.

**Risposta: 2.** Commento: **gran parte del lavoro** consiste nel **preparare, studiare e validare i dati** per creare dataset efficienti, **anche con strumenti classici**.

#### **6.3. E gli argomenti che non sono nella simulazione?**

- Sono **ugualmente importanti**: l’assenza dalla simulazione **non significa** che non siano rilevanti, e **possono uscire all’esame**.
- **Non studiare la simulazione**: usala per capire se hai compreso i concetti e per **cambiare metodo di studio** quando serve.

> 📌 **Il metodo corretto**. Creare **la propria simulazione**: rivedere **tutti gli argomenti** e chiedersi, per ciascuno, «**quale domanda può verificare questa conoscenza?**».

---

### **7. Allegato: ere digitali (non oggetto d’esame)**

L’allegato è un’estensione concettuale sulla **trasformazione digitale**, utile per generare idee: dall’**economia collaborativa** ai **robot**, all’**autonomia** e ai **sistemi intelligenti**, fino allo **Stack di Bratton** come quadro interpretativo più ampio. La ne è la copertina.

**Le quattro fasi delle ere digitali**. Il modello concettuale descrive in testo l’ultima fase:

- **mondo autonomo**: sistemi tecnologici intelligenti, che operano **senza partecipazione umana**, rendono possibili nuovi modelli di business in una società più efficiente;
- **oggi** siamo **tra l’economia collaborativa e il mondo autonomo**: i sistemi sono ancora in gran parte **centrati sull’uomo**, ma sempre più supportati da processi decisionali basati su **dati e AI**; l’autonomia completa sta emergendo, ma **non è ancora diffusa** nelle applicazioni reali.

Il diagramma sintetizza la trasformazione digitale come passaggio verso sistemi sempre più connessi e autonomi.

![](imgs/Pasted%20image%2020261002045056.png)

**Economia collaborativa**. Si basa su **piattaforme** che collegano utenti, dati e servizi. Le aziende non producono più solo beni, ma offrono **piattaforme** in cui **gli utenti creano valore** condividendo, noleggiando e possedendo in comune le risorse.

![](imgs/Pasted%20image%2020261002045122.png)

Il diagramma collega **robot, autonomia e sistemi intelligenti**.

![](imgs/Pasted%20image%2020261002045142.png)

> 📌 **Lo Stack di Bratton**. Per il sociologo **Benjamin H. Bratton**, i diversi generi di calcolo (reti elettriche intelligenti, piattaforme cloud, app mobili, città intelligenti, Internet of Things, automazione) non sono specie che evolvono ciascuna per conto proprio. Formano un **tutto coerente**: una **megastruttura accidentale** chiamata «**The Stack**», che è insieme un **apparato computazionale** e una **nuova architettura di governo**. «Siamo dentro lo Stack, e lo Stack è dentro di noi.»

![](imgs/Pasted%20image%2020261002045206.png)

---

![](imgs/Pasted%20image%2020261002045242.png)

**Che cosa mostra il diagramma**. Il mondo oggi è organizzato da un **sistema computazionale globale** fatto di **strati interconnessi**, non solo da Stati o aziende. Tutte le frecce significano che **tutto è interconnesso**: tecnologia e politica, dati e identità, infrastrutture ed economia. Esempi nel diagramma:

- **cloud** → Stato e attori non statali;
- **leggi sui dati** → identità → geopolitica;
- **piattaforme** → mercati → città.

Conclusione: la tecnologia **sta dando forma alla società**, non si limita a supportarla.

---

### **8. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Programmazione classica** / **ML** / **ibrido** | regole scritte a mano / modello appreso dai dati / moduli di entrambi i tipi |
| **Scatola bianca** / **grigia** / **nera** | funzionamento noto / parzialmente noto / solo input e output |
| **Capire l’architettura** / **capire il comportamento** | possibile anche per modelli enormi / spesso impossibile per il numero di parametri |
| **Addestramento una tantum** / **continual learning** | ciclo eseguito in sviluppo / ciclo ripetuto ogni settimana, giorno o ora |
| **Rappresentazione** / **valutazione** / **ottimizzazione** | forma del modello / misura della qualità / ricerca del modello migliore |
| **Accuratezza** / **margine** | quanti esempi sono classificati bene / quanto la frontiera dista dai punti |
| **Parametri dichiarati** / **non divulgati** | Llama 4 e Gemma 4 / modelli proprietari di frontiera |
| **IoT** / **IoE** | oggetti connessi / oggetti, persone, processi e dati |
| **Economia collaborativa** / **mondo autonomo** | piattaforme in cui gli utenti creano valore / sistemi che operano senza partecipazione umana |

---

### **9. Sintesi della lezione**

> ✅ **Punti principali**.
> - Il ML conviene quando il calcolo **non è ben compreso**, le regole hanno **troppe eccezioni** o gli algoritmi noti sono **troppo complessi**; altrimenti la programmazione classica è la prima scelta, e l’**ibrido** è spesso la soluzione migliore.
> - **Approcci a scatola nera**: un modello il cui funzionamento interno non è sufficientemente conosciuto richiede controlli esterni, monitoraggio, fallback e un livello di rischio compatibile.
> - **La dimensione dipende dal contesto**: hardware, memoria, energia, latenza, costo e validazione concorrono alla scelta; per i modelli MoE si distinguono parametri totali e attivi.
> - **Teoria dei sistemi intelligenti**: rappresentazione, valutazione e ottimizzazione.
> - **Il progetto è come il giardinaggio**: semi = algoritmi, nutrienti = dati, giardiniere = tu, piante = programmi.
> - **Ere digitali**: era collaborativa ed era autonoma.
> - **Simulazione d’esame**: classificare correttamente i compiti, ragionare sui dati e sulle rappresentazioni, ed esercitarsi a motivare la scelta del metodo.
