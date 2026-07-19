## ***Lezione 2: Problemi di esecuzione con CPU pipeline***

---

> 📌 Questa lezione rielabora integralmente le pagine 39–48 di `M5doc.pdf`: dipendenze di controllo, dati e risorse, bolle, branch prediction, forwarding, cache Harvard e riordinamento del codice.

### **1. Dipendenze e hazard**

La sovrapposizione funziona soltanto se le istruzioni possono avanzare indipendentemente. Un legame fra istruzioni o una risorsa condivisa può costringere uno stadio ad attendere. Il PDF distingue tre categorie:

| Dipendenza | Causa | Conseguenza tipica |
| --- | --- | --- |
| Control dependency | un salto cambia la prossima istruzione | fetch di istruzioni sbagliate |
| Data dependency | una istruzione usa un dato prodotto da una precedente | operando non ancora disponibile |
| Resource dependency | più stadi richiedono la stessa risorsa | conflitto strutturale |

In terminologia architetturale tali problemi sono anche chiamati **hazard** di controllo, dati e struttura.

### **2. Control dependency**

Supponiamo che l'istruzione $I_0$ sia un branch verso $I_{20}$. Prima che il salto sia risolto, la pipeline può aver già prelevato e parzialmente elaborato $I_1$, $I_2$ e $I_3$:

| Ciclo prima della risoluzione | Fetch | Decode | Execute | Write Back |
| ---: | --- | --- | --- | --- |
| 1 | $I_0$ | — | — | — |
| 2 | $I_1$ | $I_0$ | — | — |
| 3 | $I_2$ | $I_1$ | $I_0$ | — |
| 4 | $I_3$ | $I_2$ | $I_1$ | $I_0$ |

Se il salto è preso, le istruzioni 1–3 appartengono al cammino sbagliato. Non devono produrre alcun effetto architetturale.

### **3. Flush, bolle e penalità del salto**

La CPU deve:

1. invalidare le istruzioni posteriori al branch presenti nella pipeline;
2. impedire i loro write back o accessi con effetti permanenti;
3. impostare il `PC` alla destinazione corretta;
4. riavviare il fetch da $I_{20}$.

Lo **svuotamento della pipeline**, o *flush*, lascia intervalli senza istruzioni valide, detti **bolle** (*bubbles*). Occorre un nuovo transitorio di riempimento e il vantaggio può ridursi fino a zero per quella sequenza.

> ⚠️ Il lavoro parziale delle istruzioni sul percorso errato non può essere “completato per comodità”: qualunque modifica a registri o memoria renderebbe scorretto il programma.

### **4. Salti incondizionati riconosciuti presto**

Il PDF osserva che una quota significativa delle istruzioni di un programma può essere costituita da salti. Un branch incondizionato non richiede di valutare una condizione: se `FCU` riconosce già opcode e destinazione, può redirigere presto il fetch senza attendere il write back.

Anticipare la decisione riduce il numero di istruzioni errate entrate in pipeline e quindi la penalità, ma richiede che l'informazione sul salto sia disponibile nello stadio di fetch.

### **5. Predizione dei salti condizionati**

Un salto condizionato può essere risolto solo dopo aver calcolato la condizione. Per non attendere, la CPU **predice** il cammino e continua speculativamente.

Il PDF combina due idee:

- informazione dinamica sui salti già incontrati;
- regola statica per la prima occorrenza.

#### **5.1. Branch Prediction Table**

La **Branch Prediction Table** (`BPT`) è cercata associativamente usando l'indirizzo del branch. Una voce contiene:

| Campo | Contenuto |
| --- | --- |
| Branch address | indirizzo dell'istruzione di salto |
| Branch destination | indirizzo di destinazione |
| Statistics | stato che riassume gli esiti precedenti, per esempio `T`/`NT` |

Se la voce esiste:

- previsione `T` (*taken*): si effettua subito il fetch dalla destinazione;
- previsione `NT` (*not taken*): si continua in sequenza.

Quando `ECU` risolve il salto, aggiorna lo stato statistico. Se la previsione era errata, si blocca il percorso sbagliato, si effettua il flush e si riparte dall'indirizzo corretto.

#### **5.2. Prima occorrenza**

Se il branch non è ancora nella BPT, il PDF propone:

- salto all'indietro: prevedere *taken*, perché spesso chiude un ciclo;
- salto in avanti: prevedere *not taken*, perché spesso implementa un ramo `if` alternativo al cammino sequenziale.

È una euristica, non una certezza. La nuova voce viene poi corretta sulla base degli esiti reali.

### **6. Data dependency**

Consideriamo:

```text
I0: AND R0, R0, #0
I1: ADD R1, R1, R0
```

