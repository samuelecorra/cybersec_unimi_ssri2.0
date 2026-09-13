# **Lezione 15: Metodi avanzati di progettazione — multimodalità e fusione dell'informazione**

---

### **0. Panoramica della lezione**

_(slide pp. 1–4)_

La lezione presenta i **metodi avanzati per la progettazione e il miglioramento** dei sistemi biometrici. L'outline (p. 3) comprende tre blocchi:

1. **Biometria adattativa**: template update (re-enrollment) e template selection, già trattati nella **Lezione 8**.
2. **Fusione delle informazioni e tecniche avanzate di progetto** (questa lezione):
   1. multimodalità e multibiometria;
   2. soglie personalizzate;
   3. fusione neurale con dati sulla qualità del tratto;
   4. sistemi gerarchici;
   5. deep learning per estrarre feature automatiche;
   6. normalizzazione con database di riferimento (**cohort**).
3. **Multimodalità biometrica** ed esempi di sistemi multimodali.

Alcune slide introduttive sui sistemi multimodali (p. 4) provengono dal corso di **Sistemi Biometrici**: la prima parte è quindi un richiamo ampliato.

<!-- SOURCE_VISUAL id="TAB15-V01" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="4" type="pagina di sezione" description="Introduzione ai sistemi multimodali, lucidi dal corso di Sistemi Biometrici" reason="Pagina grafica di sezione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **1. Perché i sistemi multimodali**

#### **1.1 I limiti dei sistemi monomodali**

_(slide pp. 5–6)_

| Limite | Esempi (slide) |
|---|---|
| **Rumore** nei dati in ingresso | illuminazione non ottimale per il volto, umidità per le impronte |
| **Variabilità intraclasse** | posa del volto, raffreddore nella voce, ferita sul polpastrello |
| **Distintività limitata** del tratto | forma della mano, firma online |
| **Non universalità** | Failure to Enroll, malattie, fobie |
| **Attacchi al sensore** | dita in silicone, trucco e maschere, registratori |

> 📌 **Sistema biometrico multimodale** (International Biometric Group, p. 6). Un sistema che utilizza, o è in grado di utilizzare, **più di una caratteristica fisiologica o comportamentale** per l'enrollment, la verifica o l'identificazione.

#### **1.2 L'essere umano è multimodale**

_(slide p. 7)_

State aspettando l'amica **Laura**. Una persona si avvicina correndo e salutando: come capite se è lei? Il cervello esegue **automaticamente un matching multimodale**:

| Tratto | Somiglianza con il ricordo di Laura |
|---|---:|
| volto | 67% |
| voce | 71% |
| camminata | 30% |
| soft biometrics | 7% |

Le soft biometrics non tornano: il ricordo è di una persona di **67 kg, 175 cm, mora**, mentre chi arriva pesa **89 kg**, è alta **155 cm** ed è mora. Combinando le evidenze ($67\% + 71\% + 30\% + 7\%$) la conclusione è: **non è Laura, è Maria, sua sorella**.

> 💡 **Che cosa insegna l'esempio.** Volto e voce da soli, somiglianti perché le sorelle si assomigliano, porterebbero a un **falso match**. Sono i tratti **deboli** ma **indipendenti** (camminata, altezza, peso) a smascherare l'errore. È esattamente il vantaggio della fusione: un tratto poco distintivo può comunque correggere un errore di un tratto molto distintivo.

<!-- SOURCE_VISUAL id="TAB15-V02" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="7" type="illustrazione" description="Esempio di Laura e Maria: matching multimodale con volto 67%, voce 71%, camminata 30%, soft biometrics 7%" reason="Esempio illustrato con input e template" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.3 Più accurati del miglior componente**

_(slide pp. 8–9)_

> 📌 **Risultato consolidato** (p. 8). In letteratura è **perfettamente assodato** che i sistemi multimodali sono **più accurati del miglior sistema che li compone**. In un sistema con impronte, mano e volto, la ROC multimodale ha errori minori **su tutta la sua estensione**.

| Vantaggi (p. 9) | Svantaggi (p. 9) |
|---|---|
| con $N$ tratti si estrae **più informazione** e migliora il matching | **più costosi**, perché composti da più unità biometriche |
| **maggiore copertura** della popolazione: si riduce il **FTE**, perché chi non può registrarsi con un tratto usa gli altri | **più lenti** in acquisizione |
| efficace metodo **antispoofing**: è molto più difficile ingannare **più sensori contemporaneamente** | |

<!-- SOURCE_VISUAL id="TAB15-V03" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="8" type="curve ROC" description="ROC del sistema multimodale impronte, mano e volto con errori minori dei singoli componenti su tutta la curva" reason="Confronto grafico delle curve" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Perché un sistema multimodale abbassa FTA e FTE**

_(slide pp. 10–12)_

> 📌 **FTE di un sistema multimodale** (p. 10). La **probabilità di non riuscire a fare nessun enrollment**, cioè di fallire con **tutti** i tratti.

**Esempio con due tratti** (pp. 10–11), in un contesto applicativo di riferimento (frontiera, aeroporto, banca, cellulare):

- impronte: $FTE_1 = 0{,}04$ (4%);
- iride: $FTE_2 = 0{,}07$ (7%).

Spesso si può assumere che il fallimento di un sistema monomodale sia **indipendente** da quello degli altri: avere l'indice molto rovinato non ha niente a che fare con avere l'iride così dilatata da non essere riconosciuta. Con eventi indipendenti la probabilità congiunta è il **prodotto**:

$$FTE_{multimodale} = FTE_1 \cdot FTE_2 = 0{,}04 \cdot 0{,}07 = 0{,}0028 \quad (0{,}28\%)$$

Il tasso complessivo è **decisamente calato**: da 4% nel migliore dei due a 0,28%.

**Caso generale** (p. 12). Il ragionamento vale sia per il **FTA** (*Failure To Acquire*) sia per il **FTE**. Con $N$ tratti, fallimenti **indipendenti** e FTE definito come «nessun enrollment riuscito»:

$$FTE_{multimodale} = \prod_{i=1}^{N} FTE_i \qquad FTA_{multimodale} = \prod_{i=1}^{N} FTA_i$$

> ⚠️ **Formula ricostruita.** La p. 12 termina con una freccia verso la formula, che non è nel livello testo; la produttoria è la generalizzazione diretta dell'esempio della p. 11.

