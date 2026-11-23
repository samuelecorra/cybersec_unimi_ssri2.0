## **Lezione 20: Maledizione della dimensionalità, neuroni e perceptron — le reti neurali feed-forward e i loro metodi di apprendimento**

La lezione si apre con un problema: **perché più feature possono far male?** La **maledizione della dimensionalità** mostra che, aggiungendo dimensioni, lo spazio si svuota (le celle crescono come $b^d$), le distanze perdono contrasto e i modelli polinomiali esplodono in numero di coefficienti. Poi si passa all’**ispirazione biologica**: dal neurone del cervello al **neurone artificiale**, il mattone di base che somma ingressi pesati, li confronta con una soglia e applica una funzione di attivazione. Il primo learner è il **perceptron** di Rosenblatt, con la **regola delta** e un esempio svolto completo sull’AND, fino al suo limite: lo **XOR**. Il metodo generale sono le **reti multistrato** con la **backpropagation**, una discesa del gradiente in due fasi, con i problemi del **gradiente che svanisce o esplode**. In pratica: una prima rete in MATLAB e, in appendice, lo XOR risolto a mano.

> **Fonte.** La lezione segue il PDF dell’edizione 2026/27 `sources_2026-27/L20-web.pdf` (76 pagine, di cui 67–76 di appendice). I segnaposto per le figure indicano la pagina del PDF. Il PDF dell’edizione precedente è conservato nella cartella.


### **0. Panoramica**


- **il problema**: perché più feature possono far male;
- **l’ispirazione**: dai neuroni del cervello alle reti neurali;
- **il mattone di base**: il neurone artificiale;
- **il learner più semplice**: il perceptron e il suo limite (XOR);
- **il metodo generale**: reti multistrato e backpropagation;
- **la pratica**: una prima rete in MATLAB; lo XOR a mano (appendice).

<!-- SOURCE_VISUAL id="IS20-V01" source="L20-web.pdf" page="2" type="grafico" description="Miniature della panoramica: bin in 1D, 2D e 3D, epoche del perceptron sull'AND, sigmoide e sua derivata" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **1. Teoria: la maledizione della dimensionalità**


**Quando le dimensioni contano: ogni volta che si vuole aggiungere una feature nuova, pensarci…**

#### **1.1. Aggiungere feature non è sempre una buona idea!**

- $d=2$: **due cluster densi** (classe 0 e classe 1).
- $d=3$: **gli stessi dati + 1 feature casuale** ($x_3$).

Le feature **irrilevanti o rumorose** **aumentano $d$ senza aggiungere informazione**: **diluiscono le distanze**.

| Feature | Accuratezza 1-NN (120 campioni, leave-one-out) |
|---|---:|
| $d=2$ | **97,5%** |
| + 1 feature casuale | 96,7% |
| + 10 feature casuali | 75,0% |
| + 100 feature casuali | **57,5%** |
| (livello del caso) | 50% |

<!-- SOURCE_VISUAL id="IS20-V02" source="L20-web.pdf" page="4" type="grafico" description="Due cluster densi in 2D e gli stessi dati con una feature casuale aggiunta in 3D" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Dati sintetici (seme fisso): solo geometria, nessuna misura reale. **Richiamo dalla Lezione 19**: il 1-NN assegna la classe del campione di training più vicino: **dipende solo dalle distanze**.

> 💡 **Perché il rumore vince (nota didattica).** La distanza euclidea al quadrato è la somma dei contributi delle singole feature. Con 2 feature informative e 100 casuali, 100 dei 102 termini della somma sono rumore: la distanza tra due punti della stessa classe diventa quasi uguale a quella tra punti di classi diverse, e il vicino più prossimo è quasi casuale. 57,5% è appena sopra il 50% del caso. È la versione numerica dell’avvertenza della Lezione 18 (§1.1).

#### **1.2. C’è un altro motivo: la maledizione della dimensionalità**

Creare e aggiungere feature **può essere una buona idea**, ma con alcuni **inconvenienti rilevanti**.

<!-- SOURCE_VISUAL id="IS20-V03" source="L20-web.pdf" page="5" type="figura" description="Illustrazione della maledizione della dimensionalità" reason="Immagine visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.3. Definizione**

Nella data science e nel machine learning, **aggiungere attributi aiuta sempre chi apprende?** Più informazione può far male? **A volte sì!**

> 📌 **Definizione.** La **maledizione della dimensionalità** è l’insieme dei fenomeni che compaiono quando i dati vengono analizzati in **spazi ad alta dimensionalità**: il **volume dello spazio cresce così in fretta con il numero di feature $d$** che i dati disponibili diventano **sparsi**.

**Quanto è grande $d$ in pratica?** Un’immagine **Full HD** data pixel per pixel a una rete:

- immagine $1920\times 1080$ pixel, livelli di grigio: $d=1920\cdot 1080=2\,073\,600$ feature, quindi l’input è $x\in\mathbb{R}^{2\,073\,600}$;
- **a colori (RGB)**: $d=3\cdot 2\,073\,600\approx 6{,}2\times 10^6$.

#### **1.4. Il classificatore «a bin»: un esempio semplice**

> 📌 **Definizione.** Il **classificatore a bin** divide lo spazio delle feature **uniformemente in bin** (celle); un campione nuovo prende la **classe di maggioranza dei campioni di training del suo bin**.

**Input**: training set $\{(x_i,y_i)\}_{i=1}^N$, $N=12$, **una feature** ($d=1$), **$b=3$ bin**. **Output**: $n_j$ = numero di campioni nel bin $j$: $n_1=4$, $n_2=5$, $n_3=3$.

<!-- SOURCE_VISUAL id="IS20-V04" source="L20-web.pdf" page="7" type="grafico" description="Dodici campioni di tre classi su una feature divisa in tre bin" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

In media $N/b=12/3=4$ campioni per bin: **abbastanza per stimare la classe di maggioranza**.

#### **1.5. 1D → 2D → 3D: i bin esplodono**

$$\#\text{bin}=b^d,\qquad \text{campioni per bin}\approx\frac{N}{b^d},$$

con $N$ campioni, $b$ bin per feature, $d$ feature. **Ogni bin ha bisogno di campioni** per stimare la classe di maggioranza. Con gli **stessi $N=12$ campioni** e $b=3$:

| $d$ | bin | campioni per bin | bin vuoti |
|---:|---:|---:|---:|
| 1 | 3 | $12/3=4$ | 0 |
| 2 | $3^2=9$ | $12/9\approx 1{,}3$ | 3 |
| 3 | $3^3=27$ | $12/27\approx 0{,}44$ | 18 |

<!-- SOURCE_VISUAL id="IS20-V05" source="L20-web.pdf" page="8" type="grafico" description="Gli stessi 12 campioni nei bin in 1D, 2D e 3D con i bin vuoti" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Stiamo costruendo uno spazio quasi vuoto!** In un bin vuoto il classificatore non sa che cosa rispondere; in un bin con un solo campione la «maggioranza» è quel campione, rumore compreso.

#### **1.6. Numeri sulla maledizione della dimensionalità**

**30 frutti, 100 celle: 82 vuote** (limoni, mele, arance in larghezza e altezza, cm). **Quanti vicinati ci sono?** Ogni feature è divisa in $b=10$ intervalli:

| $d$ | celle |
|---:|---|
| 2 | $10^2=100$ (30 frutti: **82 celle vuote**) |
| 3 | $10^3=1000$ |
| 10 | $10^{10}$ |
| 1000 | $10^{1000}$ |

**Numero stimato di atomi nell’universo: $10^{78}$–$10^{82}$. Nessun dataset può riempire $10^{1000}$ celle.**

<!-- SOURCE_VISUAL id="IS20-V06" source="L20-web.pdf" page="9" type="grafico" description="Trenta frutti in una griglia 10x10 di larghezza e altezza con 82 celle vuote" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Quanti dati servirebbero (nota didattica).** Per avere in media anche solo **un campione per cella** servono $10^d$ campioni: 100 in 2D, un milione in 6D, $10^{10}$ in 10D. Un dataset industriale di 100 000 campioni con 10 feature lascia quindi vuote almeno $10^{10}-10^5$ celle, cioè quasi tutte. Nessun metodo può «riempire» lo spazio: deve **sfruttare una struttura** dei dati (§1.8).

#### **1.7. In alta dimensione, «più vicino» perde significato**

**500 campioni uniformi in $[0,1]^d$, una richiesta.** $\text{dist}_{\min}$ e $\text{dist}_{\max}$: distanza euclidea del campione più vicino e del più lontano.

$$r=\frac{\text{dist}_{\max}-\text{dist}_{\min}}{\text{dist}_{\min}}$$

| $d$ | $r$ | lettura |
|---:|---:|---|
| 2 | 52 | il più lontano è **53 volte** più lontano |
| 10 | 2,5 | |
| 100 | 0,39 | |
| 1000 | 0,11 | **solo l’11% più lontano** |

<!-- SOURCE_VISUAL id="IS20-V07" source="L20-web.pdf" page="10" type="grafico" description="Rapporto r tra vicino più lontano e più vicino al crescere di d in scala logaritmica" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Il vicino più prossimo e il più lontano si somigliano.** Richiamo dalla Lezione 19: **k-NN e k-means usano le distanze**: quando tutte le distanze si somigliano, **perdono contrasto**.

> 💡 **Perché succede (nota didattica).** La distanza al quadrato tra due punti uniformi è la somma di $d$ termini indipendenti; per la legge dei grandi numeri la somma si concentra attorno alla sua media ($d/6$), con una dispersione che cresce solo come $\sqrt d$. Il rapporto dispersione/media scende come $1/\sqrt d$: tutte le distanze diventano quasi uguali. In 1000 dimensioni il «vicino» è più vicino del «lontano» solo dell’11%.

#### **1.8. Come affrontare la maledizione della dimensionalità?**

- **I dati reali sono spesso confinati in una regione con dimensionalità effettiva più bassa**, e di solito sono **lisci**: **esistono ancora tecniche efficaci**.
- **Quando si può**: **selezione delle feature, riduzione della dimensionalità, controllo della complessità del modello**.

$x\in\mathbb{R}^d$ ($d=1000$) → **selezione delle feature o PCA** → $z\in\mathbb{R}^{d'}$ ($d'=20$) → modello → $\hat{y}$ (classe).

