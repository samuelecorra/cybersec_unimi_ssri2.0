## **Lezione 4: Che cosa è intelligente, basi del machine learning, ere digitali e simulazione d’esame**

La quarta lezione del corso **IS4** torna sulla domanda di partenza: **quando** un sistema si può dire intelligente e **quando** conviene usare il machine learning invece della programmazione classica. Poi affronta tre temi di progetto: quanto bisogna **conoscere** un modello per usarlo in sicurezza (scatola bianca, grigia o nera), quanto deve essere **grande**, e quali sono i **tre componenti** di ogni algoritmo di apprendimento: rappresentazione, valutazione e ottimizzazione. Chiude con una panoramica delle **ere digitali** e con la **prima simulazione d’esame** commentata.

Fonte: [PDF della Lezione 04](Lesson_04_MachineLearningBasics_Trends_Simulation.pdf), 77 pagine, nessun supplemento. L’allegato finale sulle ere digitali (pp. 69–77) **non è oggetto d’esame**. Le risposte corrette della simulazione sono quelle evidenziate o commentate dal docente; le note didattiche aggiunte sono segnalate. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Struttura della lezione**

_(slide pp. 1–2)_

Titolo originale: *What is intelligent? Machine learning basics, Digital ages, Simulation of the exam*. L’indice (p. 2) elenca:

1. **teoria**: che cosa è intelligente; **rappresentazione**, **valutazione** e **ottimizzazione**;
2. scenari attuali e futuri in cui i sistemi intelligenti hanno un ruolo rilevante: le **ere digitali**, cioè l’era **collaborativa** e l’era **autonoma**;
3. **simulazione n. 1** dell’esame, con commenti;
4. punti principali.

---

### **1. Che cosa è intelligente**

_(slide pp. 3–8)_

#### **1.1. AI, ML e sistemi intelligenti**

Quali caratteristiche servono per dire che un sistema è **intelligente** (p. 3)? La lezione riprende due definizioni già viste (Lezione 02, §2.1):

| | Definizione (p. 4) |
|---|---|
| **Artificial Intelligence** | il concetto **più ampio** di macchine capaci di svolgere compiti in un modo che consideriamo «intelligente» |
| **Machine Learning** | un’**applicazione attuale** dell’AI basata sull’idea di dare alle macchine **accesso ai dati** e lasciarle **imparare da sole** |

Il docente annota: il ML è **induzione** (Lezione 03, §4).

> 📌 **Sistemi intelligenti artificiali** (p. 5). Si può discutere all’infinito se un sistema sia intelligente. Una buona descrizione **operativa** è: sistemi software/hardware progettati per svolgere **compiti complessi** con strategie che **imitano qualche aspetto del pensiero umano**. **La chiave è l’evoluzione**: un sistema è intelligente se **può imparare**, anche solo in senso limitato, e/o **migliorare nel tempo**.

#### **1.2. Il ML va bene per ogni applicazione?**

> 📌 **Quando considerare l’AI** (p. 6). Ci sono alcuni «se»:
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

La p. 7 riassume i **tre concetti** da applicare.

<!-- SOURCE_VISUAL id="IS04-V01" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="7" type="schema" description="I tre concetti da applicare nella scelta tra programmazione classica e ML" reason="Contenuto presente solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.3. Le scelte di progetto classiche**

Le opzioni di progetto sono tre (p. 8):

| Scelta | Che cosa significa |
|---|---|
| **programmazione classica** | si **codificano le regole** |
| **modello addestrato con ML** | si applica un modello appreso dai dati |
| **approccio ibrido** | *divide et impera*: alcuni moduli si implementano con **codice classico**, altri con **modelli di ML addestrati** |

> 💡 **Perché l’ibrido è spesso la scelta migliore.** Nei sistemi reali il ML serve soprattutto nei punti in cui le regole non funzionano, per esempio la percezione, mentre controlli di validità, sicurezza e logica di business restano in codice classico, **verificabile** e **spiegabile**. È il «divide et impera» del quarto driver (Lezione 02, §9). Sulla slide la locuzione è scritta «Dividi et Impera».

---

### **2. Scatola bianca, grigia o nera**

_(slide pp. 9–14)_

La domanda (p. 9): **quale livello di conoscenza del modello** mi serve per usarlo **in sicurezza**?

