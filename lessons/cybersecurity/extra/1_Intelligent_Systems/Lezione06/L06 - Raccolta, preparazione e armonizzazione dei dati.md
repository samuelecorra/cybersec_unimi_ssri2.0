## **Lezione 6: Raccolta, preparazione e armonizzazione dei dati**

La sesta lezione del corso **IS4** si occupa della materia prima di ogni sistema intelligente: i **dati**. Si parte da dove vengono e da quanti sono, poi si affrontano i problemi concreti che si incontrano prima ancora di addestrare un modello: **formati e unità diverse**, orologi **non sincronizzati**, dati **sporchi**, **mancanti** o **non strutturati**. Il messaggio di fondo è il principio **GIGO** (*garbage in, garbage out*): nessuna rete neurale «magica» salva un progetto costruito su dati sbagliati. La lezione mostra anche come oggi **agenti di AI**, LLM e **database vettoriali** entrino nella preparazione dei dati, senza cambiare la regola di validare tutto.

Fonte: [PDF della Lezione 06](Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf), 85 pagine, nessun supplemento. La lezione si apre con una breve simulazione d’esame sulle lezioni precedenti. Numeri di mercato, prodotti e strumenti descrivono la versione del materiale acquisita; le note didattiche aggiunte sono segnalate. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Struttura della lezione**

_(slide pp. 1, 14)_

Titolo originale: *Data Gathering, Data Preprocessing, Data Harmonization for intelligent system learning*. L’indice (p. 14) elenca:

1. **raccolta dei dati**: da dove arrivano? quanti ne arrivano?
2. **preparazione dei dati**: controllo dei dati, dati non strutturati, armonizzazione, analisi, filtraggio;
3. **LLM e agenti** nella preparazione dei dati;
4. punti principali.

---

### **1. Simulazione d’esame breve**

_(slide pp. 2–13)_

La simulazione serve a **verificare la preparazione**, **non a studiare** (p. 2).

> 📌 **Regole di questa simulazione** (p. 3).
> - **1 risposta corretta** → peso **1**;
> - **N risposte sbagliate** → peso **0**;
> - **nessun peso negativo**.
>
> Consigli: non ci sono penalità se non si risponde, **ma è meglio rispondere**; **escludere le risposte sbagliate** invece di cercare quella giusta; in caso di dubbio scegliere la risposta che **sembra meno sbagliata**.

> ⚠️ **Differenza con la simulazione n. 1.** Nella Lezione 04, §6.1, erano previste anche 1–2 risposte «molto sbagliate» con peso **−1**; in questa simulazione breve **non ci sono pesi negativi**. Conviene verificare con il docente le regole valide per l’appello.

| Domanda | Opzioni | Risposta e commento |
|---|---|---|
| **D1** (pp. 4–5). Secondo la discussione in aula, la teoria dei sistemi intelligenti deve includere quali fasi di progetto? | 1. rappresentazione; 2. rappresentazione, valutazione; 3. rappresentazione, valutazione, ottimizzazione; 4. nessuna delle altre | **3** (Lezione 04, §4.4) |
| **D2** (pp. 6–7). Il clustering richiede **sempre** un dataset supervisionato? | 1. sì; 2. no | **2**. Capire se una tecnica **richiede etichette** è fondamentale: bisogna padroneggiare la tassonomia della Lezione 03 |
| **D3** (pp. 8–9). Secondo la discussione in aula, usare una soluzione a scatola nera è… | 1. una cattiva pratica per un progettista di ML; 2. ammissibile in circostanze specifiche; 3. buona pratica attuale, perché i modelli migliori sono grandi e non spiegabili | **2** (Lezione 04, §2) |
| **D4** (pp. 10–11). Nei sistemi di classificazione, durante l’addestramento si può ottimizzare in generale… | 1. l’accuratezza; 2. il margine; 3. entrambi | **3**, **se il metodo di apprendimento scelto lo permette** (Lezione 04, §4.4) |
| **D5** (pp. 12–13). La regolamentazione UE dell’AI si basa su… | 1. un elenco di casi d’uso; 2. la valutazione del rischio dell’applicazione; 3. entrambi; 4. nessuno dei precedenti | **3** (Lezione 02, §1) |

> 💡 **Perché D5 è «entrambi».** L’AI Act classifica per **livello di rischio**, ma per definire i livelli usa **elenchi di casi d’uso**: le pratiche vietate e le categorie ad alto rischio (infrastrutture critiche, istruzione, lavoro, credito, forze dell’ordine, migrazione) sono elencate esplicitamente.

---

### **2. Il valore dell’analisi dei dati**

_(slide pp. 15–17)_

**Discussione aperta in aula** (p. 15):

- quali sono le vostre **competenze di analisi dei dati**, e come le valutate?
- quali **esperienze** precedenti avete? quali **strumenti** preferite?
- usate strumenti di analisi dei dati per la **pre-elaborazione**, la **post-elaborazione**, la **comprensione dei risultati**?

Il docente chiede di fermarsi un minuto e **annotare le risposte su carta**, per rileggerle a fine corso.

La p. 16 mostra il **valore dell’analisi dei dati** secondo **Gartner**: l’AI **può aiutare in tutte le fasi**. La p. 17 riprende lo stesso grafico «per voi», collegandolo allo **stipendio atteso**.

> 💡 **Il modello di Gartner (nota didattica).** Lo schema più noto di Gartner sull’analisi dei dati ha quattro livelli, con **valore** e **difficoltà** crescenti:
>
> | Livello | Domanda |
> |---|---|
> | **descrittiva** | che cosa è successo? |
> | **diagnostica** | perché è successo? |
> | **predittiva** | che cosa succederà? |
> | **prescrittiva** | che cosa bisogna fare perché succeda? |
>
> Il contenuto esatto della figura va verificato sull’immagine.

