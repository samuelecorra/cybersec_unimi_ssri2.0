## **Lezione 12: Alberi di decisione e random forest — apprendimento interpretabile, figure riproducibili e simulazione d’esame**

Un **albero di decisione** è un’ipotesi esplicita: una sequenza di test su singole feature che termina in una classe. Proprio per questo è il modello più leggibile del corso, e viene usato dove le decisioni vanno **ispezionate, spiegate e verificate** (allarmi di sicurezza, controllo qualità, diagnosi di processo). La lezione mostra come un albero divide lo spazio delle feature in **regioni rettangolari allineate agli assi**, come se ne misura la conoscenza con la **profondità** e con l’**impurità** dei nodi (**Gini** ed **entropia**, con la dimostrazione che l’entropia binaria è massima a 50/50), e perché un singolo albero è **instabile**. Da questa instabilità nasce la **random forest**: molti alberi addestrati su campioni **bootstrap** e con feature casuali, le cui previsioni vengono **aggregate** per ridurre la varianza. Tre esempi accompagnano la teoria — il classico **Play Tennis**, un dataset simulato di **frutti** e il dataset reale **UCI Wine** — e la lezione si chiude con una **simulazione d’esame** di dieci quesiti risolti.

> **Fonte.** La lezione segue il PDF dell’edizione 2026/27 `sources_2026-27/L12-web.pdf` (93 pagine). I segnaposto per le figure indicano la pagina di quel PDF; i quesiti della simulazione dell’edizione precedente non presenti nel nuovo PDF sono conservati nel §6.2.


### **0. Panoramica**


La lezione si articola in quattro parti:

1. **alberi di decisione**: regole, divisioni (*split*), impurità, profondità;
2. **random forest**: bagging, instabilità e scelta del modello;
3. **dai dati al modello**: Play Tennis, frutti e UCI Wine;
4. **esercitazione d’esame**: 10 quesiti in questo PDF, altri 10 in aula.

![](imgs/Pasted%20image%2020261123214242.png)

---

### **1. Apprendimento interpretabile: gli alberi di decisione**


Dati tabellari, partizioni e regole di decisione esplicite.

#### **1.1. Alberi di decisione per sicurezza e privacy**

- Gli alberi di decisione rappresentano una **sequenza di regole verificabile** (*auditable*).
- Le **decisioni leggibili** aiutano **ispezione**, **debugging** e **conformità** normativa.
- L’**inferenza veloce** può supportare applicazioni con **latenza limitata** (pochi test `if` per decisione).
- **Usi tipici**: **allarmi di sicurezza**, **controllo qualità** e **diagnosi di processo**.

> 💡 **Perché nella sicurezza (nota didattica).** In un sistema che blocca una transazione o segnala un’intrusione, l’analista deve poter rispondere alla domanda «perché?». Un albero risponde con il percorso seguito («perché il numero di tentativi di accesso supera 5 *e* l’indirizzo non è noto»), che si può verificare, discutere e correggere. È anche la forma di spiegazione più vicina a quella richiesta dalle norme sulla trasparenza delle decisioni automatizzate (Lezione 2).

![](imgs/Pasted%20image%2020261123214609.png)

#### **1.2. Play Tennis: la tabella di training $X$ e l’etichetta $y$**

**Si gioca a tennis?** È l’esempio classico dei dati tabellari:

- $X$ = **osservazioni meteorologiche** (prospettiva del cielo, temperatura, umidità, vento);
- $y$ = **SÌ / NO**;
- **14 record** illustrativi.

![](imgs/Pasted%20image%2020261123214621.png)

> ⚠️ **Nota sui dati.** L’esempio da 14 record include **un valore di umidità modificato** per illustrare l’albero compatto delle slide successive. La tabella non coincide quindi esattamente con la versione storica di Quinlan.

> 💡 **La versione classica (nota didattica).** Nella versione categorica di Quinlan (1986) le 14 giornate hanno quattro feature: **Outlook** (soleggiato, nuvoloso, pioggia), **Temperature** (caldo, mite, fresco), **Humidity** (alta, normale), **Wind** (debole, forte), con 9 «sì» e 5 «no». La versione delle slide usa invece l’umidità **numerica** (in percentuale) e la variabile **Windy** (sì/no), come nella variante di C4.5: l’albero risultante è lo stesso nella struttura, ma la divisione sull’umidità diventa una soglia.

#### **1.3. Un albero è un’ipotesi esplicita**

- I **NODI** testano i valori di una feature.
- Le **FOGLIE** specificano una classe.

L’albero di Play Tennis è:

- radice **Outlook**:
  - **Sunny** (soleggiato) → nodo **Humidity**: se $\le 72{,}5\%$ → **YES**; se $>72{,}5\%$ → **NO**;
  - **Overcast** (nuvoloso) → **YES**;
  - **Rain** (pioggia) → nodo **Windy**: se **No** → **YES**; se **Yes** → **NO**.

![](imgs/Pasted%20image%2020261123214638.png)

**Esempio svolto**: Outlook = Sunny, Humidity = 68% ⇒ **YES** (perché $68\le 72{,}5$).

> 📌 **Ciò che l’albero dimostra e ciò che non dimostra.** Tutti i 14 esempi seguono le regole mostrate; **le prestazioni su dati non visti vanno comunque valutate**. Un albero che spiega perfettamente il training è un’ipotesi coerente con i dati, non una legge verificata.

> 💡 **Temperature non compare (nota didattica).** La feature Temperature è nel dataset ma non nell’albero: l’algoritmo di costruzione l’ha scartata perché nessuna divisione su di essa riduceva l’impurità più delle altre. Gli alberi fanno quindi anche una **selezione implicita delle feature** (embedded, Lezione 18).

#### **1.4. Dall’albero alle regioni allineate agli assi**

Un esempio in due dimensioni ($x_1$, $x_2$):

- un **test binario su $x_1$** produce una **frontiera verticale** ($x_1\le 4$?);
- un **test successivo su $x_2$** produce una **frontiera orizzontale** ($x_2\le 3$?);
- **due regole definiscono un rettangolo allineato agli assi**: la classe 1 è il riquadro blu $x_1\le 4$ e $x_2\le 3$; tutto il resto è classe 0.

![](imgs/Pasted%20image%2020261123215103.png)

> 📌 **Frontiere allineate agli assi.** Ogni nodo confronta **una sola feature** con una soglia ($x_j\le s$), quindi taglia lo spazio con un iperpiano **perpendicolare** a quell’asse. Il risultato è una partizione in **rettangoli** (iper-rettangoli in più dimensioni), e ogni foglia è uno di questi rettangoli.

> 💡 **Conseguenza pratica (nota didattica).** Una frontiera **obliqua**, come $x_1=x_2$, un albero la può solo **approssimare a scalini**, con molti nodi; un modello lineare la rappresenterebbe con due parametri. Viceversa, una regione rettangolare è semplicissima per un albero e difficile per un modello lineare.

#### **1.5. Spiegabilità: percorsi di decisione leggibili**

- **Ogni percorso dalla radice a una foglia** rappresenta una **regola di decisione**.
- Esempio: Sunny, umidità 68% → **YES**.
- Un albero di decisione offre una **visione preziosa e leggibile dei dati**, ma **non dimostra la qualità del modello**:
  - una feature **non usata** può essere comunque **fisicamente importante**;
  - **condizioni leggibili non garantiscono causalità né generalizzazione**.

> 💡 **L’albero come regole (nota didattica).** L’albero di Play Tennis si legge come tre regole «se-allora»: se Outlook = nuvoloso → gioca; se Outlook = soleggiato → gioca **solo se** l’umidità è al massimo 72,5%; se Outlook = pioggia → gioca **solo se** non c’è vento. È la stessa forma delle regole dell’apprendimento deduttivo (Lezione 3), ma qui le regole sono **apprese dai dati** (apprendimento induttivo).

#### **1.6. Divisioni binarie e multiple**

