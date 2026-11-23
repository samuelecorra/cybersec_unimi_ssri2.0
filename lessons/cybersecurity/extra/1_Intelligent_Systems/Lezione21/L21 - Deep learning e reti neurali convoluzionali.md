## **Lezione 21: Deep learning e reti neurali convoluzionali — dal perceptron in Keras alle CNN, ai modelli pre-addestrati e al transfer learning**

La lezione riprende le reti della Lezione 20 e le scrive in **Keras**: il perceptron dell’AND diventa `Dense(1)`, la rete dello XOR due righe `Dense`, e `fit()` nasconde il ciclo di backpropagation già fatto a mano. Si confrontano due **funzioni di perdita** (errore quadratico medio e cross-entropy) e si addestra una rete per il guasto di una pompa con la curva di validazione. Poi il **deep learning**: da dove viene (Fukushima, la backpropagation del 1986, LeNet, AlexNet, i Nobel del 2024), che cos’è (una gerarchia di feature apprese invece che progettate) e perché funziona adesso. Il cuore sono le **reti neurali convoluzionali**: la convoluzione passo per passo, la formula della dimensione d’uscita, i canali, il conteggio dei parametri, ReLU, max pooling, flatten, softmax e il ciclo di addestramento, fino a **CNN-A**, una piccola rete in stile LeNet addestrata in Keras sulle cifre MNIST (98,65% di accuratezza di test) e alla sua versione moderna CNN-B. Si chiude con le **reti pre-addestrate** (AlexNet, LeNet-5, VGG16, famiglie moderne, ResNet), il **transfer learning**, YOLO, i rischi di profilazione, e i casi d’uso: classificare una foto con InceptionV3 e GoogLeNet, e trattare come immagini spettrogrammi, la scacchiera del Go e le frasi.

> **Fonte.** La lezione segue il PDF dell’edizione 2026/27 `sources_2026-27/L21-web.pdf` (109 pagine, di cui 93–109 di appendice). I segnaposto per le figure indicano la pagina del PDF. Il notebook della cartella (`Image_classification_Inception_v3_COLAB.ipynb`) è commentato nel §10; `Truck.jpg` e `Truck299.jpg` sono le immagini d’ingresso del caso d’uso.


### **0. Panoramica**


1. il perceptron e la rete multistrato della Lezione 20, scritti in **Keras**;
2. il **deep learning**: da dove viene e perché funziona adesso;
3. le **reti neurali convoluzionali**: convoluzione, pooling, softmax, addestramento;
4. **CNN-A**, una piccola CNN in stile LeNet (non AlexNet), in Keras sulle cifre scritte a mano;
5. **CNN pre-addestrate** e **transfer learning**;
6. **casi d’uso**: classificare una foto; segnali, giochi e testi come immagini;
7. punti principali.

---

### **1. Strumenti: dal perceptron all’MLP in Keras**


Le reti della Lezione 20, scritte e addestrate in poche righe di Python.

#### **1.1. Keras in una slide**

> 📌 **Definizione.** **Keras** è una libreria Python **open source e di alto livello** per il deep learning: un modello si descrive come **una sequenza di strati**, e addestramento e previsione sono **chiamate singole**.

- **Keras 3 è multi-backend**: lo stesso codice gira su **JAX, TensorFlow o PyTorch**.
- Strati, funzioni di perdita, ottimizzatori e reti pre-addestrate **già pronti**; installato su Colab.
- **Epoca**: un passaggio sui dati di training.

| Passo | Chiamata | Che cosa fa |
|---|---|---|
| 1. descrivere la rete | `Sequential([strati])` | gli strati, in ordine |
| 2. configurare l’addestramento | `compile(loss, optimizer)` | **nessun codice viene compilato**: loss = come misurare l’errore, ottimizzatore = come aggiornare i pesi |
| 3. addestrare | `fit(x, y, epochs)` | il ciclo della Lezione 20 (avanti, errore, aggiornamento), ripetuto per molte epoche su tutti i campioni |
| 4. usare la rete | `predict(x_new)` | la rete addestrata su dati nuovi (inferenza) → previsioni $\hat{y}$ |

#### **1.2. Che cosa fa `fit()`: il ciclo di addestramento**

Il ciclo nascosto in `model.fit(x, y, epochs)`, scritto in modo esplicito (qui in PyTorch, dove lo si scrive da sé):

```python
for epoch in range(epochs):        # ripetere
    for x, y in batches:           # per esempio 32 campioni
        y_hat = model(x)           # 1. passo in avanti
        loss = loss_fn(y_hat, y)   # 2. errore
        opt.zero_grad()
        loss.backward()            # 3. gradienti
        opt.step()                 # 4. aggiornare w
```

1. **avanti**: $\hat{y}$; 2. **errore**: $\text{loss}(\hat{y},y)$; 3. **gradienti** $\partial L/\partial w$; 4. **aggiornamento** $w\leftarrow w-\alpha\,\partial L/\partial w$; poi il batch successivo; dopo tutte le epoche: i pesi $w$ addestrati.

> ⚠️ **`compile()` in Keras non compila codice**: **configura soltanto l’addestramento**, cioè sceglie `loss_fn` = come si misura l’errore (per esempio la cross-entropy) e `opt` = come si aggiornano i pesi (per esempio SGD o Adam con learning rate $\alpha$), più le metriche da stampare (per esempio l’accuratezza).

Richiamo dalla Lezione 20: **gli stessi passi della backpropagation**. **Un passaggio su tutti i batch è un’epoca.** $\alpha$: learning rate. Nelle slide successive si chiama solo `fit()`: questi quattro passi girano dentro di essa.

> 💡 **`zero_grad()` (nota didattica).** In PyTorch i gradienti si **accumulano** a ogni `backward()`: senza azzerarli, il gradiente di un batch si sommerebbe a quello del precedente. È un dettaglio che Keras gestisce da solo, ed è uno degli errori più comuni scrivendo il ciclo a mano. Un **batch** di 32 campioni significa che il gradiente è la media su 32 esempi: è il *mini-batch* della Lezione 20 (§6.11).

#### **1.3. Un perceptron in Keras (1): la rete e il training set**

**Il training set: la funzione AND.**

| Campione | $x_1$ | $x_2$ | $y$ |
|---:|---:|---:|---:|
| 1 | 0 | 0 | 0 |
| 2 | 0 | 1 | 0 |
| 3 | 1 | 0 | 0 |
| 4 | 1 | 1 | 1 |

$N=4$ campioni, $d=2$ feature, $y$ = uscita desiderata. **Da imparare**: i pesi $w_1,w_2$ e la soglia $\theta$. **Uscita**: $\hat{y}=1$ se $X=w_1x_1+w_2x_2\ge\theta$, altrimenti 0.

Richiamo dalla Lezione 20: lo stesso perceptron imparava l’AND con la regola delta. **Ora si lascia l’addestramento a Keras**: prima i dati, poi il modello.

#### **1.4. Un perceptron in Keras (2): il training set in Python**

```python
import numpy as np
# ingressi: una riga per campione, una colonna per feature
x_train = np.array([[0, 0],    # campione 1
                    [0, 1],    # campione 2
                    [1, 0],    # campione 3
                    [1, 1]])   # campione 4
# uscite desiderate, nello stesso ordine
y_train = np.array([0, 0, 0, 1])
print(x_train.shape, y_train.shape)
# -> (4, 2) (4,)
```

> 📌 **Regola.** `x_train` è la **matrice d’ingresso $N\times d$** (qui $4\times 2$); `y_train` contiene le **$N$ uscite desiderate**, riga per riga **nello stesso ordine**. Ogni dataset del corso, dall’AND alle immagini, segue questa disposizione (per le immagini ogni campione è un array $28\times 28\times 1$ invece di una riga).

#### **1.5. Un perceptron in Keras (3): il modello e il suo addestramento**

```python
import keras
from keras import layers

model = keras.Sequential([
    keras.Input(shape=(2,)),                  # x1, x2
    layers.Dense(1, activation="sigmoid")     # il neurone
])
# configurare l'addestramento (nessun codice compilato)
model.compile(optimizer=keras.optimizers.SGD(1.0),
              loss="binary_crossentropy")
model.fit(x_train, y_train, epochs=300)
```

| Keras | Figura |
|---|---|
| `Input(shape=(2,))` | ingressi $x_1,x_2$ |
| `Dense(1)` | un neurone ($\Sigma$ + attivazione) |
| kernel (2 numeri) | pesi $w_1,w_2$ |
| bias $b$ | $-\theta$ (soglia) |
| sigmoid | un gradino liscio |

`Dense(1)` è il neurone della Lezione 20 (**3 parametri**). Keras usa una **sigmoide invece del gradino**, perché l’addestramento con il gradiente ha bisogno di una funzione **liscia**; poi si applica la soglia a 0,5. Learning rate $\alpha=1{,}0$, 300 epoche.

#### **1.6. Un perceptron in Keras (4): l’uscita e i pesi appresi**

```python
p = model.predict(x_train)          # (4, 1), in 0..1
y_hat = (p >= 0.5).astype(int)      # classe 0 / 1

w, b = model.layers[0].get_weights()
print(w.ravel(), b)                 # [5.0 5.0] [-7.68]
print("theta =", -b)                # theta = [7.68]
```

| $x_1$ | $x_2$ | $y$ | $p$ | $\hat{y}$ |
|---:|---:|---:|---:|---:|
| 0 | 0 | 0 | 0,00 | 0 |
| 0 | 1 | 0 | 0,06 | 0 |
| 1 | 0 | 0 | 0,06 | 0 |
| 1 | 1 | 1 | 0,91 | 1 |

$w_1=w_2=5{,}0$, $\theta=7{,}68$: per (1,1) $X=10\ge 7{,}68\Rightarrow\hat{y}=1$; per (0,1) $X=5<7{,}68\Rightarrow\hat{y}=0$.

**Input**: `x_train`. **Output**: $p$ (4 numeri in 0..1) e la classe $\hat{y}$. **Tutti e 4 i campioni corretti** (dall’epoca 20). `get_weights()` restituisce il **kernel** (i pesi) e il **bias**: il perceptron appreso **si può leggere e verificare a mano**.

> 💡 **Verifica (nota didattica).** $\sigma(0-7{,}68)=0{,}0005$; $\sigma(5-7{,}68)=\sigma(-2{,}68)=0{,}064$; $\sigma(10-7{,}68)=\sigma(2{,}32)=0{,}91$. La retta di decisione è $5x_1+5x_2=7{,}68$, cioè $x_1+x_2=1{,}54$: separa (1,1) dagli altri tre punti con un margine molto più ampio della soluzione «al limite» del perceptron della Lezione 20 ($x_1+x_2=2$), perché la cross-entropy continua a spingere i pesi finché le probabilità non si allontanano da 0,5.

#### **1.7. Dal perceptron all’MLP: lo XOR in Keras**

```python
y_train = np.array([0, 1, 1, 0])                  # XOR
model = keras.Sequential([
    keras.Input(shape=(2,)),                      # x1, x2
    layers.Dense(2, activation="sigmoid"),        # neuroni 3, 4
    layers.Dense(1, activation="sigmoid"),        # neurone 5
])
model.summary()                                   # 6 + 3 = 9 parametri
model.compile(optimizer=keras.optimizers.Adam(0.1),
              loss="binary_crossentropy",
              metrics=["accuracy"])
```

<!-- SOURCE_VISUAL id="IS21-V01" source="L21-web.pdf" page="10" type="schema" description="Rete XOR con neuroni 3, 4 e 5 e la corrispondenza con le due righe Dense" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Una riga `Dense` per strato.** Parametri: strato nascosto $2\cdot 2$ pesi + 2 soglie = **6**; strato d’uscita $2+1=$ **3**; totale **9**: $w_{13},w_{14},w_{23},w_{24},w_{35},w_{45}$ e $\theta_3,\theta_4,\theta_5$. Richiamo dalla Lezione 20: **un perceptron non può imparare lo XOR; 2 neuroni nascosti sì**. Dentro ogni neurone: la sua soglia $\theta$.

#### **1.8. Quanto sbaglia la rete? Due funzioni di perdita**

- L’uscita della rete, di solito chiamata $\hat{y}$, qui si chiama **$p$**: con una sigmoide è un numero in $(0,1)$, letto come la **probabilità che il campione sia della classe 1** (spesso chiamata «**confidenza**», un termine ambiguo).
- $y_i$: uscita desiderata del campione $i$; $p_i$: uscita della rete; $N$: numero di campioni.

**Due misure comuni dell’errore (loss), minimizzate dall’addestramento**:

$$L^{(1)}=\text{MSE}=\frac1N\sum_{i=1}^{N}(y_i-p_i)^2,\qquad L^{(2)}=-\frac1N\sum_{i=1}^{N}\Big[y_i\ln p_i+(1-y_i)\ln(1-p_i)\Big].$$

$L^{(1)}$: **errore quadratico medio** (richiamo dalla Lezione 20: errore $e=y-\hat{y}$). $L^{(2)}$: **cross-entropy binaria**, `loss="binary_crossentropy"` in `compile()`; $\ln$: logaritmo naturale.

> ⚠️ **L’accuratezza** (frazione di classi corrette, con $p$ sogliato a 0,5) è una **metrica da leggere, non una loss**: **non dà gradiente** (è costante a tratti).

#### **1.9. Le due loss su quattro campioni**

| $i$ | $y_i$ | $p_i$ | $(y_i-p_i)^2$ | $y_i\ln p_i$ | $(1-y_i)\ln(1-p_i)$ | termine di $L^{(2)}$ |
|---:|---:|---:|---:|---:|---:|---:|
| 1 | 1 | 0,9 | 0,01 | −0,11 | 0 | 0,11 |
| 2 | 1 | 0,6 | 0,16 | −0,51 | 0 | 0,51 |
| 3 | 1 | 0,1 | 0,81 | −2,30 | 0 | 2,30 |
| 4 | 0 | 0,1 | 0,01 | 0 | −0,11 | 0,11 |
| **media su $N=4$** | | | $L^{(1)}=0{,}25$ | | | $L^{(2)}=0{,}76$ |

- Per ogni campione **sopravvive un solo termine** di $L^{(2)}$: $-\ln p_i$ se $y_i=1$, $-\ln(1-p_i)$ se $y_i=0$.
- **Il campione 3 è sbagliato e sicuro di sé: domina $L^{(2)}$.** Con $p_3=0{,}01$ l’errore quadratico sarebbe 0,98 (**al massimo 1**), il termine di cross-entropy **4,61 (nessun limite superiore)**.

> 📌 **Perché la cross-entropy per la classificazione.** Con uscite sigmoide/softmax il gradiente di $L^{(1)}$ **diventa minuscolo quando l’uscita satura**, proprio quando la rete **sbaglia di molto**: l’apprendimento si blocca. Il gradiente di $L^{(2)}$ resta $\approx p-y$. **$L^{(1)}$ resta la loss standard per la regressione.** Con $C$ classi e softmax: $L=-\ln p_{\text{vera}}$ (parte sulle CNN). Goodfellow, Bengio, Courville, *Deep Learning*, MIT Press 2016, §6.2.

> 💡 **Il conto del gradiente (nota didattica).** Con $p=\sigma(z)$: $\frac{\partial L^{(1)}}{\partial z}=-2(y-p)\,p(1-p)$, che contiene la pendenza della sigmoide $p(1-p)$ e si annulla quando $p\to 0$ o $p\to 1$, anche se $y-p$ è grande. Per $L^{(2)}$, $\frac{\partial}{\partial z}\big[-y\ln p-(1-y)\ln(1-p)\big]=p-y$: la pendenza della sigmoide si cancella. È il rimedio al gradiente che svanisce in uscita (Lezione 20, §6.14). I valori della tabella: $(0{,}01+0{,}16+0{,}81+0{,}01)/4=0{,}2475$ e $(0{,}105+0{,}511+2{,}303+0{,}105)/4=0{,}756$.

#### **1.10. Dentro il modello: dove sono $w_{13}$ e $\theta_3$?**

```python
# strato nascosto: kernel W (2x2) e bias b (2)
W, b = model.layers[0].get_weights()
print(W.shape, b.shape)        # (2, 2) (2,)
# riga = ingresso, colonna = neurone
print("w13 =", W[0, 0])        # ingresso 1 -> neurone 3
print("theta3 =", -b[0])       # soglia del neurone 3
```

