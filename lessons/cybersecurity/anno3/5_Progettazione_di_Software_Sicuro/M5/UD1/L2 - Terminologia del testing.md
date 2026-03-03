# **Lezione 2: Terminologia del testing**

---

### 1. Introduzione alla terminologia del testing

Per poter discutere in modo rigoroso del testing del software è necessario stabilire una terminologia condivisa e precisa. Nel dominio dell’ingegneria del software, infatti, alcuni termini possiedono significati ben definiti e consolidati, mentre altri risultano ambigui o utilizzati con sfumature diverse a seconda del contesto.

Alcuni concetti, come testing e debugging, sono generalmente intuitivi. Altri, come difetto o bug, sono formalizzati da standard riconosciuti, tra cui l’**IEEE Standard Glossary of Software Engineering Terminology**. Esistono infine termini, come errore, che possono essere utilizzati in modo non uniforme; per questo motivo è importante adottare definizioni coerenti per evitare ambiguità concettuali.

La chiarezza terminologica è fondamentale non solo per la comunicazione tra sviluppatori, ma anche per comprendere correttamente le relazioni causali tra fenomeni diversi che portano ai malfunzionamenti del software.

---

### 2. Malfunzionamento (Failure)

Un **failure**, ovvero un guasto o malfunzionamento, rappresenta il comportamento non corretto osservato durante l’esecuzione di un programma. Si tratta quindi di un fenomeno **dinamico**, legato all’osservazione del sistema in funzione.

Un esempio chiarisce il concetto. Si consideri una funzione che dovrebbe restituire il doppio del valore passato come parametro:

```c
int raddoppia(int x) {
    return x*x;
}
```

Se viene invocata la funzione con parametro 3, si osserva un malfunzionamento perché il risultato è 9 invece di 6. Tuttavia, con parametro 2 il comportamento appare corretto. Questo evidenzia che il malfunzionamento dipende dal caso di esecuzione.

Il malfunzionamento rappresenta quindi la manifestazione visibile di un problema interno al software.

---

### 3. Difetto (Fault o Bug)

Un **difetto**, detto anche anomalia, fault o bug, è un elemento del programma sorgente che non corrisponde alle aspettative o alle specifiche. Esso riguarda quindi la componente **statica** del sistema.

Nel caso precedente, il difetto consiste nell’operazione `*x` invece di `*2`. È importante osservare che:

- uno o più difetti possono causare uno o più malfunzionamenti;
    
- un programma può contenere molti difetti senza manifestare malfunzionamenti osservabili.
    

Lo scopo principale del testing è proprio quello di individuare i difetti attraverso l’osservazione dei malfunzionamenti che essi producono.

---

### 4. Errore umano (Error)

Il termine **errore** indica il fattore umano che causa la deviazione tra il software prodotto e il programma ideale. Si tratta quindi della causa originaria che genera difetti nel codice.

Un singolo errore umano può produrre uno o più difetti, che a loro volta possono generare malfunzionamenti durante l’esecuzione.

Gli errori possono avere origini diverse, tra cui:

- errori di analisi dei requisiti,
    
- errori di progettazione,
    
- errori di implementazione,
    
- errori di battitura.
    

La relazione causale fondamentale può essere riassunta come:

$$  
Errore\ umano \rightarrow Difetto\ nel\ codice \rightarrow Malfunzionamento  
$$

Questa distinzione è cruciale nella progettazione di software sicuro, poiché permette di individuare non solo il sintomo (failure) ma anche la causa profonda del problema.

---

### 5. Testing e Debugging

È importante distinguere chiaramente tra testing e debugging.

Il **testing** consiste nell’eseguire il programma con casi di test e analizzare i risultati allo scopo di individuare difetti. L’obiettivo principale è evidenziare comportamenti anomali.

Il **debugging**, invece, consiste nel processo di:

- individuazione della causa del difetto,
    
- correzione del codice,
    
- eventuale identificazione dell’errore umano che lo ha generato.
    

In sintesi, il testing scopre che esiste un problema, mentre il debugging lo risolve.

---

### 6. Scopi principali del testing

Il testing del software ha due obiettivi fondamentali.

Il primo è mettere in evidenza i difetti mediante l’osservazione dei malfunzionamenti, individuando quei comportamenti che rendono visibili i problemi presenti nel codice.

Il secondo è valutare l’affidabilità del software (**reliability**) e fornire confidenza sulla correttezza del prodotto, ad esempio attraverso i test di accettazione. In questo contesto il testing può:

- fornire evidenza dell’assenza di particolari tipi di malfunzionamenti,
    