| | **Alberi binari** | **Alberi a divisione multipla** |
|---|---|---|
| **nodi** | ogni nodo interno crea **esattamente due figli** | un test categorico può creare **più rami** |
| **pregi** | **implementazione e potatura** più semplici | alberi **meno profondi** |
| **esempio** | `sklearn.tree.DecisionTreeClassifier` costruisce **alberi binari** | il nodo Outlook di Play Tennis (tre rami) |

![](imgs/Pasted%20image%2020261123215232.png)

> 💡 **Ogni divisione multipla si può rendere binaria (nota didattica).** Il nodo a tre rami «Outlook ∈ {Sunny, Overcast, Rain}» equivale a due nodi binari in cascata: «Outlook = Overcast?» e poi «Outlook = Sunny?». L’albero binario è più profondo di un livello, ma ogni nodo è più semplice; è il compromesso della tabella.

![](imgs/Pasted%20image%2020261123215400.png)

#### **1.7. Classificare i frutti: dagli oggetti alle feature**

Tre classi diverse di oggetti fisici possono essere rappresentate dalle **stesse misure numeriche**:

| | Contenuto |
|---|---|
| **feature di input** | $x=(\text{larghezza [cm]},\ \text{altezza [cm]})$ |
| **etichetta di classe** | $y\in\{a,o,l\}^{32}$: $a$ = mela (*apple*), $o$ = arancia (*orange*), $l$ = limone (*lemon*) |
| **matrice dei dati** | $X\in\mathbb{R}^{32\times 2}$: **32 campioni**, **2 feature numeriche** |

![](imgs/Pasted%20image%2020261123215410.png)

> ⚠️ **Esempio didattico.** Le misure dei frutti sono **simulate**, non osservazioni raccolte da frutti reali.

**Il dataset simulato.** $X$ è mostrato in forma «feature per riga» per leggibilità (2 righe × 32 campioni); in scikit-learn è memorizzato come **32 righe × 2 colonne**. Il vettore delle classi $y$ ha 32 elementi.

![](imgs/Pasted%20image%2020261123215424.png)

**Le stesse 32 osservazioni nello spazio delle feature**: ogni punto è un campione (larghezza, altezza); il colore indica la classe $y$.

![](imgs/Pasted%20image%2020261123215436.png)

#### **1.8. Dalle misure dei frutti alle regioni di decisione**

L’albero usa **soglie allineate agli assi**: $w=7{,}3$, $h=7{,}8$, $h=8{,}0$, $w=6{,}6$, $h=6{,}0$ ($w$ = larghezza, $h$ = altezza). **Ogni ramo testa una sola feature**; **ogni regione risultante predice una classe**. Sono 32 punti simulati: **non è una valutazione dell’accuratezza predittiva**.

![](imgs/Pasted%20image%2020261123215444.png)

**Le stesse frontiere sono semplici regole se-allora.** Si segue un percorso dall’alto verso il basso; il grafico e l’albero sono generati dalle **stesse cinque soglie**.

- radice **$w>7{,}3$?**
  - **no** (frutto stretto) → **$h>8{,}0$?**
    - **sì** (stretto e alto) → **limone**;
    - **no** → **$w>6{,}6$?** → da un lato **arancia**, dall’altro **$h>6{,}0$?**, che separa **limone** e **arancia**;
  - **sì** (frutto largo) → **$h>7{,}8$?** → da un lato **arancia**, dall’altro **mela**.

![](imgs/Pasted%20image%2020261123215505.png)

> ⚠️ **Verso dei rami.** La slide etichetta esplicitamente «sì/no» solo ai primi due livelli, e con versi diversi (alla radice il ramo «no» è a sinistra, sotto $h>8{,}0$ il ramo «sì» è a sinistra). Per i nodi $w>6{,}6$, $h>6{,}0$ e $h>7{,}8$ l’assegnazione delle foglie va letta sulla figura. Una lettura coerente con la forma dei frutti è: largo e alto → arancia, largo e basso → mela; stretto e alto → limone.

#### **1.9. Come cambiano le frontiere tra classificatori diversi**

Gli **stessi 32 punti didattici**, tre classificatori diversi:

| Classificatore | Forma della frontiera |
|---|---|
| **albero di decisione** | **tagli rettangolari** |
| **$k$-NN** | **voto dei vicini locali** (frontiera irregolare, a mosaico) |
| **SVM con kernel RBF** | **frontiera di decisione non lineare** (curva) |

$k$-NN e SVM sono presentati altrove nel corso (Lezioni 19 e successive).

![](imgs/Pasted%20image%2020261123215523.png)

> 📌 **Le mappe di decisione descrivono decisioni di training, non superiorità validate.** Mostrano la **forma** delle ipotesi che ciascun modello può esprimere, non quale generalizzi meglio: per saperlo servono dati tenuti da parte. SVM (*Support Vector Machine*, macchina a vettori di supporto) con kernel RBF (*Radial Basis Function*, funzione di base radiale) usa somiglianze gaussiane tra i punti, da cui le frontiere curve.

---

### **2. Che cosa sa un albero: profondità e impurità**


#### **2.1. Come controllare la conoscenza nell’albero?**

Due strumenti:

1. la **profondità** dell’albero;
2. l’**informazione nei nodi**: **impurità di Gini** ed **entropia**.

Si useranno gli stessi **NODI** e **FOGLIE** di Play Tennis.

![](imgs/Pasted%20image%2020261123215604.png)

**Un albero, tre punti di vista**: le regole effettive usano Outlook, Humidity e Windy; i percorsi numerici hanno **profondità massima 2**. **La profondità è il percorso più lungo dalla radice a una foglia.**

#### **2.2. Profondità e impurità rispondono a domande diverse**

- La **profondità** conta i **test in sequenza** dalla radice alla foglia più profonda.
- L’**impurità di un nodo** quantifica **quanto sono mescolate le etichette** dentro il nodo.
- Lo stesso albero può avere **foglie pure poco profonde** e **nodi impuri più profondi**.
- **Nessuna delle due misure, da sola, stabilisce l’accuratezza sul test set.**

#### **2.3. La profondità dell’albero: che cosa misura?**

> 📌 **Profondità.** È il **numero massimo di archi** (decisioni) **dalla radice a una foglia**.

| Albero | Pro | Contro |
|---|---|---|
| **più profondo** | **regole più ricche** | **maggior rischio di overfitting** |
| **poco profondo** | **meno test**, più facile da spiegare | **può andare in underfitting** |

La maggior parte delle previsioni richiede solo **un piccolo numero di test `if`**.

![](imgs/Pasted%20image%2020261123215620.png)

**Da un albero semplice a un albero molto profondo.** A profondità 2 l’albero è facile da ispezionare; **alberi più profondi possono essere molto più difficili da ispezionare e spiegare**.

![](imgs/Pasted%20image%2020261123215630.png)

> 💡 **Perché la profondità controlla la complessità (nota didattica).** Un albero binario di profondità $D$ ha al massimo $2^D$ foglie, cioè $2^D$ regioni distinte. Con $D=3$ sono 8 regioni, con $D=10$ già 1024: abbastanza per isolare ogni singolo esempio di un piccolo dataset. È il collegamento con i gradi di libertà della Lezione 7: più foglie significa più parametri liberi (soglie e classi) rispetto ai dati.

#### **2.4. Impurità di Gini: quanto è mescolato un nodo?**

Per $K$ classi con proporzioni empiriche $p_1,\dots,p_K$:

$$G=1-\sum_{k=1}^{K}p_k^2.$$

- **Nodo puro**: $G=0$.
- **Miscela binaria 50/50**: $G=0{,}5$.
- **CART** confronta l’**impurità pesata dei figli**.

![](imgs/Pasted%20image%2020261123215648.png)

![](imgs/Pasted%20image%2020261123215657.png)