> ⚠️ **Due condizioni nascoste.** La formula vale solo se (1) i fallimenti sono davvero **indipendenti**: una malattia sistemica o un utente molto anziano possono invece far fallire più tratti insieme; e (2) la **politica** del sistema accetta l'enrollment con **almeno uno** dei tratti. Se il sistema richiede **tutti** i tratti, il FTE **aumenta**:
> $$FTE_{tutti} = 1 - \prod_{i=1}^{N} (1 - FTE_i) = 1 - 0{,}96 \cdot 0{,}93 = 0{,}1072$$
> Nell'esempio sale al 10,72%, più di entrambi i singoli. Il beneficio dipende dalla **regola di decisione**, non solo dal numero di tratti.

---

### **3. Quali tratti unire**

_(slide pp. 13–17)_

**Che cosa si può unire** (pp. 13–14). Alcune combinazioni sono **spazialmente vicine** (per esempio iride e volto) per **praticità** di acquisizione; altre sono **distanti** per garantire l'**assoluta indipendenza** dei tratti.

**Combinazioni più diffuse** in letteratura e in commercio (p. 15):

- viso + voce;
- viso + impronte;
- viso + impronte + voce;
- viso + impronte + geometria della mano;
- impronte + voce.

Metodi di estrazione delle feature **sempre più avanzati** permettono nuove soluzioni di fusione.

**Applicazioni target** (p. 16):

| Interesse | Applicazioni |
|---|---|
| **alto** | accesso fisico; identificazione con documenti elettronici, civile e criminale |
| **moderato** | accesso a reti informatiche o terminali; chioschi e sportelli ATM (bancomat, produzione di documenti) |
| **basso** | POS nei negozi; sorveglianza; e-commerce; telefonia |

**Terminologia** (p. 17). Dentro la macrodistinzione monomodale/multimodale la letteratura usa quattro sottocategorie:

| Macro-categoria | Sottocategoria | Significato (slide) | Esempio |
|---|---|---|---|
| Monomodale | **unibiometric** | singolo tratto biometrico | — |
| Monomodale | **unimodal biometric** | una singola immagine, una singola rappresentazione, un singolo matcher | — |
| Multimodale | **multibiometric** | tratti anche **debolmente correlati** | volto nel visibile e lo stesso volto in IR |
| Multimodale | **multimodal** | tratti **scorrelati**, di solito **diversi** | iride e impronta |

> ⚠️ **Convenzioni diverse.** Nello standard ISO/IEC TR 24722 e nel libro di Ross, Nandakumar e Jain, **multibiometric** è il termine **generale** per ogni sistema con più fonti (più sensori, algoritmi, istanze, campioni o tratti), mentre **multimodal** è il caso particolare con tratti diversi. La slide usa una suddivisione in cui i due termini sono affiancati: all'esame conviene saper spiegare entrambe.

<!-- SOURCE_VISUAL id="TAB15-V04" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="13" type="schema" description="Che cosa si può unire: sensori, algoritmi, istanze, campioni e tratti diversi" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB15-V05" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="14" type="illustrazione" description="Combinazioni di tratti biometrici spazialmente vicini e distanti in letteratura e nel mercato" reason="Combinazioni indicate sul disegno del corpo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Schemi e livelli di fusione**

_(slide pp. 18–24)_

#### **4.1 Gli schemi classici**

Le slide (pp. 18, 21, 23–24) mostrano due architetture di riferimento:

| Schema | Flusso | Livello di fusione |
|---|---|---|
| **Fusione a livello di feature** | estrattore 1 e estrattore 2 → **fusione** → un solo matching (database DB1) → decisione | feature |
| **Fusione a livello di match score** | estrattore 1 → matching 1 (DB1); estrattore 2 → matching 2 (DB2) → **fusione** dei punteggi → decisione | punteggio |

**Esempio di sistema multibiometrico** (p. 19): **iride destra e sinistra**, dove al posto del classico **IrisCode** si usa una **CNN** come estrattore.

**Livelli di integrazione** (p. 20), con **FM** (*Fusion Module*), **DM** (*Decision Module*) e **MM** (*Matching Module*):

| Livello | Che cosa si fonde | Informazione disponibile |
|---|---|---|
| **sensore** | dati grezzi | massima, ma eterogenea |
| **feature** | vettori di feature | molto ricca |
| **match score** | punteggi di confronto | un numero per matcher |
| **rank** | posizioni in classifica | un intero per candidato |
| **decisione** | esiti sì/no | un bit per matcher |

> 💡 **La regola generale.** Più presto si fonde, **più informazione** si conserva, ma **più difficile** è l'implementazione (dati eterogenei, matcher dedicati). Più tardi si fonde, **più semplice** e **indipendente dal produttore** è il sistema, ma si perde informazione. La fusione a livello di **punteggio** è il compromesso più usato (Lezione 14, §14.6).

<!-- SOURCE_VISUAL id="TAB15-V06" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="18" type="schemi a blocchi" description="Schemi classici: fusione a livello di feature e a livello di match score, sistemi multibiometrici e multimodali" reason="Schemi a blocchi con collegamenti non ricostruibili dal testo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB15-V07" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="19" type="schema" description="Sistema multibiometrico con iride destra e sinistra e CNN al posto dell'IrisCode" reason="Pagina con schema e poche etichette" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB15-V08" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="20" type="schema" description="Livelli di integrazione dell'informazione con Fusion Module, Decision Module e Matching Module" reason="Posizione dei moduli nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.2 Fondere a livelli diversi: che cosa conviene**

_(slide p. 22)_

Un esperimento sulle impronte confronta quattro configurazioni. L'**incremento di accuratezza maggiore** si ottiene, nell'ordine:

1. **3 dita**;
2. **2 dita**;
3. **1 dito con 3 matcher** diversi;
4. **1 dito con il miglior matcher** (riferimento).

> 📌 **Risultato generale.** Conviene usare **campioni indipendenti** se possibile: più istanze diverse del tratto battono più algoritmi sullo stesso campione.

> 💡 **Perché.** Tre matcher sullo stesso dito vedono **lo stesso rumore**: se il dito è sporco, sbagliano tutti insieme, quindi i loro errori sono **correlati**. Tre dita diverse hanno errori molto meno correlati e la fusione può davvero compensarli.

