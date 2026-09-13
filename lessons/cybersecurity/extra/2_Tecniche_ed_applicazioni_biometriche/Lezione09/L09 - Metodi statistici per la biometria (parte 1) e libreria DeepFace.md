# **Lezione 9: Metodi statistici per la biometria (parte 1) e libreria DeepFace**

---

### **0. Panoramica della lezione**

_(slide pp. 1–5)_

La lezione ha due parti:

- **Parte A — Modelli e metodi statistici per la biometria**: *data reporting* dei sistemi biometrici, cioè come **misurare l'accuratezza** e quali **modelli statistici** applicare;
- **Parte B — Reti deep per il volto**: la libreria **DeepFace**, con laboratorio.

Nella mappa multidisciplinare della Lezione 1 l'attenzione si sposta sulla **statistica**: si esaminano le tecniche per capire **come si comporta** un sistema biometrico, **come regolarlo** e **come fare il reporting** dei risultati.

> **Richiamo — Sistemi Biometrici.** Le basi di FMR, FNMR, DET, ROC, CMC, regola dei 3 e dei 30 sono in [L06A — Misura dei parametri](<../../../anno3/6_Sistemi_Biometrici/L06A - Misura dei parametri di un sistema biometrico.md>) e [L6B — Dispensa operativa](<../../../anno3/6_Sistemi_Biometrici/L6B - Dispensa operativa sulla misura dei parametri dei sistemi biometrici.md>). Questa lezione le riprende con l'ottica del **progetto e del reporting**.

---

## **Parte A — Modelli e metodi statistici**

### **1. Il sistema configurato e il flusso di lavoro**

_(slide pp. 6–10)_

#### **1.1 Premessa: quando il sistema è «fissato»**

_(slide p. 7)_

- Se il sistema ha una **soglia $T$ variabile**, bisogna analizzare la **curva DET** e **regolare la soglia** in base all'applicazione.
- Un sistema **completamente fissato** (soglia compresa) si comporta come un **algoritmo dipendente dai dati** di ingresso: alla domanda «può entrare?» risponde **YES/NO**.
- A quel punto si possono valutare la sua **accuratezza** e il suo **comportamento statistico**.

#### **1.2 Il flusso di lavoro**

_(slide pp. 8–9)_

1. **usare i dati biometrici** per creare confronti **genuini e impostori**;
2. **calcolare le distribuzioni** dei punteggi;
3. **fissare i parametri** del sistema (soglia);
4. **calcolare gli errori in autenticazione**;
5. **generalizzare gli errori in identificazione**, con **medie** e **intervalli di confidenza**, fino alla probabilità di errore $p$.

**Comprare un sistema «off the shelf»** (p. 9) apre diversi casi: il produttore fornisce già $p$? Si può scegliere **solo la soglia $T$** o anche altri parametri? Ci si **fida completamente** dei dati del datasheet? Oppure si **fanno esperimenti**?

> ⚠️ **Il datasheet non basta.** I numeri del produttore sono misurati su dati e condizioni del produttore. Come per l'IrisCode (Lezione 4, §6.1), le stime di laboratorio sono ottimistiche: nel contesto reale va sempre verificato almeno un sottoinsieme dei risultati.

<!-- SOURCE_VISUAL id="TAB09-V01" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="8" type="diagramma a flusso" description="Flusso: dati biometrici per genuini e impostori, distribuzioni, parametri, errori in autenticazione, generalizzazione in identificazione con medie, intervalli di confidenza e p" reason="Schema di riferimento del flusso di lavoro" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.3 EER o probabilità di errore?**

_(slide p. 10)_

Meglio ragionare sui **grafici DET** senza fissare la soglia, oppure **configurare tutto** il sistema e studiarne la **probabilità di errore**? La risposta della slide: **integrare tutte le informazioni in un documento ragionato**, che serva da **supporto per decisioni future** o modifiche.

---

### **2. Dati sperimentali o modelli statistici?**

_(slide pp. 11–13)_

| **Inferenza statistica** | **Calcolo delle probabilità** |
|---|---|
| si **inducono** le caratteristiche di una popolazione dall'osservazione di una sua parte (il **campione**) mediante un esperimento casuale | il **processo di generazione** dei dati è **noto** (modello probabilistico): si calcola la probabilità dei possibili risultati |
| «volontari, conteggi, sommatorie, foglio Excel» | «datasheet, libri, integrali, R, MATLAB» |

**Se non ho nessun dato** (p. 12): uso **volontari** per stimare le distribuzioni dei match score. Con $X_{i\_j}$ il $j$-esimo template dell'individuo $i$, si calcolano score genuini (per esempio $S(X_{1\_1}, X_{1\_2}) = 0{,}7$) e impostori (per esempio $S(X_{1\_1}, X_{3\_2}) = 0{,}11$). Le distribuzioni ottenute sono **istogrammi** calcolati con i volontari.

> 📌 **Data reporting, dato fondamentale #1: le distribuzioni dei match score** (o delle distanze). Vanno ottenute, in ordine di preferenza, per interpolazione dal **datasheet**, da una **rivista scientifica**, dai **propri dati** precedenti, da una valutazione **technology, scenario o operational**, o da **esperimenti con volontari**, controllando sempre con esperimenti quando possibile.

