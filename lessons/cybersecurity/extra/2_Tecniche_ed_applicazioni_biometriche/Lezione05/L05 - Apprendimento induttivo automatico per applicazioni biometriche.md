# **Lezione 5: Apprendimento induttivo automatico per applicazioni biometriche**

---

### **0. Panoramica della lezione**

_(slide pp. 1–4)_

Con la Lezione 5 il corso passa dal crash course alle **tecniche avanzate**: il **machine learning** (ML) come strumento per progettare i moduli di un sistema biometrico.

L'outline:

1. **primitive biometriche** viste come **blocchi di ML** di classificazione e regressione;
2. **fondamenti** di machine learning: **rappresentazione**, **valutazione**, **ottimizzazione**;
3. **modelli di apprendimento**: induttivo e deduttivo; supervisionato, non supervisionato, *reinforcement learning*;
4. **tecniche utili**: *transfer learning*, *data augmentation*, *Leave One Person Out* (LOPO) e *Leave N Persons Out* (LNPO).

La mappa delle conoscenze dei sistemi biometrici della Lezione 1 si arricchisce di una disciplina: accanto a pattern matching, elaborazione di immagini e segnali, statistica, ottica, sicurezza e interazione uomo-macchina compare l'**intelligenza artificiale** (p. 4).

---

### **1. AI, ML e DL**

_(slide pp. 5–8)_

#### **1.1 Una gerarchia di insiemi**

_(slide p. 5)_

$$DL \subset ML \subset AI$$

- **Intelligenza artificiale (AI)**: l'insieme più ampio, sistemi che svolgono compiti che richiederebbero intelligenza;
- **Machine learning (ML)**: la parte dell'AI in cui il comportamento **si apprende dai dati** invece di essere programmato esplicitamente;
- **Deep learning (DL)**: la parte del ML basata su **reti neurali profonde** (molti strati), come le CNN.

<!-- SOURCE_VISUAL id="TAB05-V01" source="Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf" page="5" type="diagramma a insiemi" description="Insiemi annidati DL dentro ML dentro AI" reason="Rappresentazione visiva della gerarchia" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2 Dove si applica**

_(slide pp. 6–8)_

L'AI è applicata in moltissimi ambiti dell'informatica: **biometria**, web search, biologia computazionale, finanza, e-commerce, esplorazione spaziale, robotica, estrazione di informazioni, social network, debugging, «**la tua applicazione!**».

Esempi di task: riconoscimento ottico dei caratteri (OCR), riconoscimento della scrittura e del parlato, creatività artificiale, computer vision e realtà virtuale, diagnosi, teoria dei giochi e pianificazione strategica, bot per videogiochi, elaborazione del linguaggio naturale e traduzione, controllo non lineare e robotica, **riconoscimento del volto e biometria in generale**, **deepfake**.

Nell'ambito dell'**usabilità e dell'interazione uomo-macchina** (p. 8) il ML abilita: interfacce cervello-computer (Neuralink), **profilazione e autenticazione biometrica continua**, interazione fluida basata sul comportamento, ambienti intelligenti, **riconoscimento delle emozioni**, modelli linguistici di grandi dimensioni (**LLM**, come ChatGPT).

---

### **2. Il sistema biometrico come classificatore**

_(slide pp. 9–12)_

#### **2.1 Chiudere il sistema in un blocco**

_(slide pp. 9–10)_

Il ML serve a **vedere il sistema biometrico come un insieme di moduli funzionali**. Il primo passo è chiudere in un unico blocco l'intera catena della Lezione 1 (acquisizione, estrazione, codifica, matching con soglia, Yes/No) e osservarne soltanto ingresso e uscita.

#### **2.2 Identificatore e autenticatore**

_(slide pp. 11–12)_

> 📌 **Il sistema biometrico è un classificatore.**
> - un sistema di **identificazione** è un classificatore **multiclasse**: alla domanda «**Chi è?**» risponde con una classe, per esempio «Donald»;
> - un sistema di **autenticazione** è un classificatore **binario**: alla domanda «**Sono la stessa persona?**» risponde «SÌ» o «NO».