> ⚠️ **Etichette della slide.** Il testo parla di «3 templates», la legenda di «3 dita (multimodale)». Secondo la terminologia del §3, più dita della stessa persona sono piuttosto un sistema **multi-istanza** (multibiometrico) che multimodale.

<!-- SOURCE_VISUAL id="TAB15-V09" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="22" type="curve ROC" description="Fusione a livelli diversi: 3 dita, 2 dita, 1 dito con 3 matcher, 1 dito con il miglior matcher" reason="Confronto grafico delle prestazioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Fusione a livello di match score**

_(slide pp. 24–27)_

#### **5.1 La regola della somma**

Il metodo **più diffuso** (p. 24). La **regola della somma pesata** si è dimostrata in grado di **aumentare sempre le prestazioni** nei test (p. 25):

$$S = w_1 s_1 + w_2 s_2 + w_3 s_3$$

dove $s_i$ è il punteggio (normalizzato) del matcher $i$ e $w_i$ il suo peso. L'esempio della slide riguarda **geometria della mano, volto e impronta**. Il punto delicato è **come scegliere** $w_1, w_2, w_3$ per ottenere il miglioramento più ampio.

> ⚠️ **Refuso.** La slide scrive «$w_1, w_3, w_3$»; i pesi sono $w_1, w_2, w_3$.

**Perché la somma supera il miglior componente** (p. 26). Perché **separa meglio le distribuzioni** di genuini e impostori: nei sistemi monomodali (finger, hand, face) c'è **più sovrapposizione** che nel sistema multimodale, anche se realizzato con una **semplice somma pesata**.

> 💡 **Il motivo statistico (nota didattica).** Supponiamo che per ogni matcher i genuini abbiano punteggio medio $\mu_G$ e gli impostori $\mu_I$, con la stessa deviazione standard $\sigma$, e che gli errori siano indipendenti. La **media** di $N$ punteggi ha ancora medie $\mu_G$ e $\mu_I$, ma deviazione standard $\sigma / \sqrt{N}$. La separazione tra le due distribuzioni, misurata da
> $$d' = \frac{\mu_G - \mu_I}{\sigma / \sqrt{N}} = \sqrt{N} \cdot \frac{\mu_G - \mu_I}{\sigma}$$
> cresce come $\sqrt{N}$: meno sovrapposizione, meno errori. Con errori correlati il guadagno si riduce, come mostra il §4.2.

<!-- SOURCE_VISUAL id="TAB15-V10" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="25" type="curve ROC" description="Regola della somma per un sistema con geometria della mano, volto e impronta" reason="Confronto grafico delle curve" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB15-V11" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="26" type="distribuzioni" description="Distribuzioni di genuini e impostori per finger, hand, face e per la somma: minore sovrapposizione nel multimodale" reason="Evidenza visiva della separazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2 Classificazione o combinazione**

_(slide p. 27)_

Due filosofie per unire i punteggi $s_1, s_2, \dots, s_n$:

| | **Classificazione** | **Combinazione** |
|---|---|---|
| Che cosa fa | un classificatore riceve $s_1, \dots, s_n$ e produce **direttamente** l'uscita **genuino/impostore** | un combinatore unisce $s_1, \dots, s_n$ in modo **lineare, non lineare o logico** e passa un **unico valore** $S$ al decisore |
| Esempi | reti neurali, kNN, classificatori lineari, SVM, alberi | AND/OR, funzioni, votazione, max, min |
| Parametri | servono **dati** e una fase di **allenamento** (*trained classifier*) | di solito pochi; il decisore finale può essere una soglia o di nuovo un classificatore |

> 💡 **Il classificatore vede la geometria.** Nello spazio dei punteggi $(s_1, s_2)$ i genuini formano una nuvola e gli impostori un'altra. La somma pesata separa le nuvole con una **retta**; un classificatore addestrato (SVM con kernel, rete neurale) può usare una **frontiera curva**, al prezzo di dati di training e rischio di overfitting (Lezione 6).

---

### **6. Normalizzazione dei punteggi**

_(slide pp. 28–35)_

#### **6.1 Perché normalizzare**

Per confrontare correttamente i valori di matcher diversi occorre **prima normalizzarli** (p. 28). La normalizzazione serve a:

1. **omogeneizzare il significato**: per esempio $s_1$ è una **similitudine** e $s_2$ una **distanza**;
2. riportare le uscite alla **stessa scala**: una varia tra 0 e 100, un'altra tra 0 e 1000;
3. **uniformare le distribuzioni**: spostare medie e variazioni per renderle confrontabili.

Due proprietà da tenere sempre presenti:

| Proprietà | Significato |
|---|---|
| **robustezza** | un valore molto diverso, magari dovuto a un errore (**outlier**), non deve stravolgere la normalizzazione |
| **efficienza** | i parametri stimati devono essere **vicini a quelli reali** dei punteggi, altrimenti la normalizzazione non è corretta |

**Esempio** (p. 29): le distribuzioni di genuini e impostori per **mano, volto e impronte** hanno centri e scale diversi; due sono **distanze**, una è una **similitudine**. Occorre (a) **omogeneizzare** tutte le distribuzioni, poi (b) **normalizzare**.

**Esempio numerico** (p. 30): una distribuzione normale ideale di genuini con **media 10** e **deviazione standard 3** (per esempio i punteggi di un sistema basato sulla retina) va portata a media zero e deviazione 1. Il problema è trovare la funzione $S$ tale che i valori $S(s_i)$ abbiano la distribuzione voluta; qui

$$S(s) = \frac{s - 10}{3}$$

> 💡 **Da distanza a similitudine (nota didattica).** L'omogeneizzazione più semplice inverte il verso: se $d \in [0, d_{max}]$ è una distanza, $s = 1 - d / d_{max}$ è una similitudine in $[0, 1]$. Senza questo passo, sommare una distanza e una similitudine farebbe **cancellare** le evidenze a vicenda.

<!-- SOURCE_VISUAL id="TAB15-V12" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="29" type="distribuzioni" description="Distribuzioni di genuini e impostori per mano, volto e impronte con centri e scale diversi, due distanze e una similitudine" reason="Evidenza visiva dell'eterogeneità dei punteggi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB15-V13" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="30" type="grafico" description="Normalizzazione di una distribuzione normale di genuini con media 10 e deviazione 3 a media 0 e deviazione 1" reason="Formula e grafico non estraibili" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2 Quando serve davvero**

