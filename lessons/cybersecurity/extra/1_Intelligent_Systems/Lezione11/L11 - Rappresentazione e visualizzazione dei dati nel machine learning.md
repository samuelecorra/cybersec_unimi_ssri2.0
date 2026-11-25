## **Lezione 11: Dalla visualizzazione dei dati alla visual analytics assistita dall’AI**

La lezione percorre sei verbi: **capire**, **ispirarsi**, **comprendere i sistemi di AI**, **creare**, **sfidare**, **riflettere**. Si parte dalla visualizzazione classica — come percepiamo, come codifichiamo i dati in segni grafici, come scegliamo un grafico a partire dal messaggio — e dal monitoraggio del **drift** dopo il rilascio, misurato con il **Population Stability Index**. Si passa poi agli esempi di visualizzazione esperta e, soprattutto, alla visualizzazione di un **sistema di AI** nelle sue quattro dimensioni: i **dati**, il **modello**, le **prestazioni** e il **funzionamento operativo**, seguendo un piccolo esperimento su cifre scritte a mano e un caso di manutenzione predittiva. L’ultima parte affida la produzione dei grafici a un **agente di AI di frontiera** e mostra, sui conteggi del paradosso di Simpson, che quattro grafici corretti degli stessi otto numeri possono raccontare quattro storie diverse: l’agente automatizza la produzione, ma la domanda, i gruppi e le verifiche restano responsabilità del progettista.

> **Fonte.** La lezione segue il PDF dell’edizione 2026/27 `sources_2026-27/L11-web-v24.pdf` (154 pagine). I segnaposto per le figure indicano la pagina di quel PDF; il materiale dell’edizione precedente non più presente nelle slide è conservato nel §12.


### **0. Panoramica**


La lezione si articola in sei parti:

1. **capire**: la visualizzazione classica;
2. **ispirarsi**: la visualizzazione esperta;
3. **comprendere i sistemi di AI** attraverso la visualizzazione: dati, modello, prestazioni, drift;
4. **creare**: l’AI di frontiera come agente di visualizzazione;
5. **sfidare**: un agente di AI crea quattro visualizzazioni di un dataset scelto da noi;
6. **riflettere**: il progettista umano resta responsabile.

![](imgs/Pasted%20image%2020261123200747.png)

#### **0.1. Perché l’EDA prima del ML?**

La sequenza corretta è **dati grezzi → EDA → conclusioni, poi ML**. L’analisi esplorativa serve a:

- **verificare il numero** di campioni e di feature;
- individuare **valori mancanti**, **outlier** e **intervalli sbagliati**;
- capire **distribuzioni** e **scale** delle feature;
- preparare **feature engineering** e **riduzione della dimensionalità**;
- individuare **cluster** o **bias** visibili **prima** dell’addestramento;
- **evitare di addestrare** un modello su dati **fraintesi**.

---

### **1. Visualizzazione classica: come vediamo, come codifichiamo i dati, come scegliamo un grafico**


#### **1.1. La visualizzazione si usa in tre momenti del flusso di lavoro**

| Passo del flusso | Momento | Uso della visualizzazione |
|---|---|---|
| **1** ottenere i dati | — | — |
| **2** pulire, preparare e manipolare i dati | **I** | visualizzazione per l’analisi esplorativa (2a *data munging*, 2b EDA con visualizzazione, 2c feature engineering) |
| **3** addestrare il modello | **II** | curve di addestramento, errori |
| **4** testare | **II** | prestazioni, **che cosa guarda il modello** |
| **5** migliorare | **III** | dopo il rilascio: **monitorare** dati, previsioni e prestazioni |

![](imgs/Pasted%20image%2020261123200816.png)


<!-- SOURCE_VISUAL id="IS11-V01" source="L11-web-v24.pdf" page="5" type="schema" description="Flusso di lavoro del ML in cinque passi con i tre momenti I, II, III della visualizzazione" reason="Schema del flusso visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123200926.png)

#### **1.2. Un’immagine vale più di mille parole**

Il proverbio inglese (*a picture is worth a thousand words*) è illustrato da una curva: con un solo sguardo racconta ciò che una **tabella di 28 valori annuali** nasconderebbe — una tendenza, un’inversione, un’anomalia.

<!-- SOURCE_VISUAL id="IS11-V02" source="L11-web-v24.pdf" page="6" type="grafico" description="Curva di 28 valori annuali che mostra a colpo d'occhio la tendenza nascosta nella tabella" reason="Curva visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123200938.png)

Gli argomenti della parte classica sono: che cos’è la visualizzazione dei dati; come funziona (teoria dell’immagine, definizione di rappresentazione per immagini, usi attuali); quali sono i benefici; esempi.

![](imgs/Pasted%20image%2020261123201020.png)

#### **1.3. Perché (quasi) solo grafici 2D e 3D?**

- Viviamo nello **spazio-tempo**: un modello matematico che fonde le **tre dimensioni dello spazio** e **quella del tempo** in un unico continuo quadridimensionale.
- Ma la nostra **percezione è limitata**: solo i grafici **2D** e, raramente, **3D** si valutano e si capiscono facilmente.
- Per questo la **riduzione della dimensionalità** è così importante: la PCA trasforma per esempio **4 feature** $F_1,\dots,F_4$ in nuove feature $F'_1,\dots,F'_4$, di cui **2 salienti** possono essere disegnate.
- Conviene inoltre aggiungere informazione **oltre i 2–3 assi** (colore, forma, dimensione: §1.6).

<!-- SOURCE_VISUAL id="IS11-V03" source="L11-web-v24.pdf" page="8" type="schema" description="PCA da 4 feature F1-F4 a 2 feature salienti F'1, F'2" reason="Schema presente solo come figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123201042.png)

##### **1.3.1. Oltre la PCA: t-SNE e UMAP sugli esempi della lezione**

Tre metodi di proiezione in 2D sono applicati a due dataset usati nella lezione:

- **cifre scritte a mano**: 1797 immagini, 64 feature, 10 classi;
- **stati di una macchina**: 360 campioni, 8 feature, 3 stati.

Ogni metodo restituisce **2 coordinate** per campione. Due indicatori misurano quanto la proiezione conserva la struttura:

- **kNN**: accuratezza di un classificatore **5-nearest-neighbour** addestrato **sulle sole 2 coordinate**;
- **trust.** (*trustworthiness*, affidabilità): frazione dei vicini in 2D che **erano vicini anche nello spazio originale** (1 = perfetto).

| Dataset | PCA (kNN · trust.) | t-SNE (kNN · trust.) | UMAP (kNN · trust.) |
|---|---|---|---|
| **cifre** (64 feature, 10 classi) | 60% · 0,83 | 98% · 0,99 | 98% · 0,99 |
| **stati della macchina** (8 feature, 3 stati) | 100% · 0,91 | 100% · 0,96 | 100% · 0,95 |

<!-- SOURCE_VISUAL id="IS11-V04" source="L11-web-v24.pdf" page="9" type="grafico" description="Proiezioni PCA, t-SNE e UMAP delle cifre e degli stati della macchina con kNN e trustworthiness" reason="Nuvole di punti colorate per classe visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123201054.png)

> ⚠️ **Leggere con cautela.** Gli **assi della PCA hanno un significato** (combinazioni lineari delle feature, ordinate per varianza); **t-SNE e UMAP conservano soltanto i vicinati, non le distanze**. Sulle cifre la PCA lineare non basta (60%), mentre i metodi non lineari separano bene le classi; sugli stati della macchina, caso facile, tutti i metodi funzionano. Se ne parlerà più a fondo in una lezione successiva (Lezione 18).

> 💡 **Perché un 5-NN come metro di giudizio (nota didattica).** È il $k$-NN della Lezione 9 usato come «sonda»: se nella proiezione i punti della stessa classe stanno vicini, un classificatore che guarda solo i 5 vicini in 2D sbaglia poco. Un’accuratezza bassa dice che la proiezione ha mescolato le classi, anche se nello spazio originale erano separabili.

#### **1.4. Un cluster suggerisce un segmento, non lo spiega**

- In altre lezioni del corso si vedono i principali metodi di **clustering** (Lezione 19).
- Un **cluster è un’ipotesi**: clienti con **comportamento simile**, non una **causa dimostrata**.
- Come usarlo, in tre passi:
  1. scegliere il cluster (per esempio **clienti giovani con spesa alta**);
  2. inviare un’**offerta di prova** a una **metà casuale** del cluster, niente all’altra metà;
  3. confrontare gli acquisti delle due metà: **solo la differenza** dice se l’offerta funziona.
- Verificare **privacy ed equità** prima di inviare qualunque offerta.

<!-- SOURCE_VISUAL id="IS11-V05" source="L11-web-v24.pdf" page="10" type="grafico" description="Cluster di clienti giovani con spesa alta in un grafico età-spesa" reason="Cluster visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123201121.png)

> 💡 **Perché la metà casuale (nota didattica).** Se si mandano coupon solo ai giovani «alti spendenti» e questi comprano, non si sa se è merito del coupon: comprerebbero comunque molto. Dividendo il cluster **a caso**, le due metà sono statisticamente uguali in tutto tranne che nell’offerta, e la differenza negli acquisti misura l’effetto dell’offerta. È un esperimento controllato randomizzato (A/B test), lo strumento che separa correlazione e causalità.

#### **1.5. Che cos’è la visualizzazione dei dati?**

> 📌 **Visualizzazione dei dati.** È il processo di **convertire dati grezzi** in **immagini di informazione facilmente comprensibili**, che permettono **decisioni rapide ed efficaci**.

All’inizio del Novecento gli psicologi della **Gestalt** osservarono che, quando degli elementi vengono riuniti in una figura, la figura acquista una **salienza percettiva** che **supera la somma delle sue parti** (Lezione 2).

<!-- SOURCE_VISUAL id="IS11-V06" source="L11-web-v24.pdf" page="11" type="figura" description="Illustrazione gestaltica: elementi riuniti in una figura con salienza superiore alla somma delle parti" reason="Figura raster della slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123201135.png)

**Dai dati a immagini facilmente comprensibili.** **Jacques Bertin**, autore del classico *Sémiologie graphique* (*Semiologia della grafica*), afferma che la «trasformazione dai numeri all’intuizione richiede **due fasi**»:

$$\text{dati}\ \xrightarrow{\ \text{costruzione}\ }\ \text{immagine}\ \xrightarrow{\ \text{percezione}\ }\ \text{intuizione}.$$

<!-- SOURCE_VISUAL id="IS11-V07" source="L11-web-v24.pdf" page="12" type="figura" description="Le due fasi di Bertin dai numeri all'intuizione e ritratto di Jacques Bertin" reason="Schema e fotografia visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123201156.png)

#### **1.6. Le 7 variabili visive di Bertin**

> 📌 **Le sette variabili visive.** **Posizione**, **forma**, **orientamento**, **colore**, **texture**, **valore** (chiaroscuro), **dimensione**. Si combinano con una **semantica visiva** che collega gli **attributi dei dati** (numeri, categorie, ordine) agli **elementi visivi** (i segni sulla pagina).

<!-- SOURCE_VISUAL id="IS11-V08" source="L11-web-v24.pdf" page="13" type="schema" description="Le sette variabili visive di Bertin e la semantica visiva tra attributi dei dati ed elementi visivi" reason="Esempi grafici delle variabili visibili solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

##### **1.6.1. Codificare i dati con indizi visivi**

Ogni grafico codifica i numeri con **indizi visivi** (*visual cues*): la **lunghezza** di una barra, la **pendenza** di una linea, l’**angolo** tra due linee, l’**area** di un disco, la **tinta** di un colore o la sua **intensità**.

<!-- SOURCE_VISUAL id="IS11-V09" source="L11-web-v24.pdf" page="14" type="infografica" description="Indizi visivi: lunghezza, pendenza, angolo, area, tinta e intensità del colore" reason="Esempi di codifica visibili solo nell'infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123201238.png)

##### **1.6.2. Non tutte le caratteristiche visive sono dello stesso tipo**

Gli indizi si possono **ordinare**: dal più **preciso** (la **lunghezza allineata**, cioè barre con una base comune) al più **generico** (la **tinta**). Quando il lettore deve **confrontare valori con precisione**, si sceglie un indizio vicino alla **cima** della classifica.

<!-- SOURCE_VISUAL id="IS11-V10" source="L11-web-v24.pdf" page="15" type="infografica" description="Classifica degli indizi visivi dal più preciso (lunghezza allineata) al più generico (tinta)" reason="Classifica visibile solo nell'infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123201251.png)

> 💡 **Da dove viene la classifica (nota didattica).** Gli esperimenti di Cleveland e McGill (1984) hanno misurato con quale precisione le persone confrontano quantità codificate in modi diversi: posizioni su una scala comune e lunghezze allineate sono lette meglio di angoli, aree, volumi e colori. Per questo un **grafico a barre** si legge meglio di un **grafico a torta**: le barre usano lunghezze allineate, la torta usa angoli e aree. Bertin distingue inoltre che cosa ogni variabile permette di leggere: posizione e dimensione mostrano **quantità e ordine**, il valore un **ordine**, tinta, forma e texture distinguono **categorie** ma non le ordinano.

#### **1.7. La teoria dell’immagine**

L’elaborazione visiva avviene in tre passi:

1. **formazione dell’immagine sulla retina**;
2. **scomposizione** dell’informazione retinica in un insieme di **rappresentazioni specializzate**;
3. **ricomposizione** dell’informazione nella **percezione degli oggetti**.

<!-- SOURCE_VISUAL id="IS11-V11" source="L11-web-v24.pdf" page="16" type="schema" description="I tre passi dell'elaborazione visiva dalla retina alla percezione degli oggetti" reason="Schema visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123201306.png)

> 📌 **Perché conta per il progettista.** Il passo 2 avviene **prima che pensiamo**: lunghezza, posizione, orientamento, dimensione e colore vengono estratti **in parallelo**, in una frazione di secondo. Quindi l’indizio scelto decide **che cosa il lettore vede per primo** e **con quanta precisione**. Il messaggio principale va affidato agli indizi che l’occhio legge meglio (lunghezza allineata e posizione, classificati nel §1.6.2); la tinta va usata per **poche categorie**; e si tiene **un messaggio per grafico**.

#### **1.8. Importante per gli utenti e per i progettisti di sistemi intelligenti**

- La visualizzazione dà al software un’**interfaccia grafica intuitiva**: gli utenti ricavano informazioni dai propri dati per decisioni **più rapide e informate**.
- **Gli utenti, ma anche voi: il progettista.** Tre dashboard che si costruiranno nel corso:

| Dashboard | Fase | Contenuto |
|---|---|---|
| **addestramento** | passo 3 | perdita (*cross-entropy*) e accuratezza per epoca |
| **test** | passo 4 | dove sbaglia il modello (matrice di confusione) |
| **rilascio** | passo 5 | prestazioni nel tempo, drift (PSI), allarmi |

<!-- SOURCE_VISUAL id="IS11-V12" source="L11-web-v24.pdf" page="17" type="grafico" description="Tre dashboard del corso: curve di addestramento, matrice di confusione, dashboard di rilascio con AUC, PSI e allarmi" reason="Tre dashboard visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123201324.png)

#### **1.9. Quale grafico?**

Si parte dal **messaggio**: **distribuzione**, **relazione**, **confronto**, **connessione**, **composizione** o **posizione geografica**. Poi si seguono i rami dalla domanda al tipo di grafico.

![](imgs/Pasted%20image%2020261123201337.png)


<!-- SOURCE_VISUAL id="IS11-V13" source="L11-web-v24.pdf" page="18" type="infografica" description="Guida alla scelta del grafico a partire dal messaggio" reason="Albero decisionale presente solo come infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V14" source="L11-web-v24.pdf" page="19" type="infografica" description="Guida alla scelta del grafico (continua): rami dalla domanda al tipo di grafico" reason="Albero decisionale presente solo come infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123201347.png)

Le slide successive applicano la guida a dati sintetici: **200 clienti**, **10 reparti**, **12 mesi**.

##### **1.9.1. Confronto: barre per confrontare, una linea per il tempo**

| Situazione | Grafico | Comando |
|---|---|---|
| confronto **tra pochi elementi** | grafico a **colonne** (spesa media per fascia d’età: 18-29, 30-39, 40-49, 50+) | `plt.bar(labels, means)` |
| confronto **tra molti elementi** | grafico a **barre orizzontali** (vendite in k€/mese di 10 reparti: alimentari, latticini, panetteria, giocattoli, libri, giardino, animali, sport, musica, utensili) | `plt.barh(departments, sales)` |
| confronto **nel tempo**, molti periodi | grafico a **linee** (vendite per mese) | `plt.plot(months, sales, 'o-')` |

<!-- SOURCE_VISUAL id="IS11-V15" source="L11-web-v24.pdf" page="20" type="grafico" description="Colonne per fasce d'età, barre per 10 reparti, linea delle vendite su 12 mesi" reason="Grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123201405.png)

> 📌 **Regola.** Confrontare **lunghezze su una base comune**; usare una **linea** quando l’asse orizzontale è il **tempo**.

##### **1.9.2. Distribuzione: come si distribuiscono i valori di una variabile**

| Situazione | Grafico | Comando |
|---|---|---|
| una variabile, **pochi dati** | **istogramma** (età) | `plt.hist(age, bins=10)` |
| una variabile, **molti dati** | **curva di densità** (istogramma a linea) | `plt.fill_between(xs, density)` |
| **due variabili** | **scatter** (età e spesa) | `plt.scatter(age, spend)` |

<!-- SOURCE_VISUAL id="IS11-V16" source="L11-web-v24.pdf" page="21" type="grafico" description="Istogramma bimodale delle età, curva di densità e scatter età-spesa" reason="Grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123201435.png)

> 📌 **Regola.** Prima di qualunque media, **guardare la forma**: le età dei 200 clienti sono **bimodali** (due gruppi, attorno a **28** e **48** anni) e una media da sola lo nasconderebbe, suggerendo un «cliente tipo» di 38 anni che quasi non esiste.

##### **1.9.3. Composizione: parti di un tutto**

| Situazione | Grafico | Comando |
|---|---|---|
| **statica**, semplice quota del totale | **torta** (clienti per fascia d’età: 18-29 41%, 30-39 21%, 40-49 22%, 50+ 15%) | `plt.pie(counts, labels=groups)` |
| **statica**, accumulo verso un totale | **cascata** (*waterfall*: ricavi +100, merci −45, personale −25, altro −10, profitto 20 k€) | `plt.bar(i, x, bottom=running)` |
| **variabile nel tempo**, molti periodi | **aree impilate** (vendite di negozio, web e app per mese) | `plt.stackplot(months, shop, web, app)` |

<!-- SOURCE_VISUAL id="IS11-V17" source="L11-web-v24.pdf" page="22" type="grafico" description="Torta delle fasce d'età, cascata dai ricavi al profitto, aree impilate per canale" reason="Grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123201502.png)

