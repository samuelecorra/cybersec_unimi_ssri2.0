## **Lezione 24: Vision Transformer, AIoT e sicurezza dell’IoT — ML all’edge e nel cloud, applicazioni, visione intelligente dall’Industria 4.0 alla 5.0**

L’ultima lezione del corso parte da una domanda di progetto: **dove deve girare l’intelligenza di un sistema connesso, e come la si mantiene sicura?** Si apre con il **Vision Transformer** (ViT), che legge un’immagine come una sequenza di patch e confronta ogni patch con tutte le altre fin dal primo strato, e lo si confronta con le CNN, fino al suo uso come estrattore di feature. Poi l’**AIoT** (*Artificial Intelligence of Things*): l’unione di sensori e reti con i modelli di AI, la pila hardware e software dal dispositivo all’edge al cloud, la **sicurezza dell’IoT** con gli aggiornamenti OTA e le regole (Cyber Resilience Act europeo, NIST CSF 2.0). Gli strumenti: **piattaforme IoT** e sistemi operativi in tempo reale, **schede e acceleratori** per l’edge AI, **TinyML** per i microcontrollori, **servizi cloud** per il ML. Le applicazioni: visione intelligente, logistica, trasporti (V2X, il sensore virtuale di Volvo) e il **Pirelli Cyber Tyre**. La chiusura: i **sistemi di visione industriali** e il rilevamento di anomalie, e il passaggio **dall’Industria 4.0 alla 5.0**, con la business intelligence e la scala dell’analisi.

> **Fonte.** La lezione segue il PDF dell’edizione 2026/27 `sources_2026-27/L24-web.pdf` (121 pagine, di cui 77–121 di appendice **non d’esame**). I segnaposto per le figure indicano la pagina del PDF. Il PDF dell’edizione precedente (`Lesson_24_Machine_Learning_Software_Cloud_Services.pdf`) è conservato nella cartella; la sua simulazione d’esame è riportata nel §14. Prodotti, prezzi e date cambiano in fretta: conta il principio.


### **0. Panoramica**


- **Domanda**: dove deve girare l’intelligenza di un sistema connesso, e come la si mantiene sicura?
- **Vision Transformer**: un’immagine come sequenza di patch; CNN o ViT; ViT come estrattore di feature (esercizio).
- **Teoria**: l’AIoT e la sua pila HW/SW; sicurezza dell’IoT, aggiornamenti OTA, regole (CRA, NIST).
- **Strumenti**: piattaforme IoT e RTOS; schede per l’edge AI; TinyML; servizi cloud per il ML.
- **Applicazioni**: visione intelligente, logistica, trasporti, il Pirelli Cyber Tyre.
- **Dall’Industria 4.0 alla 5.0**: sistemi di visione industriali e rilevamento di anomalie.

<!-- SOURCE_VISUAL id="IS24-V01" source="L24-web.pdf" page="2" type="figura" description="Immagini della panoramica: ViT, AIoT, sicurezza e visione industriale" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **1. Teoria: il Vision Transformer (ViT)**


Un’immagine letta come una sequenza di patch.

#### **1.1. Due modi di leggere la stessa immagine: CNN e ViT**

- **(a) CNN: guardare localmente, poi più largo.** Convoluzione + pooling: ogni unità vede una **piccola finestra** (il kernel $K(\cdot)$); le unità più profonde vedono **più largo** (il campo ricettivo).
- **(b) ViT: tagliare in patch, ogni patch vede ogni patch.** Encoder transformer: **self-attention globale** + testa MLP; **già nel primo strato ogni patch viene confrontata con tutte le altre** (gli archi).

<!-- SOURCE_VISUAL id="IS24-V02" source="L24-web.pdf" page="4" type="schema" description="La stessa immagine letta da una CNN con finestre locali e da un ViT con patch collegate tutte tra loro" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Stesso ingresso, stessa uscita**: immagine $I(x,y)$ → classe $\hat{y}$ (uccello, palla, auto). **Cambia quanto lontano guarda ogni unità**: una CNN costruisce la vista globale **passo per passo**, un ViT ce l’ha **dall’inizio**. Le slide successive costruiscono il ViT pezzo per pezzo.

#### **1.2. Vision Transformer (1/4): l’immagine diventa patch**

> 📌 **Definizione.** Il **Vision Transformer** (ViT) è un **encoder transformer** (Lezione 13) applicato a un’immagine **tagliata in patch di dimensione fissa**; **ogni patch diventa un token**. Dosovitskiy et al., *An Image is Worth 16x16 Words*, ICLR 2021.

- **Input**: $I(x,y)$ di $H\times W\times C$ pixel. **Numero di patch**:
  $$N=\frac{H\cdot W}{P^2}\qquad(P\text{: lato della patch in pixel}).$$
- **Esempio**: $224\times 224\times 3$, $P=16\Rightarrow N=196$ patch, ognuna con $16\cdot 16\cdot 3=768$ valori.

<!-- SOURCE_VISUAL id="IS24-V03" source="L24-web.pdf" page="5" type="schema" description="Architettura del ViT: patch, proiezione lineare, token class, embedding di posizione, encoder con self-attention e MLP, testa" reason="Schema visibile solo nella slide (ripetuto alle pp. 6, 8, 9 con parti diverse evidenziate)" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Il conto (nota didattica).** $224/16=14$ patch per lato, $14\cdot 14=196$. La figura della slide usa $N=9$ (una griglia $3\times 3$) per leggibilità. Una patch di 768 numeri è un «vettore di pixel» come quello della Lezione 18 (§2.3), che il ViT trasforma in un token come un LLM trasforma una parola (Lezione 13).

#### **1.3. Vision Transformer (2/4): le patch diventano token**

- Ogni patch viene **appiattita** (768 valori) e **moltiplicata per una matrice appresa $E$**: **un token di dimensione $D$** (ViT-Base: $D=768$).
- Si aggiunge un **embedding di posizione** $(0,\dots,N)$: **l’attenzione ignora dove si trova una patch** (Lezione 13, §4.6).
- Un **token [class] extra apprendibile** (∗) **raccoglie l’intera immagine**. **Output**: $(N+1)\times D$, per esempio $197\times 768$.

#### **1.4. Il patch embedding: da dove vengono le sue matrici**

$$z_0=E\,x+b+E_{\text{pos}}[k],$$

con $x$ la patch ($16\times 16\times 3$ = 768 numeri), $E$ una matrice $768\times 768$, $b$ il bias, $E_{\text{pos}}[k]$ l’embedding della posizione $k$ della patch, $z_0$ il token ($D=768$).

**Come si creano le matrici**:

1. **A caso, con valori piccoli**: $E$ gaussiana con $\sigma=1/\sqrt{768}\approx 0{,}036$; $E_{\text{pos}}$ con $\sigma=0{,}02$; token [class] a zero.
2. **Apprese con la backpropagation** insieme a tutta la rete nel **pre-addestramento** (ImageNet-1k: 1,28 M immagini etichettate; ImageNet-21k: 14 M): **il gradiente aggiorna $E$ come qualsiasi peso**.
3. **Poi fisse**: **la stessa $E$ per ogni patch**; il fine-tuning la regola un poco.

| Parametri (ViT-B/16) | |
|---|---:|
| $E$: $768\times 768$ | 589 824 |
| $b$ | 768 |
| $E_{\text{pos}}$: $197\times 768$ | 151 296 |
| [class] | 768 |
| **totale** | **≈ 0,74 M su 86,6 M** |

**Nessun vocabolario**: **qualsiasi patch, anche mai vista, ha il suo token.** In codice: `Conv2d(3, 768, kernel=16, stride=16)`, **la stessa $E$ fatta scorrere sull’immagine** (richiamo al primo strato delle CNN, Lezione 21).

<!-- SOURCE_VISUAL id="IS24-V04" source="L24-web.pdf" page="7" type="schema" description="Patch embedding: patch per matrice E più bias più embedding di posizione" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché è una convoluzione (nota didattica).** Una convoluzione con kernel $16\times 16$, passo 16 e 768 filtri applica a ogni patch non sovrapposta lo stesso prodotto con una matrice $768\times(16\cdot 16\cdot 3)$: è esattamente $Ex+b$ per ogni patch. Con la formula della Lezione 21 (§3.10), $O=\lfloor(224-16)/16\rfloor+1=14$: una mappa $14\times 14\times 768$, cioè 196 token. A differenza di un LLM, che ha un vocabolario finito di token, il ViT trasforma qualsiasi patch in un vettore continuo. $589\,824+768+151\,296+768=742\,656$: lo 0,86% dei parametri del modello.

#### **1.5. Vision Transformer (3/4): l’encoder transformer**

- **$L$ blocchi identici** (ViT-Base: $L=12$): Norm → **self-attention multi-testa** → + ; Norm → **MLP** → +.
- **MLP** (*multi-layer perceptron*): la rete completamente connessa del corso (Lezione 17), **due strati $768\to 3072\to 768$** applicati **a ogni token separatamente**.
- **Self-attention**: **ogni patch guarda ogni patch** (query, key, value, Lezione 13); i **+** sono **connessioni residue** (Lezione 22).
- **Output**: **stessa forma** $(N+1)\times D$, ma **ogni token ora porta il contesto dell’intera immagine**.

> 💡 **Dove stanno gli 86,6 M parametri (nota didattica).** Per blocco: l’attenzione ha 4 matrici $768\times 768$ (Q, K, V e la proiezione d’uscita), $4\cdot 589\,824\approx 2{,}36$ M; l’MLP $768\cdot 3072+3072\cdot 768\approx 4{,}72$ M; in tutto circa 7,1 M per blocco, × 12 blocchi ≈ 85 M. Due terzi dei parametri sono nelle MLP, non nell’attenzione.

#### **1.6. Vision Transformer (4/4): dai token alla risposta**

- **Solo l’uscita del token [class] entra nella testa MLP** → previsione $\hat{y}$ (uccello, palla, auto…); nel **pre-addestramento** un’MLP con uno strato nascosto, nel **fine-tuning** **uno strato lineare $768\to$ numero di classi**.
- **Togliendo la testa, i token sono un embedding $z$**, riusato da **DINOv2** e dall’**encoder d’immagine di SAM** (Lezione 23, §3.10).
- **In pratica**: **backbone ViT pre-addestrato + la propria testa**, la stessa ricetta del transfer learning con le CNN (Lezione 21, §5.6).

#### **1.7. CNN o ViT? Bias induttivo, dati e costo**

| **CNN** | **ViT** |
|---|---|
| **ipotesi incorporate** (bias induttivo): **località** ed **equivarianza alla traslazione** dei kernel $K(\cdot)$ | **attenzione globale dal primo strato**: ogni patch vede ogni patch |
| **impara bene da dataset piccoli/medi** | **poche ipotesi incorporate** → richiede **pre-addestramento su larga scala** (addestrato da zero su ImageNet-1k è **peggiore di una ResNet**) |
| **matura sui dispositivi edge** (quantizzazione, NPU) | **ottimo come backbone pre-addestrato** (DINOv2, encoder di SAM) |

**Costo dell’attenzione** $\propto N^2\cdot D$ ($N$: token, $D$: dimensione del token): richiamo dalla Lezione 13, **l’attenzione è quadratica nel numero di token**. **Esempio**: dimezzare la patch $P=16\to 8$ dà $N=196\to 784$ token (4×) e **16 volte il costo dell’attenzione** ⇒ **le immagini d’ispezione ad alta risoluzione sono costose**.

> 📌 **Da portare a casa**: **dati e pre-addestramento contano più dell’architettura** (CNN moderne come **ConvNeXt** restano competitive). Touvron et al., DeiT, ICML 2021; Liu et al., ConvNeXt, CVPR 2022.

> 💡 **Il bias induttivo come «dati gratis» (nota didattica).** Una CNN «sa» già che un bordo è un bordo ovunque si trovi (pesi condivisi, Lezione 21, §3.14): non deve impararlo dai dati. Un ViT deve scoprire dai dati anche che le patch vicine sono correlate: con pochi dati non ci riesce, con centinaia di milioni di immagini sì, e a quel punto la sua flessibilità rende. È il compromesso bias-varianza della Lezione 17 applicato alle architetture.

#### **1.8. Il ViT come estrattore di feature: prendere il suo vettore**

Immagine $I(x,y)$ ($224\times 224\times 3$) → **ViT pre-addestrato** (patch + encoder, **testa rimossa**) → uscita del **token [class]** → **embedding** $z\in\mathbb{R}^{768}$ → **classificatore semplice** (k-NN, SVM, lineare) → $\hat{y}$; oppure **ricerca per similarità** (coseno, Lezione 19) → immagini simili.

<!-- SOURCE_VISUAL id="IS24-V05" source="L24-web.pdf" page="11" type="schema" description="ViT senza testa che produce un embedding da 768 usato da un classificatore o dalla ricerca per similarità" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Sì, un ViT è un ottimo estrattore di feature**: si toglie la testa, si tiene l’uscita del token [class] (**o la media dei token delle patch**). Stessa idea di backbone + testa (Lezioni 21–22); **DINOv2/v3 sono progettati per questo**. **Input**: un’immagine $224\times 224\times 3$. **Output**: $z$, **un vettore di 768 numeri** (VGG16 ne dà 4096).

#### **1.9. Usare un ViT pre-addestrato: un’immagine nuova e il suo vettore $z$**

**Input**: una nuova immagine `img`.

```python
import torch, torch.nn.functional as F
from torchvision.models import vit_b_16, ViT_B_16_Weights
torch.set_grad_enabled(False)                     # inferenza
w = ViT_B_16_Weights.IMAGENET1K_V1
vit = vit_b_16(weights=w).eval()                  # inferenza
x = w.transforms()(img).unsqueeze(0)              # 1x3x224x224

# (a) classificare la nuova immagine (testa mantenuta)
p = vit(x).softmax(dim=1)[0]                      # 1000 probabilità
conf, idx = p.topk(5)                             # top-5
print(w.meta["categories"][idx[0]], conf[0])

# (b) riusare il suo vettore z (testa rimossa)
vit.heads = torch.nn.Identity()
z = vit(x)                                        # 1 x 768
cos = F.cosine_similarity(z, Z_good)              # 200 coseni
s = 1 - cos.max()                                 # punteggio s(x)
print("faulty" if s > tau else "good")
```

**Righe chiave**: 5: ViT-B/16 + pesi ImageNet; `.eval()`: modalità di inferenza. 6: la pre-elaborazione dell’addestramento (ridimensionamento, ritaglio 224, normalizzazione) + dimensione del batch. 9–11 (a): con la testa: 1000 probabilità, classe top-1. 14–15 (b): testa rimossa: $z$, il token [class] (768). 16–18: coseno con `Z_good` (**200 prodotti buoni**, offline); $s(x)=1-\max\cos$; **$s>\tau\Rightarrow$ difettoso** (Lezione 19). **Esempio**: $\max\cos=0{,}97$, $s=1-0{,}97=0{,}03$; $\tau=0{,}10$: $0{,}03<0{,}10\Rightarrow$ **buono**.

> 💡 **È il rilevamento di anomalie della Lezione 19 con un altro embedding (nota didattica).** `Z_good` è la matrice $200\times 768$ degli embedding dei pezzi buoni, calcolata una volta. Il punteggio $1-\max\cos$ è la distanza coseno dal vicino più prossimo (k-NN con $k=1$, Lezione 19, §7.18). `nn.Identity()` sostituisce la testa con una funzione che restituisce il suo ingresso: la rete restituisce quindi il token [class] invece dei 1000 punteggi. La soglia $\tau$ va scelta su pezzi buoni di validazione (percentile), non a occhio.

