# **Lezione 3: Macchine a Stati Finiti Estese (EFSM)**

### 1. Motivazione delle FSM estese: il problema della memoria interna

Nelle lezioni precedenti sono state introdotte le macchine a stati finiti (FSM) come modelli formali per rappresentare il comportamento dei sistemi mediante stati e transizioni. In tali modelli, le operazioni del sistema vengono descritte tramite eventi di input che producono cambiamenti di stato ed eventualmente output.

Tuttavia, in molti sistemi reali risulta indispensabile modellare anche una memoria interna, cioè informazioni che persistono nel tempo e influenzano il comportamento futuro della macchina. Le FSM tradizionali non dispongono naturalmente di variabili interne: lo stato rappresenta l’unica memoria disponibile.

Per superare questa limitazione vengono introdotte le **macchine a stati finiti estese**, abbreviate EFSM (Extended Finite State Machines), che estendono il modello FSM introducendo il concetto di variabile.

Le EFSM permettono quindi di rappresentare sistemi più complessi mantenendo una struttura concettuale simile a quella delle FSM.

---

### 2. Definizione formale delle EFSM

Formalmente, una macchina a stati finiti estesa è definita come una tupla:

(S, I, O, V, T)

dove:

- S è l’insieme finito degli stati;
    
- I è l’insieme finito degli eventi di input;
    
- O è l’insieme finito degli eventi di output;
    
- V è l’insieme finito delle variabili;
    
- T è l’insieme finito delle transizioni.
    

Ogni transizione è definita come una tupla:

(s, i, o, g, a, s′)

in cui:

- s è lo stato sorgente;
    
- i è l’evento di input;
    
- o è l’evento di output;
    
- g è una guardia, cioè un predicato sulle variabili;
    
- a è un’azione, cioè un’assegnazione a una variabile;
    
- s′ è lo stato destinazione.
    

La guardia rappresenta una condizione che deve essere soddisfatta affinché la transizione possa avvenire, mentre l’azione descrive come vengono aggiornate le variabili durante la transizione.

Questa struttura consente di modellare sistemi con comportamento dipendente dai dati interni.

---

### 3. Esempio: salvadanaio elettronico

Un esempio significativo di EFSM è rappresentato dal modello di un salvadanaio elettronico.

Il sistema presenta le seguenti caratteristiche:

- l’inserimento di una moneta incrementa il valore della variabile coin;
    
- l’emissione di monete decrementa tale valore;
    
- una luce diventa rossa quando si inseriscono monete e blu quando si richiedono monete;
    
- la macchina non restituisce monete quando è vuota.
    

Gli stati del sistema sono:

- empty;
    
- not-empty.
    

La variabile coin rappresenta la quantità di denaro contenuta nel salvadanaio. Le transizioni includono condizioni (guardie) come coin = 1 oppure coin > 1, e azioni di aggiornamento come coin := coin + 1 o coin := coin − 1.

Questo esempio mostra chiaramente come le EFSM permettano di modellare sistemi in cui il comportamento dipende non solo dallo stato logico ma anche dai valori delle variabili interne.

---

### 4. Formalizzazione del modello del salvadanaio

La formalizzazione matematica dell’esempio prevede:

- S = {empty, not-empty};
    
- I = {inc, dec};
    
- O = {red, blue};
    
- V = {coin};
    
- T = insieme delle transizioni definite mediante guardie e azioni.
    

Questa rappresentazione consente di applicare tecniche formali di analisi e verifica anche a sistemi con memoria interna.

---

### 5. Concetto di stato globale

In una EFSM, lo stato del sistema non è rappresentato solo dallo stato logico, ma anche dai valori delle variabili interne.

Per questo motivo si introduce il concetto di **stato globale**, definito come una coppia:

(s, σ)

dove:

- s è uno stato;
    
- σ è una valutazione delle variabili.
    

Ad esempio:

- (empty, coin = 0);
    
- (not-empty, coin = 1).
    

