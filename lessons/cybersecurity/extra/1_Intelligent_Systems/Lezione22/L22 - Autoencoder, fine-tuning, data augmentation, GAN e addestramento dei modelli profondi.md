## **Lezione 22: Autoencoder, transfer learning e addestramento dei modelli profondi — fine-tuning, estrazione delle feature, data augmentation, GAN e progettazione del ML assistita dagli LLM**

Il problema di partenza: **i modelli profondi hanno più di 100 milioni di parametri, le etichette sono poche e l’addestramento è difficile**. La lezione risponde in cinque passi. **Feature senza etichette**: gli **autoencoder**, dalla compressione al denoising fino al **rilevamento di anomalie** industriali e ai masked autoencoder. **Addestrare reti profonde**: il gradiente che svanisce, la **ricetta moderna** (ReLU, inizializzazione, BatchNorm, connessioni residue, Adam, regolarizzazione) e il **greedy layer-wise training** come soluzione storica la cui idea sopravvive nel pre-addestramento self-supervised. **Riusare la conoscenza**: **transfer learning**, **fine-tuning** in tre opzioni, **LoRA**, le CNN come estrattori di feature, l’**oblio catastrofico** e le sue mitigazioni (rehearsal, EWC, distillazione). **Più dati**: **data augmentation** classica, MixUp/CutMix, politiche casuali, Copy-Paste, augmentation generativa e apprendimento contrastivo; **GAN** e **modelli di diffusione**, fino al **model autophagy disorder** (MAD). **I confini**: le **scaling laws** e il ruolo di **LLM e agenti** nella progettazione del ML.

> **Fonte.** La lezione segue il PDF dell’edizione 2026/27 `sources_2026-27/L22-web.pdf` (102 pagine). I segnaposto per le figure indicano la pagina del PDF. Il PDF dell’edizione precedente è conservato nella cartella.


### **0. Panoramica**


- **il problema**: i modelli profondi hanno > 100M parametri, poche etichette, addestramento difficile;
- **feature senza etichette**: gli autoencoder, dalla compressione al rilevamento di anomalie;
- **addestrare reti profonde**: gradiente che svanisce, ricetta moderna, greedy layer-wise training;
- **riusare la conoscenza**: transfer learning, fine-tuning, LoRA, CNN come estrattori di feature, oblio catastrofico;
- **più dati**: data augmentation, GAN, modelli di diffusione, model autophagy disorder;
- **confini**: scaling laws, LLM e agenti nella progettazione del ML.

![](imgs/Pasted%20image%2020261125133300.png)

---

### **1. Modelli profondi non supervisionati: gli autoencoder**


Lavorare in addestramento senza etichette.

#### **1.1. L’apprendimento non supervisionato**

- **Può usare dati non etichettati** (illimitati).
- **Può essere rifinito con tecniche supervisionate standard** (per esempio la backpropagation, come una normale rete feed-forward).
- **Utile quando le etichette sono poche** → dataset parzialmente supervisionato (**semi-supervisionato**).

![](imgs/Pasted%20image%2020261125133331.png)

Richiamo dalla Lezione 19: il **k-means** raggruppa i campioni senza etichette; **qui è una rete a imparare la struttura**.

> 💡 **Perché poche etichette bastano (nota didattica).** Le immagini non etichettate mostrano **dove** stanno i dati (i due gruppi); le due etichette dicono soltanto **quale gruppo è quale**. Una frontiera tracciata con le sole due immagini etichettate potrebbe passare ovunque; usando anche le non etichettate passa nella zona vuota tra i gruppi. È l’ipotesi di base del semi-supervisionato: la frontiera giusta attraversa le regioni a bassa densità.

#### **1.2. L’apprendimento non supervisionato per modelli classici e profondi**

- **Idea principale**: senza etichette, i metodi cercano di **modellare la distribuzione dei dati d’ingresso** (per esempio i cluster), misurando l’**errore di ricostruzione** o la **log-verosimiglianza** dei dati.
- **Modelli**: di base, **PCA, k-means** (Lezioni 18, 19); **ICA**; **autoencoder denoising e sparsi**; **sparse coding**…

| | Superficiale | Profondo |
|---|---|---|
| **supervisionato** | SVM, regressione logistica, perceptron | rete neurale profonda, CNN, rete ricorrente |
| **non supervisionato** | denoising AE, sparse coding* | deep (stacked) denoising AE*, sparse coding gerarchico* |

(* esiste una versione supervisionata.)

![](imgs/Pasted%20image%2020261125133347.png)

#### **1.3. Obiettivo: addestrare in modo efficace gli strati nascosti**

Addestrare gli strati nascosti è un **compito difficile**. **Soluzione: gli autoencoder e il greedy layer-wise training.** Adattato da © Tony Martinez.

![](imgs/Pasted%20image%2020261125133401.png)

#### **1.4. Gli autoencoder (AE): la tecnica di base**

- Cercano di **creare feature generiche dai dati** (*encoding*):
  - **imparare la funzione identità** (uscita = ingresso) **imparando sotto-feature importanti**;
  - compressione, ecc.
- Si possono usare **le nuove feature da sole** nel nuovo training set, **o concatenarle** alle originali.
- Questa regola **addestra un solo strato**: **uno strato nascosto compatta già l’informazione in un solo passo** ($d\to m\to d$), il mattone di ciò che segue.

L’addestramento mira a $X=X'$, **con la backpropagation come in una rete feed-forward**.

![](imgs/Pasted%20image%2020261125133416.png)

#### **1.5. Gli autoencoder: la tecnica di base, $\hat{x}=x$**

> 📌 **Definizione.** Un **autoencoder** è una rete neurale addestrata a **riprodurre il proprio ingresso in uscita** attraverso uno **strato nascosto più stretto** (il **codice** o **collo di bottiglia**, *bottleneck*); l’**encoder** porta $x$ nel codice $z$, il **decoder** riporta $z$ a $\hat{x}\approx x$.

**Input**: $x\in\mathbb{R}^d$, $d$ feature (qui $d=5$), **nessuna etichetta**. **Output**: $\hat{x}\in\mathbb{R}^d$ e il codice $z\in\mathbb{R}^m$, $m<d$ (qui $m=2$).

**Errore di addestramento** (errore quadratico medio):

$$E=\frac1d\sum_{i=1}^{d}(x_i-\hat{x}_i)^2.$$

![](imgs/Pasted%20image%2020261125133430.png)

**Pochi numeri bastano**: anche uno strato nascosto ($5\to 2\to 5$) compatta l’informazione in un solo passo. **Esempio**, $d=2$: $x=(0{,}8;\ 0{,}2)$, $\hat{x}=(0{,}7;\ 0{,}4)$: $E=\frac12(0{,}1^2+0{,}2^2)=\frac12(0{,}01+0{,}04)=0{,}025$.

> 💡 **Perché il collo di bottiglia (nota didattica).** Se il codice avesse tante unità quanti ingressi ($m=d$), la rete potrebbe imparare a copiare l’ingresso senza scoprire nulla. Con $m<d$ è costretta a **comprimere**: deve trovare le poche direzioni che bastano a ricostruire i dati, come la PCA (Lezione 18), ma con funzioni non lineari. Il codice $z$ è un **embedding** appreso senza etichette.

#### **1.6. Autoencoder: ingresso, codice e uscita (con i numeri)**

**Input** $x\in\mathbb{R}^{64}$ (immagine $8\times 8$, valori in $[0,1]$) → **encoder** $64\to 32\to 8$ → **codice** $z\in\mathbb{R}^8$ → **decoder** $8\to 32\to 64$ → **output** $\hat{x}\in\mathbb{R}^{64}$ (ricostruzione).

- Ingresso: $(0{,}00;\ 0{,}00;\ 0{,}62;\ 0{,}94;\ 0{,}88;\ 0{,}25;\ \dots)$.
- Codice: $(-0{,}56;\ -0{,}47;\ -0{,}13;\ -0{,}05;\ 0{,}58;\ 0{,}18;\ 1{,}02;\ -0{,}03)$.
- Uscita: $(0{,}00;\ 0{,}05;\ 0{,}54;\ 0{,}69;\ 0{,}64;\ 0{,}21;\ \dots)$.

![](imgs/Pasted%20image%2020261125133443.png)

- **Nessuna etichetta necessaria**: **l’obiettivo della rete è il suo stesso ingresso**.
- **Errore di addestramento**: $E=\frac{1}{64}\sum_{i=1}^{64}(x_i-\hat{x}_i)^2$, con $x_i$ il pixel $i$ dell’ingresso e $\hat{x}_i$ lo stesso pixel in uscita.
- **Compressione**: $64\to 8$ numeri, cioè **8 volte meno valori**; il codice $z$ è il **vettore di feature automatico** (un **embedding**, Lezione 18).

Nelle slide successive si chiama $d$ la dimensione d’ingresso e $m$ la dimensione del codice $z$ (qui $d=64$, $m=8$).

#### **1.7. Applicazioni degli AE: riduzione della dimensionalità**

**Esempio da MNIST** (cifre in bianco e nero $28\times 28$): dalle $d=784$ dimensioni originali a un **codice di $m=2$ numeri** ($z\in\mathbb{R}^2$). **Nota**: la PCA è un metodo non supervisionato, **come gli AE**.

![](imgs/Pasted%20image%2020261125133452.png)

> 💡 **AE lineare = PCA (nota didattica).** Un autoencoder con un solo strato nascosto, attivazioni lineari e errore quadratico impara lo **stesso sottospazio** delle prime $m$ componenti principali (non necessariamente gli stessi assi). Con attivazioni non lineari e più strati può invece «piegare» lo spazio: per questo sulle cifre i gruppi del codice 2D sono di solito più separati di quelli della PCA a 2 componenti (34% della varianza, Lezione 18, §4.4).

#### **1.8. Quanto deve essere grande il codice $z$ ($m$)?**

**Input**: 8 immagini, 64 pixel ciascuna. **Output**: ricostruzioni da codici di $m=2,8,16$ numeri.

| $m$ (unità nel collo di bottiglia) | 2 | 8 | 16 |
|---|---:|---:|---:|
| errore medio di ricostruzione $E$ | 0,053 | 0,026 | 0,014 |

![](imgs/Pasted%20image%2020261125133504.png)

> 📌 **La dimensione del codice $m$ è una scelta di progetto**: $m$ **piccolo** = **compressione forte, uscita sfocata**; $m$ **grande** = **uscita fedele, meno compressione**.

> 💡 **Un compromesso a rendimenti decrescenti (nota didattica).** Da 2 a 8 l’errore si dimezza; da 8 a 16 scende di un altro 46%, ma con il doppio delle unità. È lo stesso gomito della varianza cumulativa della PCA (Lezione 18, §3.12). Con $m=64$ l’errore potrebbe arrivare a zero, ma il codice non comprimerebbe più nulla.

#### **1.9. Capire gli AE: il decoder da solo**

- Dopo l’addestramento **si può usare solo il decoder**.
- Si provano diversi **vettori 2D d’ingresso**: l’ingresso $[20,20]$ produce l’immagine di un «**7**».

**Si osservi l’interessante continuità dello spazio latente!**

![](imgs/Pasted%20image%2020261125133524.png)

> ⚠️ **La continuità non è garantita (nota didattica).** In un autoencoder **semplice** la continuità dello spazio latente **non è garantita**: tra due gruppi possono esserci zone vuote, e il decoder produce immagini senza senso. Gli **autoencoder variazionali** (VAE) aggiungono un termine che regolarizza lo spazio latente proprio per renderlo continuo e campionabile, e sono il ponte verso i modelli generativi del §6.

#### **1.10. Applicazioni degli AE: la riduzione del rumore**

