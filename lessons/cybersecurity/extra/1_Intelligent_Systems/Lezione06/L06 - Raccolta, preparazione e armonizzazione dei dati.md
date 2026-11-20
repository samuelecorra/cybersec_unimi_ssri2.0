## **Lezione 6: Raccolta, preparazione e armonizzazione dei dati**

Ogni sistema intelligente parte dai **dati**: occorre raccoglierli, stimarne i volumi, armonizzare formati e unità, sincronizzare gli orologi e trattare errori, valori mancanti e fonti non strutturate. Il principio **GIGO** (*garbage in, garbage out*) ricorda che la qualità del modello dipende dalla qualità delle informazioni e delle trasformazioni a monte. Agenti, LLM e database vettoriali possono sostenere la preparazione, purché i risultati siano verificati.


### **0. Panoramica**


La trattazione si articola nei seguenti temi:

1. **raccolta dei dati**: da dove arrivano? quanti ne arrivano?
2. **preparazione dei dati**: controllo dei dati, dati non strutturati, armonizzazione, analisi, filtraggio;
3. **LLM e agenti** nella preparazione dei dati;
4. punti principali.

---


### **1. Simulazione d’esame breve**


La simulazione serve a **verificare la preparazione**, **non a studiare**.

> 📌 **Regole di questa simulazione**.
> - **1 risposta corretta** → peso **1**;
> - **N risposte sbagliate** → peso **0**;
> - **nessun peso negativo**.
>
> Consigli: non ci sono penalità se non si risponde, **ma è meglio rispondere**; **escludere le risposte sbagliate** invece di cercare quella giusta; in caso di dubbio scegliere la risposta che **sembra meno sbagliata**.

> ⚠️ **Pesi delle risposte.** Questa simulazione breve non assegna pesi negativi; una simulazione precedente ammetteva una penalità per risposte particolarmente inappropriate. Le regole effettive dipendono dalle istruzioni dell’esame.

| Domanda | Opzioni | Risposta e commento |
|---|---|---|
| **D1**. Quali fasi descrivono un progetto di machine learning? | 1. rappresentazione; 2. rappresentazione e valutazione; 3. rappresentazione, valutazione e ottimizzazione; 4. nessuna delle altre | **3** |
| **D2**. Il clustering richiede necessariamente dati etichettati? | 1. sì; 2. no | **2** |
| **D3**. In quali condizioni è ammissibile usare una soluzione a scatola nera? | 1. mai; 2. se rischio, validazione esterna, monitoraggio e recupero dagli errori sono adeguati; 3. sempre | **2** |
| **D4**. Un classificatore può ottimizzare accuratezza e margine? | 1. solo l’accuratezza; 2. solo il margine; 3. entrambi, se il metodo lo consente | **3** |
| **D5**. Su quale criterio si basa la regolamentazione europea dell’AI? | 1. elenco dei soli casi d’uso; 2. rischio dell’applicazione; 3. combinazione di elenco di pratiche e classificazione basata sul rischio; 4. nessuno dei precedenti | **3** |

> 💡 **Perché D5 è «entrambi».** L’AI Act classifica per **livello di rischio**, ma per definire i livelli usa **elenchi di casi d’uso**: le pratiche vietate e le categorie ad alto rischio (infrastrutture critiche, istruzione, lavoro, credito, forze dell’ordine, migrazione) sono elencate esplicitamente.

---


### **2. Il valore dell’analisi dei dati**


**Discussione aperta in aula**:

- quali sono le vostre **competenze di analisi dei dati**, e come le valutate?
- quali **esperienze** precedenti avete? quali **strumenti** preferite?
- usate strumenti di analisi dei dati per la **pre-elaborazione**, la **post-elaborazione**, la **comprensione dei risultati**?

Un’autovalutazione iniziale delle proprie competenze e degli strumenti usati può essere annotata e ripresa dopo aver studiato i metodi di analisi dei dati.

Secondo **Gartner**, l’analisi dei dati crea valore nelle diverse fasi decisionali; una valutazione personale delle competenze può collegare queste capacità alle opportunità professionali.

> 💡 **Il modello di Gartner (nota didattica).** Lo schema più noto di Gartner sull’analisi dei dati ha quattro livelli, con **valore** e **difficoltà** crescenti:
>
> | Livello | Domanda |
> |---|---|
> | **descrittiva** | che cosa è successo? |
> | **diagnostica** | perché è successo? |
> | **predittiva** | che cosa succederà? |
> | **prescrittiva** | che cosa bisogna fare perché succeda? |
>
> 

<!-- SOURCE_VISUAL id="IS06-V01" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="16" type="grafico" description="Valore dell’analisi dei dati secondo Gartner, con l’AI che aiuta in tutte le fasi" reason="Livelli e assi del grafico visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS06-V02" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="17" type="grafico" description="Valore dell’analisi dei dati secondo Gartner collegato allo stipendio atteso" reason="Annotazioni del grafico visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---


### **3. La raccolta dei dati**


#### **3.1. Il primo passo del flusso di lavoro**

La raccolta dei dati è il **passo 1** del flusso di lavoro del ML. **Nella pratica** i dati arrivano da:

- query classiche a **database**;
- elaborazione di **file**;
- **formati personalizzati**;
- **flussi di dati** da IoT e AIoT;
- **piattaforme online** (AWS, Azure, Google, …).

Le fonti capaci di generare dati sono moltissime e potenti; i dettagli tecnici di ciascuna richiedono procedure specifiche.

<!-- SOURCE_VISUAL id="IS06-V03" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="19" type="diagramma" description="Passo 1 del flusso di lavoro del ML: raccolta dei dati" reason="Posizione del passo nel flusso visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Raccolta dei dati**. Il processo di **raccogliere e misurare** informazioni su **variabili di interesse** in un sistema stabilito. Si usa nelle scienze fisiche, nel settore industriale, negli affari, nelle discipline umanistiche e nelle scienze sociali, in generale in tutte le discipline che studiano la società e la cultura umana. L’AI si usa in tutti questi settori, anche con dati **fortemente non strutturati** come il testo grezzo.

> ⚠️ **GIGO: garbage in, garbage out**. **Nessuna rete neurale «magica» vi salverà** da dati spazzatura.

<!-- SOURCE_VISUAL id="IS06-V04" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="22" type="illustrazione" description="GIGO: nessuna rete neurale magica salva da dati spazzatura" reason="Illustrazione della figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. Quanti dati: l’esplosione dell’IoT**

**Dati generati dai dispositivi IoT nel 2025**:

