## **Lezione 16: Valutazione dell’accuratezza, dataset e codifica — metriche, foundation model, Iris, one-hot encoding e laboratorio**

La lezione risponde a cinque domande pratiche che ogni progetto incontra. **Quanto è buono il mio modello?** Si misura con metriche diverse per regressori ($R^2$, MSE, RMSE, MAE) e classificatori (accuratezza confrontata con baseline banali, matrice di confusione binaria e multiclasse, curva ROC). **Posso fidarmi?** I foundation model usati in modalità zero-shot e few-shot restituiscono spesso testo invece di classi, e richiedono strategie di valutazione nuove; a queste si aggiungono le mappe di salienza e l’analisi di sensibilità. **Da che cosa parto?** Da un dataset semplice e noto, il dataset **Iris** di Fisher, usato come strumento di debug. **Come do le categorie a un modello?** Con codifiche scelte con giudizio — intera per le scale ordinate, label encoding con cautela, one-hot e dummy per le categorie senza ordine. **Come lo programmo?** Con normalizzazione e divisione train/test, nel laboratorio MATLAB e Colab.

> **Fonte.** La lezione segue il PDF dell’edizione 2026/27 `sources_2026-27/L16-web.pdf` (116 pagine, parti A e B). I segnaposto per le figure indicano la pagina del PDF. Il notebook e lo script del laboratorio (`Lesson_16_laboratory_COLAB_IRIS_Dataset.ipynb`, `Lesson_16_laboratory_MATLAB_IRIS_Dataset.m`, `Lesson_16_iris.dat`) sono commentati nel §8.


### **0. Panoramica**


1. **Quanto è buono il mio modello?** Metriche per regressori e classificatori: $R^2$, MSE/RMSE, MAE, matrice di confusione, ROC.
2. **Posso fidarmi?** Foundation model, valutazione zero/few-shot, mappe di salienza, analisi di sensibilità.
3. **Da che cosa parto?** Un dataset semplice e noto (Iris) come strumento di debug; $X$ e $Y$; primi grafici.
4. **Come do le categorie a un modello?** Codifica intera, label encoding e one-hot.
5. **Come lo programmo?** Normalizzazione, divisione train/test; laboratorio in MATLAB e Colab.
6. Punti principali.

![](imgs/Pasted%20image%2020261124032554.png)

---

### **1. Teoria: valutazione dell’accuratezza di classificatori e regressori**


#### **1.1. In quale passo si usa?**

| Passo del flusso | Chi valuta | Su che cosa |
|---|---|---|
| **#3** | il **metodo di apprendimento** (ottimizzazione dei parametri) | dataset di **training** |
| **#4** | **voi**: valutazione della **generalizzazione** | dataset di **test** |
| **#5** | **voi**: miglioramento dell’intero sistema (nuovi modelli, nuovi metodi di apprendimento, …) | — |

![](imgs/Pasted%20image%2020261124032912.png)

**Espressione generale**: $Y=f(X,\text{params})$. **Le metriche dipendono da $Y$**:

- **regressori**: $Y\in\mathbb{R}$ (numeri reali);
- **classificatori**: $Y\in\mathbb{Z}$ (ID di classe).

#### **1.2. Regressori: valutare gli errori**

Si immagini di creare **per Amazon** un regressore (predittore) che, dai dati dell’utente, prevede **l’importo in euro del prossimo ordine** (entro una settimana):

```text
[euros_next_order] = function( [age, city, level_of_instructions,
                                time_of_the_day, previous_order_amount, days_by_last_order] )
```

- **Input**: 6 feature (età [anni], città [categoria], …); **output**: 1 numero [EUR].
- Come si **dimostra** che il regressore è buono?
- In base a quali **figure di merito** lo si può migliorare?

> 💡 **Una feature non numerica (nota didattica).** «Città» è una categoria: prima di darla al regressore va codificata, e il modo in cui lo si fa (§5) cambia ciò che il modello può imparare. È il collegamento tra la prima e l’ultima parte della lezione.

##### **1.2.1. Il coefficiente di determinazione $R^2$**

$$R^2=1-\frac{\sum_{i=1}^{n}(y_i-\hat{y}_i)^2}{\sum_{i=1}^{n}(y_i-\bar{y})^2}=1-\frac{SS_{\text{res}}}{SS_{\text{tot}}}.$$

- **Numeratore**: variazione **non spiegata** (rispetto all’uscita del modello).
- **Denominatore**: variazione **totale** (rispetto alla media).
- $y_i$ valore osservato, $\hat{y}_i$ uscita del modello, $\bar{y}=\frac1n\sum_i y_i$ media degli osservati, $n$ campioni.
- È la **quota della variazione dell’esito spiegata dai predittori**: **più alto è meglio**. Vale per **qualunque modello**, non solo per le rette.
- Intervallo usuale $[0,1]$ ($(-\infty,1]$ per modelli cattivi); **adimensionale**. Esempio: $R^2=0{,}81\Rightarrow$ **81% spiegato**.

<!-- SOURCE_VISUAL id="IS16-V01" source="L16-web.pdf" page="6" type="grafico" description="Dati osservati, retta del modello e media per illustrare la variazione spiegata e non spiegata in R^2" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124032946.png)

> 💡 **Perché $R^2$ può essere negativo (nota didattica).** $SS_{\text{tot}}$ è l’errore della baseline che prevede sempre la media. Se il modello sbaglia più della media, $SS_{\text{res}}>SS_{\text{tot}}$ e $R^2<0$: il modello è peggio che non usarlo. Sui dati di training un modello lineare con intercetta ha sempre $R^2\ge 0$; sul test no.

##### **1.2.2. Errore quadratico medio (MSE) e la sua radice (RMSE)**

Misurano l’**errore medio** che il modello commette nel prevedere l’esito di un’osservazione:

$$\text{MSE}=\frac1n\sum_{i=1}^{n}(\hat{y}_i-y_i)^2,\qquad \text{RMSE}=\sqrt{\text{MSE}}.$$

- **Più bassi sono, meglio è**; intervallo $[0,+\infty)$.
- **Unità**: MSE = unità di $y$ **al quadrato** (per esempio EUR²); RMSE = **unità di $y$** (per esempio EUR).
- **Sensibili agli outlier!** Elevare al quadrato dà a un errore grande un peso grande.

**Esempio** ($n=3$ ordini, EUR): osservati $y=(10,12,9)$, previsti $\hat{y}=(11,12,6)$, errori $(+1,\ 0,\ -3)$:

$$\text{MSE}=\frac{1+0+9}{3}=3{,}33\ \text{EUR}^2,\qquad \text{RMSE}=\sqrt{3{,}33}=1{,}83\ \text{EUR}.$$

##### **1.2.3. Errore assoluto medio (MAE)**

$$\text{MAE}=\frac1n\sum_{j=1}^{n}|y_j-\hat{y}_j|,$$

cioè `mean(abs(observed - predicted))`.

- Il MAE è **meno sensibile agli outlier** del MSE.
- **Più basso è meglio**; intervallo $[0,+\infty)$; **stesse unità di $y$** (per esempio EUR).

**Stesso esempio**: errori assoluti $|{+1}|,\ |0|,\ |{-3}|$:

$$\text{MAE}=\frac{1+0+3}{3}=1{,}33\ \text{EUR},\qquad \text{RMSE}=1{,}83\ \text{EUR}.$$

**Il singolo errore grande pesa di più nel RMSE.**

<!-- SOURCE_VISUAL id="IS16-V02" source="L16-web.pdf" page="8" type="figura" description="Confronto tra errori assoluti e quadratici nel MAE e nel RMSE" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124034017.png)

> 💡 **Quale scegliere (nota didattica).** Si ha sempre $\text{MAE}\le\text{RMSE}$, con uguaglianza solo se tutti gli errori hanno lo stesso valore assoluto. Un RMSE molto più grande del MAE segnala pochi errori grandi. Se un errore di 30 € è tre volte grave quanto uno di 10 €, il MAE descrive bene il costo; se è molto più che tre volte grave (per esempio una scorta esaurita), il RMSE è più adatto. Il MSE è anche la funzione obiettivo minimizzata dai minimi quadrati (Lezione 17).

#### **1.3. Classificatori: valutare gli errori**

Si immagini di creare **per un ospedale** un classificatore che fa la **diagnosi precoce del pre-diabete** dai dati del paziente e dagli esami del sangue (sesso, età, peso, emoglobina glicata, …):

```text
[diabetes_presence] = function( [sex, age, GL, ...] )
```

- **Input**: un vettore di feature del paziente; **output**: 1 classe (pre-diabete: sì / no).
- Come si dimostra che il classificatore è buono? In base a quali figure di merito lo si migliora? Una tipica è l’**accuratezza**.

$$\text{accuratezza}=\frac{N_{\text{corretti}}}{N_{\text{totale}}},$$

la **percentuale di risultati previsti che coincidono con quelli attesi**. Si ha $\text{accuratezza}=1-\text{errore}$: a volte si riporta l’errore invece dell’accuratezza, quindi **controllare quale si sta leggendo**.

**Esempio**: $N_{\text{totale}}=1000$, previsioni corrette $N_{\text{corretti}}=850$: accuratezza $=850/1000=0{,}85=85\%$.

#### **1.4. Buoni riferimenti per l’accuratezza**

- Un modo per sapere se un’accuratezza è buona è **confrontarla con classificatori banali** (baseline).
- Se i dati di training e test sono **sbilanciati verso una classe**, il modello tenderà a prevedere tutto come quella classe.
  - **Classificatore casuale**: `out = rand()`: quale accuratezza? A volte il 50%.
  - **Classificatore «non così stupido»**: `out = most_present_class(dataset)`.
- **Esempio**: nei dati di training del **Titanic** il **68%** delle persone è morto. Un modello che prevede «**tutti sono morti**» è **accurato al 68%**.

> 📌 **Dire «1% di errore» può essere molto buono, o molto cattivo!!** Dipende dalla **baseline** e dal **costo degli errori** (Lezione 14).

> 💡 **«A volte il 50%» (nota didattica).** Un classificatore casuale binario che sceglie le due classi con probabilità 0,5 ha accuratezza attesa del 50% qualunque sia la distribuzione delle classi. Uno che sceglie a caso con le stesse proporzioni dei dati ($p$ e $1-p$) ha accuratezza attesa $p^2+(1-p)^2$: con il 68% di morti, $0{,}68^2+0{,}32^2=0{,}56$. La baseline più forte resta la classe di maggioranza (68%).

#### **1.5. La matrice di confusione**

| | Previsto (falso) | Previsto (vero) |
|---|---|---|
| **Reale (falso)** | $TN$ | $FP$ |
| **Reale (vero)** | $FN$ | $TP$ |

- $TN$: previsto correttamente **non** la classe (non un cane);
- $FP$: previsto la classe (cane) ma **non lo è**: **errore di tipo I**;
- $FN$: previsto **non** la classe ma **lo è**: **errore di tipo II**;
- $TP$: previsto correttamente la classe (cane).

![](imgs/Pasted%20image%2020261124034051.png)


<!-- SOURCE_VISUAL id="IS16-V03" source="L16-web.pdf" page="12" type="figura" description="Matrice di confusione illustrata con immagini di cani (credito narkhede_sarang)" reason="Illustrazione visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124034101.png)

> ⚠️ **Attenzione agli assi.** La figura della slide ha i valori **reali sulle colonne**, la tabella li ha **sulle righe**. Le librerie non sono uniformi (scikit-learn mette i reali sulle righe): va sempre controllato.

##### **1.5.1. Le misure dalla matrice di confusione**

> 📌 **IMPORTANTE!!!** La matrice di confusione dà **più informazioni sugli errori** della sola accuratezza.

| Misura | Formula |
|---|---|
| **accuratezza** | $(TP+TN)/N$ |
| **errore di classificazione** | $(FP+FN)/N$ |
| **precisione** | $TP/(TP+FP)$ |
| **sensibilità, recall, hit rate, TPR** | $TP/(TP+FN)$ |
| **specificità, selettività, TNR** | $TN/(TN+FP)$ |

