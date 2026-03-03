# **Lezione 2: I limiti del testing**

### 1. Ripasso dei concetti fondamentali

Nella lezione precedente è stato introdotto il concetto di criterio di testing, indicato con $C$, che stabilisce se un insieme di test $T$ è adeguato per testare un programma $P$. Un criterio può anche essere utilizzato per generare automaticamente un test set.

Un criterio è detto **ideale** se, selezionando un test set secondo tale criterio, si è certi di individuare ogni difetto del programma. Questo concetto rappresenta un obiettivo teorico molto importante, ma nella pratica presenta limitazioni profonde che costituiscono il tema centrale di questa lezione.

---

### 2. Esempio di criterio valido e affidabile

Considerando il programma errato:

```c
int raddoppia(int x) {
    return x*x;
}
```

è possibile definire un criterio che selezioni almeno un valore maggiore o uguale a 3. Questo criterio risulta sia valido sia affidabile, perché consente di individuare il difetto in modo consistente.

Tuttavia, questo esempio nasconde un presupposto importante: si conosce già il comportamento atteso del programma e si sospetta già la presenza del difetto. Nella pratica reale, invece, non si dispone di tali informazioni.

La domanda fondamentale diventa quindi:

> È possibile trovare criteri che siano in generale validi e affidabili per qualunque programma?

L’unico criterio sicuramente ideale è quello esaustivo, cioè:

$$  
T = D  
$$

ma tale approccio risulta impraticabile.

---

### 3. Limiti del testing esaustivo

Il testing esaustivo è generalmente:

- **impraticabile**, perché lo spazio degli input è troppo grande;
    
- **impossibile**, perché non sempre è possibile distinguere se un programma si è bloccato oppure se è ancora in esecuzione.
    

Si consideri un esempio:

```c
int foo(int x) {
    y = very_complex_computation(x);
    write(y);
}
```

Se la funzione interna contiene un bug che provoca un ciclo infinito, diventa impossibile stabilire quanto tempo sia necessario attendere prima di concludere che il programma non terminerà.

Questo problema è direttamente collegato al **problema dell’arresto (halting problem)**, che dimostra l’impossibilità di determinare algoritmicamente, in generale, se un programma terminerà per un dato input.

---

### 4. Teorema di Howden: impossibilità di trovare test ideali

Un risultato teorico fondamentale è il **teorema di Howden**, che afferma che non esiste un algoritmo che, dato un programma $P$, sia in grado di generare un test ideale finito.

Questo risultato ha implicazioni profonde:

- non è possibile automatizzare completamente la generazione di test perfetti,
    
- non esiste una procedura generale che garantisca la scoperta di tutti i difetti.
    

Il testing rimane quindi inevitabilmente un’attività euristica e non completamente algoritmica.

---

### 5. Dijkstra e il limite concettuale del testing

Un’altra affermazione fondamentale, attribuita a Edsger Dijkstra, stabilisce che:

> Il testing può dimostrare la presenza di malfunzionamenti ma non può dimostrarne l’assenza.

Questo principio rappresenta uno dei pilastri teorici dell’ingegneria del software. Anche se un programma supera numerosi test, non è possibile garantire che sia corretto in tutti i casi possibili.

Di conseguenza, il testing non è uno strumento di prova formale della correttezza, ma un mezzo per aumentare la fiducia nel comportamento del sistema.

---

### 6. Il vero obiettivo del testing

Alla luce dei limiti teorici, il testing deve essere interpretato correttamente.

Il testing non consiste nel dimostrare che un programma è privo di difetti, ma nel cercare di individuarne la presenza. In questo senso, un test di successo non è quello che non trova errori, ma quello che individua un bug.

Questa prospettiva è particolarmente importante nella sicurezza informatica, dove l’obiettivo è scoprire vulnerabilità prima che possano essere sfruttate.

---

### 7. Criteri di testing empirici

Poiché criteri ideali non sono generalmente realizzabili, nella pratica si utilizzano **criteri empirici**, cioè criteri che non sono né completamente validi né completamente affidabili, ma che si sono dimostrati utili nell’esperienza pratica.

Questi criteri svolgono anche il ruolo di **regola di arresto (stopping rule)**, cioè forniscono un’indicazione su quando il testing può essere considerato sufficiente per avere una ragionevole fiducia nel programma.

L’adeguatezza del testing non viene più considerata come un valore booleano, ma come una misura di copertura:

$$  
P \times S \times T \rightarrow [0,1]  
$$

dove il valore rappresenta il grado di copertura raggiunto.

---

### 8. Teorema di Weyuker e problemi indecidibili

Ulteriori limiti teorici del testing emergono dal **teorema di Weyuker**, che dimostra l’indecidibilità di diversi problemi legati alla copertura del codice.

Ad esempio, non esiste un algoritmo generale che possa determinare se esiste un input che:

- esegue una particolare istruzione,
    
- percorre un determinato cammino di esecuzione,
    
- esegue tutte le istruzioni del programma.
    

Questi risultati mostrano che molte proprietà desiderabili del testing non sono risolvibili in modo generale mediante algoritmi.

---

### 9. Generazione pratica dei test

Nonostante l’indecidibilità teorica, nella pratica esistono algoritmi e tecniche che funzionano per molti programmi reali.

Il problema di trovare un insieme di input che esegua tutte le istruzioni non è computabile in generale, ma può essere risolto in molti casi concreti.

Sono stati sviluppati strumenti automatici per la generazione di test, come ad esempio CUTE e altri strumenti commerciali. Questi strumenti non garantiscono il successo in ogni situazione, ma risultano efficaci in numerosi contesti applicativi.

---

### Sintesi della lezione

In questa lezione sono stati analizzati i limiti teorici del testing del software. È stato dimostrato che non esiste un algoritmo generale per generare test ideali e che il testing non può garantire l’assenza di difetti, ma soltanto rilevarne la presenza. Sono stati introdotti i concetti di criteri empirici e di misura della copertura, insieme ai risultati teorici sull’indecidibilità di alcuni problemi di testing.

Nonostante questi limiti teorici, il testing rimane uno strumento fondamentale nella pratica dell’ingegneria del software, supportato da tecniche e strumenti che risultano efficaci nella maggior parte dei casi reali.