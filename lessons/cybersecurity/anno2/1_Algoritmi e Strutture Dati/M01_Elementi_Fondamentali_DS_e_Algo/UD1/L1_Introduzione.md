
Ogni grande impresa dell’informatica comincia da qui: dal capire come pensano le macchine e come ragiona un problema.  
Il Modulo 1 è la porta d’ingresso in questo mondo: ci insegna a guardare un programma non più come una sequenza di comandi, ma come una strategia di pensiero.  
Un algoritmo, in fondo, è solo questo: un piano chiaro per arrivare da un punto A a un punto B nel modo più intelligente possibile.  
Ma non tutti i piani si equivalgono: alcuni sono eleganti, rapidi, geniali; altri lenti, goffi, dispendiosi.

Questo modulo ci aiuterà a capire la differenza.  
Scopriremo cosa significa “efficienza”, come si può misurare il tempo che un algoritmo impiega, e perché certi metodi “buoni” funzionano mille volte meglio di altri, anche se all’apparenza fanno la stessa cosa.  
Inizieremo a dare un linguaggio ai concetti di velocità, ordine e logica, costruendo le basi per tutto ciò che verrà dopo: le strutture dati, i paradigmi di progettazione e, in definitiva, la capacità di pensare in modo computazionale.

Il Modulo 1 non è un manuale tecnico: è il punto in cui la curiosità incontra la matematica e la mente impara a ragionare come una macchina — senza mai smettere di essere umana.

---

## **Lezione 1 – Algoritmi, problemi computazionali e primi strumenti di rappresentazione**

Questa prima lezione è la nostra “accensione del motore”.  
Ci dà le basi per capire che cosa sia davvero un algoritmo, come si collega al concetto di problema computazionale, e quali strumenti minimi servono per rappresentare i dati su cui operiamo.  
In pratica, impariamo a distinguere il “cosa voglio ottenere” (il problema) dal “come ci arrivo” (l’algoritmo), e a tradurre tutto questo in procedure concrete, iterative o ricorsive, che la macchina possa comprendere e portare a termine. È la lezione che trasforma la curiosità in metodo: il primo passo verso il pensiero computazionale vero e proprio.

---

### 1. Algoritmo, problema e rappresentazione

Ogni volta che ci si trova davanti a un compito da risolvere — ordinare, cercare, calcolare, scegliere — si è davanti a un problema computazionale: una relazione tra dati in ingresso e risultato desiderato.  
L’algoritmo è la strategia, la sequenza chiara e finita di operazioni che, passo dopo passo, permette di ottenere la risposta giusta.  
Deve essere preciso, finito, deterministico (cioè non ambiguo) ed eseguibile da un qualsiasi esecutore dotato di regole elementari.

In altre parole, se il problema è “voglio trovare l’elemento più piccolo in un insieme di numeri”, l’algoritmo è la ricetta per farlo, passo dopo passo, in modo affidabile e ripetibile.

Un algoritmo quindi non è solo codice: è pensiero organizzato.  
Il problema definisce l’obiettivo; l’algoritmo ne è la traduzione operativa.  
Capire questa differenza è ciò che distingue chi “usa” la programmazione da chi pensa come un informatico.

---

### 2. Esempio: trovare il minimo in un intervallo di un array

Prendiamo un esempio concreto.  
Abbiamo un array di numeri, a, e vogliamo sapere qual è il valore minimo compreso tra le posizioni j e k.  
Il problema è chiaro: vogliamo il numero più piccolo in quella porzione.  
Ora serve l’algoritmo, cioè il metodo per arrivarci.

  
  

### Versione iterativa (passo dopo passo)

1. Si suppone che il primo elemento a[j] sia il minimo.  
      
    
2. Si scorrono tutti gli altri, da a[j+1] fino ad a[k].  
      
    
3. Ogni volta che si trova un elemento più piccolo, si aggiorna il minimo.  
      
    
4. Alla fine del ciclo, il valore più basso rimasto è il minimo assoluto.  
      
    

Questo modo di ragionare è sequenziale: passo dopo passo, sempre nello stesso schema.  
È semplice, lineare, intuitivo: come guardare tutti i numeri in fila finché non si trova il più piccolo.

### Versione ricorsiva (divide et impera)

La ricorsione funziona come uno specchio: la funzione chiama se stessa su un problema un po’ più piccolo, finché non arriva a un caso così semplice da poter dare subito la risposta.

In questo caso:

- Se c’è un solo elemento, è per forza il minimo.  
      
    
- Altrimenti si calcola il minimo nel sottoproblema (dall’indice j+1 a k) e lo si confronta con a[j].  
      
    
- Il più piccolo tra i due è il minimo finale.  
      
    

La ricorsione funziona se — e solo se — ogni chiamata riduce il problema e c’è un caso base chiaro che fa terminare la catena.  
È come scendere una scala togliendo un gradino ogni volta: se togli sempre un gradino, prima o poi tocchi terra.

---

### 3. Iterazione e ricorsione: due modi per pensare

Un ciclo iterativo e una funzione ricorsiva fanno spesso la stessa cosa, ma parlano due linguaggi diversi.  
L’iterazione è “meccanica”: fa ripetere una sequenza finché una condizione è vera.  
La ricorsione è “logica”: definisce un problema in termini di sé stesso, ma più piccolo.

Capire entrambi i modi di ragionare è fondamentale, perché alcuni problemi si risolvono più naturalmente in forma ricorsiva (come nel caso dei percorsi in un albero o dei problemi di tipo “divide et impera”), mentre altri restano più chiari in forma iterativa.  
Un buon programmatore deve saper scegliere la forma che rende più trasparente il pensiero.

---

### 4. I tipi di dato: i mattoni della rappresentazione

Ogni algoritmo ha bisogno di “contenitori” per conservare e manipolare le informazioni: i tipi di dato.  
In C — il linguaggio di riferimento di questo corso — questi mattoni sono numerici (come int, float, double), logici (boolean), e strutturati (come gli array, che raccolgono più elementi dello stesso tipo).

Nel nostro esempio, l’array a rappresenta l’insieme dei numeri in ingresso, e la variabile m memorizza il risultato, cioè il minimo trovato.  
La scelta del tipo di dato non è mai neutra: influenza la chiarezza, la precisione e persino l’efficienza dell’algoritmo.

---

### 5. Cosa bisogna metabolizzare ASAP

- Un problema computazionale è la relazione tra dati in ingresso e risultato atteso.  
      
    
- Un algoritmo è la sequenza ordinata di azioni che permette di calcolare quel risultato in modo finito e determinato.  
      
    
- Una procedura è l’implementazione di un algoritmo in un linguaggio di programmazione.  
      
    
- Le soluzioni iterative e ricorsive sono due modi diversi di affrontare lo stesso compito: la prima ripete, la seconda si richiama finché non raggiunge il caso base.  
      
    
- I tipi di dato sono il linguaggio con cui rappresentiamo tutto ciò che un algoritmo manipola: numeri, condizioni, insiemi di elementi.  
      
    

In sintesi, questa lezione ci ha fornito la grammatica minima del pensiero computazionale:  
sapere cosa significa “risolvere un problema”, come tradurre quella risoluzione in una forma eseguibile, e con quali strumenti rappresentare i dati del mondo digitale.