> 📌 **Regola.** Usare una **torta** solo per **poche parti che sommano al 100%**; una **cascata** per vedere **come si costruisce un totale**; le **aree impilate** per vedere **le parti e il totale nel tempo**. Verifica della cascata: $100-45-25-10=20$ k€ di profitto.

##### **1.9.4. Relazione: come due o tre variabili si muovono insieme**

| Situazione | Grafico | Comando |
|---|---|---|
| **due variabili** | **scatter** (età e punteggio di spesa) | `plt.scatter(age, spend)` |
| **tre variabili** | **bolle**: la dimensione (o il colore) codifica il reddito in k€ | `plt.scatter(age, spend, s=income)` |
| aggiunta: tendenza e suo $r$ | **scatter con retta interpolata**, $r=-0{,}60$ | `plt.plot(xs, k*xs+q)` |

<!-- SOURCE_VISUAL id="IS11-V18" source="L11-web-v24.pdf" page="23" type="grafico" description="Scatter età-spesa, grafico a bolle con il reddito, scatter con retta di tendenza r = -0.60" reason="Grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123201519.png)

> ⚠️ **Regola.** Età e punteggio di spesa sono **correlati** ($r=-0{,}60$ qui), ma **una correlazione non è una causa**. Il terzo grafico non è nella guida: è lo scatter con una **linea di tendenza**, la prima cosa che un progettista aggiunge.

#### **1.10. Quali sono i benefici della visualizzazione?**

- Permette di vedere i dati da **più prospettive diverse**.
- Rende possibile **interpretare grandi quantità** di dati.
- Offre la capacità di **individuare le eccezioni**.
- Permette di **analizzare pattern visivi**.
- Permette di **esplorare tendenze** navigando i dati e riconoscendo i pattern direttamente.

![](imgs/Pasted%20image%2020261123201540.png)

**Benefici (2).**

- Traduce i **pattern dei dati in intuizioni**: uno strumento decisionale molto efficace, che **riduce tempo e difficoltà** nel passare dal dato alla decisione.
- Permette di vedere le **influenze** (uscite contro ingressi) altrimenti difficili da trovare.
- Con tutti i dati disponibili è difficile trovare le **sfumature** che fanno la differenza.

L’esempio è un **diagramma di Sankey** dei flussi di materiale in tonnellate al mese (100 t in totale): la **larghezza di ogni banda è la quantità** (§7.4).

<!-- SOURCE_VISUAL id="IS11-V19" source="L11-web-v24.pdf" page="25" type="grafico" description="Diagramma di Sankey dei flussi di materiale: fornitori, produzione, clienti UE ed extra-UE, scarto, riciclo e smaltimento" reason="Bande del Sankey visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123201552.png)

#### **1.11. Conclusioni: un grafico dovrebbe…**

> 📌 **I principi di un buon grafico.**
> - **mostrare i dati**;
> - indurre l’osservatore a pensare alla **sostanza** piuttosto che alla metodologia, al design grafico o alla tecnologia di produzione;
> - **evitare di distorcere** ciò che i dati hanno da dire;
> - presentare **molti numeri in poco spazio**;
> - rendere **coerenti** grandi insiemi di dati, supportando **armonizzazione e confronto**;
> - incoraggiare l’occhio a **confrontare** dati diversi;
> - rivelare i dati a **più livelli di dettaglio**, dalla panoramica alla struttura fine;
> - avere uno **scopo ragionevolmente chiaro**: descrizione, esplorazione, tabulazione o decorazione;
> - essere **strettamente integrato** con le descrizioni **statistiche e verbali** del dataset.

![](imgs/Pasted%20image%2020261123201610.png)


<!-- SOURCE_VISUAL id="IS11-V20" source="L11-web-v24.pdf" page="26" type="figura" description="Esempio di grafico con troppo design grafico" reason="Esempio negativo visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V21" source="L11-web-v24.pdf" page="27" type="figura" description="Esempi di grafici che incoraggiano il confronto e mostrano più livelli di dettaglio" reason="Esempi visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123201625.png)

> 💡 **La fonte e il nemico (nota didattica).** L’elenco riprende quasi alla lettera i principi di **Edward Tufte**, *The Visual Display of Quantitative Information* (1983). Il nemico indicato dalla slide è il **troppo design grafico**: decorazioni, effetti 3D e ornamenti che consumano attenzione senza portare dati (il *chartjunk* di Tufte).

---

### **2. Drift e monitoraggio: osservare dati e modello dopo il rilascio**


#### **2.1. Dashboard di rilascio e monitoraggio del drift**

- **Monitorare** dati e prestazioni del modello **in produzione**.
- Rilevare il **data drift** e il **concept drift**.
- **Attivare allarmi** e intervenire **presto**.

La dashboard tipica affianca quattro pannelli: **prestazioni nel tempo** (AUC da gennaio a maggio con soglia 0,6), **data drift** (PSI con soglia 0,1), **dati di training contro dati attuali** (densità di una feature), **allarmi e azioni** («rilevato drift alto», «prestazioni sotto soglia», «riaddestramento pianificato», «ultimo aggiornamento: 2 minuti fa»).

<!-- SOURCE_VISUAL id="IS11-V22" source="L11-web-v24.pdf" page="29" type="schermata" description="Dashboard di rilascio: AUC nel tempo, PSI con soglia, densità training contro produzione, pannello allarmi" reason="Dashboard visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123201641.png)

> 💡 **Data drift e concept drift (nota didattica).** Con il **data drift** cambia la distribuzione degli **input** $P(X)$: per esempio arrivano clienti più anziani o materiali di un nuovo fornitore. Con il **concept drift** cambia la **relazione** tra input e output $P(Y\mid X)$: a parità di caratteristiche, il risultato diventa diverso (lo stesso livello di vibrazione inizia a precedere un guasto). Il primo si vede guardando solo gli input; il secondo richiede le etichette vere, o almeno un segnale sulle uscite (§2.4).

#### **2.2. Il Population Stability Index (PSI)**

> 📌 **PSI.** Un indice numerico che confronta la **distribuzione dei dati di training** con quella **attuale dei dati di produzione**:
> $$\text{PSI}=\sum_{i=1}^{B}(p_i-q_i)\ln\frac{p_i}{q_i}.$$

| Simbolo | Significato |
|---|---|
| $B$ | numero di **bin** (intervalli) della feature; $i$ è l’indice del bin |
| $p_i$ | quota dei campioni di **riferimento** (training) nel bin $i$ |
| $q_i$ | quota dei campioni **attuali** (produzione) nel bin $i$ |

![](imgs/Pasted%20image%2020261123202818.png)

Ciascun insieme di quote **somma a 1**. **Ogni termine è $\ge 0$** ed è $0$ quando $p_i=q_i$. Esempio: $p_i=0{,}08$ e $q_i=0{,}04$ danno $(0{,}04)\ln 2=0{,}0277$ (una feature, un mese di produzione).

> 📌 **A che cosa serve.** Il PSI aiuta a rilevare il **drift degli input**, che può portare a **bias**, **degrado dell’accuratezza** o alla necessità di **ricalibrare e riaddestrare**: verifica se il modello rilasciato **riceve ancora dati familiari**.

> 💡 **Perché ogni termine è non negativo (nota didattica).** Se $p_i>q_i$, allora $p_i-q_i>0$ e $p_i/q_i>1$, quindi $\ln(p_i/q_i)>0$: prodotto positivo. Se $p_i<q_i$, entrambi i fattori sono negativi: prodotto ancora positivo. Il PSI è quindi una somma di contributi che non si compensano mai: ogni bin che si sposta, in qualunque direzione, aumenta l’indice. Matematicamente è la **divergenza di Kullback-Leibler simmetrizzata**, $D_{KL}(p\Vert q)+D_{KL}(q\Vert p)$ (Lezione 4). Se un bin è vuoto il logaritmo non è definito: si sostituisce lo zero con una piccola costante.

#### **2.3. Il PSI applicato a una feature di input**

Una feature standardizzata (z-score) è divisa in 5 bin; si confrontano le quote del riferimento (training) e dell’attuale (produzione):

| Bin (z-score) | $p_i$ | $q_i$ | $p_i-q_i$ | $\ln(p_i/q_i)$ | $(p_i-q_i)\ln(p_i/q_i)$ |
|---|---:|---:|---:|---:|---:|
| $[-3,-1{,}5)$ | 0,08 | 0,04 | 0,04 | 0,6931 | 0,0277 |
| $[-1{,}5,-0{,}5)$ | 0,22 | 0,14 | 0,08 | 0,4520 | 0,0362 |
| $[-0{,}5,0{,}5)$ | 0,40 | 0,28 | 0,12 | 0,3567 | 0,0428 |
| $[0{,}5,1{,}5)$ | 0,20 | 0,32 | −0,12 | −0,4700 | 0,0564 |
| $[1{,}5,3]$ | 0,10 | 0,22 | −0,12 | −0,7885 | 0,0946 |
| **totale** | **1,00** | **1,00** | | | **PSI = 0,2577** |

<!-- SOURCE_VISUAL id="IS11-V23" source="L11-web-v24.pdf" page="31" type="grafico" description="Istogrammi a 5 bin della feature nel riferimento (training) e nella produzione" reason="Istogrammi a confronto visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123202831.png)

**Regola pratica**:

| PSI | Interpretazione |
|---|---|
| $<0{,}1$ | **nessun drift significativo** |
| $0{,}1\le\text{PSI}<0{,}25$ | **drift moderato** |
| $\ge 0{,}25$ | **drift significativo** |

Qui $\text{PSI}=0{,}2577$: i dati di produzione **si sono spostati verso z-score più alti** (la massa passa dai bin di sinistra a quelli di destra), e l’indice supera di poco la soglia del drift significativo.

> 💡 **Leggere i contributi (nota didattica).** I due bin di destra contribuiscono da soli per $0{,}0564+0{,}0946=0{,}151$, oltre metà del totale: il drift non è un rumore diffuso ma uno **spostamento sistematico** verso valori alti. È un’informazione che il solo numero 0,2577 non dà; per questo la dashboard affianca all’indice gli istogrammi.

#### **2.4. Rilevare il drift dopo il rilascio: la prospettiva dell’AI Act**

- Il **monitoraggio post-rilascio** è richiesto per i **sistemi di AI ad alto rischio**.
- Si fa tipicamente analizzando:
  - l’**input** del modello, per esempio con il **Population Stability Index** (PSI nel tempo);
  - l’**output** del modello, per esempio con il **drift della distribuzione delle predizioni** (densità dei punteggi).

La slide sintetizza: l’AI Act europeo impone un monitoraggio continuo e rigoroso delle prestazioni per i sistemi «**ad alto rischio**» e per l’**AI per finalità generali** (GPAI, *General-Purpose AI*).

<!-- SOURCE_VISUAL id="IS11-V24" source="L11-web-v24.pdf" page="32" type="grafico" description="Miniature del PSI nel tempo (input) e della densità dei punteggi (output)" reason="Grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123202913.png)

