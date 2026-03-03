# **Lezione 2: Metodi di Specifica**

### 1. Introduzione ai linguaggi di specifica

Nel processo di progettazione del software, e in particolare nella progettazione di software sicuro, la definizione dei requisiti necessita di strumenti linguistici adeguati che consentano di rappresentare in modo chiaro, preciso e verificabile le proprietà del sistema. I linguaggi di specifica costituiscono quindi uno degli elementi fondamentali per garantire la qualità della progettazione.

I linguaggi utilizzati per la specifica possono essere classificati in tre categorie principali:

- linguaggi informali, basati sul linguaggio naturale;
    
- linguaggi semi-formali, spesso grafici, come UML;
    
- linguaggi formali, basati su notazioni matematiche rigorose.
    

Un formalismo è una notazione rigorosa che consente di descrivere sistemi e proprietà senza ambiguità interpretative. La scelta del linguaggio di specifica influenza profondamente la qualità della progettazione, la verificabilità dei requisiti e la possibilità di individuare errori nelle fasi iniziali del ciclo di vita.

---

### 2. Differenti modalità di specifica: informale, formale e grafica

Uno stesso requisito può essere espresso in modi diversi a seconda del livello di formalizzazione scelto. Ad esempio, una proprietà relativa al valore di una variabile può essere descritta:

- in modo informale mediante linguaggio naturale;
    
- in modo formale tramite logica temporale;
    
- in modo grafico attraverso rappresentazioni diagrammatiche.
    

La specifica formale mediante logica temporale consente di esprimere vincoli temporali e proprietà di evoluzione del sistema nel tempo, ad esempio indicando che una variabile assume valori entro un intervallo fino a un certo evento e che non diventa mai negativa.

Le rappresentazioni grafiche, invece, permettono una comprensione immediata del comportamento del sistema, risultando particolarmente utili nella comunicazione con stakeholder non tecnici.

Nel contesto della sicurezza software, l’utilizzo di formalismi rigorosi è particolarmente importante perché consente di dimostrare proprietà di sicurezza e prevenire vulnerabilità già nella fase di specifica.

---

### 3. Formalismi operazionali e formalismi dichiarativi

I linguaggi formali possono essere distinti in due grandi categorie: formalismi operazionali e formalismi dichiarativi.

I formalismi operazionali definiscono un sistema descrivendone il comportamento come se fosse eseguito da una macchina astratta. In altre parole, specificano il sistema attraverso le operazioni che esso compie e la sequenza di stati che attraversa durante l’esecuzione.

I formalismi dichiarativi, invece, definiscono il sistema dichiarando le proprietà che esso deve soddisfare, senza descrivere esplicitamente il processo operativo che porta al risultato.

Questa distinzione è fondamentale nella progettazione software perché rappresenta due diversi modi di modellare la realtà: uno orientato al comportamento e uno orientato alle proprietà.

---

### 4. Esempi di formalismi operazionali e dichiarativi

La differenza tra approccio operazionale e dichiarativo può essere compresa attraverso esempi concreti.

Nel caso della definizione geometrica di un’ellisse, una descrizione operazionale può basarsi sul movimento di un punto nel piano mantenendo costante la somma delle distanze da due punti fissi. Una descrizione dichiarativa, invece, si basa sull’equazione matematica che caratterizza l’ellisse.

Analogamente, per l’ordinamento di un array, una definizione operazionale descrive passo dopo passo il processo di selezione dei minimi successivi, mentre una definizione dichiarativa si limita ad affermare che il risultato è una permutazione dell’array originale che soddisfa la proprietà di ordinamento.

Nel software sicuro, le specifiche dichiarative sono spesso preferite per la verifica formale delle proprietà, mentre quelle operazionali risultano più intuitive per la progettazione e l’implementazione.

---

### 5. Formalismi operazionali: caratteristiche e vantaggi

L’approccio operazionale fornisce una rappresentazione intuitiva perché è vicino al modo naturale di ragionare degli esseri umani. Descrivere il comportamento del sistema come una sequenza di operazioni facilita infatti la comprensione e la progettazione.

Tra i principali vantaggi dei formalismi operazionali vi sono:

- facilità di realizzazione del sistema;
    
- facilità di validazione tramite simulazione o esecuzione del modello.
    

Esempi di formalismi operazionali includono:

- Abstract State Machines;
    
- metodo B;
    
- metodo Z;
    
- SCR (Software Cost Reduction);
    
- reti di Petri.
    