<!-- SOURCE_VISUAL id="TAB09-V02" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="12" type="istogrammi" description="Istogrammi degli score genuini e impostori calcolati con volontari" reason="Mostra il passaggio da score singoli a istogrammi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. La cardinalità di genuini e impostori**

_(slide pp. 14–20)_

#### **3.1 Perché i genuini sono più «rumorosi»**

_(slide p. 14)_

Nelle distribuzioni reali (iride di Daugman, palmo, volto) la curva dei **genuini** appare più **rumorosa**, o disegnata a barre, di quella degli **impostori**. Il motivo: la **cardinalità** dei due insiemi è **completamente diversa**.

<!-- SOURCE_VISUAL id="TAB09-V03" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="14" type="grafici di distribuzioni" description="Distribuzioni genuini e impostori per iride (Daugman), palmprint e volto, con genuini più rumorosi" reason="Evidenza visiva della diversa cardinalità" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2 Il conteggio**

_(slide pp. 15–19)_

Si considerano **$N$ individui**, **2 immagini per individuo** ($X_{i\_1}$, $X_{i\_2}$) e un **matching simmetrico**, $S(X_{1\_1}, X_{1\_2}) = S(X_{1\_2}, X_{1\_1})$.

**Genuini** (p. 15): per ogni individuo c'è **un solo** confronto tra le sue due immagini, quindi

$$\#\text{genuini} = N$$

**Impostori** (pp. 16–18), contando come la slide:

- la prima foto del primo individuo, $X_{1\_1}$, si confronta con le 2 foto di ciascuno degli altri $N - 1$ individui: $2(N-1)$ confronti;
- la seconda foto $X_{1\_2}$ fa lo stesso: altri $2(N-1)$;
- totale per singolo individuo: $4(N-1)$;
- ripetendo per tutti gli $N$ individui:

$$\#\text{impostori} = N \cdot 4(N-1) = 4N^2 - 4N = 4(N^2 - N)$$

> 📌 **Riassunto (p. 19).** Con $M = 2$ foto per individuo e $N$ individui: **genuini $\approx N$**, **impostori $\approx N^2 - N$**. Gli impostori crescono con il **quadrato** del numero di persone, i genuini **linearmente**.

> ⚠️ **Coppie ordinate e non ordinate.** Il conteggio $N \cdot 4(N-1)$ considera ogni coppia **due volte** (A contro B e B contro A). Con matching simmetrico le coppie **distinte** sono $2(N^2 - N)$. Il fattore 2 o 4 non cambia il messaggio: il rapporto tra impostori e genuini è dell'ordine di **$N$**. Con $N = 100$ persone ci sono 100 confronti genuini e circa 20.000 confronti impostori distinti.

> 💡 **Generalizzazione (nota didattica).** Con $M$ immagini per individuo, i confronti genuini distinti sono $N\binom{M}{2}$ e quelli impostori distinti sono $\binom{N}{2} M^2$. Per $M = 2$: $N$ genuini e $2N(N-1)$ impostori.

<!-- SOURCE_VISUAL id="TAB09-V04" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="18" type="schema di conteggio" description="Cardinalità degli impostori: 4(N-1) confronti per individuo, totale N*4(N-1) = 4(N^2 - N)" reason="Schema del conteggio con le foto di ogni individuo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.3 Attenzione alle code**

_(slide p. 20)_

Quando si fissa una soglia $T_1$ con **dati reali**, la distribuzione dei **genuini è rumorosa**: quanto vale davvero $FNMR(T_1)$? Con pochi campioni la stima è incerta. Si stima invece **meglio $FMR(T_1)$**, perché gli impostori sono molto più numerosi.

> 💡 **Il legame con la regola dei 3.** Per stimare un errore piccolo servono molti tentativi (Lezione 2, APACS in Lezione 1). Con $N$ genuini la stima di FNMR ha una risoluzione di circa $1/N$; con $N^2$ impostori quella di FMR arriva a circa $1/N^2$. È per questo che negli esperimenti si ottengono spesso **FMR molto bassi** ma **FNMR poco precisi**.

<!-- SOURCE_VISUAL id="TAB09-V05" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="20" type="grafico" description="Distribuzioni reali con genuini rumorosi e soglia T1: FNMR(T1) incerto, FMR(T1) stimato meglio" reason="Mostra la coda rumorosa dei genuini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Modelli teorici e calcolo di FMR e FNMR**

_(slide pp. 21–23)_

#### **4.1 Il modello teorico**

_(slide p. 21)_

- Un **modello teorico** permette di calcolare $FMR(\cdot)$ e $FNMR(\cdot)$ **in modo preciso**.
- Ma **quanto è vicina** la mia applicazione al modello teorico che ha generato le curve?
- Si hanno sia genuini sia impostori? **Non sempre**: in caso contrario si usano, ragionando, tutti i dati che si riescono a ricavare.

**Esempio**: il modello teorico della distribuzione degli impostori per l'**IrisCode** (la binomiale della Lezione 4) permette di calcolare probabilità di **$10^{-30}$**, **impossibili da verificare** con esperimenti: servirebbero $10^{30}$ persone.