$N=TP+TN+FP+FN$ è il numero di campioni testati.

**Esempio** ($N=100$): $TN=45$, $FP=5$, $FN=10$, $TP=40$:

$$\text{accuratezza}=\frac{40+45}{100}=0{,}85,\quad \text{errore}=\frac{5+10}{100}=0{,}15,\quad \text{precisione}=\frac{40}{45}=0{,}89,\quad \text{recall}=\frac{40}{50}=0{,}80,\quad \text{specificità}=\frac{45}{50}=0{,}90.$$

> 💡 **Errori di tipo I e II nella diagnosi (nota didattica).** Nel pre-diabete un falso positivo (tipo I) manda un paziente sano a un secondo esame: costo modesto. Un falso negativo (tipo II) lascia un paziente a rischio senza cure: costo alto. Per uno screening si cerca quindi recall alto anche a scapito della precisione. Recall e specificità sono le due «accuratezze per classe»: la loro media è la balanced accuracy della Lezione 14.

#### **1.6. La matrice di confusione multiclasse**

- **10 classi** ⇒ una matrice $10\times 10$.
- **Riga $i$ = classe vera**, **colonna $j$ = classe prevista**; ogni elemento = **numero di immagini di test**.
- **Ogni riga somma a 1000** immagini di test; accuratezza complessiva $=9024/10\,000=$ **90,2%** (test set di CIFAR-10, `Cifar10Labels.mat`).

<!-- SOURCE_VISUAL id="IS16-V04" source="L16-web.pdf" page="14" type="grafico" description="Matrice di confusione 10x10 su CIFAR-10 con 9024 corretti su 10000" reason="Valori della matrice visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124034324.png)

**Come leggerla.**

- La **diagonale** contiene i valori **corretti**.
- **Tutto ciò che sta fuori dalla diagonale è un errore**: la sua **posizione** dice **quali due classi** vengono confuse.

<!-- SOURCE_VISUAL id="IS16-V05" source="L16-web.pdf" page="15" type="grafico" description="Matrice CIFAR-10 con diagonale corretta ed errori fuori diagonale evidenziati" reason="Evidenziazioni visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124034334.png)

**Esempi**: **26 uccelli** reali sono stati confusi con **aerei**; **111 cani** reali sono stati confusi con **gatti**. Servono altri esempi? Si sceglie una cella qualunque fuori diagonale: **riga = classe vera**, **colonna = classe prevista**.

<!-- SOURCE_VISUAL id="IS16-V06" source="L16-web.pdf" page="16" type="grafico" description="Matrice CIFAR-10 con le celle uccello-aereo (26) e cane-gatto (111) evidenziate" reason="Celle evidenziate visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124034401.png)

> 💡 **Le confusioni hanno senso (nota didattica).** Cani e gatti sono entrambi quadrupedi pelosi in immagini $32\times 32$; uccelli e aerei sono entrambi oggetti nel cielo. Le confusioni più frequenti rivelano somiglianze reali tra classi, e indicano dove servono più dati o feature migliori (Lezione 11, §5.17). Una matrice asimmetrica (111 cani → gatti, ma un numero diverso di gatti → cani) dice anche in quale direzione il modello «preferisce» sbagliare.

#### **1.7. La curva ROC (Receiver Operating Characteristic)**

**Un «rilevatore» generale**: gli input $x_1,x_2,x_3$ vengono pesati ($w_1,w_2,w_3$, più un bias $w_0$), sommati in $s=\sum_i w_ix_i$ e confrontati con una **soglia** da una funzione a gradino: l’uscita è **sì/no**.

- Si può calcolare l’accuratezza **in funzione della soglia finale**: molto comodo nell’implementazione.
- La ROC è un **grafico degli errori** di un classificatore binario **al variare della soglia**.
- **Un punto per soglia**:
$$\text{TPR}=\frac{TP}{TP+FN}\quad\text{contro}\quad \text{FPR}=\frac{FP}{FP+TN}.$$
- **Esempio** ($N=100$): $\text{TPR}=40/50=0{,}80$, $\text{FPR}=5/50=0{,}10$.

<!-- SOURCE_VISUAL id="IS16-V07" source="L16-web.pdf" page="17" type="schema e grafico" description="Rilevatore con somma pesata e soglia, e curva ROC con la diagonale del caso e la zona di miglioramento" reason="Schema e curva visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124034429.png)

##### **1.7.1. ROC: un punto per ogni soglia**

50 campioni positivi e 50 negativi; un campione è previsto positivo se il **punteggio è $\ge$ della soglia**.

| # | Soglia | TP | FN | FP | TN | TPR | FPR |
|---:|---:|---:|---:|---:|---:|---:|---:|
| 1 | 0,9 | 12 | 38 | 0 | 50 | 0,24 | 0,00 |
| 2 | 0,8 | 22 | 28 | 1 | 49 | 0,44 | 0,02 |
| 3 | 0,7 | 29 | 21 | 2 | 48 | 0,58 | 0,04 |
| 4 | 0,6 | 35 | 15 | 3 | 47 | 0,70 | 0,06 |
| **5** | **0,5** | **40** | **10** | **5** | **45** | **0,80** | **0,10** |
| 6 | 0,4 | 43 | 7 | 9 | 41 | 0,86 | 0,18 |
| 7 | 0,3 | 46 | 4 | 15 | 35 | 0,92 | 0,30 |
| 8 | 0,2 | 48 | 2 | 24 | 26 | 0,96 | 0,48 |
| 9 | 0,1 | 49 | 1 | 35 | 15 | 0,98 | 0,70 |
| 10 | 0,0 | 50 | 0 | 50 | 0 | 1,00 | 1,00 |

**Abbassando la soglia, TP e FP crescono insieme.** La riga 5 è l’esempio delle slide precedenti.

<!-- SOURCE_VISUAL id="IS16-V08" source="L16-web.pdf" page="18" type="grafico" description="Curva ROC costruita dai 10 punti della tabella, con la soglia che scende da 1 a 10" reason="Punti numerati della curva visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124034457.png)

> 💡 **L’area sotto la curva (nota didattica).** Con la regola dei trapezi sui punti della tabella (partendo da $(0,0)$) l’area vale circa $0{,}91$: è l’**AUC**. Si interpreta come la probabilità che un positivo scelto a caso abbia un punteggio più alto di un negativo scelto a caso. Un classificatore casuale sta sulla diagonale (AUC = 0,5), uno perfetto passa per l’angolo $(0,1)$ (AUC = 1). Scegliere **dove** stare sulla curva, cioè la soglia, dipende dai costi dei due errori (Lezione 11, §6.3).

---

### **2. Un approccio diverso all’addestramento e al test: dalla validazione guidata dai dati all’adattamento guidato dalla rappresentazione**


#### **2.1. Nuove capacità, nuova domanda: come valutarle?**

Il machine learning si è arricchito di nuove capacità grazie a **foundation model**, **modelli multimodali** e **agenti**. **Come si valutano sistemi che non rispondono con un numero o una classe?**

| **Modello classico** (classificatore) | **Nuovo modello** (LLM, multimodale) |
|---|---|
| uscita: un’**etichetta di classe** («Dog») | uscita: **testo libero** («un animale a quattro zampe con la coda») |
| **facile contare** giusti e sbagliati | **nessuna classe e nessun numero** da confrontare |

![](imgs/Pasted%20image%2020261124034537.png)

#### **2.2. L’evoluzione della pipeline di ML: stessi quattro passi, procedure diverse**

| Passo | **Tradizionale** | **Nuovo** |
|---|---|---|
| **1. dati** | raccolta dei dati: **dati etichettati per il nostro compito** | analisi dei dati: dati supervisionati **e non etichettati** (non supervisionati): **non serve etichettare tutto** |
| **2. rappresentazione** | **feature engineering**: feature progettate a mano | **foundation model pre-addestrato**: rappresentazione generale **già appresa** su dati enormi, non da noi |
| **3. adattamento al compito** | **addestramento dei modelli di ML**: si adattano i parametri sui nostri dati etichettati | **ancoraggio semantico** (*semantic grounding*): dare al modello il significato del **nostro dominio** (termini, segnali, regole); **adattamento zero/few-shot**: da 0 a 10 esempi, **nessun riaddestramento** |
| **4. controllo del risultato** | **validazione incrociata**: metrica calcolata su fold tenuti da parte | **validazione con l’umano nel ciclo**: gli **esperti rivedono** le uscite e le correggono |

Sopra: si addestra, poi si fa la validazione incrociata. Sotto: si ancora e si adatta, poi si valida con le persone.

<!-- SOURCE_VISUAL id="IS16-V09" source="L16-web.pdf" page="21" type="schema" description="Pipeline tradizionale e nuova a confronto nei quattro passi: dati, rappresentazione, adattamento, controllo" reason="Schema a due righe visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124034549.png)

**Che cosa significano i nuovi passi.** La pipeline tradizionale (raccolta dei dati → feature engineering → addestramento → validazione incrociata → rilascio) è affiancata da una pipeline **aggiuntiva** (analisi di dati supervisionati e non → foundation model pre-addestrato → ancoraggio semantico → adattamento zero/few-shot → validazione con l’umano nel ciclo):

- **ancoraggio semantico**: collegare la **conoscenza generale** del modello al **nostro dominio** (termini, segnali, regole) con **contesto, manuali ed esempi**;
- **adattamento zero/few-shot**: il compito viene appreso **da 0 a 10 esempi** dati nel prompt, **senza riaddestramento**;
- **umano nel ciclo**: gli esperti rivedono le uscite, **al posto di una metrica** su fold tenuti da parte.

#### **2.3. Foundation model: dai dati a molti compiti**

Un foundation model è addestrato **una volta** su **dati enormi e diversi** (testo, immagini, parlato, dati strutturati come tabelle e log, segnali di sensori come vibrazione e corrente) e poi **adattato** — con il **prompting**, il **fine-tuning** o lo **zero/few-shot** — a **molti compiti**: risposta a domande, analisi del sentiment, estrazione di informazioni, didascalie di immagini, riconoscimento di oggetti, esecuzione di istruzioni.

<!-- SOURCE_VISUAL id="IS16-V10" source="L16-web.pdf" page="23" type="schema" description="Foundation model addestrato su dati diversi e adattato a molti compiti" reason="Schema dati-modello-compiti visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124034646.png)

> 📌 **Un modello generale, addestrato una volta su dati diversi, adattato a molti compiti.**

#### **2.4. I foundation model in IS4**

| **Che cosa sono** | **Perché contano in IS4** |
|---|---|
| modelli su **larga scala** pre-addestrati su **dataset enormi e diversi** | **forte comprensione semantica** di codice, log e testo |
| imparano **rappresentazioni generali**, trasferibili tra compiti | **ragionamento su minacce mai viste** senza addestramento specifico |
| adattabili con **prompting, fine-tuning** o apprendimento **zero/few-shot** | **base** per sistemi industriali zero-shot e few-shot (**per progettare e addestrare, non per il rilascio in tempo reale**) |

**Non solo testo**: un foundation model può restituire **una classe o un numero**, oppure **descrivere a parole**:

| **Modello di classificazione**: l’uscita è una classe | **Modello descrittivo (linguistico)**: l’uscita è testo |
|---|---|
| Classe = cane | «Un cane marrone seduto in casa che guarda la fotocamera.» |

![](imgs/Pasted%20image%2020261124034718.png)

> 📌 **I foundation model spostano il ML dall’addestramento specifico per compito all’adattamento guidato dalla conoscenza.**

> ⚠️ **Un residuo di altri esempi.** La voce «ragionamento su minacce mai viste» proviene da un contesto di sicurezza informatica: in IS4 l’equivalente è il ragionamento su **guasti o anomalie mai visti**, come negli esempi di manutenzione del §2.7.

