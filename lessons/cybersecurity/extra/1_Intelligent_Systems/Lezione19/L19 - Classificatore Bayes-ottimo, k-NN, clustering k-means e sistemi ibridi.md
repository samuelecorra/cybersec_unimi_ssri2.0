## **Lezione 19: Classificazione Bayes-ottima, k-NN, clustering e sistemi intelligenti**

La lezione parte da una domanda di fondo: **qual è l’errore minimo possibile?** Il **classificatore Bayes-ottimo** risponde che, dove le classi si sovrappongono, anche il classificatore migliore sbaglia, e il **Naive Bayes** rende calcolabile quella regola con un’ipotesi di indipendenza. Si passa poi all’**apprendimento per memoria**: la differenza tra metodi **eager** e **lazy**, e il **k-NN**, il classificatore che non addestra nulla e decide guardando i vicini. Ma che cosa significa «vicino»? Distanza euclidea o coseno, posizione dell’origine e unità di misura cambiano i vicini: **la similarità va progettata**, e la lezione lo mostra fino agli embedding di foto reali (gatti e cani, scatole di cartone). Senza etichette si arriva al **clustering k-means**, alla scelta di $k$, all’assegnazione di nuovi campioni e alla differenza con il **rilevamento di anomalie**. L’ultima parte allarga lo sguardo oltre il singolo metodo: **algoritmi genetici**, **logica fuzzy**, **swarm intelligence** e **sistemi ibridi**.

> **Fonte.** La lezione segue il PDF dell’edizione 2026/27 `sources_2026-27/L19-web.pdf` (112 pagine, di cui 94–112 di appendice). I segnaposto per le figure indicano la pagina del PDF. Lo script MATLAB della cartella (`Lesson_19_laboratory_MATLAB_Knn.m`) è commentato nel §5; il PDF dell’edizione precedente è conservato nella cartella.


### **0. Panoramica**


- **il limite**: qual è l’errore minimo possibile? Classificatori **Bayes-ottimo** e **Naive Bayes**;
- **imparare ricordando**: apprendimento **eager** contro **lazy**, poi il classificatore **k-NN**;
- **che cosa significa «vicino»?** Distanza, origine e unità: **la similarità va progettata**;
- **senza etichette**: clustering **k-means**, scelta di $k$, nuovi campioni e anomalie;
- **oltre un solo metodo**: sistemi **genetici, fuzzy, swarm e ibridi**.


![](imgs/Pasted%20image%2020261125015331.png)

---

### **1. La classificazione Bayes-ottima**


**Stiamo inseguendo l’errore minimo, ma qual è il limite?!**

#### **1.1. Il limite Bayes-ottimo**

Dato un problema/dataset specifico, con il tempo, facendo più tentativi e usando nuove tecniche (per esempio il deep learning), gli algoritmi di ML **possono superare gli umani… ma fino a un LIMITE!**


![](imgs/Pasted%20image%2020261125015423.png)

> ⚠️ **Inseguire sempre lo 0% di errori può essere un errore!**

#### **1.2. L’errore Bayes-ottimo**

**Ogni volta che le distribuzioni delle classi non sono separate, anche il miglior classificatore produce un errore legato alle sovrapposizioni.**


![](imgs/Pasted%20image%2020261125015433.png)

#### **1.3. Il classificatore Bayes-ottimo: l’intuizione**

Due ipotesi: $h_0$ = l’input è un **avocado**, $h_1$ = l’input è un’**arancia**. Feature $D=$ **Rosso − Verde** (colore), misurata sul frutto. Quando osserviamo $D$, rispondiamo istintivamente «**arancia**» perché **la maggior parte dei campioni di training con un $D$ simile sono arance**:

$$P(C=\text{arancia}\mid D)>P(C=\text{avocado}\mid D).$$


![](imgs/Pasted%20image%2020261125015442.png)

#### **1.4. Il classificatore Bayes-ottimo: la classe d’uscita**

$$\hat{y}=\arg\max_{C_k}P(C_k\mid X),$$

con $\hat{y}$ la classe prevista, $C_k$ la classe $k$, $X$ il vettore delle feature, $P(C_k\mid X)$ la **probabilità a posteriori** di $C_k$ dato $X$. È definita come **la classe con la probabilità a posteriori più alta**.

**Esempio** (una feature $D$):

$$y=\arg\max_cP(C=c\mid D)=\arg\max\{P(C=\text{Avocado}\mid D),\,P(C=\text{Arancia}\mid D)\}=\arg\max\{0{,}3;\ 0{,}7\}=\text{Arancia}.$$


![](imgs/Pasted%20image%2020261125015505.png)

#### **1.5. La classificazione Bayes-ottima con il teorema di Bayes**

Per classificare un dato descritto dal vettore delle feature $X=(x_1,x_2,\dots,x_n)$ si sceglie la classe con la **probabilità a posteriori più alta**: $\hat{y}=\arg\max_{C_k}P(C_k\mid X)$. Con il **teorema di Bayes**:

$$P(C_k\mid X)=\frac{P(X\mid C_k)\,P(C_k)}{P(X)}.$$

Poiché $P(X)$ **è lo stesso per tutte le classi**, non influisce sulla massimizzazione:

$$\hat{y}=\arg\max_{C_k}P(X\mid C_k)\,P(C_k),$$

con $P(X\mid C_k)$ la **verosimiglianza** (*likelihood*) di $X$ nella classe $k$ e $P(C_k)$ la **probabilità a priori** (*prior*) della classe $k$.

- $P(C_k\mid X)$: **non è nota durante l’uso**. **Conoscerla esattamente = avere il classificatore perfetto.**
- $P(X\mid C_k)$: **si stima dai dati**: in addestramento si hanno i valori $X$ di ogni classe (**difficile con molte feature**: vedi il Naive Bayes).


![](imgs/Pasted%20image%2020261125015535.png)

> 💡 **Un esempio numerico (nota didattica).** Se gli avocado sono il 40% dei frutti ($P(C_0)=0{,}4$) e le arance il 60%, e per il $D$ osservato $p(D\mid\text{avocado})=0{,}5$ e $p(D\mid\text{arancia})=0{,}8$, i prodotti sono $0{,}4\cdot 0{,}5=0{,}20$ e $0{,}6\cdot 0{,}8=0{,}48$: si sceglie l’arancia. Normalizzando per $P(D)=0{,}20+0{,}48=0{,}68$ si ottengono le posteriori $0{,}29$ e $0{,}71$, vicine al $0{,}3/0{,}7$ della slide. La normalizzazione cambia i valori ma non il vincitore, ed è per questo che $P(X)$ si può ignorare.

#### **1.6. I limiti della classificazione Bayes-ottima**

- Per ogni $X$ il classificatore Bayes-ottimo **sceglie la classe più probabile**.
- Dove le distribuzioni delle classi **si sovrappongono**, lo stesso $X$ può provenire **da entrambe le classi**: **qualunque cosa decidiamo, alcuni campioni sono sbagliati**.
- L’errore finale è dato dall’**area di sovrapposizione** rimasta tra le distribuzioni delle classi (ombreggiata nel grafico).
- **Nessun algoritmo o dato aggiuntivo può eliminarlo**; **solo feature migliori**, che separano le classi, possono ridurlo.


![](imgs/Pasted%20image%2020261125015554.png)

> 📌 **L’errore minimo può essere strettamente maggiore di zero.** La sovrapposizione ombreggiata è l’errore che resta **anche per il miglior classificatore**.

> 💡 **L’errore di Bayes in formula (nota didattica).** Per due classi, in ogni punto $x$ il classificatore ottimo sbaglia con probabilità pari alla classe **meno** probabile. L’errore minimo complessivo è
> $$P_e^{*}=\int\min\big(P(c_0)\,p(x\mid c_0),\ P(c_1)\,p(x\mid c_1)\big)\,dx,$$
> cioè l’**area della sovrapposizione** delle due curve pesate. Se le curve non si toccano, $P_e^*=0$; se sono identiche e le classi equiprobabili, $P_e^*=0{,}5$. Un modello che su un problema così dichiara lo 0% di errore sta quasi certamente **memorizzando** il training set (Lezione 17, §2.5).

> 💡 **Perché «solo feature migliori» (nota didattica).** Più dati permettono di stimare meglio le curve, e quindi di avvicinarsi a $P_e^*$, ma non cambiano le curve stesse: la sovrapposizione è una proprietà della rappresentazione. Aggiungere una feature che distingue avocado e arance (per esempio la forma) separa le curve in uno spazio a più dimensioni e abbassa $P_e^*$. È il collegamento tra questa lezione e la feature engineering della Lezione 18.

#### **1.7. L’ipotesi «naive»**

$$\hat{y}=\arg\max_{C_k}\underbrace{P(X\mid C_k)}_{\text{termine difficile}}P(C_k).$$

Il termine difficile è $P(X\mid C_k)$, perché è la **probabilità congiunta di tutte le feature data la classe**: $P(x_1,x_2,\dots,x_n\mid C_k)$. Il **Naive Bayes** la semplifica assumendo che le feature siano **condizionatamente indipendenti data la classe**:

$$P(X\mid C_k)=\prod_{i=1}^{n}P(x_i\mid C_k).$$

Quindi il classificatore Naive Bayes diventa

$$\hat{y}=\arg\max_{C_k}P(C_k)\prod_{i=1}^{n}P(x_i\mid C_k).$$


![](imgs/Pasted%20image%2020261125015620.png)

#### **1.8. La classificazione Naive Bayes**

- Si stimano le **probabilità a priori delle classi** $P(C_k)$ e le **verosimiglianze per feature** $P(x_i\mid C_k)$ dai dati di training etichettati.
- **Ipotesi naive**: le feature sono condizionatamente indipendenti data la classe; è **un’approssimazione, non un requisito del teorema di Bayes**.
- Con un **modello di distribuzione di dimensione fissa per ogni feature**, i parametri **crescono linearmente con il numero di feature** (per classe).
- È spesso una **baseline forte in alta dimensionalità**, ma l’accuratezza dipende dai dati e dalla validità delle sue ipotesi.

> 💡 **Perché lineare e non esponenziale (nota didattica).** Stimare la congiunta di $n$ feature binarie richiede $2^n-1$ probabilità per classe; con 30 feature sono oltre un miliardo, e non ci sono mai abbastanza dati. Con l’ipotesi naive si stimano $n$ distribuzioni **monodimensionali** per classe: con feature gaussiane bastano una media e una varianza per feature e per classe, cioè $2\cdot n\cdot C$ parametri. Anche quando l’indipendenza è falsa, spesso l’`argmax` resta corretto (le probabilità sono sbagliate, l’ordine no): per questo funziona sorprendentemente bene, per esempio nei filtri antispam. Le probabilità che restituisce, invece, sono di solito mal calibrate (Lezione 18, §8.15).

> ⚠️ **Lo zero che azzera tutto (nota didattica).** Se una feature discreta non è mai stata osservata con una certa classe nel training, $P(x_i\mid C_k)=0$ e il prodotto si annulla, qualunque siano le altre feature. Si usa lo *smoothing* di Laplace (aggiungere un conteggio fittizio a ogni valore). Per stabilità numerica, inoltre, si sommano i logaritmi invece di moltiplicare molte probabilità piccole.

#### **1.9. Programmare il classificatore Naive Bayes**

```matlab
% MATLAB
% Training
net = fitcnb(X, Y);
% Prediction
out = predict(net, X_new);
```

```python
# Python (scikit-learn)
# Training
from sklearn.naive_bayes import GaussianNB
net = GaussianNB().fit(X, Y)
# Prediction
out = net.predict(X_new)
```

L’**addestramento** stima le distribuzioni condizionate alle classi dagli esempi etichettati $(X,Y)$; la **previsione** restituisce la classe più probabile per il nuovo campione `X_new`. Il codice completo sarà pubblicato più avanti sul sito del corso. `GaussianNB` assume per ogni feature e classe una gaussiana (media e varianza stimate dal training).

---

### **2. Modelli classici**


Pro e contro dei modelli standard e non neurali.

#### **2.1. I metodi classici (non neurali) sono importanti!**

