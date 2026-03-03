## **Lezione 2: Programmare con le code**

---

### **1. Obiettivo della lezione**

L’obiettivo è **utilizzare gli operatori fondamentali della coda** per risolvere un problema computazionale concreto, applicando lo schema di progettazione algoritmica introdotto nel Modulo 1 (“Soluzione di un semplice problema computazionale”).

Impareremo a modellare un sistema che simula l’esecuzione di lavori, gestiti secondo una politica di accesso **FIFO (First In, First Out)**.

---

### **2. Il problema del servente**

#### **Descrizione**

Consideriamo un sistema formato da:

- un **servente**, ovvero un’unità di calcolo che esegue un lavoro per volta;
    
- una **lista di lavori in attesa**, gestita come una **coda FIFO**.
    

Ogni lavoro richiede un **tempo fisso** per essere completato.  
Il servente preleva il **primo lavoro disponibile** (il più vecchio nella coda) e lo esegue.

#### **Obiettivo**

Scrivere una procedura `C` che simuli il comportamento del sistema per un intervallo temporale $T$, restituendo:

1. la lista (coda) dei lavori rimasti in attesa;
    
2. il numero totale di lavori completati.
    

---

### **3. Visualizzazione del processo**

#### **Schema logico**

$$  
\text{Arrivo lavoro} ;\longrightarrow; \text{Inserimento in coda} ;\longrightarrow;  
\text{Servente preleva testa} ;\longrightarrow; \text{Esecuzione lavoro}  
$$

#### **Esempio**

|Tempo|Azione|Coda (testa → fondo)|
|---|---|---|
|0|Arriva lavoro A|A|
|1|Arriva lavoro B|A → B|
|2|Servente esegue A|B|
|3|Arriva lavoro C|B → C|
|4|Servente esegue B|C|

> La logica è esattamente **FIFO**: il primo arrivato è sempre il primo servito.

---

### **4. Complessità del problema**

#### **Dimensione dell’input**

Nel caso pessimo, il tempo $T$ permette l’esecuzione di tutti gli $n$ lavori.  
Poiché ogni lavoro deve essere estratto una volta dalla coda:

$$  
\Omega(n)  
$$

#### **Eventi contabili**

Ogni esecuzione completa comporta almeno $n$ operazioni di estrazione:

$$  
\Omega(n)  
$$

---

### **5. Idea dell’algoritmo**

All’interno di un ciclo `while` saranno gestite tre attività principali:

1. **Avanzamento del tempo**
    
2. **Arrivo di nuovi lavori**, che vengono accodati in $L$
    
3. **Gestione del servente**, che preleva e svolge il lavoro in testa
    

Si assume l’esistenza di una **lista temporale di eventi** che supporta due operatori:

- `eventoSucc(t)` → restituisce il prossimo evento temporale
    
- `inserisciEvento(id_evento, t)` → inserisce un evento ordinato per tempo
    

---

### **6. Algoritmo iterativo**

```c
int simula_servente_FIFO(coda *L, tempo T) { 
    int numLav = 0;
    evento *E;
    tempo t = 0;

    while (t <= T) {
        E = eventoSucc(t);
        t = E->t;

        switch (E->id_evento) {
            case NEW_JOB:
                arrivoLavoro(E, L, t);
                break;

            case SERVENTE_LIBERO:
                servente(E, L, &numLav, t);
                break;
        }
    }

    return numLav;
}
```

> Il ciclo `while` rappresenta l’avanzamento del tempo discreto:  
> a ogni passo, si elabora un evento tra l’arrivo di un nuovo lavoro e la liberazione del servente.

---

### **7. Procedure principali**

#### **Procedura `arrivoLavoro`**

```c
void arrivoLavoro(evento *E, coda *L, tempo t) {
    tempo delay = random_number();
    incoda(E->job, L);
    inserisciEvento(NEW_JOB, E, t + delay);
}
```

> Ogni volta che arriva un nuovo lavoro, esso viene **inserito in fondo alla coda**.  
> Poi viene pianificato il successivo evento di arrivo con un ritardo casuale.

---

#### **Procedura `servente`**

```c
void servente(evento *E, coda *L, int *nL, tempo t) {
    int job;

    (*nL)++; // contatore lavori svolti

    if (!codavuota(L)) {
        job = leggicoda(L);
        fuoricoda(L);
        inserisciEvento(SERVENTE_LIBERO, E, t + tempo(job));
    } else {
        inserisciEvento(SERVENTE_LIBERO, E, t + WAIT_TIME);
    }
}
```

> Se la coda non è vuota, il servente preleva il primo lavoro in testa (`leggicoda`) e lo rimuove (`fuoricoda`).  
> Se non ci sono lavori, resta in attesa per un intervallo di tempo predefinito.

---

### **8. Analisi della complessità**

#### **Ipotesi**

- La coda $L$ non si svuota mai (benchmark server)
    
- Il ciclo `while` viene eseguito $n$ volte
    

#### **Costo delle operazioni**

- Operatori su coda → $O(1)$
    
- Operatori su lista temporale →
    
    - Estrazione evento: $O(1)$
        
    - Inserimento in lista ordinata: $O(n)$
        

#### **Complessità totale**

$$  
T(n) = O(n^2)  
$$

