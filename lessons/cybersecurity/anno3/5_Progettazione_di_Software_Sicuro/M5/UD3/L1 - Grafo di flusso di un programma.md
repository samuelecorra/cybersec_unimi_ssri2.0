# **Lezione 1: Grafo di flusso di un programma**

### 1. Testing strutturale basato sul codice sorgente

Nel testing basato sulla struttura dei programmi, i criteri di test vengono definiti considerando esclusivamente il codice sorgente. In questo approccio il sorgente rappresenta una fonte fondamentale di informazioni per il testing, mentre la specifica del programma viene ignorata. Ciò significa che non si tiene conto di ciò che il programma dovrebbe fare, ma solo di come è implementato.

Il codice viene utilizzato sia per generare i casi di test sia per stabilire quando il testing può essere considerato sufficiente. Questo approccio è noto come **testing strutturale** o **program-based testing**, ed è complementare al testing basato sulle specifiche.

---

### 2. Copertura del codice e flusso di controllo

I criteri di testing strutturale si basano sul concetto di **copertura del codice**, cioè sulla porzione di programma effettivamente eseguita dai casi di test.

La copertura viene analizzata principalmente rispetto alla struttura del **flusso di controllo** del programma. Il flusso di controllo rappresenta l’ordine con cui le istruzioni vengono eseguite durante l’esecuzione del programma, e costituisce l’elemento centrale per definire criteri di copertura strutturale.

Maggiore è la copertura del flusso di controllo, maggiore è la probabilità di individuare difetti presenti nel codice.

---

### 3. Il grafo del flusso di controllo

Il comportamento di un programma può essere rappresentato mediante un **grafo del flusso di controllo** (Control Flow Graph, CFG).

Il grafo del flusso di controllo è una rappresentazione grafica del codice sorgente che:

- rappresenta tutte le possibili esecuzioni del programma,
    
- ignora i valori specifici delle variabili,
    
- mostra la struttura delle istruzioni e delle decisioni.
    

Nel grafo:

- ogni istruzione è rappresentata da un nodo,
    
- i nodi sono collegati da archi che indicano il possibile passaggio di controllo all’istruzione successiva.
    

Questo tipo di rappresentazione consente di studiare il comportamento del programma in modo astratto e indipendente dagli input specifici.

---

### 4. Rappresentazione delle istruzioni semplici

Le istruzioni semplici, come assegnamenti, letture, scritture, return o chiamate a metodi esterni, vengono rappresentate mediante nodi circolari.

Ad esempio, una sequenza di istruzioni:

```
x = 10;
x = x div 2;
y = 1000;
```

produce un grafo lineare in cui ogni nodo è collegato al successivo.

Questa rappresentazione è intuitiva perché riflette direttamente la sequenza di esecuzione.

---

### 5. Rappresentazione delle decisioni: istruzioni condizionali

Le istruzioni condizionali, come l’istruzione `if`, vengono rappresentate mediante nodi decisionali a forma di rombo.

Una struttura:

```
if (cond) then P1 else P2
```

viene rappresentata con:

- un nodo decisionale che contiene la condizione,
    
- due archi di uscita marcati **true** e **false**,
    
- due nodi corrispondenti ai rami di esecuzione.
    

Il ramo `else` è opzionale se non presente nel codice.

Questa rappresentazione evidenzia i punti di biforcazione del flusso di controllo, che sono particolarmente importanti nel testing strutturale.

---

### 6. Rappresentazione dei cicli while

I cicli `while` vengono rappresentati con una struttura composta da:

- un nodo decisionale contenente la condizione,
    
- un arco **true** che conduce al corpo del ciclo,
    
- un arco **false** che conduce all’istruzione successiva,
    
- un collegamento dal corpo del ciclo alla valutazione della condizione.
    

Questa struttura rappresenta la possibilità di iterazioni multiple e il ritorno al punto di decisione.

---

### 7. Rappresentazione dei cicli do-while

Il ciclo `do-while` viene rappresentato in modo simile al ciclo `while`, ma con la differenza che la decisione è posta alla fine del corpo del ciclo.

Questo riflette la semantica del costrutto, che garantisce almeno una esecuzione del corpo prima della verifica della condizione.

---

### 8. Rappresentazione dei cicli for

I cicli `for` vengono rappresentati mediante la loro equivalente struttura `while`. In particolare:

- l’inizializzazione viene rappresentata come prima istruzione,
    
- la condizione costituisce il nodo decisionale,
    
- l’incremento viene rappresentato come ultima istruzione del corpo,
    
- dopo l’incremento si ritorna alla valutazione della condizione.
    

Questa trasformazione consente di trattare tutti i cicli con un modello uniforme.

---

### 9. Esempi di grafi di flusso di controllo

Un esempio di funzione che calcola un massimo tra due valori e lo moltiplica per un terzo parametro mostra come le istruzioni condizionali producano rami alternativi nel grafo.

Un secondo esempio con un ciclo `while` che calcola una somma iterativa evidenzia la struttura ciclica del flusso di controllo, con iterazioni ripetute fino al soddisfacimento della condizione di uscita.

Questi esempi mostrano come il grafo permetta di visualizzare chiaramente i percorsi possibili di esecuzione.

---

### 10. Il grafo come astrazione dell’esecuzione

Il grafo del flusso di controllo rappresenta un’**astrazione** del comportamento del programma. Esso:

- ignora i valori specifici delle variabili,
    
- rappresenta tutte le possibili esecuzioni,
    
- associa ogni esecuzione reale a un cammino nel grafo.
    

Per ogni computazione del programma esiste quindi una computazione corrispondente nel grafo del flusso di controllo.

Un’esecuzione concreta può essere rappresentata tracciando un percorso nel grafo, eventualmente annotato con i valori delle variabili nei diversi punti.

Questo collegamento tra esecuzione concreta e rappresentazione astratta è fondamentale per definire criteri di copertura e strategie di testing strutturale.

---

### Sintesi della lezione

In questa lezione è stato introdotto il testing strutturale basato sul codice e il concetto di copertura del programma. È stato presentato il grafo del flusso di controllo come rappresentazione astratta del comportamento del software, insieme alle regole per costruirlo a partire dalle diverse strutture di controllo del linguaggio.

È stato inoltre chiarito che ogni esecuzione del programma corrisponde a un percorso nel grafo, concetto che costituisce la base teorica per i criteri di copertura che verranno studiati nelle lezioni successive.