- **41,6 miliardi** di dispositivi connessi;
- **79,4 zettabyte** (ZB) di dati all’anno;
- fonti in crescita: **videosorveglianza**, dati della **casa intelligente**, **smartphone** (coordinate, sensori di salute, attività), dispositivi **indossabili** (smartwatch, braccialetti), **sensori**.

> 📌 **Zettabyte.** Il prefisso *zetta* indica la moltiplicazione per la **settima potenza di 1000**:
> $$1\ \text{ZB} = 1000^7\ \text{byte} = 10^{21}\ \text{byte}$$

La **videosorveglianza** produrrà una **quota rilevante** dei dati IoT, e il **5G** abilita nuovi dispositivi.

**Esempio: coordinate GPS degli smartphone in Italia**. Si assumono circa **59 milioni** di residenti (dato ISTAT al 1º gennaio 2026, arrotondato), una quota di **0,83** con cellulare, una posizione al minuto e due coordinate da 8 byte ciascuna.

$$D = 59 \times 10^6 \times 0{,}83 \times 365 \times 24 \times 60 \times 2 \times 8 \approx 4{,}12 \times 10^{14}\ \text{byte} \approx 412\ \text{TB/anno}$$

Distribuiti sui secondi di un anno, $365\cdot24\cdot3600$ s:

$$\frac{4{,}12 \times 10^{14}\ \text{byte}}{365\cdot24\cdot3600\ \text{s}} \approx 1{,}31 \times 10^{7}\ \text{byte/s} \approx 13\ \text{MB/s}$$

> ⚠️ **Ordine di grandezza e assunzioni.** Il valore dipende dal tasso di acquisizione, dalla popolazione e dalla rappresentazione numerica delle coordinate. Con le ipotesi date si ottengono circa **412 TB all’anno** e **13 MB/s** medi; il flusso reale non è costante né tutte le applicazioni memorizzano ogni posizione.

<!-- SOURCE_VISUAL id="IS06-V32" source="L06-web.pdf" page="11" type="calcolo" description="Stima del volume annuale e della velocità media dei dati GPS da smartphone in Italia" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS06-V05" source="L06-web.pdf" page="12" type="illustrazione" description="Big Data: serve il data mining" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Con tanti dati **serve il mining**: estrarre l’informazione utile.

#### **3.3. Dataset pubblici e strumenti**

**Dataset pubblici annotati**: la **fonte primaria** per l’addestramento, sotto forma di **raccolte di dataset**.

- UCI Machine Learning Repository;
- Kaggle Datasets;
- Amazon Datasets;
- Google Dataset Search;
- Microsoft Datasets;
- dataset **governativi**: portale Open Data dell’UE, dati del governo USA, della Nuova Zelanda, dell’India, …;
- dataset di **computer vision**;
- Lionbridge AI Datasets;
- … e l’elenco **cresce ogni giorno**.

**Strumento: MATLAB Database Explorer**. Permette di **collegarsi rapidamente a un database**, esplorarne i dati e **importarli** nel workspace di MATLAB in modo visuale. Funzioni utili:

- creare e configurare sorgenti dati **ODBC** e **JDBC**;
- aprire **più connessioni** allo stesso database o a database diversi;
- selezionare **tabelle e colonne** di interesse e rifinire la selezione con criteri di **query SQL**;
- **anteprima** dei dati selezionati e opzioni di importazione personalizzate;
- importare i dati nel workspace per l’analisi;
- **salvare le query SQL** generate e **generare codice MATLAB**.

---


### **4. Eterogeneità dei dati e armonizzazione**


#### **4.1. Formati, unità, intervalli**

Il tema della sezione: **unità, formati e tempi esatti**.

**Eterogeneità dei formati**. Dati raccolti da fonti diverse possono differire per:

- **formati**;
- **spazi dei nomi** (*name spaces*);
- **unità di misura**;
- **intervalli** di valori;
- **dimensioni**;
- …

> 📌 **Eterogeneità**. In statistica indica che popolazioni, campioni o risultati sono **diversi** tra loro; è l’opposto dell’**omogeneità**, in cui popolazione, dati o risultati sono **uguali**.

> 📌 **Armonizzazione dei dati (nota didattica).** È l’insieme delle operazioni che portano dati eterogenei a una rappresentazione **comune**: stesse unità, stessi formati, stesse convenzioni di riferimento, stessa scala temporale. Solo dopo l’armonizzazione i dati di fonti diverse si possono unire in un unico dataset.

**Caso d’uso: la sonda persa per un errore di unità**. Nel **settembre 1999**, dopo quasi 10 mesi di viaggio verso Marte, la **Mars Climate Orbiter** è bruciata nell’atmosfera marziana e si è disintegrata, perché qualcuno **non aveva usato le unità metriche**: i valori erano espressi in **libbre-forza per secondo** invece che nell’unità SI, **newton per secondo**. La sonda era costata in totale **325 milioni di dollari**.

> 💡 **Quanto pesa l’errore (nota didattica).** $1\ \text{lbf} \cdot \text{s} \approx 4{,}45\ \text{N} \cdot \text{s}$: gli impulsi erano **sottostimati di circa 4,45 volte**. La traiettoria calcolata portava la sonda molto più vicino al pianeta del previsto. Due gruppi di lavoro usavano due sistemi di unità senza che nessuna interfaccia li convertisse: è esattamente un problema di armonizzazione.

<!-- SOURCE_VISUAL id="IS06-V06" source="L06-web.pdf" page="17" type="immagine" description="Mars Climate Orbiter distrutta per l’uso di libbre-forza per secondo al posto di newton per secondo" reason="Immagine del caso d’uso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.2. Caso d’uso: previsione della produzione eolica**


**Il problema**: come modellare la **specificità** di un sito? Contano le **brezze** del mattino e del pomeriggio, le **colline** e le **occlusioni**.

**Fonti di dati diverse**:

- i dati delle **previsioni meteo** di **fornitori diversi** e i dati dei **sensori del parco eolico** possono usare **unità di misura** e **sistemi di riferimento** diversi;
- i dati di previsione **differiscono** da quelli della stazione di misura del sito, perché riferiti a **luoghi diversi**;
- la **griglia** dei dati meteo si trova a un’**altitudine diversa** rispetto all’impianto.

**Non idealità**:

- **colline e occlusioni** cambiano l’efficienza dell’impianto: per esempio con vento **da est** la potenza è **molto bassa** (effetto «**ombra**»), mentre altre conformazioni creano un effetto «**imbuto**»;
- le turbine hanno una **velocità minima** per iniziare a girare (**clipping**);
- **turbine uguali** hanno **curve velocità/potenza diverse** a causa delle condizioni circostanti.