#### **1.10. La classificazione delle immagini oggi: le migliori CNN e i migliori ViT**

**Stesso protocollo di valutazione** (pesi torchvision, validazione ImageNet-1k); † pre-addestrato su 3,6 miliardi di immagini (SWAG):

| Modello | Tipo | Anno | Parametri | GFLOPs | Top-1 (%) |
|---|---|---:|---:|---:|---:|
| VGG16 | CNN | 2014 | 138,4 M | 15,5 | 71,6 |
| MobileNetV3-Large | CNN (edge) | 2019 | 5,5 M | 0,22 | 75,3 |
| ResNet-50 (nuova ricetta) | CNN | 2015 | 25,6 M | 4,1 | 80,9 |
| ViT-B/16 | ViT | 2020 | 86,6 M | 17,6 | 81,1 |
| ConvNeXt-Base | CNN | 2022 | 88,6 M | 15,4 | 84,1 |
| ViT-B/16 + pre-addestramento SWAG† | ViT | 2022 | 86,9 M | 55,5 | 85,3 |
| RegNetY-128GF + SWAG† | CNN | 2022 | 644,8 M | 374,6 | 88,2 |
| ViT-H/14 + SWAG† | ViT | 2022 | 633,5 M | 1016,7 | 88,6 |

**I migliori riportati negli articoli** (dati di pre-addestramento extra, protocolli diversi); **la migliore CNN e il miglior ViT entro 1 punto**:

| Modello | Tipo | Anno | Parametri | Top-1 (%) |
|---|---|---:|---:|---:|
| DINOv3 ViT-7B, congelato + testa lineare | ViT | 2025 | 6,7 B | 88,4 |
| EVA-02-L | ViT | 2023 | 304 M | 90,0 |
| InternImage-G (conv. deformabili) | CNN | 2023 | 3 B | 90,1 |
| CoCa (encoder ViT-G) | ViT | 2022 | 2,1 B | 91,0 |

**Perché tutti si fermano al 90–91%**:

- **etichette sbagliate**: almeno il **6%** delle etichette di test;
- **una sola etichetta, molti oggetti** nella stessa foto;
- **classi ambigue**: **metà degli «errori» residui non sono errori**.

**Insiemi di riferimento usati oggi**: **ImageNet-ReaL** (stesse immagini, multi-etichette corrette); **ImageNet-V2** (immagini nuove: da −11 a −14 punti); **ImageNet-A/R/Sketch, ObjectNet** (robustezza). **DINOv3 ViT-7B**: 88,4 (val) → 90,4 (ReaL) → 81,4 (V2).

> 💡 **Leggere le due tabelle (nota didattica).** «Nuova ricetta» significa che la stessa ResNet-50 del 2015, riaddestrata con augmentation e ottimizzazione moderne (Lezione 22), passa da 76% a 80,9%: la ricetta conta quanto l’architettura. Il calo di 7 punti su ImageNet-V2, con immagini raccolte allo stesso modo ma nuove, è un esempio di **progettazione sul test** ripetuta per anni dalla comunità sullo stesso insieme di validazione (Lezione 14, §4.4): i modelli si sono adattati un po’ a quelle 50 000 immagini. I GFLOPs sono miliardi di operazioni per immagine: ViT-H/14 + SWAG costa 1017, cioè 250 volte ResNet-50, per 7,7 punti in più.

---

### **2. Teoria: l’AIoT (Artificial Intelligence of Things)**


Una nuova frontiera per le applicazioni.

#### **2.1. Definizione**

> 📌 **Definizione.** L’**AIoT** combina l’**infrastruttura IoT** (sensori, dispositivi, reti) con i **modelli di AI**: i dispositivi **raccolgono dati dai processi fisici**, l’AI **trasforma i dati in previsioni, allarmi, azioni ottimizzate e supporto alle decisioni**.

- **I dati in tempo reale sono il valore chiave** di ogni applicazione AIoT.
- **L’intelligenza può girare nel cloud, all’edge (gateway) o sul dispositivo**: la scelta dipende da **latenza, banda, privacy, energia e affidabilità**.

**Input**: segnali fisici ⇒ **Output**: decisioni e azioni.

<!-- SOURCE_VISUAL id="IS24-V06" source="L24-web.pdf" page="15" type="figura" description="Sistema AIoT dai segnali fisici alle decisioni" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Quattro obiettivi di progetto per un sistema AI+IoT**:

| Più **robusto** | Più **sicuro** | Più **scalabile** | Più **prezioso** |
|---|---|---|---|
| **autocalibrazione, autodiagnosi**, riduzione adattiva del rumore | **controllare la qualità del dato** (152 gradi: errore del sensore, o il motore sta fondendo?) | **molti protocolli e formati di dati**, per lavorare con molti dispositivi e servizi | **stessi componenti, ma il SW rende il dispositivo più attraente** |

> 💡 **Il dove in una tabella (nota didattica).** I vincoli spingono in direzioni diverse (Lezione 5):
>
> | Vincolo | Spinge verso | Esempio |
> |---|---|---|
> | **latenza** bassa | dispositivo o edge | fermare un nastro se compare un pezzo difettoso |
> | **banda** limitata | dispositivo o edge, inviando solo eventi | telecamere in un campo agricolo con copertura 4G |
> | **privacy** | dispositivo | parola di attivazione di un altoparlante (§2.3) |
> | **energia** | dispositivo con modello minuscolo, oppure cloud se il calcolo è raro | sensore a batteria |
> | **affidabilità** | edge, che funziona anche senza rete | controllo di un impianto |
> | modelli **grandi** e **riaddestramento** | cloud | aggiornamento periodico del modello di tutta la flotta |
>
> **Il peso della banda in numeri.** Una telecamera Full HD a 30 fotogrammi al secondo compressa in H.264 produce circa 4 Mbit/s. In un giorno: $4\ \text{Mbit/s}\times 86\,400\ \text{s}=345\,600\ \text{Mbit}\approx 43\ \text{GB}$ per **una sola** telecamera. Cento telecamere inviate tutte al cloud superano i 4 TB al giorno; un modello all’edge che invia solo gli eventi riduce il traffico di ordini di grandezza.

> ⚠️ **I 152 gradi (nota didattica).** Un valore fuori scala è il caso tipico in cui un sistema deve chiedersi se l’errore è nel **sensore** o nel **processo**. Un modello che riceve 152 °C e lo tratta come vero può fermare una linea per niente o, peggio, ignorare un guasto reale se lo scarta come outlier. Servono controlli incrociati (un secondo sensore, la coerenza con corrente e vibrazione, Lezione 21, §1.13) e l’autodiagnosi del sensore: è il «controllo della qualità del dato» della Lezione 6.

#### **2.2. Che cosa abilita l’AIoT: una pila, HW e SW**

**Mondo fisico** (immagini, vibrazione, temperatura, suono) → **Dispositivo** (*percepire, calcolare, agire*) → BLE, Zigbee, fieldbus, Wi-Fi → **Gateway edge** (*aggregare, inferire, lavorare offline*) → MQTT, HTTPS, Wi-Fi, 4G/5G → **Cloud** (*memorizzare, addestrare, analizzare, gestire*) → decisioni, allarmi, dashboard. In senso inverso: **comandi, nuovo firmware e nuovi modelli di ML (aggiornamenti OTA)**.

| Livello | HW | SW |
|---|---|---|
| **dispositivo** | sensori, microcontrollore (ESP32, STM32), piccola NPU | RTOS (FreeRTOS, ThreadX), LiteRT Micro |
| **gateway edge** | PC industriale, Raspberry Pi, Jetson; Hailo, Edge TPU | Linux embedded, container, AWS IoT Greengrass, ONNX Runtime |
| **cloud** | server con GPU e TPU | hub IoT (AWS IoT Core, Azure IoT Hub), storage, servizi di ML (MLaaS) |

<!-- SOURCE_VISUAL id="IS24-V07" source="L24-web.pdf" page="16" type="schema" description="Pila AIoT dispositivo, gateway edge e cloud con protocolli e aggiornamenti OTA" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Gli elenchi completi di sensori, schede, acceleratori, protocolli e runtime sono in appendice (§13.2, per riferimento, non d’esame).

#### **2.3. Un esempio di AIoT: gli altoparlanti intelligenti**

- La **parola di attivazione** è riconosciuta **in locale** da un **piccolo modello di NLP**; la voce va al cloud **solo dopo l’attivazione**.
- **Tipo**: sorgente di dati **dinamica** (Lezione 5).
- **Approccio ibrido**: elaborazione locale → attivazione; elaborazione remota → NLP (oggi anche in parte locale).
- **Il livello fog è quasi assente**: **senza connessione il dispositivo è fortemente limitato**.

<!-- SOURCE_VISUAL id="IS24-V08" source="L24-web.pdf" page="17" type="figura" description="Altoparlanti intelligenti e flusso tra elaborazione locale e cloud" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché la parola di attivazione è locale (nota didattica).** Tre motivi in uno: **privacy** (nulla esce finché non si dice la parola), **banda** (non si trasmette audio continuo), **energia** (un modello minuscolo, tipo TinyML, §6, gira con pochi milliwatt). Il prezzo sono i falsi positivi: un modello piccolo a volte si attiva per una parola simile, ed è il motivo per cui la seconda verifica avviene nel cloud.

#### **2.4. Un esempio di AIoT: l’illuminazione intelligente e connessa**

**Dispositivi edge**: lampadine e interruttore. **Sorgente dei comandi**: NLP via cloud e **direttamente al bridge**.

<!-- SOURCE_VISUAL id="IS24-V09" source="L24-web.pdf" page="18" type="figura" description="Illuminazione connessa con lampadine, interruttore, bridge e comandi vocali" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Teoria: la sicurezza dell’IoT**


Una nuova frontiera per le applicazioni.

#### **3.1. Perché la sicurezza dell’IoT**

- **Il numero dei dispositivi e la loro rilevanza pongono enormi problemi di sicurezza.** Si pensi a: **pacemaker**; **sistemi semaforici**; **sistemi di controllo e guida delle auto**.
- **Interazioni complesse → più problemi di sicurezza.**

<!-- SOURCE_VISUAL id="IS24-V10" source="L24-web.pdf" page="20" type="figura" description="Esempi di dispositivi IoT critici per la sicurezza" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. Sei principi della sicurezza IoT lungo la pila**

| Area | Che cosa fa il progettista | Esempio in fabbrica |
|---|---|---|
| **A. Manomissione fisica** | **secure boot**, porte di debug chiuse, **rilevamento dell’apertura** dell’involucro | una telecamera della linea viene aperta: **cancella le sue chiavi e si ferma** |
| **B. Solo connessioni in uscita** | **il dispositivo chiama il cloud su TLS**; **nessuno può chiamare il dispositivo** (nessuna porta in ingresso aperta) | un sensore di vibrazione invia dati ogni 10 s; nessuno può collegarsi a esso |
| **C. Identità** | **un certificato per dispositivo**; **mai una password di default** (prima regola di ETSI EN 303 645) | 500 sensori, 500 certificati: **uno rubato viene revocato da solo** |
| **D. Ciclo di vita** | **aggiornamenti firmati (OTA)**, revoca, **dismissione sicura** | un gateway dismesso viene **cancellato** e il suo certificato **revocato** |

<!-- SOURCE_VISUAL id="IS24-V11" source="L24-web.pdf" page="21" type="figura" description="Illustrazione dei principi di sicurezza IoT lungo la pila" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**I sei principi in dettaglio** (appendice, versione completa):

| A. **Dispositivo sicuro** (1, 2) | B. **Comunicazioni sicure** (3, 4) | C. **Cloud sicuro** (5) | D. **Ciclo di vita sicuro** (6) |
|---|---|---|---|
| il dispositivo **si controlla da sé** (secure boot) ed **elabora localmente i dati sensibili**; **resiste alla manomissione**: apertura dell’involucro, sondaggio della memoria, sostituzione del firmware | **il dispositivo avvia ogni connessione**: nessuna porta in ingresso aperta da attaccare; i messaggi sono **autenticati, cifrati (TLS) e controllati** (formato, frequenza, comandi) | **ogni dispositivo ha la propria identità** (certificato); **dispositivo e cloud si autenticano a vicenda**; dati **cifrati in transito e a riposo** | **controllo remoto e aggiornamenti OTA per tutto il periodo di supporto**, fino alla **dismissione sicura** del dispositivo |

**Ancora attuali**: le stesse quattro aree compaiono in **ETSI EN 303 645** (base per l’IoT consumer) e nei **requisiti essenziali del Cyber Resilience Act** europeo. (PLM: *Product Lifecycle Management*; ERP: *Enterprise Resource Planning*; SCM: *Supply Chain Management*; CRM: *Customer Relationship Management*.)

> 💡 **Perché «solo in uscita» (nota didattica).** Un dispositivo che accetta connessioni in ingresso è raggiungibile da chiunque in rete: basta una vulnerabilità del suo servizio per prenderne il controllo (è così che si sono formate botnet come Mirai, con telecamere che accettavano login con password di default). Se è sempre il dispositivo a chiamare il cloud, all’esterno non c’è nessuna porta da attaccare, e i comandi arrivano sulla stessa connessione TLS autenticata.

#### **3.3. OTA: aggiornamenti sicuri via rete**

> 📌 **Definizione.** Un aggiornamento **OTA** (*over-the-air*) **consegna software, firmware o modelli di ML nuovi** a dispositivi **già sul campo**, **attraverso la rete**, **senza accesso fisico**.

- **Perché**: **patch di sicurezza**, nuove funzioni, **nuovi modelli di ML** per migliaia di dispositivi.
- **Trasporto sicuro**: **HTTPS o MQTT su TLS**.
- **Immagine fidata**: **il produttore firma l’aggiornamento**; il dispositivo **verifica firma e hash prima di installarlo**, e **torna indietro (rollback) se la nuova versione fallisce**.

**Input**: nuova immagine firmata (firmware o modello) ⇒ **Output**: dispositivi aggiornati, o ripristinati.

**Auto**: nel **dicembre 2023 Tesla ha risolto un richiamo di circa 2 milioni di veicoli** (Autopilot) **con un solo aggiornamento OTA**. **Da luglio 2024 ogni auto nuova venduta nell’UE richiede un sistema di aggiornamento software certificato (UNECE R156)**: un aggiornamento non firmato a un’auto è un **rischio per la sicurezza**.

<!-- SOURCE_VISUAL id="IS24-V12" source="L24-web.pdf" page="22" type="figura" description="Aggiornamento OTA firmato inviato ai dispositivi sul campo" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**MQTT**: messaggistica **publish/subscribe leggera** per dispositivi con risorse limitate. **TLS**: **cifra i dati e autentica gli estremi**. **HTTPS**: HTTP su TLS.

> ⚠️ **MQTT da solo non è sicuro (nota didattica).** HTTPS è HTTP su TLS; MQTT invece è un protocollo di messaggistica che **diventa sicuro solo se trasportato su TLS** (tipicamente sulla porta 8883) e con autenticazione dei dispositivi. Un aggiornamento OTA robusto richiede inoltre:
> - la **firma digitale del firmware**, verificata dal dispositivo prima di installarlo: TLS protegge il canale, ma non dice se il firmware viene dal produttore;
> - la protezione dal **rollback** a versioni vecchie e vulnerabili;
> - un meccanismo di **ripristino** (per esempio due partizioni A/B) se l’aggiornamento fallisce a metà.