**Denoising AE**: addestrato con un **ingresso rumoroso $\tilde{x}$** e il **bersaglio pulito $x$**. Pixel mancanti, rumore, barre.

![](imgs/Pasted%20image%2020261125133537.png)

> 💡 **Perché impara qualcosa di utile (nota didattica).** Un autoencoder normale potrebbe limitarsi a copiare; un denoising AE non può, perché l’ingresso è diverso dal bersaglio. Per togliere il rumore deve sapere che aspetto hanno le immagini «vere», cioè imparare la struttura dei dati. È anche il principio dei modelli di diffusione (§6.8), che tolgono il rumore un poco alla volta.

#### **1.11. Applicazioni degli AE: la colorazione automatica**

**Input**: immagine grigia (1 canale). **Output**: immagine a colori (3 canali).

![](imgs/Pasted%20image%2020261125133548.png)

#### **1.12. Autoencoder per il rilevamento di anomalie (industria)**

Richiamo dalla Lezione 19: **anomalia = lontano dai dati normali**; punteggio $s(x)$, soglia $\tau$. **Qui il punteggio è un errore di ricostruzione.**

**Input** $x$ (finestra di vibrazione, 64 campioni a 1 kHz) → **autoencoder addestrato solo su pezzi buoni** → $\hat{x}$ → **punteggio**

$$s(x)=\frac{1}{64}\sum_i(x_i-\hat{x}_i)^2$$

→ **confronto** $s(x)>\tau$? → buono / difettoso.

![](imgs/Pasted%20image%2020261125133652.png)

- **L’AE ha visto solo pezzi buoni**: li ricostruisce bene e **ricostruisce male un difetto**, quindi $s(x)$ è grande **proprio quando appare qualcosa di nuovo**.
- **Per l’addestramento non servono esempi difettosi**: utile quando i difetti sono **rari** (prodotto buono ≫ prodotto difettoso).
- $\tau$ si fissa su **pezzi buoni di validazione**, per esempio il **98° percentile** dei loro punteggi (come nella Lezione 19).

#### **1.13. Rilevamento di anomalie con AE: un esperimento su segnali di vibrazione**

![](imgs/Pasted%20image%2020261125133715.png)

- **Dati**: buono = 50 Hz + 150 Hz + rumore; **difettoso** = buono + **piccoli impatti ogni 16 ms** (difetto del cuscinetto).
- **AE $64\to 16\to 4\to 16\to 64$**, addestrato su **1500 finestre buone**.
- **Punteggio su 500 + 500 finestre di test**; $\tau=0{,}021$ (98° percentile di 500 finestre buone di validazione).
- **Falsi allarmi** su 500 finestre buone di test: **1,2%**; **finestre difettose rilevate: 97,4%**.

Dati sintetici (seme fisso): solo geometria, nessuna misura reale.

> 💡 **Leggere i numeri (nota didattica).** Con il 98° percentile ci si aspetta circa il 2% di falsi allarmi sui buoni; il test ne dà l’1,2% (6 finestre su 500), compatibile con la variabilità di un campione di 500. Il 97,4% di rilevamento (487 su 500) è la **recall** sui difettosi, il 98,8% di buoni accettati la **specificità** (Lezione 16). Un impatto ogni 16 ms in una finestra di 64 ms significa 4 impatti per finestra: un segnale che l’AE, addestrato solo su sinusoidi, non sa ricostruire. Abbassare $\tau$ aumenterebbe il rilevamento e i falsi allarmi.

#### **1.14. I masked autoencoder (MAE)**

> 📌 **Definizione.** I **masked autoencoder** (MAE) sono una classe **molto scalabile** di modelli profondi **self-supervised** («auto-apprendimento»), usati soprattutto nella visione artificiale: **la maggior parte delle patch dell’immagine d’ingresso viene nascosta** e la rete è addestrata a **ricostruirle**, così impara da immagini non etichettate.

1. **Input**: l’immagine viene tagliata in **patch** e **la maggior parte viene nascosta** (grigio).
2. **L’encoder vede solo le patch visibili** e dà un codice (blu) per ciascuna.
3. **Token maschera grigi** prendono il posto delle patch nascoste.
4. **Il decoder produce tutte le patch** (rosso); l’addestramento le confronta con il bersaglio, **l’immagine originale**.

![](imgs/Pasted%20image%2020261125133725.png)

> 💡 **Perché è scalabile (nota didattica).** Nell’articolo originale (He et al., 2021) si nasconde il **75%** delle patch: l’encoder lavora solo sul 25% visibile, ed è quattro volte più veloce. Ricostruire tre quarti di un’immagine da un quarto obbliga la rete a capire la scena (forme, oggetti), non a copiare pixel vicini. È la stessa idea del *masked language modeling* di BERT (Lezione 13) applicata alle immagini.

---

### **2. Addestrare e progettare modelli di deep learning**


Affrontare la complessità di > 100M parametri.

#### **2.1. Prima di tutto… il progetto del deep learning**

Richiamo dalla Lezione 21: **CNN-A e CNN-B**: convoluzione, pooling, testa densa.

- Mentre **tutti i pesi vengono addestrati**, **la struttura (topologia) della CNN è di solito progettata a mano per tentativi**:
  - numero totale di strati;
  - tipi di kernel;
  - dimensioni dei kernel;
  - dimensione dei campi di sottocampionamento (pooling).
- Tipicamente, **dagli ingressi alle uscite**:
  - **diminuire la dimensione dei kernel**;
  - **aumentare il numero di feature map**.

![](imgs/Pasted%20image%2020261125133749.png)

> ⚠️ **Kernel o feature map? (nota didattica).** La Lezione 21 (§5.13) formula la regola come «diminuire la dimensione **delle mappe** e aumentarne il numero»; qui si parla di **kernel** più piccoli. Le due letture convivono: nelle reti storiche (AlexNet) i kernel scendevano da $11\times 11$ a $3\times 3$; nelle reti moderne i kernel sono quasi sempre $3\times 3$ e a diminuire è la dimensione spaziale delle mappe, mentre il numero di canali cresce.

#### **2.2. Problemi nell’addestramento delle reti profonde**

- **I primi strati di un MLP non vengono addestrati bene**:
  - **diffusione del gradiente**: l’errore **si attenua** mentre si propaga verso gli strati iniziali (Lezione 20, §6.14);
  - porta a un **addestramento molto lento**;
  - peggiorato dal fatto che **gli ultimi due strati di solito possono imparare qualsiasi compito «abbastanza bene»**, quindi l’errore verso gli strati iniziali cala in fretta mentre gli strati alti «per lo più» risolvono il compito.
- **Gli strati bassi non hanno mai l’opportunità di usare la loro capacità** per migliorare i risultati: **calcolano solo una feature map casuale**.

![](imgs/Pasted%20image%2020261125133757.png)

#### **2.3. Suggerimenti per l’addestramento del deep learning**

Addestrati con la **backpropagation** (BP) (Lezione 20):

- usare la **condivisione dei pesi** (*weight sharing*) in ogni feature map;
- **pesi iniziali casuali** in tutta la rete;
- **usare modelli pre-addestrati**.

**«Weight sharing»** (Lezione 21): **lo stesso kernel convolutivo è riusato in ogni posizione** dell’immagine. **Vantaggi**: **molti meno parametri addestrabili**, **feature map equivarianti alla traslazione**, **meno overfitting**.

![](imgs/Pasted%20image%2020261125133807.png)

Stesso colore = stesso peso: **un kernel** $(w_1,w_2,w_3)$ **in ogni posizione**. **Completamente connesso $7\to 5$: 35 pesi. Con il weight sharing: solo 3.**

> 💡 **Il conto (nota didattica).** Con un kernel di 3 su 7 ingressi le posizioni sono $7-3+1=5$ (la formula della Lezione 21, §3.10), quindi 5 uscite. Uno strato denso collegherebbe ognuna delle 5 uscite a tutti i 7 ingressi: $5\cdot 7=35$ pesi. La convoluzione ne usa 3, riusati 5 volte.

#### **2.4. La ricetta moderna: connessioni residue, addestramento end-to-end**

| Ingrediente | Problema che risolve |
|---|---|
| **attivazione di tipo ReLU** | il gradiente **non si riduce** per le unità attive |
| **inizializzazione accurata** (Xavier, He) | la dimensione del segnale **resta stabile strato dopo strato** |
| **BatchNorm / LayerNorm** | attivazioni mantenute in un **intervallo stabile** |
| **connessione residua** | **percorso diretto per il gradiente**, reti molto profonde |
| **ottimizzatore Adam** | passo **adattato a ogni parametro** |
| **regolarizzazione** (sotto) | **meno overfitting** di > 100M parametri |
| **pre-addestramento self-supervised** | usa dati non etichettati **su tutta la rete**, non strato per strato |

![](imgs/Pasted%20image%2020261125133823.png)

**Blocco residuo**: $y=x+F(x)$, con $F(x)$ fatto di 2 strati (conv, norm, ReLU) e la **scorciatoia** (*skip*): **il gradiente ha un percorso diretto attraverso la scorciatoia** (Lezione 21, §5.5).

#### **2.5. La Batch Normalization (BatchNorm)**

> 📌 **Definizione.** La **Batch Normalization** riscala, **per ogni unità e ogni mini-batch**, le attivazioni a **media 0 e varianza 1**, poi lascia che la rete le riscali con **due parametri appresi $\gamma,\beta$**.

**Input**: le attivazioni $x_1,\dots,x_B$ di un’unità per le $B$ immagini di un mini-batch (per esempio $B=32$). **Output**: $y_1,\dots,y_B$, stessa forma.

$$\hat{x}_i=\frac{x_i-\mu_B}{\sqrt{\sigma_B^2+\epsilon}},\qquad y_i=\gamma\,\hat{x}_i+\beta.$$

$\mu_B,\sigma_B^2$: media e varianza degli $x_i$ sul batch; $\epsilon$: costante minuscola (per esempio $10^{-5}$) che **evita di dividere per 0**; $\gamma,\beta$: **scala e traslazione apprese** (la rete può annullare la normalizzazione se le conviene).

**Esempio**: $x=(2,4,6)$: $\mu_B=4$, $\sigma_B\approx 1{,}6$, quindi $\hat{x}\approx(-1{,}2;\ 0;\ +1{,}2)$.

![](imgs/Pasted%20image%2020261125134952.png)

- **A che cosa serve**: mantiene le attivazioni di ogni strato in un **intervallo stabile**: il gradiente **né svanisce né esplode**, l’addestramento è **più veloce** e **tollera learning rate più grandi**.
- **Dove**: **dopo una convoluzione o uno strato lineare, prima dell’attivazione**. La **LayerNorm** (statistiche sulle feature di **un campione**) si usa quando i **batch sono piccoli**, per esempio nei **Transformer**.

> 💡 **I conti e l’inferenza (nota didattica).** $\sigma_B^2=\frac{(2-4)^2+0+(6-4)^2}{3}=\frac83$, $\sigma_B=1{,}63$, $\hat{x}=(-2/1{,}63;\ 0;\ 2/1{,}63)=(-1{,}22;\ 0;\ 1{,}22)$. In fase d’uso non c’è un batch: la BatchNorm usa la media e la varianza **accumulate durante l’addestramento** (medie mobili). È il motivo del `training=False` nel transfer learning della Lezione 21 (§5.9), ed è lo z-score della Lezione 10 applicato dentro la rete.

#### **2.6. L’ottimizzatore Adam (*Adaptive Moment Estimation*)**

> 📌 **Definizione.** **Adam** è un ottimizzatore: **aggiorna ogni peso con un passo adattato a quel peso**, usando una **media mobile del suo gradiente** (direzione) e del **suo gradiente al quadrato** (dimensione).