<!-- SOURCE_VISUAL id="IS06-V07" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="31" type="mappa" description="Sito eolico con brezze, colline e occlusioni" reason="Conformazione del sito visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS06-V08" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="33" type="schema" description="Non idealità del parco eolico: effetto ombra da est, effetto imbuto, clipping e curve di potenza diverse" reason="Schema del sito e delle curve visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Obiettivo e armonizzazione**. Creare un **dataset di 5 anni** unendo fonti con formati, unità di misura e sistemi di riferimento diversi: i dati meteo sulla **griglia** e a varie **altitudini** servono a prevedere la **potenza** (MW) e un **indice di variabilità** sul sito dell’impianto. La trasformazione deve convergere verso una convenzione dichiarata, per esempio °C, m/s, gradi da nord in senso orario, hPa, copertura nuvolosa fra 0 e 1 e tempo UTC in ISO 8601.

| Grandezza | Unità comune |
|---|---|
| temperatura | **°C** |
| velocità del vento | **m/s** |
| direzione del vento | angolo **0–360°** misurato **da nord** (nord = 0°) |
| pressione atmosferica | **hPa** |
| copertura nuvolosa | scala **da 0 a 1** |

Le fonti possono codificare la direzione «da cui proviene» il vento oppure «verso cui soffia»; alcune forniscono direttamente le componenti $u$ e $v$. La stessa variabile fisica produce numeri diversi se cambiano origine dell’angolo, verso di rotazione o convenzione.

> 💡 **Due convenzioni per l’angolo del vento.** In meteorologia l’angolo indica **da dove proviene** il vento, in senso orario da nord. In matematica e in molti modelli numerici si usa l’angolo del **vettore** velocità, **verso dove** va, in senso antiorario dall’asse est. Per queste due convenzioni la conversione è
> $$\theta_{\text{met}} = \left(270^\circ - \theta_{\text{mat}}\right) \bmod 360^\circ$$
> Verifica: un vento che soffia verso est ($\theta_{\text{mat}} = 0^\circ$) proviene da ovest, cioè $\theta_{\text{met}} = 270^\circ$. La formula è valida solo dopo aver identificato con precisione entrambe le convenzioni.

Una tabella di armonizzazione esplicita anche le differenze fra fornitori: temperatura in K, °C o °F; velocità in m/s, km/h o nodi; pressione in Pa, hPa o inHg; copertura nuvolosa come frazione, percentuale od ottavi di cielo; quota di misura; orari locali, UTC istantaneo o intervalli con convenzione di inizio/fine. Una tabella di schema e dizionario dati evita che la trasformazione dipenda da supposizioni implicite.

| Grandezza | Conversione verso la convenzione comune | Esempio |
|---|---|---|
| Temperatura | $T_{°C}=T_K-273{,}15$; $T_{°C}=(T_{°F}-32)\cdot5/9$ | 293,15 K e 68 °F corrispondono a 20 °C |
| Velocità | $v_{m/s}=v_{km/h}/3{,}6$; $v_{m/s}=0{,}514444\,v_{kn}$ | 36 km/h = 10 m/s |
| Pressione | $p_{hPa}=p_{Pa}/100$; $p_{hPa}=33{,}8639\,p_{inHg}$ | 101 325 Pa = 1013,25 hPa |
| Nuvolosità | $c=C_{\%}/100=n_{okta}/8$ | 60% = 0,60 |
| Direzione | $\theta_{from}=(\theta_{to}+180^\circ)\bmod 360^\circ$ | vento diretto a est proviene da ovest |

Se si dispone delle componenti cartesiane, la velocità è $s=\sqrt{u^2+v^2}$; la conversione angolare dipende dalla definizione di $u$, $v$ e dalla convenzione scelta per «from» o «toward». La velocità misurata a un’altezza diversa dal mozzo può essere riportata a quota $h$ con un modello di profilo, ad esempio $v(h)=v_{ref}(h/h_{ref})^\alpha$, dichiarandone le ipotesi.

Un cambio di fornitore durante una serie storica può introdurre una discontinuità artificiale: nell’esempio, il valore medio passa da 6,9 m/s a 25,2 nella nuova fonte grezza e torna a 7,0 m/s dopo la conversione da km/h. Il modello non armonizzato rischia di imparare l’identità del fornitore invece delle condizioni meteorologiche. Conservare la provenienza e le unità originali, convertire all’ingestione, confrontare le fonti nel periodo di sovrapposizione e verificare i punti di cambiamento.

<!-- SOURCE_VISUAL id="IS06-V33" source="L06-web.pdf" page="24" type="tabella" description="Confronto tra unità, convenzioni e timestamp di tre sorgenti eolica e relativo schema armonizzato" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS06-V34" source="L06-web.pdf" page="25" type="tabella" description="Conversioni di temperatura, velocità, pressione, copertura nuvolosa, direzione e altezza del vento" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS06-V35" source="L06-web.pdf" page="26" type="grafico" description="Cambio fornitore e falsa discontinuità della velocità del vento prima e dopo l’armonizzazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Temperatura in °C e kelvin.** La conversione $T[\text{K}] = T[°\text{C}] + 273{,}15$ è banale, ma dimenticarla sposta tutti i valori di 273: un modello addestrato su una fonte e usato sull’altra produce previsioni prive di senso, senza alcun messaggio d’errore.

<!-- SOURCE_VISUAL id="IS06-V09" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="34" type="schema" description="Dataset di 5 anni per la previsione eolica: griglia meteo, altitudini, predizione di MW e variabilità, due definizioni dell’angolo del vento" reason="Griglia e definizioni degli angoli visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---


### **5. Sincronizzazione dei dati**


#### **5.1. Orologi che derivano**

> 📌 **Sincronizzazione**. Il modo in cui un dispositivo **regola il proprio orologio interno** per allinearsi agli orologi degli altri dispositivi di una rete. Nelle applicazioni **industriali e scientifiche** è una parte **essenziale**.

**Il problema**:

- gli orologi di server, workstation e dispositivi di rete **non sono abbastanza accurati**;
- vengono impostati **a mano**, con uno scarto di un minuto o due, e **raramente ricontrollati**;
- sono mantenuti da un dispositivo a batteria che può **derivare fino a un secondo al giorno**;
- quindi senza un metodo adeguato è **impossibile** avere una sincronizzazione accurata.

> 💡 **Quanto è un secondo al giorno (nota didattica).** In un mese sono 30 s di scarto. Due sensori che misurano lo stesso fenomeno con una deriva di 30 s producono, per esempio, un picco di vibrazione e un picco di temperatura che sembrano **non correlati**, anche se sono lo stesso evento.

