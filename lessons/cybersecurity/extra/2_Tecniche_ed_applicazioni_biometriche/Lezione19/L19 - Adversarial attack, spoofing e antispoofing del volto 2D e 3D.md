# **Lezione 19: Adversarial attack, spoofing e antispoofing del volto 2D e 3D**

---

### **0. Panoramica della lezione**

_(slide pp. 1–2)_

**Outline** (p. 2):

1. **vettori di attacco**: approccio classico e approccio moderno;
2. **adversarial attack detection**: evasione, avvelenamento, estrazione, inferenza;
3. **spoofing e antispoofing** del **volto 2D** e del **volto 3D**.

> ⚠️ **Numerazione interna.** La copertina (p. 1) riporta l'etichetta «L21», residuo di una versione precedente del corso; il PDF corrisponde a questa lezione.

La lezione riprende e completa i temi della Lezione 17 (deepfake) e prepara la Lezione 20 (spoofing di iride, impronta e voce).

<!-- SOURCE_VISUAL id="TAB19-V01" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="1" type="copertina" description="Spoofing e antispoofing, adversarial attacks detection, volto 2D e 3D" reason="Copertina grafica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte A — Vettori di attacco**

### **1. Dal framework classico a quello moderno**

_(slide pp. 3–8)_

**Il framework classico** poggia su **due pilastri** (pp. 3–4):

| Pilastro | Esempi |
|---|---|
| **a) Attacchi al sistema stesso** | infrastruttura fisica o digitale: **DoS**, **manomissione hardware**, violazioni della **rete** |
| **b) Attacchi sugli ingressi** | **presentation attack** al sensore; **perturbazione degli ingressi** (attacchi avversari) |

> ⚠️ **Sigla PAD.** La p. 4 scioglie PAD come «Presentation Attack Devices». Nello standard ISO/IEC 30107, **PAD** significa **Presentation Attack Detection**; l'oggetto usato per l'attacco si chiama **PAI**, *Presentation Attack Instrument* (§5.1).

**Vettori classici**, trattati in altri insegnamenti (pp. 5–6):

| Categoria | Vettori |
|---|---|
| **1. Accesso** | **phishing** (ingannare gli utenti per ottenere credenziali), **brute force** (tentativi ripetuti su password o codici), **exploitation** di vulnerabilità note |
| **2. Rete** | **man-in-the-middle** (intercettazione e alterazione delle comunicazioni), **DoS/DDoS** (sovraccarico), **packet sniffing** |
| **3. Fisici / hardware** | dispositivi fisici come **chiavette USB infette** (*USB baiting*), **tampering** dei componenti, **accesso diretto** da porte non protette o dispositivi incustoditi |
| **4. Software** | **malware** (virus, worm, trojan, ransomware), **zero-day exploit**, **backdoor** |
| **5. Insider** | dipendenti malintenzionati con credenziali legittime, **social engineering**, **negligenza** |
| **6. Economici** | **frodi finanziarie**, **estorsione** (per esempio ransomware), deviazione di flussi monetari |
| **7. Data breach** | **esfiltrazione** di dati sensibili, **data poisoning** dei dati di training, **manipolazione** dei dati per influenzare le decisioni |

**Il framework avanzato** (p. 7) integra i vettori classici con le nuove minacce dovute all'uso intensivo di **IA** e **risorse esterne**:

- **modelli malintenzionati**: creati o inquinati per favorire decisioni errate, per esempio con **bias intenzionali** in modelli di raccomandazione o predizione;
- **sorgenti esterne di dati e modelli**: furto, alterazione o inserimento di dati e modelli di **terze parti**, per esempio un **dataset compromesso** usato per l'addestramento.

> 📌 **Riassumendo** (p. 8). In un framework moderno si prevedono attacchi non solo sugli **ingressi** e sul **sistema**, ma, se si usano modelli di machine learning, anche sul **modello** stesso e sui **dati per allenarlo**.

> 💡 **Collegamento con gli otto punti di attacco.** I punti di Ratha (Lezione 13, §1.4) coprono sensore, canali, estrattore, matcher, database e decisione di un sistema **già costruito**. Con il deep learning si aggiunge la **catena di costruzione**: chi controlla i dati di addestramento o i pesi pre-addestrati può inserire un comportamento malevolo prima ancora che il sistema venga installato.

<!-- SOURCE_VISUAL id="TAB19-V02" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="8" type="schema" description="Framework moderno: attacchi su ingresso, sistema biometrico, decisione, database dei dati e modello di ML" reason="Schema dei punti di attacco" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte B — Adversarial attack**

### **2. Che cosa sono**

_(slide pp. 9–18)_

**L'idea** (p. 9): **cambiamenti impercettibili** ai campioni biometrici che producono **effetti importanti** ed **errori** nei sistemi di IA. Le domande: **come accorgersi dell'attacco?** **Come rendere le reti immuni?**

**Memo** (p. 10). Con la perturbazione si può:

- **aumentare artificiosamente la similitudine** tra due campioni, in un sistema di verifica;
- **cambiare la classificazione**, in un classificatore.

> 📌 **Definizioni** (p. 11).
> - **Adversarial attack**: modificare in modo **sottile** un input originale del classificatore, così che le modifiche siano **quasi impercettibili** all'occhio umano.
> - **Adversarial image**: immagine leggermente modificata con l'obiettivo di **ingannare il classificatore**, cioè farla classificare in modo errato.

Le pp. 12–13 mostrano lo **schema generale** degli attacchi avversari.

**Perché le DNN sono vulnerabili** (p. 14):

- sono **altamente non lineari** e **sensibili a piccole perturbazioni** nello spazio di input: a una piccola variazione specifica rispondono con **grandi variazioni** delle uscite;
- l'attaccante può sfruttare il **gradiente** della rete per creare esempi impercettibili all'occhio ma capaci di alterare drasticamente l'output;
- gli attacchi si **trasferiscono** tra modelli e architetture diverse: un attacco che funziona su una DNN può funzionare anche su un'altra con struttura o dati di addestramento diversi.

**Esempi storici**:

- nel **2013** **Szegedy et al.** introducono uno dei primi esempi avversari: input normali per gli umani che portano gli algoritmi a predizioni errate **con alta confidenza**. Due foto di **lumache**, una delle quali viene classificata come **guanto** (*mitten*) da **Inception V4** di Google (p. 15);
- sul dataset **MNIST**, con una rete **LeNet** addestrata su **60.000** campioni, piccole perturbazioni fanno sbagliare le cifre (p. 16);
- **distorsione minima** (p. 17, IBM Research, «A CLEVER way to resist adversarial attack»): le curve colorate sono le **frontiere di decisione** di un classificatore; l'attacco cerca la perturbazione più piccola che attraversa una frontiera;
- **chihuahua o muffin?** (p. 18): il concetto di **somiglianza** del cervello umano **non** coincide con le **metriche** delle reti.