> ⚠️ **Alto rischio e GPAI non hanno gli stessi obblighi.** Nel regolamento il **sistema di monitoraggio post-commercializzazione** è un obbligo specifico dei fornitori di sistemi **ad alto rischio** (art. 72); per i modelli GPAI gli obblighi riguardano soprattutto documentazione tecnica e, per quelli **a rischio sistemico**, valutazioni, gestione dei rischi e segnalazione degli incidenti gravi. Le date di applicazione sono state modificate dal Regolamento (UE) 2026/1744: gli obblighi per i sistemi ad alto rischio dell’Allegato III decorrono dal **2 dicembre 2027** e quelli per i prodotti regolamentati dell’Allegato I dal **2 agosto 2028**. [Testo del Regolamento 2026/1744](https://eur-lex.europa.eu/legal-content/EN/TXT/?uri=CELEX%3A32026R1744).

#### **2.5. Il drift della distribuzione delle predizioni**

Il modello rilasciato riceve, per ogni richiesta, un input $X$ e produce un punteggio $s=P(Y=1\mid X)$. Si possono monitorare tre cose:

| Segnale | Dove si misura | Quando è disponibile |
|---|---|---|
| **feature drift** | PSI su **ogni feature di input** | subito |
| **etichette vere** | confronto con la *ground truth* | possono arrivare **molto più tardi** |
| **prediction drift** | PSI sul **punteggio di uscita** | subito |

![](imgs/Pasted%20image%2020261123202930.png)

> 📌 **Il prediction drift non è il feature drift.**
> - Il **feature drift** riguarda la distribuzione dei **dati di input**; il **prediction drift** la distribuzione delle **uscite** del modello.
> - Il prediction drift è un **segnale compatto**, dopo il rilascio, che il **comportamento del modello** potrebbe cambiare, **anche prima** che siano disponibili tutte le etichette vere.

> 💡 **Perché guardare le uscite (nota didattica).** In un sistema di concessione del credito le etichette vere (il cliente ha restituito il prestito?) arrivano dopo mesi. Se nel frattempo la quota di punteggi alti passa dal 20% al 45%, qualcosa è cambiato: negli input, nel modello o nel mondo. È un allarme precoce che non richiede di aspettare la *ground truth*. Inoltre un solo indice sull’uscita riassume l’effetto di decine di feature.

#### **2.6. Lo stesso indice sull’uscita del modello: il drift del punteggio**

La slide mostra la distribuzione dei punteggi $s=P(Y=1\mid X)$ nel **periodo di riferimento** (a sinistra) e nel **periodo attuale** (a destra), **50 000 previsioni** ciascuno.

<!-- SOURCE_VISUAL id="IS11-V25" source="L11-web-v24.pdf" page="34" type="grafico" description="Distribuzione dei punteggi del classificatore nel periodo di riferimento e in quello attuale, PSI = 0.96" reason="Istogrammi dei punteggi visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123202942.png)

> 📌 **Perché conta per il progettista.** I punteggi di un classificatore si possono **monitorare senza aspettare le etichette vere**. La formula è la stessa di prima, ora sull’uscita: qui $\text{PSI}=0{,}96>0{,}25$, un **drift significativo**. Gli input possono sembrare ancora familiari: **guardare entrambi**.

#### **2.7. Controllare drift e prestazioni nel tempo**

- **Input**: 52 settimane, 400 misure a settimana di una variabile di input; **riferimento**: le prime 12 settimane.
- **Output**: PSI settimanale e accuratezza (%): **il PSI sale, poi l’accuratezza cala**.

<!-- SOURCE_VISUAL id="IS11-V26" source="L11-web-v24.pdf" page="35" type="grafico" description="PSI settimanale con soglie 0.1 e 0.25 e accuratezza su 52 settimane: il PSI sale prima che l'accuratezza cali" reason="Andamento delle due curve visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123203004.png)

- **Perché in IS4**: un modello in un impianto o in un magazzino **cambia comportamento** quando cambiano **materiali, fornitori o stagioni**. **Allarmi**: PSI **0,1 = attenzione**, **0,25 = allarme**, oppure uno **spostamento della media oltre $2\sigma$** della finestra di riferimento.
- **Errore tipico**: una **finestra di riferimento scadente**. Entrambe le regole di allarme sono **regole pratiche, non standard**.

> 💡 **Il valore dell’anticipo (nota didattica).** Il PSI è calcolato sugli input, disponibili subito; l’accuratezza richiede le etichette. Il fatto che il PSI salga **prima** del calo di accuratezza è esattamente ciò che rende utile il monitoraggio: dà il tempo di intervenire (raccogliere etichette, riaddestrare, ricalibrare) prima che le decisioni sbagliate si accumulino.

---

### **3. Visualizzazione esperta: esempi scientifici straordinari e il loro costo**


#### **3.1. Distribuzioni nel tempo: la storia scritta nel grafico**

Un grafico delle medaglie olimpiche nel tempo mostra come la visualizzazione possa contenere la storia:

- **guerre**: i vuoti del **1916**, **1940** e **1944** sono i Giochi annullati per le guerre mondiali;
- **boicottaggi**: **Mosca 1980** e **Los Angeles 1984** cambiano chi sale sul podio;
- **politica**: la **Squadra Unificata del 1992** e le **squadre tedesche** prima e dopo il 1990 appaiono come **bande che si dividono o si fondono**.

Assenza di dati, vincitori, eventi: **i vuoti fanno parte della storia** (anni senza Giochi). La visualizzazione porta con sé la storia: un’ispirazione per le nostre tecniche.

<!-- SOURCE_VISUAL id="IS11-V27" source="L11-web-v24.pdf" page="37" type="grafico" description="Streamgraph delle medaglie olimpiche nel tempo con i vuoti delle guerre, i boicottaggi e le squadre che si dividono o fondono" reason="Grafico visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123203034.png)

#### **3.2. Fenomeni complessi: dinamiche rese visibili**

Le **equazioni di Navier–Stokes** governano il moto di un fluido. Una buona visualizzazione mostra i **vortici** e la **transizione dal moto laminare a quello turbolento**, che le equazioni da sole non lasciano vedere. Il colore porta la **quarta grandezza**, il modulo della velocità [m/s]. La stessa abilità aiuta a leggere le **superfici di decisione complesse** delle reti neurali e dei modelli di machine learning.

<!-- SOURCE_VISUAL id="IS11-V28" source="L11-web-v24.pdf" page="38" type="grafico" description="Simulazione di un fluido con vortici e transizione laminare-turbolenta, colore = modulo della velocità" reason="Visualizzazione scientifica visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123203051.png)

#### **3.3. Vaccino sì o no? Il morbillo**

Secondo l’**Organizzazione Mondiale della Sanità**, pur essendo disponibile un vaccino **sicuro ed economico**, nel **2018** ci sono stati oltre **140 000 morti** per morbillo nel mondo, per lo più **bambini sotto i cinque anni**. La vaccinazione contro il morbillo ha prodotto un **calo del 73%** delle morti tra il **2000** e il **2018** a livello mondiale. **Maurice Hilleman** (1919–2005), che sviluppò il vaccino contro il morbillo, è associato a circa **1 milione di morti evitate all’anno**.

<!-- SOURCE_VISUAL id="IS11-V29" source="L11-web-v24.pdf" page="39" type="infografica" description="Infografica sul morbillo con i dati OMS 2000-2018 e ritratto di Maurice Hilleman" reason="Infografica visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123203110.png)

#### **3.4. Superfici 3D, dati 3D nel tempo, fette e proiezioni**

- **Superfici 3D**: le **decisioni** di un sistema intelligente si possono rappresentare come **superfici** negli spazi di input interni; un’**isosuperficie** è il luogo dei punti in cui una funzione vale una costante (per esempio la frontiera di decisione di un classificatore con tre feature, dove la probabilità vale 0,5).
- **Dati 3D nel tempo**: il **monte Sant’Elena**, nello stato di Washington, **prima e dopo l’eruzione del 1980**.
- **Fette e proiezioni**: è **molto difficile** disegnare una feature su **tre assi**, perché i **voxel si coprono a vicenda**; è spesso meglio preferire **proiezioni e fette**.

![](imgs/Pasted%20image%2020261123203202.png)


<!-- SOURCE_VISUAL id="IS11-V30" source="L11-web-v24.pdf" page="40" type="grafico" description="Superficie di decisione e isosuperficie in uno spazio di input 3D" reason="Superficie visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V31" source="L11-web-v24.pdf" page="41" type="grafico" description="Monte Sant'Elena prima e dopo l'eruzione del 1980" reason="Superfici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V32" source="L11-web-v24.pdf" page="42" type="grafico" description="Fette e proiezioni di un volume 3D per evitare l'occlusione tra voxel" reason="Fette visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123203217.png)
![](imgs/Pasted%20image%2020261123203225.png)

#### **3.5. Risonanza magnetica e scelta del formato**

La slide mostra una **risonanza magnetica** (MRI) rappresentata in 3D con superfici e fette; il codice è nell’appendice (§11.9). **A seconda dell’obiettivo della visualizzazione, la scelta del formato appropriato può cambiare moltissimo**: lo stesso volume si può mostrare come fette di contorno, come isosuperficie o come proiezione.

![](imgs/Pasted%20image%2020261123203512.png)


<!-- SOURCE_VISUAL id="IS11-V33" source="L11-web-v24.pdf" page="43" type="figura" description="Risonanza magnetica rappresentata in 3D" reason="Immagine della MRI visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V34" source="L11-web-v24.pdf" page="44" type="figura" description="Due formati diversi per lo stesso volume 3D a seconda dell'obiettivo" reason="Confronto dei formati visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123203520.png)

#### **3.6. Galleria e che cosa c’è dietro un bel grafico**

La galleria (dettagli in appendice) comprende: **observablehq.com** ed esempi interattivi, **ggplot2 in R**, l’**anatomia di un grafico**, il **grafico nel grafico** e i **vettori 3D**, la **heatmap del morbillo**.

<!-- SOURCE_VISUAL id="IS11-V35" source="L11-web-v24.pdf" page="45" type="figura" description="Galleria di sei miniature: observablehq, esempi interattivi, ggplot2, anatomia di un grafico, grafico nel grafico, heatmap del morbillo" reason="Miniature visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123203531.png)

**Che cosa c’è dietro un bel grafico?** Una catena di cinque passi: **idea** (il messaggio) → **dati** (pulire, unire) → **codifica** (segni, colore) → **codice** (libreria, API) → **iterazione** (molte versioni).

- Gli esempi visti hanno richiesto **giorni di progettazione e di codice**: vortici, MRI e streamgraph non sono grafici da una riga.
- Il limite **non è la libreria**, sono **tempo e competenza**: conoscere i dati, le variabili visive e lo strumento.
- Una visualizzazione è **letta una volta dal pubblico** ma **scritta molte volte dal progettista**.
- Nel **2026** un **agente di AI** può scriverne una prima versione **in pochi secondi**: è l’ultima parte della lezione (§8).

#### **3.7. Esempi semplici con dati 3D in MATLAB**

```matlab
load penny
P = flipud(P);

subplot(2,2,1)
surf(P)

subplot(2,2,2)
mesh(P)

subplot(2,2,3)
pcolor(P)
shading flat

subplot(2,2,4)
contour(P)
```

- **Input**: la matrice `P` delle **altezze**, un valore per pixel ($x$, $y$, altezza: dati 3D). È una moneta da un centesimo (*penny*) scansionata in 3D.
- **Output**: **quattro viste della stessa matrice**: superficie (`surf`), reticolo (`mesh`), mappa di colore vista dall’alto (`pcolor`), curve di livello (`contour`).

<!-- SOURCE_VISUAL id="IS11-V36" source="L11-web-v24.pdf" page="47" type="grafico" description="Quattro viste della moneta: surf, mesh, pcolor, contour" reason="Grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123203609.png)

> 💡 **Perché `flipud` (nota didattica).** Nelle matrici l’indice di riga cresce **verso il basso**, mentre l’asse $y$ di un grafico cresce **verso l’alto**: senza ribaltare la matrice (`flipud`, *flip up-down*) la moneta apparirebbe capovolta. `shading flat` toglie le linee nere tra le celle di `pcolor`.

---

### **4. Visualizzare un sistema di AI: dati, rete, prestazioni, funzionamento e decisioni**


#### **4.1. Che cosa si visualizza in un sistema di AI nel 2026?**

| Dimensione | Domanda | Viste tipiche |
|---|---|---|
| **dati** | è **bilanciato, pulito, ben etichettato**? | griglie di immagini, proiezioni, mappe del dataset |
| **modello** | che cosa **ha imparato**, che cosa **guarda**? | pesi, attivazioni, salienza, attenzione, paesaggio della perdita |
| **prestazioni** | **dove sbaglia**, **quanto è sicuro**? | curve di addestramento, matrice di confusione, calibrazione, slice di errore |

Lungo il processo di progettazione la visualizzazione si usa in questa **sequenza**: lavorare con i **dati**, poi con il **modello**, poi con le **prestazioni**. Le stesse tre domande ritornano **prima, durante e dopo** l’addestramento. Riferimenti: le rassegne sulla visual analytics di Yuan et al. (*Computational Visual Media*, 2021) e Hohman et al. (*IEEE TVCG*, 2019).

#### **4.2. Un progettista di sistemi ha bisogno di molte viste, una per domanda**

| Blocco | Contenuto | Esempio della slide |
|---|---|---|
| **3A** il sistema e i suoi dati | architettura, pipeline, segnali dei sensori, correlazione, PCA, mappe | architettura macchina–edge–cloud–operatore |
| **3B** dentro una rete | mappa del dataset, pesi, attivazioni, attenzione, perdita, matrice di confusione (esperimento sulle cifre) | mappa del dataset (confidenza contro variabilità) |
| **3C** prestazioni e fiducia | bande di previsione, residui, costo degli errori, ROC e PR, importanza delle variabili | matrice dei costi |
| **3D** funzionamento e decisioni | carta di controllo, vita utile residua, drift, Pareto, flussi, pianificazioni | carta di controllo |

<!-- SOURCE_VISUAL id="IS11-V37" source="L11-web-v24.pdf" page="50" type="grafico" description="Quattro viste esemplari: architettura del sistema, mappa del dataset, matrice dei costi, carta di controllo" reason="Quattro pannelli visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123203654.png)

I quattro blocchi seguono nelle pagine successive, in ordine: dati, modello, prestazioni, funzionamento. **Caso guida**: la **manutenzione predittiva** di una macchina, con dati sintetici.

#### **4.3. Un giro del sistema, componente per componente**

Dalla macchina al cloud e all’operatore: **ogni parte dell’architettura ha bisogno della propria visualizzazione**.

| Componente | Ruolo | Che cosa si visualizza |
|---|---|---|
| **macchina** | sensori, ML locale | i **segnali** e l’**anomalia** (vibrazione e temperatura nel tempo) |
| **edge** | inferenza ML, allarmi | gli **allarmi rispetto ai limiti** (carta di controllo) |
| **cloud** | addestramento ML, MLOps | **training, validation, drift** (curve di perdita, miglior epoca 45) |
| **operatore** | decisione con supporto del ML | **perché il modello dà l’allarme** (contributi delle variabili) |

<!-- SOURCE_VISUAL id="IS11-V38" source="L11-web-v24.pdf" page="52" type="schema e grafici" description="Architettura macchina, edge, cloud, operatore con un grafico dedicato sotto ciascun componente" reason="Architettura e grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123203712.png)

> 📌 **Ogni componente, la sua vista.** Non solo le funzioni (addestramento, modello, validazione): **ogni componente del sistema, perfino l’operatore**, ha bisogno di vedere cose diverse. Il machine learning sta in ogni parte, quindi ogni parte riceve la propria vista di ciò che sta accadendo.

#### **4.4. Segnali multivariati dei sensori: disegnare i segnali**

- **Input**: vibrazione (g) e temperatura (°C) di una macchina, un valore al minuto, per **240 minuti**.
- **Output**: i due segnali **sullo stesso asse dei tempi**, con la **finestra dell’anomalia** evidenziata.

<!-- SOURCE_VISUAL id="IS11-V39" source="L11-web-v24.pdf" page="53" type="grafico" description="Vibrazione e temperatura su 240 minuti con doppio asse e intervallo anomalo 160-190 min evidenziato" reason="Segnali visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123203731.png)

- **Primo controllo qualitativo**: dal minuto **160 al 190** entrambi i segnali **salgono insieme**. Si muovono insieme anche in generale? È il primo modo per vedere come si comportano i segnali; il numero arriva nella pagina successiva.
- **Perché in IS4**: i segnali dei sensori sono la **materia prima** della manutenzione predittiva e dell’IoT.
- **Errore tipico**: **scale diverse su un solo asse** nascondono un segnale; finestre di sensori diversi **non sincronizzate** (Lezione 6).

#### **4.5. Segnali multivariati: la matrice di correlazione dell’EDA**

La **correlazione di Pearson** tra due sensori $x$ e $y$ è

$$r_{xy}=\frac{\sum_{i=1}^{n}(x_i-\bar{x})(y_i-\bar{y})}{\sqrt{\sum_i(x_i-\bar{x})^2}\ \sqrt{\sum_i(y_i-\bar{y})^2}},$$

dove $n$ è il numero di osservazioni, $x_i,y_i$ i valori dei due sensori all’osservazione $i$ e $\bar{x},\bar{y}$ le loro medie.

**Esempio.** $x=(1,2,3,4)$, $y=(2,3,5,6)$: $\bar{x}=2{,}5$, $\bar{y}=4$; i prodotti degli scarti sono $3+0{,}5+0{,}5+3=7$; $\sum(x_i-\bar{x})^2=5$, $\sum(y_i-\bar{y})^2=10$; quindi $r=7/\sqrt{50}=0{,}99$.

- **Input**: 5 variabili, 500 osservazioni. **Output**: matrice $5\times 5$ di $r\in[-1,1]$.

| | V1 (vibrazione) | V2 (RMS) | V3 | V4 | V5 (efficienza) |
|---|---:|---:|---:|---:|---:|
| **V1** | 1,00 | **0,94** | 0,02 | −0,02 | **−0,85** |
| **V2** | 0,94 | 1,00 | 0,36 | −0,03 | −0,81 |
| **V3** | 0,02 | 0,36 | 1,00 | −0,05 | −0,04 |
| **V4** | −0,02 | −0,03 | −0,05 | 1,00 | 0,55 |
| **V5** | −0,85 | −0,81 | −0,04 | 0,55 | 1,00 |

![](imgs/Pasted%20image%2020261123203947.png)

I nomi delle variabili V3 e V4 compaiono solo nella figura.

<!-- SOURCE_VISUAL id="IS11-V40" source="L11-web-v24.pdf" page="54" type="grafico" description="Heatmap 5x5 della correlazione di Pearson tra le variabili del sensore, con i nomi delle variabili" reason="Nomi delle variabili e colori visibili solo nella heatmap" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Lettura.** Qui vibrazione e RMS hanno $r=0{,}94$, vibrazione ed efficienza $r=-0{,}85$. Una forte correlazione tra sensori può essere un **indizio diagnostico** oppure un motivo per **rimuovere una feature**: un punto da discutere (Lezione 10, livello B). **La correlazione non è causalità.**

> 💡 **Simmetria e diagonale (nota didattica).** La matrice è simmetrica ($r_{xy}=r_{yx}$) e la diagonale vale 1 (ogni variabile è perfettamente correlata con se stessa): l’informazione utile sta nel solo triangolo superiore, $5\cdot 4/2=10$ coppie.

#### **4.6. Spazio delle feature con la PCA: ci sono cluster o stati?**

- **Input**: 360 campioni, 8 feature, 3 stati: **normale**, **usura**, **guasto**.
- **Output**: 2 componenti principali, che spiegano il **68%** e il **15%** della varianza.

<!-- SOURCE_VISUAL id="IS11-V41" source="L11-web-v24.pdf" page="55" type="grafico" description="Proiezione PCA dei 360 campioni sui primi due componenti (68% e 15%) con i tre stati normale, usura, guasto" reason="Cluster visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123205105.png)

- **Perché in IS4**: l’obiettivo è scoprire se i dati formano **cluster** (qui i tre stati della macchina). I **gruppi nascosti** possono invertire una tendenza, come nel **paradosso di Simpson** della lezione precedente, quindi si cercano **prima di fidarsi di qualunque media**; spiegano anche il comportamento della macchina.
- **Errore tipico**: leggere le **distanze in 2D come distanze vere**: si veda l’avvertenza su t-SNE (§5.2).

> 💡 **Quanto si perde (nota didattica).** Le due componenti spiegano insieme $68\%+15\%=83\%$ della varianza: il 17% della variabilità delle 8 feature non è visibile nel grafico. Due stati che sembrano sovrapposti in 2D potrebbero separarsi lungo la terza componente.

---

### **5. Visualizzare una rete neurale: un piccolo esperimento**


Cifre scritte a mano, una rete minuscola, **una vista per ogni domanda**.

#### **5.1. Un solo esperimento per tutte le viste**

**Input**:

- **1797 immagini** $8\times 8$ pixel = **64 feature** in $[0,1]$, **10 classi** (cifre da 0 a 9), con circa 180 immagini per classe (da 174 per l’8 a 183 per il 3);
- **suddivisione**: 1000 di training, 400 di validation, 397 di test;
- **50 etichette di training (5%) invertite di proposito**;
- **rete 64-16-10**, ReLU, **1210 parametri**, 80 epoche.

**Output**:

- **accuratezza di test 95,2%**: **19 errori su 397** immagini;
- **un’immagine per ogni domanda** delle pagine successive.

**Situazione iniziale**: rete non addestrata, accuratezza vicina al caso (circa 10%). **Finale**: rete addestrata, 95,2%.

<!-- SOURCE_VISUAL id="IS11-V42" source="L11-web-v24.pdf" page="57" type="figura e grafico" description="Esempi delle dieci cifre 8x8 e istogramma del numero di immagini per classe" reason="Immagini e istogramma visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123205119.png)

> 💡 **Verifica dei parametri (nota didattica).** Una rete completamente connessa 64-16-10 ha $64\cdot 16+16=1040$ parametri nel primo strato (pesi più bias) e $16\cdot 10+10=170$ nel secondo: $1040+170=1210$, come dichiarato. Le etichette invertite di proposito servono a verificare se le visualizzazioni successive riescono a **trovarle** (§5.10).

#### **5.2. Dati: le proiezioni sono immagini, non misure**

Le stesse **1797 immagini in 64 dimensioni** sono disegnate in 2D con tre metodi: **PCA** (lineare), **t-SNE con perplexity 5** e **t-SNE con perplexity 50**.

- La PCA è **lineare**; t-SNE **conserva i vicinati**.
- **Dimensione e distanza dei cluster cambiano con la perplexity** (5 contro 50): leggere i **vicinati**, non le **distanze**.

<!-- SOURCE_VISUAL id="IS11-V43" source="L11-web-v24.pdf" page="58" type="grafico" description="Le cifre proiettate con PCA, t-SNE perplexity 5 e t-SNE perplexity 50, colorate per classe" reason="Proiezioni visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123205136.png)

> 📌 **La perplexity.** È il parametro di t-SNE che stabilisce **quanti vicini** ogni punto considera, all’incirca:
> $$\text{perplexity}=2^{H(P_i)},$$
> dove $P_i$ è la probabilità che il punto $i$ scelga ciascun altro punto come vicino e $H$ è la sua entropia in bit. **Esempio**: se $P_i$ è uniforme su 30 punti, $H=\log_2 30$ e la perplexity è $2^{\log_2 30}=30$. Un valore **basso** (5) guarda molto **localmente** e divide i dati in **molti piccoli gruppi**; uno **alto** (50) guarda più **globalmente** e li **fonde**. Gli stessi dati possono quindi mostrare cluster diversi: **provare più valori prima di fidarsi di un cluster**.

> 💡 **Perché $2^H$ conta i vicini (nota didattica).** Una distribuzione uniforme su $k$ elementi ha entropia $\log_2 k$ bit, quindi $2^H=k$: la perplexity è il «numero effettivo» di vicini tra cui il punto distribuisce la probabilità. Con una distribuzione non uniforme, $2^H$ dice a quanti vicini equiprobabili corrisponde la sua incertezza (l’entropia è stata introdotta nella Lezione 4 e ripresa nella Lezione 12).

#### **5.3. E le immagini? Visualizzare CIFAR-10**

- **60 000 immagini a colori** $32\times 32$ pixel, **10 classi**, **6000 immagini per classe**.
- **50 000 di training** e **10 000 di test**.
- Un’immagine: $\mathbb{R}^{\text{altezza}[32]\times\text{larghezza}[32]\times\text{canali}[3]}$; la sua etichetta: una di 10 classi.
- **Guardare dieci immagini casuali per classe** controlla già il **bilanciamento** e le **definizioni delle classi**: «*automobile*» indica berline e SUV, «*truck*» solo i camion grandi.

<!-- SOURCE_VISUAL id="IS11-V44" source="L11-web-v24.pdf" page="59" type="figura" description="Griglia di dieci immagini casuali per ciascuna delle dieci classi di CIFAR-10" reason="Immagini visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123205613.png)

> 💡 **Perché le definizioni contano (nota didattica).** Un pickup è un’«automobile» o un «camion»? Se il dataset lo decide in un modo e l’applicazione nell’altro, il modello sembrerà sbagliare. Guardare gli esempi è il modo più rapido per scoprire queste convenzioni, che nessuna statistica riassuntiva rivela (si ricordi il controllo delle etichette della Lezione 8).

#### **5.4. Facets: uno strumento open source per i dati di addestramento**

**Leggere l’immagine di sinistra (Facets Dive)**: ogni miniatura è **un’immagine di CIFAR-10** e ogni riga è una **classe**, per esempio *airplane*. Dentro una riga le immagini sono **raggruppate per un valore**, qui il **colore**: si vede subito come i colori si distribuiscono in una classe (una riga di aerei è soprattutto cielo, blu e bianchi). In alto a destra lo stesso strumento su **dati di censimento**; in basso a destra le **statistiche per feature**, training contro test.

<!-- SOURCE_VISUAL id="IS11-V45" source="L11-web-v24.pdf" page="60" type="schermata" description="Facets Dive su CIFAR-10 raggruppato per classe e colore, Facets su dati di censimento, statistiche per feature training contro test" reason="Interfaccia visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123205631.png)

> ⚠️ **Un rischio che Facets rende visibile.** Se gli aerei sono quasi sempre su sfondo di cielo, un modello può imparare «blu = aereo» invece della forma dell’aereo: una scorciatoia che funziona sul test (anch’esso pieno di cielo) e fallisce su un aereo in hangar. È il problema del modello che «guarda i pixel sbagliati» (§5.7).

#### **5.5. Modello: i pesi come immagini e come istogrammi**

Nella rete 64-16-10 completamente connessa, i **64 pesi di un’unità nascosta** (le linee spesse dello schema) diventano **una tessera $8\times 8$**.

- **Tessera**: i 64 pesi di un’unità nascosta come immagine $8\times 8$; il **rosso eccita**, il **blu inibisce**.
- **Istogrammi**: **1024 pesi** nello strato 1 (deviazione standard **0,41**), **160** nello strato 2 (deviazione standard **0,71**). Controllare **scala**, **simmetria**, **unità morte**.

<!-- SOURCE_VISUAL id="IS11-V46" source="L11-web-v24.pdf" page="61" type="grafico" description="Schema 64-16-10, i 16 pesi del primo strato come tessere 8x8 e gli istogrammi dei pesi dei due strati" reason="Tessere e istogrammi visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123205716.png)

> 📌 **Perché conta per il progettista.** Si controlla la **salute di ogni strato**: tessere **vuote o sature** e istogrammi **estremi** rivelano **unità morte** o una **cattiva inizializzazione**, prima che si vedano nell’accuratezza. È la diagnostica dei pesi della Lezione 10 resa visibile.

> 💡 **Perché la tessera ha senso (nota didattica).** L’input è un’immagine $8\times 8$ srotolata in 64 valori; il peso $w_{j}$ dell’unità moltiplica il pixel $j$. Rimettendo i 64 pesi nella griglia $8\times 8$ si vede **quale schema di pixel** fa attivare l’unità: la tessera è il «template» che l’unità cerca, esattamente come il kernel della NCC della Lezione 9.

#### **5.6. Visualizzare i kernel delle CNN: quali pattern rilevano?**

I **quadrati grigi** sono i **kernel**; i **quadrati colorati** sono le **porzioni di immagine** di un dataset che producono la **risposta più alta** di quel kernel.

<!-- SOURCE_VISUAL id="IS11-V47" source="L11-web-v24.pdf" page="62" type="figura" description="Kernel di una CNN (quadrati grigi) e porzioni di immagine che li attivano al massimo (quadrati colorati)" reason="Kernel e porzioni visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123205725.png)

#### **5.7. Modello: attivazioni per classe**

- **Righe**: le 16 unità nascoste. **Colonne**: le 10 classi. **Colore**: l’**attivazione media** sulle 397 immagini di test.
- Un’unità **scura per una sola classe** è un **rilevatore di quella classe**.
- **3 unità non si attivano mai** sul test set: **unità morte**.

Per **un’immagine di un 3**, le attivazioni delle 16 unità sono:

$$5{,}6\quad 0{,}0\quad 0{,}3\quad 0{,}0\quad 0{,}7\quad 0{,}6\quad 2{,}2\quad 1{,}0\quad 1{,}5\quad 0{,}0\quad 2{,}3\quad 0{,}0\quad 3{,}1\quad 1{,}5\quad 1{,}1\quad 0{,}8$$

**12 unità su 16 sono attive**; le quattro a zero comprendono le unità mai attive sul test set (cerchiate in rosso tratteggiato nella figura).

<!-- SOURCE_VISUAL id="IS11-V48" source="L11-web-v24.pdf" page="63" type="grafico" description="Heatmap 16 unità x 10 classi delle attivazioni medie e attivazioni delle 16 unità per un'immagine di un 3" reason="Heatmap e cerchi delle unità visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123205934.png)

> 📌 **Perché conta per il progettista.** Si verifica che le unità **si specializzino su classi diverse** e che **nessuna sia morta**: le unità morte **sprecano capacità** e suggeriscono una **rete più piccola**. Con 3 unità morte su 16, la rete usa di fatto 13 unità.

#### **5.8. Mappe di salienza**

> 📌 **Mappe di salienza.**
> - **evidenziano le regioni dell’input** che influenzano la previsione;
> - aiutano a scoprire **correlazioni spurie** o **bias** indesiderati;
> - supportano l’**analisi degli errori** e l’**interpretabilità** del modello;
> - sono uno **strumento diagnostico**, **non una spiegazione definitiva**.

<!-- SOURCE_VISUAL id="IS11-V49" source="L11-web-v24.pdf" page="64" type="figura" description="Immagine e relativa mappa di salienza che evidenzia le regioni influenti" reason="Mappa visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123205953.png)

**Esempio.** Ogni mappa è costruita dal **gradiente dell’uscita rispetto all’input**: mostra quali pixel sono stati rilevanti per la classe. **SmoothGrad** aggiunge **rumore gaussiano** pixel per pixel a **molte copie** dell’immagine e **fa la media dei gradienti** (dettagli nelle lezioni successive). Fonte: pair-code.github.io/saliency.

<!-- SOURCE_VISUAL id="IS11-V50" source="L11-web-v24.pdf" page="65" type="figura" description="Mappe di salienza con gradiente semplice e SmoothGrad (PAIR)" reason="Mappe visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123210005.png)

**Come si calcola.** Il **gradiente del punteggio della classe rispetto ai pixel di input** misura **quanto cambia il punteggio** quando ogni pixel viene **leggermente modificato**.

<!-- SOURCE_VISUAL id="IS11-V51" source="L11-web-v24.pdf" page="66" type="schema" description="Calcolo della mappa di salienza come gradiente del punteggio di classe rispetto ai pixel" reason="Schema visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123210015.png)

> 📌 **Gradiente e SmoothGrad (formule standard, nota didattica).** Per un’immagine $x$ e il punteggio $S_c(x)$ della classe $c$, la mappa di salienza è
> $$M_c(x)=\left|\frac{\partial S_c(x)}{\partial x}\right|,$$
> un valore per pixel (per immagini a colori si prende di solito il massimo sui canali). SmoothGrad riduce il rumore della mappa facendo la media su $n$ copie perturbate:
> $$\hat{M}_c(x)=\frac{1}{n}\sum_{k=1}^{n}M_c(x+\varepsilon_k),\qquad \varepsilon_k\sim\mathcal{N}(0,\sigma^2).$$
> Il gradiente di una rete ReLU cambia bruscamente da un pixel all’altro; la media su copie rumorose leviga le fluttuazioni locali e lascia emergere le regioni stabilmente importanti.

##### **5.8.1. Come usare le mappe di salienza**

Due reti classificano la stessa immagine. **Qual è la rete migliore?**

| Rete | Previsione | Mappa di salienza |
|---|---|---|
| **NN1** | cane = **0,95** | evidenzia pixel **sbagliati** (non il cane) |
| **NN2** | cane = **0,92** | evidenzia **il cane** |

![](imgs/Pasted%20image%2020261123210334.png)


<!-- SOURCE_VISUAL id="IS11-V52" source="L11-web-v24.pdf" page="68" type="figura" description="Due reti NN1 (cane 0.95) e NN2 (cane 0.92) con le rispettive mappe di salienza: NN1 guarda i pixel sbagliati" reason="Immagini e mappe visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123210347.png)

> ⚠️ **Corretta per la ragione sbagliata.** La previsione di NN1 è corretta, ma **il modello guarda i pixel sbagliati**. Ci si può fidare di un modello che **sembra accurato per la ragione sbagliata**? Probabilmente NN1 ha imparato una correlazione con lo sfondo o con un artefatto del dataset (il caso dei «cani gialli» e il dilemma M1/M2 delle Lezioni 5 e 7): funziona finché i dati assomigliano al training e fallisce appena cambiano. NN2, meno sicura, è la rete da preferire.

#### **5.9. Modello: l’attenzione come heatmap**

La matrice è la **mappa di attenzione** di una testa di un Transformer che legge la frase «*the pump is too hot*» (la pompa è troppo calda). Righe: token corrente (*query*); colonne: token osservato (*key*).

| query \ key | the | pump | is | too | hot |
|---|---:|---:|---:|---:|---:|
| **the** | 0,15 | 0,23 | 0,15 | 0,20 | 0,27 |
| **pump** | 0,04 | 0,37 | 0,04 | 0,10 | 0,44 |
| **is** | 0,13 | 0,17 | 0,15 | 0,24 | 0,31 |
| **too** | 0,05 | 0,09 | 0,07 | 0,27 | 0,53 |
| **hot** | 0,02 | 0,13 | 0,03 | 0,15 | **0,68** |

$$A=\text{softmax}\!\left(\frac{QK^\top}{\sqrt{d}}\right),$$

dove $Q$ è il **vettore query** di ogni token (che cosa cerca), $K$ il **vettore key** (che cosa offre) e $d$ la **dimensione dei vettori** (qui 2); $\sqrt{d}$ **mantiene i punteggi moderati**. **Ogni riga somma a 1.**

<!-- SOURCE_VISUAL id="IS11-V53" source="L11-web-v24.pdf" page="69" type="grafico" description="Blocco Transformer semplificato e heatmap 5x5 dell'attenzione sulla frase the pump is too hot" reason="Heatmap e schema del blocco visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123210434.png)

**Esempio** (numeri giocattolo): la riga di «*hot*» mette **0,68** su se stessa, **0,15** su «*too*» e **0,13** su «*pump*». È una **diagnostica, non una prova di spiegazione**. Il blocco Transformer semplificato (Vaswani et al., 2017) è: embedding + posizione → attenzione multi-testa → *add & norm* → feed-forward → *add & norm*, ripetuto $N$ volte (Lezione 13).

> 📌 **Perché conta per il progettista.** Si verifica che il modello **presti attenzione alle parti rilevanti** dell’input e non ad **artefatti** come la punteggiatura o la posizione. Qui «*pump*» guarda soprattutto «*hot*» (0,44): il modello collega l’oggetto alla sua condizione.

#### **5.10. Esplorare lo spazio dei parametri: il paesaggio della perdita**

**Che cosa si fa.** Si parte dai pesi addestrati $w^*$ (1210 numeri). Si scelgono **due direzioni casuali** $d_1,d_2$ nello spazio dei pesi e ci si sposta in

$$w=w^*+\alpha\,d_1+\beta\,d_2.$$

$\alpha$ (asse $x$) e $\beta$ (asse $y$) dicono **quanto ci si sposta** lungo ciascuna direzione, in unità della dimensione dei filtri; il colore è la **perdita di validation** in $w$. La stella ($\alpha=\beta=0$) è la rete addestrata, con perdita **0,20**.

**Che cosa si vede.** Allontanandosi dalla stella la perdita supera **4 agli angoli**: il minimo è un **bacino stretto**. È una fetta 2D di uno spazio a 1210 dimensioni (Li et al., NeurIPS 2018).

<!-- SOURCE_VISUAL id="IS11-V54" source="L11-web-v24.pdf" page="70" type="grafico" description="Paesaggio della perdita di validation sul piano di due direzioni casuali attorno ai pesi addestrati, con la stella al minimo" reason="Mappa di colore del paesaggio visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123210644.png)

> 📌 **Perché conta per il progettista.** Un **bacino piatto** significa che la soluzione **tollera piccole modifiche dei pesi** (rumore, pruning, quantizzazione) e di solito **generalizza meglio**; un **bacino stretto** indica una **soluzione fragile**. Si sceglie la configurazione di addestramento che dà il bacino più piatto.

> 💡 **Perché «in unità della dimensione dei filtri» (nota didattica).** Le direzioni casuali vengono normalizzate strato per strato in modo che ogni loro componente abbia la stessa norma dei pesi corrispondenti della rete (*filter normalization*). Senza questa normalizzazione, uno spostamento $\alpha=1$ sarebbe enorme per uno strato con pesi piccoli e trascurabile per uno con pesi grandi, e confrontare la «piattezza» di reti diverse non avrebbe senso.

#### **5.11. Mostrare il processo di ottimizzazione**

Un esempio di **discesa del gradiente stocastica** in un problema di ottimizzazione **non convesso**: il percorso dei pesi su una superficie con più valli.

<!-- SOURCE_VISUAL id="IS11-V55" source="L11-web-v24.pdf" page="71" type="grafico" description="Percorso della discesa del gradiente stocastica su una superficie non convessa" reason="Percorso dell'ottimizzazione visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123210756.png)

> 📌 **Perché conta per il progettista.** Il percorso mostra se la discesa **funziona** (passi regolari in discesa) o è **erratica** (oscillazioni, un plateau, una valle sbagliata): indica di cambiare il **learning rate**, la **dimensione del batch** o l’**ottimizzatore**.

#### **5.12. Passi 3 e 4 del flusso: perché l’EDA dopo il ML?**

- **analisi degli errori** e **interpretabilità**;
- **valutazione delle prestazioni** del modello;
- **confronto** tra modelli e iperparametri;
- analisi di **confidenza**, **calibrazione** e **incertezza**;
- **dashboard** di rilascio e **monitoraggio del drift**.

![](imgs/Pasted%20image%2020261123210819.png)

#### **5.13. Prestazioni: le curve di addestramento**

Due grafici per epoca (0–80): la perdita *cross-entropy* e l’accuratezza.

- **Blu continuo**: perdita di training **sulle etichette usate per l’addestramento**, di cui il 5% invertito di proposito; resta **sopra** la perdita di validation (**0,31 contro 0,20**) solo a causa di quel rumore.
- **Blu tratteggiato**: le **stesse immagini di training** valutate con le **etichette vere**: **0,14**, **sotto** la perdita di validation, come di consueto. **Una curva di training peggiore della validation suggerisce etichette sbagliate.**
- **Miglior epoca di validation**: **74**, perdita **0,198**; l’accuratezza si stabilizza vicino al **95%**. TensorBoard disegna queste curve in tempo reale.

<!-- SOURCE_VISUAL id="IS11-V56" source="L11-web-v24.pdf" page="73" type="grafico" description="Curve di perdita cross-entropy e accuratezza: training con etichette rumorose, training con etichette vere, validation" reason="Andamento delle curve visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123210945.png)

> 📌 **Perché conta per il progettista.** Il **divario tra le due curve** indica l’**overfitting**, e la **miglior epoca** dice **quando fermare** l’addestramento (*early stopping*); una curva dal comportamento strano indica **etichette sbagliate** o un **learning rate** inadatto.

> 💡 **Perché la curva di training sta sopra (nota didattica).** Normalmente la perdita di training è più bassa di quella di validation, perché il modello ha visto quei dati. Qui il 5% delle etichette di training è sbagliato: la rete, che generalizza bene, assegna a quelle immagini la classe vera, e la perdita calcolata sull’etichetta sbagliata è alta. Valutando le stesse immagini con le etichette vere (curva tratteggiata) l’ordine consueto si ristabilisce.

#### **5.14. Mostrare gli intervalli di confidenza di una rete addestrata**

La **mancanza di dati produce intervalli più ampi**: le bande si **allargano dove non esiste alcun punto di training** (punti rossi).

<!-- SOURCE_VISUAL id="IS11-V57" source="L11-web-v24.pdf" page="74" type="grafico" description="Previsione di una rete con banda di confidenza che si allarga dove mancano i punti di training" reason="Bande e punti visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123210937.png)

> 💡 **Perché gli intervalli si allargano (nota didattica).** Nelle regioni con molti esempi tutte le funzioni compatibili con i dati concordano; dove mancano dati, molte funzioni diverse spiegano ugualmente bene il training e divergono tra loro. Mostrare l’intervallo, e non solo la curva media, avverte l’utente di non fidarsi delle previsioni nelle zone scoperte (il «buco topologico» della Lezione 6).

##### **5.14.1. Come si calcola la confidenza di una rete di regressione**

Una rete riceve immagini di $64\times 64$ pixel e restituisce **un valore per immagine**, $\hat{y}$ in °C. Sul **dataset A** ($N=200$ immagini) il valore vero $y$ è noto, quindi l’errore è $e_i=y_i-\hat{y}_i$ [°C]. Si calcolano:

$$\bar{e}=\frac{1}{N}\sum_{i=1}^{N}e_i,\qquad \sigma=\sqrt{\frac{1}{N-1}\sum_{i=1}^{N}(e_i-\bar{e})^2}.$$

$N$ è il numero di immagini; $\bar{e}$ l’**errore medio** (bias) [°C]; $\sigma$ la **deviazione standard degli errori**, nella stessa unità di $e$ [°C].

**Esempio**: $e=(1,0,3,-2,-1)$ dà $\bar{e}=0{,}2$ e $\sigma=1{,}92$ °C.

> 💡 **Il conto (nota didattica).** $\bar{e}=(1+0+3-2-1)/5=1/5=0{,}2$. Gli scarti sono $0{,}8;\,-0{,}2;\,2{,}8;\,-2{,}2;\,-1{,}2$, i loro quadrati $0{,}64+0{,}04+7{,}84+4{,}84+1{,}44=14{,}8$; dividendo per $N-1=4$ si ottiene $3{,}7$ e $\sqrt{3{,}7}=1{,}92$.

> 📌 **Nota statistica.** Se gli errori sono circa **gaussiani**, $\hat{y}\pm\sigma$ contiene circa il **68%** dei valori veri, $\hat{y}\pm 2\sigma$ circa il **95%**, $\hat{y}\pm 3\sigma$ circa il **99,7%**: un **intervallo di confidenza** per la previsione.

##### **5.14.2. L’intervallo sul grafico: gli errori del dataset A**

- $\sigma=1{,}86$ °C: l’intervallo al 95% è $\hat{y}\pm 3{,}72$ °C.
- Il **71%** dei 200 errori cade entro $\pm\sigma$, il **95%** entro $\pm 2\sigma$: la regola gaussiana **qui vale**; va **verificata sui propri dati**.
- **Perché una campana?** Un errore è la **somma di molte piccole cause indipendenti** (rumore del sensore, etichette, approssimazione del modello), e somme di questo tipo **tendono a una gaussiana** (teorema del limite centrale).
- **Stessa unità**: errori e $\sigma$ sono in °C, quindi la banda condivide l’asse con gli errori. $\sigma^2$ è in °C² e **non può**.

<!-- SOURCE_VISUAL id="IS11-V58" source="L11-web-v24.pdf" page="76" type="grafico" description="Errori dei 200 campioni del dataset A con bande +-1 sigma e +-2 sigma e istogramma a campana" reason="Errori, bande e istogramma visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211019.png)

> 📌 **Perché conta per il progettista.** Dice all’utente **quanto ci si può fidare di un singolo valore previsto**, nella **stessa unità della misura**: «23,5 °C ± 3,7 °C al 95%» è un’informazione utilizzabile, «23,5 °C» da solo no.

##### **5.14.3. Da dove viene la confidenza di una singola immagine**

- **Input**: un’immagine $8\times 8$ (64 pixel). **Output**: dieci **punteggi** $z_k$ e dieci **probabilità** $p_k$.

$$z_k=\sum_{j=1}^{16}w_{jk}\,h_j+b_k,\qquad p_k=\frac{e^{z_k}}{\sum_{m=1}^{10}e^{z_m}},\qquad \text{confidenza}=\max_k p_k.$$

$h_j$ è l’attivazione dell’unità nascosta $j$; $w_{jk}$ e $b_k$ sono peso e bias verso la classe $k$; $z_k$ è il punteggio della classe $k$. Le $p_k$ stanno in $[0,1]$ e i dieci valori **sommano a 1**. La classe prevista è quella con la $p_k$ più grande.

| Classe | 0 | 1 | 2 | **3** | 4 | 5 | 6 | 7 | 8 | 9 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| $z_k$ | −6,0 | −3,9 | +0,6 | **+4,9** | −7,1 | −0,8 | −2,1 | −4,3 | −1,0 | −1,1 |
| $p_k$ | 0,00 | 0,00 | 0,01 | **0,98** | 0,00 | 0,00 | 0,00 | 0,00 | 0,00 | 0,00 |

**Esempio** (epoca 80): $z_3=+4{,}92$, $\sum_m e^{z_m}=140{,}5$, quindi $p_3=137/140{,}5=0{,}977$: **confidenza 0,98**.

<!-- SOURCE_VISUAL id="IS11-V59" source="L11-web-v24.pdf" page="77" type="schema e grafico" description="Rete 64-16-10 con punteggi zk e probabilità softmax pk per un 3, e curve di punteggio e probabilità per epoca" reason="Schema e curve visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211053.png)

**Leggere le curve**: all’epoca 1 la confidenza è **0,17**, all’epoca 10 **0,75**, all’epoca 80 **0,98**. La slide successiva segue questo numero, epoca per epoca, per ogni immagine di training.

> 💡 **Il softmax amplifica le differenze (nota didattica).** $e^{4{,}92}\approx 137$, mentre il secondo punteggio più alto, $e^{0{,}6}\approx 1{,}8$, è quasi 75 volte più piccolo: per questo un vantaggio di 4,3 nel punteggio diventa una probabilità del 98%. Una confidenza alta, però, non garantisce che la previsione sia giusta: lo dirà la calibrazione (§5.17).

#### **5.15. Dati: una mappa del training set (dataset cartography)**

È la stessa idea delle slide precedenti — una media e una deviazione standard — ora **calcolate sulle epoche**. Per l’immagine di training $i$ si registra $p_e$, la **probabilità assegnata alla sua etichetta** all’epoca $e$:

$$\text{conf}_i=\frac{1}{E}\sum_{e}p_e,\qquad \text{std}_i=\sqrt{\frac{1}{E}\sum_{e}\big(p_e-\text{conf}_i\big)^2}.$$

$E$ è il numero di epoche (80); $p_e\in[0,1]$; $\text{conf}$ è la media di $p_e$ (come $\bar{e}$), $\text{std}$ la sua deviazione standard (come $\sigma$).

**Perché la storia?** Immagine A: $p=0{,}9$ a ogni epoca. Immagine B: $p=0{,}2;\,0{,}5;\,0{,}8;\,0{,}9$. **Stesso valore finale 0,9**, ma B è stata **imparata tardi**: $\text{conf}=0{,}60$, $\text{std}=0{,}27$, contro $0{,}90$ e $0$ per A.

**Da dove viene $p$?** Il neurone di uscita $k$ (uno per classe) ha attivazione $z_k$, la sua «forza»; il softmax dà $p_k=e^{z_k}/\sum_j e^{z_j}$. Esempio: $z=(2{,}0;\,1{,}0;\,0{,}1)$ dà $p=(0{,}66;\,0{,}24;\,0{,}10)$.

La mappa ha la **confidenza** sull’asse verticale e la **variabilità** (std) su quello orizzontale, e individua tre regioni:

| Regione | Confidenza | Variabilità | Significato |
|---|---|---|---|
| **facili da imparare** | alta | bassa | esempi tipici, imparati subito |
| **ambigui** | media | alta | esempi al confine, la rete cambia idea |
| **difficili da imparare** | bassa | bassa | esempi che la rete non accetta mai: spesso **etichette sbagliate** |

<!-- SOURCE_VISUAL id="IS11-V60" source="L11-web-v24.pdf" page="78" type="grafico" description="Mappa del dataset: confidenza media contro deviazione standard per le 1000 immagini, etichette originali e 50 invertite" reason="Disposizione dei punti nelle tre regioni visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211135.png)

> 📌 **Risultato.** **60 immagini** hanno confidenza sotto **0,25**, e **47 di queste** sono tra le **50 etichette invertite**: la mappa **trova le etichette sbagliate** (Swayamdipta et al., EMNLP 2020).

> 💡 **I conti (nota didattica).** Per B: $\text{conf}=(0{,}2+0{,}5+0{,}8+0{,}9)/4=0{,}60$; scarti $-0{,}4;\,-0{,}1;\,0{,}2;\,0{,}3$, quadrati $0{,}16+0{,}01+0{,}04+0{,}09=0{,}30$, diviso 4 e radice: $\sqrt{0{,}075}=0{,}27$. Per il softmax: $e^2=7{,}39$, $e^1=2{,}72$, $e^{0{,}1}=1{,}11$, somma $11{,}21$, quindi $p=(0{,}66;\,0{,}24;\,0{,}10)$. In termini di metriche, la mappa recupera 47 delle 50 etichette sbagliate (recall $47/50=94\%$) segnalando 60 immagini (precisione $47/60\approx 78\%$): uno strumento efficace di pulizia del dataset (Lezione 8).

#### **5.16. Dashboard di monitoraggio: TensorBoard (Google)**

TensorBoard fornisce una **suite di strumenti di visualizzazione** per rendere più facile **capire, fare debug e ottimizzare** i modelli. Si può usare per visualizzare il **grafo** TensorFlow, tracciare **metriche quantitative** sull’esecuzione del grafo e mostrare **dati aggiuntivi**, come le **immagini** che lo attraversano.

<!-- SOURCE_VISUAL id="IS11-V61" source="L11-web-v24.pdf" page="79" type="schermata" description="Interfaccia di TensorBoard con metriche di addestramento e grafo" reason="Interfaccia visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211145.png)

#### **5.17. Prestazioni: la matrice di confusione**

- **Righe**: classe vera. **Colonne**: classe prevista. **Diagonale**: previsioni corrette (**378 su 397**).
- **Fuori diagonale**: **19 errori**. La confusione più grande: **3 vero previsto come 8**, **3 immagini**.
- Dice **quali classi** vengono confuse; **non dice perché**: bisogna guardare quelle immagini.
- La si vedrà più in dettaglio più avanti nel corso (Lezione 16).

<!-- SOURCE_VISUAL id="IS11-V62" source="L11-web-v24.pdf" page="80" type="grafico" description="Matrice di confusione 10x10 delle cifre con 378 corretti in diagonale e 19 errori, tra cui tre 3 previsti come 8" reason="Valori e colori della matrice visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211154.png)

> 📌 **Perché conta per il progettista.** Dice **quali classi migliorare per prime**, con più dati o etichette migliori per quelle classi, invece di **regolare alla cieca l’intera rete**.

> 💡 **Leggere la diagonale (nota didattica).** I valori sulla diagonale sono 39, 39, 40, 34, 34, 40, 37, 39, 36, 40, che sommano a 378; $378/397=95{,}2\%$, l’accuratezza dichiarata. Le righe delle classi 3 e 4 hanno 34 corretti su 40: sono le due classi più deboli, come confermerà l’analisi per slice (§5.19).

#### **5.18. Confidenza e accuratezza: il modello è sicuro quando ha ragione?**

**Diagramma di affidabilità** (*reliability diagram*). Ogni punto è un **bin di confidenza**: $x$ è ciò che il modello **dichiara**, $y$ quanto spesso **ha ragione**. Sulla **diagonale tratteggiata** il modello è **calibrato** (dice 80% e ha ragione l’80% delle volte). **Sotto la diagonale** è **sovraconfidente**, **sopra** è **sottoconfidente**.

**Istogramma delle confidenze.** Quante immagini di test cadono in ogni bin di confidenza. Qui la maggior parte delle immagini ha **confidenza molto alta**: il modello è sicuro sulla maggioranza. I punti del grafico di sinistra che provengono da **bin con poche immagini** sono **rumorosi**: non vanno sovrainterpretati.

<!-- SOURCE_VISUAL id="IS11-V63" source="L11-web-v24.pdf" page="81" type="grafico" description="Diagramma di affidabilità con la diagonale di calibrazione perfetta e istogramma delle confidenze dei 397 test" reason="Punti e istogramma visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211207.png)

> 📌 **Come li legge il progettista.** Prima l’**istogramma** (dove sta la maggior parte delle immagini?), poi i **punti di quei bin** rispetto alla diagonale. Se stanno **sotto**, le probabilità sono **troppo ottimiste**: vanno **ricalibrate** (per esempio con il *temperature scaling*) prima di usarle in allarmi o costi.

> 📌 **ECE (*Expected Calibration Error*, errore atteso di calibrazione).** È lo **scarto medio** tra accuratezza e confidenza sui bin, **pesato** per il numero di immagini di ciascun bin:
> $$\text{ECE}=\sum_{b=1}^{M}\frac{|B_b|}{n}\,\big|\text{acc}(B_b)-\text{conf}(B_b)\big|,$$
> con $M$ bin, $B_b$ l’insieme delle immagini nel bin $b$ e $n$ il totale (formula standard, nota didattica).

> 💡 **Il temperature scaling (nota didattica).** Si divide ogni punteggio per una costante $T>0$ prima del softmax: $p_k=e^{z_k/T}/\sum_m e^{z_m/T}$. Con $T>1$ le probabilità si «ammorbidiscono» e la sovraconfidenza cala; $T$ si stima sul validation set. La classe prevista non cambia, perché dividere tutti i punteggi per la stessa costante non ne altera l’ordine: si corregge la confidenza senza toccare l’accuratezza.

#### **5.19. Prestazioni: guardare le slice, non solo la media**

- **Slice per classe vera.** Una barra per cifra; la linea tratteggiata è l’**errore complessivo**. Le barre **sopra la linea** sono le classi in cui il sistema è **più debole** (qui **3 e 4**): è lì che conviene **aggiungere dati o correggere etichette** per primi.
- **Slice per una proprietà misurabile.** Lo stesso grafico per una proprietà che si può calcolare **senza etichette**, qui la **quantità d’inchiostro** (leggera, media, pesante). **Barre piatte vicino alla linea**: il sistema **non dipende** da quella proprietà. Una barra alta rivelerebbe un **gruppo debole nascosto**.

<!-- SOURCE_VISUAL id="IS11-V64" source="L11-web-v24.pdf" page="82" type="grafico" description="Tasso di errore per classe vera con l'errore complessivo tratteggiato, e per quantità d'inchiostro leggera, media, pesante" reason="Barre visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211244.png)

> 📌 **Come le usa il progettista.** Una slice è un’**ipotesi scelta con l’esperto di dominio** (quale classe? quale illuminazione? quale fornitore?); il grafico la **accetta o la rifiuta**. **La media nasconde i gruppi deboli**: una slice mostra **dove il sistema non va considerato affidabile**, prima che fallisca in produzione. È il paradosso di Simpson della Lezione 10 applicato agli errori.

---

### **6. Visualizzare prestazioni e fiducia: curve, errori e loro costo, calibrazione, spiegazioni**


#### **6.1. Previsione con intervalli (fan chart)**

- **Input**: **156 valori settimanali** di domanda (pezzi/settimana); previsione sulle **ultime 52 settimane**.
- **Output**: la previsione con le **bande all’80% e al 95%**, non un numero singolo.

<!-- SOURCE_VISUAL id="IS11-V65" source="L11-web-v24.pdf" page="84" type="grafico" description="Fan chart della domanda settimanale: dati reali passati, previsione futura con bande all'80% e al 95%" reason="Bande della previsione visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211252.png)

- **Perché in IS4**: a sinistra della linea tratteggiata (**oggi**) i dati sono **reali**; a destra sono **previsioni**, e il futuro è **incerto**: per questo si mostrano gli intervalli. **Scorte e ordini si decidono sull’intervallo, non sulla linea centrale.**
- **Errore tipico**: mostrare **solo la linea centrale**, oppure bande **troppo strette** (la copertura va verificata: §11.3).

> 💡 **Perché la banda si apre a ventaglio (nota didattica).** L’incertezza di una previsione cresce con l’orizzonte: l’errore della settimana prossima si somma agli errori delle settimane successive. Per questo il grafico prende la forma di un ventaglio (*fan*), stretto vicino a «oggi» e largo lontano.

#### **6.2. Gli errori hanno un prezzo: la matrice di confusione pesata dai costi**

- **Input**: **1600 pezzi** di test con difetto sì/no, previsione del modello, **costi ipotizzati**: falso positivo **5 €**, falso negativo **100 €**.
- **Output**: conteggi (sinistra) e costo in euro (destra): **totale 13 815 €**, il **98% dai difetti mancati**.

| Conteggi | Previsto OK | Previsto difetto |
|---|---:|---:|
| **vero OK** | 1296 | 43 |
| **vero difetto** | 136 | 125 |

| Costo (€) | Previsto OK | Previsto difetto |
|---|---:|---:|
| **vero OK** | 0 | 215 |
| **vero difetto** | 13 600 | 0 |

![](imgs/Pasted%20image%2020261123211306.png)

- **Perché in IS4**: **l’accuratezza da sola non decide nulla**: nell’ispezione di qualità un difetto mancato costa molto più di uno scarto inutile.
- **Errore tipico**: i costi sono **ipotesi didattiche**: con dati reali bisogna **chiederli all’azienda e dichiararli**.

> 💡 **I conti (nota didattica).** $43\cdot 5=215$ € di falsi allarmi; $136\cdot 100=13\,600$ € di difetti mancati; totale $13\,815$ €, di cui $13\,600/13\,815=98{,}4\%$ dai falsi negativi. L’accuratezza è $(1296+125)/1600=88{,}8\%$, un numero che sembra buono ma nasconde che il modello lascia passare 136 dei 261 difetti (recall $125/261=48\%$). Abbassare la soglia di allarme (§6.3) aumenterebbe i falsi positivi a 5 € l’uno ma ridurrebbe quelli da 100 €.

#### **6.3. Regolare un classificatore: spostare la soglia finale**

**La soglia.** Il modello assegna a ogni pezzo un **punteggio di difetto**; il classificatore finale **dà l’allarme** se il punteggio è **almeno $t$**. Spostare $t$ cambia il comportamento: con $t$ **più basso** si trovano **più difetti** ma con **più falsi allarmi**; con $t$ **più alto** **meno falsi allarmi** ma **difetti mancati**. Precisione e recall cambiano con $t$; le curve disegnano questo per **ogni soglia**: **ogni punto è una $t$**.

$$\text{recall}=\frac{TP}{TP+FN},\qquad \text{FPR}=\frac{FP}{FP+TN},\qquad \text{precisione}=\frac{TP}{TP+FP}.$$

$TP$: difetti trovati; $FN$: difetti mancati; $FP$: falsi allarmi; $TN$: pezzi buoni passati. **Esempio**: 100 difetti, 80 trovati, 100 allarmi in totale (20 falsi): **recall 0,8**, **precisione 0,8**.

| Curva | Assi | Valore della slide |
|---|---|---|
| **ROC** | tasso di veri positivi (TPR = recall) contro tasso di falsi positivi (FPR) | **AUC = 0,89** |
| **Precision-Recall** | precisione contro recall | **AP = 0,70**, prevalenza = 0,16 |

<!-- SOURCE_VISUAL id="IS11-V66" source="L11-web-v24.pdf" page="86" type="grafico" description="Curva ROC con AUC 0.89 e curva precision-recall con AP 0.70 e prevalenza 0.16" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211319.png)