> 💡 **Un sensore che pubblica su MQTT con TLS (ricostruzione didattica, non trascritta dalle slide).**
> ```python
> import json
> import paho.mqtt.client as mqtt
>
> client = mqtt.Client(mqtt.CallbackAPIVersion.VERSION2, client_id="sensore-42")
> client.tls_set(ca_certs="ca.pem", certfile="device.crt", keyfile="device.key")
> client.connect("broker.example.com", 8883)      # MQTT su TLS
> client.loop_start()
> info = client.publish("fabbrica/linea1/temperatura", json.dumps({"t": 71.3}), qos=1)
> info.wait_for_publish()
> client.loop_stop()
> client.disconnect()
> ```
> Il dispositivo si autentica con un **proprio certificato** (`device.crt`), non con una password condivisa: è la pratica dei servizi IoT cloud (principio C del §3.2), e risponde al divieto di credenziali cablate nel codice del Cyber Resilience Act.

#### **3.4. Le regole della sicurezza IoT: che cosa deve sapere un progettista**

**Linea del tempo del Cyber Resilience Act (UE, obbligatorio, marchio CE)**: **10 dicembre 2024** entrata in vigore; **11 settembre 2026** obblighi di segnalazione (oggi); **11 dicembre 2027** applicazione completa. **NIST CSF 2.0** (USA, volontario).

<!-- SOURCE_VISUAL id="IS24-V13" source="L24-web.pdf" page="23" type="schema" description="Linea del tempo del CRA e le sei funzioni del NIST CSF 2.0 con Govern al centro" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**EU Cyber Resilience Act**:

1. **Sicurezza fin dalla progettazione**: valutazione del rischio dal primo passo; **nessuna password di default**; superficie d’attacco minima.
2. **Aggiornamenti per tutto il periodo di supporto**: patch di sicurezza, consegnate OTA.
3. **Segnalare le vulnerabilità**: quelle sfruttate **a ENISA entro 24 h e 72 h**.

**NIST CSF 2.0**: Govern (**nuova nella 2.0**: chi è responsabile, regole per i fornitori), Identify, Protect, Detect, Respond, Recover. **Auto**: **UNECE R155 e R156** (aggiornamenti software), su tutte le auto nuove nell’UE da luglio 2024. I dettagli (tutti i requisiti del CRA, l’esempio NIST su una fabbrica intelligente) sono qui sotto.

##### **3.4.1. Il Cyber Resilience Act (UE) in dettaglio**

- **Sicurezza obbligatoria**: il **Regolamento (UE) 2024/2847** fissa requisiti di cybersicurezza per **ogni prodotto con elementi digitali** (hardware o software) venduto nell’UE che **può collegarsi a un dispositivo o a una rete**.
- **Il marchio CE certifica la conformità.**
- **Supporto lungo il ciclo di vita**: aggiornamenti di sicurezza durante il periodo di supporto; gestione e segnalazione delle vulnerabilità.

**Segnalazioni**: i fabbricanti segnalano le **vulnerabilità attivamente sfruttate e gli incidenti gravi** a **ENISA e al CSIRT nazionale**: **preallarme entro 24 h**, **notifica entro 72 h**, **relazione finale entro 14 giorni dalla correzione** (1 mese per gli incidenti).

**«Security by design» obbligatoria per i fabbricanti**:

- **valutazione e mitigazione del rischio**: valutazione del rischio di cybersicurezza **dal primo passo di progettazione**;
- **impostazioni sicure di default**: i prodotti devono essere consegnati con configurazioni sicure «**out of the box**» (per esempio **nessuna password di default universale**);
- **protezione dalle minacce comuni** (per esempio **TLS** per la trasmissione) e dagli exploit come gli **attacchi di injection**;
- **superficie d’attacco minimizzata**: limitare funzioni inutili, porte aperte o interfacce esposte;
- **pratiche di sviluppo sicuro** (per esempio **evitare credenziali cablate nel codice**);
- **sicurezza del ciclo di vita**: aggiornamenti di software/firmware per correggere le vulnerabilità;
- **trasparenza e documentazione**.

> 💡 **Stato operativo (dall’audit del 20 novembre 2026).** ENISA (*European Union Agency for Cybersecurity*, Agenzia dell’Unione europea per la cybersicurezza) ha attivato la **Single Reporting Platform** l’11 settembre 2026, in coincidenza con l’applicazione degli obblighi dell’articolo 14 per i fabbricanti. La versione iniziale consente le segnalazioni obbligatorie di vulnerabilità attivamente sfruttate e incidenti gravi con impatto sui prodotti digitali; la segnalazione volontaria dell’articolo 15 sarà introdotta in una fase successiva. Gli obblighi pertinenti agli steward open-source hanno una decorrenza successiva, l’11 dicembre 2027. ([FAQ ENISA sulla piattaforma](https://www.enisa.europa.eu/topics/product-security/vulnerability-services/eu-incident-response-and-cyber-crisis-management/single-reporting-platform-srp/frequently-asked-questions)) Come per l’AI Act (Lezione 2), conviene ricordare la logica a scadenze successive più che una sola data.

##### **3.4.2. NIST CSF 2.0 (USA)**

- **Framework statunitense volontario**: **NIST Cybersecurity Framework 2.0** (febbraio 2024). Per i dispositivi NIST aggiunge **baseline IoT** (**NIST IR 8259** per i fabbricanti, **NIST IR 8425** per l’IoT consumer).
- **Sei funzioni**: **Govern** (nuova nella 2.0), **Identify, Protect, Detect, Respond, Recover**.

**Esempio: una fabbrica intelligente con sensori IoT**:

| Funzione | Azione |
|---|---|
| **Govern** | decidere **chi è responsabile** della sicurezza dei sensori, e le **regole per i fornitori** |
| **Identify** | **mappare tutti i sensori** e le loro uscite di dati |
| **Protect** | **cifrare le comunicazioni** dei sensori |
| **Detect** | **monitorare attività insolite** dei sensori |
| **Respond** | **isolare un sensore compromesso** |
| **Recover** | **aggiornare il firmware** e rafforzare la sicurezza |

**UE**: obbligatorio (CRA, marchio CE). **USA**: volontario (NIST); l’etichetta consumer «**Cyber Trust Mark**» è **ancora in fase di avvio nel 2026**.

<!-- SOURCE_VISUAL id="IS24-V14" source="L24-web.pdf" page="88" type="figura" description="Le sei funzioni del NIST CSF 2.0 applicate a una fabbrica intelligente" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Il «Detect» con il ML (nota didattica).** «Monitorare attività insolite dei sensori» è un rilevamento di anomalie sul traffico (frequenza dei messaggi, dimensioni, orari): la stessa tecnica del §9.8 applicata alla rete invece che alle immagini. Un sensore di vibrazione che comincia a inviare dati ogni 100 ms invece che ogni 10 s è un segnale di compromissione.

---

### **4. Strumenti: piattaforme IoT e sistemi operativi in tempo reale**


AWS, MATLAB ThingSpeak, Azure; FreeRTOS, ThreadX.

#### **4.1. AWS IoT: l’idea di base**

**Tutte le piattaforme IoT offrono**:

- **registro dei dispositivi**: **un’identità (certificato) per dispositivo**;
- **messaggistica sicura**: **MQTT su TLS**;
- **memorizzazione e analisi** dei flussi di dati;
- **addestramento del ML nel cloud**, rilascio dei modelli;
- **aggiornamenti OTA** di firmware e modelli.

**Che cosa le distingue**:

- **AWS Greengrass**: codice e modelli di ML **sul gateway locale**, anche **offline**;
- **Azure IoT**: **hot path** (allarmi in secondi), **cold path** (storico per l’addestramento);
- **MATLAB ThingSpeak**: analisi **direttamente in MATLAB**; prototipi, didattica;
- **Eclipse ThreadX**: RTOS **open source** (ex Azure RTOS), **certificato per la sicurezza funzionale**.

**Dal dispositivo al cloud**: **FreeRTOS** sui microcontrollori, **Greengrass** su un gateway locale (edge), **servizi cloud** per dati e analisi.

<!-- SOURCE_VISUAL id="IS24-V15" source="L24-web.pdf" page="25" type="figura" description="Architettura AWS IoT dal dispositivo con FreeRTOS al gateway Greengrass e al cloud" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.2. FreeRTOS per i dispositivi AIoT embedded**

Sistema operativo in tempo reale per microcontrollori.

- **FreeRTOS** è un **kernel di sistema operativo in tempo reale open source (MIT)** per **microcontrollori**, **mantenuto da AWS**, con librerie per **collegare, proteggere e aggiornare** dispositivi piccoli e a bassa potenza.
- Un **microcontrollore** è un **processore semplice e con risorse limitate** che si trova in elettrodomestici, sensori, tracker, automazione industriale e auto. (O una Raspberry Pi 3B.)
- **Libreria OTA**: **firmware firmato su TLS** (§3.3): **patch di sicurezza per tutto il periodo di supporto**, come richiede il CRA.

<!-- SOURCE_VISUAL id="IS24-V16" source="L24-web.pdf" page="26" type="figura" description="Microcontrollori e schede su cui gira FreeRTOS" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.3. Come un RTOS rispetta una scadenza**

Richiamo dalla Lezione 22: **un sistema in tempo reale è corretto solo se il risultato è giusto e arriva prima della scadenza** (Stankovic, 1988); tempo reale **hard e soft**; *jitter*.

- **Priorità**: ogni task ha una priorità; il lavoro **più urgente** riceve **la più alta**.
- **Scheduling preemptive**: quando un task a priorità più alta **diventa pronto**, lo scheduler **sospende il task in esecuzione entro un tempo limitato**.
- **Interrupt**: un evento hardware (nuovo campione del sensore, fotogramma della telecamera, pulsante d’emergenza) esegue una **breve routine che sveglia il task**.

**Tempo di risposta nel caso peggiore $R$ e scadenza $D$**:

$$R=L_{\text{int}}+C_{\text{sw}}+E\le D,$$

con $L_{\text{int}}$ la **latenza dell’interrupt**; $C_{\text{sw}}$ il **tempo di cambio di contesto**; $E$ il **tempo di esecuzione nel caso peggiore** del task.

<!-- SOURCE_VISUAL id="IS24-V17" source="L24-web.pdf" page="27" type="grafico" description="Diagramma temporale con task a bassa priorità di logging interrotto dall'inferenza ad alta priorità e la scadenza" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Esempio**: l’interrupt a **2 ms** interrompe L (bassa priorità, logging); **H** (alta priorità, inferenza) gira per **$E=5$ ms** e **termina a 7 ms**, ben prima della **scadenza a 12 ms** ($D=10$ ms dall’interrupt); poi L riprende. **In questo caso i requisiti sono rispettati: $R\le D$.**

> 💡 **Perché «caso peggiore» (nota didattica).** In un sistema hard real-time non basta che il tempo medio stia sotto la scadenza: deve starci **sempre**. Per questo si usa il tempo di esecuzione massimo misurato o garantito ($E$), non la media, e le reti neurali sui microcontrollori hanno un vantaggio poco noto: il numero di operazioni è fisso per ogni ingresso, quindi $E$ è prevedibile. Un sistema operativo generico (Linux standard) non dà garanzie sul tempo di preemption, un RTOS sì.

#### **4.4. I passi di progetto con un RTOS**

**Input**: requisiti (sensori, dimensione del modello, scadenza, potenza) ⇒ **Output**: un **dispositivo connesso che si può aggiornare via rete**.

1. **Scegliere il microcontrollore**: **la decisione chiave**; deve **contenere il modello e rispettare la scadenza**.
2. **Scaricare l’RTOS e le librerie** (connettività, sicurezza, OTA).
3. **Sviluppare l’applicazione.**
4. **Rilasciare e gestire la flotta** (AWS IoT Core direttamente, o attraverso un gateway Greengrass).

**Il modello ci sta?** (richiamo dalla Lezione 23): $\text{Mem}\approx\#\text{Par}\times b/8$. Con **$\#\text{Par}=250\,000$** parametri, **$b=8$ bit (INT8) = 1 byte**: **Mem ≈ 250 kB**; **MCU con 1 MB di flash: ci sta**.

<!-- SOURCE_VISUAL id="IS24-V18" source="L24-web.pdf" page="28" type="figura" description="Passi di progetto con RTOS e verifica della memoria del modello" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Flash e RAM (nota didattica).** I 250 kB di pesi stanno nella **flash** (memoria del programma), ma durante l’inferenza servono anche le **attivazioni** in **RAM**, che su un MCU è spesso 10 volte più piccola della flash (§6.3). Uno strato con una feature map $32\times 32\times 32$ in INT8 occupa 32 kB di RAM: la verifica va fatta su entrambe le memorie.

#### **4.5. AWS IoT Greengrass: da una lampada a un’app**

**Gateway edge su una macchina locale.** **Greengrass** (v2; **release 2.17 nell’aprile 2026**) esegue **componenti** (funzioni, container, inferenza di ML), **scambia messaggi con i dispositivi locali**, **continua a funzionare offline** e **si sincronizza con il cloud**. Un **nucleo più leggero** (*nucleus lite*) è pensato per dispositivi piccoli. Gli stessi dati raggiungono i servizi AWS, app di terze parti o software on-premise. **Piattaforma di ML**: Amazon SageMaker AI (servizi cloud, §7).

<!-- SOURCE_VISUAL id="IS24-V19" source="L24-web.pdf" page="29" type="figura" description="AWS IoT Greengrass dalla lampada al gateway e all'app" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.6. MATLAB ThingSpeak: online e offline**

**Dispositivi intelligenti connessi** → **ThingSpeak** (aggregazione e analisi dei dati) ↔ **MATLAB** (sviluppo di algoritmi, analisi dei sensori). (1) **Raccogliere i dati dei sensori mentre si è offline**; (2) **collegarsi periodicamente e inviare tutto insieme** con `bulk_update`; (3) **analizzare** con `thingSpeakRead` in MATLAB.

<!-- SOURCE_VISUAL id="IS24-V20" source="L24-web.pdf" page="30" type="figura" description="Flusso tra dispositivi, ThingSpeak e MATLAB" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.7. Microsoft Azure IoT ed Eclipse ThreadX (appendice)**

- **L’architettura di riferimento è ancora valida come concetto**: **cose → gateway cloud → intuizioni → azione**. **Hot path**: elaborazione di flussi, **allarmi in secondi**. **Warm e cold path**: **storico memorizzato** per report e per **addestrare i modelli di ML**.
- **Servizi nel 2026**: **IoT Hub con Device Provisioning Service**, **Azure Device Registry**, **Microsoft Fabric** per l’analisi. **IoT Central sarà ritirato il 20 settembre 2029.**

<!-- SOURCE_VISUAL id="IS24-V21" source="L24-web.pdf" page="98" type="schema" description="Architettura di riferimento di Azure IoT con hot, warm e cold path" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Eclipse ThreadX, ex Azure RTOS**:

- **suite di sviluppo embedded**: un **RTOS piccolo e veloce** per microcontrollori a 32 bit, più rete (**NetX Duo**), file system (**FileX**), interfaccia grafica (**GUIX**) e USB (**USBX**);
- **open source**: Microsoft ha **ceduto Azure RTOS alla Eclipse Foundation** (2023–24); ora è **Eclipse ThreadX**, con la licenza permissiva **MIT**, sostenuto dalla **ThreadX Alliance** (2024);
- **sicuro (safety)**: il **primo RTOS open source con certificazione di sicurezza funzionale** per prodotti critici;
- **sicuro (security)**: **IPsec** (livello IP), **TLS e DTLS** (livello socket); **aggiornamenti firmware OTA**;
- **collaudato sul mercato**: **miliardi di dispositivi installati**; portabile sui microcontrollori a 32 bit più diffusi.

<!-- SOURCE_VISUAL id="IS24-V22" source="L24-web.pdf" page="99" type="figura" description="Componenti di Eclipse ThreadX" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Un esempio: Microsoft Azure.** I dati dei dispositivi e dei sensori vengono **raccolti, elaborati e analizzati (ML) nel cloud**; **intuizioni e decisioni tornano** ad analisti e tecnici.

<!-- SOURCE_VISUAL id="IS24-V23" source="L24-web.pdf" page="100" type="schema" description="Esempio di flusso di dati con Microsoft Azure" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Strumenti: schede e acceleratori per l’edge AI**


Da un microcontrollore a 8 bit a un acceleratore neurale.

#### **5.1. Dentro una scheda per l’edge AI**

**La scheda**: **CPU** (OS, app, pre-elaborazione), **memoria** (RAM + flash), **NPU** (coprocessore neurale, integrato nel chip o esterno: PCIe, M.2, USB). **Collegamenti**: telecamere (**MIPI CSI**, USB); sensori e pulsanti (**GPIO, I2C, SPI**); attuatori e relè (**GPIO, PWM**); rete e alimentazione (Ethernet, Wi-Fi, 5 V); risultati (MQTT verso il cloud).

<!-- SOURCE_VISUAL id="IS24-V24" source="L24-web.pdf" page="32" type="schema" description="Blocchi di una scheda per l'edge AI e quattro esempi di formato: Pi 5 con AI HAT+, Coral USB, ESP32-CAM, Arduino MKR" reason="Schema e immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **GPIO** (*General-Purpose Input/Output*): un **pin digitale impostato dal software** come ingresso (pulsante, sensore) o uscita (relè, LED).
- **NPU**: coprocessore per le **moltiplicazioni-somme** delle reti neurali: **integrata** (più economica, meno potenza) o **acceleratore esterno** (più TOPS, aggiunto dopo).
- **Stessi blocchi, formati molto diversi.**

#### **5.2. L’hardware per l’edge AI oggi: confrontare con un numero**

| Dispositivo | Tipo | TOPS di picco | Prezzo |
|---|---|---|---|
| **STM32N6** (ST, 2024) | MCU con NPU | 0,6 (INT8) | prezzo da MCU |
| **Coral USB Accelerator** | Edge TPU su USB | 4 (INT8) | ≈ 60 USD |
| **RPi AI HAT+ (Hailo-8L)** | add-on per Pi 5 | 13 (INT8) | ≈ 70 USD |
| **RPi AI HAT+ (Hailo-8)** | add-on per Pi 5 | 26 (INT8) | ≈ 110 USD |
| **RPi AI HAT+ 2 (Hailo-10H)** | add-on per Pi 5, 8 GB | 40 (INT4) | ≈ 130 USD |
| **Jetson Orin Nano Super** | modulo con GPU | 67 (INT8, sparso) | 249 USD |

- **TOPS** = tera ($10^{12}$) operazioni al secondo, **di picco**: **confrontare solo alla stessa precisione, e per watt** (Edge TPU: **4 TOPS a circa 2 W**; un modulo Jetson **fino a 25 W**).
- **I TOPS di picco non sono la velocità sulla propria rete**: **misurare latenza (ms) e fps sul proprio modello** (Lezione 23, §8.1).
- **Ogni chip richiede una conversione della rete addestrata** (STM32Cube.AI, compilatore Edge TPU, compilatore Hailo, TensorRT): **addestrare → convertire → inferire sulla scheda** (toolchain ST).

> 💡 **Tre trappole nella tabella (nota didattica).** 1) I 40 TOPS del Hailo-10H sono **INT4**: alla stessa precisione dell’INT8 sarebbero circa la metà (§13.4). 2) I 67 TOPS del Jetson sono **«sparsi»**: valgono solo con reti potate con il pattern 2:4 (Lezione 23, §7.16); in denso sono circa la metà. 3) Per watt: 4 TOPS / 2 W = 2 TOPS/W per l’Edge TPU, 67 / 25 ≈ 2,7 TOPS/W per il Jetson, 3 TOPS/W dichiarati per l’NPU dell’STM32N6 (§6.3). Ridotte allo stesso denominatore, le differenze si assottigliano.