I **classificatori** sono il modello **più comune** prodotto dalle tecniche di ML. In entrambi i casi il problema è **equivalente**: in ingresso c'è **un vettore**, e il modello «**mappa**» quel vettore in **un intero** in uscita (l'indice della classe). La slide aggiunge che equivalente **non significa facile**: anche «domani investo su GOOG?» è formalmente una classificazione binaria.

> 💡 **Perché è utile questa lettura.** Se il sistema è un classificatore, si possono usare tutti gli strumenti del ML per **costruirlo**, **valutarlo** (accuratezza, errori, curve) e **migliorarlo** con più dati, senza dover scrivere a mano le regole di riconoscimento.

---

### **3. Le primitive biometriche come blocchi di ML**

_(slide pp. 13–18)_

Non solo il sistema intero: anche **molti moduli interni** sono classificatori o regressori, e possono essere **allenati**.

| Primitiva | Ingresso | Uscita | Tipo di modello |
|---|---|---|---|
| **Face detection** (p. 13) | un blocco dell'immagine | «contiene un volto?» sì/no; se no si analizza il blocco successivo | classificatore binario (elaborazione tradizionale a blocchi) |
| **Face recognition** (p. 13) | il blocco con il volto | «chi è?», per esempio «Anne» | classificatore multiclasse |
| **Fingerprint indexing** (p. 14) | un'impronta | tipo **Arch / Loop / Whorl** | classificatore multiclasse, allenato su esempi |
| **Quality assessment** (p. 15) | il sample, prima dell'estrazione | indice di qualità | modulo *Quality Checker* della pipeline |
| **Controllo qualità impronte** (p. 16) | immagine $I$ | $Q(I) \in [0, 1, \dots, 10]$ | regressore o classificatore ordinale, allenato |
| **Controllo qualità volti ICAO** (p. 17) | immagine $I$ | $Q(I) \in [0, 1, \dots, 10]$ | come sopra; l'insieme dei controlli ICAO è **ampio e difficile da automatizzare** |
| **Anti-spoofing** (p. 18) | immagine $I$ | $Spoof(I) \in [0, 1]$ | classificatore binario o stima di probabilità |

**Esempio di anti-spoofing** (p. 18): la tecnologia **Synaptics PurePrint** «esamina le immagini delle impronte usando una tecnologia di intelligenza artificiale unica per distinguere le dita false da quelle vere». La slide mostra un **dito finto in gelatina**: con un dongle USB **con** PurePrint l'utente **non riesce ad accedere**, con un dongle **senza** PurePrint **accede**.

> 📌 **Primitive = moduli allenabili.** Indicizzazione (Lezione 2), controllo qualità (Lezione 2), anti-spoofing (Lezioni 19–20) sono tutti moduli che il ML trasforma da «regole scritte a mano» in «modelli allenati sui dati».

<!-- SOURCE_VISUAL id="TAB05-V02" source="Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf" page="13" type="schema" description="Face detection a blocchi (contiene un volto? YES) seguita da sistema di riconoscimento Chi è? Classe = Anne" reason="Mostra la catena di due classificatori" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB05-V03" source="Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf" page="18" type="fotografie" description="Synaptics PurePrint anti-spoof: dito finto in gelatina, dongle con PurePrint non si logga, dongle senza PurePrint si logga; Spoof(I) in [0,1]" reason="Esempio concreto di primitiva anti-spoofing allenata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Che cosa significa allenare un modello**

_(slide pp. 19–24)_

#### **4.1 L'obiettivo del ML in biometria**

_(slide p. 20)_

Il ML costruisce un **modello configurato** (un algoritmo) che trasforma ingressi biometrici in uscite utili.

| Ingressi possibili | Uscite possibili |
|---|---|
| immagini 2D e 3D | valore in $[0, 1]$ (per esempio probabilità di spoofing) |
| segnali | classe $1, 2, 3, \dots, N$ (identità, tipo di impronta) |
| dati anagrafici | valore reale stimato: età, peso, qualità |