Il concetto di approccio a **scatola nera, grigia o bianca** esiste già nell’**ingegneria del software**, ma si adatta perfettamente ai modelli di AI (p. 10).

> 💡 **I tre livelli (nota didattica).**
> - **Scatola bianca**: si conoscono struttura e funzionamento interno, e si può seguire come ogni input diventa output (per esempio un albero di decisione piccolo).
> - **Scatola grigia**: si conosce una parte, come l’architettura o i dati di addestramento, ma non il comportamento dettagliato.
> - **Scatola nera**: si osservano solo input e output.

> 📌 **Regola di sicurezza** (p. 11). Se scarichi e usi un modello di cui **non capisci il funzionamento**, allora **per te è una scatola nera**, e **devi gestirlo come una scatola nera**.

Anche **capendo il modello**, il numero di parametri può essere così alto da rendere **impossibile** capirne davvero il comportamento (p. 12, da TechTarget).

**Posso usare una scatola nera?** (p. 13)

| Condizione | Risposta |
|---|---|
| il **rischio è basso** | **sì** |
| l’output può essere **validato esternamente** | **sì** |
| esistono **procedure di ripiego** (fallback) | **sì** |
| applicazioni **critiche ad alto rischio** **senza controlli** | **no** |

Si può anche provare a **guardare dentro** la scatola nera (p. 14): è il tema della XAI (Lezione 02, §5).

> 💡 **Il collegamento con l’AI Act.** Le condizioni del «sì» sono le stesse misure che l’AI Act chiede ai sistemi ad alto rischio: controlli esterni, supervisione umana, gestione del rischio (Lezione 02, §1.4).

<!-- SOURCE_VISUAL id="IS04-V02" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="12" type="immagine" description="Il problema della scatola nera: troppi parametri per capire il comportamento (TechTarget)" reason="Illustrazione della slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS04-V03" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="14" type="immagine" description="Guardare dentro la scatola nera" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Le dimensioni contano**

_(slide pp. 15–21)_

#### **3.1. Quanto sono grandi i modelli**

La domanda (p. 15): qual è la **dimensione giusta** di un modello di ML?

**I modelli «vecchi»: GPT** (p. 16). **GPT-3** (*Generative Pretrained Transformer 3*), sviluppato da OpenAI nel **2020**, è un modello di elaborazione del linguaggio naturale che produce articoli, riassunti di interi libri, completamento di codice e altro.

| Modello | Parametri | Dati di addestramento |
|---|---|---|
| **GPT-2** | circa **1,5 miliardi** | circa **40 GB** di testo |
| **GPT-3** | circa **175 miliardi** | circa **45 TB** di testo |

> ⚠️ **Sui 45 TB (nota didattica).** Nell’articolo di GPT-3, 45 TB è la dimensione del testo **grezzo** di Common Crawl **prima del filtraggio**; dopo il filtraggio ne restano circa 570 GB. Il salto rispetto a GPT-2 resta enorme, ma è bene non leggere il dato come testo effettivamente usato.

**Modelli recenti (2023–2025)** (p. 17):

| Modello | Dimensione riportata dalla slide | Caratteristiche |
|---|---|---|
| **GPT-4** (OpenAI, 2023) | stimata in **1 000 miliardi** di parametri o più; le cifre esatte **non sono pubbliche** | input **multimodali** (testo e immagini), ragionamento avanzato |
| **Grok 3** (xAI, 2025) | secondo le notizie, tra **500 e 1 000 miliardi**, secondo la configurazione | ragionamento migliorato, interazione con il mondo reale |
| **Gemini Ultra** (Google, 2024) | stimata in **1 500 miliardi** | compiti multimodali, risoluzione di problemi avanzata |

> 📌 **Tendenza** (p. 17). Le dimensioni dei modelli sono cresciute **in modo esponenziale**, ma la ricerca recente suggerisce **rendimenti decrescenti** del solo aumento di scala. L’attenzione si sposta su **efficienza**, **qualità dei dati** e **architettura**, per esempio *mixture-of-experts* e **modelli sparsi**.

> ⚠️ **Stime, non dati ufficiali.** Per GPT-4, Grok 3 e Gemini Ultra i produttori non hanno pubblicato il numero di parametri: le cifre sono **stime** giornalistiche o di analisti, come dice la slide stessa.