> 💡 **Che cosa significa $G$ (nota didattica).** $G$ è la probabilità di **sbagliare** se si prende un elemento a caso dal nodo e gli si assegna un’etichetta estratta a caso secondo le proporzioni del nodo: la probabilità di scegliere la classe $k$ e poi etichettarla con un’altra è $p_k(1-p_k)$, e $\sum_k p_k(1-p_k)=1-\sum_k p_k^2$. Verifiche: nodo puro, $1-1^2=0$; 50/50, $1-(0{,}5^2+0{,}5^2)=0{,}5$; tre classi equiprobabili, $1-3\cdot(1/3)^2\approx 0{,}667$. CART (*Classification and Regression Trees*) sceglie la divisione che minimizza $\sum_c \frac{n_c}{n}G(c)$, l’impurità media dei figli pesata per il numero di esempi.

#### **2.5. L’entropia: un’altra misura dell’incertezza del nodo**

$$H(Y)=-\sum_{k=1}^{K}p_k\log_2 p_k\quad\text{bit}.$$

- **Entropia alta**: etichette di classe **eterogenee**.
- **Entropia zero**: **una sola classe**, nodo perfettamente puro.
- **ID3 / C4.5** cercano le divisioni con **guadagno di informazione** elevato.

> ⚠️ **L’entropia di Shannon misura l’incertezza, non garantisce conoscenza utile.** Un nodo a entropia zero è puro sul training, ma può essere puro perché contiene un solo esempio, magari etichettato male.

Con la convenzione $0\cdot\log_2 0=0$, l’entropia è definita anche quando una classe è assente. ID3 (*Iterative Dichotomiser 3*) e il suo successore C4.5 sono gli algoritmi di Quinlan che usano l’entropia; CART usa di default Gini.

##### **2.5.1. Perché l’entropia binaria è massima in un nodo bilanciato?**

1. Si definiscono le probabilità delle due classi $p$ e $(1-p)$, con $0\le p\le 1$:
$$H(p)=-p\log_2 p-(1-p)\log_2(1-p).$$
2. Si deriva rispetto a $p$:
$$H'(p)=\log_2\frac{1-p}{p}.$$
3. Si trova il punto stazionario: $H'(p)=0\ \Longrightarrow\ \dfrac{1-p}{p}=1\ \Longrightarrow\ p=\dfrac12.$
4. Si verifica che è un massimo per $0<p<1$:
$$H''(p)=-\frac{1}{\ln 2}\left(\frac1p+\frac{1}{1-p}\right)<0.$$

Quindi $H(1/2)=1$ bit (massimo); $H(0)=H(1)=0$ per continuità (nodi puri).

> 💡 **I passaggi della derivata (nota didattica).** Scrivendo $\log_2 p=\ln p/\ln 2$, la derivata di $-p\log_2 p$ è $-\frac{1}{\ln 2}(\ln p+1)$ e quella di $-(1-p)\log_2(1-p)$ è $+\frac{1}{\ln 2}(\ln(1-p)+1)$. Sommando, i termini $\pm 1$ si annullano e resta $\frac{1}{\ln 2}\big(\ln(1-p)-\ln p\big)=\log_2\frac{1-p}{p}$. Derivando ancora: $\frac{d}{dp}\ln(1-p)=-\frac{1}{1-p}$ e $\frac{d}{dp}\ln p=\frac1p$, da cui $H''$. Infine $H(1/2)=-2\cdot\frac12\log_2\frac12=\log_2 2=1$.

##### **2.5.2. Entropia minima e massima**

- **Minimo**: $H_{\min}=0$, quando **tutti gli esempi condividono una classe**.
- **Massimo binario**: $H_{\max}=1$ bit in $(0{,}5;\,0{,}5)$.
- **Tre classi**: $(1/3,1/3,1/3)$ dà $\log_2 3\simeq 1{,}585$ bit.
- **Perché la base 2?** L’**informazione si misura in bit**.

| Caso | Gini massimo | Entropia massima |
|---|---|---|
| **2 classi** | $1-2\cdot 0{,}5^2=0{,}5$ | $1$ bit |
| **3 classi** | $1-3\cdot(1/3)^2\approx 0{,}667$ | $\log_2 3\approx 1{,}585$ bit |
| **$K$ classi** | $1-1/K$ | $\log_2 K$ bit |

![](imgs/Pasted%20image%2020261123215836.png)

#### **2.6. Entropia e guadagno di informazione sono usati ovunque**

Per una divisione in nodi figli $c$, ciascuno con $n_c$ dei $n$ esempi del padre:

$$IG=H(\text{padre})-\sum_{c}\frac{n_c}{n}H(c).$$

**Una buona divisione riduce l’impurità pesata dei suoi nodi figli.**

| Ambito | Uso |
|---|---|
| **alberi di decisione** | scegliere tra le divisioni candidate |
| **selezione delle feature** | misurare quanto una feature è informativa |
| **clustering** | quantificare la mescolanza delle etichette, quando le etichette esistono |
| **regolarizzazione / compressione** | criteri della teoria dell’informazione |

> 💡 **Il calcolo su Play Tennis, versione classica (nota didattica).** Alla radice ci sono 9 «sì» e 5 «no»:
> $$H(S)=-\tfrac{9}{14}\log_2\tfrac{9}{14}-\tfrac{5}{14}\log_2\tfrac{5}{14}\approx 0{,}940,\qquad G(S)=1-\left(\tfrac{9}{14}\right)^2-\left(\tfrac{5}{14}\right)^2\approx 0{,}459.$$
> Dividendo per Outlook: soleggiato (2 sì, 3 no) e pioggia (3 sì, 2 no) hanno $H\approx 0{,}971$, nuvoloso (4 sì) ha $H=0$. Quindi
> $$IG(S,\text{Outlook})\approx 0{,}940-\tfrac{5}{14}(0{,}971)-\tfrac{4}{14}(0)-\tfrac{5}{14}(0{,}971)\approx 0{,}247.$$

![](imgs/Pasted%20image%2020261123215901.png)

> È il guadagno più alto tra le feature: per questo Outlook è la radice. Il valore vale per la tabella classica; con l’umidità modificata delle slide i numeri dei nodi interni possono cambiare, non la scelta della radice (che dipende solo da Outlook e dalle etichette).

> 💡 **L’entropia altrove nel ML (nota didattica).** La stessa quantità compare nella **funzione di perdita cross-entropy**, $L_{CE}=-\sum_i y_i\log\hat{y}_i$, che con un’etichetta one-hot si riduce a $-\log\hat{y}_k$ (grande quando il modello assegna poca probabilità alla classe giusta, Lezione 13), nella **mutua informazione** usata per valutare i cluster e nel **label smoothing**, che evita l’eccessiva confidenza.

#### **2.7. Gini, entropia ed errore di classificazione**

Le tre misure hanno lo **stesso ordinamento di purezza**, ma **scale diverse**: sono **tutte nulle per i nodi puri** e **massime con etichette binarie bilanciate**.

![](imgs/Pasted%20image%2020261123215919.png)

> 💡 **L’errore di classificazione (nota didattica).** Per un nodo è $E=1-\max_k p_k$: la frazione di esempi sbagliati se si assegna al nodo la classe di maggioranza. Nel caso binario vale $\min(p,1-p)$, massimo $0{,}5$ a $p=0{,}5$, come Gini; l’entropia arriva a 1. Gini ed entropia sono curve **lisce e concave**, mentre l’errore è un triangolo: per questo sono preferite per scegliere le divisioni, perché premiano anche divisioni che rendono i figli più puri senza cambiarne la classe di maggioranza.

#### **2.8. Alberi di decisione: algoritmi e vantaggi**

**Algoritmi di addestramento comuni**: **ID3**, **C4.5** e **CART**.

**Vantaggi**:

- **interpretabilità**: le regole sono facili da visualizzare e verificare;
- **versatilità**: feature numeriche e categoriche (con una codifica adatta in alcune librerie);
- **preparazione minima**: di solito **non serve lo scaling** delle feature;
- **non parametrico**: nessuna ipotesi gaussiana sulla distribuzione delle feature.