<!-- SOURCE_VISUAL id="IS06-V01" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="16" type="grafico" description="Valore dell’analisi dei dati secondo Gartner, con l’AI che aiuta in tutte le fasi" reason="Livelli e assi del grafico visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS06-V02" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="17" type="grafico" description="Valore dell’analisi dei dati secondo Gartner collegato allo stipendio atteso" reason="Annotazioni del grafico visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. La raccolta dei dati**

_(slide pp. 18–27)_

#### **3.1. Il primo passo del flusso di lavoro**

La raccolta dei dati è il **passo 1** del flusso di lavoro del ML (pp. 18–19; Lezione 05, §2). **Nella pratica** i dati arrivano da (p. 20):

- query classiche a **database**;
- elaborazione di **file**;
- **formati personalizzati**;
- **flussi di dati** da IoT e AIoT;
- **piattaforme online** (AWS, Azure, Google, …).

Le fonti capaci di generare dati sono moltissime e potenti; i dettagli tecnici di ciascuna **sono fuori dal focus del corso**.

<!-- SOURCE_VISUAL id="IS06-V03" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="19" type="diagramma" description="Passo 1 del flusso di lavoro del ML: raccolta dei dati" reason="Posizione del passo nel flusso visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Raccolta dei dati** (p. 21). Il processo di **raccogliere e misurare** informazioni su **variabili di interesse** in un sistema stabilito. Si usa nelle scienze fisiche, nel settore industriale, negli affari, nelle discipline umanistiche e nelle scienze sociali, in generale in tutte le discipline che studiano la società e la cultura umana. L’AI si usa in tutti questi settori, anche con dati **fortemente non strutturati** come il testo grezzo.

> ⚠️ **GIGO: garbage in, garbage out** (p. 22). **Nessuna rete neurale «magica» vi salverà** da dati spazzatura.

<!-- SOURCE_VISUAL id="IS06-V04" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="22" type="illustrazione" description="GIGO: nessuna rete neurale magica salva da dati spazzatura" reason="Illustrazione della slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. Quanti dati: l’esplosione dell’IoT**

**Dati generati dai dispositivi IoT nel 2025** (p. 23, stima di **IDC**, International Data Corporation):

- **41,6 miliardi** di dispositivi connessi;
- **79,4 zettabyte** (ZB) di dati all’anno;
- fonti in crescita: **videosorveglianza**, dati della **casa intelligente**, **smartphone** (coordinate, sensori di salute, attività), dispositivi **indossabili** (smartwatch, braccialetti), **sensori**.

> 📌 **Zettabyte.** Il prefisso *zetta* indica la moltiplicazione per la **settima potenza di 1000**:
> $$1\ \text{ZB} = 1000^7\ \text{byte} = 10^{21}\ \text{byte}$$

La **videosorveglianza** produrrà una **quota rilevante** dei dati IoT, e il **5G** abilita nuovi dispositivi (p. 24).

**Esempio: coordinate GPS degli smartphone in Italia** (p. 24). Ipotesi della slide: popolazione di **65 milioni**, **83%** di persone con cellulare, **2 coordinate al minuto**, **8 byte** per coordinata (una coordinata GPS richiede almeno 48–64 bit, formato ISO 6709).

$$\text{byte/anno} = 65 \times 10^6 \times 0{,}83 \times 365 \times 24 \times 60 \times 2 \times 8 \approx 4{,}54 \times 10^{14}\ \text{byte} \approx 454\ \text{TB}$$

Distribuiti sui secondi di un anno, $3{,}1536 \times 10^7$ s:

$$\frac{4{,}54 \times 10^{14}\ \text{byte}}{3{,}1536 \times 10^{7}\ \text{s}} \approx 1{,}44 \times 10^{7}\ \text{byte/s} \approx 14{,}4\ \text{MB/s}$$

> ⚠️ **Un valore intermedio sbagliato.** La slide scrive «= 697996800 byte ≈ 454 TB», ma il prodotto vale circa $4{,}54 \times 10^{14}$ byte. Il numero intermedio non corrisponde (sembra un errore di calcolo, forse un overflow); il risultato finale di **454 TB** e i **14,4 MB/s** sono invece corretti.

<!-- SOURCE_VISUAL id="IS06-V05" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="25" type="illustrazione" description="Big Data: serve il data mining" reason="Pagina con sola illustrazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Con tanti dati **serve il mining** (p. 25): estrarre l’informazione utile.

#### **3.3. Dataset pubblici e strumenti**

**Dataset pubblici annotati** (p. 26): la **fonte primaria** per l’addestramento, sotto forma di **raccolte di dataset**.

- UCI Machine Learning Repository;
- Kaggle Datasets;
- Amazon Datasets;
- Google Dataset Search;
- Microsoft Datasets;
- dataset **governativi**: portale Open Data dell’UE, dati del governo USA, della Nuova Zelanda, dell’India, …;
- dataset di **computer vision**;
- Lionbridge AI Datasets;
- … e l’elenco **cresce ogni giorno**.

**Strumento: MATLAB Database Explorer** (p. 27). Permette di **collegarsi rapidamente a un database**, esplorarne i dati e **importarli** nel workspace di MATLAB in modo visuale. Funzioni utili:

- creare e configurare sorgenti dati **ODBC** e **JDBC**;
- aprire **più connessioni** allo stesso database o a database diversi;
- selezionare **tabelle e colonne** di interesse e rifinire la selezione con criteri di **query SQL**;
- **anteprima** dei dati selezionati e opzioni di importazione personalizzate;
- importare i dati nel workspace per l’analisi;
- **salvare le query SQL** generate e **generare codice MATLAB**.