> 💡 **Due spazi di distanza.** Per l'occhio due immagini sono vicine se sembrano uguali; per la rete lo sono se hanno embedding vicini. L'attacco avversario sfrutta proprio lo scarto tra i due: una perturbazione piccolissima per l'occhio (pochi livelli di grigio per pixel) può spostare l'embedding molto lontano, oltre la soglia del matcher.

<!-- SOURCE_VISUAL id="TAB19-V03" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="12" type="schema" description="Schema generale degli adversarial attacks: immagine originale più perturbazione uguale immagine avversaria" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V04" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="16" type="griglia di immagini" description="Adversarial attacks su MNIST con rete LeNet" reason="Esempi visivi senza testo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V05" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="17" type="grafico" description="Distorsione minima rispetto alle frontiere di decisione di un classificatore (IBM CLEVER)" reason="Frontiere visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Come si costruisce un attacco**

_(slide pp. 19–31)_

#### **3.1 Il gradiente come leva**

Esistono moltissimi attacchi, contro reti profonde ma anche contro modelli tradizionali come **SVM** e **regressione lineare**; la maggior parte mira a **deteriorare** le prestazioni su un compito specifico, cioè a **ingannare** l'algoritmo (p. 19).

**Uso del gradiente** (p. 20). **Come una leva**, si cerca una **minima variazione in ingresso** che faccia compiere, nello spazio delle feature interno, un **salto** abbastanza grande da spostare la classificazione. Il processo è **iterativo** e ricorda l'addestramento, ma aggiorna i **pixel** invece dei **pesi**:

$$\text{new\_weights} = \text{old\_weights} - \text{learning\_rate} \cdot \text{gradients}$$

$$\text{new\_pixels} = \text{old\_pixels} + \epsilon \cdot \text{gradients}$$

> 💡 **La differenza di segno.** Nell'addestramento si **scende** lungo il gradiente della loss rispetto ai **pesi**, per ridurre l'errore. Nell'attacco si **sale** lungo il gradiente della loss rispetto all'**immagine**, per aumentare l'errore, lasciando i pesi fermi.

> 💡 **FGSM (nota didattica).** Il metodo più noto, il *Fast Gradient Sign Method* (Goodfellow et al., 2015), usa solo il **segno** del gradiente in un unico passo:
> $$x' = x + \epsilon \cdot \mathrm{sign}\big( \nabla_x J(\theta, x, y) \big)$$
> dove $J$ è la loss, $\theta$ i pesi, $y$ l'etichetta vera. Ogni pixel cambia al massimo di $\epsilon$ (per esempio 2 livelli su 255), ma tutti nella direzione che più aumenta la loss: la somma di tanti contributi minimi sposta l'uscita.

#### **3.2 White, black e gray box**

_(slide pp. 21–22)_

| Attacco | Che cosa conosce l'attaccante |
|---|---|
| **White box** | i **parametri** del modello, per esempio perché il sistema usa una **CNN pubblica** |
| **Black box** | **nessun** parametro: usa un modello diverso, o nessun modello, sperando che le immagini avversarie si **trasferiscano** |
| **Gray box** | **alcune** informazioni, per esempio che il sistema è un **fine-tuning** di un modello pubblico |

**Attacchi fisici a modelli black box** (p. 22). «Fisico» indica un attacco con un **artefatto al sensore**: uno schermo davanti alla telecamera, un oggetto sul volto.

#### **3.3 Non-targeted e targeted**

_(slide pp. 23–31)_

| | **Non-targeted** (p. 23) | **Targeted** (p. 26) |
|---|---|---|
| Obiettivo | una predizione **errata qualsiasi** | una predizione **specifica** scelta dall'attaccante |
| Esempi biometrici | da una voce in **blacklist** a un utente **sconosciuto**; un volto che **non viene più rilevato** come volto | da una voce impostora a una **specifica voce genuina**; da un volto impostore al **genuino più simile** nel database |
| Pericolosità | minore | **maggiore** |

**Esempi nella visione**:

- **CAMOU** (p. 24): un'auto con **adesivi mimetici** progettati ad hoc viene riconosciuta come un **«dolce»** dal rilevatore;
- **Robust Physical Perturbations**, RP2 (p. 25): perturbazioni fisiche per il mondo reale, funzionanti anche a **scale** e **angoli** diversi;
- **occultamento dell'identità** (p. 27): immagini avversarie contro un rilevatore **Faster R-CNN** sul dataset **1070-W**;
- **rumore di compressione** (p. 28, Bose et al., IEEE MMSP 2018): **non è vero** che una compressione come il **JPEG** cancelli sempre la perturbazione. Anche a livelli di compressione normali, una quota tra il **95% e il 99%** dei volti può **non essere rilevata**.

**Esempio targeted nello speech-to-text** (pp. 26, 30): l'audio che dovrebbe essere trascritto come «I'm taking a walk in Central Park» diventa **testo casuale**, **silenzio** o perfino **«Call 911 immediately!»**. La rilevanza cresce ora che i sistemi di IA sono **«agentificati»**, cioè eseguono compiti di alto livello come **acquisti** ed **email**.

**Abbigliamento avversario** (p. 31). Molti sistemi di riconoscimento facciale iniziano con un **rilevatore** del corpo o del volto basato su reti profonde. L'attacco consiste nell'indossare **indumenti** (abiti, cappelli, caschi) che impediscono il rilevamento. Esistono aziende che li producono, come **Cap_able** dal 2022.

> 💡 **Colpire il primo stadio.** Se il rilevatore non trova il volto, il riconoscimento non parte nemmeno: non serve ingannare il matcher, basta **evitare la detection**. È un attacco di tipo non-targeted, economico e scalabile, ed è per questo che la robustezza del **detector** conta quanto quella dell'estrattore.

<!-- SOURCE_VISUAL id="TAB19-V06" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="24" type="immagini" description="CAMOU: auto con camuffamento avversario riconosciuta come dolce" reason="Esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V07" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="25" type="immagini" description="Robust Physical Perturbations (RP2) su segnali stradali a scale e angoli diversi" reason="Esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V08" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="27" type="immagini" description="Occultamento dell'identità con immagini avversarie contro Faster R-CNN" reason="Esempio visivo senza testo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V09" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="29" type="immagini" description="Esempi di adversarial attacks" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V10" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="31" type="fotografie" description="Abbigliamento avversario Cap_able contro il rilevamento di corpo e volto" reason="Esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. I quattro tipi di attacco avversario e le difese**

_(slide pp. 32–46)_

#### **4.1 Evasione, avvelenamento, estrazione, inferenza**