- **(Alcuni) sono semplici**: rasoio di Occam.
- I metodi di apprendimento sono **molto noti** e presenti **in tutti gli strumenti e ambienti di ML**.
- Danno un **riferimento molto solido per l’accuratezza**.
- **La spiegabilità è più alta**: per esempio nei modelli di deep learning la spiegabilità è molto difficile.


![](imgs/Pasted%20image%2020261125015648.png)

#### **2.2. Esempi di metodi classici (non neurali)**

- **basati sulla distanza**: k-NN;
- **basati sugli alberi**: albero di decisione;
- **lineari/parametrici**: classificatori lineari, quadratici e logistici;
- **probabilistici / basati sulle distribuzioni**: classificatore Bayes-ottimo, Naive Bayes;
- **metodi kernel**: Support Vector Machine, Radial Basis Functions.

> 📌 **Quando preferirli.** Questi modelli si preferiscono quando **interpretabilità, semplicità o dati limitati** rendono il deep learning inadatto.

---

### **3. Apprendimento «eager» e «lazy»**


Una differenza importante da notare.

#### **3.1. I due modi di apprendere**

| **Apprendimento eager** («impaziente») | **Apprendimento basato sulle istanze** (lazy, «pigro») |
|---|---|
| **descrizione esplicita della funzione obiettivo** su tutto il training set | **apprendere = memorizzare tutte le istanze di training** |
| durante l’addestramento il sistema costruisce una **funzione obiettivo generale, indipendente dall’input** | **classificare = assegnare la funzione obiettivo a una nuova istanza** |
| | **la generalizzazione oltre i dati di training è rimandata finché non arriva una richiesta** |
| | detto apprendimento «**lazy**» |

#### **3.2. Apprendimento eager**

- Una **funzione obiettivo generale, indipendente dall’input**, viene creata durante l’addestramento.
- **Generalizzazioni e concetti** sono prodotti durante l’apprendimento.
- Qualunque input viene poi classificato **direttamente**: «**è un topo!**».


![](imgs/Pasted%20image%2020261125015705.png)

#### **3.3. Apprendimento lazy (basato sulle istanze)**

- È molto simile a una **scrivania**: «tutto» viene conservato (1: input, 2: output).
- **La generalizzazione oltre i dati di training è rimandata finché non arriva una richiesta al sistema.**


![](imgs/Pasted%20image%2020261125015720.png)

#### **3.4. Eager contro lazy: costi ed esempi**

| | Addestramento | Previsione | Esempi |
|---|---|---|---|
| **Eager** | stimare un modello: **costoso** | **veloce**; memoria = dimensione del modello | alberi di decisione, SVM, modelli lineari/logistici, reti feed-forward e CNN |
| **Lazy** (basato sulle istanze) | memorizzare e indicizzare i campioni: **economico** | **costosa**; la memoria **cresce con i dati** | k-NN, *case-based reasoning*, regressione pesata localmente |


![](imgs/Pasted%20image%2020261125015744.png)

> 💡 **Il legame con il System 1 (nota didattica).** È l’asse veloce–lento della Lezione 18 (§8.6) visto dal lato dell’addestramento: l’eager paga una volta e decide in fretta (System 1), il lazy non paga nulla prima e paga a ogni richiesta. Il k-NN è anche il caso in cui «il modello» coincide con i dati: per rilasciarlo si consegna l’intero training set, con le conseguenze di memoria e di privacy che ne derivano.

---

### **4. Modelli classici: il k-NN**


I classificatori a vicini più prossimi (*Nearest Neighbor*).

#### **4.1. Perché studiare il k-NN?**

| **Un classificatore indispensabile** | **Uno strumento per capire i dati** |
|---|---|
| non basato su tecniche neurali; **presente in ogni libreria** | **esplorare** un dataset che sta in memoria |
| **deterministico**: stessi dati, distanza e regola per i pareggi ⇒ stessa risposta | **fare il debug** dei dati e dell’accuratezza di altri classificatori, **anche neurali** |
| **pochissime scelte di progetto**: $k$ e la distanza | con sempre più dati, il k-NN può **avvicinarsi all’errore Bayes-ottimo** ($N\to\infty$, $k\to\infty$, $k/N\to 0$); un $k=1$ fisso **in generale no** |
| **decisioni tracciabili**: si possono elencare i vicini e le distanze che hanno prodotto un’etichetta | le idee (**rappresentazione, distanza, scala**) **ritornano nelle reti profonde e negli embedding** |

> 💡 **Quanto è vicino all’ottimo (nota didattica).** Un risultato classico di Cover e Hart (1967) dice che, con infiniti dati, l’errore del **1-NN** non supera il **doppio** dell’errore di Bayes: $P_e^*\le P_{1\text{NN}}\le 2\,P_e^*$ (per due classi, a meno di un termine trascurabile). Facendo crescere $k$ insieme ai dati, ma più lentamente ($k\to\infty$, $k/N\to 0$), il k-NN **converge** proprio all’errore di Bayes: è la condizione scritta nella tabella. Un $k=1$ fisso no, perché il voto di un solo vicino resta rumoroso anche con infiniti dati.

#### **4.2. I vicini più prossimi: l’intuizione dell’anatra**

**Se sembra un’anatra e si comporta come un’anatra, esempi simili possono aiutarci a classificarla.** Esempi di training memorizzati (con la loro classe): **gallina, anatra, cigno, maiale**; una **richiesta sconosciuta** (classe = ?). Si misura la **similarità con la richiesta**; si prende la **classe dei $k$ esempi più simili**.


![](imgs/Pasted%20image%2020261125015759.png)

> ⚠️ **Solo un’analogia**: per dati reali la similarità è definita da una **metrica di distanza nello spazio delle feature**, non dall’intuizione umana.

#### **4.3. Dall’intuizione alle feature: il vicino più prossimo**

Nel piano ci sono solo gli animali di training, ognuno un vettore $x_i=(x_1,x_2)$: **lunghezza e altezza in cm** (dimensioni tipiche di un adulto, arrotondate). La stella è il punto in cui cade la richiesta, $x=(55,33)$ (lunghezza 55 cm, altezza 33 cm).

| Animale | $(x_1,x_2)$ [cm] | Distanza dalla richiesta |
|---|---|---:|
| anatra | (58, 30) | $\sqrt{3^2+3^2}=4{,}2$ cm |
| gallina | (45, 40) | $\sqrt{10^2+7^2}=12{,}2$ cm |
| maiale | (150, 85) | $\sqrt{95^2+52^2}=108{,}3$ cm |
| cigno | (145, 110) | $\sqrt{90^2+77^2}=118{,}4$ cm |

**Richiesta $x=(55,33)\Rightarrow$ 1-NN = anatra.**


![](imgs/Pasted%20image%2020261125015814.png)

#### **4.4. La classificazione k-NN: la pipeline**

> 📌 **Definizione.** Il classificatore **k-nearest-neighbor (k-NN)** è un metodo **basato sulle istanze** che assegna a una richiesta la **classe più frequente tra i $k$ campioni di training più vicini**, secondo una distanza scelta.

**Richiesta** $x\in\mathbb{R}^d$ + training set $\{(x_i,y_i)\}_{i=1}^N$ → **calcolare le distanze** ($N$ valori) → **selezionare i $k$ più vicini** ($k$ indici) → **voto a maggioranza** (con le etichette $y_i$) → classe $\hat{y}$.

$N$: campioni di training; $d$: feature; $y_i$: etichetta del campione $i$; $k$: vicini (**scelta nostra**).


![](imgs/Pasted%20image%2020261125015827.png)

> 📌 **Learner pigro**: **nulla viene stimato**; gli $N$ campioni vengono conservati e i **tre blocchi vengono eseguiti di nuovo per ogni richiesta**.

#### **4.5. Stessa richiesta, $k$ diverso, classe diversa**

I **$k$ vicini più prossimi** sono i $k$ campioni di training con le **distanze più piccole dalla richiesta** ⋆. Il **cerchio tratteggiato** ha il raggio del $k$-esimo vicino: **un $k$ più grande richiede un vicinato più grande**. 12 punti illustrativi posizionati a mano.

- $k=1$: **1 cerchio blu ⇒ blu**.
- $k=3$: **2 triangoli rossi + 1 blu ⇒ rosso**.


![](imgs/Pasted%20image%2020261125015842.png)

#### **4.6. Far crescere $k$: chi entra nel voto**

| $k$ | Entra | Distanza | Blu | Rossi | Classe |
|---:|:---:|---:|---:|---:|---|
| 1 | ● | 0,67 | 1 | 0 | blu |
| 2 | ▲ | 1,00 | 1 | 1 | pareggio |
| 3 | ▲ | 1,04 | 1 | 2 | rosso |
| 4 | ● | 1,56 | 2 | 2 | pareggio |
| 5 | ● | 1,66 | 3 | 2 | blu |
| 6 | ▲ | 1,84 | 3 | 3 | pareggio |
| 7 | ▲ | 2,19 | 3 | 4 | rosso |

- $k=5$: **3 blu, 2 rossi ⇒ blu**; $k=7$: **3 blu, 4 rossi ⇒ rosso**.


![](imgs/Pasted%20image%2020261125015859.png)

Ogni passo aggiunge il **campione successivo più vicino**. La risposta **cambia con $k$**: blu, rosso, blu, rosso. **Con due classi un $k$ dispari evita i pareggi.**

> 💡 **I pareggi oltre le due classi (nota didattica).** Con tre o più classi anche un $k$ dispari può dare pareggi (per esempio 2–2–1 con $k=5$). Le regole tipiche: preferire la classe del vicino più vicino tra quelle in parità, usare il voto pesato per distanza (§4.10), o ridurre $k$ fino a rompere il pareggio. La regola fa parte della definizione del classificatore: è il motivo per cui la tabella del §4.1 parla di «stessa regola per i pareggi».

#### **4.7. La tassellazione di Voronoi e le regioni del 1-NN**

> 📌 **Definizione.** La **cella di Voronoi** di un campione di training $x_i$ è l’insieme dei punti dello spazio delle feature **più vicini a $x_i$ che a qualsiasi altro campione di training**.

**Proprietà (distanza euclidea).** Ogni lato di una cella di Voronoi sta sull’**asse** (bisettrice perpendicolare) del segmento che unisce due campioni di training vicini $x_i$ e $x_j$, cioè sull’insieme dei punti **equidistanti da entrambi**:

$$\{x:\ \|x-x_i\|=\|x-x_j\|\}.$$

Quindi **la frontiera di decisione del 1-NN è lineare a tratti**: in 2D è fatta di **segmenti di retta**, ciascuno un pezzo dell’asse tra due campioni più vicini **di classi diverse** (in $d$ dimensioni: pezzi di iperpiani).

![](imgs/Pasted%20image%2020261125015911.png)


<!-- SOURCE_VISUAL id="IS19-V18" source="L19-web.pdf" page="28" type="grafico" description="16 celle di Voronoi con la frontiera del 1-NN in tratto spesso" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

In grigio sottile: le **16 celle**, ognuna con l’etichetta del suo campione. In tratto spesso: la **frontiera del 1-NN**, una catena di segmenti di asse.

> 💡 **Perché l’asse è una retta (nota didattica).** Sviluppando $\|x-x_i\|^2=\|x-x_j\|^2$ i termini $\|x\|^2$ si cancellano e resta $2(x_j-x_i)^\top x=\|x_j\|^2-\|x_i\|^2$: un’equazione **lineare** in $x$, cioè una retta in 2D e un iperpiano in $d$ dimensioni. Le celle tra campioni della stessa classe non contribuiscono alla frontiera: la frontiera usa solo i lati tra classi diverse.

**Diagramma di Voronoi in $N$ dimensioni**: un esempio 3D.


![](imgs/Pasted%20image%2020261125015938.png)

#### **4.8. Esempi d’uso di Voronoi**

- **scienze naturali e biologia**: la tassellazione di Voronoi emerge dalla **crescita radiale da semi** verso l’esterno;
- **salute**: correlare le **sorgenti di infezione nelle epidemie**;
- **ingegneria**: volumi liberi dei polimeri;
- geometria; informatica; **civica e pianificazione**.


![](imgs/Pasted%20image%2020261125015946.png)