_(slide p. 31)_

| | **Reti neurali, alberi** | **Sistemi classici** (somma, min, max, kNN, classificatore lineare) |
|---|---|---|
| Normalizzazione | i pesi allenati sullo strato di ingresso la realizzano **automaticamente** | **quasi necessaria** |
| Omogeneizzazione | tutte distanze o tutte similitudini, e riduzione degli outlier, **aiutano** l'apprendimento | **aiuta moltissimo** |
| Senza | apprendimento più difficile | alcune feature **pesano molto più delle altre senza motivo biometrico** |

#### **6.3 Le funzioni di normalizzazione**

_(slide p. 32)_

La slide elenca le funzioni principali. Dato l'insieme dei punteggi $\{s_k\}$, $k = 1, \dots, n$, le definizioni standard (Jain, Nandakumar, Ross, 2005) sono:

| Funzione | Formula | Proprietà (slide) |
|---|---|---|
| **Min-max** | $s'_k = \dfrac{s_k - \min_j s_j}{\max_j s_j - \min_j s_j}$ | **efficiente** |
| **Decimale** | $s'_k = \dfrac{s_k}{10^{m}}$, con $m = \lceil \log_{10} \max_j \lvert s_j \rvert \rceil$ | da usare con **ordini di grandezza** diversi |
| **Logaritmica** | $s'_k = \log s_k$ (punteggi positivi) | per diversità di **fattore logaritmico** |
| **Z-score** | $s'_k = \dfrac{s_k - \mu}{\sigma}$ | **efficiente** |
| **Mediana e MAD** | $s'_k = \dfrac{s_k - \mathrm{med}}{\mathrm{MAD}}$, con $\mathrm{MAD} = \mathrm{med}\big(\lvert s_j - \mathrm{med} \rvert\big)$ | **robusta** |
| **Sigmoide doppia** | $s'_k = \begin{cases} \dfrac{1}{1 + \exp\!\big(-2 (s_k - t)/r_1\big)} & s_k < t \\[2mm] \dfrac{1}{1 + \exp\!\big(-2 (s_k - t)/r_2\big)} & s_k \geq t \end{cases}$ | **robusta** |

Nella sigmoide doppia $t$ è il punto di riferimento (tipicamente vicino alla zona di sovrapposizione tra genuini e impostori) e $r_1, r_2$ sono le ampiezze delle due zone in cui la funzione è approssimativamente lineare.

> 📌 **Efficienti contro robuste** (p. 32). **Min-max** e **z-score** sono **efficienti**: preservano le relazioni ordinali tra i dati originali. **Mediana/MAD** e **sigmoide doppia** sono **robuste**: resistono meglio agli **outlier**.

> ⚠️ **Formule ricostruite.** Il livello testo della p. 32 riporta solo i nomi delle funzioni; le formule sono le definizioni standard della letteratura e vanno confrontate con la slide.

> 💡 **Perché min-max non è robusta (esempio didattico).** Con punteggi $\{10, 12, 14, 16, 18\}$ la min-max assegna a 14 il valore 0,5. Se per un errore compare un punteggio 1000, il massimo diventa 1000 e 14 finisce a $4/990 \approx 0{,}004$: tutti i punteggi normali vengono **schiacciati** vicino a zero. La mediana (14) e la MAD (2) non cambiano quasi, perché un singolo outlier non sposta la mediana.

#### **6.4 Esempi riassuntivi**

_(slide pp. 33–35)_

- **Esempio riassuntivo** (pp. 33–34): un metodo di fusione **semplice ed efficace** è la fusione a livello di punteggio con **somma**.
- **Integrazione di sistemi commerciali** (p. 35): un sistema fonde **tre sensori commerciali di impronte** (V1, V2, V3) e un sistema per il **volto**, con normalizzazione **min-max** e **regola della somma** su **1000 soggetti**. Risultato: la combinazione **V1 + V2 + V3 + volto non è sempre migliore** di **V2 + volto**.

> ⚠️ **Più sistemi non significa sempre meglio.** Aggiungere matcher **deboli** o **correlati** con pesi uguali può **peggiorare** la fusione: il loro rumore diluisce l'evidenza dei matcher buoni. Anche la regola della somma, che «aumenta sempre le prestazioni» rispetto al miglior componente, va verificata sui dati per ogni **sottoinsieme** di sistemi e con **pesi** adeguati.

<!-- SOURCE_VISUAL id="TAB15-V14" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="33" type="grafici" description="Esempio riassuntivo (1): fusione a livello di match score con somma" reason="Pagina con soli grafici" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB15-V15" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="34" type="grafici" description="Esempio riassuntivo (2): risultati della normalizzazione e della fusione" reason="Pagina senza testo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB15-V16" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="35" type="curve ROC" description="Integrazione di tre sensori commerciali di impronte V1, V2, V3 e volto su 1000 soggetti: V1+V2+V3+volto non sempre migliore di V2+volto" reason="Confronto grafico delle combinazioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Esempio classico: iride, volto e impronta**

_(slide pp. 36–43)_

Tre sistemi restituiscono tre **similitudini**: iride → $score_1$, volto → $score_2$, impronta → $score_3$ (p. 36). Si realizzano tre fusioni.

#### **7.1 Fusione dei punteggi**

_(slide p. 37)_

Passaggi: ogni tratto viene **normalizzato**, poi i punteggi normalizzati si combinano (per esempio con la somma pesata del §5.1) e si confrontano con una soglia.

<!-- SOURCE_VISUAL id="TAB15-V17" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="37" type="schema" description="Match score fusion: normalizzazione dei tre punteggi e combinazione" reason="Passaggi mostrati nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2 Fusione a livello di rango**

_(slide pp. 38–41)_

> 📌 **Rango** (p. 38). Numero **intero** assegnato al punteggio (reale) di un utente **rispetto agli altri**: 1° è il candidato più simile. La **fusione a livello di rango** consolida due o più risultati di **identificazione** per migliorare l'affidabilità.