#### **5.2. Tre soluzioni**

| Soluzione | Accuratezza | Note |
|---|---|---|
| **1. NTP** (*Network Time Protocol*) con un **time server pubblico** su Internet | **decine di ms** su Internet pubblica; **1 ms** in rete locale in condizioni ideali | protocollo per sincronizzare gli orologi su reti a pacchetto a **latenza variabile**, progettato per mitigare la latenza di rete; richiede di aprire nel firewall la **porta UDP 123** |
| **2. Time server dedicato** dietro il firewall | dispositivi sincronizzati entro **0,5–2 ms** | |
| **3. PTP** (*Precision Time Protocol*, standard **IEEE 1588**) | sotto il microsecondo con marcatura temporale hardware | |

**Esempio: la borsa**. La sincronizzazione è essenziale per gestire **liste degli ordini**, prezzi e dati.

| Aspetto | Valore |
|---|---|
| volumi | Bloomberg Ticker Plant: circa **200 miliardi** di messaggi al giorno; OPRA (SIP delle opzioni USA): capacità di circa **1000 miliardi** al giorno (dopo Pillar, 2024); **micro-picchi** fino a **187 milioni** di messaggi al secondo |
| latenza | latenza mediana OPRA circa **20 μs** (prima del 2024 circa 540 μs); *tick-to-trade* su **FPGA**: **100–500 ns**; un ritardo di **50 μs** fa perdere la posizione in coda e peggiora i prezzi di esecuzione |
| AI | circa il **70%** del volume azionario USA è **trading algoritmico guidato dall’AI** |
| sincronizzazione | **NTP non basta più**: con circa 1 ms è **1000 volte troppo grossolano**; la finanza usa **PTP (IEEE 1588)** con marcatura temporale **hardware**, sotto il microsecondo o al nanosecondo |

> ⚠️ **Numeri che cambiano.** Volumi e latenze dei mercati variano rapidamente: vanno letti come ordini di grandezza, non come valori da memorizzare.

#### **5.3. Sincronizzazione risolta, dati ancora sparsi**

**Esempio**. Un **time server** che prende il riferimento dal **GPS** permette alle unità di misura e di memorizzazione collegate in LAN di lavorare con l’**ora corretta**.

<!-- SOURCE_VISUAL id="IS06-V10" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="40" type="schema" description="Time server sincronizzato con il GPS che serve le unità di misura in LAN" reason="Topologia visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Ma i dati restano sparsi**. Secondo il comportamento dei sensori collegati, i dati arrivano con **marche temporali diverse** e possono **mancare**. La sincronizzazione è risolta, ma **non la sparsità** delle tuple. Esempio di tabella nel database:

| Marca temporale del server | T1 (K) | T2 (K) | Speed1 (m/s) | Speed2 (m/s) | RPM1 |
|---|---|---|---|---|---|
| 2026-04-03 14:27:55.021 | 131,5 | 191,4 | 14,5 | 25,2 | 2400 |
| 2026-04-03 14:27:58.531 | 131,5 | 191,3 | NaN | NaN | 2401 |
| 2026-04-03 14:28:02.343 | NaN | 191,3 | 14,5 | NaN | 2403 |

Le sorgenti IoT possono essere:

| Sorgente | Comportamento |
|---|---|
| **passiva** | produce dati **solo su richiesta** |
| **attiva** | i dati vanno **assorbiti quasi in tempo reale** |
| **dinamica** | può cambiare **formato** e **frequenza**, e ricevere aggiornamenti *over the air* |

---


### **6. Dati strutturati e non strutturati**


Il confronto tra le due rappresentazioni chiarisce il rapporto tra i concetti.

<!-- SOURCE_VISUAL id="IS06-V25" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="77" type="infografica" description="Confronto tra dati strutturati e non strutturati" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

| | **Dati strutturati** («bene!») | **Dati non strutturati** («male…») |
|---|---|---|
| Dove stanno | di solito in **database relazionali** (RDBMS) | in qualunque altro posto, anche in database **non relazionali** (NoSQL) |
| Forma | campi con dati di **lunghezza definita**: numeri di telefono, codici fiscali, CAP; anche stringhe di lunghezza variabile, come i nomi, dentro **record** | hanno una **struttura interna**, ma **non** secondo **modelli o schemi predefiniti** |
| Origine | umana o automatica, purché creati **dentro una struttura RDBMS** | testuali o non testuali, generati da **persone** o **macchine** |
| Ricerca | **facilmente interrogabili** da query umane e da algoritmi, grazie a tipi di dato e nomi dei campi (alfabetico, numerico, valuta, data) | gli strumenti di analisi tradizionali **non riescono a interpretarli** direttamente |

I **dati semi-strutturati** combinano una struttura parziale con contenuti flessibili.

> 💡 **Semi-strutturati (nota didattica).** Hanno etichette o marcatori che separano gli elementi, ma senza uno schema rigido: JSON, XML, file di log con campi ricorrenti. L’**email** è l’esempio della: i **metadati** (mittente, data, oggetto) sono strutturati, il **corpo del messaggio** no.

<!-- SOURCE_VISUAL id="IS06-V26" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="80" type="infografica" description="Dati semi-strutturati" reason="Pagina con sola infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Dati non strutturati generati da persone**:

| Tipo | Esempi |
|---|---|
| **file di testo** | documenti, fogli di calcolo, presentazioni, email, log |
| **email** | struttura interna nei metadati, per questo a volte detta **semi-strutturata**; ma il corpo è non strutturato |
| **social media** | Facebook, Twitter, LinkedIn |
| **siti web** | YouTube, Instagram, siti di condivisione foto |
| **dati mobili** | SMS, posizioni |
| **comunicazioni** | chat, messaggistica istantanea, registrazioni telefoniche, software di collaborazione |
| **media** | MP3, foto digitali, file audio e video |
| **applicazioni aziendali** | documenti MS Office, applicazioni di produttività |

**Dati non strutturati generati da macchine**:

| Tipo | Esempi |
|---|---|
| **immagini satellitari** | dati meteo, forme del territorio, movimenti militari |
| **dati scientifici** | esplorazione petrolifera e del gas, esplorazione spaziale, immagini sismiche, dati atmosferici |
| **sorveglianza digitale** | foto e video di sorveglianza |
| **dati dei sensori** | traffico, meteo, sensori oceanografici |