<!-- SOURCE_VISUAL id="TAB09-V06" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="21" type="grafico" description="Modello teorico della distribuzione degli impostori per l'IrisCode" reason="Mostra la curva teorica usata per probabilità estreme" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.2 Dalle distribuzioni ai tassi di errore**

_(slide pp. 22–23)_

Si fissa la soglia a un valore $T$ tra il picco degli impostori e quello dei genuini (con score di **similarità**):

- una parte dei **genuini** cade **sotto** $T$ e non viene autorizzata: **False Non-Match**;
- una parte degli **impostori** cade **sopra** $T$ e viene autorizzata: **False Match**.

Con i **conteggi** (istogrammi):

$$FNMR(T) = \frac{FNM(T)}{\text{Totale genuini}} \qquad FMR(T) = \frac{FM(T)}{\text{Totale impostori}}$$

«R» sta per *Rate*. Se si conoscono le **distribuzioni** $p_n(s)$ degli **impostori** (*non-match*) e $p_m(s)$ dei **genuini** (*match*), i conteggi diventano **integrali**:

$$FMR(T) = \int_{T}^{+\infty} p_n(s)\, ds = 1 - \int_{-\infty}^{T} p_n(s)\, ds \qquad FNMR(T) = \int_{-\infty}^{T} p_m(s)\, ds$$

> ⚠️ **Integrali solo con le distribuzioni.** Le formule integrali si applicano **solo se si conoscono** $p_n$ e $p_m$; altrimenti si approssimano con gli **istogrammi**, sostituendo gli integrali con **sommatorie e conteggi**. La seconda forma di FMR usa il fatto che l'area totale sotto una densità vale 1.

<!-- SOURCE_VISUAL id="TAB09-V07" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="23" type="grafico con formule" description="Distribuzioni impostori p_n e genuini p_m con soglia T e aree che definiscono FMR e FNMR come integrali" reason="Verificare le formule e le aree sul grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. DET, ROC, EER e punti notevoli**

_(slide pp. 24–34)_

#### **5.1 La curva DET (ripetizione)**

_(slide pp. 24–25)_

Dalla strada statistica o da quella probabilistica si arriva comunque alla **DET**: $DET(T) = (FMR(T), FNMR(T))$, con $DET(T) \rightarrow (1, 0)$ per $T \rightarrow -\infty$ e $(0, 1)$ per $T \rightarrow +\infty$; la slide definisce $ROC(T) = 1 - DET(T)$ (Lezione 2, §10).

> 📌 **Data reporting, dato fondamentale #2: la curva DET**, da ottenere con le stesse fonti delle distribuzioni (datasheet, articoli, dati propri, valutazioni, volontari).

<!-- SOURCE_VISUAL id="TAB09-V08" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="24" type="grafici" description="Distribuzioni con soglia T1 e curva DET con FMR(T1) e FNMR(T1)" reason="Legame tra distribuzioni e punto della DET" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2 FMR, FNMR, FAR, FNAR, FRR**

_(slide pp. 26–27)_

Il funzionamento di un sistema, dal punto di vista degli errori, è descritto principalmente da $FMR(T)$ e $FNMR(T)$ **per tutti i valori della soglia**. Nel caso di **identificazione positiva** i tassi prendono anche altri nomi:

| Tasso dell'algoritmo | Nome nel riconoscimento positivo |
|---|---|
| $FMR(T)$ | **False Accept Rate** $FAR(T)$ |
| $FNMR(T)$ | **False Non-Accept Rate** $FNAR(T)$, anche **False Reject Rate** $FRR(T)$ |

**DET e ROC** (p. 27) mostrano **le stesse informazioni**. Nell'autenticazione positiva la **DET** mette in evidenza i **FNM** (genuini che **non entrano**), la **ROC** mette in evidenza $1 - FNM$ (genuini che **riescono a entrare**).

> **Richiamo — Lezione 1, §12.4.** A rigore FAR e FRR descrivono il sistema e includono i mancati acquisti (FTA), mentre FMR e FNMR descrivono il singolo confronto; le slide li usano come sinonimi nel riconoscimento positivo.

#### **5.3 Regioni di funzionamento e verso degli assi**

_(slide p. 28)_

Le regioni restano quelle della Lezione 2: **altissima sicurezza** (deposito di esplosivi), **applicazioni civili**, **applicazioni forensi** (trovare tutti i candidati per un template trovato su una scena). La soglia **non si regola completamente a piacimento**.

> ⚠️ **Attenzione agli assi.** La slide avverte che nella **dispensa della lezione successiva** gli **assi sono invertiti**. Leggendo una curva DET bisogna **sempre** controllare quale grandezza è in ascissa e quale in ordinata: la regione «alta sicurezza» cambia posizione a seconda della convenzione.

<!-- SOURCE_VISUAL id="TAB09-V09" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="28" type="curva DET con regioni" description="Regioni di funzionamento sulla DET con nota sugli assi invertiti nella dispensa successiva" reason="Mostra la convenzione degli assi usata nelle slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.4 EER e FNMR a FMR fissato**

_(slide pp. 29–30)_

> 📌 **Equal Error Rate.** Il tasso di errore nell'**unico punto** in cui $FNMR = FMR$. È l'**unico numero singolo** che può riassumere il funzionamento del sistema.