Il vantaggio principale: è una delle poche tecniche che **«sfugge» al problema della normalizzazione**, centrale invece per la fusione dei punteggi (somma, media, prodotto). Si possono combinare modalità diverse (volto, impronta, palmo, iride) **senza particolari problemi**.

**Esempio** (pp. 39–41), due utenti in gallery:

| Utente | Iride | Volto | Impronta |
|---|:---:|:---:|:---:|
| **A** | 2° | 3° | 2° |
| **D** | 1° | 2° | 1° |

Tre regole, ricordando che **rango più basso = migliore**:

| Regola | Significato | A | D | Vincitore |
|---|---|:---:|:---:|:---:|
| **media** (p. 40) | l'autenticazione «media» | $\frac{2+3+2}{3} \approx 2{,}33$ | $\frac{1+2+1}{3} \approx 1{,}33$ | D |
| **miglior rango** | mi fido del tratto che «matcha» di più | $\min = 2$ | $\min = 1$ | D |
| **peggior rango** | guardo l'autenticazione peggiore | $\max = 3$ | $\max = 2$ | D |

> 💡 **Borda count.** Sommare i ranghi ($A = 7$, $D = 4$) dà la stessa classifica della media: è il classico metodo **Borda**. In questo esempio tutte le regole scelgono D, che è davanti ad A in ogni tratto; le regole differiscono quando i tratti sono in disaccordo.

> ⚠️ **Min e max sui ranghi.** La slide p. 41 associa le etichette «Min()» e «MAX()» alle regole «autenticazione migliore» e «peggiore». Sui **ranghi** l'autenticazione migliore è il rango **minimo**; sui **punteggi di similitudine** è il punteggio **massimo**. Conviene verificare sempre su quale grandezza si applica l'operatore.

<!-- SOURCE_VISUAL id="TAB15-V18" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="39" type="tabella grafica" description="Rank level: classifiche di iride, volto e impronta con utente A (2, 3, 2) e utente D (1, 2, 1)" reason="Classifiche disegnate come tabelle di campioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.3 Fusione a livello di decisione**

_(slide p. 42)_

Con i punteggi $score_1 = 0{,}5$, $score_2 = 0{,}7$, $score_3 = 0{,}9$ si possono usare regole **AND**, **OR** e **voto**:

| Regola | Significato | Obiettivo |
|---|---|---|
| **OR** | basta che **un** tratto «matchi» | **usabilità** |
| **AND** | devono «matchare» **tutti**: equivale a guardare l'autenticazione **peggiore** | **sicurezza** |
| **voto a maggioranza** | posizione **intermedia** | normale applicazione |

> 💡 **Con una soglia (esempio didattico).** Con soglia $t = 0{,}6$ le decisioni sono: iride no ($0{,}5 < 0{,}6$), volto sì, impronta sì. OR accetta, AND rifiuta (il minimo 0,5 è sotto soglia), il voto a maggioranza (2 su 3) accetta.
>
> In termini di errori, con decisioni indipendenti:
> - l'**AND** abbassa l'FMR, perché servono falsi match su tutti i tratti: $FMR_{AND} = \prod_i FMR_i$; però alza l'FNMR, perché basta un falso rifiuto: $FNMR_{AND} = 1 - \prod_i (1 - FNMR_i)$;
> - l'**OR** fa il contrario: $FNMR_{OR} = \prod_i FNMR_i$ e $FMR_{OR} = 1 - \prod_i (1 - FMR_i)$.
>
> È la stessa logica del FTE del §2.

**Sintesi della prima parte** (p. 43): schemi classici multimodali e multibiometrici, fusione a livello di match score, significato e tecniche di normalizzazione, esempio conclusivo.

<!-- SOURCE_VISUAL id="TAB15-V19" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="42" type="schema" description="Decision level con punteggi 0,5, 0,7, 0,9: OR per usabilità, AND per sicurezza, voto come posizione intermedia" reason="Regole mostrate nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **8. Sistemi multimodali gerarchici**

_(slide pp. 44–46)_

> 📌 **Sistema gerarchico** (*sequential pattern recognition*, p. 45). Le acquisizioni biometriche avvengono **in cascata**, a seconda del risultato dell'identificazione precedente.

- In **verifica** **riducono il tempo** medio di verifica: per ridurlo, si acquisiscono **per primi i tratti più accurati**.
- In **identificazione** permettono, con il **pruning**, di **ridurre le porzioni del database** da analizzare (**indexing**).
- In altre applicazioni è **l'utente a scegliere** quale tratto mostrare. La slide mostra una ripartizione degli utenti tra i rami con **28%**, **13%** e **59%**.

**Prestazioni** (p. 46) di un sistema gerarchico con **impronta + volto + geometria della mano**. Pur essendo gerarchico, mantiene il comportamento dei sistemi multimodali **paralleli**: il multimodale fa **meglio del miglior componente**. La struttura gerarchica **non** ha modificato questa caratteristica.

> 💡 **Perché i tratti accurati vanno per primi.** Un tratto molto accurato decide con sicurezza la gran parte dei casi al primo stadio, quindi pochi utenti devono passare al secondo. Se si mettesse per primo un tratto debole, quasi tutti finirebbero nella zona di incertezza e il tempo medio crescerebbe. È la logica della cascata del sistema ABC spagnolo (Lezione 14, §14.6).

<!-- SOURCE_VISUAL id="TAB15-V20" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="44" type="pagina di sezione" description="Tecniche avanzate tradizionali di data fusion per sistemi multimodali" reason="Pagina grafica di sezione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB15-V21" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="45" type="schema ad albero" description="Sistema multimodale gerarchico con acquisizioni in cascata e ripartizione degli utenti 28%, 13%, 59%" reason="Associazione delle percentuali ai rami visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB15-V22" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="46" type="curve ROC" description="Prestazioni del sistema gerarchico impronta, volto e geometria della mano rispetto ai componenti" reason="Confronto grafico delle curve" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **9. Normalizzazione con cohort**

_(slide pp. 47–51)_

L'idea: normalizzare i punteggi **in modo intelligente** usando una **grande quantità di dati esterni** (p. 47).