**Un diagramma di Voronoi degli aeroporti del mondo** proiettato su un globo 3D (Jason Davies).


![](imgs/Pasted%20image%2020261125015956.png)

> 💡 **Un uso storico (nota didattica).** Nel 1854 John Snow mappò i morti di colera a Londra e mostrò che quasi tutti vivevano più vicini a una certa pompa d’acqua di Broad Street che a qualsiasi altra: in pratica disegnò una cella di Voronoi. È l’esempio classico dell’uso «epidemie» citato dalla slide.

#### **4.9. Scegliere $k$: come cambia la frontiera di decisione**

- **$k$ piccolo**: la frontiera **segue ogni campione**, compaiono **isole**, **rumore e outlier decidono** (**overfitting**).
- **$k$ grande**: frontiera **più liscia**, ma **piccoli gruppi possono sparire** (**underfitting**).
- **Scegliere $k$ sui dati di validazione, mai sul training set.**


![](imgs/Pasted%20image%2020261125020005.png)

> 💡 **$k$ e la complessità (nota didattica).** È il dilemma bias-varianza della Lezione 17 con l’asse rovesciato: con $k=1$ la frontiera segue ogni punto (massima complessità, varianza alta); con $k=N$ il modello vota sempre la classe maggioritaria (minima complessità, bias alto). Sul training set il 1-NN ha sempre accuratezza 100% (ogni punto è il vicino di sé stesso): ecco perché $k$ non si sceglie lì.

#### **4.10. Il k-NN pesato per la distanza**

**Distanza (euclidea)**:

$$\text{dist}(x,x_i)=\sqrt{\sum_{j=1}^{d}(x_j-x_{i,j})^2},$$

con $x$ la richiesta, $x_i$ il campione di training $i$, $d$ il numero di feature, $x_j,x_{i,j}$ la loro $j$-esima feature.

**Voto pesato** tra i $k$ campioni più vicini $N_k(x)$:

$$\hat{y}=\arg\max_c\sum_{i\in N_k(x)}w_i\,[y_i=c],\qquad w_i=\frac{1}{\text{dist}(x,x_i)^p},$$

con $c$ una classe; $[y_i=c]=1$ se il campione $i$ ha classe $c$, altrimenti 0; $w_i$ il peso; $p$ l’esponente (**spesso 2, inverso del quadrato**). **Tutti i $w_i=1$: semplice voto a maggioranza.**

- **I vicini più vicini contano di più.**
- **Con i pesi, tutti i punti di training possono influenzare la risposta** (metodo di Shepard).
- Pesi $1/\text{dist}^p$ per $p=1,2,4,16$: da **partizioni lisce** ($p$ piccolo) alle **regioni di Voronoi** ($p$ grande).


![](imgs/Pasted%20image%2020261125020019.png)

> 💡 **Un esempio e un caso limite (nota didattica).** Con $k=3$, $p=2$, vicini blu a distanza 1 e rossi a distanza 2 e 2: pesi $1$ contro $\frac14+\frac14=0{,}5$, vince il blu anche se i rossi sono due. Con $p$ molto grande il vicino più vicino pesa infinitamente più degli altri, e il voto pesato diventa un 1-NN: da qui le regioni di Voronoi. Se una distanza è zero (la richiesta coincide con un campione) il peso è infinito: le implementazioni assegnano direttamente quella classe.

---

### **5. Che cosa significa «più vicino»?**


**I vicini più prossimi richiedono una definizione di similarità.** Distanza, origine e unità decidono chi è «vicino».

#### **5.1. Chi è il mio vicino più prossimo? Euclidea contro coseno**

Richiamo dalla Lezione 18: la **similarità coseno** di due vettori,

$$\cos(a,b)=\frac{a\cdot b}{\|a\|\,\|b\|},\qquad \text{dist}_{\cos}=1-\cos\in[0,2].$$

Con $q=(1,1)$, $a=(1{,}5;\ 0{,}6)$, $b=(3,3)$:

| | verso $a$ | verso $b$ | più vicino |
|---|---|---|---|
| **Euclidea** | $\sqrt{0{,}25+0{,}16}=0{,}64$ | $\sqrt{4+4}=2{,}83$ | **$a$ (rosso)** |
| **Coseno** | $2{,}1/(1{,}41\cdot 1{,}62)=0{,}92\Rightarrow\text{dist}_{\cos}=0{,}08$ | $6/(1{,}41\cdot 4{,}24)=1{,}00\Rightarrow\text{dist}_{\cos}=0{,}00$ | **$b$ (blu)** |


![](imgs/Pasted%20image%2020261125020035.png)

> 📌 **La distanza euclidea misura la posizione; il coseno misura la direzione.** $b=3q$: stessa direzione di $q$, quindi coseno 1, anche se è lontano.

#### **5.2. Il coseno dipende dall’origine**

| | prima: origine in $(0,0)$ | dopo la centratura: origine in $\mu$ |
|---|---|---|
| punti | $a=(0{,}2;\ 0{,}2)$, $b=(0{,}8;\ 0{,}8)$ | $a'=a-\mu=(-0{,}3;\ -0{,}3)$, $b'=(0{,}3;\ 0{,}3)$ |
| direzioni | **stessa direzione** ⇒ $\text{dist}_{\cos}=0$ | **direzioni opposte** ⇒ $\text{dist}_{\cos}=2$ |
| euclidea | $\|a-b\|=0{,}85$ | $0{,}85$ (invariata) |

con la media di training $\mu=(0{,}5;\ 0{,}5)$.


![](imgs/Pasted%20image%2020261125020047.png)

- **Il coseno non è invariante per traslazione**: **una nuova origine cambia ogni angolo**.
- **La centratura trasforma la similarità massima in minima** per gli stessi due punti.
- **Stimare $\mu$ solo sul training set.**

> 💡 **Il conto (nota didattica).** $\|a-b\|=\sqrt{0{,}6^2+0{,}6^2}=0{,}849$ in entrambi i casi: la distanza euclidea è invariante per traslazione perché dipende solo dalla differenza $a-b$, in cui $\mu$ si cancella. Il coseno invece usa i vettori stessi, che cambiano quando si sposta l’origine.

#### **5.3. Stessi dati, tre definizioni di «vicino»**

Euclidea; coseno grezzo; coseno dopo la centratura. **Stessi 36 punti etichettati, stesso $k=1$.** Le regioni del coseno sono **spicchi a partire dall’origine** (+): grezza a sinistra, nella media di training $\mu$ dopo la centratura. **Nessuna delle tre è «migliore» in generale**: ognuna definisce una **nozione diversa di vicino**, e **solo i dati di validazione possono dire quale si adatta al compito**. Dati sintetici (seme fisso): solo geometria, nessuna misura reale.


![](imgs/Pasted%20image%2020261125020057.png)

> 💡 **Perché spicchi (nota didattica).** Il coseno ignora la lunghezza dei vettori: tutti i punti su una semiretta uscente dall’origine hanno lo stesso coseno con qualsiasi altro punto. Le regioni di decisione sono quindi unioni di settori angolari con il vertice nell’origine, e spostando l’origine (centratura) si sposta il vertice di tutti gli spicchi.

#### **5.4. Le unità possono decidere la distanza**

| Paziente | altezza $x_1$ [m] | peso $x_2$ [kg] | glicemia $x_3$ [mg/dL] | diabetico |
|---|---:|---:|---:|---|
| #1 | 1,62 | 58 | 85 | no |
| #2 | 1,75 | 72 | 92 | no |
| #3 | 1,80 | 95 | 135 | sì |
| #4 | 1,58 | 70 | 128 | sì |
| #5 | 1,68 | 64 | 88 | no |
| #6 | 1,85 | 88 | 99 | no |
| #7 | 1,72 | 102 | 162 | sì |
| #8 | 1,60 | 55 | 81 | no |
| #9 | 1,77 | 91 | 141 | sì |
| #10 | 1,66 | 80 | 118 | no |
| **intervallo** | 1,58–1,85 | 55–102 | 81–162 | |

$x_3$: glicemia a digiuno. **Valori sintetici per la didattica, non dati clinici.**

- **Sembra tutto a posto!** Tre feature plausibili, dieci pazienti, un’etichetta chiara. **Ma guardiamo che cosa vede il k-NN** (slide successiva).
- **Scalare non rende il k-NN «giusto»: decide quale feature conta.** Per il diabete la glicemia dovrebbe probabilmente pesare più dell’altezza: **scegliere feature e scala, poi validare**.

<!-- SOURCE_VISUAL id="IS19-V27" source="L19-web.pdf" page="38" type="figura" description="Tabella dei dieci pazienti sintetici con altezza, peso e glicemia" reason="Grafica della tabella visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.5. Che cosa «vede» la distanza euclidea**


![](imgs/Pasted%20image%2020261125020140.png)

- **A sinistra**: stessa lunghezza unitaria sui due assi, **come nella distanza**. L’altezza varia di **0,5**, la glicemia di **più di 100**: **i vicini sono scelti dalla sola glicemia (e dal peso)**; tutte le altezze (1,4–2,0 m) stanno in una linea sottile.
- **A destra**: dopo la **normalizzazione min-max**.
- **Nota**: centrare e poi applicare il min-max dà **lo stesso risultato del solo min-max** (il min-max rimuove di nuovo lo spostamento). Per tenere l’origine nella media si usa lo **z-score** (Lezione 16). Dati sintetici (seme fisso).

> 💡 **Il conto (nota didattica).** Tra i pazienti #2 e #6 la differenza di altezza è 0,10 m e quella di glicemia 7 mg/dL: nella distanza euclidea l’altezza contribuisce $0{,}01$, la glicemia $49$, cioè quasi 5000 volte di più. Dopo il min-max ogni feature varia in $[0,1]$: l’altezza diventa $0{,}10/0{,}27=0{,}37$, la glicemia $7/81=0{,}09$, e ora pesa di più l’altezza. Nessuna delle due versioni è «giusta» per il diabete: è il senso della frase «scalare decide quale feature conta».

#### **5.6. Le immagini: il k-NN lavora sugli embedding**

Richiamo dalla Lezione 18: un **embedding** è il vettore $z\in\mathbb{R}^d$ calcolato da una **rete pre-addestrata**; contenuti simili danno vettori vicini.

Immagine $I(x,y)$ → **encoder di immagini** $f_\theta$ (pre-addestrato) → $z\in\mathbb{R}^d$ → **normalizzazione $\ell_2$** → $\hat{z}$ → **k-NN** con distanza coseno (sugli embedding etichettati di training) → classe; in parallelo, **PCA in 2D** → grafico 2D (**solo visualizzazione**).


![](imgs/Pasted%20image%2020261125020149.png)

Dopo la **normalizzazione $\ell_2$** ($\|\hat{z}\|=1$) **coseno e distanza euclidea danno gli stessi vicini**. **Il voto avviene in $\mathbb{R}^d$; la mappa PCA mostra soltanto i dati.** Misurato su 40 foto reali nei passi successivi.

#### **5.7. Il dataset: 40 foto di gatti e cani**

- **Input**: $N=40$ foto, ● **20 gatti**, ▲ **20 cani**, di qualsiasi dimensione e posa. Ogni foto → **ritaglio centrale 224×224 RGB** → encoder $f_\theta$ (**EmbeddingGemma 2**, Ollama, 768 dimensioni).
- **Output**: **un embedding $z_i\in\mathbb{R}^{768}$ per foto**, quindi una matrice $Z\in\mathbb{R}^{40\times 768}$. Le etichette si usano **solo per i colori e per il punteggio**.

Foto: Wikimedia Commons (autori e licenze in `fonti-wikimedia-commons.md`).


![](imgs/Pasted%20image%2020261125020159.png)

#### **5.8. I 40 embedding visti da PCA e t-SNE**

- **PCA**: lineare; **2 componenti tengono il 90% della varianza**; le foto nuove si possono proiettare. Isolata: **la foto meno tipica**.
- **t-SNE** (perplexity 10): **conserva solo i vicinati**: spazi vuoti, dimensioni dei gruppi e assi **non hanno significato**.


![](imgs/Pasted%20image%2020261125020307.png)

#### **5.9. Il coseno sugli embedding reali: attenzione all’origine**