#### **2.5. Foundation model: un compito addestrato o una descrizione a parole**

![](imgs/Pasted%20image%2020261124034751.png)

| **1. Addestrato su un compito specifico** | **2. Descrivere a parole** |
|---|---|
| foundation model **addestrato su cellule etichettate**: immagine di una cellula del sangue → **classe = cellula leucemica** | foundation model **generale, usato con un prompt**: immagine di una cellula del sangue → «**Cellula grande con un nucleo grande e irregolare e poco citoplasma: sembra un blasto, compatibile con leucemia.**» |
| addestrato su **un compito specifico**: riconoscere un difetto industriale, una malattia, una cellula leucemica | il modello **descrive ciò che vede**: l’uscita è **testo libero**, quindi il modello è **linguistico** |
| l’uscita è **una classe o un numero**: si applicano le **metriche classiche** (accuratezza, matrice di confusione, ROC) | **nessuna classe e nessun numero** da confrontare: bisogna **spostarsi in un nuovo spazio di valutazione** |

> 📌 **Lo stesso foundation model può classificare (una classe o un numero) o descrivere (testo): cambia il modo in cui lo si valuta.**

#### **2.6. Modelli zero-shot e few-shot**

| **Che cosa NON sono** | **Che cosa SONO** |
|---|---|
| non sono **modelli specifici** | **paradigmi di inferenza e di adattamento** |
| non sono **algoritmi di addestramento** | **modi di usare un modello addestrato** su compiti o classi nuovi |
| non sono **architetture** | |

- **Zero-shot**: **nessun esempio**, solo i **nomi delle classi** (cane, gatto): un’immagine sconosciuta → «cane».
- **Few-shot**: **pochi esempi etichettati** (immagine 1: cane; immagine 2: gatto): un’immagine sconosciuta → «cane».

<!-- SOURCE_VISUAL id="IS16-V11" source="L16-web.pdf" page="26" type="schema" description="Zero-shot con i soli nomi delle classi e few-shot con due esempi etichettati" reason="Schema con immagini visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124034818.png)

**Zero-shot, one-shot, few-shot: stesso modello, prompt diversi.**

| Modalità | Prompt | Uscita |
|---|---|---|
| **zero-shot** (0 esempi) | `Translate "Good morning" to French` | *Bonjour* |
| **one-shot** (1 esempio) | `Translate to French: "Hello" -> "Bonjour"  "Goodbye" ->` | *Au revoir* |
| **few-shot** (3 esempi) | `Translate to French: "Hello" -> "Bonjour"  "Yes" -> "Oui"  "No" -> "Non"  "Thank you" ->` | *Merci* |

![](imgs/Pasted%20image%2020261124034832.png)

**Il modello è lo stesso** in tutti e tre i casi: cambia solo il prompt.

##### **2.6.1. L’idea dello zero-shot con gli animali**

1. **Addestramento**: il modello impara gli **attributi** delle categorie note:
   - **zebra**: strisce, criniera, quattro zampe, coda;
   - **muflone** (*bighorn sheep*): corna ricurve, mantello lanoso, quattro zampe, coda;
   - **giraffa**: collo lungo, macchie, quattro zampe, coda.
2. **Rilascio**: un **animale nuovo, mai visto** in addestramento. Attributi trovati: **simile al cavallo, quattro zampe, coda, ali**.

**Combinare attributi noti** (simile al cavallo + ali) corrisponde alla descrizione «**cavallo alato**»: una **classe mai vista in addestramento viene riconosciuta**.

<!-- SOURCE_VISUAL id="IS16-V12" source="L16-web.pdf" page="28" type="schema" description="Zero-shot con gli animali: attributi di zebra, muflone e giraffa e riconoscimento del cavallo alato mai visto" reason="Schema con immagini degli animali visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124034844.png)

> 💡 **Perché funziona (nota didattica).** Il modello non impara «zebra» come etichetta isolata, ma come combinazione di attributi in uno **spazio semantico** condiviso con le descrizioni testuali. Una classe nuova descritta con attributi noti cade in un punto di quello spazio, e l’immagine nuova viene confrontata con quel punto: è il confronto in uno spazio allineato immagine-testo della Lezione 9 (§8.6).

| **Zero-shot learning (ZSL)** | **Few-shot learning (FSL)** |
|---|---|
| il modello svolge un compito **senza vedere alcun esempio etichettato** | il modello si adatta usando **pochissimi esempi etichettati** (per esempio 1–10) |
| usa **solo istruzioni, descrizioni o conoscenza semantica** | si basa su **rappresentazioni forti** apprese durante il pre-addestramento |

> 📌 **Zero-shot e few-shot descrivono come si usa un modello, non che tipo di modello sia.**

#### **2.7. Esempi zero-shot e few-shot nella manutenzione**

##### **2.7.1. Zero-shot**

**Prompt**: «*Sei un analista di manutenzione industriale. Questo comportamento della macchina è anomalo? Segue un estratto del log: “Il motore mostra vibrazione crescente dopo l’avvio, la temperatura sale di 12 °C in 20 minuti e l’allarme A17 compare a intermittenza.”*»

**Uscita del modello**: «*Comportamento probabilmente anomalo. Lo schema suggerisce un possibile problema meccanico o termico, come usura dei cuscinetti, disallineamento o lubrificazione insufficiente. Si raccomanda un’ispezione di manutenzione.*»

**Comportamento del modello**: **nessun esempio fornito**; decisione basata **solo sulla conoscenza semantica**; **riconoscimento di pattern** (vibrazione, temperatura, allarme).

##### **2.7.2. Few-shot addestrato**

**Comportamento del modello**: **impara lo schema da pochi esempi etichettati**; **adatta la decisione** al nuovo caso; **coerenza più forte** su campioni simili.

**Prompt**: «*Questo è un nuovo compito di classificazione per la manutenzione.*
- *Esempio 1 (anomalo): “La vibrazione aumenta dopo l’avvio, la temperatura sale di 15 °C e l’allarme A17 compare a intermittenza.” Motivo: possibile usura dei cuscinetti o problema di lubrificazione.*
- *Esempio 2 (normale): “La temperatura resta stabile, la vibrazione è in tolleranza e non viene segnalato alcun allarme durante il ciclo operativo.” Motivo: condizione operativa normale.*
- *Esempio 3 (anomalo): “L’assorbimento di corrente aumenta a carico costante, la vibrazione è sopra soglia e il motore produce un rumore anomalo.” Motivo: possibile attrito meccanico o disallineamento.*

*Ora analizza questo caso: “Il motore mostra vibrazione crescente dopo l’avvio, la temperatura sale di 12 °C in 20 minuti e l’allarme A17 compare a intermittenza.”*»

**Uscita del modello**: «*Caso di manutenzione probabilmente anomalo. I sintomi sono vicini all’Esempio 1: vibrazione crescente, deriva della temperatura e allarme A17 intermittente. Una causa possibile è l’usura dei cuscinetti, il disallineamento o la lubrificazione insufficiente.*»

> ⚠️ **Un residuo nella slide.** La descrizione del comportamento cita «la nuova famiglia di attacchi»: è un residuo di un esempio di sicurezza informatica, mentre il caso mostrato è di manutenzione.

##### **2.7.3. Usare un LLM generico (ChatGPT, Gemini, …)**

Dall’**estrazione delle feature e classificazione** al «**parlare con l’LLM**». **Oggi non è ancora possibile con alta accuratezza**: è un’**ipotesi, non una misura**.

**Prompt**: «*Sei un analista di manutenzione industriale. Analizza il seguente evento e rispondi: 1) il comportamento è probabilmente normale o anomalo? 2) se anomalo, descrivi lo schema di guasto più probabile; 3) assegna un livello di confidenza basso / medio / alto; 4) suggerisci i primi controlli di manutenzione. Evento: macchina MTR-17; fase: avvio; vibrazione: in aumento dopo 5 minuti; temperatura: +12 °C in 20 minuti; assorbimento di corrente: leggermente sopra il riferimento; allarme: A17 intermittente; nota dell’operatore: rumore anomalo dal carter del motore.*»

**Risposta desiderata**: «*1) Comportamento probabilmente anomalo. 2) Lo schema è compatibile con un guasto meccanico o termico iniziale: la combinazione di vibrazione crescente, deriva della temperatura, assorbimento leggermente superiore, allarme A17 intermittente e rumore anomalo può indicare usura dei cuscinetti, disallineamento, attrito meccanico o lubrificazione insufficiente. 3) Confidenza: media. Le evidenze sono coerenti con uno schema di allarme di manutenzione, ma il modello non ha accesso a rapporti storici, soglie specifiche della macchina o casi A17 precedenti. 4) Primi controlli: ispezionare i cuscinetti; verificare la lubrificazione; controllare l’allineamento dell’albero; confrontare lo spettro di vibrazione con il riferimento precedente; consultare lo storico dell’allarme A17 nel sistema di manutenzione.*»

> 💡 **Che cosa manca (nota didattica).** La risposta stessa dichiara il limite: senza storico, soglie e casi precedenti la confidenza resta media. È esattamente ciò che una RAG sui rapporti di manutenzione (Lezione 15, caso 1) aggiungerebbe, e ciò che una valutazione su casi etichettati (§2.8) dovrebbe misurare prima di fidarsi.

#### **2.8. Valutare i foundation model che restituiscono testo, non una classe**

**Perché la valutazione non è banale.**

- I foundation model che restituiscono **una classe o un numero** si valutano con le **metriche classiche**. Quelli che restituiscono **testo** (modelli linguistici) richiedono un **nuovo spazio di valutazione**.
- Le previsioni sono spesso **semantiche ed esplicative**, non semplici etichette di classe.
- I modelli zero/few-shot operano in condizioni di **scarsità di dati** e **spostamento di distribuzione**.
- Le **ipotesi della validazione incrociata classica possono non valere**.

![](imgs/Pasted%20image%2020261124035027.png)

**La valutazione classica ha bisogno di etichette di classe e punteggi** (validazione incrociata con fold di test, curva ROC). **L’uscita di un foundation model è testo libero** («Comportamento probabilmente anomalo. Lo schema è compatibile con un guasto meccanico o termico iniziale…»): **nessuna etichetta e nessun punteggio** su cui fissare una soglia, quindi **non si può confrontare fold per fold**.

#### **2.9. Strategie di valutazione: quattro modi per verificare**

| Strategia | Domanda |
|---|---|
| **1. metriche basate sulle etichette** | ci sono **etichette vere**? Allora si contano le risposte giuste e sbagliate (matrice di confusione, accuratezza) |
| **2. valutazione basata su scenari** | funziona ancora su **situazioni nuove**? Si testa su **scenari mai visti** o su **dati più recenti** |
| **3. confidenza e coerenza** | **quanto è sicuro** il modello, ed è **stabile**? Si chiede una confidenza e si misura l’errore su di essa |
| **4. validazione con l’umano nel ciclo** | gli **esperti concordano** con le risposte e le spiegazioni? Si misura il **tasso di accordo** |

##### **2.9.1. Valutazione 1: metriche basate sulle etichette**

Prompt: «*Vedi un cane? Rispondi sì o no.*» Su un’immagine il foundation model risponde «No»: **sbagliato, il cane c’è**. La stessa domanda su $N=100$ immagini, **50 con un cane e 50 senza**:

| | Previsto (falso) | Previsto (vero) |
|---|---:|---:|
| **Reale (falso)** | $TN=44$ | $FP=6$ |
| **Reale (vero)** | $FN=12$ | $TP=38$ |

Corrette: $38+44=82$, **accuratezza** $=82/100=0{,}82$; errori: $6+12=18$ (FP e FN).

<!-- SOURCE_VISUAL id="IS16-V13" source="L16-web.pdf" page="35" type="schema" description="Foundation model che risponde no su un'immagine con un cane e matrice di confusione su 100 immagini" reason="Schema con immagine visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124035106.png)