Le pp. 18–19 mostrano altri modelli recenti e modelli **locali** (fonte: *IEEE Computer*, 2022). Tutti restano **lontani dalla complessità del cervello umano**, che ha circa **100 000 miliardi di «parametri»**.

> 💡 **Che cosa si confronta (nota didattica).** I «100 000 miliardi di parametri» del cervello corrispondono all’ordine di grandezza delle **sinapsi**. Il confronto è solo indicativo: una sinapsi non equivale a un peso di una rete artificiale.

<!-- SOURCE_VISUAL id="IS04-V04" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="18" type="grafico" description="Dimensioni di altri modelli recenti a confronto con il cervello umano" reason="Valori dei modelli presenti solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS04-V05" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="19" type="grafico" description="Dimensioni di modelli locali a confronto con il cervello umano" reason="Valori dei modelli presenti solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. Oligopolio e limiti dei modelli grandi**

**Un oligopolio dell’AI?** (p. 20). I modelli più grandi sono in mano a **grandi aziende**: per i modelli di queste dimensioni, «**make or buy**» (costruire in casa o comprare) è ancora un’opzione praticabile?

<!-- SOURCE_VISUAL id="IS04-V06" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="20" type="schema" description="AI e grandi aziende: oligopolio e scelta make or buy" reason="Contenuto presente solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Più grande è meglio?** In alcuni casi **no** (p. 21). Alcuni modelli molto grandi (oltre 1 000 miliardi di parametri) sembrano **meno «ristretti»** dei precedenti, ma:

- un **dispositivo autonomo** non può sfruttare modelli grandi che girano nel cloud;
- **dimensioni, consumo energetico** e capacità **in tempo reale** limitano l’applicabilità di un modello molto grande;
- la **spiegabilità limitata** dei modelli grandi li esclude dalle **applicazioni critiche** (regole UE);
- in molte applicazioni specifiche sono decisive **ottimizzazione, quantizzazione e scalabilità**.

> ⚠️ **Rimando della slide.** La slide rimanda alla «Lesson 01» per le regole UE; in questa edizione del corso l’AI Act è trattato nella **Lezione 02**, §1.

> 💡 **Quantizzazione (nota didattica).** Significa rappresentare i pesi con meno bit, per esempio interi a 8 bit invece di numeri in virgola mobile a 32 bit. La memoria occupata scende di circa 4 volte e il calcolo accelera, con una piccola perdita di accuratezza. È una delle tecniche che portano i modelli su dispositivi embedded.

---

### **4. Basi del machine learning**

_(slide pp. 22–35)_

#### **4.1. Modello, addestramento, valutazione**

Il ciclo di base (pp. 22–24) è: **modello → addestramento → valutazione**, e si ripete.

| Quante volte | Contesto |
|---|---|
| **una volta** | durante lo **sviluppo** |
| **ogni settimana, giorno o ora** | nelle applicazioni di **apprendimento continuo** (*continual learning*) |

<!-- SOURCE_VISUAL id="IS04-V07" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="23" type="diagramma" description="Ciclo modello, addestramento, valutazione" reason="Ciclo presente solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS04-V08" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="24" type="diagramma" description="Il ciclo eseguito una volta nello sviluppo o periodicamente nel continual learning" reason="Ciclo presente solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.2. Esempio: classificatore cane/gatto**

Ogni animale è descritto da **peso** $W$ (kg) e **altezza** $H$ (cm) (p. 25).

**Programmazione classica**: il progettista scrive la regola.

$$\text{se } (W > A) \wedge (H < B) \;\Rightarrow\; \text{cane} \qquad\qquad \text{se } A\,W + B\,H + C > Z \;\Rightarrow\; \text{cane}$$

La prima regola taglia il piano con due soglie; la seconda con una **retta**. In entrambe le costanti $A, B, C, Z$ vanno scelte a mano.

**Machine learning**:

1. si parte da una **tabella di dati già classificati**;
2. si **addestra** un modello $Y = F(X)$, con $X = (W, H)$;
3. si decide **cane** se $F(X) > 0$.

> 💡 **Che cosa cambia.** La forma della decisione può essere la stessa, per esempio una retta. Nel ML però **i coefficienti li trovano i dati**, e $F$ può anche essere una funzione molto più complessa di una retta, cosa impossibile da scrivere a mano.