> 📌 **Per il progettista.** Si sceglie $t$ dal **costo dei due errori** (un difetto mancato costa più di un falso allarme). Le curve saranno presentate in dettaglio nella lezione sulle prestazioni (Lezione 16).

> 💡 **Perché servono due curve (nota didattica).** La prevalenza è 0,16 (coerente con i $261/1600=16\%$ di difetti del §6.2): i pezzi buoni sono la grande maggioranza. La ROC usa il FPR, che ha al denominatore tutti i pezzi buoni: anche molti falsi allarmi producono un FPR piccolo, e la curva può sembrare ottima (AUC 0,89). La curva precision-recall guarda invece quanti degli allarmi sono veri, ed è più severa quando la classe positiva è rara (AP 0,70). Un classificatore casuale avrebbe AUC 0,5 ma AP pari alla prevalenza, 0,16.

#### **6.4. Il modello è sicuro quanto dovrebbe? La calibrazione**

- **Input**: **1500 previsioni** per modello, ciascuna con una confidenza (da 0 a 1) e l’esito reale (giusto o sbagliato); dati sintetici.
- **Output**: la **frequenza osservata** per ogni livello di confidenza, e l’**ECE** (scarto medio tra le due curve).

| Modello | ECE | A confidenza 70% ha ragione… |
|---|---:|---:|
| **ben calibrato** | 0,032 | il **73%** delle volte |
| **sovraconfidente** | 0,108 | solo il **59%** delle volte |