| | **coseno grezzo** $\cos(\hat{z}_i,\hat{z}_j)$ ($\|\hat{z}\|=1$ per ogni foto) | **dopo la centratura sulla media** $\hat{z}_i-\bar{z}$ |
|---|---|---|
| intervallo | **tutti i valori tra 0,967 e 1** | — |
| media entro una classe | **0,998** | **0,87** |
| media tra classi | **0,970** | **−0,88** |
| lettura | tutto è «simile» | **i due gruppi ora sono lontani** |


![](imgs/Pasted%20image%2020261125020315.png)

Stessa scala di colori: **i coseni grezzi sono tutti ≈ 1** (tutto rosso); **spostare l’origine nella media li allarga**. Embedding: EmbeddingGemma 2 (Ollama, 768 dimensioni), calcolati dall’autore.

> 💡 **Perché tutti ≈ 1 (nota didattica).** Gli embedding di un encoder non sono sparsi uniformemente nello spazio: occupano un cono stretto attorno a una direzione comune (l’«anisotropia» degli embedding). Tutti i vettori puntano più o meno dalla stessa parte, e il coseno tra due foto qualsiasi è vicino a 1. Sottraendo la media si toglie la direzione comune e restano le differenze: il coseno ritrova tutto il suo intervallo $[-1,1]$. È l’esempio reale del §5.2.

#### **5.10. Norma $\ell_2$, coseno e k-NN sulle 40 foto**

Per **vettori unitari** ($\|\hat{z}\|=1$):

$$\|\hat{z}_a-\hat{z}_b\|^2=2\big(1-\cos(\hat{z}_a,\hat{z}_b)\big),$$

quindi **euclidea e coseno ordinano i vicini nello stesso ordine**.

- $a$, $b$ (gatto, gatto): $\cos=0{,}999\Rightarrow\sqrt{2(1-0{,}999)}=0{,}04$.
- $a$, $c$ (gatto, cane): $\cos=0{,}973\Rightarrow\sqrt{2(1-0{,}973)}=0{,}23$.

| Distanza usata | accuratezza $k=1$ | accuratezza $k=5$ |
|---|---:|---:|
| euclidea, $\hat{z}$ | 100% | 100% |
| coseno, $\hat{z}$ | 100% | 100% |
| coseno dopo la centratura | 100% | 100% |
| euclidea, PCA 2D (visualizzazione) | 100% | 100% |

**Come si calcola l’accuratezza (leave-one-out)**:

1. si toglie una foto: è la **richiesta**;
2. si trovano i suoi $k$ più vicini **tra le altre 39** (training set);
3. voto a maggioranza: classe prevista contro classe vera;
4. si ripete per tutte e 40: accuratezza = corrette / 40.

**La richiesta non è mai nel proprio training set: non è accuratezza di training.**

> 💡 **La dimostrazione in una riga (nota didattica).** $\|a-b\|^2=\|a\|^2+\|b\|^2-2a\cdot b=1+1-2\cos(a,b)$ per vettori unitari. La distanza euclidea è una funzione crescente di $1-\cos$: chi ha il coseno più alto ha anche la distanza più piccola. Il leave-one-out è la validazione LOO della Lezione 14 (§6.5): con soli 40 campioni usa al massimo i dati, e il 100% su 40 dice che, con la regola del 3, l’errore vero è probabilmente sotto il 7,5% (Tecniche biometriche, Lezione 9).

#### **5.11. Stessa tecnica, dati industriali: scatole di cartone**

- **Input**: $N=40$ foto di **scatole appiattite su una linea di produzione**, ● **20 integre**, ▲ **20 difettose** (strappate, piegate, disallineate).
- **Stessa pipeline, nessuna modifica**: encoder $f_\theta$ (EmbeddingGemma 2, Ollama, 768 dimensioni) → $z_i\in\mathbb{R}^{768}$ → PCA, t-SNE, k-NN. **Nessuna rete è stata addestrata sulle scatole.**


![](imgs/Pasted%20image%2020261125020347.png)

#### **5.12. Perché è difficile: integra o difettosa?**

- **Stesso modello di scatola, stessa stampa, stesso nastro trasportatore.**
- I difetti (**strappi, un angolo sollevato, un lembo disallineato**) coprono **una piccola parte della foto**.
- **La maggior parte dei pixel è sfondo**: nastri, rulli, riflessi, altre scatole.
- **Confrontare i pixel significherebbe confrontare gli sfondi**: serve una **rappresentazione del contenuto**, l’**embedding**.


![](imgs/Pasted%20image%2020261125020354.png)

#### **5.13. Scatole: PCA e t-SNE degli embedding**


![](imgs/Pasted%20image%2020261125020400.png)

**k-NN leave-one-out con distanza coseno: 100% ($k=1$), 100% ($k=5$).** Coseno medio **0,998 entro una classe, 0,990 tra classi**; dopo la centratura **0,69 e −0,71**. **Le stesse poche righe di codice che separavano gatti e cani ora separano le scatole buone da quelle difettose.**

> ⚠️ **Un 100% da leggere con cautela (nota didattica).** 40 foto scattate nelle stesse condizioni non sono una linea di produzione: altri lotti, altra illuminazione o un nuovo tipo di difetto possono spostare gli embedding (lo shift della Lezione 18, §8.15). Il risultato dimostra che la pipeline è promettente, non che è pronta: servono più dati, una validazione su giorni diversi e il monitoraggio del drift (Lezione 11).

#### **5.14. La similarità va progettata, non presupposta**

**Dati grezzi** → **rappresentazione** → **pre-elaborazione** → **distanza** → **voto k-NN** → $\hat{y}$: **l’errore di validazione decide ogni scelta**.


![](imgs/Pasted%20image%2020261125020411.png)

| Distanza | Considera simili | Ignora |
|---|---|---|
| **euclidea** | coordinate vicine | nulla (ma **le unità contano**) |
| **coseno** | vettori con la **stessa direzione** | la **lunghezza** (modulo) |
| **coseno dopo la centratura** | stessa direzione **dalla media di training** | la **distanza dalla media** |

> 📌 **Da portare a casa.** Il k-NN non ha quasi parametri, eppure la sua risposta dipende da **quattro scelte di progetto**. Una nozione di similarità significativa **va progettata e validata, non presupposta**.

> 📌 **Il bias induttivo del k-NN (nota didattica, dall’edizione precedente).** Ogni k-NN incorpora tre scelte: **le feature** (si assume che **tutte siano ugualmente importanti**: che cosa succede se si moltiplica una feature per 100?), **la funzione di distanza** (euclidea, coseno, gaussiana…) e **$k$**. È la versione compatta della tabella precedente.

#### **5.15. Questioni pratiche nell’uso del k-NN**

- **(+) Robusto alle etichette rumorose**: il voto fa la media di $k$ vicini.
- **(−) Velocità e memoria**: **tutti gli $N$ campioni vengono conservati e scanditi per ogni richiesta**.
- **(−) Maledizione della dimensionalità**: con molte feature (irrilevanti), **tutte le distanze diventano simili** e «più vicino» perde significato.
- **Rimedi**: selezione o estrazione delle feature (Lezione 18); la maledizione è studiata in dettaglio con le reti neurali (Lezione 20).


![](imgs/Pasted%20image%2020261125020422.png)

> 💡 **La maledizione della dimensionalità in numeri (nota didattica).** In un ipercubo unitario a $d$ dimensioni con dati uniformi, per catturare il 10% dei punti serve un cubetto di lato $0{,}1^{1/d}$: in 2D il lato è circa 0,32, ma in 10D è circa **0,79**, quasi tutto il cubo. I «vicini» non sono più vicini, e le distanze tra punti diversi diventano quasi tutte simili. È il motivo per cui il k-NN funziona bene con poche feature ben scelte (o con embedding) e male con centinaia di feature grezze.

#### **5.16. Questioni pratiche: la velocità**

**Tempo richiesto dal k-NN per $N$ punti di $D$ dimensioni**:

- **tempo per calcolare le distanze**: $O(ND)$;
- **tempo per trovare i $k$ più vicini**:
  - $O(kN)$: minimi ripetuti;
  - $O(N\log N)$: ordinamento;
  - $O(N+k\log N)$: *min-heap*;
  - $O(N+k\log k)$: mediana veloce;
- **il tempo totale è dominato dal calcolo delle distanze**;
- **si può essere più veloci se si accetta di sacrificare l’esattezza.**

<!-- SOURCE_VISUAL id="IS19-V38" source="L19-web.pdf" page="50" type="figura" description="Illustrazione del costo computazionale del k-NN" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Il conto per un’applicazione (nota didattica).** Con un milione di immagini descritte da 512 feature, ogni classificazione richiede circa $10^6\times 512\approx 5\times 10^8$ operazioni solo per le distanze, contro al più $10^6\log_2 10^6\approx 2\times 10^7$ per l’ordinamento: le distanze dominano. È l’altra faccia del lazy learning (§3.4): addestramento istantaneo, uso costoso. «Sacrificare l’esattezza» significa usare strutture come i k-d tree o gli indici approssimati (*approximate nearest neighbor*) dei database vettoriali (Lezione 6, §6.3; Lezione 15, RAG), che trovano vicini quasi certamente giusti in tempo sublineare.

---

### **6. Strumenti: programmare il k-NN in MATLAB**


#### **6.1. Il k-NN in MATLAB**

```matlab
load fisheriris
X = meas;
Y = species;
% X: 150 iris x 4 misure (lunghezza/larghezza di sepalo e petalo; cm)
% Y: i 150 nomi delle specie (le etichette)
% Addestrare un classificatore 5-nearest neighbor.
% 'NumNeighbors',5 -> k = 5 vicini nel voto
% 'Standardize',1  -> 1 = vero: prima di calcolare le
%                     distanze, ogni feature viene trasformata con lo z-score usando
%                     media e deviazione standard dei dati di TRAINING (salvate nel
%                     modello e riusate da predict); 0 = unità grezze (default)
kNN_model = fitcknn(X,Y,'NumNeighbors',5,'Standardize',1)

% Classificare un iris
x = X(1,:)                     % 5.1 3.5 1.4 0.2
label = predict(kNN_model,x)   % {'setosa'}
```

**z-score**: $z_j=(x_j-\mu_j)/\sigma_j$ per feature, per esempio $(5{,}1-5{,}84)/0{,}83\approx -0{,}89$. **Output: setosa.**

> 💡 **Perché standardizzare qui (nota didattica).** Le quattro misure sono tutte in cm, ma con intervalli diversi: la larghezza del petalo varia tra 0,1 e 2,5, la lunghezza del petalo tra 1 e 6,9. Senza standardizzazione la lunghezza del petalo peserebbe circa tre volte di più. È il caso del §5.4 in piccolo. Il primo fiore viene classificato correttamente, ma è anche nel training set: è il suo stesso vicino più prossimo.

#### **6.2. Cambiare i fold**

```matlab
% Un test di cross-validation con fold diversi, da 10 a 3
% K = 10
fprintf('KFOLD Validation with K = %d --> Error = %f \n', 10, kfoldLoss(cvmdl_results));
% K = 9...3
for K = [9:-1:3]
    cvmdl_results = crossval(kNN_model, 'KFold',K);
    fprintf('KFOLD Validation with K = %d --> Error = %f \n', K, kfoldLoss(cvmdl_results));
end
```

| $K$ (fold) | 10 | 9 | 8 | 7 | 6 | 5 | 4 | 3 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|
| errore stampato | 0,0533 | 0,0467 | 0,0533 | 0,0467 | 0,0533 | 0,0600 | 0,0467 | 0,0467 |

> ⚠️ **Attenzione**: questa è la **$K$ della validazione incrociata**, **non il $k=5$ del k-NN!** Meno fold usano **partizioni di training più piccole**, ma l’errore di CV stimato **non deve per forza crescere in modo monotono** a causa della **variabilità di campionamento**.

> 💡 **Leggere la tabella (nota didattica).** Su 150 fiori, 0,0467 = 7 errori, 0,0533 = 8 errori, 0,0600 = 9 errori: tutte le differenze sono di uno o due fiori, dentro il rumore della stima. Ogni chiamata a `crossval` sceglie una **nuova partizione casuale**, quindi rieseguendo lo script i valori cambiano. Per confrontare $K$ diversi in modo serio servirebbe ripetere più volte e confrontare medie e deviazioni standard (Lezione 14, §6.9).