<!-- SOURCE_VISUAL id="IS04-V09" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="25" type="grafico" description="Classificatore cane/gatto nel piano altezza-peso: regole classiche contro modello addestrato F(X)" reason="Frontiere di decisione e definizione grafica di F visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.3. Il ML come giardinaggio**

> 📌 **La metafora del giardinaggio** (p. 26).
> - **Semi** = algoritmi
> - **Nutrienti** = dati
> - **Giardiniere** = tu
> - **Piante** = programmi

**Allora, che cos’è il machine learning?** (p. 27)

- **automatizzare l’automazione**;
- far sì che **i computer si programmino da soli**;
- **scrivere software** è il collo di bottiglia: **lasciamo lavorare i dati**.

**Esempio: una rete neurale** (p. 28). Da qui in avanti, senza perdita di generalità, si fa riferimento alle reti **feed-forward**. Input: **età, sesso, altezza, peso**; output: **sovrappeso sì/no**.

<!-- SOURCE_VISUAL id="IS04-V10" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="28" type="diagramma" description="Rete neurale feed-forward con input età, sesso, altezza, peso e output sovrappeso sì/no" reason="Struttura della rete visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Sistemi di riconoscimento di pattern** (p. 29). Lo schema ha due rami:

| Fase | Catena |
|---|---|
| **Addestramento** | acquisizione → **database** → estrazione delle feature → **training** |
| **Test** | acquisizione → estrazione delle feature → **matching** con il modello addestrato → **esito** (per esempio «= mela») |

> ⚠️ **La stessa estrazione nei due rami.** Le feature in fase di test devono essere estratte **esattamente come** in addestramento: se cambia l’acquisizione o l’estrazione, il modello riceve dati diversi da quelli su cui ha imparato.

<!-- SOURCE_VISUAL id="IS04-V11" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="29" type="diagramma" description="Sistema di riconoscimento di pattern con rami di addestramento e di test" reason="Blocchi e frecce visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.4. I tre componenti: rappresentazione, valutazione, ottimizzazione**

Esistono **decine di migliaia** di algoritmi di machine learning e **centinaia di nuovi** ogni anno (p. 30).

> 📌 **Ogni algoritmo di ML ha tre componenti** (p. 30):
> 1. **rappresentazione**: in che forma è il modello;
> 2. **valutazione**: come si misura quanto è buono;
> 3. **ottimizzazione**: come si cerca il modello migliore.

> 💡 **Da dove viene lo schema (nota didattica).** La scomposizione «apprendimento = rappresentazione + valutazione + ottimizzazione» è resa celebre da Pedro Domingos, *A Few Useful Things to Know about Machine Learning* (2012), da cui provengono anche gli elenchi delle pp. 31, 33 e 35.

**Rappresentazione** (p. 31):

- alberi di decisione;
- insiemi di regole, programmi logici;
- istanze (per esempio k-NN);
- modelli grafici (reti bayesiane e di Markov);
- reti neurali;
- *support vector machine*;
- *ensemble* di modelli;
- ecc.

Le rappresentazioni hanno **caratteristiche diverse** (p. 32). Una è l’essere **autodidatta**: il modello dovrebbe potersi **aggiornare** con nuove informazioni quando diventano disponibili. La domanda da porsi: **quali caratteristiche contano per la mia applicazione?** L’elenco completo è nella figura.

<!-- SOURCE_VISUAL id="IS04-V12" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="32" type="tabella" description="Caratteristiche delle diverse rappresentazioni, tra cui l’aggiornamento autodidatta" reason="Elenco delle caratteristiche presente solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Valutazione** (p. 33):

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

Esempio di probabilità a posteriori: dato l’input $x$, qual è la probabilità che sia un cane, cioè che valga l’ipotesi $c$? In formula $P(c \mid x)$.

> 📌 **Due misure in formula (nota didattica).** L’**entropia** di una distribuzione $p$ su $C$ classi misura l’incertezza; la **divergenza di Kullback-Leibler** misura quanto una distribuzione $q$ si discosta da $p$:
> $$H(p) = -\sum_{i=1}^{C} p_i \log p_i \qquad\qquad D_{KL}(p \,\|\, q) = \sum_{i=1}^{C} p_i \log \frac{p_i}{q_i}$$

