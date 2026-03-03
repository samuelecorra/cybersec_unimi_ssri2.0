# **Lezione 2: Macchine a Stati Finiti con Output**

### 1. Estensione delle FSM: introduzione al concetto di output

Nella lezione precedente sono state introdotte le macchine a stati finiti (FSM) come modelli formali per rappresentare il comportamento dei sistemi software mediante stati e transizioni. Tuttavia, molti sistemi reali non si limitano a cambiare stato in risposta a eventi, ma producono anche risultati osservabili verso l’esterno. Per modellare tali comportamenti è necessario introdurre il concetto di output.

Una FSM con output rappresenta quindi un’estensione del modello base, in cui ogni transizione o ogni stato può essere associato a un’azione o a un valore prodotto dal sistema. Questa distinzione porta alla definizione di due modelli fondamentali:

- macchine di Mealy, in cui l’output è associato alle transizioni;
    
- macchine di Moore, in cui l’output è associato agli stati.
    

Questa differenza ha implicazioni importanti sia dal punto di vista della modellazione sia dal punto di vista implementativo.

---

### 2. Macchine di Mealy: definizione formale

Una macchina di Mealy è formalmente definita come una tupla:

(S, I, O, δ, λ)

dove:

- S è l’insieme finito degli stati;
    
- I è l’insieme finito degli eventi di input;
    
- O è l’insieme finito degli eventi di output;
    
- δ è la funzione di transizione degli stati;
    
- λ è la funzione di output.
    

Le funzioni hanno la forma:

- δ : S × I → S
    
- λ : S × I → O
    

In molti casi viene anche definito uno stato iniziale appartenente all’insieme degli stati.

La caratteristica principale delle macchine di Mealy è che l’output dipende sia dallo stato corrente sia dall’input ricevuto. Ciò consente una maggiore flessibilità e spesso una riduzione del numero di stati necessari per modellare il sistema.

---

### 3. Macchine di Moore: definizione formale

Una macchina di Moore è definita come una tupla:

(S, I, O, δ, λ, F)

dove:

- S è l’insieme degli stati;
    
- I è l’insieme degli input;
    
- O è l’insieme degli output;
    
- δ è la funzione di transizione;
    
- λ è la funzione di output associata agli stati;
    
- F è un sottoinsieme degli stati, spesso interpretato come insieme degli stati finali.
    

Nel modello di Moore l’output dipende esclusivamente dallo stato corrente:

λ : S → O

Questo significa che l’output cambia solamente quando cambia lo stato, rendendo il comportamento più semplice da analizzare ma talvolta meno efficiente dal punto di vista del numero di stati richiesti.

---

### 4. Rappresentazione grafica delle FSM di Mealy

Nei diagrammi che rappresentano macchine di Mealy, ogni arco viene etichettato con la notazione:

input / output

dove:

- la parte prima della barra rappresenta l’evento di input;
    
- la parte dopo la barra rappresenta l’evento di output.
    

Un esempio mostrato nelle slide prevede un insieme di stati S = {s1, s2, s3}, input {a, b} e output {0, 1}, con transizioni e output associati a ciascun evento.

Questo tipo di rappresentazione consente di visualizzare simultaneamente la dinamica degli stati e la produzione di output.

---

### 5. Output come azione del sistema

L’output di una FSM non deve necessariamente essere interpretato come un valore numerico o simbolico. In molti sistemi reali l’output corrisponde a una vera e propria azione eseguita dal sistema.

Ad esempio, una macchina che converte una sequenza di cifre decimali in un numero intero può produrre azioni come:

- INIT, per inizializzare una variabile;
    
- ADD, per aggiungere una cifra al valore corrente;
    
- OUT, per produrre il risultato finale.
    

Questo esempio evidenzia come le FSM possano modellare non solo sistemi logici ma anche processi computazionali concreti.

---

### 6. Rappresentazione alternativa delle macchine di Mealy