> 💡 **Il trucco (nota didattica).** Vincolare la risposta a «sì o no» trasforma un modello linguistico in un classificatore: le metriche classiche tornano utilizzabili. Il recall sui cani è $38/50=76\%$: il modello ne perde uno su quattro, un’informazione che l’accuratezza da sola (82%) non dà.

##### **2.9.2. Valutazione 2: valutazione basata su scenari**

**Foundation model open source: la model card.** I **dati di addestramento dichiarati** dagli autori sono manuali e log delle **famiglie di motori A e B**. Sapendolo, si possono costruire **test non usati per l’addestramento**.

| Insieme di test | Casi | Accuratezza |
|---|---|---:|
| **1. scenari noti** (nei dati di addestramento) | 24 casi di motori delle famiglie A e B | $22/24=0{,}92$ |
| **2. scenari nuovi** (mai usati nell’addestramento) | 24 casi della nuova famiglia di motori C | $18/24=0{,}75$ |

> 📌 **Il punteggio che conta è quello sugli scenari nuovi**: un **forte calo da 0,92 a 0,75** mostra **quanto bene il modello generalizza**.

Se il foundation model **non si può riaddestrare**, né fare il fine-tuning dei suoi ultimi strati (si vedrà più avanti nel corso, Lezione 22), si lavora sulla **costruzione di esempi migliori nel prompt** per ridurre l’errore.

> 💡 **Il legame con il leakage (nota didattica).** Testare un foundation model su manuali dei motori A e B è come testare un modello classico sul suo training set: i casi possono essere già stati «visti» durante il pre-addestramento. La model card serve proprio a sapere che cosa il modello ha visto, e quindi quali test sono davvero nuovi (Lezione 7).

##### **2.9.3. Valutazione 3: confidenza e coerenza**

Prompt: «*Vedi un cane? Con quale confidenza (da 0 a 1)?*» Risposta: «*Sì*», **confidenza 0,67**.

**Errore dalla confidenza** (log loss), con $p$ = confidenza data alla risposta corretta (adimensionale):

$$\text{errore}=-\ln p.$$

| Caso | Situazione | $p$ | Errore |
|---|---|---:|---:|
| **1** | il modello **ha ragione**, confidenza 0,67 | 0,67 | **0,40**: piccolo |
| **2** | **sbagliato ma sicuro** («No», confidenza 0,95) | 0,05 | **3,00**: grande |

<!-- SOURCE_VISUAL id="IS16-V14" source="L16-web.pdf" page="37" type="grafico" description="Curva dell'errore -ln p in funzione della confidenza data alla risposta corretta, con i due casi" reason="Curva visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124035141.png)

> 💡 **Perché $p=0{,}05$ nel caso 2 (nota didattica).** Il modello dice «No» con confidenza 0,95: alla risposta giusta («Sì») assegna quindi $1-0{,}95=0{,}05$. La log loss punisce severamente gli errori sicuri, come nella Lezione 14 (§1.4): un modello che dice sempre «non lo so» con confidenza 0,5 avrebbe errore $\ln 2=0{,}69$ su ogni caso, meglio di un errore sicuro.

##### **2.9.4. Valutazione 4: validazione con l’umano nel ciclo**

Il flusso: estratto di log (testo) → foundation model → **risposta + spiegazione** (testo libero) → **revisione dell’esperto**, che **concorda** oppure **corregge**; le correzioni **migliorano prompt ed esempi**.

![](imgs/Pasted%20image%2020261124035239.png)

**Esempio**: 20 risposte riviste; **concordanti 17**; corrette 3 (2 falsi allarmi, 1 mancato rilevamento).

$$\text{tasso di accordo}=\frac{17}{20}=0{,}85,\qquad \text{falsi allarmi}=\frac{2}{20}=0{,}10.$$

- L’esperto controlla **la spiegazione**, non solo l’etichetta.
- È **lento e costoso**: lo si usa **su un campione**.
- Le correzioni suggeriscono un’azione: **fine-tuning** con nuovi campioni, oppure un **insieme migliore di esempi few-shot**.

> 💡 **Il tasso di accordo come metrica (nota didattica).** È lo stesso principio dell’accordo tra annotatori della Lezione 8: se due esperti concordano tra loro solo nell’80% dei casi, un modello che concorda con un esperto all’85% non può essere giudicato «sbagliato» nel restante 15% senza un terzo parere. Con soli 20 casi l’intervallo al 95% sul tasso è circa $\pm 0{,}16$ (Lezione 14, §4.2).

---

### **3. Teoria: altre valutazioni dell’accuratezza — salienza, test di iniezione, sensibilità**


#### **3.1. Provare a modificare gli input**

Una rete NN1 riconosce un cane con **0,95**. Inserendo nell’immagine un oggetto estraneo (una **teiera**), la rete NN2 lo riconosce con **0,89**. Modificare l’input in modo controllato è un **test di iniezione**: si osserva se e quanto la previsione dipende da elementi che non dovrebbero contare.

<!-- SOURCE_VISUAL id="IS16-V15" source="L16-web.pdf" page="40" type="figura" description="Immagine del cane con e senza una teiera inserita e le confidenze 0.95 e 0.89" reason="Immagini visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124035315.png)

#### **3.2. Controllare anche con le mappe di salienza**

| Rete | Previsione | Mappa di salienza |
|---|---|---|
| **NN1** | cane = 0,95 | guarda i pixel **sbagliati** (il gatto, il pavimento) |
| **NN2** | cane = 0,92 | guarda il cane |

> ⚠️ **NN1: la previsione è corretta, ma guarda i pixel sbagliati!!** Ci si può fidare di un modello che **sembra accurato**? È lo stesso caso della Lezione 11 (§5.8.1): un’accuratezza alta ottenuta per la ragione sbagliata.

<!-- SOURCE_VISUAL id="IS16-V16" source="L16-web.pdf" page="41" type="figura" description="Mappe di salienza di NN1 (pixel sbagliati) e NN2 (il cane)" reason="Mappe visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124035338.png)

#### **3.3. Aggiungere una giusta dose di rumore**

**Analisi di sensibilità** di un modello: **stessa rete, stesso cane, cambia solo il rumore**. Senza rumore NN1 risponde **cane = 0,95**; con un po’ di rumore aggiunto all’immagine risponde **camion = 0,78**.

<!-- SOURCE_VISUAL id="IS16-V17" source="L16-web.pdf" page="42" type="figura" description="Lo stesso cane classificato come cane 0.95 e, con rumore aggiunto, come camion 0.78" reason="Immagini visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124035351.png)

> 💡 **Fragilità (nota didattica).** Un rumore che una persona quasi non nota fa cambiare classe con confidenza alta: è la stessa fragilità degli esempi avversari della Lezione 2. Un modello che cade così facilmente non è affidabile su immagini reali, che contengono sempre rumore di sensore e compressione.

#### **3.4. L’analisi di sensibilità**

> 📌 **Analisi di sensibilità.** È un insieme di metodi per **quantificare come l’incertezza sull’uscita** di un modello sia legata all’**incertezza sui suoi input** o sulla sua **rappresentazione interna**.

Valuta quanto il modello è «sensibile»:

| **1. Ai dati su cui è costruito** | **2. Alle fluttuazioni dei parametri** |
|---|---|
| **esempio**: si mescolano i dati tra train e validation e **l’accuratezza cambia molto**! | **esempio**: la **NPU a 8 bit** dà risultati **diversi** dal mio TensorFlow sul PC! |
| **perché**: un’altra divisione casuale mette **altri campioni difficili** nel test set | **perché**: i pesi `float32` **arrotondati a `int8`** cambiano ogni prodotto; l’uscita si sposta **al massimo di 0,003** |
| divisione 0: 88,9%; divisione 1: 93,3%; divisione 2: 100,0% (arancione = campioni di test; stessi dati, cambia solo la divisione) | `float32`: −1,1842 → arrotondato al livello `int8` −56 = −1,1810: errore 0,0032 (livelli vicini: −57 = −1,2021; −55 = −1,1599) |

<!-- SOURCE_VISUAL id="IS16-V18" source="L16-web.pdf" page="43" type="grafico" description="Tre divisioni train/test con accuratezze diverse e quantizzazione di un peso float32 sui livelli int8" reason="Grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124035433.png)

> 💡 **Il passo di quantizzazione (nota didattica).** I livelli `int8` vicini distano $1{,}2021-1{,}1810=0{,}0211$: è il passo della quantizzazione. L’errore massimo per un singolo peso è metà del passo, circa 0,0106; qui è 0,0032. Una NPU (*Neural Processing Unit*) lavora in `int8` per consumare meno energia e memoria (Lezione 23); la domanda è se la somma di tanti piccoli errori sposta le decisioni.

##### **3.4.1. Sensibilità in pratica: due esperimenti rapidi**

| **A. Dati: 30 divisioni train/test casuali** | **B. Parametri: pesi `float32` → `int8`** |
|---|---|
| stesso modello, stessi dati, **cambia solo la divisione**: accuratezza **dall’82% al 100%** (media **91,9%**, deviazione standard **4,5%**) | accuratezza sui 45 campioni di test: **88,9% → 88,9%** (nessun cambiamento) |
| **una divisione fortunata può lusingare** | probabilità di classe del campione 0: $(0{,}014;\ 0{,}181;\ 0{,}806)\to(0{,}014;\ 0{,}180;\ 0{,}807)$ |
| | variazione massima della probabilità della classe vincente su tutti i campioni di test: **0,003** |

<!-- SOURCE_VISUAL id="IS16-V19" source="L16-web.pdf" page="44" type="grafico" description="Istogramma delle accuratezze di 30 divisioni casuali e confronto delle probabilità float32 e int8" reason="Grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124035510.png)

> 📌 **Lettura.** A: riportare una sola divisione può dare qualunque valore tra 82% e 100%; servono più divisioni e la loro deviazione standard (Lezione 14). B: per questo **piccolo modello** la NPU a 8 bit è **sicura**; per un **modello fragile** potrebbe non esserlo.

---

### **4. Strumenti: il dataset Iris**


Partire da qualcosa di noto ogni volta che si prova un nuovo strumento.

#### **4.1. Regola d’oro #1: usare un dataset semplice e noto per iniziare**

- Per **testare le proprie conoscenze**, i propri **strumenti di elaborazione**, i **modelli**, i **metodi di apprendimento**.
- **Non partire dal dataset reale della propria applicazione!!**
- Partire da un **dataset noto e facile**, con **uscite note**:
  - grafici, accuratezza e risultati sono **ben documentati**: si possono **confrontare le proprie uscite con la letteratura**;
  - **se la pipeline fallisce su di esso, il bug è nel codice, non nei dati.**

#### **4.2. EDA: l’esempio del fiore Iris**

- Classificare i fiori di iris in **3 classi**: *Setosa*, *Versicolor*, *Virginica*.
- **4 feature** [cm]: **lunghezza e larghezza del sepalo**, **lunghezza e larghezza del petalo**.
- **150 campioni** (50 per classe).
- **Input**: un vettore $x\in\mathbb{R}^4$ [cm]. **Output**: un’etichetta di classe in $\{1,2,3\}$.

<!-- SOURCE_VISUAL id="IS16-V20" source="L16-web.pdf" page="47" type="figura" description="Fiore di iris con sepalo e petalo indicati" reason="Fotografia visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**I dati.** Il dataset è stato introdotto dallo statistico e biologo britannico **Ronald Fisher** nel suo articolo del **1936** «*The use of multiple measurements in taxonomic problems*», per **quantificare la variazione morfologica** dei fiori di iris di **tre specie affini**. Fisher è descritto come «un genio che quasi da solo ha creato le fondamenta della scienza statistica moderna».

<!-- SOURCE_VISUAL id="IS16-V21" source="L16-web.pdf" page="48" type="figura" description="Ritratto di Ronald Fisher" reason="Fotografia visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124035634.png)

**Le classi**: *Iris versicolor*, *Iris virginica*, *Iris setosa*.

