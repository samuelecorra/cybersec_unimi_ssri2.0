## **Lezione 10: Analisi esplorativa dei dati e feature engineering — estrazione delle feature, scaling, paradosso di Simpson ed EDA**

La lezione si colloca nel **passo 2** del flusso di lavoro del machine learning, la preparazione dei dati, e ne affronta tre facce. La prima è l’**estrazione delle feature**: un segnale grezzo, misurato nel tempo, diventa una sequenza di vettori compatti calcolati su finestre scorrevoli, nel dominio del tempo e in quello della frequenza; un esempio audio segue la catena completa fino ai coefficienti **MFCC**. La seconda è la **feature engineering** con lo **scaling**: come trasformare le feature estratte in input che un modello possa imparare senza che una sola unità di misura domini e senza trapelare informazione dal test. La terza è l’**analisi esplorativa dei dati** (EDA, *Exploratory Data Analysis*): statistiche descrittive, forme delle distribuzioni, outlier e il **paradosso di Simpson**, che mostra come un confronto aggregato possa invertire la conclusione vera di ciascun sottogruppo. La lezione si chiude estendendo l’EDA dall’input all’intero modello: pesi, attivazioni e uscite.

> **Fonte.** La lezione segue il PDF dell’edizione 2026/27 `sources_2026-27/L10-web.pdf` (82 pagine). I segnaposto per le figure indicano la pagina di quel PDF; il materiale dell’edizione precedente non più presente nelle slide è conservato nel §9.


### **0. Panoramica**


> 💡 **Prossima simulazione d’esame completa.** Avvicinandosi alla metà del corso, nella **Lezione 13 o 14** si svolgerà una **simulazione d’esame completa** per verificare la preparazione e il metodo di studio. Inoltre, al termine di ogni lezione o gruppo di lezioni vengono proposte **brevi simulazioni in presenza**, discusse insieme per valutare la comprensione degli argomenti. Sono esercizio e feedback, **non un’ulteriore prova valutata**.

La trattazione si articola nei seguenti temi:

1. **estrazione delle feature**: finestre, feature nel tempo e in frequenza;
2. **un esempio sonoro**: dal parlato alle bande mel e ai vettori MFCC;
3. **feature engineering** e **scaling** degli input del modello;
4. **paradosso di Simpson**: gruppi nascosti che invertono una conclusione;
5. **EDA, prima parte**: statistiche, distribuzioni, outlier;
6. **diagnostica dell’apprendimento**: input, pesi, attivazioni;
7. **pratica**: un laboratorio facoltativo e quattro dataset;
8. punti principali.

#### **0.1. Il passo 2 del flusso di lavoro: preparare i dati**

Prima di modellare si **organizzano**, si **esplorano** e si **ingegnerizzano** i dati. Il passo 2 del flusso di lavoro del ML si divide in tre sottopassi:

| Sottopasso | Contenuto | Dove |
|---|---|---|
| **2a)** *data munging* / *wrangling* | pulire e strutturare i dati grezzi | Lezione 6 |
| **2b)** **analisi esplorativa dei dati** | capire che cosa c’è nei dati | **prima parte in questa lezione**, visualizzazione nella prossima |
| **2c)** **feature engineering** | progettare la rappresentazione che il modello riceve | **questa lezione** |

<!-- SOURCE_VISUAL id="IS10-V01" source="L10-web.pdf" page="4" type="schema" description="Flusso di lavoro del ML con il passo 2 suddiviso in 2a wrangling, 2b EDA e 2c feature engineering" reason="Schema del flusso visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **0.2. L’analisi esplorativa dei dati per il ML**

La slide organizza gli strumenti dell’EDA in due rami: i **metodi descrittivi** (statistica descrittiva, trattati in questa lezione) e le **visualizzazioni** (trattate nella prossima).

| Ramo | Gruppo | Strumento | Che cosa misura |
|---|---|---|---|
| **statistica descrittiva** | **tendenza centrale** | **media** | somma di tutti i valori divisa per il numero di valori |
| | | **mediana** | valore centrale, quando i dati della distribuzione sono ordinati |
| | | **moda** | valore più frequente |
| | **misure di variazione** | **varianza** | quanto un insieme di numeri è disperso attorno alla media |
| | | **intervallo interquartile** | divide l’insieme dei dati in quartili |
| | | **deviazione standard** | dispersione dei dati rispetto alla media |
| | **forma** | **asimmetria** (*skewness*) | misura della simmetria |
| | | **curtosi** | misura del «picco» rispetto a una gaussiana |
| **visualizzazioni** | **1 dimensione** | istogramma (pochi dati), densità (molti dati); asimmetria positiva, nulla, negativa | |
| | **2 dimensioni** | scatter plot | |
| | **3 dimensioni** | grafici a bolle | |

<!-- SOURCE_VISUAL id="IS10-V02" source="L10-web.pdf" page="5" type="mappa concettuale" description="Mappa dell'EDA per il ML: statistica descrittiva (tendenza centrale, variazione, forma) e visualizzazioni in 1, 2 e 3 dimensioni" reason="Mappa con grafici di esempio visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **I metodi contano più della toolbox.** Lo stesso concetto statistico si può implementare in MATLAB, in Python o in un altro ambiente. Bisogna concentrarsi su **significato, ipotesi e interpretazione del risultato**, non sulla memorizzazione di un comando.

---

### **1. Estrazione delle feature: da un segnale a feature utili**


#### **1.1. Trasformare i segnali in feature utilizzabili**

L’estrazione delle feature converte **misure grezze** $x(t)$ in un **vettore di feature compatto e informativo**

$$f=\phi(x),$$

preservando le **proprietà rilevanti** per il compito di classificazione o regressione previsto.

**Esempi**:

- **audio** → descrittori temporali, spettrali o MFCC → classificazione / regressione;
- **immagini** → statistiche di intensità, descrittori di forma o locali → classificazione / regressione.

> 💡 **Lo stesso schema della Lezione 9.** $f=\phi(x)$ è la rappresentazione progettata $\phi_d$ della lezione precedente: qui il progettista sceglie quali statistiche calcolare su un segnale. Come lì, ciò che $\phi$ scarta non potrà essere recuperato dal modello.

#### **1.2. Da un segnale grezzo a un vettore di feature informativo**

La pipeline parte dal **segnale grezzo** $x(t)$ e lo divide in **finestre scorrevoli** $x^{(k)}$. Da ogni finestra si calcolano, in parallelo:

- **feature nel dominio del tempo**: media, RMS (*Root Mean Square*, valore quadratico medio), attraversamenti dello zero;
- **feature nel dominio della frequenza**: energia spettrale, baricentro spettrale.

I due gruppi si **uniscono** (*merge*) in un unico **vettore di feature** $f^{(k)}$: **un vettore per finestra**.

<!-- SOURCE_VISUAL id="IS10-V03" source="L10-web.pdf" page="9" type="schema" description="Segnale grezzo, finestre scorrevoli, feature nel tempo e in frequenza unite in un vettore f(k) per finestra" reason="Schema della pipeline con i grafici dei due domini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **La segmentazione è un passo comune.** I descrittori nel tempo e quelli in frequenza si calcolano **dalla stessa finestra**. Lo **scaling** si applica poi alle **feature numeriche estratte**, quando è appropriato, non al segnale grezzo.

Le tre feature temporali di base hanno definizioni semplici. Per una finestra di $L$ campioni $x_0,\dots,x_{L-1}$:

$$\mu=\frac{1}{L}\sum_{n=0}^{L-1}x_n,\qquad \text{RMS}=\sqrt{\frac{1}{L}\sum_{n=0}^{L-1}x_n^2},\qquad \text{ZCR}=\frac{1}{L-1}\sum_{n=1}^{L-1}\big[\,x_n\,x_{n-1}<0\,\big].$$

La media è il livello medio; l’RMS misura l’**energia** (per un segnale a media nulla coincide con la deviazione standard); il tasso di attraversamento dello zero (ZCR, *Zero-Crossing Rate*) conta quante volte il segnale cambia segno ed è un indice grezzo della **frequenza dominante** (formule standard, nota didattica).

#### **1.3. Controllare che cosa produce i dati**

Prima di calcolare qualunque feature bisogna sapere **da dove vengono i dati**:

| Aspetto | Che cosa registrare | Esempio |
|---|---|---|
| **provenienza** | tipo di sensore, calibrazione, versione del software, posizione | un accelerometro sulla carcassa del motore, calibrato il mese scorso, firmware 2.1 |
| **acquisizione** | timestamp, frequenza di campionamento, misure in streaming o memorizzate | campionamento a 10 kHz, registrato in raffiche memorizzate di 5 s |
| **contesto** | lo stesso valore numerico può significare cose diverse in regimi operativi diversi | lo stesso livello di vibrazione è **normale a pieno carico** e **allarmante al minimo** |

<!-- SOURCE_VISUAL id="IS10-V04" source="L10-web.pdf" page="10" type="figura" description="Sensore di vibrazione montato su un motore industriale" reason="Fotografie visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Il contesto fa parte del dato.** Un modello che riceve solo il numero, senza il regime operativo, non può distinguere i due casi dell’ultima riga. È lo stesso tema dei metadati delle etichette della Lezione 8: il valore da solo non basta, servono le informazioni che ne permettono l’interpretazione.

#### **1.4. Le finestre scorrevoli producono feature per frame**

Il segnale viene diviso in finestre (nella figura nove, da *window 1* a *window 9*), e da ciascuna si estrae un vettore di feature. Con $d=7$ feature **per canale**:

$$\text{media}\cdot\text{deviazione standard}\cdot\text{RMS}\cdot\text{range}\cdot\text{attraversamenti dello zero}\cdot\text{frequenza dominante } f^*\cdot\text{energia spettrale}.$$

Il vettore della finestra $k$ è

$$z_k=\begin{bmatrix}z_{k1}\\ z_{k2}\\ \vdots\\ z_{kd}\end{bmatrix},\qquad z_{k1}=\text{media},\ z_{k2}=\text{deviazione standard},\ \dots,\ z_{kd}=\text{energia spettrale}.$$

I vettori si **impilano come righe** nella **matrice delle feature** $Z$ (finestre × feature): **una riga = un campione** per il modello.

<!-- SOURCE_VISUAL id="IS10-V05" source="L10-web.pdf" page="11" type="schema" description="Nove finestre su un segnale, i vettori z1...z9 e la matrice delle feature Z" reason="Disposizione delle finestre sul segnale visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Quante finestre?** Per un segnale di lunghezza $N$, finestra di lunghezza $L$ e passo (*hop*) $H$:

$$N_w=\left\lfloor\frac{N-L}{H}\right\rfloor+1,\qquad \text{sovrapposizione}=\frac{L-H}{L}.$$

> 💡 **Esempio numerico (nota didattica).** Un segnale di 10 s diviso in finestre di 2 s con passo 1 s dà $N_w=\lfloor(10-2)/1\rfloor+1=9$ finestre, sovrapposte per $(2-1)/2=50\%$: sono le nove finestre della figura. La prima finestra copre 0–2 s, l’ultima 8–10 s.

> ⚠️ **Una finestra è un’osservazione derivata.** Finestre sovrapposte condividono campioni e non sono indipendenti. Se finestre dello stesso oggetto o dello stesso intervallo finiscono sia nel training sia nel test, il modello vede in anticipo parte del test: è il leakage di tipo B della Lezione 7. La suddivisione deve rispettare **gruppi e tempo**.

