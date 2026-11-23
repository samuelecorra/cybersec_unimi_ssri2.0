# Copertura pagina-per-pagina — nuova edizione, Lezioni 17–20

Revisione integrale del 2026-11-23: le lezioni sono state riscritte end-to-end seguendo l'ordine delle slide 2026/27. Per ogni pagina è stato estratto testo nativo UTF-8 parola per parola con PyMuPDF (senza scartare le celle numeriche isolate); la presenza di figure è stata stimata dai blocchi immagine e dai tracciati vettoriali, senza rendering né OCR. I placeholder `SOURCE_VISUAL` puntano esclusivamente ai PDF in `LezioneNN/sources_2026-27/` e sono riservati a figure, grafici, schermate e schemi; testo, tabelle, formule e codice delle slide sono trascritti nel Markdown. Il materiale dell'edizione precedente non più presente nelle slide è conservato nelle note o in una sezione di approfondimento. La colonna «Destinazione» indica la sezione della lezione in cui la pagina è trattata (stimata dalla posizione del placeholder più vicino).

| Lezione | PDF | Pagine | Placeholder |
|---|---|---:|---:|
| 17 | `L17-web.pdf` | 89 | 40 |
| 18 | `L18-web.pdf` | 93 | 54 |
| 19 | `L19-web.pdf` | 112 | 80 |
| 20 | `L20-web.pdf` | 76 | 46 |