| Tipo (p. 33) | Che cosa fa | Esempio |
|---|---|---|
| **Evasione** | **modifica l'input** per influenzare il modello | modifiche a immagini mediche per cambiarne la classificazione |
| **Avvelenamento** | **modifica i dati di addestramento** per inserire una **backdoor** | modelli addestrati senza accorgersene con backdoor che li controllano |
| **Estrazione** | **ruba** un modello proprietario | interrogare regolarmente un modello per estrarne informazioni |
| **Inferenza** | ottiene **informazioni su dati privati** | ricavare proprietà dei dati di addestramento fino a identificare singoli record |

**Evasione** (p. 34). È il tipo **più diffuso e studiato**. L'attaccante manipola i dati **durante il dispiegamento** per ingannare classificatori già addestrati. Per questo è il più **pratico** e usato in scenari di **intrusione** e **malware**, per esempio offuscando malware o spam perché vengano classificati come legittimi, **senza** toccare i dati di addestramento.

**Avvelenamento («backdoor»)** (p. 35):

- l'attaccante contamina i **dati di addestramento** o le loro **etichette** per causare un calo di prestazioni o un comportamento nascosto;
- poiché molti sistemi vengono **riaddestrati** con dati raccolti durante il funzionamento, l'attaccante può iniettare campioni malevoli **durante l'uso** (si pensi al **template update** della Lezione 8);
- sempre più reti vengono allenate con immagini ed etichette **prese dalla rete** senza un controllo completo della provenienza;
- nell'esempio della slide un **piccolo quadrato giallo** al centro dell'immagine è un **trigger** che fa cambiare la classificazione.

**Estrazione** (pp. 36–37):

- si **sonda** il modello con molti input e se ne osservano gli output (**accesso oracolare**);
- con le coppie input-output si addestra un **modello sostitutivo**;
- è **difficile**: serve molta capacità di calcolo per ottenere accuratezza e fedeltà, ed equivale ad addestrare un modello da zero;
- nell'esempio biometrico, oltre a replicare il modello di face authentication interrogandolo con volti, l'attaccante **migliora un'immagine** facendo **crescere il match score** a ogni query.

> 💡 **Estrazione più evasione.** Un modello sostitutivo, anche imperfetto, trasforma un attacco **black box** in un attacco **white box** sul sostituto: si calcolano i gradienti sul sostituto e, per la trasferibilità (§2), le immagini avversarie funzionano spesso anche sul modello vero. Per questo limitare il numero di query e non restituire lo score esatto sono difese concrete.

**Inferenza** (p. 38):

- **inverte il flusso di informazioni** del modello, facendogli rivelare conoscenze non destinate a essere condivise;
- viola **privacy** e **sicurezza** delle persone i cui dati sono stati usati. La domanda tipica è: **«la persona X è nei dati di addestramento?»** (*membership inference*);
- **difese**: più privacy sui dati (**anonimizzazione**, **privacy differenziale**) e classificatori che rivelano meno sui singoli dati;
- **esempio**: nel **dicembre 2023** un gruppo di ricercatori di **Google DeepMind** ha fatto rivelare a ChatGPT informazioni personali di decine di persone chiedendogli di **ripetere all'infinito la stessa parola**.

<!-- SOURCE_VISUAL id="TAB19-V11" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="35" type="immagini" description="Poisoning con trigger: il quadrato giallo al centro cambia la classificazione" reason="Trigger visibile solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V12" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="37" type="schema" description="Attacco di estrazione con modello sostitutivo addestrato sulle query al modello bersaglio" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.2 Come ci si difende**

In letteratura e nella pratica ci sono **tre approcci** (p. 39).

**Approccio 1: intelligence e buona pratica** (pp. 40–44). L'avversario ha bisogno di **qualche forma di accesso** al modello o ai dati. Anche con un modello ospitato in un ambiente protetto, l'accesso può avvenire perché:

| Situazione | Rischio |
|---|---|
| **dataset da fonte non verificata** | può essere stato **avvelenato** e pubblicato apposta per squadre ignare |
| **modello da fonte non verificata** | l'avversario lo conosce e può attaccarlo; può essere pre-addestrato su dati avvelenati |
| **modello pubblico** (per esempio da **Hugging Face**) | l'avversario analizza lo stesso modello o uno simile che svolge lo **stesso compito**: può bastare per un attacco trasferibile |
| **accesso al flusso di lavoro di ML** (lo hai pubblicato? un insider?) | architettura, pesi, dati e perfino le **difese** diventano noti; gli **insider** malintenzionati sono probabilmente la **minaccia maggiore** |

**Approccio 2: addestramento avversario** (p. 45). Si **inseriscono nel training set esempi corrotti**: dopo questo apprendimento modificato, i dati perturbati **non riescono più** a cambiare l'esito. È il metodo di difesa **più noto**.

> 💡 **Il gioco a due (nota didattica).** L'addestramento avversario risolve un problema min-max: per ogni campione si cerca la perturbazione peggiore entro il budget $\epsilon$, e si aggiornano i pesi per classificare bene anche quella,
> $$\min_\theta\ \mathbb{E}_{(x, y)} \left[ \max_{\lVert \delta \rVert \leq \epsilon} J(\theta, x + \delta, y) \right]$$
> Protegge bene dagli attacchi **simili** a quelli visti in addestramento, ma costa più calcolo e può ridurre un po' l'accuratezza sui dati puliti.

**Approccio 3: regolarizzazione** (p. 46). Le tecniche di regolarizzazione aiutano la rete a **resistere** o a **recuperare** dalle perturbazioni:

| Tecnica | Come agisce |
|---|---|
| **Weight decay** | aggiunge alla loss una **penalità proporzionale alla grandezza dei pesi**, per esempio $J' = J + \lambda \lVert \theta \rVert^2$, riducendo l'overfitting |
| **Dropout** | elimina **casualmente** unità o connessioni durante l'addestramento, creando una forma di apprendimento collettivo con più **diversità e ridondanza** |
| **Batch normalization** | **normalizza gli input di ogni strato**, riducendo lo spostamento covariato interno (i parametri di uno strato cambiano la distribuzione degli ingressi del successivo) e rendendo la rete più robusta |

> ⚠️ **Nessuna difesa è definitiva.** La regolarizzazione rende la funzione della rete più «liscia» e aiuta, ma da sola non basta contro attacchi mirati; l'addestramento avversario protegge dagli attacchi noti ma non da quelli nuovi. Nella pratica le tre strade si combinano, e resta decisivo **non esporre** modello, score e dati (approccio 1).

---

## **Parte C — Presentation attack e vulnerabilità biometrica**

### **5. Terminologia ISO e metriche**

_(slide pp. 47–60)_

#### **5.1 Zero effort e non-zero effort**

Dopo gli attacchi al modello si torna agli **attacchi sugli ingressi** e alla norma **ISO/IEC 30107** (p. 47). I contenuti falsi generati per fini commerciali o politici sono stati trattati nella Lezione 17 (p. 48).

**Vulnerabilità dei sistemi biometrici** (p. 49). Secondo la terminologia ISO gli attacchi si dividono in:

