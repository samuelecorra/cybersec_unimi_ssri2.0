# **Lezione 4: Macchine a stati finiti in Java**

---

### 1. Ripasso delle macchine a stati finiti (FSM)

Le macchine a stati finiti (Finite State Machines, FSM) sono modelli formali già introdotti nelle unità didattiche precedenti e ampiamente utilizzati nella progettazione di sistemi hardware e software, in particolare nei sistemi di controllo e nei protocolli.

Una FSM è caratterizzata da:

- un insieme finito di stati;
- un insieme di transizioni tra stati;
- un insieme di input (eventi);
- un insieme di output (azioni).

Il comportamento della macchina è determinato dalla combinazione tra lo stato corrente e l’evento ricevuto. Ogni transizione può inoltre generare un’azione.

La domanda centrale della lezione è come tradurre una FSM in codice Java in modo strutturato, modulare e sicuro.

---

### 2. Esempio: sistema Turnstile (tornello)

Per illustrare l’implementazione, viene considerato un sistema reale: un tornello di accesso (turnstile), tipicamente presente nelle stazioni o nei sistemi di trasporto.

Il comportamento del tornello dipende dagli eventi:

- inserimento di moneta;
- passaggio dell’utente.

Per separare responsabilità e migliorare la modularità, viene introdotta una classe controller che rappresenta le azioni fisiche del tornello:

```java
public interface TurnstileController {
    public void lock();
    public void unlock();
    public void thankyou();
    public void alarm();
}
````

La classe che rappresenta la FSM mantiene un riferimento al controller:

```java
public class Turnstile {
    private TurnstileController turnstileController;

    public Turnstile(TurnstileController tsc) {
        turnstileController = tsc;
    }
}
```

Questa separazione tra logica di stato e azioni concrete è un principio fondamentale nella progettazione software sicura e modulare.

---

### 3. Prima implementazione della FSM in Java

Una prima soluzione consiste nel rappresentare stati ed eventi tramite costanti intere e mantenere lo stato corrente in una variabile.

```java
public class Turnstile {

    public static final int LOCKED = 0;
    public static final int UNLOCKED = 1;

    public static final int COIN = 0;
    public static final int PASS = 1;

    int state = LOCKED;
}
```

La gestione delle transizioni viene realizzata mediante un metodo:

```java
public void event(int event)
```

che utilizza istruzioni `switch/case` annidate per determinare il comportamento in base allo stato corrente e all’evento ricevuto.

---

### 4. Metodo event e gestione delle transizioni

Il metodo `event` implementa la logica della FSM:

- lo stato corrente determina quali eventi sono validi;
    
- l’evento determina il nuovo stato;
    
- vengono eseguite le azioni associate.
    

Esempio:

```java
public void event(int event) {
    switch (state) {
        case LOCKED:
            switch (event) {
                case COIN:
                    state = UNLOCKED;
                    turnstileController.unlock();
                    break;
                case PASS:
                    turnstileController.alarm();
                    break;
            }
            break;

        case UNLOCKED:
            ...
    }
}
```

Con Java moderno è possibile migliorare la leggibilità utilizzando `enum` invece di interi:

```java
public enum State { LOCKED, UNLOCKED }
```

È inoltre consigliabile aggiungere un caso `default` per gestire errori o situazioni inattese.

---

### 5. Limiti dell’approccio switch/case

Questo approccio presenta importanti svantaggi strutturali.

Se la macchina possiede:

- M stati;
    
- N eventi;
    

il metodo deve gestire M × N combinazioni.

Le conseguenze sono:

- bassa modularità;
    
- difficoltà di manutenzione;
    
- codice poco estendibile.
    

Ad esempio:

- aggiungere uno stato richiede modifiche al metodo principale;
    
- aggiungere un evento richiede modifiche in tutti i casi.
    

Per sistemi complessi questo approccio diventa rapidamente ingestibile.

---

### 6. Introduzione allo State Pattern

Per risolvere i problemi di modularità si introduce lo **State Pattern**, uno dei design pattern fondamentali dell’ingegneria del software.

L’idea centrale è rappresentare ogni stato come una classe separata.

La macchina a stati mantiene un riferimento allo stato corrente tramite polimorfismo. Quando lo stato cambia, cambia l’oggetto referenziato.

Lo stato corrente diventa responsabile di:

- gestire l’evento;
    
- decidere il prossimo stato;
    
- attivare le azioni.
    

Questo approccio migliora la modularità e la manutenibilità, anche se aumenta la quantità di codice.

---

### 7. Definizione formale dello State Pattern

Lo State Pattern ha lo scopo di:

> permettere a un oggetto di cambiare comportamento quando cambia il suo stato interno.

Il pattern è applicabile quando:

- il comportamento dipende dallo stato;
    
- il codice utilizza istruzioni condizionali basate sullo stato.
    

La soluzione consiste nel:

- definire una classe concreta per ogni stato;
    
- definire una interfaccia comune per gli stati;
    
- delegare allo stato corrente la gestione degli eventi;
    
- permettere allo stato di cambiare lo stato della macchina.
    

---

### 8. Struttura delle classi del Turnstile

La struttura generale prevede:

- una classe FSM che rappresenta la macchina;
    
- una interfaccia per gli stati;
    
- sottoclassi per ogni stato;
    
- un controller per le azioni.
    

La classe FSM contiene:

- riferimento allo stato corrente;
    
- riferimento al controller;
    
- metodi che delegano gli eventi allo stato.
    

---

### 9. Classe TurnstileFSM

La classe principale rappresenta la macchina a stati:

```java
class TurnstileFSM {

