## **Lezione 4: Bistabili**

### **1. Dal circuito combinatorio al circuito sequenziale**

I circuiti logici studiati finora erano **combinatori**:  
l’uscita dipendeva **solo dagli ingressi presenti nello stesso istante**.  
Ogni variazione degli input produceva **immediatamente** una variazione dell’output.

Nei calcolatori reali, però, serve qualcosa in più:

- salvare informazioni,
    
- mantenere un dato nel tempo,
    
- attendere un comando prima di cambiare valore,
    
- ricordare un’informazione anche quando gli ingressi tornano a zero.
    

Per ottenere questo comportamento servono **circuiti con memoria**, detti **circuiti sequenziali**.  
Il più semplice di tutti è il **bistabile**.

---

### **2. Cos’è un bistabile**

Un **bistabile** è un circuito che può assumere e mantenere **uno di due stati logici stabili**:

- $0$
    
- $1$
    

Si chiama “bi-stabile” proprio perché possiede **due stati stabili**.  
È l’equivalente elettronico di un **interruttore**: resta nella posizione impostata finché non riceve un comando per cambiare.

---

### **3. Cos’è un latch**

Il più semplice bistabile è il **latch SR**.

La parola _latch_ significa _chiavistello_:  
un meccanismo che mantiene una posizione finché non viene sbloccato manualmente.

#### **Definizione formale**

Un **latch** è un **circuito sequenziale retroazionato** in grado di:

- memorizzare un bit,
    
- mantenere il valore anche in assenza di input,
    
- cambiare solo quando riceve un comando.
    

A differenza dei circuiti combinatori, il suo output dipende anche **dallo stato precedente**.

---

### **4. Latch SR – Significato degli ingressi**

Gli ingressi sono:

- **S (Set)** → forza $Q = 1$
    
- **R (Reset)** → forza $Q = 0$
    

L’uscita principale è:

- **$Q$** → bit memorizzato
    
- **$\overline{Q}$** → complemento di $Q$
    

---

### **5. Struttura del latch SR con porte NOR**

Il latch SR si costruisce con **due porte NOR incrociate**:

- l’uscita della prima NOR entra nella seconda,
    
- l’uscita della seconda entra nella prima,
    
- gli ingressi esterni sono $S$ e $R$.
    

Questa struttura introduce la **retroazione (feedback)**, che è il meccanismo fisico della memoria.

---

### **6. Equazioni logiche del latch SR NOR**

$$  
\begin{cases}  
Q = \neg(R + \overline{Q}) \  
\overline{Q} = \neg(S + Q)  
\end{cases}  
$$

Queste equazioni mostrano che:

- ogni uscita dipende dall’ingresso esterno,
    
- ma anche dall’uscita opposta.
    

---

### **7. Concetto di autoconsistenza**

Una configurazione è **autoconsistente** se:

- ipotizzo dei valori per $Q$ e $\overline{Q}$,
    
- li inserisco nelle equazioni,
    
- ottengo **gli stessi valori di partenza**.
    

In tal caso il circuito è **stabile**.  
Se invece le equazioni restituiscono valori diversi, il circuito **deve cambiare stato**.

---

### **8. Stato di memoria: $S = 0$, $R = 0$**

Supponiamo:

$$  
\begin{cases}  
S = 0 \  
R = 0 \  
Q = 0 \  
\overline{Q} = 1  
\end{cases}  
$$

Verifica:

$$  
Q = \neg(0 + 1) = 0  
$$

$$  
\overline{Q} = \neg(0 + 0) = 1  
$$

La configurazione è **autoconsistente** → il circuito **mantiene lo stato**.  
Questo è uno **stato di memoria**.

---

### **9. Operazione di Set**

Partiamo da:

$$  
\begin{cases}  
Q = 0 \  
\overline{Q} = 1 \  
S = 0 \  
R = 0  
\end{cases}  
$$

Ora poniamo:

$$  
S = 1, \quad R = 0  
$$

Allora:

$$  
\overline{Q} = \neg(1 + 0) = 0  
$$

$$  
Q = \neg(0 + 0) = 1  
$$

Il latch passa allo stato:

$$  
Q = 1, \quad \overline{Q} = 0  
$$

Quando riportiamo $S = 0$, lo stato resta invariato → **memoria a 1**.

---

### **10. Operazione di Reset**

Partiamo da:

$$  
Q = 1, \quad \overline{Q} = 0  
$$

Poniamo:

$$  
S = 0, \quad R = 1  
$$

Allora:

$$  
Q = \neg(1 + 0) = 0  
$$

$$  
\overline{Q} = \neg(0 + 0) = 1  
$$

Anche riportando $R = 0$, lo stato resta:

$$  
Q = 0, \quad \overline{Q} = 1  
$$

→ **memoria a 0**.

---

### **11. Caso proibito: $S = 1$, $R = 1$**

Qui si richiede contemporaneamente:

- $Q = 1$ (Set)
    
- $Q = 0$ (Reset)
    

È una **contraddizione logica**.  
Questo stato è **proibito** e non deve mai essere utilizzato.

---

### **12. Transizioni tra stati**

- Da $Q = 0$ a $Q = 1$ → impulso su $S$
    
- Da $Q = 1$ a $Q = 0$ → impulso su $R$
    
- Con $S = 0$ e $R = 0$ → memoria mantenuta
    

---

### **13. Simbolo astratto del latch**

Nei diagrammi reali si usa un **simbolo semplificato**, che:

- nasconde le porte interne,
    
- mostra solo ingressi $S$, $R$ e uscite $Q$, $\overline{Q}$,
    
- permette di progettare sistemi complessi in modo modulare.
    

---

### **14. Dal bistabile SR al bistabile SRC**

Nel bistabile **SRC**:

- $S$ e $R$ non agiscono direttamente,
    
- passano prima da **due porte AND**,
    
- queste sono abilitate dal segnale **$C$ (Control / Clock)**.
    

Se:

- $C = 0$ → il latch è **bloccato**
    
- $C = 1$ → il latch è **attivo**
    

---

### **15. Cos’è il clock**

Il **clock** è:

- un segnale periodico,
    
- di solito un’onda quadra,
    
- che sincronizza tutte le operazioni del sistema digitale.
    

Serve per:

- evitare glitch,
    
- eliminare ambiguità temporali,
    
- rendere prevedibili i cambiamenti di stato.
    

---

### **16. Ruolo del clock nel bistabile SRC**

Nel SRC:

- quando $C = 0$ → il circuito **ignora $S$ e $R$**
    
- quando $C = 1$ → il circuito **accetta $S$ e $R$**
    

Questo introduce la **logica sequenziale temporizzata**, base di:

- flip-flop,
    
- registri,
    
- memorie,
    
- CPU.
    

---