`I0` azzera `R0`, ma il valore diventa architetturalmente visibile nei GPR soltanto al suo write back. `I1` richiede `R0` già all'inizio dell'execute, quando `I0` potrebbe non averlo ancora scritto.

Questa è una dipendenza **Read After Write** (`RAW`):

$$
I_0\text{ scrive }R0
\quad\land\quad
I_1\text{ legge }R0.
$$

Senza contromisure, `I1` deve attendere introducendo uno stallo. In una CPU sequenziale il problema non compare perché `I0` termina prima che inizi `I1`.

### **7. Data forwarding**

Il risultato di `I0` è spesso disponibile in `ECU` prima di essere scritto dai circuiti di write back. Il **data forwarding**, o *bypassing*, crea un percorso diretto:

$$
\text{uscita ECU dell'istruzione produttrice}
\longrightarrow
\text{ingresso ECU dell'istruzione consumatrice}.
$$

La logica confronta i registri sorgente della nuova istruzione con il registro destinazione delle istruzioni ancora in volo. In caso di uguaglianza seleziona il valore inoltrato invece di quello vecchio letto dal banco registri.

Nel caso precedente, l'ALU produce già zero al termine di `E0`; quel valore viene passato all'ALU per `E1`, che può sommarlo a `R1` senza attendere `WB0`.

> ⚠️ Il forwarding risolve soltanto i casi nei quali il dato esiste in tempo. Un caricamento da memoria può rendere il valore disponibile troppo tardi e richiedere comunque uno o più cicli di stallo.

### **8. Resource dependency**

Il PDF usa il frammento:

```text
I0: LD  R0, var
I1: AND R1, R1, #0
I2: ADD R1, R0, R0
```

Durante lo stesso intervallo:

- `FCU` vuole usare bus e memoria per il fetch di `I2`;
- `ECU` vuole leggere `var` per completare `I0`.

Se esiste un solo percorso verso la memoria, le due operazioni non possono avvenire simultaneamente. Lo stadio deve essere dilatato o una delle istruzioni deve attendere: è un **conflitto strutturale**.

### **9. Organizzazione Harvard modificata**

La soluzione illustrata separa all'interno della CPU:

- una cache L1 per le istruzioni, con bus indirizzi, dati e controllo dedicati;
- una cache L1 per i dati o variabili, con un secondo insieme di bus.

Così `FCU` può leggere l'istruzione mentre `ECU` accede a una variabile. È una realizzazione interna di tipo **Harvard**, con spazi e percorsi distinti vicino alla CPU.

All'esterno delle due L1 il sistema può mantenere una memoria di lavoro unificata e un solo bus di sistema: per questo si parla comunemente di architettura Harvard modificata.

### **10. Limiti della separazione delle cache**

La doppia L1 elimina il conflitto ordinario fra fetch e accesso ai dati, ma non tutti i conflitti:

- un miss di entrambe le cache può richiedere contemporaneamente il livello inferiore condiviso;
- execute e write back possono contendere il percorso dati;
- più operazioni di memoria ravvicinate possono superare il numero di porte disponibili.

Poiché i miss L1 sono statisticamente meno frequenti degli hit, il conflitto sul bus esterno ha un impatto minore rispetto a quello che si avrebbe con una sola cache e un solo bus interno.

### **11. Compilazione ottimizzante**

Una parte degli hazard può essere ridotta prima dell'esecuzione. Un compilatore o riordinatore del codice sposta istruzioni indipendenti fra produttore e consumatore oppure fra due accessi alla stessa risorsa.

Esempio concettuale:

```text
LOAD R1, [A]
ADD  R2, R1, R3
```

può diventare, se `XOR` è indipendente:

```text
LOAD R1, [A]
XOR  R4, R4, R5
ADD  R2, R1, R3
```

Il lavoro utile di `XOR` occupa un ciclo che altrimenti sarebbe uno stallo. Il riordinamento deve preservare dipendenze, eccezioni ed effetti osservabili: non è lecito spostare arbitrariamente `LOAD` e `STORE`.

### **12. Quadro riepilogativo**

| Problema | Esempio | Soluzione principale nel PDF |
| --- | --- | --- |
| Control dependency | branch preso dopo fetch sequenziale | riconoscimento precoce, BPT, branch prediction, flush su errore |
| Data dependency | `ADD` legge un registro non ancora scritto | stallo o data forwarding |
| Resource dependency | fetch e load usano lo stesso bus | cache L1 separate e bus duplicati |
| Conflitto residuo | più accessi dati ravvicinati | compilazione ottimizzante e riordinamento sicuro |

> ✅ La pipeline aumenta il throughput mantenendo più istruzioni in volo, ma rende visibili legami che una CPU sequenziale nascondeva. Predizione, forwarding, duplicazione delle risorse e scheduling del codice riducono le bolle senza cambiare il risultato architetturale del programma.