> 📌 **Coorte** (p. 48). In statistica e demografia, un insieme di individui di una popolazione predefinita che hanno sperimentato **lo stesso evento in un periodo predefinito**; per esempio i nati in un certo anno. In biometria una coorte può essere:
> - uomini della **stessa età** del soggetto in ingresso;
> - dita di persone diverse in un **database pubblico** che risultano **simili** (a bassa distanza) al dito sconosciuto da riconoscere.
>
> Il concetto: individuare gli **impostori più simili** e usare i loro punteggi o distanze per **calibrare** il sistema.

**Esempio** (p. 49). La distanza misurata è $dist = 13$: **è tanto o poco**? La si confronta con un database esterno. I **20 individui** del database pubblico **più simili** al dato in ingresso, cioè gli impostori esterni più vicini, hanno distanza media **130**:

$$\text{Fresh\_score\_norm} = \frac{\text{Fresh\_score}}{\text{Dist\_media\_vicini}} = \frac{13}{130} = 0{,}1$$

La distanza è **un decimo** di quella dei migliori impostori: un segnale forte di genuinità.

> 💡 **Perché è più intelligente di min-max (nota didattica).** Una distanza assoluta di 13 può essere piccola per un campione di ottima qualità e grande per uno sfocato. La coorte è calcolata **sulla stessa query**: se la query è di bassa qualità, anche gli impostori le risultano più lontani (o più vicini), e il rapporto compensa. La normalizzazione diventa **adattiva** al singolo tentativo.

**Estensioni** (p. 50): invece di un semplice rapporto, o di un banale min-max o z-norm, si possono usare funzioni più complesse, come **SVM** o **reti neurali**, che imparano a scalare gli ingressi rispetto alle distanze calcolate sul database esterno.

**Caratteristiche** (p. 51):

| Proprietà | Motivo |
|---|---|
| **privacy-compliant** | la normalizzazione usa dati di **database pubblici**, non dati memorizzati dei singoli utenti |
| **generale** | basata solo sui **punteggi**, non sulle caratteristiche di un tratto: si applica a **qualsiasi** sistema biometrico |

<!-- SOURCE_VISUAL id="TAB15-V23" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="49" type="schema" description="Normalizzazione cohort: distanza 13 confrontata con la distanza media 130 dei 20 impostori esterni più vicini" reason="Formula e schema non completamente estraibili" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **10. Fusione a livello di feature**

_(slide pp. 52–56)_

**Unire direttamente le informazioni estratte dal tratto** (p. 52). È **potente ma più complessa** (p. 53):

- serve un **matcher specifico** e un **database apposito** (non commerciale);
- bisogna **scegliere bene i tratti**, in modo che **si compensino**: non si fondono a livello di feature tratti **«a caso»**.

**Il problema principale** (p. 54) è l'**eccessiva eterogeneità** tra le feature, per esempio volto con **autofacce** e impronta con **minuzie** (un vettore di numeri contro un insieme di punti). La fusione è di solito possibile quando da **tutti** i tratti si estraggono **feature numeriche vettoriali**.

**Esempio mano + volto** (pp. 55–56):

1. si estraggono le feature: **mano**, 14 lunghezze; **volto**, 25 coefficienti delle **autofacce** (vettore ridotto);
2. si **normalizzano** le feature;
3. si **concatenano** i due vettori:
$$\mathbf{x} = [\,\underbrace{h_1, \dots, h_{14}}_{\text{mano}},\ \underbrace{f_1, \dots, f_{25}}_{\text{volto}}\,] \in \mathbb{R}^{39}$$
4. si calcola la **distanza euclidea** tra i template concatenati;
5. si calcolano anche le distanze delle **singole** feature;
6. si uniscono tutti i risultati con un **classificatore** o un **combinatore con soglia**.

Si possono così mettere **in cascata** la fusione a livello di **feature** e quella a livello di **matching** (p. 56).

> 💡 **Perché normalizzare prima di concatenare.** Le lunghezze della mano sono in millimetri (decine), i coefficienti delle autofacce possono valere migliaia. Senza normalizzazione la distanza euclidea sul vettore concatenato dipenderebbe **quasi solo** dal volto: le 14 feature della mano sarebbero di fatto ignorate.

<!-- SOURCE_VISUAL id="TAB15-V24" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="56" type="schema" description="Fusione a livello di feature di volto (autofacce) e mano (misure) in cascata con la fusione a livello di matching" reason="Schema della cascata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **11. Fusione a livello di feature con il deep learning**

_(slide pp. 57–60)_

Il deep learning permette di **estrarre automaticamente** feature molto significative (p. 57): il problema di trovare feature utili per la fusione a partire da dati complessi **può essere automatizzato**.

**Passo 1: addestramento con identificazione** (p. 58).

- La rete ha una **parte A** (strati **convoluzionali**) e una **parte B** (rete **feed-forward** con **softmax** sulle identità).
- Si chiede alla rete di **identificare l'utente**: è un compito **molto difficile**, e non interessa che venga molto bene.
- Serve a costringere la rete a creare **feature salienti**: è nell'uscita della parte A che **si realizzano le feature** che si useranno in verifica.

**Passo 2: uso biometrico e fusione** (p. 59).

- Si **scarta** la parte B e si usa la **parte A** come estrattore.
- I template A e B passano al **matcher**, che può essere una semplice **norma euclidea**, e si ottiene il punteggio.
- Il **vettore** estratto può essere usato per la **fusione** a livello di feature con altri tratti.

> 💡 **Collegamento con le Lezioni 8 e 11.** È lo stesso schema della CNN addestrata in identificazione e poi usata come **estrattore di embedding**: il penultimo strato di VGG-Face o FaceNet diventa il template. Poiché gli embedding sono vettori numerici omogenei, il problema di eterogeneità del §10 sparisce: embedding di volto e di iride si possono concatenare o fondere direttamente.

**Memo** (p. 60): nel corso **Intelligent Systems for Industry, Supply Chain and Environment** si studiano modelli tradizionali e reti deep per applicare questi concetti a molti tipi di applicazioni, con attività di laboratorio.

<!-- SOURCE_VISUAL id="TAB15-V25" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="58" type="schema di rete" description="Passo 1: addestramento in identificazione con strati convoluzionali (parte A), rete feed-forward e softmax sulle ID (parte B)" reason="Architettura mostrata nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB15-V26" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="59" type="schema" description="Passo 2: la parte A estrae i template, matcher con norma euclidea e vettore usato per la fusione" reason="Schema del flusso di verifica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **12. Parametrizzazione per il singolo utente**

