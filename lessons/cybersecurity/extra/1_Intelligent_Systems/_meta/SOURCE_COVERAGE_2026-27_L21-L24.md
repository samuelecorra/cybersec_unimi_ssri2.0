# Copertura pagina-per-pagina — nuova edizione, Lezioni 21–24

Revisione integrale del 2026-11-23: le lezioni sono state riscritte end-to-end seguendo l'ordine delle slide 2026/27. Per ogni pagina è stato estratto testo nativo UTF-8 parola per parola con PyMuPDF (senza scartare le celle numeriche isolate); la presenza di figure è stata stimata dai blocchi immagine e dai tracciati vettoriali, senza rendering né OCR. I placeholder `SOURCE_VISUAL` puntano esclusivamente ai PDF in `LezioneNN/sources_2026-27/` e sono riservati a figure, grafici, schermate e schemi; testo, tabelle, formule e codice delle slide sono trascritti nel Markdown. Il materiale dell'edizione precedente non più presente nelle slide è conservato nelle note o in una sezione di approfondimento. La colonna «Destinazione» indica la sezione della lezione in cui la pagina è trattata (stimata dalla posizione del placeholder più vicino).

| Lezione | PDF | Pagine | Placeholder |
|---|---|---:|---:|
| 21 | `L21-web.pdf` | 109 | 64 |
| 22 | `L22-web.pdf` | 102 | 84 |
| 23 | `L23-web.pdf` | 112 | 85 |
| 24 | `L24-web.pdf` | 121 | 85 |