<!-- SOURCE_VISUAL id="IS11-V67" source="L11-web-v24.pdf" page="87" type="grafico" description="Curve di calibrazione di un modello ben calibrato (ECE 0.032) e di uno sovraconfidente (ECE 0.108)" reason="Curve rispetto alla diagonale visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211337.png)

- **Perché in IS4**: la **confidenza mostrata all’utente guida le decisioni**. Con il modello sovraconfidente l’operatore **accetta errori**. **Correzione**: **ricalibrare**.
- **Errore tipico**: **leggere la calibrazione come accuratezza**: un modello calibrato può comunque **classificare male**.

#### **6.5. Quali variabili usa il modello? La permutation importance**

- **Input**: una **random forest** (Lezione 12), **4 variabili di processo** (temperatura, pressione, velocità, umidità), **1600 pezzi** di test.
- **Output**: per ogni variabile, il **calo di accuratezza** quando i suoi valori vengono **rimescolati**.

<!-- SOURCE_VISUAL id="IS11-V68" source="L11-web-v24.pdf" page="88" type="grafico" description="Barre di permutation importance per temperatura, pressione, velocità e umidità" reason="Lunghezze delle barre visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211347.png)

- **Spiegabilità per dati tabellari**: è **la stessa domanda delle mappe di salienza** (quali pixel?), ora posta sulle **variabili** (quali misure?). **Se rimescolare una variabile danneggia il modello, il modello la usa.**
- **Perché conta per il progettista**: si verifica **con l’esperto di processo** che il modello si basi sulle **cause giuste** (qui **temperatura e pressione**), e si possono **eliminare le variabili che non fanno nulla**.
- **Errore tipico**: leggere l’**importanza come causalità**; le **variabili correlate si dividono l’importanza** in modo **instabile**. Questa è la permutation importance, **non SHAP**, che dà lo stesso tipo di risposta **con un segno**.

> 💡 **Perché rimescolare (nota didattica).** Rimescolare i valori di una colonna ne conserva la distribuzione ma rompe il legame con il target e con le altre variabili. Se il modello usava quella variabile, le sue previsioni peggiorano; se non la usava, nulla cambia. Con due variabili quasi identiche, rimescolarne una sola fa poco danno perché il modello si appoggia all’altra: entrambe sembrano poco importanti, anche se insieme sono essenziali.