**Un esempio web di trasformazione dei dati**. **RDFa** (*Resource Description Framework in Attributes*) è una **raccomandazione W3C** che aggiunge a HTML, XHTML e ai documenti XML un insieme di **attributi** per incorporare **metadati ricchi** nelle pagine web: così una parte del contenuto di una pagina diventa leggibile dalle macchine.

<!-- SOURCE_VISUAL id="IS06-V27" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="83" type="esempio di codice" description="Pagina web arricchita con attributi RDFa" reason="Esempio di markup presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Reti neurali e dati non strutturati**. **Non è strettamente obbligatorio** avere dati strutturati per usare il ML. Esempi di **classificazione del testo**:

- capire il **sentiment** del pubblico dai social media;
- rilevare email **spam** e non spam;
- **etichettare automaticamente** le richieste dei clienti;
- **categorizzare** gli articoli di notizie in argomenti predefiniti.

<!-- SOURCE_VISUAL id="IS06-V28" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="84" type="infografica" description="Esempi di classificazione del testo con reti neurali" reason="Icone degli esempi visibili solo nell’infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---


### **7. Preparazione dei dati**


#### **7.1. Tutto si riduce a costruire X e Y**

Obiettivo della sezione: passare da dati **rumorosi, disordinati, non strutturati** al **dataset perfetto** per la fase di apprendimento. È il **passo 2** del flusso di lavoro del ML.

Una rappresentazione esemplifica il passaggio dall’«**entropia**» a **dati strutturati**, come una riga di valori numerici (5.1, 3.5, 1.4, 0.2).

> 📌 **Y = FUNC(X), tutto qui**. Per ogni dataset di machine learning, e in ogni toolbox, **tutto consiste nel creare X e Y**:
> - $X$: la matrice delle **feature**, una riga per **campione**;
> - $Y$: l’**identificativo della classe** o il **valore da apprendere** per ogni campione.

> 💡 **Un campione riconoscibile (nota didattica).** La riga 5.1, 3.5, 1.4, 0.2 è il primo campione del dataset **Iris**: lunghezza e larghezza del sepalo, lunghezza e larghezza del petalo in centimetri, con classe *Iris setosa*. In forma matriciale, con $n$ campioni e $d$ feature:
> $$X \in \mathbb{R}^{n \times d}, \qquad Y \in \{1, \dots, C\}^{n} \text{ (classificazione)} \quad \text{oppure} \quad Y \in \mathbb{R}^{n} \text{ (regressione)}$$

<!-- SOURCE_VISUAL id="IS06-V11" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="44" type="illustrazione" description="Dall’entropia dei dati grezzi ai dati strutturati" reason="Illustrazione della trasformazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2. Pre-elaborazione e data wrangling**


**Preparazione dei dati**: una parte **molto importante** della data science. Comprende la **pulizia dei dati** (*data cleaning*) e l’**ingegneria delle feature** (*feature engineering*), due passi **obbligatori** per ottenere accuratezza e prestazioni migliori nei progetti di machine learning e deep learning.

La distinzione tra **pre-elaborazione** (*preprocessing*) e **data wrangling** comprende anche la visualizzazione dei dati e l’ingegneria delle feature.

<!-- SOURCE_VISUAL id="IS06-V12" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="47" type="schema" description="Preparazione dei dati: pre-elaborazione e data wrangling" reason="Relazione tra i concetti visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS06-V13" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="48" type="schema" description="Ridefinizione di pre-elaborazione e data wrangling con visualizzazione e ingegneria delle feature" reason="Relazione tra i concetti visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Prima di tutto**. «È un **mito assoluto** che si possa lanciare un algoritmo sui dati grezzi e veder **saltare fuori le intuizioni**» (**Jeffrey Heer**, esperto di visualizzazione dell’informazione e analisi interattiva dei dati alla University of Washington).
> - Il problema del data wrangling **cresce** con i dati non strutturati e i formati diversi che arrivano da sensori, online e database tradizionali.
> - Tutti questi dati vanno **puliti e organizzati** prima di applicare analisi, classificatori e regressori.

Il **data wrangling** si articola in passi operativi per ispezionare, correggere e trasformare i dati.

<!-- SOURCE_VISUAL id="IS06-V14" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="50" type="illustrazione" description="Illustrazione introduttiva sul data wrangling" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS06-V15" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="52" type="diagramma" description="Passi del data wrangling" reason="Sequenza dei passi visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS06-V16" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="53" type="diagramma" description="Attività del data wrangling" reason="Attività visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**I compiti del data wrangling**:

| Compito | Che cosa fa |
|---|---|
| **scoperta** (*discovering*) | **capire a fondo** i dati e decidere l’approccio migliore; per esempio, analizzando dati meteo si scopre che vengono **da una sola area**, e ci si concentra sull’individuazione di pattern |
| **strutturazione** | i dati arrivano da fonti diverse, **in forme e dimensioni diverse**: vanno portati a un **formato adeguato** |
| **pulizia** | **correggere o rimuovere** i dati che possono degradare le prestazioni dell’analisi |
| **arricchimento** | **estrarre nuove feature** o nuovi dati dal dataset per migliorare il modello |
| **validazione** | migliorare la **qualità** dei dati e le **regole di coerenza**, così da poter **verificare** le trasformazioni applicate |

**Pre-elaborazione dei dati**: la tecnica che converte i **dati grezzi** in un **dataset pulito**. Quando i dati arrivano da fonti diverse o in formato grezzo, non sono (sempre) pronti per l’analisi. Comprende quattro operazioni:

1. **pulizia** dei dati;
2. **integrazione** dei dati;
3. **trasformazione** dei dati;
4. **riduzione** dei dati.

<!-- SOURCE_VISUAL id="IS06-V17" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="56" type="schema" description="Le quattro operazioni della pre-elaborazione: pulizia, integrazione, trasformazione, riduzione" reason="Schema delle operazioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Che cosa cercare con pre-elaborazione e visualizzazione**. Sono tra i primi passi per **capire i dati**. Servono a trovare:
> - **outlier**;
> - **errori**;
> - **valori mancanti**;
> - **scale diverse**;
> - **distribuzioni**;
> - **cluster** nel dataset;
> - **feature salienti**.
>
> **Da ricordare.** Le prime voci riguardano la **preparazione dei dati**, le ultime l’**ingegneria delle feature**.

**Perché la pre-elaborazione è così importante?**. Perché i dati reali **non sono formattati**:

| Problema | Cause tipiche |
|---|---|
| **dati inaccurati o mancanti** | raccolta non continua, errori di inserimento, problemi tecnici di sensori o trasmissione, DoS, ritardi |
| **dati rumorosi, errati, outlier** | problemi tecnologici del dispositivo di acquisizione, errori umani nell’inserimento |
| **dati incoerenti** | **duplicati**, errori di inserimento, codici o nomi sbagliati: in generale **violazioni dei vincoli** sui dati |