    private TurnstileController turnstileController;
    private TurnstileState state = ...;

    public void coin() {
        state.coin(this);
    }

    public void pass() {
        state.pass(this);
    }

    void thankyou() { turnstileController.thankyou(); }
    void alarm() { turnstileController.alarm(); }
    void lock() { ... }
    void unlock() { ... }
}
```

La macchina delega completamente la gestione degli eventi allo stato corrente.

---

### 10. Gerarchia degli stati

Ogni stato implementa una interfaccia comune:

```java
interface TurnstileState {
    void coin(TurnstileFSM t);
    void pass(TurnstileFSM t);
}
```

Le classi concrete rappresentano stati specifici:

```java
class LockedState implements TurnstileState { ... }
class UnlockedState implements TurnstileState { ... }
```

Ogni stato viene implementato come singleton, cioè con una sola istanza:

```java
public static TurnstileState LOCKEDSTATE = new LockedState();
```

Questo riduce l’uso di memoria e garantisce consistenza.

---

### 11. Gestione delle transizioni nello stato

La logica di transizione viene implementata nei metodi dello stato.

Esempio:

```java
public void coin(TurnstileFSM t) {
    t.setState(UnlockedState.UNLOCKEDSTATE);
    t.unlock();
}
```

Lo stato:

- decide il nuovo stato;
    
- invoca le azioni sul controller;
    
- modifica la macchina.
    

La macchina FSM viene passata come parametro per consentire queste operazioni.

---

### 12. Estensione della FSM

Lo State Pattern facilita l’estensione del sistema.

Se si aggiunge uno stato:

- si crea una nuova sottoclasse che implementa l’interfaccia.
    

Se si aggiunge un evento:

- si aggiunge un metodo all’interfaccia;
    
- tutte le classi stato implementano il comportamento.
    

Questo approccio migliora notevolmente la modularità rispetto allo switch/case.

---

### 13. Tool automatici per FSM

Esistono strumenti che permettono di definire FSM in modo formale e generare automaticamente il codice.

Un esempio è:

**SMC — State Machine Compiler**

Questo strumento:

- prende una descrizione testuale della FSM;
    
- genera codice in vari linguaggi (C, C++, Java, PHP, ecc.).
    

L’uso di tool automatici riduce errori umani e aumenta la sicurezza del software.

---

### 14. Sintesi della lezione

In questa lezione è stato mostrato come implementare una macchina a stati finiti in Java utilizzando lo State Pattern.

I concetti principali sono:

- la FSM è rappresentata da una classe che delega:
    
    - le azioni a un controller;
        
    - le transizioni a una gerarchia di stati;
        
- ogni stato è una sottoclasse che implementa i metodi per ogni evento;
    
- la logica di transizione è incapsulata nello stato corrente;
    
- lo State Pattern migliora modularità e manutenibilità rispetto allo switch/case.
    

---

### Prossimi passi

Nelle lezioni successive verrà approfondita la trasformazione dalla specifica al codice e l’uso di modelli per ottenere implementazioni sicure.