---

### **6. Strumenti: TinyML**


Machine learning per dispositivi piccolissimi.

#### **6.1. TinyML**

- Un **sottoinsieme del machine learning** che permette ai modelli di ML di girare su **dispositivi piccoli e a bassa potenza**, come i **microcontrollori**.
- I modelli TinyML sono **addestrati tipicamente su potenti server cloud** e poi **rilasciati sui dispositivi edge**, dove eseguono l’**inferenza con bassa latenza e basso consumo**.

<!-- SOURCE_VISUAL id="IS24-V25" source="L24-web.pdf" page="35" type="figura" description="Illustrazione del TinyML" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2. TinyML: esempi e applicazioni**

**Esempi**: **termostato intelligente** che impara le preferenze di riscaldamento e raffreddamento; **fitness tracker** indossabile: livelli di attività e qualità del sonno; **altoparlante intelligente** che riconosce la voce; **telecamera di sicurezza** che rileva gli intrusi e invia un allarme; **macchina della fabbrica intelligente** che si monitora e **prevede la manutenzione**.

**Aree applicative**: **dispositivi indossabili** (frequenza cardiaca, gesti); **IoT industriale** (**manutenzione predittiva, rilevamento di anomalie**); **casa intelligente** (movimento, voce, energia); agricoltura, sanità, trasporti.

<!-- SOURCE_VISUAL id="IS24-V26" source="L24-web.pdf" page="36" type="figura" description="Esempi di dispositivi TinyML" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.3. Tradizionale contro TinyML: ordini di grandezza**

| | Tradizionale (PC, server) | Dispositivo piccolissimo (MCU) |
|---|---|---|
| **calcolo** | 1 – 5 GHz, molti core | 1 – 800 MHz, un core |
| **memoria (RAM)** | 4 – 128 GB | **2 KB – 4 MB** |
| **memoria di massa** | 64 GB – 4 TB | 32 KB – 4 MB (flash) |
| **potenza** | 30 – 100 W | **150 µW – qualche 100 mW** |

**Il divario di memoria è di circa $10^4$**: **un modello che sta su un portatile non sta su un microcontrollore senza quantizzazione e pruning** (Lezione 23). RAM del portatile: 16 GB; RAM di un MCU: 1 MB; rapporto: $16\ \text{GB}/1\ \text{MB}\approx 16\,000\approx 10^4$.

**Esempio: la fascia alta degli MCU oggi**, **STM32N6** (ST, 2024): CPU **Cortex-M55 a 800 MHz**; RAM **4,2 MB** sul chip; **NPU 0,6 TOPS, 3 TOPS/W**. Intervalli come ordini di grandezza; valori dei dispositivi piccolissimi aggiornati agli MCU con NPU (2024–26).

> 💡 **Quanto è piccolo un microcontrollore (nota didattica).** L’ATmega328P dell’Arduino UNO (§13.3) ha 32 KB di flash per il programma e **2 KB di RAM**. Un modello con 1000 parametri quantizzati a 8 bit occupa circa 1 KB e ci sta. MobileNetV2, con circa 3,4 milioni di parametri, occupa circa 3,4 MB anche in INT8: **mille volte** la memoria disponibile. Per questo i microcontrollori piccoli ospitano **alberi di decisione** o **reti minuscole** (§8.6, il sensore con machine learning core), mentre la visione richiede schede con acceleratori. 150 µW con una batteria a bottone da 1 Wh durano circa 9 mesi.

#### **6.4. LiteRT (prima TensorFlow Lite)**

**LiteRT** (chiamato **TensorFlow Lite fino al 2024**) è il **runtime di Google per il ML sul dispositivo** su telefoni, Linux embedded e microcontrollori (**LiteRT for Microcontrollers**). Permette l’**inferenza con bassa latenza e un binario piccolo**, ed esegue modelli **convertiti da TensorFlow, Keras, JAX e PyTorch**.

Richiamo dalla Lezione 23: la pipeline della figura (**distillazione, quantizzazione, compilazione**) è la **toolchain edge**: quantizzazione, pruning e distillazione **fanno stare la rete nel dispositivo**.

<!-- SOURCE_VISUAL id="IS24-V27" source="L24-web.pdf" page="38" type="schema" description="Pipeline LiteRT con distillazione, quantizzazione e compilazione verso il dispositivo" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Dal modello Keras al file per il dispositivo (ricostruzione didattica, non trascritta dalle slide).**
> ```python
> import tensorflow as tf
>
> converter = tf.lite.TFLiteConverter.from_keras_model(model)   # model: modello Keras addestrato
> converter.optimizations = [tf.lite.Optimize.DEFAULT]           # quantizzazione dei pesi
> tflite_model = converter.convert()
>
> with open("model.tflite", "wb") as f:
>     f.write(tflite_model)
> ```
> Con `Optimize.DEFAULT` i pesi vengono quantizzati a 8 bit (Lezione 23, §7.10). Per portare a interi anche le **attivazioni**, come richiedono molti microcontrollori e acceleratori, bisogna fornire al convertitore un piccolo **dataset rappresentativo**, con cui stimare gli intervalli dei valori (i campioni di calibrazione della Lezione 23). Dal 2024 TensorFlow Lite si chiama **LiteRT**, ma il formato `.tflite` è lo stesso.

---

### **7. Strumenti: i servizi cloud per il ML**


*Machine Learning as a Service* (MLaaS).

#### **7.1. Il machine learning «come servizio» (MLaaS)**

> 📌 **Definizione.** Il ***Machine Learning as a Service*** (MLaaS) è una **definizione ombrello** delle piattaforme cloud **automatiche e semi-automatiche** che coprono la **maggior parte dei compiti infrastrutturali** di un progetto di ML.

- **Compiti coperti**: **pre-elaborazione dei dati**, **addestramento del modello**, **valutazione del modello**, **gestione dei dataset**.
- I **risultati delle previsioni** si possono collegare, per esempio, alla **propria infrastruttura IT interna** attraverso **API REST**.
  - Un’**API REST** (*Representational State Transfer*) è un **insieme di regole** che permette ad applicazioni software diverse di **comunicare sul web** usando i **metodi HTTP standard** come **GET, POST, PUT e DELETE**.

> 💡 **Chiamare un modello via REST (ricostruzione didattica, non trascritta dalle slide).**
> ```python
> import requests
>
> resp = requests.post(
>     "https://esempio-endpoint.cloud/models/qualita/predict",   # indirizzo di esempio
>     headers={"Authorization": "Bearer <token>"},
>     json={"instances": [[5.1, 3.5, 1.4, 0.2]]},
>     timeout=10,
> )
> resp.raise_for_status()
> print(resp.json())          # per esempio {"predictions": ["setosa"]}
> ```
> Il sistema aziendale non deve sapere nulla del modello: invia dati in JSON con un **POST** e riceve la predizione. Il prezzo di questa comodità è che **i dati escono dall’azienda** a ogni chiamata: valgono le regole sulla sovranità dei dati della Lezione 15.

#### **7.2. I modelli di servizio cloud: IaaS, PaaS, SaaS**

| | **IaaS: infrastruttura** (*ospitare*) | **PaaS: piattaforma** (*costruire*) | **SaaS: software** (*consumare*) |
|---|---|---|---|
| che cosa | il fornitore **affitta storage, rete, server** e altre risorse di calcolo, **a consumo**; **si gestiscono OS, framework e codice** | il fornitore **gestisce infrastruttura e runtime**; **si portano dati e codice** e si costruisce l’applicazione | il fornitore consegna **un’applicazione completa via Internet**; gli utenti **si abbonano** e la usano via web o API |
| esempio di ML | una **macchina virtuale con GPU** per addestrare la propria rete | una **piattaforma di ML gestita** (job di addestramento, AutoML, rilascio di endpoint) | un’**API pronta di visione o di parlato** chiamata via REST |

**Da IaaS a SaaS: meno controllo, meno sforzo di gestione.**

<!-- SOURCE_VISUAL id="IS24-V28" source="L24-web.pdf" page="41" type="schema" description="I tre modelli di servizio cloud IaaS, PaaS e SaaS" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Il modello a tre livelli (appendice)**: **nota, le cose cambiano in fretta…** I nomi dei fornitori illustrano i livelli: **SaaS in alto, PaaS in mezzo, IaaS alla base**.

<!-- SOURCE_VISUAL id="IS24-V29" source="L24-web.pdf" page="101" type="schema" description="Il modello a tre livelli dei servizi cloud con i nomi dei fornitori" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Chi gestisce che cosa (nota didattica).** Il criterio per non confondere i tre livelli è **fin dove arriva la responsabilità del cliente**:
>
> | Che cosa | On-premise | IaaS | PaaS | SaaS |
> |---|---|---|---|---|
> | hardware, rete, data center | cliente | fornitore | fornitore | fornitore |
> | sistema operativo, librerie | cliente | **cliente** | fornitore | fornitore |
> | codice e modello | cliente | cliente | **cliente** | fornitore |
> | dati | cliente | cliente | cliente | **cliente** (anche nel SaaS i dati restano una responsabilità del cliente, per il GDPR) |

#### **7.3. I servizi cloud di ML oggi (novembre 2026)**

| Fornitore | Principali servizi di ML e caratteristiche |
|---|---|
| **AWS** | **Amazon SageMaker AI**: costruire, addestrare e rilasciare i propri modelli (notebook, job di addestramento, endpoint, MLOps); **Amazon Bedrock** per i foundation model |
| **Google Cloud** | **Gemini Enterprise Agent Platform** (**ex Vertex AI**, rinominata nell’aprile 2026): addestramento personalizzato, AutoML, catalogo di modelli, endpoint, agenti |
| **Microsoft Azure** | **Azure Machine Learning** (addestramento, MLOps) e **Microsoft Foundry** (ex Azure AI Foundry): catalogo di modelli, agenti |

**Comune a tutti**: gestione dei dataset, addestramento su GPU o TPU, AutoML, registro dei modelli, **rilascio come endpoint REST**, monitoraggio. **I nomi dei prodotti cambiano spesso** (due ridenominazioni nel 2025–26): **imparare il modello di servizio, non il marchio**.

Richiamo dalla Lezione 23: **inferenza cloud o locale**. Il cloud dà **scala e strumenti gestiti**; l’inferenza locale **tiene immagini e dati di produzione dentro lo stabilimento** (privacy, GDPR, segreto industriale), **funziona offline** e **non ha costi per richiesta**.

---

### **8. Panoramica: le applicazioni dei sistemi intelligenti**


Visione, logistica, trasporti e il Pirelli Cyber Tyre.

#### **8.1. Sei esempi di sistemi di visione intelligenti**

**Robotica, controllo, fabbrica intelligente, sorveglianza, ambiente intelligente, logistica.**

<!-- SOURCE_VISUAL id="IS24-V30" source="L24-web.pdf" page="44" type="schema" description="Sei aree di applicazione dei sistemi di visione intelligenti" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.2. Sistemi intelligenti per la robotica**

- **Macchine automatiche** che eseguono compiti prima svolti da persone: **robot industriali** (pick and place, taglio, saldatura), **droni**, **cacciatori di mine**, **aspirapolvere**.
- **Componenti**: struttura del corpo, **attuatori**, fonte di energia, **sensori**, **controllore** (la parte basata sull’AI).
- **Traiettorie, aptica, lavoro collaborativo**: molti usano **telecamere**.