$$W=\begin{bmatrix}w_{13}&w_{14}\\w_{23}&w_{24}\end{bmatrix},\qquad b=\begin{bmatrix}-\theta_3\\-\theta_4\end{bmatrix}.$$

| Parametro | dopo l’inizializzazione | dopo l’addestramento |
|---|---:|---:|
| $w_{13}$ | 0,39 | 8,47 |
| $w_{14}$ | 0,47 | 8,01 |
| $w_{23}$ | −1,16 | −8,63 |
| $w_{24}$ | −1,20 | −8,81 |
| $\theta_3$ | 0,00 | −4,53 |
| $\theta_4$ | 0,00 | 4,21 |
| $w_{35}$, $w_{45}$ | −0,87; 1,17 | −12,53; 13,03 |
| $\theta_5$ | 0,00 | −6,08 |

Keras parte con **pesi casuali e bias nulli** (quindi tutte le $\theta=0$), come nel passo 1 della backpropagation (Lezione 20). **Dopo 500 epoche i pesi sono grandi: le sigmoidi funzionano quasi come gradini.** Stesso seme e codice in `scripts/keras_perceptron_mlp.py`.

> 💡 **Che cosa ha imparato ogni neurone (nota didattica).** Il neurone 3 calcola $\sigma(8{,}47x_1-8{,}63x_2+4{,}53)$ (con $-\theta_3=4{,}53$): vale quasi 1 tranne quando $x_2=1$ e $x_1=0$. Il neurone 4 calcola $\sigma(8{,}01x_1-8{,}81x_2-4{,}21)$: vale quasi 1 solo per (1,0). L’uscita $\sigma(-12{,}53h_3+13{,}03h_4+6{,}08)$ combina le due rette. Le due rette non sono quelle «OR e AND» della Lezione 20: ci sono più soluzioni equivalenti, e la discesa del gradiente ne trova una.

#### **1.11. Addestrare e controllare l’MLP**

```python
hist = model.fit(x_train, y_train, epochs=500)
plt.plot(hist.history["loss"])    # curva di apprendimento
```

| $x_1$ | $x_2$ | $y$ | $p$ | $\hat{y}$ |
|---:|---:|---:|---:|---:|
| 0 | 0 | 0 | 0,00 | 0 |
| 0 | 1 | 1 | 1,00 | 1 |
| 1 | 0 | 1 | 1,00 | 1 |
| 1 | 1 | 0 | 0,00 | 0 |

<!-- SOURCE_VISUAL id="IS21-V02" source="L21-web.pdf" page="14" type="grafico" description="Curve di loss e accuratezza dell'MLP XOR su 500 epoche e regioni di decisione della rete addestrata" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Controllo**: la loss scende, l’accuratezza è 1 **dall’epoca 25** (tratteggio). ⚠️ **Attenzione**: con 2 neuroni nascosti **solo 12 partenze casuali su 20 hanno risolto lo XOR in 500 epoche**; le altre **si sono bloccate in un minimo locale**. **Guardare sempre la curva.**

> 💡 **Perché si blocca (nota didattica).** Con soli 2 neuroni nascosti la rete ha esattamente la capacità minima per lo XOR: se all’inizio entrambi i neuroni imparano la stessa retta, o uno satura, l’errore resta fermo su un altopiano (per esempio tre punti giusti su quattro) e il gradiente non trova la strada. Con 4–8 neuroni nascosti quasi tutte le partenze convergono: un po’ di capacità in più rende il paesaggio dell’errore più facile da percorrere, ed è uno dei motivi per cui le reti reali sono sovradimensionate e poi regolarizzate.

#### **1.12. Che cosa ha imparato l’MLP? 1000 ingressi casuali**

```python
# dopo l'addestramento: esplorare gli ingressi
rng = np.random.default_rng(0)
x_new = rng.uniform(-0.2, 1.2, (1000, 2))
p = model.predict(x_new)[:, 0]          # 1000 valori

ax = plt.figure().add_subplot(
    projection="3d")
ax.plot_trisurf(x_new[:, 0], x_new[:, 1],
                p, cmap="coolwarm")
```

- **Input**: 1000 coppie casuali $(x_1,x_2)$ in $[-0{,}2;\ 1{,}2]^2$; **output**: 1000 valori $p$.
- $p\approx 1$ (rosso) vicino a (0,1), (1,0); $p\approx 0$ (blu) **lungo la diagonale**.
- La **linea scura sul pavimento**: la frontiera di decisione $p=0{,}5$, **due rette come nella Lezione 20**.

<!-- SOURCE_VISUAL id="IS21-V03" source="L21-web.pdf" page="15" type="grafico" description="Superficie 3D dell'uscita p dell'MLP su 1000 ingressi casuali con la frontiera p = 0,5" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.13. La rete standard in Keras: una pompa che può guastarsi**

```python
# X: (2000, 5) misure, y: (2000,) 0/1
X = (X - X_tr.mean(0)) / X_tr.std(0)            # z-score
model = keras.Sequential([
    keras.Input(shape=(5,)),                    # x1..x5
    layers.Dense(4, activation="relu"),         # nascosto 1
    layers.Dense(4, activation="relu"),         # nascosto 2
    layers.Dense(1, activation="sigmoid")])     # p
model.compile("adam", "binary_crossentropy",
              metrics=["accuracy"])
hist = model.fit(X_tr, y_tr, epochs=60,
                 validation_split=0.2)
```

- **Compito**: classificazione binaria (etichette 1 = la pompa **si è guastata entro 7 giorni**, 0 = no).
- **Input**: $x_1..x_5$ = temperatura (°C), vibrazione (mm/s), corrente (A), pressione (bar), velocità (rpm). **Output**: $\hat{y}\in[0,1]$, **probabilità di guasto**.
- **Parametri**: $5\cdot 4+4$, $4\cdot 4+4$, $4+1$ = **49**. Dati: **2000 pompe sintetiche** (appendice, §9.1, e Laboratorio 1).

<!-- SOURCE_VISUAL id="IS21-V04" source="L21-web.pdf" page="16" type="schema" description="Rete con cinque ingressi, due strati nascosti da 4 e un'uscita per il guasto della pompa" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Richiamo dalla Lezione 20: **una rete feed-forward è una funzione** (l’esempio della pompa).

> ⚠️ **Lo z-score con le statistiche del training (nota didattica).** La riga `X = (X - X_tr.mean(0)) / X_tr.std(0)` usa media e deviazione standard **del solo training set**, anche per validazione e test: è la regola anti-leakage della Lezione 10. Le cinque misure hanno scale molto diverse (1450 rpm contro 4,1 mm/s, Lezione 20, §3.2): senza z-score la velocità dominerebbe la prima somma pesata. La ReLU (§3.11) sostituisce la sigmoide negli strati nascosti.

#### **1.14. Addestrare e controllare: la curva di validazione**

<!-- SOURCE_VISUAL id="IS21-V05" source="L21-web.pdf" page="17" type="grafico" description="Curve di loss e accuratezza di training e validazione per la rete della pompa su 60 epoche" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **Classificazione, 2000 pompe sintetiche**: **1280 training, 320 validazione, 400 test** (dati: appendice).
- **Validazione** = dati **non usati per aggiornare i pesi**.
- **Le due curve restano vicine: nessun overfitting.**
- **Accuratezza di test 83,7%** contro il **71,7% di «prevedere sempre nessun guasto»**.
- **Pompa nuova** (72 °C, 4,1 mm/s, 12,5 A, 6,2 bar, 1450 rpm): $\hat{y}=0{,}91$.

Dati sintetici (seme fisso): solo geometria, nessuna misura reale. Script: `scripts/keras_perceptron_mlp.py`.

> 💡 **Le partizioni e il riferimento (nota didattica).** 2000 pompe: 400 (20%) per il test; delle 1600 rimaste, `validation_split=0.2` ne tiene 320 per la validazione e 1280 per il training. Il riferimento del 71,7% è la **classe maggioritaria** sul test (Lezione 16, §2.3): una rete che non batte quel numero non ha imparato nulla. 83,7% lo batte di 12 punti, ma su 400 pompe l’incertezza è di circa ±3,6 punti (Lezione 14, §4.2). Con i guasti la metrica giusta non è solo l’accuratezza: un guasto mancato costa più di un falso allarme (recall, Lezione 5).

---

### **2. Panoramica: il deep learning**


Da dove viene, che cos’è, e perché funziona adesso.

#### **2.1. DL ⊂ ML ⊂ AI**

**Il deep learning è un sottoinsieme del machine learning, che è un sottoinsieme dell’intelligenza artificiale.** I suoi metodi sono **vecchi** (reti neurali, Lezione 20); il suo successo è **recente** (anni 2010).

<!-- SOURCE_VISUAL id="IS21-V06" source="L21-web.pdf" page="19" type="figura" description="Insiemi annidati deep learning, machine learning, intelligenza artificiale" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2. La tabella di marcia**

- **1980**: Fukushima, **Neocognitron**; **1986**: **backpropagation** (Rumelhart, Hinton, Williams).
- **1998**: LeCun, **LeNet**: la **prima CNN in produzione** (assegni bancari).
- **2006–2012**: il gruppo di Hinton addestra reti profonde su **hardware parallelo (GPU)**.
- **2012**: **AlexNet**: CNN profonda + GPU + ReLU + **1,2 milioni di immagini etichettate**.

<!-- SOURCE_VISUAL id="IS21-V07" source="L21-web.pdf" page="20" type="figura" description="Linea del tempo del deep learning dal Neocognitron ad AlexNet" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.3. La rilevanza: due premi Nobel nel 2024**

- **Fisica**: **Hopfield e Hinton**, «per scoperte e invenzioni fondamentali che rendono possibile il machine learning con le reti neurali artificiali». Hinton è coautore dell’articolo del 1986 sulla backpropagation (Lezione 20).
- **Chimica**: **Baker** (progettazione di proteine); **Hassabis e Jumper** per **AlphaFold**, una rete profonda che **predice la struttura 3D di una proteina dalla sua sequenza di amminoacidi**.

<!-- SOURCE_VISUAL id="IS21-V08" source="L21-web.pdf" page="21" type="figura" description="I premi Nobel 2024 per la fisica e per la chimica legati al deep learning" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.4. Il deep learning: una definizione**

> 📌 **Definizione.** Il **deep learning** è una classe di metodi di machine learning che usano **molti strati non lineari impilati**; ogni strato trasforma l’uscita del precedente, così **le feature vengono apprese come una gerarchia**, dal basso livello all’alto livello, **invece di essere progettate a mano**.

- **Input**: dati grezzi (pixel, campioni di un segnale, parole).
- **Output**: una classe, un valore, o una **nuova rappresentazione**.
- **Supervisionato** (classificatori, regressori) o **non supervisionato** (analisi di pattern).
- Richiamo dalla Lezione 20: una rete profonda ha **molti strati nascosti** (3 o più).

<!-- SOURCE_VISUAL id="IS21-V09" source="L21-web.pdf" page="22" type="figura" description="Illustrazione della definizione di deep learning" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.5. Perché adesso? Calcolo, dati e interesse**

- Le reti profonde **hanno bisogno di calcolo**: da **1 GFLOPS (1986)** a **decine di TFLOPS su una GPU (2016)**.
- Un **inverno dell’AI** è un periodo di **ridotti finanziamenti e interesse** per la ricerca in AI: due inverni, **1974–1980** e **1987–1993**.
- **Google Trends, Italia, 2004–oggi**: «deep learning» (blu) e «artificial intelligence» (rosso), 100 = interesse massimo. Il deep learning cresce **da circa il 2015**; entrambi **saltano dopo il 2022**.

<!-- SOURCE_VISUAL id="IS21-V10" source="L21-web.pdf" page="23" type="grafico" description="Crescita della potenza di calcolo e Google Trends di deep learning e intelligenza artificiale in Italia" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **I numeri (nota didattica).** Da 1 GFLOPS a «decine di TFLOPS» è un fattore $10^4$ in trent’anni; oggi una GPU da centro di calcolo supera i petaFLOPS in bassa precisione (Lezione 23). Il salto dopo il 2022 è ChatGPT (novembre 2022): la stessa tecnologia, i transformer (Lezione 13), resa visibile al grande pubblico.

#### **2.6. Machine learning contro deep learning**

Richiamo dalla Lezione 20: visto come «un primo sguardo». **Nel ML classico un ingegnere progetta l’estrazione delle feature**; nel deep learning **estrazione delle feature e classificazione sono un unico blocco**, appreso insieme dagli esempi. Le slide successive mostrano che cosa significava «progettato da un ingegnere» nella visione artificiale, e che aspetto hanno le feature apprese.

<!-- SOURCE_VISUAL id="IS21-V11" source="L21-web.pdf" page="24" type="schema" description="Pipeline ML classica con estrazione delle feature progettata contro deep learning con un unico blocco appreso" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.7. Prima del deep learning: il progettista estrae le feature**

Gli **indizi di livello medio** (dai «*token*» di D. Marr) e le **parti degli oggetti** sono **difficili da progettare a mano**. Anni di ricerca hanno prodotto **descrittori fatti a mano** di bordi e gradienti (**SIFT, HoG, spin images, textons, SURF, LBP, istogrammi di colore**…). **Domanda**: e se si imparassero gli estrattori di feature **direttamente dai dati**? Adattato da © J. Hays, R. Fergus.

<!-- SOURCE_VISUAL id="IS21-V12" source="L21-web.pdf" page="25" type="figura" description="Descrittori progettati a mano e indizi di livello medio difficili da progettare" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.8. Un estrattore di feature classico: la convoluzione**

**Filtro $3\times 3$ $K$** (Sobel verticale):

$$K=\begin{bmatrix}-1&0&1\\-2&0&2\\-1&0&1\end{bmatrix}$$

**Input** $I$: immagine in scala di grigi $200\times 300$; **output**: feature map $198\times 298$ (arancio = bordo scuro→chiaro, blu = chiaro→scuro).

<!-- SOURCE_VISUAL id="IS21-V13" source="L21-web.pdf" page="26" type="figura" description="Immagine del camion e la sua feature map con il filtro di Sobel verticale" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Definizione.** Una **convoluzione** fa scorrere un **piccolo filtro $K$** (una griglia $3\times 3$ di numeri) sull’immagine $I$ e, in ogni posizione, **moltiplica la patch per il filtro e somma i prodotti**: il risultato è una **nuova immagine, la feature map**. Risponde alla domanda «**quanto questa patch somiglia al filtro?**». Qui un filtro di bordo verticale **progettato a mano** (Sobel): la cabina, le ruote e i bordi del rimorchio si illuminano. I progettisti usavano questi filtri per estrarre feature; **una CNN li imparerà**.

#### **2.9. Filtri diversi, feature map diverse**

| bordi verticali | bordi orizzontali | bordi diagonali | sfocatura (media, $\times\frac19$) |
|---|---|---|---|
| $\begin{bmatrix}-1&0&1\\-2&0&2\\-1&0&1\end{bmatrix}$ | $\begin{bmatrix}-1&-2&-1\\0&0&0\\1&2&1\end{bmatrix}$ | $\begin{bmatrix}0&1&2\\-1&0&1\\-2&-1&0\end{bmatrix}$ | $\begin{bmatrix}1&1&1\\1&1&1\\1&1&1\end{bmatrix}$ |

<!-- SOURCE_VISUAL id="IS21-V14" source="L21-web.pdf" page="27" type="figura" description="Le quattro feature map del camion ottenute con i quattro filtri" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Stesso input, quattro filtri $3\times 3$, quattro mappe.** Uno strato convolutivo **applica molti filtri in parallelo**; le loro uscite si **impilano come canali**. Qui i filtri sono fatti a mano; **in una CNN i 9 numeri di ogni filtro vengono appresi dall’addestramento**.

> 💡 **Perché la media sfoca (nota didattica).** Il filtro della media sostituisce ogni pixel con la media dei suoi 9 vicini: le variazioni rapide (bordi, rumore) si attenuano. I filtri di bordo hanno somma dei coefficienti nulla: su una regione uniforme danno 0, e rispondono solo dove l’intensità cambia. È la stessa idea di correlazione con un pattern della Lezione 10 (§2).

