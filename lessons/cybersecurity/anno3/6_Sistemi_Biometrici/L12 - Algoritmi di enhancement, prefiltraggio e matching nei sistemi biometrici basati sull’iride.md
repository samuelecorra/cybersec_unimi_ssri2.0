# **Lezione 12 – Algoritmi di enhancement, prefiltraggio e matching nei sistemi biometrici basati sull’iride**

---

## **Parte A — Controllo qualità, localizzazione dell’iride e segmentazione**

### **1. Struttura generale del sistema di riconoscimento dell’iride**

Un sistema biometrico basato sull’iride segue la classica architettura proposta da Daugman, composta da diversi moduli funzionali:

- acquisizione dell’immagine dell’occhio
    
- controllo qualità del campione
    
- estrazione delle caratteristiche
    
- generazione dell’IrisCode
    
- database dei template
    
- modulo di matching
    
- decisione di identità

![](imgs/Pasted%20image%2020260218165018.png)

Durante la fase di **enrollment** il template viene creato e memorizzato, mentre durante la **identificazione** il template acquisito viene confrontato con quelli presenti nel database.

Il controllo qualità ha un ruolo fondamentale perché un IrisCode costruito su un’immagine non valida porta inevitabilmente a errori di riconoscimento.

---

### **2. Controllo qualità del campione: problema del fuoco**

La probabilità che un’immagine dell’iride sia perfettamente a fuoco è relativamente bassa per diversi motivi fisici e fisiologici:

- l’ingrandimento ottico richiesto è molto elevato
    
- l’occhio esegue micro-movimenti continui e involontari
    
- l’illuminazione deve rimanere limitata per motivi di sicurezza
    
- tempi di esposizione brevi implicano apertura maggiore e profondità di campo ridotta

Di conseguenza il sistema deve verificare automaticamente la qualità del fuoco attraverso algoritmi software.

---

### **3. Deblur e indice di fuoco**

L’effetto di sfocatura introdotto dal sistema ottico può essere parzialmente compensato tramite un filtro di **deblur**, che si comporta come l’inverso del sistema che ha prodotto la sfocatura.

![](imgs/Pasted%20image%2020260218165107.png)

Dal punto di vista del segnale, il blur attenua le componenti ad alta frequenza dell’immagine. Il filtro di deblur fa l’opposto:

- amplifica le alte frequenze
    
- aumenta il dettaglio percepito
    
- rende misurabile la nitidezza

Si utilizza una maschera (kernel) convoluzionale, ad esempio 8×8 pixel.

Il costo computazionale è molto contenuto: su un processore RISC a 300 MHz l’elaborazione di un frame monocromatico 640×480 richiede circa 15 ms, quindi può essere eseguita in tempo reale.

---

### **4. Algoritmo di stima del fuoco**

Il controllo del fuoco può essere implementato tramite un kernel derivativo.

![](imgs/Pasted%20image%2020260218165205.png)

L’algoritmo logico è:

1. acquisire il frame dell’immagine
    
2. convolvere l’immagine con il kernel
    
3. calcolare l’indice di fuoco come media dei valori assoluti dell’output
    
4. confrontare con una soglia
  
L’indice di fuoco rappresenta la quantità di dettagli fini presenti nell’immagine:

- valori alti → immagine nitida
    
- valori bassi → immagine sfocata

Se l’indice non supera la soglia, il frame viene scartato.

---

### **5. Conseguenze delle acquisizioni fuori fuoco**

Un’immagine fuori fuoco produce un IrisCode dominato dal rumore del sensore CCD.

Se tale template venisse memorizzato in fase di enrollment:

- il sistema genererebbe inevitabilmente un **false non-match**
    
- l’identità non verrebbe mai riconosciuta correttamente

![](imgs/Pasted%20image%2020260218165329.png)

Per questo motivo il controllo qualità verifica che la componente ad alta frequenza dello spettro superi una soglia minima.

---

### **6. Prefiltraggio e regolazione del contrasto**

Nei sistemi di riconoscimento dell’iride non sono generalmente necessari prefiltraggi complessi.

Talvolta si applica un **contrast stretching** per:

- migliorare la visibilità per operatori umani
    
- aumentare l’intelligibilità dell’immagine

![](imgs/Pasted%20image%2020260218165355.png)
  
Questa operazione non modifica in modo significativo le prestazioni del sistema automatico.

---

### **7. Gradienti dell’immagine e individuazione dei bordi**

Per individuare la pupilla e l’iride è utile analizzare i gradienti dell’immagine:

- gradiente orizzontale
    
- gradiente verticale
    
- modulo del gradiente
    
- gradiente radiale rispetto a un centro ipotizzato
    
![](imgs/Pasted%20image%2020260218165440.png)
  