**Input**: il gradiente $g_t=\partial E/\partial w$ di ogni peso all’iterazione $t$. **Output**: il peso aggiornato $w_{t+1}$.

| Classico (lezioni precedenti) | Adam |
|---|---|
| $w_{t+1}=w_t-\alpha\,g_t$ | $w_{t+1}=w_t-\alpha\,\dfrac{\hat{m}_t}{\sqrt{\hat{v}_t}+\epsilon}$ |

con $m_t=\beta_1m_{t-1}+(1-\beta_1)g_t$ e $v_t=\beta_2v_{t-1}+(1-\beta_2)g_t^2$.

- $m_t$: **media mobile dei gradienti** (direzione, con inerzia); $v_t$: **media mobile dei gradienti al quadrato** (quanto il peso oscilla); $\hat{m}_t,\hat{v}_t$: gli stessi, **corretti per le prime iterazioni** (partono da 0).
- $\beta_1\approx 0{,}9$, $\beta_2\approx 0{,}999$: **memoria delle medie**; $\alpha$: learning rate (per esempio $10^{-3}$); $\epsilon\approx 10^{-8}$: evita di dividere per 0.
- **Esempio**: gradienti **grandi e rumorosi** ($\pm 10$) danno un $\hat{v}$ grande: **passi piccoli**; gradienti **piccoli e costanti** (0,01) danno un $\hat{v}$ piccolo: **passi relativamente più grandi**.

**Quando è utile**: reti profonde, **gradienti rumorosi dei mini-batch**, pesi con **dimensioni del gradiente molto diverse**, fine-tuning, **buoni primi risultati con poca regolazione**. La **discesa del gradiente con momentum** si usa ancora e **può generalizzare leggermente meglio sulle CNN** se ben regolata.

> 💡 **Due conti (nota didattica).** La correzione è $\hat{m}_t=m_t/(1-\beta_1^t)$: al primo passo $m_1=0{,}1\,g_1$ e $\hat{m}_1=0{,}1\,g_1/0{,}1=g_1$, senza la correzione il primo passo sarebbe 10 volte troppo piccolo. Con un gradiente costante $g$, a regime $\hat{m}\approx g$ e $\hat{v}\approx g^2$, quindi il passo è $\alpha\,g/|g|=\alpha$: **il passo di Adam è circa $\alpha$ qualunque sia la scala del gradiente**, ed è per questo che lo stesso $10^{-3}$ funziona su reti molto diverse.

#### **2.7. Regolarizzazione: early stopping, weight decay, dropout**

> 📌 **Definizione.** La **regolarizzazione** è **qualsiasi modifica di un algoritmo di apprendimento** intesa a **ridurne l’errore di generalizzazione ma non l’errore di training**. I. Goodfellow, Y. Bengio, A. Courville, *Deep Learning*, MIT Press, 2016, cap. 7.

- **Early stopping**: fermare l’addestramento all’**epoca con l’errore di validazione più basso** e tenere quei pesi (Lezione 21: la curva di validazione).
- **Weight decay**: minimizzare $E'=E+\lambda\sum_iw_i^2$; $\lambda\ge 0$ fissa **quanto vengono penalizzati i pesi grandi**.
- **Dropout**: a ogni passo di addestramento **ogni unità nascosta viene spenta con probabilità $p$** (per esempio $p=0{,}5$); **in test si usano tutte le unità**.

![](imgs/Pasted%20image%2020261125135348.png)

**Dropout, un passo di addestramento**: le unità grigie sono spente, **la rete non può contare su nessuna singola unità**.

> 💡 **Il weight decay in una riga (nota didattica).** Il gradiente di $\lambda w^2$ è $2\lambda w$, quindi a ogni passo il peso si riduce di un fattore $(1-2\alpha\lambda)$ prima della correzione dell’errore: i pesi che non servono «decadono» verso zero. È la regolarizzazione L2 della Lezione 10 (§8) e della Lezione 18 (§1.2.6). Tutte e tre le tecniche agiscono sulla **varianza** del dilemma bias-varianza (Lezione 17).

#### **2.8. Perché ora tutti gli strati si possono addestrare insieme**

> 📌 **Definizione.** L’**addestramento end-to-end** ottimizza **tutti i pesi della rete contemporaneamente**, con **una loss calcolata all’uscita** e retropropagata fino al primo strato.

| Ostacolo | Soluzione vista in questa e nelle lezioni precedenti |
|---|---|
| il gradiente svanisce prima dello strato 1 | ReLU, inizializzazione He, BatchNorm, connessioni residue |
| troppo pochi esempi etichettati | ImageNet (1,2 M immagini) + data augmentation |
| addestramento troppo lento | GPU A100: AlexNet da zero in **ore o pochi giorni** (2012: 5–6 giorni) |
| overfitting; gradienti a mano | dropout, weight decay, early stopping; **gradienti automatici** (PyTorch, Keras) |

**Output**: una rete profonda, **da zero** o **una pre-addestrata da affinare**.

#### **2.9. Reti per la visione addestrate end-to-end: quanto grandi?**

- **AlexNet** (2012): 8 strati, **60 M parametri, 650 000 neuroni**.
- **VGG-16** (2014): 16 strati, **138 M**; le varianti più profonde **erano ancora inizializzate da una rete più superficiale addestrata**.
- **ResNet-152** (2015): **152 strati, 60 M**, **addestrata direttamente** grazie a **connessioni residue + BatchNorm**.
- **Parametri ≠ neuroni**: un parametro è **un peso o un bias**; **un neurone ha molti pesi**.
- **Per un’applicazione**, **reti compatte** (MobileNetV3-Small: **2,5 M**) o **CNN ad hoc** spesso **bastano**.

![](imgs/Pasted%20image%2020261125140857.png)

Il grafico riporta anche LeNet-5 (7 strati), GoogLeNet (22 strati), EfficientNet-B0 (82 strati), ConvNeXt-T (18 blocchi), ViT-B/16 (12 blocchi) e una CNN ad hoc (4 blocchi conv), con la soglia in cui l’addestramento end-to-end diventa pratico.

> ⚠️ **Il numero dei parametri di AlexNet (nota didattica).** Qui si scrive «60 M», la cifra arrotondata dell’articolo originale; la Lezione 21 (§5.1) conta 62,4 M sulla versione a una sola torre. ResNet-152 ha meno parametri di VGG-16 pur avendo 10 volte più strati: la profondità non coincide con la dimensione.

---

### **3. Il greedy layer-wise training**


Una soluzione storica all’addestramento delle reti profonde: è ancora valida?

#### **3.1. Inizializzazione non supervisionata (tecnica di base)**

→ **esportare lo strato: addestrarlo come un autoencoder!**

![](imgs/Pasted%20image%2020261125141839.png)

#### **3.2. AE impilati (tecnica di base)**

- **Impilare molti autoencoder (sparsi) in successione** e addestrarli con il **greedy layer-wise training**.
- **Eliminare ogni volta lo strato d’uscita del decoder.**

![](imgs/Pasted%20image%2020261125141847.png)

- **Tenere i parametri del primo strato, addestrare il secondo.**
- Eliminare ogni volta lo strato d’uscita del decoder.

![](imgs/Pasted%20image%2020261125141858.png)

- **La regola d’oro è ridurre il numero di neuroni andando verso le uscite.**

![](imgs/Pasted%20image%2020261125141907.png)

- **Fare l’addestramento supervisionato sull’ultimo strato.** Si può fare perché si hanno **gli ingressi $x$ e i pesi degli AE** → **si possono calcolare gli ingressi dello strato finale** ($h_1,h_2,\dots$), e **le etichette $y$** dell’uscita.

![](imgs/Pasted%20image%2020261125141914.png)

- **Poi fare l’addestramento supervisionato dell’intera rete per affinare tutti i pesi.**

![](imgs/Pasted%20image%2020261125141921.png)

Adattato da © Tony Martinez.

#### **3.3. Il greedy layer-wise training in quattro immagini**

1. **AE 1**, bersaglio $=x$: si addestra $W_1$ (con un decoder usato solo in quel passo) → $h_1$.
2. **Congelare $W_1$, AE 2**, bersaglio $=h_1$: si addestra $W_2$ → $h_2$.
3. **Addestrare l’uscita**, etichetta $y$: si addestra $w_3$.
4. **Fine-tuning di tutto** $W_1,W_2,w_3$, con $\alpha$ piccolo.

![](imgs/Pasted%20image%2020261125141934.png)

- **I passi 1–2 non richiedono etichette**: ogni strato è addestrato come **l’encoder di un autoencoder**. $W_1$: $5\times 3$ pesi, $W_2$: $3\times 2$, $w_3$: $2\times 1$ (**regola d’oro**: meno unità andando avanti, $5\to 3\to 2\to 1$).
- **Il passo 3 usa le etichette $y$ solo per $w_3$**; il **passo 4 affina tutti i pesi con un learning rate piccolo $\alpha$**, partendo da una **buona inizializzazione**.

#### **3.4. Greedy layer-wise: riassunto (tecnica di base)**

1. **Addestrare il primo strato** usando i dati **senza le etichette** (non supervisionato).
2. Poi **congelare i parametri del primo strato** e addestrare il secondo strato usando **l’uscita del primo come ingresso non supervisionato** del secondo.
3. **Ripetere** per quanti strati si desidera.
4. **Usare le uscite dello strato finale** come ingressi di uno strato/modello supervisionato e **addestrare gli ultimi strati supervisionati** (lasciando congelati i primi pesi).
5. **Scongelare tutti i pesi e affinare l’intera rete** con un addestramento supervisionato, partendo dai pesi del pre-addestramento.

#### **3.5. Greedy layer-wise: perché aiuta (tecnica di base)**

- Il greedy layer-wise training **evita molti dei problemi** dell’addestramento supervisionato di una rete profonda:
  - **ogni strato riceve tutta l’attenzione dell’apprendimento a turno**, perché è l’unico strato «in cima» al momento;
  - **può sfruttare dati non etichettati**;
  - quando alla fine si affina l’intera rete con l’addestramento supervisionato, **i pesi sono già in un buon bacino d’errore** e richiedono solo un affinamento. Questo aiuta contro:
    - l’**apprendimento inefficace degli strati iniziali**;
    - i **minimi locali delle reti profonde**.

![](imgs/Pasted%20image%2020261125141949.png)

#### **3.6. Il greedy layer-wise training è ancora valido oggi?**

| 2006 | 2012 | 2015 | 2018–22 |
|---|---|---|---|
| pre-addestramento layer-wise (Hinton; Bengio) | end-to-end: ReLU, dropout, GPU (AlexNet) | BatchNorm, reti residue (152 strati) | pre-addestramento self-supervised dell’intera rete (BERT, MAE) |

![](imgs/Pasted%20image%2020261125142004.png)

> 📌 **La procedura: storica.** CNN e transformer standard **non si addestrano più strato per strato**: la ricetta moderna addestra **tutti gli strati insieme**. **L’idea: viva.** «**Pre-addestrare senza etichette, poi affinare con poche etichette**» è esattamente ciò che fanno oggi il **pre-addestramento self-supervised** e il **transfer learning**.

---

### **4. Transfer learning e fine-tuning nei modelli profondi**


Sfruttare addestramento e conoscenza precedenti.

#### **4.1. Il transfer learning per evitare l’overfitting e migliorare l’apprendimento**

Richiamo dalla Lezione 21: **una CNN pre-addestrata = backbone + testa; si riusa il backbone**. I **metodi di ML tradizionali** devono **ricominciare da capo** l’apprendimento ogni volta che cambiano il **dominio applicativo** o il **dataset**.