**Accuratezza contro margine** (p. 34). Le frontiere di decisione $H_2$ e $H_3$ hanno **entrambe accuratezza 100%** (errore nullo), ma **margini diversi**. Si vuole **aumentare il margine**: si preferisce $H_3$.

> 💡 **Perché il margine conta.** Il margine è la distanza tra la frontiera e i punti più vicini. Una frontiera che passa rasente ai dati classifica bene l’addestramento, ma basta un piccolo spostamento di un nuovo punto per sbagliare, come negli esempi avversari (Lezione 02, §4). Un margine ampio dà **generalizzazione** e **robustezza**: è l’idea alla base delle *support vector machine*.

<!-- SOURCE_VISUAL id="IS04-V13" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="34" type="grafico" description="Frontiere H2 e H3 con accuratezza 100% ma margini diversi" reason="Posizione delle frontiere e dei punti visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Ottimizzazione** (p. 35):

| Tipo | Esempio |
|---|---|
| **combinatoria** | ricerca **greedy** |
| **convessa** | **discesa del gradiente** |
| **vincolata** | **programmazione lineare** |

> 📌 **Discesa del gradiente (nota didattica).** Per minimizzare una funzione di errore $L(\theta)$ si aggiornano i parametri nella direzione opposta al gradiente, con passo $\eta > 0$ (*learning rate*):
> $$\theta \leftarrow \theta - \eta\, \nabla_\theta L(\theta)$$

> ✅ **Un esempio che lega i tre componenti.** In una rete neurale per il sovrappeso: la **rappresentazione** è la rete feed-forward; la **valutazione** è, per esempio, l’errore quadratico o l’accuratezza; l’**ottimizzazione** è la discesa del gradiente che cerca i pesi migliori.

---

### **5. Ere digitali e sfide recenti**

_(slide pp. 36–40)_

**Un mondo digitale: sfide per ambiente e industria** (p. 37). Gli attori affrontano **sfide enormi**, ma nel mondo esistono iniziative rilevanti:

- **Industria 4.0** e **Industria 5.0**;
- **Internet of Things** (IoT);
- **Internet of Everything** (IoE), secondo la definizione di **Cisco**;
- **intelligenza artificiale**, con un’importanza crescente in ogni applicazione.

La p. 38 rinvia all’allegato (§7) per i dettagli.

> 💡 **IoT contro IoE (nota didattica).** Nella definizione di Cisco, l’Internet of Everything collega non solo gli **oggetti** (IoT), ma anche **persone, processi e dati**. Industria 4.0 indica la fabbrica connessa e automatizzata; Industria 5.0 rimette al centro la collaborazione **uomo-macchina**, la sostenibilità e la resilienza.

<!-- SOURCE_VISUAL id="IS04-V14" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="38" type="infografica" description="Ere digitali e sfide recenti, con rinvio all’allegato" reason="Contenuto presente solo nell’infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Sistemi autonomi in agricoltura** (p. 39, **AgXeed**): un unico mezzo autonomo per irrorazione, sarchiatura, pacciamatura, lavorazione del terreno, semina, diserbo, falciatura, andanatura e voltatura del fieno.

**Sistemi autonomi per gli anziani** (p. 40, **Toyota Research Institute**, TRI): robot che lavorano in **case e ambienti diversi**, per riordinare, pulire, **percepire e afferrare oggetti trasparenti**. Il gruppo *Tactile* di TRI sviluppa **sensori tattili molto cedevoli** e gli algoritmi per usarli (progetto **Punyo**).

<!-- SOURCE_VISUAL id="IS04-V15" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="39" type="immagini" description="Mezzi agricoli autonomi AgXeed e relative lavorazioni" reason="Esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS04-V16" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="40" type="immagini" description="Robot per l’assistenza agli anziani del Toyota Research Institute e sensori tattili Punyo" reason="Esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Simulazione d’esame n. 1**

_(slide pp. 41–67)_

#### **6.1. Come funziona la prova**

La simulazione serve a **verificare la preparazione**, **non a studiare** (p. 41).