| | **Zero-effort** | **Non-zero effort** |
|---|---|---|
| Che cosa | test **impostore «inter-individuale»**: persone diverse che si presentano normalmente | si creano **spoof** o **artefatti** per testare la robustezza ai presentation attack |
| Metriche | **FMR, FNMR**, **FAR, FRR** (Lezione 2) | metriche di spoofing: **APCER**, **BPCER** |

**Metriche per gli attacchi non-zero effort** (pp. 50–51). Non si usano FAR e FRR classici:

| Metrica | Nome | Che cosa misura |
|---|---|---|
| **APCER** | *Attack Presentation Classification Error Rate* | la **vulnerabilità**: quota di attacchi classificati come presentazioni genuine |
| **BPCER** | *Bona Fide Presentation Classification Error Rate* | l'impatto sull'**usabilità**: quota di presentazioni genuine classificate come attacchi |

> 💡 **Le formule (ISO/IEC 30107-3, nota didattica).** Per un tipo di strumento d'attacco con $N_{PAIS}$ presentazioni, e $N_{BF}$ presentazioni genuine, con $Res_i = 1$ se la presentazione $i$ è classificata come **attacco** e $0$ altrimenti:
> $$APCER = \frac{1}{N_{PAIS}} \sum_{i=1}^{N_{PAIS}} (1 - Res_i) \qquad BPCER = \frac{1}{N_{BF}} \sum_{i=1}^{N_{BF}} Res_i$$
> Se su 200 attacchi con maschera 6 passano, $APCER = 3\%$; se su 1000 utenti genuini 20 vengono bloccati, $BPCER = 2\%$. Come FMR e FNMR, le due metriche si scambiano al variare della soglia del modulo PAD, e si rappresentano con curve DET. Lo standard chiede di riportare l'APCER per **ogni tipo** di strumento d'attacco, e di considerare il **peggiore**.

> ⚠️ **«APMR».** La p. 49 cita «APCER, APMR». Nella norma la metrica a livello di **sistema completo** è la **IAPMR** (*Impostor Attack Presentation Match Rate*): la quota di attacchi d'impostore che **ottengono un match** con l'identità bersaglio, cioè passano sia il PAD sia il matcher.

**Vulnerabilità (continua)** (p. 52). La slide richiama il **function creep**: il **graduale ampliamento** dell'uso di una tecnologia o di un sistema **oltre lo scopo** per cui era nato, soprattutto quando porta a una potenziale **invasione della privacy** (Lezione 13).

> 📌 **Un sistema biometrico è un sistema informatico** (p. 53). Valgono sempre anche le possibilità di attacco **classiche** studiate nel corso di laurea.

<!-- SOURCE_VISUAL id="TAB19-V13" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="52" type="schema" description="Vulnerabilità dei sistemi biometrici con riferimento al function creep" reason="Schema delle vulnerabilità" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2 Attacchi diretti e indiretti**

_(slide pp. 54–57)_

| | **Indiretti** (pp. 55–56) | **Diretti** (p. 57) |
|---|---|---|
| Dove | **dentro** il sistema | **al sensore** |
| Esempi | **scavalcare** l'estrattore o il comparatore; **manipolare** i riferimenti nel database; sfruttare punti deboli dei **canali**; **sostituzione** nel database, **sovrascrittura** dell'output, **tampering**, **intercettazione** | **presentation** o **spoofing attack**: il sensore **non** viene sostituito né manomesso |

È la stessa distinzione vista per gli e-Gate (Lezione 14, §15.3).

<!-- SOURCE_VISUAL id="TAB19-V14" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="54" type="schema" description="Adversary attacks diretti e indiretti sullo schema del sistema biometrico" reason="Posizione degli attacchi nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V15" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="56" type="schema" description="Esempi di attacchi indiretti: sostituzione nel database, sovrascrittura dell'output, tampering, intercettazione" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.3 Le definizioni della ISO/IEC 30107-1**

_(slide pp. 58–60)_

> 📌 **Glossario** (ISO/IEC 30107-1:2016, p. 59).
> - **Presentation Attack** (PA): presentazione al sottosistema di acquisizione con l'obiettivo di **interferire** con il funzionamento del sistema biometrico.
> - **Presentation Attack Detection** (PAD): **determinazione automatica** di un presentation attack.
> - **Presentation Attack Instrument** (PAI): caratteristica biometrica od **oggetto** usato in un attacco.
> - **Presentazione normale** (*bona fide*): interazione tra il soggetto e il sistema di acquisizione nel modo previsto dalla **policy** del sistema (non un attacco).

**Che cosa significa essere conformi alla ISO 30107** (p. 60). Il sistema è stato **testato** con:

- **campioni genuini** (bona fide);
- **artefatti d'attacco** (presentation attack instrument);
- un **protocollo di prova strutturato** con **metriche specifiche** (APCER, BPCER).

---

### **6. Impostori, occultatori e artefatti**

_(slide pp. 61–81)_

#### **6.1 Impostori e trucco**

> 📌 **Impostore** (ISO/IEC 2382-37, p. 61). Soggetto sovversivo che tenta di essere **associato al riferimento biometrico di un altro**. Obiettivo: esito **positivo** in 1:1 o 1:N (**impersonation attack**).

Azioni possibili:

- trovare un **sosia** (*look-alike*);
- rendere il proprio **aspetto simile** al riferimento;
- presentare un **artefatto**: **gummy finger** per l'impronta, **trucco avversario** per il volto, **lenti a contatto stampate** per l'iride.

**Il trucco** (pp. 62–63):

- lo stesso soggetto prima e dopo il trucco può mostrare una **discrepanza rilevante** (Li et al., «Anti-Makeup», AAAI-18);
- il trucco può rendere un soggetto **simile a un bersaglio** e compromettere l'accuratezza (Dantcheva, Chen, Ross, BTAS 2012; approcci robusti con sottospazi a patch, *Information Fusion*, 2016).

<!-- SOURCE_VISUAL id="TAB19-V16" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="63" type="fotografie" description="Soggetto prima e dopo il trucco confrontato con il volto bersaglio" reason="Esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2 Occultatori d'identità e presentazioni non conformi**

> 📌 **Identity concealer** (ISO/IEC 2382-37, p. 64). Soggetto sovversivo che tenta di **evitare di essere associato al proprio** riferimento biometrico. Obiettivo: esito **negativo** in 1:1 o 1:N, tipico delle **watchlist**.

Azioni: presentarsi **lontano** dalla posa, posizione o espressione standard; **evitare il rilevamento** o la **segmentazione**.

**CV Dazzle** (pp. 65–66): **camuffamento** contro il face detection con trucco e acconciature (Feng, ACM Multimedia 2013):