![](imgs/Pasted%20image%2020261125142042.png)

> 📌 **Definizione.** Il **transfer learning** migliora l’apprendimento di un **compito obiettivo** in un **dominio obiettivo** usando **la conoscenza appresa su un compito o dominio sorgente diverso**. http://ruder.io/transfer-learning/

![](imgs/Pasted%20image%2020261125142050.png)

#### **4.2. I modelli pre-addestrati sono disponibili online**

- Le grandi CNN **richiedono settimane di addestramento su più GPU** su dataset di immagini come ImageNet.
- Molti ricercatori **rilasciano la loro CNN finale** a beneficio di altri, che possono usarla per il **fine-tuning**.
- **Fonti principali (2026)**:
  - **Hugging Face Hub** (anche i modelli d’immagine `timm`);
  - **Keras Applications / KerasHub**;
  - **PyTorch `torchvision.models`**;
  - **Kaggle Models** (TensorFlow Hub vi è stato spostato nel 2023–24);
  - **ONNX Model Zoo** (ora ospitato su Hugging Face);
  - **reti pre-addestrate del Deep Learning Toolbox di MATLAB**.

> ⚠️ **Prima di riusare un modello, controllarne la licenza e i dati su cui è stato addestrato.** Un modello addestrato su dati non distribuibili o con una licenza non commerciale può non essere usabile in un prodotto; dati di addestramento sbilanciati si trasferiscono con il modello (Lezione 5, equità).

#### **4.3. Il fine-tuning: partire da una CNN pubblica**

![](imgs/Pasted%20image%2020261125142113.png)

> 📌 **Definizione.** Il **fine-tuning** **continua l’addestramento di un modello pre-addestrato sui dati del nuovo compito**, di solito con un **learning rate piccolo $\alpha$**.

- **Il fine-tuning è oggi uno dei flussi di lavoro pratici dominanti del deep learning.**
- **La stessa idea vale non solo per le CNN**, ma anche per **vision transformer, modelli multimodali, modelli del parlato e LLM**.

![](imgs/Pasted%20image%2020261125142120.png)

#### **4.4. Transfer learning: dalla CNN pre-addestrata al proprio compito**

**Input** $I(x,y)$ ($224\times 224\times 3$) → **backbone pre-addestrato, congelato** → **embedding** $z$ (4096 numeri) → **nuova testa addestrata** ($4096\to 5$) → $\hat{y}$: **5 punteggi** (ok, graffio, ammaccatura, crepa, macchia).

![](imgs/Pasted%20image%2020261125142129.png)

- **Parametri addestrati**: $4096\times 5+5=20\,485$ (pesi + bias); **congelati**: circa **60 milioni** (backbone di tipo AlexNet).
- È l’**opzione 1** dei passi successivi; le opzioni 2 e 3 **sbloccano anche gli strati più alti o tutti**, con un $\alpha$ piccolo.
- **Compito d’esempio**: **5 classi di difetti superficiali** con **qualche centinaio di immagini etichettate per classe**, invece di milioni.

#### **4.5. Il fine-tuning della CNN: tre opzioni**

Blocchi conv (da **generici** a **specifici**) → testa densa → $\hat{y}$; le barre indicano gli strati addestrati, il resto è congelato.

| Opzione | Parametri addestrati | Dati etichettati necessari | Rischio principale |
|---|---|---|---|
| **1) solo la nuova testa** | $\approx 10^4$ | **pochi** (centinaia) | feature **non abbastanza specifiche** |
| **2) strati alti + testa** | $\approx 10^6$–$10^7$ | **medi** (migliaia) | **overfitting** se i dati sono pochi |
| **3) tutti i pesi, $\alpha$ piccolo** | tutti ($\approx 10^7$–$10^8$) | **molti** | overfitting, **oblio catastrofico** |

![](imgs/Pasted%20image%2020261125142143.png)

> 📌 **Regola pratica**: **meno dati si hanno e più sono simili alla sorgente, meno strati si sbloccano.**

> 💡 **Perché «generici → specifici» (nota didattica).** I primi blocchi di una CNN imparano bordi e tessiture (Lezione 21, §2.12), utili per qualsiasi immagine; gli ultimi imparano parti di oggetti di ImageNet (occhi di cane, ruote). Per difetti superficiali su metallo, i blocchi alti di ImageNet sono i meno adatti: con abbastanza dati conviene sbloccarli (opzione 2). Con immagini molto diverse dalla sorgente (radiografie, spettrogrammi) a volte conviene sbloccare quasi tutto.

#### **4.6. Fine-tuning efficiente nei parametri: la Low-Rank Adaptation (LoRA)**

**Input** $x$ ($k=4096$ valori) → $W$ **congelata** ($d\times k$) **+** $B$ **addestrata** ($d\times r$) $\times$ $A$ **addestrata** ($r\times k$) → **output** $y$ ($d=4096$ valori).

> 📌 **Definizione.** **LoRA** (*low-rank adaptation*) **congela una matrice di pesi pre-addestrata $W$** e impara **solo un aggiornamento a basso rango $BA$**:
> $$y=Wx+BAx.$$
> $r$: il **rango**, piccolo (per esempio $r=8$). **Un modello base, molti piccoli moduli per i compiti.**

- **Strato completo**: $4096\cdot 4096=16\,777\,216$ parametri.
- **LoRA, $r=8$**: $2\cdot 4096\cdot 8=65\,536$.
- **Rapporto**: $65\,536/16\,777\,216\approx 0{,}4\%$.

![](imgs/Pasted%20image%2020261125142154.png)

**Nata per i modelli linguistici**, usata anche per i **vision transformer**; lasciare $W$ intatta **limita anche l’oblio catastrofico** (§4.11).

> 💡 **Perché «basso rango» (nota didattica).** $BA$ è una matrice $d\times k$ come $W$, ma costruita come prodotto di due matrici sottili: il suo rango è al massimo $r=8$. L’ipotesi è che l’adattamento a un compito nuovo richieda di cambiare i pesi solo in poche «direzioni». Di solito si inizializza $B=0$: all’inizio $BA=0$ e il modello parte esattamente da quello pre-addestrato. Per un nuovo compito si salvano solo $A$ e $B$ (256 KB invece di 64 MB per questo strato in float32).

#### **4.7. Estrazione automatica delle feature con i modelli profondi**

**Migliorare l’approccio della PCA usando il DL.**

**Le CNN come estrattori di feature (tecnica di base).**

1. **In una CNN pre-addestrata si rimuove l’ultimo strato completamente connesso**: per esempio le uscite di questo strato sono i **1000 punteggi di classe di un compito diverso**, come ImageNet.
2. Poi si tratta **il resto della CNN come un estrattore di feature fisso** per il nuovo dataset.
   - In un’**AlexNet** questo calcola un **vettore a 4096 dimensioni per ogni immagine**: le attivazioni dello strato nascosto **immediatamente prima del classificatore**.
   - **Nota**: controllare se queste feature sono «**ReLUed**» (sogliate a zero) se lo erano anche durante l’addestramento della CNN (come di solito accade).

![](imgs/Pasted%20image%2020261125142306.png)

Una volta estratti i **codici a 4096 dimensioni** per tutte le immagini, si addestra un **classificatore lineare** (per esempio **SVM lineare o classificatore softmax**) per il nuovo dataset (il proprio dataset di immagini).

![](imgs/Pasted%20image%2020261125142314.png)

> 💡 **Il dettaglio della ReLU (nota didattica).** Se durante l’addestramento la ReLU veniva applicata dopo fc7, il classificatore originale ha sempre visto valori $\ge 0$. Prendere le attivazioni **prima** della ReLU dà valori anche negativi: l’informazione è la stessa, ma un classificatore lineare addestrato su feature di un tipo e usato sull’altro sbaglia. La regola è usare le feature nella stessa forma in cui le vedeva la rete.

#### **4.8. Le CNN come estrattori: un embedding, molti usi**

La propria immagine $I(x,y)$ → **backbone pre-addestrato, congelato** → **embedding** $z\in\mathbb{R}^{4096}$ →

- **classificatore lineare** (SVM, softmax) → le proprie classi $\hat{y}$;
- **k-NN, coseno** → classe dell’immagine etichettata più vicina (Lezione 19);
- **distanza dal normale** $s(z)>\tau$? → anomalia / OK.

![](imgs/Pasted%20image%2020261125142323.png)

Richiamo dalla Lezione 18: un **embedding** è una **rappresentazione vettoriale appresa**; ingressi simili danno vettori vicini. **Qui il backbone non viene mai riaddestrato: si estrae una volta, si riusa molte volte.** Si addestra **solo il blocco leggero a destra** (**o nulla**, per il k-NN).

#### **4.9. Lo stato dell’arte dell’estrazione automatica delle feature**

Oggi lo stesso flusso si fa con **backbone più forti**:

- **CNN**: ResNet, EfficientNet, ConvNeXt;
- **Vision Transformer** (ViT);
- **backbone self-supervised**, addestrati senza etichette: **DINOv2 (2023), DINOv3 (2025)**;
- **encoder immagine–testo** (tipo CLIP);
- **modelli pre-addestrati specifici di dominio**.

![](imgs/Pasted%20image%2020261125142331.png)

> 💡 **Prospettiva**: i nomi cambiano ogni anno; **il principio** (backbone congelato → embedding → modello leggero) **resta**. Gli embedding di EmbeddingGemma 2 usati nelle Lezioni 18 e 19 sono un esempio di questo stesso principio.

#### **4.10. L’oblio catastrofico**

Dimenticare informazioni apprese in precedenza quando si imparano informazioni nuove. «**Che cosa sto dimenticando??**»

> 📌 **Definizione.** L’**oblio catastrofico** (*catastrophic forgetting*) si verifica quando **le reti neurali dimenticano compiti appresi in precedenza** dopo essere state addestrate su **dati nuovi** o aver subito un **fine-tuning** per compiti specifici. Definizione da IBM Think (ibm.com/think).

![](imgs/Pasted%20image%2020261125150404.png)

- È un fenomeno osservato nel ML, **specificamente nelle reti neurali**.
- **La capacità di ricordare informazioni apprese prima è gravemente compromessa quando la rete impara informazioni nuove.**
- Si verifica quando una rete è **addestrata in sequenza su compiti diversi**: mentre impara i nuovi compiti, **le prestazioni sui compiti precedenti peggiorano in modo significativo**.
- **Particolarmente rilevante** nel *continual learning*, nella **personalizzazione**, nell’**adattamento di dominio**, nel **fine-tuning di grandi modelli pre-addestrati**.

![](imgs/Pasted%20image%2020261125151017.png)

**L’oblio catastrofico misurato con l’accuratezza sul test set.**

![](imgs/Pasted%20image%2020261125151029.png)

#### **4.11. L’oblio misurato, e il rehearsal**

> 📌 **Definizione.** Il **rehearsal** (anche *replay*) limita l’oblio **mescolando un piccolo campione dei dati del vecchio compito** nell’addestramento sul nuovo, così **i pesi continuano a servire entrambi**.

**Input**: immagini di cifre $8\times 8$; **compito A** = cifre 0–4, **compito B** = cifre 5–9. **Un MLP, 10 uscite.** **Output**: accuratezza di test su A e su B dopo ogni epoca.

| Momento | Accuratezza su A | Accuratezza su B |
|---|---:|---:|
| dopo A | **99,3%** | — |
| dopo B, **nessuna protezione** | **0,0%** | 96,3% |
| dopo B, **rehearsal** (20% di A mescolato) | **93,7%** | 95,2% |

![](imgs/Pasted%20image%2020261125151049.png)