---

### **7. Visualizzare funzionamento e decisioni: monitoraggio delle condizioni, compromessi, flussi e pianificazioni**


#### **7.1. Monitoraggio delle condizioni: la carta di controllo**

- **Input**: **300 campioni** di vibrazione RMS (mm/s), uno ogni 10 minuti; media e deviazione standard stimate sui **primi 150 campioni sani**.
- **Output**: **linea centrale**, limiti **$\pm 3\sigma$** e **punti di allarme**.

<!-- SOURCE_VISUAL id="IS11-V69" source="L11-web-v24.pdf" page="90" type="grafico" description="Carta di controllo della vibrazione RMS con linea centrale, limiti +-3 sigma e punti di allarme" reason="Andamento e allarmi visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211400.png)

- **Perché in IS4**: è la **base del monitoraggio delle condizioni**; permette di discutere il **costo di un falso allarme** contro un **guasto mancato**.
- **Errore tipico**: **soglie senza legame con i costi**; il criterio $\pm 3\sigma$ **presuppone dati circa gaussiani e indipendenti**, spesso **falso per i sensori**.

$$\text{LCL}=\mu-3\sigma,\qquad \text{UCL}=\mu+3\sigma,$$

con $\mu$ e $\sigma$ stimati sul periodo sano (formula standard, nota didattica).

> 💡 **Perché l’ipotesi di indipendenza conta (nota didattica).** Con dati gaussiani e indipendenti, un punto cade fuori da $\pm 3\sigma$ per caso solo nello 0,27% dei casi: un falso allarme ogni 370 campioni, circa ogni 2,6 giorni con un campione ogni 10 minuti. I segnali dei sensori però sono **autocorrelati** (un valore alto è seguito da valori alti): i falsi allarmi arrivano a grappoli e la stima di $\sigma$ sul periodo sano può essere troppo ottimista.

#### **7.2. Curve di degradazione e vita utile residua (RUL)**

- **Input**: **6 macchine**, indice di salute da 0 a 1 per ciclo operativo.
- **Output**: **traiettorie di degradazione** e la **soglia di guasto a 0,2**.

<!-- SOURCE_VISUAL id="IS11-V70" source="L11-web-v24.pdf" page="91" type="grafico" description="Traiettorie dell'indice di salute di 6 macchine per ciclo operativo con soglia di guasto a 0.2" reason="Traiettorie visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211425.png)

- **Perché in IS4**: introduce la **manutenzione predittiva** e spiega perché servono **molte storie di guasto** per addestrare un modello di **RUL** (*Remaining Useful Life*, vita utile residua).
- **Errore tipico**: **pochi guasti reali** nei dati; **macchine ancora in funzione** (**dati censurati**) trattate come se **non si fossero mai guastate**.

> 💡 **Dati censurati (nota didattica).** Di una macchina ancora funzionante al ciclo 150 si sa solo che la sua vita è **almeno** 150 cicli, non quanto durerà. Trattarla come «vita = 150» sottostima la durata; escluderla spreca informazione. I metodi di analisi di sopravvivenza usano proprio questa informazione parziale.

#### **7.3. Scegliere in presenza di conflitti: la frontiera di Pareto**

- **Input**: **8 modelli ipotetici**, con **latenza** (ms, più bassa è meglio) e **qualità** (%, più alta è meglio), rilasciati **all’edge** o **nel cloud** (Edge S, Edge M, Edge L, Cloud S, Cloud L e i modelli B, C, D).
- **Output**: i **modelli non dominati**, uniti dalla **frontiera**.

<!-- SOURCE_VISUAL id="IS11-V71" source="L11-web-v24.pdf" page="92" type="grafico" description="Frontiera di Pareto tra latenza e qualità di 8 modelli edge e cloud, con i modelli dominati in grigio" reason="Posizione dei modelli visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211436.png)

- **Perché in IS4**: la **frontiera di Pareto** è l’insieme dei modelli per cui **nessun altro modello è migliore su entrambi gli assi contemporaneamente**. È il **luogo dei migliori compromessi**: muovendosi lungo di essa **si guadagna su un asse e si perde sull’altro**; i punti grigi sono **dominati** e si scartano. **Non è un metodo di machine learning**: si può calcolare automaticamente per **piani logistici** e per la **produzione su macchine diverse**.
- **Errore tipico**: mostrare la frontiera senza dichiarare… (la frase è troncata nella slide; presumibilmente: senza dichiarare quale punto si sceglie e perché).

> 📌 **Dominanza (definizione standard, nota didattica).** Una soluzione $a$ **domina** $b$ se è almeno altrettanto buona su tutti gli obiettivi e strettamente migliore su almeno uno. La frontiera di Pareto è l’insieme delle soluzioni non dominate. La scelta di un punto sulla frontiera non è un fatto tecnico ma una decisione: dipende da quanto vale un millisecondo rispetto a un punto percentuale di qualità.

#### **7.4. Flussi: dove va il materiale? (Sankey)**

- **Input**: **tre fornitori**, **produzione**, **scarto** e **clienti**, in tonnellate al mese (totale **100 t**).
- **Output**: **dove si concentrano** i flussi e **dove si perdono**: **15 t su 100 sono scarto**, di cui **10 t riciclate**.

| Nodo | Ingressi | Uscite |
|---|---|---|
| fornitori | — | A 40 t, B 35 t, C 25 t |
| produzione e scarto | 100 t | produzione 85 t, scarto 15 t |
| produzione | 85 t | cliente UE 50 t, cliente extra-UE 35 t |
| scarto | 15 t | riciclo 10 t, smaltimento 5 t |

<!-- SOURCE_VISUAL id="IS11-V72" source="L11-web-v24.pdf" page="93" type="grafico" description="Sankey dei flussi di materiale con fornitori A, B, C, produzione, scarto, clienti, riciclo e smaltimento" reason="Bande del Sankey visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211504.png)

- **Perché in IS4**: i **bilanci di materiale, energia e CO₂** sono il **linguaggio comune** di industria, ambiente e supply chain.
- **Errore tipico**: **unità e perimetro non dichiarati**; i flussi **devono bilanciarsi a ogni nodo** (qui $40+35+25=100=85+15$, $85=50+35$, $15=10+5$).

#### **7.5. Pianificazioni: il diagramma di Gantt**

- **Input**: **7 ordini** che attraversano **4 macchine** in sequenza (taglio, fresatura, tornitura, assemblaggio), durate casuali da 2 a 5 ore.
- **Output**: una pianificazione con **macchine ferme** e **colli di bottiglia** (totale circa **39 h**, in ordine di arrivo, **non ottimizzata**).

<!-- SOURCE_VISUAL id="IS11-V73" source="L11-web-v24.pdf" page="94" type="grafico" description="Gantt di 7 ordini su 4 macchine con tempi morti e colli di bottiglia, circa 39 ore" reason="Barre del Gantt visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211513.png)

- **Perché in IS4**: lo stesso grafico mostra l’**output di un algoritmo di scheduling** o la **traccia di un agente che pianifica** (§8.3).
- **Errore tipico**: **confrontare due pianificazioni senza dichiarare l’obiettivo**: tempo totale, ritardi, utilizzo delle macchine.

#### **7.6. Promemoria: che cosa disegnare e che cosa può andare storto**

| Dimensione | Domanda | Grafico | Avvertenza |
|---|---|---|---|
| **dati** | bilanciati, puliti, ben etichettati? | griglia di immagini, proiezione, mappa del dataset | le **proiezioni distorcono le distanze** |
| **modello** | che cosa ha imparato, dove guarda? | pesi, attivazioni, salienza, attenzione | **diagnostiche, non prove** |
| **prestazioni** | dove sbaglia, quanto è sicuro? | curve, matrice di confusione, calibrazione, slice | **la media nasconde le slice** |
| **drift** | il mondo è cambiato dall’addestramento? | istogrammi riferimento contro attuale, PSI | **una media stabile può nascondere una forma cambiata** |

---

### **8. L’AI di frontiera come agente di visualizzazione**


System prompt, codice Python, esecuzione, controllo e revisione.

#### **8.1. Il ciclo: progettista, agente, figura**

Il **progettista** invia all’**agente** un prompt e i dati; l’agente **scrive il codice**, il codice **viene eseguito** e produce la **figura**, che torna al progettista per essere **controllata e rivista**.

![](imgs/Pasted%20image%2020261123211557.png)

- **Input**: un **file di dati** (righe, colonne), una **frase con il messaggio**, dei **vincoli** (pubblico, colori, formato).
- **Output**: uno **script** e una **figura**, **in pochi secondi**; il progettista **verifica** il risultato, **rivede il prompt** e chiede di nuovo.
- **L’agente non ha accesso alla domanda reale**: solo a ciò che è **scritto nel prompt e nei dati**.

#### **8.2. Istruzioni che non si vedono modellano anch’esse la figura**

> 📌 **Perché conta per il progettista.** Quando si chiede un grafico a un agente, l’agente **ha già ricevuto istruzioni da qualcun altro**, e non le vediamo. Possono **cambiare lo script che scrive** e quindi **la figura che otteniamo**, senza alcun segno sulla pagina.

| Chi scrive le istruzioni | Visibilità |
|---|---|
| il **fornitore** del modello e dello strumento: **system prompt** e **descrizioni degli strumenti** | **nascoste** all’utente |
| il **team** che ha configurato l’agente: **prompt interni** | **non visibili** |
| il **progettista** (noi): regole, linee guida di design e **skill** scritte di proposito | è **buona pratica** ed è **sotto il nostro controllo** |

**Un esempio: le regole di uno strumento per grafici rapidi.**

- «*line, bar, or scatter*» (solo linee, barre o scatter); «*at most 12 series and 2,000 points*» (al massimo 12 serie e 2000 punti);
- «*never rely on color alone to carry meaning*» (non affidare mai il significato al solo colore);
- «*Do not use this tool when […] you would have to invent or estimate the data*» (non usare lo strumento quando si dovrebbero inventare o stimare i dati).

**Tipi, limiti e accessibilità sono decisi prima che chiediamo.**

> ✅ **Che cosa fare.** Scrivere **le proprie linee guida e skill**; **leggere lo script e controllare i numeri**. **Il messaggio del grafico resta compito nostro.**

#### **8.3. Che cosa succede dentro l’agente mentre lavora: la traccia**

> 📌 **Perché conta per il progettista.** I modelli di frontiera di oggi **non rispondono soltanto**: **eseguono un compito in più passi**, e non si può giudicare il risultato **senza vedere i passi**. La **traccia** è la **visualizzazione di quel lavoro**: dice che cosa l’agente ha deciso, quali strumenti ha usato, dove ha atteso e dove una persona deve approvare.

La traccia d’esempio (esecuzione illustrativa, senza dati reali) mostra sei passi su un asse dei tempi: **pianificazione** (ragionamento), **lettura dei sensori** (chiamata a strumento), **recupero dei manuali** (chiamata a strumento), **valutazione del rischio** (controllo), **approvazione umana** (persona), **creazione dell’ordine di lavoro** (azione).

<!-- SOURCE_VISUAL id="IS11-V74" source="L11-web-v24.pdf" page="99" type="grafico" description="Traccia di un agente come Gantt: pianificazione, lettura sensori, recupero manuali, valutazione del rischio, approvazione umana, ordine di lavoro" reason="Barre temporali della traccia visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211627.png)

| Che cosa leggere (concetti, non numeri) | Che cosa progettare |
|---|---|
| **ordine e dipendenza**: un passo parte solo quando il precedente ha prodotto ciò che gli serve | il **punto di approvazione umana**, **prima delle azioni irreversibili** |
| **chiamate a strumenti**: dove l’agente legge dati, recupera documenti o agisce su un sistema | **dove registrare e mostrare errori e tentativi** |
| **attese**: le barre più lunghe sono i **colli di bottiglia** | **limite**: la traccia mostra **che cosa ha fatto** l’agente, **non se aveva ragione** |

> 💡 **Il legame con la Lezione 15 (nota didattica).** La traccia è la versione visiva del ciclo agentico (pensare, agire con uno strumento, osservare, ripetere fino alla condizione di arresto). L’approvazione umana prima di creare l’ordine di lavoro è il principio *human-in-the-loop* per le azioni con effetti sul mondo reale.

#### **8.4. Che cosa programma l’agente: un grafico è un breve script**

```python
import matplotlib.pyplot as plt, numpy as np
# (superati, totale) per linea e tipo di prodotto
C = {'A': {'easy': (19, 20), 'hard': (72, 100)},
     'B': {'easy': (90, 100), 'hard': (14, 20)}}
groups = ['easy', 'hard']
w = 0.36
fig, ax = plt.subplots(figsize=(5, 3.4))
for j, line in enumerate(C):
    rate = [100 * C[line][g][0] / C[line][g][1]
            for g in groups]
    ax.bar(np.arange(2) + (j - .5) * w, rate, w,
           label='Line ' + line)
ax.set_xticks([0, 1]); ax.set_xticklabels(groups)
ax.set_ylabel('pass rate (%)'); ax.legend()
plt.savefig('grouped.pdf', bbox_inches='tight')
```

<!-- SOURCE_VISUAL id="IS11-V75" source="L11-web-v24.pdf" page="100" type="grafico" description="Grafico a barre raggruppate prodotto dallo script: linee A e B per prodotti facili e difficili (95%, 90%, 72%, 70%)" reason="Grafico risultante visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123211700.png)

> 📌 **Leggere il codice.** Il tasso è calcolato come **superati diviso totale dentro ciascun gruppo**. Se l’agente avesse **sommato prima i gruppi**, la figura sarebbe stata il **grafico a barre aggregato** — e avrebbe raccontato la storia opposta (§9).

> 💡 **Come si dispongono le barre (nota didattica).** `np.arange(2)` dà le posizioni 0 e 1 dei due gruppi; `(j - .5) * w` sposta la linea A ($j=0$) di $-0{,}18$ e la linea B ($j=1$) di $+0{,}18$, così le due barre di larghezza $w=0{,}36$ stanno affiancate attorno a ogni posizione.

---

### **9. Quattro visualizzazioni, un dataset: correttezza, informazione, possibile bias**


#### **9.1. Il caso: gli stessi dati, quattro viste efficaci**

![](imgs/Pasted%20image%2020261123211736.png)

| Tipo di prodotto | Linea A: superati / totale | Linea B: superati / totale |
|---|---|---|
| **facile** | 19/20 | 90/100 |
| **difficile** | 72/100 | 14/20 |

**Prompt all’agente**: «*Ecco i conteggi di superamento di due linee di produzione per tipo di prodotto. Proponi quattro diverse visualizzazioni efficaci degli stessi dati, un messaggio ciascuna, e fornisci il codice.*»

Sono gli **stessi dati sintetici dell’esempio di Simpson** della lezione precedente. **Otto numeri, quattro storie possibili.**

#### **9.2. Quattro viste degli stessi otto numeri**

| Vista | Che cosa mostra | Messaggio |
|---|---|---|
| **1. Aggregato** | un tasso per linea: A 75,8%, B 86,7% | **B è migliore** |
| **2. Per tipo** | tasso per gruppo: facili 95% contro 90%, difficili 72% contro 70% | **A è migliore in entrambi** |
| **3. Mix** | quota di prodotti facili e difficili: A 17%/83%, B 83%/17% (120 pezzi ciascuna) | **B produce soprattutto pezzi facili** |
| **4. Punti** | tasso per gruppo e per «tutti», con l’area del punto = numero di pezzi | **tasso e numerosità insieme** |

<!-- SOURCE_VISUAL id="IS11-V76" source="L11-web-v24.pdf" page="103" type="grafico" description="Quattro viste degli stessi conteggi: aggregato, per tipo, mix dei prodotti, punti con area proporzionale ai pezzi" reason="Quattro grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **9.3. Confrontare le quattro viste**

| Vista | Correttezza | Informazione trasmessa | Possibile bias |
|---|---|---|---|
| **1 Aggregato** | numeri giusti, **confronto fuorviante** | un tasso per linea | **nasconde il mix di prodotti** (Simpson) |
| **2 Per tipo** | giusta | il tasso in ogni gruppo | **nasconde la dimensione del campione** (20 contro 100 pezzi) |
| **3 Mix** | giusta | **perché l’aggregato si inverte** | **non mostra alcun tasso**: spiega, non confronta |
| **4 Punti** | giusta | tasso e dimensione insieme | l’**area si legge meno precisamente della lunghezza** (Bertin) |

#### **9.4. Quale vista è quella giusta? Dipende dalla domanda**

- La **vista 1** è **pulita e sbagliata** per una decisione sulle linee: nasconde che B produce l’**83% di pezzi facili** e A solo il **17%**.
- La **vista 2** risponde a **quale linea è migliore, prodotto per prodotto**: A (95% contro 90%, 72% contro 70%).
- La **vista 3** **spiega l’inversione**: il mix di pezzi facili e difficili, non la qualità della linea.
- La **vista 4** mostra **tasso e dimensione del campione insieme**: le celle da **20 pezzi** sono le **meno affidabili**.
- **Tutte e quattro sono grafici onesti degli stessi numeri**; solo **la domanda** decide quale mostrare.

> 📌 **Il limite dell’agente.** L’agente può produrre le quattro viste **in un minuto**. **Non può sapere** che la decisione riguarda il **confronto tra linee a parità di difficoltà**.

> 💡 **Perché le celle da 20 sono meno affidabili (nota didattica).** Con $n=20$ pezzi, un solo pezzo in più o in meno sposta il tasso di 5 punti percentuali; con $n=100$, di un punto. La differenza tra 72% (su 100) e 70% (su 20) è ben dentro questa incertezza: la vista 4 lo rende visibile, la vista 2 no.

#### **9.5. Strumenti di BI e di monitoraggio con assistente AI**

![](imgs/Pasted%20image%2020261123212443.png)

| Tipo | Strumenti | Uso tipico |
|---|---|---|
| **visualizzazione degli esperimenti ML** | TensorBoard, Weights & Biases, MLflow, Neptune, ClearML | curve di addestramento, perdita, accuratezza, iperparametri, confronto tra modelli |
| **BI / dashboard con AI** | Tableau, Power BI, Zoho Analytics, Domo, ThoughtSpot | metriche di business, **query in linguaggio naturale**, monitoraggio dei KPI, report |
| **osservabilità / monitoraggio operativo** (passo 5 del flusso) | Grafana, Datadog, Prometheus, Evidently AI, WhyLabs | sistemi in produzione, telemetria, drift, allarmi, affidabilità |

> 📌 **Osservabilità.** Significa **monitorare un sistema rilasciato attraverso segnali misurabili**, per **capirne il comportamento**, **rilevare guasti** e **supportare azioni correttive**.

**Esempio: Microsoft Power BI.** Si collega a dati di **molte sorgenti** e li visualizza su una **piattaforma unificata e scalabile** per la business intelligence **self-service e aziendale**.