<!-- SOURCE_VISUAL id="IS16-V22" source="L16-web.pdf" page="49" type="figura" description="Fotografie delle tre specie: Iris versicolor, virginica e setosa" reason="Fotografie visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124035618.png)

**Le feature.** Di ogni campione sono state misurate **quattro feature**: lunghezza e larghezza di **sepali e petali**, in centimetri. Sulla base della loro combinazione, Fisher sviluppò un modello per **distinguere le specie** tra loro (l’analisi discriminante lineare, LDA, Lezione 17).

**La scheda del dataset**: nome del file *Fisher’s Iris*; soggetti: agricoltura, dataset famosi; storia: *Fisher’s Irises*; riferimento: Fisher, R. A. (1936), *Annals of Eugenics* 7, 179–188; autorizzazione: uso libero. È un dataset reso famoso da Fisher, che lo usò per illustrare i principi dell’**analisi discriminante**: **6 variabili, 150 osservazioni**. Variabili: 1. `Species_No` (specie come codice); 2. `Species_Name` (nome della specie); 3. `Petal_Width`; 4. `Petal_Length`; 5. `Sepal_Width`; 6. `Sepal_Length`.

**I dati grezzi** (unità: cm; 150 righe: 50 setosa, 50 versicolor, 50 virginica):

| | Sepal.Length | Sepal.Width | Petal.Length | Petal.Width | Species |
|---:|---:|---:|---:|---:|---|
| 1 | 5,1 | 3,5 | 1,4 | 0,2 | setosa |
| 2 | 4,9 | 3,0 | 1,4 | 0,2 | setosa |
| 3 | 4,7 | 3,2 | 1,3 | 0,2 | setosa |
| 4 | 4,6 | 3,1 | 1,5 | 0,2 | setosa |
| 5 | 5,0 | 3,6 | 1,4 | 0,2 | setosa |
| 6 | 5,4 | 3,9 | 1,7 | 0,4 | setosa |
| 7 | 4,6 | 3,4 | 1,4 | 0,3 | setosa |
| 8 | 5,0 | 3,4 | 1,5 | 0,2 | setosa |
| … | … | … | … | … | … |

#### **4.3. EDA: $Y=\text{FUNC}(X)$, tutto qui!!!**

$$X\in\mathbb{R}^{4\times N}=\begin{bmatrix}5{,}1&4{,}9&6{,}3&7{,}0&\cdots\\3{,}5&3{,}0&3{,}3&3{,}2&\cdots\\1{,}4&1{,}4&6{,}0&4{,}7&\cdots\\0{,}2&0{,}2&2{,}5&1{,}4&\cdots\end{bmatrix},\qquad Y\in\{1,2,3\}^N=\begin{bmatrix}1&1&3&2&\cdots\end{bmatrix}.$$

Per ogni dataset nel machine learning o in una toolbox, **tutto consiste nel creare $X$ e $Y$**. $X$: le feature (qui $4\times N$, [cm]). $Y$: l’ID di classe, o il valore da apprendere ($1\times N$); 1 = «setosa».

#### **4.4. EDA: i boxplot di Fisher**

Un boxplot riassume ogni feature con la **mediana**, i **quartili** (la scatola) e gli **outlier** (Lezione 10). **Input**: lunghezza del sepalo [cm] di 150 fiori (50 per specie). **Output**: 3 scatole, una per specie.

<!-- SOURCE_VISUAL id="IS16-V23" source="L16-web.pdf" page="54" type="grafico" description="Boxplot della lunghezza del sepalo per le tre specie di iris" reason="Scatole visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124035823.png)

#### **4.5. EDA: i grafici paralleli**

**La visualizzazione è la chiave della comprensione!** (Un’immagine vale più di mille righe di tabella.) In un grafico a coordinate parallele ogni fiore è una linea spezzata che attraversa un asse verticale per ogni feature, colorata per specie (Lezione 11, §11.7).

<!-- SOURCE_VISUAL id="IS16-V24" source="L16-web.pdf" page="55" type="grafico" description="Coordinate parallele delle quattro feature di Iris colorate per specie" reason="Linee visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124035834.png)

**Con un’altra toolbox.** Nota: qui servono **le stesse unità o una scala simile**!

<!-- SOURCE_VISUAL id="IS16-V25" source="L16-web.pdf" page="56" type="grafico" description="Coordinate parallele di Iris prodotte con un'altra toolbox" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124035900.png)

**Armonizzazione.** Con la media e la deviazione standard si possono **armonizzare** i valori (z-score):

$$z=\frac{x-\mu}{\sigma},$$

con $x$ valore grezzo [cm], $\mu$ media della feature [cm], $\sigma$ sua deviazione standard [cm], $z$ adimensionale. **Esempio**: $x=6{,}0$ cm, $\mu=5{,}8$ cm, $\sigma=0{,}8$ cm: $z=(6{,}0-5{,}8)/0{,}8=0{,}25$. Dopo la trasformazione la **media di tutti i valori è 0** e la **deviazione standard è 1**.

<!-- SOURCE_VISUAL id="IS16-V26" source="L16-web.pdf" page="57" type="grafico" description="Coordinate parallele di Iris dopo lo z-score" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124035914.png)

**Un caso più complesso.** In questo caso è **più difficile capire quale feature aiuterà meglio la classificazione**.

![](imgs/Pasted%20image%2020261124035927.png)


<!-- SOURCE_VISUAL id="IS16-V27" source="L16-web.pdf" page="58" type="grafico" description="Grafico parallelo di un caso più complesso (dati del Titanic)" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Leggere i rapporti.** Il grafico mostra anche il **rapporto tra i valori (netti) delle feature**: la **larghezza di ogni banda è la quota di persone** che seguono quel percorso.

<!-- SOURCE_VISUAL id="IS16-V28" source="L16-web.pdf" page="59" type="grafico" description="Grafico a categorie parallele del Titanic con bande proporzionali alle quote" reason="Bande visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124035947.png)

**Che cosa si vede?** **Nessun bambino nell’equipaggio**; **soprattutto maschi**; **quasi nessun bambino in prima classe**; eccetera. Informazioni significative e intuizioni preziose si possono estrarre **solo osservando** il grafico. È lo stesso grafico a categorie parallele (alluvionale) dei dati del Titanic presentato in altre lezioni del corso.

<!-- SOURCE_VISUAL id="IS16-V29" source="L16-web.pdf" page="60" type="grafico" description="Grafico alluvionale del Titanic con le osservazioni su equipaggio, sesso e classe" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.6. EDA: i grafici di correlazione e la PCA**

- Ogni pannello disegna **una feature contro un’altra**: una nuvola **allungata** indica feature **correlate**, una nuvola **rotonda** feature **indipendenti**.
- Si comincia a capire le **correlazioni** tra le feature e **come i cluster (le classi) si sovrappongono**.
- **Attenzione: la correlazione non è causalità.**

<!-- SOURCE_VISUAL id="IS16-V30" source="L16-web.pdf" page="61" type="grafico" description="Matrice di scatter plot a coppie delle quattro feature di Iris" reason="Nuvole di punti visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124040127.png)

**PCA sul dataset Iris.** La PCA **riduce la dimensionalità** di un dataset **conservando il più possibile le sue caratteristiche spaziali** (**non servono etichette**): **4 feature in ingresso** $F_1,\dots,F_4$, **4 componenti ordinate in uscita** $F'_1,\dots,F'_4$ (Lezione 18).

![](imgs/Pasted%20image%2020261124040223.png)


![](imgs/Pasted%20image%2020261124040232.png)

**Armonizzazione dei dati di Iris**: **nessun dato mancante**, **nessun errore di etichettatura**, **nessuno scaling necessario**. Si userà il dataset Iris di Fisher **come strumento di debug**! Negli esempi successivi si usa la notazione di **Google TensorFlow**, una libreria software **libera e open source** per la programmazione **dataflow e differenziabile** su molti compiti.

**Produrre il pairplot** in Python:

```python
seaborn.pairplot(dataset,
                 hue="species", size=2,
                 diag_kind="kde")
plt.show()
```

**Input**: il dataset (150 righe × 5 colonne). **Output**: una griglia $4\times 4$ di grafici.

<!-- SOURCE_VISUAL id="IS16-V31" source="L16-web.pdf" page="64" type="grafico" description="Pairplot seaborn di Iris con le densità sulla diagonale" reason="Griglia di grafici visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124040302.png)

> 💡 **I parametri (nota didattica).** `hue="species"` colora i punti per specie; `diag_kind="kde"` mette sulla diagonale la stima di densità (*Kernel Density Estimation*) di ogni feature invece dell’istogramma. Nelle versioni recenti di seaborn il parametro `size` è stato rinominato `height`.

#### **4.7. Prepararsi ad apprendere**

Dai dati categorici grezzi (per esempio «dog», «apple», «10.0.0.5») si arriva all’addestramento in quattro passi:

1. **codifica**: codifica intera, codifica one-hot;
2. **preparare le feature di input**;
3. **dividere** train / test;
4. **addestramento**: rete neurale TensorFlow.

![](imgs/Pasted%20image%2020261124040311.png)

---

### **5. Teoria: codifica intera e one-hot — l’apprendimento può dipendere da come si elencano le classi**


Clustering e classificazione con dati categorici: apprendimento supervisionato e non supervisionato su input non numerici.

#### **5.1. Le feature categoriche in IS4**

Nel machine learning classico si addestrano i modelli su **matrici numeriche** $X$ (input) e $Y$ (output). **Ma che cosa succede quando input e output non sono solo numeri?** I dataset industriali **raramente contengono solo valori numerici**: log ed eventi includono spesso **attributi categorici** che vanno gestiti con cura. **Input**: una colonna di etichette (testo). **Output**: una colonna di numeri utilizzabile da un modello.

<!-- SOURCE_VISUAL id="IS16-V32" source="L16-web.pdf" page="68" type="figura" description="Una colonna di etichette categoriche trasformata in numeri" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124040342.png)

**Esempi in applicazioni industriali e di manutenzione:**

| Feature | Categorie possibili |
|---|---|
| tipo di macchina | pompa, motore, nastro trasportatore, compressore, braccio robotico |
| tipo di guasto | usura dei cuscinetti, surriscaldamento, perdita, disallineamento, guasto del sensore |
| codice di allarme | A17, T02, VIB_HIGH, TEMP_WARN, PRESS_LOW |
| azione di manutenzione | ispezione, lubrificazione, sostituzione, ricalibrazione, pulizia |
| turno dell’operatore | mattina, pomeriggio, notte |
| linea di produzione | linea 1, linea 2, linea 3 |
| stato del componente | normale, attenzione, critico, offline |
| fornitore | fornitore A, B, C |
| tipo di materiale | acciaio, alluminio, plastica, composito |
| famiglia di prodotto | standard, personalizzato, prototipo, ricambio |

#### **5.2. Le feature ordinali si possono mappare su interi**

Non tutte le feature categoriche sono uguali: alcune categorie formano una **scala ordinata**, in cui la mappatura su interi **ha senso**:

- le categorie hanno un **ordine naturale**;
- le differenze riflettono un’**intensità crescente o decrescente**;
- i valori interi **conservano la relazione semantica**.

**Esempi**: priorità: bassa → 1, media → 2, alta → 3; gravità: informativo → 1, attenzione → 2, critico → 3; fiducia: fidato < sconosciuto < sospetto.

<!-- SOURCE_VISUAL id="IS16-V33" source="L16-web.pdf" page="70" type="figura" description="Scala ordinale mappata su interi" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Codifica intera.** È una trasformazione delle feature che **mappa i valori categorici su numeri interi**, **conservando un ordine esplicito** quando le categorie rappresentano una **scala ordinale**.

**Perché qui è valida**: l’**ordine ha un significato semantico**; **valori più alti** significano **effetto o importanza maggiori**; la **distanza tra i valori** ha un significato interpretabile. **Esempio**: $\{\text{bassa},\text{media},\text{alta}\}\to\{1,2,3\}$, e $3-2=2-1$: **lo stesso «passo»**.