#### **2.10. Imparare una gerarchia di feature**

**Immagine** (pixel) → **strato 1** (feature semplici, «bordi») → **strato 2** («parti di oggetti») → **strato 3** («oggetti») → **classificatore** («c’è un volto?»); **l’addestramento regola tutti gli strati insieme**.

<!-- SOURCE_VISUAL id="IS21-V15" source="L21-web.pdf" page="28" type="figura" description="Gerarchia di feature da pixel a bordi, parti e oggetti" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Ogni strato estrae feature dall’uscita dello strato precedente. Si addestrano tutti gli strati insieme.** Adattato da © J. Hays, R. Fergus.

#### **2.11. Addestrare una volta, rilasciare molte volte**

- **Addestramento**: molti esempi etichettati; punteggi (per esempio gatto 0,62, tazza 0,30, razzo 0,08) contro l’etichetta «gatto» → errore $e$ → **all’indietro: l’errore cambia tutti i pesi** (convoluzioni + pooling, densi).
- **Rilascio (inferenza)**: una foto nuova, **pesi fissi**, **solo in avanti** ⇒ gatto (0,91).

<!-- SOURCE_VISUAL id="IS21-V16" source="L21-web.pdf" page="29" type="schema" description="Addestramento con passo avanti e indietro contro rilascio con solo passo avanti" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Addestramento**: avanti, errore, indietro; molti esempi, molte epoche (**lento, GPU**). **Rilascio**: pesi fissi, un passo in avanti (**veloce, anche su dispositivi piccoli**). Punteggi illustrativi; foto: dati d’esempio di scikit-image, pubblico dominio.

#### **2.12. Guardare dentro: che cosa hanno imparato le unità?**

- **Strato 1 (conv)**: 25 filtri $3\times 3$: **bordi orientati**.
- **Strato 2 (conv)**: 8 unità: le patch $8\times 8$ che le eccitano di più: **tratti, curve, angoli**.
- **Strato 3 (denso)**: 8 unità: le cifre di test che le eccitano di più: **forme intere**.

<!-- SOURCE_VISUAL id="IS21-V17" source="L21-web.pdf" page="30" type="figura" description="Filtri del primo strato, patch che eccitano il secondo e cifre che eccitano il terzo strato di CNN-A" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Misurato su **CNN-A addestrata su MNIST**. Per ogni unità si ordinano gli ingressi per attivazione e si mostrano i primi (*maximally activating inputs*). **Dai bordi alle parti agli oggetti interi**: la stessa gerarchia vista sui volti (bordi → occhi, nasi → volti). **Nessuno ha progettato questi rilevatori.** Guardarli è **un primo passo verso la spiegabilità** (Lezione 2).

#### **2.13. Perché reti profonde?**

- **Plausibilità biologica**: la corteccia visiva elabora le immagini in una **gerarchia di aree**.
- **Efficienza**: alcune funzioni richiedono **esponenzialmente più unità se si tolgono strati** (Håstad, 1986).
- **Le sotto-feature di una rete profonda si possono condividere tra compiti** (la radice del transfer learning).

**Esempio: la parità di $n$ bit** ($\hat{y}=1$ se il numero di 1 è dispari, cioè lo XOR di tutti i bit), $n=4$: **profonda**: blocchi XOR in un albero; **superficiale**: 2 strati, 8 unità, una per ogni pattern dispari.

| | strati | unità |
|---|---|---|
| profonda | $\log_2 n$ | $n-1$ blocchi XOR |
| superficiale | 2 | $2^{n-1}+1$ |
| $n=32$ | 5 contro 2 | 31 blocchi contro $\approx 2{,}1\cdot 10^9$ |

<!-- SOURCE_VISUAL id="IS21-V18" source="L21-web.pdf" page="31" type="schema" description="Albero di blocchi XOR per la parità e rete superficiale con un'unità per ogni pattern dispari" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Håstad**: per circuiti AND/OR con $k$ strati, la parità richiede $\approx e^{n^{1/(k-1)}}$ porte. Con neuroni a soglia il divario è minore, ma **la profondità fa comunque risparmiare unità**. Adattato da © T. Martinez.

> 💡 **L’esempio della parità (nota didattica).** Con una catena di porte XOR a due ingressi bastano $n-1$ porte, disposte in $\log_2 n$ livelli. Con soli **due livelli** (un OR di AND) servono invece $2^{n-1}$ termini, uno per ogni combinazione con un numero dispari di 1 (più l’unità d’uscita): con $n=4$ sono 8, con $n=32$ sono $2^{31}\approx 2{,}1\cdot 10^9$. La profondità permette di **riusare** risultati intermedi: lo XOR di $x_1,x_2$ calcolato una volta serve a tutti i livelli successivi.

---

### **3. Teoria: le reti neurali convoluzionali**


Convoluzione, ReLU, pooling, softmax, e come si imparano i pesi.

#### **3.1. Dai pixel a una decisione**

**Input**: immagine RGB $299\times 299\times 3=268\,203$ numeri → **CNN** (filtri appresi + classificatore: **InceptionV3**, addestrata su **ImageNet**, 1,28 milioni di foto) → **output**: **1000 probabilità** (una per classe), somma = 1.

<!-- SOURCE_VISUAL id="IS21-V19" source="L21-web.pdf" page="33" type="schema" description="Dalla foto del camion alle 1000 probabilità di InceptionV3" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Definizione.** Una **rete neurale convoluzionale (CNN)** è una rete neurale in cui alcuni strati sono **convolutivi**: **ogni unità guarda una piccola patch dell’ingresso**, e **gli stessi pesi vengono riusati in ogni posizione**. Qui: immagine in ingresso, probabilità di classe in uscita («moving van» 0,44, «trailer truck» 0,42).

#### **3.2. Perché non una rete completamente connessa sulle immagini?**

| | **Completamente connessa** | **Convolutiva** |
|---|---|---|
| collegamento | **ogni pixel a ogni neurone** | **ogni filtro vede una patch $3\times 3$ e scorre** |
| ingressi | $n=299\cdot 299\cdot 3=268\,203$ | foto RGB $299\times 299\times 3$, filtro $3\times 3\times 3$ |
| primo strato | 1000 neuroni ⇒ $268\,203\cdot 1000\approx$ **268 milioni di pesi** | **32 filtri · 27 pesi = 864 pesi** (primo strato di InceptionV3), 32 feature map |

<!-- SOURCE_VISUAL id="IS21-V20" source="L21-web.pdf" page="34" type="schema" description="Rete completamente connessa contro strato convolutivo sulla stessa foto" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Stesso input, stesso primo compito** (estrarre feature). **Tre idee fanno la differenza**: **locale** (un pattern è piccolo), **condiviso** (lo stesso rilevatore è utile ovunque), **gerarchico** (pattern piccoli si combinano in pattern più grandi). Le slide successive mostrano le prime due con gli uccelli.

> 💡 **Il rapporto (nota didattica).** $268\,203\,000/864\approx 310\,000$: lo strato convolutivo ha circa 300 000 volte meno pesi. È la risposta alla Lezione 20 (§1.10): la maledizione della dimensionalità per le immagini si affronta con la struttura (località e condivisione), non con più dati.

#### **3.3. Alcuni pattern sono molto più piccoli dell’intera immagine**

Un neurone **non ha bisogno di vedere l’intera immagine per scoprire un becco**: basta una **piccola regione**, e una piccola regione richiede **pochi parametri**. È l’idea della **connettività locale**.

<!-- SOURCE_VISUAL id="IS21-V21" source="L21-web.pdf" page="35" type="figura" description="Un becco rilevato guardando solo una piccola regione dell'immagine dell'uccello" reason="Immagine visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.4. Lo stesso pattern compare in posti diversi**

Due rilevatori che cercano lo **stesso becco in posti diversi** possono usare **gli stessi parametri**. **Idea**: addestrare molti piccoli rilevatori e lasciare che ognuno **si muova sull’immagine** (**condivisione dei pesi**, *weight sharing*).

<!-- SOURCE_VISUAL id="IS21-V22" source="L21-web.pdf" page="36" type="figura" description="Due becchi in posizioni diverse rilevati dallo stesso filtro" reason="Immagine visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.5. Uno strato convolutivo: filtri che scorrono**

> 📌 **Definizione.** Uno **strato convolutivo** ha un certo numero di **piccoli filtri (kernel)**; ogni filtro **scorre sull’ingresso** e, in ogni posizione, calcola un **prodotto scalare** con la patch sottostante.

- **Input**: un’immagine (o le mappe dello strato precedente).
- **Output**: **una feature map per filtro**.
- L’uscita è **alta dove l’ingresso somiglia al filtro**.
- In una CNN **i valori dei filtri sono pesi appresi dall’addestramento**.

<!-- SOURCE_VISUAL id="IS21-V23" source="L21-web.pdf" page="37" type="figura" description="Filtri che scorrono sull'immagine in uno strato convolutivo" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.6. La convoluzione passo per passo (1): un prodotto scalare**

**Immagine $I$ ($6\times 6$)** e **filtro $K$ ($3\times 3$)**:

$$I=\begin{bmatrix}1&0&0&0&0&1\\0&1&0&0&1&0\\0&0&1&1&0&0\\1&0&0&0&1&0\\0&1&0&0&1&0\\0&0&1&0&1&0\end{bmatrix},\qquad K=\begin{bmatrix}1&-1&-1\\-1&1&-1\\-1&-1&1\end{bmatrix}.$$

Si moltiplica la patch rossa (in alto a sinistra) per il filtro **elemento per elemento** e si sommano i 9 prodotti:

$$y_{i,j}=\sum_{u=1}^{3}\sum_{v=1}^{3}I_{i+u-1,\ j+v-1}\,K_{u,v}.$$

- $I$: immagine d’ingresso, $K$: filtro (kernel), $y$: mappa d’uscita.
- $i,j$: posizione dell’uscita; $u,v$: posizione dentro il filtro.
- **Esempio**: i tre 1 sulla diagonale della patch incontrano i $+1$ del filtro; tutti gli altri prodotti sono 0: $1+1+1=3=y_{1,1}$.
- **I 9 valori del filtro sono i pesi da imparare.**

> ⚠️ **Convoluzione o correlazione? (nota didattica).** La formula non ribalta il filtro: in senso matematico è una **cross-correlazione** (Lezione 9). Le librerie di deep learning la chiamano comunque «convoluzione»; poiché i pesi si imparano, ribaltarli o no non cambia ciò che la rete può rappresentare. In MATLAB `conv2` ribalta il filtro, `filter2` no (§9.2).

#### **3.7. La convoluzione passo per passo (2): far scorrere il filtro**

Si sposta la patch **di un pixel a destra** (verde) e si ripete: $0\cdot 1+0\cdot(-1)+\dots=-1$. **Dopo tutte le 16 posizioni si ottiene una mappa $4\times 4$** (passo, *stride*, = 1):

$$y=\begin{bmatrix}3&-1&-3&-1\\-3&1&0&-3\\-3&-3&0&1\\3&-2&-2&-1\end{bmatrix}.$$

Valori calcolati con `scripts/conv_numpy.py` (e MATLAB/Octave, identici).

#### **3.8. Dove si accende il filtro?**

**La mappa vale 3 solo dove la patch contiene la diagonale del filtro**: in alto a sinistra ($y_{1,1}$, arancio) e in basso a sinistra ($y_{4,1}$, verde acqua). **Uno strato convolutivo è un rilevatore di pattern; la mappa dice dove si trova il pattern.**

> 💡 **Verifica di $y_{4,1}$ (nota didattica).** La patch delle righe 4–6 e colonne 1–3 è $\begin{bmatrix}1&0&0\\0&1&0\\0&0&1\end{bmatrix}$, la stessa diagonale di $y_{1,1}$: di nuovo $1+1+1=3$. Il massimo possibile è 3 (tre $+1$ allineati e nessun $-1$ attivo): la convoluzione con pesi $\pm 1$ conta le corrispondenze meno le discordanze.

#### **3.9. Perché l’uscita è più piccola: tre esempi**

| caso | che cosa succede | uscita |
|---|---|---|
| (a) passo $S=1$, nessun padding | la finestra $3\times 3$ sta in $6-3+1=4$ posizioni per lato | $O=4$ |
| (b) passo $S=2$ | la finestra salta 2 pixel: posizioni 1 e 3 ⇒ 2 per lato | $O=2$ |
| (c) padding $P=1$ | una **cornice di zeri** (grigio): $6+2\cdot 1=8$; la dimensione si **conserva** («same») | $O=6$ |

<!-- SOURCE_VISUAL id="IS21-V24" source="L21-web.pdf" page="41" type="schema" description="Tre esempi di dimensione d'uscita: passo 1, passo 2 e padding 1" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Rosso = prima posizione del filtro $3\times 3$, verde = seconda posizione. **L’uscita ha un valore per ogni posizione in cui il filtro sta dentro l’ingresso (con padding)**: contare le posizioni lungo un lato.

#### **3.10. La regola generale: la dimensione d’uscita**

$$O=\left\lfloor\frac{N+2P-k}{S}\right\rfloor+1$$

- $N$: dimensione d’ingresso (pixel per lato); $k$: dimensione del filtro;
- $P$: **padding**, zeri aggiunti attorno al bordo;
- $S$: **passo** (*stride*) del filtro che scorre;
- $O$: dimensione d’uscita;
- $\lfloor\cdot\rfloor$: **parte intera** (arrotondamento per difetto): $\lfloor 1{,}5\rfloor=1$, $\lfloor 2\rfloor=2$. **Un filtro che non sta completamente nell’ultimo passo viene scartato.**

| caso | $N$ | $k$ | $P$ | $S$ | $O$ |
|---|---:|---:|---:|---:|---:|
| giocattolo, passo 1 | 6 | 3 | 0 | 1 | 4 |
| giocattolo, passo 2 | 6 | 3 | 0 | 2 | 2 |
| giocattolo, «same» | 6 | 3 | 1 | 1 | 6 |
| camion (altezza) | 200 | 3 | 0 | 1 | 198 |
| cifra, conv 1 | 28 | 3 | 0 | 1 | 26 |

**Esempio**, giocattolo con passo 2: $O=\lfloor(6+0-3)/2\rfloor+1=\lfloor 1{,}5\rfloor+1=2$. Il passo 2 sull’immagine giocattolo dà **un valore su due** della mappa $4\times 4$: $\begin{bmatrix}3&-3\\-3&0\end{bmatrix}$. Il padding «same» ($P=1$ per $k=3$) **conserva la dimensione**; un passo $S=2$ la **dimezza**.

> 💡 **«Same» in generale (nota didattica).** Con $S=1$ la dimensione si conserva quando $2P=k-1$, cioè $P=(k-1)/2$: $P=1$ per $k=3$, $P=2$ per $k=5$. Per questo i filtri hanno quasi sempre dimensione dispari. AlexNet (§7.1): $O=\lfloor(227-11)/4\rfloor+1=55$.

#### **3.11. Molti filtri, molti canali**

**Ingresso $6\times 6\times 1$**, **2 filtri** → **uscita $4\times 4\times 2$**: **una mappa (canale) per filtro**.

$$K_2=\begin{bmatrix}-1&1&-1\\-1&1&-1\\-1&1&-1\end{bmatrix},\qquad y^{(2)}=\begin{bmatrix}-1&-1&-1&-1\\-1&-1&-2&1\\-1&-1&-2&1\\-1&0&-4&3\end{bmatrix}.$$

<!-- SOURCE_VISUAL id="IS21-V25" source="L21-web.pdf" page="43" type="schema" description="Due filtri applicati alla stessa immagine 6x6 producono due canali 4x4" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Il **filtro 1** (arancio) trova la **diagonale principale**, il **filtro 2** (verde acqua) una **barra verticale**: il suo valore più alto, **3, è in basso a destra**, dove l’immagine ha un tratto verticale. **Con $F$ filtri l’uscita ha $F$ canali**: il primo strato convolutivo della nostra CNN ha **25 filtri ⇒ $26\times 26\times 25$**.

#### **3.12. Immagini a colori: i filtri hanno profondità**