- trucco che **contrasta** con il tono della pelle in tonalità e direzioni insolite;
- oscurare la zona del **ponte nasale**;
- coprire parzialmente **una delle regioni oculari**;
- nascondere la **forma ellittica** della testa;
- creare un aspetto **asimmetrico**.

Seguono esempi artistici, tra cui quelli di Grigori Bakunov.

> 💡 **Perché funziona.** I rilevatori classici come Viola-Jones cercano pattern di contrasto tipici (occhi più scuri delle guance, ponte del naso chiaro) e la simmetria del volto. CV Dazzle rompe esattamente quei pattern: è un attacco avversario **fisico** e **non-targeted** contro il primo stadio, come l'abbigliamento del §3.3.

**Presentazione non conforme** (p. 67):

- **Apple Face ID** funziona con lenti **polarizzate**, ma alcune lenti **bloccano l'infrarosso** impedendo il riconoscimento;
- i sensori di **impronte** funzionano male se il polpastrello è **mal posizionato**, **ruotato troppo**, **bagnato** o **sporco**;
- **lo scopo** è **bypassare** la biometria ingannando il sensore per passare all'**autenticazione classica**, più facilmente attaccabile.

> ⚠️ **Il fallback è parte della superficie d'attacco.** Se dopo tre fallimenti biometrici il sistema chiede un PIN a 4 cifre, la sicurezza reale è quella del PIN. La politica di ripiego va progettata con la stessa cura del sistema biometrico.

**Occlusioni** (p. 68). Il volto può essere coperto da **barba**, **occhiali da sole** o **indumenti**, in enrollment o in verifica. **Non accettare** campioni se l'area coperta non garantisce l'**accuratezza certificata** del sistema. Esempio: una **patente** rilasciata in Florida con foto occlusa.

<!-- SOURCE_VISUAL id="TAB19-V17" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="66" type="fotografie" description="Esempi di identity concealing con trucco e acconciature" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.3 Liveness, artefatti e spoof**

_(slide pp. 69–72)_

> 📌 **Liveness** (p. 69, da E. Newton, NIST). Qualità o stato dell'**essere vivo**, reso evidente da:
> - **caratteristiche anatomiche**, come l'assorbimento dell'illuminazione da parte di pelle e sangue;
> - **reazioni involontarie**, come piccoli movimenti degli occhi;
> - **funzioni fisiologiche**, come la reazione dell'iride alla luce e l'attività cardiaca;
> - **reazioni volontarie** o comportamenti, come la presentazione in risposta a un'istruzione.
>
> **Liveness detection**: rilevamento di queste caratteristiche o reazioni per stabilire se il campione proviene da un **soggetto vivo presente** al punto di acquisizione. I metodi di liveness detection sono un **sottoinsieme** dei metodi **PAD**.

> 📌 **Artefatto e spoof** (p. 70). **Artefatto**: oggetto o rappresentazione **artificiale** che presenta una **copia** di caratteristiche biometriche o pattern biometrici **sintetici**. **Spoof** (verbo): sovvertire un sistema presentando un artefatto.

> 💡 **Perché la liveness è solo una parte del PAD.** Un dito vero **amputato** o un volto vero **sotto costrizione** non sono artefatti; un utente vivo con una **lente a contatto stampata** è vivo ma presenta un artefatto. Il PAD deve riconoscere tutti gli attacchi, la liveness ne copre solo una parte.

La p. 71 classifica i **Presentation Attack Instruments** (§6.5).

**Trucco professionale** (p. 72). Trucco e **maschere di pelle artificiale** possono ingannare efficacemente il riconoscimento facciale. Contromisure possibili: tecniche **termografiche** o **multibanda** su frequenze **non visibili** per rilevare componenti artificiali sulla pelle vera (Lezione 12).

<!-- SOURCE_VISUAL id="TAB19-V18" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="71" type="tabella grafica" description="Classificazione dei Presentation Attack Instruments (NIST, E. Newton)" reason="Classificazione presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.4 Attacchi nella realtà**

_(slide pp. 73–79)_

**La «fascinazione» degli attacchi** (p. 73). I media rappresentano gli attacchi in modo **estremamente irrealistico**, come in *Demolition Man* (1993); la realtà è diversa. La p. 74 confronta presentazioni genuine e attacchi reali (Fierrez et al., *Introduction to Face Presentation Attack Detection*), e la p. 75 i **livelli** di attacco per l'antispoofing.

**Materiali sempre più realistici** (p. 76). Nelle protesi e nei materiali sintetici che emulano il corpo si sono fatti **grandi passi avanti**, come le **maschere in lattice stampate in 3D** (progetto europeo **TABULA RASA**).

**Laboratori e dataset per i test** (p. 77):

- **iBeta Quality Assurance**: laboratorio **indipendente** che testa i sistemi secondo **ISO/IEC 30107**. L'azienda invia il proprio sistema di liveness; iBeta prepara gli attacchi (**foto, replay, maschere, silicone**), li presenta al sistema, registra **quanti attacchi passano** (APCER) e **quanti genuini vengono bloccati** (BPCER) e produce un **report ufficiale**;
- **Axon Labs**: vende **dataset** per **allenare** modelli di liveness, fare **pre-testing** e preparare un sistema prima della certificazione.

**Casi reali**:

- **maschere in silicone** (p. 78): **Conrad Zdzierak**, 30 anni, si è dichiarato colpevole in **Ohio** di una rapina aggravata e cinque rapine, commesse indossando una **maschera** che lo faceva sembrare un **uomo nero**. La somiglianza era tale che alcuni **testimoni** hanno riconosciuto nella foto dell'uomo inizialmente arrestato il rapinatore;
- **Marc Terrance George** (p. 79): giamaicano, 41 anni, condannato a **Tucson** a **13 mesi** di carcere e all'espulsione dopo essersi dichiarato colpevole nel 2006 di rientro illegale. Nel settembre 2005, al porto d'ingresso di **Nogales** (Arizona), gli agenti hanno scoperto che le sue **impronte digitali** erano state **sostituite chirurgicamente** con **pelle dei piedi**.

> 💡 **La lezione dei due casi.** Il primo inganna **l'occhio umano**, il secondo un **sistema automatico**: entrambi mostrano che l'attaccante motivato è disposto a costi e rischi enormi. La valutazione di un PAD deve quindi considerare il **potenziale d'attacco** (competenze, tempo, costo dei materiali), non solo le foto stampate.

<!-- SOURCE_VISUAL id="TAB19-V19" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="74" type="fotografie" description="Presentazioni genuine contro attacchi reali al volto" reason="Confronto visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V20" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="75" type="tabella grafica" description="Livelli di attacco per l'antispoofing secondo il riferimento ISO" reason="Livelli presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.5 Tipi di presentation attack**

_(slide pp. 80–81)_

Classificazione di E. Newton (NIST, p. 80):