> 💡 **Scale diverse: normalizzazione (nota didattica).** Due tecniche classiche per portare le feature su scale confrontabili:
> $$x' = \frac{x - x_{\min}}{x_{\max} - x_{\min}} \in [0, 1] \qquad\qquad z = \frac{x - \mu}{\sigma}$$
> La normalizzazione min-max è sensibile agli outlier; lo z-score centra i dati in media nulla e deviazione standard unitaria. I parametri ($x_{\min}$, $x_{\max}$, $\mu$, $\sigma$) si stimano **solo sui dati di addestramento**, così la validazione resta indipendente.

#### **7.3. Strumenti, agenti e LLM**


**Strumenti di pre-elaborazione**:

| Strumento | Descrizione |
|---|---|
| **MATLAB** | connessione ai database, selezione delle tabelle e importazione dei dati nell’ambiente di lavoro |
| **R** | ambiente con molti pacchetti per la pre-elaborazione, come **dplyr** |
| **Weka** | raccolta di algoritmi di ML per il **data mining**, con strumenti di pre-elaborazione |
| **RapidMiner** | piattaforma **open source** di analisi predittiva per il data mining |
| **Python** | linguaggio con molte librerie per la pre-elaborazione |
| **agenti** | Claude Code/Cowork, Codex, Gemini, … |

**Agenti di AI per il data wrangling**:

- **Claude Code**, **Cowork**, **Codex CLI**, **Gemini CLI**: pulizia **autonoma** dei dati, **inferenza dello schema**, **risoluzione delle entità**, generazione di **espressioni regolari** a partire da esempi;
- uso tipico: «dato questo CSV disordinato con **formati di data e unità misti**, normalizzalo» → l’agente **scrive, esegue e testa** la pipeline;
- il ruolo umano si sposta **dallo scrivere la trasformazione al validarla**.

Gli agenti possono anche sostenere la **creazione di database**.

<!-- SOURCE_VISUAL id="IS06-V18" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="60" type="schermata" description="Agenti di AI per il data wrangling e la creazione di database" reason="Pagina con sola schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**LLM per l’estrazione strutturata da dati non strutturati**:

- **input**: testo libero (referti, email, note cliniche, PDF) → **output**: record **JSON tipizzati** o Markdown;
- **sostituisce** gran parte delle pipeline di **NER** e di parsing basato su regole;
- esempio: **1000 referti di laboratorio** in PDF → un **DataFrame** con **15 colonne normalizzate**, con un solo prompt.

> 📌 **Named Entity Recognition** (NER). Tecnica classica di NLP che **estrae entità con nome** dal testo libero: persone, luoghi, date, quantità, organizzazioni, codici medici, ecc.

**Database vettoriali: il nuovo livello di memorizzazione dei dati non strutturati**:

- esempi: **Qdrant**, **Pinecone**, **pgvector**, **ChromaDB**, **Weaviate**, **Milvus**;
- memorizzano **embedding** (vettori densi, tipicamente di **384–3072 dimensioni**) invece di testo o immagini grezzi;
- permettono **ricerca semantica**, **RAG** e **deduplicazione per significato**, non per corrispondenza di stringhe;
- pipeline tipica: documento grezzo → **modello di embedding** → **database vettoriale** → **query di similarità**.

> 📌 **Similarità coseno (nota didattica).** La query di similarità confronta di solito i vettori con la similarità coseno:
> $$\cos(\mathbf{u}, \mathbf{v}) = \frac{\mathbf{u} \cdot \mathbf{v}}{\lVert \mathbf{u} \rVert \, \lVert \mathbf{v} \rVert} \in [-1, 1]$$
> Due frasi con significato simile hanno embedding vicini (coseno prossimo a 1) anche se non condividono parole: per questo la deduplicazione «per significato» trova «auto rossa» e «vettura di colore rosso».

L’indice conserva identificativi dei documenti, embedding e metadati; il testo originale viene recuperato dopo la ricerca. In fase di **indicizzazione**, ogni documento viene eventualmente suddiviso in blocchi, trasformato nello stesso spazio vettoriale e associato ai metadati. In fase di **query**, anche la domanda è convertita con lo stesso modello e si recuperano i $k$ vettori più vicini. Cambiare modello di embedding richiede di ricostruire i vettori, perciò la versione va registrata.

Una scansione esatta confronta la query $q\in\mathbb{R}^d$ con tutti gli $N$ vettori, con costo dell’ordine di $O(Nd)$. Per collezioni grandi si usano indici di ricerca approssimata dei vicini (*Approximate Nearest Neighbours*, ANN): riducono il numero di confronti e restituiscono vicini molto buoni, senza garantire che coincidano sempre con quelli esatti.

**HNSW** (*Hierarchical Navigable Small World*) costruisce un grafo multilivello fra vettori. La ricerca parte da pochi nodi di un livello superiore, si sposta verso nodi progressivamente più vicini alla query e scende verso livelli più densi fino a selezionare i candidati. È una strategia di indicizzazione, non una nuova misura di similarità.

<!-- SOURCE_VISUAL id="IS06-V29" source="L06-web.pdf" page="79" type="diagramma" description="Ricerca approssimata HNSW: navigazione da livelli sparsi a livelli densi del grafo di embedding" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS06-V30" source="L06-web.pdf" page="81" type="schema" description="Percorsi di indicizzazione dei documenti e query verso recupero top-k, contesto e risposta citata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Una dimostrazione con ChromaDB indicizza tre brevi note con metadati e interroga la raccolta con la frase «cardiac infarct». Il recupero trova note sull’infarto miocardico anche senza parole identiche, eventualmente filtrando per reparto. Le distanze restituite dipendono dalla metrica dell’indice.

> ⚠️ **Limiti della ricerca vettoriale.** Gli embedding non sono spiegazioni interpretabili; possono incorporare i bias del modello, richiedono una nuova indicizzazione se cambia il modello e non sono adatti a interrogazioni numeriche esatte o aggregazioni. La base strutturata resta la fonte autorevole per conteggi e valori; vettori e metadati personali restano soggetti ai requisiti di privacy.