Un filtro **attraversa sempre tutti i canali del suo ingresso** e somma tutto in **un numero per posizione**: **1 filtro → 1 mappa**. Il camion come $H\times W\times 3$: **tre piani R, G, B**; un filtro $3\times 3\times 3$.

$$\#\text{parametri}=\underbrace{F\,(k\cdot k\cdot C)}_{\text{pesi}}+\underbrace{F}_{\text{bias}}$$

- $F$: numero di filtri (= canali d’uscita); $k$: dimensione del filtro; $C$: canali d’ingresso.
- $+F$: ogni filtro ha anche **un bias** $b$, aggiunto dopo la somma dei prodotti: $y=\sum I\cdot K+b$ (il $-\theta$ della Lezione 20). $F$ filtri ⇒ $F$ bias.
- **RGB, $F=2$ filtri $3\times 3$**: ognuno 27 pesi + 1 bias = 28; totale $2\cdot 27+2=56$.
- **Primo strato**: $C=3$ (R, G, B). **Strati più profondi**: ogni feature map è un canale, ma ci sono tante mappe quanti erano i filtri prima: la **conv 2 di CNN-A vede 25 mappe** ⇒ filtri $3\times 3\times 25$, $50\,(3\cdot 3\cdot 25)+50=11\,300$.

<!-- SOURCE_VISUAL id="IS21-V26" source="L21-web.pdf" page="44" type="figura" description="Il camion separato nei tre piani R, G, B e un filtro 3x3x3 che li attraversa" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Il primo strato di InceptionV3 (nota didattica).** $32$ filtri $3\times 3\times 3$: $32\cdot 27=864$ pesi (§3.2), più 32 bias se presenti. Nelle architetture con batch normalization (§4.7) il bias si omette (`use_bias=False`), perché la normalizzazione successiva lo annullerebbe.

#### **3.13. Convoluzione contro strato completamente connesso (primo strato)**

- **Entrambi gli strati possono risolvere gli stessi problemi.**
- Lo strato completamente connesso vede l’immagine come **36 ingressi $x_1..x_{36}$**, ogni unità collegata a tutti.
- **La convoluzione è uno strato completamente connesso speciale**: **la maggior parte dei pesi è 0 e gli altri sono condivisi**.

<!-- SOURCE_VISUAL id="IS21-V27" source="L21-web.pdf" page="45" type="schema" description="L'immagine 6x6 vista come 36 ingressi di uno strato completamente connesso" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.14. Meno parametri: connessioni locali e pesi condivisi**

- L’uscita «3» è **collegata solo a 9 ingressi** (1, 2, 3, 7, 8, 9, 13, 14, 15), **non a tutti i 36**.
- L’uscita «−1» usa **gli stessi 9 pesi** (stessi colori) sugli ingressi **spostati di un pixel**.

<!-- SOURCE_VISUAL id="IS21-V28" source="L21-web.pdf" page="46" type="schema" description="Due uscite della convoluzione collegate a 9 ingressi ciascuna con gli stessi pesi" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Esempio**: 16 uscite di uno strato completamente connesso su 36 ingressi richiedono $16\cdot 36=576$ pesi; la convoluzione ne richiede **9** (un filtro), **riusati 16 volte**.

> 💡 **Equivarianza alla traslazione (nota didattica).** Poiché lo stesso filtro è applicato ovunque, spostare il pattern nell’immagine sposta allo stesso modo la sua risposta nella mappa: la convoluzione è **equivariante** alla traslazione. Il pooling (§3.17) aggiunge un po’ di **invarianza**: piccoli spostamenti non cambiano l’uscita. Uno strato denso dovrebbe imparare lo stesso becco separatamente in ogni posizione.

#### **3.15. ReLU: tenere l’evidenza, scartare il resto**

$$\text{ReLU}(x)=\max(0,x),$$

con $x$ un valore di una feature map. **Esempi**: $\text{ReLU}(3)=3$, $\text{ReLU}(-2)=0$.

La **Rectified Linear Unit** è l’attivazione applicata a ogni valore **dopo ogni convoluzione**. È **economica**; per ingressi positivi la sua **derivata è 1** (**meno gradiente che svanisce**, Lezione 20), per ingressi negativi 0 (**l’unità può diventare inattiva**). Su questa mappa **il 56% dei valori diventa 0**.

<!-- SOURCE_VISUAL id="IS21-V29" source="L21-web.pdf" page="47" type="figura" description="Grafico della ReLU e feature map prima e dopo la ReLU" reason="Grafico e mappe visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **«L’unità può diventare inattiva» (nota didattica).** Se i pesi di un neurone ReLU finiscono in una zona in cui la somma pesata è negativa per **tutti** gli ingressi, la sua uscita e il suo gradiente sono sempre 0 e non impara più: è una «ReLU morta» (Lezione 10, §8). Si mitiga con un learning rate non troppo alto e varianti come la Leaky ReLU ($0{,}01x$ per $x<0$).

#### **3.16. L’architettura di una CNN: il quadro d’insieme**

1. **Input**: un’immagine (il volto).
2. **Convoluzione + max pooling**: **si può ripetere molte volte**.
3. **Flatten**: mappe → **un vettore**.
4. **Rete feed-forward completamente connessa** (Lezione 20).
5. **Output**: la classe (volto, corpo, cane, cavallo…).

<!-- SOURCE_VISUAL id="IS21-V30" source="L21-web.pdf" page="48" type="schema" description="Architettura generale di una CNN: convoluzione e pooling ripetuti, flatten, strati densi, classe" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**CNN-A: la CNN di questa lezione, con le sue forme.**

| Input | Conv $3\times 3$ | Max pool | Conv $3\times 3$ | Max pool | Flatten | Dense ReLU | Softmax |
|---|---|---|---|---|---|---|---|
| $28\times 28\times 1$ | $26\times 26\times 25$ | $13\times 13\times 25$ | $11\times 11\times 50$ | $5\times 5\times 50$ | 1250 | 100 | 10 |
| | ← **estrattore di feature (filtri appresi)** → | | | | ← **classificatore** → | | |

<!-- SOURCE_VISUAL id="IS21-V31" source="L21-web.pdf" page="49" type="schema" description="Schema a blocchi di CNN-A con le forme di ogni strato" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

In questa lezione si usa una **rete piccola e semplificata che somiglia a LeNet (non ad AlexNet)**: la chiamiamo **CNN-A**. **Input**: una cifra grigia $28\times 28$ (784 numeri). **Output**: 10 probabilità (cifre 0–9). Ogni blocco è una **pila di feature map**: altezza = dimensione spaziale, profondità = numero di canali. **Le mappe diventano più piccole e più numerose.** Ritorna nella parte Keras come mappa.

#### **3.17. Perché il pooling: sottocampionare non cambia l’oggetto**

$775\times 525$ pixel: un uccello → tenere 1 pixel su 4 per lato: $194\times 131$, ancora un uccello → di nuovo $\times 2$: $97\times 65$, **ancora un uccello**. Feature map $198\times 298$ → $99\times 149$ → $48\times 73$.

<!-- SOURCE_VISUAL id="IS21-V32" source="L21-web.pdf" page="50" type="figura" description="Un uccello sottocampionato più volte resta riconoscibile, con le feature map ridotte" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- Il pooling **sottocampiona le feature map**: $2\times 2$ dà una compressione **4:1**, $3\times 3$ **9:1**.
- **Conserva la posizione approssimativa, non quella esatta**: **robusto a piccoli spostamenti**.
- **Meno valori ⇒ meno parametri e meno calcolo** negli strati successivi.

#### **3.18. Il max pooling passo per passo**

$$\begin{bmatrix}1&1&2&4\\5&6&7&8\\3&2&1&0\\1&2&3&4\end{bmatrix}\ \xrightarrow{\ \text{max pooling }2\times 2,\ \text{passo }2\ }\ \begin{bmatrix}6&8\\3&4\end{bmatrix}$$

Ingresso: $4\times 4$ (16 valori); uscita: $2\times 2$ (4 valori). Ogni uscita è il **valore più grande di un blocco $2\times 2$**: $\max(1,1,5,6)=6$; $\max(2,4,7,8)=8$; $\max(3,2,1,2)=3$; $\max(1,0,3,4)=4$. **Nessun peso da imparare.** Applicato a **ogni canale separatamente**: $26\times 26\times 25\to 13\times 13\times 25$.

#### **3.19. Dalle feature map alle classi: flatten e denso**

1. **Flatten**: le mappe $5\times 5\times 50$ diventano **un vettore di 1250 numeri**.
2. **Strato denso** (completamente connesso): **100 neuroni** combinano le feature (l’MLP della Lezione 20).
3. **Strato d’uscita**: **un punteggio per classe** (10 cifre), trasformato in probabilità dalla **softmax**.

> 💡 **Variante moderna: il global average pooling.** Sostituisce il flatten: **ogni mappa → la sua media**, $5\times 5\times 50\to 50$ numeri, **molti meno pesi nella testa**. Con 50 valori invece di 1250, il primo strato denso da 100 neuroni passerebbe da 125 100 a 5100 parametri.

#### **3.20. L’ultimo strato tipico: la softmax**

$$p_c=\frac{e^{z_c}}{\sum_{j=1}^{C}e^{z_j}}$$

- $z_c$: **punteggio (logit) della classe $c$**: la somma pesata del neurone d’uscita $c$, un numero reale qualsiasi.
- $C$: numero di classi; $p_c\in(0,1)$, $\sum_cp_c=1$.
- **Esempio** ($C=3$): $z=(2{,}0;\ 1{,}0;\ 0{,}1)$, $e^z=(7{,}39;\ 2{,}72;\ 1{,}11)$, somma $=11{,}21$, $p=(0{,}66;\ 0{,}24;\ 0{,}10)$: «ok» 0,66, «graffio» 0,24, «ammaccatura» 0,10.

<!-- SOURCE_VISUAL id="IS21-V33" source="L21-web.pdf" page="53" type="schema" description="Feature dello strato precedente, tre neuroni d'uscita con i logit e la softmax con le probabilità" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Definizione.** La **softmax** trasforma i $C$ logit dell’ultimo strato in $C$ **punteggi positivi a somma 1**, **mantenendone l’ordine** (qui: ispezione con 3 classi). Spesso letti come probabilità, ma **non necessariamente calibrati**: una rete può essere «sicura» al 99% e sbagliare comunque (Lezione 18, §8.15).

#### **3.21. CNN-A: forme e parametri**

| Strato | Forma d’uscita | Parametri |
|---|---|---:|
| input | $28\times 28\times 1$ | 0 |
| conv $3\times 3$, 25 filtri | $26\times 26\times 25$ | $25(9\cdot 1)+25=250$ |
| max pool $2\times 2$ | $13\times 13\times 25$ | 0 |
| conv $3\times 3$, 50 filtri | $11\times 11\times 50$ | $50(9\cdot 25)+50=11\,300$ |
| max pool $2\times 2$ | $5\times 5\times 50$ | 0 |
| flatten | 1250 | 0 |
| dense 100 + ReLU | 100 | $1250\cdot 100+100=125\,100$ |
| dense 10 + softmax | 10 | $100\cdot 10+10=1\,010$ |
| **totale** | | **137 660** |

**Dove sono i pesi?**

- **I due strati convolutivi: l’8% dei parametri**, eppure **fanno il «vedere»**.
- **Il primo strato denso: il 91%.**
- **Perché da 11 a 5?** $\lfloor 11/2\rfloor=5$: il pooling **scarta l’ultima riga e l’ultima colonna**.

Keras stampa questa tabella con `model.summary()`.

> 💡 **I conti (nota didattica).** Conv: $250+11\,300=11\,550$, cioè $11\,550/137\,660=8{,}4\%$; denso 1: $125\,100/137\,660=90{,}9\%$. È lo stesso squilibrio di AlexNet (§7.2) e il motivo del global average pooling (§3.19): la testa densa è la parte più costosa e più incline all’overfitting.

#### **3.22. Il ciclo di addestramento**

**Batch di immagini + etichette vere** → **passo in avanti** (la CNN) → **probabilità** → **loss** $L=-\log p_{\text{vera}}$ → **backpropagation** (gradienti $\partial L/\partial w$) → **aggiornare tutti i pesi** $w\leftarrow w-\eta\,\partial L/\partial w$ → tutti i batch = 1 epoca; **ripetere per molte epoche** → pesi addestrati.

<!-- SOURCE_VISUAL id="IS21-V34" source="L21-web.pdf" page="55" type="schema" description="Ciclo di addestramento di una CNN con loss, backpropagation e aggiornamento" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- $L$: **loss di cross-entropy**; $p_{\text{vera}}$: probabilità data alla classe corretta. **Esempi**: $p_{\text{vera}}=0{,}66\Rightarrow L=0{,}42$; $p_{\text{vera}}=0{,}10\Rightarrow L=2{,}30$.
- $w$: qualsiasi peso (anche **i 9 numeri di ogni filtro**); $\eta$: learning rate (per esempio $10^{-3}$). Richiamo dalla Lezione 20: **stessa backpropagation**; ottimizzatori come **Adam** adattano $\eta$.

> 💡 **Come si aggiorna un filtro (nota didattica).** Lo stesso peso $K_{u,v}$ è usato in tutte le posizioni, quindi il suo gradiente è la **somma** dei contributi di tutte le posizioni (appendice, §9.5). La backpropagation attraverso il max pooling manda il gradiente solo al valore che era il massimo; attraverso la ReLU, solo ai valori positivi.

#### **3.23. Pratiche moderne destinate a restare**

| Pratica | Perché |
|---|---|
| **filtri $3\times 3$, padding «same»** | filtri piccoli impilati **vedono aree grandi con pochi pesi** |
| **batch normalization** | normalizza ogni canale durante l’addestramento: **più veloce, più stabile** |
| **global average pooling** | sostituisce flatten + grande strato denso: **molti meno pesi** |
| **data augmentation** | ribaltamenti, spostamenti, rotazioni casuali: **più dati gratis, generalizzazione migliore** |
| **dropout** | **spegne a caso dei neuroni** durante l’addestramento: meno overfitting |
| **connessioni residue** | permettono di addestrare reti **molto profonde (50–150 strati)** |
| **transfer learning** | partire da una rete **pre-addestrata su milioni di immagini** |

**Ricette vecchie** come le attivazioni sigmoide negli strati profondi, i filtri molto grandi ($11\times 11$, AlexNet) o le enormi teste completamente connesse **non si usano più nei progetti nuovi**.

> 💡 **Due filtri $3\times 3$ valgono un $5\times 5$ (nota didattica).** Due convoluzioni $3\times 3$ in cascata «vedono» una regione $5\times 5$ dell’ingresso (il campo ricettivo cresce di 2 a ogni strato), con $2\cdot 9=18$ pesi per canale invece di 25, e con una ReLU in mezzo in più. Tre $3\times 3$ valgono un $7\times 7$ (27 contro 49 pesi): è l’idea di VGG (§7.3).

> 💡 **Il dropout in pratica (nota didattica).** Con tasso di dropout $r$, a ogni passo di addestramento ogni unità viene azzerata con probabilità $r$ (per esempio 0,5), e le altre vengono scalate di $1/(1-r)$ perché la somma attesa resti la stessa. In fase di uso il dropout è **spento**. Ogni neurone impara così a funzionare senza poter contare su specifici compagni, e la rete somiglia a una media di tante sotto-reti.

---

### **4. Strumenti: la stessa CNN in Keras**


Codice a sinistra, architettura a destra, un blocco alla volta.

#### **4.1. Passo 1: l’ingresso**

```python
import keras
from keras import layers

# 70 000 cifre grigie, 28x28, valori 0..255
data = keras.datasets.mnist.load_data()
(x_tr, y_tr), (x_te, y_te) = data
x_tr = x_tr[..., None] / 255.0     # (60000,28,28,1)
x_te = x_te[..., None] / 255.0     # valori 0..1

model = keras.Sequential([
    keras.Input(shape=(28, 28, 1)),   # H, W, canali
])
```

