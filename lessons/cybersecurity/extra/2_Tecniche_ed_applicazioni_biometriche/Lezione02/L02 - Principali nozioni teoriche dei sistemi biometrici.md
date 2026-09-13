# **Lezione 2: Principali nozioni teoriche dei sistemi biometrici**

---

### **0. Panoramica della lezione**

_(slide pp. 1–3)_

La seconda lezione completa il **crash course** iniziato nella Lezione 1 (le slide la intitolano «Seconda parte alla introduzione alla biometria»). Come avverte la nota di p. 3, i lucidi sono tratti dal corso di Sistemi Biometrici per allineare chi non lo ha seguito, ma gli argomenti **possono essere chiesti all'esame**.

L'outline si divide in due parti:

| Parte 1 | Parte 2 |
|---|---|
| aspetti analitici del tratto: variabilità intraclasse e similitudine interclasse | distribuzione degli score genuini e impostori |
| matching: spazio delle feature e metrica | calcolo di FMR e FNMR |
| esempi di diversi tipi di template | curva ROC e significato dell'EER |
| definizione di autenticazione e identificazione | penetration rate e binning error |
| non idealità: distanza tra i template | progettazione e comparazione |
| genuino e impostore; regole di decisione in verifica e identificazione | interoperabilità e scalabilità |

> **Richiamo — Sistemi Biometrici.** I contenuti corrispondono a [L04 — Struttura e aspetti analitici del tratto](<../../../anno3/6_Sistemi_Biometrici/L04 - Struttura dei sistemi biometrici e aspetti analitici del tratto.md>), [L05 — Rappresentazione, matching e indicizzazione](<../../../anno3/6_Sistemi_Biometrici/L05 - Rappresentazione, Estrazione delle Caratteristiche, Matching e Indicizzazione nei DB biometrici.md>), [L06A — Misura dei parametri](<../../../anno3/6_Sistemi_Biometrici/L06A - Misura dei parametri di un sistema biometrico.md>) e [L03 — Comparazione dei sistemi biometrici](<../../../anno3/6_Sistemi_Biometrici/L03 - Comparazione dei sistemi biometrici + Aspetti di Privacy, GDPR e AI Act.md>). Qui i concetti sono ricostruiti in forma completa ma compatta, con particolare cura per formule e calcoli richiesti negli esercizi di dimensionamento.

---

## **Parte A — Aspetti analitici del tratto e del sistema**

### **1. Il tratto cambia nel tempo**

_(slide pp. 4–8)_

#### **1.1 Variabilità temporale**

Due acquisizioni dello stesso individuo **non sono mai identiche**, e la differenza cresce con il tempo trascorso tra enrollment e riconoscimento.

- **Impronta** (p. 5): campioni dello stesso dito a distanza di un **mese**, confrontati tra un soggetto di **22 anni** e uno di **81 anni**. Nella persona anziana la pelle è meno elastica e le creste sono più usurate: la qualità e la stabilità del campione peggiorano.
- **Volto** (p. 6): lo stesso individuo a **+1 anno**, a **+2 anni** e a distanza di **decenni**. L'esempio più noto è **Sharbat Gula**, fotografata nel **1985** e ritrovata nel **2002**.

> 📌 **Conseguenza progettuale.** La **permanenza** (una delle 7 proprietà) non è mai perfetta. Un template registrato oggi si allontanerà progressivamente dal tratto reale: servono soglie tolleranti, riacquisizioni periodiche o tecniche di **aggiornamento del template** (Lezione 15).

<!-- SOURCE_VISUAL id="TAB02-V01" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="5" type="confronto di immagini" description="Impronte dello stesso individuo a distanza di un mese: soggetto di 22 anni contro soggetto di 81 anni" reason="Mostra l'effetto dell'età sulla qualità e stabilità del campione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB02-V02" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="6" type="confronto di immagini" description="Volti dello stesso individuo a +1 anno, +2 anni e decenni; Sharbat Gula nel 1985 e nel 2002" reason="Evidenza visiva della variabilità temporale del volto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2 Umano contro sistema**

_(slide pp. 7–8)_

> 📌 **Un sistema biometrico NON deve necessariamente ragionare come un umano.**

L'esempio della slide p. 8 mostra due immagini in cui il cervello vede a sinistra il volto di **Al Gore** e a destra quello di **Bill Clinton**, ma il **volto è sempre quello di Clinton**: cambia solo il contesto (capelli, sfondo, persone accanto). Il contesto induce in errore l'osservatore umano, mentre un sistema biometrico, che misura il volto, lo classifica come **identico**.

> 💡 **Due lezioni opposte.** L'essere umano è bravissimo a riconoscere volti familiari, ma è influenzato dal contesto; il sistema automatico ignora il contesto e misura solo il tratto. Progettare un sistema imitando la percezione umana non è quindi un obiettivo di per sé: conta la **misura oggettiva**.

<!-- SOURCE_VISUAL id="TAB02-V03" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="8" type="illusione visiva" description="Stesso volto di B. Clinton inserito in due contesti: a sinistra il contesto fa percepire A. Gore" reason="L'illusione si comprende solo guardando l'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Variabilità intraclasse e similitudine interclasse**

_(slide pp. 9–10)_

| | **Variabilità intraclasse** | **Similitudine interclasse** |
|---|---|---|
| Definizione (slide) | variazione del **sample o delle feature dello stesso individuo** tra acquisizioni in istanti diversi | particolare **vicinanza** dei sample o delle feature acquisiti da **individui diversi** |
| Cause / esempi | effetti casuali (rumore del dispositivo); variazioni dello sfondo; variazioni del tratto (invecchiamento, posizione, espressione, usura); occlusioni parziali | gemelli, sosia |
| Errore che provoca | **falsi rifiuti** del genuino (FNM) | **false accettazioni** dell'impostore (FM) |

> 📌 **Il sistema ideale** ha **bassa variabilità intraclasse** (i campioni della stessa persona restano vicini) e **bassa similitudine interclasse**, cioè **alta variabilità interclasse** (i campioni di persone diverse restano lontani). Le slide pp. 23–24 lo esprimono proprio così nello spazio delle feature (§4.2).