> ⚠️ **Il passo uguale è un’ipotesi (nota didattica).** Codificare 1, 2, 3 assume che la distanza tra «bassa» e «media» sia uguale a quella tra «media» e «alta». Se il passaggio a «critico» è molto più grave degli altri, si possono scegliere valori non equispaziati (per esempio 1, 2, 10), ma la scelta va motivata dal dominio.

#### **5.3. Label encoding**

Il **label encoding** assegna **un intero univoco a ogni valore distinto**, **nell’ordine di prima comparsa**.

| `dst_ip` | etichetta |
|---|---:|
| 192.168.1.10 | 0 |
| 10.0.0.5 | 1 |
| 172.16.0.3 | 2 |
| 192.168.1.10 | 0 |
| 192.168.1.11 | 3 |

- **Conserva l’identità, non la semantica.**
- **Introduce un ordine numerico artificiale** (può essere dannoso con alcuni metodi di addestramento!).

**Lo spazio degli IP** è gerarchico: rete (192.168, 10.0, 172.16) → sottorete (192.168.1, 10.0.0, 172.16.0) → host. **192.168.1.10 e 192.168.1.11 sono fratelli nell’albero**, ma ricevono le etichette **0 e 3**.

<!-- SOURCE_VISUAL id="IS16-V34" source="L16-web.pdf" page="72" type="schema" description="Albero degli indirizzi IP (rete, sottorete, host) con le etichette del label encoding" reason="Albero visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124040508.png)

##### **5.3.1. Label encoding su una feature nominale: i colori**

Il label encoding (intero) di una feature **nominale** assegna un intero a ogni colore: rosso → 1, verde → 2, blu → 3.

- **Facilmente reversibile**: 2 → verde.
- **Non esiste un ordine naturale**: il verde sta davvero «tra» il rosso e il blu?
- Il modello leggerebbe comunque $3-2=2-1$ come «**lo stesso passo**».
- **L’ordine è arbitrario.**

> ⚠️ **Attenzione durante l’addestramento.** Una rete neurale può imparare che confondere il **blu con il rosso** è un salto di $3-1=2$, quindi **più grave** che confondere il **verde con il rosso**, un salto di $2-1=1$. I colori **non sono ordinati**: questa distanza è **artificiale**.

##### **5.3.2. Quando si può usare il label encoding?**

| **Accettabile** | **Sconsigliato** |
|---|---|
| **modelli ad albero** (alberi di decisione, random forest) | **k-means**, **DBSCAN** |
| **hashing o indicizzazione** | **qualunque modello basato su distanze o lineare** |
| come **passo prima degli embedding** | |

> ⚠️ **Il «lazy encoding»** non è una tecnica nuova: è una **cattiva abitudine**, usare il label encoding solo perché è il più semplice e veloce, **senza ragionare** sul significato della feature. Per gli indirizzi IP l’intero è **arbitrario** e **nasconde la gerarchia** (rete / sottorete / host): il modello ha bisogno di **più divisioni** per «riscoprire» che 192.168.1.1 e 192.168.1.2 sono correlati. **BISOGNA CODIFICARE CON GIUDIZIO!!!**

> 💡 **Perché gli alberi tollerano il label encoding (nota didattica).** Un albero divide con soglie del tipo $x\le s$: con abbastanza divisioni può isolare qualunque etichetta, quindi l’ordine artificiale gli costa solo profondità, non correttezza. Un modello lineare invece moltiplica $x$ per un peso: con l’etichetta 3 il contributo è tre volte quello dell’etichetta 1, una relazione priva di senso per i colori.

#### **5.4. La codifica one-hot**

**Perché serve una codifica.**

- Le categorie **non hanno un ordine naturale**.
- Una mappatura diretta (tcp = 1, udp = 2) **crea distanze artificiali**.
- Le distanze diventerebbero **prive di significato**.
- La **codifica one-hot** risolve il problema **elegantemente**, ma **aumenta molto la dimensione** del vettore delle feature: nell’esempio, **da 1 a 3 feature**.

**Label encoding** (una colonna numerica):

| Cibo | Cat.# | Calorie |
|---|---:|---:|
| mela | 1 | 95 |
| pollo | 2 | 231 |
| broccoli | 3 | 50 |

**One-hot encoding** (una colonna per categoria):

| Mela | Pollo | Broccoli | Calorie |
|---:|---:|---:|---:|
| 1 | 0 | 0 | 95 |
| 0 | 1 | 0 | 231 |
| 0 | 0 | 1 | 50 |

**Esempio di rete**: $\text{protocollo}\in\{\text{tcp},\text{udp},\text{icmp}\}$: tcp = $[1,0,0]$, udp = $[0,1,0]$, icmp = $[0,0,1]$; **1 feature → 3 feature**.

> 💡 **Distanze uniformi (nota didattica).** Due vettori one-hot diversi hanno sempre distanza euclidea $\sqrt2$ e prodotto scalare 0: tutte le categorie sono **equidistanti**, nessuna è «tra» le altre. È la proprietà voluta per le categorie nominali, e lo stesso limite visto per i token nella Lezione 13 (§3.3): nessuna somiglianza tra categorie viene rappresentata.

**Dopo la codifica one-hot.** Permette:

- un **calcolo valido delle distanze**;
- una **stima significativa della covarianza**.

**L’aumento di dimensionalità è atteso e voluto.** Si può poi applicare la **PCA** per **ridurre la dimensionalità**, **eliminare la ridondanza** e **permettere la visualizzazione**: testo o JSON con tutti i valori categorici → codifica one-hot ($x$, $d$ valori) → PCA → $z$ compatto ($k<d$ valori).

![](imgs/Pasted%20image%2020261124040549.png)

#### **5.5. La codifica dummy**

| id | X | X=a | X=b | X=c |
|---:|---|---:|---:|---:|
| 1 | a | 1 | 0 | 0 |
| 2 | c | 0 | 0 | 1 |
| 3 | a | 1 | 0 | 0 |
| 4 | b | 0 | 1 | 0 |

Eliminando la colonna X=c:

| X=a | X=b |
|---:|---:|
| 1 | 0 |
| 0 | 0 |
| 1 | 0 |
| 0 | 1 |

> 📌 **Codifica dummy.** È la **one-hot con una colonna eliminata**: la categoria eliminata è quella **con tutti zeri** ($(0,0)$ significa c), il che **evita colonne ridondanti** (**multicollinearità**).

![](imgs/Pasted%20image%2020261124040601.png)

> 💡 **Perché la ridondanza dà fastidio (nota didattica).** Con la one-hot completa le colonne sommano sempre a 1: X=c è determinata dalle altre due ($1-\text{X=a}-\text{X=b}$). In una regressione lineare con intercetta questa dipendenza esatta rende la matrice $X^\top X$ non invertibile e i coefficienti non unici (Lezione 17). Con $C$ categorie la dummy usa $C-1$ colonne; per le reti neurali e gli alberi di solito si tiene la one-hot completa.

#### **5.6. Altri metodi di codifica per i dati categorici**

Esistono molte altre tecniche per codificare dati categorici: **one-hot**, **dummy**, **effect**, **label**, **ordinale**, **count** e **binaria**.

<!-- SOURCE_VISUAL id="IS16-V35" source="L16-web.pdf" page="78" type="figura" description="Panoramica dei metodi di codifica dei dati categorici" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124040610.png)

> 💡 **Tre di questi in breve (nota didattica).** La **count encoding** sostituisce ogni categoria con il numero di volte che compare nel dataset; la **binary encoding** scrive l’indice della categoria in binario (con 1000 categorie bastano 10 colonne invece di 1000); la **effect encoding** è come la dummy, ma la categoria di riferimento è codificata con $-1$ invece che con tutti zeri. Ogni scelta è un compromesso tra dimensione del vettore e informazione che si introduce.

---

### **6. Strumenti: programmazione, normalizzazione e partizioni train/test**


Gli strumenti di base per iniziare.

#### **6.1. Codifica one-hot in MATLAB**

```matlab
labels = ["red"; "blue"; "red"; "green"; "yellow"; "blue"]; % 6 elementi
labels = categorical(labels); % solo 4 etichette diverse
categories(labels)
labels = onehotencode(labels,2)
```

Uscita:

```text
ans = 4x1 cell: {'blue'} {'green'} {'red'} {'yellow'}
labels = 6×4
  0 0 1 0
  1 0 0 0
  0 0 1 0
  0 1 0 0
  0 0 0 1
  1 0 0 0
```

**Input**: 6 etichette (testo), 4 distinte. **Output**: una matrice $6\times 4$ di 0/1, con **esattamente un 1 per riga**. Le categorie sono **ordinate alfabeticamente** (blue, green, red, yellow), quindi «red» è $[0\ 0\ 1\ 0]$. Il `2` di `onehotencode` indica la dimensione lungo cui disporre le categorie (le colonne).

#### **6.2. Codifica one-hot in Python**

```python
import numpy as np
from sklearn.preprocessing import OneHotEncoder
labels = np.array(["red", "blue", "red",
                   "green", "yellow", "blue"])
labels = labels.reshape(-1, 1)              # 6 elementi
enc = OneHotEncoder(sparse_output=False)
# solo 4 etichette diverse
labels = enc.fit_transform(labels).astype(int)
print(enc.categories_[0])
print(labels)
```

Uscita:

```text
['blue' 'green' 'red' 'yellow']
[[0 0 1 0]
 [1 0 0 0]
 [0 0 1 0]
 [0 1 0 0]
 [0 0 0 1]
 [1 0 0 0]]
```

**Stesso risultato della versione MATLAB** (6 righe, 4 colonne): da qui in poi si resta in Python.

> 💡 **Due dettagli (nota didattica).** `reshape(-1, 1)` trasforma il vettore di 6 elementi in una matrice $6\times 1$, perché gli encoder di scikit-learn si aspettano una matrice (campioni × feature); `-1` significa «calcola tu questa dimensione». `fit_transform` impara le categorie (fit) e le applica (transform): con dati di test si chiama solo `transform`, e con `handle_unknown="ignore"` una categoria mai vista diventa una riga di soli zeri invece di un errore.

#### **6.3. Preparare le feature di input**

Per **migliorare la discesa del gradiente** si **normalizzano** i valori con `normalize()`; `X_data` conterrà le feature normalizzate usate per addestrare la rete neurale.

```python
from sklearn.preprocessing import normalize
FEATURES = dataset.columns[0:4]
X_data = dataset[FEATURES].as_matrix()  # per compatibilità
X_data = normalize(X_data)
```

> ⚠️ **Due attenzioni sul codice (nota didattica).**
> - `sklearn.preprocessing.normalize` **non** fa una min-max per feature: porta **ogni campione** (ogni riga) a **norma unitaria L2**. Per portare ogni feature in $[0,1]$ si usa `MinMaxScaler`, per lo z-score `StandardScaler` (Lezione 10), stimandoli sul solo training.
> - `as_matrix()` è stato rimosso da pandas: oggi si scrive `dataset[FEATURES].to_numpy()` (come nel §8.2).

#### **6.4. Dividere train e test**

`X_data` e `y` contengono i dati necessari ad addestrare una rete neurale con un motore come TensorFlow. Ora si dividono i dati in un insieme di **training** e uno di **test**, per **prevenire l’overfitting** e ottenere un **benchmark migliore** delle prestazioni della rete.

```python
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X_data, Y,
                                       test_size=0.3, random_state=1)
```

**Input**: $X$ ($4\times N$), $Y$ ($1\times N$). **Output**: **70% training, 30% test**.

> ⚠️ **Usare questo parametro con giudizio** nel caso di **serie temporali**: una divisione casuale **mescola passato e futuro** (Lezione 14, §6.12).

**Un esempio giocattolo.** Con $X$ di 5 righe $[0,1],[2,3],[4,5],[6,7],[8,9]$ e `test_size=0.33`, **2 righe su 5 vanno nel test** ($[4,5]$ e $[2,3]$ nell’esempio) e le altre vengono **mescolate** nel training ($[8,9]$, $[0,1]$, $[6,7]$).

