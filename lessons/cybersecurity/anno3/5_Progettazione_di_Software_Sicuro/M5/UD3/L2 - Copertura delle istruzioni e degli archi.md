# **Lezione 2: Copertura delle istruzioni e degli archi**

### 1. Criteri di test strutturali basati sulla copertura

Nel testing strutturale i criteri di test vengono definiti in relazione alla copertura del programma. Formalmente, un criterio può essere visto come una funzione:

$$  
C : P \times T \rightarrow {true, false}  
$$

dove $P$ è il programma e $T$ è il test set. L’obiettivo è stabilire se l’insieme di test è adeguato rispetto al criterio scelto.

In questa lezione vengono introdotti due criteri fondamentali:

1. copertura delle istruzioni (**statement coverage**);
    
2. copertura degli archi (**branch coverage**).
    

Entrambi i criteri si basano sulla struttura del grafo di flusso di controllo del programma.

---

### 2. Copertura delle istruzioni (Statement Coverage)

Il criterio di copertura delle istruzioni richiede che **ogni istruzione del programma venga eseguita almeno una volta** durante l’esecuzione dei test.

Formalmente, un test set $T$ è adeguato secondo questo criterio se, per ogni istruzione $s$ del programma $P$, esiste almeno un caso di test in $T$ che esegue $s$.

Si consideri ad esempio il codice:

```
if x != 0 then
    x := x + 10
else
    x := x - 10
```

Per ottenere la copertura delle istruzioni è necessario un test set che includa almeno:

- un valore con $x = 0$,
    
- un valore con $x \neq 0$.
    

Ad esempio:

$$  
T = {x=0, x=10}  
$$

---

### 3. Esempi di statement coverage

Un esempio di programma con istruzione condizionale mostra come due diversi casi di test possano coprire percorsi differenti del grafo di flusso, permettendo di eseguire tutte le istruzioni.

Un secondo esempio con un ciclo `while` evidenzia che anche strutture iterative possono essere completamente coperte con un opportuno valore di input, ad esempio $x=1$ per garantire almeno una iterazione del ciclo.

Un terzo esempio dimostra che anche programmi più complessi possono essere completamente coperti con un singolo caso di test, purché tale caso attraversi tutte le istruzioni.

Questo evidenzia che la copertura delle istruzioni non dipende dal numero di test, ma dalla loro capacità di attraversare tutte le parti del codice.

---

### 4. Capacità di individuazione dei difetti

Un aspetto importante nella valutazione di un criterio di copertura è la sua **fault detection capability**, cioè la capacità di individuare difetti.

Nel caso dello statement coverage:

- errori presenti nelle istruzioni eseguite vengono generalmente individuati;
    
- errori nelle condizioni decisionali potrebbero non essere individuati.
    

Per questo motivo il criterio è considerato relativamente debole. Tuttavia, in ambito industriale può essere considerato un **criterio minimo accettabile**, ad esempio nel software commerciale.

---

### 5. Misura della copertura delle istruzioni

La copertura delle istruzioni può essere misurata quantitativamente. Si definisce:

$$  
C_0 = \frac{\text{numero di istruzioni eseguite}}{\text{numero di istruzioni eseguibili}}  
$$

Un test set può quindi soddisfare il criterio solo parzialmente. In questo caso il valore numerico rappresenta il grado di copertura raggiunto.

Ad esempio, se un test esegue 5 istruzioni su 6 eseguibili, la copertura sarà:

$$  
\frac{5}{6}  
$$

Questo approccio trasforma il criterio in una metrica di qualità del testing.

---

### 6. Copertura degli archi (Branch Coverage)

Il criterio di copertura degli archi richiede che **ogni arco del grafo di flusso di controllo venga percorso almeno una volta**.

Considerando il codice:

```
if x < 0 then
    x++
endif
```

per ottenere la copertura dei branch sono necessari almeno due casi di test:

- uno con $x < 0$,
    
- uno con $x \geq 0$.
    

Ad esempio:

$$  
T = {x=-3, x=10}  
$$

Formalmente, un test set soddisfa il branch coverage se ogni ramo decisionale del programma viene eseguito almeno una volta.

---

### 7. Differenza tra statement coverage e branch coverage

Il branch coverage è più forte dello statement coverage.

Infatti:

- se un test set soddisfa il branch coverage, allora soddisfa automaticamente anche lo statement coverage;
    
- il contrario non è sempre vero.
    

Questo accade perché il branch coverage richiede di attraversare tutte le alternative decisionali, mentre lo statement coverage richiede solo l’esecuzione delle istruzioni.

---

### 8. Misura della copertura degli archi

Analogamente allo statement coverage, anche il branch coverage può essere misurato quantitativamente.

Si definisce:

$$  
C_{path} = \frac{\text{numero di archi eseguiti}}{\text{numero di archi eseguibili}}  
$$

Ad esempio, se un programma contiene due rami possibili e un test ne percorre solo uno, la copertura sarà del 50%.

---

### 9. Generazione dei casi di test per la copertura

La generazione di un test set per soddisfare un criterio di copertura segue generalmente una procedura sistematica:

1. costruire il grafo di flusso del programma;
    
2. individuare i percorsi necessari per soddisfare il criterio;
    
3. determinare i valori di input che inducono tali percorsi;
    
4. ottimizzare il test set eliminando casi ridondanti.
    

Il passo più complesso è la determinazione degli input che producono un determinato percorso.

---

### 10. Metodo pratico per trovare gli input

Un metodo intuitivo consiste nel lavorare all’indietro lungo il percorso desiderato:

- si impone come vera la condizione finale del percorso;
    
- si procede a ritroso nel grafo;
    
- se si incontra un’assegnazione si sostituisce la variabile con il valore assegnato;
    
- se si incontra una decisione vera si aggiunge la condizione;
    
- se si incontra una decisione falsa si aggiunge la negazione della condizione.
    

Questo metodo consente di costruire vincoli sugli input necessari per ottenere la copertura desiderata.

---

### 11. Limiti nella generazione dei test

In alcuni casi le condizioni necessarie per coprire un determinato percorso possono risultare:

- molto complesse;
    
- non risolvibili algoritmicamente;
    
- insoddisfacibili.
    

Ad esempio, una condizione come:

$$  
x > 0 \land x < 0  
$$

è impossibile da soddisfare. In tali casi il percorso non è copribile e potrebbe indicare la presenza di codice non raggiungibile, che a sua volta può rappresentare un difetto del programma.

---

### Sintesi della lezione

In questa lezione sono stati introdotti due criteri fondamentali del testing strutturale: la copertura delle istruzioni e la copertura degli archi. È stato mostrato che la copertura degli archi è più forte della copertura delle istruzioni e che entrambi i criteri possono essere misurati quantitativamente.

Sono state inoltre presentate tecniche per generare casi di test in grado di soddisfare un determinato criterio di copertura, evidenziando le difficoltà teoriche e pratiche legate alla complessità delle condizioni di esecuzione.

Questi criteri costituiscono la base per criteri di copertura più avanzati che verranno studiati nelle lezioni successive.