---

### **4. Eterogeneità dei dati e armonizzazione**

_(slide pp. 28–34)_

#### **4.1. Formati, unità, intervalli**

Il tema della sezione (p. 28): **unità, formati e tempi esatti**.

**Eterogeneità dei formati** (p. 29). Dati raccolti da fonti diverse possono differire per:

- **formati**;
- **spazi dei nomi** (*name spaces*);
- **unità di misura**;
- **intervalli** di valori;
- **dimensioni**;
- …

> 📌 **Eterogeneità** (p. 29). In statistica indica che popolazioni, campioni o risultati sono **diversi** tra loro; è l’opposto dell’**omogeneità**, in cui popolazione, dati o risultati sono **uguali**.

> 📌 **Armonizzazione dei dati (nota didattica).** È l’insieme delle operazioni che portano dati eterogenei a una rappresentazione **comune**: stesse unità, stessi formati, stesse convenzioni di riferimento, stessa scala temporale. Solo dopo l’armonizzazione i dati di fonti diverse si possono unire in un unico dataset.

**Caso d’uso: la sonda persa per un errore di unità** (p. 30, articolo di Ajay Harish). Nel **settembre 1999**, dopo quasi 10 mesi di viaggio verso Marte, la **Mars Climate Orbiter** è bruciata nell’atmosfera marziana e si è disintegrata, perché qualcuno **non aveva usato le unità metriche**: i valori erano espressi in **libbre-forza per secondo** invece che nell’unità SI, **newton per secondo**. La sonda era costata in totale **325 milioni di dollari**.

> 💡 **Quanto pesa l’errore (nota didattica).** $1\ \text{lbf} \cdot \text{s} \approx 4{,}45\ \text{N} \cdot \text{s}$: gli impulsi erano **sottostimati di circa 4,45 volte**. La traiettoria calcolata portava la sonda molto più vicino al pianeta del previsto. Due gruppi di lavoro usavano due sistemi di unità senza che nessuna interfaccia li convertisse: è esattamente un problema di armonizzazione.

<!-- SOURCE_VISUAL id="IS06-V06" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="30" type="immagine" description="Mars Climate Orbiter distrutta per l’uso di libbre-forza per secondo al posto di newton per secondo" reason="Immagine del caso d’uso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.2. Caso d’uso: previsione della produzione eolica**

_(slide pp. 31–34)_

**Il problema** (p. 31): come modellare la **specificità** di un sito? Contano le **brezze** del mattino e del pomeriggio, le **colline** e le **occlusioni**.

**Fonti di dati diverse** (p. 32):

- i dati delle **previsioni meteo** di **fornitori diversi** e i dati dei **sensori del parco eolico** possono usare **unità di misura** e **sistemi di riferimento** diversi;
- i dati di previsione **differiscono** da quelli della stazione di misura del sito, perché riferiti a **luoghi diversi**;
- la **griglia** dei dati meteo si trova a un’**altitudine diversa** rispetto all’impianto.

**Non idealità** (p. 33):

- **colline e occlusioni** cambiano l’efficienza dell’impianto: per esempio con vento **da est** la potenza è **molto bassa** (effetto «**ombra**»), mentre altre conformazioni creano un effetto «**imbuto**»;
- le turbine hanno una **velocità minima** per iniziare a girare (**clipping**);
- **turbine uguali** hanno **curve velocità/potenza diverse** a causa delle condizioni circostanti.

<!-- SOURCE_VISUAL id="IS06-V07" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="31" type="mappa" description="Sito eolico con brezze, colline e occlusioni" reason="Conformazione del sito visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS06-V08" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="33" type="schema" description="Non idealità del parco eolico: effetto ombra da est, effetto imbuto, clipping e curve di potenza diverse" reason="Schema del sito e delle curve visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Obiettivo e armonizzazione** (p. 34). Creare un **dataset di 5 anni** unendo fonti con formati, unità di misura e sistemi di riferimento diversi: i dati meteo sulla **griglia** e a varie **altitudini** servono a prevedere la **potenza** (MW) e un **indice di variabilità** sul sito dell’impianto. Le operazioni di armonizzazione:

| Grandezza | Unità comune |
|---|---|
| temperatura | **°C** |
| velocità del vento | **m/s** |
| direzione del vento | angolo **0–360°** misurato **da nord** (nord = 0°) |
| pressione atmosferica | **hPa** |
| copertura nuvolosa | scala **da 0 a 1** |

La slide mostra anche **due definizioni diverse dell’angolo del vento**, da riportare alla stessa convenzione.

> 💡 **Due convenzioni per l’angolo del vento (nota didattica).** In meteorologia l’angolo indica **da dove proviene** il vento, in senso orario da nord. In matematica e in molti modelli numerici si usa l’angolo del **vettore** velocità, **verso dove** va, in senso antiorario dall’asse est. La conversione è
> $$\theta_{\text{met}} = \left(270^\circ - \theta_{\text{mat}}\right) \bmod 360^\circ$$
> Verifica: un vento che soffia verso est ($\theta_{\text{mat}} = 0^\circ$) proviene da ovest, cioè $\theta_{\text{met}} = 270^\circ$. Quali siano le due definizioni della slide va verificato sulla figura.

> ⚠️ **Temperatura in °C e kelvin.** La conversione $T[\text{K}] = T[°\text{C}] + 273{,}15$ è banale, ma dimenticarla sposta tutti i valori di 273: un modello addestrato su una fonte e usato sull’altra produce previsioni prive di senso, senza alcun messaggio d’errore.