<!-- SOURCE_VISUAL id="IS21-V35" source="L21-web.pdf" page="58" type="figura" description="Le prime 6 immagini di test MNIST con le loro etichette 7, 2, 1, 0, 4, 1" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Input**: un batch di immagini, forma `(batch, 28, 28, 1)`. **Keras mette il canale per ultimo**, come in un file d’immagine. **Scalare a 0..1 aiuta l’addestramento.** MNIST si scarica automaticamente (11 MB). Le prime 6 immagini di test `x_te[:6]` hanno etichette $y=7,2,1,0,4,1$.

> 💡 **`[..., None]` (nota didattica).** I dati MNIST arrivano come $60\,000\times 28\times 28$; lo strato convolutivo vuole anche l’asse dei canali. `x[..., None]` aggiunge un asse alla fine ($28\times 28\to 28\times 28\times 1$), come `np.newaxis` nella Lezione 17 (§7.8). Dividere per 255 porta i livelli di grigio in $[0,1]$: la normalizzazione min-max della Lezione 10.

#### **4.2. Passo 2: il primo blocco convolutivo**

```python
model = keras.Sequential([
    keras.Input(shape=(28, 28, 1)),
    # 25 filtri 3x3 appresi + ReLU
    layers.Conv2D(25, 3, activation="relu"),
    # tenere il massimo di ogni blocco 2x2
    layers.MaxPooling2D(2),
])
```

$28\to 26$ (filtro $3\times 3$, nessun padding, vedi la formula di $O$) $\to 13$ (pooling). **25 filtri ⇒ 25 canali.** Parametri per filtro: 9 (+1 bias); **250 in totale**.

#### **4.3. Passo 3: il secondo blocco convolutivo**

```python
model = keras.Sequential([
    keras.Input(shape=(28, 28, 1)),
    layers.Conv2D(25, 3, activation="relu"),
    layers.MaxPooling2D(2),
    # 50 filtri, ciascuno 3x3x25: combinano
    # le 25 mappe del blocco precedente
    layers.Conv2D(50, 3, activation="relu"),
    layers.MaxPooling2D(2),
])
```

$13\to 11\to 5$. Parametri per filtro: $3\cdot 3\cdot 25=225$. **Keras deduce che arrivano 25 canali**: si dà solo il numero di filtri. **Qui si rilevano parti delle cifre** (anelli, tratti).

#### **4.4. Passo 4: la testa classificatrice**

```python
model = keras.Sequential([
    keras.Input(shape=(28, 28, 1)),
    layers.Conv2D(25, 3, activation="relu"),
    layers.MaxPooling2D(2),
    layers.Conv2D(50, 3, activation="relu"),
    layers.MaxPooling2D(2),
    layers.Flatten(),                           # -> 1250
    layers.Dense(100, activation="relu"),
    layers.Dense(10, activation="softmax"),
])
model.summary()                                 # stampa la tabella: 137,660
```

**Output**: 10 probabilità per immagine. **La testa è l’MLP della prima parte della lezione.** **8 righe descrivono l’intera architettura.**

#### **4.5. Passo 5: addestrare, controllare, valutare**

```python
# etichette intere 0..9 -> cross-entropy "sparse"
model.compile(optimizer="adam",
              loss="sparse_categorical_crossentropy",
              metrics=["accuracy"])
# tutto il ciclo di addestramento è qui dentro
hist = model.fit(x_tr, y_tr, epochs=5,
                 batch_size=128, validation_split=0.1)
loss, acc = model.evaluate(x_te, y_te)
p = model.predict(x_te[:6])          # (6, 10)
print(p.argmax(axis=1))              # 6 cifre
```

<!-- SOURCE_VISUAL id="IS21-V36" source="L21-web.pdf" page="62" type="grafico" description="Curve di loss e accuratezza di training e validazione di CNN-A su 5 epoche" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Esecuzione reale** (Keras 3, CPU, **circa 4 minuti**): **54 000 immagini per il training, 6000 per la validazione, 10 000 per il test**. **Accuratezza di test di CNN-A: 98,65%.** La loss di training continua a scendere mentre quella di validazione **si appiattisce**: più epoche aggiungerebbero poco.

> 💡 **«Sparse» (nota didattica).** `sparse_categorical_crossentropy` accetta etichette intere (7, 2, 1…); `categorical_crossentropy` vorrebbe le etichette in one-hot (Lezione 16, §5). Sono la stessa loss $-\ln p_{\text{vera}}$. Con `batch_size=128` e 54 000 immagini un’epoca fa $\lceil 54\,000/128\rceil=422$ aggiornamenti, 2110 in 5 epoche.

#### **4.6. Dove sbaglia la rete?**

- **Matrice di confusione**: riga = cifra vera, colonna = cifra prevista.
- La **diagonale** conta le risposte corrette (**9865 su 10 000**); le celle rosse mostrano quali classi vengono confuse.
- **Errore più frequente**: un **5 vero letto come 3 (20 volte)**.

| Cifra vera | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| corrette (diagonale) | 976 | 1133 | 1015 | 1006 | 975 | 863 | 947 | 1022 | 967 | 961 |

(Diagonale trascritta dalla matrice; le celle fuori diagonale sono nella figura.)

<!-- SOURCE_VISUAL id="IS21-V37" source="L21-web.pdf" page="63" type="grafico" description="Matrice di confusione 10x10 di CNN-A sul test MNIST e le sei cifre sbagliate con il punteggio softmax più alto" reason="Matrice e immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Errori con il punteggio softmax più alto**: previsto (punteggio), cifra vera: 3 (1,00) vero 5; 0 (1,00) vero 5; 3 (1,00) vero 5; 8 (1,00) vero 5; 1 (1,00) vero 6; 3 (0,99) vero 5. **Nell’industria confondere «difetto» con «ok» costa di solito molto più del contrario.**

> 💡 **Leggere la diagonale (nota didattica).** La somma è $976+1133+1015+1006+975+863+947+1022+967+961=9865$. Il 5 è la classe più debole: 863 corrette su 892 cinque del test MNIST (96,7%), e i suoi errori sono sicuri di sé (confidenza 1,00): di nuovo la softmax non calibrata (§3.20). Per un sistema di ispezione la matrice si legge per costo: la cella «difetto previsto ok» va resa piccola anche a prezzo di più falsi allarmi (Lezione 16, §2).

#### **4.7. Che cosa ha imparato il primo strato?**

I filtri mostrati sono i **pesi dello strato evidenziato**: la **prima convoluzione di CNN-A** (Conv $3\times 3$, 25 filtri). **I 25 filtri appresi** (arancio +, blu −): **diversi mostrano un’orientazione: rilevatori di bordi**; altri rispondono a **tratti chiari**. **Meno ordinati di quelli fatti a mano, ma funzionano per questi dati.** Si leggono con `model.layers[0].get_weights()[0]`, forma `(3, 3, 1, 25)`. Per confronto, i filtri di bordo fatti a mano (Sobel verticale e orizzontale) del §2.9.

<!-- SOURCE_VISUAL id="IS21-V38" source="L21-web.pdf" page="64" type="figura" description="I 25 filtri 3x3 appresi dal primo strato di CNN-A accanto ai filtri di bordo fatti a mano" reason="Immagini dei filtri visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.8. CNN-B: una versione moderna di CNN-A**

```python
def block(f):                                        # conv -> batch norm -> ReLU
    return [layers.Conv2D(f, 3, padding="same", use_bias=False),
            layers.BatchNormalization(), layers.ReLU()]

model = keras.Sequential([
    keras.Input(shape=(28, 28, 1)),
    layers.RandomRotation(0.03),                     # augmentation,
    layers.RandomTranslation(0.05, 0.05),            # solo in addestramento
    *block(32), layers.MaxPooling2D(2),              # 28 -> 14
    *block(64), layers.MaxPooling2D(2),              # 14 -> 7
    *block(96),
    layers.GlobalAveragePooling2D(),                 # 96 mappe -> 96
    layers.Dropout(0.3),
    layers.Dense(10, activation="softmax"),
])
```

**Stessi ingresso e uscita**, buone pratiche attuali: padding «same», **batch norm**; **augmentation dentro il modello**; **global average pooling + dropout**.

| dati, epoche | CNN-A | CNN-B |
|---|---:|---:|
| parametri | 137 660 | **75 754** |
| MNIST, 5 epoche | **98,65%** | 95,79% |
| cifre, 40 epoche | 97,3% | **98,9%** |

**CNN-B impara più lentamente** (augmentation, dropout); **con 40 epoche vince**.

> 💡 **Perché meno parametri e più lenta (nota didattica).** Il global average pooling elimina la testa da 125 100 parametri: tutto il resto sono filtri. L’augmentation e il dropout rendono il compito di training più difficile a ogni epoca (immagini ruotate e spostate, neuroni spenti): il modello generalizza meglio ma ha bisogno di più epoche. Confrontare due modelli a parità di epoche può quindi far sembrare peggiore quello più robusto: è il «guardare sempre le curve» del §1.11. La riga «cifre, 40 epoche» riporta un esperimento su dati di cifre diverso dalla prima riga, come indicato nella slide.

#### **4.9. CNN Explainer: provatela!**

**CNN Explainer** è una **visualizzazione interattiva di una piccola CNN che gira nel browser**: si fa clic su qualsiasi mappa per vedere la convoluzione, la ReLU e il pooling che l’hanno prodotta. https://poloclub.github.io/cnn-explainer/

<!-- SOURCE_VISUAL id="IS21-V39" source="L21-web.pdf" page="66" type="schermata" description="Interfaccia di CNN Explainer" reason="Schermata visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.10. Tocca a voi: il laboratorio di codice**

Tre notebook Colab, pubblicati sul sito del corso. Ciascuno: **input → modello → output**, con esercizi.

| Notebook | Input → output | Esercizio |
|---|---|---|
| 1. Perceptron e MLP | AND, XOR, 5 misure della pompa → classe o probabilità | far fallire lo XOR con 1 neurone; cambiare le unità nascoste |
| 2. CNN sulle cifre scritte a mano | cifra $28\times 28$ → 10 probabilità | contare i parametri; Fashion-MNIST; CNN moderna |
| 3. CNN pre-addestrata | la propria foto → le 5 classi ImageNet più probabili | un’immagine casuale; transfer learning su 2 classi |

I dataset si scaricano in una riga (`keras.datasets.mnist`, `fashion_mnist`); **tutto gira sulla CPU o GPU gratuita di Colab**.

---

### **5. Teoria: CNN famose e transfer learning**


Non partire da zero: riusare ciò che milioni di immagini hanno insegnato.

#### **5.1. AlexNet (2012): la rete che ha cominciato tutto**

- **Krizhevsky, Sutskever, Hinton (2012).**
- **Input**: immagine RGB $227\times 227\times 3$ (ritagli di $256\times 256$); **output**: 1000 probabilità.
- **8 strati**: 5 convolutivi + 3 completamente connessi.
- **ReLU, dropout, data augmentation, addestrata su 2 GPU.**
- **Errore top-5 su ImageNet: 15,3%**, contro il **26,2%** del miglior metodo non profondo.
- **Parametri: 62,4 M** (62 378 344).

| Strato | Filtri / unità | Uscita | Parametri |
|---|---|---|---:|
| conv1 | 96 di $11\times 11$, passo 4 | $55\times 55\times 96$ | 34 944 |
| conv2 | 256 di $5\times 5$ | $27\times 27\times 256$ | 614 656 |
| conv3 | 384 di $3\times 3$ | $13\times 13\times 384$ | 885 120 |
| conv4 | 384 di $3\times 3$ | $13\times 13\times 384$ | 1 327 488 |
| conv5 | 256 di $3\times 3$ | $13\times 13\times 256$ | 884 992 |
| fc6 | 4096 | 4096 | 37 752 832 |
| fc7 | 4096 | 4096 | 16 781 312 |
| fc8 | 1000 + softmax | 1000 | 4 097 000 |
| **totale** | | | **62 378 344** |

AlexNet: strati e parametri (max pooling dopo conv1, conv2, conv5).

<!-- SOURCE_VISUAL id="IS21-V40" source="L21-web.pdf" page="69" type="schema" description="Architettura di AlexNet" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Top-5 (nota didattica).** L’errore top-5 conta come corretta una previsione se la classe vera è tra le 5 più probabili: con 1000 classi molto simili (decine di razze di cane) è una misura più indulgente e più informativa della top-1. Da 26,2% a 15,3% è una riduzione relativa del 42% in un solo anno: è il risultato che ha convinto la comunità.

#### **5.2. AlexNet in Keras: `model.summary()`**

```text
Model: "alexnet"
Layer (type)            Output Shape            Param #
=======================================================
conv1 (Conv2D)          (None, 55, 55, 96)      34,944
pool1 (MaxPooling2D)    (None, 27, 27, 96)      0
conv2 (Conv2D)          (None, 27, 27, 256)     614,656
pool2 (MaxPooling2D)    (None, 13, 13, 256)     0
conv3 (Conv2D)          (None, 13, 13, 384)     885,120
conv4 (Conv2D)          (None, 13, 13, 384)     1,327,488
conv5 (Conv2D)          (None, 13, 13, 256)     884,992
pool5 (MaxPooling2D)    (None, 6, 6, 256)       0
flatten (Flatten)       (None, 9216)            0
fc6 (Dense)             (None, 4096)            37,752,832
dropout (Dropout)       (None, 4096)            0
fc7 (Dense)             (None, 4096)            16,781,312
dropout_1 (Dropout)     (None, 4096)            0
fc8 (Dense)             (None, 1000)            4,097,000
=======================================================
Total params: 62,378,344 (237.95 MB)
```

- **Stesso totale della tabella precedente**: 62 378 344.
- **conv1**: $96\,(11\cdot 11\cdot 3)+96=34\,944$.
- **fc6**: $9216\cdot 4096+4096=37\,752\,832$, **il 60% di tutti i pesi in un solo strato denso**.
- **5 strati convolutivi: 3,7 M (6%).**
- `None` = la dimensione del batch, libera.

Versione a una sola torre (senza convoluzioni raggruppate), input $227\times 227\times 3$. Codice: `scripts/alexnet_summary.py`.

> 💡 **I conti (nota didattica).** $9216=6\cdot 6\cdot 256$ (l’uscita di pool5 appiattita). $62\,378\,344\cdot 4$ byte $=249{,}5$ milioni di byte $=237{,}9$ MiB: i «237,95 MB» di Keras sono mebibyte. Conv: $34\,944+614\,656+885\,120+1\,327\,488+884\,992=3\,747\,200$, il 6%. La versione originale del 2012 divideva conv2, conv4 e conv5 su due GPU (convoluzioni raggruppate), con meno parametri.

#### **5.3. LeNet-5 e VGG16: piccola e profonda**

- **LeNet-5** (LeCun, 1998): **5 strati** (3 conv + 2 completamente connessi), cifre grigie $32\times 32$ → 10 classi; **leggeva gli assegni bancari**. **CNN-A ha lo stesso spirito.** Parametri: **circa 60 mila**.
- **VGG16** (Simonyan e Zisserman, Oxford, 2014): **16 strati** (13 conv + 3 completamente connessi), **solo filtri $3\times 3$**, $224\times 224\times 3$ → 1000 classi; **92,7% di accuratezza top-5 su ImageNet**. Parametri: **138 M** (138 357 544).

<!-- SOURCE_VISUAL id="IS21-V41" source="L21-web.pdf" page="71" type="schema" description="Architetture di LeNet-5 e VGG16" reason="Schemi visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Adattato da © T. Martinez e dagli articoli originali.

#### **5.4. Famiglie di CNN usate nelle applicazioni**

| Famiglia | Idea chiave | Parametri | Uso tipico |
|---|---|---:|---|
| **GoogLeNet** (2014) | filtri paralleli di diverse dimensioni, 22 strati | 7 M | classificazione, Places365 |
| **Inception-v3** (2015) | la stessa, 48 strati | 23,9 M | classificazione (il nostro camion) |
| **ResNet-50** (2015) | **connessioni residue** | 25,6 M | **backbone robusto di default** |
| **MobileNet-V2** (2018) | convoluzioni leggere «depthwise» | 3,5 M | **telefoni, telecamere, dispositivi edge** |
| **EfficientNet-B0** (2019) | profondità/larghezza/risoluzione bilanciate | 5,3 M | accuratezza per calcolo |
| **ConvNeXt-T** (2022) | CNN riprogettata con ricette moderne | 28,6 M | backbone moderno forte |
| **famiglia YOLO** | rilevamento in un passo | 3–70 M | box + classi in tempo reale |