#### **1.5. Che cosa entra nel vettore di feature di un frame?**

Il vettore di un frame raccoglie descrittori di domini diversi:

- **dominio del tempo**: media, varianza, RMS, tasso di attraversamento dello zero, asimmetria e curtosi;
- **domini della frequenza e cepstrale**: baricentro spettrale, energia per banda, contrasto spettrale. I **coefficienti MFCC** derivano da una rappresentazione spettrale in **scala mel** (§2).

Un esempio di vettore è

$$f^{(k)}=\big[\text{RMS},\ \text{ZCR},\ \text{Centroid},\ \text{MFCC}_1,\ \dots,\ \text{MFCC}_K\big]^{(k)}.$$

L’intero segnale diventa una **sequenza di vettori**

$$F=\big[f^{(1)},\ f^{(2)},\ \dots,\ f^{(m)}\big],$$

con $m$ finestre e un vettore $f^{(k)}$ per ciascuna (qui disegnati come **una colonna per finestra**).

<!-- SOURCE_VISUAL id="IS10-V06" source="L10-web.pdf" page="12" type="schema" description="Matrice F con una colonna per finestra e righe RMS, ZCR, centroid, MFCC1..." reason="Disposizione della matrice visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Il baricentro spettrale (nota didattica).** È la «frequenza media» pesata dalla potenza: $\text{Centroid}=\sum_k f_k\,P[k]\,/\sum_k P[k]$. Un suono «brillante» (molte alte frequenze) ha baricentro alto, uno «cupo» basso.

> ⚠️ **Righe o colonne?** In questa slide ogni finestra è una **colonna**, nella precedente una **riga**. Le due convenzioni coesistono nel corso e nelle librerie (§3.2): bisogna sempre controllare quale si sta usando.

#### **1.6. Rappresentazione nel tempo o in frequenza?**

- Il **dominio del tempo** mostra **come il segnale cambia nel tempo**.
- Il **dominio della frequenza** rivela le **componenti periodiche** e la loro **intensità**.
- Uno **spettro di potenza** mostra come la potenza del segnale si distribuisce tra le frequenze.

<!-- SOURCE_VISUAL id="IS10-V07" source="L10-web.pdf" page="13" type="grafico" description="Lo stesso segnale nel dominio del tempo e nel dominio della frequenza" reason="Grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Spettro di ampiezza e spettro di potenza non sono la stessa cosa.** Uno **spettro di ampiezza** (*magnitude spectrum*) mostra il modulo $|X(f)|$ delle componenti; lo **spettro di potenza** ne mostra il quadrato, $|X(f)|^2$ (normalizzato). Il quadrato accentua le componenti forti e attenua le deboli, per cui i due grafici hanno forme diverse e vanno distinti. Lo spettro di una finestra di $N$ campioni a frequenza $f_s$ si calcola con la trasformata discreta di Fourier, $X[k]=\sum_{n=0}^{N-1}x[n]\,e^{-j2\pi kn/N}$, e il bin $k$ corrisponde alla frequenza $f_k=k\,f_s/N$.

#### **1.7. Tempo contro frequenza: un segnale industriale periodico**

L’esempio confronta due segnali di vibrazione di un cuscinetto, uno sano e uno con un difetto. **Nel tempo i due segnali sembrano uguali**: RMS **1,16** contro **1,19**, curtosi **2,9** contro **3,0**. Nello **spettro dell’inviluppo** il difetto compare invece come **picchi alla frequenza BPFO e ai suoi multipli**. I segnali sono sintetici e illustrativi.

<!-- SOURCE_VISUAL id="IS10-V08" source="L10-web.pdf" page="14" type="grafico" description="Due segnali di vibrazione simili nel tempo e i loro spettri dell'inviluppo, con i picchi del difetto alla BPFO e multipli" reason="Forme dei segnali e picchi spettrali visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Che cos’è la BPFO (nota didattica).** BPFO (*Ball Pass Frequency, Outer race*, frequenza di passaggio delle sfere sull’anello esterno) è la frequenza con cui le sfere di un cuscinetto passano su un punto dell’anello esterno. Se lì c’è un difetto, ogni passaggio produce un piccolo urto: una serie di impulsi periodici. Gli urti sono deboli e si perdono nell’RMS complessivo, ma la loro **periodicità** si concentra in uno spettro a frequenze precise. L’**inviluppo** (l’andamento dell’ampiezza del segnale, ottenuto per demodulazione) mette in evidenza proprio la ripetizione degli urti.

> 📌 **La rappresentazione decide che cosa è visibile.** Le feature nel tempo (RMS, curtosi) non vedono il difetto; quelle in frequenza sì. È il principio della Lezione 9 — la rappresentazione determina che cosa significa «simile» — applicato alla diagnostica industriale.

#### **1.8. Lo spettrogramma: la frequenza che cambia nel tempo**

Uno **spettrogramma** visualizza l’ampiezza o la potenza del segnale **in funzione sia del tempo sia della frequenza**; **non è un unico spettro globale**. Si ottiene calcolando lo spettro di ogni finestra e affiancando gli spettri come colonne: l’asse orizzontale è il tempo (l’indice della finestra), quello verticale la frequenza, il colore la potenza.

<!-- SOURCE_VISUAL id="IS10-V09" source="L10-web.pdf" page="15" type="grafico" description="Spettrogramma con potenza in funzione del tempo e della frequenza" reason="Spettrogramma visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Un esempio di periodicità (nota didattica).** Un movimento ripetuto 12 volte in 10 secondi ha frequenza $f=12/10\ \text{s}=1{,}2$ Hz: nello spettro compare un picco a 1,2 Hz anche quando, nel tempo, il segnale appare irregolare. Se la ripetizione rallenta a metà registrazione, uno spettro globale mostrerebbe due picchi senza dire quando; lo spettrogramma mostra lo **spostamento del picco nel tempo**.

---

### **2. Caso d’uso: dal parlato ai vettori MFCC**


*«Everybody dance now»*: da 22 516 campioni a 12 numeri per finestra.

#### **2.1. Il campione: una frase parlata**

| Campo | Valore |
|---|---|
| **frase** | «*Everybody dance now*» (3 parole) |
| **durata** | 1407 ms, tagliata al primo campione sonoro; il parlato termina a 1234 ms |
| **formato** | WAV, mono, 16 bit, frequenza di campionamento $f_s=16$ kHz (16 000 numeri al secondo) |
| **campioni** | $N=1{,}407\ \text{s}\times 16\,000=22\,516$ numeri in $[-1,1]$ (circa 45 kB) |
| **sorgente** | voce di sintesi vocale (*text-to-speech*), non una registrazione commerciale |

<!-- SOURCE_VISUAL id="IS10-V10" source="L10-web.pdf" page="17" type="grafico" description="Forma d'onda della frase Everybody dance now" reason="Forma d'onda visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché circa 45 kB (nota didattica).** 22 516 campioni a 16 bit = 2 byte ciascuno: $22\,516\times 2=45\,032$ byte, cioè circa 45 kB.

#### **2.2. La catena di elaborazione**

| Passo | Operazione | Dimensione dell’output |
|---|---|---|
| segnale vocale $x[n]$ | 22 516 campioni, 1407 ms, 16 kHz | — |
| **finestratura** | finestre di 25 ms, passo 10 ms | $139\times 400$ |
| **FFT + filtri mel + log** | energia logaritmica in 26 bande mel | $139\times 26$ |
| **DCT** | si tengono 12 coefficienti | $139\times 12$ (MFCC) |
| **distanza** | si confrontano i vettori delle parole | $z=[c_1\ \dots\ c_{12}]^\top$ dalla parola di ogni finestra |

<!-- SOURCE_VISUAL id="IS10-V11" source="L10-web.pdf" page="18" type="schema" description="Catena: segnale, finestratura 139x400, FFT e filtri mel 139x26, DCT 139x12, distanza tra vettori di parola" reason="Schema a blocchi della catena" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Il numero di finestre si ricava dalla formula del §1.4 con $L=400$ e $H=160$ campioni: $N_w=\lfloor(22\,516-400)/160\rfloor+1=\lfloor 138{,}2\rfloor+1=139$.

> ⚠️ **Una semplificazione voluta.** La frase viene tagliata in **tre sezioni attorno alle parole**. Questo **non è riconoscimento vocale** (*speech-to-text*): lo scopo è mostrare che **pochi numeri per finestra** bastano per vedere che le tre parti differiscono e per **misurare quanto**.

#### **2.3. Che cosa vede il computer: 12 numeri per finestra**

- **Input**: 139 finestre × 12 coefficienti per frase.

Una finestra (25 ms) al centro di ciascuna parola, coefficienti $c_1\dots c_{12}$ (adimensionali):

| | **everybody** | **dance** | **now** |
|---|---:|---:|---:|
| centro della finestra [ms] | 322 | 752 | 1052 |
| $c_1$ | +0,3 | **+6,4** | +1,8 |
| $c_2$ | −5,4 | **+10,4** | −3,2 |
| $c_3$ | −3,0 | +5,0 | −0,5 |
| $c_4$ | −5,2 | +0,6 | −2,3 |
| $c_5$ | +4,5 | −4,7 | +1,2 |
| $c_6$ | −1,5 | +0,3 | −2,7 |
| $c_7$ | −0,8 | −2,7 | −2,8 |
| $c_8$ | −3,7 | −5,7 | −2,9 |
| $c_9$ | +1,6 | −0,4 | −1,6 |
| $c_{10}$ | −4,2 | −2,2 | −6,8 |
| $c_{11}$ | −1,1 | −1,9 | +1,4 |
| $c_{12}$ | −3,5 | −0,8 | −1,8 |

**Come leggere la tabella.**

- **Stessa riga, colonna diversa**: come un coefficiente cambia da una parola all’altra.
- *dance* ha $c_1$ e $c_2$ grandi (**+6,4**, **+10,4**) dove le altre parole sono vicine a zero o negative.
- **Una parola è un vettore di 12 numeri, non un suono.**

#### **2.4. Come si calcolano i coefficienti**

1. **Pre-enfasi**: $y[n]=x[n]-0{,}97\,x[n-1]$. Rafforza le alte frequenze deboli. Esempio: $0{,}50-0{,}97\cdot 0{,}40=0{,}112$.
2. **Finestre** di 25 ms (400 campioni), di **Hamming**, con passo 10 ms (160 campioni): sovrapposizione del **60%**.
3. **Spettro di potenza**: $P[k]=\big|\text{FFT}(y)[k]\big|^2/N$, con $N=512$; il bin $k$ si trova a $k\cdot 31{,}25$ Hz.
4. **Filtri mel**: $E[m]=\sum_k H_m[k]\,P[k]$, con $m=1,\dots,26$ e
$$\text{mel}(f)=2595\,\log_{10}\!\left(1+\frac{f}{700}\right).$$
5. **Logaritmo**: $L[m]=\ln\big(E[m]+10^{-10}\big)$.
6. **DCT**:
$$c_i=\sum_{m=1}^{26}L[m]\cos\!\left(\frac{\pi\,i\,(m-\tfrac12)}{26}\right),\qquad i=0,\dots,12.$$

| Simbolo | Significato |
|---|---|
| $x[n]$ | campione $n$ |
| $N$ | lunghezza della FFT |
| $k$ | bin di frequenza |
| $H_m[k]$ | filtro triangolare $m$ (valori da 0 a 1) |
| $E[m]$ | energia nella banda $m$ |
| $L[m]$ | energia logaritmica |
| $c_i$ | coefficiente $i$ |