> 💡 **Perché si arriva a 0% (nota didattica).** Durante l’addestramento su B la rete vede solo le classi 5–9: la cross-entropy spinge le uscite 0–4 verso probabilità zero **per qualsiasi ingresso**, quindi anche un «2» viene classificato come una delle cifre 5–9. Non è solo dimenticare le feature: lo strato d’uscita impara che le classi 0–4 non esistono. Con il 20% di esempi di A nel batch le uscite 0–4 restano «vive», e si perdono solo 5,6 punti su A al costo di 1,1 punti su B.

#### **4.12. L’oblio catastrofico visto con le frontiere di decisione**

1) imparare il compito 1 (i dati del compito 2 non sono ancora visti); 2) poi imparare **solo** il compito 2; ideale: entrambi i compiti. **Stessa rete, stesse classi** (● classe 0, ▲ classe 1). Il compito 2 ha **le stesse classi in una regione nuova**: addestrarsi solo su di esso **sposta la frontiera lontano dai dati del compito 1**.

| Accuratezza sul compito 1 | |
|---|---:|
| dopo il compito 1 | 98,8% |
| dopo il solo compito 2 | 85,0% |
| addestramento congiunto (ideale) | 96,3% |

![](imgs/Pasted%20image%2020261125151105.png)

Dati sintetici (seme fisso): solo geometria, nessuna misura reale.

#### **4.13. Mitigare l’oblio catastrofico**

**È ancora un problema aperto nella ricerca in AI: non esiste una soluzione completa.** Si può provare con:

- **Rehearsal**: una strategia **semplice e intuitiva** in cui i **dati vecchi vengono mescolati ai nuovi** durante l’addestramento per aiutare il modello a ricordare i compiti vecchi. **Può essere costosa in risorse**, perché richiede di **conservare i dati vecchi**.
- **Elastic Weight Consolidation (EWC)**: aggiunge alla loss un **termine di regolarizzazione che preserva i pesi importanti per i compiti vecchi**:
  $$E'=E_B+\frac{\lambda}{2}\sum_iF_i\,(w_i-w_i^A)^2,$$
  con $E_B$ l’errore sul nuovo compito B; $w_i^A$ il peso $i$ dopo il compito A; $F_i\ge 0$ l’**importanza** di $w_i$ per il compito A; $\lambda$ la forza. **Esempio**: $F_i=0\Rightarrow w_i$ è **libero**; $F_i$ grande $\Rightarrow w_i$ **resta vicino a $w_i^A$**.
- **Congelare o adattare**: congelare il backbone, o usare **LoRA**: i pesi pre-addestrati $W$ **restano congelati** e si addestra solo un piccolo aggiornamento a basso rango $BA$, $y=Wx+BAx$ (una minuscola frazione dei parametri). **I pesi vecchi non possono essere sovrascritti.**

> 💡 **EWC come weight decay «mirato» (nota didattica).** È il weight decay del §2.7 con due differenze: tira i pesi verso i valori di A invece che verso zero, e lo fa con forza diversa per ogni peso ($F_i$, stimata dall’informazione di Fisher, cioè da quanto la loss di A è sensibile a quel peso). I pesi poco importanti per A restano liberi di imparare B.

#### **4.14. Mitigare l’oblio catastrofico (2)**

- **Knowledge distillation**: il **modello nuovo (studente)** è addestrato a **riprodurre l’uscita del modello vecchio (insegnante)**, il che lo aiuta a conservare la conoscenza dei compiti vecchi.
- **Architetture dinamiche**: metodi come «**PathNet**» o le «**Progressive Neural Networks**» **adattano l’architettura della rete ai nuovi compiti**. Più complessi, ma possono essere efficaci nel ridurre l’oblio catastrofico.

![](imgs/Pasted%20image%2020261125151154.png)

#### **4.15. Knowledge distillation: etichette morbide da un insegnante**

> 📌 **Definizione.** La **knowledge distillation** addestra una **rete studente** a **riprodurre le probabilità d’uscita ammorbidite di una rete insegnante**, in aggiunta (o al posto) delle etichette vere.

Immagine $I(x,y)$ → **insegnante** (grande, congelato) → punteggi morbidi $p^T$; → **studente** (piccolo, addestrato) → punteggi $p^S$; **loss: rendere $p^S$ vicino a $p^T$**.

**Softmax con temperatura $T$**:

$$p_i=\frac{e^{z_i/T}}{\sum_je^{z_j/T}},$$

con $z_i$ il punteggio d’uscita (logit) della classe $i$. **Logit dell’insegnante** $z=(2,1,0)$:

| $T$ | $p$ |
|---:|---|
| 1 | $(0{,}67;\ 0{,}24;\ 0{,}09)$ |
| 4 | $(0{,}42;\ 0{,}33;\ 0{,}25)$ |

![](imgs/Pasted%20image%2020261125151217.png)

**Una $T$ alta rivela quali classi sbagliate sono simili**: **più informazione dell’etichetta dura** $(1,0,0)$. **Due usi**: **conservare la conoscenza vecchia** (oblio) e **comprimere un modello grande in uno piccolo per un dispositivo edge** (Lezione 23).

> 💡 **I conti (nota didattica).** $T=1$: $e^2=7{,}39$, $e^1=2{,}72$, $e^0=1$, somma $11{,}11$: $(0{,}665;\ 0{,}245;\ 0{,}090)$. $T=4$: $e^{0{,}5}=1{,}65$, $e^{0{,}25}=1{,}28$, $1$, somma $3{,}93$: $(0{,}419;\ 0{,}326;\ 0{,}254)$. Dividere i logit per $T$ avvicina le probabilità: l’ordine resta lo stesso, ma diventano visibili le «seconde scelte» dell’insegnante (per esempio che un 7 somiglia a un 1 più che a un 8). È la temperatura della generazione di testo negli LLM (Lezione 13).

---

### **5. Data augmentation**


Migliorare e ampliare il dataset di addestramento.

#### **5.1. ML classico contro deep learning**

**I modelli di DL hanno bisogno di grandi dataset per essere addestrati!**

![](imgs/Pasted%20image%2020261125151233.png)

#### **5.2. La data augmentation**

> 📌 **Definizione.** La **data augmentation** **amplia il training set con copie modificate dei campioni di training**, prodotte da **trasformazioni che conservano l’etichetta**; il modello impara a essere **invariante a quelle trasformazioni**.

- **La vostra alleata contro i problemi di overfitting!** Si ricordi che si possono avere **> 100M parametri liberi** da fissare/regolare.
- **La data augmentation non risolve da sola l’applicazione**: **servono comunque buoni dati reali**.

![](imgs/Pasted%20image%2020261125151254.png)

#### **5.3. Le augmentation classiche in un’immagine**

**Input**: un’immagine etichettata ($x$, $y=$ «gatto»). **Output**: **7 nuove immagini $T(x)$ con la stessa etichetta $y$**; $T(\cdot)$ è una **trasformazione casuale, estratta di nuovo a ogni epoca**. Foto: esempio «chelsea» di scikit-image (pubblico dominio).

![](imgs/Pasted%20image%2020261125152612.png)

#### **5.4. Augmentation n. 1: rotazioni, traslazioni, scale**

Stessa immagine d’ingresso di una pallina da tennis: la CNN **impara a rilevare l’oggetto in posizioni, scale e rotazioni diverse**. I toolbox offrono funzioni per questo, per esempio `imageDataAugmenter` di MATLAB.

```matlab
pixelRange = [-30 30];
imageAugmenter = imageDataAugmenter( ...
    'RandXReflection',true, ...
    'RandXTranslation',pixelRange, ...
    'RandYTranslation',pixelRange);
augimdsTrain = augmentedImageDatastore(inputSize(1:2),imdsTrain, ...
    'DataAugmentation',imageAugmenter);
```

![](imgs/Pasted%20image%2020261125152637.png)

**Input**: le immagini di training `imdsTrain`. **Output**: un datastore che **restituisce una nuova variante casuale a ogni lettura**. `imageDataAugmenter` definisce le trasformazioni casuali: una **riflessione lungo l’asse verticale** e una **traslazione fino a ±30 pixel** su entrambi gli assi. **Ogni epoca vede varianti diverse dello stesso ingresso.**

#### **5.5. Augmentation n. 2: con il rumore**

Immagine originale, etichetta $y=6$ → **augmentation $T$: rumore** → **6 varianti rumorose, ciascuna con etichetta $y=6$** → **la stessa rete, addestrata su tutte le varianti** (ingressi $x_1\dots x_{64}$, uscite punteggi $p_0\dots p_9$).

![](imgs/Pasted%20image%2020261125152650.png)

**Rumore di tipi diversi** (gaussiano, additivo, moltiplicativo), **condizioni d’applicazione**, **rumore del sensore** (rumore di pixel), **risoluzioni**, **ambienti** (interno, esterno, sfondi)… **L’etichetta non cambia: la rete impara a ignorare il rumore.**

#### **5.6. Augmentation n. 3: con condizioni diverse (simulate)**

**Si dovrebbe considerare di riprodurre le condizioni reali in addestramento. Anche per grandi dataset: si può fare usando CNN pubbliche!**

**Input**: un’immagine etichettata della linea (un pezzo con un graffio). **Output**: varianti **nelle condizioni dell’impianto**; l’etichetta resta «**difetto**». Immagini sintetiche illustrative.

![](imgs/Pasted%20image%2020261125152707.png)

#### **5.7. Data augmentation: lo stato dell’arte**

L’augmentation moderna comprende anche (passi successivi):

- **MixUp, CutMix, Cutout**: mescolare immagini ed etichette;
- **RandAugment, TrivialAugment, AugMix**: politiche casuali;
- **Copy-Paste**: oggetti nuovi su sfondi nuovi;
- **augmentation sintetica e generativa** (diffusione).

![](imgs/Pasted%20image%2020261125152716.png)

#### **5.8. MixUp e CutMix: mescolare immagini ed etichette**

**Input**: due immagini etichettate $(x_i,y_i)$, $(x_j,y_j)$, etichette **one-hot**. **Output**: una nuova coppia $(\tilde{x},\tilde{y})$.

- **MixUp**: $\tilde{x}=\lambda x_i+(1-\lambda)x_j$, $\tilde{y}=\lambda y_i+(1-\lambda)y_j$; $\lambda\in[0,1]$ casuale.
- **CutMix**: **un riquadro di $x_j$ sostituisce lo stesso riquadro di $x_i$**; $\lambda$ = frazione dell’area lasciata a $x_i$.

**Esempio CutMix**: immagine $300\times 451$ pixel, riquadro $150\times 200$: area del riquadro $=30\,000$, immagine $=135\,300$; $\lambda=1-30\,000/135\,300=0{,}78$; etichetta $=(0{,}78$ gatto, $0{,}22$ tazza$)$.

![](imgs/Pasted%20image%2020261125152725.png)

**Effetto**: **decisioni più lisce, meno eccesso di fiducia.**

> 💡 **Perché riduce l’eccesso di fiducia (nota didattica).** Con le etichette miste la rete viene premiata se risponde «78% gatto, 22% tazza» invece di «100% gatto»: impara probabilità intermedie, e le sue confidenze diventano più calibrate (Lezione 18, §8.15). Nello spazio delle feature l’effetto è riempire lo spazio tra le classi di esempi «a metà», che rendono la frontiera più lineare e meno frastagliata.

#### **5.9. Politiche casuali: RandAugment, TrivialAugment, AugMix**