| Lezione | Pagina | Titolo della slide | Destinazione | Trattamento | Ispezione visiva |
|---:|---:|---|---|---|---|
| 17 | 1 | Intelligent Systems for Industry, Supply Chain and Environment | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 17 | 2 | Outline | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 17 | 3 | Memory of Agents: context management | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 17 | 4 | Agents for ML: where they help, what they can do | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 17 | 5 | Context in LLMs and agents | §1 La memoria degli agenti: la gestione del contesto | placeholder IS17-V01 | non richiesta |
| 17 | 6 | Context size is increasing! | §1 La memoria degli agenti: la gestione del contesto | placeholder IS17-V02 | non richiesta |
| 17 | 7 | Context size matters | §1 La memoria degli agenti: la gestione del contesto | testo/tabella/codice trascritti | non richiesta |
| 17 | 8 | Context size (local and cloud) | §1 La memoria degli agenti: la gestione del contesto | testo/tabella/codice trascritti | non richiesta |
| 17 | 9 | LLM context is rebuilt at every chat turn | §1 La memoria degli agenti: la gestione del contesto | placeholder IS17-V03 | non richiesta |
| 17 | 10 | LLM context is rebuilt at every chat turn | §1 La memoria degli agenti: la gestione del contesto | placeholder IS17-V04 | non richiesta |
| 17 | 11 | Context grows in chats and with agents | §1 La memoria degli agenti: la gestione del contesto | placeholder IS17-V05 | non richiesta |
| 17 | 12 | Example of a growing context | §1 La memoria degli agenti: la gestione del contesto | testo/tabella/codice trascritti | non richiesta |
| 17 | 13 | Context rot: never use the full context from the start | §1 La memoria degli agenti: la gestione del contesto | placeholder IS17-V06 | non richiesta |
| 17 | 14 | Context management techniques | §1 La memoria degli agenti: la gestione del contesto | testo/tabella/codice trascritti | non richiesta |
| 17 | 15 | Context management 1: Oﬄoading | §1 La memoria degli agenti: la gestione del contesto | testo/tabella/codice trascritti | non richiesta |
| 17 | 16 | Oﬄoading with documents: inside the prompt or outside? | §1 La memoria degli agenti: la gestione del contesto | testo/tabella/codice trascritti | non richiesta |
| 17 | 17 | Context management 2: RAG | §1 La memoria degli agenti: la gestione del contesto | testo/tabella/codice trascritti | non richiesta |
| 17 | 18 | Context management 3: Loadout / dynamic tool selection | §1 La memoria degli agenti: la gestione del contesto | testo/tabella/codice trascritti | non richiesta |
| 17 | 19 | Context management 4: Pruning | §1 La memoria degli agenti: la gestione del contesto | testo/tabella/codice trascritti | non richiesta |
| 17 | 20 | Context management 5: Summarization | §1 La memoria degli agenti: la gestione del contesto | testo/tabella/codice trascritti | non richiesta |
| 17 | 21 | Context management 6: Quarantine | §1 La memoria degli agenti: la gestione del contesto | testo/tabella/codice trascritti | non richiesta |
| 17 | 22 | Managing context during an agentic workﬂow | §1 La memoria degli agenti: la gestione del contesto | placeholder IS17-V07 | non richiesta |
| 17 | 23 | Toolboxes: classiﬁcation errors in Python | §1 La memoria degli agenti: la gestione del contesto | testo/tabella/codice trascritti | non richiesta |
| 17 | 24 | Test the wine decision tree | §1 La memoria degli agenti: la gestione del contesto | testo/tabella/codice trascritti | non richiesta |
| 17 | 25 | The shallow tree is not a perfect learner. . . | §2 Strumenti: gli errori di classificazione in Python | placeholder IS17-V08 | non richiesta |
| 17 | 26 | Let’s check the accuracy | §2 Strumenti: gli errori di classificazione in Python | placeholder IS17-V09 | non richiesta |
| 17 | 27 | Let’s check the accuracy | §2 Strumenti: gli errori di classificazione in Python | placeholder IS17-V10 | non richiesta |
| 17 | 28 | Process the accuracy | §2 Strumenti: gli errori di classificazione in Python | testo/tabella/codice trascritti | non richiesta |
| 17 | 29 | Adding layers | §2 Strumenti: gli errori di classificazione in Python | placeholder IS17-V11 | non richiesta |
| 17 | 30 | We created a perfect learner | §2 Strumenti: gli errori di classificazione in Python | placeholder IS17-V12 | non richiesta |
| 17 | 31 | Process the accuracy | §2 Strumenti: gli errori di classificazione in Python | testo/tabella/codice trascritti | non richiesta |
| 17 | 32 | Is it a good idea to create a perfect learner? | §2 Strumenti: gli errori di classificazione in Python | testo/tabella/codice trascritti | non richiesta |
| 17 | 33 | We can test the generalization with cross-validation! | §2 Strumenti: gli errori di classificazione in Python | testo/tabella/codice trascritti | non richiesta |
| 17 | 34 | 10-fold CV accuracy per fold: it’s not so perfect! | §2 Strumenti: gli errori di classificazione in Python | placeholder IS17-V13 | non richiesta |
| 17 | 35 | Let’s see the cross-validation accuracies. . . | §2 Strumenti: gli errori di classificazione in Python | testo/tabella/codice trascritti | non richiesta |
| 17 | 36 | Theory: the Bias/Variance Tradeoﬀ | §2 Strumenti: gli errori di classificazione in Python | testo/tabella/codice trascritti | non richiesta |
| 17 | 37 | Too simple, just right, too complex | §3 Teoria: il compromesso (dilemma) bias/varianza | placeholder IS17-V14 | non richiesta |
| 17 | 38 | How do mean and std of CV error change with depth? | §3 Teoria: il compromesso (dilemma) bias/varianza | placeholder IS17-V15 | non richiesta |
| 17 | 39 | Bias–Variance illustration (Wine dataset, n = 178) | §3 Teoria: il compromesso (dilemma) bias/varianza | placeholder IS17-V16 | non richiesta |
| 17 | 40 | The bias-variance dilemma | §3 Teoria: il compromesso (dilemma) bias/varianza | placeholder IS17-V17 | non richiesta |
| 17 | 41 | The bias-variance dilemma (regressor) | §3 Teoria: il compromesso (dilemma) bias/varianza | placeholder IS17-V18 | non richiesta |
| 17 | 42 | Bias–Variance trade-oﬀ: the theory | §3 Teoria: il compromesso (dilemma) bias/varianza | testo/tabella/codice trascritti | non richiesta |
| 17 | 43 | Visualization of the Bias/Variance Tradeoﬀ | §3 Teoria: il compromesso (dilemma) bias/varianza | placeholder IS17-V19 | non richiesta |
| 17 | 44 | Toolboxes: feature engineering + classiﬁcation in Matlab | §3 Teoria: il compromesso (dilemma) bias/varianza | testo/tabella/codice trascritti | non richiesta |
| 17 | 45 | Choosing a direction: PCA or LDA? | §4 Strumenti: feature engineering + classificazione in MATLAB | placeholder IS17-V20 | non richiesta |
| 17 | 46 | Recall: feature engineering with labels | §4 Strumenti: feature engineering + classificazione in MATLAB | testo/tabella/codice trascritti | non richiesta |
| 17 | 47 | From 4 measurements to a species: a linear classiﬁer | §4 Strumenti: feature engineering + classificazione in MATLAB | testo/tabella/codice trascritti | non richiesta |
| 17 | 48 | What the LDA does: it rotates the labelled cloud | §4 Strumenti: feature engineering + classificazione in MATLAB | placeholder IS17-V21 | non richiesta |
| 17 | 49 | LDA step by step: matrices, projection, threshold | §4 Strumenti: feature engineering + classificazione in MATLAB | testo/tabella/codice trascritti | non richiesta |
| 17 | 50 | Linear classiﬁcation in Python (1/4): look at the data | §4 Strumenti: feature engineering + classificazione in MATLAB | placeholder IS17-V22 | non richiesta |
| 17 | 51 | Linear classiﬁcation in Python (2/4): train and validation | §4 Strumenti: feature engineering + classificazione in MATLAB | testo/tabella/codice trascritti | non richiesta |
| 17 | 52 | Linear classiﬁcation in Python (4/4): confusion matrices | §4 Strumenti: feature engineering + classificazione in MATLAB | placeholder IS17-V23 | non richiesta |
| 17 | 53 | Theory: new model? | §4 Strumenti: feature engineering + classificazione in MATLAB | testo/tabella/codice trascritti | non richiesta |
| 17 | 54 | What to know in case of new models? | §5 Teoria: un modello nuovo? Che cosa sapere per un avvio corretto | placeholder IS17-V24 | non richiesta |
| 17 | 55 | What to know in case of new models? | §5 Teoria: un modello nuovo? Che cosa sapere per un avvio corretto | testo/tabella/codice trascritti | non richiesta |
| 17 | 56 | What to do in case of new models? | §5 Teoria: un modello nuovo? Che cosa sapere per un avvio corretto | testo/tabella/codice trascritti | non richiesta |
| 17 | 57 | Application of a new classiﬁer | §5 Teoria: un modello nuovo? Che cosa sapere per un avvio corretto | placeholder IS17-V25 | non richiesta |
| 17 | 58 | Theory: a simple example of the complete workﬂow | §5 Teoria: un modello nuovo? Che cosa sapere per un avvio corretto | testo/tabella/codice trascritti | non richiesta |
| 17 | 59 | Linear Regression | §6 Teoria: un semplice esempio del flusso completo — il regressore lineare | placeholder IS17-V26 | non richiesta |
| 17 | 60 | Linear Regression examples | §6 Teoria: un semplice esempio del flusso completo — il regressore lineare | placeholder IS17-V27 | non richiesta |
| 17 | 61 | Notation with 3 input features | §6 Teoria: un semplice esempio del flusso completo — il regressore lineare | testo/tabella/codice trascritti | non richiesta |
| 17 | 62 | Notation with N input features | §6 Teoria: un semplice esempio del flusso completo — il regressore lineare | testo/tabella/codice trascritti | non richiesta |
| 17 | 63 | Linear regressor and linear classiﬁer | §6 Teoria: un semplice esempio del flusso completo — il regressore lineare | placeholder IS17-V28 | non richiesta |
| 17 | 64 | Linear Regression | §6 Teoria: un semplice esempio del flusso completo — il regressore lineare | testo/tabella/codice trascritti | non richiesta |
| 17 | 65 | Residual Sum-of-Squares | §6 Teoria: un semplice esempio del flusso completo — il regressore lineare | testo/tabella/codice trascritti | non richiesta |
| 17 | 66 | Gradient, gradient, gradient! (in the exam) | §6 Teoria: un semplice esempio del flusso completo — il regressore lineare | testo/tabella/codice trascritti | non richiesta |
| 17 | 67 | But gradient depends on. . . (worked example) | §6 Teoria: un semplice esempio del flusso completo — il regressore lineare | testo/tabella/codice trascritti | non richiesta |
| 17 | 68 | Is the regression good? R2 is not enough | §6 Teoria: un semplice esempio del flusso completo — il regressore lineare | placeholder IS17-V29 | non richiesta |
| 17 | 69 | Iterative solutions and closed form | §6 Teoria: un semplice esempio del flusso completo — il regressore lineare | testo/tabella/codice trascritti | non richiesta |
| 17 | 70 | Toolboxes: Matlab and Python | §6 Teoria: un semplice esempio del flusso completo — il regressore lineare | testo/tabella/codice trascritti | non richiesta |
| 17 | 71 | The same regressor in two toolboxes | §6 Teoria: un semplice esempio del flusso completo — il regressore lineare | testo/tabella/codice trascritti | non richiesta |
| 17 | 72 | Matlab ﬁle | §6 Teoria: un semplice esempio del flusso completo — il regressore lineare | testo/tabella/codice trascritti | non richiesta |
| 17 | 73 | Matlab: create and train a linear regressor | §7 Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto) | placeholder IS17-V30 | non richiesta |
| 17 | 74 | Create and train a linear regressor (1/2) | §7 Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto) | testo/tabella/codice trascritti | non richiesta |
| 17 | 75 | The created linear regressor | §7 Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto) | placeholder IS17-V31 | non richiesta |
| 17 | 76 | Access the parameters | §7 Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto) | placeholder IS17-V32 | non richiesta |
| 17 | 77 | Processing inputs and plotting the results | §7 Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto) | placeholder IS17-V33 | non richiesta |
| 17 | 78 | Accuracy assessment | §7 Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto) | placeholder IS17-V34 | non richiesta |
| 17 | 79 | Try a feedforward neural network! | §7 Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto) | placeholder IS17-V35 | non richiesta |
| 17 | 80 | Try a feedforward neural network! (1/2) | §7 Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto) | placeholder IS17-V36 | non richiesta |
| 17 | 81 | Wrong design: 100 neurons with 51 data points! (1/2) | §7 Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto) | placeholder IS17-V37 | non richiesta |
| 17 | 82 | Let’s build a test set. . . | §7 Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto) | testo/tabella/codice trascritti | non richiesta |
| 17 | 83 | . . . and we overﬁtted (a lot!) | §7 Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto) | placeholder IS17-V38 | non richiesta |
| 17 | 84 | Remember next time you will initialize a model with #DoF ≫ | §7 Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto) | placeholder IS17-V39 | non richiesta |
| 17 | 85 | Python in Colab: the ﬁle and the data | §7 Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto) | placeholder IS17-V40 | non richiesta |
| 17 | 86 | Python: create, plot and assess the regressor | §7 Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto) | testo/tabella/codice trascritti | non richiesta |
| 17 | 87 | Main points | §7 Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto) | testo/tabella/codice trascritti | non richiesta |
| 17 | 88 | Appendix | §7 Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto) | testo/tabella/codice trascritti | non richiesta |
| 17 | 89 | What are the mean() and std() of the kFCV | §7 Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto) | testo/tabella/codice trascritti | non richiesta |
| 18 | 1 | Intelligent Systems for Industry, Supply Chain and Environment | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 18 | 2 | Outline | §0 Panoramica | placeholder IS18-V01 | non richiesta |
| 18 | 3 | Feature extraction / feature selection | §0 Panoramica | placeholder IS18-V02 | non richiesta |
| 18 | 4 | Feature engineering | §0 Panoramica | placeholder IS18-V03 | non richiesta |
| 18 | 5 | Feature engineering: one typical application | §0 Panoramica | placeholder IS18-V04 | non richiesta |
| 18 | 6 | Techniques at a glance: extract or select | §0 Panoramica | placeholder IS18-V05 | non richiesta |
| 18 | 7 | Part 1: feature selection | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 18 | 8 | Why select features? | §1 Parte 1: la selezione delle feature | placeholder IS18-V06 | non richiesta |
| 18 | 9 | Filters, wrappers, embedded methods | §1 Parte 1: la selezione delle feature | placeholder IS18-V07 | non richiesta |
| 18 | 10 | Filters | §1 Parte 1: la selezione delle feature | testo/tabella/codice trascritti | non richiesta |
| 18 | 11 | Wrappers | §1 Parte 1: la selezione delle feature | testo/tabella/codice trascritti | non richiesta |
| 18 | 12 | Sequential Forward Selection (SFS, wrapper): candidates | §1 Parte 1: la selezione delle feature | placeholder IS18-V08 | non richiesta |
| 18 | 13 | Sequential Forward Selection (SFS, wrapper): path | §1 Parte 1: la selezione delle feature | placeholder IS18-V09 | non richiesta |
| 18 | 14 | Sequential Backward Selection (SBS, wrapper) | §1 Parte 1: la selezione delle feature | placeholder IS18-V10 | non richiesta |
| 18 | 15 | Embedded methods | §1 Parte 1: la selezione delle feature | testo/tabella/codice trascritti | non richiesta |
| 18 | 16 | Feature selection: embedded, neural network example | §1 Parte 1: la selezione delle feature | placeholder IS18-V11 | non richiesta |
| 18 | 17 | Lab 1: feature selection with RFE | §1 Parte 1: la selezione delle feature | testo/tabella/codice trascritti | non richiesta |
| 18 | 18 | Dataset used in the scripts | §2 Laboratorio 1: selezione delle feature con RFE | placeholder IS18-V12 | non richiesta |
| 18 | 19 | Dimensionality reduction: feature selection (RFE) | §2 Laboratorio 1: selezione delle feature con RFE | placeholder IS18-V13 | non richiesta |
| 18 | 20 | RFE: prepare the data | §2 Laboratorio 1: selezione delle feature con RFE | testo/tabella/codice trascritti | non richiesta |
| 18 | 21 | RFE: deﬁne the wrapper | §2 Laboratorio 1: selezione delle feature con RFE | testo/tabella/codice trascritti | non richiesta |
| 18 | 22 | RFE: ranking of the pixels | §2 Laboratorio 1: selezione delle feature con RFE | testo/tabella/codice trascritti | non richiesta |
| 18 | 23 | RFE: plot the pixel ranking | §2 Laboratorio 1: selezione delle feature con RFE | placeholder IS18-V14 | non richiesta |
| 18 | 24 | RFE: how many pixels do we need? | §2 Laboratorio 1: selezione delle feature con RFE | placeholder IS18-V15 | non richiesta |
| 18 | 25 | Part 2: feature extraction | §2 Laboratorio 1: selezione delle feature con RFE | testo/tabella/codice trascritti | non richiesta |
| 18 | 26 | Visualizing high-dimensional unsupervised data | §3 Parte 2: l’estrazione delle feature — la PCA | placeholder IS18-V16 | non richiesta |
| 18 | 27 | Principal Component Analysis | §3 Parte 2: l’estrazione delle feature — la PCA | placeholder IS18-V17 | non richiesta |
| 18 | 28 | PCA seen on the data matrix (3 genes, 20 cells) | §3 Parte 2: l’estrazione delle feature — la PCA | placeholder IS18-V18 | non richiesta |
| 18 | 29 | PCA idea | §3 Parte 2: l’estrazione delle feature — la PCA | placeholder IS18-V19 | non richiesta |
| 18 | 30 | PCA idea (2) | §3 Parte 2: l’estrazione delle feature — la PCA | placeholder IS18-V20 | non richiesta |
| 18 | 31 | PCA idea (3) | §3 Parte 2: l’estrazione delle feature — la PCA | placeholder IS18-V21 | non richiesta |
| 18 | 32 | Principal Component Analysis (PCA) | §3 Parte 2: l’estrazione delle feature — la PCA | testo/tabella/codice trascritti | non richiesta |
| 18 | 33 | Principal Component Analysis (PCA): the block | §3 Parte 2: l’estrazione delle feature — la PCA | testo/tabella/codice trascritti | non richiesta |
| 18 | 34 | PCA block with the real numbers (3 genes) | §3 Parte 2: l’estrazione delle feature — la PCA | placeholder IS18-V22 | non richiesta |
| 18 | 35 | Principal Component Analysis (PCA): the steps | §3 Parte 2: l’estrazione delle feature — la PCA | testo/tabella/codice trascritti | non richiesta |
| 18 | 36 | PCA example: from dim 10 to dim 2 | §3 Parte 2: l’estrazione delle feature — la PCA | placeholder IS18-V23 | non richiesta |
| 18 | 37 | Eigenvalues: how many components do we keep? | §3 Parte 2: l’estrazione delle feature — la PCA | placeholder IS18-V24 | non richiesta |
| 18 | 38 | PCA in practice: a pipeline for a classiﬁer | §3 Parte 2: l’estrazione delle feature — la PCA | placeholder IS18-V25 | non richiesta |
| 18 | 39 | PCA applications | §3 Parte 2: l’estrazione delle feature — la PCA | testo/tabella/codice trascritti | non richiesta |
| 18 | 40 | Lab 2: PCA on the digits | §3 Parte 2: l’estrazione delle feature — la PCA | testo/tabella/codice trascritti | non richiesta |
| 18 | 41 | Dimensionality reduction via PCA: load data | §3 Parte 2: l’estrazione delle feature — la PCA | testo/tabella/codice trascritti | non richiesta |
| 18 | 42 | Inspecting one sample | §4 Laboratorio 2: la PCA sulle cifre | placeholder IS18-V26 | non richiesta |
| 18 | 43 | Two raw features: scatter plot | §4 Laboratorio 2: la PCA sulle cifre | placeholder IS18-V27 | non richiesta |
| 18 | 44 | Dimensionality reduction via PCA | §4 Laboratorio 2: la PCA sulle cifre | testo/tabella/codice trascritti | non richiesta |
| 18 | 45 | PCA components: scatter plot | §4 Laboratorio 2: la PCA sulle cifre | placeholder IS18-V28 | non richiesta |
| 18 | 46 | Feature extraction: LDA, t-SNE, ICA | §4 Laboratorio 2: la PCA sulle cifre | testo/tabella/codice trascritti | non richiesta |
| 18 | 47 | Dimensionality reduction: Linear Discriminant Analysis (LDA) | §5 Teoria: riduzione della dimensionalità con LDA, t-SNE, ICA | placeholder IS18-V29 | non richiesta |
| 18 | 48 | LDA: the Fisher rotation uses the classes | §5 Teoria: riduzione della dimensionalità con LDA, t-SNE, ICA | placeholder IS18-V30 | non richiesta |
| 18 | 49 | Dimensionality reduction: t-SNE | §5 Teoria: riduzione della dimensionalità con LDA, t-SNE, ICA | placeholder IS18-V31 | non richiesta |
| 18 | 50 | ICA: separating mixed sources (examples) | §5 Teoria: riduzione della dimensionalità con LDA, t-SNE, ICA | placeholder IS18-V32 | non richiesta |
| 18 | 51 | ICA: observations, sources and noise | §5 Teoria: riduzione della dimensionalità con LDA, t-SNE, ICA | placeholder IS18-V33 | non richiesta |
| 18 | 52 | Lab 3: t-SNE on the digits | §5 Teoria: riduzione della dimensionalità con LDA, t-SNE, ICA | testo/tabella/codice trascritti | non richiesta |
| 18 | 53 | Dimensionality reduction via t-SNE | §5 Teoria: riduzione della dimensionalità con LDA, t-SNE, ICA | testo/tabella/codice trascritti | non richiesta |
| 18 | 54 | t-SNE components: scatter plot | §6 Laboratorio 3: la t-SNE sulle cifre | placeholder IS18-V34 | non richiesta |
| 18 | 55 | Comparison: two arbitrary pixels, PCA, t-SNE | §6 Laboratorio 3: la t-SNE sulle cifre | placeholder IS18-V35 | non richiesta |
| 18 | 56 | Five ways to visualize high-dimensional data | §6 Laboratorio 3: la t-SNE sulle cifre | testo/tabella/codice trascritti | non richiesta |
| 18 | 57 | The projection changes the representation | §6 Laboratorio 3: la t-SNE sulle cifre | placeholder IS18-V36 | non richiesta |
| 18 | 58 | Images: visualize features, not necessarily pixels | §6 Laboratorio 3: la t-SNE sulle cifre | placeholder IS18-V37 | non richiesta |
| 18 | 59 | Lab 4: ﬁve maps on Iris, Wine, MNIST | §6 Laboratorio 3: la t-SNE sulle cifre | testo/tabella/codice trascritti | non richiesta |
| 18 | 60 | Iris: compare ﬁve 2-D projections | §7 Laboratorio 4: cinque mappe su Iris, Wine, MNIST | placeholder IS18-V38 | non richiesta |
| 18 | 61 | Wine: same input, ﬁve diﬀerent objectives | §7 Laboratorio 4: cinque mappe su Iris, Wine, MNIST | placeholder IS18-V39 | non richiesta |
| 18 | 62 | MNIST subset: images become feature vectors | §7 Laboratorio 4: cinque mappe su Iris, Wine, MNIST | placeholder IS18-V40 | non richiesta |
| 18 | 63 | EmbeddingGemma 2: a multimodal encoder | §7 Laboratorio 4: cinque mappe su Iris, Wine, MNIST | placeholder IS18-V41 | non richiesta |
| 18 | 64 | From one MNIST image to a point on the map | §7 Laboratorio 4: cinque mappe su Iris, Wine, MNIST | placeholder IS18-V42 | non richiesta |
| 18 | 65 | MNIST: ﬁve projections of learned image embeddings | §7 Laboratorio 4: cinque mappe su Iris, Wine, MNIST | placeholder IS18-V43 | non richiesta |
| 18 | 66 | Which tool, when? | §7 Laboratorio 4: cinque mappe su Iris, Wine, MNIST | testo/tabella/codice trascritti | non richiesta |
| 18 | 67 | Python: the ﬁve 2-D maps in a few lines | §7 Laboratorio 4: cinque mappe su Iris, Wine, MNIST | testo/tabella/codice trascritti | non richiesta |
| 18 | 68 | Embeddings | §7 Laboratorio 4: cinque mappe su Iris, Wine, MNIST | testo/tabella/codice trascritti | non richiesta |
| 18 | 69 | Embeddings: learned features, then view or decide | §8 Teoria: gli embedding e il «System 1» nell’AI | placeholder IS18-V44 | non richiesta |
| 18 | 70 | Embeddings: similarity, and what a map does not tell | §8 Teoria: gli embedding e il «System 1» nell’AI | testo/tabella/codice trascritti | non richiesta |
| 18 | 71 | System 1 in AI | §8 Teoria: gli embedding e il «System 1» nell’AI | testo/tabella/codice trascritti | non richiesta |
| 18 | 72 | Thinking, fast and slow: two systems | §8 Teoria: gli embedding e il «System 1» nell’AI | placeholder IS18-V45 | non richiesta |
| 18 | 73 | Kahneman’s biology: a wide front end, a narrow back end | §8 Teoria: gli embedding e il «System 1» nell’AI | placeholder IS18-V46 | non richiesta |
| 18 | 74 | From the human System 1 to an AI System 1 | §8 Teoria: gli embedding e il «System 1» nell’AI | testo/tabella/codice trascritti | non richiesta |
| 18 | 75 | Our techniques on the fast–slow axis | §8 Teoria: gli embedding e il «System 1» nell’AI | testo/tabella/codice trascritti | non richiesta |
| 18 | 76 | Human biases and their analogues in our pipeline | §8 Teoria: gli embedding e il «System 1» nell’AI | testo/tabella/codice trascritti | non richiesta |
| 18 | 77 | How System One models work: the whole picture | §8 Teoria: gli embedding e il «System 1» nell’AI | placeholder IS18-V47 | non richiesta |
| 18 | 78 | Structure of a System One model | §8 Teoria: gli embedding e il «System 1» nell’AI | placeholder IS18-V48 | non richiesta |
| 18 | 79 | Three primitives: Choice, Score, Noul | §8 Teoria: gli embedding e il «System 1» nell’AI | testo/tabella/codice trascritti | non richiesta |
| 18 | 80 | What a Noul does | §8 Teoria: gli embedding e il «System 1» nell’AI | placeholder IS18-V49 | non richiesta |
| 18 | 81 | Noul in practice: a probability and two thresholds | §8 Teoria: gli embedding e il «System 1» nell’AI | placeholder IS18-V50 | non richiesta |
| 18 | 82 | Why fast? Time and cost per decision | §8 Teoria: gli embedding e il «System 1» nell’AI | placeholder IS18-V51 | non richiesta |
| 18 | 83 | “No hallucination” does not mean “correct” | §8 Teoria: gli embedding e il «System 1» nell’AI | testo/tabella/codice trascritti | non richiesta |
| 18 | 84 | Calibration: is 90% conﬁdent right 90% of the time? | §8 Teoria: gli embedding e il «System 1» nell’AI | placeholder IS18-V52 | non richiesta |
| 18 | 85 | The cascade: System 1 proposes, System 2 validates | §8 Teoria: gli embedding e il «System 1» nell’AI | placeholder IS18-V53 | non richiesta |
| 18 | 86 | A decision layer for the enterprise | §8 Teoria: gli embedding e il «System 1» nell’AI | placeholder IS18-V54 | non richiesta |
| 18 | 87 | Is this a System One job? Six questions | §8 Teoria: gli embedding e il «System 1» nell’AI | testo/tabella/codice trascritti | non richiesta |
| 18 | 88 | Applications and limits of System One models | §8 Teoria: gli embedding e il «System 1» nell’AI | testo/tabella/codice trascritti | non richiesta |
| 18 | 89 | Products at the end of October 2026 (OUTLOOK) | §8 Teoria: gli embedding e il «System 1» nell’AI | testo/tabella/codice trascritti | non richiesta |
| 18 | 90 | Main points | §8 Teoria: gli embedding e il «System 1» nell’AI | testo/tabella/codice trascritti | non richiesta |
| 18 | 91 | Appendix: PCA algorithm (sample covariance matrix) | §8 Teoria: gli embedding e il «System 1» nell’AI | testo/tabella/codice trascritti | non richiesta |
| 18 | 92 | Appendix: PCA algorithm, main steps | §8 Teoria: gli embedding e il «System 1» nell’AI | testo/tabella/codice trascritti | non richiesta |
| 18 | 93 | Appendix: weights and variance of a component | §8 Teoria: gli embedding e il «System 1» nell’AI | testo/tabella/codice trascritti | non richiesta |
| 19 | 1 | Intelligent Systems for Industry, Supply Chain and Environment | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 19 | 2 | Outline | §0 Panoramica | placeholder IS19-V01 | non richiesta |
| 19 | 3 | Bayes Optimal Classiﬁcation | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 19 | 4 | Bayes optimal limit | §1 La classificazione Bayes-ottima | placeholder IS19-V02 | non richiesta |
| 19 | 5 | Bayes optimal error | §1 La classificazione Bayes-ottima | placeholder IS19-V03 | non richiesta |
| 19 | 6 | Bayes optimal classiﬁer: intuition | §1 La classificazione Bayes-ottima | placeholder IS19-V04 | non richiesta |
| 19 | 7 | Bayes Optimal Classiﬁer: output class | §1 La classificazione Bayes-ottima | placeholder IS19-V05 | non richiesta |
| 19 | 8 | Bayes Optimal Classiﬁcation | §1 La classificazione Bayes-ottima | placeholder IS19-V06 | non richiesta |
| 19 | 9 | Bayes Optimal Classiﬁcation: limits | §1 La classificazione Bayes-ottima | placeholder IS19-V07 | non richiesta |
| 19 | 10 | The Naive assumption | §1 La classificazione Bayes-ottima | placeholder IS19-V08 | non richiesta |
| 19 | 11 | Naive Bayes Classiﬁcation | §1 La classificazione Bayes-ottima | testo/tabella/codice trascritti | non richiesta |
| 19 | 12 | Coding the naïve Bayes classiﬁer | §1 La classificazione Bayes-ottima | testo/tabella/codice trascritti | non richiesta |
| 19 | 13 | Classical models | §1 La classificazione Bayes-ottima | testo/tabella/codice trascritti | non richiesta |
| 19 | 14 | Classical (non neural) methods are important! | §2 Modelli classici | placeholder IS19-V09 | non richiesta |
| 19 | 15 | Examples of classical methods (non neural) | §2 Modelli classici | testo/tabella/codice trascritti | non richiesta |
| 19 | 16 | “Eager” and “Lazy” learning methods | §2 Modelli classici | testo/tabella/codice trascritti | non richiesta |
| 19 | 17 | Eager and Lazy Learning Methods | §2 Modelli classici | testo/tabella/codice trascritti | non richiesta |
| 19 | 18 | Eager learning | §3 Apprendimento «eager» e «lazy» | placeholder IS19-V10 | non richiesta |
| 19 | 19 | Lazy (instance-based) learning | §3 Apprendimento «eager» e «lazy» | placeholder IS19-V11 | non richiesta |
| 19 | 20 | Eager vs lazy: costs and examples | §3 Apprendimento «eager» e «lazy» | placeholder IS19-V12 | non richiesta |
| 19 | 21 | Classical models: kNN | §3 Apprendimento «eager» e «lazy» | testo/tabella/codice trascritti | non richiesta |
| 19 | 22 | Why study the kNN? | §3 Apprendimento «eager» e «lazy» | testo/tabella/codice trascritti | non richiesta |
| 19 | 23 | Nearest neighbors: the duck intuition | §4 Modelli classici: il k-NN | placeholder IS19-V13 | non richiesta |
| 19 | 24 | From intuition to features: the nearest neighbor | §4 Modelli classici: il k-NN | placeholder IS19-V14 | non richiesta |
| 19 | 25 | kNN classiﬁcation: the processing pipeline | §4 Modelli classici: il k-NN | placeholder IS19-V15 | non richiesta |
| 19 | 26 | Same query, diﬀerent k, diﬀerent class | §4 Modelli classici: il k-NN | placeholder IS19-V16 | non richiesta |
| 19 | 27 | Growing k: who enters the vote | §4 Modelli classici: il k-NN | placeholder IS19-V17 | non richiesta |
| 19 | 28 | Voronoi tessellation and 1-NN regions | §4 Modelli classici: il k-NN | placeholder IS19-V18 | non richiesta |
| 19 | 29 | N-dimensional Voronoi diagram | §4 Modelli classici: il k-NN | placeholder IS19-V19 | non richiesta |
| 19 | 30 | Voronoi usage examples | §4 Modelli classici: il k-NN | placeholder IS19-V20 | non richiesta |
| 19 | 31 | Voronoi diagram usage | §4 Modelli classici: il k-NN | placeholder IS19-V21 | non richiesta |
| 19 | 32 | Choosing k: how the decision boundary changes | §4 Modelli classici: il k-NN | placeholder IS19-V22 | non richiesta |
| 19 | 33 | Distance-Weighted Nearest Neighbor Algorithm | §4 Modelli classici: il k-NN | placeholder IS19-V23 | non richiesta |
| 19 | 34 | What does “nearest” mean? | §4 Modelli classici: il k-NN | testo/tabella/codice trascritti | non richiesta |
| 19 | 35 | Who is my nearest neighbor? Euclidean vs cosine | §5 Che cosa significa «più vicino»? | placeholder IS19-V24 | non richiesta |
| 19 | 36 | Cosine depends on the origin | §5 Che cosa significa «più vicino»? | placeholder IS19-V25 | non richiesta |
| 19 | 37 | Same data, three deﬁnitions of “near” | §5 Che cosa significa «più vicino»? | placeholder IS19-V26 | non richiesta |
| 19 | 38 | Units can decide the distance | §5 Che cosa significa «più vicino»? | placeholder IS19-V27 | non richiesta |
| 19 | 39 | What the Euclidean distance “sees” | §5 Che cosa significa «più vicino»? | placeholder IS19-V28 | non richiesta |
| 19 | 40 | Images: kNN works on embeddings | §5 Che cosa significa «più vicino»? | placeholder IS19-V29 | non richiesta |
| 19 | 41 | The dataset: 40 photos of cats and dogs | §5 Che cosa significa «più vicino»? | placeholder IS19-V30 | non richiesta |
| 19 | 42 | The 40 embeddings seen by PCA and t-SNE | §5 Che cosa significa «più vicino»? | placeholder IS19-V31 | non richiesta |
| 19 | 43 | Cosine on the real embeddings: mind the origin | §5 Che cosa significa «più vicino»? | placeholder IS19-V32 | non richiesta |
| 19 | 44 | ℓ2 norm, cosine and kNN on the 40 photos | §5 Che cosa significa «più vicino»? | testo/tabella/codice trascritti | non richiesta |
| 19 | 45 | Same technique, industrial data: cardboard boxes | §5 Che cosa significa «più vicino»? | placeholder IS19-V33 | non richiesta |
| 19 | 46 | Why it is hard: intact or defective? | §5 Che cosa significa «più vicino»? | placeholder IS19-V34 | non richiesta |
| 19 | 47 | Boxes: PCA and t-SNE of the embeddings | §5 Che cosa significa «più vicino»? | placeholder IS19-V35 | non richiesta |
| 19 | 48 | Similarity must be designed, not assumed | §5 Che cosa significa «più vicino»? | placeholder IS19-V36 | non richiesta |
| 19 | 49 | Practical issues when using kNN | §5 Che cosa significa «più vicino»? | placeholder IS19-V37 | non richiesta |
| 19 | 50 | Practical issues when using kNN: speed | §5 Che cosa significa «più vicino»? | placeholder IS19-V38 | non richiesta |
| 19 | 51 | Toolboxes: Coding kNN | §5 Che cosa significa «più vicino»? | testo/tabella/codice trascritti | non richiesta |
| 19 | 52 | kNN coding in MATLAB | §5 Che cosa significa «più vicino»? | testo/tabella/codice trascritti | non richiesta |
| 19 | 53 | kNN coding in MATLAB: changing the folds | §5 Che cosa significa «più vicino»? | testo/tabella/codice trascritti | non richiesta |
| 19 | 54 | Clustering: k-Means | §5 Che cosa significa «più vicino»? | testo/tabella/codice trascritti | non richiesta |
| 19 | 55 | Imagine these assignments | §5 Che cosa significa «più vicino»? | testo/tabella/codice trascritti | non richiesta |
| 19 | 56 | Clustering rationale | §7 Clustering: k-means | placeholder IS19-V39 | non richiesta |
| 19 | 57 | Clustering | §7 Clustering: k-means | placeholder IS19-V40 | non richiesta |
| 19 | 58 | Clustering | §7 Clustering: k-means | placeholder IS19-V41 | non richiesta |
| 19 | 59 | Why do we cluster? | §7 Clustering: k-means | testo/tabella/codice trascritti | non richiesta |
| 19 | 60 | k-means step by step (1/2) | §7 Clustering: k-means | placeholder IS19-V42 | non richiesta |
| 19 | 61 | k-means step by step (2/2): iterate until nothing changes | §7 Clustering: k-means | placeholder IS19-V43 | non richiesta |
| 19 | 62 | Clustering using k-means | §7 Clustering: k-means | testo/tabella/codice trascritti | non richiesta |
| 19 | 63 | k-means: the partition algorithm | §7 Clustering: k-means | placeholder IS19-V44 | non richiesta |
| 19 | 64 | k-means converges to a local minimum | §7 Clustering: k-means | placeholder IS19-V45 | non richiesta |
| 19 | 65 | k-Means outlier problems | §7 Clustering: k-means | placeholder IS19-V46 | non richiesta |
| 19 | 66 | Strengths and weaknesses of k-means | §7 Clustering: k-means | placeholder IS19-V47 | non richiesta |
| 19 | 67 | Diﬀerent behavior of the clustering methods | §7 Clustering: k-means | placeholder IS19-V48 | non richiesta |
| 19 | 68 | Exclusive and overlapping clustering | §7 Clustering: k-means | placeholder IS19-V49 | non richiesta |
| 19 | 69 | How to choose k, the number of clusters | §7 Clustering: k-means | placeholder IS19-V50 | non richiesta |
| 19 | 70 | A clustering pipeline | §7 Clustering: k-means | placeholder IS19-V51 | non richiesta |
| 19 | 71 | How to decide k? Elbow plot (basic method) | §7 Clustering: k-means | placeholder IS19-V52 | non richiesta |
| 19 | 72 | How to decide k? Advanced methods | §7 Clustering: k-means | placeholder IS19-V53 | non richiesta |
| 19 | 73 | A new sample: where does it belong? | §7 Clustering: k-means | placeholder IS19-V54 | non richiesta |
| 19 | 74 | Clustering is not anomaly detection | §7 Clustering: k-means | placeholder IS19-V55 | non richiesta |
| 19 | 75 | Anomaly = far from the normal data | §7 Clustering: k-means | placeholder IS19-V56 | non richiesta |
| 19 | 76 | k = 3 | §7 Clustering: k-means | placeholder IS19-V57 | non richiesta |
| 19 | 77 | Toolboxes: EDA and Clustering in Python | §7 Clustering: k-means | testo/tabella/codice trascritti | non richiesta |
| 19 | 78 | Customer data clustering: loading and plotting the data | §8 Strumenti: EDA e clustering in Python | placeholder IS19-V58 | non richiesta |
| 19 | 79 | k-means in Python | §8 Strumenti: EDA e clustering in Python | testo/tabella/codice trascritti | non richiesta |
| 19 | 80 | Clustering can be used for insights! | §8 Strumenti: EDA e clustering in Python | placeholder IS19-V59 | non richiesta |
| 19 | 81 | In the appendix: clustering gas-sensor signals | §8 Strumenti: EDA e clustering in Python | placeholder IS19-V60 | non richiesta |
| 19 | 82 | Evolutionary computing | §8 Strumenti: EDA e clustering in Python | testo/tabella/codice trascritti | non richiesta |
| 19 | 83 | Genetic algorithms | §9 Teoria: il calcolo evolutivo | placeholder IS19-V61 | non richiesta |
| 19 | 84 | Fuzzy vs Boolean logic | §9 Teoria: il calcolo evolutivo | placeholder IS19-V62 | non richiesta |
| 19 | 85 | Swarm Intelligence Systems | §9 Teoria: il calcolo evolutivo | placeholder IS19-V63 | non richiesta |
| 19 | 86 | Hybrid Intelligent Systems | §9 Teoria: il calcolo evolutivo | testo/tabella/codice trascritti | non richiesta |
| 19 | 87 | Hybrid Intelligent Systems | §10 Teoria: i sistemi intelligenti ibridi | placeholder IS19-V64 | non richiesta |
| 19 | 88 | Why hybrid? | §10 Teoria: i sistemi intelligenti ibridi | placeholder IS19-V65 | non richiesta |
| 19 | 89 | Examples of hybrid intelligent systems | §10 Teoria: i sistemi intelligenti ibridi | placeholder IS19-V66 | non richiesta |
| 19 | 90 | Example of Genetic Parameters Optimization | §10 Teoria: i sistemi intelligenti ibridi | placeholder IS19-V67 | non richiesta |
| 19 | 91 | Example of Genetic Parameters Optimization | §10 Teoria: i sistemi intelligenti ibridi | testo/tabella/codice trascritti | non richiesta |
| 19 | 92 | Genetic Parameters Optimization: crossover and mutations | §10 Teoria: i sistemi intelligenti ibridi | testo/tabella/codice trascritti | non richiesta |
| 19 | 93 | Main points | §10 Teoria: i sistemi intelligenti ibridi | testo/tabella/codice trascritti | non richiesta |
| 19 | 94 | Appendix | §10 Teoria: i sistemi intelligenti ibridi | testo/tabella/codice trascritti | non richiesta |
| 19 | 95 | kNN in MATLAB: cross-validation of a trained model | §10 Teoria: i sistemi intelligenti ibridi | testo/tabella/codice trascritti | non richiesta |
| 19 | 96 | Polynomial ﬁt and linear classiﬁer: MATLAB vs scikit-learn | §10 Teoria: i sistemi intelligenti ibridi | testo/tabella/codice trascritti | non richiesta |
| 19 | 97 | kNN, decision tree, naive Bayes: MATLAB vs scikit-learn | §10 Teoria: i sistemi intelligenti ibridi | testo/tabella/codice trascritti | non richiesta |
| 19 | 98 | Customer data clustering: Colab setup | §11 Appendice: materiale di laboratorio | placeholder IS19-V68 | non richiesta |
| 19 | 99 | Customer data clustering: import data and select features | §11 Appendice: materiale di laboratorio | placeholder IS19-V69 | non richiesta |
| 19 | 100 | Customer data clustering: exploration 1, .head | §11 Appendice: materiale di laboratorio | placeholder IS19-V70 | non richiesta |
| 19 | 101 | Customer data clustering: exploration 2, .shape | §11 Appendice: materiale di laboratorio | placeholder IS19-V71 | non richiesta |
| 19 | 102 | Customer data clustering: exploration 3, .info | §11 Appendice: materiale di laboratorio | placeholder IS19-V72 | non richiesta |
| 19 | 103 | Customer data clustering: exploration 4, .isnull | §11 Appendice: materiale di laboratorio | placeholder IS19-V73 | non richiesta |
| 19 | 104 | Customer data clustering: exploration 5, plt.hist | §11 Appendice: materiale di laboratorio | placeholder IS19-V74 | non richiesta |
| 19 | 105 | Gas sensor clustering: the UCI dataset | §11 Appendice: materiale di laboratorio | placeholder IS19-V75 | non richiesta |
| 19 | 106 | Gas sensor clustering: the data ﬁle and the script | §11 Appendice: materiale di laboratorio | placeholder IS19-V76 | non richiesta |
| 19 | 107 | Gas sensor clustering: EDA, table and box plots | §11 Appendice: materiale di laboratorio | placeholder IS19-V77 | non richiesta |
| 19 | 108 | Gas sensor clustering: EDA code | §11 Appendice: materiale di laboratorio | testo/tabella/codice trascritti | non richiesta |
| 19 | 109 | Gas sensor clustering: EDA, scatter matrix | §11 Appendice: materiale di laboratorio | placeholder IS19-V78 | non richiesta |
| 19 | 110 | Gas sensor clustering: EDA, t-SNE shows states | §11 Appendice: materiale di laboratorio | placeholder IS19-V79 | non richiesta |
| 19 | 111 | Gas sensor clustering: k-means code | §11 Appendice: materiale di laboratorio | testo/tabella/codice trascritti | non richiesta |
| 19 | 112 | Gas sensor clustering: clusters in sensor space | §11 Appendice: materiale di laboratorio | placeholder IS19-V80 | non richiesta |
| 20 | 1 | Intelligent Systems for Industry, Supply Chain and Environment | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 20 | 2 | Outline | §0 Panoramica | placeholder IS20-V01 | non richiesta |
| 20 | 3 | Theory: curse of dimensionality | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 20 | 4 | Adding features is not always a good idea! | §1 Teoria: la maledizione della dimensionalità | placeholder IS20-V02 | non richiesta |
| 20 | 5 | There is another reason: the curse of dimensionality | §1 Teoria: la maledizione della dimensionalità | placeholder IS20-V03 | non richiesta |
| 20 | 6 | Curse of dimensionality | §1 Teoria: la maledizione della dimensionalità | testo/tabella/codice trascritti | non richiesta |
| 20 | 7 | The “bin” classiﬁer: a simple example | §1 Teoria: la maledizione della dimensionalità | placeholder IS20-V04 | non richiesta |
| 20 | 8 | 1D →2D →3D: the bins explode | §1 Teoria: la maledizione della dimensionalità | placeholder IS20-V05 | non richiesta |
| 20 | 9 | Numbers about the curse of dimensionality | §1 Teoria: la maledizione della dimensionalità | placeholder IS20-V06 | non richiesta |
| 20 | 10 | In high dimension, “nearest” loses its meaning | §1 Teoria: la maledizione della dimensionalità | placeholder IS20-V07 | non richiesta |
| 20 | 11 | How to face the curse of dimensionality? | §1 Teoria: la maledizione della dimensionalità | placeholder IS20-V08 | non richiesta |
| 20 | 12 | Curse of dimensionality: polynomial ﬁtting | §1 Teoria: la maledizione della dimensionalità | testo/tabella/codice trascritti | non richiesta |
| 20 | 13 | From L19 to L20: what is learned? | §1 Teoria: la maledizione della dimensionalità | testo/tabella/codice trascritti | non richiesta |
| 20 | 14 | Overview: introduction to neural networks | §1 Teoria: la maledizione della dimensionalità | testo/tabella/codice trascritti | non richiesta |
| 20 | 15 | Neurons and synapses | §2 Panoramica: introduzione alle reti neurali | placeholder IS20-V09 | non richiesta |
| 20 | 16 | Neurons and synapses: scale and power | §2 Panoramica: introduzione alle reti neurali | placeholder IS20-V10 | non richiesta |
| 20 | 17 | Connections | §2 Panoramica: introduzione alle reti neurali | placeholder IS20-V11 | non richiesta |
| 20 | 18 | Analogy between biological and artiﬁcial neurons | §2 Panoramica: introduzione alle reti neurali | placeholder IS20-V12 | non richiesta |
| 20 | 19 | Artiﬁcial neural network | §2 Panoramica: introduzione alle reti neurali | placeholder IS20-V13 | non richiesta |
| 20 | 20 | Characteristics of artiﬁcial neural networks | §2 Panoramica: introduzione alle reti neurali | testo/tabella/codice trascritti | non richiesta |
| 20 | 21 | Theory: artiﬁcial neural networks | §2 Panoramica: introduzione alle reti neurali | testo/tabella/codice trascritti | non richiesta |
| 20 | 22 | Typical applications | §3 Teoria: le reti neurali artificiali | placeholder IS20-V14 | non richiesta |
| 20 | 23 | A feedforward network is a function | §3 Teoria: le reti neurali artificiali | placeholder IS20-V15 | non richiesta |
| 20 | 24 | Neural networks: radial basis function | §3 Teoria: le reti neurali artificiali | placeholder IS20-V16 | non richiesta |
| 20 | 25 | Neural network training | §3 Teoria: le reti neurali artificiali | placeholder IS20-V17 | non richiesta |
| 20 | 26 | Training and inference | §3 Teoria: le reti neurali artificiali | placeholder IS20-V18 | non richiesta |
| 20 | 27 | Deep learning networks | §3 Teoria: le reti neurali artificiali | placeholder IS20-V19 | non richiesta |
| 20 | 28 | Machine learning versus deep learning: a ﬁrst look | §3 Teoria: le reti neurali artificiali | placeholder IS20-V20 | non richiesta |
| 20 | 29 | Theory: the neuron as a computing element | §3 Teoria: le reti neurali artificiali | testo/tabella/codice trascritti | non richiesta |
| 20 | 30 | The neuron as a simple computing element | §4 Teoria: il neurone come semplice elemento di calcolo | placeholder IS20-V21 | non richiesta |
| 20 | 31 | Basic neuron model | §4 Teoria: il neurone come semplice elemento di calcolo | testo/tabella/codice trascritti | non richiesta |
| 20 | 32 | Activation functions | §4 Teoria: il neurone come semplice elemento di calcolo | placeholder IS20-V22 | non richiesta |
| 20 | 33 | Theory: perceptron | §4 Teoria: il neurone come semplice elemento di calcolo | testo/tabella/codice trascritti | non richiesta |
| 20 | 34 | Can a single neuron learn a task? | §5 Teoria: il perceptron | placeholder IS20-V23 | non richiesta |
| 20 | 35 | The perceptron goal: binary classiﬁcation | §5 Teoria: il perceptron | placeholder IS20-V24 | non richiesta |
| 20 | 36 | Linear separability in the perceptron | §5 Teoria: il perceptron | placeholder IS20-V25 | non richiesta |
| 20 | 37 | How does the perceptron learn? | §5 Teoria: il perceptron | testo/tabella/codice trascritti | non richiesta |
| 20 | 38 | The perceptron learning rule | §5 Teoria: il perceptron | testo/tabella/codice trascritti | non richiesta |
| 20 | 39 | The perceptron training algorithm in 4 steps | §5 Teoria: il perceptron | placeholder IS20-V26 | non richiesta |
| 20 | 40 | Step 3: the delta rule | §5 Teoria: il perceptron | testo/tabella/codice trascritti | non richiesta |
| 20 | 41 | Perceptron learning example: AND (1/5) | §5 Teoria: il perceptron | testo/tabella/codice trascritti | non richiesta |
| 20 | 42 | Perceptron learning example: AND (2/5) | §5 Teoria: il perceptron | testo/tabella/codice trascritti | non richiesta |
| 20 | 43 | Perceptron learning example: AND (3/5) | §5 Teoria: il perceptron | testo/tabella/codice trascritti | non richiesta |
| 20 | 44 | Perceptron learning example: AND (4/5) | §5 Teoria: il perceptron | testo/tabella/codice trascritti | non richiesta |
| 20 | 45 | Perceptron learning example: AND (5/5) | §5 Teoria: il perceptron | placeholder IS20-V27 | non richiesta |
| 20 | 46 | Two-dimensional plots of basic logical operations | §5 Teoria: il perceptron | placeholder IS20-V28 | non richiesta |
| 20 | 47 | Theory: multilayer neural networks | §5 Teoria: il perceptron | testo/tabella/codice trascritti | non richiesta |
| 20 | 48 | Multilayer neural networks | §6 Teoria: le reti neurali multistrato | placeholder IS20-V29 | non richiesta |
| 20 | 49 | What does the hidden layer hide? | §6 Teoria: le reti neurali multistrato | placeholder IS20-V30 | non richiesta |
| 20 | 50 | Learning in a multilayer network: two phases | §6 Teoria: le reti neurali multistrato | placeholder IS20-V31 | non richiesta |
| 20 | 51 | Notation for back-propagation | §6 Teoria: le reti neurali multistrato | placeholder IS20-V32 | non richiesta |
| 20 | 52 | Back-propagation, step 1: initialisation | §6 Teoria: le reti neurali multistrato | placeholder IS20-V33 | non richiesta |
| 20 | 53 | Back-propagation, step 2: activation (forward pass) | §6 Teoria: le reti neurali multistrato | placeholder IS20-V34 | non richiesta |
| 20 | 54 | Learning as walking downhill on the error | §6 Teoria: le reti neurali multistrato | placeholder IS20-V35 | non richiesta |
| 20 | 55 | Back-propagation, step 3: weights of the output layer | §6 Teoria: le reti neurali multistrato | placeholder IS20-V36 | non richiesta |
| 20 | 56 | Back-propagation, step 3: error gradient of the output layer | §6 Teoria: le reti neurali multistrato | testo/tabella/codice trascritti | non richiesta |
| 20 | 57 | Back-propagation, step 3: the hidden layer | §6 Teoria: le reti neurali multistrato | placeholder IS20-V37 | non richiesta |
| 20 | 58 | Back-propagation, step 4: iteration and stopping | §6 Teoria: le reti neurali multistrato | placeholder IS20-V38 | non richiesta |
| 20 | 59 | What the hidden layer learned: XOR solved | §6 Teoria: le reti neurali multistrato | placeholder IS20-V39 | non richiesta |
| 20 | 60 | Main problems of back-propagation | §6 Teoria: le reti neurali multistrato | placeholder IS20-V40 | non richiesta |
| 20 | 61 | The vanishing gradient | §6 Teoria: le reti neurali multistrato | placeholder IS20-V41 | non richiesta |
| 20 | 62 | The exploding gradient | §6 Teoria: le reti neurali multistrato | placeholder IS20-V42 | non richiesta |
| 20 | 63 | Toolboxes: neural networks in practice | §6 Teoria: le reti neurali multistrato | testo/tabella/codice trascritti | non richiesta |
| 20 | 64 | Many types of networks — today: perceptron, FF and RBF | §7 Strumenti: le reti neurali in pratica | placeholder IS20-V43 | non richiesta |
| 20 | 65 | A ﬁrst feedforward network in MATLAB (CODE) | §7 Strumenti: le reti neurali in pratica | placeholder IS20-V44 | non richiesta |
| 20 | 66 | Main points | §7 Strumenti: le reti neurali in pratica | testo/tabella/codice trascritti | non richiesta |
| 20 | 67 | Appendix | §7 Strumenti: le reti neurali in pratica | testo/tabella/codice trascritti | non richiesta |
| 20 | 68 | Polynomial ﬁtting in one dimension (CODE) | §8 Appendice | placeholder IS20-V45 | non richiesta |
| 20 | 69 | d | §8 Appendice | testo/tabella/codice trascritti | non richiesta |
| 20 | 70 | Theory: example of back-propagation | §8 Appendice | testo/tabella/codice trascritti | non richiesta |
| 20 | 71 | Three-layer network for the Exclusive-OR | §8 Appendice | placeholder IS20-V46 | non richiesta |
| 20 | 72 | Exclusive-OR: forward pass | §8 Appendice | testo/tabella/codice trascritti | non richiesta |
| 20 | 73 | Exclusive-OR: backward pass, output neuron | §8 Appendice | testo/tabella/codice trascritti | non richiesta |
| 20 | 74 | Exclusive-OR: backward pass, hidden neurons | §8 Appendice | testo/tabella/codice trascritti | non richiesta |
| 20 | 75 | Exclusive-OR: weights after one sample | §8 Appendice | testo/tabella/codice trascritti | non richiesta |
| 20 | 76 | Exclusive-OR: ﬁnal results | §8 Appendice | testo/tabella/codice trascritti | non richiesta |