Lo stato globale rappresenta quindi la configurazione completa del sistema in un determinato momento.

---

### 6. Concetto di transizione globale

Analogamente allo stato globale, si definisce il concetto di **transizione globale**, che descrive il passaggio tra configurazioni complete del sistema.

Una transizione globale è una tupla:

((s, σ), i, o, (s′, σ′))

tale che esiste una transizione locale (s, i, o, g, a, s′) per cui:

- la valutazione σ soddisfa la guardia g;
    
- la nuova valutazione σ′ deriva dall’applicazione dell’azione a.
    

Ad esempio:

((empty, coin = 0), inc, red, (not-empty, coin = 1))

Questa formalizzazione consente di analizzare in modo preciso l’evoluzione del sistema.

---

### 7. Grafo di raggiungibilità

Un concetto fondamentale nelle EFSM è il **grafo di raggiungibilità**, definito come un grafo orientato in cui:

- i nodi rappresentano gli stati globali;
    
- gli archi rappresentano le transizioni globali.
    

Il grafo di raggiungibilità descrive tutte le configurazioni possibili che il sistema può assumere durante l’esecuzione.

Un risultato importante è che, se le variabili assumono valori in un dominio finito, il grafo di raggiungibilità di una EFSM può essere ricondotto a una FSM tradizionale. Ciò significa che tutte le proprietà teoriche delle FSM rimangono applicabili.

---

### 8. Esempio di grafo di raggiungibilità

Nel caso del salvadanaio elettronico, il grafo di raggiungibilità include stati globali come:

- empty con coin = 0;
    
- not-empty con coin = 1;
    
- not-empty con coin = 2;
    

e così via, a seconda dei valori possibili della variabile coin.

Il diagramma mostrato nelle slide evidenzia come le transizioni tra stati globali dipendano sia dagli input sia dai valori della variabile.

---

### 9. Esercizio applicativo: dispositivo pompa di calore / condizionatore

Un esercizio applicativo riguarda la modellazione di un dispositivo che può funzionare sia come pompa di calore sia come condizionatore.

Il sistema presenta le seguenti caratteristiche:

- lo stato iniziale è spento;
    
- la modalità viene impostata tramite un selettore inverno/estate;
    
- il dispositivo memorizza l’ultima modalità anche quando è spento;
    
- in modalità pompa la temperatura iniziale è 20 gradi;
    
- in modalità condizionatore la temperatura iniziale è 24 gradi;
    
- la temperatura può essere modificata mediante telecomando.
    

La soluzione utilizza:

- variabili per rappresentare la temperatura;
    
- stati per rappresentare le modalità operative;
    
- transizioni per rappresentare eventi come accensione, spegnimento e variazioni di temperatura.
    

Questo esempio evidenzia come le EFSM siano particolarmente adatte alla modellazione di dispositivi con memoria e configurazioni persistenti.

---

### 10. Sintesi della lezione

In questa lezione è stato introdotto il modello delle macchine a stati finiti estese, che rappresenta un’evoluzione delle FSM tradizionali mediante l’introduzione delle variabili interne.

Sono stati analizzati:

- la definizione formale delle EFSM;
    
- il concetto di guardia e azione;
    
- il concetto di stato globale;
    
- il concetto di transizione globale;
    
- il grafo di raggiungibilità;
    
- esempi applicativi.
    

Un aspetto fondamentale da ricordare è che il grafo di raggiungibilità di una EFSM può essere ricondotto a una FSM tradizionale quando le variabili hanno dominio finito, e quindi tutte le proprietà teoriche delle FSM rimangono valide.

---

### 11. Prossimi passi

Le EFSM costituiscono la base per modelli ancora più avanzati utilizzati nella progettazione di sistemi complessi, inclusi modelli gerarchici, concorrenti e basati su statecharts.

Le lezioni successive approfondiranno tali evoluzioni e le loro applicazioni nella progettazione di software sicuro.