> 💡 **Perché lo scaling non serve.** Ogni nodo confronta una feature con una soglia: moltiplicare la feature per 1000 sposta la soglia ma non cambia **quali** campioni vanno a destra o a sinistra (Lezione 10, §3.8).

![](imgs/Pasted%20image%2020261123215936.png)

#### **2.9. Alberi di decisione: svantaggi pratici**

- **Overfitting**: alberi più profondi possono imparare il **rumore** e generalizzare male.
- **Instabilità**: piccole modifiche ai record di training possono produrre **un albero diverso**.
- **Dati sbilanciati**: un criterio di divisione ingenuo può favorire la **classe di maggioranza**.

> ⚠️ **Lezione di progetto.** Il fatto che un albero sia **leggibile non dimostra che le sue previsioni siano affidabili**.

#### **2.10. Alberi di decisione: messa a punto e validazione**

- **Potatura o limite di profondità**: impedisce rami eccessivamente dettagliati.
- **Selezione delle feature**: rimuove input irrilevanti o ridondanti, quando è utile.
- **Dimensione minima delle foglie**: impedisce decisioni sostenute da **troppo poche osservazioni**.
- **Ensemble**: combinare più alberi può migliorare la robustezza — è la **random forest** (§3).

Alberi più piccoli possono essere più facili da spiegare, ma **la complessità va comunque validata**.

> ⚠️ **Importante.** Si regola sui **dati di validation**, **non sul test set** finale tenuto da parte (Lezione 14).

![](imgs/Pasted%20image%2020261123220033.png)

---

### **3. Random forest: bagging, instabilità e scelta del modello**


#### **3.1. Perché un singolo albero è instabile**

- Un campione di training **diverso** può cambiare la **migliore divisione alla radice**.
- Questo può **ristrutturare gran parte dell’albero** e cambiare le singole previsioni.
- Il **bagging** addestra ogni albero su un **ricampionamento bootstrap** del training set.
- La **media o il voto** della foresta **riduce la varianza** se gli **errori non sono perfettamente correlati**.

> 💡 **Perché l’instabilità (nota didattica).** L’albero si costruisce con un’**ottimizzazione greedy e locale**: a ogni nodo si sceglie la divisione migliore **in quel momento**, senza tornare indietro. Se due divisioni alla radice sono quasi equivalenti, una piccola variazione dei dati fa vincere l’altra, e tutto ciò che sta sotto cambia. È un modello a **bassa distorsione e alta varianza**, il candidato ideale per una tecnica che riduce proprio la varianza.

> 📌 **Perché la media riduce la varianza (formula standard, nota didattica).** Se $B$ previsori hanno ciascuno varianza $\sigma^2$ e correlazione a coppie $\rho$, la varianza della loro media è
> $$\operatorname{Var}\!\left(\frac1B\sum_{b=1}^{B}h_b\right)=\rho\,\sigma^2+\frac{1-\rho}{B}\,\sigma^2.$$
> Aumentando $B$ il secondo termine scompare, ma il primo resta: per questo conta che gli alberi siano **diversi** (piccolo $\rho$), non solo numerosi. Il bootstrap e le feature casuali servono proprio a ridurre $\rho$.

#### **3.2. Campionamento bootstrap: tre alberi da dati ricampionati**

**Training set etichettato originale** ($n=7$):

$$X=[1\ 2\ 3\ 4\ 5\ 6\ 7],\qquad y=[0\ 0\ 1\ 1\ 2\ 2\ 2].$$

| | Campione bootstrap | Etichette | Albero ottenuto |
|---|---|---|---|
| **bootstrap 1** | $X_1=[1,1,3,4,5,7,7]$ | $y_1=[0,0,1,1,2,2,2]$ | $x\le 2$? → 0; altrimenti $x\le 4$? → 1, sennò 2 |
| **bootstrap 2** | $X_2=[2,3,3,5,5,6,7]$ | $y_2=[0,1,1,2,2,2,2]$ | $x\le 2{,}5$? → 0; altrimenti $x\le 4$? → 1, sennò 2 |
| **bootstrap 3** | $X_3=[1,2,4,4,6,6,7]$ | $y_3=[0,0,1,1,2,2,2]$ | $x\le 3$? → 0; altrimenti $x\le 5$? → 1, sennò 2 |

Ogni albero usa **7 estrazioni accoppiate con reinserimento** (si estrae la coppia $(x_i,y_i)$, non $x$ e $y$ separatamente). **I duplicati e i campioni omessi generano regole diverse.**

![](imgs/Pasted%20image%2020261123220112.png)

> 💡 **Il voto su due campioni nuovi (nota didattica).** Per $x=3$ (classe vera 1): l’albero 1 dice 1 ($3>2$, $3\le 4$), l’albero 2 dice 1, l’albero 3 dice 0 ($3\le 3$): il voto di maggioranza dà **1**, corretto, anche se un albero sbaglia. Per $x=5$ (classe vera 2): alberi 1 e 2 dicono 2, l’albero 3 dice 1 ($5\le 5$): la foresta dice **2**. Gli errori dei singoli alberi cadono in punti diversi e il voto li compensa.

> 📌 **Quanti esempi distinti in un bootstrap (nota didattica).** La probabilità che un esempio non venga mai estratto in $n$ estrazioni con reinserimento è $(1-1/n)^n$, che tende a $e^{-1}\approx 0{,}368$. Un campione bootstrap contiene quindi in media circa il **63,2%** di esempi distinti; il restante 36,8% (*out-of-bag*) può servire a stimare l’errore della foresta senza un validation set separato. Nell’esempio, il bootstrap 1 contiene 5 valori distinti su 7 (mancano 2 e 6).

#### **3.3. Random forest: alberi indipendenti, previsioni combinate**

- Lo **stesso campione di input** viene valutato da **molti alberi**; ogni albero restituisce una previsione.
- Gli alberi sono stati addestrati su **campioni bootstrap diversi**. I **sottoinsiemi casuali di feature** nelle divisioni aggiungono **diversità**.
- Le loro **regole apprese diverse** possono produrre **classi previste diverse**.
- Il **bagging** (*Bootstrap Aggregating*) combina i risultati:
  - **classificazione**: **voto di maggioranza** (o media delle probabilità di classe);
  - **regressione**: **media** delle previsioni numeriche.
- L’output finale combina tutti gli alberi; gli errori, se **imperfettamente correlati**, tendono a compensarsi.

![](imgs/Pasted%20image%2020261123220127.png)

> 📌 **Il prezzo della foresta.** Una foresta **scambia la trasparenza di un singolo percorso di decisione con previsioni aggregate**.

> 📌 **In formula (nota didattica).** Con $B$ alberi $h_1,\dots,h_B$:
> $$\hat{y}_{\text{classificazione}}=\operatorname{moda}\{h_1(x),\dots,h_B(x)\},\qquad \hat{y}_{\text{regressione}}=\frac1B\sum_{b=1}^{B}h_b(x).$$
> La «casualità delle feature» consiste nel considerare, a ogni nodo, solo un sottoinsieme casuale di $m$ feature tra le $d$ disponibili (per la classificazione si usa spesso $m\approx\sqrt{d}$): anche un predittore fortissimo non può dominare la radice di tutti gli alberi, e gli alberi restano diversi.

#### **3.4. L’esempio dei frutti con una random forest**

Dopo la definizione dell’architettura: **100 alberi randomizzati** addestrati sugli **stessi 32 punti didattici**.

![](imgs/Pasted%20image%2020261123220137.png)

> 💡 **Che cosa cambia rispetto all’albero (nota didattica).** Ogni albero produce regioni rettangolari, ma con soglie leggermente diverse; il voto di 100 alberi crea frontiere ancora allineate agli assi ma con «gradini» più fini e una transizione più graduale tra le classi. Le zone in cui gli alberi non concordano corrispondono alle regioni più incerte.

#### **3.5. Albero o random forest: quando usare quale?**