Questi strumenti consentono di modellare sistemi complessi, inclusi sistemi concorrenti e distribuiti, che sono tipici delle architetture software moderne.

---

### 6. Formalismi dichiarativi: caratteristiche e vantaggi

L’approccio dichiarativo offre una rappresentazione priva di ambiguità, poiché si basa su proprietà matematiche rigorose. Tuttavia, risulta generalmente più difficile da comprendere e sviluppare rispetto all’approccio operazionale.

Il principale vantaggio dei formalismi dichiarativi è la facilità di verifica. Le proprietà dichiarate possono infatti essere dimostrate mediante tecniche formali, come il model checking o la dimostrazione automatica di teoremi.

Esempi di formalismi dichiarativi includono:

- logica temporale;
    
- Trio;
    
- algebre dei processi.
    

Nel contesto della sicurezza software, questi formalismi sono particolarmente utili per verificare proprietà critiche come autenticazione, autorizzazione, integrità dei dati e assenza di condizioni di errore.

---

### 7. UML come linguaggio semi-formale di modellazione

UML (Unified Modeling Language) rappresenta oggi il principale linguaggio semi-formale utilizzato nell’ingegneria del software. Esso definisce:

- una notazione grafica;
    
- un insieme di viste organizzate in diagrammi strutturali e comportamentali;
    
- una sintassi formalizzata mediante un meta-modello;
    
- una semantica descritta principalmente in forma testuale.
    

La semantica descritta in prosa introduce inevitabilmente elementi di ridondanza, frammentarietà e ambiguità, motivo per cui UML non è considerato un linguaggio completamente formale.

Tuttavia, UML rappresenta un compromesso efficace tra formalismo e comprensibilità, risultando estremamente diffuso nella pratica industriale.

---

### 8. Modelli e diagrammi UML

Un modello UML è composto da diversi diagrammi che rappresentano differenti aspetti del sistema. Tra i principali diagrammi UML troviamo:

- diagrammi dei casi d’uso;
    
- diagrammi di stato;
    
- diagrammi delle componenti;
    
- diagrammi di deployment;
    
- diagrammi degli oggetti;
    
- diagrammi delle classi;
    
- diagrammi di sequenza;
    
- diagrammi di collaborazione;
    
- diagrammi di attività.
    

Questa varietà di diagrammi consente di analizzare il sistema da prospettive multiple, migliorando la comprensione globale della struttura e del comportamento.

---

### 9. Viste UML

UML organizza la rappresentazione del sistema attraverso diverse viste, ciascuna focalizzata su un aspetto specifico.

La Use-Case View mostra le funzionalità offerte dal sistema così come percepite dagli attori esterni. Si tratta di una visione di tipo black box, che non entra nei dettagli interni dell’implementazione.

La Logical View analizza la struttura interna del sistema e descrive come le funzionalità vengono realizzate. Questa rappresenta una visione white box che include sia aspetti statici sia dinamici.

La Component View rappresenta l’organizzazione e le dipendenze delle componenti software, fornendo una visione architetturale del sistema.

La Deployment View descrive l’allocazione del software sull’architettura hardware, evidenziando la distribuzione fisica dei componenti.

---

### 10. Relazione tra viste e diagrammi UML

Ogni vista UML è associata a specifici diagrammi:

- Use-Case View → diagrammi dei casi d’uso;
    
- Logical View → diagrammi delle classi, degli oggetti, delle interazioni, delle macchine a stati e delle attività;
    
- Component View → diagrammi delle componenti;
    
- Deployment View → diagrammi di dislocamento.
    

Questa organizzazione consente di mantenere una struttura coerente e modulare nella rappresentazione del sistema.

---

### 11. Sintesi della lezione

In questa lezione sono stati introdotti i principali metodi di specifica utilizzati nell’ingegneria del software e nella progettazione di sistemi sicuri. È stata presentata la classificazione dei linguaggi di modellazione in informali, semi-formali e formali, insieme alla distinzione tra formalismi operazionali e dichiarativi.

È stato inoltre analizzato UML come standard de facto per la modellazione del software, evidenziandone struttura, viste e diagrammi.

Un aspetto fondamentale da ricordare è che:

- gli approcci funzionali e operazionali facilitano la realizzazione e il testing del sistema;
    
- gli approcci dichiarativi facilitano l’analisi e la verifica delle proprietà.
    

UML rappresenta oggi uno strumento centrale per specificare, visualizzare e sviluppare artefatti software.