**Esempi**: $\text{mel}(1000\ \text{Hz})\approx 1000$ mel; $E=100\Rightarrow L=4{,}6$; $E=1000\Rightarrow L=6{,}9$.

**Significato**: $c_0$ è il **volume complessivo** (qui scartato); $c_1\dots c_{12}$ descrivono la **forma dello spettro** (il tratto vocale), **non l’altezza della voce** (il pitch).

> 💡 **Perché ogni passo (nota didattica).**
> - **Pre-enfasi**: nella voce l’energia cala con la frequenza; sottrarre il 97% del campione precedente è un filtro passa-alto che riequilibra lo spettro.
> - **Hamming**: tagliare il segnale bruscamente ai bordi della finestra creerebbe componenti spurie; la finestra di Hamming porta dolcemente a zero i bordi.
> - **$N=512$**: la FFT è più efficiente con lunghezze potenze di 2; i 400 campioni vengono completati con zeri. La risoluzione è $f_s/N=16\,000/512=31{,}25$ Hz, e i bin utili sono $N/2+1=257$.
> - **Scala mel**: l’orecchio distingue bene le frequenze basse e male quelle alte; la scala mel è quasi lineare sotto 1 kHz e logaritmica sopra. Verifica: $2595\log_{10}(1+1000/700)=2595\log_{10}(2{,}4286)\approx 2595\cdot 0{,}3854\approx 1000$.
> - **Logaritmo**: anche la percezione del volume è logaritmica; il termine $10^{-10}$ evita $\ln 0$ nelle bande silenziose. Verifica: $\ln 100\approx 4{,}6$, $\ln 1000\approx 6{,}9$: un fattore 10 in energia diventa una differenza costante di $\ln 10\approx 2{,}3$.
> - **DCT**: le 26 energie di bande vicine sono fortemente correlate; la trasformata del coseno le **decorrela** e concentra l’informazione sulla forma dello spettro nei primi coefficienti. È una forma di compressione analoga alla PCA.

#### **2.5. Dal segnale allo spettrogramma agli MFCC**

Per ogni finestra $t$ del segnale $x[n]$ (1,4 s, 16 kHz) si calcola $|\text{FFT}|^2/N$: lo **spettro** $P[k]$ di quella finestra (257 valori) è **una colonna** dello spettrogramma $P[k,t]$. Quella colonna passa per il **banco di 26 filtri triangolari** $H_m[k]$, che producono le 26 energie $E[m]=\sum_k H_m[k]\,P[k]$; poi per il logaritmo (26 valori $L[m]$) e per la DCT (26 valori, di cui si tengono i primi 13, $c_0\dots c_{12}$).

<!-- SOURCE_VISUAL id="IS10-V12" source="L10-web.pdf" page="21" type="schema" description="Dalla finestra al suo spettro P[k] (colonna dello spettrogramma), banco di 26 filtri mel, logaritmo e DCT" reason="Schema con spettrogramma e filtri triangolari" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Una finestra, una colonna.** Una finestra dà **una colonna** dello spettrogramma; quella colonna è **un solo spettro** $P[k]$, l’input dell’estrazione delle feature. Gli MFCC sono quindi una compressione, colonna per colonna, dello spettrogramma.

#### **2.6. I coefficienti cepstrali in scala mel (MFCC)**

**Energia di banda.** Ogni **banda orizzontale** $m$ dello spettrogramma corrisponde a un **filtro triangolare** $H_m$: pesa lo spettro di potenza $P[k]$ della finestra e lo somma in **un solo numero**,

$$E[m]=\sum_k H_m[k]\,P[k].$$

**Esempio** (finestra nella parola *now*, banda $m=10$, circa 1,1 kHz): $E=9{,}8\cdot 10^6$, quindi $L=\ln E=16{,}1$.

**Dalle barre agli MFCC.** Le **26 barre** $L[m]$ sono **un profilo dello spettro**. La DCT lo trasforma in $c_0\dots c_{12}$; si tengono $c_1\dots c_{12}$, cioè la **forma delle barre**.

<!-- SOURCE_VISUAL id="IS10-V13" source="L10-web.pdf" page="22" type="grafico" description="Spettrogramma con una banda mel evidenziata, le 26 barre L[m] di una finestra e i coefficienti MFCC risultanti" reason="Grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché «cepstrali» (nota didattica).** Il *cepstrum* è lo «spettro del logaritmo dello spettro» (il nome inverte le prime sillabe di *spectrum*). Nella voce lo spettro è il prodotto tra la sorgente (le corde vocali, che fissano il pitch) e il filtro (il tratto vocale, che dà il timbro della vocale). Il logaritmo trasforma il prodotto in una somma; la DCT separa poi l’andamento lento (il filtro, nei primi coefficienti) dalle oscillazioni rapide (il pitch, nei coefficienti alti). Tenere solo 12 coefficienti significa tenere il **tratto vocale** e scartare il **pitch**.

#### **2.7. Tre viste allineate degli stessi 1,4 secondi**

La slide affianca, sullo stesso asse dei tempi, la **forma d’onda**, lo **spettrogramma** e la **mappa degli MFCC** della frase: tre rappresentazioni dello stesso segnale.

<!-- SOURCE_VISUAL id="IS10-V14" source="L10-web.pdf" page="23" type="grafico" description="Forma d'onda, spettrogramma e mappa MFCC allineati sugli stessi 1,4 secondi" reason="Tre grafici allineati visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.8. Dalle finestre alle parole: vettori e distanze**

Sull’estratto originale, tagliato al primo campione sonoro, le parole sono separate a **990** e **1480 ms**. Per ogni parola si calcola il **vettore medio** degli MFCC delle sue finestre; la **distanza euclidea** tra i vettori medi (12 dimensioni, adimensionale) vale:

| Coppia | Distanza |
|---|---:|
| everybody–dance | **2,5** |
| everybody–now | 5,4 |
| dance–now | 6,2 |

<!-- SOURCE_VISUAL id="IS10-V15" source="L10-web.pdf" page="24" type="grafico" description="Estratto originale con i tagli tra le parole a 990 e 1480 ms e le distanze tra i vettori medi" reason="Grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Una dimostrazione, non una valutazione.** È un unico estratto, tagliato a mano dall’inviluppo dell’energia. Le distanze dicono che, in questa registrazione, *everybody* e *dance* sono le parole più simili e *now* la più diversa; non dicono nulla sulla capacità di riconoscere parole in generale.

#### **2.9. Tre registrazioni a confronto**

La stessa frase viene analizzata in tre registrazioni diverse: il campione di sintesi, un’intervista e la versione musicale. Le clip sono tagliate al primo campione sonoro; l’intervista si ferma dove partono gli applausi (2,5 s). **Voce, musica e catena di registrazione cambiano i numeri**: bisogna confrontare il **pattern**. La scala degli MFCC è condivisa tra i pannelli.

<!-- SOURCE_VISUAL id="IS10-V16" source="L10-web.pdf" page="25" type="grafico" description="Mappe MFCC di tre registrazioni della stessa frase con scala condivisa" reason="Mappe visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**I tre spettrogrammi affiancati** usano la stessa scala di tempo e di dB in ogni pannello. Le linee tratteggiate segnano i tagli tra le parole; quelli dell’intervista sono le **due pause più lunghe**, nell’ipotesi che la frase sia la stessa.

<!-- SOURCE_VISUAL id="IS10-V17" source="L10-web.pdf" page="26" type="grafico" description="Tre spettrogrammi affiancati con stessa scala di tempo e dB e tagli tratteggiati tra le parole" reason="Spettrogrammi visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.10. Si possono distinguere le parole? Due componenti principali**

I 12 coefficienti $c_1\dots c_{12}$ di ogni finestra di 25 ms vengono proiettati sulle **prime due componenti principali**, stimate **separatamente per ogni clip**; le linee uniscono finestre consecutive. Nella clip originale e nell’intervista *now* si **separa** dal resto; nell’originale *everybody* e *dance* **si sovrappongono**.

<!-- SOURCE_VISUAL id="IS10-V18" source="L10-web.pdf" page="27" type="grafico" description="Proiezione delle finestre MFCC sulle prime due componenti principali per le tre clip, con le traiettorie delle parole" reason="Proiezioni visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Coerenza con le distanze (nota didattica).** La sovrapposizione di *everybody* e *dance* nel piano delle componenti principali concorda con la distanza più piccola del §2.8 (2,5), mentre *now* separato concorda con le due distanze maggiori (5,4 e 6,2). La PCA (Lezione 18) conserva le direzioni di massima varianza e permette di **vedere** in 2D una struttura che vive in 12 dimensioni; poiché è stimata separatamente per ogni clip, gli assi di clip diverse non sono confrontabili tra loro.

#### **2.11. Un campione ricostruito e un esercizio**

**Originale e ricostruzione.** Per ragioni di **diritto d’autore** la registrazione originale non è inclusa nelle slide, solo la sua analisi. Le viste allineate usano una **ricostruzione**: le stesse tre parole pronunciate da una voce di sintesi.

**Esercizio.** Procurarsi l’estratto originale (da una copia legittimamente posseduta, circa 2 s) e ripetere l’analisi:

1. convertirlo in WAV mono a 16 kHz e segnare i confini delle parole in ms (per esempio con Audacity);
2. eseguire la stessa pipeline e ottenere la tabella di $c_1\dots c_{12}$ per parola;
3. confrontare con il campione ricostruito: distanza tra i tre vettori medi e quale coppia di parole è la più vicina.

**Che cosa aspettarsi.** I numeri **non saranno identici**: voce e pitch diversi, musica sotto la voce, catena di registrazione diversa. Ciò che può sopravvivere è il **pattern** (quali parole sono simili o diverse). Va discusso perché.

> 💡 **Traccia per la discussione (nota didattica).** Gli MFCC descrivono il tratto vocale, quindi la stessa vocale pronunciata da voci diverse tende a produrre forme spettrali simili: è questo che può far sopravvivere il pattern. Ma il valore assoluto dei coefficienti dipende da microfono, equalizzazione, compressione e dalla musica, che aggiunge energia in bande che la voce non occupa. Per questo si confrontano **distanze relative** e **ordinamenti**, non numeri singoli.

---

### **3. Feature engineering e scaling: costruire e scalare gli input del modello**


#### **3.1. Dalle feature agli input del modello**

La pipeline completa è:

$$x(t)\ \xrightarrow{\text{finestratura}}\ x^{(k)}\ \xrightarrow{\text{estrazione}}\ f^{(k)}\ \xrightarrow{\text{feature engineering}}\ X,y\ \xrightarrow{\text{scaling}}\ X'\ \xrightarrow{\text{modello}}\ Y.$$

- **Situazione** (illustrativa): sensori di vibrazione su una macchina inviano dati a un server.
- **Input**: un vettore di feature per finestra, con $d$ feature ciascuno (RMS, ZCR, frequenza dominante, …). $X$: $d$ feature × $n$ campioni (in colonna); $y$: $n$ etichette.
- **Output da apprendere**: un **ID di classe per finestra** (per esempio *normale* / *usurato* / *guasto*).
- **Domanda**: come trasformare le feature in input da cui un modello possa imparare?

<!-- SOURCE_VISUAL id="IS10-V19" source="L10-web.pdf" page="30" type="schema" description="Pipeline finestratura, estrazione, feature engineering, scaling e modello con i sensori di vibrazione" reason="Schema con immagine del caso d'uso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. Dove si colloca la feature engineering**