La scelta dipende dall’applicazione: **interpretabilità e audit** contro **robustezza predittiva**, sotto vincoli pratici.

| Usare un **albero di decisione** quando… | Usare una **random forest** quando… |
|---|---|
| le persone devono **spiegare o verificare** le singole decisioni | **accuratezza e robustezza** hanno la priorità |
| gli esperti di dominio devono **ispezionare o validare le regole** | i dati hanno **pattern complessi o rumorosi** |
| il problema è **relativamente piccolo o semplice** | un singolo albero mostra **overfitting / alta varianza** |
| dominano **basso costo di inferenza** e **trasparenza** | una certa **perdita di interpretabilità** è accettabile |

| Proprietà | Albero singolo | Random forest |
|---|---|---|
| **interpretazione** | percorsi leggibili | molti percorsi da aggregare |
| **varianza** | spesso alta | spesso più bassa |
| **training / memoria** | di solito più economico | più alberi addestrati |
| **previsione** | un solo percorso | aggregare molte previsioni |
| **scaling delle feature** | di solito non necessario | di solito non necessario |

> ✅ **Scegliere dai vincoli**: accuratezza, latenza, spiegabilità, dimensione del campione e incertezza della validazione. In una frase: **un albero per spiegare le decisioni, una foresta per migliorare le prestazioni**.

---

### **4. Caso d’uso industriale: un esempio di chimica del vino**


Un dataset pubblico reale; limiti realistici di modellazione.

#### **4.1. Gli strumenti: alberi di decisione in Python**

Costruiamo il primo modello di ML: **dalle misure tabellari a un albero interpretabile**. **Dataset, strumenti software e modello** formano insieme il **flusso di lavoro di apprendimento**.

#### **4.2. L’ispettore del vino: un caso d’uso industriale**

**Motivazione.** La **chimica** aiuta a classificare un campione di vino rispetto a **cultivar di riferimento**.

- Le misure di **composizione** possono supportare lo **screening di laboratorio** e la **tracciabilità**.
- È un esempio di **classificazione del cultivar**, **non di valutazione della qualità** né di **autenticazione di un’etichetta commerciale**.
- I **marchi di vino** nelle fotografie **non sono la ground truth** per le classi UCI.

> 💡 **Il contesto applicativo (nota didattica, dall’edizione precedente).** Nelle grandi cantine la composizione di un vino è questione di tradizione, scienza e disciplinari: il **Chianti**, per esempio, deve contenere almeno il 70% di **Sangiovese**. Il controllo manuale è lento e soggettivo, ed etichette sbagliate o sofisticazioni danneggiano marchio, prezzo e legalità. Un modello che riconosce il cultivar da dati chimici può supportare l’etichettatura e il rilevamento di anomalie, ma solo dopo una validazione su campioni reali e indipendenti: le nuove slide insistono proprio su questo limite.

#### **4.3. La pipeline: misurare, addestrare, prevedere**

1. **Caricare** le feature chimiche e le etichette di classe.
2. **Addestrare** un classificatore ad albero di decisione.
3. **Prevedere** la classe di cultivar per una nuova misura.
4. **Valutare** su campioni etichettati **indipendenti**.

#### **4.4. Caricare il dataset UCI Wine**

**UCI Wine (ID 109)** — https://archive.ics.uci.edu/dataset/109/wine:

- $n=178$ campioni;
- $d=13$ feature chimiche;
- **tre classi di cultivar anonime**;
- **nessun valore mancante** nella versione distribuita.

| Classe (sklearn) | Campioni | Percentuale |
|---:|---:|---:|
| 0 | 59 | 33,1% |
| 1 | 71 | 39,9% |
| 2 | 48 | 27,0% |

- **178 osservazioni** in totale.
- I cultivar hanno **nomi commerciali sconosciuti**.
- Le **immagini di vini famosi sono solo illustrative**, non etichette.

> 💡 **Le 13 feature (nota didattica).** Sono i risultati di un’analisi chimica di vini della stessa regione italiana: alcol, acido malico, ceneri, alcalinità delle ceneri, magnesio, fenoli totali, flavonoidi, fenoli non flavonoidi, proantocianine, intensità del colore, tonalità, rapporto OD280/OD315 dei vini diluiti, prolina. Le scale sono molto diverse (la prolina vale centinaia, la tonalità circa 1): irrilevante per un albero, decisivo per un $k$-NN (§4.11). Un classificatore che dicesse sempre «classe 1» avrebbe già un’accuratezza del 39,9%: è la **baseline** con cui confrontare qualunque modello.

#### **4.5. Caricare etichette e feature in Python**

```python
from sklearn.datasets import load_wine
data = load_wine(as_frame=True)
X, y = data.data, data.target
```

- $X\in\mathbb{R}^{178\times 13}$, $y\in\{0,1,2\}^{178}$.
- I **nomi delle feature** preservano il significato di ogni misura.
- L’oggetto **`Bunch`** del dataset memorizza **tabelle e metadati**.

> 💡 **L’oggetto `Bunch` (nota didattica).** È una struttura di scikit-learn che funziona come un dizionario ma permette anche l’accesso per attributo: `data.data` equivale a `data["data"]`. Con `as_frame=True`, `data.data` è un DataFrame pandas con i nomi delle colonne e `data.target` una Series; `data.DESCR` contiene la descrizione testuale del dataset.

#### **4.6. Ispezionare $X$ e $y$ prima dell’addestramento**

Il vettore target $y$ contiene **un’etichetta di classe per campione**; la matrice delle feature $X$ contiene **13 misure per campione**.

![](imgs/Pasted%20image%2020261123220350.png)

> ✅ **Controllo.** $n$, $d$, **distribuzione delle etichette**, **unità**, **valori mancanti** e **strategia di divisione** appropriata (Lezione 10, §8.3).

#### **4.7. Addestrare e disegnare un albero di decisione**

```python
clf = DecisionTreeClassifier(
    max_depth=3, random_state=42)
clf.fit(X, y)
```

- Un **albero poco profondo** rende **ispezionabili** le sue scelte.
- Il diagramma seguente mostra la **logica di training**, **non una stima imparziale delle prestazioni**.

`random_state=42` fissa il seme casuale, così l’albero è **riproducibile**: quando due divisioni hanno lo stesso guadagno, la scelta tra esse non cambia da un’esecuzione all’altra.

**L’albero a profondità 3**, addestrato sui 178 campioni:

![](imgs/Pasted%20image%2020261123220404.png)

#### **4.8. Leggere l’albero addestrato**

- **Seguire un campione** dalla radice valutando **ogni disuguaglianza**.
- In ogni nodo, ispezionare il **numero di record di training** e la **distribuzione delle classi**.
- Un’**impurità delle foglie vicina a zero non implica errore zero** sui lotti futuri.
- I **percorsi di decisione sono spiegazioni locali**, legate al modello addestrato.

**L’albero a profondità 5.** Aumentare la profondità massima può **ridurre l’impurità di training**, ma può **aumentare la varianza** e la **complessità** dell’albero.

![](imgs/Pasted%20image%2020261123220506.png)

> ⚠️ **Foglie pure sul training non sono una buona notizia.** Se l’albero più profondo arriva a Gini = 0 in ogni foglia, ha **memorizzato** gli esempi di training, compresi eventuali errori ed esempi anomali: è il rischio di overfitting del §2.3. Le prestazioni vere si misurano su dati **mai visti**.

#### **4.9. Classificare e interpretare**

**Classificare una misura esistente.**

```python
sample = X.iloc[[0]]
prediction = clf.predict(sample)
```

`iloc[]` seleziona dati in un DataFrame pandas **con indici di posizione interi**; le doppie parentesi `[[0]]` restituiscono un DataFrame di una riga (con i nomi delle colonne), non una Series.

- Ogni feature chimica assume un **valore numerico**.
- La previsione identifica una delle **tre classi di cultivar anonime**.
- Un **esempio del training set verifica il funzionamento**, non la generalizzazione.