> 📌 **Data reporting, dato fondamentale #3: EER oppure $FNMR@FMR = xxx$.** Oltre all'EER si riporta spesso il FNMR misurato alla soglia che dà un FMR prefissato, per esempio $FNMR@FMR = 0{,}001$.

> 💡 **Quale dei due riportare.** L'EER è comodo per confrontare algoritmi, ma un'applicazione reale lavora raramente in quel punto. $FNMR@FMR = 0{,}001$ risponde invece a una domanda operativa: «se accetto al massimo un impostore su mille confronti, quanti genuini respingo?». Per un sistema di accesso sicuro è il dato più utile.

<!-- SOURCE_VISUAL id="TAB09-V10" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="30" type="curva DET" description="Curva DET con EER e punto FNMR@FMR=0,001" reason="Mostra i due indicatori sulla curva" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.5 Il sistema ideale e le scale logaritmiche**

_(slide pp. 31–32)_

In un **sistema ideale** le distribuzioni non si sovrappongono e la DET passa per tre punti:

| Punto | Soglia | $(FMR, FNMR)$ |
|---|---|---|
| **A** | $-\infty$ | $(1, 0)$ |
| **B** | intermedia | $(0, 0)$ |
| **C** | $+\infty$ | $(0, 1)$ |

Sulla ROC, con $1 - FNMR$ in ordinata, gli stessi punti sono $A = (1, 1)$, $B = (0, 1)$, $C = (0, 0)$.

**Variazioni della ROC** (p. 32): cambiando la **scala degli assi** si ottengono rappresentazioni equivalenti. Se l'errore su un asse diventa **molto piccolo**, conviene usare una **scala logaritmica**.

> 💡 **Perché la scala logaritmica.** Tra $FMR = 10^{-2}$ e $FMR = 10^{-5}$ c'è un fattore mille di sicurezza, ma su una scala lineare tra 0 e 1 i due punti sono indistinguibili. Con la scala logaritmica ogni ordine di grandezza occupa lo stesso spazio.

<!-- SOURCE_VISUAL id="TAB09-V11" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="31" type="grafici" description="Sistema ideale: distribuzioni separate, DET con punti A, B, C e ROC corrispondente" reason="Mostra la posizione dei punti notevoli" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB09-V12" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="32" type="grafici" description="Variazioni della ROC con scale lineari e logaritmiche" reason="Confronto visivo delle rappresentazioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.6 ZeroFMR e ZeroFNMR**

_(slide pp. 33–34)_

Quanto bisogna **alzare la soglia** di somiglianza per **non far entrare nessun impostore**? E **quanti genuini** restano fuori?

> 📌 **ZeroFMR.** La frazione di genuini **sotto soglia**, cioè $FNMR(t)$, alla soglia **più bassa** per cui $FMR = 0$. **ZeroFNMR** è il duale: il $FMR(t)$ alla soglia più alta per cui $FNMR = 0$.

Nei sistemi di riconoscimento positivo si parla anche di **ZeroFAR** e **ZeroFRR**:

| Regolazione | Modalità di funzionamento |
|---|---|
| soglia a **zeroFMR** (zeroFAR) | **alta security**: nessun impostore, al prezzo di rifiuti di genuini |
| soglia a **zeroFNMR** (zeroFRR) | **alta convenience**: nessun genuino respinto, al prezzo di accettare impostori |

> ⚠️ **«Zero» sul campione, non nella realtà.** $FMR = 0$ significa che **nessun impostore del dataset di test** ha superato la soglia. Con $n$ confronti impostori senza errori, la regola dei 3 dice solo che il vero FMR è probabilmente sotto $3/n$ (Lezione 2; Sistemi Biometrici, L06A §14).

<!-- SOURCE_VISUAL id="TAB09-V13" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="33" type="grafico" description="ZeroFMR e ZeroFNMR: soglie t sulle distribuzioni e corrispondenti tassi" reason="Localizza le due soglie sulle distribuzioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Identificazione: la curva CMC**

_(slide pp. 35–38)_

#### **6.1 Costruire la CMC passo per passo**

_(slide pp. 35–36)_

Esempio con **5 persone in galleria**. Si presenta **un'immagine come probe**: si fanno **5 confronti** e si **ordinano** gli esiti per similitudine (score 0,71; 0,70; 0,32; 0,30; 0,23). **Non si usa la soglia**: si guarda l'ordinamento dei match score.

- **$k = 1$**: si prende **solo il più simile** (0,71). In questo esempio **non è** la persona giusta: **errore**.
- **$k = 2$**: si controlla se la persona giusta è tra **i 2 più simili** (0,71 e 0,70). Lo è: **successo**.

Si ripete per **tutte le persone** della galleria usate come probe e si contano gli errori: si ottengono $CMC(k = 1)$, $CMC(k = 2)$, e così via. Più grande è $k$, maggiore è la probabilità che la persona cercata sia nell'insieme dei $k$ candidati. La slide commenta: il sistema è **perfettibile come identificatore**, ma **forse funziona come sistema di screening**.