![](imgs/Pasted%20image%2020261124040848.png)

> 💡 **Perché 2 su 5 (nota didattica).** scikit-learn arrotonda per eccesso la dimensione del test: $0{,}33\cdot 5=1{,}65\to 2$. `random_state=1` fissa il mescolamento, così la divisione è **riproducibile**; senza, ogni esecuzione darebbe una divisione diversa (e, come visto nel §3.4.1, un’accuratezza diversa). Per le classi sbilanciate si aggiunge `stratify=Y` (Lezione 14, §6.4).

**Lo stesso in Colab.**

<!-- SOURCE_VISUAL id="IS16-V36" source="L16-web.pdf" page="85" type="schermata" description="Divisione train/test eseguita in Colab con l'output delle dimensioni" reason="Schermate visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124040918.png)

**Il passo successivo è l’apprendimento.** Come si vede negli esercizi in MATLAB e Colab, si è ora pronti a creare i **primi classificatori**! (Ma prima, un bel respiro.)

#### **6.5. Punti principali – parte A**

> ✅ **Punti principali della parte A.**
> - Il dataset **Iris di Fisher** come «**strumento di debug**».
> - La **codifica one-hot**.
> - Creare le **matrici per l’apprendimento** ($X$ e $Y$).
> - **Analisi statistica dei dati, prima di qualunque elaborazione.**
> - Esempi di **grafici rilevanti per il ML**.
> - La **visualizzazione dei dati** è molto utile…

---

### **7. Lezione 16 – Laboratorio: gestione di base dei dataset, programmazione in MATLAB e Colab, codifica one-hot**


#### **7.1. Il laboratorio in sintesi: MATLAB e Python**

| Passo | MATLAB | Python / Colab |
|---|---|---|
| **1. caricare Iris** | `load fisheriris` / `readtable` | `pd.read_csv(url)` |
| **2. feature $X$ / classi $Y$** | slicing della matrice `X(:,1:4)` | `dataset[FEATURES]` |
| **3. normalizzare** | `normalize(X)` | `normalize(X_data)` |
| **4. classi → numeri** | `categorical`, `onehotencode` | `pd.factorize`, one-hot |
| **5. dividere train / test** | `cvpartition` | `train_test_split` |

**Input**: Iris, 150 fiori × 4 feature (cm) + 1 classe (3 specie). **Output**: $X$ ($150\times 4$), $Y$ (codici delle classi), insiemi di training e test.

**Che cosa produce il laboratorio (Python / Colab)**: **caricare e ispezionare** (150 righe, 4 feature, 1 classe) → **normalizzare** (dai valori grezzi all’intervallo $[0,1]$) → **classi → 0, 1, 2** (iris-setosa → 0, iris-versicolor → 1, iris-virginica → 2) → **visualizzare** (Feat2 + Feat3 separano le classi). Ogni passo è mostrato riga per riga, con le uscite, nel §7.4.

#### **7.2. Eseguire il laboratorio e che cosa aspettarsi all’esame**

**Eseguirlo su Colab**: 1. scaricare il notebook (`*.ipynb`) da Ariel; 2. caricarlo su Google Colab; 3. eseguire tutte le celle e leggere le uscite. **MATLAB**: gli stessi passi dallo script d’esempio.

> 📌 **All’esame non c’è programmazione (esatta).** Si chiederà di **commentare esempi di codice**, per mostrare di aver capito le nozioni. È comunque importante studiare **come la teoria si applica a casi pratici** ed esempi, per capire meglio i concetti.

**Il laboratorio** (in un file separato): caricamento e gestione di base dei dataset, con un esempio Colab e uno MATLAB, entrambi sul dataset Iris.

| **Colab** | **MATLAB** |
|---|---|
| preparazione dell’ambiente | caricare il dataset |
| caricamento dei dati | creare le strutture dati ($X$ e $Y$) |
| analisi preliminare dei dati | analisi preliminare dei dati |
| disegnare i dati | disegnare i dati |
| normalizzazione | normalizzazione |
| esempio di classificazione | esempio di classificazione |

#### **7.3. La cosa migliore da fare**

**MATLAB**: **scaricare l’esempio e farlo girare!** L’esempio è commentato: esplorare il codice, provare a modificarlo e/o aggiungere nuovi grafici.

<!-- SOURCE_VISUAL id="IS16-V37" source="L16-web.pdf" page="97" type="schermata" description="Script MATLAB d'esempio del laboratorio" reason="Schermata visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124041212.png)

**Colab**: scaricare il codice, caricarlo sul proprio Google Drive, aprirlo con Colab e farlo girare (con Google Chrome, dopo il login): colab.research.google.com. Gli esempi sono commentati: esplorarli, modificarli, aggiungere nuovi grafici.

![](imgs/Pasted%20image%2020261124041238.png)

**Caricare il proprio `*.ipynb`.** Un file **IPYNB** è un **documento notebook** usato da **Jupyter Notebook**, un ambiente computazionale interattivo pensato per lavorare con Python e i propri dati: 1. aprire colab.research.google.com; 2. File → Upload notebook; 3. scegliere il file `.ipynb`.

![](imgs/Pasted%20image%2020261124041250.png)

**Farlo girare**: 1) leggere i commenti; 2) cliccare su `[ ]` per eseguire la cella, oppure «*Run all*».

<!-- SOURCE_VISUAL id="IS16-V38" source="L16-web.pdf" page="100" type="schermata" description="Esecuzione delle celle in Colab con il pulsante della cella e Run all" reason="Schermata visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124041300.png)

#### **7.4. Caricamento, EDA e grafici in Python/Colab (e uno sguardo a Matplotlib)**

##### **7.4.1. Preparazione dell’ambiente**

```python
import pandas as pd   # Python Data Analysis Library
import numpy as np    # Python Scientific Library
```

**Pandas**: libreria per la pre-elaborazione dei dataset. **NumPy**: libreria per la manipolazione di array (grandi array e matrici multidimensionali, più molte funzioni matematiche). Si pronuncia «NUM-py» o «NUM-pi»: entrambe accettate, nessuno sarà bocciato per questo.

##### **7.4.2. Caricamento dei dati**

```python
url = "https://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data"
new_names = ['sepal_length','sepal_width','petal_length',
             'petal_width','iris_class']
dataset = pd.read_csv(url, names=new_names, skiprows=0, delimiter=',')
dataset.info()
```

**Input**: un URL (file di testo). **Output**: una tabella (DataFrame) di **150 righe × 5 colonne**. Il file UCI non ha intestazione: `names` assegna i nomi delle colonne.

`dataset.info()` dà informazioni organizzate: **quante righe**, il **tipo** di ogni colonna e se **manca qualcosa** (*Non-Null Count*):

```text
<class 'pandas.core.frame.DataFrame'>
RangeIndex: 150 entries, 0 to 149
Data columns (total 5 columns):
 #   Column        Non-Null Count  Dtype
 0   sepal_length  150 non-null    float64
 1   sepal_width   150 non-null    float64
 2   petal_length  150 non-null    float64
 3   petal_width   150 non-null    float64
 4   iris_class    150 non-null    object
memory usage: 6.0+ KB
```

**Le prime righe** (quattro colonne numeriche in cm e una categorica, la classe):

| | sepal_length | sepal_width | petal_length | petal_width | iris_class |
|---:|---:|---:|---:|---:|---|
| 0 | 5,1 | 3,5 | 1,4 | 0,2 | Iris-setosa |
| 1 | 4,9 | 3,0 | 1,4 | 0,2 | Iris-setosa |
| 2 | 4,7 | 3,2 | 1,3 | 0,2 | Iris-setosa |
| 3 | 4,6 | 3,1 | 1,5 | 0,2 | Iris-setosa |
| 4 | 5,0 | 3,6 | 1,4 | 0,2 | Iris-setosa |

##### **7.4.3. Pre-elaborazione: feature e classi**

```python
y = dataset['iris_class']
x = dataset.drop(['iris_class'], axis=1)
print("dataset : ", dataset.shape)
print("x : ", x.shape)
print("y : ", y.shape)
```

```text
dataset : (150, 5)
x : (150, 4)    ← 4 colonne: le feature
y : (150,)      ← 1 colonna: la classe
```

Prima si **separano le feature dalle classi**: pre-elaborazioni **diverse** per feature e classi. È necessario? **SÌ! Per evitare il data leakage!!!** `shape` funziona come `size()` in MATLAB. In `drop`, `axis` indica se eliminare etichette dall’**indice** (0, righe) o dalle **colonne** (1).

> 💡 **Perché separare evita il leakage (nota didattica).** Se si normalizzasse o si facesse una PCA sull’intero DataFrame, la colonna della classe (codificata come numero) entrerebbe nella trasformazione delle feature: l’informazione sulla risposta finirebbe negli input. Separare subito $x$ e $y$ rende impossibile questo errore.

##### **7.4.4. Conversione in NumPy e slicing**

```python
xNumpy = x.to_numpy()
yNumpy = y.to_numpy()
```

Si usa NumPy per convertire i dati in **formato matriciale**: facilita l’**accesso a porzioni** delle matrici.

**Slicing per colonna** (una feature):

```python
feature_to_extract = 1
feature = xNumpy[:,feature_to_extract]
print(feature)
# [3.5 3.  3.2 3.1 3.6 3.9 3.4 3.4 ...]   (150 valori, primi 8)
```

**Debugging!!** I valori stampati sono `sepal_width`: 3,5; 3,0; 3,2; 3,1; 3,6… **esattamente la seconda colonna** della tabella. **L’indice 1 è la seconda colonna**: Python conta **da 0**.

**Slicing per riga** (un campione):

```python
sample_to_extract = 1
sample = xNumpy[sample_to_extract,:]
print(sample)
# [4.9 3.  1.4 0.2]
```

**Debugging!!** Si confronta con la riga di indice 1 della tabella: 4,9; 3,0; 1,4; 0,2. **Stessi valori**: lo slicing fa ciò che ci si aspetta.

> 📌 **Il metodo del debugging.** Ogni volta che si estrae una porzione di dati si confronta il risultato con un valore noto (la tabella stampata). È esattamente lo scopo della regola d’oro #1 (§4.1): con un dataset noto, una discrepanza indica un errore nel codice.

##### **7.4.5. Normalizzazione dei dati**

La normalizzazione porta i dati nell’**intervallo $[0,1]$**. Normalizzazione **min-max**:

$$x'=\frac{x-\min(x)}{\max(x)-\min(x)},$$

con $x$ valore di una feature [cm], $\min(x)$ e $\max(x)$ il valore più piccolo e più grande di quella feature nei dati [cm], $x'$ adimensionale in $[0,1]$. **Esempio** (lunghezza del sepalo): $x=5{,}1$ cm, min = 4,3 cm, max = 7,9 cm: $x'=(5{,}1-4{,}3)/(7{,}9-4{,}3)=0{,}22$.

```python
xNumpy_Norm = (xNumpy - np.min(xNumpy)) / (np.max(xNumpy) - np.min(xNumpy))
```

> ⚠️ **Attenzione: in questa riga min e max sono presi sull’intera matrice, non per feature.** Per colonna si scrive `axis=0`: `np.min(xNumpy, axis=0)` e `np.max(xNumpy, axis=0)`.

**Prima e dopo.** `plt.hist(xNumpy[:,0])` e `plt.hist(xNumpy_Norm[:,0])`: **stessa forma, nuova scala orizzontale**, da 4,3–7,9 cm a circa **0,54–1** (il codice usa il minimo 0,1 e il massimo 7,9 dell’intera matrice).

<!-- SOURCE_VISUAL id="IS16-V39" source="L16-web.pdf" page="111" type="grafico" description="Istogrammi della lunghezza del sepalo prima e dopo la normalizzazione globale" reason="Istogrammi visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124041904.png)