#### **6.3. La validazione incrociata di un modello già addestrato (appendice A2)**

```matlab
% Esempio: come applicare la cross-validation a un modello
% già addestrato (kNN_model, 5-NN sui 150 iris).
% crossval riaddestra 10 sotto-classificatori, ciascuno su
% 9/10 dei dati, e lo testa sul restante 1/10
cvmdl_results = crossval(kNN_model, 'KFold',10)
% errore medio di classificazione sui 10 fold di test
kfoldLoss(cvmdl_results)   % ans = 0.0533
```

La 10-fold CV del 5-NN sui 150 iris dà un **errore di classificazione di 0,0533** (≈ **8 errori su 150**).

> 💡 **Lo script della cartella (nota didattica).** `Lesson_19_laboratory_MATLAB_Knn.m` contiene gli stessi passi (caricamento, `plotmatrix(X)`, `fitcknn`, `predict`, `crossval` con 10 fold e il ciclo da 9 a 3). Due commenti dello script sono datati: la standardizzazione è accompagnata da «vedi la lezione sulla codifica delle uscite», ma riguarda gli **input**; e il commento iniziale rimanda al codice LDA della «Lezione 14», che in questa edizione si trova nelle Lezioni 16 e 17.

#### **6.4. Modelli classici in codice: MATLAB contro scikit-learn (appendice A3–A4)**

**Regressione polinomiale e classificatore lineare**:

```matlab
% MATLAB
% Fit polinomiale (grado 3), univariato
p = polyfit(x, y, 3);
y_new = polyval(p, x_new);

% Fit polinomiale (grado 3), 2 variabili
mdl = fitlm(X, y, 'poly3');
y_new = predict(mdl, x_new);

% Classificatore lineare
mdl = fitclinear(X, y);
y_new = predict(mdl, x_new);
```

```python
# Python (scikit-learn)
# Fit polinomiale (grado 3), univariato
p = numpy.polyfit(x, y, 3)
y_new = numpy.polyval(p, x_new)

# Fit polinomiale (grado 3), 2 variabili
from sklearn.preprocessing import PolynomialFeatures
from sklearn.linear_model import LinearRegression
poly = PolynomialFeatures(degree=3)
X_poly = poly.fit_transform(X)
mdl = LinearRegression().fit(X_poly, y)
x_new_poly = poly.transform([x_new])
y_new = mdl.predict(x_new_poly)

# Classificatore lineare
from sklearn.linear_model import LogisticRegression
mdl = LogisticRegression().fit(X, y)
y_new = mdl.predict([x_new])
```

**k-NN, albero di decisione, Naive Bayes**:

```matlab
% MATLAB
% Classificatore kNN
mdl = fitcknn(X, y, ...
              'NumNeighbors', 5);
y_new = predict(mdl, x_new);

% Albero di decisione
mdl = fitctree(X, y);
y_new = predict(mdl, x_new);

% Classificatore Naive Bayes
mdl = fitcnb(X, y);
y_new = predict(mdl, x_new);
```

```python
# Python (scikit-learn)
# Classificatore kNN
from sklearn.neighbors import KNeighborsClassifier
mdl = KNeighborsClassifier(n_neighbors=5).fit(X, y)
y_new = mdl.predict([x_new])

# Albero di decisione
from sklearn.tree import DecisionTreeClassifier
mdl = DecisionTreeClassifier().fit(X, y)
y_new = mdl.predict([x_new])

# Classificatore Naive Bayes
from sklearn.naive_bayes import GaussianNB
mdl = GaussianNB().fit(X, y)
y_new = mdl.predict([x_new])
```

In Python, `x_new = np.array([[1.5, 2.7]])` come nella tabella di origine: **un campione con due feature**.

> ⚠️ **Le parentesi di `x_new` (nota didattica).** scikit-learn vuole sempre una matrice campioni × feature. Se `x_new` è già `np.array([[1.5, 2.7]])` (forma $1\times 2$), scrivere `mdl.predict([x_new])` aggiunge una terza dimensione e dà errore: va passato `mdl.predict(x_new)`. La forma `[x_new]` è giusta solo se `x_new` è un vettore piatto, per esempio `np.array([1.5, 2.7])`. Il `fitlm(X, y, 'poly3')` di MATLAB e `PolynomialFeatures(degree=3)` creano gli stessi termini (fino al grado 3 con le interazioni), come ricordato nella Lezione 20.

---

### **7. Clustering: k-means**


Un metodo non supervisionato.

#### **7.1. Immaginiamo questi incarichi**

Si riceve un dataset **non supervisionato**, per esempio su:

- **dati dei clienti**, e si chiede di provare a definire **nuovi tipi di clienti**;
- **segnali e vibrazioni di un motore**, e si chiede di scoprire se ci sono **stati di guasto** nel sistema;
- **foto di prodotti da una linea di produzione**, per trovare **gruppi di difetti simili**.

**Quale può essere una buona tecnica per affrontare il problema? Il clustering è un’ottima risposta!**

#### **7.2. La logica del clustering**

Nelle applicazioni è **molto raro** riuscire a identificare i cluster nei dati **scrivendo regole a mano**, perché:

- la **dimensionalità del problema è molto alta**;
- **non si è (ancora) esperti del campo** (per esempio l’espressione genica in medicina, un parametro complesso in finanza);
- la **quantità di dati è enorme**;
- …

**Serve un metodo automatico!** (Con poche dimensioni i gruppi si vedono; con molte dimensioni: ???)


![](imgs/Pasted%20image%2020261125020541.png)

#### **7.3. Il clustering: definizione**

> 📌 **Definizione.** L’**analisi dei cluster** (clustering) è il compito di **raggruppare un insieme di oggetti** in modo che gli oggetti dello **stesso gruppo** (detto **cluster**) siano **più simili** (posizione, feature, forme…) **tra loro** che a quelli degli **altri gruppi** (cluster).


![](imgs/Pasted%20image%2020261125020553.png)

La **Gestalt umana** percepisce i gruppi a colpo d’occhio (Lezione 2).

**Fare gruppi di feature simili** (volti): bambino/adulto? Maschio/femmina? Felice/neutro/triste? Capelli lunghi/calvo? Barba/rasato? Occhiali da sole/occhiali/nessuno? … **Bisogna estrarre gruppi da un dataset di feature non supervisionato.**


![](imgs/Pasted%20image%2020261125020603.png)

> ⚠️ **Lo stesso insieme, più raggruppamenti.** Gli stessi volti si possono raggruppare per età, per espressione o per occhiali: non esiste un clustering «giusto» in assoluto, ma solo uno utile per un compito. Le feature scelte decidono quale raggruppamento emerge (§7.12).

#### **7.4. Perché fare clustering?**

- **Riassumere i dati**:
  - guardare grandi quantità di dati;
  - **compressione o denoising a patch**: guardare i centroidi rispetto all’intero dataset;
  - rappresentare un **grande vettore continuo con il numero del cluster**: i dati sono così densi che si vedono le transizioni.
- **Contare**: istogrammi di tessitura, colore, vettori SIFT.
- **Segmentazione**: separare l’immagine in regioni diverse (Lezione 23).
- **Previsione**: immagini nello stesso cluster possono avere le stesse etichette.

> 💡 **La quantizzazione vettoriale (nota didattica).** «Rappresentare un vettore con il numero del cluster» è la **quantizzazione vettoriale**: con 256 cluster di colori, ogni pixel RGB (3 byte) diventa un indice di 1 byte, e l’immagine si comprime di tre volte perdendo poco (è il principio delle immagini a tavolozza). Gli istogrammi di vettori SIFT («*bag of visual words*») contano quante volte ogni cluster di descrittori compare in un’immagine.

#### **7.5. Il k-means passo per passo (1/2)**

**Input**: $N=60$ punti 2D **non etichettati**, il numero di cluster $k=3$.

- **Passo 1**: si collocano $k$ centri × **a caso**.
- **Passo 2**: **ogni punto si unisce al centro più vicino** (euclideo); le regioni sono le **celle di Voronoi dei centri**.

Colori e forme sono **indici di cluster, non etichette di classe**.


![](imgs/Pasted%20image%2020261125020615.png)

#### **7.6. Il k-means passo per passo (2/2): iterare finché nulla cambia**

Partenza: centri casuali; iterazione 1: assegnazione + aggiornamento; **iterazione 2: 3 punti cambiano cluster**; it. 3: 4 punti; it. 4: 8 punti; it. 5: 12 punti; it. 6: 4 punti; **it. 7: nessun cambiamento → stop**.


![](imgs/Pasted%20image%2020261125020627.png)

- **Passo 3**: si sposta ogni centro nella **media dei suoi punti** (frecce).
- **Passo 4**: si ripetono i passi 2–3.
- **Ci si ferma quando un’iterazione non cambia alcuna assegnazione**: i centri delle iterazioni 6 e 7 coincidono. **Output**: $k$ centri, **un indice di cluster per punto**.

#### **7.7. Il clustering con il k-means: l’obiettivo**

Date $n$ osservazioni $(x_1,x_2,\dots,x_n)$, il clustering k-means mira a dividerle in $k$ ($\le n$) insiemi $S=\{S_1,S_2,\dots,S_k\}$ in modo da **minimizzare la somma dei quadrati entro i cluster**. In altre parole, il suo obiettivo è trovare

$$\arg\min_S\sum_{i=1}^{k}\sum_{x\in S_i}\|x-\mu_i\|^2,$$

dove $\mu_i$ è il **centro del cluster** (media dei punti di $S_i$), $\|\cdot\|$ la norma euclidea, $k$ il numero di cluster.

**Esempio**: $S_1=\{1,3\}$ dà $\mu_1=2$ e costo $(1-2)^2+(3-2)^2=2$. **È facile calcolare $\mu$ dato $S$, e viceversa.**

> 💡 **Perché «facile in un verso e nell’altro» (nota didattica).** Con le assegnazioni $S$ fissate, il centro che minimizza $\sum_{x\in S_i}\|x-\mu\|^2$ è la media (basta derivare e porre a zero). Con i centri fissati, l’assegnazione migliore manda ogni punto al centro più vicino. Il k-means alterna i due problemi facili invece di risolvere quello difficile (trovare insieme $S$ e $\mu$, un problema NP-difficile).

#### **7.8. Il k-means: l’algoritmo di partizione**

1. Decidere un valore per $k$.
2. Inizializzare i $k$ centri dei cluster (**a caso**, se necessario).
3. Assegnare ognuno degli $N$ oggetti al **centro più vicino**.
4. Ristimare i $k$ centri come le **medie dei loro membri**.
5. Se **nessun oggetto ha cambiato appartenenza** nell’ultima iterazione, fermarsi; altrimenti tornare al passo 3.


![](imgs/Pasted%20image%2020261125020637.png)

> 📌 **Convergenza.** Ogni passo 3 e ogni passo 4 possono **solo abbassare (o mantenere) l’obiettivo** della fase precedente: **l’algoritmo si ferma sempre, ma in un minimo locale**.

> 💡 **Un’iterazione a mano (nota didattica).** Punti 1D: 1, 2, 3, 10, 11, 12, con $k=2$ e centri iniziali 1 e 2.
> - **Assegnazione**: {1} al centro 1; {2, 3, 10, 11, 12} al centro 2.
> - **Aggiornamento**: $\mu_1=1$, $\mu_2=(2+3+10+11+12)/5=7{,}6$.
> - **Assegnazione**: {1, 2, 3} sono più vicini a 1, {10, 11, 12} a 7,6.
> - **Aggiornamento**: $\mu_1=2$, $\mu_2=11$.
> - Nessun punto cambia gruppo al giro successivo: **uscita**, con i due cluster naturali. L’obiettivo passa da $0+[(2-7{,}6)^2+(3-7{,}6)^2+(10-7{,}6)^2+(11-7{,}6)^2+(12-7{,}6)^2]=89{,}2$ dopo il primo aggiornamento a $2+2=4$ alla fine.

