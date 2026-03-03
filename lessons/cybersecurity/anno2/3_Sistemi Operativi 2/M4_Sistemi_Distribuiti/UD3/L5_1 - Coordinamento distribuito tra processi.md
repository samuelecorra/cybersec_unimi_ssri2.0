# **M4 UD3 Lezione 5 - Coordinamento distribuito tra processi (Parte 1)**

### **1. Introduzione**

Il **coordinamento tra processi** è un aspetto fondamentale nei sistemi distribuiti, dove i processi lavorano su **macchine diverse** senza un orologio o una memoria comune.  
L’obiettivo è definire **come sincronizzare le operazioni**, mantenendo un **ordine coerente degli eventi** e garantendo **l’accesso esclusivo alle risorse condivise**.

I temi principali trattati in questa lezione sono:

- Ordinamento degli eventi
    
- Sincronizzazione
    
    - Mutua esclusione
        
    - Atomicità
        
    - Gestione della concorrenza

---

### **2. Ordinamento degli eventi**

#### **2.1 Il problema**

In un sistema distribuito, non esiste un **orologio globale** né una **memoria condivisa**.  
Pertanto, non è possibile ottenere un **ordinamento totale** degli eventi in modo diretto.

La soluzione pratica consiste nell’utilizzare un **ordinamento parziale**, basato sulla relazione logica **“accaduto prima”**.

---

### **3. Relazione “accaduto prima” (→)**

La relazione “accaduto prima” (proposta da Lamport) definisce una forma di **causalità logica** tra eventi.

#### **Definizione**

Siano A e B due eventi in un sistema distribuito:

1. Se A e B appartengono allo **stesso processo** e A avviene prima di B,  
    allora  
    $$A \to B$$
    
2. Se A è l’evento di **trasmissione di un messaggio** in un processo e B è l’evento di **ricezione dello stesso messaggio** in un altro processo,  
    allora  
    $$A \to B$$
    
3. Se $A \to B$ e $B \to C$, allora  
    $$A \to C$$

---

#### **Caratteristiche**

- La relazione **non è riflessiva**.
    
- Se due eventi **non sono in relazione**, essi sono **concorrenti** e **non si influenzano**.
    
- Se due eventi **sono in relazione**, uno può **influenzare** l’altro.

---

### **4. Ordinamento globale e marche di tempo**

Per stabilire un ordinamento globale coerente, si utilizzano **marche di tempo (timestamp)**.

#### **4.1 Orologi logici**

Ogni processo mantiene un **orologio logico locale** $C_i$ che:

- si **incrementa monotonicamente** nel tempo,
    
- viene **aggiornato in base alla comunicazione** con altri processi.

Quando un processo riceve un messaggio con marca temporale $n$, il suo orologio viene aggiornato a:

$$  
C_i = \max(C_i, n) + 1  
$$

Questo assicura che gli eventi correlati rispettino la relazione di causalità $A \to B$.

---

#### **4.2 Generazione delle marche di tempo**

Le marche di tempo possono essere generate in due modi:

|Soluzione|Descrizione|
|---|---|
|**Centralizzata**|Un distributore unico assegna marche temporali globali. Semplice ma poco scalabile e con singolo punto di guasto.|
|**Distribuita**|Ogni processo genera la propria marca locale e la combina con un **identificatore di nodo**, garantendo unicità a livello di sistema.|

---

### **5. Mutua esclusione nei sistemi distribuiti**

L’accesso a risorse condivise richiede che solo **un processo per volta** entri nella **sezione critica (CS)**.  
Nei sistemi distribuiti, la mutua esclusione può essere implementata in diversi modi.

---

#### **5.1 Metodo centralizzato**

- Un **processo coordinatore** gestisce tutte le richieste di accesso.
    
- Mantiene una **coda delle richieste** e concede il permesso a un processo per volta.

**Vantaggi:**

- Implementazione semplice.

**Svantaggi:**

- **Collo di bottiglia**: tutte le richieste passano dal coordinatore.
    
- **Vulnerabilità ai guasti**: se il coordinatore fallisce, il sistema resta bloccato.
    
- **Scarsa scalabilità** nei sistemi con molti nodi.

---

#### **5.2 Metodo distribuito**

Ogni processo partecipa alla decisione collettiva sull’accesso alla sezione critica.

**Funzionamento:**

1. Quando un processo $P$ vuole entrare nella sezione critica:
    
    - genera una **marca temporale** $T_P$;
        
    - invia una **richiesta di accesso** a tutti gli altri processi.
    
2. Quando un processo $Q$ riceve la richiesta:
    
    - se è **in sezione critica**, ritarda la risposta;
        
    - se **non vuole entrare**, risponde immediatamente con l’autorizzazione;
        
    - se **vuole entrare ma non vi è ancora entrato**, confronta la propria marca $T_Q$ con quella di $P$:
        
        - se $T_Q > T_P$, risponde subito a $P$;
            
        - altrimenti, ritarda la risposta per entrare prima.

**Proprietà:**

- **No starvation** (nessun processo resta in attesa indefinita).
    
- **No deadlock** (nessuna attesa circolare).
    
- **Tolleranza ai guasti** maggiore rispetto al metodo centralizzato.

---

#### **5.3 Metodo a passaggio di token**

- I processi sono organizzati in un **anello logico**.
    
- Un **token** circola nell’anello e rappresenta il **permesso di accesso** alla sezione critica.
    
- Solo il processo in possesso del token può entrare in CS.

**Vantaggi:**

- Elevata efficienza: non richiede broadcast di messaggi.
    
- L’accesso avviene in **tempo proporzionale al numero di nodi**.

**Svantaggi:**

- Se il token viene perso, è necessario un **protocollo di rigenerazione**.
    
- È necessario mantenere l’**ordine logico** dei processi.

---

### **6. Sintesi dei metodi di mutua esclusione**

| Metodo            | Coordinazione      | Messaggi richiesti                              | Tolleranza ai guasti | Scalabilità |
| ----------------- | ------------------ | ----------------------------------------------- | -------------------- | ----------- |
| **Centralizzato** | Unico coordinatore | 3 per richiesta (richiesta, risposta, rilascio) | Bassa                | Bassa       |
| **Distribuito**   | Tutti i processi   | $2 × (N − 1)$                                   | Alta                 | Media       |
| **Token-based**   | Anello logico      | $O(1)$                                          | Media                | Alta        |

---

### **7. Conclusione**

Il **coordinamento distribuito** è alla base dell’esecuzione corretta dei processi in ambienti multi-nodo.  
Attraverso l’uso di **orologi logici**, **marche temporali** e **algoritmi di mutua esclusione**, è possibile garantire che gli eventi mantengano una **coerenza causale**, anche in assenza di un orologio globale o di una memoria condivisa.