> 📌 **EDA e feature engineering hanno ruoli diversi.** L’EDA **descrive** i dati; la feature engineering **cambia la rappresentazione** usata da un modello. Nel passo 2 del flusso siamo ora al sottopasso **2c**.

<!-- SOURCE_VISUAL id="IS10-V20" source="L10-web.pdf" page="31" type="figura" description="Collocazione della feature engineering rispetto all'EDA" reason="Figura raster della slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS10-V21" source="L10-web.pdf" page="32" type="schema" description="Flusso di lavoro del ML con evidenziato il sottopasso 2c feature engineering" reason="Schema del flusso visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**La feature engineering è iterativa**:

1. identificare **che cosa il compito richiede** dai dati;
2. proporre **trasformazioni candidate** delle feature;
3. ispezionare **distribuzioni**, **relazioni** e **casi di fallimento** delle feature;
4. **valutare** con un protocollo di training/validation **difendibile**;
5. **rivedere** la rappresentazione e iterare quando le evidenze lo giustificano.

Si ispeziona, si rivede e si verifica di nuovo **finché la rappresentazione non diventa utile e affidabile**.

#### **3.3. La tabella di apprendimento: $X$ e $y$**

- $X$ raccoglie le **feature estratte da ogni campione**.
- $y$ memorizza l’**ID di classe** o il **valore da apprendere**.
- In questo corso i **campioni** sono spesso disegnati **in colonna**; molte toolbox usano la convenzione **trasposta**.

| | $s_1$ | $s_2$ | $s_3$ | $s_4$ | $s_5$ | $s_6$ | $s_7$ | $s_8$ |
|---|---:|---:|---:|---:|---:|---:|---:|---:|
| feature 1 | 5,1 | 0,4 | 1,3 | 0,2 | 7,0 | 2,8 | 1,5 | 0,9 |
| feature 2 | 3,5 | 0,7 | 2,2 | 0,5 | 6,8 | 3,1 | 1,0 | 0,4 |
| feature 3 | 1,4 | 0,2 | 3,9 | 0,8 | 5,7 | 2,5 | 0,6 | 1,1 |
| feature 4 | 0,2 | 0,1 | 4,7 | 0,4 | 4,9 | 1,8 | 2,2 | 0,3 |
| $y$ | 1 | 0 | 3 | 1 | 2 | 0 | 1 | 3 |

Una **etichetta per campione**, allineata con la sua colonna di $X$.

> 📌 **Sapere sempre che cosa entra e che cosa si predice.** Bisogna sempre sapere **che cosa entra nel modello** e **qual è il target da predire**. Un errore di trasposizione (campioni scambiati con feature) produce un modello che «funziona» sintatticamente ma impara una cosa priva di senso.

#### **3.4. Il modello di AI: $Y=\text{FUNC}(X)$**

$$Y=\text{FUNC}(X)$$

- Per ogni dataset, nel machine learning o in una toolbox, **tutto consiste nel creare $X$ e $Y$**.
- $X$: le feature di ogni campione. $Y$: l’ID di classe, o il valore da apprendere.
- $\text{FUNC}$ è il **modello**, appreso da molte coppie $(X,Y)$: associa a un nuovo $X$ una **previsione** di $Y$.

**Notazione.** Qui le feature sono disegnate come righe e i campioni come colonne; in molte toolbox la tabella è trasposta (un campione per riga).

#### **3.5. Tre livelli di feature engineering**

La feature engineering agisce su tre livelli crescenti della tabella: **(A)** una feature alla volta, **(B)** tutte le feature insieme ma senza etichette, **(C)** l’intero dataset usando le etichette.

##### **3.5.1. Livello (A): una sola feature alla volta**

Si ottimizza **una singola feature** (una riga della tabella del §3.3) per volta. Operazioni tipiche: **conversione di unità**, **trattamento dei valori mancanti**, **scaling**. Anche una trasformazione di una sola feature può **cambiare le distanze** e quindi il comportamento successivo del modello. Esempio: convertire le unità di accelerazione in modo coerente **prima** di calcolare l’RMS.

| Operazione | Esempio |
|---|---|
| **armonizzazione delle unità** | centimetri in metri; gradi/s in radianti/s |
| **ispezione degli outlier** | identificare i valori sospetti **prima** di rifiutarli |
| **scaling** | min-max e standardizzazione |
| **trasformazioni non lineari** | logaritmo per dati positivi con asimmetria a destra |

**Esempio di catena**: i valori «120 cm, 1,3 m, 0,9 m» diventano, dopo l’**armonizzazione**, «1,20, 1,30, 0,90 m» e, dopo lo **scaling min-max**, «0,75, 1,00, 0,00».

> 💡 **Verifica del min-max (nota didattica).** Con $x_{\min}=0{,}90$ e $x_{\max}=1{,}30$: $(1{,}20-0{,}90)/0{,}40=0{,}75$, $(1{,}30-0{,}90)/0{,}40=1$, $(0{,}90-0{,}90)/0{,}40=0$. Senza armonizzazione, «120» e «1,3» sarebbero stati confrontati come numeri con scale diverse di un fattore 100, e il min-max avrebbe prodotto risultati privi di senso.

Queste operazioni sono **locali**, ma devono essere **giustificate dai dati e dal compito**.

##### **3.5.2. Livello (B): tutte le feature insieme, senza etichette**

Si ottimizzano **tutte le feature contemporaneamente**:

- creare **descrittori congiunti** quando i canali separati non catturano il compito;
- esplorare le **interazioni** tra feature e i **sensori correlati**;
- **non** rimuovere variabili **solo perché correlate**: la rilevanza **dipende dal compito**.

Le operazioni tipiche sono:

- rimuovere feature **fortemente correlate** solo quando le evidenze del compito lo supportano;
- creare **combinazioni più informative** delle misure grezze;
- usare la **PCA** e altri metodi di riduzione della dimensionalità, che ispezionano **solo la struttura di $X$**.

> 📌 **A questo livello le etichette non si usano ancora.** Tutte le operazioni del livello (B) guardano soltanto $X$.

> 💡 **Esempi (nota didattica).** Se «altezza in cm» e «altezza in pollici» sono entrambe presenti, la correlazione è 1 e una delle due è ridondante. Un descrittore congiunto è il BMI ($\text{massa}/\text{altezza}^2$), che combina due misure in una grandezza più informativa per molti compiti clinici. Due feature con correlazione di Pearson $|\rho|$ vicina a 1 portano quasi la stessa informazione; la PCA trova le direzioni di massima varianza risolvendo $\Sigma\,v_k=\lambda_k v_k$ sulla matrice di covarianza $\Sigma$ e proietta i dati sugli autovettori con gli autovalori più grandi (Lezione 18). Ma due sensori correlati in condizioni normali possono divergere proprio durante un guasto: per questo la correlazione da sola non giustifica la rimozione.

##### **3.5.3. Livello (C): l’intero dataset, con le etichette**

Si ottimizza **l’intero dataset usando le etichette**:

- la **selezione** e l’**ingegnerizzazione** delle feature vengono valutate **rispetto al target** $y$;
- si confrontano le rappresentazioni candidate con una **validazione su dati tenuti da parte** (*held-out*), **non con la sola accuratezza di training**;
- attenzione al **leakage** quando le etichette influenzano le decisioni di pre-elaborazione.

> ⚠️ **Il leakage del livello (C).** Se si sceglie quali feature tenere guardando la loro correlazione con $y$ su **tutto** il dataset, test compreso, la stima finale sarà ottimistica: la scelta ha già «visto» il test. La selezione va ripetuta **dentro** ogni fold di validazione (Lezione 14).

#### **3.6. Selezione, estrazione, engineering: non sono sinonimi**

| Termine | Definizione | Esempio |
|---|---|---|
| **selezione delle feature** | tenere un **sottoinsieme** delle variabili esistenti | tenere 4 descrittori su 20 candidati |
| **estrazione delle feature** | trasformare le misure in una **nuova rappresentazione** | finestra → RMS (una nuova feature) |
| **feature engineering** | progettazione **più ampia** di rappresentazioni e trasformazioni, che include estrazione e selezione dove appropriato | anche scaling, codifica, combinazioni |

Un valore RMS estratto da una finestra di vibrazione è una **nuova feature**; tenere solo 4 descrittori su 20 è **selezione**. Entrambe fanno parte della feature engineering.

#### **3.7. EDA e feature engineering si sovrappongono**

Entrambe usano la statistica. L’**EDA verifica le ipotesi**; la **feature engineering cambia la rappresentazione** per rendere un’applicazione **apprendibile e robusta**.

| | EDA | Feature engineering |
|---|---|---|
| **domanda** | che cosa rivelano i dati? | che cosa deve ricevere il modello? |
| **esempi** | gli estremi contano; confrontare i gruppi; forma della distribuzione | dal segnale alle feature; feature in frequenza; la tabella di apprendimento |

Il punto di contatto è la forma della distribuzione: nelle distribuzioni **asimmetriche** media, mediana e moda occupano **posizioni diverse**, in quelle **simmetriche coincidono**.

<!-- SOURCE_VISUAL id="IS10-V22" source="L10-web.pdf" page="42" type="grafico" description="Distribuzioni con asimmetria a sinistra, simmetrica e a destra, con posizioni di media, mediana e moda" reason="Forme delle distribuzioni visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.8. Scaling: imparare la forma, non solo la grandezza**

- Un **grande intervallo numerico** non significa che una feature sia **più importante**.
- Lo scaling può impedire che **un’unità di misura domini** i metodi basati su distanze.
- Per i modelli addestrati con il **gradiente**, uno scaling appropriato può **migliorare l’ottimizzazione**.
- Lo scaling **non è universalmente benefico**: per esempio molti modelli ad **albero** sono **insensibili** agli scaling monotoni.

Con la feature 1 in $[0,1000]$ e la feature 2 in $[0,1]$, in unità grezze la distanza dipende praticamente **solo dalla feature 1**; dopo lo scaling **contano entrambe**.

<!-- SOURCE_VISUAL id="IS10-V23" source="L10-web.pdf" page="43" type="grafico" description="Punti in unità grezze (feature 1 in 0-1000, feature 2 in 0-1) e dopo lo scaling, con le distanze" reason="Geometria dei punti visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché gli alberi sono insensibili (nota didattica).** Un albero di decisione (Lezione 12) divide i dati con soglie del tipo $x_j\le t$. Una trasformazione monotona crescente $g$ conserva l’ordine dei valori, quindi la soglia $t$ diventa $g(t)$ e la partizione resta identica. Le distanze euclidee, invece, cambiano con la scala: è il caso del $k$-NN visto nella Lezione 9.

#### **3.9. Quattro trasformazioni comuni**

| Trasformazione | Idea | Cautela |
|---|---|---|
| **min-max** | mappare in un intervallo scelto | **sensibile agli estremi** |
| **z-score** | sottrarre la media, dividere per la deviazione standard | presuppone **statistiche di training significative** |
| **clipping** | limitare i valori fuori dai limiti | può **distruggere eventi rari veri** |
| **logaritmo** | comprimere i valori positivi grandi | richiede un **trattamento definito** di zero e negativi |