<!-- SOURCE_VISUAL id="TAB02-V04" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="10" type="fotografie" description="Esempi di similitudine interclasse: gemelli e sosia" reason="Esempio visivo della vicinanza tra individui diversi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Migliorare il sistema: acquisizione e controllo della qualità**

_(slide pp. 11–20)_

#### **3.1 Le fasi da analizzare**

_(slide pp. 11–13)_

Per migliorare un sistema biometrico si analizzano, nell'ordine della pipeline:

1. **acquisizione del tratto**: sensori e dati ambientali;
2. **controllo della qualità** dell'acquisizione;
3. **rappresentazione**: del sample, estrazione delle caratteristiche, del template;
4. **matching**;
5. **ricerca, organizzazione e scalabilità del DB**.

Lo schema di riferimento è quello di enrollment della Lezione 1: *Trait → Acquisition Module → Sample → Quality Checker → Sample → Feature Extraction Module → Template → DataBase*.

#### **3.2 Il controllo della qualità**

_(slide pp. 14–17)_

Dopo l'acquisizione molti sistemi eseguono un **controllo automatico della qualità** del tratto rilevato, per evitare problemi di funzionamento molto importanti.

- Il controllo produce un **indice di qualità** del sample acquisito.
- **Funzionamento di base**: se l'indice è **sufficientemente alto** si procede; **altrimenti** si acquisisce un altro sample (per esempio chiedendo all'utente di ripetere).

Esempi di indice di qualità:

- **impronte** (p. 16): si misura se **creste (ridge) e valli** hanno un **buon contrasto** e sono **continue**;
- **volto** (p. 17): le **norme ICAO** (*International Civil Aviation Organization*) definiscono i requisiti delle foto dei documenti di viaggio; l'indice è una funzione $Q(I)$ dell'immagine $I$, espressa su una scala discreta come $[0, 1, \dots, 10]$.

> ⚠️ **Il concetto è semplice, la progettazione no.** Stabilire una formula $Q(I)$ che predica davvero se un campione produrrà un buon matching è difficile: un'immagine «bella» per l'occhio umano può essere pessima per l'algoritmo, e viceversa.

<!-- SOURCE_VISUAL id="TAB02-V05" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="16" type="esempi di immagini" description="Impronte con diverso indice di qualità in base a contrasto e continuità di ridge e valli" reason="Mostra concretamente cosa misura un indice di qualità" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB02-V06" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="17" type="schema" description="Norme ICAO per il volto: immagine I, funzione di qualità Q(I) con scala da 0 a 10" reason="Rappresenta l'indice di qualità come funzione dell'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.3 Enhancement**

_(slide pp. 18–19)_

- **Impronte** (p. 18): un algoritmo automatico rileva la presenza delle creste e **migliora solo le porzioni** dell'immagine con qualità sufficiente, per **non «inventare»** strutture inesistenti. La domanda critica è: **quanti artefatti sono stati introdotti?**
- **Volto in videosorveglianza** (p. 19): l'enhancement **riduce il rumore** tipico dei filmati e **aumenta il contrasto delle frequenze spaziali** caratteristiche di un volto.

> ⚠️ **Enhancement e artefatti.** Un miglioramento aggressivo può creare minuzie o dettagli falsi, che poi generano falsi match. Per questo si migliora solo dove l'informazione originale è affidabile.

<!-- SOURCE_VISUAL id="TAB02-V07" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="18" type="prima e dopo" description="Enhancement di un'impronta applicato solo alle regioni di qualità sufficiente" reason="Mostra il rischio di artefatti" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB02-V08" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="19" type="prima e dopo" description="Enhancement di un volto da filmato di videosorveglianza con riduzione del rumore e aumento del contrasto" reason="Esempio di enhancement su volto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.4 Segmentazione**

_(slide p. 20)_

La **segmentazione** seleziona la **regione di interesse** nell'immagine acquisita, scartando lo sfondo. Ogni applicazione ha i propri metodi: segmentazione di un **volto** in una scena, di un'**impronta** rispetto allo sfondo del sensore.

<!-- SOURCE_VISUAL id="TAB02-V09" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="20" type="esempi di segmentazione" description="Segmentazione di un volto e di un'impronta: regione di interesse separata dallo sfondo" reason="Esempio visivo di segmentazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Rappresentazione e spazio delle feature**

_(slide pp. 21–26)_

#### **4.1 Il problema della rappresentazione**

_(slide pp. 21–22)_

Il **funzionamento del riconoscimento** si basa sui principi del **pattern recognition**. La slide ripropone l'elenco delle fasi (§3.1) per spostare l'attenzione dalla qualità alla **rappresentazione** e al **matching**.

#### **4.2 Lo spazio delle feature N-dimensionale**

_(slide pp. 23–24)_

Ogni template è un **punto** in uno spazio a $N$ dimensioni, una per feature (*Feature 1, Feature 2, …, Feature N*). I template dello stesso individuo formano una **nuvola** di punti; nell'esempio compaiono gli individui $i$, $j$ e $k$.

Gli obiettivi di progetto (p. 24):

1. **bassa variabilità intraclasse**: le nuvole di ogni individuo sono **compatte**;
2. **alta variabilità interclasse**: le nuvole di individui diversi sono **lontane** tra loro.

> 💡 **Intuizione geometrica.** Se le nuvole sono compatte e distanti, basta una soglia di distanza per decidere; se si sovrappongono, qualunque soglia produce errori. Tutta la teoria degli errori (Parte B) descrive quanto si sovrappongono queste nuvole.

<!-- SOURCE_VISUAL id="TAB02-V10" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="24" type="diagramma spazio delle feature" description="Nuvole di template degli individui i, j, k nello spazio N-dimensionale; obiettivi bassa variabilità intraclasse e alta variabilità interclasse" reason="Rappresentazione geometrica centrale per capire gli errori" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.3 Le tre componenti della rappresentazione**

_(slide p. 25)_