<!-- SOURCE_VISUAL id="IS11-V77" source="L11-web-v24.pdf" page="107" type="schermata" description="Dashboard di Microsoft Power BI" reason="Interfaccia visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **10. Il progettista resta responsabile**


L’AI **automatizza la produzione**, non la **domanda** né l’**interpretazione**.

#### **10.1. L’agente non sostituisce il progettista**

1. **Prima il messaggio**: scrivere che cosa il lettore deve concludere **prima** di chiedere qualunque grafico.
2. **Ricalcolare due numeri a mano**: 19/20 è il 95%; la figura dice 95%?
3. **Chiedere i gruppi**: dividere per **ogni confondente noto** prima di aggregare (paradosso di Simpson).
4. **Chiedere il grafico opposto**: «quale vista contraddirebbe questa?».
5. **Controllare assi e omissioni**: base dell’asse, scala, colore, che cosa è lasciato fuori.
6. **Conservare dati e codice**: **una figura che non si può rigenerare non è un’evidenza**.

> ⚠️ **L’agente ha i propri bias.** Disegna **ciò che gli è stato chiesto e ciò che è comune**, e può disegnare **rumore** o un’**interpretazione sbagliata** con **totale sicurezza**. **Comprensione ed esperienza restano al progettista.**

#### **10.2. Alcune slide divertenti prima di chiudere (non d’esame)**

![](imgs/Pasted%20image%2020261123212553.png)

![](imgs/Pasted%20image%2020261123212614.png)

Le ultime slide **non sono d’esame**: si può confrontare i diversi 007? Si è creativi? Si possono confrontare stili di vita diversi?

- **Uso eccessivo della visualizzazione** (umorismo): anche per «applicazioni» molto particolari, la scelta di uno **spazio di input** adeguato permette **confronti** e dà **intuizioni**.

<!-- SOURCE_VISUAL id="IS11-V78" source="L11-web-v24.pdf" page="111" type="figura" description="Visualizzazione umoristica che confronta i diversi interpreti di 007 in uno spazio di input" reason="Illustrazione visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **Sei creativo?** Come trovare il creativo **più simile a te**?
  1. decidere lo **spazio di input**;
  2. decidere la **metrica** (distanza);
  3. **scorrere** i candidati per trovare il creativo più vicino.

  Così si trova il **primo vicino più prossimo** (*first nearest neighbour*), un metodo tipico di ML (Lezione 9). **L’allineamento qui è critico**: si provi a pensare questo grafico senza.

<!-- SOURCE_VISUAL id="IS11-V79" source="L11-web-v24.pdf" page="112" type="figura" description="Routine quotidiane di creativi famosi allineate su un asse temporale" reason="Illustrazione visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.3. Punti principali**

> ✅ **Punti principali della lezione.**
> - Monitorare il **drift con il PSI** (sotto 0,1 stabile, da 0,1 a 0,25 moderato, sopra 0,25 significativo) **sulle feature e sul punteggio di uscita**.
> - **Visualizzazione classica**: scegliere la **variabile visiva** (la **lunghezza allineata** è la più precisa) e il **grafico a partire dal messaggio**; **mostrare l’incertezza**.
> - In **3D** preferire **superfici, fette e proiezioni**; **un concetto in un’immagine potente**.
> - **Visualizzare l’AI**: **dati** (griglie, mappe del dataset), **modello** (pesi, attivazioni, salienza), **prestazioni** (curve, confusione, calibrazione, slice).
> - **Proiezioni, salienza e attenzione sono diagnostiche, non prove.**
> - Un **agente scrive grafici in pochi secondi**, ma **il progettista possiede il messaggio, i gruppi e le verifiche** (Simpson).

---

### **11. Appendice: dettagli della visualizzazione**


EDA in Python, dashboard, strumenti per i grafici, 3D con il codice, altre viste IS4.

#### **11.1. Una pipeline di dati come grafo (DAG)**

![](imgs/Pasted%20image%2020261123212647.png)

- **Input**: dati grezzi dei sensori e i passi **ingest** (acquisizione), **clean** (valori mancanti), **feature** (finestre), **train** (modello), **validate** (test set), **deploy** (servizio).
- **Output**: le **dipendenze** tra i passi (**grafo aciclico diretto**, DAG, *Directed Acyclic Graph*).

<!-- SOURCE_VISUAL id="IS11-V80" source="L11-web-v24.pdf" page="115" type="schema" description="DAG della pipeline: ingest, clean, feature, train, validate, deploy" reason="Grafo delle dipendenze visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **Perché in IS4**: le pipeline devono essere **modulari e riproducibili**; **l’ordine decide che cosa il modello può vedere**.
- **Errore tipico**: **data leakage**: normalizzare o costruire feature **sull’intero dataset prima della divisione** train/test (Lezioni 7 e 10).

#### **11.2. Spazio e tempo: mappe di PM2.5**

![](imgs/Pasted%20image%2020261123212658.png)

- **Input**: griglia $80\times 80$ su $10\times 10$ km, PM2.5 (µg/m³) alle **06:00**, **12:00** e **18:00**.
- **Output**: **tre mappe con la stessa scala di colore**.

<!-- SOURCE_VISUAL id="IS11-V81" source="L11-web-v24.pdf" page="116" type="grafico" description="Tre mappe di PM2.5 alle 06, 12 e 18 con la stessa scala di colore" reason="Mappe visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **Perché in IS4**: le reti di sensori **ambientali e logistiche** producono dati con **coordinate**: bisogna scegliere **scala, interpolazione e istanti**.
- **Errore tipico**: **scale di colore diverse tra le mappe**; **interpolazione che inventa valori** dove non ci sono sensori.

#### **11.3. Controllare le bande: la copertura dell’intervallo di previsione**

![](imgs/Pasted%20image%2020261123212715.png)

Nelle settimane di test $t$ i punti sono i valori reali $y_t$, la banda è $[L_t,U_t]$. La copertura è

$$\text{PICP}=\frac{1}{N}\sum_{t=1}^{N}\mathbf{1}\big[y_t\in[L_t,U_t]\big],$$

dove $N$ è il numero di punti di test (per esempio 100 settimane), $y_t$ il valore reale al tempo $t$ (pezzi/settimana), $L_t$ e $U_t$ gli estremi inferiore e superiore dell’intervallo previsto, e $\mathbf{1}[\cdot]$ vale 1 se la condizione è vera e 0 altrimenti.

<!-- SOURCE_VISUAL id="IS11-V82" source="L11-web-v24.pdf" page="117" type="grafico" description="Schizzo di 10 settimane di test con banda di previsione e valori reali, 8 dentro la banda" reason="Punti e banda visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Esempio**: intervallo **nominale al 90%**, il valore reale cade dentro **82 volte su 100** settimane: $\text{PICP}=0{,}82$. Il modello è **troppo sicuro di sé** (**sottocopertura**): **allargare le bande o ricalibrare**. Nello schizzo, **8 settimane su 10** sono dentro: $\text{PICP}=0{,}8$.

> 💡 **PICP (nota didattica).** La sigla sta per *Prediction Interval Coverage Probability*, probabilità di copertura dell’intervallo di previsione. È per gli intervalli ciò che la calibrazione è per le probabilità di classe: un intervallo al 90% è onesto se contiene il valore vero circa il 90% delle volte. Una copertura troppo alta indica invece bande inutilmente larghe.

#### **11.4. Controllare la previsione: i residui**

![](imgs/Pasted%20image%2020261123212834.png)

- **Input**: gli errori di previsione delle **52 settimane** di test (pezzi).
- **Output**: tre pannelli: **errore nel tempo**, **autocorrelazione** (ACF), **istogramma**.

<!-- SOURCE_VISUAL id="IS11-V83" source="L11-web-v24.pdf" page="118" type="grafico" description="Residui della previsione nel tempo, loro autocorrelazione ACF con limiti tratteggiati e istogramma" reason="Tre pannelli visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **Perché in IS4**: un **RMSE basso non dice che il modello sia adatto**: i residui mostrano **bias**, **stagionalità dimenticata**, **code pesanti**.
- **Errore tipico**: fermarsi alla **metrica riassuntiva**; **barre dell’ACF oltre le linee tratteggiate** indicano **struttura rimasta negli errori**.

> 💡 **Che cosa dice l’ACF (nota didattica).** L’autocorrelazione al lag $k$ è la correlazione tra l’errore della settimana $t$ e quello della settimana $t-k$. Se il modello ha catturato tutta la struttura, i residui sono rumore e l’ACF resta entro le bande di confidenza (circa $\pm 2/\sqrt{N}$). Una barra alta al lag 52, per esempio, direbbe che il modello ha dimenticato la stagionalità annuale.

#### **11.5. Curve di training e validation, attribuzione delle feature, calibrazione**

![](imgs/Pasted%20image%2020261123213021.png)

![](imgs/Pasted%20image%2020261123213031.png)

![](imgs/Pasted%20image%2020261123213045.png)

**Curve di training e validation.**

- **Input**: la perdita di una rete a ogni epoca, **80 epoche**. **Output**: perdita di training e di validation; **miglior epoca 45**.
- **Perché in IS4**: **convergenza, overfitting e generalizzazione**.
- **Errore tipico**: leggere il **rumore della curva di validation come segnale**; **una sola esecuzione**.

<!-- SOURCE_VISUAL id="IS11-V84" source="L11-web-v24.pdf" page="119" type="grafico" description="Curve di perdita di training e validation su 80 epoche con miglior epoca 45" reason="Andamento delle curve visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Attribuzione delle feature (illustrativa).**

- **Input**: **5 variabili di processo** e il loro contributo a **una previsione** (valori illustrativi).
- **Output**: barre di contributi positivi e negativi: vibrazione RMS **+0,54**, temperatura **+0,37**, pressione **−0,21**, ore di utilizzo **+0,17**, velocità **−0,09** (unità arbitrarie).
- **Perché in IS4**: esamina le **ragioni apparenti** di una previsione e le **scorciatoie**.
- **Errore tipico**: **non sono valori SHAP reali**: per un esperimento vero si applica un metodo di attribuzione a un modello addestrato.

<!-- SOURCE_VISUAL id="IS11-V85" source="L11-web-v24.pdf" page="120" type="grafico" description="Barre orizzontali dei contributi illustrativi di 5 variabili a una previsione" reason="Barre visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Diagramma di calibrazione.**

- **Input**: confidenza prevista e frequenza osservata, un modello. **Output**: curva di calibrazione ed **ECE = 0,123**: il modello è **sovraconfidente**.
- **Perché in IS4**: una **probabilità di guasto dichiarata** deve essere **utilizzabile nelle decisioni**.
- **Errore tipico**: **confondere accuratezza e calibrazione**.

<!-- SOURCE_VISUAL id="IS11-V86" source="L11-web-v24.pdf" page="121" type="grafico" description="Diagramma di calibrazione di un modello sovraconfidente con ECE 0.123" reason="Curva rispetto alla diagonale visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.6. Dashboard di drift, matrice di confusione e ROC, salienza sintetica**

![](imgs/Pasted%20image%2020261123213053.png)

![](imgs/Pasted%20image%2020261123213102.png)

![](imgs/Pasted%20image%2020261123213118.png)

**Dashboard di drift e monitoraggio.**

- **Input**: **180 giorni sintetici**, PSI e punteggio F1. **Output**: PSI e F1 **prima e dopo il cambiamento dei dati al giorno 105**.
- **Perché in IS4**: l’AI **si degrada in produzione**: monitoraggio, allarmi, procedure di aggiornamento.
- **Errore tipico**: guardare **solo l’accuratezza** e non gli input.

<!-- SOURCE_VISUAL id="IS11-V87" source="L11-web-v24.pdf" page="122" type="grafico" description="PSI e F1 su 180 giorni con il cambiamento dei dati al giorno 105" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Matrice di confusione e curva ROC.**

| | Previsto: nessun guasto | Previsto: guasto |
|---|---:|---:|
| **vero: nessun guasto** | 769 | 31 |
| **vero: guasto** | 29 | 171 |

- **Input**: **1000 esempi sintetici** con classe vera e prevista. **Output**: conteggi per cella e curva ROC (**AUC 0,98**).
- **Perché in IS4**: **quanti falsi allarmi e guasti mancati sono accettabili**.
- **Errore tipico**: **scegliere la soglia senza i costi**.

<!-- SOURCE_VISUAL id="IS11-V88" source="L11-web-v24.pdf" page="123" type="grafico" description="Matrice di confusione guasto/non guasto (769, 31, 29, 171) e curva ROC con AUC 0.98" reason="Matrice e curva visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Le metriche della matrice (nota didattica).** Accuratezza $(769+171)/1000=94\%$; recall $171/(171+29)=85{,}5\%$; precisione $171/(171+31)=84{,}7\%$; FPR $31/800=3{,}9\%$. Un AUC di 0,98 convive con 29 guasti mancati: l’AUC riassume tutte le soglie, la matrice ne fotografa una sola.

**Mappa di salienza su un’immagine sintetica.**

- **Input**: un’immagine sintetica $64\times 64$ con un **graffio**; una mappa di importanza $64\times 64$. **Output**: una **sovrapposizione** che mostra **dove guarda il modello** (tre pannelli: superficie, mappa di salienza, sovrapposizione).
- **Perché in IS4**: nel **controllo qualità visivo** il modello deve guardare il **difetto**, non il **bordo** o la **luce**.
- **Errore tipico**: è **illustrativa**: la mappa è **costruita a mano**, non estratta da una rete addestrata; fidarsi solo delle mappe che superano i **test di sanità** (*sanity checks*).

<!-- SOURCE_VISUAL id="IS11-V89" source="L11-web-v24.pdf" page="124" type="figura" description="Superficie sintetica con graffio, mappa di salienza e sovrapposizione" reason="Tre immagini visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.7. Frontiera di Pareto logistica, coordinate parallele e curve di apprendimento**

![](imgs/Pasted%20image%2020261123213144.png)

![](imgs/Pasted%20image%2020261123213220.png)

**Frontiera di Pareto di 120 piani logistici.**

- **Input**: 120 piani con **costo** (k€/mese), **emissioni** (t CO₂/mese) e **livello di servizio** (%). **Output**: i piani **non dominati** (la linea); il **colore** è il terzo obiettivo.
- **Perché in IS4**: il **formato naturale** per decisioni con **obiettivi in conflitto**.
- **Errore tipico**: qui la frontiera usa **solo due obiettivi**; la **scelta finale va motivata**.

<!-- SOURCE_VISUAL id="IS11-V90" source="L11-web-v24.pdf" page="125" type="grafico" description="Frontiera di Pareto costo-emissioni di 120 piani logistici, colore = livello di servizio" reason="Punti e frontiera visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Coordinate parallele e curve di apprendimento.**

- **Input**: **40 esperimenti** con **3 iperparametri** ($\log_{10}$ del learning rate, profondità, $\log_2$ della dimensione del batch) e un punteggio di validation; **5 addestramenti con semi diversi**, 60 epoche.
- **Output**: le **regioni efficaci** della configurazione; curve di training e validation.
- **Perché in IS4**: regolare un modello in modo **documentabile**, con **più semi**.
- **Errore tipico**: mostrare **un solo addestramento**; **pochi tentativi** rendono le regioni **inaffidabili**.

<!-- SOURCE_VISUAL id="IS11-V91" source="L11-web-v24.pdf" page="126" type="grafico" description="Coordinate parallele di 40 esperimenti su 3 iperparametri e curve di apprendimento di 5 semi" reason="Linee e curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Come si leggono le coordinate parallele (nota didattica).** Ogni esperimento è una linea spezzata che attraversa assi verticali paralleli, uno per iperparametro più quello del punteggio. Colorando le linee per punteggio si vede quali combinazioni di valori portano ai risultati migliori: è un modo per rappresentare in 2D punti di uno spazio a 4 dimensioni senza proiettarli.

#### **11.8. Galleria: penny, immagine, fusione di metodi, confidenza, strumenti**

![](imgs/Pasted%20image%2020261123213246.png)

![](imgs/Pasted%20image%2020261123213259.png)

![](imgs/Pasted%20image%2020261123213337.png)

![](imgs/Pasted%20image%2020261123213427.png)

![](imgs/Pasted%20image%2020261123213446.png)

![](imgs/Pasted%20image%2020261123213459.png)

![](imgs/Pasted%20image%2020261123213525.png)

![](imgs/Pasted%20image%2020261123213548.png)

![](imgs/Pasted%20image%2020261123213557.png)

![](imgs/Pasted%20image%2020261123213606.png)

![](imgs/Pasted%20image%2020261123213620.png)

![](imgs/Pasted%20image%2020261123213705.png)

- **La moneta 3D scansionata**: i dispositivi di imaging 3D producono una **matrice di altezze**, un numero $z$ per pixel $(x,y)$ (il codice è nel §3.7).

<!-- SOURCE_VISUAL id="IS11-V92" source="L11-web-v24.pdf" page="127" type="figura" description="Moneta scansionata in 3D come matrice di altezze" reason="Immagine visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **La visualizzazione nel ML** serve per: dati di training, prestazioni del modello, interpretabilità e ispezione del modello, dati ad alta dimensionalità, didattica e comunicazione. Ora si rivede come gestire dati strutturati e immagini.
- **L’immagine** (Bertin): il concetto chiave di Bertin, da cui la teoria prende il nome, è l’**immagine**. Semplificando, un’immagine è l’**unità percettiva fondamentale** di una visualizzazione. Una visualizzazione **ideale contiene una sola immagine**, per ottimizzarne l’«**efficienza**»: la velocità con cui l’osservatore estrae l’informazione. **Dove si sta perdendo energia? Basta un’occhiata.**

<!-- SOURCE_VISUAL id="IS11-V93" source="L11-web-v24.pdf" page="129" type="figura" description="Esempio di immagine unica efficiente: dove si sta perdendo energia" reason="Illustrazione visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **Una fusione di metodi per le proprie applicazioni**: la visualizzazione dell’informazione è una fusione importante di **grafica**, **visualizzazione scientifica**, **gestione dei database** e **interazione uomo-macchina**. Nelle proprie applicazioni va usata per comunicare **risultati complessi come immagini comprensibili**, migliorando l’**HMI** (*Human-Machine Interface*, interfaccia uomo-macchina).
- **Confidenza**: la banda è l’**intervallo di confidenza al 95%**: **stretta dove la popolazione è uniforme**, **larga dove non lo è**.

<!-- SOURCE_VISUAL id="IS11-V94" source="L11-web-v24.pdf" page="131" type="grafico" description="Curva con banda di confidenza al 95% stretta dove la popolazione è uniforme e larga dove non lo è" reason="Banda visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **Annotazioni, mappe di colore e limiti** (galleria MATLAB, it.mathworks.com/products/matlab/plot-gallery.html).

<!-- SOURCE_VISUAL id="IS11-V95" source="L11-web-v24.pdf" page="132" type="figura" description="Esempi della galleria MATLAB con annotazioni, mappe di colore e limiti" reason="Esempi visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **La struttura di un grafico molto semplice ma completo**: ogni elemento ha un **nome** e uno **scopo**: **figura**, **assi**, **titolo**, **etichette**, **tacche**, **griglia**, **legenda**, **marcatori**, **linee** e **bordi** (*spines*).