**Interpretare l’output.** La classe prevista è $\hat{y}\in\{0,1,2\}$.

- Il valore riportato è una **classe del modello**, **non un marchio commerciale**.
- Una foglia può mostrare i **conteggi dei campioni di training** e le **probabilità stimate** (`clf.predict_proba`).
- Un risultato corretto su un record di training **non è una verifica indipendente**.

**Classificare una misura nuova.**

- Misurare le **stesse 13 variabili chimiche**, **nelle stesse unità e nello stesso ordine**.
- Usare il modello addestrato per calcolare la previsione del cultivar.
- **Monitorare le misure fuori distribuzione** prima di considerare affidabile il risultato.

**Valutare con cura il risultato su un campione nuovo.**

- Una previsione è un’**ipotesi sul campione**, **non un’identità certificata** del vino.
- **Validare** con vini misurati in modo indipendente e con etichette affidabili.
- Verificare che i **cultivar di training coprano i casi d’uso operativi**.
- **Non identificare la classe 0 con uno specifico Barolo di marca.**

> 💡 **Fuori distribuzione (nota didattica).** Un albero assegna **sempre** una classe, anche a un vino di un quarto cultivar mai visto o a una misura con un errore di unità: il campione cade comunque in qualche foglia. Prima di fidarsi conviene verificare che ogni feature stia nell’intervallo osservato in training, o misurare la distanza dai campioni noti (Lezione 9): è lo stesso problema della «classe nessuno» della Lezione 5.

#### **4.10. Esportare regole di decisione leggibili**

```python
from sklearn.tree import export_text
print(export_text(clf,
      feature_names=list(X.columns)))
```

**Perché è utile?** Permette di **ispezionare esattamente quali feature e soglie** sono state scelte. **La leggibilità da sola non prova stabilità né equità.**

![](imgs/Pasted%20image%2020261123220705.png)

#### **4.11. Addestrare e ispezionare una random forest in Python**

```python
rf = RandomForestClassifier(
    n_estimators=100,
    random_state=42)
rf.fit(X, y)
```

- **Campioni di training bootstrap** più **test casuali sulle feature**.
- **Aggregare molti alberi** invece di ispezionare un solo percorso.

**Ispezionare le previsioni.**

- `rf.predict(new)` genera una **previsione di classe**.
- `rf.feature_importances_` riassume l’**importanza delle divisioni a livello degli alberi** (**non la causalità**).
- **Confrontare con un singolo albero su campioni tenuti da parte.**
- Riportare **dimensione del campione**, **bilanciamento delle classi** e **incertezza**.

![](imgs/Pasted%20image%2020261123220741.png)

> 💡 **Che cosa misura `feature_importances_` (nota didattica).** È la riduzione totale di impurità (Gini) prodotta dalle divisioni su ciascuna feature, sommata su tutti i nodi e mediata sugli alberi, normalizzata a somma 1. Favorisce le feature con molti valori distinti e si divide tra feature correlate; la permutation importance della Lezione 11 (§6.5) è un controllo complementare, calcolato su dati tenuti da parte.

#### **4.12. Alberi e foreste spiegano anche i dati**

- Un **albero spiega quali feature e quali soglie** dividono i record di training.
- Una **foresta aggrega molti alberi** per decisioni più stabili.
- L’**importanza aggregata** può evidenziare le feature che **il modello usa**, **non le cause fisiche**.
- Bisogna **capire i dati e verificare la generalizzazione**, non solo l’accuratezza di training.

> 💡 **Albero e foresta come strumenti di EDA (nota didattica).** Imparando una gerarchia di regole, un albero mostra come i dati si separano in **gruppi omogenei**, quali feature distinguono le classi e dove emergono le frontiere; la foresta, con l’importanza media delle feature, indica quali variabili contano di più **in media** su molte viste diverse dei dati. È un uso esplorativo coerente con il paradosso di Simpson (Lezione 10): un albero che divide prima per «linea di produzione» rivela un gruppo che una media avrebbe nascosto.

#### **4.13. Benchmark Wine: evitare il data leakage**

- **Non ispezionare mai le etichette di test** per scegliere la profondità finale senza una strategia di validazione indipendente.
- Nella **validazione incrociata**, **ogni fold di training addestra il proprio modello**; i fold di validation restano da parte.
- Se lo scaling è necessario (per esempio con **SVM** o **$k$-NN**), va messo **dentro una `Pipeline`**.
- Un **errore di training nullo** valuta solo la **memorizzazione** dei dati addestrati.

> 💡 **Lo schema corretto (nota didattica, ricostruzione non presente nelle slide).**
> 
```python
from sklearn.model_selection import cross_val_score, StratifiedKFold
cv = StratifiedKFold(n_splits=5, shuffle=True, random_state=42)
for depth in (1, 2, 3, 5, None):
	clf = DecisionTreeClassifier(max_depth=depth, random_state=42)
    scores = cross_val_score(clf, X, y, cv=cv)
    print(depth, scores.mean().round(3), scores.std().round(3))
```

La profondità si sceglie dalla media e dalla deviazione standard dell’accuratezza sui fold (Lezione 14); il test set finale, se esiste, si usa una sola volta alla fine. `StratifiedKFold` mantiene in ogni fold le proporzioni 33/40/27% delle classi.

#### **4.14. Appendice: accesso ufficiale a UCI Wine**

```bash
pip install ucimlrepo
```

```python
# uso minimo (il download richiede l'accesso alla rete)
from ucimlrepo import fetch_ucirepo
wine = fetch_ucirepo(id=109)
X = wine.data.features
y = wine.data.targets
print(wine.metadata)
print(wine.variables)
```

Fonte: https://archive.ics.uci.edu/dataset/109/wine. Copia alternativa offline: `datasets/wine_uci109_sklearn_mirror.csv`.

> ⚠️ **Due versioni, due codifiche delle classi (nota didattica).** Nel file UCI le classi sono numerate **1, 2, 3**; in scikit-learn **0, 1, 2**. Mescolare codice e risultati delle due versioni senza accorgersene sposta tutte le etichette di uno: un errore silenzioso del tipo descritto nella Lezione 8 (conversioni e interscambio).

---

### **5. Simulazione d’esame: dieci quesiti risolti**


Quesito, risposta evidenziata, spiegazione; altri dieci quesiti sono riservati all’aula.

#### **5.1. Preparare l’approccio**

Questa è una **simulazione**, **non un sostituto dello studio**.

- **Leggere tutta la domanda** e fare attenzione alle **negazioni**.
- **Trovare perché ogni distrattore è falso**, prima di decidere.
- C’è **una sola risposta corretta** per ogni domanda.
- Le **lezioni non campionate qui contano comunque** all’esame.

> 📌 **Il metodo d’esame del corso.** Non cercare la risposta giusta: **passo 0**, leggere il testo con attenzione a negazioni e indicazioni («non crescente», «minore di»…); **passo 1**, **scartare** le risposte sbagliate con un ragionamento, un controesempio, una nozione o un calcolo di dimensionamento; **passo 2**, scegliere la risposta che resta, **la meno sbagliata**.

Pronti per i dieci quesiti? *On your mark… get set…* Ogni quesito è riportato con il testo e le opzioni originali, la **risposta evidenziata nel PDF** e il **contesto d’aula**. Per ogni quesito la slide di contesto ripete che la risposta convalidata è quella mostrata, che testo e opzioni sono stati **volutamente preservati** e che, come discusso in aula, si tratta di **una semplificazione, ma di una regola pratica molto utile**.

#### **5.2. I dieci quesiti**

**Q1 — Sistemi intelligenti: sorgenti di dati.** Considerando i dispositivi IoT come fonte di dati per sistemi intelligenti **esterni** (il sistema intelligente non è pensato per essere integrato nel dispositivo IoT), quali tipi di dispositivi IoT si possono davvero usare?

