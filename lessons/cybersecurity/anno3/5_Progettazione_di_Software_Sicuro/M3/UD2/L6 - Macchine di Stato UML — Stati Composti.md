# **Lezione 6: Macchine di Stato UML — Stati Composti**

### 1. Introduzione agli stati composti in UML

Nelle lezioni precedenti sono state studiate le macchine di stato UML con stati semplici. Tuttavia, nei sistemi software complessi è spesso necessario modellare comportamenti articolati che non possono essere rappresentati efficacemente mediante una singola struttura piatta di stati.

Per questo motivo UML introduce il concetto di **stato composto**, cioè uno stato che contiene al proprio interno una macchina di stato completa. In altre parole, uno stato può essere raffinato in sottostati e transizioni interne, permettendo una rappresentazione gerarchica del comportamento.

Questo approccio consente di modellare sistemi complessi mediante livelli successivi di dettaglio, migliorando la modularità e la leggibilità dei modelli.

Un esempio mostrato nelle slide riguarda uno stato “LampFlashing” che contiene due sottostati interni “FlashOn” e “FlashOff”, con transizioni temporizzate tra essi.

---

### 2. Stati composti sequenziali

Uno stato composto sequenziale rappresenta una situazione in cui l’oggetto attraversa una sequenza interna di sottostati in modo ordinato.

Quando il sistema entra nello stato composto:

- viene attivata la macchina interna;
    
- l’esecuzione procede attraverso i sottostati fino al completamento;
    
- al termine può essere generato un evento di completamento.
    

Questo modello è particolarmente utile per rappresentare procedure multi-fase o workflow strutturati.

---

### 3. Transizioni di completamento

Le **transizioni di completamento** sono attivate da un evento speciale chiamato _completion event_, generato automaticamente quando:

- termina una macchina annidata;
    
- termina un’attività interna.
    

Un esempio mostrato nelle slide riguarda un processo di commit con due fasi interne (Phase1 e Phase2) che, una volta completate, producono un evento di completamento che consente la transizione allo stato successivo.

Questo meccanismo consente di modellare processi sequenziali senza dover esplicitare eventi artificiali.

---

### 4. Ordine di esecuzione delle azioni nei cambiamenti gerarchici

Quando avviene una transizione tra stati che coinvolge strutture gerarchiche, l’ordine di esecuzione delle azioni segue regole precise.

Nel caso di transizione tra sottostati appartenenti a stati composti diversi, la sequenza tipica è:

1. esecuzione delle azioni di uscita dello stato più interno;
    
2. esecuzione delle azioni di uscita degli stati contenitori;
    
3. esecuzione delle azioni della transizione;
    
4. esecuzione delle azioni di ingresso degli stati contenitori;
    
5. esecuzione delle azioni di ingresso dello stato più interno.
    

Questo ordine garantisce coerenza nel comportamento del sistema e deve essere sempre considerato nella progettazione.

---

### 5. Regole di attivazione delle transizioni

In alcune situazioni possono essere abilitate contemporaneamente più transizioni in risposta allo stesso evento. UML stabilisce una regola fondamentale:

- hanno priorità le transizioni più interne.
    

Questo significa che la macchina privilegia il comportamento più specifico rispetto a quello più generale, seguendo la struttura gerarchica degli stati.

---

### 6. Stati History

Gli **stati History** rappresentano un meccanismo per ricordare lo stato interno precedentemente attivo di uno stato composto.

Quando si ritorna nello stato composto tramite una transizione history:

- il sistema riprende dall’ultimo sottostato visitato;
    
- non riparte necessariamente dallo stato iniziale.
    

Questo meccanismo è particolarmente utile nei sistemi che possono essere sospesi e successivamente ripresi, come procedure diagnostiche o processi multi-fase.

Un esempio nelle slide mostra uno stato di diagnosi con più sottostati, da cui è possibile uscire temporaneamente e poi riprendere dal punto precedente tramite uno pseudostato History.

---

### 7. Stati composti paralleli

Uno stato composto può contenere più **regioni ortogonali**, cioè più macchine di stato interne che operano concorrentemente.

Le regioni parallele:

- osservano gli stessi eventi;
    
- reagiscono simultaneamente;
    
- rappresentano comportamenti concorrenti dello stesso oggetto.
    

Questo modello è particolarmente utile quando un sistema deve eseguire più attività indipendenti in parallelo.

---

### 8. Esempio: superamento di un esame

Un esempio applicativo riguarda un corso universitario che richiede:

- due laboratori propedeutici;
    
- un progetto;
    
- una prova finale.
    

Tutti questi elementi devono essere completati con successo per superare il corso. Se la prova finale fallisce, il corso non è superato.

Questo scenario è naturalmente modellabile mediante regioni parallele, poiché più attività possono evolvere indipendentemente ma devono sincronizzarsi per raggiungere l’obiettivo finale.

---

### 9. Interazione tra diagrammi di stato paralleli

La sincronizzazione tra regioni parallele può avvenire mediante diversi meccanismi:

#### Sincronizzazione tramite condizioni

Una guardia può dipendere dallo stato di un’altra macchina o da variabili condivise.

#### Sincronizzazione tramite eventi

Le azioni di un diagramma possono generare eventi che attivano transizioni in altri diagrammi.

#### Sincronizzazione tramite stati di sincronizzazione

Gli **synch states** rappresentano punti in cui più flussi devono convergere o divergere.

Un esempio mostrato nelle slide riguarda un processo di costruzione con più attività parallele che devono sincronizzarsi prima di procedere alla fase successiva.

---

### 10. Interazione tramite eventi: esempio telecomando

Un esempio significativo di interazione tramite eventi riguarda un telecomando che può controllare una televisione o un videoregistratore.

Il comportamento dipende dalla modalità corrente:

- in modalità controllo TV, l’evento di accensione accende la televisione;
    
- in modalità controllo VCR, lo stesso evento accende il videoregistratore.
    

Il diagramma mostrato nelle slide evidenzia la coordinazione tra le macchine di stato dei dispositivi e quella del telecomando mediante eventi condivisi (diagramma a pagina 7).

---

### 11. Differenze rispetto alle FSM classiche

Le macchine di stato UML con stati composti introducono caratteristiche avanzate rispetto alle FSM tradizionali:

- gerarchia degli stati;
    
- concorrenza tramite regioni parallele;
    
- memoria tramite stati history;
    
- eventi complessi e sincronizzazione;
    
- modularità e raffinamento progressivo.
    

Queste proprietà rendono UML particolarmente adatto alla modellazione di sistemi software complessi e distribuiti.

---

### 12. Sintesi della lezione

In questa lezione sono stati introdotti i concetti di stati composti sequenziali e paralleli nelle macchine di stato UML.

Sono stati analizzati:

- la gerarchia degli stati;
    
- le transizioni di completamento;
    
- l’ordine delle azioni nei cambiamenti gerarchici;
    
- gli stati history;
    
- le regioni parallele;
    
- i meccanismi di sincronizzazione;
    
- esempi applicativi.
    

Un aspetto fondamentale da ricordare è che sia una macchina che una sua sottomacchina possono reagire allo stesso evento, ma l’evento viene consumato dalla sottomacchina.

---

### 13. Prossimi passi

Nelle lezioni successive verrà approfondito come modellare sistemi complessi tramite raffinamento progressivo utilizzando macchine di stato UML.