> 💡 **Perché 0,54 (nota didattica).** Con il minimo globale 0,1 (la larghezza minima di un petalo) e il massimo globale 7,9: $(4{,}3-0{,}1)/(7{,}9-0{,}1)=4{,}2/7{,}8\approx 0{,}54$. La feature non arriva mai a 0, perché il minimo usato appartiene a un’altra colonna. La min-max è anche lineare: la forma dell’istogramma non cambia (Lezione 10, §3.11).

##### **7.4.6. Conversione dei dati: da categorici a decimali**

```python
yNumpy, dummy = pd.factorize(yNumpy)
print(yNumpy)
# [0 0 0 ... 0 1 1 1 ... 1 2 2 2 ... 2]   (50 zeri, 50 uno, 50 due)
```

I dati di classe sono in **formato categorico** (testo); li si converte in **interi** per una manipolazione più facile: Iris-setosa → 0, Iris-versicolor → 1, Iris-virginica → 2.

> 💡 **È un label encoding (nota didattica).** `pd.factorize` assegna un intero a ogni valore distinto nell’ordine in cui compare (§5.3). È accettabile qui perché serve solo come **etichetta di classe** da prevedere, non come feature di input su cui un modello calcola distanze.

##### **7.4.7. Visualizzazione dei dati**

Visualizzare feature e classi dà un **primo controllo visivo della capacità discriminante**. **Limite**: la visualizzazione è limitata a **2 o 3 dimensioni**. Anche un dataset semplice come Iris (4D) **non si può disegnare con tutte le sue feature sui propri assi**. (Provare a disegnare un quarto asse alla lavagna: il risultato somiglia all’immagine.)

<!-- SOURCE_VISUAL id="IS16-V40" source="L16-web.pdf" page="113" type="figura" description="Tentativo umoristico di disegnare un quarto asse" reason="Illustrazione visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124041929.png)

**Usare la visualizzazione per gestire le 4 dimensioni di Iris**:

1. **scegliere due feature** da disegnare (un piano 2D);
2. **assegnare un colore** in base alla classe.

![](imgs/Pasted%20image%2020261124041950.png)

**Input**: 150 × 4 feature + 150 codici di classe. **Output**: uno scatter plot per coppia di feature.

| Coppia | Risultato |
|---|---|
| **Feat0 + Feat1** (lunghezza e larghezza del sepalo) | **buona discriminazione per la classe 0**; **cattiva per le classi 1 e 2** (le classi sono mescolate) |
| **Feat2 + Feat3** (lunghezza e larghezza del petalo) | **discriminazione migliore per tutte le classi** |

![](imgs/Pasted%20image%2020261124042003.png)


<!-- SOURCE_VISUAL id="IS16-V41" source="L16-web.pdf" page="115" type="grafico" description="Scatter Feat0 contro Feat1: classe 0 separata, classi 1 e 2 mescolate" reason="Punti colorati visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS16-V42" source="L16-web.pdf" page="116" type="grafico" description="Scatter Feat2 contro Feat3: discriminazione migliore per tutte le classi" reason="Punti colorati visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124042011.png)

> 📌 **Morale: scegliere la coppia giusta di feature è già metà del classificatore.** Lo si vedrà nella Lezione 17, dove una sola regola sulla lunghezza del petalo separa la setosa dalle altre specie.

#### **7.5. Punti principali**

> ✅ **Punti principali della lezione.**
> - **Valutazione dell’accuratezza e della confidenza** per classificatori e regressori: $R^2$, MSE, RMSE, matrice di confusione, ROC, analisi di sensibilità.
> - **Gestione di base dei dataset**: programmazione in MATLAB e Colab, con il dataset Iris come riferimento.
> - **Codifica delle categorie**: **codifica intera** (valori ordinati) e **one-hot** (nessun ordine).
> - **Caricamento e gestione** di base dei dataset: un esempio MATLAB e uno Colab.
> - **Analisi esplorativa dei dati (EDA) prima di qualunque elaborazione.**
> - **Partire da dataset noti** per fare il debug di strumenti, grafici, pre-elaborazione e apprendimento.
> - **Codificare le feature categoriche con giudizio**, conservando o evitando un ordine artificiale.

---

### **8. Approfondimento — I file del laboratorio**


Il notebook e lo script della cartella della lezione completano le slide con un classificatore; i commenti seguenti derivano dalla loro lettura integrale.

#### **8.1. Il notebook Colab: la classificazione con 1-NN**

Dopo i passi del §7.4 il notebook `Lesson_16_laboratory_COLAB_IRIS_Dataset.ipynb` addestra un classificatore **1-NN** con distanza euclidea su una divisione a metà:

```python
from sklearn.model_selection import train_test_split
x_train, x_test, y_train, y_test = train_test_split(xNumpy, yNumpy, test_size=0.5)
# xTrain (75, 4), xTest (75, 4), yTrain (75,), yTest (75,)

from sklearn.neighbors import KNeighborsClassifier
classifier = KNeighborsClassifier(n_neighbors=1, metric='euclidean')
classifier.fit(x_train, y_train)
output_test = classifier.predict(x_test)

from sklearn.metrics import confusion_matrix
CM = confusion_matrix(y_test, output_test)
print(CM)

accuracy = CM.trace() / CM.sum()
print("Accuracy: {0}".format(accuracy*100))
```

Output salvato:

$$CM=\begin{bmatrix}27&0&0\\0&21&2\\0&0&25\end{bmatrix},\qquad \text{accuratezza}=\frac{27+21+25}{75}=\frac{73}{75}\approx 97{,}33\%.$$

> 💡 **Leggere il risultato (nota didattica).** In scikit-learn le **righe** sono le classi **vere** e le **colonne** quelle **previste**: 2 versicolor (classe 1) sono state classificate come virginica (classe 2), mentre la setosa è riconosciuta perfettamente, coerentemente con gli scatter plot del §7.4.7. `CM.trace()` somma la diagonale (i corretti), `CM.sum()` tutti gli elementi. La divisione **non ha `random_state`**: rieseguendo il notebook si ottengono numeri leggermente diversi, la variabilità misurata nel §3.4.1. Il 1-NN lavora sui dati non normalizzati `xNumpy`: qui va bene perché le quattro feature sono tutte in centimetri con scale simili.

#### **8.2. Lo script MATLAB: la classificazione con LDA**

Lo script `Lesson_16_laboratory_MATLAB_IRIS_Dataset.m` segue gli stessi passi:

| Blocco | Istruzioni principali | Che cosa fa |
|---|---|---|
| **scaricare e leggere** | `websave('iris.dat', url)`, `textscan(fid,'%f%f%f%f%s','delimiter',',')` | salva il file UCI e legge quattro colonne numeriche e una di testo; il commento ricorda che equivale a `load fisheriris` |
| **matrice delle feature** | `P = measurements;` `plotmatrix(P)` | la matrice $X$ e lo scatter plot a coppie |
| **target** | ciclo con `switch species{i}` | il vettore target $Y$: setosa → 0, versicolor → 1, virginica → 2 |
| **statistiche descrittive** | `mean`, `std`, `min`, `max` per colonna, stampate con `fprintf` | primo controllo delle feature |
| **grafici** | `gscatter(P(:,i), P(:,j), species, 'rgb', 'osd')` in una griglia 4 × 4 | tutte le coppie colorate per specie, con le etichette in centimetri |
| **classificatore** | `lda = fitcdiscr(P,T);` `ldaClass = resubPredict(lda);` | **analisi discriminante lineare**, il metodo di Fisher |
| **figure di merito** | `errorVector = ldaClass ~= T;` somma, media e deviazione standard dell’errore | numero e percentuale di errori |
| **matrice di confusione** | `confusionMatrix = confusionmat(ldaClass,T)` | tabella degli errori per classe |

```matlab
%% VERY EASY TO CREATE A CLASSIFIER NOW
% Linear Discriminant Analysis
lda = fitcdiscr(P,T);
ldaClass = resubPredict(lda);

% Classification error
errorVector = ldaClass ~= T;
totalErrorPerc = mean(errorVector)*100;
fprintf('Total classification error = %03.03f %% \n', totalErrorPerc);

%Confusion Matrix
confusionMatrix = confusionmat(ldaClass,T)
```

> ⚠️ **Due osservazioni sullo script (nota didattica).**
> - `resubPredict` predice **gli stessi dati usati per addestrare** (*resubstitution*): l’errore ottenuto è una stima con **bias ottimistico** (Lezione 14, §3.2), non un errore di generalizzazione. Per una stima corretta serve una divisione o una validazione incrociata, per esempio con `crossval` o `cvpartition` (il passo 5 della tabella del §7.1).
> - `confusionmat(group, grouphat)` si aspetta prima le etichette **vere** e poi quelle **previste**. Lo script le passa in ordine inverso (`ldaClass`, `T`), quindi nella matrice ottenuta le **righe** sono le classi **previste** e le colonne quelle vere: va letta di conseguenza.

> 💡 **Due linguaggi, due classificatori.** Il notebook usa un **1-NN** su una divisione a metà, lo script una **LDA** (*Linear Discriminant Analysis*) su tutti i dati. Il confronto è istruttivo: l’errore dello script è misurato sui dati di addestramento, quello del notebook su dati mai visti. Per confrontare davvero i due metodi bisognerebbe valutarli con lo **stesso** schema di validazione (Lezione 14).

---

### **9. Distinzioni da non confondere**


| Coppia | Differenza chiave |
|---|---|
| **$R^2$** / **RMSE e MAE** | quota di variazione spiegata, adimensionale / errore nelle unità di $y$ |
| **MSE** / **RMSE** | unità al quadrato / unità di $y$ |
| **RMSE** / **MAE** | penalizza gli errori grandi / meno sensibile agli outlier |
| **accuratezza** / **errore** | corretti / $1-$ accuratezza: controllare quale si legge |
| **classificatore casuale** / **classe di maggioranza** | circa 50% (binario) / la frequenza della classe più comune (68% nel Titanic) |
| **errore di tipo I** / **di tipo II** | falso positivo / falso negativo |
| **recall (TPR)** / **specificità (TNR)** | $TP/(TP+FN)$ / $TN/(TN+FP)$ |
| **riga** / **colonna** della matrice multiclasse | classe vera / classe prevista (controllare la convenzione) |
| **punto della ROC** / **curva ROC** | una soglia / tutte le soglie |
| **pipeline tradizionale** / **nuova** | dati etichettati, feature, addestramento, CV / foundation model, ancoraggio, zero/few-shot, umano nel ciclo |
| **modello di classificazione** / **modello descrittivo** | uscita classe o numero, metriche classiche / uscita testo, nuovo spazio di valutazione |
| **zero-shot** / **one-shot** / **few-shot** | 0 / 1 / pochi esempi nel prompt, stesso modello |
| **valutazione su etichette** / **su scenari** / **confidenza** / **umano nel ciclo** | conta i giusti / prova su casi nuovi / log loss sulla confidenza / tasso di accordo con gli esperti |
| **scenari noti** / **nuovi** | punteggio ottimistico / punteggio che conta |
| **sensibilità ai dati** / **ai parametri** | cambia la divisione / cambia la precisione numerica dei pesi |
| **codifica intera** / **label encoding** | scala ordinale con passo significativo / identificativo arbitrario per ordine di comparsa |
| **one-hot** / **dummy** | $C$ colonne, ridondanti / $C-1$ colonne, nessuna multicollinearità |
| **label encoding con alberi** / **con modelli a distanza** | accettabile / sconsigliato |
| **`normalize`** / **`MinMaxScaler`** | ogni campione a norma unitaria / ogni feature in $[0,1]$ |
| **min-max globale** / **per feature** | `np.min(X)` / `np.min(X, axis=0)` |
| **`random_state` fissato** / **assente** | divisione riproducibile / divisione diversa a ogni esecuzione |
| **`resubPredict`** / **validazione su dati nuovi** | errore sul training, ottimistico / stima della generalizzazione |