_(slide pp. 61–63)_

Tenendo conto delle **caratteristiche singolari di ogni utente** le prestazioni aumentano ancora (p. 61). Due approcci:

1. **soglie personalizzate**: ogni utente ha una **distanza dagli impostori** diversa, quindi può avere la **sua soglia di decisione** per ogni tratto;
2. **pesi personalizzati**: ogni utente produce campioni di **qualità diversa**, quindi i tratti si possono pesare in base alla **qualità di acquisizione in enrollment** e all'**errore** di quel tratto (per esempio far pesare di più le impronte del volto nella decisione finale).

> 📌 **Due insiemi di parametri di progetto** in un sistema multimodale: le **soglie** dei matching e i **pesi** delle biometrie in base alla **qualità** o alla **confidenza**.

#### **12.1 Soglia per utente**

Sia $X_{i\_j}$ il $j$-esimo template dell'individuo $i$ (p. 62). Per l'utente 1 si calcolano:

| Confronti **genuini** (solo $X_1$ con se stesso) | Confronti **impostori** ($X_1$ contro tutti gli altri) |
|---|---|
| $S(X_{1\_1}, X_{1\_2}) = 0{,}7$ | $S(X_{1\_1}, X_{3\_2}) = 0{,}11$ |
| $S(X_{1\_1}, X_{1\_3}) = 0{,}8$ | $S(X_{1\_2}, X_{3\_1}) = 0{,}21$ |
| | $S(X_{1\_1}, X_{4\_2}) = 0{,}001$ |
| | $S(X_{1\_2}, X_{5\_2}) = 0{,}19$ |

> 💡 **Leggere l'esempio.** Per l'utente 1 i genuini stanno tra 0,7 e 0,8 e gli impostori non superano 0,21: qualsiasi soglia personale in $(0{,}21;\ 0{,}7)$ separa perfettamente questi dati. Una soglia globale scelta per tutti gli utenti potrebbe invece cadere in una zona sfavorevole per lui.

**Due utenti con distribuzioni diverse** (p. 63). Le distribuzioni delle distanze **genuine** di due utenti A e B hanno medie **48/100** e **58/100**. Da ciascuna si ricava la soglia «perfetta» per quell'utente, per esempio con **FRR = 1%**:

$$t_A : P(d_A > t_A) = 0{,}01 \qquad t_B : P(d_B > t_B) = 0{,}01$$

dove $d_A$ e $d_B$ sono le distanze genuine dei due utenti: l'utente B, con distanze genuine mediamente più alte, riceve una soglia più alta.

> ⚠️ **FAR o FRR?** Il testo della slide parla di soglia per un **FAR** desiderato dell'1% ricavata dalle distribuzioni dei **genuini**, mentre le etichette dei grafici indicano **Soglia@FRR=0,01**. Dalla distribuzione dei **genuini** si controlla il **FRR** (FNMR): quanti genuini superano la soglia di distanza. Per fissare il **FAR** (FMR) serve la distribuzione degli **impostori** di quell'utente.

<!-- SOURCE_VISUAL id="TAB15-V27" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="63" type="distribuzioni" description="Distribuzioni delle distanze genuine di utente A (media 48/100) e utente B (media 58/100) con le soglie personali a FRR 0,01" reason="Posizione delle soglie sulle distribuzioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **13. Integrazione delle soft biometrics**

_(slide pp. 64–67)_

Le **soft biometrics** si possono usare **in aggiunta** al sistema principale (p. 64): **genere**, colore della **pelle**, dei **capelli** e degli **occhi**, **peso**, **altezza**…

**Dove si integrano** (p. 65). L'integrazione corretta è **a valle** del modulo biometrico primario, in un **modulo di post-processing**.

**Esempio**:

- dall'**impronta** si estrae il template $x$, e si misura l'**altezza** $y$;
- il matcher primario restituisce un punteggio interpretabile come la **probabilità** che l'input $x$ corrisponda all'individuo $\omega$ (quello con il punteggio più alto nel database): $P(\omega \mid x)$;
- il post-processing deve stimare $P(\omega \mid x, y)$ con la **formula di Bayes**.

**Implementazione** (p. 66). Impronta e altezza si considerano **indipendenti**, quindi:

$$P(\omega \mid x, y) = \frac{P(\omega \mid x) \cdot P(y \mid \omega)}{P(y)}$$

I due termini hanno un significato preciso:

| Termine | Significato (slide) |
|---|---|
| $P(y \mid \omega)$ | probabilità che l'individuo $\omega$ **possa essere alto** $y$. Più il sistema di misura è **preciso**, più scende se $y$ è diverso dall'altezza registrata di $\omega$. Si stima dalla **tabella** delle altezze registrate e dalla **precisione** della misura |
| $P(y)$ | normalizza la **rarità** dell'altezza rilevata: più $y$ è **raro**, più $P(y)$ è basso e, al denominatore, **alza** il punteggio |

Nell'esempio della slide: punteggio primario **0,66**, altezza misurata $y = 192$ cm, $P(y) = 0{,}015$.

> 💡 **Come si deriva (nota didattica).** Per Bayes $P(\omega \mid x, y) = \dfrac{p(x, y \mid \omega)\, P(\omega)}{p(x, y)}$. Con $x$ e $y$ indipendenti sia dato $\omega$ sia marginalmente, $p(x, y \mid \omega) = p(x \mid \omega)\, p(y \mid \omega)$ e $p(x, y) = p(x)\, p(y)$. Raggruppando si ottiene $\dfrac{p(x \mid \omega) P(\omega)}{p(x)} \cdot \dfrac{p(y \mid \omega)}{p(y)} = P(\omega \mid x) \cdot \dfrac{p(y \mid \omega)}{p(y)}$. L'indipendenza marginale è un'**approssimazione**: il valore risultante va trattato come punteggio e rinormalizzato sui candidati.