| Lezione | Pagina | Titolo della slide | Destinazione | Trattamento | Ispezione visiva |
|---:|---:|---|---|---|---|
| 21 | 1 | Intelligent Systems for Industry, Supply Chain and Environment | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 21 | 2 | Outline | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 21 | 3 | Toolboxes: neural networks in Keras | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 21 | 4 | Keras in one slide | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 21 | 5 | What fit() does: the training loop | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 21 | 6 | A perceptron in Keras (1): the network and the training set | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 21 | 7 | A perceptron in Keras (2): the training set in Python | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 21 | 8 | A perceptron in Keras (3): the model and its training | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 21 | 9 | A perceptron in Keras (4): output and learned weights | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 21 | 10 | From the perceptron to the MLP: XOR in Keras | §1 Strumenti: dal perceptron all’MLP in Keras | placeholder IS21-V01 | non richiesta |
| 21 | 11 | How wrong is the network? Two loss functions | §1 Strumenti: dal perceptron all’MLP in Keras | testo/tabella/codice trascritti | non richiesta |
| 21 | 12 | The two losses on four samples | §1 Strumenti: dal perceptron all’MLP in Keras | testo/tabella/codice trascritti | non richiesta |
| 21 | 13 | Inside the model: where are w13 and θ3? | §1 Strumenti: dal perceptron all’MLP in Keras | testo/tabella/codice trascritti | non richiesta |
| 21 | 14 | Training and checking the MLP | §1 Strumenti: dal perceptron all’MLP in Keras | placeholder IS21-V02 | non richiesta |
| 21 | 15 | What has the MLP learned? 1000 random inputs | §1 Strumenti: dal perceptron all’MLP in Keras | placeholder IS21-V03 | non richiesta |
| 21 | 16 | The standard network in Keras: a pump that may fail | §1 Strumenti: dal perceptron all’MLP in Keras | placeholder IS21-V04 | non richiesta |
| 21 | 17 | Training and checking: the validation curve | §1 Strumenti: dal perceptron all’MLP in Keras | placeholder IS21-V05 | non richiesta |
| 21 | 18 | Overview: deep learning | §1 Strumenti: dal perceptron all’MLP in Keras | testo/tabella/codice trascritti | non richiesta |
| 21 | 19 | < | §2 Panoramica: il deep learning | placeholder IS21-V06 | non richiesta |
| 21 | 20 | The road map | §2 Panoramica: il deep learning | placeholder IS21-V07 | non richiesta |
| 21 | 21 | The relevance: two Nobel prizes in 2024 | §2 Panoramica: il deep learning | placeholder IS21-V08 | non richiesta |
| 21 | 22 | Deep learning: a deﬁnition | §2 Panoramica: il deep learning | placeholder IS21-V09 | non richiesta |
| 21 | 23 | Why now? Compute, data and interest | §2 Panoramica: il deep learning | placeholder IS21-V10 | non richiesta |
| 21 | 24 | Machine learning versus deep learning | §2 Panoramica: il deep learning | placeholder IS21-V11 | non richiesta |
| 21 | 25 | Before deep learning: the designer extracts the features | §2 Panoramica: il deep learning | placeholder IS21-V12 | non richiesta |
| 21 | 26 | A classic feature extractor: the convolution | §2 Panoramica: il deep learning | placeholder IS21-V13 | non richiesta |
| 21 | 27 | Diﬀerent ﬁlters, diﬀerent feature maps | §2 Panoramica: il deep learning | placeholder IS21-V14 | non richiesta |
| 21 | 28 | Learning a hierarchy of features | §2 Panoramica: il deep learning | placeholder IS21-V15 | non richiesta |
| 21 | 29 | Train once, deploy many times | §2 Panoramica: il deep learning | placeholder IS21-V16 | non richiesta |
| 21 | 30 | Looking inside: what did the units learn? | §2 Panoramica: il deep learning | placeholder IS21-V17 | non richiesta |
| 21 | 31 | Why deep networks? | §2 Panoramica: il deep learning | placeholder IS21-V18 | non richiesta |
| 21 | 32 | Theory: convolutional neural networks | §2 Panoramica: il deep learning | testo/tabella/codice trascritti | non richiesta |
| 21 | 33 | From pixels to a decision | §3 Teoria: le reti neurali convoluzionali | placeholder IS21-V19 | non richiesta |
| 21 | 34 | Why not a fully connected network on images? | §3 Teoria: le reti neurali convoluzionali | placeholder IS21-V20 | non richiesta |
| 21 | 35 | Some patterns are much smaller than the whole image | §3 Teoria: le reti neurali convoluzionali | placeholder IS21-V21 | non richiesta |
| 21 | 36 | The same pattern appears in diﬀerent places | §3 Teoria: le reti neurali convoluzionali | placeholder IS21-V22 | non richiesta |
| 21 | 37 | A convolutional layer: ﬁlters that slide | §3 Teoria: le reti neurali convoluzionali | placeholder IS21-V23 | non richiesta |
| 21 | 38 | Convolution step by step (1): one dot product | §3 Teoria: le reti neurali convoluzionali | testo/tabella/codice trascritti | non richiesta |
| 21 | 39 | Convolution step by step (2): slide the ﬁlter | §3 Teoria: le reti neurali convoluzionali | testo/tabella/codice trascritti | non richiesta |
| 21 | 40 | Where does the ﬁlter ﬁre? | §3 Teoria: le reti neurali convoluzionali | testo/tabella/codice trascritti | non richiesta |
| 21 | 41 | Why the output is smaller: three examples | §3 Teoria: le reti neurali convoluzionali | placeholder IS21-V24 | non richiesta |
| 21 | 42 | The general rule: output size | §3 Teoria: le reti neurali convoluzionali | testo/tabella/codice trascritti | non richiesta |
| 21 | 43 | Many ﬁlters, many channels | §3 Teoria: le reti neurali convoluzionali | placeholder IS21-V25 | non richiesta |
| 21 | 44 | Colour images: ﬁlters have depth | §3 Teoria: le reti neurali convoluzionali | placeholder IS21-V26 | non richiesta |
| 21 | 45 | Convolution versus fully connected (ﬁrst layer) | §3 Teoria: le reti neurali convoluzionali | placeholder IS21-V27 | non richiesta |
| 21 | 46 | Fewer parameters: local connections and shared weights | §3 Teoria: le reti neurali convoluzionali | placeholder IS21-V28 | non richiesta |
| 21 | 47 | ReLU: keep the evidence, drop the rest | §3 Teoria: le reti neurali convoluzionali | placeholder IS21-V29 | non richiesta |
| 21 | 48 | CNN architecture: the big picture | §3 Teoria: le reti neurali convoluzionali | placeholder IS21-V30 | non richiesta |
| 21 | 49 | CNN-A: the CNN of this lesson, with its shapes | §3 Teoria: le reti neurali convoluzionali | placeholder IS21-V31 | non richiesta |
| 21 | 50 | Why pooling: subsampling does not change the object | §3 Teoria: le reti neurali convoluzionali | placeholder IS21-V32 | non richiesta |
| 21 | 51 | Max pooling step by step | §3 Teoria: le reti neurali convoluzionali | testo/tabella/codice trascritti | non richiesta |
| 21 | 52 | From feature maps to classes: ﬂatten and dense | §3 Teoria: le reti neurali convoluzionali | testo/tabella/codice trascritti | non richiesta |
| 21 | 53 | The typical last layer: softmax | §3 Teoria: le reti neurali convoluzionali | placeholder IS21-V33 | non richiesta |
| 21 | 54 | CNN-A: shapes and parameters | §3 Teoria: le reti neurali convoluzionali | testo/tabella/codice trascritti | non richiesta |
| 21 | 55 | The training loop | §3 Teoria: le reti neurali convoluzionali | placeholder IS21-V34 | non richiesta |
| 21 | 56 | Modern practice that is here to stay | §3 Teoria: le reti neurali convoluzionali | testo/tabella/codice trascritti | non richiesta |
| 21 | 57 | Toolboxes: a CNN in Keras | §3 Teoria: le reti neurali convoluzionali | testo/tabella/codice trascritti | non richiesta |
| 21 | 58 | Step 1: the input | §4 Strumenti: la stessa CNN in Keras | placeholder IS21-V35 | non richiesta |
| 21 | 59 | Step 2: ﬁrst convolution block | §4 Strumenti: la stessa CNN in Keras | testo/tabella/codice trascritti | non richiesta |
| 21 | 60 | Step 3: second convolution block | §4 Strumenti: la stessa CNN in Keras | testo/tabella/codice trascritti | non richiesta |
| 21 | 61 | Step 4: the classiﬁer head | §4 Strumenti: la stessa CNN in Keras | testo/tabella/codice trascritti | non richiesta |
| 21 | 62 | Step 5: train, check, evaluate | §4 Strumenti: la stessa CNN in Keras | placeholder IS21-V36 | non richiesta |
| 21 | 63 | Where does the network fail? | §4 Strumenti: la stessa CNN in Keras | placeholder IS21-V37 | non richiesta |
| 21 | 64 | What did the ﬁrst layer learn? | §4 Strumenti: la stessa CNN in Keras | placeholder IS21-V38 | non richiesta |
| 21 | 65 | CNN-B: a modern version of CNN-A | §4 Strumenti: la stessa CNN in Keras | testo/tabella/codice trascritti | non richiesta |
| 21 | 66 | CNN Explainer: try it! | §4 Strumenti: la stessa CNN in Keras | placeholder IS21-V39 | non richiesta |
| 21 | 67 | Your turn: the coding lab | §4 Strumenti: la stessa CNN in Keras | testo/tabella/codice trascritti | non richiesta |
| 21 | 68 | Theory: pretrained networks | §4 Strumenti: la stessa CNN in Keras | testo/tabella/codice trascritti | non richiesta |
| 21 | 69 | AlexNet (2012): the network that started it | §5 Teoria: CNN famose e transfer learning | placeholder IS21-V40 | non richiesta |
| 21 | 70 | AlexNet in Keras: model.summary() | §5 Teoria: CNN famose e transfer learning | testo/tabella/codice trascritti | non richiesta |
| 21 | 71 | LeNet-5 and VGG16: small and deep | §5 Teoria: CNN famose e transfer learning | placeholder IS21-V41 | non richiesta |
| 21 | 72 | Families of CNNs used in applications | §5 Teoria: CNN famose e transfer learning | testo/tabella/codice trascritti | non richiesta |
| 21 | 73 | Residual connections: the idea behind very deep CNNs | §5 Teoria: CNN famose e transfer learning | placeholder IS21-V42 | non richiesta |
| 21 | 74 | The big picture: reuse a trained backbone | §5 Teoria: CNN famose e transfer learning | placeholder IS21-V43 | non richiesta |
| 21 | 75 | A CNN as a feature extractor: VGG-Face + PCA | §5 Teoria: CNN famose e transfer learning | placeholder IS21-V44 | non richiesta |
| 21 | 76 | Transfer learning in three moves | §5 Teoria: CNN famose e transfer learning | placeholder IS21-V45 | non richiesta |
| 21 | 77 | Transfer learning in Keras 3 | §5 Teoria: CNN famose e transfer learning | testo/tabella/codice trascritti | non richiesta |
| 21 | 78 | Beyond classiﬁcation: detection with YOLO | §5 Teoria: CNN famose e transfer learning | placeholder IS21-V46 | non richiesta |
| 21 | 79 | A cheap network can say a lot about you | §5 Teoria: CNN famose e transfer learning | placeholder IS21-V47 | non richiesta |
| 21 | 80 | The same task, two approaches | §5 Teoria: CNN famose e transfer learning | placeholder IS21-V48 | non richiesta |
| 21 | 81 | Who designs the CNN? Designer, AutoML, agents | §5 Teoria: CNN famose e transfer learning | placeholder IS21-V49 | non richiesta |
| 21 | 82 | Use case: classify a photo | §5 Teoria: CNN famose e transfer learning | testo/tabella/codice trascritti | non richiesta |
| 21 | 83 | The pipeline: shapes at every step | §6 Caso d’uso: classificare una foto con una CNN pre-addestrata | placeholder IS21-V50 | non richiesta |
| 21 | 84 | The code in Keras 3 (Colab) | §6 Caso d’uso: classificare una foto con una CNN pre-addestrata | placeholder IS21-V51 | non richiesta |
| 21 | 85 | Reading the result critically | §6 Caso d’uso: classificare una foto con una CNN pre-addestrata | placeholder IS21-V52 | non richiesta |
| 21 | 86 | The same in MATLAB: a webcam and GoogLeNet | §6 Caso d’uso: classificare una foto con una CNN pre-addestrata | placeholder IS21-V53 | non richiesta |
| 21 | 87 | Try a random input: the network still answers | §6 Caso d’uso: classificare una foto con una CNN pre-addestrata | placeholder IS21-V54 | non richiesta |
| 21 | 88 | Use case: beyond photos | §6 Caso d’uso: classificare una foto con una CNN pre-addestrata | testo/tabella/codice trascritti | non richiesta |
| 21 | 89 | A 1-D signal becomes an image: the spectrogram | §7 Caso d’uso: oltre le foto | placeholder IS21-V55 | non richiesta |
| 21 | 90 | AlphaGo: the board of Go is an image | §7 Caso d’uso: oltre le foto | placeholder IS21-V56 | non richiesta |
| 21 | 91 | CNNs on texts: a historical approach (2014) | §7 Caso d’uso: oltre le foto | placeholder IS21-V57 | non richiesta |
| 21 | 92 | Main points | §7 Caso d’uso: oltre le foto | testo/tabella/codice trascritti | non richiesta |
| 21 | 93 | Appendix | §7 Caso d’uso: oltre le foto | testo/tabella/codice trascritti | non richiesta |
| 21 | 94 | Data of the pump example (synthetic) | §7 Caso d’uso: oltre le foto | testo/tabella/codice trascritti | non richiesta |
| 21 | 95 | Convolution in code: two lines, no loops | §7 Caso d’uso: oltre le foto | testo/tabella/codice trascritti | non richiesta |
| 21 | 96 | CNN-A in PyTorch | §7 Caso d’uso: oltre le foto | testo/tabella/codice trascritti | non richiesta |
| 21 | 97 | The training loop, written explicitly | §7 Caso d’uso: oltre le foto | testo/tabella/codice trascritti | non richiesta |
| 21 | 98 | Back-propagation through a convolution | §7 Caso d’uso: oltre le foto | testo/tabella/codice trascritti | non richiesta |
| 21 | 99 | How convolution is vectorised: patches as a matrix | §9 Appendice: dettagli di supporto | placeholder IS21-V58 | non richiesta |
| 21 | 100 | Pretrained networks in PyTorch (torchvision) | §9 Appendice: dettagli di supporto | testo/tabella/codice trascritti | non richiesta |
| 21 | 101 | CNN-A in MATLAB | §9 Appendice: dettagli di supporto | testo/tabella/codice trascritti | non richiesta |
| 21 | 102 | Transfer learning and live camera in MATLAB | §9 Appendice: dettagli di supporto | testo/tabella/codice trascritti | non richiesta |
| 21 | 103 | Spectrogram + CNN in code | §9 Appendice: dettagli di supporto | testo/tabella/codice trascritti | non richiesta |
| 21 | 104 | Original ﬁgures: training and deployment (NVIDIA course) | §9 Appendice: dettagli di supporto | placeholder IS21-V59 | non richiesta |
| 21 | 105 | Original ﬁgures: learned units | §9 Appendice: dettagli di supporto | placeholder IS21-V60 | non richiesta |
| 21 | 106 | A “deeper” discussion: related ﬁelds | §9 Appendice: dettagli di supporto | placeholder IS21-V61 | non richiesta |
| 21 | 107 | Taxonomy of learning models | §9 Appendice: dettagli di supporto | placeholder IS21-V62 | non richiesta |
| 21 | 108 | Word embedding (historical): words converted into numbers | §9 Appendice: dettagli di supporto | placeholder IS21-V63 | non richiesta |
| 21 | 109 | Word2Vec (historical): a neural feature extractor for words | §9 Appendice: dettagli di supporto | placeholder IS21-V64 | non richiesta |
| 22 | 1 | Intelligent Systems for Industry, Supply Chain and Environment | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 22 | 2 | Outline | §0 Panoramica | placeholder IS22-V01 | non richiesta |
| 22 | 3 | Unsupervised deep learning models: Autoencoders | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 22 | 4 | Unsupervised learning | §1 Modelli profondi non supervisionati: gli autoencoder | placeholder IS22-V02 | non richiesta |
| 22 | 5 | Unsupervised learning for classical and deep models | §1 Modelli profondi non supervisionati: gli autoencoder | placeholder IS22-V03 | non richiesta |
| 22 | 6 | Goal: training eﬀectively the hidden layers | §1 Modelli profondi non supervisionati: gli autoencoder | placeholder IS22-V04 | non richiesta |
| 22 | 7 | Autoencoders (AEs): basic technique | §1 Modelli profondi non supervisionati: gli autoencoder | placeholder IS22-V05 | non richiesta |
| 22 | 8 | ˆx = x | §1 Modelli profondi non supervisionati: gli autoencoder | placeholder IS22-V06 | non richiesta |
| 22 | 9 | Autoencoder: input, code and output (with numbers) | §1 Modelli profondi non supervisionati: gli autoencoder | placeholder IS22-V07 | non richiesta |
| 22 | 10 | Applications of AEs: dimensionality reduction | §1 Modelli profondi non supervisionati: gli autoencoder | placeholder IS22-V08 | non richiesta |
| 22 | 11 | z | §1 Modelli profondi non supervisionati: gli autoencoder | placeholder IS22-V09 | non richiesta |
| 22 | 12 | Understanding AEs: the decoder alone | §1 Modelli profondi non supervisionati: gli autoencoder | placeholder IS22-V10 | non richiesta |
| 22 | 13 | Applications of AEs: noise reduction | §1 Modelli profondi non supervisionati: gli autoencoder | placeholder IS22-V11 | non richiesta |
| 22 | 14 | Applications of AEs: auto colorization | §1 Modelli profondi non supervisionati: gli autoencoder | placeholder IS22-V12 | non richiesta |
| 22 | 15 | Autoencoders for anomaly detection (industry) | §1 Modelli profondi non supervisionati: gli autoencoder | placeholder IS22-V13 | non richiesta |
| 22 | 16 | AE anomaly detection: a run on vibration signals | §1 Modelli profondi non supervisionati: gli autoencoder | placeholder IS22-V14 | non richiesta |
| 22 | 17 | Masked Autoencoders (MAE) | §1 Modelli profondi non supervisionati: gli autoencoder | placeholder IS22-V15 | non richiesta |
| 22 | 18 | Training and designing deep learning models | §1 Modelli profondi non supervisionati: gli autoencoder | testo/tabella/codice trascritti | non richiesta |
| 22 | 19 | First of all. . . deep learning design | §2 Addestrare e progettare modelli di deep learning | placeholder IS22-V16 | non richiesta |
| 22 | 20 | Problems while training deep networks | §2 Addestrare e progettare modelli di deep learning | placeholder IS22-V17 | non richiesta |
| 22 | 21 | Deep learning training hints | §2 Addestrare e progettare modelli di deep learning | placeholder IS22-V18 | non richiesta |
| 22 | 22 | Modern recipe: residual connections, end-to-end training | §2 Addestrare e progettare modelli di deep learning | placeholder IS22-V19 | non richiesta |
| 22 | 23 | Batch Normalization (BatchNorm) | §2 Addestrare e progettare modelli di deep learning | placeholder IS22-V20 | non richiesta |
| 22 | 24 | Adaptive Moment Estimation (Adam) | §2 Addestrare e progettare modelli di deep learning | testo/tabella/codice trascritti | non richiesta |
| 22 | 25 | Regularization: early stopping, weight decay, dropout | §2 Addestrare e progettare modelli di deep learning | placeholder IS22-V21 | non richiesta |
| 22 | 26 | Why all layers can now be trained together | §2 Addestrare e progettare modelli di deep learning | testo/tabella/codice trascritti | non richiesta |
| 22 | 27 | Vision networks trained end-to-end: how big? | §2 Addestrare e progettare modelli di deep learning | placeholder IS22-V22 | non richiesta |
| 22 | 28 | Greedy layer-wise training | §2 Addestrare e progettare modelli di deep learning | testo/tabella/codice trascritti | non richiesta |
| 22 | 29 | Unsupervised initialization (basic technique) | §3 Il greedy layer-wise training | placeholder IS22-V23 | non richiesta |
| 22 | 30 | Stacked AEs (basic technique) | §3 Il greedy layer-wise training | placeholder IS22-V24 | non richiesta |
| 22 | 31 | Stacked AEs (basic technique) (2) | §3 Il greedy layer-wise training | placeholder IS22-V25 | non richiesta |
| 22 | 32 | Stacked AEs (basic technique) (3) | §3 Il greedy layer-wise training | placeholder IS22-V26 | non richiesta |
| 22 | 33 | Stacked AEs (basic technique) (4) | §3 Il greedy layer-wise training | placeholder IS22-V27 | non richiesta |
| 22 | 34 | Stacked AEs (basic technique) (5) | §3 Il greedy layer-wise training | placeholder IS22-V28 | non richiesta |
| 22 | 35 | Greedy layer-wise training in four pictures | §3 Il greedy layer-wise training | placeholder IS22-V29 | non richiesta |
| 22 | 36 | Greedy layer-wise: summary (basic technique) | §3 Il greedy layer-wise training | testo/tabella/codice trascritti | non richiesta |
| 22 | 37 | Greedy layer-wise: why it helps (basic technique) | §3 Il greedy layer-wise training | placeholder IS22-V30 | non richiesta |
| 22 | 38 | Is greedy layer-wise training still valid today? | §3 Il greedy layer-wise training | placeholder IS22-V31 | non richiesta |
| 22 | 39 | Transfer learning and ﬁne-tuning in deep models | §3 Il greedy layer-wise training | testo/tabella/codice trascritti | non richiesta |
| 22 | 40 | Transfer learning to avoid overﬁtting and improve learning | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V32 | non richiesta |
| 22 | 41 | Transfer learning to avoid overﬁtting and improve learning (2) | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V33 | non richiesta |
| 22 | 42 | Pretrained models are available online | §4 Transfer learning e fine-tuning nei modelli profondi | testo/tabella/codice trascritti | non richiesta |
| 22 | 43 | Fine tuning: starting from a public CNN | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V34 | non richiesta |
| 22 | 44 | Fine-tuning is very eﬀective! | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V35 | non richiesta |
| 22 | 45 | Transfer learning: from pretrained CNN to your task | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V36 | non richiesta |
| 22 | 46 | Fine-tuning the CNN: three options | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V37 | non richiesta |
| 22 | 47 | Parameter-eﬃcient ﬁne-tuning: Low-Rank Adaptation (LoRA) | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V38 | non richiesta |
| 22 | 48 | Automatic feature extraction with deep learning models | §4 Transfer learning e fine-tuning nei modelli profondi | testo/tabella/codice trascritti | non richiesta |
| 22 | 49 | CNNs as feature extractors (basic technique) | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V39 | non richiesta |
| 22 | 50 | ConvNets as feature extractors (2) | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V40 | non richiesta |
| 22 | 51 | ConvNets as feature extractors: one embedding, many uses | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V41 | non richiesta |
| 22 | 52 | State of the art of automatic feature extraction | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V42 | non richiesta |
| 22 | 53 | Catastrophic forgetting | §4 Transfer learning e fine-tuning nei modelli profondi | testo/tabella/codice trascritti | non richiesta |
| 22 | 54 | Beware! Catastrophic forgetting/inference | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V43 | non richiesta |
| 22 | 55 | Beware! Catastrophic forgetting | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V44 | non richiesta |
| 22 | 56 | Catastrophic forgetting (by testset accuracy) | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V45 | non richiesta |
| 22 | 57 | Forgetting, measured, and rehearsal (by testset accuracy) | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V46 | non richiesta |
| 22 | 58 | Catastrophic forgetting (by decision boundaries) | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V47 | non richiesta |
| 22 | 59 | Mitigation of catastrophic forgetting | §4 Transfer learning e fine-tuning nei modelli profondi | testo/tabella/codice trascritti | non richiesta |
| 22 | 60 | Mitigation of catastrophic forgetting (2) | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V48 | non richiesta |
| 22 | 61 | Knowledge distillation: soft labels from a teacher | §4 Transfer learning e fine-tuning nei modelli profondi | placeholder IS22-V49 | non richiesta |
| 22 | 62 | Data augmentation | §4 Transfer learning e fine-tuning nei modelli profondi | testo/tabella/codice trascritti | non richiesta |
| 22 | 63 | Classical ML vs deep learning | §5 Data augmentation | placeholder IS22-V50 | non richiesta |
| 22 | 64 | Data augmentation | §5 Data augmentation | placeholder IS22-V51 | non richiesta |
| 22 | 65 | Classic augmentations in one picture | §5 Data augmentation | placeholder IS22-V52 | non richiesta |
| 22 | 66 | Augmentation #1: rotations, translations, scales | §5 Data augmentation | placeholder IS22-V53 | non richiesta |
| 22 | 67 | Data augmentation #2: with noise/noises | §5 Data augmentation | placeholder IS22-V54 | non richiesta |
| 22 | 68 | Data augmentation #3: with diﬀerent (simulated) conditions | §5 Data augmentation | placeholder IS22-V55 | non richiesta |
| 22 | 69 | Data augmentation: state of the art | §5 Data augmentation | placeholder IS22-V56 | non richiesta |
| 22 | 70 | and | §5 Data augmentation | placeholder IS22-V57 | non richiesta |
| 22 | 71 | Random policies: RandAugment, TrivialAugment, AugMix | §5 Data augmentation | placeholder IS22-V58 | non richiesta |
| 22 | 72 | Copy-Paste: rare defects on many good parts | §5 Data augmentation | placeholder IS22-V59 | non richiesta |
| 22 | 73 | Caveat! Augmentation must preserve the label | §5 Data augmentation | placeholder IS22-V60 | non richiesta |
| 22 | 74 | Generative augmentation with diﬀusion models | §5 Data augmentation | placeholder IS22-V61 | non richiesta |
| 22 | 75 | Deep learning + data augmentation | §5 Data augmentation | placeholder IS22-V62 | non richiesta |
| 22 | 76 | Augmentation without labels: contrastive self-supervised | §5 Data augmentation | placeholder IS22-V63 | non richiesta |
| 22 | 77 | Contrastive pretraining in IS4: two examples | §5 Data augmentation | placeholder IS22-V64 | non richiesta |
| 22 | 78 | Generative Adversarial Networks (GAN) | §5 Data augmentation | testo/tabella/codice trascritti | non richiesta |
| 22 | 79 | GAN: generator and discriminator | §6 Le reti generative avversarie (GAN) | placeholder IS22-V65 | non richiesta |
| 22 | 80 | Real or created by a GAN? | §6 Le reti generative avversarie (GAN) | placeholder IS22-V66 | non richiesta |
| 22 | 81 | Answer: it is fake (GAN-generated) | §6 Le reti generative avversarie (GAN) | placeholder IS22-V67 | non richiesta |
| 22 | 82 | GANs create realistic industrial data | §6 Le reti generative avversarie (GAN) | placeholder IS22-V68 | non richiesta |
| 22 | 83 | Style transfer by CNN (GAN) in industry | §6 Le reti generative avversarie (GAN) | placeholder IS22-V69 | non richiesta |
| 22 | 84 | GANs and operating conditions: weather and light | §6 Le reti generative avversarie (GAN) | placeholder IS22-V70 | non richiesta |
| 22 | 85 | Style transfer by CNN (GAN): how it is built | §6 Le reti generative avversarie (GAN) | placeholder IS22-V71 | non richiesta |
| 22 | 86 | What GANs are used for | §6 Le reti generative avversarie (GAN) | placeholder IS22-V72 | non richiesta |
| 22 | 87 | Diﬀusion models (denoise) | §6 Le reti generative avversarie (GAN) | placeholder IS22-V73 | non richiesta |
| 22 | 88 | Diﬀusion: add noise, learn to remove it | §6 Le reti generative avversarie (GAN) | placeholder IS22-V74 | non richiesta |
| 22 | 89 | Diﬀusion models (denoise): conditioning with text | §6 Le reti generative avversarie (GAN) | placeholder IS22-V75 | non richiesta |
| 22 | 90 | Sony World Photography Awards 2023 | §6 Le reti generative avversarie (GAN) | placeholder IS22-V76 | non richiesta |
| 22 | 91 | The MAD eﬀect: model autophagy disorder | §6 Le reti generative avversarie (GAN) | placeholder IS22-V77 | non richiesta |
| 22 | 92 | Synthetic content is ﬁlling the web | §6 Le reti generative avversarie (GAN) | placeholder IS22-V78 | non richiesta |
| 22 | 93 | Reduction of the model autophagy disorder | §6 Le reti generative avversarie (GAN) | placeholder IS22-V79 | non richiesta |
| 22 | 94 | Avoiding model collapse: replace or accumulate? | §6 Le reti generative avversarie (GAN) | placeholder IS22-V80 | non richiesta |
| 22 | 95 | Current boundaries in ML | §6 Le reti generative avversarie (GAN) | testo/tabella/codice trascritti | non richiesta |
| 22 | 96 | Scaling laws: more compute, data, parameters | §7 I confini attuali del ML | placeholder IS22-V81 | non richiesta |
| 22 | 97 | LLM and ML design | §7 I confini attuali del ML | testo/tabella/codice trascritti | non richiesta |
| 22 | 98 | Enemy or ally? | §8 LLM e progettazione del machine learning | placeholder IS22-V82 | non richiesta |
| 22 | 99 | ChatGPT can leverage your work | §8 LLM e progettazione del machine learning | testo/tabella/codice trascritti | non richiesta |
| 22 | 100 | Design ML with agents | §8 LLM e progettazione del machine learning | placeholder IS22-V83 | non richiesta |
| 22 | 101 | Agents and the “gold rush” | §8 LLM e progettazione del machine learning | placeholder IS22-V84 | non richiesta |
| 22 | 102 | Main points | §8 LLM e progettazione del machine learning | testo/tabella/codice trascritti | non richiesta |
| 23 | 1 | Intelligent Systems for Industry, Supply Chain and Environment | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 23 | 2 | Outline | §0 Panoramica | placeholder IS23-V01 | non richiesta |
| 23 | 3 | Theory: image classiﬁcation | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 23 | 4 | Image classiﬁcation | §1 Teoria: la classificazione delle immagini | placeholder IS23-V02 | non richiesta |
| 23 | 5 | Intelligent Vision tasks | §1 Teoria: la classificazione delle immagini | placeholder IS23-V03 | non richiesta |
| 23 | 6 | A quick test 1 . . . | §1 Teoria: la classificazione delle immagini | testo/tabella/codice trascritti | non richiesta |
| 23 | 7 | A quick test 1 | §1 Teoria: la classificazione delle immagini | placeholder IS23-V04 | non richiesta |
| 23 | 8 | A quick test 2 . . . | §1 Teoria: la classificazione delle immagini | testo/tabella/codice trascritti | non richiesta |
| 23 | 9 | A quick test 2 . . . | §1 Teoria: la classificazione delle immagini | placeholder IS23-V05 | non richiesta |
| 23 | 10 | A quick test 3 . . . | §1 Teoria: la classificazione delle immagini | testo/tabella/codice trascritti | non richiesta |
| 23 | 11 | A quick test 3 . . . | §1 Teoria: la classificazione delle immagini | placeholder IS23-V06 | non richiesta |
| 23 | 12 | A quick test 4 . . . | §1 Teoria: la classificazione delle immagini | testo/tabella/codice trascritti | non richiesta |
| 23 | 13 | A quick test 4 . . . | §1 Teoria: la classificazione delle immagini | placeholder IS23-V07 | non richiesta |
| 23 | 14 | The task is deﬁned by its output | §1 Teoria: la classificazione delle immagini | testo/tabella/codice trascritti | non richiesta |
| 23 | 15 | Direct image classiﬁcation | §1 Teoria: la classificazione delle immagini | placeholder IS23-V08 | non richiesta |
| 23 | 16 | Direct image classiﬁcation: example with AlexNet | §1 Teoria: la classificazione delle immagini | placeholder IS23-V09 | non richiesta |
| 23 | 17 | How do we train a classiﬁer? | §1 Teoria: la classificazione delle immagini | placeholder IS23-V10 | non richiesta |
| 23 | 18 | Image classiﬁcation: ImageNet — hard set | §1 Teoria: la classificazione delle immagini | placeholder IS23-V11 | non richiesta |
| 23 | 19 | Theory: object detection | §1 Teoria: la classificazione delle immagini | testo/tabella/codice trascritti | non richiesta |
| 23 | 20 | Complex object detection | §2 Teoria: il rilevamento di oggetti | placeholder IS23-V12 | non richiesta |
| 23 | 21 | Object detection: bounding box | §2 Teoria: il rilevamento di oggetti | placeholder IS23-V13 | non richiesta |
| 23 | 22 | Detection is not segmentation! | §2 Teoria: il rilevamento di oggetti | placeholder IS23-V14 | non richiesta |
| 23 | 23 | Detection example: person detection in images | §2 Teoria: il rilevamento di oggetti | placeholder IS23-V15 | non richiesta |
| 23 | 24 | Articulated body pose estimation: OpenPose | §2 Teoria: il rilevamento di oggetti | placeholder IS23-V16 | non richiesta |
| 23 | 25 | Object classiﬁcation/detection: classical ML vs deep learning | §2 Teoria: il rilevamento di oggetti | placeholder IS23-V17 | non richiesta |
| 23 | 26 | A third option: hybrid solutions | §2 Teoria: il rilevamento di oggetti | placeholder IS23-V18 | non richiesta |
| 23 | 27 | Object measurement: a three step process | §2 Teoria: il rilevamento di oggetti | placeholder IS23-V19 | non richiesta |
| 23 | 28 | Object measurements in industrial applications | §2 Teoria: il rilevamento di oggetti | placeholder IS23-V20 | non richiesta |
| 23 | 29 | Object segmentation and measurement: bin picking | §2 Teoria: il rilevamento di oggetti | placeholder IS23-V21 | non richiesta |
| 23 | 30 | Theory: object segmentation | §2 Teoria: il rilevamento di oggetti | testo/tabella/codice trascritti | non richiesta |
| 23 | 31 | General segmentations | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V22 | non richiesta |
| 23 | 32 | Ex. in environmental applications: drones | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V23 | non richiesta |
| 23 | 33 | Ex. in environmental applications: satellites and airborne | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V24 | non richiesta |
| 23 | 34 | Ex. autonomous vehicles: semantic segmentation | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V25 | non richiesta |
| 23 | 35 | Autonomous vehicle: from the image to the brake | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V26 | non richiesta |
| 23 | 36 | Image segmentation applications: examples | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V27 | non richiesta |
| 23 | 37 | More application examples of image segmentation | §3 Teoria: la segmentazione degli oggetti | testo/tabella/codice trascritti | non richiesta |
| 23 | 38 | Segmentation “deﬁnition” | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V28 | non richiesta |
| 23 | 39 | Two close tasks | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V29 | non richiesta |
| 23 | 40 | Basic taxonomy | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V30 | non richiesta |
| 23 | 41 | Classical methods and other methods (for your reference) | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V31 | non richiesta |
| 23 | 42 | Image segmentation via k-means clustering (unsupervised) | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V32 | non richiesta |
| 23 | 43 | Recent improvements: Segment Anything Model (SAM) | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V33 | non richiesta |
| 23 | 44 | SAM: promptable segmentation | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V34 | non richiesta |
| 23 | 45 | SAM: promptable segmentation (architecture) | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V35 | non richiesta |
| 23 | 46 | SAM = pretrained backbone + light head | §3 Teoria: la segmentazione degli oggetti | testo/tabella/codice trascritti | non richiesta |
| 23 | 47 | SAM in Colab with metaseg | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V36 | non richiesta |
| 23 | 48 | SAM in Colab with metaseg | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V37 | non richiesta |
| 23 | 49 | SAM2 (video and images) | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V38 | non richiesta |
| 23 | 50 | State of the art: SAM 3, segment anything with concepts | §3 Teoria: la segmentazione degli oggetti | placeholder IS23-V39 | non richiesta |
| 23 | 51 | Theory: semantic segmentation | §3 Teoria: la segmentazione degli oggetti | testo/tabella/codice trascritti | non richiesta |
| 23 | 52 | Semantic segmentation (recall) | §4 Teoria: la segmentazione semantica | placeholder IS23-V40 | non richiesta |
| 23 | 53 | Pixel classiﬁcation is a good idea but. . . not feasible | §4 Teoria: la segmentazione semantica | placeholder IS23-V41 | non richiesta |
| 23 | 54 | Semantic segmentation: a fully convolutional solution | §4 Teoria: la segmentazione semantica | placeholder IS23-V42 | non richiesta |
| 23 | 55 | Upscaling: nn.ConvTranspose2d() — what it computes | §4 Teoria: la segmentazione semantica | placeholder IS23-V43 | non richiesta |
| 23 | 56 | nn.ConvTranspose2d(): the stride sets the output size | §4 Teoria: la segmentazione semantica | placeholder IS23-V44 | non richiesta |
| 23 | 57 | Theory: ResNet as a general backbone | §4 Teoria: la segmentazione semantica | testo/tabella/codice trascritti | non richiesta |
| 23 | 58 | ResNet-50 for classiﬁcation | §5 Teoria: ResNet come backbone generale | placeholder IS23-V45 | non richiesta |
| 23 | 59 | ResNet-50: where the residual connections are | §5 Teoria: ResNet come backbone generale | placeholder IS23-V46 | non richiesta |
| 23 | 60 | ResNet-50 for feature extraction | §5 Teoria: ResNet come backbone generale | placeholder IS23-V47 | non richiesta |
| 23 | 61 | ResNet-50 →ResNet-50 + upscaling: segmentation | §5 Teoria: ResNet come backbone generale | placeholder IS23-V48 | non richiesta |
| 23 | 62 | Image classiﬁcation via DL | §5 Teoria: ResNet come backbone generale | testo/tabella/codice trascritti | non richiesta |
| 23 | 63 | Use case: objects recognition from UAVs | §5 Teoria: ResNet come backbone generale | testo/tabella/codice trascritti | non richiesta |
| 23 | 64 | Use cases: UAVs/drones | §6 Caso d’uso: il riconoscimento di oggetti da UAV/droni | placeholder IS23-V49 | non richiesta |
| 23 | 65 | Drones/UAVs in applications | §6 Caso d’uso: il riconoscimento di oggetti da UAV/droni | placeholder IS23-V50 | non richiesta |
| 23 | 66 | Amazon Prime Air | §6 Caso d’uso: il riconoscimento di oggetti da UAV/droni | placeholder IS23-V51 | non richiesta |
| 23 | 67 | Object recognition from UAVs: three examples | §6 Caso d’uso: il riconoscimento di oggetti da UAV/droni | placeholder IS23-V52 | non richiesta |
| 23 | 68 | Objects recognition from UAVs | §6 Caso d’uso: il riconoscimento di oggetti da UAV/droni | placeholder IS23-V53 | non richiesta |
| 23 | 69 | Obj. rec. from UAVs: step 1 — segmentation | §6 Caso d’uso: il riconoscimento di oggetti da UAV/droni | placeholder IS23-V54 | non richiesta |
| 23 | 70 | Obj. rec. from UAVs: step 2 — CNN design | §6 Caso d’uso: il riconoscimento di oggetti da UAV/droni | placeholder IS23-V55 | non richiesta |
| 23 | 71 | Obj. rec. from UAVs: CNN learning dataset creation | §6 Caso d’uso: il riconoscimento di oggetti da UAV/droni | placeholder IS23-V56 | non richiesta |
| 23 | 72 | Obj. rec. from UAVs: results | §6 Caso d’uso: il riconoscimento di oggetti da UAV/droni | placeholder IS23-V57 | non richiesta |
| 23 | 73 | Theory: machine learning hardware | §6 Caso d’uso: il riconoscimento di oggetti da UAV/droni | testo/tabella/codice trascritti | non richiesta |
| 23 | 74 | Why Machine Learning Is Possible? | §7 Teoria: l’hardware per il machine learning | placeholder IS23-V58 | non richiesta |
| 23 | 75 | HW: when and where is more relevant? | §7 Teoria: l’hardware per il machine learning | placeholder IS23-V59 | non richiesta |
| 23 | 76 | CPU, GPU, TPU, NPU, VPU | §7 Teoria: l’hardware per il machine learning | placeholder IS23-V60 | non richiesta |
| 23 | 77 | CPU, GPU | §7 Teoria: l’hardware per il machine learning | placeholder IS23-V61 | non richiesta |
| 23 | 78 | TPU, NPU | §7 Teoria: l’hardware per il machine learning | placeholder IS23-V62 | non richiesta |
| 23 | 79 | VPU | §7 Teoria: l’hardware per il machine learning | placeholder IS23-V63 | non richiesta |
| 23 | 80 | GPU vs CPU | §7 Teoria: l’hardware per il machine learning | testo/tabella/codice trascritti | non richiesta |
| 23 | 81 | Some other data: CPU vs GPU speed-up | §7 Teoria: l’hardware per il machine learning | placeholder IS23-V64 | non richiesta |
| 23 | 82 | ML server farm. . . | §7 Teoria: l’hardware per il machine learning | placeholder IS23-V65 | non richiesta |
| 23 | 83 | GPUs: not always needed | §7 Teoria: l’hardware per il machine learning | placeholder IS23-V66 | non richiesta |
| 23 | 84 | ML@CPUs | §7 Teoria: l’hardware per il machine learning | testo/tabella/codice trascritti | non richiesta |
| 23 | 85 | Where does inference run? Cloud or local | §7 Teoria: l’hardware per il machine learning | placeholder IS23-V67 | non richiesta |
| 23 | 86 | Uniﬁed memory vs discrete GPU | §7 Teoria: l’hardware per il machine learning | placeholder IS23-V68 | non richiesta |
| 23 | 87 | Memory and quantization: fewer bits per weight | §7 Teoria: l’hardware per il machine learning | testo/tabella/codice trascritti | non richiesta |
| 23 | 88 | Tools for local models (outlook, not in the exam) | §7 Teoria: l’hardware per il machine learning | placeholder IS23-V69 | non richiesta |
| 23 | 89 | Moving ML to the “edge” | §7 Teoria: l’hardware per il machine learning | placeholder IS23-V70 | non richiesta |
| 23 | 90 | Example: ML on an ARM processor | §7 Teoria: l’hardware per il machine learning | testo/tabella/codice trascritti | non richiesta |
| 23 | 91 | HW for ML: example, the Arm family | §7 Teoria: l’hardware per il machine learning | placeholder IS23-V71 | non richiesta |
| 23 | 92 | Google TPU: from the ﬁrst chip to Ironwood | §7 Teoria: l’hardware per il machine learning | placeholder IS23-V72 | non richiesta |
| 23 | 93 | Moving ML to the “edge” (2) | §7 Teoria: l’hardware per il machine learning | placeholder IS23-V73 | non richiesta |
| 23 | 94 | Making a network ﬁt the edge: three tools | §7 Teoria: l’hardware per il machine learning | testo/tabella/codice trascritti | non richiesta |
| 23 | 95 | From a trained model to an edge engine | §7 Teoria: l’hardware per il machine learning | placeholder IS23-V74 | non richiesta |
| 23 | 96 | Measuring edge inference: latency, throughput, energy | §8 Misurare e rilasciare | placeholder IS23-V75 | non richiesta |
| 23 | 97 | Movidius Neural Compute Stick from Intel (historical example) | §8 Misurare e rilasciare | placeholder IS23-V76 | non richiesta |
| 23 | 98 | The MATLAB environment (HW + SW) | §8 Misurare e rilasciare | placeholder IS23-V77 | non richiesta |
| 23 | 99 | Tools: OpenVINO | §8 Misurare e rilasciare | testo/tabella/codice trascritti | non richiesta |
| 23 | 100 | OpenVINO: intro | §9 Strumenti: OpenVINO | placeholder IS23-V78 | non richiesta |
| 23 | 101 | OpenVINO: supported devices | §9 Strumenti: OpenVINO | placeholder IS23-V79 | non richiesta |
| 23 | 102 | A typical workﬂow with OpenVINO | §9 Strumenti: OpenVINO | testo/tabella/codice trascritti | non richiesta |
| 23 | 103 | OpenVINO today: runtime and tools | §9 Strumenti: OpenVINO | testo/tabella/codice trascritti | non richiesta |
| 23 | 104 | Tools: Edge Impulse | §9 Strumenti: OpenVINO | testo/tabella/codice trascritti | non richiesta |
| 23 | 105 | Edge Impulse | §10 Strumenti: Edge Impulse (non d’esame) | placeholder IS23-V80 | non richiesta |
| 23 | 106 | Edge Impulse: the platform home page (not in the exam) | §10 Strumenti: Edge Impulse (non d’esame) | placeholder IS23-V81 | non richiesta |
| 23 | 107 | Edge Impulse: from your tools to the device (not in the exam) | §10 Strumenti: Edge Impulse (non d’esame) | placeholder IS23-V82 | non richiesta |
| 23 | 108 | Main points | §10 Strumenti: Edge Impulse (non d’esame) | testo/tabella/codice trascritti | non richiesta |
| 23 | 109 | Appendix | §10 Strumenti: Edge Impulse (non d’esame) | testo/tabella/codice trascritti | non richiesta |
| 23 | 110 | Read the MATLAB short ebook on Deep Learning (not in the | §12 Appendice (non d’esame) | placeholder IS23-V83 | non richiesta |
| 23 | 111 | In case you might be interested. . . (not in the exam) | §12 Appendice (non d’esame) | placeholder IS23-V84 | non richiesta |
| 23 | 112 | Edge Impulse platform (not in the exam) | §12 Appendice (non d’esame) | placeholder IS23-V85 | non richiesta |
| 24 | 1 | Intelligent Systems for Industry, Supply Chain and Environment | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 24 | 2 | Outline | §0 Panoramica | placeholder IS24-V01 | non richiesta |
| 24 | 3 | Theory: Vision Transformer | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 24 | 4 | Two ways to read the same image: CNN and ViT | §1 Teoria: il Vision Transformer (ViT) | placeholder IS24-V02 | non richiesta |
| 24 | 5 | Vision Transformer (1/4): the image becomes patches | §1 Teoria: il Vision Transformer (ViT) | placeholder IS24-V03 | non richiesta |
| 24 | 6 | Vision Transformer (2/4): patches become tokens | §1 Teoria: il Vision Transformer (ViT) | testo/tabella/codice trascritti | non richiesta |
| 24 | 7 | Patch embedding: where its matrices come from | §1 Teoria: il Vision Transformer (ViT) | placeholder IS24-V04 | non richiesta |
| 24 | 8 | Vision Transformer (3/4): the transformer encoder | §1 Teoria: il Vision Transformer (ViT) | testo/tabella/codice trascritti | non richiesta |
| 24 | 9 | Vision Transformer (4/4): from tokens to the answer | §1 Teoria: il Vision Transformer (ViT) | testo/tabella/codice trascritti | non richiesta |
| 24 | 10 | CNN or ViT? Inductive bias, data and cost | §1 Teoria: il Vision Transformer (ViT) | testo/tabella/codice trascritti | non richiesta |
| 24 | 11 | ViT as a feature extractor: taking its vector | §1 Teoria: il Vision Transformer (ViT) | placeholder IS24-V05 | non richiesta |
| 24 | 12 | Using a pretrained ViT: a new image and its vector z | §1 Teoria: il Vision Transformer (ViT) | testo/tabella/codice trascritti | non richiesta |
| 24 | 13 | Image classiﬁcation today: best CNNs and ViTs | §1 Teoria: il Vision Transformer (ViT) | testo/tabella/codice trascritti | non richiesta |
| 24 | 14 | Theory: AIoT | §1 Teoria: il Vision Transformer (ViT) | testo/tabella/codice trascritti | non richiesta |
| 24 | 15 | Artiﬁcial Intelligence of Things (AIoT) | §2 Teoria: l’AIoT (Artificial Intelligence of Things) | placeholder IS24-V06 | non richiesta |
| 24 | 16 | What enables AIoT: one stack, HW and SW | §2 Teoria: l’AIoT (Artificial Intelligence of Things) | placeholder IS24-V07 | non richiesta |
| 24 | 17 | Example of AIoT: smart speakers | §2 Teoria: l’AIoT (Artificial Intelligence of Things) | placeholder IS24-V08 | non richiesta |
| 24 | 18 | Example of AIoT: intelligent and connected lighting | §2 Teoria: l’AIoT (Artificial Intelligence of Things) | placeholder IS24-V09 | non richiesta |
| 24 | 19 | Theory: Security of IoT | §2 Teoria: l’AIoT (Artificial Intelligence of Things) | testo/tabella/codice trascritti | non richiesta |
| 24 | 20 | Security of IoT | §3 Teoria: la sicurezza dell’IoT | placeholder IS24-V10 | non richiesta |
| 24 | 21 | Six principles of IoT security across the stack | §3 Teoria: la sicurezza dell’IoT | placeholder IS24-V11 | non richiesta |
| 24 | 22 | OTA: secure over-the-air updates | §3 Teoria: la sicurezza dell’IoT | placeholder IS24-V12 | non richiesta |
| 24 | 23 | IoT security regulation: what a designer must know | §3 Teoria: la sicurezza dell’IoT | placeholder IS24-V13 | non richiesta |
| 24 | 24 | Toolboxes: IoT platforms | §3 Teoria: la sicurezza dell’IoT | testo/tabella/codice trascritti | non richiesta |
| 24 | 25 | AWS IoT: the basic idea | §4 Strumenti: piattaforme IoT e sistemi operativi in tempo reale | placeholder IS24-V15 | non richiesta |
| 24 | 26 | FreeRTOS for embedded AIoT devices | §4 Strumenti: piattaforme IoT e sistemi operativi in tempo reale | placeholder IS24-V16 | non richiesta |
| 24 | 27 | How an RTOS meets a deadline | §4 Strumenti: piattaforme IoT e sistemi operativi in tempo reale | placeholder IS24-V17 | non richiesta |
| 24 | 28 | Design steps for RTOS | §4 Strumenti: piattaforme IoT e sistemi operativi in tempo reale | placeholder IS24-V18 | non richiesta |
| 24 | 29 | AWS IoT Greengrass: from a lamp to an app | §4 Strumenti: piattaforme IoT e sistemi operativi in tempo reale | placeholder IS24-V19 | non richiesta |
| 24 | 30 | MATLAB ThingSpeak: online and oﬄine | §4 Strumenti: piattaforme IoT e sistemi operativi in tempo reale | placeholder IS24-V20 | non richiesta |
| 24 | 31 | Toolboxes: Boards and accelerators | §4 Strumenti: piattaforme IoT e sistemi operativi in tempo reale | testo/tabella/codice trascritti | non richiesta |
| 24 | 32 | Inside an edge AI board | §5 Strumenti: schede e acceleratori per l’edge AI | placeholder IS24-V24 | non richiesta |
| 24 | 33 | Edge AI hardware today: comparing with one number | §5 Strumenti: schede e acceleratori per l’edge AI | testo/tabella/codice trascritti | non richiesta |
| 24 | 34 | Toolboxes: TinyML | §5 Strumenti: schede e acceleratori per l’edge AI | testo/tabella/codice trascritti | non richiesta |
| 24 | 35 | Tiny ML | §6 Strumenti: TinyML | placeholder IS24-V25 | non richiesta |
| 24 | 36 | Tiny ML: examples and applications | §6 Strumenti: TinyML | placeholder IS24-V26 | non richiesta |
| 24 | 37 | Traditional vs TinyML: orders of magnitude | §6 Strumenti: TinyML | testo/tabella/codice trascritti | non richiesta |
| 24 | 38 | LiteRT (formerly TensorFlow Lite) | §6 Strumenti: TinyML | placeholder IS24-V27 | non richiesta |
| 24 | 39 | Toolboxes: Cloud services for ML | §6 Strumenti: TinyML | testo/tabella/codice trascritti | non richiesta |
| 24 | 40 | Machine Learning “as a Service” (MLaaS) | §6 Strumenti: TinyML | testo/tabella/codice trascritti | non richiesta |
| 24 | 41 | Cloud service models: IaaS, PaaS, SaaS | §7 Strumenti: i servizi cloud per il ML | placeholder IS24-V28 | non richiesta |
| 24 | 42 | Cloud ML services today (November 2026) | §7 Strumenti: i servizi cloud per il ML | testo/tabella/codice trascritti | non richiesta |
| 24 | 43 | Overview: Applications | §7 Strumenti: i servizi cloud per il ML | testo/tabella/codice trascritti | non richiesta |
| 24 | 44 | Six examples of Intelligent Vision Systems | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V30 | non richiesta |
| 24 | 45 | Intelligent systems for robotics | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V31 | non richiesta |
| 24 | 46 | Methods and applications: Control | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V32 | non richiesta |
| 24 | 47 | Vision systems for production inspection (smart factory) | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V33 | non richiesta |
| 24 | 48 | Intelligent vision systems for surveillance and security | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V34 | non richiesta |
| 24 | 49 | Five more application areas | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V35 | non richiesta |
| 24 | 50 | AIoT in logistics and general apps | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V41 | non richiesta |
| 24 | 51 | Logistics: vision in operations and drone inventory | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V42 | non richiesta |
| 24 | 52 | Vehicle to everything (V2X): four ideas | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V43 | non richiesta |
| 24 | 53 | Intelligent transportation systems (ITS) | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V48 | non richiesta |
| 24 | 54 | Volvo Slippery Road Alert: a virtual sensor in the cloud | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V49 | non richiesta |
| 24 | 55 | Pirelli Cyber Tyre: from a small sensor to the smart city | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V50 | non richiesta |
| 24 | 56 | Pirelli Cyber Car: sensor, edge, fog and cloud | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V51 | non richiesta |
| 24 | 57 | Overview: Industrial Intelligent Vision Systems | §8 Panoramica: le applicazioni dei sistemi intelligenti | testo/tabella/codice trascritti | non richiesta |
| 24 | 58 | Intelligent vision systems for Industry 4.0 | §9 Panoramica: i sistemi di visione intelligenti industriali | placeholder IS24-V53 | non richiesta |
| 24 | 59 | History. . . today | §9 Panoramica: i sistemi di visione intelligenti industriali | placeholder IS24-V54 | non richiesta |
| 24 | 60 | Industrial vision systems: sensors and smart cameras | §9 Panoramica: i sistemi di visione intelligenti industriali | placeholder IS24-V55 | non richiesta |
| 24 | 61 | Industrial vision along the production chain | §9 Panoramica: i sistemi di visione intelligenti industriali | placeholder IS24-V56 | non richiesta |
| 24 | 62 | The basic setup | §9 Panoramica: i sistemi di visione intelligenti industriali | placeholder IS24-V57 | non richiesta |
| 24 | 63 | Industrial vision in the automation network | §9 Panoramica: i sistemi di visione intelligenti industriali | placeholder IS24-V58 | non richiesta |
| 24 | 64 | Industrial vision example: relay contact measurement | §9 Panoramica: i sistemi di visione intelligenti industriali | placeholder IS24-V59 | non richiesta |
| 24 | 65 | From complete systems to small budgets | §9 Panoramica: i sistemi di visione intelligenti industriali | placeholder IS24-V60 | non richiesta |
| 24 | 66 | Anomaly detection | §9 Panoramica: i sistemi di visione intelligenti industriali | placeholder IS24-V61 | non richiesta |
| 24 | 67 | Overview: from Industry 4.0 to 5.0 | §9 Panoramica: i sistemi di visione intelligenti industriali | testo/tabella/codice trascritti | non richiesta |
| 24 | 68 | Industry 4.0 in brief | §10 Panoramica: dall’Industria 4.0 alla 5.0 | placeholder IS24-V62 | non richiesta |
| 24 | 69 | The I4.0 environment and its digital technologies | §10 Panoramica: dall’Industria 4.0 alla 5.0 | placeholder IS24-V63 | non richiesta |
| 24 | 70 | From Industry 4.0 to Industry 5.0: a bridge | §10 Panoramica: dall’Industria 4.0 alla 5.0 | placeholder IS24-V65 | non richiesta |
| 24 | 71 | Industry 5.0 | §10 Panoramica: dall’Industria 4.0 alla 5.0 | placeholder IS24-V66 | non richiesta |
| 24 | 72 | Industry 5.0 in practice: a project you can propose | §10 Panoramica: dall’Industria 4.0 alla 5.0 | placeholder IS24-V68 | non richiesta |
| 24 | 73 | Business intelligence: from the board room to the shop ﬂoor | §10 Panoramica: dall’Industria 4.0 alla 5.0 | placeholder IS24-V69 | non richiesta |
| 24 | 74 | The analytics ladder: from describing to making it happen | §10 Panoramica: dall’Industria 4.0 alla 5.0 | placeholder IS24-V70 | non richiesta |
| 24 | 75 | Big value can start from a small sensor | §10 Panoramica: dall’Industria 4.0 alla 5.0 | placeholder IS24-V71 | non richiesta |
| 24 | 76 | Main points | §10 Panoramica: dall’Industria 4.0 alla 5.0 | testo/tabella/codice trascritti | non richiesta |
| 24 | 77 | Appendix | §10 Panoramica: dall’Industria 4.0 alla 5.0 | testo/tabella/codice trascritti | non richiesta |
| 24 | 78 | Appendix: ViT exercise | §10 Panoramica: dall’Industria 4.0 alla 5.0 | testo/tabella/codice trascritti | non richiesta |
| 24 | 79 | Exercise: VGG16 and ViT on the same course image | §10 Panoramica: dall’Industria 4.0 alla 5.0 | testo/tabella/codice trascritti | non richiesta |
| 24 | 80 | Exercise: the code (PyTorch, torchvision) | §10 Panoramica: dall’Industria 4.0 alla 5.0 | testo/tabella/codice trascritti | non richiesta |
| 24 | 81 | Exercise: results | §10 Panoramica: dall’Industria 4.0 alla 5.0 | testo/tabella/codice trascritti | non richiesta |
| 24 | 82 | Image classiﬁcation today: best CNNs and ViTs | §10 Panoramica: dall’Industria 4.0 alla 5.0 | testo/tabella/codice trascritti | non richiesta |
| 24 | 83 | AIoT HW: what enables the AI? | §10 Panoramica: dall’Industria 4.0 alla 5.0 | testo/tabella/codice trascritti | non richiesta |
| 24 | 84 | AIoT SW: what enables the AI? | §10 Panoramica: dall’Industria 4.0 alla 5.0 | testo/tabella/codice trascritti | non richiesta |
| 24 | 85 | Six principles of IoT security across the stack | §10 Panoramica: dall’Industria 4.0 alla 5.0 | testo/tabella/codice trascritti | non richiesta |
| 24 | 86 | IoT security standards: Cyber Resilience Act (EU) | §10 Panoramica: dall’Industria 4.0 alla 5.0 | testo/tabella/codice trascritti | non richiesta |
| 24 | 87 | IoT security standards: Cyber Resilience Act (EU) | §10 Panoramica: dall’Industria 4.0 alla 5.0 | testo/tabella/codice trascritti | non richiesta |
| 24 | 88 | IoT security standards: NIST CSF 2.0 (USA) | §3 Teoria: la sicurezza dell’IoT | placeholder IS24-V14 | non richiesta |
| 24 | 89 | Some IoT devices | §13 Appendice: hardware, software, schede e altri dettagli (non d’esame) | placeholder IS24-V72 | non richiesta |
| 24 | 90 | Raspberry Pi AI HAT+: Hailo accelerators | §13 Appendice: hardware, software, schede e altri dettagli (non d’esame) | placeholder IS24-V73 | non richiesta |
| 24 | 91 | Google Coral | §13 Appendice: hardware, software, schede e altri dettagli (non d’esame) | placeholder IS24-V74 | non richiesta |
| 24 | 92 | Google Coral: Edge TPU and pretrained models | §13 Appendice: hardware, software, schede e altri dettagli (non d’esame) | placeholder IS24-V75 | non richiesta |
| 24 | 93 | Boards and AIoT | §13 Appendice: hardware, software, schede e altri dettagli (non d’esame) | placeholder IS24-V76 | non richiesta |
| 24 | 94 | Boards and AIoT (2) | §13 Appendice: hardware, software, schede e altri dettagli (non d’esame) | placeholder IS24-V77 | non richiesta |
| 24 | 95 | Boards and AIoT: a benchmark of previous editions | §13 Appendice: hardware, software, schede e altri dettagli (non d’esame) | placeholder IS24-V78 | non richiesta |
| 24 | 96 | Examples of AIoT for smart retail / ads boards | §13 Appendice: hardware, software, schede e altri dettagli (non d’esame) | placeholder IS24-V79 | non richiesta |
| 24 | 97 | MATLAB: a very good ML platform, but not only | §13 Appendice: hardware, software, schede e altri dettagli (non d’esame) | placeholder IS24-V80 | non richiesta |
| 24 | 98 | Microsoft Azure IoT | §4 Strumenti: piattaforme IoT e sistemi operativi in tempo reale | placeholder IS24-V21 | non richiesta |
| 24 | 99 | Eclipse ThreadX, formerly Azure RTOS | §4 Strumenti: piattaforme IoT e sistemi operativi in tempo reale | placeholder IS24-V22 | non richiesta |
| 24 | 100 | An example: Microsoft Azure | §4 Strumenti: piattaforme IoT e sistemi operativi in tempo reale | placeholder IS24-V23 | non richiesta |
| 24 | 101 | Cloud services: the 3-tier model | §7 Strumenti: i servizi cloud per il ML | placeholder IS24-V29 | non richiesta |
| 24 | 102 | Vehicle to everything (V2X) | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V44 | non richiesta |
| 24 | 103 | Road map for V2X (think of electric/hybrid cars) | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V45 | non richiesta |
| 24 | 104 | Functions of V2X | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V46 | non richiesta |
| 24 | 105 | IoT: Platooning vehicles | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V47 | non richiesta |
| 24 | 106 | Pirelli Cyber Tyre: business opportunities | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V52 | non richiesta |
| 24 | 107 | Intelligent vision systems for smart cars | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V36 | non richiesta |
| 24 | 108 | Intelligent vision systems for smart homes and entertainment | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V37 | non richiesta |
| 24 | 109 | Intelligent vision systems for the smart city | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V38 | non richiesta |
| 24 | 110 | Intelligent vision systems for the environment: wildﬁre smoke | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V39 | non richiesta |
| 24 | 111 | Intelligent vision systems for logistics: liquid lenses | §8 Panoramica: le applicazioni dei sistemi intelligenti | placeholder IS24-V40 | non richiesta |
| 24 | 112 | Appendix: Industry 4.0 | §8 Panoramica: le applicazioni dei sistemi intelligenti | testo/tabella/codice trascritti | non richiesta |
| 24 | 113 | The I4.0 environment and its digital technologies | §10 Panoramica: dall’Industria 4.0 alla 5.0 | placeholder IS24-V64 | non richiesta |
| 24 | 114 | Industry 4.0 design principles (1 and 2) | §10 Panoramica: dall’Industria 4.0 alla 5.0 | testo/tabella/codice trascritti | non richiesta |
| 24 | 115 | Industry 4.0 design principles (3 and 4) | §10 Panoramica: dall’Industria 4.0 alla 5.0 | testo/tabella/codice trascritti | non richiesta |
| 24 | 116 | Industry 5.0 | §10 Panoramica: dall’Industria 4.0 alla 5.0 | placeholder IS24-V67 | non richiesta |
| 24 | 117 | Industry 4.0: the four main synergic features | §13 Appendice: hardware, software, schede e altri dettagli (non d’esame) | placeholder IS24-V81 | non richiesta |
| 24 | 118 | 1) Vertical networking of smart production systems | §13 Appendice: hardware, software, schede e altri dettagli (non d’esame) | placeholder IS24-V82 | non richiesta |
| 24 | 119 | 2) Horizontal integration via global value chain networks | §13 Appendice: hardware, software, schede e altri dettagli (non d’esame) | placeholder IS24-V83 | non richiesta |
| 24 | 120 | 3) Through-engineering across the entire value chain | §13 Appendice: hardware, software, schede e altri dettagli (non d’esame) | placeholder IS24-V84 | non richiesta |
| 24 | 121 | 4) Acceleration through exponential technologies | §13 Appendice: hardware, software, schede e altri dettagli (non d’esame) | placeholder IS24-V85 | non richiesta |
