# **Lezione 7: Macchine di Stato UML — Stati Semplici**

### 1. Introduzione alle macchine di stato UML

Le macchine di stato UML rappresentano uno dei principali strumenti di modellazione dinamica previsti dal linguaggio UML. Esse consentono di descrivere il comportamento temporale di un oggetto durante il suo ciclo di vita, modellando le diverse configurazioni in cui può trovarsi e le transizioni tra tali configurazioni.

Una macchina di stato UML è sempre associata a una classe e descrive il comportamento delle istanze di quella classe. In altre parole, rappresenta come un oggetto evolve nel tempo in risposta a eventi interni o esterni.

Dal punto di vista formale, una macchina di stato UML è un grafo in cui:

- i nodi rappresentano gli stati dell’oggetto;
    
- gli archi rappresentano le transizioni tra stati.
    

La notazione adottata da UML deriva dal formalismo delle **Statecharts di Harel**, che costituiscono un’evoluzione delle macchine a stati finiti classiche, introducendo concetti più avanzati come gerarchia, concorrenza e modularità.

---

### 2. Concetto di stato in UML

Uno stato rappresenta una condizione di esistenza dell’oggetto durante la quale esso:

- attende un evento;
    
- esegue determinate azioni;
    
- svolge attività nel tempo.
    

Le azioni associate agli stati possono essere di tre tipi principali:

- **entry action**, eseguita quando l’oggetto entra nello stato;
    
- **exit action**, eseguita quando l’oggetto esce dallo stato;
    
- **do activity**, attività che si svolge per tutta la permanenza nello stato.
    

È importante distinguere tra azioni e attività:

- le azioni sono operazioni atomiche e non interrompibili;
    
- le attività richiedono tempo e possono essere interrotte da una transizione.
    

La notazione UML prevede inoltre stati iniziali e stati finali, che rappresentano rispettivamente l’inizio e la conclusione del comportamento modellato.

---

### 3. Concetto di transizione

Una transizione rappresenta il passaggio da uno stato a un altro. Essa è etichettata con informazioni che specificano:

- l’evento che causa la transizione;
    
- eventuali condizioni (guardie);
    
- le azioni eseguite durante il passaggio.
    

Una transizione si attiva quando:

- si verifica l’evento associato;
    
- la guardia, se presente, risulta vera.
    

Nel modello UML si assume che:

- venga gestita una sola istanza di evento alla volta;
    
- un evento non abbia durata temporale.
    

Questo significa che gli eventi sono considerati istantanei e non persistenti.

---

### 4. Attività di stato e concorrenza

Le attività associate agli stati possono attivare thread concorrenti che eseguono operazioni fino al completamento dell’attività oppure fino all’attivazione di una transizione di uscita.

Un esempio tipico è un sistema di allarme che esegue continuamente una funzione di segnalazione finché non avviene un evento di disattivazione.

Questo concetto introduce una dimensione temporale e concorrente nel comportamento dell’oggetto, rendendo il modello UML più espressivo rispetto alle FSM classiche.

---

### 5. Esempio applicativo: carriera di uno studente

Un esempio significativo di macchina di stato UML riguarda la modellazione della carriera universitaria di uno studente.

Gli stati principali includono:

- in corso;
    
- fuori corso;
    
- ritirato;
    
- laureato.
    

Lo studente rimane nello stato “in corso” se supera regolarmente gli esami previsti. Diventa “fuori corso” se non completa gli esami nei tempi stabiliti. Può ritirarsi se interrompe gli studi oppure laurearsi quando completa tutti i requisiti.

L’esempio evidenzia anche la presenza di transizioni cicliche (loop transitions) sugli stati “in corso” e “fuori corso”, che potrebbero essere modellate mediante transizioni interne.

Questo tipo di modellazione è particolarmente utile per rappresentare workflow e processi con evoluzione temporale.

---

### 6. Tipologie di eventi in UML

UML distingue diverse categorie di eventi che possono attivare transizioni:

#### Eventi di interazione

- **Call event**: si verifica quando un oggetto invoca un’operazione propria o di un altro oggetto.
    
- **Signal event**: si verifica quando un oggetto riceve un segnale da un altro oggetto.
    

#### Eventi temporali

- **Time event**: rappresenta il trascorrere del tempo o il raggiungimento di una determinata istanza temporale.
    
- Può essere espresso mediante la notazione `after(t)`.
    

#### Eventi di cambiamento

- **Change event**: si verifica quando una condizione booleana diventa vera.
    
- È espresso mediante la notazione `when(condizione)`.
    

Queste tipologie consentono di modellare sistemi reattivi complessi con vincoli temporali e condizioni dinamiche.

---

### 7. Sequenza di esecuzione delle azioni durante una transizione

Quando avviene una transizione tra stati, le azioni vengono eseguite secondo un ordine preciso:

1. vengono eseguite le azioni di uscita dello stato sorgente;
    
2. vengono eseguite le azioni della transizione;
    
3. vengono eseguite le azioni di ingresso dello stato destinazione.
    

Questo ordine è fondamentale per garantire coerenza nel comportamento del sistema.

Un esempio mostrato nelle slide riguarda il passaggio tra stati di una lampada, con messaggi di stampa e operazioni di accensione o spegnimento eseguite secondo questa sequenza.

---

### 8. Branching condizionale statico: Junction pseudostate

Il **junction pseudostate** rappresenta un punto di decisione statico in cui il percorso successivo dipende da condizioni note al momento della modellazione.

Le guardie vengono valutate prima dell’ingresso nello pseudostato e consentono di scegliere il percorso corretto.

Questo tipo di branching è utile quando la logica decisionale è deterministica e non dipende da calcoli dinamici complessi.

---

### 9. Branching condizionale dinamico: Choice pseudostate

Il **choice pseudostate** rappresenta un punto di decisione dinamico in cui le guardie vengono valutate al momento in cui il punto di decisione viene raggiunto.

A differenza del junction, il choice permette di modellare decisioni basate su valori calcolati dinamicamente durante l’esecuzione, come il risultato di una funzione.

Questo meccanismo aumenta la flessibilità espressiva del modello UML.

---

### 10. Differenze tra UML state machines e FSM classiche

Le macchine di stato UML contengono molte più informazioni rispetto alle FSM classiche ed estese.

Tra le principali differenze troviamo:

- presenza di azioni entry, exit e do;
    
- supporto a eventi complessi;
    
- gestione esplicita del tempo;
    
- supporto a gerarchia e concorrenza (nelle lezioni successive);
    
- integrazione con il modello a oggetti.
    

Questo rende le macchine di stato UML particolarmente adatte alla progettazione software moderna.

---

### 11. Sintesi della lezione

In questa lezione sono state introdotte le macchine di stato UML con stati semplici come strumento di modellazione dinamica del comportamento degli oggetti.

Sono stati analizzati:

- il concetto di stato;
    
- il concetto di transizione;
    
- le azioni e le attività;
    
- i tipi di eventi;
    
- la sequenza di esecuzione delle azioni;
    
- i pseudostati di decisione;
    
- esempi applicativi.
    

Un aspetto fondamentale da ricordare è che una macchina di stato UML è sempre associata a una classe e descrive il comportamento delle sue istanze.

---

### 12. Prossimi passi

Nella lezione successiva verrà approfondito come esprimere in UML il concetto di sottomacchina sequenziale e parallela, introducendo quindi modelli più complessi basati su gerarchia e concorrenza.