<!-- SOURCE_VISUAL id="TAB09-V14" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="35" type="esempio" description="CMC con k=1: probe, 5 persone in galleria ordinate per score (0.71 errore, 0.70, 0.32, 0.30, 0.23)" reason="Mostra quale volto corrisponde a ciascuno score" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB09-V15" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="36" type="esempio" description="CMC con k=2: la persona corretta è tra i due più simili, successo" reason="Completa l'esempio della CMC" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2 La Cumulative Match Characteristic**

_(slide p. 37)_

**Procedimento di calcolo** con $N$ persone nel DB:

1. per ogni individuo $k$, si ordinano gli **$R$ individui più simili** a $k$ ($R$ = *rank*);
2. si conta **quante volte (in %)** $k$ era tra gli $R$ trovati;
3. si ripete per tutti i $k$ da 1 a $N$.

> 💡 **In formula (nota didattica).** Se $r(k)$ è la posizione in cui compare l'identità corretta per il probe $k$:
>
> $$CMC(R) = \frac{1}{N} \sum_{k=1}^{N} \mathbb{1}\big[r(k) \leq R\big]$$
>
> dove $\mathbb{1}[\cdot]$ vale 1 se la condizione è vera. La CMC è **non decrescente** in $R$ e vale 1 per $R = N$.

> 📌 **Data reporting: la CMC.** **Non è così grave** che la $CMC(1)$ sia bassa; è **importante** che la CMC **arrivi al 100% velocemente**, già per **rank bassi**.

<!-- SOURCE_VISUAL id="TAB09-V16" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="37" type="grafico" description="Curva CMC(R) che raggiunge il 100% per rank bassi" reason="Forma tipica della curva CMC" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.3 Identificazione gerarchica**

_(slide p. 38)_

Una soluzione per l'identificazione con **$N$ grande** è usare **due sistemi biometrici in cascata**:

| Stadio | Sistema | Uso |
|---|---|---|
| **SB1** | adatto all'**identificazione con N grande** (iride, impronta), veloce e compatto in memoria | si guarda la **CMC** e si sceglie il **rank $K$** che dà **probabilità di riconoscimento del 100%**: si riducono i candidati a **$K$ (pochi)** |
| **SB2** | **accurato**, con **ottima DET**, magari inefficiente per l'identificazione | si fissa una **soglia $T$** e, tra i $K$ candidati, si sceglie il più somigliante **se sopra soglia**; altrimenti l'uscita è «**non trovato**» |

Esempio della slide: **1.000.000** di candidati → SB1 → **$K = 18$** candidati → SB2 con **$T = 0{,}99$** → identità trovata **12334**.

> 💡 **Perché CMC per il primo stadio e DET per il secondo.** Il primo stadio non decide nulla: deve solo **non perdere** la persona giusta, quindi conta il rank al quale la CMC arriva al 100%. Il secondo stadio decide su pochi candidati, quindi contano FMR e FNMR alla soglia $T$. Combinare i due sfrutta la velocità del primo e l'accuratezza del secondo.

<!-- SOURCE_VISUAL id="TAB09-V17" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="38" type="schema" description="Identificazione gerarchica: SB1 riduce 1.000.000 di candidati a K=18 con la CMC, SB2 con DET e soglia T=0.99 trova l'identità 12334" reason="Schema del sistema in cascata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Soglia fissata e probabilità di errore**

_(slide pp. 39–45)_

#### **7.1 Il sistema diventa un classificatore**

_(slide p. 39)_

Una volta **fissata la soglia $T$** (di similitudine; il concetto è duale con le distanze), il sistema è **completamente definito** e diventa un **classificatore**, con la regola di comportamento della verifica (Lezione 2, §7.2):

$$(I, X_Q) \in \begin{cases} \omega_1 & \text{se } S(X_Q, X_I) \geq T \\ \omega_2 & \text{altrimenti} \end{cases}$$

Si definisce la **probabilità di errore**:

$$p = P(\text{errore}) = P(FM(T)) + P(FNM(T))$$

con la **stima** possibile $p = \frac{FM + FNM}{\text{TOTALE}}$.

> ⚠️ **$p$ dipende dal mix di tentativi (nota didattica).** $P(FM)$ e $P(FNM)$ sono probabilità **congiunte**: se $\pi_{imp}$ è la frazione di tentativi fatti da impostori e $\pi_{gen}$ quella dei genuini,
>
> $$p = \pi_{imp} \cdot FMR(T) + \pi_{gen} \cdot FNMR(T)$$
>
> Lo stesso sistema, con la stessa soglia, ha un $p$ diverso in un ufficio (quasi solo genuini) e in un varco sotto attacco (molti impostori). Per questo $p$ va sempre riportato insieme alle condizioni in cui è stato misurato.

#### **7.2 Quale modello statistico?**

_(slide pp. 40–44)_

Esempi per introdurre il discorso:

| Sistema | Probabilità |
|---|---|
| lancio di un dado **equo** | $P(6) = 1/6 \approx 0{,}1666$ |
| lancio di un dado **truccato** | $P(6) = 0{,}67$ |
| classificatore del **genere** | $P(\text{classificazione sbagliata}) = 0{,}2$ |

Un tipico **classificatore di immagini** può essere **multiclasse** («che oggetto è?» → albero) o **binario** («è un albero?», «sono due alberi?» → YES). Allo stesso modo il **classificatore biometrico** è **multiclasse** in identificazione («chi è?» → Donald) e **binario** in autenticazione («sono la stessa persona?» → NO). In entrambi i casi:

> 📌 **Sistema biometrico = classificatore con $P(\text{errore}) = p$.** Il sistema, con i suoi due ingressi (campione e template) e un'uscita, si modella come un esperimento aleatorio con probabilità di errore $p$. Esempio della slide: un sistema di autenticazione con **soglia 0,395** ha **$p = 0{,}02$**.

> 💡 **Verso la prossima lezione.** Se ogni tentativo è un esperimento indipendente con probabilità di errore $p$, il numero di errori su $n$ tentativi segue una **binomiale**: da qui si ricavano intervalli di confidenza, regola dei 3 e dimensionamento dei test (Lezione 10).

<!-- SOURCE_VISUAL id="TAB09-V18" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="44" type="schema" description="Sistema biometrico come classificatore con ingresso 1, ingresso 2, uscita; autenticazione con P(errore)=0.02 a soglia 0.395" reason="Esempio numerico del modello" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.3 Metriche di valutazione**

_(slide p. 45)_

Le metriche per valutare un classificatore sono le stesse della Lezione 5: **accuratezza**, **precision e recall**, **errore quadratico**, **likelihood**, **probabilità a posteriori**, **costo/utilità**, **margine**, **entropia**, **divergenza K-L**, …

---

## **Parte B — Reti deep per il volto: la libreria DeepFace**

### **8. Che cos'è DeepFace**

_(slide pp. 46–55)_

#### **8.1 Contesto e funzioni**

_(slide pp. 46–49)_

DeepFace è una delle **librerie più versatili, utili e aggiornate** per il riconoscimento del volto.

> ⚠️ **Due «DeepFace» diversi.** **DeepFace** è anche il nome della **rete** di Facebook/Meta del 2014 (Lezione 8, §5.3: 9 strati, allineamento 3D, 97,35% su LFW). Qui si parla invece della **libreria Python open source** `deepface`, scritta principalmente da **Sefik Ilkin Serengil**. La libreria **non nasce da zero**: integra e riusa (*wrapping* e adattamento) implementazioni open source di diversi modelli.

La libreria offre un'**interfaccia unificata** per il riconoscimento facciale deep, integra modelli allo stato dell'arte (**VGG-Face**, **FaceNet**, **ArcFace**, …) e **pipeline complete** di face detection, embedding e matching.

| Funzione | Descrizione |
|---|---|
| **Face verification** | stabilire se due volti sono della stessa persona |
| **Face identification** | trovare l'identità di un volto in una galleria |
| **Facial attribute analysis** | stimare **età**, **genere**, **emozioni**, **razza/etnia** |
| **Real-time face analysis** | riconoscimento e analisi degli attributi su un **flusso video** in tempo reale, per esempio da webcam |

<!-- SOURCE_VISUAL id="TAB09-V19" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="49" type="panoramica" description="Funzioni disponibili nella libreria DeepFace: verification, identification, facial attribute analysis, real-time analysis" reason="Panoramica visiva delle funzioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.2 I modelli di riconoscimento integrati**

_(slide pp. 50–55)_

Chiamare DeepFace un semplice «wrapper» è **riduttivo**: contiene diversi modelli.

| Modello | Caratteristiche (slide) |
|---|---|
| **VGG-Face** | noto per la **profondità**, adattato al volto da un modello VGG pre-allenato; **97,78%** di precisione su LFW; è il **modello predefinito** della libreria |
| **OpenFace** | open source, mira a prestazioni **comparabili a FaceNet** con **complessità computazionale inferiore**; adatto al **tempo reale** |
| **ArcFace** | **robusto e accurato**, soprattutto in scenari con **classi aperte**, dove il numero di identità è sconosciuto in training (per esempio **videosorveglianza**) |
| **DeepID** | serie di modelli con miglioramenti incrementali, efficaci con variazioni di **angolazione, illuminazione, espressione** |
| **Dlib** | «la più semplice API di riconoscimento facciale al mondo per Python»; mappa il volto in uno spazio a **128 dimensioni**; il matching è una **distanza euclidea** |

**Come scegliere** (p. 55): si considerano **accuratezza**, **velocità di inferenza** e **risorse computazionali**; ogni modello ha vantaggi e svantaggi nel contesto applicativo. La funzione `DeepFace.stream(db_path=...)` accede alla **webcam** e applica insieme riconoscimento e analisi degli attributi.

> 💡 **ArcFace e la Lezione 8.** ArcFace è robusto in insieme aperto proprio per il **margine angolare** della sua loss: le identità mai viste restano separate. Coerentemente, con i suoi embedding si usa la **distanza coseno**.

---

### **9. I face detector**

_(slide pp. 56–60)_

Prima del riconoscimento serve rilevare il volto. DeepFace offre diversi *detector backend* (il parametro `detector_backend`):