<!-- SOURCE_VISUAL id="IS24-V31" source="L24-web.pdf" page="45" type="figura" description="Robot generici e industriali" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.3. Metodi e applicazioni: il controllo**

Applicazioni dei sistemi intelligenti per il **controllo di processi industriali**, l’**automazione industriale**, i **sistemi robotici**, i **prodotti complessi**, le **reti di distribuzione dell’energia**, i **sistemi automobilistici e di trasporto**. Controllo di un impianto; controllo di una posizione; controllo della stabilità.

<!-- SOURCE_VISUAL id="IS24-V32" source="L24-web.pdf" page="46" type="figura" description="Esempi di controllo di impianto, di posizione e di stabilità" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.4. Visione per l’ispezione della produzione e per la sorveglianza**

**Ispezione della produzione (fabbrica intelligente)**: **ingressi non ideali e cambiamenti nel tempo**.

<!-- SOURCE_VISUAL id="IS24-V33" source="L24-web.pdf" page="47" type="figura" description="Ispezione della produzione con ingressi non ideali" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Sorveglianza e sicurezza**: **scenari complessi e diversi da gestire**.

<!-- SOURCE_VISUAL id="IS24-V34" source="L24-web.pdf" page="48" type="figura" description="Scenari di videosorveglianza" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Sorveglianza e AI Act (nota didattica).** I sistemi di visione per la sorveglianza ricadono spesso nelle categorie ad alto rischio dell’AI Act, e l’identificazione biometrica remota in tempo reale in spazi pubblici è vietata salvo eccezioni (Lezione 2; Lezione 23, §2.4). Il progetto parte dal compito (rilevare un comportamento) e dalla base giuridica, non dalla tecnologia disponibile.

#### **8.5. Altre cinque aree di applicazione**

| Area | Che cosa fa |
|---|---|
| **auto intelligenti** | identificazione del conducente, assistenza alla guida, guida autonoma, sorveglianza dell’abitacolo, impostazioni automatiche |
| **case intelligenti, intrattenimento** | identificazione, profilazione (adulto, bambino, cane, gatto), controllo con i gesti, sicurezza |
| **città intelligente** | gestione del traffico, rilevamento di anomalie, prevenzione del crimine, aiuto alle persone vulnerabili agli attraversamenti |
| **ambiente** | **fumo degli incendi boschivi**: luce visibile, fotogrammi di bassa qualità, hardware limitato; feature statiche e dinamiche; **impara ambienti nuovi** |
| **logistica** | **lenti liquide**: la lente rimette a fuoco su scatole di altezze diverse, poi un **OCR di deep learning** legge il codice |

<!-- SOURCE_VISUAL id="IS24-V35" source="L24-web.pdf" page="49" type="figura" description="Cinque aree: auto, case, città, ambiente, logistica" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**In dettaglio (appendice)**:

- **Auto intelligenti**: identificazione del conducente; assistenza alla guida e guida autonoma; sorveglianza dell’abitacolo (attenzione del conducente, passeggeri); impostazioni automatiche (sedile, specchi, luci).

<!-- SOURCE_VISUAL id="IS24-V36" source="L24-web.pdf" page="107" type="figura" description="Visione intelligente per le auto" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **Case intelligenti e intrattenimento**: identificazione delle persone in casa; profilazione: adulto, bambino, cane, gatto; controllo con i gesti dei dispositivi (TV, giochi); sicurezza.

<!-- SOURCE_VISUAL id="IS24-V37" source="L24-web.pdf" page="108" type="figura" description="Visione intelligente per la casa e l'intrattenimento" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **Città intelligente**: gestione del traffico; rilevamento di anomalie, prevenzione del crimine; **aiuto alle persone vulnerabili agli attraversamenti**: il semaforo dà **più tempo** a un bambino, a un anziano, a un cieco o a una persona in carrozzina.

<!-- SOURCE_VISUAL id="IS24-V38" source="L24-web.pdf" page="109" type="figura" description="Visione intelligente per la città" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **Ambiente: il fumo degli incendi boschivi**: telecamere a luce visibile, fotogrammi di bassa qualità, hardware limitato. **Passo 1**: feature statiche e dinamiche **scelte dal progettista**; **passo 2**: **classificazione con una rete neurale**. **Il sistema impara ambienti nuovi.**

<!-- SOURCE_VISUAL id="IS24-V39" source="L24-web.pdf" page="110" type="figura" description="Rilevamento del fumo degli incendi boschivi" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Il fumo come sistema ibrido (nota didattica).** Feature progettate (il fumo si muove lentamente verso l’alto, ha bordi sfumati e colore grigiastro: feature dinamiche e statiche) più un classificatore neurale: è la terza via della Lezione 23 (§2.7), scelta perché l’hardware è limitato e i dati di fumo reale sono rari.

- **Logistica: le lenti liquide**: scatole di altezze diverse passano sotto la stessa telecamera; **la lente liquida cambia il fuoco in millisecondi**; poi un **OCR di deep learning legge il codice**.

<!-- SOURCE_VISUAL id="IS24-V40" source="L24-web.pdf" page="111" type="figura" description="Lenti liquide per leggere codici su scatole di altezze diverse" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché una lente liquida (nota didattica).** Una lente fissa ha una profondità di campo limitata: una scatola alta 10 cm e una alta 60 cm non possono essere entrambe a fuoco. Una lente liquida cambia la curvatura di una goccia con una tensione elettrica, quindi la distanza di fuoco, in pochi millisecondi e senza parti meccaniche: un sensore di distanza misura l’altezza della scatola e la lente si regola prima dello scatto.

#### **8.6. AIoT nella logistica e nelle applicazioni generali**

- **Intelligenza sul sensore.**
- **Tracciamento intelligente degli asset** con il machine learning: **si può costruire un albero di decisione per il sensore senza scrivere una riga di codice**.
- **Modulo inerziale a 6 assi LSM6DSOX** con **machine learning core**: passi semplici per costruire nel sensore la logica di tracciamento intelligente degli asset, per sviluppare in fretta e arrivare sul mercato prima.

<!-- SOURCE_VISUAL id="IS24-V41" source="L24-web.pdf" page="50" type="figura" description="Il modulo LSM6DSOX con machine learning core per il tracciamento degli asset" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Un albero di decisione dentro il sensore (nota didattica).** Il *machine learning core* esegue piccoli alberi di decisione (Lezione 12) su feature calcolate dall’accelerometro e dal giroscopio (media, varianza, picchi in una finestra): per esempio «pacco fermo / in movimento / caduto». Il microcontrollore principale resta addormentato e si sveglia solo quando l’albero segnala un evento: il consumo scende da milliwatt a microwatt. È il TinyML al suo estremo (§6).

#### **8.7. Logistica: la visione nelle operazioni e l’inventario con i droni**

- **Inventario con droni**: **droni autonomi indoor** volano lungo gli scaffali **dove il GPS non funziona**, **leggono le etichette dei pallet**, **trovano i posti vuoti** e **contano i colli**; i conteggi vengono confrontati con il **sistema di gestione del magazzino**.
- **Esempio**: **Corvus Robotics** (Corvus One); **nel 2026 una versione per magazzini della catena del freddo sotto zero**. https://www.corvus-robotics.com/corvus-one
- **Vedere, parlare e pensare le operazioni logistiche**: inventario basato sulla visione, veicoli autonomi, smistamento e ispezione.

<!-- SOURCE_VISUAL id="IS24-V42" source="L24-web.pdf" page="51" type="figura" description="Drone per l'inventario in un magazzino e visione nelle operazioni logistiche" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.8. Vehicle to everything (V2X): quattro idee**

1. **V2X**: il veicolo comunica con **veicoli (V2V)**, **infrastruttura (V2I)**, **pedoni (V2P)**, **la rete (V2N)**.
2. **Due famiglie radio**: **C-V2X** (LTE, 5G, anche diretta) e **IEEE 802.11p** (ITS-G5); **collegamenti diretti: qualche centinaio di metri**.
3. **La latenza decide il percorso**: un **avviso di collisione** richiede **circa 100 ms**: **da auto ad auto**; un **avviso di ghiaccio** può aspettare **secondi**: **può passare per il cloud**.
4. **Il platooning, una lezione di progetto**: camion a pochi metri, **un solo conducente**. **Funziona** (prove UE 2016–2022), eppure **nessun servizio commerciale**; **Daimler si è fermata nel 2019** (risparmio di carburante minore del previsto). **Costo, regole e responsabilità decidono, non solo la tecnologia.**

<!-- SOURCE_VISUAL id="IS24-V43" source="L24-web.pdf" page="52" type="figura" description="Comunicazioni V2X e camion in platooning" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché la latenza conta (nota didattica).** A 130 km/h un’auto percorre $\frac{130\ \text{km/h}}{3{,}6}\approx 36{,}1\ \text{m/s}$. Con 100 ms di latenza, un avviso arriva quando l’auto ha già percorso circa 3,6 m; con 10 ms, circa 36 cm. Per un avviso di frenata tra veicoli vicini la differenza è tra un tamponamento evitato e uno no. Un viaggio auto → cloud → auto su rete mobile può superare i 100 ms anche con il 5G: per questo le collisioni si gestiscono in comunicazione diretta.

**In dettaglio (appendice).** Il **V2X** indica la comunicazione **ad alta banda, bassa latenza e alta affidabilità** tra una vasta gamma di sensori legati al trasporto e al traffico. Collegamenti radio per V2V e V2I: **C-V2X** (su LTE e 5G) e **IEEE 802.11p** (ITS-G5).

<!-- SOURCE_VISUAL id="IS24-V44" source="L24-web.pdf" page="102" type="figura" description="Comunicazioni V2X" reason="Immagine visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Road map del V2X** (pensando alle auto elettriche/ibride): tassonomia **V2V, V2D, V2P, V2H, V2G e V2I** (veicolo verso veicolo, dispositivo, pedone, casa, rete elettrica, infrastruttura).

<!-- SOURCE_VISUAL id="IS24-V45" source="L24-web.pdf" page="103" type="schema" description="Road map e tassonomia del V2X" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Funzioni del V2X**: un **pedone si avvicina alle strisce**; i semafori possono cambiare colore e **le auto vengono avvisate prima**. **Circolazione più fluida e sicura.**

<!-- SOURCE_VISUAL id="IS24-V46" source="L24-web.pdf" page="104" type="figura" description="Pedone alle strisce con semafori e auto avvisati via V2X" reason="Immagine visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**IoT: veicoli in platooning.**

- **Platooning abilitato dall’IoT**: camion che viaggiano **molto vicini**, uno dietro l’altro, con un **sistema di comunicazione comune basato sull’IoT V2V**. **Un solo conducente**, nel primo camion.
- **Promessa**: **meno consumo di carburante** (meno resistenza dell’aria) e **traffico più sicuro**. Prove di sei costruttori nella **European Truck Platooning Challenge (2016)**.
- **Verifica con la realtà**: l’introduzione sul mercato prevista per il **2023–30 non è avvenuta su larga scala**. Nel **2019 Daimler ha fermato il platooning** (risparmi di carburante minori del previsto, limiti legali) ed è passata ai **singoli camion automatizzati**.

<!-- SOURCE_VISUAL id="IS24-V47" source="L24-web.pdf" page="105" type="figura" description="Camion in platooning" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Un aggiornamento rispetto all’edizione precedente (nota didattica).** La versione precedente della slide citava prototipi di Tesla, Volvo, Iveco e Scania e un’introduzione sul mercato prevista tra il 2023 e il 2030; l’edizione 2026/27 registra che quella previsione non si è avverata. È un caso utile da ricordare: una tecnologia che funziona nelle prove può non diventare un prodotto se costi, regole e responsabilità non tornano.

#### **8.9. I sistemi di trasporto intelligenti (ITS)**

- **Sistemi avanzati per la gestione del trasporto e del traffico** (autostrada, città, regione) che permettono agli utenti un uso **più sicuro, coordinato e intelligente** delle reti di trasporto con l’intelligenza artificiale.
- **Esempi**: **chiamata d’emergenza** dopo un incidente; **telecamere che fanno rispettare il codice della strada**; **limiti di velocità che cambiano con le condizioni e il meteo**; **prevenzione delle collisioni**.
- **Comunicazioni wireless**: **corto raggio**, dirette tra veicoli (IEEE 802.11p / ITS-G5, C-V2X sidelink, qualche centinaio di metri); **lungo raggio** attraverso le reti cellulari (4G LTE, 5G).
- **Calcolo**: microcontrollori sui sensori e nei dispositivi dell’auto; **sensori virtuali** (per esempio la presenza di ghiaccio).
- **Fusione di dati eterogenei**: sensori, telecamere di sorveglianza, smartphone, celle telefoniche; protocolli e AI diversi.

<!-- SOURCE_VISUAL id="IS24-V48" source="L24-web.pdf" page="53" type="figura" description="Esempi di sistemi di trasporto intelligenti" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.10. Volvo Slippery Road Alert: un sensore virtuale nel cloud**

**Livello 1, edge**: velocità delle ruote, interventi di ABS e controllo della trazione, GPS → **ECU dell’auto** (sensore virtuale «**strada scivolosa**»). **Livello 2, nodo**: **unità telematica** → rete mobile (4G/5G). **Livello 3, cloud**: **cloud Volvo** (aggrega molte auto sulla mappa) → **avviso alle auto in avvicinamento**; amministrazione stradale: **manutenzione invernale**.

<!-- SOURCE_VISUAL id="IS24-V49" source="L24-web.pdf" page="54" type="schema" description="Volvo Slippery Road Alert su tre livelli: edge, nodo, cloud" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- Richiamo dalla Lezione 3: un **sensore virtuale** **fonde sensori reali in una grandezza che nessun sensore misura**: qui «**la strada è scivolosa**».
- **2016**: Volvo serie 90 in Svezia e Norvegia, con le amministrazioni stradali; **2019**: tutta Europa; **2020**: USA, dati anche alle città e a Waze (Waycare).
- **2019**: pilota UE **Data for Road Safety**: dati di sicurezza anonimizzati **condivisi tra marchi**.

> 📌 **Non V2V diretto ma veicolo–rete–veicolo**: **secondi di latenza**, **vanno bene per il ghiaccio, non per una collisione** (§8.8).

#### **8.11. Pirelli Cyber Tyre: da un piccolo sensore alla città intelligente**

- **Il pneumatico è l’unica parte dell’auto che tocca la strada**: i suoi sensori misurano **il contatto** (acqua, ghiaccio, scarsa aderenza, usura). **Demo di ricerca di un ADAS 5G**: Pirelli, Ericsson, Audi, TIM, Italdesign, KTH.
- **Sicurezza dell’auto**: una **misura diretta all’impronta di contatto** può **avvisare di una bassa aderenza prima di ABS e controllo della trazione**, che **reagiscono quando la ruota già slitta** (il sensore virtuale di Volvo).
- **Sicurezza della città** (uno scenario futuro): **ogni auto diventa una sonda della strada**; avvisi di ghiaccio alle auto che seguono, **asfalto consumato e buche** per la manutenzione stradale.
- **Business**: **servizi** (sostituzione proattiva, assistenza) e **dati**.

<!-- SOURCE_VISUAL id="IS24-V50" source="L24-web.pdf" page="55" type="figura" description="Il Pirelli Cyber Tyre con il sensore nel pneumatico" reason="Immagine visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.12. Pirelli Cyber Car: sensore, edge, fog e cloud**