- **RandAugment**: applica **$N$ operazioni estratte a caso** da un elenco, **tutte con forza $M$**; **solo 2 numeri da regolare** (per esempio $N=2$, $M=9$ su una scala 0–30).
- **TrivialAugment** (riga superiore): **una sola operazione casuale con una forza casuale** per immagine; **niente da regolare**, un **default forte**.
- **AugMix** (riga inferiore): **mescola diverse catene casuali e l’originale**; addestrata anche a **dare la stessa uscita sulle varianti** → **robustezza alle immagini corrotte**.

![](imgs/Pasted%20image%2020261125152739.png)

#### **5.10. Copy-Paste: difetti rari su molti pezzi buoni**

**Input**: **pochi difetti reali con la loro maschera** + **molte immagini di pezzi buoni**. **Output**: **nuove immagini di difetti**, con etichette di box / maschera **gratis** (la posizione dell’incollaggio è nota). **Industria**: i difetti sono rari (prodotto buono ≫ difettoso), **i pezzi buoni costano poco**.

![](imgs/Pasted%20image%2020261125152751.png)

> ⚠️ **Controllo**: **illuminazione, scala e tessitura devono sembrare quelle di un difetto reale**; **validare e testare solo su difetti reali**. Dati sintetici (seme fisso).

#### **5.11. Attenzione! L’augmentation deve conservare l’etichetta**

**Una rotazione è utile per un rilevatore di palline da tennis, ma può essere sbagliata per le cifre 6 e 9, i segnali stradali, le immagini mediche o i difetti sensibili all’orientamento.**

![](imgs/Pasted%20image%2020261125152806.png)

> 💡 **Altri esempi (nota didattica).** Un ribaltamento orizzontale scambia una freccia «svolta a sinistra» con «svolta a destra»; in una radiografia del torace sposta il cuore a destra (una condizione rara e diversa); per un graffio orientato lungo la direzione di lavorazione, la rotazione crea un difetto di tipo diverso. La scelta delle trasformazioni è conoscenza del dominio, non un default.

#### **5.12. Augmentation generativa con i modelli di diffusione**

**Immagine reale $x$**, etichetta $y$ → **modello di diffusione** image-to-image (rumore parziale; prompt: «**stesso pezzo, polveroso, poca luce**») → **varianti $\tilde{x}_1,\dots,\tilde{x}_n$, stessa etichetta $y$** → **filtro** (etichetta conservata?) → **solo training set**.

![](imgs/Pasted%20image%2020261125152832.png)

- L’immagine reale viene **solo parzialmente resa rumorosa e poi ripulita** (la diffusione è spiegata più avanti, §6.8): **l’oggetto e la sua etichetta sopravvivono**, cambiano sfondo, luce e stile.
- **Utile per le classi rare** e per **condizioni difficili da fotografare** (notte, pioggia, sporco).
- **Regole**: **controllare che l’immagine sintetica conservi l’etichetta**; **aumentare solo la partizione di training, mai validazione o test** (leakage); **misurare su dati reali**.

**Prospettiva** (tecnica recente, **non d’esame**): **DA-Fusion** e **DiffuseMix** sono esempi recenti di questa idea.

#### **5.13. Deep learning + data augmentation**

Curve qualitative (nessun dato misurato) delle prestazioni (per esempio l’accuratezza) in funzione della **quantità di dati reali etichettati**: ML classico, deep learning, DL + augmentation; **stesse prestazioni, meno immagini**.

![](imgs/Pasted%20image%2020261125152842.png)

> 📌 **L’augmentation non crea informazione nuova, insegna invarianze**, quindi la rete ha bisogno di **meno immagini reali per le stesse prestazioni**.

#### **5.14. Augmentation senza etichette: l’apprendimento self-supervised contrastivo**

> 📌 **Definizione.** L’**apprendimento self-supervised** addestra una rete su un **compito costruito dai dati stessi**, **senza etichette umane**. Nell’**apprendimento contrastivo**, **due viste aumentate della stessa immagine devono dare embedding vicini**, **viste di immagini diverse embedding lontani**.

Immagine non etichettata $x$ → **augmentation casuale $T_1$** → vista 1 $x_1=T_1(x)$ → **encoder $f$** → $z_1=f(x_1)$; **augmentation casuale $T_2$** → vista 2 $x_2=T_2(x)$ → **encoder $f$** (**stessi pesi, condivisi**) → $z_2=f(x_2)$. **Loss**: $z_1$ vicino a $z_2$, **lontano dagli $z$ delle altre immagini**.

![](imgs/Pasted%20image%2020261125152852.png)

$T_1,T_2$: **due trasformazioni casuali diverse** della stessa immagine (ritaglio, rotazione, cambio di colore, rumore), **estratte di nuovo a ogni passo**: ognuna riceve $x$ e ne restituisce una vista cambiata. L’**encoder $f$** (una CNN) riceve una vista e restituisce un **embedding $z$** (per esempio 128 numeri). **Uscita del metodo**: un **backbone pre-addestrato senza etichette**, poi **fine-tuning con poche etichette**. **Stesso obiettivo dell’autoencoder e del pre-addestramento greedy layer-wise, ma sull’intera rete insieme.**

**Metodi noti**: **CPC/InfoNCE** (2018), **SimCLR** (2020), **MoCo** (2020), **CLIP** (immagine–testo, 2021), **SeCo** (telerilevamento, stagioni come positivi, 2021); **senza negativi**: BYOL, SimSiam, DINO.

> 💡 **La loss in formula (nota didattica).** La forma InfoNCE, per un batch di $N$ immagini, è
> $$L=-\log\frac{e^{\cos(z_1,z_2)/\tau}}{\sum_{k}e^{\cos(z_1,z_k)/\tau}},$$
> dove al numeratore c’è la coppia positiva (le due viste della stessa immagine) e al denominatore anche le viste delle altre immagini del batch (negative). È una cross-entropy su «quale delle $N$ immagini è la mia altra vista?», con la similarità coseno (Lezione 18) e una temperatura $\tau$ come nella distillazione.

#### **5.15. Il pre-addestramento contrastivo in IS4: due esempi**

1. **Industria: ispezione visiva dei pezzi** (telecamera di linea, **nessuna etichetta di difetto**). Immagine non etichettata $x$ → vista 1 $T_1(x)$ (ritaglio, rotazione, luminosità), vista 2 $T_2(x)$ (ribaltamento, ritaglio, contrasto, rumore) → **encoder $f$, pre-addestrato senza etichette**, $z_1\approx z_2$ → **fine-tuning su pochi difetti etichettati** → difetto / nessun difetto per ogni pezzo.
2. **Ambiente, veicoli: scene stradali** (telecamera su un veicolo, nessuna etichetta). Vista 1: ritaglio, variazione di colore; vista 2: **altro meteo** (per esempio con una GAN) → encoder $f$ → **fine-tuning su poche scene etichettate** → strada, ostacoli, condizione meteo.

![](imgs/Pasted%20image%2020261125152909.png)

> 📌 **Le augmentation conservano ciò che conta** (un difetto resta un difetto, una strada resta una strada) **e cambiano ciò che non dovrebbe contare** (posizione, luce, meteo). **Input**: immagini non etichettate; **output**: un backbone, poi un classificatore con poche etichette.

> ⚠️ **Una trappola (nota didattica).** Se tra le trasformazioni ci fosse un ritaglio così aggressivo da escludere il difetto, le due viste di un pezzo difettoso sarebbero una «con graffio» e una «senza»: la loss chiederebbe di renderle simili, insegnando alla rete a **ignorare** proprio i graffi. Le augmentation contrastive vanno scelte con la stessa cura del §5.11.

---

### **6. Le reti generative avversarie (GAN)**


Generative Adversarial Networks: creare un pattern/un’immagine da un numero casuale.

#### **6.1. GAN: generatore e discriminatore**

> 📌 **Definizione.** Una **rete generativa avversaria** (GAN) addestra **due reti l’una contro l’altra**: un **generatore $G$** trasforma un **rumore casuale** in campioni, un **discriminatore $D$** stima **la probabilità che un campione sia vero**; **$G$ è addestrato a ingannare $D$**.

**Rumore $z$** (100 numeri casuali) → **generatore $G$** → **immagine falsa $G(z)$** ($64\times 64\times 3$) → **discriminatore $D$** ← **immagine vera $x$** (dal dataset) → $p\in[0,1]$, probabilità «vera»; **aggiornare $G$: fare in modo che $D$ dica «vera» sui falsi**.

![](imgs/Pasted%20image%2020261125152923.png)

**Passi alternati su un batch** (per esempio 64 veri + 64 falsi): **$D$ impara $p\to 1$ sui veri, $p\to 0$ sui falsi**; poi **$G$ impara a spingere il $p$ di $D$ verso 1 sui suoi falsi**. **Dopo l’addestramento si usa solo $G$**: numeri casuali in ingresso, **immagini nuove in uscita**.

> 💡 **Il gioco a due in formula (nota didattica).** L’addestramento risolve
> $$\min_G\max_D\ \mathbb{E}_{x\sim p_{\text{dati}}}\big[\log D(x)\big]+\mathbb{E}_{z\sim p_z}\big[\log\big(1-D(G(z))\big)\big].$$
> $D$ vuole questo valore **alto** (riconosce i veri, scarta i falsi); $G$ lo vuole **basso** (inganna $D$). All’equilibrio ideale le immagini generate sono indistinguibili dalle vere e $D$ risponde $0{,}5$ a tutto. In pratica l’addestramento è **instabile**: se $D$ vince troppo presto, $G$ non riceve gradiente utile; un altro guasto tipico è il **mode collapse**, in cui $G$ produce sempre poche immagini simili. Per $D$ è la cross-entropy binaria della Lezione 21 (§1.8).

#### **6.2. Vera o creata da una GAN?**

**Domanda**: questa persona è vera o generata da una GAN? Decidere prima di girare pagina.

![](imgs/Pasted%20image%2020261125153247.png)

**Risposta: è falsa** (generata da una GAN). Immaginata da una **StyleGAN2** (dicembre 2019), Karras et al. e Nvidia. https://www.thispersondoesnotexist.com/

> ⚠️ **Manca ancora la «Gestalt» (nota didattica).** L’**analisi del contesto** rivela spesso gli errori di una GAN: la **fusione delle feature di livelli diversi** non è sempre realistica (orecchini diversi, sfondi incoerenti, dettagli che non stanno insieme). È la Gestalt della Lezione 2: il tutto è più della somma delle parti, e le parti generate bene non bastano a fare un tutto coerente. Con StyleGAN2 e i modelli successivi questi indizi sono sempre più rari.

#### **6.3. Le GAN creano dati industriali realistici**

Un **generatore $G$ addestrato** riceve un **vettore casuale $z$** ($d=100$ numeri, più un’etichetta di classe opzionale) e restituisce **una nuova immagine che somiglia al training set**.

- **Difetti industriali**: addestrato sui **pochi difetti reali**, $G$ restituisce **nuove immagini di difetti** per **bilanciare le classi rare** di una CNN di ispezione della qualità (Lezione 14, bilanciamento).
- **Veicoli e scene stradali**: nuove auto, pedoni e situazioni di traffico per **testare i sistemi di assistenza alla guida su casi rari**.
- **Macchine e ambiente**: immagini termiche sintetiche, segnali di sensori, immagini satellitari o meteo **dove i campioni reali sono scarsi, costosi o privati**.

![](imgs/Pasted%20image%2020261125153313.png)

**Il realismo è molto migliorato** (volti sfocati nel 2014 → oggi): **l’ispezione visiva da sola non è più un rilevatore affidabile**.

#### **6.4. Style transfer con CNN (GAN) nell’industria**

**Style transfer / traduzione image-to-image**: una rete generativa $G$ riceve un’**immagine di contenuto $x_C$** e uno **stile/condizione obiettivo $x_S$**, e restituisce **lo stesso contenuto nella nuova condizione**.