> 💡 **Esempio numerico (valori ipotetici).** Supponiamo una misura dell'altezza con errore gaussiano di deviazione standard 2 cm e due candidati con lo stesso punteggio d'impronta 0,66.
> - Il candidato $\omega_1$ ha altezza registrata **190 cm**: $y = 192$ dista una deviazione standard, e la probabilità di una lettura in un intervallo di 1 cm attorno a 192 è circa $0{,}121$. Il fattore $P(y \mid \omega_1) / P(y) = 0{,}121 / 0{,}015 \approx 8$ **rafforza** il candidato.
> - Il candidato $\omega_2$ è registrato a **170 cm**: 192 dista 11 deviazioni standard, $P(y \mid \omega_2) \approx 0$, e il candidato viene praticamente **escluso**.
>
> Un tratto debole come l'altezza non identifica nessuno da solo, ma **scarta** con decisione i candidati incompatibili: è il caso di Laura e Maria del §1.2.

**Risultati** (p. 67): il grafico mostra la probabilità che l'utente da identificare si trovi tra i **primi $n$** candidati del post-processing, cioè una **CMC** (Lezione 2). Frecce e ovali evidenziano l'**incremento** di prestazioni dovuto alla misura dell'**altezza**.

<!-- SOURCE_VISUAL id="TAB15-V28" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="66" type="schema con formula" description="Modulo di post-processing: formula di Bayes con score 0,66, altezza 192 cm, P(y)=0,015 e tabella delle altezze registrate" reason="Formula e tabella nell'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB15-V29" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="67" type="curva CMC" description="Risultati dell'integrazione della soft biometrics: incremento della probabilità di identificazione entro i primi n candidati grazie all'altezza" reason="Incrementi evidenziati con frecce e ovali" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **14. Fusione basata sulla qualità**

_(slide pp. 68–69)_

Un approccio **ibrido** di **selezione e fusione** (p. 68). Oltre ai punteggi $mscore_1$ e $mscore_2$ dei matcher (per esempio impronta e iride), il modulo di fusione riceve le **qualità** $q_1 = Q_{finger}(\cdot)$ e $q_2 = Q_{iris}(\cdot)$ dei campioni in ingresso (p. 69).

> 📌 **L'idea chiave.** Un punteggio **basso** in ingresso può dipendere dalla **qualità del tratto**, non per forza da un **impostore**. Il modulo di fusione deve saper distinguere i due casi.

Il modulo **FUSION** può essere il classico caso di una **somma pesata** oppure una **rete neurale** che riceve $(mscore_1, mscore_2, q_1, q_2)$ e restituisce il punteggio $s$.

> 💡 **Una forma semplice (nota didattica).** Una somma pesata dalla qualità è
> $$s = \frac{q_1 \cdot mscore_1 + q_2 \cdot mscore_2}{q_1 + q_2}$$
> Se l'impronta è sporca ($q_1 \to 0$), il punteggio fuso dipende quasi solo dall'iride: un dito sporco non fa più rifiutare un utente genuino con un'iride ben acquisita. Una rete neurale impara relazioni più ricche, per esempio che un punteggio medio con qualità alta è più sospetto dello stesso punteggio con qualità bassa.

<!-- SOURCE_VISUAL id="TAB15-V30" source="Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf" page="69" type="schema" description="Fusione con qualità del tratto: mscore1, mscore2, q1=Q_finger, q2=Q_iris in ingresso a una somma pesata o a una rete neurale" reason="Schema a blocchi con formula non estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **15. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Unibiometric** / **unimodal biometric** | un solo tratto / una sola immagine, rappresentazione e matcher |
| **Multibiometric** / **multimodal** (slide) | tratti anche debolmente correlati (volto VIS e IR) / tratti scorrelati e diversi (iride e impronta) |
| **FTE con «almeno un tratto»** / **con «tutti i tratti»** | $\prod FTE_i$, diminuisce / $1 - \prod (1 - FTE_i)$, aumenta |
| **Fusione di feature** / **di punteggio** | più informazione, matcher e database dedicati / più semplice, indipendente dal produttore, la più usata |
| **Fusione di rango** / **di punteggio** | interi, niente normalizzazione, solo identificazione / reali, normalizzazione necessaria |
| **Classificatore** / **combinatore** | produce direttamente genuino/impostore, va addestrato / produce un valore $S$ per il decisore |
| **Normalizzazione efficiente** / **robusta** | min-max, z-score / mediana-MAD, sigmoide doppia |
| **Omogeneizzazione** / **normalizzazione** | stesso verso (tutte distanze o similitudini) / stessa scala e distribuzione |
| **AND** / **OR** | sicurezza, FMR più basso / usabilità, FNMR più basso |
| **Più dita** / **più matcher sullo stesso dito** | errori indipendenti, guadagno maggiore / errori correlati, guadagno minore |
| **Cohort** / **min-max** | normalizzazione adattiva sulla query con impostori esterni simili / parametri fissi |
| **Soglia globale** / **soglia per utente** | stessa per tutti / dalla distribuzione dei confronti dell'utente |
| **Soft biometrics** / **tratto primario** | poco distintiva, integrata a valle con Bayes / identifica |
| **Punteggio basso da impostore** / **da bassa qualità** | evidenza contraria / evidenza debole, da pesare meno |

---

### **16. Sintesi della lezione**

_(slide p. 70)_

La sintesi finale della slide (p. 70) ricorda anche le tecniche di **update dei template** (spaziale e temporale) e di **selezione ed eliminazione**, trattate nella **Lezione 8**.

> ✅ **In sintesi.**
> - **Perché la multimodalità**: i sistemi monomodali soffrono di rumore, variabilità, distintività limitata, non universalità e spoofing; il multimodale è più accurato del miglior componente, riduce FTE e FTA ($\prod FTE_i$ con fallimenti indipendenti e regola «almeno un tratto») ed è più robusto allo spoofing, ma costa di più ed è più lento.
> - **Livelli di fusione**: sensore, feature, punteggio, rango, decisione; conviene usare campioni indipendenti.
> - **Punteggio**: somma pesata che separa meglio le distribuzioni; classificatori o combinatori; normalizzazione (min-max, decimale, z-score, mediana-MAD, sigmoide doppia) efficiente o robusta; più sistemi non sempre significa meglio.
> - **Rango e decisione**: media, migliore, peggiore; AND per la sicurezza, OR per l'usabilità, voto come compromesso.
> - **Tecniche avanzate**: sistemi gerarchici (tratti accurati per primi, pruning), normalizzazione cohort con impostori esterni simili, fusione di feature (normalizzare e concatenare) anche con embedding deep, soglie e pesi personalizzati, soft biometrics con Bayes, fusione pesata dalla qualità.