| Origine | Tipo | Esempi |
|---|---|---|
| **Artificiale** | **completo** | gummy finger, **video** del volto |
| | **parziale** | **colla** sul dito, occhiali da sole, pattern artificiali, **lenti a contatto** |
| **Umana** | **senza vita** | parte di cadavere, dito o mano recisi |
| | **alterata** | **mutilazione**, scambio chirurgico di impronte tra mani e/o piedi |
| | **non conforme** | espressione estrema, punta o lato del dito |
| | **coercita** | soggetto **incosciente**, sotto **minaccia** o costrizione |
| | **conforme** | tentativo d'impostore **zero-effort** |

**Coercizione** (p. 81): a volte gli attacchi possono essere **più semplici** di quanto immaginino i progettisti (si rimanda a una raccolta sui miti del «corpo rubato»).

---

## **Parte D — Antispoofing**

### **7. Metodi generali**

_(slide pp. 82–96)_

#### **7.1 Dove e che cosa osservare**

**Rilevamento dell'attacco dentro o fuori il sistema** (p. 83):

- **interno**: il sistema analizza il campione;
- **esterno**: informazioni di contesto, come la **stessa identità in due posti contemporaneamente**.

La p. 84 mostra lo **schema generale** di antispoofing con il modulo di riconoscimento facciale (**FR**). La p. 85 confronta le **variazioni reali** delle immagini (posa, scala, espressione, illuminazione, dataset **SiW**) con quelle degli **attacchi** con diversi supporti (Liu et al., CVPR 2018).

> 📌 **Approcci multibanda** (p. 86). **Più tipi di immagine** si sfruttano, **migliore** è la capacità di individuare un attacco.

<!-- SOURCE_VISUAL id="TAB19-V21" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="84" type="schema" description="Schema generale di antispoofing con modulo di riconoscimento facciale" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V22" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="85" type="griglia di immagini" description="Variazioni reali di posa, scala, espressione e luce (SiW) contro presentation attack con supporti diversi" reason="Confronto visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2 Sette famiglie di tecniche**

| # | Tecnica | Principio (slide) |
|:---:|---|---|
| 1 | **Immagini infrarosse** (p. 87) | immagini **stampate** o mostrate su uno **schermo** **non** possono attaccare camere **NIR**: la carta riflette l'IR in modo diverso dalla pelle, e gli schermi non emettono il volto in NIR |
| 2 | **Approcci compositi VIS + NIR + 3D** (p. 88) | dispositivi che acquisiscono il **volume** degli oggetti, come **Kinect** e **RealSense**, migliorano l'antispoofing; esempio con **RealSense R200** e una mesh 3D da **250.000 triangoli** |
| 3 | **Analisi a colori** (pp. 89–90) | **istogrammi** di ogni canale colore per riconoscere foto stampate e schermi; **effetto moiré**: le immagini di stampe o schermi mostrano un pattern di colore distintivo, visibile direttamente o con la **trasformata di Fourier** |
| 4 | **3D con un sensore 2D** (p. 91) | tecnica **shape from motion** con una sola camera; per esempio **ZoOm** di FaceTec chiede di **avvicinare il telefono** al volto |
| 5 | **Flusso ottico** (pp. 92–93) | negli attacchi con **fotografie** il flusso è più **omogeneo e uniforme**; nei volti veri si rilevano i **micro-movimenti** del volto rispetto all'ambiente (Lezione 16) |
| 6 | **Sensori ad altissimo frame rate** (p. 94) | catturano le **dinamiche** del tratto: **120 fps** a **19,3 MP**, **960 fps** in Full HD. Portano miglioramenti come l'osservazione della **compressione del dito** durante l'acquisizione e delle **pulsazioni cardiache** nel volto (Lezione 12) |
| 7 | **Challenge-response** (p. 95) | il sistema dà **istruzioni casuali** e verifica che siano state seguite: **girare la testa** in una direzione estratta a caso; per la voce, **dire una lista di parole** («Open Sesame») |

> 💡 **Il moiré nello spettro (nota didattica).** Un'immagine naturale ha uno spettro di Fourier che decresce dolcemente dalle basse alle alte frequenze. Riprendere uno schermo sovrappone due griglie periodiche (pixel dello schermo e pixel del sensore): nascono frange di **battimento** alla frequenza differenza, che nello spettro compaiono come **picchi isolati e periodici**. Nell'esempio della slide, l'immagine Lena ripresa con un iPhone 4 dallo schermo di un MacBook Pro da 13″ mostra proprio questo **pattern di picchi**: «probabilmente falsa» (Lezione 12, §8.1).

> 💡 **Perché le foto hanno flusso omogeneo.** Una foto mossa davanti alla camera è un **piano rigido**: tutti i suoi punti si spostano con la stessa trasformazione, e il flusso ottico è quasi uniforme. Un volto vero è **tridimensionale** (il naso si sposta più delle orecchie quando la testa ruota) e **deformabile** (palpebre, bocca). Questa **non uniformità** è la firma della vita.

**Lo standard di riferimento** (p. 96): **ISO/IEC 30107-3:2017**, *Biometric presentation attack detection — Part 3: Testing and reporting*:

- **principi e metodi** per valutare le prestazioni dei meccanismi PAD;
- **reporting** dei risultati delle valutazioni;
- **classificazione dei tipi di attacco** noti (in un allegato informativo). La slide mostra un esempio di **livelli di attacco** per il volto.

> ⚠️ **I «livelli» dei laboratori.** I **livelli** 1, 2 e 3 citati da produttori e laboratori sono categorie dei **programmi di test** (come quelli di iBeta), che graduano gli attacchi per **competenze, tempo e costo** dei materiali secondo i principi della ISO/IEC 30107-3. La norma definisce il metodo di test e le metriche, non una «certificazione di livello».

<!-- SOURCE_VISUAL id="TAB19-V23" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="87" type="confronto di immagini" description="Osservazioni su immagini infrarosse: foto stampate e schermi non attaccano camere NIR" reason="Confronto visivo VIS e NIR" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V24" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="88" type="immagini" description="Approccio composito VIS, NIR e 3D con RealSense R200 e mesh da 250.000 triangoli" reason="Dati 3D visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V25" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="89" type="grafici" description="Analisi degli istogrammi dei canali colore per attacchi con immagine stampata e schermo" reason="Istogrammi visibili solo nei grafici" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V26" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="90" type="immagini e spettro" description="Effetto moiré: Lena ripresa da uno schermo con picchi periodici nello spettro di Fourier" reason="Pattern e picchi visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V27" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="93" type="immagini" description="Flusso ottico omogeneo nei replay attack con foto e micro-movimenti nel volto reale" reason="Campi di flusso visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **8. Antispoofing del volto 2D**

_(slide pp. 97–104)_

**Materiali realistici anche per il 2D** (p. 98): le maschere in lattice stampate in 3D del progetto TABULA RASA attaccano anche i sistemi 2D.