<!-- SOURCE_VISUAL id="IS06-V09" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="34" type="schema" description="Dataset di 5 anni per la previsione eolica: griglia meteo, altitudini, predizione di MW e variabilità, due definizioni dell’angolo del vento" reason="Griglia e definizioni degli angoli visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Sincronizzazione dei dati**

_(slide pp. 35–41)_

#### **5.1. Orologi che derivano**

> 📌 **Sincronizzazione** (pp. 35–36). Il modo in cui un dispositivo **regola il proprio orologio interno** per allinearsi agli orologi degli altri dispositivi di una rete. Nelle applicazioni **industriali e scientifiche** è una parte **essenziale**.

**Il problema** (p. 37):

- gli orologi di server, workstation e dispositivi di rete **non sono abbastanza accurati**;
- vengono impostati **a mano**, con uno scarto di un minuto o due, e **raramente ricontrollati**;
- sono mantenuti da un dispositivo a batteria che può **derivare fino a un secondo al giorno**;
- quindi senza un metodo adeguato è **impossibile** avere una sincronizzazione accurata.

> 💡 **Quanto è un secondo al giorno (nota didattica).** In un mese sono 30 s di scarto. Due sensori che misurano lo stesso fenomeno con una deriva di 30 s producono, per esempio, un picco di vibrazione e un picco di temperatura che sembrano **non correlati**, anche se sono lo stesso evento.

#### **5.2. Tre soluzioni**

| Soluzione (p. 38) | Accuratezza | Note |
|---|---|---|
| **1. NTP** (*Network Time Protocol*) con un **time server pubblico** su Internet | **decine di ms** su Internet pubblica; **1 ms** in rete locale in condizioni ideali | protocollo per sincronizzare gli orologi su reti a pacchetto a **latenza variabile**, progettato per mitigare la latenza di rete; richiede di aprire nel firewall la **porta UDP 123** |
| **2. Time server dedicato** dietro il firewall | dispositivi sincronizzati entro **0,5–2 ms** | |
| **3. PTP** (*Precision Time Protocol*, standard **IEEE 1588**) | sotto il microsecondo con marcatura temporale hardware | |

**Esempio: la borsa** (p. 39). La sincronizzazione è essenziale per gestire **liste degli ordini**, prezzi e dati.

| Aspetto | Valori riportati dalla slide |
|---|---|
| volumi | Bloomberg Ticker Plant: circa **200 miliardi** di messaggi al giorno; OPRA (SIP delle opzioni USA): capacità di circa **1000 miliardi** al giorno (dopo Pillar, 2024); **micro-picchi** fino a **187 milioni** di messaggi al secondo |
| latenza | latenza mediana OPRA circa **20 μs** (prima del 2024 circa 540 μs); *tick-to-trade* su **FPGA**: **100–500 ns**; un ritardo di **50 μs** fa perdere la posizione in coda e peggiora i prezzi di esecuzione |
| AI | circa il **70%** del volume azionario USA è **trading algoritmico guidato dall’AI** |
| sincronizzazione | **NTP non basta più**: con circa 1 ms è **1000 volte troppo grossolano**; la finanza usa **PTP (IEEE 1588)** con marcatura temporale **hardware**, sotto il microsecondo o al nanosecondo |

> ⚠️ **Numeri che cambiano.** Volumi e latenze dei mercati variano rapidamente: vanno letti come ordini di grandezza, non come valori da memorizzare.

#### **5.3. Sincronizzazione risolta, dati ancora sparsi**

**Esempio** (p. 40). Un **time server** che prende il riferimento dal **GPS** permette alle unità di misura e di memorizzazione collegate in LAN di lavorare con l’**ora corretta**.

<!-- SOURCE_VISUAL id="IS06-V10" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="40" type="schema" description="Time server sincronizzato con il GPS che serve le unità di misura in LAN" reason="Topologia visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Ma i dati restano sparsi** (p. 41). Secondo il comportamento dei sensori collegati, i dati arrivano con **marche temporali diverse** e possono **mancare**. La sincronizzazione è risolta, ma **non la sparsità** delle tuple. Esempio di tabella nel database:

| Marca temporale del server | T1 (K) | T2 (K) | Speed1 (m/s) | Speed2 (m/s) | RPM1 |
|---|---|---|---|---|---|
| 2026-04-03 14:27:55.021 | 131,5 | 191,4 | 14,5 | 25,2 | 2400 |
| 2026-04-03 14:27:58.531 | 131,5 | 191,3 | NaN | NaN | 2401 |
| 2026-04-03 14:28:02.343 | NaN | 191,3 | 14,5 | NaN | 2403 |

I tre tipi di sorgenti IoT (Lezione 05, §6.2) spiegano i buchi:

| Sorgente | Comportamento |
|---|---|
| **passiva** | produce dati **solo su richiesta** |
| **attiva** | i dati vanno **assorbiti quasi in tempo reale** |
| **dinamica** | può cambiare **formato**, **frequenza**, ricevere aggiornamenti *over the air* (lezioni successive) |

---

### **6. Preparazione dei dati**

_(slide pp. 42–63)_

#### **6.1. Tutto si riduce a costruire X e Y**

Obiettivo della sezione (p. 42): passare da dati **rumorosi, disordinati, non strutturati** al **dataset perfetto** per la fase di apprendimento. È il **passo 2** del flusso di lavoro del ML (p. 43).

La p. 44 illustra il passaggio dall’«**entropia**» a **dati strutturati**, come la riga di numeri 5.1, 3.5, 1.4, 0.2.