Il gradiente radiale è particolarmente utile perché enfatizza strutture circolari come pupilla e iride.

---

### **8. Prefiltraggio gaussiano**

Prima di calcolare i gradienti si può applicare un filtro gaussiano per:

- ridurre il rumore
    
- migliorare la stabilità del rilevamento
    
- evitare falsi bordi dovuti a piccoli dettagli

![](imgs/Pasted%20image%2020260218165524.png)

L’operazione consiste nella convoluzione dell’immagine con un kernel gaussiano dipendente dalla distanza radiale.

---

### **9. Integrale circolare**

Per identificare strutture circolari si può calcolare la somma dei valori lungo un cammino circolare per diversi:

- centri
    
- raggi

![](imgs/Pasted%20image%2020260218165544.png)

Quando il cerchio coincide con un bordo reale dell’immagine, il valore dell’integrale aumenta significativamente.

Questo principio è alla base del metodo di Daugman.

---

### **10. Operatore integrodifferenziale di Daugman**

Le immagini dell’occhio presentano elevata variabilità e molti algoritmi classici di segmentazione falliscono.

Daugman propone invece un approccio basato sulla ricerca del massimo di un operatore integrodifferenziale:

- si esplorano possibili cerchi nell’immagine
    
- si calcola la derivata radiale lungo il contorno
    
- si seleziona la configurazione con risposta massima
    
L’operatore complessivamente si comporta come un rilevatore di bordi circolari.

Il termine gaussiano nel modello:

- introduce un prefiltraggio passa-basso
    
- elimina la ricerca di strutture troppo piccole
    
- stabilizza l’algoritmo

![](imgs/Pasted%20image%2020260218165631.png)

---

### **11. Prestazioni della localizzazione**

L’operatore permette:

- ottimo fitting dei contorni della pupilla e dell’iride
    
- robustezza rispetto ad altri bordi (es. palpebre)
    
- elevata precisione nella stima dei parametri geometrici

![](imgs/Pasted%20image%2020260218165721.png)

---

### **12. Estrazione delle palpebre**

Modificando l’operatore di Daugman è possibile individuare anche le palpebre.

Le palpebre possono essere modellate come porzioni di parabola, quindi:

- il cammino di integrazione diventa parabolico
    
- non più circolare
    
![](imgs/Pasted%20image%2020260218181332.png)

Questo permette di separare efficacemente l’iride dalle occlusioni.

---

### **13. Estrazione delle ciglia**

Le ciglia possono essere individuate sfruttando conoscenze a priori:

- originano dalle palpebre
    
- sono più scure dell’iride
    
- hanno dimensioni limitate
    
![](imgs/Pasted%20image%2020260218181808.png)

Si possono usare tecniche di segmentazione o trasformate wavelet.

È fondamentale eliminare tutte le regioni non appartenenti all’iride, altrimenti si inserirebbe informazione errata nel template.

---

### **14. Pipeline completa di segmentazione**

La sequenza completa di elaborazione è:

1. stima dei centri e raggi di pupilla e iride
    
2. determinazione dei parametri delle palpebre
    
3. estrazione della regione iridea
    
4. rimozione delle ciglia e delle occlusioni

![](imgs/Pasted%20image%2020260218181902.png)  

Il risultato è una regione dell’iride pulita pronta per la normalizzazione e la codifica.

---

### **15. Sintesi della Parte A**

Sono stati introdotti:

- controllo qualità delle immagini
    
- stima del fuoco
    
- localizzazione di pupilla e iride
    
- estrazione di palpebre e ciglia
    
- pipeline completa di segmentazione
    

---

## **Parte B — Algoritmi di matching dell’IrisCode**

### **16. Obiettivi della parte di matching**

Questa parte approfondisce:

- calcolo del matching tra IrisCode
    
- distribuzioni di genuini e impostori
    
- scelta della soglia decisionale
    
- similarità tra iridi geneticamente identiche
    
- numero effettivo di bit indipendenti

![](imgs/Pasted%20image%2020260220120624.png)

Alcuni concetti aggiuntivi verranno trattati nella lezione successiva.

---

### **17. Distribuzione dei bit in un IrisCode**

In un IrisCode la probabilità che ciascun bit sia:

- 1 oppure
    
- 0

è circa del 50%.

Questo significa che il codice ha quasi **massima entropia**, proprietà fondamentale per un sistema biometrico efficace.

![](imgs/Pasted%20image%2020260220120656.png)

---

### **18. Correlazioni tra bit**

Nonostante l’elevata entropia, esistono correlazioni tra bit:

- la struttura dell’iride è auto-predittiva
    
- caratteristiche radiali si ripetono su diverse corone