- verificare comportamenti critici o frequenti,
    
- aumentare la fiducia nel sistema.
    

È importante notare che il testing non dimostra la correttezza assoluta, ma aumenta il livello di confidenza.

---

### 7. Tipi di testing: classificazioni principali

Esistono numerosi tipi di testing che possono essere classificati secondo diversi criteri:

- **Accessibilità al sistema**: white box, black box, grey box.
    
- **Livello di granularità**: unit, integration, system, acceptance.
    
- **Aspetti testati**: functional, robustness, performance, reliability, usability.
    
- **Momento nel ciclo di vita**: regression testing.
    

Questa molteplicità di classificazioni riflette la complessità del testing e la necessità di analizzare il software da prospettive differenti.

---

### 8. Livelli del testing

Il testing può essere organizzato in diversi livelli di granularità, ciascuno con obiettivi specifici.

Il **test di accettazione** confronta il comportamento del software con i requisiti dell’utente finale, verificando che il sistema soddisfi le esigenze operative.

Il **test di conformità** confronta l’intero comportamento del software con le specifiche dei requisiti formali.

Il **test di sistema** analizza il comportamento dell’intero sistema hardware e software considerato come un’unica entità.

Il **test di integrazione** verifica la cooperazione tra le diverse unità del sistema secondo quanto previsto dal progetto.

Il **test di unità** verifica il comportamento delle singole componenti.

Il **test di regressione** controlla il comportamento di versioni successive del software per garantire che modifiche introdotte non abbiano generato nuovi difetti.

---

### 9. Unit Testing

Il **unit testing** verifica il codice a livello di singola unità. Nei linguaggi orientati agli oggetti, come Java, l’unità tipicamente coincide con una classe e il testing riguarda i metodi.

Per ogni metodo testato si introducono elementi specifici:

- **test unit**: il metodo da testare,
    
- **test driver**: il codice che invoca il metodo con parametri appropriati,
    
- **test stub**: componenti opzionali che simulano metodi chiamati dall’unità per isolare il test.
    

La scrittura degli stub può risultare onerosa e spesso viene evitata quando non strettamente necessaria.

Un esempio chiarisce il concetto:

- `foo` è l’unità da testare,
    
- `testFoo` è il driver,
    
- `gig` può essere uno stub che simula una dipendenza.
    

Questo approccio consente di isolare il comportamento della componente senza coinvolgere l’intero sistema.

---

### 10. Testing di integrazione

Il **test di integrazione** verifica il corretto funzionamento delle interazioni tra unità diverse. I problemi più comuni riguardano:

- incompatibilità di tipi tra moduli,
    
- errori nei domini dei parametri,
    
- interpretazioni diverse dei dati condivisi.
    

Un esempio classico riguarda unità che interpretano lo stesso parametro con unità di misura diverse (secondi contro millisecondi), situazione che ha causato problemi reali in sistemi spaziali.

Esistono diverse strategie di integrazione:

- **top-down**, partendo dai livelli superiori e utilizzando stub per simulare i moduli inferiori;
    
- **bottom-up**, partendo dai moduli di base e utilizzando driver per simulare i livelli superiori;
    
- **big-bang**, integrando tutto simultaneamente.
    

Ogni strategia presenta vantaggi e svantaggi in termini di complessità e rischio.

---

### 11. Regression Testing

Il **regression testing** assume particolare importanza durante la manutenzione del software. È noto che i costi di manutenzione rappresentano circa i due terzi del costo totale del ciclo di vita, mentre lo sviluppo iniziale incide per circa un terzo.

Quando un programma viene modificato, gli obiettivi del testing sono:

- verificare che i difetti segnalati siano stati eliminati,
    
- assicurarsi che non siano stati introdotti nuovi difetti,
    
- riutilizzare i casi di test esistenti,
    
- evitare la necessità di ritestare completamente componenti non modificati.
    

Il regression testing permette quindi di confrontare il comportamento tra versioni successive garantendo stabilità evolutiva del sistema.

---

### Sintesi della lezione

In questa lezione sono state introdotte le principali definizioni della terminologia del testing, distinguendo chiaramente tra errore umano, difetto nel codice e malfunzionamento osservabile. È stata inoltre chiarita la differenza tra testing e debugging e sono stati analizzati gli scopi del testing in termini di individuazione dei difetti e valutazione dell’affidabilità.

Sono stati infine presentati i diversi livelli e tipi di testing, con particolare attenzione al unit testing, al testing di integrazione e al regression testing, evidenziando il loro ruolo nel ciclo di vita del software e nella manutenzione evolutiva.