<!-- SOURCE_VISUAL id="IS10-V24" source="L10-web.pdf" page="44" type="grafico" description="Curve output contro input di min-max, z-score, clipping e logaritmo" reason="Forme delle curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Regola d’oro.** I parametri della pre-elaborazione stimata (minimo, massimo, media, deviazione standard, soglie) si **apprendono solo sui dati di training**, poi si applica **la stessa trasformazione** ai dati di validation e di test.

#### **3.10. Min-max e z-score: invarianti diversi**

$$\text{min-max:}\quad x'=\frac{x-x_{\min}}{x_{\max}-x_{\min}},\qquad\qquad \text{z-score:}\quad z=\frac{x-\mu_{\text{train}}}{\sigma_{\text{train}}}.$$

| | Min-max | Z-score |
|---|---|---|
| **che cosa garantisce** | $x_{\min}\to 0$, $x_{\max}\to 1$: valori adattati in $[0,1]$ **sui dati di training** | la distribuzione di training ha **media circa 0** e **deviazione standard 1**, quando $\sigma_{\text{train}}>0$ |
| **dati nuovi** | i valori tenuti da parte **possono cadere fuori** da $[0,1]$ | possono avere media e deviazione diverse da 0 e 1 |

> ⚠️ **Le feature costanti vanno gestite esplicitamente.** Se una feature è costante sul training, $x_{\max}-x_{\min}=0$ e $\sigma_{\text{train}}=0$: la **divisione per zero** rende indefinite entrambe le espressioni. Una feature costante non porta informazione e di solito si rimuove; le librerie spesso la lasciano a 0 senza avvisare.

> 💡 **Esempio: il prezzo delle auto (nota didattica, dall’edizione precedente).** A 0 € non c’è nessuna auto, a 12 000 € ce ne sono alcune, a 250 000 € pochissime: una coda lunga verso i prezzi alti. Con $x_{\min}=5000$ € e $x_{\max}=250\,000$ €, un’auto da 20 000 € diventa $x'=15\,000/245\,000\approx 0{,}06$: quasi tutte le auto finiscono schiacciate vicino a 0, e un solo modello di lusso decide la scala. È il motivo per cui il min-max è «sensibile agli estremi».

#### **3.11. Quale trasformazione cambia quale proprietà?**

La slide applica le quattro trasformazioni alla **stessa variabile asimmetrica** e ne mostra gli istogrammi:

| Trasformazione | Effetto sulla distribuzione |
|---|---|
| **scaling in un intervallo** | cambia solo l’asse (qui in $[0,1]$): la **forma resta asimmetrica** |
| **clipping** | **taglia la coda** e accumula valori al limite (qui a 12) |
| **scaling logaritmico** | **comprime la coda**: la distribuzione diventa più simmetrica |
| **z-score** | centra e scala: media 0, ma la **forma resta asimmetrica** (valori fino a circa 6) |

<!-- SOURCE_VISUAL id="IS10-V25" source="L10-web.pdf" page="46" type="grafico" description="Quattro istogrammi della stessa variabile asimmetrica dopo scaling in intervallo, clipping, logaritmo e z-score" reason="Forme degli istogrammi visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Scopi diversi.** Scaling in un intervallo, clipping e logaritmo hanno **scopi diversi**. Min-max e z-score sono **trasformazioni lineari**: spostano e allungano l’asse, ma **non cambiano la forma** della distribuzione. Solo le trasformazioni **non lineari** (logaritmo, clipping) la modificano. Bisogna ispezionare la distribuzione risultante invece di applicarle meccanicamente.

#### **3.12. Il clipping è una politica, non un default**

- Una soglia può **limitare** i valori estremi.
- Un’euristica come $|z|>3$ presuppone che la soglia **abbia senso** per i dati e per il compito.
- **Non cancellare i guasti rari** se il compito reale è proprio il **rilevamento dei guasti**.

Nell’esempio la variabile grezza arriva fino a circa 25 e, dopo il clipping, è limitata al massimo di **4,0**: tutti i valori estremi si accumulano nell’ultimo bin.

<!-- SOURCE_VISUAL id="IS10-V26" source="L10-web.pdf" page="47" type="grafico" description="Istogramma della feature grezza con outlier e della stessa feature limitata a 4.0" reason="Istogrammi visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Clipping sullo z-score (nota didattica).** La forma tipica limita i valori a $\pm 3\sigma$:
> $$x'=\min\big(\max(x,\ \mu-3\sigma),\ \mu+3\sigma\big).$$
> Per una distribuzione normale circa il **99,7%** dei valori cade entro $\pm 3\sigma$, quindi il clipping tocca solo i casi più estremi. Ma $\mu$ e $\sigma$ sono essi stessi influenzati dagli outlier, e molte distribuzioni reali non sono normali: con code pesanti, $3\sigma$ può tagliare molti valori legittimi.

#### **3.13. Il logaritmo comprime una lunga coda destra**

- È utile per alcune variabili **fortemente positive** e **asimmetriche a destra**.
- **Riesprime le differenze moltiplicative** in modo più compatto: $\log(10x)-\log(x)=\log 10$ per qualunque $x$, quindi un fattore 10 diventa uno spostamento costante.
- Si sceglie $\log x$ per $x>0$, oppure si **giustifica esplicitamente** $\log(1+x)$ per $x$ non negativi.

L’esempio è il **numero di valutazioni per film**: la maggior parte dei film ne ha poche e pochi ne hanno moltissime (fino a circa 1400). Dopo il logaritmo la distribuzione si estende in modo più equilibrato tra 0 e 7.

<!-- SOURCE_VISUAL id="IS10-V27" source="L10-web.pdf" page="48" type="grafico" description="Istogramma del numero di valutazioni per film e del suo logaritmo" reason="Istogrammi visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Il logaritmo di zero.** $\log 0$ non è definito: se la feature può valere zero (un film senza valutazioni, un numero di acquisti) si usa $\log(1+x)$, ma la scelta va dichiarata, perché cambia l’interpretazione dei valori piccoli.

#### **3.14. Riepilogo: dalle feature grezze agli input del modello**

- **Input** (tabella adimensionale): $X$ di dimensione $d\times n$ più $y$ di dimensione $1\times n$.
- **Output**: $X'$ con la **stessa dimensione** $d\times n$, più i **parametri memorizzati** (per esempio $\mu_{\text{train}}$, $\sigma_{\text{train}}$ per feature).

**Passi**:

1. **una feature alla volta**: unità, outlier, scala;
2. **tutte le feature**: ridondanza, descrittori congiunti;
3. **con le etichette**: selezione giudicata su dati tenuti da parte.

> ✅ **Regola d’oro.** Lo scaling si stima **solo sui dati di training**, poi si applica la **stessa trasformazione** a validation e test. I parametri stimati sono parte del modello: vanno salvati e riapplicati identici in produzione.

---

### **4. Paradosso di Simpson: gruppi nascosti che invertono una conclusione**


#### **4.1. Perché l’EDA conta anche con una buona estrazione delle feature**

Anche una pipeline ben costruita — estrazione delle feature, scaling, regressione o classificazione — può imparare una **relazione fuorviante** quando **gruppi rilevanti restano nascosti**.

> 📌 **Paradosso di Simpson.** Una **tendenza aggregata** può **invertirsi all’interno di ciascun sottogruppo**. La domanda da porsi è: *nei nostri dati ci sono gruppi noti, o nascosti?*

#### **4.2. Il caso della regressione**

- **A. Ignorare i gruppi**: la tendenza complessiva appare **positiva**.
- **B. Rivelare i gruppi**: **dentro entrambi i gruppi** (A e B), la tendenza è **negativa**.

L’input $x$ è la feature osservata (asse orizzontale), il target $y$ l’etichetta numerica osservata (asse verticale). **Informazione mancante**: il **gruppo** $g$. I dati sono etichettati per la regressione, ma l’appartenenza al gruppo viene ignorata.

<!-- SOURCE_VISUAL id="IS10-V28" source="L10-web.pdf" page="52" type="grafico" description="Paradosso di Simpson nella regressione: tendenza aggregata positiva e tendenze negative nei gruppi A e B" reason="Nuvole di punti e rette visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Come nasce l’inversione (nota didattica).** Il gruppo B sta più in alto e più a destra del gruppo A. Dentro ciascun gruppo, all’aumentare di $x$, $y$ diminuisce; ma passando da A a B entrambe le variabili aumentano. Una sola retta su tutti i punti «unisce» i due gruppi e trova una pendenza positiva che non descrive nessuno dei due. Un modello addestrato senza la variabile $g$ impara proprio quella pendenza sbagliata.

#### **4.3. Due linee di produzione, due tipi di biscotti**

Un esempio industriale. Le due linee producono tre tipi di esiti:

- **biscotto facile**: forma semplice;
- **biscotto difficile**: forma complessa, si rompe più facilmente;
- **biscotto rotto**: non supera l’ispezione di qualità (QA, *Quality Assurance*).

Nell’illustrazione la **linea A** supera il controllo con 7 biscotti su 10 ispezionati (tasso del 70%), la **linea B** con 8 su 10 (80%).

<!-- SOURCE_VISUAL id="IS10-V29" source="L10-web.pdf" page="53" type="figura" description="Due linee di produzione di biscotti facili e difficili con controllo QA: 7/10 e 8/10" reason="Illustrazione visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

$$\text{tasso di superamento}=\frac{\text{biscotti che superano il QA}}{\text{biscotti ispezionati}}.$$

> ⚠️ **Che cosa non dice il tasso.** Il tasso di superamento dice, tra i biscotti esaminati, quale frazione è buona. **Non dice quanto è abile una linea**, perché dipende anche da **quali biscotti la linea produce**.

#### **4.4. Il caso della misura industriale**

**Domanda**: quale linea ha il **miglior tasso di qualità osservato**?

- **Record in input**: linea di produzione (A/B) e difficoltà del prodotto (facile/difficile).
- **Esito misurato**: ogni prodotto finito **supera o non supera** l’ispezione di qualità.
- **Metrica**: tasso di superamento QA $=$ prodotti superati / prodotti ispezionati.
- **Rischio**: le due linee producono **proporzioni diverse** di prodotti facili e difficili.

> 📌 **Non è ancora un classificatore.** Si stanno **misurando e confrontando esiti osservati**. I conteggi sono **sintetici**, a scopo illustrativo: **non è giustificata alcuna conclusione causale**.

#### **4.5. Ispezionare i gruppi prima di calcolare una sola media**

| Tipo di prodotto | Linea A: superati / totale | Linea B: superati / totale |
|---|---|---|
| **facile** | $19/20=95\%$ | $90/100=90\%$ |
| **difficile** | $72/100=72\%$ | $14/20=70\%$ |
| **tutti** | $91/120=75{,}8\%$ | $104/120=86{,}7\%$ |

**A ha il tasso più alto in ciascun sottogruppo**, eppure **B ha il tasso complessivo più alto**. Possono essere vere entrambe le cose? I rapporti sono calcolati dai conteggi; le percentuali sono arrotondate a un decimale dove necessario.

<!-- SOURCE_VISUAL id="IS10-V30" source="L10-web.pdf" page="56" type="grafico" description="Barre dei tassi QA: dentro i gruppi facile e difficile vince A, nell'aggregato vince B" reason="Diagramma a barre visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**L’inversione.** Dentro i gruppi *facile* e *difficile*, A ha il tasso più alto (95% contro 90%, 72% contro 70%). Dopo l’aggregazione, B ha il tasso più alto (86,7% contro 75,8%), perché i **mix di prodotti differiscono**.