#### **7.9. Il k-means converge a un minimo locale**

**Inizializzazioni casuali diverse possono finire in un minimo locale**; il minimo globale «**dovrebbe essere qui!**».


![](imgs/Pasted%20image%2020261125020649.png)

> 💡 **Il rimedio pratico (nota didattica).** Si esegue il k-means **più volte** con inizializzazioni diverse e si tiene la soluzione con la somma dei quadrati più bassa; l’inizializzazione **k-means++** sceglie i centri iniziali distanti tra loro (ogni nuovo centro con probabilità proporzionale al quadrato della distanza dal centro più vicino già scelto) e riduce i risultati cattivi. In scikit-learn il parametro `n_init` fa proprio questo, e `init="k-means++"` è il default.

**I problemi del k-means con gli outlier**: un punto lontano sposta la media del suo cluster.


![](imgs/Pasted%20image%2020261125020658.png)

#### **7.10. Punti di forza e debolezze del k-means**

| **Punti di forza** | **Debolezze** |
|---|---|
| **efficiente**: $O(tNkd)$ per $t$ iterazioni, $N$ punti, $k$ cluster, $d$ feature | **serve una media**: non per dati categorici («mela», «limone») |
| **converge sempre** (a una partizione stazionaria) | **$k$ va fissato in anticipo** |
| **nuovi punti**: il centro appreso più vicino | **sensibile agli outlier** (la media si sposta) |
| | **fallisce su forme non convesse** |
| | **funziona meglio su gruppi compatti, all’incirca sferici** |


![](imgs/Pasted%20image%2020261125020706.png)

> ⚠️ **Cluster ellittici (nota didattica).** Il k-means standard usa la distanza euclidea dal centro e tende a produrre cluster sferici e di dimensioni simili; per cluster ellittici o di dimensioni molto diverse funziona male. Servono varianti, come la distanza di Mahalanobis (Lezione 8) o i modelli a misture gaussiane (GMM). Per i dati categorici esiste il *k-modes*, che usa la moda al posto della media. Per le forme non convesse (due mezzelune, anelli concentrici) si usano metodi basati sulla densità come DBSCAN.

**Comportamento diverso dei metodi di clustering**: la stessa nuvola di dati divisa in modo diverso da algoritmi diversi.


![](imgs/Pasted%20image%2020261125020715.png)

#### **7.11. Clustering esclusivo e sovrapposto**

- **Clustering esclusivo**: ogni dato appartiene **esattamente a un cluster**, senza ambiguità (per esempio il k-means); partizioni **distinte e non sovrapposte**.
- **Clustering sovrapposto**: i dati possono appartenere **a più cluster contemporaneamente** (per esempio **Fuzzy C-Means**), rispecchiando scenari reali in cui le categorie si intersecano.
- **Distinzione chiave**: il clustering esclusivo impone un confine «**duro**», quello sovrapposto permette un’appartenenza «**morbida**» o condivisa.
- **Si sceglie rispetto alla propria applicazione.**


![](imgs/Pasted%20image%2020261125020722.png)

> 💡 **Il Fuzzy C-Means (nota didattica).** Ogni punto $x_i$ ha un grado di appartenenza $u_{ij}\in[0,1]$ a ogni cluster $j$, con $\sum_ju_{ij}=1$; i centri sono medie pesate da $u_{ij}^m$ ($m>1$ regola quanto sono «morbidi» i confini). Un punto a metà tra due cluster ha appartenenze $0{,}5$ e $0{,}5$ invece di essere assegnato a forza a uno solo: è la logica fuzzy del §10.2 applicata al clustering.

#### **7.12. Come scegliere $k$, il numero di cluster**

- **Il vero numero di cluster è ignoto** nell’apprendimento non supervisionato.
- **Conoscenza a priori? Usarla!**
- **$k$ troppo basso**: *under-clustering* (gruppi naturali **fusi**).
- **$k$ troppo alto**: *over-clustering* (gruppi naturali **divisi**).
- Un **$k$ piccolo e fisso** è utile per **confrontare rappresentazioni di feature**.


![](imgs/Pasted%20image%2020261125020731.png)

#### **7.13. Una pipeline di clustering**

**Dati grezzi** → **ispezione visiva** → **codifica** (categorico → numerico) → **scalatura** → **k-means** ($k$, distanza) → **indice di cluster**; in parallelo, **PCA** → mappa 2D (**solo visualizzazione**).


![](imgs/Pasted%20image%2020261125020738.png)

- **Le stesse scelte del k-NN**: **rappresentazione, scalatura e distanza** decidono quali punti formano un gruppo.
- **La PCA disegna soltanto il risultato**; **i cluster vanno giudicati nello spazio delle feature originale (scalato)**.

#### **7.14. Come decidere $k$? Il grafico a gomito (metodo di base)**

Il **metodo del gomito** determina il numero di cluster disegnando la **somma dei quadrati entro i cluster** (WCSS, *Within-Cluster Sum of Squares*) in funzione di $k$. Un $k$ candidato sta vicino al «**gomito**», dove le ulteriori riduzioni della WCSS diventano piccole. **Il gomito può essere ambiguo o assente**; la scelta va confermata con indici di validità dei cluster e conoscenza del dominio.


![](imgs/Pasted%20image%2020261125020746.png)

> 💡 **Perché serve il gomito (nota didattica).** La WCSS **diminuisce sempre** all’aumentare di $k$, e vale 0 con $k=n$ (ogni punto è il suo centro): scegliere il $k$ con la WCSS minima porterebbe sempre a $k=n$. È lo stesso fenomeno dell’errore di training che scende con la complessità (Lezione 17): si cerca il punto in cui aggiungere complessità smette di rendere.

#### **7.15. Come decidere $k$? Metodi avanzati**

- **Silhouette score**: misura la **coerenza dei cluster**;
- **Gap statistic**: confronta con una **distribuzione nulla**;
- **indice di Davies-Bouldin**: misura **separazione e compattezza** dei cluster;
- **BIC/AIC per le GMM**: criteri di **selezione del modello** per i modelli a misture gaussiane;
- **indice di Calinski-Harabasz**: **rapporto di varianza** per i cluster.

I criteri d’informazione **bayesiano (BIC)** e **di Akaike (AIC)** bilanciano **adattamento e complessità del modello** penalizzando l’aggiunta di parametri.


![](imgs/Pasted%20image%2020261125020808.png)

> 📌 **Silhouette (nota didattica).** Per un punto $i$, sia $a(i)$ la distanza media dagli altri punti del **suo** cluster e $b(i)$ la distanza media dai punti del cluster **più vicino** tra gli altri:
> $$s(i)=\frac{b(i)-a(i)}{\max\big(a(i),\,b(i)\big)}\in[-1,1].$$
> Vicino a 1: il punto sta bene nel suo cluster; vicino a 0: sta sul confine; negativo: probabilmente è nel cluster sbagliato. Si sceglie il $k$ con la **silhouette media più alta**. A differenza della WCSS, la silhouette non migliora automaticamente con $k$: con troppi cluster $b(i)$ diventa piccolo e $s(i)$ scende. I criteri BIC e AIC hanno la forma «errore di adattamento + penalità × numero di parametri» ($\text{AIC}=2p-2\ln\hat{L}$, $\text{BIC}=p\ln N-2\ln\hat{L}$): il BIC penalizza di più, e sceglie modelli più semplici.

#### **7.16. Un campione nuovo: dove appartiene?**

$$\hat{c}(x)=\arg\min_{j\in\{1,\dots,k\}}\|x-\mu_j\|,$$

con $x$ il **nuovo prodotto**, sottoposto **alla stessa pre-elaborazione dei dati di training**; $\mu_j$ il centroide del cluster $j$; $\hat{c}$ l’indice del centroide più vicino. **Esempio**: $d_1=1{,}5<d_2=2{,}3\Rightarrow\hat{c}=1$.


![](imgs/Pasted%20image%2020261125020825.png)

- **Il clustering dà un indice, non un significato.**
- Nomi come «**prodotti buoni**» o «**prodotti difettosi**» vengono dati **a posteriori da una persona** (per esempio un ispettore della qualità), **mai dall’algoritmo**.

> 💡 **Una classificazione senza addestramento supervisionato (nota didattica).** Assegnare al centroide più vicino è un **1-NN sui centroidi**: le regioni sono le celle di Voronoi dei $k$ centri (§7.5). Una volta che una persona ha dato un nome ai cluster, il k-means diventa di fatto un classificatore, ma con le etichette assegnate dopo e senza garanzia che i cluster coincidano con le categorie che interessano.

#### **7.17. Il clustering non è rilevamento di anomalie**

| **Clustering** | **Le anomalie sono** |
|---|---|
| **trova una struttura** | **regioni a bassa densità** |
| **non definisce le anomalie** | **lontane dai centroidi** |
| | **cluster piccoli o isolati** |


![](imgs/Pasted%20image%2020261125020836.png)

> ⚠️ **Perché il k-means da solo non trova le anomalie (nota didattica).** Il k-means assegna **ogni** punto a un cluster, anomalie comprese: un guasto mai visto viene messo nel cluster più vicino come se fosse normale. E un gruppo di anomalie abbastanza numeroso può addirittura spostare un centroide verso di sé (§7.9). Serve un criterio aggiuntivo: la distanza dal centroide, la densità locale, o la dimensione del cluster.

#### **7.18. Anomalia = lontano dai dati normali**

> 📌 **Definizione.** La ***novelty detection*** impara **solo da campioni normali** e segnala i campioni nuovi **troppo lontani da essi**; l’***outlier detection*** cerca **campioni rari** in un insieme **non etichettato**.

$$s(x)=\text{dist}\big(x,x_{(k)}\big),\qquad \text{anomalia se } s(x)>\tau,$$

con $x_{(k)}$ il **$k$-esimo campione normale più vicino**; $s$ il **punteggio di anomalia**, nella stessa unità delle feature; $\tau$ la **soglia fissata sui dati normali**.

**Esempio**: $k=5$, $\tau=1{,}27$ (**il 98% dei campioni normali ha $s\le\tau$**): un punto con $s=0{,}4\Rightarrow$ **normale**; un punto con $s=2{,}9\Rightarrow$ **anomalia**.


![](imgs/Pasted%20image%2020261125020844.png)

**La stessa idea altrove**: distanza da un centroide; **errore di ricostruzione di un autoencoder** (Lezione 22).

> 💡 **La soglia come percentile (nota didattica).** Fissare $\tau$ in modo che il 98% dei normali abbia $s\le\tau$ significa accettare che il 2% dei campioni normali venga segnalato come anomalo: è il **tasso di falsi allarmi** scelto in progetto. La soglia si sceglie sui costi (un falso allarme ferma la linea, un’anomalia persa manda un pezzo difettoso al cliente), esattamente come le soglie del Noul (Lezione 18, §8.16). Perché il $k$-esimo vicino e non il primo: un punto anomalo con un solo vicino casuale vicino a sé verrebbe considerato normale; chiedere $k$ vicini entro $\tau$ è più robusto.

#### **7.19. Il punteggio di anomalia: un esempio svolto con $k=3$**

| | primi 3 vicini $d_{(1)},d_{(2)},d_{(3)}$ | $s=d_{(3)}$ | confronto con $\tau=1{,}10$ | esito |
|---|---|---:|---|---|
| $x_A$ (stella) | 0,56; 0,65; 0,86 | 0,86 | $0{,}86\le 1{,}10$ | **normale** |
| $x_B$ (triangolo) | 2,17; 2,28; 2,31 | 2,31 | $2{,}31>1{,}10$ | **anomalia** |


![](imgs/Pasted%20image%2020261125020904.png)

$\tau$ ricalcolato per $k=3$ sui dati normali. **Stessa regola**: anomalia **se meno di $k$ campioni normali stanno entro il raggio $\tau$**.

> 💡 **Le due letture sono equivalenti (nota didattica).** $s(x)=d_{(k)}>\tau$ significa che il $k$-esimo vicino è oltre il raggio $\tau$, cioè che **meno di $k$** vicini stanno dentro il cerchio di raggio $\tau$. Per $x_B$ nessun vicino sta dentro il cerchio; per $x_A$ tutti e tre. La soglia cambia con $k$ ($1{,}27$ con $k=5$, $1{,}10$ con $k=3$) perché il $k$-esimo vicino è più lontano quando $k$ cresce.