1. dispositivi IoT a dati passivi;
2. dispositivi IoT a dati attivi;
3. dispositivi IoT a dati dinamici;
4. **tutti i precedenti**;
5. nessuno dei precedenti.

**Risposta: 4.** Promemoria della slide: **dati passivi non significa un’applicazione dati/IoT passiva**. Poiché i sensori vanno **gestiti**, è l’applicazione a doversi far carico della logica necessaria (interrogarli quando servono i dati). Le tre categorie (passiva, attiva, dinamica) sono quelle della Lezione 5.

---

**Q2 — Progettazione dei sistemi intelligenti.** Secondo la discussione in aula, la pratica di progettazione (corretta) per le reti neurali prevede di:

1. partire dai modelli di deep learning, perché sono la tecnologia più avanzata di cui disponiamo;
2. partire dai modelli di deep learning, perché sono la tecnologia più avanzata, e poi usare i metodi classici come riferimento;
3. **partire da reti neurali semplici prima di considerare i modelli di deep learning**.

**Risposta: 3.** È un’**applicazione del rasoio di Occam** (Lezione 5): a parità di prestazioni si preferisce il modello più semplice, e si aumenta la complessità solo quando dati e validazione lo giustificano.

---

**Q3 — Pre-elaborazione dei dati: dati mancanti.** I valori mancanti si possono anche riempire calcolando la media, la moda o la mediana dei valori osservati:

1. è molto insolito e poco comune nella pratica;
2. **è una soluzione molto semplice ed efficace nel caso in cui il metodo di apprendimento non sia in grado di gestire i dati mancanti**;
3. non è possibile, perché sono solo statistiche descrittive delle feature e non si possono usare per riempire i dati mancanti.

**Risposta: 2** (imputazione, Lezione 6). Va ricordato che le statistiche di imputazione si calcolano **sul solo training set** e che l’imputazione riduce la varianza della feature.

---

**Q4 — Generalizzazione e preparazione del dataset.** Riferendosi alla discussione in aula sul data leakage, qual è la situazione peggiore?

1. **il passaggio indesiderato di dati dal dataset di test al dataset di training**;
2. il passaggio indesiderato di dati dal dataset di training al dataset di test;
3. nessuna delle precedenti, perché trasferire dati dal test e/o dal training è normale quando si misura l’accuratezza del modello.

**Risposta: 1.** Commento della slide: poiché un **modello grande può imparare perfettamente** (fino all’overfitting) i dati di training, se si **sottraggono dati al test di generalizzazione** la situazione diventa **più ottimistica** (Lezione 7).

---

**Q5 — Generalizzazione e preparazione del dataset.** Un’informazione aggiuntiva può permettere al modello di imparare o sapere qualcosa che altrimenti non saprebbe, e a sua volta **invalidare la stima delle prestazioni** del modello in costruzione. Questo si chiama:

1. **data leakage**;
2. pre-elaborazione dei dati;
3. armonizzazione dei dati;
4. data wrangling.

**Risposta: 1.**

---

**Q6 — #DoF.** I gradi di libertà (#DoF) di un problema sono il numero di variabili indipendenti che vanno specificate per determinare univocamente una soluzione. Quindi è importante considerare il #DoF:

1. per progettare il numero di vettori del dataset di apprendimento;
2. per evitare il problema dell’overfitting nel modello;
3. **tutte le precedenti**;
4. nessuna delle precedenti.

**Risposta: 3** (Lezione 7). Il contesto d’aula precisa: in senso formale i gradi di libertà riguardano le **variabili indipendenti**; i collegamenti con la **dimensione del dataset** e l’**overfitting** sono **euristiche di progettazione d’aula**, non un teorema universale.

---

**Q7 — Metriche di base per la similarità: il coseno.** A proposito della metrica coseno si può dire che:

1. due vettori con la stessa orientazione hanno similarità coseno 1;
2. due vettori orientati a 90° l’uno rispetto all’altro hanno similarità 0;
3. **tutte le precedenti**;
4. nessuna delle precedenti.

**Risposta: 3** (Lezione 8; Lezione 9, §8.5): $\cos 0^\circ=1$, $\cos 90^\circ=0$.

---

**Q8 — Similarità nei dataset di immagini.** Quale feature (o quali feature) discusse in aula offre la proprietà di permettere un **confronto veloce** basato su un **breve vettore 1D** di elementi o di bit?

1. pHash (*p* = percettivo, basato sulla trasformata discreta del coseno);
2. aHash (*a* = media: bit a 0 se il grigio è sotto il grigio medio);
3. **tutte le precedenti**;
4. cross-correlazione (questa è sbagliata).

**Risposta: 3.** La cross-correlazione confronta mappe di pixel su ogni spostamento ed è costosa. La slide aggiunge: **lo stesso vale per il confronto degli istogrammi**, anch’esso basato su un vettore breve (768 valori, Lezione 9, §2.8).

---

**Q9 — Similarità nel dataset.** In accordo con la discussione in aula, quale descrizione caratterizza meglio l’attività di progettazione?

1. la similarità nel dataset richiede più spazio e tempo di elaborazione;
2. la similarità nel dataset può migliorare la generalizzazione;
3. **entrambe le precedenti**;
4. nessuna delle precedenti.

**Risposta: 3.** C’è un **«dilemma» tra i casi 1 e 2** (Lezione 8): esempi simili costano memoria e tempo (e i duplicati accidentali sono uno spreco), ma varianti simili, come quelle della data augmentation, aiutano la generalizzazione. Il contesto d’aula precisa: la similarità può aiutare la generalizzazione **solo quando raccolta e divisione dei dati preservano l’indipendenza voluta** tra training e test.

---

**Q10 — Preparazione del dataset: cardinalità delle operazioni di controllo.** In accordo con la discussione in aula, in un dataset di **1100 immagini etichettate** la ricerca dei duplicati si fa tipicamente:

1. esplorando manualmente il dataset, per risultati migliori, perché il numero di immagini non è critico;
2. **con iterazioni automatiche** (confronti).

**Risposta: 2.** Un controllo di base richiede $\#\text{iter}=N(N-1)>1{,}2$ milioni:

$$N(N-1)=1100\cdot 1099=1\,208\,900.$$

Il contesto d’aula precisa: la risposta di riferimento conta i **confronti ordinati** $N(N-1)$; se ogni **coppia non ordinata** si confronta una sola volta, il numero è $N(N-1)/2=604\,450$ (Lezione 8).

> 💡 **Il metodo applicato (nota didattica).** In molti quesiti la risposta giusta si trova **escludendo**: in Q3 l’opzione 3 è falsa perché l’imputazione con media o mediana è un metodo standard; in Q4 l’opzione 3 contraddice la definizione stessa di leakage; in Q10 un calcolo di dimensionamento (oltre un milione di confronti) esclude l’esplorazione manuale.

#### **5.3. E gli argomenti non presenti nella simulazione?**

- **L’intero corso resta materia d’esame.**
- **Un argomento assente qui non è un argomento a bassa priorità.**
- Usare le simulazioni per **esercitare il ragionamento** e l’**esclusione delle risposte sbagliate**, non per memorizzare.
- La sessione in aula ha **dieci quesiti aggiuntivi**.

> ⚠️ **Numerazione delle slide d’esame.** Le slide dei quesiti riportano a piè di pagina i numeri 48–76, ereditati dal documento della simulazione dell’edizione precedente, mentre nel PDF 2026/27 occupano le pagine 62–91.

---

### **6. Punti principali e materiale aggiuntivo**


#### **6.1. Punti principali**

> ✅ **Punti principali della lezione.**
> - Un **albero codifica una sequenza di divisioni interpretabili** sulle feature.
> - **Gini ed entropia** valutano la **mescolanza delle etichette**, **non la causalità**.
> - Una **profondità maggiore** può aumentare **varianza e overfitting**.
> - **Alberi randomizzati con bagging** possono **stabilizzare le previsioni**.

