# **Lezione 1: Macchine a Stati Finiti di Base**

### 1. Introduzione alle Macchine a Stati Finiti

Le macchine a stati finiti, abbreviate FSM (Finite State Machines), rappresentano una delle notazioni formali più importanti per la modellazione del comportamento dei sistemi software. Esse consentono di descrivere in modo astratto come un sistema evolve nel tempo in risposta a eventi esterni o interni.

Una FSM possiede due caratteristiche fondamentali che la rendono particolarmente utile nella progettazione del software sicuro:

- una rigorosa definizione matematica, che garantisce precisione e assenza di ambiguità;
    
- una rappresentazione grafica intuitiva mediante diagrammi di stato, che facilita la comprensione anche a livello progettuale.
    

Nel contesto della sicurezza software, le FSM sono spesso utilizzate per modellare protocolli di autenticazione, sistemi di controllo degli accessi, comportamenti di componenti embedded e workflow applicativi.

---

### 2. Concetti fondamentali: stati e transizioni

Il funzionamento di una macchina a stati finiti può essere compreso attraverso esempi semplici, come quello di una lampadina controllata da un pulsante.

In questo caso:

- gli stati rappresentano le configurazioni possibili del sistema (ad esempio acceso e spento);
    
- le transizioni rappresentano il passaggio da uno stato all’altro in seguito a un evento (ad esempio la pressione del pulsante).
    

Le FSM modellano quindi:

- le configurazioni istantanee del sistema tramite stati;
    
- le operazioni o eventi tramite transizioni.
    

Le operazioni possono ricevere input e, in modelli più avanzati, produrre output. Le FSM che producono output sono note come macchine di Mealy o di Moore, che verranno studiate successivamente.

---

### 3. Ambiti di utilizzo delle FSM

Le macchine a stati finiti trovano applicazione in numerosi ambiti dell’ingegneria del software e dei sistemi:

- interfacce grafiche utente (GUI);
    
- protocolli di rete;
    
- dispositivi medici come pacemaker;
    
- sistemi bancari automatici;
    
- applicazioni web;
    
- software di sicurezza;
    
- sistemi embedded.
    

La diffusione delle FSM deriva dalla loro capacità di rappresentare in modo chiaro sistemi reattivi, cioè sistemi che rispondono a eventi provenienti dall’ambiente.

---

### 4. Limiti delle FSM nella specifica dei requisiti

Nonostante la loro utilità, le FSM non sono in grado di rappresentare tutti i tipi di requisiti. Esistono categorie di requisiti che non possono essere facilmente modellate mediante macchine a stati finiti.

Tra questi troviamo:

- requisiti real-time;
    
- requisiti legati alle prestazioni;
    
- requisiti relativi a specifici tipi di computazione complessa.
    

Questi limiti derivano dal fatto che le FSM descrivono principalmente la sequenza degli stati e delle transizioni, ma non includono naturalmente concetti quantitativi complessi o temporali continui.

---

### 5. Modellazione con FSM e livelli di astrazione

Un aspetto fondamentale della modellazione mediante FSM è che il modello non è unico. Lo stesso sistema può essere rappresentato con diversi modelli FSM a seconda del livello di astrazione scelto.

Ad esempio, nel caso di un sistema di controllo di un impianto chimico, un modello ad alto livello può limitarsi a rappresentare stati come:

- sistema acceso;
    
- sistema spento;
    
- allarme.
    

Un modello più dettagliato può invece includere stati intermedi relativi alle azioni di riduzione della pressione o della temperatura e agli eventuali fallimenti delle azioni correttive.

Questa proprietà consente di utilizzare le FSM sia per analisi preliminari sia per progettazioni più dettagliate.

---

### 6. Esempi di FSM a diversi livelli di dettaglio

Il sistema di controllo di un impianto chimico rappresenta un esempio significativo di come il livello di dettaglio influenzi la modellazione.

In una rappresentazione raffinata, il sistema può includere stati come:

- normale;
    
- off;
    
- azione di diminuzione della pressione;
    
- azione di diminuzione della temperatura.
    

Le transizioni tra questi stati dipendono da eventi come:

- allarme di pressione pericolosa;
    
- allarme di temperatura pericolosa;
    
- successo o fallimento delle azioni correttive;
    
- ritorno dei parametri entro i limiti.
    

Questo esempio evidenzia come le FSM permettano di rappresentare sistemi critici in cui la sicurezza e il controllo degli errori sono fondamentali.

---

### 7. Definizione matematica delle FSM

Formalmente, una macchina a stati finiti è definita come una tupla:

(S, I, δ)

dove:

- S è un insieme finito di stati;
    
- I è un insieme finito di eventi di input;
    
- δ è la funzione di transizione che associa uno stato e un input a un nuovo stato.
    

La funzione di transizione ha quindi la forma:

δ : S × I → S

Questa definizione matematica garantisce la precisione del modello e consente analisi formali delle proprietà del sistema.

---

### 8. Rappresentazione grafica delle FSM

Dal punto di vista grafico, una FSM è rappresentata mediante un grafo orientato:

- i nodi rappresentano gli stati;
    
- gli archi rappresentano le transizioni;
    
- le etichette sugli archi indicano gli eventi di input che causano il cambiamento di stato.
    

Questa rappresentazione grafica è particolarmente utile nella progettazione perché consente di visualizzare rapidamente il comportamento del sistema e individuare eventuali errori logici.

---

### 9. Formalizzazione di un esempio reale

Nel caso raffinato del sistema di controllo di un impianto chimico, è possibile definire formalmente:

- l’insieme degli stati;
    
- l’insieme degli input;
    
- la funzione di transizione.
    

Ad esempio, uno stato normale può passare allo stato di diminuzione della pressione in presenza di un allarme, oppure tornare allo stato normale se i parametri rientrano nei limiti.

Questo tipo di formalizzazione è essenziale nei sistemi critici, dove errori di comportamento possono avere conseguenze gravi.

---

### 10. Esercizi di modellazione con FSM

La progettazione di FSM può essere esercitata attraverso esempi pratici, come sistemi di lampade controllate da pulsanti.

Nel primo esercizio, una lampada con due bottoni si accende se uno qualsiasi dei due viene premuto quando la lampada è spenta.

Nel secondo esercizio, un sistema con due lampade e un bottone attraversa una sequenza ciclica di configurazioni:

- entrambe spente;
    
- prima accesa;
    
- seconda accesa;
    
- entrambe accese;
    
- ritorno allo stato iniziale.
    

Questi esercizi mostrano come le FSM possano rappresentare comportamenti sequenziali complessi anche con pochi stati.

---

### 11. Sintesi della lezione

In questa lezione sono state introdotte le macchine a stati finiti come metodo formale per descrivere il comportamento dei sistemi software. Sono stati analizzati:

- il concetto di stato e transizione;
    
- la definizione matematica delle FSM;
    
- la rappresentazione grafica mediante diagrammi di stato;
    
- i livelli di astrazione nella modellazione;
    
- esempi applicativi e limiti del modello.
    

È importante ricordare che non tutti i requisiti possono essere catturati mediante FSM. Tuttavia, esse rappresentano una base fondamentale su cui vengono costruiti modelli più avanzati e arricchiti.

---

### 12. Prossimi passi

Il modello di base delle macchine a stati finiti costituisce il punto di partenza per lo studio di modelli più complessi che includono ulteriori informazioni, come output, condizioni temporali e variabili interne.

Le lezioni successive approfondiranno tali estensioni.