| Detector | Tecnologia | Rilevamento | Allineamento | Velocità |
|---|---|---|---|---|
| **OpenCV** | Haar Cascade, **non deep** (già visto nella Lezione 7) | buono | limitato | veloce |
| **Dlib** | **HOG** (*Histogram of Oriented Gradients*), **non deep** | punteggi relativamente **elevati** | punteggi relativamente **elevati** | veloce |
| **SSD** (*Single-Shot Detector*) | **deep**, molto popolare | paragonabile a OpenCV, **elevato** | **medio**: non supporta i landmark e usa il rilevatore di occhi di OpenCV | veloce |
| **MTCNN** | **deep con landmark** facciali | **elevato** | **elevato** | **più lento** di OpenCV, SSD e Dlib |
| **RetinaFace** | **deep**, il modello pubblico più avanzato | **il migliore**, anche *in the wild* | elevato | **il più lento**: richiede molto calcolo |

L'uso è molto intuitivo (p. 60): si sceglie il detector da una lista `["opencv", "ssd", "mtcnn", "dlib", "retinaface"]` e lo si passa alla funzione di rilevamento.

> 💡 **L'allineamento conta quanto il rilevamento.** Un volto rilevato ma ruotato produce un embedding peggiore. I detector con **landmark** (MTCNN, RetinaFace) permettono di ruotare e scalare il volto in una posizione standard prima dell'estrazione, come l'allineamento 2D/3D di DeepFace-Meta.

<!-- SOURCE_VISUAL id="TAB09-V20" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="56" type="confronto di immagini" description="Diversi face detector usabili in DeepFace applicati alla stessa immagine" reason="Confronto visivo dei rilevamenti" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB09-V21" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="57" type="confronto di immagini" description="Face detector OpenCV tra quelli disponibili" reason="Esempio del detector già noto dal laboratorio precedente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB09-V22" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="59" type="esempio" description="Risultati di RetinaFace" reason="La slide contiene solo l'esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **10. Le funzioni in pratica**

_(slide pp. 61–67)_

- **Emotion detection** (p. 61): verrà trattata più in dettaglio più avanti nel corso (Lezione 16).
- **Informazioni estraibili** (p. 62): età, genere, emozione, etnia.
- **Face authentication** (p. 63) e **face recognition** in identificazione (p. 64), con **probe** e **gallery**.
- **Probe e gallery** (pp. 65–66): le definizioni della Lezione 1, §8. La **sonda** è il campione attualmente interrogato; la **galleria** è la collezione di campioni memorizzati, pre-elaborati e documentati con metadati.

> ⚠️ **Non è perfetto** (p. 67). Che uno strumento sia molto avanzato o provenga da un ottimo laboratorio **non significa** che sia il migliore. Usandolo su esempi pratici se ne vedono i **limiti**. È rilasciato **gratuitamente**, e probabilmente esistono modelli molto più avanzati **non rilasciati**. Nel corso si vedrà come **personalizzare** i modelli e renderli più efficaci per le proprie esigenze.

<!-- SOURCE_VISUAL id="TAB09-V23" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="61" type="esempio" description="Emotion detection su volti" reason="La slide contiene solo l'esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB09-V24" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="62" type="esempio" description="Alcune delle informazioni estraibili da un volto con DeepFace" reason="Mostra gli attributi stimati" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB09-V25" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="64" type="schema" description="Face recognition in identificazione con probe e gallery" reason="Mostra la struttura probe contro galleria" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **11. Laboratorio DeepFace**

_(slide pp. 68–79, 81–82)_

> 📌 **Il codice non è materia d'esame**; vanno studiate le **osservazioni** e le **nozioni teoriche**.

#### **11.1 Divide et impera e versioni**

_(slide pp. 68–70)_

Si richiama l'approccio **divide et impera** della Lezione 8: face detector → ritaglio → stimatore dell'età → sfocatura.

**Nota sulle versioni** (p. 70): controllare gli aggiornamenti delle librerie e installare la versione corretta di TensorFlow. Per un PC locale, a inizio 2026: TensorFlow **2.16.1 o superiore** con il pacchetto `tf-keras` (ponte verso il vecchio Keras), **deepface 0.0.98**, e **NumPy inferiore alla 2.0**.

> 💡 **Perché fissare le versioni.** Le librerie deep evolvono rapidamente e le API cambiano (per esempio Keras 3 in TensorFlow 2.16 e NumPy 2.0 hanno rotto la compatibilità con molto codice). Fissare le versioni rende il laboratorio **riproducibile**.

#### **11.2 Analisi di età e genere**

_(slide pp. 71–75)_

- **Versione Colab** (p. 71): si installa la libreria, si importa `DeepFace` e si chiama l'analisi sull'immagine `Mark1.jpg` con le azioni **età** e **genere**.
- **Con il detector di Haar** (pp. 72–73): si legge l'immagine (`Mark2.jpg` o `face3.jpg`), si controlla che sia stata caricata, si rilevano i volti in scala di grigi con Haar Cascade, si **ritaglia** ogni volto, si chiama l'analisi di età e genere sul ritaglio e si scrivono età e genere sull'immagine.
- **Attenzione a verificare gli output** (pp. 74–75): **non sempre le cose vanno come ci si aspetta**. Bisogna (1) **verificare gli output** e (2) **inserire controlli incrociati**.

Le immagini `Mark1.jpg`, `Mark2.jpg` e `face3.jpg` sono fornite con i materiali di laboratorio della Lezione 11.