**Sensore** (pressione del pneumatico + informazioni) → **flusso di dati su Amazon AWS**.

<!-- SOURCE_VISUAL id="IS24-V51" source="L24-web.pdf" page="56" type="schema" description="Flusso dei dati del Cyber Car dal sensore all'edge, al fog e ad AWS" reason="Schema e immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Oggi**: sulle **auto di fascia alta**, attraverso **partnership con i costruttori**; nel **maggio 2026 Pirelli ha annunciato la produzione nel suo stabilimento di Rome (Georgia, USA)**. **Sicurezza**: un pneumatico connesso **raccoglie dati del veicolo**, quindi è una **tecnologia sensibile**: l’Italia ha **limitato l’influenza dell’azionista cinese Sinochem** (**golden power**) prima della produzione e delle vendite negli USA.

**Le opportunità di business** (appendice): https://www.pirelli.com/tyres/en-au/car/tech-and-knowledge/cyber-tyre e https://www.pirellicyberfleet.com/

<!-- SOURCE_VISUAL id="IS24-V52" source="L24-web.pdf" page="106" type="figura" description="Le opportunità di business del Pirelli Cyber Tyre" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Il Cyber Tyre sui livelli del corso (nota didattica).** **Sensore** nel pneumatico (misura e prima elaborazione, con energia minima: TinyML); **edge** nella centralina dell’auto (fusione con ABS e ESP, avviso al conducente in millisecondi); **fog** (nodi della rete mobile, raccolta dalle auto di una zona); **cloud** (mappa delle strade, servizi, addestramento dei modelli). È lo stesso schema del Volvo Slippery Road Alert, con un sensore diretto invece di un sensore virtuale. Il «golden power» mostra che un sensore che raccoglie dati di mobilità è anche una questione di sovranità dei dati (Lezione 15).

---

### **9. Panoramica: i sistemi di visione intelligenti industriali**


Le caratteristiche necessarie per creare applicazioni nell’Industria 4.0 e 5.0.

#### **9.1. I sistemi di visione intelligenti per l’Industria 4.0**

- **Le telecamere industriali diventano più piccole e più economiche**, mentre le prestazioni migliorano di continuo; **con la rete aprono nuove applicazioni**.
- Sono **gli occhi che catturano il processo** e danno un **feedback** attraverso il software di elaborazione delle immagini.
- **Usate in tutte le fasi della produzione**: ispezione delle **materie prime**, **monitoraggio della produzione**, **ispezione finale** e **garanzia della qualità**; **indispensabili per l’efficienza e per standard di qualità elevati** (*). (*) White paper: *Industrial Cameras in Industry 4.0 for Efficient Production*, www.baslerweb.com.

<!-- SOURCE_VISUAL id="IS24-V53" source="L24-web.pdf" page="58" type="figura" description="Telecamere industriali per l'Industria 4.0" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **9.2. La storia… oggi**

**Dal 2018**:

- **smart camera con FPGA o NPU**;
- **con una rete neurale profonda a bordo**;
- **misure**;
- tecniche avanzate di **OCR + QR + codici a barre**;
- **nessun addestramento a bordo: solo inferenza!** La rete viene **addestrata offline**, poi **caricata sulla telecamera**.

<!-- SOURCE_VISUAL id="IS24-V54" source="L24-web.pdf" page="59" type="figura" description="Evoluzione delle smart camera fino alle reti neurali a bordo" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **9.3. I sistemi di visione industriali: sensori e smart camera**

**Telecamera → elaborazione a bordo = smart camera**; **profilometro laser 3D**.

<!-- SOURCE_VISUAL id="IS24-V55" source="L24-web.pdf" page="60" type="figura" description="Smart camera e profilometro laser 3D" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Il profilometro laser (nota didattica).** Una linea laser proiettata sul pezzo appare deformata vista da una telecamera inclinata: dalla deformazione si ricava per triangolazione l’altezza di ogni punto della linea. Facendo scorrere il pezzo sotto la linea si ottiene un profilo 3D completo, con risoluzioni di micrometri: è uno dei sensori 3D del bin picking (Lezione 23, §2.10).

#### **9.4. La visione industriale lungo la catena di produzione**

**Qualità, ispezione, produzione, sicurezza**: automazione della produzione, ispezione visiva, controllo di processo, automazione dell’imballaggio.

<!-- SOURCE_VISUAL id="IS24-V56" source="L24-web.pdf" page="61" type="figura" description="Visione industriale lungo la catena di produzione" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **9.5. La configurazione di base e la rete di automazione**

**Sistemi di illuminazione e telecamera**; **HW+SW (+ modelli profondi)** per **classificazione, misure e valutazione della qualità**.

<!-- SOURCE_VISUAL id="IS24-V57" source="L24-web.pdf" page="62" type="figura" description="Configurazione di base di un sistema di visione: illuminazione, telecamera, elaborazione" reason="Immagine visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **L’illuminazione decide metà del problema (nota didattica).** Un graffio quasi invisibile con la luce diffusa diventa una linea brillante con una luce radente (a basso angolo); una superficie lucida si ispeziona con luce coassiale; un contorno si misura in controluce. Scegliere l’illuminazione giusta può rendere banale un problema che, con immagini mal illuminate, richiederebbe una rete profonda e migliaia di esempi.

**La visione industriale nella rete di automazione.** Un sistema intelligente **scarta i pezzi difettosi da un nastro**; **protocolli fieldbus su Ethernet 10/100** con **sincronizzazione temporale IEEE 1588**. HMI, I/O distribuito, switch, azionamenti, smart camera, braccio pick-and-place, contenitori buono/scarto. **Le telecamere alimentano l’unità di elaborazione delle immagini**, che comanda **PLC, monitor, robot e nastri**.

<!-- SOURCE_VISUAL id="IS24-V58" source="L24-web.pdf" page="63" type="schema" description="Rete di automazione con smart camera, PLC, robot, nastri e sincronizzazione IEEE 1588" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché serve IEEE 1588 (nota didattica).** La telecamera vede il pezzo in un punto, il braccio lo scarta **più avanti** sul nastro. Se il nastro scorre a 1 m/s e il braccio è 0,5 m dopo la telecamera, il braccio deve agire esattamente 500 ms dopo lo scatto. Un errore di **10 ms** tra gli orologi dei due dispositivi sposta il punto di presa di **1 cm**, abbastanza per mancare un pezzo piccolo. Il **Precision Time Protocol** (IEEE 1588) sincronizza gli orologi con precisione sotto il millisecondo, come visto per la sincronizzazione dei dati nella Lezione 6.

#### **9.6. Un esempio di visione industriale: la misura dei contatti di un relè**

**Il relè da controllare**, **telecamere C-mount** (telecamera USB 1 e 2), **sensore di prossimità**, **attuatore e struttura di movimentazione**.

<!-- SOURCE_VISUAL id="IS24-V59" source="L24-web.pdf" page="64" type="figura" description="Sistema di misura dei contatti di un relè con due telecamere USB C-mount" reason="Immagine visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **9.7. Dai sistemi completi ai piccoli budget**

- **Cella d’ispezione completa**: **100 000 – 200 000 €** (ordine di grandezza: telecamere, illuminazione, meccanica, integrazione).
- **Piccoli progetti**: una **scheda con telecamera da 2 MP a circa 8 USD**.

<!-- SOURCE_VISUAL id="IS24-V60" source="L24-web.pdf" page="65" type="figura" description="Una cella d'ispezione completa e una scheda con telecamera da 8 dollari" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Da 8 dollari a 200 000 euro (nota didattica).** La differenza di prezzo non sta nel sensore. Un sistema industriale completo include **ottiche** e **illuminazione** progettate per il pezzo; **meccanica** e integrazione con robot e PLC; **sincronizzazione**; **validazione** su migliaia di pezzi; **certificazione** e **manutenzione** per anni. Un prototipo a 8 dollari dimostra che l’idea funziona; un sistema a 200 000 euro garantisce che funzioni **sempre**, su una linea che non può fermarsi. È la distanza tra «saper addestrare una rete» e «saper progettare un sistema intelligente» con cui il corso si era aperto (Lezione 1).

#### **9.8. Il rilevamento di anomalie**

- **Come si insegna a una telecamera a rilevare deviazioni** se **non sono, o non sono del tutto, prevedibili**? L’elaborazione delle immagini **basata su regole dovrebbe arrendersi**. **Con i sistemi di AI una sfida del genere si risolve facilmente.**
- **Il rilevamento di anomalie è la terza funzionalità dell’AI**, oltre al **rilevamento** e alla **classificazione** degli oggetti.
- **L’identificazione di eventi, oggetti od osservazioni rari** che sono **sospetti** perché **differiscono in modo significativo** dai comportamenti o dai pattern standard.

Richiamo dalle Lezioni 19 e 22: **punteggio di anomalia $s(x)$ e soglia $\tau$**: l’immagine $x$ viene segnalata quando $s(x)>\tau$. $s$ può essere la **distanza dai dati normali** (Lezione 19) o l’**errore di ricostruzione di un autoencoder addestrato solo sui pezzi buoni** (Lezione 22).

<!-- SOURCE_VISUAL id="IS24-V61" source="L24-web.pdf" page="66" type="figura" description="Esempi di anomalie su pezzi industriali" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Come si fa senza esempi di difetti (nota didattica).** Il problema pratico è che i difetti sono **rari** e **tutti diversi**: non si può addestrare un classificatore con una classe «difetto» ben rappresentata (Lezione 14). Si addestra allora un modello **solo sui pezzi buoni**, per esempio un **autoencoder** (Lezione 22, §1.12), e si segnala come anomalia ciò che il modello **non sa ricostruire**:
> $$\text{anomalia}\iff\|x-\hat{x}\|^2>\tau.$$
> La soglia $\tau$ si sceglie su un **validation set di pezzi buoni**, per esempio al 99° percentile dell’errore di ricostruzione: così si accetta circa l’1% di falsi allarmi. «Facilmente», come dice la slide, va letto con cautela: serve verificare su difetti veri, anche pochi, che la soglia li catturi, e controllare nel tempo il **drift** (Lezione 11): un cambio di fornitore della materia prima può far sembrare anomali tutti i pezzi. Con un ViT o una CNN pre-addestrati si può usare anche la distanza coseno dagli embedding dei pezzi buoni (§1.9).

---

### **10. Panoramica: dall’Industria 4.0 alla 5.0**


Le tecnologie 4.0 sono il ponte verso gli obiettivi 5.0.

#### **10.1. L’Industria 4.0 in breve**

- **Processi connessi**: macchine, sensori e dispositivi IoT collegati a un **modello dell’intera linea che decide da solo**.
- **La quarta rivoluzione**: i **sistemi cyber-fisici**.
- **Quattro principi di progetto**: **interoperabilità, trasparenza dell’informazione, assistenza tecnica, decisioni decentralizzate** (§10.7).
- **Oggi**: la **base tecnologica dell’Industria 5.0**.

<!-- SOURCE_VISUAL id="IS24-V62" source="L24-web.pdf" page="68" type="figura" description="Illustrazioni dell'Industria 4.0 e dei sistemi cyber-fisici" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.2. L’ambiente I4.0 e le sue tecnologie digitali**

**Smart factory** al centro, collegata a **Internet delle cose**, **Internet dei dati**, **Internet dei servizi**, **Internet delle persone**, e ai settori **smart grid, mobilità, logistica, edifici, case**. **Tecnologie digitali chiave**: cloud computing; sensori intelligenti, piattaforme IoT; **big data analytics, algoritmi**; sistemi cyber-fisici; realtà aumentata, HMI avanzate; dispositivi mobili; stampa 3D; autenticazione, rilevamento delle frodi. **Dati e analisi: la capacità centrale.**

<!-- SOURCE_VISUAL id="IS24-V63" source="L24-web.pdf" page="69" type="schema" description="L'ambiente dell'Industria 4.0 con le reti di cose, dati, servizi e persone e le tecnologie digitali" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Dalla figura dell’ambiente IoP e dal rapporto PwC «*Building the digital enterprise*» (figure originali in appendice). L’**Internet delle persone** (IoP): le persone **comunicano con i propri dispositivi, direttamente, senza una terza parte fidata**.

<!-- SOURCE_VISUAL id="IS24-V64" source="L24-web.pdf" page="113" type="figura" description="Figure originali dell'ambiente IoP e del rapporto PwC" reason="Figure visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.3. Dall’Industria 4.0 all’Industria 5.0: un ponte**

**Input**: una linea di produzione e i suoi dati ⇒ **Output**: un progetto con **tecnologia 4.0 e KPI 5.0**.

| **Industria 4.0** — il come | **Industria 5.0** — il perché, per chi |
|---|---|
| sistemi cyber-fisici; IoT, AI all’edge e nel cloud; visione, **digital twin** | **stesse tecnologie, obiettivi di progetto più ampi**: **centrata sull’uomo, sostenibile, resiliente** |

<!-- SOURCE_VISUAL id="IS24-V65" source="L24-web.pdf" page="70" type="schema" description="Il ponte dalle tecnologie dell'Industria 4.0 agli obiettivi dell'Industria 5.0" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **Commissione europea (2021)**: l’Industria 5.0 **completa ed estende** l’Industria 4.0; **non è né la sua continuazione cronologica né un’alternativa**.
- La 4.0 chiede «**si può automatizzare e connettere?**» (produttività, qualità); la 5.0 aggiunge «**è un bene per l’operatore, per il pianeta e per la prossima crisi?**».
- **Per voi, come nuovi informatici ed esperti di AI**: **progettare con le tecnologie 4.0, giustificare il progetto con gli obiettivi 5.0**.

> 💡 **Il digital twin (nota didattica).** Un **gemello digitale** è un modello software di una macchina o di una linea, alimentato in tempo reale dai sensori, che si comporta come l’originale: permette di provare un cambiamento (una nuova velocità del nastro, un nuovo prodotto) sul modello prima di farlo sulla linea vera. È la «trasparenza dell’informazione» del §10.7 portata al suo estremo, e uno dei modi per misurare i KPI 5.0 senza fermare la produzione.

#### **10.4. L’Industria 5.0**

**1.0** meccanizzazione → **2.0** produzione di massa → **3.0** automazione → **4.0** cyber-fisica → **5.0** **persone, pianeta, resilienza**.

| **Centrata sull’uomo** (per le persone e la società) | **Sostenibile** | **Resiliente** |
|---|---|---|
| **AI che assiste l’operatore**; aggiornamento delle competenze, competenze digitali | **meno energia e materiali**; **economia circolare** | **continua a produrre quando qualcosa si rompe**: un sensore, una macchina, un fornitore, l’energia, un attacco informatico |

**Costruita sulla 4.0** (CPS, IoT, AI, visione); **giudicata su produttività e persone, energia, ripresa**. Commissione europea, *Industry 5.0: towards a sustainable, human-centric and resilient European industry*, 2021.

<!-- SOURCE_VISUAL id="IS24-V66" source="L24-web.pdf" page="71" type="schema" description="Le cinque rivoluzioni industriali e i tre valori dell'Industria 5.0" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**In dettaglio (appendice)**: un **approccio centrato sull’uomo** alle tecnologie digitali, AI compresa; **aggiornamento e riqualificazione** delle competenze dei lavoratori, in particolare **digitali**; **industrie efficienti nelle risorse e sostenibili**, transizione a un’**economia circolare**; un’industria **competitiva e resiliente**, che investe in **ricerca e innovazione**.