> 📌 **Regole della prova a risposta multipla** (p. 42).
> - **1 risposta corretta** → peso **+1**;
> - **N risposte sbagliate** → peso **0**;
> - **1 (raramente 2) risposta «molto sbagliata»** → peso **−1** (non di più).
>
> Consigli del docente:
> 1. **non ci sono penalità** se non si risponde, **ma è meglio provare a rispondere**;
> 2. **non cercare la risposta giusta: escludere quelle sbagliate**;
> 3. in caso di **dubbio**, scegliere la risposta che **sembra meno sbagliata**.

> 💡 **Perché conviene rispondere.** Con il punteggio della slide una risposta a caso vale in media più di zero, a meno che quasi tutte le opzioni siano «molto sbagliate». Con 5 opzioni, di cui una corretta (+1), una molto sbagliata (−1) e tre sbagliate (0), il valore atteso di una risposta a caso è $\frac{1}{5}(+1) + \frac{1}{5}(-1) = 0$; basta escludere **una** opzione sbagliata per renderlo positivo.

#### **6.2. Domande e risposte commentate**

_(slide pp. 43–66)_

**D1** (pp. 43–44). Un modello di AI elabora un’**immagine RGB** per stimare l’**età in anni** del volto presente. Che tipo di modello è?
1. modello di classificazione;
2. **modello di regressione**;
3. modello di clustering;
4. modello di apprendimento per rinforzo;
5. nessuno dei precedenti.

**Risposta: 2**, evidenziata sulla slide senza commento. L’età è una **grandezza numerica** da predire: è una regressione (Lezione 03, §3.3), anche se poi la si arrotonda agli anni interi.

**D2** (pp. 45–46). L’AI si può applicare ai seguenti settori: 1. robotica; 2. estrazione di informazioni; 3. **tutti i precedenti**.

**Risposta: 3.** Commento: la gamma delle applicazioni **sta esplodendo**, e comprende sia la 1 sia la 2.

**D3** (pp. 47–48). Le reti neurali artificiali sono in grado di **apprendere i bias umani**.
1. Falso: la complessità delle reti è così lontana da quella del cervello da rendere impossibile imitare questa caratteristica.
2. Falso: i bias umani non sono né riproducibili né misurabili.
3. **Vero.**

**Risposta: 3.** Commento: è vero perché **sono gli esseri umani a preparare i dataset**, e la **distribuzione statistica** dei campioni tra etichette e classi può influenzare fortemente l’apprendimento dei modelli.

**D4** (pp. 49–50). I modelli di AI recenti risolvono analogie come «**Parigi sta alla Francia come Tokyo sta a ?**» rispondendo correttamente «Giappone». 1. **Vero**; 2. Falso.

**Risposta: 1.** Commento: i cosiddetti **word embedding**, spesso usati come input delle reti neurali, sono appresi da grandi raccolte di testi e **risolvono le analogie**.

> 💡 **Come funziona l’analogia (nota didattica).** Ogni parola è un vettore; le relazioni diventano **differenze tra vettori**, per cui
> $$\vec{v}(\text{Tokyo}) - \vec{v}(\text{Parigi}) + \vec{v}(\text{Francia}) \approx \vec{v}(\text{Giappone})$$
> La parola il cui vettore è più vicino al risultato è la risposta. È un esempio concreto dello «spazio delle feature» (Lezione 02, §3.2).

**D5** (pp. 51–52). Secondo il **grafico dello spettro dati-conoscenza** discusso in aula, la **quantità minima di dati** serve quando:
1. non si ha alcuna conoscenza del modello che genera i dati;
2. è disponibile un modello statistico, anche limitato, del processo;
3. **è disponibile un modello matematico del processo**.

**Risposta: 3.** Commento: bisogna solo **regolare o stimare alcuni parametri** del modello matematico, quindi servono meno dati che per addestrare un modello completo senza informazioni a priori (Lezione 01).

**D6** (pp. 53–54). Si può pensare al singolo dato in input a una rete neurale come a un **punto nello «spazio di input»** del modello, sia che l’input sia un singolo valore, un vettore N-dimensionale o un’immagine. 1. **Vero**; 2. Falso.

**Risposta: 1.** Commento: è proprio la rappresentazione corretta, che permette di capire i modelli di AI **in un quadro comune** (feature 1…4, oggetti *i, j, k*: Lezione 02, §3.2).