**Possibili approcci** (p. 99). Si applicano i concetti generali:

- **istogrammi** dei colori;
- controllo in **IR**;
- **VIS + IR**, con fusione e/o verifica di **congruenza**;
- ricerca del **moiré** e studio in frequenza dei **pattern di stampa**.

Si aggiungono metodi specifici per il volto, su **frame singolo** (2D statico) e su **video** (2D nel tempo).

**Approccio deep generalista** su frame singolo (p. 100). Con **database pubblici** di attacchi e immagini reali si **addestrano reti** a trovare nei dettagli la classificazione **reale/falso**. È **sempre possibile**, ma con **bassa explainability** e poca possibilità di **tuning**.

**Battito delle palpebre** (p. 101). Con un sensore ad alto frame rate (**oltre 30 fps**) e buona risoluzione si verifica che:

1. **non** ci sia **moiré**, quindi non si stia riprendendo un video;
2. il **movimento delle palpebre** sia compatibile, per **direzione** ed **entità**, con quello naturale.

Il **flusso ottico** assegna a ogni pixel del frame corrente un **vettore di movimento** verso la posizione dello stesso pixel in un frame successivo.

**Attacco e difesa** (p. 102). Un attacco con una **maschera con i fori** per mostrare **occhi vivi** viene comunque identificato: il flusso ottico mostra **deformazioni** dovute alla **discontinuità** ai bordi dei fori, tra le parti **mobili** (occhi) e quelle **fisse** (maschera).

**Fotopletismografia remota** (pp. 103–104):

- l'rPPG rileva gli attacchi con **maschere 3D** tramite la corrispondenza del segnale del battito in diverse regioni (Liu et al., ECCV 2018);
- **Intel FakeCatcher** (2022): prima piattaforma online per la detezione dei deepfake, con **rPPG** e rilevamento dello **sguardo** (Lezione 17, §8.2).

> 💡 **Maschera e battito.** Sopra una maschera in silicone il colore non varia con il battito cardiaco: il segnale rPPG manca o è solo rumore. Sui fori degli occhi il segnale c'è, ma è **incoerente** con quello, assente, della fronte e delle guance: la corrispondenza tra regioni tradisce la maschera.

<!-- SOURCE_VISUAL id="TAB19-V28" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="101" type="sequenza di immagini" description="Controllo del battito delle palpebre con frame video e flusso ottico corrispondente" reason="Flusso visibile solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V29" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="102" type="immagini" description="Maschera con fori per gli occhi rilevata dalle deformazioni del flusso ottico ai bordi" reason="Deformazioni visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V30" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="103" type="schema e grafici" description="Antispoofing con fotopletismografia remota per maschere 3D (Liu et al., ECCV 2018)" reason="Segnali visibili solo nei grafici" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **9. Antispoofing del volto 3D**

_(slide pp. 105–124)_

#### **9.1 Sensori 3D**

**Il 3D come antispoofing** (p. 105): una foto o uno schermo sono **piatti**.

**Sensori e webcam 3D** (pp. 106–108): **Apple iPhone X Face ID**, **Intel RealSense D435** (anche integrato nei laptop), **Microsoft Kinect**, **RealSense R200**. La tecnologia **RealSense** permette di rilevare la **congruenza tra 2D e 3D** della scena.

**Apple Face ID** (p. 109): sistema a **luce strutturata** che proietta circa **30.000 punti** per ricostruire il 3D.

**Spoofing di Face ID** (p. 110). **Bkav Corporation**, azienda vietnamita di sicurezza di rete, software, smartphone e smart home, ha dimostrato un attacco combinando **3D e IR con parti nel visibile**: una maschera che riproduce la forma del volto con elementi che ingannano anche la parte infrarossa.

> 💡 **La lezione dell'attacco a Face ID.** Il 3D alza molto il costo dell'attacco: servono il modello della forma del volto, materiali con la giusta risposta IR e parti nel visibile. Ma non lo rende impossibile. È il principio del §6.4: la sicurezza di un PAD si esprime come **potenziale d'attacco** necessario, non come assoluto.

<!-- SOURCE_VISUAL id="TAB19-V31" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="107" type="immagini" description="Immagini RGB, IR e profondità della RealSense D435 (ripasso)" reason="Mappe visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V32" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="109" type="schema" description="Apple Face ID a luce strutturata con circa 30.000 punti" reason="Schema dei componenti" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **9.2 3D da un sensore 2D, spettrometria e termografia**

**Miglioramenti della biometria del volto** (p. 111). Tra i fattori migliorativi del volto si erano indicati **alta risoluzione**, **frame multipli** e **dati 3D**: un sistema commerciale li riassume tutti.

**Controllo 3D con un sistema 2D** (pp. 112–113). Partendo dal sensore più semplice, la **camera visibile 2D**, si può eseguire lo **shape from motion**: il movimento relativo tra camera e volto rivela la profondità.

**Spettrometria e termografia del volto** (p. 114):

- con **illuminatori a frequenze ottiche diverse** si ottiene in tempo reale una **spettrografia** del volto;
- un classificatore analizza lo spettrogramma e stima il **materiale**: **organico** o **plastico**, se contiene **acqua**, **grassi**, **pigmenti**, **gomma**, **silicone**;
- anche un'immagine **termografica** dice se si ha davanti un tessuto alla **temperatura corretta**.

**Approfondimenti non richiesti all'esame** (p. 115): il sito spoofbounty.com e le demo di **ZoOm** e **BioID**.

**Attacchi alla challenge-response** (p. 116). Il **3D face tracking** con fusione delle texture «in the wild» permette di **alterare in tempo reale** pose ed espressioni di un volto: è la tecnologia con cui un attaccante può rispondere a una challenge (Lezione 17, §7).

<!-- SOURCE_VISUAL id="TAB19-V33" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="113" type="sequenza di immagini" description="Shape from motion sul volto con una camera 2D" reason="Ricostruzione visibile solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **9.3 FaceTec ZoOm**

_(slide pp. 117–124)_

**Il principio** (p. 118): **avvicinare il telefono** al volto. **Non serve** ruotare la testa per ricostruire il volto da orecchio a orecchio: la **zona centrale** del volto ha i **dettagli 3D** più utili.

> 💡 **Perché basta avvicinare il telefono.** Avvicinandosi, la prospettiva cambia: il naso si ingrandisce molto più delle orecchie (Lezione 11, §8.3, e Lezione 12, Parte A). Una foto piatta, invece, si ingrandisce **uniformemente**. Dal diverso ingrandimento delle parti del volto si ricava la profondità.

**Affermazioni del produttore, senza test indipendenti** (p. 119):

- «le 3D FaceMap di ZoOm sono create con **normali camere 2D**, ma contengono **molti più dati** di una foto appiattita»;
- «ZoOm usa lo stesso hardware diffuso per **superare in ogni modo** il matching 2D».