![](imgs/Pasted%20image%2020260220120714.png)

Di conseguenza:

- i gradi di libertà reali sono inferiori al numero totale di bit
    
- alcuni pattern hanno probabilità maggiore di altri

Questo è importante per l’analisi statistica del sistema.

---

### **19. Variabilità intraclasse**

La variabilità tra acquisizioni della stessa persona può aumentare per diversi fattori:

- rotazioni della testa
    
- variazioni dello zoom ottico
    
- dilatazione della pupilla

![](imgs/Pasted%20image%2020260220120737.png)

Il sistema deve essere robusto rispetto a queste variazioni.

---

### **20. Matching tramite distanza di Hamming**

Il confronto tra due IrisCode viene effettuato calcolando la **distanza di Hamming**.

Per due stringhe binarie A e B di lunghezza N:

- si contano i bit in disaccordo
    
- si divide per N

![](imgs/Pasted%20image%2020260220120848.png)

Tipicamente:

- N ≈ 2048 bit
  
La distanza risultante è un numero tra 0 e 1.

---

### **21. Creazione delle maschere**

Se sono presenti occlusioni (palpebre, riflessi, ciglia):

- si crea una maschera binaria che indica le zone valide
    
- le zone non informative vengono escluse dal confronto

![](imgs/Pasted%20image%2020260220120927.png)

Ogni IrisCode ha quindi:

- template
    
- mask associata

---

### **22. Uso delle maschere nel matching**

La distanza di Hamming viene calcolata solo sui bit validi:

- si applicano operazioni logiche AND e XOR
    
- si considerano solo le posizioni comuni valide

![](imgs/Pasted%20image%2020260220120957.png)

Questo evita che occlusioni influenzino il risultato.

---

### **23. Velocità del matching**

Le operazioni logiche su bit sono estremamente veloci perché:

- si eseguono su parole di 32 bit o superiori
    
- sono facilmente parallelizzabili

Prestazioni tipiche:

- 100.000 confronti al secondo su CPU 300 MHz
    
- 1 milione al secondo su server 3 GHz
    
- decine di milioni su server dedicati

Questo rende possibile l’identificazione su scala nazionale.

---

### **24. Compensazione delle rotazioni dell’IrisCode**

Due acquisizioni della stessa iride possono differire per piccoli shift del pattern:

- spostamento ±1 posizione
    
- equivalenti a ±2 bit

Per compensare:

- si eseguono più confronti con shift del template
    
- si prende il valore minimo di distanza  

Tipicamente si effettuano tre confronti:

- senza shift
    
- shift a sinistra
    
- shift a destra

![](imgs/Pasted%20image%2020260220121247.png)

---

### **25. Distribuzione della distanza di Hamming**

Le distribuzioni di distanza per confronti tra persone diverse (impostori) mostrano:

- valori concentrati intorno a circa 0.5
    
- comportamento binomiale

![](imgs/Pasted%20image%2020260220121311.png)

Utilizzando più shift (es. 7 configurazioni) si ottiene una stima più robusta.

Riducendo la soglia sotto circa 0.3:

- la probabilità di falso match diventa estremamente bassa

Questa è una delle principali ragioni della potenza dei sistemi basati sull’iride.

---

### **26. Distribuzioni genuini vs impostori**

Le distribuzioni di matching mostrano separazione molto netta tra:

- confronti genuini
    
- confronti impostori

![](imgs/Pasted%20image%2020260220121341.png)

Sono stati analizzati milioni di confronti in:

- condizioni realistiche con diversi sensori
    
- condizioni ideali di laboratorio

In entrambi i casi la separazione è eccellente, caratteristica ideale per un sistema biometrico.

---

### **27. Sintesi della Parte B**

Sono stati analizzati:

- calcolo del matching con distanza di Hamming
    
- uso delle maschere
    
- velocità computazionale
    
- compensazione delle rotazioni
    
- distribuzioni statistiche dei match score

---

### **28. Sintesi finale della lezione**

  

La lezione ha coperto l’intera catena di elaborazione successiva alla segmentazione dell’iride:

- controllo qualità delle immagini
    
- localizzazione geometrica robusta tramite operatore di Daugman
    
- estrazione delle regioni utili
    
- confronto tra IrisCode tramite distanza di Hamming
    
- proprietà statistiche e prestazioni del sistema
    

  

L’elevata entropia dell’IrisCode e la separazione netta delle distribuzioni rendono il riconoscimento dell’iride uno dei sistemi biometrici più affidabili esistenti.

---

Se vuoi, nella prossima risposta posso prepararti:

- mappa mentale completa della Lezione 12
    
- oppure riassunto da esame in 2 pagine
    
- oppure domande probabili del prof con risposte
    

  

Dimmi tu 👍