> L’algoritmo non è ottimale, ma è un modello didatticamente utile di **simulazione a eventi discreti**, che riproduce fedelmente un sistema FIFO reale.

---

### **9. Sintesi conclusiva**

|Aspetto|Descrizione|
|---|---|
|**Struttura dati**|Coda FIFO|
|**Scenario simulato**|Servente che gestisce lavori in ordine di arrivo|
|**Operatori usati**|`incoda`, `fuoricoda`, `leggicoda`|
|**Complessità**|$O(n^2)$|
|**Significato didattico**|Modello base per la simulazione discreta di eventi|

---

> Con questa lezione si chiude il **Modulo 2 – Organizzazioni dati di tipo lineare**.  
> Abbiamo visto come **liste, pile e code** incarnino tre logiche diverse di accesso ai dati (sequenziale, LIFO, FIFO),  
> costituendo la base concettuale di tutte le strutture dati dinamiche e degli algoritmi che le utilizzano.


---

## **Scheda riassuntiva – Liste, Pile e Code**

---

### **1. Visione generale**

|Struttura|Politica di accesso|Inserimento|Rimozione|Esempio reale|
|---|---|---|---|---|
|**Lista**|Accesso sequenziale libero|In qualunque posizione|In qualunque posizione|Rubrica, playlist modificabile|
|**Pila**|**LIFO (Last In, First Out)**|In testa|In testa|Pila di piatti|
|**Coda**|**FIFO (First In, First Out)**|In fondo|In testa|Fila alla mensa|

---

### **2. Rappresentazioni grafiche**

#### **Lista**

$$  
a_1 \longrightarrow a_2 \longrightarrow a_3 \longrightarrow a_4  
$$

- Ogni elemento punta al successivo
    
- Accesso sequenziale: per leggere $a_3$, occorre passare da $a_1$ e $a_2$
    
- Inserimenti e cancellazioni possibili ovunque
    

---

#### **Pila (LIFO)**

$$  
\text{Top} \longrightarrow a_3 \longrightarrow a_2 \longrightarrow a_1  
$$

- Solo la cima (`top`) è accessibile
    
- Operazioni:
    
    - `inpila(a)` → aggiunge in cima
        
    - `fuoripila()` → rimuove dalla cima
        
- Utile per gestire **chiamate ricorsive**, **undo/redo**, **backtracking**
    

---

#### **Coda (FIFO)**

$$  
\text{Head} \longrightarrow a_1 \longrightarrow a_2 \longrightarrow a_3 \longrightarrow \text{Tail}  
$$

- Accesso ai soli estremi: testa (rimozione) e fondo (inserimento)
    
- Operazioni:
    
    - `incoda(a)` → aggiunge in fondo
        
    - `fuoricoda()` → rimuove dalla testa
        
- Modella sistemi a **servizio sequenziale** (processi, reti, eventi)
    

---

### **3. Confronto tra le realizzazioni**

|Caratteristica|Lista|Pila|Coda|
|---|---|---|---|
|**Politica di accesso**|Libera|LIFO|FIFO|
|**Accesso casuale**|✗|✗|✗|
|**Inserimento dinamico**|✓|Solo in testa|Solo in fondo|
|**Cancellazione dinamica**|✓|Solo in testa|Solo in testa|
|**Implementazione possibile con**|Puntatori o cursori|Liste o vettori|Liste o vettori circolari|
|**Costo medio operazioni**|$O(1)$|$O(1)$|$O(1)$|
|**Esempi d’uso tipici**|Editor di testo, playlist|Stack delle chiamate, parsing|Scheduler, sistemi di rete|

---

### **4. Sintesi concettuale**

#### **Lista**

> Struttura generica e flessibile.  
> Base per costruire pile, code e molte altre strutture complesse.

#### **Pila**

> Rappresenta la **memoria temporanea** di un processo.  
> È la forma strutturata dello **stack** di esecuzione.

#### **Coda**

> Garantisce l’**ordine temporale** dei dati.  
> È la forma algoritmica della **giustizia**: chi arriva per primo, viene servito per primo.

---

### **5. Schema visivo comparato**

```text
LISTA:   [a1] -> [a2] -> [a3] -> [a4]

PILA:    [a1]
          ↑
          [a2]
          ↑
          [a3]   (top)

CODA:   (head) [a1] -> [a2] -> [a3] (tail)
```

---

### **6. Riepilogo finale del Modulo 2**

|Struttura|Accesso|Implementazione|Complessità|Applicazioni|
|---|---|---|---|---|
|**Lista**|Sequenziale|Puntatori / cursori|$O(1)$|Strutture dinamiche di base|
|**Pila**|LIFO|Lista / Vettore|$O(1)$|Stack, ricorsione|
|**Coda**|FIFO|Lista / Vettore circolare|$O(1)$|Scheduling, buffer di rete|

---

> Con questo si chiude il **Modulo 2 – Organizzazioni dati di tipo lineare**.  
> Hai padroneggiato le tre fondamenta operative della memoria logica:  
> **la lista (libertà)**, **la pila (disciplina)** e **la coda (giustizia)**.  
> Da qui in avanti — Modulo 3 e successivi — costruiremo su queste basi il vero linguaggio della macchina.

---