Lo slogan della slide è: «**Autoprogrammare questo codice!**». Il modello non viene scritto, viene **ricavato dai dati**.

#### **4.2 Programmazione classica contro ML**

_(slide pp. 21–22)_

Esempio: un classificatore **cane/gatto** basato su **altezza** $H$ (cm) e **peso** $W$ (kg).

| Approccio | Come si costruisce la decisione |
|---|---|
| **Programmazione classica** | si scrivono regole: `IF W > A AND H < B → dog`, oppure `IF A·W + B·H + C > Z → dog`, scegliendo a mano le costanti |
| **Machine learning** | si raccoglie una **tabella di dati classificati**, si **allena un modello** $F(\cdot)$ tale che $Y = F(X)$, e si decide `dog = IF F(X) > 0` |

In forma compatta, con $X = (W, H)$:

$$Y = F(X), \qquad \text{classe} = \begin{cases} \text{cane} & \text{se } F(X) > 0 \\ \text{gatto} & \text{altrimenti} \end{cases}$$

> 💡 **La differenza essenziale.** Nella programmazione classica la **conoscenza** sta nella testa del programmatore, che sceglie soglie e pesi. Nel ML la conoscenza sta nei **dati**, e l'algoritmo trova da solo i parametri. La regola lineare $A \cdot W + B \cdot H + C > Z$ è già un classificatore: il ML ne sceglie automaticamente $A$, $B$, $C$.

<!-- SOURCE_VISUAL id="TAB05-V04" source="Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf" page="22" type="grafico a dispersione" description="Esempi di cani e gatti nel piano altezza-peso con regole IF e superficie di separazione appresa F()" reason="Visualizza la differenza tra regola manuale e modello appreso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.3 Una metafora: il giardinaggio**

_(slide p. 23)_

| Giardinaggio | Machine learning |
|---|---|
| semi | **algoritmi** |
| nutrienti | **dati** |
| giardiniere | **tu** |
| piante | **programmi** |

Senza dati buoni (nutrienti) anche il miglior algoritmo (seme) non produce nulla, e serve comunque qualcuno che curi il processo.

#### **4.4 Esempio: rete neurale feed-forward**

_(slide p. 24)_

Nel corso ci si riferisce, **senza perdita di generalità**, alle **Feed-Forward Neural Networks (FFNN)**. L'esempio: ingressi **età**, **sesso**, **altezza**, **peso**, **sport**; uscita **sovrappeso (sì/no)**.

---

### **5. Le tre componenti di ogni algoritmo di ML**

_(slide pp. 25–31)_

Esistono **decine di migliaia** di algoritmi di ML, e **centinaia** se ne aggiungono ogni anno. Ognuno ha **tre componenti**: **Representation**, **Evaluation**, **Optimization**.

#### **5.1 Representation**

_(slide pp. 26–27)_

> 📌 **Rappresentazione.** L'insieme di **tutti i modelli teoricamente possibili** che un algoritmo può esplorare, formulati in uno specifico **linguaggio formale**.

Il linguaggio ha peculiarità che rendono **certi modelli più facili da esprimere** di altri, anche dentro lo stesso insieme di ipotesi: si crea un «**panorama dei modelli possibili**» con limiti e opportunità.

Esempi: alberi di decisione, insiemi di regole, reti bayesiane e di Markov, **reti neurali**, **support vector machine**, ensemble di modelli.

> 💡 **Nei classificatori la rappresentazione cambia il tipo di superficie decisionale** (p. 27): un classificatore lineare separa con una retta, un albero di decisione con rettangoli, una rete neurale con curve arbitrarie.

<!-- SOURCE_VISUAL id="TAB05-V05" source="Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf" page="27" type="grafici" description="Superfici decisionali diverse nel piano altezza-peso a seconda della rappresentazione del classificatore" reason="Mostra come la rappresentazione cambia la forma della separazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2 Evaluation**

_(slide pp. 28–29)_

> 📌 **Valutazione.** «**Come giudichi o preferisci un modello rispetto a un altro**»: la definizione di una funzione di utilità, di perdita, di punteggio o di *fitness*.