> ⚠️ **Attenzione: GIGO vale ancora**.
> - Gli agenti **inventano schemi** (allucinazioni), le estrazioni degli LLM **derivano** nel tempo, gli embedding **riflettono i bias** dell’addestramento.
> - **Validare sempre**: controlli di **tipo** e di **intervallo**, **riconciliazione** tra fonti, **controlli a campione** fatti da persone.
> - Regola: **assistito dall’AI ≠ affidato all’AI** (*AI-assisted ≠ AI-trusted*).
> - La preparazione dei dati resta una fase esplicita del flusso di lavoro anche quando viene accelerata da strumenti generativi e agenti.

In un flusso di armonizzazione automatizzata, l’agente esamina file grezzi, profila colonne, unità, intervalli e valori mancanti, scrive il codice di conversione, genera test e documenta le assunzioni. Le regole possono imporre di non modificare gli originali, conservare i valori mancanti con flag dedicati, registrare ogni conversione e chiedere conferma quando un’unità è ambigua. Il risultato utile comprende tabella pulita, codice riproducibile, test e rapporto delle decisioni. La revisione umana verifica soprattutto provenienza, fuso orario, convenzione dell’angolo, duplicati e NaN.

<!-- SOURCE_VISUAL id="IS06-V31" source="L06-web.pdf" page="74" type="schema" description="Flusso di data wrangling assistito da agente: ispezione, codice e test con validazione umana" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---


### **8. Dati mancanti**


#### **8.1. Rappresentazione e origine**

> 📌 **NaN**. *Not a Number*: un valore di un tipo numerico che si interpreta come **indefinito, non rappresentabile o mancante**, codificato secondo lo standard **IEEE 754**.

> ⚠️ **NaN esiste solo per i numeri in virgola mobile**. Gli interi non possono contenere NaN: per questo le librerie moderne (pandas `Int64`, Arrow, Polars) hanno introdotto un marcatore **NA** indipendente dal tipo per le colonne che ammettono valori nulli.

> 💡 **Una proprietà di NaN (nota didattica).** Per IEEE 754 un NaN **non è uguale a nulla, nemmeno a se stesso**: `NaN == NaN` è falso. Per trovarlo si usano funzioni dedicate come `isnan` in MATLAB o `isna` in pandas; e qualsiasi somma che coinvolge un NaN dà NaN, per cui una media calcolata senza gestirli risulta NaN.

**Esempio: tre ospedali che uniscono i dati**. Tre ospedali uniscono i dati dei loro pazienti sugli **esami del sangue**, ma i laboratori hanno **sistemi e strumenti diversi** con un **numero diverso di feature**. Qui l’**armonizzazione** è cruciale, perché le fonti sono diverse: per esempio mg/dL e mmol/L.

> 💡 **La conversione dipende dalla sostanza (nota didattica).** Da mg/dL a mmol/L si divide per la massa molare (in g/mol) e si moltiplica per 10. Per il **glucosio** ($M \approx 180{,}16$ g/mol):
> $$c\,[\text{mmol/L}] = \frac{c\,[\text{mg/dL}] \times 10}{180{,}16} \approx \frac{c\,[\text{mg/dL}]}{18}$$
> quindi 90 mg/dL ≈ 5,0 mmol/L. Il fattore **cambia per ogni analita**: usare lo stesso numero per colesterolo e glucosio è un errore di armonizzazione.

<!-- SOURCE_VISUAL id="IS06-V19" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="66" type="tabella" description="Tre ospedali con esami del sangue su feature diverse e valori mancanti" reason="Struttura delle tabelle degli ospedali visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.2. Eliminare feature o record?**

**Rimuovere le feature incomplete**. Si **cancella** la colonna? E se la **feature n. 12** fosse proprio quella che serve a **rilevare il tumore** studiato?

**Rimuovere i vettori incompleti**. «Addio Jamie e il signor “35 Male”»: da **6 record** si passa a **4** completi. Questa **perdita di informazione** è accettabile per la tua applicazione?

<!-- SOURCE_VISUAL id="IS06-V20" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="67" type="tabella" description="Rimozione della feature 12 incompleta" reason="Tabella dell’esempio visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS06-V21" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="68" type="tabella" description="Rimozione dei record incompleti: da 6 a 4 record" reason="Tabella dell’esempio visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Un buco «topologico»**. Nel piano delle feature $f_1$, $f_2$ i campioni sono «scarto» (x) o «buono» (o). La **matrice dei dati è completa**, ma lo **spazio delle feature non è coperto uniformemente**. Senza un’adeguata **visualizzazione dei dati** è difficile accorgersene.

> 💡 **Perché il valore è pericoloso.** Nessuna cella è NaN, quindi nessun controllo automatico segnala il problema. Ma nella regione vuota il modello **non ha mai visto esempi** e deciderà a caso: è il limite della generalizzazione.

<!-- SOURCE_VISUAL id="IS06-V22" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="69" type="grafico" description="Dati completi ma spazio delle feature f1-f2 non coperto uniformemente" reason="Regione vuota visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.3. Tre strategie**

**1. Ignorare il record mancante**. È il metodo **più semplice ed efficiente**, ma:

- **come li gestisce il tuo algoritmo** di apprendimento?
- **non va usato** quando i valori mancanti sono **moltissimi**;
- **non va usato** quando il problema si può **risolvere alla radice**: debug, riprogettazione, ripetizione dell’esperimento, invece di ignorare la causa.

**2. Riempire a mano i valori mancanti**. È **uno dei metodi migliori**, ma con dataset **grandi** e molti valori mancanti diventa **troppo lento**. Nelle **sperimentazioni mediche** ogni singolo dato è **importante e costoso**: escludere un vettore **non è una buona prima strategia**. Una vignetta ironizza sull’impossibilità pratica di ripetere una raccolta costosa solo perché alcuni sensori hanno prodotto valori mancanti.

**3. Riempire con valori calcolati**:

- **media**, **moda** o **mediana** dei valori osservati;
- trovare la **colonna o il vettore più simile** e copiarne i valori, per esempio con un classificatore **k-NN**;
- **generare** i valori con un algoritmo di ML o di deep learning;
- **svantaggio**: può **introdurre bias** nei dati.

> 📌 **Imputazione con la media (nota didattica).** Se la feature $j$ è osservata sull’insieme di campioni $O_j$, un valore mancante si sostituisce con
> $$\hat{x}_{ij} = \frac{1}{|O_j|} \sum_{k \in O_j} x_{kj}$$
> La media **riduce artificialmente la varianza** della feature e **indebolisce le correlazioni** con le altre: è un effetto tipico dell’imputazione con la media.

**Un caso base: il modello lineare**. La serie $F$ viene riempita con **interpolazione lineare**; $TF$ indica i valori di $F$ riempiti. **La scelta del modello** usato per interpolare i punti mancanti **influenzerà l’apprendimento** del modello successivo.

