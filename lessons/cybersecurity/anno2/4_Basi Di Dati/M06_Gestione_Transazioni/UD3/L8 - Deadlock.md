# **M6 UD3 Lezione 8 - Deadlock**

### **1. Introduzione al problema del deadlock**

Un **deadlock** (blocco reciproco) si verifica quando **due o più transazioni** si trovano in **attesa circolare** l’una dell’altra.  
In altre parole, ciascuna transazione attende che un’altra **rilasci un lock** su una risorsa di cui ha bisogno per proseguire, ma nessuna può farlo perché anch’essa è bloccata.

**Esempio di situazione base:**

- $t_i$ è in attesa che $t_j$ rilasci un lock
    
- $t_j$ è in attesa che $t_i$ rilasci un lock

Il fenomeno può coinvolgere anche **più di due transazioni**, ad esempio:

$$  
t_1 \rightarrow t_2 \rightarrow t_3 \rightarrow \dots \rightarrow t_n \rightarrow t_1  
$$

dove ciascuna attende una risorsa posseduta dalla successiva, formando un **ciclo di attesa**.

---

### **2. Esempio pratico di deadlock**

Consideriamo le seguenti transazioni:

```
t1: r1(x) w1(y)
t2: r2(y) w2(x)
```

**Sequenza delle richieste:**

1. `r_lock1(x)`
    
2. `r_lock2(y)`
    
3. `r1(x)`
    
4. `r2(y)`
    
5. `w_lock1(y)`
    
6. `w_lock2(x)`

→ In questo scenario si verifica un **deadlock**, perché:

- `t1` attende che `t2` liberi `y`;
    
- `t2` attende che `t1` liberi `x`.

Nessuna delle due può procedere: il sistema entra in **stallo**.

---

### **3. Rappresentazione tramite grafo di attesa**

Il **grafo di attesa (wait-for graph)** è lo strumento formale usato per rilevare i deadlock.

- Ogni **nodo** rappresenta una **transazione**.
    
- Esiste un **arco orientato** da $t_i$ a $t_j$ se $t_i$ è in attesa di una risorsa **bloccata da $t_j$**.

> C’è un **deadlock** se e solo se nel grafo esiste un **ciclo**.

**Esempio:**

```
t1 → t2
t2 → t1
```

Ciclo → presenza di deadlock.

---

### **4. Tecniche di gestione dei deadlock**

Esistono **tre principali strategie** per affrontare i deadlock:

1. **Timeout**
    
2. **Rilevamento e risoluzione (deadlock detection)**
    
3. **Prevenzione (deadlock prevention)**

---

### **5. Timeout**

Con la tecnica del **timeout**, una transazione può restare in attesa **solo per un tempo massimo prefissato**.

Se il tempo scade e la transazione non ha ancora ottenuto il lock richiesto:

- la richiesta viene **rifiutata**;
    
- la transazione viene **abortita e riavviata**.

#### **Scelta del timeout**

- **Troppo basso** → troppi abort inutili (transazioni terminate prima del necessario).
    
- **Troppo alto** → tempi di attesa eccessivi.

💡 È la **soluzione più semplice** e viene usata nella maggior parte dei **DBMS commerciali**.

---

### **6. Rilevamento e risoluzione**

La tecnica di **rilevamento** controlla periodicamente le **tabelle dei lock** per individuare la presenza di cicli nel **grafo di attesa**.

- Non impone vincoli preventivi: il sistema **lascia che il deadlock si verifichi**.
    
- Quando un ciclo viene trovato → si procede alla **risoluzione** abortendo una delle transazioni coinvolte.

#### **Due decisioni fondamentali:**

1. **Quando effettuare il controllo**
    
    - Continuamente (approccio reattivo)
        
    - Periodicamente (approccio a intervalli)
    
2. **Quale transazione terminare**
    
    - Di solito si sceglie quella **meno costosa da riavviare**
        
    - Oppure quella **che ha fatto meno lavoro**

---

### **7. Prevenzione**

La **prevenzione** adotta strategie per **impedire a priori** che si creino cicli di attesa.  
Può basarsi su:

- **Tecniche di allocazione preventiva**
    
- **Regole di ordinamento sugli oggetti o sui timestamp**

#### **Esempi di prevenzione**

1. **2PL conservativo:**
    
    - Le transazioni devono **richiedere tutti i lock all’inizio** della loro esecuzione.
        
    - Se anche un solo lock non è disponibile, la transazione **non inizia**.
        
    - Evita completamente il deadlock, ma riduce il parallelismo.
    