<!-- SOURCE_VISUAL id="IS24-V67" source="L24-web.pdf" page="116" type="figura" description="I valori dell'Industria 5.0 secondo la Commissione europea" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.5. L’Industria 5.0 in pratica: un progetto che si può proporre**

**Immagini delle telecamere**, **contatore di energia (kWh)**, **sensori di linea** → **edge AI** (ispezione + anomalie, $s(x)>\tau$) → **livello decisionale** (pianificazione, energia, assistenza all’operatore) → **KPI**: qualità, energia per pezzo, carico dell’operatore, ripresa.

| KPI | Prima | Obiettivo | Prospettiva |
|---|---|---|---|
| **tasso di scarto** | 3% | 1% | 4.0: qualità, produttività |
| **energia per pezzo** | 1,20 kWh | 1,00 kWh (−17%) | 5.0: sostenibile |
| **controlli visivi manuali per turno** | 400 | 50 (**solo i pezzi segnalati**) | 5.0: centrata sull’uomo |
| **tempo per adattarsi a un prodotto nuovo** | 2 giorni | 4 ore (**riaddestramento su poche immagini**) | 5.0: resiliente |

<!-- SOURCE_VISUAL id="IS24-V68" source="L24-web.pdf" page="72" type="schema" description="Progetto Industria 5.0 con sensori, edge AI, livello decisionale e KPI" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Obiettivi illustrativi**: **le stesse telecamere e gli stessi modelli di questa lezione, giudicati su quattro KPI, non uno.**

**Perché un’azienda dice sì**: (1) **valore per le persone, l’energia e la resilienza**; (2) **finanziamenti pubblici**: gli obiettivi 5.0 **aprono fondi UE e nazionali** che un’azienda vuole ottenere. **Italia 2026–2028**: **Nuovo Piano Transizione 5.0**, **iper-ammortamento** (costo maggiorato **fino al 180%** ai fini fiscali) sui beni **4.0 interconnessi**, investimenti **dal 1° gennaio 2026 al 30 settembre 2028** (MIMIT). **Le regole cambiano: controllare sempre quelle in vigore.**

> 💡 **Ogni KPI è una tecnica del corso (nota didattica).** Scarto dal 3% all’1%: classificazione e rilevamento di anomalie (Lezioni 19, 22, §9.8). Energia per pezzo: $1{,}20\to 1{,}00$ kWh è $-16{,}7\%$, arrotondato a −17%, ottenuto ottimizzando la pianificazione con i dati dei contatori. Controlli manuali da 400 a 50: la cascata System 1 → persona della Lezione 18 (§8.16), in cui la persona controlla solo i casi segnalati. Adattamento in 4 ore: transfer learning su poche immagini (Lezioni 21–22). Un «iper-ammortamento al 180%» significa che, ai fini fiscali, un investimento da 100 000 € si ammortizza come se ne valesse 180 000.

#### **10.6. La business intelligence: dalla sala del consiglio al reparto**

> 📌 **Definizione.** La **business intelligence** (BI) è l’**insieme di metodi, processi e strumenti** che **raccolgono, integrano e analizzano i dati di un’organizzazione** per **supportare le decisioni** (H. Dresner, 1989).

- **Integrazione**: **molte sorgenti in un unico archivio** (*data warehouse*).
- **KPI e dashboard**: **pochi numeri aggiornati** per chi decide.
- **Analisi**: **descrittiva**; con il ML anche **predittiva e prescrittiva** (§10.7).
- **Non uno slogan**: **Power BI** è leader nel **Gartner Magic Quadrant per il 18° anno** (2025), con Tableau, Looker, Qlik.

**Linea 3, oggi**: **OEE 78%**, **scarto 1,2%**, **1,00 kWh/pezzo**, **fermo 42 min**; grafici di scarto al giorno [%] ed energia per pezzo. **Input**: sensori IoT, PLC, ERP ⇒ **Output**: **decisioni sulla linea**.

<!-- SOURCE_VISUAL id="IS24-V69" source="L24-web.pdf" page="73" type="figura" description="Dashboard della linea 3 con OEE, scarto, energia per pezzo e fermi" reason="Dashboard visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Nata per i manager delle multinazionali, la BI raggiunge ora ogni azienda, anche manifatturiera**: i dati vengono **dai sensori e dalle telecamere di questo corso**, e la dashboard **la legge il capoturno, non solo l’impiegato**.

> 💡 **L’OEE (nota didattica).** L’*Overall Equipment Effectiveness* è il prodotto di tre fattori: **disponibilità** (tempo in cui la macchina lavora sul tempo pianificato), **prestazione** (velocità reale su velocità nominale) e **qualità** (pezzi buoni su pezzi prodotti). Per esempio $0{,}90\cdot 0{,}88\cdot 0{,}988\approx 0{,}78$: un OEE del 78% con lo 1,2% di scarto. È un KPI composto, come un punteggio di rischio: dice che c’è un problema, la scomposizione dice dove. Le dashboard sono visualizzazione dei dati, con le regole della Lezione 11.

#### **10.7. La scala dell’analisi: dal descrivere al far accadere**

Richiamo dalla Lezione 1: **il vostro valore, e il vostro stipendio, crescono con la capacità di descrivere, spiegare, prevedere e, in cima, far accadere le cose buone.**

| Gradino | Domanda | Esempio | Strumenti |
|---|---|---|---|
| **descrittiva** | che cosa è successo? | lo scarto era il 3% la settimana scorsa | dashboard, BI |
| **diagnostica** | perché è successo? | lo scarto sale sopra i 40 pezzi/min | EDA, XAI |
| **predittiva** | che cosa succederà? | questo cuscinetto si guasta tra circa 200 h | modelli di ML, punteggio di anomalia |
| **prescrittiva** | come farla accadere? | rallentare la linea 3, manutenzione sabato | ottimizzazione, agenti |

<!-- SOURCE_VISUAL id="IS24-V70" source="L24-web.pdf" page="74" type="grafico" description="La scala dell'analisi con valore e difficoltà crescenti dai quattro gradini" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Questo corso vi ha dato gli strumenti per tutti e quattro i gradini.** Dal modello di Gartner dell’*analytic ascendancy* (2012).

> 💡 **Il corso sulla scala (nota didattica).** Descrittiva: EDA e visualizzazione (Lezioni 10–11). Diagnostica: correlazione, alberi di decisione leggibili, spiegabilità (Lezioni 2, 11, 12). Predittiva: regressori, classificatori, reti, anomalie (Lezioni 12–22). Prescrittiva: ottimizzazione genetica e agenti (Lezioni 15, 19). Ogni gradino richiede i precedenti: non si prescrive bene senza aver capito perché.

**I quattro principi di progetto dell’Industria 4.0** (appendice):

- **interoperabilità**: la capacità di macchine, dispositivi, sensori e persone di **collegarsi e comunicare tra loro** attraverso l’IoT o l’IoP;
- **trasparenza dell’informazione**: la capacità dei sistemi informativi di **creare una copia virtuale del mondo fisico** arricchendo i modelli digitali dell’impianto con i **dati dei sensori**; richiede di aggregare i dati grezzi in **informazioni di contesto di valore più alto**;
- **assistenza tecnica migliorata**: la capacità dei sistemi di assistenza di **supportare le persone** aggregando e visualizzando le informazioni in modo comprensibile, per **decisioni informate** e per risolvere **problemi urgenti** con poco preavviso (sfruttando la capacità dei sistemi cyber-fisici di svolgere compiti **sgradevoli, troppo faticosi o pericolosi** per i colleghi umani);
- **decisioni decentralizzate**: la capacità dei sistemi cyber-fisici di **prendere decisioni da soli** e svolgere i compiti nel modo **più autonomo possibile**; **solo in caso di eccezioni, interferenze o obiettivi in conflitto** i compiti vengono delegati a un livello superiore.

«**Per essere conformi ai fondi dell’Industria 4.0…**»: se si guida il progetto di un sistema con fondi Industria 4.0, **tenere a mente questi principi**.

#### **10.8. Un grande valore può partire da un piccolo sensore**

- **Non sempre serve una tecnologia dirompente**: anche **un piccolo sensore può creare grande valore**, per l’azienda e per l’utente.
- **Condividere l’informazione trasforma una misura in un servizio**: un sensore virtuale, un avviso alle auto che seguono, una mappa per la manutenzione delle strade.
- **Costruirlo richiede la conoscenza eterogenea di questo corso**: AI sul dispositivo, livelli edge, fog e cloud, latenza e OS in tempo reale, sicurezza e aggiornamenti OTA, dati e modelli.
- **L’Industria 5.0 è l’abilitatore**: fondi e obiettivi per rendere questi sistemi **sostenibili e centrati sull’uomo**.

**Sensore del pneumatico, lampada connessa, altoparlante intelligente, anomalia su un dado, telecamera del traffico.** **Fabbriche e aziende stanno già trasmettendo dati: aspettano la vostra idea intelligente per usarli.**

<!-- SOURCE_VISUAL id="IS24-V71" source="L24-web.pdf" page="75" type="figura" description="Cinque esempi: sensore del pneumatico, lampada, altoparlante, anomalia su un dado, telecamera del traffico" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **11. Punti principali**


> ✅ **Punti principali della lezione.**
> - **ViT**: patch → token → **self-attention globale** (costo $\propto N^2D$) → token [class] → testa; **ottimo come estrattore di feature pre-addestrato**.
> - **AIoT**: percepire, decidere, agire; **dispositivo, edge o cloud** secondo **latenza, banda, privacy, energia**.
> - **La sicurezza IoT copre dispositivo, comunicazioni, cloud e ciclo di vita**; gli **aggiornamenti OTA sono firmati e inviati su TLS**.
> - **Regole**: **CRA europeo obbligatorio** (segnalazioni **dall’11 settembre 2026**, applicazione completa **11 dicembre 2027**); **NIST CSF 2.0, sei funzioni**.
> - **Un RTOS rispetta le scadenze** con **priorità, preemption e interrupt**: tempo di risposta nel caso peggiore **$R\le D$**.
> - **Scheda per l’edge AI**: CPU, memoria, **NPU** (integrata o esterna), I/O su GPIO e MIPI CSI; **confrontare i TOPS alla stessa precisione**.
> - **ML nel cloud**: MLaaS su **IaaS, PaaS, SaaS**; **i marchi cambiano, i modelli di servizio restano**.
> - **Applicazioni**: un **sensore virtuale condiviso attraverso il cloud trasforma una misura in un servizio**; visione industriale: **$s(x)>\tau$**.
> - **L’Industria 4.0 dà le tecnologie, la 5.0 gli obiettivi e i fondi**; la **BI sale la scala descrivere, spiegare, prevedere, prescrivere**: **un grande valore può partire da un piccolo sensore**.

---

### **12. Appendice: esercizio ViT (non d’esame)**


#### **12.1. Esercizio: VGG16 e ViT sulla stessa immagine del corso**

**Compito.** Classificare la foto del cane `dog1.jpg` (usata nella Lezione 9) con **due modelli pre-addestrati su ImageNet-1k** (1000 classi) e confrontarli.

- **Dati**: l’immagine; **VGG16** (CNN, 2014, **138 M parametri**) e **ViT-B/16** (2020, **86,6 M parametri**), entrambi da torchvision.
- **Ingresso di ogni modello**: tensore $1\times 3\times 224\times 224$, ridimensionato e normalizzato dalla trasformazione fornita con i pesi.
- **Uscita**: 1000 probabilità (softmax); stampare le **5 classi più probabili** con la loro probabilità.
- **Poi**: togliere le teste e confrontare i vettori di feature: **4096 valori (VGG16)** e **768 valori (ViT)**.

**Domande a cui rispondere**:

1. I due modelli **concordano sulla razza**? Quale è **più sicuro**?
2. Quale modello ha **più parametri**, e quale ha la **migliore accuratezza su ImageNet** (tabella alla fine)?
3. **Quale vettore si userebbe per un k-NN** sulle proprie foto di cani, e perché?

Il codice completo sarà pubblicato sul sito del corso.

#### **12.2. Il codice (PyTorch, torchvision)**

```python
import torch
from PIL import Image
from torchvision.models import (vgg16, VGG16_Weights,
                                vit_b_16, ViT_B_16_Weights)
img = Image.open("dog1.jpg").convert("RGB")       # immagine del corso (L09)

def top5(build, weights):
    model = build(weights=weights).eval()         # pre-addestrato su ImageNet-1k
    x = weights.transforms()(img).unsqueeze(0)    # 1 x 3 x 224 x 224
    with torch.no_grad():
        p = model(x).softmax(dim=1)[0]            # 1000 probabilità
    conf, idx = p.topk(5)
    for c, i in zip(conf, idx):
        print(f"{weights.meta['categories'][i]:24s} {c:.3f}")
print("VGG16");    top5(vgg16,    VGG16_Weights.IMAGENET1K_V1)
print("ViT-B/16"); top5(vit_b_16, ViT_B_16_Weights.IMAGENET1K_V1)
```

**Input**: `dog1.jpg`. **Output**: le 5 classi ImageNet più probabili e le probabilità per modello; **cambia solo il modello**.

#### **12.3. I risultati**

Eseguire il codice dal vivo e **compilare la tabella** (classe top-1 e la sua probabilità).

| Modello | Classe top-1 | Probabilità | Vettore di feature | Parametri |
|---|---|---|---|---:|
| VGG16 (CNN) | | | 4096 valori | 138,4 M |
| ViT-B/16 | | | 768 valori | 86,6 M |

- ImageNet ha **circa 120 razze di cane**: un modello può avere ragione su «cane» e sbagliare la razza; **guardare tutta la top-5**.
- **Un vettore più piccolo (768 invece di 4096) con qualità comparabile** significa **meno memoria e un k-NN più veloce** sulle proprie immagini.
- **La probabilità non è una confidenza calibrata**: due modelli possono essere **entrambi «sicuri» e non essere d’accordo** (Lezione 16).

> 💡 **Una traccia per la domanda 3 (nota didattica).** Il k-NN calcola $N\cdot D$ operazioni per richiesta (Lezione 19, §5.16): con 768 invece di 4096 dimensioni è 5,3 volte più veloce e occupa 5,3 volte meno memoria; la maledizione della dimensionalità (Lezione 20) favorisce anch’essa il vettore più corto. Se i due vettori danno accuratezze simili in validazione, il rasoio di Occam (Lezione 17, §5.2) sceglie il ViT.

#### **12.4. La classificazione delle immagini oggi: tabella estesa**

Stesso protocollo (pesi torchvision, validazione ImageNet-1k):

| Modello | Tipo | Anno | Parametri | GFLOPs | Top-1 (%) |
|---|---|---:|---:|---:|---:|
| VGG16 | CNN | 2014 | 138,4 M | 15,5 | 71,6 |
| MobileNetV3-Large | CNN (edge) | 2019 | 5,5 M | 0,22 | 75,3 |
| ResNet-50 (nuova ricetta) | CNN | 2015 | 25,6 M | 4,1 | 80,9 |
| ViT-B/16 | ViT | 2020 | 86,6 M | 17,6 | 81,1 |
| MaxViT-T | ibrido | 2022 | 30,9 M | 5,6 | 83,7 |
| ConvNeXt-Base | CNN | 2022 | 88,6 M | 15,4 | 84,1 |
| Swin V2-B | ViT (finestre) | 2022 | 87,9 M | 20,3 | 84,1 |
| EfficientNetV2-L | CNN | 2021 | 118,5 M | 56,1 | 85,8 |
| ViT-B/16 + pre-addestramento SWAG† | ViT | 2022 | 86,9 M | 55,5 | 85,3 |
| RegNetY-128GF + SWAG† | CNN | 2022 | 644,8 M | 374,6 | 88,2 |
| ViT-H/14 + SWAG† | ViT | 2022 | 633,5 M | 1016,7 | 88,6 |