<!-- SOURCE_VISUAL id="IS04-V17" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="54" type="schema" description="Commento alla D6: oggetti come punti nello spazio delle feature" reason="Disposizione dei punti visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**D7** (pp. 55–56). È corretto dire che una delle caratteristiche chiave di un sistema intelligente artificiale è la **capacità di imparare** (anche solo in senso limitato) e/o **migliorare nel tempo**. 1. **Vero**; 2. Falso.

**Risposta: 1.** Commento: si può discutere all’infinito se un sistema sia intelligente, ma la capacità di imparare è **una delle più importanti** (§1.1).

**D8** (pp. 57–58). Secondo la definizione di intelligenza computazionale di **Andries Engelbrecht**, quale delle seguenti **non** è inclusa?
1. reti neurali artificiali;
2. calcolo evolutivo;
3. swarm intelligence;
4. sistemi immunitari artificiali;
5. sistemi fuzzy;
6. **sono tutte incluse**.

**Risposta: 6** (Lezione 02, §2.2).

**D9** (pp. 59–60). Secondo la discussione in aula sulla **Gestalt**, quale frase è più corretta?
1. la Gestalt è una caratteristica presente per progetto nelle reti neurali classiche;
2. la Gestalt è una caratteristica presente per progetto nelle reti di deep learning;
3. **la Gestalt è una caratteristica tipicamente umana, non (ancora) ben imitata dalle reti artificiali attuali**.

**Risposta: 3.** Il commento rimanda a un esempio visivo (Lezione 02, §6).

<!-- SOURCE_VISUAL id="IS04-V18" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="60" type="immagine" description="Esempio visivo a commento della domanda sulla Gestalt" reason="Esempio presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**D10** (pp. 61–62). Le attività a) **selezione** dei dati, b) **filtraggio** dei dati, c) **miglioramento** (*enhancing*) dei dati:
1. fanno parte del lavoro normale dello specialista di AI;
2. contribuiscono a **ridurre la complessità** del compito di apprendimento;
3. **tutte le precedenti (1 e 2)**;
4. appartengono agli approcci classici di ML e (giustamente) non si usano più nel deep learning.

**Risposta: 3.** Commento: a), b) e c) sono **estremamente importanti** per il comportamento finale del modello e per la complessità dell’addestramento (quarto driver, Lezione 02, §9).

**D11** (pp. 63–64). L’**errore quadratico medio** (MSE) compare tipicamente in quale fase del progetto? 1. rappresentazione; 2. **valutazione**.

**Risposta: 2**, evidenziata sulla slide senza commento. L’errore quadratico è nell’elenco delle misure di **valutazione** (§4.4).

**D12** (pp. 65–66). Oggi l’uso dei metodi classici di **estrazione delle feature** e di **analisi dei dati** è superato, perché il deep learning li ha resi obsoleti e non sono più presenti nella pratica comune. 1. Vero; 2. **Falso**.

**Risposta: 2.** Commento: **gran parte del lavoro** consiste nel **preparare, studiare e validare i dati** per creare dataset efficienti, **anche con strumenti classici**.

> ⚠️ **Refusi nelle slide della simulazione.** «What king of model» (*kind*), «weigth», «ASWER», «machine learining», «understating» (*understanding*). Non cambiano il senso delle domande.

#### **6.3. E gli argomenti che non sono nella simulazione?**

_(slide p. 67)_

- Sono **ugualmente importanti**: l’assenza dalla simulazione **non significa** che non siano rilevanti, e **possono uscire all’esame**.
- **Non studiare la simulazione**: usala per capire se stai seguendo il corso con profitto e, se serve, per **cambiare metodo di studio**.

> 📌 **Il metodo corretto** (p. 67). Creare **la propria simulazione**: scorrere **tutte le slide** del corso chiedendosi, per ciascuna, «**che cosa si può chiedere su questa slide?**».

---

### **7. Allegato: ere digitali (non oggetto d’esame)**

_(slide pp. 69–77)_

L’allegato (pp. 69–70) è un’estensione concettuale sulla **trasformazione digitale**, utile per generare idee: dall’**economia collaborativa** ai **robot**, all’**autonomia** e ai **sistemi intelligenti**, fino allo **Stack di Bratton** come quadro interpretativo più ampio. La p. 71 ne è la copertina.