> 📌 **Y = FUNC(X), tutto qui** (p. 45). Per ogni dataset di machine learning, e in ogni toolbox, **tutto consiste nel creare X e Y**:
> - $X$: la matrice delle **feature**, una riga per **campione**;
> - $Y$: l’**identificativo della classe** o il **valore da apprendere** per ogni campione.

> 💡 **Un campione riconoscibile (nota didattica).** La riga 5.1, 3.5, 1.4, 0.2 è il primo campione del dataset **Iris**: lunghezza e larghezza del sepalo, lunghezza e larghezza del petalo in centimetri, con classe *Iris setosa*. In forma matriciale, con $n$ campioni e $d$ feature:
> $$X \in \mathbb{R}^{n \times d}, \qquad Y \in \{1, \dots, C\}^{n} \text{ (classificazione)} \quad \text{oppure} \quad Y \in \mathbb{R}^{n} \text{ (regressione)}$$

<!-- SOURCE_VISUAL id="IS06-V11" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="44" type="illustrazione" description="Dall’entropia dei dati grezzi ai dati strutturati" reason="Illustrazione della trasformazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2. Pre-elaborazione e data wrangling**

_(slide pp. 46–54, 56–58)_

**Preparazione dei dati** (p. 46): una parte **molto importante** della data science. Comprende la **pulizia dei dati** (*data cleaning*) e l’**ingegneria delle feature** (*feature engineering*), due passi **obbligatori** per ottenere accuratezza e prestazioni migliori nei progetti di machine learning e deep learning.

Le pp. 47–48 mettono in relazione **pre-elaborazione** (*preprocessing*) e **data wrangling**, con una ridefinizione che include **visualizzazione dei dati** e **ingegneria delle feature**.

<!-- SOURCE_VISUAL id="IS06-V12" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="47" type="schema" description="Preparazione dei dati: pre-elaborazione e data wrangling" reason="Relazione tra i concetti visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS06-V13" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="48" type="schema" description="Ridefinizione di pre-elaborazione e data wrangling con visualizzazione e ingegneria delle feature" reason="Relazione tra i concetti visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Prima di tutto** (p. 49). «È un **mito assoluto** che si possa lanciare un algoritmo sui dati grezzi e veder **saltare fuori le intuizioni**» (**Jeffrey Heer**, esperto di visualizzazione dell’informazione e analisi interattiva dei dati alla University of Washington).
> - Il problema del data wrangling **cresce** con i dati non strutturati e i formati diversi che arrivano da sensori, online e database tradizionali.
> - Tutti questi dati vanno **puliti e organizzati** prima di applicare analisi, classificatori e regressori.

Le pp. 50–53 illustrano il **data wrangling**, i suoi **passi** e le sue **attività**.

<!-- SOURCE_VISUAL id="IS06-V14" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="50" type="illustrazione" description="Illustrazione introduttiva sul data wrangling" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS06-V15" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="52" type="diagramma" description="Passi del data wrangling" reason="Sequenza dei passi visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS06-V16" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="53" type="diagramma" description="Attività del data wrangling" reason="Attività visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**I compiti del data wrangling** (p. 54):

| Compito | Che cosa fa |
|---|---|
| **scoperta** (*discovering*) | **capire a fondo** i dati e decidere l’approccio migliore; per esempio, analizzando dati meteo si scopre che vengono **da una sola area**, e ci si concentra sull’individuazione di pattern |
| **strutturazione** | i dati arrivano da fonti diverse, **in forme e dimensioni diverse**: vanno portati a un **formato adeguato** |
| **pulizia** | **correggere o rimuovere** i dati che possono degradare le prestazioni dell’analisi |
| **arricchimento** | **estrarre nuove feature** o nuovi dati dal dataset per migliorare il modello |
| **validazione** | migliorare la **qualità** dei dati e le **regole di coerenza**, così da poter **verificare** le trasformazioni applicate |

**Pre-elaborazione dei dati** (p. 56): la tecnica che converte i **dati grezzi** in un **dataset pulito**. Quando i dati arrivano da fonti diverse o in formato grezzo, non sono (sempre) pronti per l’analisi. Comprende quattro operazioni:

1. **pulizia** dei dati;
2. **integrazione** dei dati;
3. **trasformazione** dei dati;
4. **riduzione** dei dati.

<!-- SOURCE_VISUAL id="IS06-V17" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="56" type="schema" description="Le quattro operazioni della pre-elaborazione: pulizia, integrazione, trasformazione, riduzione" reason="Schema delle operazioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Che cosa cercare con pre-elaborazione e visualizzazione** (p. 57). Sono tra i primi passi per **capire i dati**. Servono a trovare:
> - **outlier**;
> - **errori**;
> - **valori mancanti**;
> - **scale diverse**;
> - **distribuzioni**;
> - **cluster** nel dataset;
> - **feature salienti**.
>
> Il docente sottolinea: **è un elenco importante da ricordare**. Le prime voci riguardano la **preparazione dei dati**, le ultime l’**ingegneria delle feature**.

**Perché la pre-elaborazione è così importante?** (p. 58). Perché i dati reali **non sono formattati**:

| Problema | Cause tipiche |
|---|---|
| **dati inaccurati o mancanti** | raccolta non continua, errori di inserimento, problemi tecnici di sensori o trasmissione, DoS, ritardi |
| **dati rumorosi, errati, outlier** | problemi tecnologici del dispositivo di acquisizione, errori umani nell’inserimento |
| **dati incoerenti** | **duplicati**, errori di inserimento, codici o nomi sbagliati: in generale **violazioni dei vincoli** sui dati |

