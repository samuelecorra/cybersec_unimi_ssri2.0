# **Lezione 3: Copertura delle decisioni e delle condizioni**

### 1. Introduzione ai nuovi criteri di copertura strutturale

Dopo aver studiato i criteri di copertura delle istruzioni e degli archi, vengono introdotti due ulteriori criteri di testing strutturale che analizzano più in profondità la logica delle decisioni presenti nel codice:

1. copertura delle decisioni;
    
2. copertura delle condizioni.
    

Questi criteri permettono di valutare in modo più preciso la qualità del testing, in particolare nei punti del programma in cui il flusso di controllo dipende da espressioni booleane.

---

### 2. Decisioni e condizioni: definizioni

È fondamentale distinguere tra **decisione** e **condizione**.

Una **decisione** è un predicato booleano che costituisce la guardia di una struttura di controllo, come un’istruzione `if`, `while` o `for`. Ad esempio:

```
if (x > 0 || y > 0)
```

l’intera espressione `x > 0 || y > 0` rappresenta la decisione.

Una **condizione** è invece una componente atomica della decisione, cioè una sotto-espressione booleana non ulteriormente decomponibile. Nell’esempio precedente le condizioni sono:

- $x > 0$
    
- $y > 0$
    

Questa distinzione è essenziale perché i criteri di copertura analizzano separatamente questi due livelli logici.

---

### 3. Copertura delle decisioni

Il criterio di copertura delle decisioni richiede che **ogni decisione del programma venga valutata sia vera sia falsa almeno una volta** durante l’esecuzione dei test.

Formalmente, un test set $T$ è adeguato se, per ogni decisione del programma:

- esiste un caso di test che rende la decisione vera;
    
- esiste un caso di test che rende la decisione falsa.
    

Questo criterio è equivalente al **branch coverage**, poiché garantisce che ogni ramo del grafo di flusso venga percorso.

Ad esempio, per la decisione:

```
if (x > 0 || y > 0)
```

sono sufficienti due casi di test:

- $x=1, y=1$ → decisione vera;
    
- $x=-1, y=-1$ → decisione falsa.
    

---

### 4. Copertura delle condizioni

Il criterio di copertura delle condizioni richiede che **ogni condizione atomica venga valutata sia vera sia falsa almeno una volta**.

Formalmente, un test set è adeguato se, per ogni condizione:

- esiste un caso di test che la rende vera;
    
- esiste un caso di test che la rende falsa.
    

Questo criterio è più fine rispetto alla copertura delle decisioni perché analizza separatamente le singole componenti della decisione.

---

### 5. Relazione tra copertura delle condizioni e delle decisioni

Un risultato importante è che:

- la copertura delle condizioni **non implica** la copertura delle decisioni;
    
- la copertura delle decisioni **non implica** la copertura delle condizioni.
    

I due criteri sono quindi **incomparabili**.

Ad esempio, è possibile avere un test set che rende ogni condizione vera e falsa ma che non produce mai una decisione falsa complessiva. Viceversa, è possibile coprire entrambe le alternative della decisione senza rendere vera una delle condizioni atomiche.

Questo mostra che i criteri analizzano proprietà diverse del programma.

---

### 6. Copertura decisioni e condizioni combinata

Per superare i limiti dei due criteri separati si introduce il criterio combinato di **copertura delle decisioni e delle condizioni**, che richiede simultaneamente:

- copertura di tutte le decisioni;
    
- copertura di tutte le condizioni.
    

Questo criterio è più forte dei due precedenti e fornisce una maggiore probabilità di individuare difetti logici nelle espressioni booleane.

---

### 7. Short circuit evaluation

Un aspetto importante nella valutazione delle condizioni è la **short circuit evaluation** (valutazione a corto circuito), utilizzata dai compilatori per migliorare l’efficienza.

Ad esempio:

- nell’espressione `a && b`, se `a` è falsa non viene valutata `b`;
    
- nell’espressione `a || b`, se `a` è vera non viene valutata `b`.
    

Questo comportamento può complicare il testing perché alcune condizioni potrebbero non essere effettivamente valutate durante l’esecuzione.

In alcuni linguaggi esistono operatori che evitano il corto circuito (ad esempio `&` e `|`), ma non sempre vengono utilizzati.

---

### 8. Implicazioni del corto circuito sul testing

Per garantire la copertura di una condizione all’interno di una decisione con corto circuito, è necessario costruire i casi di test in modo che le condizioni precedenti non interrompano la valutazione.

Ad esempio, per testare la condizione $x>0$ nell’espressione:

$$  
y>0 \lor x>0  
$$

è necessario scegliere un caso di test in cui $y>0$ sia falso, altrimenti la seconda condizione non verrà valutata.

Questo introduce un vincolo importante nella generazione dei test.

---

### 9. Generazione dei casi di test per condizioni

La generazione dei test per coprire condizioni specifiche segue lo stesso principio generale del testing strutturale:

- si individua il percorso desiderato nel grafo di flusso;
    
- si costruiscono vincoli sugli input che garantiscono la valutazione desiderata;
    
- si risolvono i vincoli per ottenere i valori di input.
    

Quando si vuole coprire una particolare condizione, essa deve essere inclusa esplicitamente nella costruzione delle condizioni sugli input.

---

### Sintesi della lezione

In questa lezione sono stati introdotti i criteri di copertura delle decisioni e delle condizioni, distinguendo chiaramente tra decisioni (predicati booleani completi) e condizioni (componenti atomiche). È stato dimostrato che i due criteri sono incomparabili e che la loro combinazione produce un criterio più forte.

È stata inoltre analizzata l’influenza della valutazione a corto circuito sulla copertura delle condizioni e sulla generazione dei casi di test, evidenziando le difficoltà pratiche nel garantire una copertura completa delle espressioni booleane.