**I migliori riportati negli articoli** (dati di pre-addestramento extra, protocolli diversi): ConvNeXt V2-Huge (CNN, 2023, 650 M): 88,9; EVA-02-L (ViT, 2023, 304 M): 90,0.

**La migliore CNN e il miglior ViT stanno entro circa 1 punto a dimensione simile**; **VGG16 è 17 punti sotto ViT-B con più parametri**. **I grandi salti vengono dai dati di pre-addestramento** (†SWAG: debolmente supervisionato, **3,6 miliardi di immagini Instagram**) **e dal costo** (GFLOPs: $10^9$ operazioni per immagine). torchvision model zoo (docs.pytorch.org/vision/stable/models); Singh et al., SWAG, CVPR 2022; Woo et al., ConvNeXt V2, CVPR 2023; Fang et al., EVA-02, 2023. Prospettiva: i valori cambiano con le nuove versioni.

---

### **13. Appendice: hardware, software, schede e altri dettagli (non d’esame)**


#### **13.1. AIoT HW: che cosa abilita l’AI?**

L’AIoT è resa possibile da dispositivi e infrastrutture capaci di **percepire, calcolare, comunicare e agire**.

| Componente | Esempi |
|---|---|
| **sensori** | telecamere, IMU, microfoni, temperatura, pressione, vibrazione, sensori nei pneumatici |
| **microcontrollori e CPU embedded** | ESP32, STM32, ARM Cortex-M, dispositivi di classe Raspberry Pi |
| **acceleratori di AI** | GPU, TPU, NPU, VPU, Edge TPU, Hailo, Jetson, FPGA (Lezione 23, §7) |
| **connettività** | Wi-Fi, Bluetooth, Zigbee, LoRaWAN, 4G/5G, Ethernet, fieldbus |
| **gateway edge** | PC industriali o macchine locali che eseguono runtime edge |

#### **13.2. AIoT SW: che cosa abilita l’AI?**

Il software rende possibili **interoperabilità, rilascio dei modelli, monitoraggio e controllo**.

| Componente | Esempi |
|---|---|
| **runtime embedded** | FreeRTOS, Eclipse ThreadX, Linux embedded |
| **runtime di AI** | LiteRT (ex TensorFlow Lite), ONNX Runtime, SDK dei produttori |
| **protocolli di comunicazione** | MQTT, HTTP/REST, **OPC UA**, **Modbus**, **CAN bus** |
| **servizi cloud/edge** | AWS IoT Core e Greengrass, Azure IoT Hub, piattaforme di ML nel cloud |
| **API e strumenti MLOps** | endpoint dei modelli, aggiornamenti OTA, logging, monitoraggio, pipeline di riaddestramento |

> 💡 **I tre protocolli industriali (nota didattica).** **Modbus** (1979) è il protocollo più semplice e diffuso tra PLC e sensori; **CAN bus** collega le centraline di un’auto o di una macchina con messaggi brevi e priorità; **OPC UA** è lo standard moderno dell’Industria 4.0 per lo scambio di dati tra macchine di produttori diversi, con sicurezza integrata. Un gateway edge spesso traduce Modbus o CAN in MQTT verso il cloud.

#### **13.3. Alcuni dispositivi IoT**

| Dispositivo | Caratteristiche | MATLAB | AWS IoT |
|---|---|:---:|:---:|
| **ATmega328P** (Arduino UNO) | microcontrollore **molto piccolo**, **8 bit**, **32 KB di flash** | sì | **no** |
| **Atmel ATSAMW25** (Arduino MKR1000) | SoC progettato per progetti e dispositivi IoT | — | — |
| processori **ARM** più grandi | **32 e 64 bit** | sì | **sì** |
| **ESP32** (Espressif) | **Wi-Fi e Bluetooth sul chip**: il microcontrollore che tutti usano oggi per i dispositivi connessi; dual core, 32 bit, **240 MHz, 520 KB di SRAM**, **pochi dollari**; il suo SDK gira su **FreeRTOS**; l’**ESP32-S3** aggiunge **istruzioni vettoriali per piccole reti neurali**; **ESP32-CAM**: ESP32 + telecamera da 2 MP, circa **8 USD** | — | — |
| **FPGA** + microcontrollore molto grande + **telecamere di visione** | — | — | — |

La slide riporta anche un costo di «**1 euro**», riferito al microcontrollore più piccolo.

<!-- SOURCE_VISUAL id="IS24-V72" source="L24-web.pdf" page="89" type="figura" description="Esempi di dispositivi IoT: ATmega328P, ATSAMW25, ESP32-CAM, FPGA" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **13.4. Raspberry Pi AI HAT+: gli acceleratori Hailo**

- Una **scheda aggiuntiva per Raspberry Pi 5** (collegamento PCIe) con un **acceleratore neurale Hailo**: **la rete gira sull’acceleratore**, **la CPU gestisce telecamera e applicazione**. Un modo **accessibile, economico ed efficiente nei consumi** per aggiungere l’AI.
- **13 TOPS** (Hailo-8L, INT8) nel primo AI Kit e nell’AI HAT+ (2024); **26 TOPS** con l’Hailo-8.
- **AI HAT+ 2** (2026): **Hailo-10H, 40 TOPS in INT4**, **8 GB di memoria a bordo**, circa **130 USD**: anche **piccoli modelli linguistici e visione-linguaggio**.

> ⚠️ **I TOPS in INT4 non sono confrontabili con i TOPS in INT8**: leggere sempre la precisione (Lezione 23, quantizzazione).

<!-- SOURCE_VISUAL id="IS24-V73" source="L24-web.pdf" page="90" type="figura" description="Raspberry Pi 5 con AI HAT+ Hailo" reason="Immagine visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **13.5. Google Coral**

- Una **piattaforma sviluppata da Google** per costruire e rilasciare l’AI **all’edge senza dipendere dall’elaborazione nel cloud**.
- È composta da **componenti hardware, strumenti software e modelli di ML pre-addestrati** progettati per un’elaborazione AI veloce ed efficiente **in locale** su sistemi embedded.
- **2025**: Google ha rilasciato **Coral NPU come open source**: un **progetto di NPU basato su RISC-V** per dispositivi indossabili (circa **512 GOPS a pochi mW**); i primi chip sono prodotti da **Synaptics**.

<!-- SOURCE_VISUAL id="IS24-V74" source="L24-web.pdf" page="91" type="figura" description="La piattaforma Google Coral" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Edge TPU e modelli pre-addestrati**:

- **Componente centrale**: l’**Edge TPU**, **4 TOPS (INT8) a circa 2 W**, in moduli **da 25 a 130 USD**; **raffreddamento passivo** (senza ventole) nella maggior parte delle configurazioni.
- **Un’ampia gamma di modelli pre-addestrati** da integrare nell’HW: **inferenza molto veloce**; **transfer learning sul dispositivo limitato a casi specifici**.

<!-- SOURCE_VISUAL id="IS24-V75" source="L24-web.pdf" page="92" type="figura" description="Moduli Edge TPU di Coral e modelli pre-addestrati" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **13.6. Schede e AIoT**

Tabelle comparative da https://qengineering.eu/deep-learning-with-raspberry-pi-and-alternatives.html.

<!-- SOURCE_VISUAL id="IS24-V76" source="L24-web.pdf" page="93" type="tabella" description="Confronto delle schede per il deep learning embedded (qengineering.eu)" reason="Tabella presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS24-V77" source="L24-web.pdf" page="94" type="tabella" description="Confronto delle schede per il deep learning embedded, seconda parte" reason="Tabella presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Un benchmark delle edizioni precedenti**: **FPS di modelli d’immagine** su schede delle edizioni precedenti del corso (RPi 4, Jetson Nano, Coral, NCS2). Fonte: qengineering.eu. Per le schede di oggi si veda la tabella del §5.2.

<!-- SOURCE_VISUAL id="IS24-V78" source="L24-web.pdf" page="95" type="grafico" description="FPS di modelli d'immagine su RPi 4, Jetson Nano, Coral e NCS2" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **13.7. Esempi di AIoT per il retail e i cartelloni pubblicitari**

L’AIoT usa tipicamente **capacità di visione artificiale e sensori** per **raccogliere informazioni** su clienti, sensori, veicoli, ecc. **British Airways, Piccadilly Circus, campagna #LookUp**: la lampadina resta spenta finché un passante non le cammina sotto. Allora la lampadina, grazie ai **sensori di movimento**, si accende: a chi guarda il cartellone sembra che la persona che passa sotto abbia appena avuto **un’idea fantastica**, il «*light bulb moment*».

<!-- SOURCE_VISUAL id="IS24-V79" source="L24-web.pdf" page="96" type="figura" description="Cartellone della campagna #LookUp di British Airways a Piccadilly Circus" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Il caso #LookUp (nota didattica).** Nella versione originale della campagna (2014) il cartellone mostrava un bambino che indicava gli aerei British Airways che passavano davvero sopra Piccadilly, con numero di volo e destinazione: un sistema di tracciamento degli aerei collegato in tempo reale. È un esempio di AIoT che crea valore senza raccogliere dati personali dei passanti.

#### **13.8. MATLAB: un’ottima piattaforma di ML, ma non solo**

E molte altre!

<!-- SOURCE_VISUAL id="IS24-V80" source="L24-web.pdf" page="97" type="figura" description="L'ecosistema MATLAB per il ML e l'IoT" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **13.9. Industria 4.0: le quattro caratteristiche sinergiche principali (non d’esame)**

<!-- SOURCE_VISUAL id="IS24-V81" source="L24-web.pdf" page="117" type="figura" description="Le quattro caratteristiche sinergiche dell'Industria 4.0" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

1. **Rete verticale dei sistemi di produzione intelligenti**: usa i **sistemi di produzione cyber-fisici** (CPPS) per permettere agli impianti di **reagire rapidamente ai cambiamenti di domanda o di scorte e ai guasti**. Le **fabbriche intelligenti si organizzano da sole** e permettono una produzione **specifica per il cliente e personalizzata** (clienti/mercato ↔ produzione).

<!-- SOURCE_VISUAL id="IS24-V82" source="L24-web.pdf" page="118" type="figura" description="Rete verticale dei sistemi di produzione intelligenti" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

2. **Integrazione orizzontale attraverso le reti globali della catena del valore**: reti locali e globali collegano via CPPS **logistica, magazzino, produzione, marketing e vendite**. Le reti sono **ottimizzate in tempo reale**: trasparenza e flessibilità per rispondere più rapidamente a problemi e guasti facilitano una migliore ottimizzazione globale. **La storia di ogni parte o prodotto è registrata** e accessibile in qualsiasi momento: **tracciabilità costante**.

<!-- SOURCE_VISUAL id="IS24-V83" source="L24-web.pdf" page="119" type="figura" description="Integrazione orizzontale lungo la catena del valore" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

3. **Ingegneria lungo l’intera catena del valore** (*through-engineering*): lo sviluppo e la produzione di **nuovi prodotti** e **sistemi di produzione** sono **integrati e coordinati con i cicli di vita dei prodotti**, creando nuove sinergie tra sviluppo del prodotto e sistemi di produzione.

<!-- SOURCE_VISUAL id="IS24-V84" source="L24-web.pdf" page="120" type="figura" description="Ingegneria lungo l'intera catena del valore" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

4. **Accelerazione attraverso le tecnologie esponenziali**: l’Industria 4.0 richiede già soluzioni di automazione **altamente cognitive** e **altamente autonome**; per aumentare ulteriormente l’autonomia e accelerare **personalizzazione e flessibilità**: **intelligenza artificiale**, **robotica avanzata**, **tecnologia dei sensori**.

<!-- SOURCE_VISUAL id="IS24-V85" source="L24-web.pdf" page="121" type="figura" description="Accelerazione attraverso le tecnologie esponenziali" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **14. Approfondimento — la simulazione d’esame dell’edizione precedente**


L’edizione precedente di questa lezione chiudeva il corso con una slide sulla simulazione finale, non presente nel PDF 2026/27.

> 📌 **Simulazione d’esame (edizione precedente).**
> - Una simulazione dell’esame viene **svolta in aula**. Come spiegato nella prima lezione, **non viene distribuito alcun PDF**.
> - Per le domande sulle **prime 4 e 6 lezioni** si vedano le simulazioni della **lezione 4** e della **lezione 6**.
> - Per le domande sulle **lezioni 4–13** si veda la simulazione della **lezione 13**.
> - Questa simulazione si concentra sulle **lezioni dalla 14** in poi (il testo della slide si interrompe qui).

> ⚠️ **Dove sono le simulazioni in questi appunti.** Nei materiali del corso:
> - la prima simulazione è nella **Lezione 4, §7**;
> - la simulazione breve di 5 domande è nella **Lezione 6**;
> - la simulazione di metà corso con 20 domande è nel PDF della **Lezione 12**, non della 13 come indicava la slide.
>
> La simulazione finale non è pubblicata; il metodo di risposta e le regole di punteggio restano quelli delle simulazioni precedenti.

---

### **15. Distinzioni da non confondere**


| Coppia | Differenza chiave |
|---|---|
| **CNN** / **ViT** | finestre locali, vista globale costruita a strati / ogni patch vede ogni patch dal primo strato |
| **patch** / **token** | blocco di pixel / vettore $D$ ottenuto con $E$ |
| **token [class]** / **media dei token** | due modi di riassumere l’immagine in $z$ |
| **bias induttivo** / **dati** | ipotesi incorporate (CNN, pochi dati) / pre-addestramento su larga scala (ViT) |
| **ImageNet val** / **ImageNet-V2** | insieme usato per anni / immagini nuove, 7–14 punti in meno |
| **IoT** / **AIoT** | sensori e reti / sensori e reti con modelli di AI che decidono |
| **dispositivo** / **edge** / **cloud** | latenza minima, risorse minime / gateway, offline / modelli grandi, dati in uscita |
| **TLS sul canale** / **firma del firmware** | protegge il trasporto dell’aggiornamento / garantisce che il firmware venga dal produttore |
| **connessione in uscita** / **porta in ingresso** | il dispositivo chiama il cloud / chiunque può chiamare il dispositivo |
| **CRA** / **NIST CSF 2.0** | UE, obbligatorio, marchio CE / USA, volontario, sei funzioni |
| **RTOS** / **OS generico** | tempo di risposta garantito ($R\le D$) / nessuna garanzia di preemption |
| **flash** / **RAM** di un MCU | pesi del modello / attivazioni durante l’inferenza |
| **TOPS INT8** / **TOPS INT4** / **sparsi** | non confrontabili direttamente |
| **TinyML** / **ML tradizionale** | KB e µW / GB e W, divario ~$10^4$ |
| **IaaS** / **PaaS** / **SaaS** | ospitare / costruire / consumare |
| **V2V diretto** / **veicolo–rete–veicolo** | ~100 ms, collisioni / secondi, ghiaccio |
| **sensore reale** / **sensore virtuale** | misura una grandezza / la stima fondendo altri sensori |
| **Industria 4.0** / **5.0** | il come (tecnologie) / il perché e per chi (obiettivi) |
| **descrittiva** / **diagnostica** / **predittiva** / **prescrittiva** | che cosa / perché / che cosa succederà / come farlo accadere |
| **prototipo da 8 $** / **sistema da 200 000 €** | dimostra che funziona / garantisce che funzioni sempre |