<!-- SOURCE_VISUAL id="IS20-V08" source="L20-web.pdf" page="11" type="schema" description="Riduzione da 1000 a 20 feature prima del modello" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Richiamo dalla Lezione 18: filtri, wrapper, selezione embedded e PCA **riducono $d$ prima dell’apprendimento** ($d'\ll d$). **Molti classificatori possono essere o non essere significativamente colpiti dalla maledizione della dimensionalità: conoscere il proprio classificatore!**

> 💡 **La dimensionalità effettiva (nota didattica).** Le immagini $28\times 28$ di cifre scritte a mano vivono in uno spazio a 784 dimensioni, ma una configurazione casuale di pixel non sembra quasi mai una cifra: le cifre vere occupano una «superficie» di poche decine di dimensioni (spessore del tratto, inclinazione, forma). PCA e t-SNE funzionano proprio perché trovano questa struttura (Lezione 18, §3 e §5): è il motivo della PCA(50) prima delle mappe di MNIST.

#### **1.9. La maledizione della dimensionalità: il fitting polinomiale**

**Polinomio di grado $M=3$ in $d$ variabili**:

$$y(x,w)=w_0+\sum_{i=1}^{d}w_ix_i+\sum_{i=1}^{d}\sum_{j=1}^{d}w_{ij}x_ix_j+\sum_{i=1}^{d}\sum_{j=1}^{d}\sum_{k=1}^{d}w_{ijk}x_ix_jx_k,$$

con $x=(x_1,\dots,x_d)\in\mathbb{R}^d$ l’input; $y$ l’uscita (1 valore); $w$ i coefficienti stimati dai dati.

- **Il numero di coefficienti indipendenti** (gradi di libertà) **cresce come $d^3$** ($x_ix_j=x_jx_i$ si conta una volta).
- **Grado $M$**: cresce come $d^M$. **Esplode quando $d$ o $M$ aumentano.**
- **Ogni coefficiente ha bisogno di dati per essere stimato.**

**Coefficienti per $M=3$**, $\binom{d+3}{3}$:

| $d$ | 1 | 10 | 100 | 1000 |
|---|---:|---:|---:|---:|
| coefficienti | 4 | 286 | 176 851 | $\approx 1{,}7\times 10^8$ |

Il codice (Python, MATLAB) è nell’appendice (§8.1).

> 💡 **Da dove viene $\binom{d+M}{M}$ (nota didattica).** Un monomio di grado al più $M$ in $d$ variabili si identifica con gli esponenti $(a_0,a_1,\dots,a_d)$ non negativi a somma $M$ (dove $a_0$ è il «grado mancante»): il numero di questi vettori è $\binom{d+M}{M}$. Per $d=10$: $\binom{13}{3}=\frac{13\cdot 12\cdot 11}{6}=286$. Con 286 coefficienti servono molto più di 286 campioni (Lezione 7: #DoF ≪ #dati); con 176 851 coefficienti il modello polinomiale è inutilizzabile con dataset normali.

#### **1.10. Dalla Lezione 19 alla Lezione 20: che cosa si impara?**

Richiamo dalla Lezione 19: un **encoder neurale pre-addestrato** produceva **embedding fissi**; il k-NN li classificava con una distanza.

| **L19: rappresentazione fissa** | **L20: addestrare la rappresentazione neurale** |
|---|---|
| immagine → encoder pre-addestrato → $z\in\mathbb{R}^{768}$ | campioni di training $(x,y)$ → rete |
| $z$ → k-NN (distanza, voto) → $\hat{y}$ | errore di previsione → gradienti → **cambiare i pesi** |
| nessun peso dell’encoder è stato aggiornato per il compito di classificazione | si studia **come imparano i neuroni**, non solo come si usa una rappresentazione pre-addestrata |

> ⚠️ **La dimensionalità conta ancora per le reti neurali.** Uno **strato denso** con $d$ feature d’ingresso e $h$ neuroni ha $dh+h$ **parametri addestrabili** (pesi e bias). Per $d=1000$, $h=100$: **100 100 parametri in un solo strato**. **Il deep learning non elimina automaticamente la maledizione**; contano architettura e dati (le CNN nella Lezione 21).

> 💡 **Il conto (nota didattica).** $1000\cdot 100=100\,000$ pesi più 100 bias. Con un’immagine Full HD in scala di grigi come input ($d=2\,073\,600$) e solo 100 neuroni lo strato avrebbe oltre 207 milioni di parametri: è il motivo per cui le immagini si trattano con le convoluzioni, che condividono i pesi (Lezione 21).

---

### **2. Panoramica: introduzione alle reti neurali**


Come funziona il cervello con le reti neurali.

#### **2.1. Neuroni e sinapsi**

- Una **rete neurale** è un **modello di ragionamento basato sul cervello umano**, che consiste in un insieme **densamente interconnesso** di cellule nervose, le unità di base di elaborazione dell’informazione chiamate **neuroni**.
- Una misura molto citata stima **86,1 miliardi di neuroni** (deviazione standard 8,1 miliardi, $n=4$ cervelli maschili), e il numero di **sinapsi** si discute di solito all’ordine di grandezza approssimativo di **$10^{14}$**; **le stime sono incerte**.
- **Un neurone artificiale è una semplificazione, non una simulazione biologica.** Il suo valore sta nell’**imparare funzioni utili ingresso–uscita**.

Neuroni: Azevedo et al., *J. Comp. Neurol.* 513 (2009), doi:10.1002/cne.21974; $n=4$. Sinapsi: stima dell’ordine di grandezza.

<!-- SOURCE_VISUAL id="IS20-V09" source="L20-web.pdf" page="15" type="figura" description="Neuroni biologici e sinapsi" reason="Immagine visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Le stime (nota didattica).** L’edizione precedente riportava cifre più basse riprese da testi classici; l’edizione 2026/27 usa la misura di Azevedo et al. con la sua incertezza. La Lezione 4 (§3.1) parlava di circa 100 000 miliardi di «parametri», coerente con $10^{14}$ sinapsi. Un campione di 4 cervelli è piccolo: la deviazione standard di 8,1 miliardi va letta come un ordine di variabilità, non come un intervallo di confidenza preciso.

#### **2.2. Neuroni e sinapsi: scala e potenza**

**Circa 86 miliardi di neuroni; sinapsi $\sim 10^{14}$** (stima approssimativa). **Consumo di potenza del cervello: circa 20 W.** È un confronto di scala **solo concettuale**: non carichi di lavoro né metriche di prestazione uguali.

<!-- SOURCE_VISUAL id="IS20-V10" source="L20-web.pdf" page="16" type="figura" description="Confronto concettuale di scala e potenza tra cervello e calcolatore" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Venti watt (nota didattica).** È la potenza di una lampadina a basso consumo, contro le centinaia di watt di una singola GPU da addestramento (Lezione 23) e i megawatt di un centro di calcolo. Il confronto è «solo concettuale» perché cervello e GPU non fanno lo stesso lavoro: ma dice che l’efficienza energetica del calcolo neurale artificiale è ancora lontanissima da quella biologica.

#### **2.3. Le connessioni**

- **Ogni neurone ha una struttura molto semplice**, ma **l’insieme di tali elementi costituisce una potenza di elaborazione enorme**.
- Un neurone consiste in un **corpo cellulare (soma)**, un certo numero di **fibre (dendriti)** e una **singola fibra lunga (assone)**.

<!-- SOURCE_VISUAL id="IS20-V11" source="L20-web.pdf" page="17" type="figura" description="Struttura del neurone: soma, dendriti, assone" reason="Immagine visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.4. Analogia tra neuroni biologici e artificiali**

| Biologico | Artificiale |
|---|---|
| soma | neurone |
| dendrite | ingresso $x_i$ |
| assone | uscita $\hat{y}$ |
| sinapsi | peso $w_i$ |

<!-- SOURCE_VISUAL id="IS20-V12" source="L20-web.pdf" page="18" type="schema" description="Neurone biologico accanto al neurone artificiale con cinque ingressi, pesi, somma e soglia" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **La forza di una sinapsi diventa un numero, il peso: imparare significa cambiare i pesi.**

#### **2.5. La rete neurale artificiale**

> 📌 **Definizione.** Una **rete neurale artificiale (ANN)** è un modello fatto di **unità di elaborazione semplici**, i **neuroni**, collegate da **collegamenti pesati**; **i pesi si imparano dagli esempi**.

**Input**: $x\in\mathbb{R}^5$ (5 feature) → segnali d’ingresso → **strato nascosto 1** → **strato nascosto 2** → **strato d’uscita** → **output** $\hat{y}$ (1 valore). **Ogni unità di uno strato è collegata a ogni unità dello strato successivo.**

<!-- SOURCE_VISUAL id="IS20-V13" source="L20-web.pdf" page="19" type="schema" description="Rete con 5 ingressi, due strati nascosti e un'uscita, completamente connessa" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.6. Caratteristiche delle reti neurali artificiali**

- **Imparare dall’esperienza**: problemi complessi e difficili da risolvere, ma con **molti dati** che descrivono il problema.
- **Generalizzare dagli esempi**: possono **interpolare** dall’apprendimento precedente e dare la risposta corretta a **dati mai visti**.
- **Sviluppo rapido delle applicazioni**: le reti neurali sono **macchine generiche**, abbastanza **indipendenti dalla conoscenza del dominio**.
- **Adattabilità**: si adattano a un ambiente che cambia, **se progettate correttamente**.
- **Efficienza computazionale**: l’**addestramento** richiede molta potenza di calcolo; una rete addestrata non richiede **quasi nulla** in modalità di richiamo (*recall*, cioè l’uso).
- **Non linearità**: non si basano su ipotesi lineari sul mondo reale.

> 💡 **«Generalizzare» con giudizio (nota didattica).** L’interpolazione funziona **dentro** la regione coperta dagli esempi di training; fuori, la rete **estrapola** senza garanzie (Lezione 6, il buco topologico; Lezione 17, §7.5, la rete da 100 neuroni oltre $x=3$). E l’«indipendenza dal dominio» non vuol dire che la conoscenza del problema sia inutile: è il quarto driver della Lezione 2. L’efficienza «quasi nulla in recall» è l’ammortizzazione del System 1 (Lezione 18, §8.5).

---

### **3. Teoria: le reti neurali artificiali**


Reti feed-forward, addestramento, deep learning.

#### **3.1. Applicazioni tipiche**

- L’ispirazione originale era il **cervello umano**; l’enfasi oggi è sull’**utilità come strumento di calcolo**.
- Esistono molti paradigmi utili di reti neurali; questa lezione si limita alla **rete feed-forward**, la più popolare.

<!-- SOURCE_VISUAL id="IS20-V14" source="L20-web.pdf" page="22" type="figura" description="Applicazioni tipiche delle reti neurali" reason="Immagine visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. Una rete feed-forward è una funzione**

**Esempio**: temperatura 72 °C ($x_1$), vibrazione 4,1 mm/s ($x_2$), corrente 12,5 A ($x_3$), pressione 6,2 bar ($x_4$), velocità 1450 rpm ($x_5$) → rete → $\hat{y}=0{,}87$, **probabilità di guasto della pompa entro 7 giorni**.

<!-- SOURCE_VISUAL id="IS20-V15" source="L20-web.pdf" page="23" type="schema" description="Rete feed-forward con cinque misure di una pompa e la probabilità di guasto in uscita" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Rete feed-forward.** Una **struttura a strati** di elementi di elaborazione **omogenei, semplici ma non lineari**; **i segnali scorrono solo in avanti**, dagli ingressi alle uscite; **tutta l’elaborazione è locale a ciascun elemento**.

**Stessa struttura, altri usi**: **classificazione** ($\hat{y}$ = probabilità di classe) o **regressione** ($\hat{y}$ = un valore, per esempio le ore di vita residua). I valori sono illustrativi.

> ⚠️ **Le unità diverse (nota didattica).** 72, 4,1, 12,5, 6,2 e 1450: la velocità è due ordini di grandezza più grande delle altre misure. Senza normalizzazione (Lezione 10) il peso corrispondente dovrebbe essere minuscolo e l’addestramento sarebbe lento e instabile, per la stessa ragione per cui il k-NN della Lezione 19 vedeva solo la glicemia.

#### **3.3. Le reti a funzione di base radiale (RBF)**

> 📌 **Definizione.** Una **rete RBF** ha **unità nascoste che rispondono alla distanza da un prototipo**. La sua uscita è una **somma pesata di queste risposte**.

$$g_i(x)=\exp\left(-\frac{\|x-\mu_i\|^2}{2\sigma_i^2}\right),$$

con $\mu_i$ il **prototipo** del neurone $i$; $\sigma_i$ la **larghezza**; $g_i\in(0,1]$ la **risposta**.

$$\hat{y}_j(x)=\sum_{i=1}^{k}w_{ij}\,g_i(x)=w_j^\top g(x),\qquad j=1,\dots,c,$$

con $\hat{y}_j$ il **punteggio della categoria $j$**; $x=(x_1,\dots,x_d)^\top$ l’input; $g(x)=(g_1(x),\dots,g_k(x))^\top$ le risposte dei $k$ neuroni RBF; $w_j=(w_{1j},\dots,w_{kj})^\top$ i pesi dai neuroni alla categoria $j$.

<!-- SOURCE_VISUAL id="IS20-V16" source="L20-web.pdf" page="24" type="schema" description="Rete RBF con unità nascoste gaussiane centrate sui prototipi" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Richiamo dalla Lezione 19: una SVM con kernel RBF non è una rete neurale RBF.** Usano la stessa gaussiana, ma la SVM sceglie i «prototipi» tra i vettori di supporto e ottimizza il margine, mentre la rete RBF sceglie i prototipi (spesso con il k-means) e addestra i pesi d’uscita.

> 💡 **Il legame con k-NN e k-means (nota didattica).** Un neurone RBF vale 1 quando $x$ coincide con il prototipo e decresce con la distanza: è un «vicino morbido». Con prototipi presi dai centroidi del k-means (Lezione 19, §7) e pesi d’uscita stimati con i minimi quadrati (Lezione 17), la rete RBF unisce le due idee della lezione precedente. La larghezza $\sigma$ ha il ruolo di $k$ nel k-NN: piccola, frontiera frastagliata; grande, frontiera liscia.

#### **3.4. L’addestramento di una rete neurale**

Durante l’addestramento la rete è **costretta ad aggiustare i suoi parametri (i pesi)** in modo che la sua **risposta ai dati d’ingresso** diventi **più vicina alla risposta desiderata**.

input $x$ → rete neurale (pesi $w$) → $\hat{y}$; uscita desiderata $y$; **errore** $e=y-\hat{y}$ → **aggiornamento dei pesi** $\Delta w$.

<!-- SOURCE_VISUAL id="IS20-V17" source="L20-web.pdf" page="25" type="schema" description="Ciclo di addestramento: rete, uscita, confronto con l'uscita desiderata, errore e aggiornamento dei pesi" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.5. Addestramento e inferenza**

- **Addestramento**: molti campioni etichettati, **passo in avanti**, errore, **passo all’indietro** (i pesi cambiano).
- **Inferenza**: campioni nuovi, **solo passo in avanti** (pesi fissi).

<!-- SOURCE_VISUAL id="IS20-V18" source="L20-web.pdf" page="26" type="schema" description="Addestramento con passo avanti e indietro contro inferenza con solo passo avanti" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.6. Le reti di deep learning**

- **Rete semplice**: 2 strati nascosti. **Rete profonda**: 5 strati nascosti.

<!-- SOURCE_VISUAL id="IS20-V19" source="L20-web.pdf" page="27" type="schema" description="Una rete con 2 strati nascosti e una rete profonda con 5 strati nascosti" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Definizione.** Una **rete neurale profonda** è una rete neurale con **molti strati nascosti**; il **deep learning** addestra tali reti in modo che **imparino anche le feature dai dati grezzi**.

**Non esiste un’unica convenzione per «profondo».** Regole pratiche:

- **Strati nascosti**: 1–2 strati nascosti: **superficiale** (*shallow*); **3 o più: profonda** (alcuni autori già da 2).
- **Profondità** = **numero di strati con pesi che il segnale attraversa** dall’ingresso all’uscita = **strati nascosti + 1** (anche lo strato d’uscita ha pesi). Qui: rete di sinistra profondità 3, rete di destra profondità 6. In letteratura (Schmidhuber, 2015) una profondità **> 10** si chiama **molto profonda**.
- **Neuroni**: nemmeno qui c’è una soglia; le reti profonde hanno tipicamente **almeno migliaia di neuroni** (milioni di pesi), quelle moderne molti di più.

#### **3.7. Machine learning e deep learning: un primo sguardo**

Nella pipeline classica illustrata, **le feature sono progettate separatamente**; il deep learning **può imparare feature e classificatore insieme**. Le curve delle prestazioni sono **illustrative, non una classifica universale**. Contano i dati e la scelta del modello.

<!-- SOURCE_VISUAL id="IS20-V20" source="L20-web.pdf" page="28" type="grafico" description="Pipeline classica con feature progettate contro deep learning e curve delle prestazioni al crescere dei dati" reason="Schema e grafico visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Più avanti, Lezione 21**: lì si presentano le **reti neurali convoluzionali (CNN)**; qui si costruisce l’**unità di base, il neurone**.

> 💡 **Il grafico «dati contro prestazioni» (nota didattica).** Il confronto classico mostra che con **pochi dati** i metodi classici sono competitivi o migliori, mentre con **moltissimi dati** le prestazioni del deep learning continuano a salire quando quelle dei metodi classici si appiattiscono. È coerente con la maledizione della dimensionalità e con il conteggio dei parametri (Lezione 7): più parametri richiedono più dati. Ed è il motivo per cui la Lezione 19 raccomanda i metodi classici quando i dati sono pochi.

---

### **4. Teoria: il neurone come semplice elemento di calcolo**


Ingressi, pesi, soglia, funzione di attivazione.

#### **4.1. Il neurone come elemento di calcolo**

> 📌 **Definizione.** Un **neurone artificiale** calcola la **somma pesata dei suoi ingressi**, **sottrae una soglia** e passa il risultato attraverso una **funzione di attivazione**.

**Ingressi**: $d=5$ segnali $x_1,\dots,x_5$. **Parametri**: 5 pesi $w_1,\dots,w_5$ e la soglia $\theta$. **Uscita**: 1 segnale $\hat{y}$, inviato a **tutti i neuroni dello strato successivo**.

<!-- SOURCE_VISUAL id="IS20-V21" source="L20-web.pdf" page="30" type="schema" description="Neurone con cinque ingressi pesati, somma, soglia e uscita" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.2. Il modello di base del neurone**

Il neurone confronta la somma pesata $X$ con la soglia $\theta$: **sotto la soglia l’uscita è $-1$**, altrimenti il neurone **si attiva** e l’uscita è **$+1$** (funzione di attivazione **segno**).

$$X=\sum_{i=1}^{d}w_ix_i,\qquad \hat{y}=\begin{cases}+1&\text{se }X\ge\theta\\-1&\text{se }X<\theta\end{cases}$$

con $x_i$ l’ingresso $i$ ($d$ ingressi); $w_i$ il peso dell’ingresso $i$; $X$ l’**ingresso netto**; $\theta$ la soglia; $\hat{y}$ l’uscita ($\pm 1$).

**Esempio**: $w=(0{,}4;\ -0{,}2;\ 0{,}1;\ 0{,}3;\ 0{,}5)$, $\theta=0{,}5$.

| | $x=(1,0,1,1,0)$ | $x=(0,1,1,0,0)$ |
|---|---|---|
| $X$ | $0{,}4+0{,}1+0{,}3=0{,}8$ | $-0{,}2+0{,}1=-0{,}1$ |
| confronto | $0{,}8\ge 0{,}5$ | $-0{,}1<0{,}5$ |
| uscita | $\hat{y}=+1$ | $\hat{y}=-1$ |

> 💡 **La soglia come bias (nota didattica).** $X\ge\theta$ equivale a $X-\theta\ge 0$, cioè $\sum_iw_ix_i+b\ge 0$ con $b=-\theta$: è la $z=w^\top x+b$ del classificatore lineare della Lezione 17 (§6.3), con la funzione segno al posto di $\text{sgn}$. Lo stesso trucco della colonna di uni: la soglia è un peso su un ingresso fisso a $-1$ (§8.2).

#### **4.3. Le funzioni di attivazione**

$z=X-\theta$ è l’ingresso della funzione di attivazione.

| Funzione | Definizione | Uscita | Nota della slide |
|---|---|---|---|
| **gradino** (*step*) | 1 se $z\ge 0$, altrimenti 0 | $\{0,1\}$ | non lineare, **non derivabile in 0** |
| **segno** (*sign*) | $+1$ se $z\ge 0$, altrimenti $-1$ | $\{-1,+1\}$ | uscita $\pm 1$ |
| **lineare** | $z$ | $\mathbb{R}$ | **nessuna limitazione** |
| **lineare saturata** | $z$ limitata a $[-1,1]$ (o $[0,1]$) | intervallo limitato | **intervallo d’ingresso limitato** |
| **sigmoide** | $\sigma(z)=\dfrac{1}{1+e^{-z}}$ | $(0,1)$ | **liscia** |
| **tangente iperbolica** | $\tanh(z)$ | $(-1,1)$ | **liscia** |

<!-- SOURCE_VISUAL id="IS20-V22" source="L20-web.pdf" page="32" type="grafico" description="Grafici delle sei funzioni di attivazione: gradino, segno, lineare, lineare saturata, sigmoide e tangente iperbolica" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Da ricordare**: $\sigma'(z)=\sigma(z)\,[1-\sigma(z)]$. In $z=0$: $\sigma=0{,}5$, $\sigma'=0{,}25$.

> 💡 **La derivata della sigmoide (nota didattica).** $\sigma(z)=(1+e^{-z})^{-1}$, quindi $\sigma'(z)=\dfrac{e^{-z}}{(1+e^{-z})^2}=\dfrac{1}{1+e^{-z}}\cdot\dfrac{e^{-z}}{1+e^{-z}}=\sigma(z)\,[1-\sigma(z)]$. Il prodotto $\sigma(1-\sigma)$ è massimo quando $\sigma=0{,}5$, e vale $0{,}25$: è il numero che spiega il gradiente che svanisce (§6.11). La tanh è una sigmoide riscalata, $\tanh(z)=2\sigma(2z)-1$, con derivata massima 1 in $z=0$. La ReLU, $\max(0,z)$, usata nelle reti moderne (Lezione 21), ha derivata 1 per $z>0$.

---

### **5. Teoria: il perceptron**


Un singolo neurone che impara.

#### **5.1. Un singolo neurone può imparare un compito?**

> 📌 **Definizione.** Il **perceptron** è un **singolo neurone con pesi regolabili** e un’attivazione **a limitatore rigido** (*hard limiter*, gradino); **impara a separare due classi da esempi etichettati**.

- **Rosenblatt, 1958**: la **prima procedura** per addestrare una semplice ANN.
- **Ingressi**: $d$ feature; **uscita**: $\hat{y}\in\{0,1\}$ (classe $A_2$ o $A_1$).
- **Gradino (0/1) invece di segno ($\pm 1$)**: **cambiano solo le etichette delle classi**.

<!-- SOURCE_VISUAL id="IS20-V23" source="L20-web.pdf" page="34" type="schema" description="Perceptron a due ingressi con pesi, somma, soglia e gradino" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2. L’obiettivo del perceptron: la classificazione binaria**

Il perceptron divide lo spazio a $d$ dimensioni con un **iperplano** in **due regioni di decisione**, $A_1$ ($\hat{y}=1$) e $A_2$ ($\hat{y}=0$):

$$\sum_{i=1}^{d}w_ix_i-\theta=0.$$

Con $d=2$ è una **retta**, $w_1x_1+w_2x_2-\theta=0$, cioè

$$x_2=mx_1+q,\qquad m=-\frac{w_1}{w_2},\qquad q=\frac{\theta}{w_2}.$$

**Esempio**: $w=(0{,}3;\ -0{,}1)$, $\theta=0{,}2$: $m=-0{,}3/(-0{,}1)=3$, $q=0{,}2/(-0{,}1)=-2$. **Retta: $x_2=3x_1-2$.** Per $x=(1,0)$: $0{,}3-0-0{,}2=0{,}1\ge 0\Rightarrow A_1$.

<!-- SOURCE_VISUAL id="IS20-V24" source="L20-web.pdf" page="35" type="grafico" description="Retta x2 = 3x1 - 2 che separa le regioni A1 e A2" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Da dove viene la retta (nota didattica).** Da $w_1x_1+w_2x_2=\theta$ si isola $x_2$: $x_2=\frac{\theta-w_1x_1}{w_2}=-\frac{w_1}{w_2}x_1+\frac{\theta}{w_2}$. Il vettore dei pesi $w$ è **perpendicolare** alla retta e punta verso la regione $A_1$: aumentare un peso ruota la retta, cambiare la soglia la trasla. Se $w_2=0$ la retta è verticale ($x_1=\theta/w_1$) e la forma $x_2=mx_1+q$ non vale.

#### **5.3. La separabilità lineare nel perceptron**

- **2 ingressi**: una **retta**, $w_1x_1+w_2x_2-\theta=0$.
- **3 ingressi**: un **piano**, $w_1x_1+w_2x_2+w_3x_3-\theta=0$.

<!-- SOURCE_VISUAL id="IS20-V25" source="L20-web.pdf" page="36" type="grafico" description="Frontiera del perceptron come retta con 2 ingressi e come piano con 3 ingressi" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Un perceptron risolve solo problemi linearmente separabili.**

#### **5.4. Come impara il perceptron?**

**Imparare significa fare piccoli aggiustamenti dei pesi** per ridurre la differenza tra l’uscita **effettiva** e quella **desiderata**. I pesi iniziali sono **casuali**, di solito in $[-0{,}5;\ 0{,}5]$.

All’iterazione $p$ il perceptron riceve **un campione di training** con uscita desiderata $y(p)$ e produce $\hat{y}(p)$. L’errore è

$$e(p)=y(p)-\hat{y}(p),\qquad p=1,2,3,\dots$$

- $e(p)>0$: l’uscita è **troppo bassa**, aumentare $\hat{y}(p)$;
- $e(p)<0$: l’uscita è **troppo alta**, diminuire $\hat{y}(p)$;
- $e(p)=0$: corretta, **nessun cambiamento**.

> ⚠️ **Iterazione ed epoca.** **Un’epoca = l’intero training set presentato una volta.** $p$ conta i **campioni**: **$p$ non è un’epoca**. Con 4 campioni, un’epoca sono 4 iterazioni.

#### **5.5. La regola di apprendimento del perceptron**

$$\underbrace{w_i(p+1)}_{\text{iterazione successiva}}=\underbrace{w_i(p)}_{\text{corrente}}+\underbrace{\alpha\cdot x_i(p)\cdot e(p)}_{\Delta w_i(p)}$$

con $w_i(p)$ il peso dell’ingresso $i$ all’iterazione $p$; $x_i(p)$ l’ingresso $i$; $e(p)=y(p)-\hat{y}(p)$ l’errore; $\alpha$ il **learning rate** (una costante positiva minore di 1, per esempio $\alpha=0{,}1$). Con $z=\sum_iw_ix_i-\theta$, la regola generale impara anche la soglia: $\Delta\theta=-\alpha e$ (equivalentemente $\Delta b=\alpha e$ per $b=-\theta$).

**Esempio**: $\alpha=0{,}1$, $x=(1,0)$, $e=-1$: $\Delta w_1=0{,}1\cdot 1\cdot(-1)=-0{,}1$; $\Delta w_2=0{,}1\cdot 0\cdot(-1)=0$; $w=(0{,}3;\ -0{,}1)\to(0{,}2;\ -0{,}1)$.

- **Cambiano solo i pesi degli ingressi attivi** ($x_i\ne 0$).
- **Il segno di $e(p)$ decide la direzione** del cambiamento.
- Proposta da **Rosenblatt (1960)**: dà l’**algoritmo di addestramento del perceptron**.

> 💡 **Perché la regola ha senso (nota didattica).** Se l’uscita doveva essere 1 ma è 0 ($e=+1$), ogni peso con ingresso positivo **cresce** di $\alpha x_i$: la prossima volta la somma pesata sarà più grande e il neurone più vicino ad attivarsi. Se un ingresso vale 0, il suo peso **non cambia**: quell’ingresso non ha contribuito all’errore. Il segno di $\Delta\theta=-\alpha e$ è opposto: se l’uscita è troppo bassa, conviene **abbassare** la soglia. La slide data la proposta al 1958 (procedura) e al 1960 (regola): sono due pubblicazioni di Rosenblatt.

#### **5.6. L’algoritmo di addestramento del perceptron in 4 passi**

Training set $\{(x,y)\}$, $\alpha$ → **1. inizializzare** ($w_i,\theta\in[-0{,}5;\ 0{,}5]$) → **2. attivare** (calcolare $\hat{y}(p)$) → **3. addestrare i pesi** ($\Delta w_i=\alpha x_ie$, $\Delta\theta=-\alpha e$) → **4. iterare** ($p\leftarrow p+1$, campione successivo, finché un’epoca non ha errori) → $w,\theta$ addestrati.

<!-- SOURCE_VISUAL id="IS20-V26" source="L20-web.pdf" page="39" type="schema" description="I quattro passi dell'algoritmo del perceptron in sequenza" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Passo 1: inizializzazione.** Si impostano i pesi iniziali $w_1,\dots,w_d$ e la soglia $\theta$ a numeri casuali nell’intervallo $[-0{,}5;\ 0{,}5]$.

**Passo 2: attivazione.** Alla prima iterazione ($p=1$) si applicano gli ingressi $x_1(p),\dots,x_d(p)$ e l’uscita desiderata $y(p)$, e si calcola l’uscita effettiva

$$\hat{y}(p)=\text{step}\left[\sum_{i=1}^{d}x_i(p)\,w_i(p)-\theta\right],$$

con $d$ il numero di ingressi del perceptron e step l’attivazione a gradino (1 se l’argomento è $\ge 0$, altrimenti 0).

**Passo 3: addestramento dei pesi (la regola delta).** Si aggiornano i pesi del perceptron:

$$w_i(p+1)=w_i(p)+\Delta w_i(p),\qquad \Delta w_i(p)=\alpha\cdot x_i(p)\cdot e(p).$$

$\Delta w_i(p)$ è la **correzione del peso**. Se la soglia è addestrabile, $\theta(p+1)=\theta(p)-\alpha e(p)$.

> 📌 **Molto importante!** Questo passaggio è **la base dei metodi di apprendimento nelle reti neurali**: la **backpropagation usa la stessa forma, con un «errore» diverso** (§6.6).

**Passo 4: iterazione.** Si incrementa $p$ di uno, si torna al passo 2 e si ripete **fino alla convergenza**: **un’epoca intera con $e(p)=0$ per ogni campione**.

> 💡 **Il teorema di convergenza (nota didattica).** Se le due classi sono linearmente separabili, il perceptron trova una retta che le separa in un numero **finito** di aggiornamenti (Novikoff, 1962), qualunque sia il punto di partenza. Se non lo sono (lo XOR, §5.8), le correzioni non finiscono mai e la retta oscilla: per questo serve anche un numero massimo di epoche.

#### **5.7. Esempio di apprendimento del perceptron: l’AND**

**Compito**: imparare l’**AND logico**.

| $x_1$ | $x_2$ | $y$ |
|---:|---:|---:|
| 0 | 0 | 0 |
| 0 | 1 | 0 |
| 1 | 0 | 0 |
| 1 | 1 | 1 |

**Impostazione**: **soglia fissa $\theta=0{,}2$**, $\alpha=0{,}1$, pesi iniziali $w=(0{,}3;\ -0{,}1)$, attivazione a gradino. **In questo esempio svolto si aggiornano solo i pesi.**

**Epoca 1, campione 1**: $x=(0,0)$, $y=0$. $X-\theta=0{,}3\cdot 0+(-0{,}1)\cdot 0-0{,}2=-0{,}2<0$; $\hat{y}=0$, $e=0-0=0$; $w$ invariato: $(0{,}3;\ -0{,}1)$.

**Epoca 1, campione 2**: $x=(0,1)$, $y=0$. $X-\theta=0-0{,}1-0{,}2=-0{,}3<0$; $\hat{y}=0$, $e=0$; $w$ invariato: $(0{,}3;\ -0{,}1)$.

**Epoca 1, campione 3**: $x=(1,0)$, $y=0$. $X-\theta=0{,}3-0-0{,}2=0{,}1\ge 0$; $\hat{y}=1$, $e=0-1=-1$. $\Delta w_1=0{,}1\cdot 1\cdot(-1)=-0{,}1$; $\Delta w_2=0{,}1\cdot 0\cdot(-1)=0$ (ingresso spento). $w=(0{,}2;\ -0{,}1)$.

**Epoca 1, campione 4**: $x=(1,1)$, $y=1$. $X-\theta=0{,}2-0{,}1-0{,}2=-0{,}1<0$; $\hat{y}=0$, $e=1-0=+1$. $\Delta w_1=0{,}1\cdot 1\cdot 1=+0{,}1$; $\Delta w_2=0{,}1\cdot 1\cdot 1=+0{,}1$. $w=(0{,}3;\ 0{,}0)$.

**Fine dell’epoca 1**: **due errori, due correzioni**. Gli stessi quattro campioni vengono presentati di nuovo nelle epoche 2, 3, … finché un’epoca non termina **senza errori**.

**Tabella completa** (soglia fissa $\theta=0{,}2$, $\alpha=0{,}1$, pesi iniziali $(0{,}3;\ -0{,}1)$; campioni sempre nell’ordine (0,0), (0,1), (1,0), (1,1); si addestrano solo i pesi):

| Epoca | $x_1$ | $x_2$ | $y$ | $\hat{y}$ | $e$ | $w_1$ prima | $w_2$ prima | $w_1$ dopo | $w_2$ dopo |
|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| 1 | 0 | 0 | 0 | 0 | 0 | 0,3 | −0,1 | 0,3 | −0,1 |
| | 0 | 1 | 0 | 0 | 0 | 0,3 | −0,1 | 0,3 | −0,1 |
| | 1 | 0 | 0 | 1 | −1 | 0,3 | −0,1 | **0,2** | −0,1 |
| | 1 | 1 | 1 | 0 | +1 | 0,2 | −0,1 | **0,3** | **0,0** |
| 2 | 0 | 0 | 0 | 0 | 0 | 0,3 | 0,0 | 0,3 | 0,0 |
| | 0 | 1 | 0 | 0 | 0 | 0,3 | 0,0 | 0,3 | 0,0 |
| | 1 | 0 | 0 | 1 | −1 | 0,3 | 0,0 | **0,2** | 0,0 |
| | 1 | 1 | 1 | 1 | 0 | 0,2 | 0,0 | 0,2 | 0,0 |
| 3 | 0 | 0 | 0 | 0 | 0 | 0,2 | 0,0 | 0,2 | 0,0 |
| | 0 | 1 | 0 | 0 | 0 | 0,2 | 0,0 | 0,2 | 0,0 |
| | 1 | 0 | 0 | 1 | −1 | 0,2 | 0,0 | **0,1** | 0,0 |
| | 1 | 1 | 1 | 0 | +1 | 0,1 | 0,0 | **0,2** | **0,1** |
| 4 | 0 | 0 | 0 | 0 | 0 | 0,2 | 0,1 | 0,2 | 0,1 |
| | 0 | 1 | 0 | 0 | 0 | 0,2 | 0,1 | 0,2 | 0,1 |
| | 1 | 0 | 0 | 1 | −1 | 0,2 | 0,1 | **0,1** | 0,1 |
| | 1 | 1 | 1 | 1 | 0 | 0,1 | 0,1 | 0,1 | 0,1 |
| 5 | 0 | 0 | 0 | 0 | 0 | 0,1 | 0,1 | 0,1 | 0,1 |
| | 0 | 1 | 0 | 0 | 0 | 0,1 | 0,1 | 0,1 | 0,1 |
| | 1 | 0 | 0 | 0 | 0 | 0,1 | 0,1 | 0,1 | 0,1 |
| | 1 | 1 | 1 | 1 | 0 | 0,1 | 0,1 | 0,1 | 0,1 |

$y$: uscita desiderata; $\hat{y}$: uscita effettiva; $e=y-\hat{y}$. In grassetto: i pesi cambiati dalla regola delta $\Delta w_i=\alpha x_ie$. **I pesi dopo un campione sono i pesi prima del successivo**: l’epoca 1 termina con $w=(0{,}3;\ 0{,}0)$, l’epoca 2 con $w=(0{,}2;\ 0{,}0)$.

**Errori per epoca: 2, 1, 2, 1, 0.** Nell’epoca 5 ogni errore è zero: **niente più aggiornamenti**; il perceptron **è converso** su questi quattro esempi.

> 💡 **I casi limite con «≥» (nota didattica).** Nell’epoca 2, campione (1,0): $X-\theta=0{,}3-0{,}2=0{,}1\ge 0$, quindi $\hat{y}=1$ e l’errore corregge $w_1$ a 0,2. Nell’epoca 3, campione (1,0): $X-\theta=0{,}2-0{,}2=0$, e poiché il gradino usa «$\ge$» l’uscita è ancora 1: un’altra correzione. Nell’epoca 4, campione (1,1): $0{,}2+0{,}1-0{,}2=0{,}1\ge 0$, corretto. Il «$\ge$» invece di «$>$» decide l’esito proprio sui punti che cadono sulla retta.

#### **5.8. L’AND: come si muove la retta**

| | partenza | dopo l’epoca 1 | dopo l’epoca 2 | dopo l’epoca 3 | dopo le epoche 4 e 5 |
|---|---|---|---|---|---|
| $w$ | $(0{,}3;\ -0{,}1)$ | $(0{,}3;\ 0{,}0)$ | $(0{,}2;\ 0{,}0)$ | $(0{,}2;\ 0{,}1)$ | $(0{,}1;\ 0{,}1)$ |
| errori | | 2 | 1 | 2 | 1, 0 |

<!-- SOURCE_VISUAL id="IS20-V27" source="L20-web.pdf" page="45" type="grafico" description="La retta di decisione del perceptron sull'AND dopo ogni epoca con le regioni colorate" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**L’epoca 5 non ha errori: niente più aggiornamenti.** **Retta finale**: $0{,}1x_1+0{,}1x_2=0{,}2$, cioè $x_1+x_2=2$. **Il punto (1,1) sta sulla retta**: $X-\theta=0$, e il gradino dà $\hat{y}=1$ («$\ge$» nel gradino). Regione rosa: $\hat{y}=1$; regione blu: $\hat{y}=0$. **Ogni errore sposta la retta.** Epoca 5: 0 errori ⇒ stop.

> ⚠️ **Una soluzione fragile (nota didattica).** La retta finale passa esattamente per (1,1): l’AND è classificato bene solo grazie al «$\ge$», e un minimo rumore sull’ingresso (1,1) farebbe sbagliare. Il perceptron si ferma alla **prima** retta che separa i dati di training, non alla migliore: non massimizza il margine come una SVM (Lezione 19, §2.2). Con un $\theta$ addestrabile o un ordine diverso dei campioni la retta finale sarebbe un’altra.

#### **5.9. Grafici bidimensionali delle operazioni logiche di base**

| | AND | OR | XOR |
|---|---|---|---|
| separabilità | **una retta basta** | **una retta basta** | **nessuna retta** |
| esito | apprendibile | apprendibile | **non apprendibile** |

<!-- SOURCE_VISUAL id="IS20-V28" source="L20-web.pdf" page="46" type="grafico" description="AND e OR separabili da una retta, XOR non separabile" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **AND e OR sì, XOR no.** Un perceptron può imparare AND e OR, ma **non l’OR esclusivo**: la sua frontiera di decisione è una **retta**, e **nessuna retta mette (0,1), (1,0) da una parte e (0,0), (1,1) dall’altra**.

> 💡 **Perché nessuna retta funziona (nota didattica).** Con $\hat{y}=1$ se $w_1x_1+w_2x_2\ge\theta$: il punto (0,0) deve dare 0, quindi $0<\theta$; i punti (1,0) e (0,1) devono dare 1, quindi $w_1\ge\theta$ e $w_2\ge\theta$; ma allora $w_1+w_2\ge 2\theta>\theta$ e il punto (1,1) verrebbe classificato 1 invece di 0. Il sistema di disequazioni non ha soluzione. Minsky e Papert (1969) resero celebre questo limite, che contribuì al primo «inverno» delle reti neurali (Lezione 21).

---

### **6. Teoria: le reti neurali multistrato**


Il metodo della backpropagation.

#### **6.1. Le reti neurali multistrato**

> 📌 **Definizione.** Un **perceptron multistrato (MLP)** è una **rete neurale feed-forward** con **uno o più strati nascosti** di neuroni tra gli ingressi e lo strato d’uscita.

- **Strato d’ingresso**: i segnali sorgente $x_1,\dots,x_d$ (**nessun calcolo**).
- **Almeno uno strato nascosto** di neuroni di calcolo.
- **Uno strato d’uscita** di neuroni di calcolo.
- **I segnali si propagano in avanti, strato per strato.**

<!-- SOURCE_VISUAL id="IS20-V29" source="L20-web.pdf" page="48" type="schema" description="Perceptron multistrato con strato d'ingresso, strati nascosti e strato d'uscita" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2. Che cosa nasconde lo strato nascosto?**

- Uno strato nascosto **«nasconde» la sua uscita desiderata**: il training set dà solo $x$ e $y$.
  - I neuroni nascosti **non si possono osservare** attraverso il comportamento ingresso/uscita della rete.
  - **Non c’è un modo ovvio per sapere quale dovrebbe essere la loro uscita desiderata.**
- **Reti feed-forward commerciali**: tipicamente **1–2 strati nascosti**, da **10 a 1000 neuroni per strato**.
- **Reti sperimentali**: **3+ strati nascosti** e **milioni di neuroni**.

<!-- SOURCE_VISUAL id="IS20-V30" source="L20-web.pdf" page="49" type="schema" description="Rete con l'uscita nota e i neuroni nascosti con uscita desiderata ignota" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Più avanti, Lezione 21**: le reti convoluzionali hanno **molti più strati** (≫), e i loro strati **funzionano in modo piuttosto diverso**.

> 💡 **Il problema che la backpropagation risolve (nota didattica).** Per il perceptron l’errore è noto perché si conosce l’uscita desiderata. Per un neurone nascosto no. La backpropagation **distribuisce** l’errore dell’uscita ai neuroni nascosti in proporzione a quanto ciascuno vi ha contribuito, cioè ai pesi che li collegano all’uscita (§6.8). È il problema dell’**assegnazione del merito** (*credit assignment*).

#### **6.3. L’apprendimento in una rete multistrato: due fasi**

L’apprendimento procede **come per il perceptron**: si presentano i campioni, si confronta l’uscita con quella desiderata, si aggiustano i pesi per ridurre l’errore. L’algoritmo di **backpropagation** ha **due fasi**:

1. **passo in avanti** (*forward pass*): l’ingresso viene propagato **strato per strato** finché lo strato d’uscita produce $\hat{y}$ (segnali d’ingresso, in avanti);
2. **passo all’indietro** (*backward pass*): l’errore viene propagato **dallo strato d’uscita indietro verso l’ingresso**; **i pesi vengono modificati lungo il percorso** (segnali d’errore, all’indietro).

<!-- SOURCE_VISUAL id="IS20-V31" source="L20-web.pdf" page="50" type="schema" description="Segnali d'ingresso in avanti e segnali d'errore all'indietro in una rete multistrato" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.4. Notazione per la backpropagation**

| Simbolo | Significato |
|---|---|
| $x_i$ | ingresso $i$, $i=1,\dots,d$ |
| $h_j$ | uscita del neurone nascosto $j$, $j=1,\dots,m$ |
| $\hat{y}_k$ | uscita del neurone d’uscita $k$, $k=1,\dots,l$ |
| $y_k$ | uscita desiderata del neurone $k$ |
| $e_k=y_k-\hat{y}_k$ | errore del neurone d’uscita $k$ |
| $w_{ij}$ | peso ingresso $i$ → nascosto $j$ |
| $w_{jk}$ | peso nascosto $j$ → uscita $k$ |
| $\theta_j$, $\theta_k$ | soglie |
| $\alpha$ | learning rate |
| $p$ | iterazione (un campione) |

Qui: **1 strato nascosto** (rete a tre strati). Con 2 strati nascosti **le stesse regole si ripetono strato per strato**.

<!-- SOURCE_VISUAL id="IS20-V32" source="L20-web.pdf" page="51" type="schema" description="Rete a tre strati con i simboli xi, hj, yk, wij, wjk" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.5. Backpropagation, passo 1: inizializzazione**

**Passo 1: inizializzazione** (neurone per neurone). Si impostano tutti i pesi e le soglie della rete a **numeri casuali**, **distribuiti uniformemente in un intervallo piccolo**:

$$\left(-\frac{2{,}4}{F_i},\ +\frac{2{,}4}{F_i}\right),$$

con $F_i$ il **numero totale di ingressi del neurone $i$**.

- Neurone nascosto, $F_i=5$ ingressi: intervallo $(-0{,}48;\ +0{,}48)$.
- Neurone d’uscita, $F_i=4$ ingressi: intervallo $(-0{,}6;\ +0{,}6)$.

**Più ingressi, pesi più piccoli**: la somma pesata **non parte nella saturazione della sigmoide**.

<!-- SOURCE_VISUAL id="IS20-V33" source="L20-web.pdf" page="52" type="schema" description="Rete con un neurone nascosto a 5 ingressi e il suo intervallo di inizializzazione" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché dividere per il numero di ingressi (nota didattica).** La somma pesata di un neurone con molti ingressi è grande anche con pesi piccoli: se i pesi fossero nello stesso intervallo per tutti, i neuroni con molti ingressi partirebbero **saturi**, con la sigmoide già vicina a 0 o 1 e derivata quasi nulla (§6.11). Scalare l’intervallo con $1/F_i$ mantiene la somma in una zona in cui il neurone impara. Le inizializzazioni moderne (Xavier/Glorot, He) seguono la stessa idea, con un fattore $1/\sqrt{F_i}$.

#### **6.6. Backpropagation, passo 2: attivazione (passo in avanti)**

**Passo 2: attivazione.** Si applicano gli ingressi $x_1(p),\dots,x_d(p)$ e le uscite desiderate $y_1(p),\dots,y_l(p)$.

**(a) Uscite dello strato nascosto**:

$$h_j(p)=\text{sigmoid}\left[\sum_{i=1}^{d}x_i(p)\,w_{ij}(p)-\theta_j\right].$$

**(b) Uscite dello strato d’uscita**, i cui ingressi sono le $h_j$:

$$\hat{y}_k(p)=\text{sigmoid}\left[\sum_{j=1}^{m}h_j(p)\,w_{jk}(p)-\theta_k\right].$$

$d$: ingressi del neurone nascosto $j$; $m$: ingressi del neurone d’uscita $k$ (= neuroni nascosti); $l$: numero di uscite; $\text{sigmoid}(z)=1/(1+e^{-z})$.

<!-- SOURCE_VISUAL id="IS20-V34" source="L20-web.pdf" page="53" type="schema" description="Passo in avanti: (a) strato nascosto, (b) strato d'uscita" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.7. Imparare come scendere lungo l’errore**

> 📌 **Definizione.** La **discesa del gradiente** cambia ogni peso **contro la pendenza dell’errore**:
> $$\Delta w=-\alpha\frac{\partial E}{\partial w},$$
> con $E$ l’errore da minimizzare, $\partial E/\partial w$ la **pendenza**, $\alpha$ il **learning rate** (ampiezza del passo). **La backpropagation calcola queste pendenze per tutti i pesi: i termini $\delta$.**

**Esempio**: $E(w)=(w-2)^2+0{,}5$, partenza $w=-1$. Pendenza $=2(w-2)=-6$. Con $\alpha=0{,}1$: $\Delta w=-0{,}1\cdot(-6)=+0{,}6$; **nuovo $w=-0{,}4$** (verso il minimo $w=2$).

| $\alpha$ | comportamento |
|---|---|
| 0,1 | **lento** |
| 0,4 | **buono** |
| 0,95 | **oscilla** |

<!-- SOURCE_VISUAL id="IS20-V35" source="L20-web.pdf" page="54" type="grafico" description="Discesa del gradiente sulla parabola dell'errore con learning rate 0,1, 0,4 e 0,95" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **I tre comportamenti in numeri (nota didattica).** Con $E=(w-2)^2+0{,}5$ l’aggiornamento è $w\leftarrow w-2\alpha(w-2)$, cioè la distanza dal minimo si moltiplica per $(1-2\alpha)$ a ogni passo. $\alpha=0{,}1$: fattore 0,8, la distanza si riduce del 20% per passo (lento). $\alpha=0{,}4$: fattore 0,2, quasi subito al minimo. $\alpha=0{,}95$: fattore $-0{,}9$, il peso salta da una parte all’altra del minimo e si avvicina lentamente (oscilla). Con $\alpha>1$ il fattore supera 1 in modulo e **diverge**: è il «troppo grande» della Lezione 17 (§6.6).

#### **6.8. Backpropagation, passo 3: i pesi dello strato d’uscita**

**Passo 3: addestramento dei pesi.** Si propagano **all’indietro** gli errori dei neuroni d’uscita. **Prima lo strato d’uscita**:

$$\Delta w_{jk}(p)=\alpha\cdot h_j(p)\cdot\delta_k(p),\qquad w_{jk}(p+1)=w_{jk}(p)+\Delta w_{jk}(p),$$

con $\delta_k(p)$ il **gradiente dell’errore** del neurone d’uscita $k$.

| | Regola |
|---|---|
| perceptron | $\Delta w_i=\alpha\cdot x_i\cdot e$ |
| strato d’uscita | $\Delta w_{jk}=\alpha\cdot h_j\cdot\delta_k$ |

> 📌 **La stessa equazione del perceptron**: l’ingresso $x_i$ diventa $h_j$, l’errore $e$ diventa $\delta_k$.

<!-- SOURCE_VISUAL id="IS20-V36" source="L20-web.pdf" page="55" type="schema" description="Correzione dei pesi dello strato d'uscita" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.9. Il gradiente dell’errore dello strato d’uscita**

**(a) Gradiente dell’errore dei neuroni dello strato d’uscita**:

$$\delta_k(p)=\hat{y}_k(p)\,[1-\hat{y}_k(p)]\,e_k(p),\qquad e_k(p)=y_k(p)-\hat{y}_k(p).$$

$\hat{y}_k[1-\hat{y}_k]$: **derivata della sigmoide** all’uscita del neurone $k$; $e_k$: errore del neurone $k$.

**Esempio**: $\hat{y}_k=0{,}5$, $y_k=1$: $e_k=1-0{,}5=0{,}5$; $\delta_k=0{,}5\cdot 0{,}5\cdot 0{,}5=0{,}125$.

> ⚠️ **Non d’esame: la formula.** Tenere a mente il suo **significato**, «**errore × pendenza dell’attivazione**». Un neurone **saturo** ($\hat{y}_k\approx 0$ o $1$) ha $\delta_k\approx 0$ e **impara lentamente**.

> 💡 **Da dove viene (nota didattica).** Con l’errore quadratico $E=\frac12e_k^2$ e $\hat{y}_k=\sigma(z_k)$, la regola della catena dà $\frac{\partial E}{\partial w_{jk}}=-e_k\cdot\sigma'(z_k)\cdot h_j=-\delta_k h_j$. La discesa del gradiente $\Delta w=-\alpha\,\partial E/\partial w$ dà quindi $\Delta w_{jk}=\alpha h_j\delta_k$: la formula del §6.8 è la discesa del gradiente scritta per un neurone sigmoide.

#### **6.10. Backpropagation, passo 3: lo strato nascosto**

**(b) Gradiente dell’errore dei neuroni dello strato nascosto**: i gradienti d’uscita **viaggiano all’indietro attraverso i pesi $w_{jk}$**:

$$\delta_j(p)=h_j(p)\,[1-h_j(p)]\sum_{k=1}^{l}\delta_k(p)\,w_{jk}(p).$$

Poi $\Delta w_{ij}(p)=\alpha\cdot x_i(p)\cdot\delta_j(p)$ e $w_{ij}(p+1)=w_{ij}(p)+\Delta w_{ij}(p)$.

> ⚠️ **Non d’esame: la formula.** Stessa struttura di $\delta_k$: **pendenza della sigmoide × un errore**; qui l’«errore» del neurone nascosto $j$ è $\sum_k\delta_kw_{jk}$, **raccolto dallo strato dopo di esso**.

<!-- SOURCE_VISUAL id="IS20-V37" source="L20-web.pdf" page="57" type="schema" description="Gradienti dello strato d'uscita che tornano allo strato nascosto attraverso i pesi" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Da dove viene $h_j(1-h_j)$ (nota didattica).** È la **derivata della sigmoide** (§4.3). Il gradiente $\delta$ è l’errore moltiplicato per quanto il neurone è sensibile in quel punto: se la sigmoide è saturata ($h\approx 0$ o $h\approx 1$), $h(1-h)\approx 0$ e il neurone quasi non impara. Il termine $\sum_k\delta_kw_{jk}$ è l’errore «restituito» al neurone nascosto dai neuroni a cui è collegato: un neurone collegato all’uscita con un peso grande riceve molta della «colpa».

#### **6.11. Backpropagation, passo 4: iterazione e arresto**

**Passo 4: iterazione.** Si incrementa $p$ di uno, si torna al passo 2, si ripete **finché il criterio scelto è soddisfatto**:

- **è stato raggiunto il numero massimo di epoche**;
- **l’errore è più piccolo di una soglia fissata**;
- **non c’è più miglioramento dei pesi** (gradiente ≈ 0).

**La curva di apprendimento** (errore in funzione dell’epoca) **dice quando fermarsi**. L’errore è la **somma degli errori quadratici**:

$$\text{SSE}=\sum_{n=1}^{N}(y_n-\hat{y}_n)^2$$

su $N$ campioni ($y_n$ desiderato, $\hat{y}_n$ effettivo).

**Apprendimento dello XOR con backpropagation online riproducibile**: 4 campioni XOR, aggiornamenti online, $\alpha=0{,}1$, pesi iniziali della fonte: **56 346 epoche** (**225 384 aggiornamenti**) per raggiungere **SSE ≈ 0,001** (obiettivo SSE = 0,001). **Errore di training, non accuratezza di test.**

<!-- SOURCE_VISUAL id="IS20-V38" source="L20-web.pdf" page="58" type="grafico" description="Curva di apprendimento dello XOR: SSE in scala logaritmica in funzione delle epoche fino a 56346" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Online, batch e mini-batch (nota didattica).** «Online» significa che i pesi si aggiornano dopo **ogni** campione: 4 aggiornamenti per epoca, $4\cdot 56\,346=225\,384$. In modalità *batch* si somma il gradiente su tutti i campioni e si aggiorna una volta per epoca; in *mini-batch* (il default delle librerie moderne) si aggiorna ogni $B$ campioni. 56 346 epoche per 4 punti sono tantissime: con $\alpha$ piccolo, sigmoide e partenza poco favorevole la discesa attraversa lunghi «altipiani» di errore quasi costante.

#### **6.12. Che cosa ha imparato lo strato nascosto: lo XOR risolto**

**XOR: due frontiere nascoste, una regione non lineare.** Dopo 56 346 epoche di addestramento online ($\alpha=0{,}1$, pesi iniziali della fonte), **ogni neurone nascosto traccia una retta**; il neurone d’uscita **combina le loro attivazioni**.

- **XOR = OR e non AND**: una **regione tra due rette**, impossibile per un singolo perceptron.
- (0,1), (1,0) stanno **tra le rette** ⇒ rosso, uscita 1; (0,0), (1,1) stanno **fuori** ⇒ blu, uscita 0.

<!-- SOURCE_VISUAL id="IS20-V39" source="L20-web.pdf" page="59" type="grafico" description="Le due rette dei neuroni nascosti e la regione XOR tra di esse" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché due neuroni nascosti bastano (nota didattica).** Ciascun neurone nascosto traccia una **retta** nel piano degli ingressi, come un perceptron. Il neurone d’uscita combina le due regioni: un neurone nascosto si attiva per «almeno un ingresso a 1» (OR) e l’altro per «entrambi a 1» (AND); l’uscita risponde 1 quando il primo è attivo e il secondo no, che è esattamente lo XOR. È il superamento del limite del §5.9, e il motivo per cui lo strato nascosto è la vera fonte di potenza delle reti: trasforma un problema non separabile in uno separabile (lo stesso principio delle feature polinomiali, §1.9, e degli embedding, Lezione 18).

#### **6.13. I problemi principali della backpropagation**

- **Instabilità del gradiente**:
  - **gradiente che svanisce** (*vanishing gradient*);
  - **gradiente che esplode** (*exploding gradient*).
- **La scelta della funzione di attivazione è cruciale** per il comportamento del gradiente durante l’apprendimento.

<!-- SOURCE_VISUAL id="IS20-V40" source="L20-web.pdf" page="60" type="figura" description="Illustrazione dei problemi del gradiente" reason="Immagine visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.14. Il gradiente che svanisce**

**Il gradiente dell’errore rispetto ai pesi diventa molto piccolo e l’apprendimento si ferma**, soprattutto nei **primi strati delle reti profonde** con attivazioni **sigmoide o tanh**.

<!-- SOURCE_VISUAL id="IS20-V41" source="L20-web.pdf" page="61" type="grafico" description="Sigmoide e sua derivata con le zone di saturazione e il massimo 0,25" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Uno strato indietro**: il gradiente $\delta_j$ del neurone penultimo $j$ è il **prodotto di due gradienti**:

$$\delta_j=\underbrace{h_j(1-h_j)}_{\text{locale: pendenza di }j}\cdot\underbrace{\sum_k\delta_kw_{jk}}_{\text{in arrivo dall’uscita}}.$$

- **Uscita**: $\hat{y}_k=0{,}5$, $y_k=1\Rightarrow e_k=0{,}5$; $\delta_k=0{,}5\cdot 0{,}5\cdot 0{,}5=0{,}125$.
- **Penultimo**: $h_j=0{,}5$, $w_{jk}=1\Rightarrow\delta_j=0{,}25\cdot 0{,}125\approx 0{,}031$.

**Ogni strato attraversato all’indietro moltiplica il gradiente per una pendenza $h_j(1-h_j)\le 0{,}25$ e per i pesi**:

- **5 strati**: $0{,}25^5\approx 1\times 10^{-3}$;
- **10 strati**: $0{,}25^{10}\approx 1\times 10^{-6}$.

#### **6.15. Il gradiente che esplode**

**La situazione opposta**: i gradienti diventano **eccessivamente grandi**, e l’apprendimento diventa **instabile e inefficiente**. In $\delta_j=h_j(1-h_j)\sum_k\delta_kw_{jk}$ **pesi $w_{jk}$ grandi amplificano il gradiente a ogni strato**: **controllare l’ampiezza dei pesi**.

| Fattore per strato | 10 strati |
|---|---|
| 1,5 (**esplode**) | $1{,}5^{10}\approx 58$ |
| 0,25 (**svanisce**) | $0{,}25^{10}\approx 10^{-6}$ |

<!-- SOURCE_VISUAL id="IS20-V42" source="L20-web.pdf" page="62" type="grafico" description="Scala del gradiente in funzione del numero di strati attraversati per fattori 1,5 e 0,25" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **I rimedi (nota didattica).** Con un fattore per strato di $0{,}25\cdot w$, il gradiente resta stabile solo se $|w|\approx 4$; con pesi dell’ordine di 1 svanisce, con pesi grandi (per esempio 6) esplode ($1{,}5^L$). Rimedi diffusi, che si vedranno nelle Lezioni 21 e 22: attivazioni **ReLU** (pendenza 1 per $z>0$), **inizializzazione** accurata (§6.5), **normalizzazione** degli strati (*batch normalization*), **clipping** del gradiente e **connessioni residue** (ResNet), che offrono al gradiente una «scorciatoia» che salta gli strati.

---

### **7. Strumenti: le reti neurali in pratica**


Molti tipi di reti; una prima rete in MATLAB.

#### **7.1. Molti tipi di reti — oggi: perceptron, FF e RBF**

<!-- SOURCE_VISUAL id="IS20-V43" source="L20-web.pdf" page="64" type="figura" description="Mappa dei tipi di reti neurali con perceptron, feed-forward e RBF evidenziati" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2. Una prima rete feed-forward in MATLAB (codice)**

```matlab
[x,t] = simplefit_dataset;      % ingressi x, target t
net = feedforwardnet(10);       % 1 strato nascosto, 10 neuroni
net = train(net,x,t);           % backpropagation
view(net)
y = net(x);                     % uscite della rete
perf = perform(net,y,t)         % errore (mse)
```

**Risultato nell’esecuzione della fonte**: `perf = 1.4639e-04`.

**Input**: $x$ (**1 feature, 94 campioni**), target $t$ (94 valori). **Output**: la rete addestrata `net`, le sue uscite $y$ e l’**errore quadratico medio** `perf`.

<!-- SOURCE_VISUAL id="IS20-V44" source="L20-web.pdf" page="65" type="schermata" description="Struttura della rete feed-forward con 10 neuroni nascosti mostrata da view(net)" reason="Schermata visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

In Colab l’**occultamento dell’informazione** è meno evidente… Ci saranno **sessioni di laboratorio dedicate**. Il codice completo sarà pubblicato più avanti sul sito del corso.

> ⚠️ **Che cosa fa `train` di default (nota didattica).** A differenza degli esempi della Lezione 17 (§7.4), qui non si impostano `divideParam`: `train` divide automaticamente i dati in **training, validation e test** (per default 70%, 15%, 15%) e usa la validation per l’**early stopping**. `perform(net, y, t)` calcolato su **tutti** i dati mescola quindi errori di training e di test: il valore $1{,}46\times 10^{-4}$ non è un errore di test. Per l’errore di test si usa `tr.testInd` restituito da `[net, tr] = train(...)`. **Parametri**: 10 neuroni con 1 ingresso e 1 uscita danno $10\cdot 1+10=20$ parametri nascosti e $10+1=11$ d’uscita, 31 in totale contro 94 campioni.

---

### **8. Appendice**


Dettagli di supporto: codice del fitting polinomiale; esempio svolto di backpropagation.

#### **8.1. Il fitting polinomiale (codice)**

**In una dimensione.**

```python
import numpy as np
x = np.array([0, 1, 2, 3, 4, 5])               # (6,)
y = np.array([0, 0.8, 0.9, 2.1, 2.0, 5.3])     # (6,)
p = np.polyfit(x, y, 3)                        # 4 coefficienti
y_new = np.polyval(p, 2.5)                     # previsione
```

**Input**: 6 coppie $(x,y)$, grado $M=3$. **Output**: $p=(p_0,p_1,p_2,p_3)$ di $y(x)=p_0x^3+p_1x^2+p_2x+p_3$, poi $\hat{y}(2{,}5)$. Risultato: $p\approx(0{,}123;\ -0{,}715;\ 1{,}537;\ -0{,}059)$, $\hat{y}(2{,}5)\approx 1{,}24$. MATLAB: `p = polyfit(x,y,3)`.

<!-- SOURCE_VISUAL id="IS20-V45" source="L20-web.pdf" page="68" type="grafico" description="Sei punti di training, la cubica interpolata e la previsione in x = 2,5" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Verifica (nota didattica).** $0{,}123\cdot 15{,}625-0{,}715\cdot 6{,}25+1{,}537\cdot 2{,}5-0{,}059=1{,}922-4{,}469+3{,}843-0{,}059\approx 1{,}24$. Attenzione all’ordine: `np.polyfit` restituisce i coefficienti **dal grado più alto al più basso**, quindi $p_0$ è il coefficiente di $x^3$. Con 4 coefficienti e 6 punti la cubica non passa per tutti i punti (2 gradi di libertà «di riserva» per mediare il rumore).

**Con $d$ feature.**

```matlab
mdl = fitlm(X, y, 'poly33');      % d=2, grado 3
y_new = predict(mdl, X_new);
```

```python
from sklearn.preprocessing import PolynomialFeatures
from sklearn.linear_model import LinearRegression
poly = PolynomialFeatures(degree=3)
X_poly = poly.fit_transform(X)                   # (N,d)->(N,termini)
mdl = LinearRegression().fit(X_poly, y)
y_new = mdl.predict(poly.transform(X_new))
```

**Input**: $X$ con $N$ campioni e $d$ feature, $y$ con $N$ valori. **Output**: le previsioni $\hat{y}$ per i campioni nuovi. `fitlm` di MATLAB **espande le feature da solo**; scikit-learn le **crea esplicitamente** con `PolynomialFeatures`. **Il modello è lineare nei parametri $w$, non in $x$.**

Con $d=2$, $M=3$: $\binom{5}{3}=10$ colonne: $1,\ x_1,\ x_2,\ x_1^2,\ x_1x_2,\ x_2^2,\ x_1^3,\ x_1^2x_2,\ x_1x_2^2,\ x_2^3$.

> 💡 **«Lineare nei parametri» (nota didattica).** $y=w_0+w_1x_1+\dots+w_9x_2^3$ è una curva in $x$, ma una combinazione lineare dei $w$: quindi si stima con i minimi quadrati della Lezione 17, trattando $x_1^2$, $x_1x_2$… come feature nuove. `'poly33'` indica il grado massimo 3 per entrambe le variabili. È il ponte tra il regressore lineare e le reti: le feature polinomiali sono fisse e scelte a mano, gli strati nascosti di una rete le imparano.

#### **8.2. Esempio di backpropagation (non d’esame)**

**Non d’esame, ma utile per controllare i concetti: la backpropagation «con la matita».**

**Rete a tre strati per l’OR esclusivo.**

- **Ingressi**: 2 ($x_1$, $x_2$); **neuroni nascosti**: 2 (3 e 4); **neurone d’uscita**: 1 (5); attivazioni **sigmoide**.
- **La soglia di un neurone è un peso $\theta$ su un ingresso fisso uguale a $-1$.**
- **Valori iniziali**: $w_{13}=0{,}5$, $w_{14}=0{,}9$, $w_{23}=0{,}4$, $w_{24}=1{,}0$, $w_{35}=-1{,}2$, $w_{45}=1{,}1$, $\theta_3=0{,}8$, $\theta_4=-0{,}1$, $\theta_5=0{,}3$.

<!-- SOURCE_VISUAL id="IS20-V46" source="L20-web.pdf" page="71" type="schema" description="Rete a tre strati per lo XOR con neuroni 3, 4, 5, pesi e soglie su ingressi fissi a -1" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Passo in avanti.** Campione di training: $x_1=x_2=1$, uscita desiderata $y_5=0$.

$$h_3=\text{sigmoid}(1\cdot 0{,}5+1\cdot 0{,}4-1\cdot 0{,}8)=\text{sigmoid}(0{,}1)=0{,}5250,$$

$$h_4=\text{sigmoid}(1\cdot 0{,}9+1\cdot 1{,}0+1\cdot 0{,}1)=\text{sigmoid}(2{,}0)=0{,}8808,$$

$$\hat{y}_5=\text{sigmoid}\big(0{,}5250\cdot(-1{,}2)+0{,}8808\cdot 1{,}1-1\cdot 0{,}3\big)=\text{sigmoid}(0{,}0389)=0{,}5097,$$

$$e=y_5-\hat{y}_5=0-0{,}5097=-0{,}5097.$$

> 💡 **Il segno di $\theta_4$ (nota didattica).** La soglia entra come $-1\cdot\theta_4=-1\cdot(-0{,}1)=+0{,}1$: è il «$+1\cdot 0{,}1$» della seconda riga. Per $\hat{y}_5$: $-0{,}6300+0{,}9689-0{,}3=0{,}0389$.

**Passo all’indietro, neurone d’uscita.** Gradiente dell’errore del neurone 5, poi le correzioni con $\alpha=0{,}1$:

$$\delta_5=\hat{y}_5(1-\hat{y}_5)\,e=0{,}5097\cdot 0{,}4903\cdot(-0{,}5097)=-0{,}1274,$$

$$\Delta w_{35}=\alpha\,h_3\,\delta_5=0{,}1\cdot 0{,}5250\cdot(-0{,}1274)=-0{,}0067,$$

$$\Delta w_{45}=\alpha\,h_4\,\delta_5=0{,}1\cdot 0{,}8808\cdot(-0{,}1274)=-0{,}0112,$$

$$\Delta\theta_5=\alpha\cdot(-1)\cdot\delta_5=0{,}0127.$$

> 💡 **Il segno corretto (nota didattica).** L’edizione precedente riportava $\Delta\theta_5=-0{,}0127$; l’edizione 2026/27 corregge in $+0{,}0127$: infatti $0{,}1\cdot(-1)\cdot(-0{,}1274)$ è positivo, e l’aggiornamento $\theta_5=0{,}3+0{,}0127=0{,}3127$ è coerente. Alzare la soglia abbassa l’uscita, che doveva scendere da 0,51 a 0.

**Passo all’indietro, neuroni nascosti.**

$$\delta_3=h_3(1-h_3)\,\delta_5\,w_{35}=0{,}5250\cdot 0{,}4750\cdot(-0{,}1274)\cdot(-1{,}2)=0{,}0381,$$

$$\delta_4=h_4(1-h_4)\,\delta_5\,w_{45}=0{,}8808\cdot 0{,}1192\cdot(-0{,}1274)\cdot 1{,}1=-0{,}0147.$$

Correzioni $\Delta w_{ij}=\alpha\,x_i\,\delta_j$ con $x_1=x_2=1$:

$$\Delta w_{13}=\Delta w_{23}=0{,}1\cdot 1\cdot 0{,}0381=0{,}0038,\qquad \Delta w_{14}=\Delta w_{24}=0{,}1\cdot 1\cdot(-0{,}0147)=-0{,}0015,$$

$$\Delta\theta_3=0{,}1\cdot(-1)\cdot 0{,}0381=-0{,}0038,\qquad \Delta\theta_4=0{,}1\cdot(-1)\cdot(-0{,}0147)=0{,}0015.$$

> 💡 **Leggere i segni (nota didattica).** L’uscita doveva essere 0 ed è 0,51: bisogna **abbassarla**. Il neurone 3 è collegato all’uscita con un peso **negativo** ($w_{35}=-1{,}2$), quindi per abbassare $\hat{y}_5$ conviene **aumentare** $h_3$: $\delta_3>0$ e i pesi verso il neurone 3 crescono. Il neurone 4 ha un peso **positivo** verso l’uscita, quindi va **ridotto**: $\delta_4<0$. Si noti anche che $|\delta_4|<|\delta_3|$ nonostante $|w_{45}|\approx|w_{35}|$: il neurone 4 è più saturo ($h_4=0{,}88$, pendenza $0{,}105$ contro $0{,}249$), ed è il gradiente che svanisce in miniatura.

**I pesi dopo un campione.** Si aggiornano tutti i pesi e le soglie: $w(p+1)=w(p)+\Delta w(p)$.

| Parametro | Valore iniziale | Correzione | Valore aggiornato |
|---|---:|---:|---:|
| $w_{13}$ | 0,5 | +0,0038 | 0,5038 |
| $w_{14}$ | 0,9 | −0,0015 | 0,8985 |
| $w_{23}$ | 0,4 | +0,0038 | 0,4038 |
| $w_{24}$ | 1,0 | −0,0015 | 0,9985 |
| $w_{35}$ | −1,2 | −0,0067 | −1,2067 |
| $w_{45}$ | 1,1 | −0,0112 | 1,0888 |
| $\theta_3$ | 0,8 | −0,0038 | 0,7962 |
| $\theta_4$ | −0,1 | +0,0015 | −0,0985 |
| $\theta_5$ | 0,3 | +0,0127 | 0,3127 |

Si ripete sui quattro campioni XOR nell’ordine indicato. Con $\alpha=0{,}1$ la SSE sull’intero insieme scende **sotto 0,001 dopo 56 346 epoche**; si vedano lo script di riproducibilità e la curva di apprendimento nella lezione (§6.11).

**I risultati finali.**

| $x_1$ | $x_2$ | $y_5$ desiderata | $\hat{y}_5$ effettiva | errore $e$ |
|---:|---:|---:|---:|---:|
| 1 | 1 | 0 | 0,0155 | −0,0155 |
| 0 | 1 | 1 | 0,9849 | 0,0151 |
| 1 | 0 | 1 | 0,9849 | 0,0151 |
| 0 | 0 | 0 | 0,0175 | −0,0175 |

**Somma degli errori quadratici: 0,0010.** **Lo strato nascosto permette alla rete di separare lo XOR**, cosa che un singolo perceptron non può fare. I valori corrispondono all’esperimento riproducibile di backpropagation online a 56 346 epoche (4 campioni di training, $\alpha=0{,}1$).

> 💡 **Verifica della SSE (nota didattica).** $0{,}0155^2+0{,}0151^2+0{,}0151^2+0{,}0175^2=0{,}00024+0{,}00023+0{,}00023+0{,}00031=0{,}00101$. Le uscite non sono esattamente 0 e 1 perché la sigmoide le raggiunge solo con ingressi infiniti: con una soglia a 0,5 la rete classifica correttamente tutti e quattro i casi.

---

### **9. Punti principali**


> ✅ **Punti principali della lezione.**
> - **Più feature non sono sempre meglio**: $b^d$ celle, dati sparsi e distanze che **perdono contrasto** (maledizione della dimensionalità); rimedi: **selezione delle feature, riduzione della dimensionalità, controllo della complessità**.
> - Un **neurone artificiale** calcola $X=\sum_iw_ix_i$, lo **confronta con una soglia** $\theta$ e applica una **funzione di attivazione**.
> - Il **perceptron** impara con la **regola delta** $\Delta w_i=\alpha x_ie$; risolve **solo problemi linearmente separabili**: **AND e OR sì, XOR no**.
> - Gli **strati nascosti** rendono la rete più potente ma **nascondono la loro uscita desiderata**: la **backpropagation** la calcola con un **passo in avanti** e un **passo all’indietro** dei gradienti d’errore $\delta$.
> - **La backpropagation è discesa del gradiente**: il learning rate $\alpha$ fissa il passo; **la curva di apprendimento dice quando fermarsi**.
> - **Con la profondità i gradienti possono svanire** (pendenza della sigmoide $\le 0{,}25$) **o esplodere** (pesi grandi): contano la funzione di attivazione e l’ampiezza dei pesi.

---

### **10. Distinzioni da non confondere**


| Coppia | Differenza chiave |
|---|---|
| **feature informativa** / **feature casuale** | migliora le distanze / le diluisce (97,5% → 57,5% con 100 casuali) |
| **dimensione nominale** / **effettiva** | numero di feature / dimensione della regione occupata dai dati |
| **celle** $b^d$ / **campioni** $N$ | crescono esponenzialmente / crescono linearmente |
| **neurone biologico** / **artificiale** | cellula viva / funzione somma-soglia-attivazione |
| **gradino** / **segno** | uscita 0/1 / uscita ±1 |
| **sigmoide** / **tanh** | $(0,1)$, derivata massima 0,25 / $(-1,1)$, derivata massima 1 |
| **soglia** $\theta$ / **bias** $b$ | $X\ge\theta$ / $X+b\ge 0$, con $b=-\theta$ |
| **iterazione** / **epoca** | un campione / tutto il training set |
| **perceptron** / **MLP** | un neurone, frontiera lineare / strati nascosti, frontiere non lineari |
| **linearmente separabile** / **non separabile** | AND, OR / XOR |
| **regola delta** / **backpropagation** | $\Delta w=\alpha xe$ / stessa forma con $\delta$ al posto di $e$, strato per strato |
| **passo in avanti** / **all’indietro** | calcola le uscite / propaga gli errori e corregge i pesi |
| **$\delta_k$ d’uscita** / **$\delta_j$ nascosto** | pendenza × errore noto / pendenza × errore restituito $\sum_k\delta_kw_{jk}$ |
| **learning rate piccolo** / **grande** | convergenza lenta / oscillazione o divergenza |
| **online** / **batch** | aggiornamento a ogni campione / una volta per epoca |
| **gradiente che svanisce** / **che esplode** | fattore per strato < 1 / fattore > 1 |
| **rete RBF** / **SVM con kernel RBF** | prototipi e pesi d’uscita addestrati / vettori di supporto e margine |
| **rete superficiale** / **profonda** | 1–2 strati nascosti / 3 o più |
| **errore di training** / **di test** | SSE 0,001 sui 4 punti XOR / non misurato nell’esempio |
| **`perform` su tutti i dati** / **sul test set** | mescola training e test / stima della generalizzazione |