**Le quattro fasi delle ere digitali** (p. 72). La slide descrive in testo l’ultima fase:

- **mondo autonomo**: sistemi tecnologici intelligenti, che operano **senza partecipazione umana**, rendono possibili nuovi modelli di business in una società più efficiente;
- **oggi** siamo **tra l’economia collaborativa e il mondo autonomo**: i sistemi sono ancora in gran parte **centrati sull’uomo**, ma sempre più supportati da processi decisionali basati su **dati e AI**; l’autonomia completa sta emergendo, ma **non è ancora diffusa** nelle applicazioni reali.

Le fasi precedenti sono indicate solo nella figura.

<!-- SOURCE_VISUAL id="IS04-V19" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="72" type="diagramma" description="Le quattro fasi delle ere digitali fino al mondo autonomo" reason="Nomi delle prime fasi presenti solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Economia collaborativa** (p. 73). Si basa su **piattaforme** che collegano utenti, dati e servizi. Le aziende non producono più solo beni, ma offrono **piattaforme** in cui **gli utenti creano valore** condividendo, noleggiando e possedendo in comune le risorse.

La p. 74 unisce **robot, autonomia e sistemi intelligenti**.

<!-- SOURCE_VISUAL id="IS04-V20" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="74" type="immagini" description="Robot, autonomia e sistemi intelligenti" reason="Contenuto presente solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Lo Stack di Bratton** (p. 75). Per il sociologo **Benjamin H. Bratton**, i diversi generi di calcolo (reti elettriche intelligenti, piattaforme cloud, app mobili, città intelligenti, Internet of Things, automazione) non sono specie che evolvono ciascuna per conto proprio. Formano un **tutto coerente**: una **megastruttura accidentale** chiamata «**The Stack**», che è insieme un **apparato computazionale** e una **nuova architettura di governo**. «Siamo dentro lo Stack, e lo Stack è dentro di noi.»

**Che cosa mostra il diagramma** (pp. 76–77). Il mondo oggi è organizzato da un **sistema computazionale globale** fatto di **strati interconnessi**, non solo da Stati o aziende. Tutte le frecce significano che **tutto è interconnesso**: tecnologia e politica, dati e identità, infrastrutture ed economia. Esempi nel diagramma:

- **cloud** → Stato e attori non statali;
- **leggi sui dati** → identità → geopolitica;
- **piattaforme** → mercati → città.

Conclusione: la tecnologia **sta dando forma alla società**, non si limita a supportarla.

<!-- SOURCE_VISUAL id="IS04-V21" source="Lesson_04_MachineLearningBasics_Trends_Simulation.pdf" page="76" type="diagramma" description="Diagramma dello Stack di Bratton con gli strati interconnessi" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

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
| **Parametri dichiarati** / **stimati** | GPT-2, GPT-3 / GPT-4, Grok 3, Gemini Ultra |
| **IoT** / **IoE** | oggetti connessi / oggetti, persone, processi e dati |
| **Economia collaborativa** / **mondo autonomo** | piattaforme in cui gli utenti creano valore / sistemi che operano senza partecipazione umana |

---

### **9. Sintesi della lezione**

_(slide p. 68)_

> ✅ **Punti principali** (p. 68).
> - Il ML conviene quando il calcolo **non è ben compreso**, le regole hanno **troppe eccezioni** o gli algoritmi noti sono **troppo complessi**; altrimenti la programmazione classica è la prima scelta, e l’**ibrido** è spesso la soluzione migliore.
> - **Approcci a scatola nera**: un modello che non capisci va gestito come scatola nera, e si usa solo con rischio basso, validazione esterna o procedure di ripiego.
> - **Più grande non è sempre meglio**: dispositivi autonomi, consumi, tempo reale e spiegabilità limitano i modelli enormi.
> - **Teoria dei sistemi intelligenti**: rappresentazione, valutazione e ottimizzazione.
> - **Il progetto è come il giardinaggio**: semi = algoritmi, nutrienti = dati, giardiniere = tu, piante = programmi.
> - **Ere digitali**: era collaborativa ed era autonoma.
> - **Simulazione d’esame**: escludere le risposte sbagliate, rispondere comunque, e costruirsi la propria simulazione su tutte le slide.