<!-- SOURCE_VISUAL id="TAB09-V26" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="74" type="risultato del laboratorio" description="Output del laboratorio con età e genere stimati sui volti rilevati" reason="Slide senza testo con il risultato dell'analisi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB09-V27" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="75" type="risultato del laboratorio" description="Esempio di output inatteso che richiede verifica e controlli incrociati" reason="Mostra un errore concreto dell'analisi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.3 Verifica dell'identità**

_(slide p. 76)_

La funzione di verifica confronta due immagini (`img1_path`, `img2_path`). Può gestire **più volti** nelle immagini: in quel caso vengono confrontati i volti **più simili** tra loro.

#### **11.4 Compiti facoltativi**

_(slide pp. 77–79)_

I compiti **non sono un progetto d'esame** e **non danno punti**. Si inviano per email al docente: (1) il **codice**, (2) le **immagini di ingresso**, (3) le **immagini di uscita** prodotte e (4) un **PDF di una pagina** che spiega ingressi, uscite e risultati. Il docente **non esegue i codici** per vedere gli output: servono per **imparare** e diventare più operativi; in caso di richiesta di tesi, compiti ben eseguiti **saranno valutati** (senza garanzie). Saltare i punti 3 e 4 «non è un buon segnale».

| Compito | Richiesta |
|---|---|
| **«BLUR MINORI»** | script Python che legge un'immagine, **trova i volti**, **valuta l'età** ed esegue una **sfocatura dei volti dei minori di 14 anni** (la slide confronta un esempio di sfocatura manuale con uno automatico) |
| **«AGE DETECTOR IN THE WILD»** | raffinare lo script per analizzare **più volti possibili** in `face3.jpg` e in scenari **meno controllati** |

<!-- SOURCE_VISUAL id="TAB09-V28" source="Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf" page="78" type="confronto di immagini" description="Compito BLUR MINORI: sfocatura manuale contro automatica dei volti dei minori" reason="Esempio visivo del risultato atteso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.5 Il codice di appendice**

_(slide pp. 81–82)_

L'appendice contiene la versione completa del codice (non richiesta all'esame), presentata come **«traccia di codice da ottimizzare e migliorare»**. Rispetto a quella delle slide aggiunge due protezioni: salta i ritagli **vuoti** e intercetta le eccezioni dell'analisi.

> ⚠️ **Il difetto da correggere (nota didattica).** Il codice analizza **un solo volto ritagliato** alla volta, quindi l'analisi restituisce una lista con **un solo elemento**; ma poi legge il risultato con l'indice del volto, `predictions[i]`. Dal secondo volto in poi ($i \geq 1$) l'indice è fuori dalla lista e il volto viene saltato con il messaggio di «mismatch». Il risultato corretto del ritaglio è sempre `predictions[0]`. È il tipo di controllo incrociato degli output che la slide p. 75 raccomanda.

---

### **12. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Inferenza statistica** / **Calcolo delle probabilità** | dal campione alla popolazione / dal modello noto ai risultati |
| **Cardinalità genuini** / **impostori** | circa $N$ / circa $N^2 - N$ |
| **FMR stimato** / **FNMR stimato** | preciso (molti impostori) / rumoroso (pochi genuini) |
| **Conteggi** / **Integrali** | con istogrammi / solo con distribuzioni note $p_n$, $p_m$ |
| **DET** / **ROC** | evidenzia i genuini respinti / evidenzia i genuini accettati |
| **EER** / **FNMR@FMR** | punto in cui gli errori coincidono / genuini respinti a un FMR operativo |
| **ZeroFMR** / **ZeroFNMR** | alta security / alta convenience |
| **CMC** / **DET** | identificazione per rank, senza soglia / verifica con soglia |
| **SB1** / **SB2** gerarchici | riduce i candidati al rank della CMC al 100% / decide con soglia su pochi candidati |
| **DeepFace (Meta)** / **`deepface` (libreria)** | rete del 2014 / libreria Python che integra più modelli |
| **OpenCV/Dlib** / **MTCNN/RetinaFace** | detector non deep, veloci / deep con landmark, più accurati e lenti |

---

### **13. Sintesi della lezione**

_(slide p. 80)_

> ✅ **In sintesi.**
> - **Flusso di reporting**: genuini e impostori → distribuzioni → soglia → errori in autenticazione → generalizzazione in identificazione; non fidarsi ciecamente dei datasheet.
> - **Cardinalità**: genuini $\approx N$, impostori $\approx N^2 - N$; per questo FNMR è stimato peggio di FMR.
> - **FMR e FNMR** da conteggi o da integrali sulle distribuzioni; modelli teorici per probabilità non verificabili sperimentalmente.
> - **Dati da riportare**: distribuzioni, DET, EER o FNMR@FMR, CMC; attenzione agli **assi** e alle scale logaritmiche; **ZeroFMR** e **ZeroFNMR**.
> - **CMC** per l'identificazione e **sistemi gerarchici** a due stadi.
> - Con soglia fissata il sistema è un **classificatore con probabilità di errore $p$**.
> - **Libreria DeepFace**: funzioni, modelli (VGG-Face predefinito, OpenFace, ArcFace, DeepID, Dlib a 128 dimensioni), detector (OpenCV, Dlib, SSD, MTCNN, RetinaFace); laboratorio con età e genere, verifica degli output, compiti facoltativi.