Il problema della rappresentazione si suddivide in:

1. **rappresentazione del sample** (come è codificato il dato grezzo);
2. **estrazione delle caratteristiche**;
3. **rappresentazione del template** (per esempio una lista di terne $(35, 150, 10), (40, 170, 3), (45, 142, 34), \dots$).

> 📌 La rappresentazione è **uno dei cardini della progettazione** di un sistema biometrico e **influenza direttamente tutte le fasi successive**: matching, dimensione del DB, velocità, accuratezza.

#### **4.4 Diversi tipi di template**

_(slide p. 26)_

| Tratto | Feature set | Esempio di template (slide) | Lunghezza |
|---|---|---|---|
| **Mano** | lunghezza e larghezza delle dita, larghezza del palmo | `[65 53 59 52 62 47 47 45 244 333 253 287 243 149]` | **fissa** |
| **Volto** | *eigen-coefficients* (coefficienti delle eigenfaces) | `[-315.91 -441.10 212.35 … 90.78 -840.12 437.74]` | **fissa** |
| **Impronta** | coordinate delle minuzie e orientamento della cresta nella minuzia | `[(35, 150, 10) (40, 170, 3) (45, 142, 34) …]` | **variabile** |

> ⚠️ **Lunghezza fissa contro variabile.** Con template a lunghezza fissa il matching può usare una distanza tra vettori (per esempio euclidea). Con le minuzie il numero di elementi cambia da un'acquisizione all'altra (minuzie mancanti o spurie) e i punti non sono allineati: il matching richiede **allineamento e ricerca delle corrispondenze** (§5.2).

<!-- SOURCE_VISUAL id="TAB02-V11" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="26" type="confronto template" description="Tre tipi di template: mano (lunghezza fissa), volto eigen-coefficients (fissa), impronta minuzie (variabile)" reason="Confronto visivo dei formati di template" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Il matching**

_(slide pp. 27–29)_

#### **5.1 Dove agisce e che cosa calcola**

_(slide pp. 27–28)_