<!-- SOURCE_VISUAL id="IS11-V96" source="L11-web-v24.pdf" page="133" type="grafico" description="Anatomia di un grafico con tutti gli elementi etichettati" reason="Elementi etichettati visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **Grafico nel grafico, vettori in linee 3D** (galleria MATLAB).

<!-- SOURCE_VISUAL id="IS11-V97" source="L11-web-v24.pdf" page="134" type="figura" description="Grafico nel grafico e vettori 3D dalla galleria MATLAB" reason="Esempi visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **Strumenti interattivi online: observablehq.com**, con la sua galleria di visualizzazioni interattive, e altri esempi di visualizzazione interattiva.

<!-- SOURCE_VISUAL id="IS11-V98" source="L11-web-v24.pdf" page="135" type="schermata" description="Galleria di visualizzazioni interattive di observablehq.com" reason="Schermata visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V99" source="L11-web-v24.pdf" page="136" type="figura" description="Tre esempi di visualizzazione interattiva" reason="Esempi visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Grafici belli, dati sbagliati.** La potenza grafica di grafici e diagrammi è **inutile** se i dati:
> - **non sono rappresentativi**;
> - sono **corrotti**;
> - **non sono pre-elaborati correttamente**;
> - sono **falsi** (per esempio per una data augmentation **eccessiva o sbagliata**).
>
> Prima del grafico vanno verificate le quattro domande: rappresentativi? non corrotti? pre-elaborati correttamente? genuini?

- **La libreria ggplot2 di R.** **R** è un linguaggio di programmazione e un ambiente software **libero** per il **calcolo statistico** e la **grafica**, sostenuto dalla R Foundation for Statistical Computing; ggplot2 implementa la «grammatica della grafica» (dati, estetiche, geometrie, livelli).

<!-- SOURCE_VISUAL id="IS11-V100" source="L11-web-v24.pdf" page="139" type="figura" description="Esempi di grafici realizzati con ggplot2 in R" reason="Esempi visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.9. Grafici 3D con il codice MATLAB**

##### **11.9.1. Istogramma 3D a barre**

<!-- SOURCE_VISUAL id="IS11-V101" source="L11-web-v24.pdf" page="140" type="grafico" description="Istogramma bivariato 3D delle corse del bike sharing di Boston per durata e anno di nascita" reason="Grafico 3D visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123213733.png)

```matlab
histogram2(rideData.Duration, rideData.birth_date, 'BinWidth',[2 2])
xlabel('Length of Ride')
ylabel('Birth Year')
zlabel('Number of Rides')
title('Ride counts based on ride length and the age of the rider')
view(17,30)
```

- **Input**: durata della corsa [min] e anno di nascita del ciclista, dal programma di **bike sharing di Boston**.
- **Output**: **istogramma bivariato**, bin $2\times 2$, con il **numero di corse sull’asse $z$**. **Non è specifico di MATLAB**: ogni libreria offre l’equivalente. `view(17,30)` imposta azimut e elevazione della vista.

##### **11.9.2. Trovare il massimo di una funzione 2D**

<!-- SOURCE_VISUAL id="IS11-V102" source="L11-web-v24.pdf" page="142" type="grafico" description="Superficie peaks con il massimo marcato da una stella verde" reason="Superficie e punto visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123213818.png)

```matlab
Z = peaks;                              % superficie curva e liscia
surf(Z); xlabel('x'); ylabel('y'); zlabel('z');
Zmax = max(max(Z))
[ymax, xmax] = find(Z == Zmax)
hold on;
plot3(xmax, ymax, Zmax, 'hg', 'Markersize', 15);
hold off;
```

- **Compito**: disegnare `peaks` e **localizzarne il massimo**.
- **Input**: la matrice `Z` della superficie. **Output**: `Zmax` e la posizione `(xmax, ymax)` marcata sul grafico (`'hg'` = esagramma verde).

> 📌 **Apprendere significa ottimizzare**: trovare un **minimo** o un **massimo** di una funzione (la perdita, Lezione 4).

> 💡 **Righe e colonne (nota didattica).** `max(max(Z))` prende prima il massimo di ogni colonna, poi il massimo di quei massimi. `find(Z == Zmax)` con due uscite restituisce **riga** e **colonna**: la riga corrisponde all’asse $y$, la colonna all’asse $x$. Per questo il codice scrive `[ymax, xmax]` e poi disegna `plot3(xmax, ymax, ...)`: è lo stesso scambio di convenzioni (riga, colonna) contro $(x,y)$ visto nella Lezione 9.

##### **11.9.3. Vettori in 3D**

<!-- SOURCE_VISUAL id="IS11-V103" source="L11-web-v24.pdf" page="144" type="grafico" description="Superficie z = x exp(-x^2-y^2) con i vettori normali in ogni punto della griglia" reason="Superficie e frecce visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123213902.png)

```matlab
[x,y] = meshgrid(-2:.2:2, -1:.15:1);
z = x .* exp(-x.^2 - y.^2);
[u,v,w] = surfnorm(x,y,z);
quiver3(x,y,z,u,v,w);
hold on, surf(x,y,z), hold off
```

Si disegnano i **vettori normali** di $z=x\,e^{-x^2-y^2}$.

- **Input**: griglia $x\in[-2,2]$ (passo 0,2), $y\in[-1,1]$ (passo 0,15).
- **Output**: la superficie e, in ogni punto della griglia, una **freccia normale** a essa. Mostra una **proprietà complessa di un singolo punto**.

##### **11.9.4. Mostrare una proprietà in 3D**

<!-- SOURCE_VISUAL id="IS11-V104" source="L11-web-v24.pdf" page="146" type="grafico" description="Superficie z = x exp(-x^2-y^2) colorata con la pendenza e barra dei colori" reason="Colorazione visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123213923.png)

```matlab
[x,y] = meshgrid([-2:.1:2]);
Z = x.*exp(-x.^2-y.^2);
mesh(x,y,Z,abs(gradient(Z)))
xlabel('x'); ylabel('y'); zlabel('z');
colorbar; colormap jet
```

Si mappa con una **tavolozza adatta** il **gradiente** di $z=x\,e^{-x^2-y^2}$.

- **Input**: griglia $[-2,2]$ con passo 0,1. **Output**: la superficie **colorata secondo $|\nabla z|$**: il **colore è la pendenza**.

> ⚠️ **Quale gradiente? (nota didattica).** Con una sola uscita, `gradient(Z)` restituisce solo la derivata lungo $x$, quindi il codice colora con $\left|\partial z/\partial x\right|$, non con il modulo del gradiente dichiarato dalla slide. Per colorare con $\lVert\nabla z\rVert=\sqrt{(\partial z/\partial x)^2+(\partial z/\partial y)^2}$ servono entrambe le componenti: `[gx, gy] = gradient(Z); mesh(x, y, Z, sqrt(gx.^2 + gy.^2))`. Inoltre `gradient` usa un passo unitario se non gli si passa la spaziatura della griglia (qui 0,1): i valori assoluti sono scalati, mentre la forma della colorazione resta corretta.

##### **11.9.5. Ispezionare superfici di decisione aggiungendo le luci**

<!-- SOURCE_VISUAL id="IS11-V105" source="L11-web-v24.pdf" page="148" type="grafico" description="Superficie sin(r)/r illuminata con luce e riflessi Phong" reason="Effetto dell'illuminazione visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123214019.png)

```matlab
ezsurf('sin(sqrt(x^2+y^2))/sqrt(x^2+y^2)',[-6*pi,6*pi])
view(0,75); shading interp; lightangle(-45,30)
set(findobj(gca,'type','surface'), ...
    'FaceLighting','phong', 'AmbientStrength',.3, ...
    'DiffuseStrength',.8, 'SpecularStrength',.9, ...
    'SpecularExponent',25, 'BackFaceLighting','unlit')
```

- **Input**: una funzione simbolica e il suo dominio, qui $\sin\!\big(\sqrt{x^2+y^2}\big)/\sqrt{x^2+y^2}$ su $[-6\pi,6\pi]$.
- **Output**: la superficie con **ombreggiatura interpolata** e una **luce**: il **rilievo è più facile da leggere**.

> 💡 **Il modello di Phong e una funzione datata (nota didattica).** L’illuminazione di Phong somma tre componenti: **ambientale** (luce uniforme, qui 0,3), **diffusa** (dipende dall’angolo tra la superficie e la luce, 0,8) e **speculare** (il riflesso lucido, 0,9, con esponente 25 che ne regola la nitidezza). Nelle versioni recenti di MATLAB `ezsurf` è sostituita da `fsurf`, con sintassi a funzione anonima: `fsurf(@(x,y) sin(sqrt(x.^2+y.^2))./sqrt(x.^2+y.^2), [-6*pi 6*pi])`.

##### **11.9.6. Dati 3D: vettori di velocità in un getto di fluido**

<!-- SOURCE_VISUAL id="IS11-V106" source="L11-web-v24.pdf" page="150" type="grafico" description="Tre fette ortogonali del volume flow colorate per velocità, in prospettiva con luce" reason="Fette del volume visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123214055.png)

```matlab
[x,y,z,v] = flow;                                   % dati di volume
xmax = max(x(:)); ymax = max(y(:)); zmin = min(z(:));
hx = slice(x,y,z,v,xmax,[],[]);                     % fetta x = xmax
hy = slice(x,y,z,v,[],ymax,[]);                     % fetta y = ymax
hz = slice(x,y,z,v,[],[],zmin);                     % fetta z = zmin
set([hx hy hz],'FaceColor','interp','EdgeColor','none')
view(-38.5,16); camproj perspective
lightangle(-45,45); colormap(jet(24))
```

**Velocità in un getto di fluido** nell’acqua (senza pareti). **Input**: i dati di volume `[x,y,z,v] = flow`. **Output**: **tre fette ortogonali** del volume colorate per velocità. Sono mostrate solo le righe chiave; il codice completo sarà pubblicato sul sito del corso.

##### **11.9.7. Superfici 3D: la risonanza magnetica**

```matlab
load mri; D = squeeze(D);                          % D: 128x128x27
subplot(1,2,1);
contourslice(D,[],[],[1,12,19,27],8); view(3); axis tight
subplot(1,2,2);
Ds = smooth3(D);
patch(isosurface(Ds,5),'FaceColor',[1,.75,.65],'EdgeColor','none');
patch(isocaps(D,5),'FaceColor','interp','EdgeColor','none');
view(45,30); daspect([1,1,.4]); lightangle(45,30); lighting phong
```

![](imgs/Pasted%20image%2020261123214109.png)

| Riga | Ruolo |
|---|---|
| `squeeze(D)` | elimina la dimensione unitaria: `D` diventa un volume $128\times 128\times 27$ voxel |
| `contourslice(D,[],[],[1,12,19,27],8)` | **curve di livello** (8 livelli) su quattro fette orizzontali |
| `smooth3` | leviga il volume prima di estrarre la superficie |
| `isosurface(Ds,5)` | **isosuperficie** al livello 5 |
| `isocaps(D,5)` | le «coperture» che chiudono la superficie dove il volume è tagliato |
| `daspect([1,1,.4])` | proporzioni: le fette sono più distanziate dei pixel |

**Input**: la matrice 3D `D` di una MRI, $128\times 128\times 27$ voxel. **Output**: a sinistra **quattro fette di contorno**; a destra un’**isosuperficie con coperture al livello 5**. Sono mostrate solo le righe chiave; il codice completo sarà pubblicato sul sito del corso.

#### **11.10. Mapper, la topologia dei dati (TDA)**

- **Nome**: **Mapper** (Singh, Mémoli, Carlsson 2007), versione semplice **Ball Mapper** (Dłotko 2019), uno strumento di **analisi topologica dei dati** (TDA, *Topological Data Analysis*): **sfere sovrapposte coprono i punti**, **un nodo per sfera**, si **collegano le sfere che condividono punti**, si **colora per una grandezza**.
- **Chi lo usa**: soprattutto la **ricerca**. Un sottogruppo di **tumore al seno** con sopravvivenza eccellente trovato su dati genici (Nicolau et al., *PNAS* 2011); i **ruoli dei giocatori** nel basket (Lum et al., *Sci. Rep.* 2013); la **forma delle attivazioni** delle reti neurali (Gabrielsson, Carlsson 2019). Open source: KeplerMapper, giotto-tda; l’azienda **Ayasdi** è stata costruita su di esso. **Non è uno strumento standard** delle pipeline di ML.
- **La nostra rete delle cifre**: 397 immagini → **26 nodi** (dimensione: immagini; colore: tasso di errore; etichetta: cifra di maggioranza).
- **Il nostro test**: su questa piccola rete **non è più chiaro della matrice di confusione**; servono dati più grandi e più strutturati.
- **Perché in appendice**: è una **tecnica di frontiera**; l’esame copre i concetti fondamentali della lezione.

<!-- SOURCE_VISUAL id="IS11-V107" source="L11-web-v24.pdf" page="153" type="grafico" description="Grafo Ball Mapper delle 397 immagini di test in 26 nodi colorati per tasso di errore" reason="Grafo visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123214129.png)

**La figura originale di Sankey**, con le sue molte biforcazioni, chiude l’appendice.

<!-- SOURCE_VISUAL id="IS11-V108" source="L11-web-v24.pdf" page="154" type="figura" description="La figura originale di Sankey con molte biforcazioni" reason="Figura storica visibile solo nell'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123214142.png)

> 💡 **Chi era Sankey (nota didattica).** Il diagramma prende il nome dal capitano irlandese Matthew H. P. R. Sankey, che nel 1898 lo usò per rappresentare il bilancio energetico di una macchina a vapore: la larghezza delle frecce era proporzionale ai flussi di energia, e si vedeva a colpo d’occhio dove l’energia andava perduta.

---

### **12. Approfondimento — Materiale dell’edizione precedente**

L’edizione precedente conteneva alcuni contenuti che le slide 2026/27 hanno sostituito o compresso; restano utili come complemento.

#### **12.1. EDA in Python con pandas**

Un esempio in **Google Colab** con **pandas** per rivedere le tecniche dell’EDA:

| Passo | Comando | A che cosa serve |
|---|---|---|
| importare i dati e selezionare le feature | `pd.read_csv(...)`, selezione delle colonne | caricare solo le colonne utili |
| esplorazione 1 | `.head()` | vedere le prime righe |
| esplorazione 2 | `.shape`, `.describe()` | dimensioni e statistiche descrittive per colonna |
| esplorazione 3 | `.info()` | tipi delle colonne e valori non nulli |
| esplorazione 4 | `.isnull()` | individuare i valori mancanti |
| esplorazione 5 | `plt.hist(...)` | istogrammi delle distribuzioni |
| grafici | scatter plot delle feature | relazioni e raggruppamenti (il cluster del §1.4) |

> 💡 **Un idioma utile.** `dataset.isnull().sum()` restituisce, per ogni colonna, quanti valori mancano. Il controllo di `.describe()` serve anche a scovare intervalli impossibili, come un’età minima negativa. La funzione `first_eda` della Lezione 10 (§8.3) raccoglie questi controlli in un’unica funzione.

#### **12.2. Altri strumenti di visual analytics**

- **Visdom** (Meta AI): genera visualizzazioni di **dati in tempo reale** per seguire esperimenti eseguiti su **server remoti**; si vedono nel browser e si condividono facilmente.
- **DQNViz**: un approccio di *visual analytics* per capire le **Deep Q-Networks** (reti Q profonde, modelli di apprendimento per rinforzo profondo). L’esempio mostrava anche il rischio di visualizzazioni **troppo dense**, che contraddicono il principio dell’«immagine unica» di Bertin.

---

### **13. Distinzioni da non confondere**


| Coppia | Differenza chiave |
|---|---|
| **EDA prima del ML** / **dopo il ML** | capire i dati prima di addestrare / valutare, interpretare e monitorare il modello |
| **PCA** / **t-SNE, UMAP** | assi con significato, lineare / solo vicinati, distanze non affidabili |
| **perplexity bassa** / **alta** | molti piccoli gruppi locali / gruppi fusi, vista più globale |
| **cluster** / **causa** | ipotesi di segmento / effetto dimostrato con metà casuale di controllo |
| **lunghezza allineata** / **tinta** | indizio più preciso / indizio più generico, adatto a poche categorie |
| **data drift** / **concept drift** | cambia $P(X)$ / cambia $P(Y\mid X)$ |
| **feature drift** / **prediction drift** | PSI sugli input / PSI sul punteggio di uscita, senza etichette |
| **PSI < 0,1** / **0,1–0,25** / **≥ 0,25** | stabile / moderato / significativo (regola pratica) |
| **$p_i$** / **$q_i$** | quota di riferimento (training) / quota attuale (produzione) |
| **monitoraggio post-commercializzazione** / **obblighi GPAI** | obbligo dei sistemi ad alto rischio / documentazione e, se a rischio sistemico, valutazioni e incidenti |
| **salienza, attenzione, proiezioni** / **prove** | diagnostiche / spiegazioni definitive (che non sono) |
| **NN1 (0,95, pixel sbagliati)** / **NN2 (0,92, il cane)** | accurata per la ragione sbagliata / da preferire |
| **bacino piatto** / **bacino stretto** | soluzione robusta, generalizza meglio / soluzione fragile |
| **conf alta, std bassa** / **conf bassa** nella mappa del dataset | esempi facili / spesso etichette sbagliate |
| **accuratezza** / **calibrazione** | quante risposte giuste / la confidenza dichiarata corrisponde alla frequenza osservata |
| **ROC** / **precision-recall** | FPR sui negativi, ottimista con classi rare / precisione degli allarmi, più severa |
| **sotto la diagonale** / **sopra** nel diagramma di affidabilità | sovraconfidente / sottoconfidente |
| **permutation importance** / **SHAP** | calo di accuratezza senza segno / contributo con segno |
| **ECE** / **PICP** | calibrazione delle probabilità di classe / copertura degli intervalli di previsione |
| **carta di controllo $\pm 3\sigma$** / **soglia di costo** | regola statistica con ipotesi di gaussianità e indipendenza / scelta economica |
| **dominato** / **non dominato** | esiste un’alternativa migliore su tutti gli assi / sta sulla frontiera di Pareto |
| **istruzioni del fornitore** / **linee guida del progettista** | nascoste / sotto il nostro controllo |
| **traccia dell’agente** / **correttezza** | che cosa ha fatto / se aveva ragione (non lo dice) |
| **vista aggregata** / **vista per tipo** | B migliore, nasconde il mix / A migliore in ogni gruppo, nasconde la numerosità |
| **`surf`** / **`mesh`** / **`pcolor`** / **`contour`** | superficie colorata / reticolo / mappa dall’alto / curve di livello |
| **grafico 3D pieno** / **fette e proiezioni** | voxel che si coprono / parti interne visibili |