Una macchina di Mealy può essere descritta anche attraverso la tupla:

(S, I, O, T)

dove T rappresenta l’insieme delle transizioni.

Una transizione è definita come una quadrupla:

(s, i, o, s′)

in cui:

- s è lo stato sorgente;
    
- i è l’evento di input;
    
- o è l’evento di output;
    
- s′ è lo stato destinazione.
    

Questa rappresentazione è particolarmente utile nelle analisi formali e negli algoritmi di verifica automatica.

Nelle lezioni successive del corso, per semplicità, il termine FSM verrà utilizzato per indicare principalmente macchine di Mealy.

---

### 7. Limiti delle FSM: numero finito di stati

Uno dei principali limiti delle macchine a stati finiti è che esse possono rappresentare solamente un numero finito di configurazioni. Questo può diventare problematico quando si modellano sistemi complessi composti da più sottosistemi.

Quando si combinano più FSM, il numero totale di stati risultante cresce come il prodotto del numero di stati dei singoli modelli. Se si hanno FSM con k₁, k₂, …, kₙ stati, la composizione produce una FSM con:

k₁ × k₂ × … × kₙ stati

Questa crescita è esponenziale e prende il nome di esplosione dello spazio degli stati.

Nelle slide viene illustrato questo fenomeno attraverso l’esempio di un sistema composto da produttore, consumatore e magazzino (diagramma a pagina 7), dove la combinazione delle macchine produce un numero molto elevato di configurazioni possibili.

---

### 8. Superare i limiti delle FSM: estensioni e Statecharts

Per affrontare il problema della composizionalità e dell’esplosione degli stati sono state sviluppate estensioni del modello FSM. Tra queste, le Statecharts di UML rappresentano una soluzione particolarmente diffusa.

Le Statecharts introducono concetti avanzati come:

- sottomacchine;
    
- composizione sequenziale;
    
- composizione parallela;
    
- modularità.
    

Queste estensioni consentono di modellare sistemi complessi mantenendo una struttura gestibile e comprensibile.

---

### 9. Esercizio applicativo: sistema di sbarra per parcheggio

Un esempio pratico di modellazione mediante FSM riguarda un sistema di sbarra automatica per un parcheggio.

Il sistema deve soddisfare diverse condizioni:

- per l’accesso, il semaforo deve essere verde;
    
- quando la sbarra è aperta, il semaforo è rosso;
    
- dopo l’ingresso dell’auto, la sbarra si chiude e il semaforo torna verde;
    
- per l’uscita è necessario inserire un biglietto prepagato;
    
- dopo l’uscita la sbarra si richiude.
    

La soluzione utilizza eventi come:

- segnali di presenza dell’auto;
    
- inserimento del biglietto;
    
- cambiamenti del semaforo.
    

Questo esempio mostra come le FSM siano strumenti efficaci per modellare sistemi di controllo reali, inclusi quelli con implicazioni di sicurezza fisica.

---

### 10. Sintesi della lezione

In questa lezione è stata analizzata l’estensione delle macchine a stati finiti con il concetto di output. Sono stati introdotti i modelli di Mealy e Moore e le loro differenze principali.

Sono stati inoltre discussi:

- la rappresentazione formale e grafica delle FSM con output;
    
- il concetto di output come azione;
    
- il problema dell’esplosione degli stati;
    
- le estensioni del modello FSM tramite Statecharts;
    
- applicazioni pratiche attraverso esercizi.
    

Un aspetto importante da ricordare è che, nel seguito del corso, il termine FSM verrà utilizzato principalmente per indicare macchine di Mealy.

---

### 11. Prossimi passi

Il modello di FSM costituisce la base per modelli più avanzati di specifica del comportamento dei sistemi software, inclusi modelli gerarchici e concorrenti utilizzati nelle architetture moderne.

Le lezioni successive approfondiranno tali evoluzioni.