#### **4.6. Perché accade: i pesi dei gruppi sono diversi**

Una **percentuale aggregata** è una **media pesata** delle percentuali dei sottogruppi, con pesi pari alle quote dei gruppi:

$$p_A=\frac{20}{120}(0{,}95)+\frac{100}{120}(0{,}72)=0{,}1583+0{,}6000=0{,}7583,$$

$$p_B=\frac{100}{120}(0{,}90)+\frac{20}{120}(0{,}70)=0{,}7500+0{,}1167=0{,}8667.$$

| Linea | Quota facili | Quota difficili |
|---|---:|---:|
| A | 16,7% | **83,3%** |
| B | **83,3%** | 16,7% |

> 📌 **L’inversione viene dalle proporzioni, non da un errore aritmetico.** La linea A produce soprattutto biscotti difficili, che passano meno spesso il controllo; la linea B soprattutto biscotti facili. Il tasso aggregato mescola due cose diverse: **quanto bene lavora la linea** e **che cosa le viene chiesto di produrre**.

#### **4.7. Confrontare con un mix di prodotti comune**

Si supponga di confrontare le due linee sotto lo **stesso mix ipotetico**: 50% di prodotti facili e 50% di difficili.

$$p^{\text{standard}}_A=0{,}5(0{,}95)+0{,}5(0{,}72)=83{,}5\%,\qquad p^{\text{standard}}_B=0{,}5(0{,}90)+0{,}5(0{,}70)=80{,}0\%.$$

Usare **pesi comuni** rimuove la differenza di composizione per tipo di prodotto in questo confronto descrittivo, e A torna a essere la linea migliore.

> ⚠️ **Standardizzare non è dimostrare.** La standardizzazione **non prova da sola un vantaggio causale né una significatività statistica**: con pochi campioni (20 prodotti in un gruppo) la differenza tra 72% e 70% può essere dovuta al caso, e il risultato dipende dal mix scelto come riferimento. Si ricordi la Lezione 7 (osservazioni rappresentative); più avanti, la Lezione 14 tratterà la validazione tra popolazioni diverse.

#### **4.8. Perché EDA e clustering contano**

- **Gruppi noti**: analizzare separatamente **macchina, tipo di prodotto, regime o fornitore** quando è rilevante.
- **Gruppi sconosciuti**: i **grafici esplorativi** e, più avanti, il **clustering** (Lezione 19) possono rivelare strutture non notate prima.
- **Modellazione cieca**: a un regressore o a un classificatore si può chiedere di spiegare osservazioni incompatibili **senza le variabili che le distinguono**.
- **Importante**: scoprire un gruppo **non stabilisce automaticamente una relazione causale**.

> 📌 **Prima dell’addestramento** bisogna verificare che gli input **descrivano adeguatamente il compito**. Nella prossima lezione si approfondiranno EDA e visualizzazione; più avanti il clustering servirà a indagare i gruppi sconosciuti.

---

### **5. EDA, prima parte: statistiche descrittive e controlli visivi**


#### **5.1. Statistiche descrittive: un outlier cambia il riassunto**

Vettore di esempio: $x=[1,2,3,4,50]$.

| Statistica | Valore |
|---|---:|
| media | 12,0 |
| mediana | 3,0 |
| deviazione standard | 21,27 |
| minimo | 1 |
| massimo | 50 |

- **Media contro mediana**: il valore isolato 50 cambia la **media molto più della mediana**.
- **Deviazione standard**: misura la dispersione; è usata nello **scaling z-score**.
- **Minimo e massimo**: definiscono gli estremi osservati; lo **scaling min-max è sensibile agli estremi rari**.

> 💡 **I conti (nota didattica).** Media: $(1+2+3+4+50)/5=60/5=12$. Mediana: il valore centrale dei dati ordinati, cioè 3. Deviazione standard campionaria: gli scarti dalla media sono $-11,-10,-9,-8,+38$; la somma dei quadrati è $121+100+81+64+1444=1810$; dividendo per $n-1=4$ si ottiene $452{,}5$ e la radice è $21{,}27$. L’unico valore 50 contribuisce da solo per l’80% della somma dei quadrati: per questo media e deviazione standard sono **non robuste**, mentre mediana e IQR lo sono.

> 📌 **Perché conta.** Bisogna **ispezionare la distribuzione prima di stimare i parametri di pre-elaborazione**: con questo vettore uno z-score o un min-max sarebbero dominati dal solo 50.

#### **5.2. La media non descrive l’intera forma**

| Distribuzione | Relazione tra le misure centrali |
|---|---|
| **asimmetrica a sinistra** | media < mediana < moda |
| **simmetrica** | media = mediana = moda |
| **asimmetrica a destra** | moda < mediana < media |

<!-- SOURCE_VISUAL id="IS10-V31" source="L10-web.pdf" page="62" type="grafico" description="Distribuzioni asimmetriche e simmetrica con media, mediana e moda, e illustrazione del camion sotto il ponte basso" reason="Grafici e illustrazione visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Il camion e il ponte.** L’**altezza media** dei camion non basta per evitare un **ponte basso**: contano i **valori estremi**. In applicazioni come questa **una delle due code dell’errore costa molto di più dell’altra**: sottostimare l’altezza di un camion è un incidente, sovrastimarla una deviazione di percorso. Lo stesso vale per l’errore di una regressione che prevede una scorta o un consumo.

#### **5.3. Stesso riassunto, distribuzioni diverse**

Quattro distribuzioni con media quasi uguale e IQR simili:

| Distribuzione | Media | IQR |
|---|---:|---:|
| uniforme | +0,13 | 1,59 |
| normale | −0,03 | 1,36 |
| lieve asimmetria a destra | +0,00 | 1,17 |
| forte asimmetria a destra | −0,00 | 0,94 |

<!-- SOURCE_VISUAL id="IS10-V32" source="L10-web.pdf" page="63" type="grafico" description="Quattro distribuzioni (uniforme, normale, lieve e forte asimmetria a destra) con media e IQR simili" reason="Forme delle distribuzioni visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Stesso riassunto $\neq$ stessi dati.** Valori simili di media o IQR possono nascondere **code, cluster e osservazioni estreme** diverse. L’IQR, $Q_3-Q_1$, misura la dispersione del **50% centrale** dei dati: è robusto agli outlier proprio perché **non guarda le code**, e per lo stesso motivo non le vede. Servono i grafici.

#### **5.4. Il boxplot riassume una distribuzione**

- **Mediana**: la linea dentro la scatola.
- **Intervallo interquartile**: $\text{IQR}=Q_3-Q_1$, l’altezza della scatola.
- **Outlier potenziali**: i punti oltre i «baffi», che arrivano fino a $Q_1-1{,}5\cdot\text{IQR}$ e $Q_3+1{,}5\cdot\text{IQR}$.
- La consueta **regola di $1{,}5\times$ IQR** dipende dalla **convenzione di disegno**.

Per una distribuzione **normale standardizzata**, la slide mostra le probabilità di ciascuna zona:

| Zona | Probabilità |
|---|---:|
| sotto $Q_1-1{,}5\cdot\text{IQR}$ (outlier) | 0,35% |
| tra il baffo inferiore e $Q_1$ | 24,65% |
| scatola, da $Q_1$ a $Q_3$ | 50% |
| tra $Q_3$ e il baffo superiore | 24,65% |
| sopra $Q_3+1{,}5\cdot\text{IQR}$ (outlier) | 0,35% |

<!-- SOURCE_VISUAL id="IS10-V33" source="L10-web.pdf" page="64" type="grafico" description="Boxplot allineato alla densità normale standard con le probabilità 0.35%, 24.65%, 50%" reason="Allineamento tra boxplot e densità visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Da dove vengono 0,35% e 24,65% (nota didattica).** Per una normale standard $Q_1\approx-0{,}674$ e $Q_3\approx+0{,}674$, quindi $\text{IQR}\approx 1{,}349$ e il baffo superiore è a $0{,}674+1{,}5\cdot 1{,}349\approx 2{,}698$. La probabilità di superarlo è $P(Z>2{,}698)\approx 0{,}35\%$. Tra $Q_3$ e il baffo resta $25\%-0{,}35\%=24{,}65\%$. In totale, per dati normali, solo lo 0,7% dei punti viene segnalato come outlier: chi vede molti più punti fuori dai baffi ha dati con code più pesanti della gaussiana.

#### **5.5. Outlier: usare grafici complementari**

Gli **stessi dati** di un sensore sono mostrati come istogramma e come boxplot. Nell’**istogramma** i valori rari sono **visivamente poco evidenti**; nel **boxplot** gli outlier potenziali sono **segnalati** come punti singoli. Gli outlier si vedono in entrambi i grafici?

<!-- SOURCE_VISUAL id="IS10-V34" source="L10-web.pdf" page="65" type="grafico" description="Stessi dati di un sensore come istogramma (outlier poco visibili) e come boxplot (outlier segnalati)" reason="Confronto tra i due grafici visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Errore o evento raro?** I valori estremi possono **distorcere lo scaling min-max** e influire sull’addestramento successivo. Bisogna **verificare se sono errori o eventi rari significativi**: eliminare automaticamente un valore raro può cancellare proprio il guasto che si vuole rilevare (§3.12).

#### **5.6. Il boxplot in MATLAB: confrontare distribuzioni per gruppo**

```matlab
load carsmall
boxplot(MPG, Origin)
title('Miles per Gallon by Vehicle Origin')
xlabel('Country of Origin')
ylabel('Miles per Gallon (MPG)')
```

`MPG` (miglia per gallone) è una feature numerica; `Origin` (il Paese di origine) definisce i **gruppi**: ogni scatola visualizza **una distribuzione condizionata**.

<!-- SOURCE_VISUAL id="IS10-V35" source="L10-web.pdf" page="66" type="grafico" description="Boxplot MATLAB dei consumi MPG per Paese di origine del dataset carsmall, con l'outlier tedesco" reason="Grafico visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Che cosa mostra in più della media.** Un boxplot non mostra solo l’**efficienza media** per Paese, ma anche la **dispersione**, dove è **centrata** la distribuzione e gli **outlier** (sì, un *hypermiler* in Germania!). È anche lo strumento naturale contro il paradosso di Simpson: confrontare i gruppi uno per uno prima di aggregare.

---

### **6. Oltre gli input: la diagnostica dell’apprendimento**


#### **6.1. Quattro punti in cui ispezionare un sistema di ML**

L’analisi dei dati non riguarda solo gli input: supporta anche la diagnostica di **pesi**, **attivazioni**, **uscite ed errori**. In una rete ci sono quattro punti di osservazione:

1. **input** $X_{ij}$;
2. **pesi** $W_{ij}$;
3. **attivazioni** $Y_{ij}$ degli strati nascosti;
4. **uscite ed errori** $O_{ij}$.

<!-- SOURCE_VISUAL id="IS10-V36" source="L10-web.pdf" page="68" type="schema" description="Rete con strato di input, tre strati nascosti e strato di output, con i quattro punti di ispezione: input, pesi, attivazioni, uscite ed errori" reason="Posizione dei punti nella rete visibile solo nello schema (le slide 69-73 ripetono lo schema evidenziando un punto alla volta)" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Nella prossima lezione si vedranno i **grafici corretti** per ciascuna di queste quattro fasi.

#### **6.2. Analizzare gli input del modello**