---

### **8. Strumenti: EDA e clustering in Python**


In Python: un esempio semplice che ripassa le tecniche appena studiate.

#### **8.1. Clustering dei dati dei clienti: caricare e disegnare i dati**

**Da dove vengono questi dati?** La tabella dei clienti caricata nell’esercizio Python (**200 clienti, 5 colonne**); si tengono **2 feature, Età e Spending Score** (punteggio di spesa). Passi di caricamento ed esplorazione (`.head`, `.shape`, `.info`, `.isnull`, istogrammi): appendice A5–A11 (§11).


![](imgs/Pasted%20image%2020261125020946.png)

#### **8.2. Il k-means in Python**

```python
from sklearn.cluster import KMeans
kmeans = KMeans(n_clusters=4)
kmeans.fit(X)
y_kmeans = kmeans.predict(X)
centers = kmeans.cluster_centers_

# s = dimensione del marcatore
plt.scatter(X[:, 0], X[:, 1], c=y_kmeans, s=50, cmap='viridis')
plt.scatter(centers[:, 0], centers[:, 1], c='black', s=200, alpha=0.5);
plt.xlabel('age')
plt.ylabel('Spending score')
```

**Input**: $X$, **200 clienti × 2 feature** (età, spending score). **Output**: `y_kmeans`, **un indice di cluster in $\{0,1,2,3\}$ per cliente**, e `centers`, una **matrice $4\times 2$ di centroidi**. **In più di 3 dimensioni il clustering può aiutare ancora di più.**

> ⚠️ **Due dettagli del codice (nota didattica).** 1) Età (circa 18–70) e spending score (1–100) hanno scale simili ma non uguali, e il codice non standardizza: per due feature va bene, ma con più feature in unità diverse va inserito uno `StandardScaler` (§7.13). 2) Senza `random_state` i centri iniziali cambiano a ogni esecuzione, e con loro la numerazione dei cluster: il «cluster 2» di oggi può essere il «cluster 0» di domani. Il nome di un cluster si dà guardando il centroide, non l’indice.

#### **8.3. Il clustering si può usare per ottenere intuizioni!**

È stato identificato un cluster caratterizzato da:

- **giovane età**;
- **spending score alto**.

**Inviare spesso coupon e offerte dedicate!**


![](imgs/Pasted%20image%2020261125020959.png)

#### **8.4. In appendice: clustering di segnali di sensori di gas**

- **Dataset pubblico UCI**: **16 sensori chimici di gas** esposti a **metano + etilene**.
- **Serie temporali multivariate**: **19 colonne numeriche** (tempo, 2 concentrazioni di gas, 16 sensori).
- **Nessuna etichetta**: ogni riga è un punto in $\mathbb{R}^{16}$ ⇒ **clustering**.

Dati grezzi (prime righe della tabella) e cluster k-means ($k=2$; colore = indice di cluster, mostrati 2 dei 16 sensori).


![](imgs/Pasted%20image%2020261125021015.png)

L’esempio completo (EDA, matrice di scatter, t-SNE, codice e risultati del k-means) è nell’appendice A12–A19 (§11.2): **non d’esame**, ma un buon ripasso della lezione.

---

### **9. Teoria: il calcolo evolutivo**


Algoritmi genetici, logica fuzzy, swarm intelligence e sistemi ibridi.

#### **9.1. Gli algoritmi genetici**

> 📌 **Definizione.** Un **algoritmo genetico (GA)** è una **ricerca basata su una popolazione** che migliora le soluzioni candidate con **selezione, crossover e mutazione**, guidata da un **punteggio di fitness**.

1. **popolazione iniziale casuale**, ordinata per fitness;
2. i **migliori individui** vengono **incrociati e mutati**;
3. **ripetere** finché il risultato è abbastanza buono o il budget è esaurito.

- **Principio di Darwin**: «**sopravvivenza del più adatto**».
- Un GA **deve riconoscere una buona soluzione** (fitness), **non sapere come costruirla**.
- **Input**: codifica di una soluzione + funzione di fitness; **output**: il miglior individuo trovato.

![](imgs/Pasted%20image%2020261125021055.png)

Ciclo: popolazione iniziale casuale → **ordinamento per fitness + selezione dei genitori** → **crossover + mutazione** → nuova prole → **selezione dei sopravvissuti** → nuova popolazione → … → miglior individuo (abbastanza buono o budget esaurito). Il calcolo evolutivo comprende **strategie evolutive**, **programmazione evolutiva**, **algoritmi genetici** e **programmazione genetica**.


![](imgs/Pasted%20image%2020261125021101.png)

> 💡 **Perché «riconoscere, non costruire» (nota didattica).** Per ottimizzare gli iperparametri di un sistema non serve sapere come il loro valore influisce sull’errore (nessun gradiente, Lezione 17): basta poter **misurare** l’errore di una configurazione. È ciò che rende i GA adatti a problemi in cui la funzione obiettivo è una scatola nera, discreta o non derivabile, come l’errore di validazione di un modello addestrato.

#### **9.2. Logica fuzzy e logica booleana**

> 📌 **Definizione.** La **logica fuzzy** permette a un’affermazione di essere **vera in una misura in $[0,1]$** invece che solo 0 o 1.

- **Logica booleana**: «Nick è onesto?» → **Vero / Sì / 1** oppure **Falso / No / 0**.
- **Logica fuzzy**: «Nick è onesto?» → **estremamente onesto (1,00)**, **molto onesto (0,85)**, **a volte onesto (0,35)**, …, **estremamente disonesto (0,00)**.

Un **sistema fuzzy**: **numeri in ingresso** (*crisp*) → **fuzzificatore** → **regole e insiemi fuzzy** (inferenza) → **defuzzificatore** → **numero in uscita** (*crisp*).

![](imgs/Pasted%20image%2020261125021111.png)



![](imgs/Pasted%20image%2020261125021129.png)

> 📌 **Insiemi e regole fuzzy (nota didattica).** In logica booleana un elemento **appartiene o no** a un insieme. In logica fuzzy ha un **grado di appartenenza** $\mu(x)\in[0,1]$. Esempio: per l’insieme «temperatura calda», $\mu(18\,°\text{C})=0$, $\mu(24\,°\text{C})=0{,}5$, $\mu(30\,°\text{C})=1$. Una **regola fuzzy** ha la forma «**se** la temperatura è calda **e** l’umidità è alta **allora** la ventola è veloce»: il grado con cui la regola si attiva dipende dai gradi di appartenenza (per esempio il minimo tra i due), e le uscite delle regole si combinano in un valore numerico finale (la defuzzificazione, per esempio con il baricentro). È lo stesso principio del Fuzzy C-Means del §7.11: appartenenze parziali invece di confini netti.

> ⚠️ **Grado di verità non è probabilità.** «Nick è onesto con grado 0,85» non significa «c’è l’85% di probabilità che Nick sia onesto»: descrive **quanto** l’affermazione è vera, non l’incertezza su un fatto che è o vero o falso. Un Noul (Lezione 18) restituisce una probabilità, un sistema fuzzy un grado.

#### **9.3. I sistemi a intelligenza di sciame**

Ispirati al comportamento degli **insetti sociali** come le **formiche**, e di altre società animali come **stormi di uccelli** o **banchi di pesci**. Caratterizzati da:

- comportamenti **decentralizzati e auto-organizzati**;
- **risoluzione di problemi complessi**;
- **capacità di ottimizzazione**.

![](imgs/Pasted%20image%2020261125021146.png)



![](imgs/Pasted%20image%2020261125021153.png)

> 💡 **Due esempi classici (nota didattica).** Nell’*Ant Colony Optimization* le formiche virtuali lasciano un «feromone» sui percorsi brevi, che attira altre formiche e si rinforza: si usa per problemi di instradamento (per esempio il commesso viaggiatore, la logistica). Nella *Particle Swarm Optimization* ogni particella è una soluzione candidata che si muove nello spazio dei parametri attratta dalla propria migliore posizione e da quella del gruppo. Nessuna particella conosce la soluzione: emerge dall’interazione.

---

### **10. Teoria: i sistemi intelligenti ibridi**


Reti neurali, sistemi fuzzy e algoritmi genetici insieme.

#### **10.1. Definizione**

> 📌 **Definizione.** «Un **sistema software** che impiega, **in parallelo**, una **combinazione di metodi e tecniche** dell’intelligenza artificiale.»


![](imgs/Pasted%20image%2020261125021208.png)

#### **10.2. Perché ibrido?**

- **Calcolo neurale, machine learning, logica fuzzy, algoritmi evolutivi, swarm intelligence, metodi ad agenti**, tra gli altri («**soft computing**»), si sono affermati e hanno mostrato **i loro punti di forza e i loro limiti**.
- Trovare **la combinazione migliore di tecniche per mitigare i limiti** nella propria applicazione.
- Alcune **fusioni possibili**:
  - **soft computing/AI e hard computing** (calcolo classico, esatto);
  - **diverse tecniche di soft computing/AI**;
  - **incorporazione** (per esempio **neuro-fuzzy**).


![](imgs/Pasted%20image%2020261125021215.png)

#### **10.3. Esempi di sistemi intelligenti ibridi**

**Sistemi semplici «in parallelo»** (non incorporati). **Combinazioni comuni**: neuro-fuzzy; genetico-fuzzy; genetico-neurale; neuro-fuzzy-genetico; sistemi esperti ibridi / neurali; sistemi a intelligenza di sciame.


![](imgs/Pasted%20image%2020261125021226.png)

> 💡 **Un esempio concreto (nota didattica).** Un sistema neuro-fuzzy usa una rete neurale per **imparare** dai dati le funzioni di appartenenza e i pesi delle regole di un sistema fuzzy: il risultato ha l’accuratezza appresa dalla rete e la leggibilità delle regole «se… allora…». Un sistema genetico-neurale usa un GA per scegliere l’architettura o gli iperparametri della rete (§10.4). Il System 1 + System 2 della Lezione 18 è anch’esso un ibrido: un classificatore veloce e un LLM o una persona.

#### **10.4. Un esempio di ottimizzazione genetica dei parametri**

Anche **solo 4–5 parametri** nel progetto (come in un sistema ibrido) possono rendere il progetto **impossibile da risolvere con la forza bruta**: un algoritmo genetico può essere una buona soluzione.


![](imgs/Pasted%20image%2020261125021239.png)

> 💡 **Perché la forza bruta non basta (nota didattica).** Se ognuno dei 5 parametri può assumere 20 valori, le configurazioni sono $20^5=3{,}2$ milioni. Ognuna richiede di addestrare il sistema e di valutarlo con una cross-validation (Lezione 14): anche a un secondo per configurazione sarebbero più di un mese di calcolo. Un algoritmo genetico ne valuta qualche centinaio o migliaio, concentrandosi sulle zone promettenti.

**Creazione della popolazione e selezione**, con errori di validazione illustrativi (**più bassi è meglio**):

| Individuo | Par.#1 | Par.#2 | Par.#3 | Par.#4 | Par.#5 | Errore di validazione |
|---|---:|---:|---:|---:|---:|---:|
| #1 | 1 | 4 | 10 | 10 | 8 | 3,3% |
| #2 | 1 | 4 | 8 | 8 | 6 | **3,1%** ◀ **il più adatto!** |
| #3 | 3 | 4 | 8 | 6 | 6 | 4,1% |
| … | … | … | … | … | … | … |

#### **10.5. Ottimizzazione genetica dei parametri: crossover e mutazioni**

| Individuo | Par.#1 | Par.#2 | Par.#3 | Par.#4 | Par.#5 | Errore di validazione |
|---|---:|---:|---:|---:|---:|---:|
| #1 (madre) | 1 | 4 | 10 | 10 | 8 | 3,3% |
| #2 (padre) | 1 | 4 | 8 | 8 | 6 | 3,1% |
| #12 (figlio) | 1 | 4 | 10 | 10 | 8 | non valutato |
| #12 (figlio mutato) | 1 | 4 | 10 | **5** | 8 | **2,8%** |