**Scegliere una rete pre-addestrata per dimensione** (memoria, velocità) **e per il dataset di addestramento** (ImageNet: 1000 oggetti; **Places365**: 365 categorie di luoghi come campo, parco, pista). I **vision transformer** competono con le CNN su grandi dati e sono trattati in altre lezioni del corso (Lezione 22); **LeNet, AlexNet e VGG sono soprattutto di interesse storico e didattico**.

> 💡 **«Depthwise» (nota didattica).** Una convoluzione normale $3\times 3$ da $C$ a $F$ canali ha $9CF$ pesi. MobileNet la divide in una convoluzione *depthwise* (un filtro $3\times 3$ per canale, $9C$ pesi) seguita da una *pointwise* $1\times 1$ ($CF$ pesi): con $C=F=256$ si passa da 589 824 a 67 840 pesi, circa 9 volte meno. È il motivo per cui gira su un telefono (Lezione 23, hardware).

#### **5.5. Le connessioni residue: l’idea dietro le CNN molto profonde**

$$y=\text{ReLU}\big(F(x)+x\big)$$

- $x$: mappe d’ingresso del blocco; $F(x)$: ciò che calcolano i due strati conv (conv $3\times 3$ + BN + ReLU, conv $3\times 3$ + BN); $y$: uscita.
- **Il blocco impara solo una correzione $F(x)$ al suo ingresso.**
- **Se un blocco non è utile, $F(x)\approx 0$ e il segnale passa invariato.**
- **I gradienti scorrono attraverso il percorso di scorciatoia** (*skip*): si possono addestrare reti con **50–150 strati** (ResNet).

**Esempio**: $x=2$, $F(x)=0{,}3\Rightarrow y=\text{ReLU}(2{,}3)=2{,}3$.

<!-- SOURCE_VISUAL id="IS21-V42" source="L21-web.pdf" page="73" type="schema" description="Blocco residuo con due convoluzioni e la scorciatoia che somma l'ingresso" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché il gradiente non svanisce (nota didattica).** Derivando $y=F(x)+x$ rispetto a $x$ si ottiene $\frac{\partial y}{\partial x}=\frac{\partial F}{\partial x}+1$: anche se $\partial F/\partial x$ è piccolo, il «+1» della scorciatoia lascia passare il gradiente. Attraverso $L$ blocchi il prodotto delle derivate non scende come $0{,}25^L$ (Lezione 20, §6.14), perché ogni fattore è vicino a 1.

#### **5.6. Il quadro d’insieme: riusare un backbone addestrato**

**Backbone**: la parte convolutiva (conv + ReLU + pooling, ripetuti: bordi → tessiture → parti), **pre-addestrata su ImageNet** (1,28 M immagini) → flatten / pool → feature → **testa**: l’ultima decisione (Dense + softmax → 1000 probabilità).

<!-- SOURCE_VISUAL id="IS21-V43" source="L21-web.pdf" page="74" type="schema" description="Backbone convolutivo pre-addestrato e testa di decisione" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Definizione.** Il **transfer learning** **riusa una rete addestrata su un grande compito sorgente** (per esempio ImageNet) come **punto di partenza per un nuovo compito obiettivo**; di solito **solo gli ultimi strati vengono addestrati sui dati nuovi**.

- **Uso così com’è**: backbone + testa originale classificano nelle **1000 classi ImageNet** (camion, tazza, cane…).
- **Trasferimento**: si **tiene il backbone**, si **sostituisce la testa** con una per le proprie classi (ok / difetto…) e la si addestra con **qualche centinaio di immagini**.

#### **5.7. Una CNN come estrattore di feature: VGG-Face + PCA**

**Due immagini di volti** → **backbone VGG-Face, senza testa** (13 conv $3\times 3$ in 5 blocchi, poi FC; addestrato su **2,6 M immagini di volti**) → embedding $z\in\mathbb{R}^{4096}$ → **PCA** $W^\top(z-\bar{z})$ → $u\in\mathbb{R}^k$ (per esempio $k=128$) → **distanza**: stessa persona se $\text{dist}(u_1,u_2)<\tau$? → sì / no.

<!-- SOURCE_VISUAL id="IS21-V44" source="L21-web.pdf" page="75" type="schema" description="Verifica di volti con backbone VGG-Face, PCA e distanza con soglia" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Input**: due immagini di volti. **Output**: stessa persona sì/no. **La CNN senza la testa dà un vettore di feature per ogni volto (un embedding)**; la PCA lo riduce; **una distanza sotto la soglia $\tau$ significa «stessa persona»**. Richiamo dalla Lezione 18: PCA ed embedding; dalla Lezione 19: distanza e soglia $\tau$. **Lo stesso schema funziona per i prodotti**: embedding + distanza per trovare **pezzi simili o anomalie**. Volti ritagliati dalla figura dell’articolo VGG-Face. https://www.robots.ox.ac.uk/~vgg/software/vgg_face/

#### **5.8. Il transfer learning in tre mosse**

**Backbone congelato**: pesi **non aggiornati** (`include_top=False`) → feature → **nuova testa addestrata** → $P(\text{ok})$, $P(\text{difetto})$.

1. **Caricare** il backbone **senza la sua testa ImageNet**.
2. **Congelare** i suoi pesi: **restano come imparati su ImageNet**.
3. **Addestrare la nuova testa** sulle proprie classi; opzionalmente fare poi il **fine-tuning** degli ultimi strati del backbone **con un learning rate piccolo**.

<!-- SOURCE_VISUAL id="IS21-V45" source="L21-web.pdf" page="76" type="schema" description="Backbone congelato e nuova testa addestrata per ok e difetto" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Caso industriale tipico**: **ispezione visiva con 2 classi** (ok / difetto) e **200–500 foto etichettate per classe**. **Addestrare da zero farebbe overfitting**; il transfer learning funziona **in pochi minuti** su una GPU da portatile o su Colab.

> 💡 **Perché il learning rate piccolo nel fine-tuning (nota didattica).** I pesi del backbone sono già buoni: un passo grande li sposterebbe lontano dalla soluzione di ImageNet prima che la nuova testa abbia imparato, distruggendo le feature (oblio catastrofico, Lezione 22). Si addestra prima la sola testa con il backbone congelato, poi si «scongelano» gli ultimi blocchi con $\eta$ 10–100 volte più piccolo.

#### **5.9. Il transfer learning in Keras 3**

```python
# le proprie foto in data_dir/ok/*.jpg, data_dir/defect/*.jpg
train = keras.utils.image_dataset_from_directory(
    "data_dir", image_size=(224, 224), validation_split=0.2,
    subset="training", seed=1)
# 1. caricare il backbone senza la testa -> 1280 feature
base = keras.applications.MobileNetV2(include_top=False,
    weights="imagenet", input_shape=(224, 224, 3), pooling="avg")
base.trainable = False                                   # 2. congelarlo

inputs = keras.Input((224, 224, 3))
x = layers.RandomFlip("horizontal")(inputs)              # augmentation
x = keras.applications.mobilenet_v2.preprocess_input(x)
x = base(x, training=False)
x = layers.Dropout(0.2)(x)
# 3. nuova testa: un'uscita per ciascuna delle proprie classi
outputs = layers.Dense(2, activation="softmax")(x)
model = keras.Model(inputs, outputs)
model.compile("adam", "sparse_categorical_crossentropy", metrics=["accuracy"])
model.fit(train, epochs=5)
```

Immagine $224\times 224\times 3$ → augmentation + pre-elaborazione → **backbone MobileNetV2** (**2,3 M pesi, congelati**) → 1280 → dropout → **Dense(2) + softmax** (**2562 pesi addestrati**) → $P(\text{ok})$, $P(\text{difetto})$.

> 💡 **Tre dettagli (nota didattica).** 1) $2562=1280\cdot 2+2$: si addestra lo 0,1% dei parametri. 2) `training=False` tiene il backbone in modalità di inferenza anche durante l’addestramento: le statistiche della batch normalization restano quelle di ImageNet, invece di essere ricalcolate su batch piccoli. 3) La struttura delle cartelle (`ok/`, `defect/`) dà le etichette: `image_dataset_from_directory` le assegna in ordine alfabetico (defect = 0, ok = 1). Il dataset di validazione si crea con `subset="validation"` e lo stesso `seed`.

#### **5.10. Oltre la classificazione: il rilevamento con YOLO**

> 📌 **Definizione.** **YOLO** (*You Only Look Once*) è una famiglia di **rilevatori in tempo reale**: in **un solo passo in avanti** predicono **quali oggetti sono presenti e dove**, come **box con una classe e un punteggio**.

**Immagine** $640\times 640\times 3$ → **backbone** (feature, CNN) → **neck** (fonde mappe di 3 dimensioni: oggetti piccoli, medi, grandi) → **head** (per ogni cella: box $(x,y,w,h)$, punteggio, classe) → **tenere i box migliori (NMS)** → uscita: box con classe (bicicletta, cane, camion).

<!-- SOURCE_VISUAL id="IS21-V46" source="L21-web.pdf" page="78" type="schema" description="Struttura di YOLO con backbone, neck, head e NMS e l'immagine con i box di bicicletta, cane e camion" reason="Schema e immagine visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Input**: un’immagine. **Output**: un elenco di box, ognuno con **posizione** $(x,y)$, **dimensione** $(w,h)$, **classe e punteggio**. La testa predice qualche box candidato **in ogni cella della griglia di feature**; la **NMS** (*non-maximum suppression*) **rimuove i duplicati che si sovrappongono**. **Usi**: ispezione della qualità, sicurezza (caschi, persone vicino alle macchine), conteggio delle merci, droni, dispositivi edge. **Un passo in avanti ⇒ tempo reale.**

> 💡 **Classificazione e rilevamento (nota didattica).** Un classificatore come GoogLeNet risponde «in questa immagine c’è un camion». Un rilevatore come YOLO risponde «c’è un camion in questo rettangolo, una persona in quest’altro e un cane qui». La qualità di un box si misura di solito con l’**intersezione su unione** tra box predetto $B_p$ e box vero $B_g$:
> $$\text{IoU}=\frac{|B_p\cap B_g|}{|B_p\cup B_g|}\in[0,1],$$
> e un rilevamento conta come corretto, per esempio, se $\text{IoU}\ge 0{,}5$. La NMS tiene il box con il punteggio più alto ed elimina quelli che lo sovrappongono con IoU sopra una soglia. Il tema è sviluppato nella Lezione 23.

#### **5.11. Una rete economica può dire molto di voi**

**Galleria dell’utente A** (foto ridimensionate a $224\times 224$) → **GoogLeNet pre-addestrata su Places365** (365 classi di luoghi, 7 M parametri) → «scogliera, montagna, nevaio…» → **contare le classi della galleria** → profilo: «**alpinista**». **Galleria dell’utente B** → «centro commerciale, negozio di abbigliamento…» → profilo: «**amante dello shopping**».

<!-- SOURCE_VISUAL id="IS21-V47" source="L21-web.pdf" page="79" type="schema" description="Due gallerie di foto personali classificate per luogo e trasformate in due profili" reason="Schema e foto visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Input**: le foto di una galleria personale. **Modello**: una piccola CNN pre-addestrata su Places365, usata così com’è. **Output**: **una classe di luogo per foto ⇒ abitudini e interessi del proprietario**. La rete è **piccola ed economica** (millisecondi per foto su una CPU): **profilare migliaia di utenti costa poco**. **Pensarci prima di condividere dati.**

> ⚠️ **Profilazione e GDPR (nota didattica).** Dedurre abitudini e interessi dalle foto personali è una **profilazione** di dati personali: per il GDPR richiede una base giuridica, trasparenza verso l’utente e, per una vendita a terzi, di norma il **consenso**. La facilità tecnica dell’operazione è proprio ciò che rende necessarie le regole viste nelle Lezioni 2 e 15.

#### **5.12. Lo stesso compito, due approcci**

- **(A) Transfer learning: riaddestrare la testa** (questa lezione): foto → backbone CNN (pre-addestrato) → **nuova testa** Dense + softmax → le proprie classi: montagna, negozio, spiaggia…
- **(B) Embedding + distanza** (Lezioni 18, 19): foto → backbone CNN (pre-addestrato) → embedding $z$ → **k-NN / k-means sui vettori** → foto simili, gruppi, foto insolite.

<!-- SOURCE_VISUAL id="IS21-V48" source="L21-web.pdf" page="80" type="schema" description="Due pipeline sullo stesso backbone: nuova testa addestrata o embedding con k-NN e k-means" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

| | **(A) testa riaddestrata** | **(B) embedding + distanza** |
|---|---|---|
| richiede | **foto etichettate per ogni nuova classe** | **nessuna etichetta** (cluster) o **pochi esempi** (k-NN) |
| uscita | una classe tra quelle addestrate | vicini, gruppi, distanza dal «normale» |
| punto di forza | **personalizzabile**: esattamente le proprie classi | **flessibile**: nuove classi senza riaddestrare |

**Entrambi sono validi; la scelta dipende dalle etichette disponibili e da che cosa va deciso.**

#### **5.13. Chi progetta la CNN? Progettista, AutoML, agenti**

- **I pesi si imparano con la backpropagation**; **la struttura** (strati, dimensioni dei filtri, pooling) è di solito **progettata a mano per tentativi**.
- **Regola pratica**: **diminuire la dimensione delle mappe e aumentarne il numero** strato dopo strato.
- **Oggi anche**: architetture pre-addestrate, ottimizzazione degli iperparametri, **AutoML**, ***neural architecture search*** (Lezione 5).
- **Agenti** (prospettiva, richiamo dalla Lezione 15): **AutoResearch** (A. Karpathy), un agente AI che **modifica il codice, esegue esperimenti, valuta la metrica di validazione e tiene solo i miglioramenti**.

<!-- SOURCE_VISUAL id="IS21-V49" source="L21-web.pdf" page="81" type="figura" description="Progettazione delle CNN per tentativi, AutoML e agenti" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Adattato da © T. Martinez.

> ⚠️ **Il rischio di progettare sulla validazione (nota didattica).** Un agente che prova migliaia di modifiche e tiene quelle che migliorano la metrica di validazione fa la stessa cosa di un wrapper (Lezione 18, §1.2.2): con abbastanza tentativi, alcune modifiche migliorano la validazione per caso. Il risultato finale va misurato su un test set che l’agente non ha mai visto (Lezione 14, §4.4).

---

### **6. Caso d’uso: classificare una foto con una CNN pre-addestrata**


InceptionV3 in Python (Colab) e GoogLeNet in MATLAB.

#### **6.1. La pipeline: le forme a ogni passo**

**JPEG** $2048\times 1365\times 3$ → **ridimensionare** $299\times 299\times 3$ → **aggiungere l’asse del batch** $1\times 299\times 299\times 3$ → **pre-elaborare** (0..255 → −1..1) → **InceptionV3** → $1\times 1000$ → **decodificare** (i nomi delle prime 5) → «**moving van**» 0,44.

<!-- SOURCE_VISUAL id="IS21-V50" source="L21-web.pdf" page="83" type="schema" description="Pipeline dal JPEG del camion alle 5 classi di InceptionV3 con le forme a ogni passo" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Inception-v3: **48 strati, 23,9 M parametri**, input $299\times 299\times 3$.

> ⚠️ **La maggior parte degli errori in pratica non sta nella rete ma nell’«idraulica»**: dimensione d’ingresso sbagliata, **asse del batch mancante**, o una **pre-elaborazione diversa da quella usata in addestramento**. **Ogni modello pre-addestrato ha la sua `preprocess_input`.**

#### **6.2. Il codice in Keras 3 (Colab)**

```python
import keras
from keras.applications import inception_v3 as iv3

# 24 M parametri, scaricati una volta (~92 MB)
model = iv3.InceptionV3(weights="imagenet")

# leggere e ridimensionare alla dimensione d'ingresso della rete
img = keras.utils.load_img("truck.jpg", target_size=(299, 299))
x = keras.utils.img_to_array(img)[None]     # (1,299,299,3)
x = iv3.preprocess_input(x)                 # -> -1..1
p = model.predict(x)                        # (1, 1000)

for _, name, prob in iv3.decode_predictions(p, top=5)[0]:
    print(f"{name:22s} {prob:.3f}")
```