Si immagini un **panorama**: a ogni modello corrisponde un'**altezza**, e le **aree più basse** sono le preferibili. Funzioni di valutazione diverse danno altezze leggermente diverse nello stesso panorama. Il grafico della slide mostra la **performance** (errore di training) in funzione dello **spazio dei parametri**.

Esempi: **accuracy**, **precision e recall**, **errore quadratico**, **likelihood** (probabilità stimata di un modello dati i dati osservati), probabilità a posteriori, costo/utilità, **margine**, **entropia**, **divergenza K-L**, metriche di regolarizzazione.

> 💡 **Nota didattica (non presente nelle slide): due valutazioni frequenti.** Con $y_i$ il valore vero e $\hat{y}_i$ la predizione su $n$ esempi:
>
> $$MSE = \frac{1}{n}\sum_{i=1}^{n}(y_i - \hat{y}_i)^2 \qquad Accuracy = \frac{\#\{\text{predizioni corrette}\}}{n}$$
>
> In biometria l'accuracy da sola è ingannevole, perché i confronti impostori sono molto più numerosi dei genuini: per questo si usano FMR, FNMR e le curve (Lezione 2).

<!-- SOURCE_VISUAL id="TAB05-V06" source="Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf" page="28" type="grafico" description="Panorama della performance (training error) nello spazio dei parametri" reason="Visualizza la metafora del panorama" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.3 Optimization**

_(slide pp. 30–31)_

> 📌 **Ottimizzazione.** «**Come esplori lo spazio dei modelli rappresentati per ottenere valutazioni migliori**»: la strategia per attraversare il panorama dei parametri e trovare un buon punto di funzionamento.

| Famiglia | Esempio |
|---|---|
| ottimizzazione **combinatoria** | ricerca greedy |
| ottimizzazione **convessa** | **discesa del gradiente** |
| ottimizzazione **vincolata** | programmazione lineare |
| altre tecniche | **discesa del gradiente stocastica**, **algoritmi genetici** |

In una rete neurale l'ottimizzazione **modifica i pesi del modello per ridurre l'errore di apprendimento**.

> 💡 **Nota didattica (non presente nelle slide): discesa del gradiente.** Con pesi $w$, funzione di perdita $L(w)$ e passo di apprendimento $\eta$, ogni iterazione aggiorna
>
> $$w \leftarrow w - \eta\, \nabla L(w)$$
>
> cioè scende nel panorama nella direzione di massima pendenza.

> ⚠️ **Non si torna indietro.** Una volta ottenuto un modello addestrato è molto probabile che **non si possa più ricostruire esattamente come è stato ottimizzato**, per esempio se arrivano nuovi dati: il risultato dipende da inizializzazione, ordine dei dati e scelte casuali.

---

### **6. Tipi di apprendimento**

_(slide pp. 32–52)_

#### **6.1 Deduttivo e induttivo**

_(slide pp. 32–37)_

| Termine | Definizione (slide) |
|---|---|
| **Teoria** | un **modello generale** che spiega un insieme di fenomeni, integrando più ipotesi verificate, con capacità esplicative e predittive |
| **Ipotesi** | un'**affermazione specifica e falsificabile** che collega concetti osservabili e che può essere verificata o smentita dai dati |

- Ragionamento **deduttivo**: si parte da una **teoria** e la si applica a un caso.
- Ragionamento **induttivo**: si parte dai **dati** e se ne ricava una regola.

Gli esercizi della slide:

| Situazione | Risposta |
|---|---|
| «Sulla base di quanto mi hanno insegnato i genitori, preferire un posto fisso, accetto un lavoro. Inizio a lavorarci e sono contento.» | **deduttiva**: parto da una teoria |
| «Nelle settimane scorse sono partito alle 8:00 e ho trovato traffico in tangenziale, quindi decido di partire in anticipo. Arrivo in orario: serve partire prima dell'orario di punta.» | **induttiva**: parto dai dati |

#### **6.2 Induzione, deduzione e trasduzione**

_(slide pp. 38–40)_

| Modalità | Definizione nel ML (slide) |
|---|---|
| **Induzione** | **generalizzare** da esempi specifici a una regola o un modello generale; è il processo con cui un modello viene **addestrato** su dati per fare previsioni su dati non visti |
| **Deduzione** | applicare **regole generali** a casi specifici, come in un sistema basato su regole predefinite |
| **Trasduzione** | apprendere **da esempi specifici a esempi specifici**, senza formare un modello generale: si mappa direttamente un input a un output |

**Test: ChatGPT in quale funzione rientra?** (pp. 39–40). ChatGPT, come tutti i **Large Language Models** (LLM), è un **GPT** (*Generative Pre-trained Transformer*) ed è fondamentalmente un modello **induttivo**: in addestramento apprende da un grande insieme di testi per **generalizzare** un modello capace di rispondere a molti prompt. Una volta addestrato può essere visto anche come **trasduttore**, perché mappa direttamente un input in un output senza una fase di deduzione esplicita:

1. input: «Qual è il tempo oggi?»;
2. codifica in un vettore $[x_1, x_2, \dots, x_n]$;
3. trasformazione con meccanismi di **attenzione** e reti neurali;
4. output decodificato: «Il tempo oggi è soleggiato».

#### **6.3 Supervisionato, non supervisionato, semi-supervisionato, per rinforzo**

_(slide pp. 41–43)_

Sia $X$ un vettore di dati in ingresso (numeri, feature, immagini) e $Y$ il vettore delle uscite corrispondenti.

| Tipo | Dati di addestramento |
|---|---|
| **Supervisionato** (induttivo) | contengono le **uscite desiderate**: si hanno $X$ e $Y$ |
| **Non supervisionato** | **non** contengono le uscite: si ha solo $X$ |
| **Semi-supervisionato** | contengono **poche** uscite desiderate: $X$ e un $Y$ ridotto |
| **Per rinforzo** (*reinforcement*) | **ricompense** da sequenze di azioni: $X$ e $Y$, ma $Y$ rappresenta una **sequenza di decisioni** o di parametri decisionali |

<!-- SOURCE_VISUAL id="TAB05-V07" source="Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf" page="42" type="schema" description="Tipi di apprendimento illustrati graficamente" reason="La slide non ha testo oltre al titolo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB05-V08" source="Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf" page="43" type="albero tassonomico" description="Tassonomia del machine learning" reason="La tassonomia è presente solo come figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.4 L'apprendimento induttivo**

_(slide p. 44)_

> 📌 **Inductive learning.** Dati esempi di ingressi $X$ e uscite desiderate $Y$, trovare una funzione $F(X)$ che riproduca $Y$ con il **minimo errore**:
>
> $$F(X) \rightarrow Y$$
>
> - $F$ con uscita **discreta**: **classificazione**;
> - $F$ con uscita **continua**: **regressione**;
> - sottocaso: $F(X) = P(X)$, **stima di probabilità**.

Le **reti neurali** appartengono all'apprendimento induttivo.

#### **6.5 Perché l'induttivo va bene in biometria e il deduttivo è raro**

_(slide pp. 45–46)_

**Vantaggi dell'apprendimento induttivo** in biometria:

- **mitiga il collo di bottiglia della conoscenza diretta** del problema, che sarebbe necessaria per programmare esplicitamente la soluzione;
- permette di **scalare** la base di conoscenza **senza rifare tutto**;
- è «**adaptive**»: offre soluzioni che **resistono ai cambiamenti** delle condizioni operative;
- è **più facile riallenare** e risolvere **nuovi domini** applicativi (*transfer learning*, §7).

**Perché i metodi deduttivi sono rari**: è **più probabile avere tanti dati**, anche rumorosi e incompleti, che un **modello del mondo efficace** da applicare direttamente. Domanda della slide: **che cos'è una buona impronta?** Nessuna equazione lo stabilisce. Le **eccezioni**, dove esistono modelli deduttivi potenti, sono campi come le **equazioni di Maxwell** (onde elettromagnetiche), le **equazioni dell'atmosfera** (previsioni meteo) e il **moto dei gravi**.

#### **6.6 Reinforcement learning**

_(slide pp. 47–49)_

- I modelli di **apprendimento per rinforzo** usano dinamiche opposte, **ricompense e punizioni**, per «rinforzare» diversi tipi di conoscenza; sono presenti in molte soluzioni moderne di AI.
- **Obiettivo**: ottenere **quante più ricompense possibile**. Esempi di agenti (*bot*): videogiochi, compravendita di azioni, rilevamento di intrusioni.
- **Esempio nelle impronte** (p. 49): le minuzie (fine riga, biforcazioni) si possono trovare **percorrendo le creste in toni di grigio**, come nel metodo *direct gray scale* della Lezione 3. Le decisioni su **come muovere gli «automi»** lungo le creste sono perfettamente **ottimizzabili con RL**, usando una **funzione di ricompensa** che premia, per esempio, il raggiungimento di una fine riga.

<!-- SOURCE_VISUAL id="TAB05-V09" source="Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf" page="49" type="immagine annotata" description="Cammino di un agente lungo le creste di un'impronta e rewarding function di un fine riga" reason="Esempio visivo di RL applicato alle minuzie" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.7 Modelli usati in biometria**

_(slide pp. 50–52)_

Le slide pp. 50–51 mostrano i **principali modelli di ML usabili in biometria** (per esempio quelli disponibili in MATLAB) ed evidenziano i **più usati**. La p. 52 li classifica **in base a ingressi e uscite**:

| Tipo di uscita / uso | Applicazioni biometriche (slide) |
|---|---|
| regressione | **stima dell'età**, **soft biometrics** |
| clustering, riduzione della dimensionalità | **comprensione dei dati**, elaborazione degli input, **partizioni degli utenti** |
| classificazione | quasi **tutto** |

<!-- SOURCE_VISUAL id="TAB05-V10" source="Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf" page="51" type="mappa dei modelli" description="Principali modelli di machine learning usati in biometria con evidenza dei più usati" reason="L'elenco dei modelli è solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB05-V11" source="Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf" page="52" type="tabella" description="Classificazione dei modelli in base a I/O con applicazioni: age estimation, soft biometrics, data understanding, user partitions, biometric applications" reason="Verificare l'associazione tra tipi di modello e applicazioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Transfer learning**

_(slide pp. 53–57)_

> 📌 **Transfer learning.** **Trasferire la conoscenza** appresa in un dominio applicativo a un altro dominio, invece di ripartire da zero.

- È considerato la **prossima frontiera** del ML: offre un **ulteriore miglioramento** e **maggiore sicurezza nella generalizzazione** (p. 54).
- **Imparare da simulazioni** (p. 55): un modello allenato su dati **simulati**, abbondanti ed economici, viene poi adattato ai dati reali.
- **Trasferire il dominio** (p. 56): le persone usano **parole diverse** nei diversi contesti per esprimere la stessa opinione (testi ufficiali contro testi social); nella classificazione di immagini si parte da un dominio **semplificato** (per esempio sfondi uniformi) per arrivare a quello reale.
- **Con le CNN** (p. 57): si usa una **rete convoluzionale pre-allenata** (*pre-trained CNN*) su un grande dataset generico e la si riadatta al compito biometrico.

> 💡 **Perché funziona con le CNN.** I primi strati di una CNN imparano filtri generici (bordi, texture, forme) utili a quasi ogni immagine; solo gli ultimi strati sono specifici del compito. Si mantengono i primi strati e si riallenano gli ultimi con pochi dati biometrici: è il tema delle Lezioni 6–8.

<!-- SOURCE_VISUAL id="TAB05-V12" source="Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf" page="55" type="immagini" description="Transfer learning da simulazioni a dati reali" reason="L'esempio di simulazione è mostrato solo con immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB05-V13" source="Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf" page="57" type="schema" description="Applicazione del transfer learning con CNN pre-allenata" reason="Mostra quali strati si riusano e quali si riallenano" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **8. Preparazione dei dati e data augmentation**

_(slide pp. 58–64)_

#### **8.1 La catena di verifica dell'apprendimento**

_(slide pp. 58–59)_

La catena proposta è: **data augmentation → LOPO → stima degli errori**.

**Dati e prestazioni** (p. 59): il **deep learning ha bisogno di molti dati**. Con pochi dati il ML classico può fare meglio; al crescere dei dati le prestazioni del DL continuano a salire, mentre quelle dei metodi classici tendono a saturare.

<!-- SOURCE_VISUAL id="TAB05-V14" source="Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf" page="59" type="grafico" description="Prestazioni in funzione della quantità di dati: machine learning classico contro deep learning" reason="Il confronto delle due curve è visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.2 Data augmentation**

_(slide pp. 60–64)_

> 📌 **Data augmentation.** Generare **nuovi esempi di training** trasformando quelli esistenti. È l'**alleato contro l'overfitting**: una rete può avere **oltre 100 milioni di parametri liberi** da fissare, molti più degli esempi disponibili.

> ⚠️ **Non basta da sola.** La slide avverte che la data augmentation **non risolve l'applicazione da sola**: servono comunque **buoni dati reali**.

| Tipo | Trasformazioni (slide) | Obiettivo |
|---|---|---|
| **#1 geometrica** (p. 61) | **rotazioni**, **traslazioni**, **scale**: la stessa immagine di una pallina da tennis in posizioni, scale e rotazioni diverse | la rete impara a riconoscere l'oggetto indipendentemente da posizione, dimensione, orientamento; i toolbox avanzati hanno funzioni dedicate |
| **#2 rumore** (p. 62) | rumore **gaussiano, additivo, moltiplicativo**; rumore del sensore (pixel), **risoluzioni**, **ambienti** (indoor, outdoor, sfondi) | robustezza alle condizioni di acquisizione |
| **#3 condizioni simulate** (p. 63) | **perturbazione dei landmark** per l'allineamento del volto, **flipping**, **clipping**, **color casting**, **blurring** | riprodurre in training le **condizioni reali**, anche con dataset grandi; si può fare usando **CNN pubbliche** |

Esempio avanzato (p. 64): **3D Face Tracking and Texture Fusion in the Wild**, un approccio **in tempo reale** per **alterare pose ed espressioni** del volto e generare così nuovi esempi.

<!-- SOURCE_VISUAL id="TAB05-V15" source="Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf" page="63" type="griglia di immagini" description="Data augmentation con condizioni simulate: perturbazione landmark, flipping, clipping, color casting, blurring" reason="Le trasformazioni si capiscono vedendo gli esempi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB05-V16" source="Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf" page="64" type="fotogrammi" description="3D Face Tracking and Texture Fusion in the Wild: alterazione in tempo reale di pose ed espressioni" reason="Esempio di augmentation 3D sul volto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **9. Leave One Person Out (LOPO) e LNPO**

_(slide pp. 65–67)_

#### **9.1 Il problema: imparare le persone invece del tratto**

_(slide p. 65)_

Una rete neurale con molti livelli o neuroni, e in generale un modello di ML con molti gradi di libertà, può **imparare a memoria i soggetti** invece della **caratteristica biometrica** che si vuole usare.

- Impara, per esempio, il **colore della pelle** o la **presenza della barba**, ma **non i tratti del volto**.
- L'accuratezza misurata può arrivare **fittiziamente a EER = 0**.

> 📌 **Soluzione.** Si tiene **un gruppo di persone** usato **solo in validazione** per l'autenticazione, **mai visto in allenamento**.

> ⚠️ **Perché una divisione casuale degli esempi non basta.** Se le immagini della stessa persona finiscono sia nel training sia nel test, il modello riconosce nel test dettagli già visti (capelli, occhiali, sfondo di quella sessione). L'errore stimato è ottimistico. La divisione va fatta **per persona**, non per immagine.

#### **9.2 LOPO, LNPO e mix**

_(slide p. 66)_

- **LOPO** (*Leave One Person Out*): si esclude dal training **una persona** alla volta.
- **L(NP)O** (*Leave N Persons Out*): si escludono **N persone** alla volta.
- **MIX**: i gruppi esclusi mescolano soggetti con caratteristiche diverse; ora è **più difficile** che il modello impari a riconoscere solo dal **colore dei capelli**.

Si ripete l'addestramento con $k$ gruppi diversi esclusi. L'**errore di generalizzazione** è la **media degli errori** dei $k$ gruppi testati:

$$E_{gen} = \frac{1}{k} \sum_{i=1}^{k} E_i$$

dove $E_i$ è l'errore misurato sul gruppo $i$, escluso dall'allenamento corrispondente.

#### **9.3 Procedura L(1)PO passo per passo**

_(slide p. 67)_

1. Si crea un **DB di coppie input/output**, per esempio coppie di campioni con output 1 se appartengono alla stessa persona e 0 altrimenti.
2. Si esegue il **primo apprendimento** lasciando nel **validation set** un individuo **mai visto**: i dati delle altre persone formano il **training set**.
3. Si usano i dati degli individui **non impiegati in allenamento** per controllare l'**errore di generalizzazione**.
4. Si **ripete** il processo cambiando l'individuo escluso, **trascrivendo gli errori** di ciascun gruppo, e se ne calcola la media.

> 💡 **Relazione con la k-fold cross validation.** LOPO e LNPO sono una **cross validation a $k$ fold** in cui i fold sono costruiti **per identità**. È lo stesso principio della Lezione 2 sulle valutazioni: stimare l'errore su **utenti nuovi**, cioè nella condizione in cui il sistema lavorerà davvero.

<!-- SOURCE_VISUAL id="TAB05-V17" source="Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf" page="67" type="schema procedurale" description="L(1)PO: DB di coppie input/output, train set e validation set con un individuo mai visto, ripetizione e raccolta degli errori" reason="La procedura è illustrata passo per passo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **10. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **AI** / **ML** / **DL** | insieme generale / apprendimento dai dati / reti neurali profonde |
| **Identificatore** / **Autenticatore** | classificatore multiclasse / binario |
| **Programmazione classica** / **ML** | regole e soglie scritte a mano / modello $F$ allenato sui dati |
| **Representation** / **Evaluation** / **Optimization** | spazio dei modelli possibili / come si giudica un modello / come si esplora lo spazio |
| **Induzione** / **Deduzione** / **Trasduzione** | dai dati alla regola / dalla regola al caso / da casi a casi senza modello generale |
| **Supervisionato** / **Non supervisionato** / **Rinforzo** | $X$ e $Y$ / solo $X$ / ricompense su sequenze di azioni |
| **Classificazione** / **Regressione** | uscita discreta / uscita continua |
| **Transfer learning** / **Data augmentation** | riuso di conoscenza da un altro dominio / nuovi esempi ottenuti trasformando quelli esistenti |
| **Split per immagine** / **LOPO** | stima ottimistica (stessa persona in train e test) / stima su persone mai viste |

---

### **11. Sintesi della lezione**

_(slide p. 68)_

> ✅ **In sintesi.**
> - Un sistema biometrico è un **classificatore**: multiclasse in identificazione, binario in autenticazione; anche le **primitive** (face detection, indexing, qualità, anti-spoofing) sono modelli allenabili.
> - Ogni algoritmo di ML ha tre componenti: **rappresentazione**, **valutazione**, **ottimizzazione**.
> - L'apprendimento **induttivo** ($F(X) \rightarrow Y$) domina in biometria perché abbondano i dati e mancano modelli teorici del tratto; il **deduttivo** è raro; **ChatGPT** è induttivo e trasduttivo.
> - Tipi di apprendimento: **supervisionato**, **non supervisionato**, **semi-supervisionato**, **per rinforzo** (anche per seguire le creste delle impronte).
> - **Transfer learning** da simulazioni o da CNN pre-allenate; **data augmentation** geometrica, con rumore e con condizioni simulate, contro l'overfitting.
> - **LOPO/LNPO**: separare le persone tra training e validazione, altrimenti la rete impara i soggetti e l'EER risulta fittiziamente nullo.