> ✅ **Idee chiave da conservare dopo questa lezione.**
> - **Tennis**: 14 record didattici; **frutti**: punti didattici simulati; **Wine**: 178 osservazioni UCI.
> - Usare una **validazione su dati tenuti da parte** e **preservare la provenienza** del dataset.
> - **In aula** ci sono **dieci ulteriori quesiti**.

> ⚠️ **Un’incongruenza dei punti principali.** La slide finale descrive i frutti come «56 punti approssimativamente digitalizzati», mentre le slide 10–15 e 33 parlano di **32 punti simulati**: il riferimento corretto per l’esempio della lezione è quello delle slide di contenuto (32 punti simulati).

#### **6.2. Le dieci domande aggiuntive dell’edizione precedente**

La simulazione di metà corso dell’edizione 2025/26 comprendeva venti quesiti: i primi dieci coincidono con quelli del §5.2, mentre gli altri dieci (sulle Lezioni 8–11 e sul quadro normativo) non compaiono nel PDF 2026/27. Sono riportati qui come esercizio aggiuntivo, utile in vista dei dieci quesiti riservati all’aula; le risposte sono quelle evidenziate nel PDF dell’edizione precedente. Le regole di quella simulazione erano: 1 punto per risposta corretta, 0 per risposta sbagliata, **nessun peso negativo** (meglio quindi rispondere sempre).

**D11 — Errori di etichettatura.** Qual è in genere il caso peggiore per l’accuratezza di generalizzazione? ERR1 = duplicati con la **stessa** etichetta; ERR2 = duplicati con etichette **diverse**. 1. ERR1; **2. ERR2**; 3. equivalenti.
**Risposta: 2.** I duplicati con etichette opposte danno al modello due risposte contraddittorie per lo stesso input; quelli con la stessa etichetta sono solo uno spreco (Lezione 8). La slide originale scriveva «EER2» ed «EE2» per ERR2.

**D12 — Correlazione e convoluzione.** **1. sono molto simili nel significato e nell’espressione matematica**; 2. espressione simile, ma significato e uso completamente diversi; 3. nessuna relazione.
**Risposta: 1.** Differiscono solo per il ribaltamento del kernel (Lezione 9, §3.4).

**D13 — Autocorrelazione.** Che caratteristica ha la mappa `O = xcorr2(A, A)` di un’immagine generica? 1. un plateau centrale piatto e rumoroso; **2. un picco evidente al centro con un massimo ben definito**; 3. non si può calcolare con una sola immagine.
**Risposta: 2** (Lezione 9, §3.8.1). L’opzione 3 è falsa per definizione: l’autocorrelazione usa proprio una sola immagine.

**D14 — Outlier estremi.** Con outlier estremi conviene: **1. clipping delle feature**; 2. normalizzazione min-max; 3. norma z.
**Risposta: 1** (Lezione 10, §3.12), con l’avvertenza che il clipping è una politica, non un default: non va usato se gli outlier sono proprio i guasti da rilevare.

**D15 — Scalatura logaritmica.** Si applica tipicamente in caso di: 1. outlier; 2. valori negativi; **3. un intervallo di valori molto ampio (> 0)**.
**Risposta: 3.** In questo caso min-max e z-score non sono efficaci; l’opzione 2 è esclusa perché il logaritmo non è definito per valori negativi (Lezione 10, §3.13).

**D16 — Variabili visive.** Per disegnare molte figure di merito di una rete su un nuovo dataset, qual è l’ordine corretto degli attributi visivi da **sinistra: bassa accuratezza** a **destra: alta accuratezza**? 1. intensità del colore > tinta > lunghezza; 2. area > lunghezza > tinta; 3. pendenza > angolo > volume; **4. tinta > area > lunghezza**.
**Risposta: 4.** L’ordine va dall’attributo letto con meno precisione (la tinta) a quello letto con più precisione (la lunghezza): è la classifica degli indizi visivi della Lezione 11, §1.6.2.

**D17 — Intervalli di confidenza.** Si possono rappresentare nello stesso grafico delle figure di merito? 1. no, hanno unità e significato diversi; **2. sì, hanno le stesse unità e lo stesso significato**.
**Risposta: 2** (Lezione 11, §5.14.2: errori e $\sigma$ condividono l’unità, quindi la banda condivide l’asse).

**D18 — Passi con visualizzazione.** Nel flusso 1) prendere i dati, 2) pulire e manipolare, 3) addestrare, 4) testare, 5) migliorare il progetto, in quali passi entra principalmente la visualizzazione? A. #1; B. #5; C. #1 e #5; **D. #2, #3 e #5**.
**Risposta: D.** Corrisponde ai tre momenti I, II, III della Lezione 11, §1.1 (nella versione 2026/27 il momento II comprende anche il passo 4).

**D19 — Autocorrelazione con pattern ripetuti.** Per un’immagine con similarità interna (pattern ripetuti), l’uscita di `xcorr2(A, A)`: A. non si può calcolare; B. è un plateau piatto con un picco centrale; **C. ha molti picchi e un massimo evidente**; D. ha molti picchi equivalenti con lo stesso massimo.
**Risposta: C.** I picchi secondari corrispondono agli spostamenti in cui i pattern si sovrappongono; il massimo assoluto resta al centro, a spostamento nullo (il reticolo di picchi dei coni, Lezione 9, §3.8.1). I difetti si trovano come mancanze in questo pattern regolare.

**D20 — Quadro normativo.** Il quadro normativo UE sull’AI è: A. centrato sui servizi pubblici; B. sulla sanità; C. sulla privacy dei dati; **D. nessuna delle altre opzioni**.
**Risposta: D**, «perché è molto più ampio» (Lezione 2): l’AI Act regola i sistemi di AI per livello di rischio in tutti i settori, non un singolo ambito.

---

### **7. Distinzioni da non confondere**


| Coppia | Differenza chiave |
|---|---|
| **nodo** / **foglia** | testa il valore di una feature / specifica una classe |
| **albero binario** / **a divisione multipla** | due figli, più profondo, potatura semplice / più rami, meno profondo |
| **albero profondo** / **poco profondo** | regole ricche, rischio di overfitting / facile da spiegare, rischio di underfitting |
| **profondità** / **impurità** | numero di test in sequenza / quanto sono mescolate le etichette in un nodo |
| **Gini** / **entropia** / **errore di classificazione** | $1-\sum p_k^2$, CART / $-\sum p_k\log_2 p_k$ in bit, ID3 e C4.5 / $1-\max p_k$ |
| **entropia 0** / **entropia massima** | nodo puro / classi equiprobabili ($1$ bit se binario, $\log_2 K$ con $K$ classi) |
| **frontiera di un albero** / **frontiera lineare** | a scalini, allineata agli assi / retta obliqua |
| **albero leggibile** / **previsioni affidabili** | regole ispezionabili / richiedono validazione su dati non visti |
| **bootstrap** / **bagging** | estrazione con reinserimento di coppie $(x_i,y_i)$ / aggregazione delle previsioni (voto o media) |
| **albero** / **random forest** | spiegabile ma instabile, alta varianza / robusta, varianza più bassa, meno spiegabile |
| **più alberi** / **alberi diversi** | riducono solo la parte non correlata della varianza / riducono la correlazione $\rho$ |
| **Gini = 0 sul training** / **buon modello** | albero che ha memorizzato / modello che generalizza |
| **classe UCI 0/1/2** / **marchio commerciale** | etichetta anonima del modello / identità che il modello non certifica |
| **classi UCI 1/2/3** / **classi sklearn 0/1/2** | codifica del file originale / codifica di scikit-learn |
| **`feature_importances_`** / **causalità** | quanto il modello usa le feature / cause fisiche (non dimostrate) |
| **validation** / **test** | dove si regolano profondità e iperparametri / stima finale, usata una sola volta |
| **leakage test → training** / **training → test** | caso peggiore (stima ottimistica) / caso meno grave |
| **confronti ordinati** / **coppie non ordinate** | $N(N-1)$ / $N(N-1)/2$ |