**3D FaceMap** (p. 120): con **un video di 2 secondi** si ottengono tre risultati: **verifica di liveness**, **superficie 3D** e **riconoscimento 3D** del volto.

**Antispoofing dichiarato** (p. 121). Il produttore afferma di aver effettuato **centinaia di milioni** di tentativi di spoofing con ogni tipo di supporto:

- foto su carta e immagini digitali;
- video ad alta risoluzione;
- **sostituzione dell'immagine dopo il controllo di liveness**;
- maschere di carta con fori per occhi e bocca;
- maschere «hollywoodiane», statue di cera, bambole realistiche;
- foto o frame animati in **avatar**;
- **proiezioni video su teste 3D**;
- utenti **addormentati** con gli occhi chiusi;
- impostori, sosia, doppelgänger.

La demo di liveness (p. 122) **non è certificata** per questo tipo di attacchi (**livello 3**). Il confronto tra 2D, 3D e FaceTec della p. 123 è **fornito dal produttore**. Secondo la p. 124, ZoOm supera i test PAD di **livello 1 e 2** di un laboratorio NIST/NVLAP con **«0% FAR»**.

> ⚠️ **Come leggere «0% FAR».** Nei test PAD la metrica giusta è l'**APCER**, non il FAR (§5.1). Inoltre **zero errori osservati** non significa tasso nullo: con la **regola del 3** (Lezione 9), se su $n$ attacchi indipendenti non ne passa nessuno, il limite superiore al 95% del tasso vero è circa
> $$APCER_{95\%} \approx \frac{3}{n}$$
> Con 150 attacchi il risultato è compatibile con un APCER fino al 2%. Senza conoscere $n$, i tipi di strumento d'attacco e il BPCER corrispondente, lo «0%» non è confrontabile (Lezione 10).

<!-- SOURCE_VISUAL id="TAB19-V34" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="123" type="tabella grafica" description="Confronto fra 2D, 3D e FaceTec fornito dal produttore" reason="Tabella presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB19-V35" source="Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf" page="124" type="tabella grafica" description="Livelli ISO di antispoofing e risultato ZoOm ai test PAD di livello 1 e 2" reason="Livelli e risultati presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **9.4 BioID**

_(slide pp. 125–128)_

**La società** (p. 126). **BioID GmbH**, tedesca, attiva da **20 anni**, specializzata in:

- **autenticazione biometrica** e **riconoscimento facciale**;
- **liveness detection** (PAD);
- **onboarding remoto** ed **eKYC** (*electronic Know Your Customer*).

**L'offerta**:

- sistema di liveness e riconoscimento facciale **3D da cellulare con sensore 2D** (BioID Web Service);
- **primo brevetto** nel **2004** (labbra più voce, tema della prossima lezione);
- funziona con camere di **produttori e formati diversi**;
- tecnologia dichiarata **conforme al GDPR**, anche se conviene **verificare su quali aspetti**;
- la società dichiara che il sistema si basa su **flusso ottico**, **analisi delle texture** con IA e **CNN** addestrate con moltissimi esempi di attacchi e volti reali.

**Liveness detection di livello 3** (pp. 127–128). Un video mostra il modulo **PAD conforme alla ISO/IEC 30107** che rileva attacchi con **maschere in silicone** tramite **liveness passiva**, **senza hardware dedicato**.

---

### **10. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Framework classico** / **moderno** | attacchi al sistema e agli ingressi / anche al modello e ai dati di addestramento |
| **Adversarial attack** / **presentation attack** | perturbazione impercettibile del campione digitale o fisico contro il modello / artefatto presentato al sensore |
| **Addestramento** / **attacco con gradiente** | pesi aggiornati contro il gradiente / pixel aggiornati lungo il gradiente |
| **White** / **gray** / **black box** | parametri noti / alcune informazioni (fine-tuning) / nessun parametro, trasferibilità |
| **Non-targeted** / **targeted** | un errore qualsiasi / una classe o identità precisa |
| **Evasione** / **avvelenamento** | modifica l'input in deployment / modifica i dati di training, backdoor |
| **Estrazione** / **inferenza** | ruba il modello con query / ricava informazioni sui dati di training |
| **Addestramento avversario** / **regolarizzazione** | esempi avversari nel training / weight decay, dropout, batch normalization |
| **Zero-effort** / **non-zero effort** | impostori normali, FMR/FNMR / artefatti, APCER/BPCER |
| **APCER** / **BPCER** | attacchi accettati come genuini (sicurezza) / genuini rifiutati come attacchi (usabilità) |
| **PAD** / **PAI** | rilevamento dell'attacco / strumento usato per l'attacco |
| **Impostore** / **identity concealer** | vuole essere riconosciuto come un altro / vuole non essere riconosciuto |
| **Liveness detection** / **PAD** | segni di vita / tutti gli attacchi, sottoinsieme contro insieme |
| **Attacco diretto** / **indiretto** | al sensore, facile / dentro il sistema, richiede accesso |
| **Foto o schermo** / **volto vero** (flusso ottico) | flusso rigido e omogeneo / flusso non uniforme e deformabile |
| **3D con sensore dedicato** / **shape from motion** | luce strutturata o stereo / profondità dal movimento con camera 2D |
| **0 errori osservati** / **tasso zero** | risultato di un test finito / limite superiore circa $3/n$ |

---

### **11. Sintesi della lezione**

_(slide p. 129)_

> ✅ **In sintesi.**
> - **Vettori di attacco**: framework classico (sistema e ingressi, sette famiglie di vettori) e moderno (anche modelli e dati di addestramento).
> - **Adversarial attack**: perturbazioni impercettibili che sfruttano non linearità, gradiente e trasferibilità; white, gray e black box; attacchi targeted e non-targeted, anche fisici (CAMOU, RP2, abbigliamento, CV Dazzle); quattro tipi (evasione, avvelenamento, estrazione, inferenza); difese con intelligence, addestramento avversario e regolarizzazione.
> - **Presentation attack**: zero-effort contro non-zero effort; APCER e BPCER; definizioni ISO/IEC 30107-1 (PA, PAD, PAI, bona fide); impostori, occultatori, presentazioni non conformi, occlusioni; liveness come sottoinsieme del PAD; casi reali e classificazione degli attacchi.
> - **Antispoofing generale**: infrarosso, VIS+NIR+3D, istogrammi e moiré, shape from motion, flusso ottico, alto frame rate, challenge-response; test secondo ISO/IEC 30107-3.
> - **Volto 2D**: reti deep generaliste, battito delle palpebre con flusso ottico, maschere con fori, rPPG.
> - **Volto 3D**: sensori a luce strutturata (Face ID) e RealSense, spoofing di Bkav, spettrometria e termografia, FaceTec ZoOm (affermazioni del produttore da leggere con la regola del 3) e BioID con liveness passiva.