Uscita del notebook del corso (InceptionV3).

<!-- SOURCE_VISUAL id="IS21-V51" source="L21-web.pdf" page="84" type="schermata" description="Foto del camion e uscita stampata delle 5 classi più probabili" reason="Immagine e output visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **`preprocess_input` di Inception (nota didattica).** Per InceptionV3 la funzione scala i pixel come $x/127{,}5-1$, da $[0,255]$ a $[-1,1]$; per VGG16 e ResNet50 invece sottrae le medie per canale di ImageNet. Usare la funzione di un altro modello non dà errori, ma degrada le previsioni in silenzio. 92 MB: $23{,}9$ M parametri × 4 byte ≈ 96 milioni di byte ≈ 91 MiB.

#### **6.3. Leggere il risultato in modo critico**

- «**moving van**» **0,44** e «**trailer truck**» **0,42**: la rete **divide la sua convinzione tra due classi ImageNet vicine**.
- **Le etichette sono quelle di ImageNet**: la rete **non può dire «semirimorchio refrigerato»** se quella classe non esiste.
- **Un punteggio top-1 basso è un’informazione utile**: **instradare i casi incerti a una persona** (la cascata della Lezione 18, §8.16).
- **Per le proprie classi ⇒ transfer learning.**

Rango 1 come stampato dal notebook (0,438); rango 2 misurato dal suo grafico a barre; i ranghi 3–5 sono sotto 0,01.

<!-- SOURCE_VISUAL id="IS21-V52" source="L21-web.pdf" page="85" type="grafico" description="Grafico a barre delle probabilità delle 5 classi più probabili per il camion" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.4. Lo stesso in MATLAB: una webcam e GoogLeNet**

```matlab
% rete + nomi delle 1000 classi ImageNet
[net, classNames] = imagePretrainedNetwork("googlenet");
sz = net.Layers(1).InputSize(1:2);           % [224 224]
camera = webcam;
I = snapshot(camera);
X = single(imresize(I, sz));                  % 224x224x3
scores = predict(net, X);                     % 1x1000
[label, score] = scores2label(scores, classNames)
[~, idx] = maxk(scores, 5);                   % top-5
barh(scores(flip(idx)));
yticklabels(classNames(flip(idx)))
```

**API aggiornata (R2024a+)**: sostituisce `googlenet` + `classify`. Deep Learning Toolbox + pacchetto di supporto per la webcam. Esempi: **banana** (punteggio 1); **tazza da caffè** 0,83, poi tazza.

<!-- SOURCE_VISUAL id="IS21-V53" source="L21-web.pdf" page="86" type="figura" description="Foto da webcam di una banana e di una tazza con i punteggi di GoogLeNet" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **`flip(idx)` (nota didattica).** `maxk` restituisce i 5 indici in ordine **decrescente** di punteggio; `barh` disegna la prima barra in basso. Ribaltando l’ordine con `flip`, la classe più probabile finisce in cima al grafico. `single(...)` converte l’immagine `uint8` in virgola mobile, come vuole `predict`.

#### **6.5. Provare un ingresso casuale: la rete risponde comunque**

```matlab
im = rand(224, 224, 3);                       % rumore
scores = predict(net, single(im));
[label, s] = scores2label(scores, ...
                          classNames)
% label = spotlight, s = 0.497
semilogy(sort(scores))                        % 1000 punteggi
```

- L’ingresso è **puro rumore**, eppure la softmax **deve dare 1000 punteggi che sommano a 1**.
- «**Spotlight**» (riflettore) ottiene **0,50**: **la rete non dice mai «non lo so»**.
- **Tempo di previsione**: circa **0,4 s** su una CPU da portatile.

<!-- SOURCE_VISUAL id="IS21-V54" source="L21-web.pdf" page="87" type="grafico" description="Immagine di rumore casuale e i 1000 punteggi ordinati in scala logaritmica" reason="Immagine e grafico visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Il rumore ha una classe (nota didattica).** La softmax distribuisce sempre una massa pari a 1 tra le 1000 classi, e qui ne dà metà a «spotlight». Per un’applicazione è proprio il problema della Lezione 5 (§1.6): una rete di classificazione **non sa dire «nessuna delle classi»**. Servono una soglia di confidenza, una classe «nessuno» addestrata o un rilevatore di input fuori distribuzione. Attenzione: `rand` produce valori in $[0,1]$, mentre la rete si aspetta pixel in $[0,255]$: l’immagine è quasi nera con un po’ di rumore, e la confidenza alta resta comunque ingiustificata.

---

### **7. Caso d’uso: oltre le foto**


Segnali, giochi e testi come immagini: spettrogrammi di macchine e voci, la scacchiera del Go, frasi.

#### **7.1. Un segnale 1-D diventa un’immagine: lo spettrogramma**

- **Motore**: 2 s a 8 kHz = **16 000 campioni** → immagine $49\times 663$. Un **cuscinetto danneggiato** colpisce **12 volte al secondo**: **strisce verticali attorno a 2,6 kHz**. Dati sintetici (seme fisso).
- **Voce**: una CNN legge una finestra dello spettrogramma e restituisce una **parola**.

<!-- SOURCE_VISUAL id="IS21-V55" source="L21-web.pdf" page="89" type="figura" description="Spettrogramma di un motore con cuscinetto danneggiato e spettrogramma di un parlato letto da una CNN" reason="Spettrogrammi visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Input**: un segnale. **Spettrogramma**: energia per frequenza (righe) e tempo (colonne) (Lezione 10). **Output**: classe (sano / danneggiato; una parola). **Ancora una ricetta valida per suoni di macchine, vibrazioni, ECG**; il riconoscimento del parlato moderno usa spesso **transformer** o ibridi convoluzione-transformer (**Conformer**).

> 💡 **Da dove vengono 49 e 663 (nota didattica).** Con finestre di 96 campioni e sovrapposizione di 72 (§9.10), il passo è $96-72=24$ campioni: $\lfloor(16\,000-96)/24\rfloor+1=663$ colonne. Le righe sono le frequenze della FFT di 96 punti, da 0 a 4 kHz: $96/2+1=49$. Ogni colonna copre $24/8000=3$ ms; ogni riga $4000/48\approx 83$ Hz. Gli impatti 12 volte al secondo producono una striscia verticale ogni 83 ms, cioè ogni circa 28 colonne.

#### **7.2. AlphaGo: la scacchiera del Go è un’immagine**

Dall’articolo di *Nature* su AlphaGo (Silver et al., 2016).

<!-- SOURCE_VISUAL id="IS21-V56" source="L21-web.pdf" page="90" type="figura" description="Scacchiera del Go e la rete di AlphaGo che produce le probabilità della mossa successiva" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Input**: matrice $19\times 19$ (nero 1, bianco −1, nessuno 0; **48 piani di feature**). **Output**: **probabilità della mossa successiva** su ognuna delle $19\times 19$ posizioni. Si potrebbe usare una rete completamente connessa, ma **una CNN funziona molto meglio**: **i pattern locali di pietre contano ovunque**. **Nota: nessun max pooling, perché ogni posizione conta.**

> 💡 **Perché niente pooling (nota didattica).** Il pooling rende la rete robusta a piccoli spostamenti (§3.17): utile per riconoscere un uccello, dannoso quando l’uscita è proprio una posizione. Spostare la mossa di una casella sul Go cambia completamente la partita. Le reti di segmentazione (Lezione 23) hanno lo stesso problema e lo risolvono con il decoder.

#### **7.3. Le CNN sui testi: un approccio storico (2014)**

- **Input**: un tweet. **Ogni parola → un vettore** (*word embedding*, uno dei primi modi di trasformare le parole in numeri, appendice).
- **Il tweet → una matrice $d\times|s|$**: come uno «**spettrogramma**» di parole.
- I **filtri scorrono lungo le parole**; pooling; softmax. **Output**: positivo / negativo.

<!-- SOURCE_VISUAL id="IS21-V57" source="L21-web.pdf" page="91" type="schema" description="CNN su un tweet: matrice degli embedding delle parole, filtri, pooling e softmax" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Lo stato dell’arte oggi**: il testo è elaborato dai **transformer** (Lezione 13), **non dalle CNN**. L’esempio è conservato perché **mostra la potenza della convoluzione**: **trova pattern locali in qualsiasi dato disposto su una griglia**, non solo nelle immagini.

---

### **8. Punti principali**


> ✅ **Punti principali della lezione.**
> - **In Keras una rete è un elenco di strati**: `Dense(1)` è il perceptron, `Dense(2)+Dense(1)` l’MLP dello XOR; **`fit` nasconde il ciclo di addestramento**. **Guardare sempre le curve di apprendimento.**
> - Il **deep learning impara le feature come una gerarchia** invece di progettarle; **funziona adesso grazie a dati, GPU e ReLU**.
> - Una **convoluzione è un piccolo filtro che scorre sull’immagine**: **connessioni locali e pesi condivisi**, molti meno parametri di uno strato denso.
> - **Dimensione d’uscita** $O=\lfloor(N+2P-k)/S\rfloor+1$; un filtro **attraversa tutti i canali d’ingresso**; **$F$ filtri danno $F$ mappe**.
> - **Conv → ReLU → pooling, ripetuti**; poi flatten (o global pooling), denso, softmax. **I punteggi softmax non sono calibrati: anche il rumore riceve una classe.**
> - **In pratica si parte da un backbone pre-addestrato e si addestra una nuova testa: transfer learning.**
> - **Qualsiasi segnale disponibile come immagine** (spettrogramma, scacchiera del Go, matrice di una frase) **può alimentare una CNN**.

---

### **9. Appendice: dettagli di supporto**


Il codice «sotto il cofano», MATLAB, le immagini extra della lezione originale.

#### **9.1. I dati dell’esempio della pompa (sintetici)**

Ogni riga è una pompa; l’etichetta $y$ dice se si è guastata entro 7 giorni. Generati con un seme fisso da `scripts/keras_perceptron_mlp.py` (stesso codice nel Laboratorio 1, parte C).

| Pompa | $x_1$ temp. (°C) | $x_2$ vibr. (mm/s) | $x_3$ corrente (A) | $x_4$ press. (bar) | $x_5$ velocità (rpm) | $y$ |
|---:|---:|---:|---:|---:|---:|---:|
| 1 | 62,1 | 4,23 | 11,4 | 5,24 | 1458 | 1 |
| 2 | 74,6 | 2,42 | 12,1 | 6,16 | 1446 | 0 |
| 3 | 76,2 | 3,12 | 11,2 | 5,68 | 1520 | 1 |
| 4 | 67,5 | 1,81 | 9,9 | 6,22 | 1525 | 0 |
| 5 | 68,3 | 3,79 | 8,8 | 5,88 | 1490 | 0 |
| 6 | 61,1 | 3,31 | 10,1 | 5,40 | 1535 | 0 |
| … | … | … | … | … | … | … |
| 2000 | | | | | | |

**609 guasti su 2000 (30,5%).** Il rischio **cresce con temperatura, vibrazione e corrente, e quando la velocità cala**; **alla rete questa regola non viene detta: deve impararla dagli esempi.** Prima dell’addestramento ogni colonna viene trasformata con lo **z-score usando media e deviazione standard del solo training set** (Lezione 16). Dati sintetici (seme fisso).

> 💡 **Il riferimento della classe maggioritaria (nota didattica).** Con il 30,5% di guasti sull’intero dataset, «nessun guasto» sarebbe corretto il 69,5% delle volte; sul test set specifico è il 71,7% (§1.14): la proporzione di guasti nelle 400 pompe di test è un po’ più bassa (28,3%). Una partizione stratificata (Lezione 14, §6.3) avrebbe reso le due proporzioni uguali.

#### **9.2. La convoluzione in codice: due righe, nessun ciclo**

```python
# Python (NumPy)
from numpy.lib.stride_tricks import sliding_window_view
import numpy as np

def conv2d(I, K, stride=1):
    # ogni patch k x k di I, come array (H', W', k, k)
    W = sliding_window_view(I, K.shape)[::stride, ::stride]
    # prodotto scalare patch . kernel per tutte le patch insieme
    return np.einsum("ijkl,kl->ij", W, K)

def relu(X): return np.maximum(X, 0)
def maxpool(X, p=2):     # blocchi p x p non sovrapposti
    H, W = X.shape[0]//p*p, X.shape[1]//p*p
    return X[:H, :W].reshape(H//p, p, W//p, p).max(axis=(1, 3))
```

```matlab
% MATLAB
% Le CNN calcolano una correlazione:
% ribaltare il kernel per conv2, oppure usare filter2
Y = conv2(I, rot90(K,2), "valid");
Y = filter2(K, I, "valid");                  % uguale
Ys = Y(1:2:end, 1:2:end);                    % passo 2
R = max(Y, 0);                               % ReLU
P = squeeze(max(max( ...                     % pool 2x2
    reshape(A,2,2,2,2),[],1),[],3));
```

**Input**: $I$ ($6\times 6$), $K$ ($3\times 3$). **Output**: la mappa $4\times 4$ delle slide precedenti. Entrambe le versioni sono eseguite in `coding-lab/` (Python) e verificate in Octave (MATLAB).

> 💡 **Due dettagli (nota didattica).** 1) `einsum("ijkl,kl->ij", W, K)` moltiplica ogni patch $(k,l)$ per il kernel e somma su $k,l$, lasciando gli indici di posizione $i,j$: è la formula del §3.6 in una riga. 2) `maxpool` taglia prima l’ultima riga e l’ultima colonna se la dimensione è dispari ($11\to 10$), come Keras (§3.21). Nella riga MATLAB del pooling la variabile `A` è la mappa da sottocampionare (qui una $4\times 4$; la forma `reshape(A,2,2,2,2)` vale solo per quella dimensione).

#### **9.3. CNN-A in PyTorch**

```python
import torch, torch.nn as nn

net = nn.Sequential(
    # i canali d'ingresso sono espliciti: 1 -> 25 -> 50
    nn.Conv2d(1, 25, 3), nn.ReLU(), nn.MaxPool2d(2),
    nn.Conv2d(25, 50, 3), nn.ReLU(), nn.MaxPool2d(2),
    nn.Flatten(),
    # 1250 = 50*5*5 va calcolato a mano
    nn.Linear(50 * 5 * 5, 100), nn.ReLU(),
    nn.Linear(100, 10))                  # logit (nessuna softmax)
# numero di parametri: 137660
print(sum(p.numel() for p in net.parameters()))
```

| | Keras 3 | PyTorch |
|---|---|---|
| disposizione dell’immagine | $N\times H\times W\times C$ | $N\times C\times H\times W$ |
| canali d’ingresso | dedotti | scritti |
| dimensione del flatten | dedotta | a mano |
| softmax | nel modello | nella loss |

#### **9.4. Il ciclo di addestramento scritto in modo esplicito**

```python
opt = torch.optim.Adam(net.parameters(), lr=1e-3)
loss_fn = nn.CrossEntropyLoss()          # softmax + -log p

for epoch in range(3):
    for x, y in train_loader:            # x: (128,1,28,28)
        logits = net(x)                  # 1. passo in avanti
        loss = loss_fn(logits, y)        # 2. loss
        opt.zero_grad()                  # 3. azzerare i gradienti
        loss.backward()                  # 4. backprop
        opt.step()                       # 5. aggiornamento
```

**Queste cinque righe sono esattamente ciò che `model.fit()` fa per voi in Keras.** Keras 3 può anche girare su questo motore: `KERAS_BACKEND="torch"`.

#### **9.5. La backpropagation attraverso una convoluzione**

Lo **stesso peso del filtro** $K_{u,v}$ è usato in ogni posizione d’uscita, quindi **il suo gradiente somma i contributi di tutte le posizioni**:

$$\frac{\partial L}{\partial K_{u,v}}=\sum_i\sum_j\frac{\partial L}{\partial y_{i,j}}\,I_{i+u-1,\ j+v-1}.$$