I problemi di rappresentazione, per quanto riguarda il matching, impattano sul **Matching Module**, presente **solo** nella fase di verifica/identificazione (non nell'enrollment).

Il matching richiede la **definizione di una metrica** nello spazio delle feature:

$$d = \text{matching}(X_a, X_b)$$

dove $X_a$ e $X_b$ sono due template, per esempio un'impronta dell'individuo $k$ e una dell'individuo $j$. A seconda della definizione, il valore è una **distanza** (piccola = simili) o uno **score di similarità** (grande = simili).

#### **5.2 Esempio: matching tra impronte**

_(slide p. 29)_

Input: template con **coordinate delle minuzie e loro angolazione**. I passi sono:

1. **rototraslazione** per allineare le due mappe attorno a una minuzia;
2. **individuazione delle coppie** di minuzie corrispondenti nelle due mappe;
3. **calcolo delle distanze** e dell'**indice di matching**.

Il dettaglio degli algoritmi di matching delle impronte è nella Lezione 3.

<!-- SOURCE_VISUAL id="TAB02-V12" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="29" type="schema a passi" description="Matching tra impronte: rototraslazione delle mappe di minuzie, coppie corrispondenti, calcolo dell'indice di matching" reason="Illustra i tre passi del matching minutiae-based" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte B — Misurare l'accuratezza**

### **6. Quanto sbaglia un sistema biometrico?**

_(slide pp. 30–31)_

- Rispondere alla domanda «**qual è il tasso di errore di verifica/identificazione?**» **non è banale**.
- Descrivere un sistema con **un solo tasso di errore** è **quasi sempre sbagliato**.
- Servono un **insieme di dati** e **curve di funzionamento**.
- Per questo **comparare due sistemi** è difficile: **non basta confrontare due numeri**.

Il motivo è la **soglia** (p. 31, lo stesso schema della Lezione 1): il matching produce uno score (esempio: soglia = 87%); con soglia bassa «entrano tutti», con soglia alta «non entra nessuno». Ogni valore di soglia produce una coppia diversa di errori.

---

### **7. Genuini, impostori e regole di decisione**

_(slide pp. 32–36)_

#### **7.1 Genuino e impostore**

_(slide p. 32)_

- **Genuino**: individuo che accede al sistema e **ha titolo** per farlo.
- **Impostore**: chi prova ad accedere **senza averne titolo**.
- **Genuine comparison**: confronto tra campioni della **stessa** persona; **impostor comparison**: confronto tra campioni di persone **diverse**.

La formulazione del problema è **diversa** in verifica e in identificazione.

#### **7.2 Verifica come classificazione binaria**

_(slide pp. 33–34)_

> 📌 **Problema della verifica.** Dato in ingresso (*query*) un insieme di caratteristiche $X_Q$ e l'identità dichiarata $I$, determinare se la coppia $(I, X_Q)$ appartiene a $\omega_1$ (richiesta **vera**, utente genuino) o a $\omega_2$ (richiesta **falsa**, impostore). $X_Q$ viene confrontato con $X_I$, il template memorizzato associato all'identità $I$.

La regola di decisione è una **comparazione con soglia**:

$$(I, X_Q) \in \begin{cases} \omega_1 & \text{se } S(X_Q, X_I) \geq T \\ \omega_2 & \text{altrimenti} \end{cases}$$

| Simbolo | Significato |
|---|---|
| $S(\cdot,\cdot)$ | funzione che misura la **similitudine** tra $X_Q$ e $X_I$ |
| $S(X_Q, X_I)$ | **similarity score** o **match score** |
| $T$ | **soglia** prefissata |

#### **7.3 Identificazione con reiezione**

_(slide pp. 35–36)_

> 📌 **Problema di identificazione.** Dato $X_Q$, determinare l'identità $I_k$ con $k \in \{1, 2, \dots, M, M+1\}$, dove $I_1, \dots, I_M$ sono le $M$ identità memorizzate e $I_{M+1}$ rappresenta la **reiezione**: nessuna delle identità registrate è sufficientemente simile all'ingresso.

La regola esegue **$M$ comparazioni con soglia**:

$$X_Q \in \begin{cases} I_k & \text{se } \max_{k} S(X_Q, X_{I_k}) \geq T, \quad k = \arg\max_{k} S(X_Q, X_{I_k}) \\ I_{M+1} & \text{altrimenti} \end{cases}$$

dove $X_{I_k}$ è il template dell'identità $I_k$. Il sistema sceglie l'identità **più simile**, ma la accetta solo se lo score supera la soglia: è la regola dell'**identificazione aperta** vista nella Lezione 1.

> ⚠️ **Score o distanza.** In alcuni casi si usa una **distanza** tra $X_Q$ e $X_I$ invece di una similarità: una **grande distanza** corrisponde a un **basso match score**. Con le distanze la disuguaglianza si inverte ($d \leq T$ per accettare) e il $\max$ diventa un $\min$.

> **Richiamo — Sistemi Biometrici, L06A §4–5.** Le stesse formulazioni sono in [L06A](<../../../anno3/6_Sistemi_Biometrici/L06A - Misura dei parametri di un sistema biometrico.md>).

---

### **8. Distanza tra i template e replay attack**

_(slide pp. 37–38)_

$N$ template della **stessa persona**, acquisiti in tempi diversi, **non sono mai uguali**: esiste sempre una distanza nello spazio delle feature, dovuta a rumore di acquisizione, posa diversa, illuminazione o sfondo, condizioni soggettive e ambientali (umidità, temperatura, …).

Conseguenze:

1. la soglia **non può essere resa arbitrariamente severa**: con la soglia al valore di massima somiglianza richiesta (nella formulazione a distanza, $T = 0$) **nessuno** verrebbe riconosciuto;
2. se si osserva una distanza **nulla** tra $X_Q$ e $X_I$, cioè $S(X_Q, X_I)$ uguale al **massimo valore ammissibile**, probabilmente si è di fronte a un **replay attack**: la **copia illecita di un template memorizzato** riproposta in ingresso per frodare il sistema.

> 💡 **Il paradosso della perfezione.** In biometria una corrispondenza «troppo perfetta» è sospetta. Un sistema robusto può quindi rifiutare anche gli score **troppo alti**, perché nessuna acquisizione reale riproduce esattamente un template.

---

### **9. Distribuzioni degli score, False Match e False Non-Match**

_(slide pp. 39–41)_

#### **9.1 Score genuini e impostori**

_(slide p. 40)_

Sia $X_{i\_j}$ il $j$-esimo template dell'individuo $i$. La slide riporta un esempio di score:

| Match **genuini** | Score | Match **impostori** | Score |
|---|---:|---|---:|
| $S(X_{1\_1}, X_{1\_2})$ | 0,7 | $S(X_{1\_1}, X_{3\_2})$ | 0,11 |
| $S(X_{1\_1}, X_{1\_3})$ | 0,8 | $S(X_{4\_1}, X_{3\_1})$ | 0,21 |
| $S(X_{2\_1}, X_{2\_2})$ | 0,4 | $S(X_{5\_2}, X_{1\_2})$ | 0,001 |
| $S(X_{2\_1}, X_{2\_3})$ | 0,5 | $S(X_{2\_2}, X_{1\_2})$ | 0,19 |

Ripetendo i confronti su un intero database si ottengono due **distribuzioni**: quella degli score genuini (in media alti) e quella degli score impostori (in media bassi). Nella realtà le due distribuzioni **si sovrappongono**.

<!-- SOURCE_VISUAL id="TAB02-V13" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="40" type="esempio numerico e distribuzioni" description="Score genuini e impostori calcolati su template X1_1, X1_2 e altri, con istogrammi delle due distribuzioni" reason="Collega i singoli score alle distribuzioni genuine e impostore" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **9.2 Le due tipologie di errore**

_(slide p. 41)_

I due errori **vanno assolutamente distinti**, perché hanno **gravità diverse**.

| Errore | Esempio della slide | Condizione | Nome statistico (slide) |
|---|---|---|---|
| **False Match (FM)** | il **ladro entra** in casa perché il sistema lo scambia per voi | uno **score impostore** è **maggiore** della soglia $T$ | **errore di tipo II** |
| **False Non-Match (FNM)** | **voi non entrate** perché il vostro template non assomiglia abbastanza a quello registrato | uno **score genuino** è **minore** della soglia $T$ | **errore di tipo I** |

> 💡 **Perché «tipo I» e «tipo II».** Se l'ipotesi nulla è «l'utente è genuino», rifiutarla quando è vera (respingere un genuino) è l'errore di tipo I; accettarla quando è falsa (accogliere un impostore) è l'errore di tipo II.

#### **9.3 Stimare FMR e FNMR dagli score**

> 💡 **Esempio svolto (costruito sui valori di p. 40).** Con score di similarità e regola «accetta se $s \geq T$»:
>
> $$FMR(T) = \frac{\#\{\text{score impostori} \geq T\}}{\#\{\text{confronti impostori}\}} \qquad FNMR(T) = \frac{\#\{\text{score genuini} < T\}}{\#\{\text{confronti genuini}\}}$$
>
> - con $T = 0{,}5$: nessuno score impostore supera 0,5, quindi $FMR = 0/4 = 0\%$; lo score genuino 0,4 è sotto soglia, quindi $FNMR = 1/4 = 25\%$;
> - con $T = 0{,}2$: lo score impostore 0,21 supera la soglia, quindi $FMR = 1/4 = 25\%$; tutti i genuini superano 0,2, quindi $FNMR = 0\%$.
>
> Abbassare la soglia ha scambiato un tipo di errore con l'altro. Con soli 4 confronti per tipo le stime sono ovviamente grezze: la precisione statistica delle stime è il tema delle Lezioni 9–10.

---

### **10. Curve DET e ROC**

_(slide pp. 42–43)_

#### **10.1 Definizioni**

_(slide p. 42)_

Le prestazioni si esprimono mostrando **come variano FMR e FNMR al variare della soglia $T$**. Con $p_{imp}(s)$ e $p_{gen}(s)$ densità degli score impostori e genuini:

$$FMR(T) = \int_{T}^{+\infty} p_{imp}(s)\, ds \qquad FNMR(T) = \int_{-\infty}^{T} p_{gen}(s)\, ds$$

Sul grafico delle due distribuzioni, $FMR(T_1)$ è l'area della coda degli **impostori a destra** di $T_1$ e $FNMR(T_1)$ l'area della coda dei **genuini a sinistra** di $T_1$.

La **curva DET** (*Decision Error Tradeoff*) è la curva parametrica in $T$:

$$DET(T) = \big(FMR(T),\, FNMR(T)\big), \qquad DET(T) \rightarrow \begin{cases} (1, 0) & T \rightarrow -\infty \\ (0, 1) & T \rightarrow +\infty \end{cases}$$

- con **soglia bassissima** tutti vengono accettati: $FMR = 1$, $FNMR = 0$;
- con **soglia altissima** tutti vengono rifiutati: $FMR = 0$, $FNMR = 1$.

La slide definisce la **curva ROC** (*Receiver Operating Characteristic*) come complemento:

$$ROC(T) = 1 - DET(T)$$

> ⚠️ **Convenzioni sulla ROC.** In letteratura la ROC biometrica si traccia spesso come $\big(FMR(T),\, 1 - FNMR(T)\big)$, cioè tasso di falsi match contro **Genuine Acceptance Rate**. La formula della slide complementa entrambe le coordinate; il contenuto informativo è lo stesso, ma prima di leggere una ROC bisogna controllare **quali grandezze sono sugli assi**.

> 📌 **Equal Error Rate (EER).** È il valore in cui le due curve di errore si incrociano: $FMR(T^*) = FNMR(T^*) = EER$. È un **numero di sintesi** utile per confrontare algoritmi, ma **non è un punto di lavoro consigliato**: nessuna applicazione reale sceglie la soglia solo perché i due errori sono uguali.

<!-- SOURCE_VISUAL id="TAB02-V14" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="42" type="grafici distribuzioni e curva DET" description="Distribuzioni impostori e genuini con soglia T1, aree FMR(T1) e FNMR(T1); curva DET con FMR e FNMR" reason="Visualizza il legame tra aree delle code e punti della curva" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.2 Regioni di funzionamento**

_(slide p. 43)_

Regolando la soglia si regola il **livello di sicurezza**. La slide individua tre regioni sulla DET:

| Regione | Esempio | Preferenza | Motivazione |
|---|---|---|---|
| **Altissima sicurezza** | deposito di esplosivi | **FMR basso**, anche con FNMR più alto | meglio che un genuino ritenti piuttosto che entri un impostore |
| **Applicazioni civili normali** | uso quotidiano | compromesso | equilibrio tra sicurezza e comodità |
| **Applicazioni forensi** | trovare tutti i possibili candidati per un'impronta trovata su una scena | **FMR alto** ma **FNMR basso** | non perdere possibili criminali perché non «matchati»; i falsi candidati si scartano dopo |

Nell'uso forense il **secondo livello di analisi** (alibi, altre prove) realizza l'**approccio garantista** tipico della legislazione nel caso di dubbio: la biometria restringe il campo, non condanna.

> ⚠️ **La soglia non si regola completamente a piacimento.** Oltre certi valori alcuni sistemi diventano **non sensibili**: la curva non raggiunge tutti i punti teorici, perché gli score sono discreti o saturano.

<!-- SOURCE_VISUAL id="TAB02-V15" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="43" type="curva DET con regioni" description="Regioni di funzionamento sulla DET: altissima sicurezza (deposito esplosivi), applicazioni civili, applicazioni forensi" reason="Collega la posizione sulla curva al tipo di applicazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **11. Utenti, usabilità e valutazione**

_(slide pp. 44–48)_

#### **11.1 Lo zoo di Doddington**

_(slide pp. 44–45)_

Gli utenti non si comportano tutti allo stesso modo. Doddington li classifica con quattro animali:

| Animale | Descrizione (slide) | Effetto sugli errori |
|---|---|---|
| **Pecore** (*sheep*) | feature molto distintive e **bassa variabilità intraclasse** | **bassi FMR e FNMR** |
| **Capre** (*goats*) | **alta variabilità intraclasse** | molti **falsi rifiuti** |
| **Agnelli** (*lambs*) | feature che si **sovrappongono** estesamente a quelle degli altri: **bassa distanza interclasse** | **FAR alto** |
| **Lupi** (*wolves*) | capaci di **manipolare il tratto** (di solito comportamentale) per impersonare un utente legittimo | **aumentano il FAR** |

> ⚠️ **Precisazione sulle slide.** Per gli agnelli la slide scrive che «un agnello entra al posto di una pecora». Nella formulazione originale di Doddington (e in [L06A §11](<../../../anno3/6_Sistemi_Biometrici/L06A - Misura dei parametri di un sistema biometrico.md>)) gli agnelli sono soprattutto le **vittime** dei falsi match: il loro template è **facile da imitare**, quindi sono gli altri, e in particolare i lupi, a essere accettati **al posto loro**. In entrambe le letture l'effetto è lo stesso: gli agnelli fanno salire il FAR.

<!-- SOURCE_VISUAL id="TAB02-V16" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="45" type="illustrazione" description="Zoo di Doddington: pecore, capre, agnelli e lupi con le rispettive caratteristiche" reason="Ancora visiva per ricordare le quattro categorie" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.2 Usabilità: ISO 9241-11**

_(slide p. 46)_

> 📌 **ISO 9241-11.** *The usability is the extent to which a product can be used by specified users to achieve specified goals in a specified context of use with **effectiveness**, **efficiency** and **satisfaction**.*

- L'**accettazione** da parte degli utenti è **soggettiva**, ma si possono adottare metriche, per esempio **questionari** con punteggi di gradimento.
- La slide cita un rapporto NIST sull'effetto di **altezza e angolo** di acquisizione (NISTIR 7504) come esempio di studio di usabilità.
- L'usabilità **potrebbe essere una parte importante** del progetto di un sistema.

#### **11.3 Valutazioni indipendenti e strategie di test**

_(slide pp. 47–48)_

- Si possono usare **laboratori esterni accreditati** (esempio: il **NIST**) per valutare le caratteristiche di un sistema; l'accreditamento dei laboratori di prova fa riferimento alla norma **ISO/IEC 17025**.
- La norma **ISO/IEC 19795** (*Biometric Performance Testing and Reporting*) distingue tre strategie di valutazione:

| Valutazione | Che cosa si testa | Come |
|---|---|---|
| **Technology** | gli **algoritmi** | su **DB di dati pubblici**, in modo ripetibile |
| **Scenario** | la **tecnologia** in un ambiente realistico | in **stanze attrezzate** per confrontare diverse tecnologie |
| **Operational** | il **sistema completo** | **in deployment**, con utenti reali |

> 💡 **Come scegliere.** Una technology evaluation confronta algoritmi a parità di dati, ma non dice come si comporterà il sistema con utenti reali. Un'operational evaluation è la più realistica ma non è ripetibile. Le tre valutazioni sono complementari.

<!-- SOURCE_VISUAL id="TAB02-V17" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="48" type="schema" description="Strategie di valutazione ISO/IEC 19795: Technology (DB pubblici), Scenario (stanze attrezzate), Operational (sistema in deployment)" reason="Riepilogo visivo delle tre strategie" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte C — Progettazione, comparazione e scalabilità**

### **12. Comparare i sistemi biometrici**

_(slide pp. 49–53)_

#### **12.1 Un problema multi-parametro**

_(slide p. 50)_

La comparazione è **molto complessa**: i **parametri di giudizio sono molti** e **difficilmente stimabili**. La slide li rappresenta su assi con i relativi range:

| Parametro | Range indicativo (slide) |
|---|---|
| Gradimento degli utenti | da basso ad alto |
| Accuratezza | 99,0% – 99,99% – 99,9999% |
| Velocità | fino a 10000 |
| Usabilità | da complesso a facile |
| Costo del sistema | fino a 10000 |
| Scalabilità | da zero a 10000 |
| Interoperabilità | da 1 tipo a 6 tipi |

<!-- SOURCE_VISUAL id="TAB02-V18" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="50" type="grafico a radar" description="Parametri di comparazione con scale: gradimento, accuratezza 99.0/99.99/99.9999, velocità, usabilità, costo, scalabilità, interoperabilità" reason="Unità e significato delle scale sono chiari solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **12.2 Comparazione qualitativa-quantitativa dei tratti**

_(slide p. 51)_

| Tratto | Autenticazione | Identificazione | Variazione nella vita / giorno dopo giorno | Costo sensore [USD] | Dimensioni sensore |
|---|---|---|---|---|---|
| **Impronta** | sì | sì | nessuna / piccola | 10–100 | molto piccola |
| **Occhio (iride)** | sì | sì | nessuna / molto piccola | 100–1000 | media |
| **Mano** | sì | no | molta / molto piccola | — | piccola |
| **Volto** | sì | no | molta / media | — | molto piccola |
| **Voce** | sì | no | molta / media | 0–100 | molto piccola |
| **Firma** | sì | no | molta / media | — | media |

> ⚠️ **Tabella parziale.** La slide contiene anche la colonna «**Massimo numero di sample indipendenti**» e alcuni costi che non sono presenti nel livello testo del PDF; le celle con «—» vanno completate sull'immagine.

<!-- SOURCE_VISUAL id="TAB02-V19" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="51" type="tabella" description="Comparazione dei tratti: autenticazione, identificazione, variazione nella vita e giorno dopo giorno, massimo numero di sample indipendenti, costo e dimensioni del sensore" reason="Completare la colonna dei sample indipendenti e i costi mancanti" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **12.3 Non tutti i tratti servono per l'identificazione**

_(slide p. 52)_

> 📌 **Ad oggi solo iride e impronta** sono usate per **identificazione 1:N con N grandi**.

I requisiti per il funzionamento 1:N su larga scala sono:

| Requisito | Valore indicativo (slide) |
|---|---|
| accuratezza | tasso di errore $\ll 10^{-5}$ |
| dimensione del template | $< 1$ kB |
| tempo per un singolo confronto | $< 1$ ms |

Per questo **mano, volto, voce e firma** sono usati solo per **autenticazione (1:1)** oppure per **identificazione 1:N con N di qualche decina** di persone (per esempio i dipendenti di una banca).

> 💡 **Perché servono questi numeri.** In identificazione un probe viene confrontato con $N$ template: se $N = 10^6$ e ogni confronto richiede 1 ms, una ricerca completa dura $10^3$ s, cioè oltre 16 minuti, e la probabilità di almeno un falso match cresce circa come $N \cdot FMR$ (Lezione 1, §6.2). Template piccoli, confronti rapidissimi ed errori per confronto minuscoli sono condizioni **necessarie**, non accessorie.

> ⚠️ **Precisazione.** Oggi il riconoscimento del **volto** basato su deep learning viene impiegato anche in identificazioni su database di grandi dimensioni; l'affermazione della slide va letta nel contesto dei sistemi classici che il crash course presenta. Il tema torna nelle Lezioni 7–9 e 18.

#### **12.4 Gradimento e accuratezza**

_(slide p. 53)_

Il **gradimento** si può misurare con **questionari**, **interviste**, oppure facendo **scegliere agli utenti tra due sistemi alternativi**. Va messo in relazione con l'accuratezza: i tratti più graditi non sono in genere i più accurati.

---

### **13. Interoperabilità e scalabilità**

_(slide pp. 54–57)_

- **Interoperabilità** (p. 54): diventerà **sempre più importante**, perché il **numero di tipi di sensori** è destinato ad aumentare. Un template acquisito con un sensore deve poter essere confrontato con uno acquisito da un sensore diverso.
- **Scalabilità** (p. 55): come vengono influenzate **accuratezza** e **velocità** al crescere del numero di campioni contenuti nel sistema? **Attenzione**: molto spesso è un aspetto **non ben documentato** dai produttori.
- **Indicizzazione** (p. 56): come rendere le ricerche **più efficienti e veloci**.

> 📌 **Requisito di scalabilità (p. 57).** I sistemi che gestiscono molte identità devono operare efficacemente al crescere degli utenti registrati, e il **tasso di peggioramento delle prestazioni** deve essere **minore del tasso di inserimento** di nuovi utenti.

Esempi di sistemi USA con milioni di identità che iniziano a integrare la biometria: **registro elettorale e patenti**, **controllo delle frontiere** aria/terra/acqua, **applicazioni bancarie e transazioni online**.

---

### **14. Partizione del database e penetration rate**

_(slide pp. 58–63)_

#### **14.1 Origine storica: la classificazione di Henry**

_(slide p. 58)_

**Sir Edward Henry**, ispettore della polizia in India, pubblica nel **1900** *Classification and Uses of Fingerprints*. Il suo metodo diventa lo **standard in Gran Bretagna** fino all'avvento dell'elaboratore elettronico, ed è ancora oggi **fondamentale** per gestire efficacemente i DB di impronte.

#### **14.2 Definizione di penetration rate**

_(slide p. 59)_

Gestire la complessità delle ricerche al crescere dei template è possibile solo con un'attenta **organizzazione del DB**: un DB organizzato permette di confrontare il template in ingresso **non con tutti** i template, ma **solo con quelli di una partizione**.

> 📌 **Penetration rate** (tasso di penetrazione). *The expected proportion of the templates to be searched over all input samples under the rule that the search proceeds through the entire partition regardless of whether a match is found.* È la **frazione attesa del database** che viene esaminata per ogni ricerca, supponendo di scandire **tutta** la partizione anche se il match viene trovato prima.

Più è **basso**, più la ricerca è **veloce**.

#### **14.3 Esempio: DB di 900 persone con test su probe reali**

_(slide pp. 60–61)_

Un DB contiene **900 persone** divise in tre classi: **bambini 200**, **teenager 300**, **adulti 400**. Invece di cercare ogni probe tra tutti i 900 template, si costruisce un **classificatore bambino/teenager/adulto** e si cerca solo nella classe corrispondente.

Per misurare il penetration rate secondo la definizione si usano **6 persone estratte casualmente**: 1 bambino, 1 teenager e 4 adulti. Ogni probe richiede tanti confronti quanti sono i template del suo bin:

$$P_{rate} = \frac{1}{N_{ricerche}} \sum_{j=1}^{N_{ricerche}} \frac{n_j}{N_{Tot}} = \frac{1}{6}\left(\frac{200}{900} + \frac{300}{900} + 4 \cdot \frac{400}{900}\right) = \frac{2100}{5400} \approx 38{,}9\%$$

dove $n_j$ è il numero di template nel bin scandito dalla ricerca $j$ e $N_{Tot} = 900$.

> ⚠️ **Il test va fatto con i probe realistici.** Nel DB sono memorizzati tutti i genuini, ma alcuni usano il sistema **più spesso** di altri. Il penetration rate reale dipende da **chi si presenta davvero**: la stima corretta richiede una simulazione con probe simili a quelli dell'applicazione.

<!-- SOURCE_VISUAL id="TAB02-V20" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="61" type="schema di calcolo" description="Feature extraction e classificatore Bambino/Teenager/Adulto; 6 probe con 200, 300 e 4 volte 400 confronti; Prate = 38,9%" reason="Schema del calcolo del penetration rate con probe reali" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **14.4 Stima dal solo database**

_(slide p. 62)_

Nelle **fasi iniziali del progetto** non si dispone di utenti reali, ma solo del DB. Si usano allora le **partizioni stesse come campione** degli utenti, ipotizzando che **ogni genuino registrato si presenti una volta**. Con $K$ classi, $N_i$ utenti nella classe $i$ e $N_{Tot}$ utenti totali:

$$P_{rate} = \frac{1}{N_{Tot}} \sum_{i=1}^{K} N_i \cdot \frac{N_i}{N_{Tot}} = \sum_{i=1}^{K} \left(\frac{N_i}{N_{Tot}}\right)^2$$

Ogni classe $i$ contribuisce con $N_i$ ricerche, ciascuna delle quali scandisce la frazione $N_i / N_{Tot}$ del DB. Nell'esempio:

$$P_{rate} = \frac{200}{900} \cdot \frac{200}{900} + \frac{300}{900} \cdot \frac{300}{900} + \frac{400}{900} \cdot \frac{400}{900} = 0{,}0494 + 0{,}1111 + 0{,}1975 \approx 35{,}8\%$$

> 💡 **Perché 38,9% e 35,8% sono diversi.** Le due stime usano **popolazioni di probe diverse**: i 6 utenti estratti contengono proporzionalmente più adulti (4 su 6 = 67%) di quanti ne contenga il DB (400 su 900 = 44%), e gli adulti hanno il bin più grande. Una distribuzione dei probe diversa da quella del DB cambia il penetration rate.

> ⚠️ **$K$ bin non danno un penetration rate di $1/K$.** Con 3 bin uguali da 300 template si otterrebbe $3 \cdot (1/3)^2 = 33{,}3\%$, che è il **minimo** possibile con 3 classi: per la disuguaglianza di Cauchy-Schwarz $\sum_i (N_i/N_{Tot})^2 \geq 1/K$, con uguaglianza solo per bin uguali. Qualunque sbilanciamento dei bin lo fa crescere (qui 35,8%). Le popolazioni reali quasi mai si distribuiscono in modo uniforme tra le classi (p. 66).

#### **14.5 Caso realistico: sistema nazionale di impronte**

_(slide p. 63)_

- Le impronte si classificano in «**arch**», «**loop**», ecc.
- Se il sistema usa **due impronte**, il matching si esegue solo sulla porzione di DB con individui che hanno **entrambe** le impronte dello stesso tipo di quelle in ingresso.
- I sistemi di grandi dimensioni su **2 impronte** con **4–5 classi** funzionano correttamente con **penetration rate del 10%**.
- Serve un **algoritmo automatico molto robusto** per la classificazione delle impronte.

---

### **15. Binning e binning error**

_(slide pp. 64–67)_

#### **15.1 Il binning**

_(slide pp. 64–65)_

Quando il DB viene creato, i template vengono disposti nelle **partizioni** (*bins*) da un **classificatore**. Nell'esempio semplificato (di solito si usano più classi per impronta) il sistema usa **due impronte**, destra (DX) e sinistra (SX), ciascuna classificata come «arch» o «loop»: si ottengono **4 bin**.

| Bin | Impronta DX | Impronta SX |
|---|---|---|
| BIN1 | arch | arch |
| BIN2 | arch | loop |
| BIN3 | loop | arch |
| BIN4 | loop | loop |

<!-- SOURCE_VISUAL id="TAB02-V21" source="Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf" page="64" type="schema a blocchi" description="Enrollment con classificatore che assegna i template a 4 bin (DX/SX arch/loop)" reason="Mostra dove si inserisce il classificatore nella pipeline" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **15.2 Binning error**

_(slide pp. 66–67)_

> 📌 **Binning error.** Si verifica quando l'individuo presenta i propri tratti e l'algoritmo di classificazione **sbaglia il bin**. Se l'individuo era registrato, **con alta probabilità non si avrà un match**, perché il suo template si trova in un bin **diverso** da quello scandito.

Il binning error è quindi un **falso non-match causato dalla partizione**, non dal matcher. Sia $p$ la probabilità di sbagliare **una** classificazione. Con **due** classificazioni indipendenti (DX e SX) il bin è sbagliato se **almeno una** delle due è errata:

$$\begin{aligned}
\text{Binning error} &= P(\text{almeno 1 errore}) = 1 - P(0 \text{ errori}) \\
&= 1 - (1-p)(1-p) = 1 - (p^2 - 2p + 1) \\
&= 2p - p^2 \approx 2p
\end{aligned}$$

L'approssimazione vale perché $p$ è piccola e $p^2$ lo è ancora di più. Il passaggio chiave usa l'evento complementare: la probabilità di **non** sbagliare una classificazione è $1 - p$, perché le due ipotesi (sbagliare, non sbagliare) completano lo spazio delle possibilità.

> 💡 **Generalizzazione (coerente con [L05 §9](<../../../anno3/6_Sistemi_Biometrici/L05 - Rappresentazione, Estrazione delle Caratteristiche, Matching e Indicizzazione nei DB biometrici.md>)).** Con $n$ classificazioni indipendenti: $\text{Binning error} = 1 - (1-p)^n \approx n\,p$. Esempio: con $p = 5\%$ e 2 impronte il valore esatto è $2 \cdot 0{,}05 - 0{,}05^2 = 9{,}75\%$, contro l'approssimazione $10\%$.

> ⚠️ **$p$ deve essere piccola, o non funziona nulla.** Il binning riduce il penetration rate ma aggiunge un errore. Più classi rendono i bin più piccoli (ricerca più veloce) ma rendono la classificazione più difficile ($p$ cresce): la scelta del numero di classi è un **compromesso** tra velocità e accuratezza.

---

### **16. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Variabilità intraclasse** / **Similitudine interclasse** | campioni della stessa persona diversi tra loro / campioni di persone diverse simili tra loro |
| **Genuine** / **Impostor comparison** | confronto tra campioni della stessa persona / di persone diverse |
| **False Match** / **False Non-Match** | impostore accettato (tipo II) / genuino rifiutato (tipo I) |
| **FMR** / **FNMR** al crescere di $T$ | diminuisce / aumenta |
| **DET** / **ROC** | $(FMR, FNMR)$ al variare di $T$ / complemento (per la slide $1 - DET$) |
| **EER** / **punto di lavoro** | numero di sintesi dove i due errori coincidono / soglia scelta in base all'applicazione |
| **Score** / **Distanza** | alto = simile / basso = simile (regola e $\max$/$\min$ si invertono) |
| **Template a lunghezza fissa** / **variabile** | vettore confrontabile con una distanza / insieme di minuzie da allineare |
| **Penetration rate** / **Binning error** | frazione di DB scandita (velocità) / probabilità di cercare nel bin sbagliato (accuratezza) |
| **Technology** / **Scenario** / **Operational evaluation** | algoritmi su DB pubblici / tecnologie in ambiente attrezzato / sistema completo in esercizio |
| **Pecore** / **Capre** / **Agnelli** / **Lupi** | pochi errori / molti falsi rifiuti / facili da imitare / abili a imitare |

---

### **17. Sintesi della lezione**

_(slide p. 68)_

> ✅ **In sintesi.**
> - Il tratto **cambia nel tempo** e il sistema non ragiona come un umano: conta la misura, non il contesto.
> - **Variabilità intraclasse** e **similitudine interclasse** sono le due cause degli errori; nello spazio delle feature si vogliono nuvole compatte e lontane.
> - **Qualità**, **enhancement** e **segmentazione** migliorano l'acquisizione; il **quality checker** decide se riacquisire.
> - Tipi di **template** a lunghezza fissa (mano, volto) e variabile (minuzie); il **matching** richiede una metrica.
> - Regole di decisione con soglia in **verifica** (classificazione binaria $\omega_1/\omega_2$) e in **identificazione** (argmax con reiezione $I_{M+1}$); uno score massimo è sospetto di **replay attack**.
> - **FMR** e **FNMR** come aree delle code delle distribuzioni; curve **DET** e **ROC**; **EER**; regioni di funzionamento civili, di alta sicurezza e forensi.
> - **Zoo di Doddington**, usabilità **ISO 9241-11**, valutazioni **ISO/IEC 19795**.
> - Comparazione dei tratti: solo **iride e impronta** per 1:N con N grandi; **interoperabilità** e **scalabilità**.
> - **Penetration rate** (38,9% con probe reali, 35,8% stimato dal DB) e **binning error** $2p - p^2 \approx 2p$.