- $x_C$: auto di giorno, $x_S$: notte → $G(x_C,x_S)$: **la stessa scena di notte**.
- $x_C$: strada d’estate, $x_S$: inverno, neve → $G(x_C,x_S)$: **strada invernale buia**.

![](imgs/Pasted%20image%2020261125153331.png)

**La stessa idea nell’industria**: render CAD → immagine realistica da telecamera; pezzo senza difetti → pezzo con un graffio sintetico; termico → visibile. **Uso**: **casi rari** (difetti, notte, neve) **senza fotografarli**. Immagini: disegni illustrativi.

#### **6.5. GAN e condizioni operative: meteo e luce**

**Stessa scena** (contenuto $x_C$), **un’altra condizione $x_S$**: una GAN riceve un’immagine e la restituisce **come se fosse scattata con un meteo o una luce diversi**. **Uso**: addestrare e testare i sistemi di visione (veicoli, telecamere, ispezione) **su condizioni rare**.

- $x_C$: neve, coperto, $x_S$: sereno → **stessa strada, senza neve**.
- $x_C$: giorno di pioggia, $x_S$: notte → **stessa strada di notte**.

![](imgs/Pasted%20image%2020261125153344.png)S

#### **6.6. Style transfer con CNN (GAN): come è costruito**

**Style transfer**: il **generatore $G$** riceve un’**immagine di contenuto $x_C$** (che cosa c’è nella scena), un’**immagine di stile $x_S$** (che aspetto ha) e **rumore $z$**, e restituisce un’**immagine sintetica $\hat{x}$**; il **discriminatore $D$** restituisce **vera / sintetizzata**.

Immagine di contenuto $x_C$ → **encoder CNN** → **blocchi residui** (+ feature di stile dall’**encoder di stile** di $x_S$, + rumore $z$) → **decoder CNN** → immagine sintetica $\hat{x}$ → **discriminatore $D$** (← immagine reale del dominio obiettivo) → vera / sintetizzata.

![](imgs/Pasted%20image%2020261125153358.png)

**Segnali di addestramento**: **loss di contenuto** (oggetti e disposizione di $\hat{x}$ restano quelli di $x_C$), **loss di stile** (tessitura e statistiche del colore di $\hat{x}$ corrispondono a $x_S$) e la **loss avversaria**: $D$ è addestrato a distinguere il vero dal sintetizzato, $G$ a ingannarlo.

#### **6.7. A che cosa servono le GAN**

Una GAN **genera campioni nuovi che somigliano ai dati di training**. Usi nei domini IS4:

- **Industria**: **difetti sintetici** (graffi, crepe, corrosione) per l’ispezione visiva; render CAD/simulatore → immagini realistiche da telecamera; **segnali di vibrazione sintetici** per la manutenzione predittiva.
- **Supply chain, logistica**: pacchi, pallet ed etichette sintetici (danni, occlusioni, poca luce) per le telecamere di magazzino; **scenari di domanda e traffico** per mettere alla prova la pianificazione.
- **Ambiente, veicoli**: varianti di **neve, pioggia e notte** delle scene stradali; **super-risoluzione** di immagini satellitari; situazioni di guida rare, **senza problemi di privacy** (persone, targhe).
- **Strumenti per immagini**: sintesi da testo a immagine, **restauro di immagini vecchie**.

![](imgs/Pasted%20image%2020261125153446.png)

- **Riservatezza**: lavorare su immagini sintetiche e **condividerle** con fornitori, studenti o servizi cloud **senza rivelare segreti industriali** o altri contenuti riservati (Lezione 15, sovranità dei dati).
- **Sicurezza**: creare **situazioni pericolose** (incidenti, incendi, collisioni, guasti delle macchine) **senza produrle davvero**, danneggiare le attrezzature o mettere in pericolo le persone.

> ⚠️ **Midjourney non è una GAN (nota didattica).** La slide illustra l’elenco con una «colomba meccanica» creata con **Midjourney**, che è un sistema basato su **modelli di diffusione** (§6.8), non su una GAN. Le due famiglie risolvono lo stesso problema (generare immagini realistiche) e molti usi dell’elenco valgono per entrambe; i grandi generatori testo-immagine attuali sono quasi tutti a diffusione.

#### **6.8. I modelli di diffusione (denoise)**

- I **modelli di diffusione** **generano dati imparando a invertire un processo graduale di aggiunta di rumore**.
- Durante l’addestramento il modello osserva **immagini progressivamente corrotte dal rumore** e **impara a prevedere come togliere quel rumore passo dopo passo**.
- In fase di generazione il processo **parte da rumore casuale** e lo **ripulisce iterativamente** finché si ottiene un’immagine realistica.

![](imgs/Pasted%20image%2020261125153504.png)

#### **6.9. La diffusione: aggiungere rumore, imparare a toglierlo**

> 📌 **Definizione.** Un **modello di diffusione** impara a **invertire un processo graduale di aggiunta di rumore**: una rete è addestrata a **prevedere il rumore aggiunto a un’immagine**; la generazione **parte da rumore puro e lo toglie passo dopo passo**.

- **In avanti: aggiungere rumore** (nessun apprendimento): immagine pulita $x_0$ → un po’ di rumore $x_1$ → … → **rumore puro** $x_T$ ($T=1000$), immagini $64\times 64\times 3$.
- **All’indietro**: immagine rumorosa $x_t$ → **rete di denoising** (prevede il rumore, guidata dal prompt «**un corgi che suona la tromba**») → si toglie una parte del rumore → $x_{t-1}$, meno rumorosa; **ripetere $T$ volte**.

![](imgs/Pasted%20image%2020261125153539.png)

**Generazione**: si parte da **rumore puro $x_T$**; dopo **$T$ passi all’indietro** l’uscita $x_0$ è **un’immagine nuova che segue il prompt**.

> 💡 **La diffusione in formula (nota didattica).** Nella formulazione più diffusa (**DDPM**, *Denoising Diffusion Probabilistic Model*), l’immagine $x_0$ corrotta al passo $t$ si ottiene in un colpo solo:
> $$x_t=\sqrt{\bar{\alpha}_t}\,x_0+\sqrt{1-\bar{\alpha}_t}\,\varepsilon,\qquad \varepsilon\sim\mathcal{N}(0,I),$$
> dove $\bar{\alpha}_t$ scende da circa 1 (immagine pulita) a circa 0 (rumore puro). La rete $\varepsilon_\theta$ impara a **indovinare il rumore** aggiunto:
> $$\mathcal{L}=\mathbb{E}\big[\|\varepsilon-\varepsilon_\theta(x_t,t)\|^2\big].$$
> È un denoising autoencoder (§1.10) addestrato a tutti i livelli di rumore insieme. Il «rumore parziale» dell’augmentation generativa (§5.12) significa partire da un $x_t$ intermedio invece che da $x_T$: l’immagine originale resta riconoscibile.

#### **6.10. Modelli di diffusione: il condizionamento con il testo**

- L’**encoder del testo** converte il prompt in **embedding**. Questi embedding **condizionano il modello di diffusione**, guidando ogni passo di denoising verso un’immagine che corrisponde al prompt. **CLIP** si usa comunemente in modelli come **Stable Diffusion v1**, ma anche altri encoder di testo come **T5** si usano nei sistemi moderni.

Sono oggi **uno degli approcci dominanti** per la generazione di immagini di alta qualità, l’editing, l’*inpainting* (riempimento di parti mancanti), la super-risoluzione e i sistemi da testo a immagine. **Li usiamo per la data augmentation!**

![](imgs/Pasted%20image%2020261125153610.png)

#### **6.11. Sony World Photography Awards 2023**

**BBC, 18/04/2023**: l’opera dell’artista tedesco **Boris Eldagsen**, intitolata ***Pseudomnesia: The Electrician***, ha vinto la categoria *creative open* del Sony World Photography Award della settimana precedente. **Il vincitore rifiuta il premio dopo aver rivelato che è una creazione dell’AI.**

![](imgs/Pasted%20image%2020261125153650.png)

> 💡 **Il contesto (nota didattica).** Eldagsen dichiarò di aver partecipato per provocare una discussione: se una giuria di esperti non distingue un’immagine generata da una fotografia, il concorso deve dire che cosa intende per fotografia. È lo stesso punto del §6.3: l’ispezione visiva non basta più. Per i dati industriali significa tracciare la **provenienza** delle immagini (§6.14).

#### **6.12. L’effetto MAD: il model autophagy disorder**

- **Il peggior nemico dell’intelligenza artificiale potrebbe essere l’intelligenza artificiale stessa.**
  - Uno studio della **Rice University e di Stanford** ha rivelato che **addestrare modelli di AI su dati sintetici** sembra causarne l’**erosione**, almeno nel lungo periodo.
  - Il termine per questa apparente «**autoallergia**» del software è **MAD**, acronimo di ***Model Autophagy Disorder***.
  - **Molte AI vengono così addestrate, senza saperlo, su quantità crescenti di dati già sintetizzati.**

«**Mucche che mangiano altre mucche**» sono diventate pazze… (il richiamo è alla «mucca pazza», la BSE).

![](imgs/Pasted%20image%2020261125153729.png)

#### **6.13. Il contenuto sintetico sta riempiendo il web**

- **Nov 2022**: rilascio di **ChatGPT**.
- **Circa nov 2023**: circa il **39%** dei nuovi articoli web in inglese **generati dall’AI**.
- **Nov 2024**: **oltre il 50%**: gli articoli dell’AI **superano quelli umani**; poi **all’incirca stabile** (fino a maggio 2025).

![](imgs/Pasted%20image%2020261125153807.png)

**Misurato su**: **65 000 articoli web in inglese**, campionati a caso dall’archivio **Common Crawl**, pubblicati da gennaio 2020 a maggio 2025, etichettati da un **rilevatore di testo AI** (falsi positivi 4,2%, falsi negativi 0,6%).

**Perché conta**: dopo il 2022 **una raccolta dal web non è più un dataset reale garantito**: testi e immagini trovati online **possono essere già sintetici**, quindi il ciclo della slide precedente **può chiudersi da solo**.

> ⚠️ **Leggere con cautela**: **i rilevatori sono imperfetti**, **il testo AI ritoccato da persone non è stato valutato**, e **lo studio viene da un’azienda di marketing**; altre stime possono differire. Fonte: studio Graphite (2025), riportato in «*More articles are now created by AI than humans*», Stacker, 23 ottobre 2025.

> 💡 **Quanto pesano gli errori del rilevatore (nota didattica).** Con il 4,2% di falsi positivi, anche un web interamente umano risulterebbe «generato dall’AI» per il 4,2%: è il pavimento della misura, e le quote misurate prima del 2022 dovrebbero stare vicino a quel valore. Attorno al 50% l’effetto dei due errori è di circa 2 punti, ma solo se i tassi del rilevatore, misurati su un insieme di prova, valgono anche sugli articoli reali, che possono essere stati ritoccati: è lo stesso problema di generalizzazione di ogni metrica misurata su un test set (Lezione 16).

#### **6.14. Ridurre il model autophagy disorder**

**Conclusione**: **senza abbastanza dati reali freschi a ogni generazione di un ciclo autofago**, i futuri modelli generativi sono **destinati a veder diminuire progressivamente la qualità (precisione) o la diversità (recall)**. Esempio con riaddestramenti successivi di **StyleGAN-2**.

![](imgs/Pasted%20image%2020261125155134.png)

**Lo stesso problema riguarda ora LLM e modelli d’immagine**: da ChatGPT (2022) Internet si è riempita di testi e immagini generati, quindi **un dataset sicuramente reale non è più disponibile di default**. **I dati sintetici richiedono provenienza, filtraggio, controllo della diversità e validazione.**

