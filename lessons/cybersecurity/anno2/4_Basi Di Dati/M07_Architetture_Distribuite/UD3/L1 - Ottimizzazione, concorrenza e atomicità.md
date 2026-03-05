# **M7 UD3 Lezione 1 - Ottimizzazione, concorrenza e atomicità**

### **1. Introduzione**

La tecnologia delle **basi di dati distribuite** introduce nuove sfide rispetto ai sistemi centralizzati.  
Quando i dati sono distribuiti su più nodi della rete, alcuni aspetti del sistema — come **consistenza** e **persistenza** — restano invariati, mentre altri, come **isolamento** e **atomicità**, vengono fortemente influenzati dalla distribuzione.

In questa lezione vengono analizzati i tre ambiti principali di intervento:

- l’**ottimizzazione delle interrogazioni distribuite**,
    
- il **controllo della concorrenza** tra transazioni,
    
- e il **mantenimento dell’atomicità** anche in caso di guasti.

---

### **2. Aspetti non influenzati dalla distribuzione**

La distribuzione dei dati **non modifica** i seguenti aspetti fondamentali:

- **Consistenza**  
    I vincoli di integrità descrivono proprietà **locali** delle basi di dati e rimangono validi indipendentemente dalla distribuzione.  
    Tuttavia, questo rappresenta **un limite tecnologico**: i DBMS attuali non garantiscono ancora un controllo automatico di vincoli globali tra nodi diversi.
    
- **Persistenza**  
    Ogni sistema assicura la **durabilità dei dati locali**, grazie a meccanismi di **recovery** come:
    
    - _log_ delle operazioni,
        
    - _checkpoint_ periodici,
        
    - _dump_ (salvataggi completi).

Questi meccanismi operano **a livello locale**, garantendo la sopravvivenza dei dati in caso di guasti isolati.

---

### **3. Aspetti influenzati dalla distribuzione**

La distribuzione impatta invece su due proprietà cruciali delle transazioni:

- **Isolamento** → gestito tramite il **controllo di concorrenza**;
    
- **Atomicità** → garantita tramite **protocolli di affidabilità distribuita**.

Inoltre, la distribuzione dei dati richiede modifiche anche nel processo di **ottimizzazione delle interrogazioni**, poiché le operazioni coinvolgono più nodi collegati in rete.

---

### **4. Ottimizzazione di interrogazioni distribuite**

In un sistema distribuito, le query devono essere **scomposte e coordinate** tra diversi DBMS.  
L’ottimizzatore di query, sotto la responsabilità del **DBMS che riceve la richiesta**, deve:

1. **Scomporre la query** in più **sotto-query** (subquery), ognuna indirizzata a un DBMS specifico.
    
2. **Costruire un piano di esecuzione distribuito**, che:
    
    - coordini l’esecuzione delle subquery sui vari nodi,
        
    - gestisca lo **scambio di dati** tra DBMS,
        
    - garantisca un’esecuzione **globalmente ottimizzata**.

---

### **5. Calcolo del costo di una query distribuita**

Nel contesto distribuito, il costo complessivo di una query non dipende solo dalle operazioni I/O e CPU, ma anche dal **traffico di rete** generato.  
La formula generale è:

$$  
C_{totale} = C_{I/O} \times n_{I/O} + C_{CPU} \times n_{CPU} + C_{tr} \times n_{tr}  
$$

dove:

- $n_{I/O}$ = numero di accessi a disco,
    
- $n_{CPU}$ = numero di operazioni di elaborazione,
    
- $n_{tr}$ = quantità di dati trasmessi in rete,
    
- $C_{tr}$ = costo di trasmissione per unità di dato.

➡️ In un sistema distribuito, il **termine di trasmissione ($C_{tr} \times n_{tr}$)** diventa particolarmente rilevante, poiché la latenza e la banda di rete influenzano pesantemente le prestazioni.

---

### **6. Controllo di concorrenza**

In un sistema distribuito, una transazione globale $t_i$ può essere scomposta in più **sotto-transazioni** $t_{ij}$, ognuna eseguita su un nodo diverso $j$.

Esempio:

- $t_1$: `r₁₁(x) w₁₁(x) r₁₂(y) w₁₂(y)`
    
- $t_2$: `r₂₂(y) w₂₂(y) r₂₁(x) w₂₁(x)`

Ogni sotto-transazione può accedere a dati differenti, ma le loro interazioni possono creare **conflitti globali**, anche se localmente tutto sembra corretto.

---

### **7. Serializzabilità locale e globale**

Consideriamo due schedulazioni locali:

- **S1:** `r₁₁(x) w₁₁(x) r₂₁(x) w₂₁(x)`
    
- **S2:** `r₂₂(y) w₂₂(y) r₁₂(y) w₁₂(y)`

Queste esecuzioni sono:

- **localmente serializzabili**, perché ogni DBMS mantiene l’ordine corretto delle transazioni;
    
- **globalmente non serializzabili**, perché il **grafo dei conflitti** presenta un **ciclo**:
    
    - sul nodo 1, $t_1$ precede $t_2$ ed è in conflitto con $t_2$;
        
    - sul nodo 2, $t_2$ precede $t_1$ ed è in conflitto con $t_1$.

➡️ **Conclusione:**  
La **serializzabilità locale non garantisce quella globale**, perciò serve un **coordinamento centrale o distribuito** per mantenere la coerenza complessiva.

---

### **8. Atomicità nelle transazioni distribuite**

L’**atomicità** garantisce che una transazione distribuita venga eseguita **interamente o non eseguita affatto**, anche in presenza di guasti.  
Tuttavia, in un sistema distribuito può essere compromessa da diversi tipi di errore:

- **Guasti ai nodi (hardware o software)** → perdita temporanea o permanente di una parte del sistema.
    
- **Perdita di messaggi** → un messaggio o la sua conferma non arrivano, lasciando i partecipanti in uno **stato incerto**.
    
    - ogni messaggio (`msg`) deve essere seguito da un **messaggio di conferma (`ack`)**;
        
    - la perdita di uno dei due lascia il mittente senza certezza sull’avvenuta ricezione.
    
- **Guasti nei link di comunicazione** → la rete si **partiziona** in sottoreti isolate, e una transazione può restare attiva su più segmenti senza possibilità di completamento coordinato.

Per affrontare questi problemi si utilizzano **protocolli di commit distribuito**, come il **Two-Phase Commit (2PC)**, che verrà approfondito nelle lezioni successive.

---

### **9. Sintesi finale**

In questa lezione abbiamo visto che:

- la **distribuzione dei dati** non altera consistenza e persistenza, ma influisce su isolamento e atomicità;
    
- l’**ottimizzazione delle query distribuite** richiede di considerare il costo di trasmissione dei dati in rete;
    
- la **serializzabilità locale non implica quella globale**, quindi il controllo di concorrenza deve essere coordinato;
    
- l’**atomicità** può essere compromessa da guasti di rete o perdita di messaggi e deve essere assicurata tramite protocolli specifici.

In sintesi, l’efficacia di una base di dati distribuita dipende dalla capacità del sistema di **mantenere coerenza e affidabilità**, anche quando l’elaborazione coinvolge nodi diversi e transazioni concorrenti.

---


![](imgs/Pasted%20image%2020251125053153.png)