> 📌 **Interpolazione lineare (nota didattica).** Se il valore manca all’istante $t$, compreso tra due istanti noti $t_1 < t < t_2$:
> $$\hat{x}(t) = x(t_1) + \frac{x(t_2) - x(t_1)}{t_2 - t_1}\,(t - t_1)$$

<!-- SOURCE_VISUAL id="IS06-V23" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="73" type="grafico" description="Serie F con valori mancanti riempiti per interpolazione lineare (TF)" reason="Andamento della serie visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Soluzioni possibili in breve**:

| Passo o strategia | Note |
|---|---|
| **passo 0: quantificare il problema** | i valori mancano per **righe** o per **colonne**? quanti sono? |
| **non fare nulla** | lasciare che l’algoritmo gestisca i valori mancanti. Va bene, **ma che cosa sta facendo?** Spiegabilità e cross-validation possono essere compromesse; alcuni metodi ignorano i dati, altri **si bloccano** o si comportano in modo erratico |
| **riempire a mano** | se possibile, **chiedendo all’esperto** distribuzione e intervalli dei dati |
| **approccio statistico** | media, moda, mediana, **valore tabù** (!) o costante |
| **k-Nearest Neighbor** | trovare il vettore o i vettori **più simili** e copiarne i valori mancanti |

> ⚠️ **Feature categoriche**. Con una feature **categorica** la media produce un **numero reale** che non corrisponde a nessuna categoria: la media tra «rosso» = 1 e «blu» = 3 non è «verde» = 2. Per queste feature si usano la **moda** o il k-NN.

> 💡 **Il valore tabù (nota didattica).** È un valore impossibile per la feature (per esempio −999 per un’età) usato per marcare il dato mancante. È pratico, ma pericoloso: se non viene gestito, il modello lo tratta come un valore vero e la media della colonna crolla. Per questo motivo il valore sentinella va riconosciuto e convertito in un dato mancante prima dell’analisi.

#### **8.4. Perché i valori mancano**

La scelta del rimedio dipende dal meccanismo che genera l’assenza. Tre categorie distinguono quanto la probabilità di mancanza dipenda dalle altre variabili o dal valore non osservato:

| Meccanismo | Significato | Esempio e conseguenza |
|---|---|---|
| **MCAR** (*Missing Completely At Random*) | La mancanza non dipende né dalle variabili osservate né dal valore mancante. | Pacchetto perso per un errore casuale di comunicazione. Eliminare i casi o imputarli può ridurre l’efficienza, senza introdurre sistematicamente distorsione. |
| **MAR** (*Missing At Random*) | La mancanza dipende da variabili osservate, ma non dal valore mancante una volta considerate quelle variabili. | Un esame è più spesso assente in un ospedale o in una fascia d’età. L’eliminazione semplice può sottorappresentare quel gruppo; l’imputazione può usare le variabili osservate correlate. |
| **MNAR** (*Missing Not At Random*) | La probabilità di mancanza dipende dal valore non osservato stesso. | Un sensore si protegge quando la vibrazione supera il proprio intervallo. Gli estremi spariscono e la media osservata sottostima quella reale. Occorrono un indicatore di mancanza, un sensore adeguato e conoscenza del processo. |

Rimuovere righe incomplete non è una pulizia neutra: cambia la popolazione osservata. In MAR può eliminare quasi tutti i dati di un sito; in MNAR può nascondere proprio gli eventi estremi. Prima di scegliere una strategia, misurare il tasso di assenza per feature e gruppo e verificare se gli schemi delle mancanze sono informativi.

> ⚠️ **Imputazione e data leakage.** Ogni statistica di imputazione si stima sul solo insieme di addestramento. La media o mediana congelata si applica poi a validation e test; lo stesso vale per normalizzazione, PCA e selezione delle feature. Calcolare l’imputazione prima della suddivisione trasferisce informazioni dai dati di valutazione alla pipeline e rende la misura ottimistica.

<!-- SOURCE_VISUAL id="IS06-V24" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="75" type="schema" description="Riepilogo delle strategie per i dati mancanti" reason="Schema di riepilogo presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---


### **9. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Eterogeneità** / **omogeneità** | dati o popolazioni diversi / uguali |
| **Armonizzazione** / **sincronizzazione** | stesse unità, formati e convenzioni / stesso riferimento temporale |
| **NTP** / **time server dedicato** / **PTP** | decine di ms su Internet, 1 ms in LAN / 0,5–2 ms / sotto il microsecondo con marcatura hardware |
| **Sincronizzazione** / **sparsità** | orologi allineati / tuple con valori mancanti anche a orologi allineati |
| **Pre-elaborazione** / **data wrangling** | pulizia, integrazione, trasformazione, riduzione / scoperta, strutturazione, pulizia, arricchimento, validazione |
| **Valore mancante** / **buco topologico** | cella NaN / matrice completa ma regione dello spazio delle feature senza esempi |
| **Ignorare** / **riempire a mano** / **riempire con valori calcolati** | semplice ma perde informazione / accurato ma lento / veloce ma introduce bias |
| **NaN** / **NA** | valore IEEE 754 solo per virgola mobile / marcatore indipendente dal tipo |
| **Assistito dall’AI** / **affidato all’AI** | l’agente scrive la pipeline e l’umano valida / fiducia senza validazione (da evitare) |
| **Strutturati** / **semi-strutturati** / **non strutturati** | schema rigido (RDBMS) / marcatori senza schema rigido (email, JSON) / nessuno schema predefinito (testo, immagini, audio) |

---


### **10. Sintesi della lezione**


> ✅ **Punti principali**.
> - Usare un dataset **senza lavoro preliminare** è **inutile, pericoloso** e fa perdere tempo: **GIGO**.
> - **Studiare, pulire, armonizzare** i dati e **capirne le relazioni di base** è **obbligatorio**: unità e convenzioni comuni (Mars Climate Orbiter, parco eolico), orologi sincronizzati (NTP, PTP).
> - La preparazione dei dati è il **passo 2** del flusso di lavoro: pre-elaborazione e data wrangling, alla ricerca di outlier, errori, valori mancanti, scale diverse, distribuzioni, cluster e feature salienti.
> - Oggi **agenti, LLM e database vettoriali** accelerano il passo 2, ma **assistito dall’AI non significa affidato all’AI**: validare sempre.
> - **Gestire i dati mancanti**: quantificare il problema, poi scegliere tra ignorare, riempire a mano, statistiche o k-NN, consapevoli dei bias introdotti.
> - **Dati strutturati e non strutturati**: il ML funziona anche sui secondi.