- **Crossover**: il figlio combina i geni dei due genitori (magenta: padre, verde: madre), con il **punto di crossover tra Par.#2 e Par.#3**.
- **Mutazione**: **Par.#4 passa da 10 a 5** ($-5$). L’errore di validazione del 2,8% è **illustrativo, non un dato misurato**.

> 💡 **Due osservazioni sulla tabella (nota didattica).** 1) Padre e madre hanno gli stessi Par.#1 e Par.#2 (1 e 4): il figlio prende quei geni «dal padre» ma sono identici a quelli della madre, quindi prima della mutazione il figlio coincide con la madre (stessa riga 1, 4, 10, 10, 8): il crossover non ha prodotto nulla di nuovo, ed è la mutazione a esplorare. È un esempio di perdita di diversità, il motivo per cui i GA hanno bisogno della mutazione. 2) L’edizione 2026/27 chiarisce che la fitness è un **errore di validazione da minimizzare**; l’edizione precedente lasciava ambiguo se i valori del 3–4% fossero errori o accuratezze. Anche qui vale la regola della Lezione 14: se il GA sceglie sulla validazione, la stima finale va fatta su un test set mai usato dalla ricerca.

---

### **11. Appendice: materiale di laboratorio**


Dettagli di supporto e materiale di laboratorio: A2–A4 k-NN e modelli classici in codice (MATLAB e scikit-learn, §6.3–6.4); A5–A11 clustering dei clienti (Python); A12–A19 clustering dei sensori di gas. Codice e schermate per lo studio individuale; il codice completo sarà pubblicato più avanti sul sito del corso.

#### **11.1. Clustering dei clienti in Colab (A5–A11)**

Le slide sono schermate del notebook: preparazione di Colab, importazione dei dati e selezione delle feature, poi cinque passi di esplorazione.

Setuppiamo:

![](imgs/Pasted%20image%2020261125021330.png)

![](imgs/Pasted%20image%2020261125052353.png)

![](imgs/Pasted%20image%2020261125052401.png)

![](imgs/Pasted%20image%2020261125052411.png)

![](imgs/Pasted%20image%2020261125052422.png)

![](imgs/Pasted%20image%2020261125052433.png)

![](imgs/Pasted%20image%2020261125052444.png)

> 💡 **Che cosa controlla ogni passo (nota didattica).** `.head()` le prime righe (i tipi di valore sono quelli attesi?); `.shape` le dimensioni (200 righe, 5 colonne); `.info()` tipi e valori non nulli per colonna; `.isnull().sum()` i valori mancanti; gli istogrammi le distribuzioni (asimmetrie, valori impossibili). È la checklist EDA della Lezione 11 (§3).

#### **11.2. Clustering dei sensori di gas (A12–A19, non d’esame)**

**Il dataset UCI.** Non d’esame, ma un buon ripasso dell’intera lezione su un dataset pubblico famoso: ***Gas sensor array under dynamic gas mixtures*** (UCI Machine Learning Repository, J. Fonollosa, 2015, CC BY 4.0).

**Miscela di gas** (metano + etilene) → **array di 16 sensori MOX** (16 letture/s) → $x\in\mathbb{R}^{16}$ → **k-means** (nessuna etichetta) → **indice di cluster**.

![](imgs/Pasted%20image%2020261125052510.png)

| tempo [s] | metano [ppm] | etilene [ppm] | S1 | S2 | S3 | … | S14 | S15 | S16 |
|---:|---:|---:|---:|---:|---:|---|---:|---:|---:|
| 0,00 | 0,00 | 0,00 | −41,98 | 2067,64 | −37,13 | … | 5056,98 | 3639,09 | 3128,49 |
| 1,00 | 0,00 | 0,00 | −41,17 | 2081,41 | 2,77 | … | 5046,29 | 3635,46 | 3131,01 |
| 2,00 | 0,00 | 0,00 | 59,43 | 2072,86 | 198,53 | … | 5089,52 | 3647,27 | 3128,21 |
| 3,00 | 0,00 | 0,00 | 364,55 | 2070,96 | 689,49 | … | 5048,86 | 3651,21 | 3133,82 |

(Ricostruzione dell’ordine delle colonne S1–S3 dal testo estratto.) Dalla pagina UCI: **16 sensori** (Figaro TGS-2600, -2602, -2610, -2620, 4 unità ciascuno); **circa 12 ore di registrazione a 100 Hz**; **4 178 504 righe × 19 colonne** (tempo, 2 set-point dei gas, 16 letture dei sensori); metano 0–300 ppm, etilene 0–20 ppm. L’esercizio usa un file ridotto, `ethylene_methane2_red.txt` (**una riga al secondo**).

![](imgs/Pasted%20image%2020261125052521.png)

> 💡 **I numeri (nota didattica).** 12 ore a 100 Hz: $12\cdot 3600\cdot 100=4\,320\,000$ campioni, coerente con le 4 178 504 righe (circa 11,6 ore). Il file ridotto a una riga al secondo ne ha circa 42 000. MOX (*Metal Oxide*) indica sensori a ossido metallico, la cui resistenza cambia con la concentrazione del gas. Valori negativi o molto diversi tra i sensori (−42 contro 5000) mostrano che le unità grezze non sono confrontabili: da qui la standardizzazione del §11.2.

**Il file dei dati e lo script.** Il dataset contiene le serie temporali acquisite da **16 sensori chimici** esposti a miscele di gas a concentrazioni variabili: **tempo** (secondi), **concentrazione di metano** (ppm), **concentrazione di etilene** (ppm), **letture dei sensori** (16 canali). **16 canali = spazio delle feature a 16 dimensioni.**

**EDA: tabella e box plot.** Il dataset è **non supervisionato**: non contiene variabili target esplicite. **Nessun valore mancante** (0,0% per ogni colonna).

![](imgs/Pasted%20image%2020261125052547.png)

**EDA: il codice.**

```python
# Eliminare le colonne che non sono feature dei sensori
# (Time, Methane_conc, Ethylene_conc)
features_to_drop = ['Time', 'Methane_conc', 'Ethylene_conc']
data = data.drop(columns=features_to_drop)
```

**Input**: la tabella con 19 colonne. **Output**: `data` con **le sole 16 colonne dei sensori** (lo spazio delle feature per il clustering). I due grafici della slide di origine mostrano **il primo sensore nel tempo** e lo **scatter del sensore 3 contro il sensore 6**.

> 💡 **Non supervisionato per scelta (nota didattica).** Il file contiene le concentrazioni dei gas, che potrebbero fare da target; l’esempio le **toglie** per vedere se il clustering ritrova da solo gli «stati» del sistema. Le concentrazioni restano utili **dopo**, per interpretare i cluster (che cosa hanno in comune le righe del cluster 0?), ma non guidano il raggruppamento.

**EDA: matrice di scatter.**

- È sempre una buona idea **controllare le distribuzioni delle feature**.
- Si vedono **sensori molto correlati**.
- **Distribuzioni bimodali o N-modali** sono un buon indizio di «**stati**» nel sistema.
- Si deve esplorare uno spazio a 16 dimensioni…

![](imgs/Pasted%20image%2020261125052615.png)

**EDA: la t-SNE mostra gli stati.** L’esistenza di «**stati**» nel sistema è confermata dalla visualizzazione t-SNE (**che potente trasformazione di embedding non supervisionata!**).

![](imgs/Pasted%20image%2020261125052623.png)

**Il codice del k-means.**

```python
scaler = StandardScaler()
data_scaled = scaler.fit_transform(data)

# Applicare il clustering k-means (nello spazio delle feature standardizzato)
kmeans = KMeans(n_clusters=2, random_state=42)
kmeans_labels = kmeans.fit_predict(data_scaled)
```

**Input**: `data`, $N\times 16$ letture dei sensori. **Output**: `kmeans_labels`, **un’etichetta in $\{0,1\}$ per campione**, usata per colorare lo scatter S3 contro S6 e le componenti t-SNE 1 contro 2.

**I cluster nello spazio dei sensori.** La figura mostra **centroidi e assegnazioni dei cluster nelle coordinate originali dei sensori**. Un embedding t-SNE è **una visualizzazione separata**; le sue distanze possono essere distorte, quindi **i cluster vanno validati nello spazio delle feature standardizzato originale**.

![](imgs/Pasted%20image%2020261125052642.png)

> 💡 **Una catena coerente (nota didattica).** EDA (nessun mancante, scale molto diverse, distribuzioni multimodali) → standardizzazione → t-SNE per **vedere** quanti stati ci sono → k-means per **assegnare** gli stati → validazione dei cluster nello spazio originale. La t-SNE suggerisce $k$, il k-means lo usa: nessuno dei due da solo basterebbe.

---

### **12. Punti principali**


> ✅ **Punti principali della lezione.**
> - Dove le classi si sovrappongono **anche il classificatore Bayes-ottimo sbaglia**: lo **0% di errore può essere impossibile**.
> - Il **Naive Bayes rende calcolabile la regola di Bayes** assumendo le feature **indipendenti data la classe**.
> - I learner **eager generalizzano durante l’addestramento**; i learner **lazy** come il k-NN **generalizzano al momento della richiesta**.
> - **k-NN = distanze + $k$ più vicini + voto.** Un $k$ piccolo **segue il rumore**, un $k$ grande **nasconde i piccoli gruppi**: scegliere $k$ sui dati di validazione.
> - **I vicini dipendono dalla distanza, dall’origine (coseno) e dalle unità (scala)**: **la similarità è una scelta di progetto**.
> - Il **k-means alterna assegnazione e aggiornamento delle medie**; raggiunge un **minimo locale** e richiede $k$, scalatura e una buona inizializzazione.
> - Un campione **lontano dai dati normali è un candidato anomalia**; **GA, sistemi fuzzy e ibridi** combinano o regolano altri metodi.

---

### **13. Distinzioni da non confondere**


| Coppia | Differenza chiave |
|---|---|
| **errore di Bayes** / **errore di un modello** | limite della rappresentazione, irriducibile con più dati / errore del modello, riducibile fino al limite |
| **posteriore** $P(C\mid X)$ / **verosimiglianza** $P(X\mid C)$ | probabilità della classe dati i dati / probabilità dei dati data la classe |
| **Bayes-ottimo** / **Naive Bayes** | congiunta esatta, ignota / prodotto delle marginali per classe, ipotesi di indipendenza |
| **eager** / **lazy** | addestramento costoso, previsione veloce / addestramento nullo, previsione costosa |
| **1-NN** / **k-NN** | regioni di Voronoi, overfitting / frontiera più liscia, $k$ da validare |
| **$k$ del k-NN** / **$K$ della CV** | numero di vicini nel voto / numero di fold |
| **voto semplice** / **voto pesato** | tutti i vicini uguali / pesi $1/\text{dist}^p$ |
| **euclidea** / **coseno** | posizione, invariante per traslazione / direzione, dipende dall’origine |
| **coseno grezzo** / **dopo la centratura** | embedding tutti ≈ 1 / gruppi separati |
| **min-max** / **z-score** | intervallo $[0,1]$, toglie la centratura / media 0, origine nella media |
| **mappa 2D** / **spazio completo** | solo visualizzazione / dove avviene il voto o il clustering |
| **classificazione** / **clustering** | etichette note / gruppi senza etichette, nomi dati a posteriori |
| **clustering esclusivo** / **sovrapposto** | un cluster per punto (k-means) / appartenenze parziali (Fuzzy C-Means) |
| **WCSS** / **silhouette** | scende sempre con $k$ / ha un massimo |
| **minimo locale** / **globale** | dove si ferma il k-means / il migliore possibile, da avvicinare con più inizializzazioni |
| **clustering** / **rilevamento di anomalie** | trova una struttura / segnala i punti lontani dai normali |
| **novelty detection** / **outlier detection** | impara solo dai normali / cerca rari in un insieme non etichettato |
| **grado fuzzy** / **probabilità** | quanto è vera un’affermazione / incertezza su un fatto |
| **crossover** / **mutazione** | combina geni dei genitori / esplora valori nuovi |
| **sistema ibrido in parallelo** / **incorporato** | metodi affiancati / un metodo dentro l’altro (neuro-fuzzy) |