2. **Ordinamento sugli oggetti:**
    
    - Gli oggetti vengono **numerati o ordinati**.
        
    - Ogni transazione deve acquisire i lock **seguendo sempre lo stesso ordine**.
        
    - In questo modo, non possono formarsi cicli di attesa.

---

### **8. Uccisione di transazioni**

Una soluzione preventiva consiste nell’**uccidere una delle transazioni** coinvolte, prima che si formi un ciclo.

Le politiche di uccisione si distinguono in:

- **Preemptive (interrompenti):**  
    uccidono la transazione **che possiede** la risorsa.
    
- **Non-preemptive (non interrompenti):**  
    uccidono la transazione **che richiede** la risorsa.

---

### **9. Prevenzione basata su timestamp**

Si utilizzano i **timestamp** delle transazioni per stabilire **chi deve aspettare e chi deve essere abortito**, secondo due politiche principali.

#### **a. Wait–Die (non interrompente)**

Quando `t_i` richiede un lock su `x` posseduto da `t_j`:

- Se $ts(t_i) < ts(t_j)$ → `t_i` **aspetta**
    
- Altrimenti → `t_i` viene **abortita e riavviata** con lo stesso timestamp

#### **b. Wound–Wait (interrompente)**

Quando `t_i` richiede un lock su `x` posseduto da `t_j`:

- Se $ts(t_i) > ts(t_j)$ → `t_i` **aspetta**
    
- Altrimenti → `t_j` viene **abortita e riavviata**

---

### **10. Osservazioni sui metodi basati su timestamp**

- Le transazioni **uccise devono essere riavviate con lo stesso timestamp**, altrimenti rischierebbero di essere **uccise continuamente** (problema di _starvation_).
    
- Questi metodi sono **raramente adottati nei DBMS commerciali**, perché:
    
    - la probabilità di deadlock è **molto più bassa** rispetto a quella dei normali conflitti;
        
    - la loro implementazione comporta un **overhead elevato**.

---

### **11. Altri approcci senza timestamp**

#### **a. No waiting**

- Se una transazione `t_i` non ottiene subito il lock, viene **abortita immediatamente** e poi **riavviata**.

#### **b. Cautious waiting**

- Se la transazione che detiene il lock (`t_j`) **non è in attesa** → `t_i` **aspetta**.
    
- Se invece `t_j` è **anch’essa in attesa**, allora `t_i` viene **abortita** per evitare un ciclo.

#### **c. Altri criteri possibili**

- Si può scegliere di uccidere la transazione che ha:
    
    - **fatto meno lavoro**, oppure
        
    - **consumato meno risorse**.

---

### **12. Livelock e starvation**

#### **Livelock**

Si verifica quando una transazione **rimane perennemente in attesa** di un lock, ma il sistema continua ad assegnarlo ad altri processi.  
La transazione non è tecnicamente bloccata, ma **non riesce mai a progredire**.

Causa: **allocazione non equa dei lock** da parte del lock manager.

---

#### **Starvation**

Si verifica quando una transazione viene **uccisa ripetutamente** (abort continui) e **non riesce mai a completarsi**.  
Succede se i **timestamp non vengono gestiti correttamente**.

👉 Per evitare la starvation, le transazioni abortite per prevenire un deadlock devono essere **riavviate con lo stesso timestamp**.

---

### **13. In sintesi**

In questa lezione abbiamo affrontato il problema del **deadlock**, cioè la **mutua attesa permanente** tra transazioni concorrenti, e le relative soluzioni.

**Riepilogo:**

- **Deadlock:** situazione di attesa circolare tra due o più transazioni.
    
- **Rilevamento:** analisi del grafo di attesa per individuare cicli.
    
- **Timeout:** interruzione automatica dopo un tempo massimo.
    
- **Prevenzione:** applicazione di regole o politiche per evitare la formazione di cicli.
    
- **Politiche basate su timestamp:**
    
    - _Wait–Die_ (non interrompente)
        
    - _Wound–Wait_ (interrompente)
    
- **Problemi correlati:**
    
    - _Livelock_ (attesa infinita senza blocco)
        
    - _Starvation_ (transazione continuamente abortita)

---

![](imgs/Pasted%20image%2020251125052209.png)

![](imgs/Pasted%20image%2020251125052220.png)