- **Distribuzione e scala** di ogni feature di input.
- **Dati mancanti**, **valori implausibili**, **sparsità** e **sbilanciamento tra classi**.
- **Correlazioni**, **ridondanza** e **differenze tra sottogruppi**.
- **Interpretazione**: i problemi negli input possono **compromettere l’apprendimento anche con un modello potente**.

**Strumenti**: istogrammi, boxplot, mappe dei valori mancanti, correlazioni.

| Aspetto | Contenuto (integrazione dall’edizione precedente) |
|---|---|
| **perché** | *garbage in, garbage out*: input di scarsa qualità limitano l’apprendimento; individuare feature ridondanti, rumorose o mancanti migliora pre-elaborazione e generalizzazione |
| **come** | statistiche (media, deviazione standard, minimo, massimo); PCA o t-SNE per la struttura; matrici di correlazione; controllo dello sbilanciamento |
| **visualizzazione** | istogrammi, scatter plot, boxplot; heatmap delle correlazioni a coppie; t-SNE (*t-distributed Stochastic Neighbor Embedding*) e UMAP (*Uniform Manifold Approximation and Projection*) per proiezioni in 2D |

##### **6.2.1. Perché lo scaling facilita l’addestramento con il gradiente**

- Scale delle feature **molto diverse** possono rendere l’ottimizzazione **mal condizionata**.
- La discesa del gradiente può richiedere **più passi** o una scelta più accurata del **learning rate**.
- Uno scaling ragionevole può **migliorare la convergenza**, ma **non garantisce l’accuratezza**.

<!-- SOURCE_VISUAL id="IS10-V37" source="L10-web.pdf" page="70" type="grafico" description="Curve di livello dell'errore J nello spazio dei pesi con e senza scaling, e percorso della discesa del gradiente" reason="Percorsi della discesa visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché la scala allunga il percorso (nota didattica).** Se $x_1$ vale migliaia e $x_2$ decimi, le curve di livello dell’errore $J(w_1,w_2)$ sono **ellissi molto allungate**. L’aggiornamento $\mathbf{W}\leftarrow\mathbf{W}-\eta\,\nabla J(\mathbf{W})$ segue il gradiente, che è quasi perpendicolare alle curve di livello: la discesa **zigzaga** lungo l’asse stretto e avanza lentamente lungo quello lungo, e un passo $\eta$ adatto a una direzione è troppo grande o troppo piccolo per l’altra. Con feature sulla stessa scala le curve diventano quasi **circonferenze** e il gradiente punta direttamente verso il minimo.

#### **6.3. Ispezionare pesi e gradienti**

- Esaminare **grandezze**, **istogrammi** e **norme dei gradienti** attraverso gli strati.
- Gradienti **estremamente grandi o minuscoli** possono indicare un **addestramento instabile** (gradienti che esplodono o svaniscono, Lezione 20).
- La **sparsità dei pesi** può rivelare effetti della **regolarizzazione** od opportunità di **pruning**.
- **Non** dedurre la qualità del modello **dal solo istogramma dei pesi**.

| Aspetto | Contenuto (integrazione dall’edizione precedente) |
|---|---|
| **come** | media, deviazione standard, norma massima dei pesi; istogramma per strato; sparsità e potenziale di pruning; entropia o curtosi per rilevare il collasso dei pesi |
| **visualizzazione** | istogrammi dei pesi; evoluzione della norma per epoca; grafici del flusso dei gradienti (per esempio con TensorBoard) |

> 💡 **Regolarizzazione L1 e L2 (nota didattica).** Si aggiunge all’errore un termine che penalizza i pesi grandi:
> $$J_{L2}(\mathbf{W})=J(\mathbf{W})+\lambda\sum_i w_i^2,\qquad J_{L1}(\mathbf{W})=J(\mathbf{W})+\lambda\sum_i|w_i|.$$
> L2 rimpicciolisce tutti i pesi; L1 ne porta molti **esattamente a zero**. Nell’istogramma dei pesi, L1 produce un grande picco a zero: è la sparsità che rende possibile il pruning.

#### **6.4. Ispezionare le attivazioni interne**

- Misurare le **distribuzioni delle uscite dei neuroni** su dati di input **rappresentativi**.
- Un neurone **ReLU** che restituisce **sempre zero** è un candidato **neurone inattivo/morto**.
- Una **sigmoide** vicina ai suoi estremi può diventare **satura** e avere **derivate piccole**.
- I grafici delle attivazioni sono **diagnostica**, **non** una prova indipendente della correttezza delle previsioni.

> 💡 **Neuroni morti e saturazione (nota didattica).** $\text{ReLU}(z)=\max(0,z)$ ha derivata nulla per $z<0$: se un neurone riceve sempre input negativi, il suo gradiente è sempre zero e **non impara più**. La sigmoide $\sigma(z)=1/(1+e^{-z})$ ha derivata $\sigma(z)\big(1-\sigma(z)\big)$, che tende a 0 quando $\sigma(z)$ è vicina a 0 o a 1: il neurone **saturo** impara lentissimamente. Un istogramma delle attivazioni con tutti i valori a zero, o ammassati agli estremi, rivela subito il problema. Strumenti utili: istogrammi e heatmap delle attivazioni, violin plot, attivazione media per strato, entropia della distribuzione.

#### **6.5. Ispezionare uscite ed errori di previsione**

- Analizzare gli **errori per classe**, **regime operativo**, **dispositivo** o **periodo di tempo**.
- Disegnare le **distribuzioni dei residui** e ispezionare i **pattern sistematici**.
- Usare **misure di prestazione adatte al compito** su dati tenuti da parte.
- Un **punteggio alto** o un valore di **confidenza grezzo** **non implica automaticamente la calibrazione**.

**Più avanti**: Lezione 11 (visualizzazione), 14 (validazione), 16 (calibrazione), 20 (addestramento).

| Aspetto | Contenuto (integrazione dall’edizione precedente) |
|---|---|
| **perché** | guida la diagnosi delle prestazioni; aiuta a individuare bias sistematici o overfitting; può guidare la pesatura della perdita o la calibrazione |
| **come** | matrice di confusione; accuratezza, precisione, recall e F1 per classe; curve di calibrazione; istogrammi dell’errore in funzione della confidenza |
| **visualizzazione** | curve ROC e precision-recall; heatmap degli errori; distribuzione dell’uscita softmax; errori positivi contro negativi |

> 💡 **Calibrazione (nota didattica).** Un modello è **calibrato** se, tra tutte le previsioni fatte con confidenza 0,8, circa l’80% è corretto. Molte reti profonde sono **sovraconfidenti**: la curva di calibrazione confronta la confidenza dichiarata con l’accuratezza reale e mostra lo scarto.

---

### **7. Pratica: un laboratorio facoltativo e quattro dataset**


#### **7.1. Laboratorio facoltativo: ispezionare un dataset reale**

1. Scegliere un **dataset documentato** con feature numeriche misurate.
2. Calcolare le **statistiche riassuntive** e confrontarle con i **grafici delle distribuzioni**.
3. Applicare **una trasformazione giustificata**, poi ispezionarne l’effetto.
4. Registrare un **outlier** e discutere: **artefatto o evento reale**?
5. Riportare brevemente **numero di campioni**, **unità**, **valori mancanti** e **ipotesi**.

È solo esercizio; il codice e i passi specifici dello strumento sono nel notebook di accompagnamento.

#### **7.2. Quattro dataset su cui esercitarsi**

| Dataset | Dimensione e origine | Feature tipiche | Avvertenza |
|---|---|---|---|
| **Breast Cancer Wisconsin (Diagnostic)** | $569\times 30$, incluso in scikit-learn | raggio (px), texture, area (px²), levigatezza, compattezza | l’**area in pixel²** schiaccia la levigatezza: **scalare prima** |
| **California Housing** | $20\,640\times 8$, scaricabile con scikit-learn | reddito (10 000 $), età della casa (anni), popolazione, stanze (per isolato), valore della casa ($) | **code lunghe**; il valore della casa è **troncato a 500 001 $** |
| **AI4I 2020 Predictive Maintenance** (UCI) | $10\,000\times$ circa 6 misure | temperatura dell’aria (K), temperatura di processo (K), velocità di rotazione (rpm), coppia (Nm), usura dell’utensile (min) | **guasti rari: non eliminarli con il clipping** |
| **Air Quality** (UCI) | dati orari di sensori | concentrazione di CO (mg/m³), NOx (ppb), NO₂ (µg/m³), temperatura (°C), umidità relativa (%) | i valori **mancanti sono codificati come −200** |

<!-- SOURCE_VISUAL id="IS10-V38" source="L10-web.pdf" page="75" type="grafico" description="Quattro pannelli con le scale delle feature dei dataset Breast Cancer, California Housing, AI4I 2020 e Air Quality su asse logaritmico" reason="Grafici delle scale visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Le trappole di ciascun dataset (nota didattica).** Il **troncamento** a 500 001 $ di California Housing significa che tutte le case di valore superiore hanno lo stesso target: un modello non può imparare nulla sopra quel valore, e l’istogramma mostra un picco artificiale all’estremo. Il **−200** di Air Quality è un valore sentinella: trattato come numero vero, sposta media e minimo e crea una falsa coda; va convertito in mancante (Lezione 6). Nell’**AI4I** i guasti sono pochi per natura: ogni clipping o rimozione di outlier rischia di cancellare proprio la classe da rilevare.

Come scaricarli ed eseguire una prima EDA: si veda l’appendice (§8).

---

### **8. Punti principali e appendice**


> ✅ **Punti principali della lezione.**
> - L’**EDA** rivela distribuzione, variazione, outlier e relazioni.
> - I **segnali nel tempo richiedono contesto**; una **finestra è un’osservazione derivata**.
> - Gli **MFCC** descrivono la **forma dello spettro**, non il pitch.
> - Un **vettore di feature** è una **rappresentazione progettata**, non una copia neutra dei dati grezzi.
> - La **composizione dei gruppi** può invertire i confronti aggregati (**paradosso di Simpson**).
> - Confrontare **strati e conteggi**; i **tassi standardizzati non sono prova causale**.
> - **Scaling, clipping e logaritmo** risolvono **problemi diversi**.
> - Ispezionare **input, pesi, attivazioni ed errori**.

#### **8.1. Appendice: verifica riproducibile del paradosso di Simpson**

**Due linee, 30 prodotti ciascuna: quale è migliore?** Il tipo di prodotto è 1 (facile) o 2 (difficile); un errore è 1, nessun errore è 0. Ogni posizione è un prodotto.

| Linea | Sequenza |
|---|---|
| produzione A | `221222222212221222212222211222` |
| errori A | `010100111100010000000111010011` |
| produzione B | `111121111112112112111211111112` |
| errori B | `101010000100001001010101000000` |

**Domanda**: quale linea è migliore? Contare prima a occhio, poi verificare per tipo di prodotto.

```python
prodA = "221222222212221222212222211222"
errA  = "010100111100010000000111010011"
prodB = "111121111112112112111211111112"
errB  = "101010000100001001010101000000"

def rate(prod, err, t=None):
    ids = [i for i in range(30)
           if t is None or prod[i] == t]
    return sum(int(err[i]) for i in ids) / len(ids)

for t in ("1", "2", None):
    print(t, round(rate(prodA, errA, t), 2),
             round(rate(prodB, errB, t), 2))
# 1 0.17 0.21      (A migliore)
# 2 0.5 0.67       (A migliore)
# None 0.43 0.3    (B sembra migliore nel complesso)
```

