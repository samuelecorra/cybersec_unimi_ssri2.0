# **Lezione 4: Communicating Finite State Machines (CFSM)**

### 1. Motivazione: sistemi concorrenti e comunicanti

Nelle lezioni precedenti sono state studiate le macchine a stati finiti (FSM) e le loro estensioni con variabili (EFSM), strumenti fondamentali per modellare il comportamento dei sistemi software. Tuttavia, molti sistemi reali non sono costituiti da un singolo componente, ma da più entità che operano in modo concorrente e interagiscono tra loro.

In questi casi, modellare l’intero sistema mediante una singola FSM può risultare complesso e poco naturale. È spesso più efficace rappresentare il sistema come un insieme di macchine a stati finiti che operano simultaneamente e comunicano tramite scambio di messaggi.

Le **Communicating Finite State Machines (CFSM)** rappresentano quindi un modello formale per descrivere sistemi concorrenti e comunicanti. Esse sono particolarmente utilizzate nella specifica di:

- sistemi embedded;
    
- protocolli di comunicazione;
    
- sistemi distribuiti;
    
- applicazioni concorrenti.
    

Nel contesto della sicurezza software, le CFSM risultano fondamentali per modellare protocolli di autenticazione, handshake di comunicazione e sistemi multi-componente.

---

### 2. Definizione formale delle CFSM

Formalmente, una CFSM è definita come una coppia:

(C, P)

dove:

- C è un insieme finito di canali di comunicazione (message queues);
    
- P è un insieme finito di processi.
    

Ogni processo è a sua volta una macchina a stati finiti descritta dalla tupla:

(S, I, O, T)

in cui:

- S è l’insieme degli stati;
    
- I è l’insieme degli input;
    
- O è l’insieme degli output;
    
- T è l’insieme delle transizioni.
    

Le transizioni possono assumere tre forme principali:

- (s, null, s′) — transizione interna senza comunicazione;
    
- (s, c?i, s′) — ricezione di un messaggio i dal canale c;
    
- (s, c!o, s′) — invio di un messaggio o sul canale c.
    

Questa struttura consente di modellare esplicitamente la comunicazione asincrona tra processi.

---

### 3. Esempio: produttore e consumatore

Un esempio classico di CFSM è rappresentato da un sistema produttore-consumatore che comunica tramite un canale.

Il sistema comprende:

- un processo produttore che genera un segnale “ready”;
    
- un processo consumatore che riceve tale segnale;
    
- un canale di comunicazione che memorizza temporaneamente i messaggi.
    

Il produttore può passare da uno stato idle a busy e inviare un messaggio sul canale. Il consumatore riceve il messaggio e modifica il proprio stato di conseguenza.

Questo esempio dimostra come la comunicazione tra componenti possa essere modellata in modo naturale mediante CFSM.

---

### 4. Stato globale nelle CFSM

Per descrivere completamente la configurazione di un sistema CFSM è necessario considerare simultaneamente:

- lo stato di ciascun processo;
    
- il contenuto dei canali di comunicazione.
    

Per questo motivo si introduce il concetto di **stato globale**, definito come una coppia:

(θ, σ)

dove:

- θ = ⟨s₁, s₂, …, sₙ⟩ rappresenta gli stati dei processi;
    
- σ rappresenta la valutazione dei canali.
    

Ad esempio, nel sistema produttore-consumatore possono esistere stati globali come:

- entrambi idle con canale vuoto;
    
- produttore busy con canale contenente un messaggio;
    
- consumatore busy con canale vuoto.
    

Questo concetto è fondamentale per l’analisi formale del comportamento del sistema.

---

### 5. Transizioni globali nelle CFSM

Una **transizione globale** descrive il passaggio tra due configurazioni globali del sistema.

Una transizione globale può verificarsi in tre situazioni:

1. Transizione interna di un processo senza modificare i canali.
    
2. Ricezione di un messaggio da un canale, che comporta la rimozione del messaggio dalla coda.
    
3. Invio di un messaggio su un canale, che comporta l’inserimento del messaggio nella coda.
    

Formalmente, la transizione globale aggiorna:

- lo stato del processo coinvolto;
    
- il contenuto del canale, se necessario.
    

Questa formalizzazione consente di analizzare sistemi concorrenti in modo rigoroso.

---

### 6. Grafo di raggiungibilità delle CFSM

Analogamente alle FSM e alle EFSM, anche per le CFSM si definisce il **grafo di raggiungibilità**, che rappresenta tutte le configurazioni possibili del sistema.

Nel grafo di raggiungibilità:

- i nodi sono gli stati globali;
    
- gli archi sono le transizioni globali.
    

L’esempio del produttore-consumatore mostrato nelle slide evidenzia come la combinazione degli stati dei processi e dei contenuti dei canali generi un insieme di configurazioni globali.

---

### 7. CFSM estese: introduzione delle variabili

Il modello CFSM può essere ulteriormente esteso introducendo variabili interne ai processi, analogamente alle EFSM.

Una **CFSM estesa** è definita come una coppia:

(C, P)

dove ogni processo è descritto dalla tupla:

(S, I, O, V, T)

in cui V rappresenta l’insieme delle variabili.

Le transizioni possono includere:

- guardie sulle variabili;
    
- azioni di aggiornamento delle variabili;
    
- operazioni di comunicazione sui canali.
    

Questo modello consente di rappresentare sistemi distribuiti con memoria interna e logica complessa.

---

### 8. Estensione temporale delle CFSM

Un’ulteriore estensione introduce informazioni temporali nelle transizioni.

Una **CFSM temporale** consente di specificare intervalli temporali entro cui una transizione può avvenire dopo l’ingresso in uno stato.

Le transizioni possono assumere la forma:

(s, g, a | I/O, s′, [t₁, t₂])

dove l’intervallo [t₁, t₂] indica il tempo entro cui l’azione deve essere eseguita.

Questo tipo di modellazione è particolarmente importante nei sistemi real-time e nei protocolli temporizzati.

---

### 9. Esercizio applicativo: calendario elettronico

Un esempio complesso di CFSM estesa e temporale riguarda un calendario elettronico.

Il sistema include:

- un processo Calendar che memorizza data e ora corrente;
    
- un processo PeriodicTicker che genera periodicamente segnali di aggiornamento;
    
- canali di comunicazione tra processi e utente;
    
- variabili per memorizzare il tempo.
    

Il processo PeriodicTicker invia un segnale tick ogni 10 secondi, mentre il processo Calendar aggiorna l’orario entro un intervallo temporale specificato.

Questo esempio dimostra la capacità delle CFSM di modellare sistemi distribuiti con vincoli temporali e memoria interna.

---

### 10. Sintesi della lezione

In questa lezione è stato introdotto il modello delle Communicating Finite State Machines, che consente di rappresentare sistemi composti da più componenti concorrenti che comunicano tramite messaggi.

Sono stati analizzati:

- la definizione formale delle CFSM;
    
- il concetto di stato globale;
    
- il concetto di transizione globale;
    
- il grafo di raggiungibilità;
    
- le estensioni con variabili;
    
- le estensioni temporali;
    
- esempi applicativi.
    

Un aspetto fondamentale da ricordare è che il grafo di raggiungibilità di una CFSM può essere ricondotto a una FSM, e quindi tutte le proprietà teoriche delle FSM rimangono valide.

---

### 11. Prossimi passi

Le CFSM rappresentano un modello avanzato per la specifica dei sistemi concorrenti e distribuiti. Le lezioni successive approfondiranno ulteriormente tecniche di modellazione e verifica basate su questi modelli.