> 💡 **Scale diverse: normalizzazione (nota didattica).** Due tecniche classiche per portare le feature su scale confrontabili:
> $$x' = \frac{x - x_{\min}}{x_{\max} - x_{\min}} \in [0, 1] \qquad\qquad z = \frac{x - \mu}{\sigma}$$
> La prima (min-max) è sensibile agli outlier; la seconda (z-score) centra i dati in media nulla con deviazione standard unitaria. I parametri ($x_{\min}$, $x_{\max}$, $\mu$, $\sigma$) vanno calcolati **solo sui dati di addestramento**, altrimenti si introduce data leakage (Lezione 05, §1.2).

#### **6.3. Strumenti, agenti e LLM**

_(slide pp. 55, 59–63)_

**Strumenti di pre-elaborazione** (p. 55):

| Strumento | Descrizione |
|---|---|
| **MATLAB** | vedi le prossime slide e lezioni |
| **R** | ambiente con molti pacchetti per la pre-elaborazione, come **dplyr** |
| **Weka** | raccolta di algoritmi di ML per il **data mining**, con strumenti di pre-elaborazione |
| **RapidMiner** | piattaforma **open source** di analisi predittiva per il data mining |
| **Python** | linguaggio con molte librerie per la pre-elaborazione |
| **agenti** | Claude Code/Cowork, Codex, Gemini, … |

**Agenti di AI per il data wrangling** (pp. 59–60):

- **Claude Code**, **Cowork**, **Codex CLI**, **Gemini CLI**: pulizia **autonoma** dei dati, **inferenza dello schema**, **risoluzione delle entità**, generazione di **espressioni regolari** a partire da esempi;
- uso tipico: «dato questo CSV disordinato con **formati di data e unità misti**, normalizzalo» → l’agente **scrive, esegue e testa** la pipeline;
- il ruolo umano si sposta **dallo scrivere la trasformazione al validarla**.

La p. 60 estende l’uso degli agenti alla **creazione di database**.

<!-- SOURCE_VISUAL id="IS06-V18" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="60" type="schermata" description="Agenti di AI per il data wrangling e la creazione di database" reason="Pagina con sola schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**LLM per l’estrazione strutturata da dati non strutturati** (p. 61):

- **input**: testo libero (referti, email, note cliniche, PDF) → **output**: record **JSON tipizzati** o Markdown;
- **sostituisce** gran parte delle pipeline di **NER** e di parsing basato su regole;
- esempio: **1000 referti di laboratorio** in PDF → un **DataFrame** con **15 colonne normalizzate**, con un solo prompt.

> 📌 **Named Entity Recognition** (NER, p. 61). Tecnica classica di NLP che **estrae entità con nome** dal testo libero: persone, luoghi, date, quantità, organizzazioni, codici medici, ecc.

**Database vettoriali: il nuovo livello di memorizzazione dei dati non strutturati** (p. 62):

- esempi: **Qdrant**, **Pinecone**, **pgvector**, **ChromaDB**, **Weaviate**, **Milvus**;
- memorizzano **embedding** (vettori densi, tipicamente di **384–3072 dimensioni**) invece di testo o immagini grezzi;
- permettono **ricerca semantica**, **RAG** e **deduplicazione per significato**, non per corrispondenza di stringhe;
- pipeline tipica: documento grezzo → **modello di embedding** → **database vettoriale** → **query di similarità**.

> 📌 **Similarità coseno (nota didattica).** La query di similarità confronta di solito i vettori con la similarità coseno:
> $$\cos(\mathbf{u}, \mathbf{v}) = \frac{\mathbf{u} \cdot \mathbf{v}}{\lVert \mathbf{u} \rVert \, \lVert \mathbf{v} \rVert} \in [-1, 1]$$
> Due frasi con significato simile hanno embedding vicini (coseno prossimo a 1) anche se non condividono parole: per questo la deduplicazione «per significato» trova «auto rossa» e «vettura di colore rosso».

> ⚠️ **Attenzione: GIGO vale ancora** (p. 63).
> - Gli agenti **inventano schemi** (allucinazioni), le estrazioni degli LLM **derivano** nel tempo, gli embedding **riflettono i bias** dell’addestramento.
> - **Validare sempre**: controlli di **tipo** e di **intervallo**, **riconciliazione** tra fonti, **controlli a campione** fatti da persone.
> - Regola: **assistito dall’AI ≠ affidato all’AI** (*AI-assisted ≠ AI-trusted*).
> - Lezione appresa: nel 2026 la preparazione dei dati è **ancora il passo 2** del flusso di lavoro del ML, ma **il passo 2 stesso ora usa l’AI**.

---

### **7. Dati mancanti**

_(slide pp. 64–75)_

#### **7.1. Rappresentazione e origine**

> 📌 **NaN** (p. 65). *Not a Number*: un valore di un tipo numerico che si interpreta come **indefinito, non rappresentabile o mancante**, codificato secondo lo standard **IEEE 754**.

> ⚠️ **NaN esiste solo per i numeri in virgola mobile** (p. 65). Gli interi non possono contenere NaN: per questo le librerie moderne (pandas `Int64`, Arrow, Polars) hanno introdotto un marcatore **NA** indipendente dal tipo per le colonne che ammettono valori nulli.

> 💡 **Una proprietà di NaN (nota didattica).** Per IEEE 754 un NaN **non è uguale a nulla, nemmeno a se stesso**: `NaN == NaN` è falso. Per trovarlo si usano funzioni dedicate come `isnan` in MATLAB o `isna` in pandas; e qualsiasi somma che coinvolge un NaN dà NaN, per cui una media calcolata senza gestirli risulta NaN.