| Tipo | Linea A: errori / prodotti | Linea B: errori / prodotti |
|---|---|---|
| P1 (facile) | $1/6=17\%$ | $5/24=21\%$ |
| P2 (difficile) | $12/24=50\%$ | $4/6=67\%$ |
| tutti i prodotti | $13/30=43\%$ | $9/30=30\%$ |

<!-- SOURCE_VISUAL id="IS10-V39" source="L10-web.pdf" page="79" type="grafico" description="Barre dei tassi di errore delle linee A e B per prodotto facile, difficile e aggregato" reason="Diagramma a barre visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Lettura del risultato.** La linea A ha il **tasso di errore più basso per entrambi i tipi di prodotto**, ma la linea B ha il **tasso complessivo più basso** perché lavora **soprattutto prodotti facili** (24 su 30, contro i 6 di A). **Confrontare il simile con il simile.**

> 💡 **Come funziona `rate` (nota didattica).** La lista `ids` raccoglie le posizioni dei prodotti del tipo `t` (tutte, se `t` è `None`); la funzione somma gli errori in quelle posizioni e divide per il loro numero. È la stratificazione del §4.5 scritta in tre righe: lo stesso calcolo, filtrato per gruppo o no.

#### **8.2. Appendice: scaricare i quattro dataset**

```python
# pip install scikit-learn pandas matplotlib ucimlrepo
from sklearn.datasets import load_breast_cancer, fetch_california_housing
from ucimlrepo import fetch_ucirepo

bc = load_breast_cancer(as_frame=True).frame          # 569 righe: 30 feature + target
ca = fetch_california_housing(as_frame=True).frame    # 20640 righe: 8 feature + target
ai = fetch_ucirepo(id=601).data.original              # AI4I 2020 Predictive Maintenance
aq = fetch_ucirepo(id=360).data.original              # Air Quality (sensori orari)
```

- **Output**: quattro tabelle pandas (righe = campioni, colonne = feature misurate, più un target o un flag di guasto).
- I due dataset UCI si possono scaricare anche come CSV dalla pagina del repository UCI del dataset (id 601 e 360).
- **Prima di qualunque grafico**, annotare per ogni colonna: **unità di misura**, **sensore o fonte**, e **come è codificato un valore mancante**.

#### **8.3. Appendice: un primo giro di EDA**

```python
import matplotlib.pyplot as plt

def first_eda(df):
    print(df.shape)                                             # 1 dimensione
    print(df.dtypes)                                            # 2 tipi
    print(df.isna().sum())                                      # 3 valori mancanti
    num = df.select_dtypes("number")
    print(num.describe().T[["min", "50%", "mean", "max", "std"]])  # 4 scale, outlier
    print((num == -200).sum())                                  # 5 mancanti codificati (Air Quality)
    num.hist(bins=40, figsize=(12, 8)); plt.show()              # 6 distribuzioni
    num.plot(kind="box", subplots=True, layout=(3, -1), figsize=(12, 8)); plt.show()  # 7 outlier
    print(num.corr().round(2))                                  # 8 relazioni

first_eda(bc)
```

- **Input**: una tabella di $n$ campioni × $p$ feature. **Output**: otto controlli stampati o disegnati, **nessun modello**.
- **Leggerli in ordine**: dimensione e unità, valori mancanti, minimo–mediana–media–massimo (**una media lontana dalla mediana indica una coda lunga**), istogrammi, boxplot, correlazioni.
- **Atteso**: Breast Cancer mostra l’**area nell’ordine delle centinaia** e la **levigatezza vicina a 0,1**; Air Quality mostra **molti −200**.

> 💡 **Perché il controllo 5 è separato dal 3 (nota didattica).** `isna()` vede solo i mancanti codificati come `NaN`. Un dataset che codifica i mancanti con un numero sentinella (−200) risulta «completo» al controllo 3 e va scoperto con un controllo esplicito sul valore.

#### **8.4. Appendice: da qui, tocca a voi**

Il codice apre solo la porta. L’analisi che segue è vostra:

1. scegliere un dataset e dire che cosa rappresenta **una riga** e che cosa misura **ogni colonna**, con la sua unità;
2. trovare le feature con **scale diverse** e quelle con **code lunghe** o **outlier**;
3. applicare **una trasformazione giustificata** (min-max, z-score, logaritmo o clipping) e disegnare la stessa feature **prima e dopo**;
4. per un outlier, decidere: **artefatto o evento reale**? Scrivere le evidenze;
5. riportare in poche righe: numero di campioni, unità, valori mancanti, ipotesi e che cosa si controllerebbe dopo.

> ⚠️ **Domande da tenere a mente.** La conclusione cambierebbe se i dati fossero **divisi per gruppo**, come nel paradosso di Simpson? I parametri di pre-elaborazione sono stati calcolati **solo sui dati di training**?

---

### **9. Approfondimento — Materiale dell’edizione precedente**

L’edizione precedente apriva la lezione con le soluzioni dei compiti MATLAB della Lezione 9 e con un capitolo sulla similarità come strumento del deep learning. Nell’edizione 2026/27 questi temi sono stati assorbiti dalla Lezione 9 (§3.4–3.10: autocorrelazione, istanze multiple, kernel estraneo, CNN). Il ragionamento resta utile come ripasso.

#### **9.1. Soluzioni dei compiti: autocorrelazione e normalizzazione**

- **Compito 1** — che cosa succede se si usa come sottoimmagine **lo stesso template**? È l’**autocorrelazione** $\text{CRR}(x,x)$: il picco è **stretto**, il massimo **netto e ben separato**, al centro della mappa (spostamento nullo).
- **Compito 2** — e con **due immagini completamente diverse**? Nessun picco dominante: una superficie bassa e irregolare.

> ⚠️ **Confrontare i massimi richiede una normalizzazione.** `xcorr2` calcola una **somma** di prodotti, non una media: due immagini grandi producono somme grandi anche se si somigliano poco. Per confrontare i massimi di correlazioni diverse bisogna normalizzare rispetto al numero $n$ di pixel sovrapposti:
> $$\text{CRR}_{\text{norm}}(i,j)=\frac{1}{n}\sum_{x,y}f(x+i,y+j)\,t(x,y).$$
> La NCC della Lezione 9 completa il passo dividendo anche per le deviazioni standard.

#### **9.2. Correlazione, convoluzione e il kernel dei gelati**

> 📌 **«Basta il segno meno!».** Tra correlazione e convoluzione cambia solo il segno degli indici del kernel:
> $$(f\star t)(i,j)=\sum_{x,y}f(i+x,j+y)\,t(x,y),\qquad (f*t)(i,j)=\sum_{x,y}f(i-x,j-y)\,t(x,y).$$
> Il segno meno equivale a ruotare il kernel di 180° prima di farlo scorrere.

- **Similarità interna.** L’autocorrelazione di un’immagine con molti gelati simili mostra **picchi alti e separati**: la presenza di **pattern ripetuti**.
- **Cercare un pattern con un kernel.** Un singolo gelato (non ritagliato dall’immagine grande, ma **simile**) usato come kernel produce **picchi** in corrispondenza dei gelati. Conviene pensare in termini di **pattern** e usare un kernel di **dimensione simile** agli oggetti cercati.
- **La controprova con il panda.** Con un panda come kernel la correlazione diventa **rumorosa**, ma mostra comunque dei picchi: il panda si comporta da **rilevatore di blob**, perché le sue macchie scure rispondono alle zone scure dell’immagine. Un picco non significa «ho trovato l’oggetto», ma «qui c’è qualcosa che somiglia al kernel secondo questa misura».
- **Difetti come mancanze nel pattern.** In un prodotto con struttura regolare, un difetto «rompe» la ripetizione e il picco atteso si abbassa o scompare.

> 💡 **Uno strato convolutivo in formula (nota didattica).** Ogni filtro $k$ produce una mappa di attivazione facendo scorrere il kernel $W_k$ sull’input $X$, aggiungendo un bias e applicando una funzione di attivazione $\varphi$:
> $$A_k(i,j)=\varphi\!\left(\sum_{x,y}X(i+x,j+y)\,W_k(x,y)+b_k\right).$$
> È la correlazione dei gelati con due differenze: i kernel sono **appresi** e sono **molti**, uno per ogni pattern utile.

Il codice degli esperimenti (file `lesson_09_code_STEP2.m`) converte in `double` **prima** di sottrarre la media, evitando il troncamento del tipo `uint8`:

```matlab
img_cream = double(rgb2gray( imread( 'ice_creams.jpg')));
img_cream_norm = img_cream - mean(mean(img_cream));
crr5 = xcorr2( img_cream_norm , img_cream_norm ) ;     % stessa immagine
img_single = double(rgb2gray( imread( 'icecream2.jpg')));
img_single_norm = img_single - mean(mean(img_single));
crr6 = xcorr2( img_cream_norm , img_single_norm ) ;    % un gelato come kernel
```

#### **9.3. Funzioni descrittive in MATLAB**

Per l’EDA si usano comandi semplici ed efficaci, i cui valori servono anche nella feature engineering: `mean`, `median`, `mode`, `std`, `var`, `min`, `max`, `range`, `quantile`/`prctile`, `iqr`, `skewness` e `kurtosis`. La scelta dipende dalla distribuzione e dalla domanda dell’analisi; come ricordano le nuove slide, conta il concetto, non il comando.

---

### **10. Distinzioni da non confondere**


| Coppia | Differenza chiave |
|---|---|
| **wrangling** / **EDA** / **feature engineering** | pulire e strutturare / descrivere che cosa c’è nei dati / cambiare la rappresentazione per il modello |
| **dominio del tempo** / **della frequenza** / **spettrogramma** | come cambia il segnale / componenti periodiche / frequenze nel tempo |
| **spettro di ampiezza** / **spettro di potenza** | $\lvert X\rvert$ / $\lvert X\rvert^2$ |
| **RMS e curtosi** / **spettro dell’inviluppo** | quasi uguali per cuscinetto sano e difettoso / picchi alla BPFO solo nel difettoso |
| **$c_0$** / **$c_1\dots c_{12}$** | volume complessivo, scartato / forma dello spettro (tratto vocale), non il pitch |
| **finestra** / **campione indipendente** | osservazione derivata, sovrapposta / da non dare per scontato nello split |
| **selezione** / **estrazione** / **engineering** | sottoinsieme di variabili / nuova rappresentazione / progettazione complessiva |
| **livello (A)** / **(B)** / **(C)** | una feature alla volta / tutte senza etichette / tutto il dataset con le etichette |
| **min-max** / **z-score** | $[0,1]$ sul training, sensibile agli estremi / media 0 e deviazione 1 sul training |
| **trasformazioni lineari** / **non lineari** | min-max, z-score: la forma resta / logaritmo, clipping: la forma cambia |
| **clipping** / **logaritmo** | taglia gli estremi (può cancellare guasti rari) / comprime una coda lunga |
| **media e deviazione standard** / **mediana e IQR** | sensibili agli outlier / robuste agli outlier |
| **tasso per strato** / **tasso aggregato** | confronta il simile con il simile / media pesata dalle composizioni dei gruppi |
| **standardizzazione dei tassi** / **prova causale** | confronto descrittivo con pesi comuni / richiede altro |
| **neurone morto** / **neurone saturo** | ReLU sempre a zero / sigmoide vicina a 0 o 1 |
| **punteggio alto** / **modello calibrato** | non implica calibrazione / confidenza 0,8 ⇒ circa 80% di risposte corrette |
