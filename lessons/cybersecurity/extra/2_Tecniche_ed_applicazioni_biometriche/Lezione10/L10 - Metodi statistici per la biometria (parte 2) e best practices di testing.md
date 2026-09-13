# **Lezione 10: Metodi statistici per la biometria (parte 2) e best practices di testing**

---

### **0. Panoramica della lezione**

_(slide pp. 1–2)_

La Lezione 10 completa i modelli statistici della Lezione 9 e affronta la valutazione dei sistemi che **apprendono**. È composta da:

- **Parte A** — il sistema biometrico come **classificatore che sbaglia con probabilità $p$**: prove di Bernoulli, binomiale, regola dei 3, crescita degli errori in identificazione, regola dei 30;
- **Parte B** — la **dispensa** *Best Practices in Testing and Reporting Performance of Biometric Devices* (materia d'esame);
- **Parte C** — la valutazione in presenza di **machine learning**: overfitting, partizioni, cross-validation (k-FCV, stratified, 5×2, LOO), **LOPO/LNPO** e nuove tecniche zero-shot e few-shot.

La **Parte D** ricostruisce la dispensa (Mansfield e Wayman, NPL, 2002).

> **Richiamo — Sistemi Biometrici.** Bernoulli, binomiale, regola dei 3 e dei 30 e crescita dell'errore in identificazione sono trattati in [L06A — Misura dei parametri](<../../../anno3/6_Sistemi_Biometrici/L06A - Misura dei parametri di un sistema biometrico.md>) e [L6B — Dispensa operativa](<../../../anno3/6_Sistemi_Biometrici/L6B - Dispensa operativa sulla misura dei parametri dei sistemi biometrici.md>).

---

## **Parte A — Il sistema biometrico come esperimento aleatorio**

### **1. Errori come prove di Bernoulli**

_(slide pp. 3–9)_

#### **1.1 Dal classificatore alla prova di Bernoulli**

_(slide pp. 3–7)_

Come nella Lezione 9, il sistema biometrico è un **classificatore** (multiclasse in identificazione, binario in autenticazione) con **$P(\text{errore}) = p$**; per esempio un sistema di autenticazione con soglia 0,395 e $p = 0{,}02$.

> 📌 **Prova di Bernoulli.** Un esperimento con **due soli esiti** possibili, «successo» (1) e «insuccesso» (0), in cui la **probabilità $p$** del successo è **costante** e i risultati delle prove sono **indipendenti**. Esempi: «dado = 6», «scommessa sul dado persa», «genere non correttamente individuato».

**Come si modellano gli errori** (p. 7):

- date due impronte generiche (genuine o impostori), il sistema in **autenticazione** mostra un tasso medio di errore **stimabile e fissato** $p$: l'evento «il sistema sbaglia un'autenticazione» è una **prova di Bernoulli**;
- un sistema usato in **identificazione (1:N)** si modella come **$N$ prove di Bernoulli**, cioè un **processo di Bernoulli**.

#### **1.2 Il «mezzo pollo statistico»**

_(slide p. 8)_

Come nel celebre «mezzo pollo a testa» (se uno mangia due polli e un altro nessuno, in media ne hanno mangiato uno ciascuno), dire $p(\text{errore}) = 5\%$ può significare cose **molto diverse** per gli utenti:

| Popolazione di 100 utenti | Errore medio |
|---|---:|
| 90 persone con errore 0% e 10 con errore 50% | $\frac{10 \cdot 50\%}{100} = 5\%$ |
| 50 persone con errore 0% e 50 con errore 10% | $\frac{50 \cdot 10\%}{100} = 5\%$ |
| 30 persone con errore 0% e 70 con errore 7% | $\frac{70 \cdot 7\%}{100} \approx 5\%$ |

> ⚠️ **Dipende dallo zoo.** Il primo caso ha pochi utenti (**capre**) che sbagliano metà delle volte; il secondo distribuisce l'errore su metà della popolazione. La media è la stessa, ma l'**esperienza** degli utenti e le contromisure (per esempio un tratto alternativo per le capre) sono completamente diverse. Dipende da quante **pecore, capre, agnelli e lupi** ci sono (Lezione 2, §11.1).

#### **1.3 La distribuzione di Bernoulli**

_(slide p. 9)_

Se $X$ vale 1 (errore) con probabilità $p$ e 0 con probabilità $1 - p$:

$$E[X] = p \qquad \text{Var}(X) = p(1-p) \qquad \sigma = \sqrt{p(1-p)}$$

**Esempio della slide**: $p = 0{,}01$ dà $\text{Var} = 0{,}01 \cdot 0{,}99 = 0{,}0099$ e $\sigma \approx 0{,}099$. La distribuzione di Bernoulli **descrive l'errore in autenticazione**, cioè di una singola prova.

<!-- SOURCE_VISUAL id="TAB10-V01" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="9" type="grafico" description="Distribuzione di Bernoulli con p=0,01, varianza 0,0099 e deviazione standard 0,099" reason="Grafico della distribuzione a due valori" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. La distribuzione binomiale**

_(slide pp. 10–13)_

#### **2.1 La formula**

_(slide p. 10)_

La binomiale **descrive gli errori in identificazione** o su **$N$ utenti autenticati**. Sia $X_i$ la variabile che vale 1 se il sistema sbaglia la prova $i$ (probabilità $p$) e 0 altrimenti ($q = 1 - p$). Il **numero di errori** dopo $n$ prove è

$$S_n = X_1 + X_2 + \dots + X_n$$

e la probabilità di avere **esattamente $k$ errori** su $n$ prove è

$$P(S_n = k) = \binom{n}{k} p^k q^{n-k} = \frac{n!}{k!\,(n-k)!}\, p^k (1-p)^{n-k}$$

cioè il **numero di sequenze** con $k$ successi e $n - k$ fallimenti, moltiplicato per la **probabilità** che una qualunque di esse si verifichi.

> 💡 **Esempio della slide svolto.** Con $p = 10^{-2}$, la probabilità di **1 errore su 1000 prove** si ottiene con $n = 1000$ e $k = 1$:
>
> $$P(S_{1000} = 1) = 1000 \cdot 0{,}01 \cdot 0{,}99^{999} \approx 10 \cdot e^{-10{,}04} \approx 4{,}4 \cdot 10^{-4}$$
>
> Il valore è piccolo perché con $p = 1\%$ su 1000 prove ci si aspettano circa $np = 10$ errori, non uno solo.

#### **2.2 La roulette russa**

_(slide p. 11)_

| Roulette russa | Sistema biometrico in identificazione |
|---|---|
| $p$ = probabilità di «errore» a ogni colpo = $1/6$ (ruotando il tamburo) | $p$ = probabilità di errore di una **singola autenticazione** |
| $N$ tentativi | $N$ = **numero di utenti** nel DB confrontati |
| $P(\text{almeno un colpo in } N) = 1 - (5/6)^N$ | $P(\text{almeno un errore}) = 1 - (1-p)^N$ |

> 💡 **L'analogia.** Anche con probabilità piccola per ogni tentativo, ripetendo il «gioco» $N$ volte la probabilità di almeno un evento negativo cresce rapidamente: con 6 colpi la roulette arriva già a $1 - (5/6)^6 \approx 67\%$. È esattamente ciò che succede a un sistema che confronta un campione con $N$ template (§5).

#### **2.3 La forma della binomiale**

_(slide pp. 12–13)_

- Con **20 lanci** di una moneta **normale** ($p = 0{,}5$) la distribuzione è simmetrica attorno a 10; con una moneta **truccata** ($p = 0{,}7$) si sposta verso 14; con **40 lanci** si allarga. La binomiale **non è una distribuzione continua**: è definita solo per $k$ interi.
- **I sistemi biometrici lavorano con errori bassi.** Con $p = 0{,}01$ la forma **cambia completamente** e **cade subito verso zero** (esempio MATLAB `binopdf(k, 20, 0.01)`).

La probabilità di **nessun errore su 20 test** è

$$P(S_{20} = 0) = 0{,}99^{20} \approx 0{,}817$$

<!-- SOURCE_VISUAL id="TAB10-V02" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="12" type="grafici" description="Binomiale per 20 lanci con p=0,5 e p=0,7 e per 40 lanci con p=0,5" reason="Mostra forma e discretezza della binomiale" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB10-V03" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="13" type="grafico" description="Binomiale B(20, 0,01) calcolata con binopdf, con P(0 errori) = 0,817" reason="Forma della binomiale con errore basso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Che cosa bisogna stimare**

_(slide p. 14)_

Dato un sistema con tasso di errore $p$ in una generica autenticazione:

1. quanto può **cambiare $p$** se si ripete l'esperimento con gli stessi volontari?
2. qual è il tasso di errore $p_2$ se si usa il sistema per **identificare 100 persone** (un dipartimento)?
3. qual è il tasso $p_3$ per **1.000.000 di persone**?
4. come si **stima** il tasso di errore iniziale $p$?
5. **quante persone $N$** servono per una buona stima di $p$?
6. come si **definisce correttamente** una buona stima di $p$?

Le risposte sono nelle regole dei paragrafi successivi e nella dispensa.

---

### **4. La regola dei 3**

_(slide pp. 15–16)_

> 📌 **Regola dei 3.** «Qual è il tasso di errore più basso $p$ che può essere stimato con un esperimento di confronto su $N$ campioni **indipendenti**?» È il tasso per cui la probabilità di osservare **zero errori** in $N$ prove è il 5%:
>
> $$p \approx \frac{3}{N} \quad \text{(confidenza al 95\%)}$$

**Come leggerla**: se un sistema commette **zero errori su $N$ prove**, non si deve pensare di avere un sistema con $p = 0$, ma, con il 95% di confidenza, un sistema con **$p \lesssim 3/N$**.

**Esempio della slide**: 300 prove indipendenti con zero errori permettono di dire, con confidenza al 95%, che il tasso di errore è **al più $3/300 = 1\%$**. L'intervallo di riferimento è quello di **Clopper–Pearson** (1934).

> 💡 **Da dove viene il 3.** Si impone che zero errori siano un evento raro: $(1-p)^N = 0{,}05$. Passando ai logaritmi, $N \ln(1-p) = \ln 0{,}05 \approx -3$, e per $p$ piccolo $\ln(1-p) \approx -p$, quindi $Np \approx 3$. La dispensa aggiunge che per il **90%** di confidenza vale $p \approx 2/N$, perché $\ln 0{,}10 \approx -2{,}3$.

**Estensione** (p. 16): se il sistema commette, per esempio, **5 errori** su $N$ prove, il tasso teorico stimabile e il suo **intervallo di confidenza** si ricavano dagli **intervalli di confidenza della binomiale** (tabella della slide).

<!-- SOURCE_VISUAL id="TAB10-V04" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="16" type="tabella" description="Estensione della regola dei 3: tasso teorico stimabile e intervallo di confidenza della binomiale con 5 errori su N prove" reason="I valori della tabella sono solo nell'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Da verifica a identificazione**

_(slide p. 17)_

«**Quanto aumentano gli errori** se si usa un sistema di verifica ($FMR$, $FNMR$) in modalità di identificazione ($FMR_N$, $FNMR_N$)?» Con **ipotesi semplificative** (campioni indipendenti, nessuna indicizzazione del DB, un solo campione per utente), in prima approssimazione:

$$FNMR_N = FNMR$$

$$FMR_N = 1 - (1 - FMR)^N \approx N \cdot FMR$$

- I tassi di errore per i **genuini non cambiano**: il genuino deve comunque essere confrontato con il proprio template.
- $FMR_N$ è $1 -$ la probabilità che **non ci sia nessun falso match** su tutti gli $N$ campioni confrontati.
- Con FMR «piccolo» l'errore di falso match **aumenta linearmente con la dimensione del DB**.

> ⚠️ **L'approssimazione lineare ha un limite.** $N \cdot FMR$ vale solo se $N \cdot FMR \ll 1$. Con $FMR = 10^{-6}$ e $N = 10^6$ la formula lineare dà 1, cioè 100%, mentre quella esatta dà $1 - (1 - 10^{-6})^{10^6} \approx 1 - e^{-1} \approx 63\%$: comunque inaccettabile per un sistema nazionale. È la ragione per cui l'identificazione su larga scala richiede FMR molto più bassi di $1/N$ (Lezione 2, §12.3).

---

### **6. La regola dei 30**

_(slide p. 18)_

> 📌 **Regola dei 30** (Doddington). Serve a determinare la **dimensione del campione** di test: «**per essere sicuri, con confidenza al 90%, che il tasso di errore vero sia entro ±30% del tasso osservato, bisogna osservare almeno 30 errori**».

**Esempio**: con **30 falsi non-match** su **3000** confronti genuini indipendenti, il tasso osservato è 1% e si può dire, con confidenza al 90%, che l'errore vero sta **tra 0,7% e 1,3%**.

> 💡 **Come si usa per dimensionare un test.** Se l'obiettivo è misurare un $FNMR$ intorno all'1% e un $FMR$ intorno allo 0,1%, per osservare 30 errori di ciascun tipo servono circa $30 / 0{,}01 = 3000$ tentativi genuini e $30 / 0{,}001 = 30.000$ tentativi impostori (esempio della dispensa, §18.4).

---

### **7. Riepilogo e argomenti di progetto**

_(slide pp. 19–23)_

La slide p. 19 riassume gli argomenti teorici utili nei progetti biometrici: **non idealità del mondo reale**, **non indipendenza dei campioni**, **zoo di Doddington** e casi singolari, **dimensione del test**, **regola dei 3 e dei 30**, **intervalli di confidenza**, lungo il flusso di lavoro della Lezione 9. L'outline della seconda parte (p. 20): dispensa Best Practices e metodo per progettare e misurare le prestazioni di modelli di machine learning.

Altri elementi da riportare nel **data reporting**:

- **FTE e FTA** (p. 21): i tassi di **mancato enrollment** e **mancata acquisizione**, legati al modulo di acquisizione e al quality checker;
- **tentativi zero-effort** e **presentation attack** (p. 22): vanno **previsti e dettagliati**; sono trattati nella parte finale del corso;
- **strategie di valutazione** (p. 23): **technology**, **scenario**, **operational** (Lezione 2, §11.3), a cui la dispensa fa riferimento continuamente.

---

## **Parte B — La dispensa nelle slide**

### **8. Come studiare la dispensa**

_(slide pp. 24–38)_

> 📌 **La dispensa è materia d'esame** e va studiata perché riassume bene molti argomenti del corso: **sì le sezioni 1, 2, 3, 4, 5; no la sezione 6; sì le sezioni 7 e 8**.

Le slide pp. 25–38 riportano estratti della dispensa, in particolare della sezione **7 (Reporting performance results)**, con i commenti del docente:

| Slide | Commento del docente |
|---|---|
| pp. 26–31 | si esaminano i **dettagli richiesti** per una **descrizione completa** del sistema e del test |
| p. 32 | dati **molto importanti** da comunicare nella documentazione; la slide annota «12h» e «30 anni» |
| p. 33 | i dati riguardano tutta la pipeline: acquisizione, quality checker, estrazione, matching e soglia |
| p. 34 | esempio di errore: **creare un test troppo favorevole**, o in condizioni **troppo diverse** da quelle reali |
| p. 35 | **policy**: «se l'utente non capisce bene il protocollo lo escludiamo»; va bene, **ma poi come si stimano questi utenti in deployment?** |
| p. 36 | esempi di casi da documentare: **malattie**, **tatuaggi**, **protesi estetiche estreme** |

> ⚠️ **Escludere un utente dal test non lo esclude dal mondo.** Se il protocollo scarta chi non collabora bene, il FTE e il FNMR misurati sono ottimistici: in esercizio quelle persone useranno comunque il sistema. Esclusioni e casi anomali vanno **documentati** (dispensa, §7 punto j).

<!-- SOURCE_VISUAL id="TAB10-V05" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="25" type="estratto della dispensa" description="Parti molto utili della dispensa Best Practices evidenziate dal docente" reason="La slide è uno screenshot della dispensa" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB10-V06" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="32" type="estratto della dispensa annotato" description="Reporting performance results (2) con annotazioni 12h e 30 anni" reason="Verificare a quali voci si riferiscono le annotazioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB10-V07" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="37" type="estratto della dispensa" description="Reporting performance results (2): ulteriori dati da comunicare" reason="Screenshot della dispensa senza testo aggiuntivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte C — Valutare i sistemi che apprendono**

### **9. Il flusso di lavoro con il machine learning**

_(slide pp. 39–45)_

#### **9.1 Il passo in più: allenare**

_(slide pp. 39–42)_

Il flusso della Lezione 9 si arricchisce di un passo: **allenare il sistema**, applicando le **opportune procedure di training**, prima di calcolare le distribuzioni.

Comprare un sistema «off the shelf» **con ML** apre casi ulteriori: si ha già $p$? Il sistema va **allenato** o sottoposto a **fine tuning**? Si sceglie solo $T$? Ci si fida dei datasheet?

> ⚠️ **Anche solo cambiare il localizzatore di volti cambia tutte le prestazioni.** In un sistema basato su ML ogni modulo (detector, allineamento, estrattore) è stato ottimizzato insieme agli altri: sostituirne uno invalida le misure precedenti.

#### **9.2 Problemi e soluzioni**

_(slide pp. 43–45)_

Se il classificatore **apprende da esempi** (per esempio una rete neurale), come se ne stima l'accuratezza? La gestione dei dati diventa **critica**, perché compaiono **overfitting**, **underfitting**, **bias** e **leakage delle informazioni**. Le soluzioni sono:

- **corretta gestione del dataset**;
- creazione e uso di **partizioni** opportune;
- tecniche di **cross-validation**.

**Quale modello scegliere** tra diverse reti o modelli allenati? Si usano **metodi statistici** di confronto:

- **test parametrici**, rispettandone le condizioni d'uso;
- **test non parametrici** per confrontare **due modelli su più dataset**;
- procedure per **confronti multipli** tra più di due modelli.

---

### **10. Dataset e partizioni**

_(slide pp. 46–56)_

#### **10.1 Benchmark e scelta dei dati**

_(slide pp. 46–48)_

- Lo scopo finale del ML è **risolvere problemi reali**, ma testare una tecnica in ogni condizione **non è fattibile**: si valuta su un insieme di **dataset pubblici standard** (*benchmark*).
- **Non è attualmente possibile** realizzare un sistema che riconosca un volto **in ogni tipo di immagine**: bisogna costruirlo **specifico per la propria applicazione** (l'esempio della slide mostra volti con **mascherine**).
- I dati vanno usati correttamente per **evitare bias**. Come scegliere? Dati **specializzati**, molto simili alla propria applicazione, o **generici**, da contesti molto diversi? La slide usa l'analogia della scelta di una **guida** specializzata o generica.

<!-- SOURCE_VISUAL id="TAB10-V08" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="47" type="immagini" description="Volti con mascherine come esempio di dominio specifico per un face detector" reason="Esempio visivo della necessità di dati specifici" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.2 Non usare tutti i dati per il training**

_(slide pp. 49–53)_

Se tutto il dataset è usato **sia per costruire sia per validare** il modello, non si sa come si comporterà su **dati nuovi**. Due problemi:

| Problema | Definizione (slide) | Errori |
|---|---|---|
| **Underfitting** | il modello **non riesce ad apprendere la complessità** dei dati | **elevati** sia in training sia in validazione |
| **Overfitting** | il modello **apprende anche il rumore** o **impara a memoria** senza capacità di generalizzazione | **bassi** in training, **alti** in validazione |

**L'esperimento** (p. 50): si ripete l'intero apprendimento **aumentando il numero di neuroni**. L'errore sul **training** continua a scendere; l'errore di **validazione** scende e poi, oltre una certa complessità, **risale**: è il segnale che si sta **esagerando con la complessità**. Usare **due insiemi**, uno di training e uno **mai usato in training**, permette di identificare l'overfitting.

**Esempi 1D e 2D** (pp. 51–53): un **modello lineare** non basta a seguire l'andamento dei dati (underfitting); un **polinomio con troppi gradi di libertà** impara il rumore (overfitting); un polinomio con i **giusti gradi di libertà** segue l'andamento senza il rumore.

<!-- SOURCE_VISUAL id="TAB10-V09" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="50" type="grafico" description="Errore su train e test set al crescere del numero di neuroni con risalita dell'errore di validazione" reason="Curva classica dell'overfitting" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB10-V10" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="51" type="grafici" description="Underfitting e overfitting 1D: modello lineare, polinomio con troppi gradi di libertà, polinomio adeguato" reason="Esempi visivi dei tre casi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB10-V11" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="52" type="grafici" description="Underfitting e overfitting in 2D" reason="Superfici decisionali nei due casi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB10-V12" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="53" type="grafico" description="Overfitting 2D (bis)" reason="Secondo esempio visivo di overfitting" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.3 Che cosa succede se le partizioni sono sbagliate**

_(slide pp. 54–56)_

**Underfitting**: anche usando tutti i dati il modello ha **errore elevato**. Cause possibili:

- il modello **non è adeguato** ai dati;
- mancano **dati di apprendimento** che spieghino la complessità del fenomeno;
- mancano **dati in una parte del dominio** (la slide evidenzia una zona vuota: «mancano dati qui!»);
- mancano **variabili importanti**.

**Overfitting**: per evitare di continuare ad aggiustare i pesi sui dati di training si adotta una condizione di **early stopping**. Ma **se non si tengono da parte casi mai visti**, non ci si può accorgere di essere in overfitting.

> 📌 **Il partizionamento del dataset è necessario** per controllare le prestazioni, **evitare l'overfitting** e **stimare la generalizzazione**. Esistono più metodi, e **come si creano le partizioni influenzerà molto** le prestazioni: con partizioni sbagliate il modello avrà un comportamento **incompleto o polarizzato**.

<!-- SOURCE_VISUAL id="TAB10-V13" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="57" type="schema" description="Approccio generale di cross-validazione con errori e_i sul singolo gruppo" reason="Schema generale della cross-validation" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **11. La cross-validation**

_(slide pp. 57–73)_

#### **11.1 Il concetto**

_(slide pp. 57–58)_

> 📌 **Cross-validation.** Tecnica per valutare la **generalizzazione** di un modello su un set di dati **indipendente**: stima quanto bene il modello farà previsioni su **dati non visti** in addestramento.

In pratica si **generano molti modelli** con dati **leggermente diversi** e si osserva **quanto differisce l'errore** tra loro. La teoria dice che è la **migliore simulazione possibile** del mondo reale con i dati a disposizione.

#### **11.2 k-Fold Cross Validation**

_(slide pp. 59–63)_

1. Il dataset viene diviso **casualmente** in **$k$ partizioni di uguale dimensione**.
2. **Una** partizione è tenuta per **verificare** il modello, le **$k - 1$** rimanenti servono ad **allenarlo**.
3. Il processo si ripete **$k$ volte**, usando a turno ciascuna partizione per la validazione e **mai** in apprendimento nello stesso giro.

Si ottengono gli errori $\{e_1, e_2, \dots, e_k\}$, da cui:

$$\bar{e} = \frac{1}{k}\sum_{i=1}^{k} e_i \qquad s_e = \sqrt{\frac{1}{k-1}\sum_{i=1}^{k}(e_i - \bar{e})^2} \qquad e_{max} = \max_i e_i$$

**Esempio della slide**: la stima dell'errore risulta **media 3,3%**, **deviazione standard 1%**, cioè $3{,}3\% \pm 1\%$: è la **stima dello stimatore**.

**Quale $k$ scegliere** (p. 63): di solito **tra 5 e 10–12**. Attenzione: $k$ va regolato per evitare partizioni **troppo piccole** o non correttamente popolate di tutte le casistiche. Regola di buon senso: dataset **esteso** → **10-FCV**; dataset **piccolo** → **5-FCV**.

> 💡 **Il compromesso su $k$.** Con $k$ grande ogni modello si allena su quasi tutti i dati (stima meno pessimistica), ma le partizioni di test sono piccole e gli allenamenti molti. Con $k$ piccolo il costo scende, ma ogni modello vede meno dati.

<!-- SOURCE_VISUAL id="TAB10-V14" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="61" type="schema" description="k-FCV: k giri con partizione di validazione diversa e insieme di errori e1..ek" reason="Schema dei giri della k-FCV" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.3 Stratified k-FCV**

_(slide pp. 64–65)_

Il k-FCV normale può **sbilanciare la proporzione delle classi** nelle partizioni di validazione, con stime non ottimali. Nella **stratified k-FCV** non si assegna a caso: si mette in **ogni partizione lo stesso numero di campioni di ogni classe**, mantenendo la distribuzione.

L'esempio con $k = 5$ mantiene la proporzione tra le classi **F** e **M** in ogni partizione; la slide mostra anche partizioni che **non** vanno bene. Lo stesso vale per **età**, **genere**, **etnia**, **sfondo**, e così via.

<!-- SOURCE_VISUAL id="TAB10-V15" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="65" type="schema" description="Stratified k-FCV con k=5 che mantiene la proporzione F/M e un esempio di partizioni non corrette" reason="Confronto tra partizioni stratificate e non" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.4 5×2 Cross Validation**

_(slide pp. 66–68)_

1. Il dataset viene diviso **casualmente in 2 partizioni** A e B, e si applica una k-FCV con $k = 2$: si allena con A e si testa con B, poi si allena con B e si testa con A; si calcola l'**errore medio** delle due prove.
2. Si **ripete il punto 1 per 5 volte**, con divisioni casuali diverse.
3. Si **aggregano** gli errori di ogni giro, $\{e_1, \dots, e_5\}$.
4. Si calcolano **media e deviazione standard** degli errori.

La divisione può essere 50%–50% ma anche diversa.

<!-- SOURCE_VISUAL id="TAB10-V16" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="68" type="schema" description="5x2 CV: cinque ripetizioni di divisione casuale in due partizioni con errori e1..e5" reason="Schema della procedura 5x2" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.5 Quale modello va in deployment**

_(slide pp. 69–70)_

Qualunque tecnica si usi, si generano **più modelli**, ognuno con il proprio errore. Quale mandare in esercizio?

| Opzione | Descrizione |
|---|---|
| **A** | si **rialllena il modello su tutti i dati**, ma si **comunicano i risultati della cross-validation** |
| **B** | si sceglie il modello con **errore più basso** in test e validazione, oppure con **minore entropia nei pesi** |
| **C** | si tiene un **test set aggiuntivo** per una **valutazione finale** |

La slide p. 70 indica l'opzione C come **altro modo corretto** di procedere: tenere da parte un insieme mai usato né per allenare né per scegliere il modello.

> ⚠️ **Perché l'opzione B da sola è ottimistica.** Se si sceglie il modello che ha ottenuto l'errore più basso su un insieme di validazione, quell'insieme è stato **usato per decidere**: il suo errore non è più una stima indipendente. Serve un test finale separato.

<!-- SOURCE_VISUAL id="TAB10-V17" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="70" type="schema" description="Scelta del modello per il deployment con valutazione finale su test set separato" reason="Schema delle partizioni training, validazione e test" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.6 Leave One Out**

_(slide pp. 71–73)_

Nel caso estremo di k-FCV, **$k$ è uguale al numero di esempi** del dataset: a ogni giro **un solo campione** viene usato come test e **tutti gli altri** per l'apprendimento.

<!-- SOURCE_VISUAL id="TAB10-V18" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="73" type="schema" description="Leave One Out: un campione in test e tutti gli altri in training" reason="Schema del LOO" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **12. Leave One Person Out e LNPO**

_(slide pp. 74–78)_

**Il contesto** (p. 75): un sistema biometrico, per esempio una rete neurale, impara ad autenticare le persone da migliaia di esempi; è comunque un **classificatore** (uscita 1 genuino, 0 impostore) con probabilità di errore $p$.

**Cross-validation semplice: no good** (p. 76). Se le **stesse persone** compaiono sia in training sia in validazione, il modello può semplificare troppo: riconosce **dal colore dei capelli** invece che dalle **feature facciali**.

> 📌 **LOPO.** Una rete con molti livelli o neuroni può **imparare a memoria i soggetti** e non la caratteristica biometrica (colore della pelle, barba…); l'accuratezza può arrivare **fittiziamente a EER = 0**. **Soluzione**: tenere un **gruppo di persone** usato **solo in validazione** e **mai visto in allenamento**. Con **L(NP)O** e **MIX** diventa più difficile che il modello impari a riconoscere solo dal colore dei capelli (Lezione 5, §9).

> ⚠️ **LOO non è LOPO.** Il Leave One Out esclude **un campione**; il Leave One Person Out esclude **tutti i campioni di una persona**. In biometria solo il secondo garantisce che la persona di test non sia mai stata vista.

<!-- SOURCE_VISUAL id="TAB10-V19" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="78" type="schema" description="LOPO, L(NP)O e MIX con persone escluse dal training" reason="Schema delle partizioni per persona" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **13. Nuove tecniche: modelli multimodali, zero-shot e few-shot**

_(slide p. 79)_

Nelle lezioni successive si introdurranno **modelli linguistici e multimodali** applicati alla biometria avanzata:

- **modelli multimodali** (immagine, video, audio, testo);
- **modelli foundation** e **linguistici**;
- approcci **zero-shot** e **few-shot**.

> ⚠️ **Questi approcci rompono molte ipotesi viste finora.** Non esiste più un **dataset etichettato classico**, non c'è una **separazione chiara training/test**, e la **cross-validation tradizionale** non è direttamente applicabile per valutare le prestazioni, stimare l'errore e misurare la confidenza. Come farlo sarà visto nelle prossime lezioni.

La slide mostra un esempio: a un modello multimodale si chiede «vedi in questa immagine una potenziale situazione pericolosa? Se sì, quale?». Il modello risponde descrivendo **scontri e disordini** con forze dell'ordine e **fumo o fumogeni** che riducono la visibilità.

> 💡 **Perché la valutazione è difficile.** Un modello di questo tipo non restituisce uno score confrontabile con una soglia ma un testo libero; inoltre è stato allenato su dati enormi e sconosciuti, quindi non si può garantire che le immagini di test non siano già state viste. I concetti di FMR, FNMR e LOPO vanno reinterpretati.

<!-- SOURCE_VISUAL id="TAB10-V20" source="Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf" page="79" type="esempio" description="Immagine di scontri con fumo e risposta di un modello multimodale sulla situazione pericolosa" reason="Esempio di valutazione zero-shot" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **14. Sintesi delle slide e appendice**

_(slide pp. 80–82)_

Punti principali (p. 80): sistema biometrico come **classificatore che sbaglia con probabilità $p$**; distribuzione degli errori (**Bernoulli, binomiale**); **FTA, FTE**; **dispensa Best Practices**; **stima con sistemi che apprendono**; **overfitting**; **partizione** del dataset; **k-FCV**, **LOO**, **LOPO**, **LNPO**.

L'appendice (pp. 81–82) ripropone il codice DeepFace + Haar Cascade della Lezione 9, **non richiesto all'esame** (con lo stesso difetto di indicizzazione `predictions[i]` discusso nella Lezione 9, §11.5).

---

## **Parte D — Dispensa: *Best Practices in Testing and Reporting Performance of Biometric Devices***

### **15. Il documento e il suo scopo**

_(dispensa pp. 1–5)_

**NPL Report CMSC 14/02**, **versione 2.01**, **A. J. Mansfield** (National Physical Laboratory) e **J. L. Wayman** (San Jose State University), **agosto 2002**, prodotto per il **Biometrics Working Group** britannico. Materia d'esame: **sezioni 1–5, 7, 8**; **non** la sezione 6.

**Obiettivi** (sommario):

- fornire un **quadro** per sviluppare e descrivere completamente i **protocolli di test**;
- aiutare a evitare **bias sistematici** dovuti a raccolta o analisi dei dati errate;
- ottenere la **migliore stima possibile delle prestazioni sul campo** con il **minimo sforzo**;
- migliorare la comprensione dei **limiti di applicabilità** dei risultati e dei metodi.

È una **revisione** della *Issue 1* del febbraio 2000, informata dai commenti della comunità, dal **CESG/NPL Biometric Test Programme** (sei tecnologie e sette prodotti), da altri programmi di test e da progressi sulla relazione statistica tra **dimensione del test e intervalli di confidenza**; è compatibile con i documenti **NIST** *The Speaker Verification Test Protocol* e *An Introduction to Testing Biometric Systems*.

**Introduzione** (sezione 1):

- il documento riguarda solo i **test tecnici di prestazione** (*technical performance testing*): **tassi di errore** (falsi positivi e negativi, **failure-to-enrol**, **failure-to-acquire**) e **throughput** (utenti processati per unità di tempo, per velocità di calcolo e interazione uomo-macchina);
- **non** considera altri test forse più importanti: **affidabilità, disponibilità e manutenibilità**, **vulnerabilità**, **sicurezza**, **accettazione degli utenti**, **fattori umani**, **costi/benefici**, **conformità alle norme sulla privacy**;
- è necessario perché la letteratura degli ultimi decenni mostra **protocolli di test contrastanti e contraddittori**, anche dentro la stessa organizzazione;
- i test possono essere di tre tipi (**technology, scenario, operational**), ognuno con protocollo e risultati diversi; non sempre si potrà seguire completamente la best practice, ma le linee guida evidenziano le **insidie** e aiutano a spiegare le deviazioni.

---

### **16. Definizioni**

_(dispensa pp. 6–12)_

#### **16.1 Componenti di un sistema biometrico**

_(dispensa pp. 6–7)_

Il diagramma generale comprende: **data collection** (presentazione, sensore), **trasmissione** (compressione, canale, espansione), **signal processing** (segmentazione, estrazione delle feature, controllo qualità), **data storage** (template, immagini), **matching** (pattern matching → matching score), **decisione** (criteri di accettazione → accept/reject).

| Termine | Definizione (dispensa) |
|---|---|
| **Sample** | misura biometrica presentata dall'utente e acquisita come **immagine o segnale** (immagini di impronta, volto, iride) |
| **Features** | rappresentazione **matematica** dell'informazione estratta dal sample (coordinate delle minuzie, coefficienti PCA, iris-code) |
| **Template / Model** | misura di riferimento **memorizzata**, basata sulle feature dei sample di enrollment; più in generale un **modello** della possibile variabilità delle feature dell'utente |
| **Matching score** | misura della **similarità** tra feature del sample e template (o di quanto le feature si adattano al modello); la decisione match/non-match dipende dal superamento di una **soglia** |
| **Decision** | determinazione della probabile **validità della dichiarazione** di identità o non identità |
| **Transaction** | **tentativo** di un utente di validare una dichiarazione presentando **uno o più sample**, secondo la politica di decisione |
| **Presentation effects** | variabili che influenzano **come** le caratteristiche sono presentate al sensore (posa e illuminazione per il volto; rotazione e umidità per l'impronta) |
| **Channel effects** | modifiche al segnale dovute a **campionamento, rumore e risposta in frequenza** del sensore e del canale di trasmissione |

<!-- SOURCE_VISUAL id="TAB10-V21" source="Lezione_10b_Best Practices Biometrics_Dispensa.pdf" page="6" type="diagramma" description="Figure 1: diagramma generale di un sistema biometrico con data collection, trasmissione, signal processing, storage, matching e decisione" reason="Schema di riferimento della dispensa" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **16.2 Tipi di valutazione**

_(dispensa pp. 7–8)_

| Tipo | Obiettivo | Caratteristiche (dispensa) |
|---|---|---|
| **Technology** | **confrontare algoritmi** concorrenti della stessa tecnologia | database standardizzato raccolto con un sensore «universale»; regola dei **«tre orsi»** (*Three Bears*): database né troppo difficile né troppo facile, «giusto»; il test va fatto su dati **mai visti** dagli sviluppatori; elaborazione **offline**; risultati **ripetibili** |
| **Scenario** | prestazioni del **sistema completo** in un'applicazione **prototipo o simulata** | ogni sistema ha il proprio sensore e riceve dati leggermente diversi; stessa popolazione e stesso ambiente per tutti; combinazione di offline e online; ripetibile solo se lo scenario è ben controllato |
| **Operational** | prestazioni di un sistema completo in un **ambiente applicativo specifico** con una **popolazione specifica** | test offline talvolta impossibile; risultati **non ripetibili**; la **verità a terra** (chi stava davvero presentando un tratto in buona fede) è difficile da stabilire |

> 💡 **La regola dei tre orsi.** Nella fiaba, Riccioli d'oro trova la pappa, la sedia e il letto di un orso troppo caldi, duri o alti, quelli di un altro troppo freddi, morbidi o bassi, e quelli del piccolo orso «giusti». Un database troppo facile non distingue gli algoritmi (tutti a errore quasi zero), uno troppo difficile nemmeno (tutti sbagliano).

#### **16.3 Dichiarazioni di identità**

_(dispensa p. 8)_

| Termine | Definizione (dispensa) |
|---|---|
| **Verification** | l'utente fa una dichiarazione **positiva** di identità: confronto **uno a uno** con il template dell'identità dichiarata |
| **Identification** | l'utente **non dichiara** o fa una dichiarazione **negativa** implicita: ricerca **uno a molti** su tutto il database |
| **Positive claim** | l'utente afferma (esplicitamente o implicitamente) di **essere registrato** o noto al sistema (esempio: controllo accessi) |
| **Negative claim** | l'utente afferma di **non essere registrato** (esempio: iscrizione a servizi sociali aperti solo a chi non è già iscritto) |
| **Explicit claim** | c'è una dichiarazione esplicita: basta il confronto con **un solo template** |
| **Implicit claim** | la dichiarazione è implicita: il sample va confrontato con **molti template** |
| **Genuine claim** | dichiarazione positiva **veritiera**: confronto con un template realmente corrispondente |
| **Impostor claim** | dichiarazione positiva **falsa**: confronto con un template non corrispondente |

> 📌 **Verifica e identificazione non bastano.** La dispensa osserva che i due termini non coprono tutte le applicazioni: servono anche le dimensioni **positiva/negativa** ed **esplicita/implicita** (Lezione 1, §7).

#### **16.4 Misure di prestazione**

_(dispensa pp. 8–10)_

**Errori di decisione** (tradizionalmente usati per esprimere le prestazioni):

| Tasso | Definizione (dispensa) | Tipo di errore |
|---|---|---|
| **False Accept Rate (FAR)** | proporzione attesa di **transazioni** con dichiarazioni **false** (di identità in un sistema positivo, di non identità in uno negativo) **erroneamente confermate** | **tipo II** |
| **False Reject Rate (FRR)** | proporzione attesa di **transazioni** con dichiarazioni **vere** **erroneamente negate** | **tipo I** |

«Accettazione» e «rifiuto» si riferiscono **sempre alla dichiarazione dell'utente**. La dispensa nota che in letteratura le definizioni sono in **conflitto**: nei grandi sistemi di identificazione si chiama «falso rifiuto» ciò che nel controllo accessi si chiama «falsa accettazione». Le definizioni proposte risolvono il conflitto. Gli errori di decisione derivano da **errori di matching**, **errori di acquisizione** e, in alcuni sistemi, **errori di binning**, combinati in modo che dipende dal tipo di confronto (1:1 o 1:N), dal tipo di dichiarazione e dalla **politica di decisione** (per esempio tentativi multipli).

**Errori di matching** (singolo confronto sample-template):

| Tasso | Definizione (dispensa) |
|---|---|
| **False Match Rate (FMR)** | probabilità attesa che un sample venga dichiarato **erroneamente corrispondente** a un singolo template **«non-self»** scelto a caso («falso positivo») |
| **False Non-Match Rate (FNMR)** | probabilità attesa che un sample venga dichiarato **erroneamente non corrispondente** al template dello **stesso utente** («falso negativo») |

> ⚠️ **«Non-self» significa geneticamente diverso.** Confronti tra caratteristiche **geneticamente identiche** (occhio sinistro e destro della stessa persona, **gemelli identici**) producono distribuzioni di score diverse e **non vanno usati** per calcolare il FMR.

> 📌 **FMR/FNMR non sono sinonimi di FAR/FRR.** FMR e FNMR si calcolano sul **numero di confronti**; FAR e FRR sulle **transazioni**, si riferiscono all'accettazione o al rifiuto dell'ipotesi dichiarata e **includono la mancata acquisizione**. Esempi:
> - in un sistema **positivo** con **tre tentativi**, si ha un **falso rifiuto** con qualunque combinazione di mancate acquisizioni e falsi non-match nei tre tentativi, e una **falsa accettazione** se un'immagine viene acquisita e falsamente associata in uno qualsiasi dei tre;
> - in un sistema **negativo**, la dichiarazione di non essere registrato viene **falsamente rifiutata** se l'immagine acquisita corrisponde falsamente a uno o più template; a seconda della politica può essere **falsamente accettata** se l'immagine non viene acquisita o se viene falsamente non associata.

**Media sugli utenti** (punto 44): se ogni utente ha un template e fa lo stesso numero di tentativi, i tassi osservati sono le stime migliori; i tassi vanno **mediati sugli utenti**, non sui tentativi, altrimenti peserebbero di più gli **utenti frequenti** e chi richiede **molti tentativi**.

**Errori di acquisizione**:

| Tasso | Definizione (dispensa) |
|---|---|
| **Failure to Enrol (FTE)** | proporzione attesa della **popolazione** per cui il sistema **non riesce a generare template ripetibili**: chi non può presentare il tratto, chi non produce un'immagine di qualità sufficiente all'enrollment, chi non riesce a far corrispondere il proprio template nei tentativi di conferma; dipende dalla **politica di enrollment** |
| **Failure to Acquire (FTA)** | proporzione attesa di **transazioni** per cui il sistema **non riesce a catturare o localizzare** un'immagine o un segnale di qualità sufficiente; dipende dalle **soglie di qualità** |

**Prestazioni del binning**: **penetration rate** (proporzione attesa dei template da cercare su tutti gli ingressi, scandendo l'intera partizione anche se il match è trovato prima; valori bassi sono desiderabili) e **binning error rate** (template di enrollment e sample successivo dello stesso utente in **partizioni diverse**). Più partizioni abbassano il penetration rate ma aumentano il binning error (Lezione 2, §14–15).

#### **16.5 Tentativi genuini e impostori, online e offline**

_(dispensa pp. 10–11)_

- **Genuine attempt**: un singolo tentativo **in buona fede** di un utente di corrispondere al **proprio** template.
- **Impostor attempt**: un singolo tentativo **«zero-effort»** di una persona **sconosciuta al sistema** di corrispondere a un template memorizzato.
- **Sconosciuto al sistema**: una persona è nota se è registrata **e** la sua registrazione **influenza i template degli altri**. Esempi di template **dipendenti**: sistemi **eigenface** che usano tutte le immagini registrate per costruire le immagini base, sistemi di speaker recognition basati su **cohort**.
- **Zero-effort**: l'impostore presenta il **proprio** tratto come se cercasse di verificarsi sul proprio template (nella firma dinamica, firma con la **propria** firma). Dove gli aspetti del tratto sono facili da imitare può servire una misura con **impostori attivi**, fuori dall'ambito del documento.
- **Online**: enrollment o calcolo degli score **al momento** della presentazione (il sample si può scartare subito, ma si raccomanda comunque di conservare le immagini).
- **Offline**: basato su immagini o segnali **raccolti prima**; permette maggiore controllo su quali tentativi e template usare. I test **technology** sono sempre offline.

#### **16.6 Curve ROC e DET, termini statistici**

_(dispensa pp. 11–12)_

- **ROC**: traccia in funzione della soglia il tasso di **falsi positivi** (impostori accettati, **FMR**) sull'**asse x** contro i **veri positivi** (genuini accettati, **$1 - FNMR$**) sull'**asse y**; è indipendente dalla soglia e permette di confrontare sistemi.
- **DET**: ROC modificata, **preferita in biometria**; mette **tassi di errore su entrambi gli assi** (FNMR contro FMR), trattando i due errori in modo uniforme, e si può tracciare su **assi logaritmici**, che separano meglio i sistemi con buone prestazioni. Si può usare sia per gli errori di **matching** (FNMR contro FMR) sia per quelli di **decisione** (FRR contro FAR).

> ⚠️ **Gli assi della dispensa.** Come avvertito nella Lezione 9 (§5.3), la convenzione della dispensa (FMR in ascissa, FNMR in ordinata, scala log-log) può differire da quella di alcune slide. Prima di leggere una curva, controllare sempre gli assi.

| Termine statistico | Definizione (dispensa) |
|---|---|
| **Varianza** | misura della dispersione: $V(X) = E[(X - \mu)^2]$; indica quanto una stima è probabilmente vicina al valore vero |
| **Intervallo di confidenza** (al 95%) | stime inferiore $L$ e superiore $U$ tali che $P(x \in [L, U]) = 95\%$; più piccolo il test, più largo l'intervallo |
| **Errore di tipo I** | rifiutare un'ipotesi vera |
| **Errore di tipo II** | accettare un'ipotesi falsa |

<!-- SOURCE_VISUAL id="TAB10-V22" source="Lezione_10b_Best Practices Biometrics_Dispensa.pdf" page="12" type="grafici" description="Figure 2 (ROC: FMR in ascissa, 1-FNMR in ordinata) e Figure 3 (DET log-log: FMR e FNMR) sugli stessi dati" reason="Confronto visivo tra ROC e DET della dispensa" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **17. Pianificare la valutazione**

_(dispensa pp. 13–17)_

#### **17.1 Le prime decisioni e le informazioni sul sistema**

_(dispensa p. 13)_

Prima si decide **che cosa esattamente** si vuole determinare e **quale tipo** di valutazione è appropriato. Poi si raccolgono informazioni sul sistema:

| Domanda (dispensa) | Perché conta |
|---|---|
| il sistema **registra** le transazioni? | altrimenti vanno annotate a mano |
| **salva** immagini o feature di ogni transazione? | necessario per generare gli score **offline** |
| restituisce **score** o solo **decisioni**? | nel secondo caso bisogna raccogliere dati a **più impostazioni di sicurezza** per costruire la DET |
| genera **template indipendenti**? | cambia la procedura per gli impostori |
| è disponibile l'**SDK** del produttore? | serve per generare template, estrarre feature e calcolare score offline, che devono coincidere con quelli del sistema reale |
| quali **soglie** di qualità e decisione sono raccomandate? | influenzano la qualità dei sample |
| si conoscono **tassi di errore attesi**? | aiutano a dimensionare il test |
| quali **fattori** influenzano le prestazioni? | vanno controllati (Appendice A) |

Nei test **scenario** e **operational** tutte le regolazioni (incluse le soglie) vanno fatte **prima** della raccolta, in accordo con il produttore: per esempio un controllo qualità più severo riduce falsi match e falsi non-match ma aumenta la **FTA**. La soglia di decisione va impostata con cura se il risultato è mostrato all'utente, perché il **feedback** influenza il comportamento.

#### **17.2 Controllare i fattori che influenzano le prestazioni**

_(dispensa pp. 13–14)_

Ogni fattore viene classificato in una di quattro categorie:

1. inserito nella **struttura dell'esperimento** come **variabile indipendente**, per osservarne l'effetto;
2. **controllato** come parte delle condizioni sperimentali (costante);
3. **«randomizzato fuori»** dall'esperimento;
4. giudicato **trascurabile** e ignorato (senza questa categoria l'esperimento diventerebbe troppo complesso).

C'è un possibile conflitto tra **validità interna** (le differenze sono dovute solo alle variabili indipendenti) e **validità esterna** (i risultati rappresentano davvero l'applicazione reale). **Technology**: applicazione e popolazione generiche con la regola dei tre orsi. **Scenario**: si modella un'applicazione reale con utenti rappresentativi. **Operational**: ambiente e popolazione sono dati, con poco controllo.

#### **17.3 Selezione dei volontari**

_(dispensa pp. 14–15)_

- I sample devono provenire da una popolazione di test (*crew*). **Non è best practice** generare **immagini artificiali** o modificare immagini reali (migliorerebbe la validità interna ma ridurrebbe quella esterna e favorirebbe i sistemi che modellano le immagini come il generatore).
- **Scenario**: la crew deve essere **demograficamente simile** all'applicazione, idealmente estratta a caso tra i potenziali utenti; **operational**: nessun controllo sugli utenti.
- Enrollment e test avvengono in **sessioni separate** (giorni, settimane, mesi, anni): ci si deve aspettare **abbandoni**.
- Reclutare **volontari** può introdurre **bias**: persone con caratteristiche insolite, lavoratori, persone con disabilità possono essere sottorappresentate, e chi è più contrario alla tecnologia difficilmente si offre. Può servire una selezione non uniforme per rendere la crew rappresentativa.
- I volontari devono essere **pienamente informati** (procedura, uso e diffusione dei dati, numero e durata delle sessioni); le loro **identità non vanno mai rilasciate**; firmano un **modulo di consenso** (Appendice C).
- Nei test technology e scenario i volontari devono essere **motivati**: se si annoiano possono sperimentare o essere meno attenti.

#### **17.4 Dimensione del test**

_(dispensa pp. 15–17)_

La dimensione (volontari, tentativi, dita/mani/occhi per persona) determina l'**accuratezza** delle stime: più grande il test, più accurati i risultati. Regole come la **regola dei 3** e **dei 30** danno **limiti inferiori**, ma sono **troppo ottimistiche**, perché assumono un'**unica fonte di variabilità**, e in biometria non è così.

> 📌 **Dieci coppie enrollment-test da cento persone non equivalgono a una coppia da mille persone.** La varianza dovuta alle **differenze tra volontari** scala come $1/(\text{numero di volontari})$, non come $1/(\text{numero di tentativi})$.

- **Regola dei 3**: $p \approx 3/N$ al 95%, $p \approx 2/N$ al 90%; 300 campioni indipendenti senza errori permettono di dire, al 95%, che l'errore è dell'1% o meno. L'ipotesi è che i tentativi siano **indipendenti e identicamente distribuiti**: si ottiene se ogni tentativo genuino usa un volontario diverso e nessuna coppia di tentativi impostori coinvolge lo stesso volontario. Tuttavia i **confronti incrociati** generano molti più tentativi impostori e, secondo la letteratura, **riducono l'incertezza** nonostante le dipendenze.
- **Regola dei 30**: per essere al 90% sicuri che l'errore vero sia entro ±30% di quello osservato servono almeno **30 errori**; 30 falsi non-match su 3000 tentativi genuini indipendenti danno un intervallo tra **0,7% e 1,3%**. Generalizzazioni: entro **±10%** servono almeno **260 errori**, entro **±50%** almeno **11 errori**. Con obiettivi di **FNMR 1%** e **FMR 0,1%** servono **3000 tentativi genuini** e **30.000 impostori** indipendenti; a rigore 3000 persone registrate e 30.000 impostori, oppure si riusano meno volontari accettando una **perdita di significatività**.

**Più transazioni per persona** (punto 88): è molto più facile far tornare i volontari registrati che trovarne di nuovi. Esempio: **200 volontari** che si registrano e fanno **tre transazioni genuine in due ulteriori occasioni** producono **1200 tentativi genuini** (non del tutto indipendenti); con lo stesso sforzo **240 volontari** con una sola transazione ne producono **240** indipendenti. Le transazioni multiple aumentano di **6 volte** i genuini e di **4 volte** gli impostori (confronti incrociati). Attenzione: il comportamento cambia con i tentativi successivi (familiarità, feedback), quindi il FNMR osservato **dipende dallo schema dei tentativi**.

**Raccomandazioni** (punto 90):

1. la crew deve essere **la più grande possibile** (il limite è il costo di reclutamento e gestione): il **numero di persone** conta più del numero totale di tentativi;
2. poi si raccolgono abbastanza sample per volontario da superare quanto richiesto dalla regola dei 3 o dei 30, possibilmente in **giorni diversi** o da **dita/occhi/mani diversi** (se rappresentativi dell'uso normale: il mignolo non lo è);
3. infine, analizzati i dati, si stima l'**incertezza** per verificare se il test era abbastanza grande.

Vale la **legge dei rendimenti decrescenti**: oltre un certo punto gli errori dovuti al **bias** di ambiente o di selezione superano quelli dovuti alla dimensione.

#### **17.5 Test multipli**

_(dispensa p. 17)_

- **Technology**: per tratti con **standard d'immagine** (impronta, volto, voce) si può raccogliere un unico corpus per testare offline algoritmi di più produttori; ma acquisizione ed elaborazione **non sono del tutto indipendenti** (il controllo qualità può chiedere di riacquisire; le interfacce utente del produttore influenzano la qualità), quindi il test può essere **sbilanciato** a favore di alcuni sistemi.
- **Scenario**: la stessa crew può usare più dispositivi per sessione, **randomizzando l'ordine** per bilanciare l'abitudine; attenzione ai **conflitti** tra comportamenti ideali (un dispositivo vuole l'immagine in movimento, un altro ferma).
- **Operational**: di solito non permette test multipli sugli stessi dati.

---

### **18. Raccolta dei dati**

_(dispensa pp. 17–24)_

#### **18.1 Evitare gli errori di raccolta**

_(dispensa pp. 17–18)_

Le immagini raccolte sono il **corpus**; le informazioni su immagini e volontari sono il **database**. Entrambi possono essere corrotti da errori umani, e gli errori di raccolta **possono facilmente superare quelli del dispositivo biometrico**.

| Errori di **corpus** (immagine acquisita male) | Errori di **database** (immagine etichettata male) |
|---|---|
| volontari che usano il sistema in modo scorretto (per esempio lo scanner capovolto) | PIN sbagliato assegnato al volontario |
| immagine vuota o corrotta se l'utente inserisce il PIN e se ne va prima dell'acquisizione | errori di battitura del PIN |
| | parte del corpo sbagliata (medio al posto dell'indice) |

Servono software che **minimizzi l'inserimento da tastiera**, **più operatori** che ricontrollino i dati, **ridondanza** incorporata, supervisori formati, **criteri oggettivi** stabiliti in anticipo, documentazione di ogni circostanza insolita.

#### **18.2 Dati e dettagli da raccogliere**

_(dispensa pp. 18–19)_

La situazione ideale è che il sistema **salvi i sample** e **registri automaticamente** enrollment e transazioni (identità dichiarata, score di matching e qualità). Vantaggi:

1. template e score generabili **offline** con l'SDK, con **confronto incrociato completo** e molti più score impostori;
2. **riuso** delle immagini per valutare miglioramenti o altri algoritmi;
3. controllo degli errori di corpus e database ispezionando immagini e log;
4. meno dati trascritti a mano.

Se il sistema restituisce **solo decisioni**, per costruire la DET si raccolgono transazioni a **più impostazioni di sicurezza** (per esempio bassa, media, alta), che parametrizzano la curva al posto della soglia. Nei test online **ogni volontario** deve fare transazioni a **ogni impostazione**: non si può assumere che un utente accettato a un'impostazione severa sia sempre accettato a una più permissiva, né il contrario.

#### **18.3 Enrollment**

_(dispensa pp. 19–20)_

- Ogni volontario si registra **una sola volta** (anche se l'enrollment genera più template o richiede più tentativi); evitare registrazioni multiple accidentali.
- Si possono fare **prove pratiche** all'enrollment per verificare la qualità, ma i loro score **non vanno registrati come genuini**; possono servire per generare impostori offline.
- **Technology**: tutti gli enrollment nelle **stesse condizioni**; molte raccolte sono state rovinate da cambi di protocollo o di apparecchiature (esempio celebre: il **«Great Divide»** del corpus vocale KING, dove un'apparecchiatura smontata e rimontata cambiò la risposta in frequenza a metà raccolta).
- **Scenario**: l'enrollment deve **modellare** quello dell'applicazione; attenzione particolare al **«rumore»**, acustico per la voce, **luminoso** per i sistemi ottici (luce diretta sul sensore, riflessi); i risultati in un ambiente **non si trasferiscono** ad altri.
- Il **controllo qualità** può respingere tentativi di enrollment: seguire il produttore, permettere **più tentativi** con un massimo prefissato, registrare punteggi di qualità e immagini; **riportare la percentuale di FTE**.
- L'intervento dello sperimentatore per presentazioni inappropriate (dito sbagliato, frase sbagliata) va registrato. Nei test technology e scenario i dati di enrollment **non vanno rimossi solo perché anomali** (*outlier*); nei test operational la rimozione può basarsi sugli outlier, documentandone l'effetto.
- Procedure e criteri di intervento vanno fissati **in anticipo**, con supervisori formati, perché l'esperienza acquisita durante il test può cambiare gli enrollment successivi.

#### **18.4 Transazioni genuine**

_(dispensa pp. 20–22)_

- **Technology**: dati né troppo difficili né troppo facili; l'intervallo tra enrollment e test determina la difficoltà per l'**invecchiamento del template** (*template ageing*): aumento degli errori dovuto a cambiamenti nel tempo del tratto, della presentazione e del sensore.
- **Scenario**: ambiente (rumore compreso) il più vicino possibile all'applicazione e **costante**; separazione temporale adeguata all'invecchiamento. Se non è nota, la regola pratica è il **tempo di guarigione** della parte del corpo: **2–3 settimane** per le impronte, **pochi giorni** per l'occhio, **uno o due mesi** per il volto (considerando un taglio di capelli come una «ferita»).
- **Operational**: può servire **bilanciare la frequenza** d'uso, perché i risultati non siano dominati da pochi utenti frequenti; i dati **non vanno rimossi** senza una validazione esterna dell'abuso; con i timestamp si può studiare l'invecchiamento.
- **Tutti i dati di test vanno aggiunti al corpus**, che corrispondano o no al template: alcuni software registrano una misura solo se corrisponde, e questo **sottostima gravemente il FNMR**. I dati si escludono solo per cause **prefissate e indipendenti dagli score**.
- Effetti di presentazione e canale devono essere **uniformi** o **casuali** tra i volontari, senza correlazione tra sessione di enrollment e di test.
- **Abitudine** dell'utente (score in miglioramento) e **invecchiamento** (score in peggioramento) si compensano: senza scale temporali diverse e note non si possono separare.
- Le **soglie** usate durante la raccolta influenzano il comportamento (soglie severe → presentazioni più accurate): il database non è indipendente dalla soglia quanto si assume.
- **Tutti i tentativi**, comprese le **mancate acquisizioni**, vanno registrati, con qualità e score quando disponibili.

#### **18.5 Transazioni impostori**

_(dispensa pp. 22–24)_

| Aspetto | Indicazione (dispensa) |
|---|---|
| **Online** | i volontari presentano sample contro enrollment altrui: tentativi **zero-effort** contro template **non-self scelti a caso**, con selezione indipendente per ogni utente |
| **Offline** | si confrontano sample già raccolti con i template; permette il **confronto incrociato completo** (ogni sample contro ogni template non-self) |
| **Sistemi con binning** | confrontare solo con template **dello stesso bin**: nelle impronte su larga scala solo tra impronte dello stesso tipo **arch/loop/whorl**; nella voce, eventualmente, solo tra parlatori dello **stesso genere**. I confronti nello stesso bin possono avere FMR più alti di quelli casuali |
| **Consapevolezza** | il volontario idealmente **non deve sapere** se il confronto è genuino o impostore, per evitare cambiamenti anche inconsci |
| **Condizioni** | stesse condizioni dei genuini; i **«background database»** raccolti in ambienti o popolazioni diversi **non sono best practice** |
| **Template dipendenti** | gli impostori **non devono essere registrati**; offline si usa il **jack-knife** (si registra tutta la crew tranne un volontario, usato come impostore, e si ripete per tutti) o una partizione casuale tra impostori e registrati |
| **Numero di confronti** | con $T$ template e $N$ feature della stessa crew si fanno $N(T - 1)$ confronti non-self: **non indipendenti**, ma **non distorti** e più efficienti di confronti scelti a caso |
| **Sequenze di sample** | se il sistema sceglie il sample migliore rispetto al template, il sample salvato per un genuino può non essere adatto come impostore: in quel caso salvare l'intera sequenza o generare gli impostori online |
| **Confronti tra template** | utili quando i sample non sono salvati; ma se l'enrollment usa **più immagini**, la stima degli impostori risulta **distorta**, e non esistono correzioni |
| **Confronti intra-individuo** | dita, occhi o mani **diversi della stessa persona** non sono equivalenti a persone diverse e **non vanno inclusi** tra gli impostori |

---

### **19. Analisi dei dati**

_(dispensa pp. 24–26)_

**FTE e FTA** (sezione 5.1): la FTE si stima come **proporzione di volontari** non registrabili secondo la politica prefissata; la FTA come **proporzione di transazioni** registrate (genuine e impostori online) non completate per mancata presentazione, estrazione o controllo qualità. Anche nei test technology possono esserci fallimenti (immagini troppo scadenti per estrarre feature).

**Curve DET** (sezione 5.2):

- si costruiscono dagli score tra **singoli sample** e **singoli template**; dipendono dai dettagli del test e dal **controllo qualità** (più severo → FTA più alta, FMR e FNMR più bassi);
- gli score vanno ordinati; gli **outlier** vanno investigati (possibili errori di etichetta), e la rimozione di score va **documentata**;
- gli **istogrammi** sono istruttivi ma **non si usano** per costruire la DET: la curva si ottiene accumulando direttamente gli score ordinati. **In nessun caso si devono sostituire gli istogrammi con modelli** come stima delle distribuzioni;
- la DET si traccia su scala **log-log**, con **FMR in ascissa** e **FNMR in ordinata**.

> ⚠️ **Modelli teorici e best practice.** Nella Lezione 9 (§4.1) il docente usa il **modello teorico** degli impostori dell'IrisCode per calcolare probabilità di $10^{-30}$. La dispensa vieta di sostituire gli istogrammi con modelli nel **riportare i risultati di un test**. I due punti non si contraddicono: il modello serve a **ragionare ed estrapolare** oltre ciò che è misurabile, mentre la **stima delle prestazioni misurate** deve basarsi sui dati empirici.

> 💡 **La nota sulla direzione degli score.** La dispensa assume score che **crescono** al diminuire della similarità (distanze): per questo definisce il FMR come proporzione di score impostori **inferiori o uguali** alla soglia. Con score di similarità, come nelle slide, la disuguaglianza si inverte.

**Errori di decisione dagli errori di base**: FAR e FRR dipendono da FMR, FNMR, FTA e, se c'è, da binning error rate (BER) e penetration rate (PR), secondo la politica di decisione. Se l'accettazione dipende da **un singolo match riuscito**:

$$FAR = PR \times FMR \times (1 - FTA)$$

$$FRR = FTA + (1 - FTA) \times BER + (1 - FTA) \times (1 - BER) \times FNMR$$

> 💡 **Come leggerle.** Un **impostore** viene accettato solo se il suo campione viene **acquisito** ($1 - FTA$), cercato nel bin del template attaccato (fattore $PR$) e **falsamente associato** ($FMR$). Un **genuino** viene rifiutato se il campione **non viene acquisito** ($FTA$), oppure se viene acquisito ma finisce nel **bin sbagliato** ($BER$), oppure se viene acquisito, finisce nel bin giusto e subisce un **falso non-match**: i tre casi sono mutuamente esclusivi e si sommano.

**Binning error contro penetration rate** (sezione 5.3): nei sistemi di identificazione **negativa** vanno valutati anche gli algoritmi di binning. Con il corpus offline si contano le coppie template-sample corrispondenti finite in **bin diversi** (binning error) e il numero medio di confronti richiesti diviso per la dimensione del database (penetration rate). Se l'algoritmo ha parametri regolabili si traccia una **curva** o una superficie dei compromessi.

---

### **20. Incertezza delle stime (sezione 6, non richiesta all'esame)**

_(dispensa pp. 26–29)_

> ⚠️ **Questa sezione non è materia d'esame** secondo l'indicazione del docente (slide p. 24). Se ne riporta la sintesi per completezza.

- Le stime sono affette da **errori casuali** (variazione naturale di volontari e sample, che si riduce aumentando il test) ed **errori sistematici** (bias nelle procedure, per esempio categorie sottorappresentate), che si possono verificare confrontando sottogruppi o ripetendo il test in condizioni diverse.
- Le formule della **varianza** assumono: crew rappresentativa, tentativi di soggetti diversi indipendenti, tentativi indipendenti dalla soglia, **tassi di errore variabili nella popolazione** (capre, lupi, agnelli), numero di errori non troppo piccolo (altrimenti vale la regola dei 3).
- **Un tentativo per volontario**: $\hat{p} = \frac{\sum_i a_i}{n}$, $\hat{V}(\hat{p}) = \frac{\hat{p}(1 - \hat{p})}{n - 1}$. Con **più tentativi** per volontario **non è valido** sostituire $n$ con il numero di tentativi $nm$: servono formule che tengano conto della variabilità tra volontari.
- La varianza del FMR con **confronto incrociato completo** ha una formula dedicata (Bickel).
- **Intervalli di confidenza**: per il teorema del limite centrale, $\hat{p} \pm z(1 - \alpha/2)\sqrt{\hat{V}(\hat{p})}$, con $z(0{,}975) = 1{,}96$ al 95%. Con tassi vicini allo 0% l'intervallo può diventare **negativo**, segno di non normalità: si usa allora il **bootstrap** (campionamento con reinserimento di volontari, template e tentativi, replicando la struttura del test), con almeno **1000** campioni bootstrap per limiti al 95% e **5000** al 99%.

---

### **21. Riportare i risultati**

_(dispensa p. 29)_

> 📌 **Che cosa riportare** (sezione 7). DET, FTE, FTA, penetration rate e binning error dipendono da tipo di test, applicazione e popolazione. Per interpretarli correttamente vanno forniti:
> - **a.** dettagli del **sistema** testato, non solo della componente biometrica (per esempio l'interfaccia utente);
> - **b.** **tipo di valutazione**: database usati (technology), scenario di test (scenario), applicazione operativa (operational);
> - **c.** **dimensione** della valutazione: numero di volontari, dita/mani/occhi registrati per volontario, visite per volontario, transazioni per visita;
> - **d.** **demografia** della crew;
> - **e.** dettagli dell'**ambiente** di test;
> - **f.** **separazione temporale** tra enrollment e transazioni di test;
> - **g.** **soglie** di qualità e di decisione usate durante la raccolta;
> - **h.** quali **fattori** che influenzano le prestazioni sono stati controllati e **come** (Appendice A);
> - **i.** dettagli della **procedura**, per esempio le politiche per stabilire i fallimenti di enrollment;
> - **j.** **casi anomali** e dati **esclusi** dall'analisi;
> - **k.** **incertezze** stimate, se calcolate;
> - **l.** **deviazioni** da queste best practice, spiegate: talvolta si compromette un aspetto per ottenerne un altro (randomizzare l'ordine delle dita può confondere gli utenti e aumentare gli errori di etichetta).

Per **confrontare sistemi diversi** è più utile la **DET degli errori di decisione** (FRR contro FAR), che mostra l'effetto combinato di errori di matching, acquisizione e binning, rispetto ai grafici dei tassi fondamentali.

---

### **22. Conclusioni e riferimenti**

_(dispensa pp. 29–31)_

**Conclusioni** (sezione 8): le raccomandazioni sono **molto generali** e non sempre applicabili completamente; spesso servono **compromessi** (per esempio controllare le condizioni per la validità interna può contrastare con un ambiente realmente rappresentativo per la validità esterna). Lo sperimentatore sceglie il miglior compromesso per gli obiettivi, ma deve **riportare** ciò che ha fatto per permettere un'interpretazione corretta. Il documento resta aperto a commenti e critiche.

Seguono i **ringraziamenti** (CESG, lavori originali di Alvin Martin e George Doddington del NIST, UK Biometrics Working Group, U.S. Biometrics Consortium) e **34 riferimenti bibliografici** (tra cui i test FRVT 2000, FVC2000, il lavoro di Doddington sullo zoo e quelli sulla regola dei 3 e sul bootstrap).

---

### **23. Appendici**

_(dispensa pp. 32–36)_

#### **23.1 Appendice A — Fattori che influenzano le prestazioni**

_(dispensa pp. 32–35)_

Per ogni fattore si decide durante la pianificazione: quali **controlli** servono, quali **ragioni** rendono inutile controllarlo, quali **informazioni** registrare. In genere un fattore problematico **riduce la qualità** del sample, aumentando FTE, FTA o FNMR; in alcuni casi immagini rumorose producono **match spuri**, aumentando il FMR.

| Categoria | Esempi di fattori (dispensa) |
|---|---|
| **Demografia** | **età** (bambini, che cambiano rapidamente, e anziani, che guariscono più lentamente, hanno più falsi non-match e mancate acquisizioni); **origine etnica**, **genere**, **occupazione** (un sistema tarato su una popolazione può funzionare peggio su un'altra) |
| **Applicazione** | **tempo trascorso dall'enrollment** (invecchiamento del template); **ora del giorno**; **familiarità** dell'utente; **motivazione** |
| **Fisiologia** | barba e baffi; calvizie; **disabilità e malattie** (amputazioni, artrite, cecità, lividi, raffreddore e laringite, stampelle, gonfiori, sedia a rotelle, cambiamenti delle condizioni mediche); ciglia lunghe; unghie; **condizione delle impronte** (profondità e spaziatura delle creste, pelle secca, screpolata o umida); **altezza**; **intensità del colore dell'iride**; **tono della pelle** |
| **Comportamento** | dialetto, accento e lingua madre; espressione, intonazione e volume; espressioni facciali; alfabeto (per la firma); frasi pronunciate male; **movimento**; **posa e posizionamento** (frontale, profilo, inclinazione, rotazioni, distanza, troppo in alto o in basso); attività precedenti (fiato corto, sudore, pelle raggrinzita dopo il nuoto); **stress, umore, distrazioni** |
| **Aspetto** | cerotti e bende; **abbigliamento** (cappelli, orecchini, sciarpe, maniche, tacchi, pantaloni e scarpe per la camminata); **lenti a contatto colorate**; **cosmetici**; **occhiali e occhiali da sole**; unghie finte; acconciatura e colore dei capelli; anelli; **tatuaggi** |
| **Ambiente** | **sfondo** (colore, confusione, presenza di altri volti o ombre); **rumori e altre voci**; **illuminazione** (livello, direzione, riflessi); **meteo** (temperatura e umidità su secchezza delle dita, visibilità delle vene e immagini termiche; pioggia e neve sui capelli) |
| **Sensore e hardware** | **sporco, aloni, impronte residue** su lente o piano; **fuoco**; **qualità** del sensore (microfono, camera); **variazioni** tra esemplari e versioni di sensori, **usura**, **sostituzione**; **canale di trasmissione** (rumore, variabilità, carico della rete) |
| **Interfaccia utente** | **feedback** (l'utente vede la propria impronta e corregge la presentazione?); **istruzioni**; **supervisione** (differenze tra supervisori) |

**Esempi di reporting** (punti 196–201):

| Fattore | Osservazione | Controllo | Registrazione |
|---|---|---|---|
| posizione del dito | le guide dello scanner posizionano le dita entro le tolleranze | nessuno | — |
| illuminazione | le variazioni della luce diurna causano problemi | stanza senza luce naturale e con illuminazione costante | — |
| illuminazione | luce parassita produce riflessi sull'iride | unità modificata per schermare il sensore | — |
| occhiali | quasi impossibile registrare chi porta occhiali sul sistema X | agli utenti con occhiali si chiede di toglierli | numero di persone con occhiali, da includere nella FTE |
| sporco sul piano | l'accumulo di sebo degrada le prestazioni | pulizia regolare, con calendario dichiarato | quando il sistema viene pulito |
| meteo | dita sudate causano problemi | nessuno, condizioni considerate tipiche | temperatura e umidità durante il test |

#### **23.2 Appendice B — Varianza in funzione della dimensione del test**

_(dispensa pp. 35–36)_

Al crescere del test la varianza delle stime diminuisce, ma il fattore di scala **dipende dalla fonte di variabilità**.

- La varianza del **FNMR osservato** ha componenti che scalano come $1/(\text{numero di volontari})$ (variabilità tra persone) e $1/(\text{numero di tentativi genuini})$ (variabilità residua tra tentativi). Con $n$ volontari e $m$ tentativi indipendenti ciascuno, se $\rho$ e $\sigma^2$ sono media e varianza del FNMR tra le persone:

$$V(FNMR_{OBS}) = \frac{\sigma^2}{n} + \frac{\rho(1 - \rho) - \sigma^2}{mn}$$

- La varianza del **FMR osservato** ha componenti per impostori, template attaccati, coppie impostore-template, sample genuini e tentativi impostori.
- **Caso più ottimistico** (nessuna variabilità tra persone): $V(FNMR_{OBS}) = \rho(1-\rho)/(mn)$ e $V(FMR_{OBS}) = \alpha(1-\alpha)/(mnt)$.
- **Caso più pessimistico** (tutta la variabilità tra persone): $V(FNMR_{OBS}) = \rho(1-\rho)/n$ e $V(FMR_{OBS}) = \alpha(1-\alpha)/\min(n, t)$.

> 📌 **Il legame con lo zoo di Doddington.** Le **capre** hanno un FNMR personale molto più alto della media, gli **agnelli** subiscono una quota sproporzionata di falsi match, i **lupi** li producono. La loro esistenza implica che le componenti di varianza **per volontario** e **per template** non sono nulle: aumentare i tentativi per persona non basta, servono **più persone**.

> 💡 **Esempio numerico (nota didattica).** Con $\rho = 1\%$, 100 volontari e 10 tentativi ciascuno, la deviazione standard del FNMR osservato vale $\sqrt{0{,}0099/1000} \approx 0{,}31\%$ nel caso ottimistico e $\sqrt{0{,}0099/100} \approx 1\%$ nel caso pessimistico: la stessa misura «1%» può essere affidabile o quasi inutile, a seconda di quanto gli utenti differiscono tra loro.

#### **23.3 Appendice C — Modulo di consenso**

_(dispensa p. 36)_

Esempio di **modulo di consenso** per un test di prestazione: nome, contatti, identificativi usati nel corpus; consenso alla raccolta e alla conservazione elettronica di immagini o registrazioni del tratto e delle risposte ai questionari; uso dei dati da parte dell'organizzazione di test e di altre aziende indicate per valutare le prestazioni; garanzia che **nome e identità** non saranno memorizzati o mostrati in alcun database rilasciato o report; firma. Le note precisano che può essere utile raccogliere altre informazioni (età, occupazione), che per i sistemi di firma la clausola sul nome va adattata e che, con due tipi di tratto nello stesso corpus, **non deve essere possibile associare** per esempio un volto a una firma.

---

### **24. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Bernoulli** / **Binomiale** | una prova (autenticazione) / numero di errori su $n$ prove (identificazione, molti utenti) |
| **Regola dei 3** / **Regola dei 30** | limite superiore dell'errore con zero errori osservati / numero di errori per una stima entro ±30% |
| **$FMR_N$** / **$FNMR_N$** | cresce circa come $N \cdot FMR$ / resta uguale a FNMR |
| **FAR/FRR** / **FMR/FNMR** | su transazioni, includono FTA e binning / su singoli confronti |
| **FTE** / **FTA** | proporzione di popolazione non registrabile / proporzione di transazioni non acquisite |
| **Technology** / **Scenario** / **Operational** | algoritmi su DB fisso, ripetibile / sistema in ambiente simulato / sistema reale, non ripetibile |
| **Underfitting** / **Overfitting** | errori alti ovunque / errore basso in training e alto in validazione |
| **k-FCV** / **Stratified** / **5×2** / **LOO** / **LOPO** | $k$ partizioni casuali / proporzioni di classe mantenute / 5 ripetizioni di 2 partizioni / un campione alla volta / una persona alla volta |
| **Validità interna** / **esterna** | effetti dovuti solo alle variabili studiate / risultati rappresentativi dell'applicazione reale |
| **Errori casuali** / **sistematici** | calano aumentando il test / dovuti a bias, non calano con la dimensione |
| **Istogrammi empirici** / **Modelli teorici** | base obbligatoria per la DET di un test / strumento per ragionare ed estrapolare |

---

### **25. Sintesi della lezione**

> ✅ **In sintesi.**
> - Ogni autenticazione è una **prova di Bernoulli** con probabilità di errore $p$; gli errori su $n$ prove seguono la **binomiale**; la stessa media può nascondere distribuzioni molto diverse tra gli utenti (**zoo di Doddington**).
> - **Regola dei 3** ($p \approx 3/N$ al 95%), **regola dei 30** (30 errori per ±30% al 90%), **identificazione**: $FNMR_N = FNMR$, $FMR_N = 1 - (1-FMR)^N \approx N \cdot FMR$.
> - Nei sistemi che **apprendono**: overfitting e underfitting, **partizioni** corrette, **k-FCV**, stratified, 5×2, LOO, scelta del modello da mandare in esercizio, **LOPO/LNPO** e nuove sfide dei modelli **zero-shot e few-shot**.
> - **Dispensa Best Practices** (sezioni 1–5, 7, 8): definizioni di sample, template, transazione, tipi di valutazione, dichiarazioni positive/negative ed esplicite/implicite, **FAR/FRR contro FMR/FNMR**, FTE/FTA, binning; pianificazione, selezione dei volontari, **dimensione del test**; raccolta di genuini e impostori; analisi con **DET log-log** e le formule di **FAR e FRR**; che cosa riportare; fattori che influenzano le prestazioni.