#### **6.15. Evitare il collasso del modello: sostituire o accumulare?**

Ogni generazione $t$ addestra un nuovo modello su un training set $D_t$; il modello produce poi i dati sintetici $S_t$.

- **Sostituire**: $D_t=S_{t-1}$, **solo i dati della generazione precedente**. **Risultato**: **i casi rari si perdono per primi, poi la diversità**.
- **Accumulare**: $D_t=D_0\cup S_1\cup\dots\cup S_{t-1}$, **i dati reali originali $D_0$ più tutti i dati sintetici**. **Risultato**: **il degrado resta limitato**.

![](imgs/Pasted%20image%2020261125155228.png)

**Esperimento giocattolo**: ogni generazione **adatta una gaussiana a 10 campioni estratti dal modello precedente** (mediana di 300 esecuzioni). **Regole**: **tenere un insieme di riferimento reale**, **tracciare la provenienza**, **testare su dati reali**.

> 💡 **Perché la diversità crolla (nota didattica).** Stimare la deviazione standard da 10 campioni la sottostima in media (e con molta variabilità): la generazione successiva campiona da una gaussiana un po’ più stretta, e così via. Ogni passo perde un po’ di code, cioè di casi rari, e l’errore si accumula: la deviazione standard va verso zero. Accumulando, i dati reali $D_0$ restano sempre nel training set e «ancorano» la stima.

---

### **7. I confini attuali del ML**


Più dati? Più parametri? È la strada giusta per ottenere sistemi migliori?

#### **7.1. Le scaling laws: più calcolo, dati, parametri**

- **Scaling law**: l’**errore di test $L$** scende come **una legge di potenza** della dimensione $X$ ($X$ = parametri $N$, dati $D$ o calcolo $C$):
  $$L\approx\left(\frac{X_0}{X}\right)^{\alpha},\qquad \alpha>0\ \text{piccolo}.$$
  **In assi log–log è una retta**: **ogni fattore 10 dà lo stesso fattore**, quindi **i rendimenti sono decrescenti**. Osservata per i **modelli linguistici** e per i **ViT**.
- **Bilanciamento (Chinchilla, 2022)**: **a calcolo fissato, $N$ e $D$ devono crescere insieme** (circa **20 token di addestramento per parametro**); **un modello più piccolo addestrato su più dati può battere uno più grande**.
- **Per le applicazioni**: **backbone pre-addestrati compatti o CNN ad hoc, con buoni dati, spesso bastano**; **non scalare fine a se stesso**.

![](imgs/Pasted%20image%2020261125155256.png)

> 💡 **Il conto dei rendimenti decrescenti (nota didattica).** Con $\alpha=0{,}076$ (un valore tipico per i parametri dei modelli linguistici), moltiplicare $N$ per 10 moltiplica la loss per $10^{-0{,}076}=0{,}84$: un miglioramento del 16%. Per dimezzare la loss serve un fattore $2^{1/0{,}076}\approx 9000$ nei parametri. Chinchilla: con 70 miliardi di parametri si dovrebbe addestrare su circa 1,4 mila miliardi di token. I **Vision Transformer** (ViT) applicano il transformer (Lezione 13) a patch d’immagine, come il MAE del §1.14, e seguono lo stesso andamento.

---

### **8. LLM e progettazione del machine learning**


Che cosa può fare uno strumento come ChatGPT per aiutare il lavoro di un progettista di machine learning?

#### **8.1. Nemico o alleato?**

- Come ogni modello linguistico di AI, ChatGPT si può vedere come **un alleato** per analisti dei dati e progettisti di ML **più che come un nemico**.
- È **cruciale esercitare il pensiero critico**, **validare i risultati** e **verificare in modo indipendente** le informazioni fornite.
- In quanto modello linguistico, **non ha accesso a dati in tempo reale o specifici del dominio**: le sue risposte si basano su **pattern appresi da informazioni preesistenti**.

![](imgs/Pasted%20image%2020261125155826.png)

> 💡 **Una precisazione aggiornata (nota didattica).** La frase sull’assenza di dati in tempo reale vale per il modello da solo; un assistente con strumenti (ricerca web, esecuzione di codice, RAG sui documenti aziendali) può leggere dati attuali e del dominio (Lezione 15). La regola della slide non cambia: ciò che produce va verificato, perché un’informazione recuperata può essere usata male e un calcolo può essere fatto sui dati sbagliati.

#### **8.2. ChatGPT può far rendere il vostro lavoro**

Si possono sfruttare assistenti tipo ChatGPT per **compiti diversi**: che cosa si dà, che cosa si ottiene, **che cosa si deve controllare**.

| Compito | Si dà | Si ottiene | Si deve controllare |
|---|---|---|---|
| **analisi esplorativa dei dati, visualizzazione** | descrizione dei dati o un campione da ispezionare | riassunti, grafici, outlier | che **le statistiche siano calcolate sui propri dati** |
| **pre-elaborazione, selezione delle feature** | colonne, tipi, obiettivo | codice per pulizia, scalatura, codifica | **nessun leakage** (fit solo sul training) |
| **scelta dell’algoritmo** | problema, dimensione dei dati, vincoli | modelli candidati con pro e contro | **validazione sui propri dati** |
| **sviluppo del modello, valutazione** | compito e metrica | codice di addestramento, iperparametri | **metrica, partizioni, baseline** |
| **assistenza al codice, debug** | messaggio d’errore, codice | correzioni, spiegazioni (Python, R, MATLAB) | **eseguire e testare il codice** |
| **ricerca, concetti** | domanda | spiegazioni, riferimenti | **che i riferimenti esistano e dicano ciò che si afferma** |

> 💡 **Un esempio di verifica: il dizionario dei dati di Iris (nota didattica, dall’edizione precedente).** Alla richiesta «*give me a data dictionary of the iris dataset*», un assistente descrive i campi; per controllarne la risposta invece di fidarsi:
>
> | Campo | Tipo | Unità | Contenuto |
> |---|---|---|---|
> | sepal length | numerico continuo | cm | lunghezza del sepalo |
> | sepal width | numerico continuo | cm | larghezza del sepalo |
> | petal length | numerico continuo | cm | lunghezza del petalo |
> | petal width | numerico continuo | cm | larghezza del petalo |
> | species | categorico nominale | — | setosa, versicolor, virginica (50 per specie) |
>
> Il dataset ha 150 righe e nessun valore mancante (Lezione 16). Un dizionario che dicesse «4 classi» o «lunghezze in mm» andrebbe corretto: è la colonna «che cosa controllare» applicata.

#### **8.3. Progettare il ML con gli agenti**

![](imgs/Pasted%20image%2020261125155912.png)

#### **8.4. Gli agenti e la «corsa all’oro»**

- Durante la **corsa all’oro** (le migrazioni di massa in cerca d’oro, soprattutto nel XIX secolo) **la maggior parte dei partecipanti non trovò la ricchezza**, e affrontò difficoltà o addirittura perdite.
- Alcuni guadagnarono **profitti sostanziali non estraendo l’oro**, ma con **attività collaterali**.
- Questo richiamo storico aiuta a **riformulare ciò che LLM e agenti possono fare nel proprio lavoro**: **nuove attività aiutate, basate o migliorate da essi**, non «contro» di essi o «sostituite» da essi.

![](imgs/Pasted%20image%2020261125155957.png)

> 💡 **Picconi e pale (nota didattica).** Il detto americano è «*sell picks and shovels*»: nella corsa all’oro guadagnava con certezza chi vendeva gli attrezzi. Per un progettista di sistemi intelligenti il valore è nelle attività attorno al modello, che il corso ha coperto una per una: dati puliti e armonizzati, validazione corretta, monitoraggio, integrazione nel processo industriale.

---

### **9. Punti principali**


> ✅ **Punti principali della lezione.**
> - Un **autoencoder impara un codice compatto $z$ senza etichette ricostruendo il proprio ingresso**; addestrato su pezzi buoni, il suo **errore di ricostruzione $s(x)$ segnala le anomalie**.
> - Le **reti profonde si addestrano end-to-end** grazie a ReLU, inizializzazione accurata, normalizzazione, connessioni residue, dati e regolarizzazione; il **greedy layer-wise training è storico**, la sua idea **vive nel pre-addestramento self-supervised**.
> - **Transfer learning**: riusare un backbone pre-addestrato; **pochi dati → addestrare solo la testa**, **più dati → sbloccare più strati con un $\alpha$ piccolo**; **LoRA addestra un piccolo aggiornamento a basso rango**.
> - Un **backbone congelato dà embedding riusabili** per classificatori, k-NN e punteggi di anomalia.
> - **L’addestramento sequenziale causa l’oblio catastrofico**: rehearsal, EWC, congelamento/LoRA e distillazione lo mitigano.
> - **La data augmentation deve conservare l’etichetta e toccare solo la partizione di training**; MixUp/CutMix, politiche casuali, Copy-Paste e augmentation generativa la estendono.
> - **GAN e modelli di diffusione generano immagini**; **addestrarsi sui propri dati sintetici senza dati reali porta al collasso del modello**.

---

### **10. Distinzioni da non confondere**


| Coppia | Differenza chiave |
|---|---|
| **autoencoder** / **PCA** | non lineare, appreso con backprop / lineare, autovettori (un AE lineare impara lo stesso sottospazio) |
| **encoder** / **decoder** | da $x$ al codice $z$ / dal codice a $\hat{x}$ |
| **AE semplice** / **VAE** | spazio latente non necessariamente continuo / spazio latente regolarizzato e campionabile |
| **AE** / **denoising AE** | ricostruisce l’ingresso / ricostruisce l’ingresso pulito da uno rumoroso |
| **AE per anomalie** / **classificatore** | solo esempi buoni, punteggio e soglia / esempi di tutte le classi |
| **MAE** / **AE** | ricostruisce patch nascoste / ricostruisce tutto l’ingresso |
| **BatchNorm** / **LayerNorm** | statistiche sul batch / statistiche sulle feature di un campione |
| **SGD** / **Adam** | stesso passo per tutti i pesi / passo adattato a ogni peso |
| **early stopping** / **weight decay** / **dropout** | ferma alla validazione migliore / penalizza i pesi grandi / spegne unità a caso |
| **greedy layer-wise** / **end-to-end** | strato per strato, storico / tutti gli strati insieme, moderno |
| **transfer learning** / **fine-tuning** | riuso del backbone, testa nuova / continuare l’addestramento con $\alpha$ piccolo |
| **fine-tuning completo** / **LoRA** | tutti i pesi / aggiornamento a basso rango, ~0,4% dei parametri |
| **estrattore di feature** / **fine-tuning** | backbone mai riaddestrato / backbone modificato |
| **rehearsal** / **EWC** | mescolare dati vecchi / ancorare i pesi importanti |
| **etichetta dura** / **etichetta morbida** | $(1,0,0)$ / probabilità dell’insegnante con temperatura $T$ |
| **augmentation** / **dati nuovi** | insegna invarianze / porta informazione nuova |
| **MixUp** / **CutMix** | media pesata di due immagini / un riquadro di una nell’altra |
| **augmentation supervisionata** / **contrastiva** | stessa etichetta per le varianti / embedding vicini per le viste, nessuna etichetta |
| **GAN** / **diffusione** | generatore contro discriminatore, un passo / denoising iterativo in $T$ passi |
| **sostituire** / **accumulare** | solo dati sintetici, collasso / dati reali sempre presenti, degrado limitato |
| **più parametri** / **più dati** | Chinchilla: vanno bilanciati a calcolo fissato |