- $\partial L/\partial y_{i,j}$: quanto cambia la loss se cambia l’uscita $y_{i,j}$ (arriva dallo strato successivo).
- $I_{i+u-1,j+v-1}$: il pixel d’ingresso che $K_{u,v}$ ha moltiplicato in quella posizione.

**Esempio** (1-D per semplicità): ingresso $I=(1,2,3)$, filtro $K=(k_1,k_2)$, uscite $y_1=k_1\cdot 1+k_2\cdot 2$, $y_2=k_1\cdot 2+k_2\cdot 3$. Se $\partial L/\partial y=(0{,}5;\ -1)$:

$$\frac{\partial L}{\partial k_1}=0{,}5\cdot 1+(-1)\cdot 2=-1{,}5,\qquad \frac{\partial L}{\partial k_2}=0{,}5\cdot 2+(-1)\cdot 3=-2.$$

> 📌 **Il gradiente di un filtro è a sua volta una correlazione** dell’ingresso con il gradiente in arrivo: **autograd lo fa per voi**.

#### **9.6. Come si vettorizza la convoluzione: le patch come matrice**

Si copia ogni patch $3\times 3$ in **una riga** («**im2col**»): la matrice delle patch è **16 righe × 9 colonne**; moltiplicata per il filtro ($9\times 1$) dà $16\times 1$, rimodellato in $4\times 4$. **L’intera convoluzione è un solo prodotto matriciale**; con $F$ filtri il filtro diventa una matrice $9\times F$. **È così che la calcolano le GPU**, ed è ciò che fanno `sliding_window_view` + `einsum` nel nostro codice NumPy.

<!-- SOURCE_VISUAL id="IS21-V58" source="L21-web.pdf" page="99" type="schema" description="Trasformazione im2col: patch dell'immagine come righe di una matrice moltiplicata per il filtro" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **9.7. Reti pre-addestrate in PyTorch (torchvision)**

```python
import torch
from PIL import Image
from torchvision.models import inception_v3, Inception_V3_Weights

w = Inception_V3_Weights.DEFAULT                 # pesi ImageNet
net = inception_v3(weights=w).eval()             # modalità di inferenza
# i pesi portano con sé la loro pre-elaborazione
x = w.transforms()(Image.open("truck.jpg").convert("RGB"))[None]
with torch.no_grad():                            # niente gradienti
    p = net(x).softmax(dim=1)[0]                 # (1000,)
for prob, i in zip(*p.topk(5)):
    print(w.meta["categories"][i], round(prob.item(), 3))
```

Stesso compito del caso d’uso in Keras: `w.transforms()` (ridimensionamento, ritaglio, normalizzazione); `w.meta` (i nomi delle 1000 classi); `.eval()` + `no_grad()` (solo previsione). Script: `coding-lab/python/appendix_pytorch_*`.

#### **9.8. CNN-A in MATLAB**

```matlab
layers = [
    imageInputLayer([28 28 1])
    convolution2dLayer(3, 25)              % 26x26x25
    reluLayer
    maxPooling2dLayer(2, Stride=2)         % 13x13x25
    convolution2dLayer(3, 50)              % 11x11x50
    reluLayer
    maxPooling2dLayer(2, Stride=2)         % 5x5x50
    fullyConnectedLayer(100)               % flatten implicito
    reluLayer
    fullyConnectedLayer(10)
    softmaxLayer];
opts = trainingOptions("adam", MaxEpochs=5, ...
    Metrics="accuracy", Plots="training-progress");
net = trainnet(imdsTrain, layers, "crossentropy", opts);
scores = minibatchpredict(net, imdsTest);  % N x 10
YPred = scores2label(scores, classNames);
```

**Strato per strato la stessa rete di Keras** (137 660 parametri). `imdsTrain`: un `imageDatastore` delle cifre $28\times 28$ fornite con il toolbox (script completo in `coding-lab/matlab/cnn_digits.m`).

#### **9.9. Transfer learning e telecamera dal vivo in MATLAB**

```matlab
% Transfer learning: nuova testa con il proprio numero di classi
net = imagePretrainedNetwork("mobilenetv2", ...
    NumClasses=numel(classNames));
sz = net.Layers(1).InputSize(1:2);
aug = imageDataAugmenter(RandXReflection=true);
trainDS = augmentedImageDatastore(sz, imdsTrain, ...
    DataAugmentation=aug);
opts = trainingOptions("adam", InitialLearnRate=1e-4, ...
    MaxEpochs=5);
net = trainnet(trainDS, net, "crossentropy", opts);
```

```matlab
% Webcam dal vivo
[net, cls] = imagePretrainedNetwork("googlenet");
sz = net.Layers(1).InputSize(1:2);
camera = webcam; h = figure;
while ishandle(h)
    I = snapshot(camera);
    s = predict(net, single(imresize(I, sz)));
    [lab, p] = scores2label(s, cls);
    image(I); axis image off
    title(sprintf("%s (%.2f)", string(lab), p))
    drawnow
end
```

Richiede il Deep Learning Toolbox e, per la telecamera, il pacchetto di supporto USB Webcam. Script completi in `coding-lab/matlab/`.

> 💡 **`InitialLearnRate=1e-4` (nota didattica).** Qui `imagePretrainedNetwork(..., NumClasses=...)` sostituisce la testa e lascia addestrabile tutta la rete: è un fine-tuning completo, per cui si usa un learning rate 10 volte più piccolo del default di Adam ($10^{-3}$), la regola del §5.8.

#### **9.10. Spettrogramma + CNN in codice**

```python
# Python (SciPy + Keras 3)
from scipy.signal import spectrogram
# 16000 campioni -> immagine (49, 663, 1)
def to_image(x, fs=8000):
    f, t, S = spectrogram(x, fs, nperseg=96,
                          noverlap=72)
    return np.log10(S + 1e-10)[..., None]

model = keras.Sequential([
    keras.Input((49, 663, 1)),
    layers.Conv2D(16, 3, activation="relu"),
    layers.MaxPooling2D(2),
    layers.Conv2D(32, 3, activation="relu"),
    layers.GlobalAveragePooling2D(),
    layers.Dense(2, activation="softmax")])      # 2 classi
```

```matlab
% MATLAB
[S, f, t] = spectrogram(x, 96, 72, [], 8000);
X = log10(abs(S).^2 + 1e-10);                    % immagine
```

**Stessa ricetta per**: comandi vocali, suoni di macchine, ECG, vibrazioni di ponti e pompe. **Per il linguaggio le CNN sono state sostituite dai transformer**, trattati in altre lezioni del corso.

> 💡 **Il logaritmo (nota didattica).** L’energia di uno spettrogramma copre molti ordini di grandezza: il $\log_{10}$ (la scala in decibel, a meno di un fattore 10) rende visibili sia le componenti forti sia quelle deboli, e il $+10^{-10}$ evita $\log 0$. Senza logaritmo la CNN vedrebbe solo i picchi più energetici.

#### **9.11. Figure originali della lezione precedente**

**Addestramento e rilascio (corso NVIDIA)**: addestramento con molte immagini etichettate (avanti + indietro); rilascio con una nuova immagine, solo in avanti, pesi fissi; «profondo» = molti strati, molti parametri, molti esempi. Fonte: corso NVIDIA Deep Learning; i numeri della figura di destra risalgono a circa il 2015.

<!-- SOURCE_VISUAL id="IS21-V59" source="L21-web.pdf" page="104" type="figura" description="Figure originali NVIDIA su addestramento e rilascio" reason="Figure visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Unità apprese**: ogni quadrato mostra il pattern d’ingresso che **attiva di più** una delle 100 unità di uno strato appreso; **la maggior parte delle unità è diventata un rilevatore di bordi orientati: nessuno li ha progettati**. Adattato da © T. Martinez.

<!-- SOURCE_VISUAL id="IS21-V60" source="L21-web.pdf" page="105" type="figura" description="Pattern d'ingresso che attivano di più 100 unità di uno strato appreso" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Una discussione «più profonda»: i campi collegati.**

<!-- SOURCE_VISUAL id="IS21-V61" source="L21-web.pdf" page="106" type="figura" description="Mappa dei campi collegati al deep learning" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Tassonomia dei modelli di apprendimento** (fonte: corso NVIDIA Deep Learning; * esiste una versione supervisionata).

<!-- SOURCE_VISUAL id="IS21-V62" source="L21-web.pdf" page="107" type="figura" description="Tassonomia dei modelli di apprendimento" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Word embedding (storico): parole convertite in numeri.**

<!-- SOURCE_VISUAL id="IS21-V63" source="L21-web.pdf" page="108" type="figura" description="Parole convertite in vettori numerici con un word embedding" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Word2Vec (storico): un estrattore neurale di feature per le parole.**

<!-- SOURCE_VISUAL id="IS21-V64" source="L21-web.pdf" page="109" type="figura" description="Architettura di Word2Vec" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Word2Vec (nota didattica, Lezione 13).** Word2Vec impara un vettore per ogni parola chiedendo a una rete poco profonda di prevedere le parole vicine (o la parola centrale dalle vicine): parole che compaiono negli stessi contesti finiscono vicine nello spazio, e alcune relazioni diventano differenze di vettori (re − uomo + donna ≈ regina). È l’antenato degli embedding dei token dei transformer.

---

### **10. Approfondimento — il notebook Colab `Image_classification_Inception_v3_COLAB.ipynb`**


Il notebook della cartella è la versione dell’edizione precedente del caso d’uso del §6. Il codice:

```python
!pip install -q keras

from keras.applications.inception_v3 import InceptionV3
model = InceptionV3(weights='imagenet', include_top=True)
```

`weights='imagenet'` carica i pesi pre-addestrati; `include_top=True` include lo strato finale che classifica nelle 1000 classi.

**Pre-elaborazione e predizione**: una funzione `predict()` che riceve l’immagine e restituisce le **5 classi più probabili**.

```python
import numpy as np
from keras.applications.inception_v3 import preprocess_input, decode_predictions

def predict(model, img, top_n=5):
    x = np.expand_dims(img, axis=0)   # da (299, 299, 3) a (1, 299, 299, 3): un batch di una immagine
    x = preprocess_input(x)           # normalizzazione richiesta dalla rete
    preds = model.predict(x)          # HERE'S THE CLASS PREDICTION
    return decode_predictions(preds, top=top_n)[0]
```

**Caricare un esempio e ridimensionarlo** (nel notebook `Truck.jpg`, 593 821 byte) a $299\times 299\times 3$:

```python
from google.colab import files
uploaded = files.upload()
for fn in uploaded.keys():
    print('User uploaded file "{name}" with length {length} bytes'.format(name=fn, length=len(uploaded[fn])))

import matplotlib.image as mpimg
import cv2
img = mpimg.imread(fn)
img = cv2.resize(img, dsize=(299, 299), interpolation=cv2.INTER_CUBIC)
plt.imshow(img)
```

**Eseguire la predizione e visualizzare le probabilità**:

```python
pred = predict(model, img)
print(pred[0])     # ('n03796401', 'moving_van', 0.43757406)

plt.figure(figsize=(8, 2))
classes = [c[1] for c in pred]
probas = [c[2] for c in pred]
y_pos = np.arange(len(classes))
plt.barh(y_pos, probas, align='center')
plt.yticks(y_pos, classes)
plt.gca().invert_yaxis()
plt.xlabel('Probability')
plt.xlim(0, 1)
print(classes)
```

Risultato salvato nel notebook: la classe più probabile è **«moving_van»** (furgone per traslochi) con probabilità **0,438**; le 5 classi sono `moving_van`, `trailer_truck`, `mobile_home`, `recreational_vehicle`, `tow_truck` (la versione 2026/27 riporta «trailer truck» al secondo posto con 0,42, §6.3).

> 💡 **Leggere il risultato (nota didattica).** Le cinque classi sono tutte veicoli simili, e la prima ha meno della metà della massa: la rete «sa» che si tratta di un grande veicolo, ma è incerta sul tipo esatto, perché ImageNet non ha una classe «camion generico». Il codice che trasforma `n03796401` in «moving_van» è `decode_predictions`, che usa gli identificativi WordNet delle classi di ImageNet.

> ⚠️ **Dettagli del codice (nota didattica).**
> - `cv2.resize` a $299\times 299$ **non conserva le proporzioni**: un’immagine rettangolare viene deformata. Spesso conviene ritagliare al centro prima di ridimensionare.
> - `mpimg.imread` restituisce valori `uint8` tra 0 e 255 per i JPEG, che è ciò che `preprocess_input` si aspetta (li porta tra −1 e 1). Con un PNG gli stessi valori arriverebbero già tra 0 e 1, e la normalizzazione sarebbe sbagliata senza alcun errore visibile.
> - Il commento del notebook su `preprocess_input` parla di «centrare i dati sottraendo la media dei canali del dataset di addestramento». Per **Inception-v3** non è così: la funzione scala i pixel come $x/127{,}5-1$ (§6.2). La sottrazione delle medie per canale è la modalità di VGG16 e ResNet50.
> - Il notebook importa anche `from keras.preprocessing import image` e `from PIL import Image`, che non usa. Il primo nelle versioni recenti di Keras non esiste più in quella forma (si usa `keras.utils.load_img`, come nella versione 2026/27).
> - L’output salvato mostra «Saving Truck.jpg to Truck (4).jpg»: il file era già stato caricato più volte, e Colab ha salvato la nuova copia con un suffisso. La chiave restituita da `files.upload()` resta però «Truck.jpg», e `mpimg.imread(fn)` legge la **prima copia** presente su disco. Qui è la stessa immagine e il risultato non cambia, ma caricando un’immagine diversa con lo stesso nome si classificherebbe quella vecchia senza accorgersene.
> - Il file `Truck299.jpg` presente nella cartella sembra una versione già ridimensionata, ma il notebook non lo usa.

---

### **11. Distinzioni da non confondere**


| Coppia | Differenza chiave |
|---|---|
| **`compile()`** / **compilazione del codice** | configura loss, ottimizzatore e metriche / non avviene |
| **loss** / **metrica** | derivabile, minimizzata dall’addestramento / letta, nessun gradiente (accuratezza) |
| **MSE** / **cross-entropy** | regressione, limitata a 1 per campione con $p\in(0,1)$ / classificazione, illimitata per errori sicuri |
| **epoca** / **batch** | un passaggio su tutti i dati / un gruppo di campioni per un aggiornamento |
| **validazione** / **test** | controlla l’addestramento / misura finale, mai usato per scegliere |
| **feature progettate** / **apprese** | SIFT, HoG, Sobel / filtri appresi da una CNN |
| **convoluzione** / **strato denso** | locale, pesi condivisi / ogni ingresso a ogni neurone |
| **filtro** / **feature map** | i $k\times k\times C$ pesi / l’uscita di un filtro su tutta l’immagine |
| **padding** / **stride** | zeri attorno al bordo, conserva la dimensione / passo del filtro, riduce la dimensione |
| **canali d’ingresso** / **filtri** | profondità del filtro / numero di mappe in uscita |
| **ReLU** / **sigmoide** | derivata 1 per $x>0$, unità può morire / derivata massima 0,25, satura |
| **max pooling** / **convoluzione** | nessun peso, prende il massimo / pesi appresi, prodotto scalare |
| **flatten** / **global average pooling** | tutte le posizioni, molti pesi nella testa / una media per mappa, pochi pesi |
| **logit** / **probabilità softmax** | numero reale qualsiasi / positivi a somma 1, non calibrati |
| **top-1** / **top-5** | classe più probabile corretta / classe vera tra le 5 più probabili |
| **backbone** / **testa** | parte convolutiva riusabile / decisione finale specifica |
| **transfer learning** / **fine-tuning** | backbone congelato, nuova testa / sblocco degli ultimi strati con $\eta$ piccolo |
| **classificazione** / **rilevamento** | una classe per immagine / box con classe e punteggio (YOLO) |
| **testa riaddestrata** / **embedding + distanza** | classi fisse etichettate / vicini e cluster senza riaddestrare |
| **Keras** / **PyTorch** | canali in fondo, flatten dedotto, softmax nel modello / canali dopo il batch, flatten a mano, softmax nella loss |
| **convoluzione matematica** / **convoluzione delle CNN** | filtro ribaltato (`conv2`) / correlazione (`filter2`) |