**Esempio: tre ospedali che uniscono i dati** (p. 66). Tre ospedali uniscono i dati dei loro pazienti sugli **esami del sangue**, ma i laboratori hanno **sistemi e strumenti diversi** con un **numero diverso di feature**. Qui l’**armonizzazione** è cruciale, perché le fonti sono diverse: per esempio mg/dL e mmol/L.

> 💡 **La conversione dipende dalla sostanza (nota didattica).** Da mg/dL a mmol/L si divide per la massa molare (in g/mol) e si moltiplica per 10. Per il **glucosio** ($M \approx 180{,}16$ g/mol):
> $$c\,[\text{mmol/L}] = \frac{c\,[\text{mg/dL}] \times 10}{180{,}16} \approx \frac{c\,[\text{mg/dL}]}{18}$$
> quindi 90 mg/dL ≈ 5,0 mmol/L. Il fattore **cambia per ogni analita**: usare lo stesso numero per colesterolo e glucosio è un errore di armonizzazione.

<!-- SOURCE_VISUAL id="IS06-V19" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="66" type="tabella" description="Tre ospedali con esami del sangue su feature diverse e valori mancanti" reason="Struttura delle tabelle degli ospedali visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2. Eliminare feature o record?**

**Rimuovere le feature incomplete** (p. 67). Si **cancella** la colonna? E se la **feature n. 12** fosse proprio quella che serve a **rilevare il tumore** studiato?

**Rimuovere i vettori incompleti** (p. 68). «Addio Jamie e il signor “35 Male”»: da **6 record** si passa a **4** completi. Questa **perdita di informazione** è accettabile per la tua applicazione?

<!-- SOURCE_VISUAL id="IS06-V20" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="67" type="tabella" description="Rimozione della feature 12 incompleta" reason="Tabella dell’esempio visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS06-V21" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="68" type="tabella" description="Rimozione dei record incompleti: da 6 a 4 record" reason="Tabella dell’esempio visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Un buco «topologico»** (p. 69). Nel piano delle feature $f_1$, $f_2$ i campioni sono «scarto» (x) o «buono» (o). La **matrice dei dati è completa**, ma lo **spazio delle feature non è coperto uniformemente**. Senza un’adeguata **visualizzazione dei dati** è difficile accorgersene.

> 💡 **Perché è pericoloso.** Nessuna cella è NaN, quindi nessun controllo automatico segnala il problema. Ma nella regione vuota il modello **non ha mai visto esempi** e deciderà a caso: è il limite della generalizzazione (Lezione 03, §4.4).

<!-- SOURCE_VISUAL id="IS06-V22" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="69" type="grafico" description="Dati completi ma spazio delle feature f1-f2 non coperto uniformemente" reason="Regione vuota visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.3. Tre strategie**

**1. Ignorare il record mancante** (p. 70). È il metodo **più semplice ed efficiente**, ma:

- **come li gestisce il tuo algoritmo** di apprendimento?
- **non va usato** quando i valori mancanti sono **moltissimi**;
- **non va usato** quando il problema si può **risolvere alla radice**: debug, riprogettazione, ripetizione dell’esperimento, invece di ignorare la causa.

**2. Riempire a mano i valori mancanti** (p. 71). È **uno dei metodi migliori**, ma con dataset **grandi** e molti valori mancanti diventa **troppo lento**. Nelle **sperimentazioni mediche** ogni singolo dato è **importante e costoso**: escludere un vettore **non è una buona prima strategia**. La slide scherza: «Scusate, mi mancano alcuni valori dai sensori… possiamo rifarlo?»

**3. Riempire con valori calcolati** (p. 72):

- **media**, **moda** o **mediana** dei valori osservati;
- trovare la **colonna o il vettore più simile** e copiarne i valori, per esempio con un classificatore **k-NN**;
- **generare** i valori con un algoritmo di ML o di deep learning;
- **svantaggio**: può **introdurre bias** nei dati.

> 📌 **Imputazione con la media (nota didattica).** Se la feature $j$ è osservata sull’insieme di campioni $O_j$, un valore mancante si sostituisce con
> $$\hat{x}_{ij} = \frac{1}{|O_j|} \sum_{k \in O_j} x_{kj}$$
> La media **riduce artificialmente la varianza** della feature e **indebolisce le correlazioni** con le altre: è uno dei bias di cui parla la slide.

**Un caso base: il modello lineare** (p. 73). La serie $F$ viene riempita con **interpolazione lineare**; $TF$ indica i valori di $F$ riempiti. **La scelta del modello** usato per interpolare i punti mancanti **influenzerà l’apprendimento** del modello successivo.

> 📌 **Interpolazione lineare (nota didattica).** Se il valore manca all’istante $t$, compreso tra due istanti noti $t_1 < t < t_2$:
> $$\hat{x}(t) = x(t_1) + \frac{x(t_2) - x(t_1)}{t_2 - t_1}\,(t - t_1)$$

<!-- SOURCE_VISUAL id="IS06-V23" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="73" type="grafico" description="Serie F con valori mancanti riempiti per interpolazione lineare (TF)" reason="Andamento della serie visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Soluzioni possibili in breve** (pp. 74–75):

| Passo o strategia | Note |
|---|---|
| **passo 0: quantificare il problema** | i valori mancano per **righe** o per **colonne**? quanti sono? |
| **non fare nulla** | lasciare che l’algoritmo gestisca i valori mancanti. Va bene, **ma che cosa sta facendo?** Spiegabilità e cross-validation possono essere compromesse; alcuni metodi ignorano i dati, altri **si bloccano** o si comportano in modo erratico |
| **riempire a mano** | se possibile, **chiedendo all’esperto** distribuzione e intervalli dei dati |
| **approccio statistico** | media, moda, mediana, **valore tabù** (!) o costante |
| **k-Nearest Neighbor** | trovare il vettore o i vettori **più simili** e copiarne i valori mancanti |

> ⚠️ **Feature categoriche** (p. 74). Con una feature **categorica** la media produce un **numero reale** che non corrisponde a nessuna categoria: la media tra «rosso» = 1 e «blu» = 3 non è «verde» = 2. Per queste feature si usano la **moda** o il k-NN.

> 💡 **Il valore tabù (nota didattica).** È un valore impossibile per la feature (per esempio −999 per un’età) usato per marcare il dato mancante. È pratico, ma pericoloso: se non viene gestito, il modello lo tratta come un valore vero e la media della colonna crolla. Il punto esclamativo della slide invita proprio alla prudenza.

<!-- SOURCE_VISUAL id="IS06-V24" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="75" type="schema" description="Riepilogo delle strategie per i dati mancanti" reason="Schema di riepilogo presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **8. Dati strutturati e non strutturati**

_(slide pp. 76–84)_

La p. 77 introduce graficamente il confronto.

<!-- SOURCE_VISUAL id="IS06-V25" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="77" type="infografica" description="Confronto tra dati strutturati e non strutturati" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

| | **Dati strutturati** («bene!», p. 78) | **Dati non strutturati** («male…», p. 79) |
|---|---|---|
| Dove stanno | di solito in **database relazionali** (RDBMS) | in qualunque altro posto, anche in database **non relazionali** (NoSQL) |
| Forma | campi con dati di **lunghezza definita**: numeri di telefono, codici fiscali, CAP; anche stringhe di lunghezza variabile, come i nomi, dentro **record** | hanno una **struttura interna**, ma **non** secondo **modelli o schemi predefiniti** |
| Origine | umana o automatica, purché creati **dentro una struttura RDBMS** | testuali o non testuali, generati da **persone** o **macchine** |
| Ricerca | **facilmente interrogabili** da query umane e da algoritmi, grazie a tipi di dato e nomi dei campi (alfabetico, numerico, valuta, data) | gli strumenti di analisi tradizionali **non riescono a interpretarli** direttamente |

La p. 80 presenta i **dati semi-strutturati**, a metà strada.

> 💡 **Semi-strutturati (nota didattica).** Hanno etichette o marcatori che separano gli elementi, ma senza uno schema rigido: JSON, XML, file di log con campi ricorrenti. L’**email** è l’esempio della p. 81: i **metadati** (mittente, data, oggetto) sono strutturati, il **corpo del messaggio** no.

<!-- SOURCE_VISUAL id="IS06-V26" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="80" type="infografica" description="Dati semi-strutturati" reason="Pagina con sola infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Dati non strutturati generati da persone** (p. 81):

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

**Dati non strutturati generati da macchine** (p. 82):

| Tipo | Esempi |
|---|---|
| **immagini satellitari** | dati meteo, forme del territorio, movimenti militari |
| **dati scientifici** | esplorazione petrolifera e del gas, esplorazione spaziale, immagini sismiche, dati atmosferici |
| **sorveglianza digitale** | foto e video di sorveglianza |
| **dati dei sensori** | traffico, meteo, sensori oceanografici |

**Un esempio web di trasformazione dei dati** (p. 83). **RDFa** (*Resource Description Framework in Attributes*) è una **raccomandazione W3C** che aggiunge a HTML, XHTML e ai documenti XML un insieme di **attributi** per incorporare **metadati ricchi** nelle pagine web: così una parte del contenuto di una pagina diventa leggibile dalle macchine.

<!-- SOURCE_VISUAL id="IS06-V27" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="83" type="esempio di codice" description="Pagina web arricchita con attributi RDFa" reason="Esempio di markup presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Reti neurali e dati non strutturati** (p. 84). **Non è strettamente obbligatorio** avere dati strutturati per usare il ML. Esempi di **classificazione del testo**:

- capire il **sentiment** del pubblico dai social media;
- rilevare email **spam** e non spam;
- **etichettare automaticamente** le richieste dei clienti;
- **categorizzare** gli articoli di notizie in argomenti predefiniti.

<!-- SOURCE_VISUAL id="IS06-V28" source="Lesson_06_DataGathering_DataPreprocessing_DataHarmonization.pdf" page="84" type="infografica" description="Esempi di classificazione del testo con reti neurali" reason="Icone degli esempi visibili solo nell’infografica" -->
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

_(slide p. 85)_

> ✅ **Punti principali** (p. 85).
> - Usare un dataset **senza lavoro preliminare** è **inutile, pericoloso** e fa perdere tempo: **GIGO**.
> - **Studiare, pulire, armonizzare** i dati e **capirne le relazioni di base** è **obbligatorio**: unità e convenzioni comuni (Mars Climate Orbiter, parco eolico), orologi sincronizzati (NTP, PTP).
> - La preparazione dei dati è il **passo 2** del flusso di lavoro: pre-elaborazione e data wrangling, alla ricerca di outlier, errori, valori mancanti, scale diverse, distribuzioni, cluster e feature salienti.
> - Oggi **agenti, LLM e database vettoriali** accelerano il passo 2, ma **assistito dall’AI non significa affidato all’AI**: validare sempre.
> - **Gestire i dati mancanti**: quantificare il problema, poi scegliere tra ignorare, riempire a mano, statistiche o k-NN, consapevoli dei bias introdotti.
> - **Dati strutturati e non strutturati**: il ML funziona anche sui secondi.
