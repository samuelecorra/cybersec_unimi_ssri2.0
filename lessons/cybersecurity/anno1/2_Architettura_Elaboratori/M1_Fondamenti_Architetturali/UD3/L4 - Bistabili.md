## ***Lezione 4: Bistabili***

---

> 📌 Questa lezione rielabora integralmente le pagine 86–98 di `M1doc.pdf`.

### **1. Dal circuito combinatorio al circuito sequenziale**

I circuiti logici studiati finora erano **combinatori**:  
l’uscita dipendeva **solo dagli ingressi presenti nello stesso istante**.  
Una variazione degli ingressi produce una variazione dell’uscita dopo il **ritardo di propagazione** delle porte, senza dipendere da uno stato memorizzato precedente.

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

#### **3.1. Definizione formale**

Un **latch** è un **circuito sequenziale retroazionato** in grado di:

- memorizzare un bit,
    
- mantenere il valore quando gli ingressi di controllo sono inattivi,
    
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

La retroazione rende l’uscita presente parte degli ingressi che determineranno l’uscita successiva. Le due configurazioni normali sono complementari, $(Q,\overline Q)=(1,0)$ e $(0,1)$, e ciascuna sostiene se stessa finché un comando esterno non la modifica.

---

### **6. Equazioni logiche del latch SR NOR**

$$
\begin{cases}
Q=\overline{R+\overline Q},\\
\overline Q=\overline{S+Q}.
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
S=0,\\
R=0,\\
Q=0,\\
\overline Q=1.
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
Q=0,\\
\overline Q=1,\\
S=0,\\
R=0.
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

Nel latch NOR, $S=R=1$ forza entrambe le uscite a zero:

$$
Q=\overline{1+\overline Q}=0,
\qquad
\overline Q=\overline{1+Q}=0.
$$

Il circuito non sta risolvendo direttamente due comandi contraddittori imponendo insieme $Q=1$ e $Q=0$; produce invece $(Q,\overline Q)=(0,0)$, che viola la normale complementarità delle uscite. Il problema più delicato nasce quando $S$ e $R$ tornano quasi simultaneamente a $0$: piccole differenze nei ritardi delle due porte possono condurre in modo non prevedibile a uno dei due stati stabili e, nei circuiti reali, anche a una temporanea **metastabilità**.

> ⚠️ Per questo la combinazione $S=R=1$ è detta proibita o non valida nel latch SR-NOR: il suo rilascio non determina in modo affidabile quale bit resterà memorizzato.

---

### **12. Transizioni tra stati**

- Da $Q = 0$ a $Q = 1$ → impulso su $S$
    
- Da $Q = 1$ a $Q = 0$ → impulso su $R$
    
- Con $S = 0$ e $R = 0$ → memoria mantenuta

| $S$ | $R$ | $Q$ successivo | operazione |
| ---: | ---: | --- | --- |
| 0 | 0 | $Q$ precedente | memoria |
| 1 | 0 | 1 | set |
| 0 | 1 | 0 | reset |
| 1 | 1 | non valido | entrambe le uscite a 0; rilascio indeterminato |

Le equazioni descrivono la condizione stabile, ma una transizione avviene nel tempo. Durante un set, per esempio, l’attivazione di $S$ porta prima $\overline Q$ a $0$ dopo un ritardo di porta; questa variazione retroagisce sull’altra NOR e porta poi $Q$ a $1$. È proprio la successione causale, non un cambiamento matematicamente istantaneo, a rendere comprensibile il funzionamento fisico.
    

---

### **13. Simbolo astratto del latch**

Nei diagrammi reali si usa un **simbolo semplificato**, che:

- nasconde le porte interne,
    
- mostra solo ingressi $S$, $R$ e uscite $Q$, $\overline{Q}$,
    
- permette di progettare sistemi complessi in modo modulare.
    

---

### **14. Dal latch SR al latch SR controllato**

Nel bistabile **SRC**:

- $S$ e $R$ non agiscono direttamente,
    
- passano prima da **due porte AND**,
    
- queste sono abilitate dal segnale **$C$ (Control)**.
    

Gli ingressi effettivi del latch NOR sono

$$
S_g=S\cdot C,
\qquad
R_g=R\cdot C.
$$

Quindi:

- $C = 0$ → $S_g=R_g=0$ e il latch conserva lo stato,
    
- $C = 1$ → $S_g=S$, $R_g=R$ e il latch è sensibile agli ingressi.

Questo circuito è un **gated SR latch sensibile al livello**: durante tutto l’intervallo in cui $C=1$, una variazione di $S$ o $R$ può modificare lo stato. Se $C$ è un segnale periodico lo si può chiamare clock, ma il circuito non è ancora un flip-flop sensibile al solo fronte.
    

---

### **15. Cos’è il clock**

Il **clock** è:

- un segnale periodico,
    
- di solito un’onda quadra,
    
- che sincronizza tutte le operazioni del sistema digitale.
    

Serve per coordinare gli istanti o gli intervalli nei quali i componenti sequenziali possono aggiornare lo stato. Non elimina automaticamente glitch o ambiguità: il progetto deve rispettare ritardi di propagazione, tempi di setup e hold e vincoli sul percorso del clock.

È importante distinguere:

- **latch sensibile al livello**: trasparente durante un intero livello di enable;
- **flip-flop sensibile al fronte**: campiona l’ingresso in prossimità del fronte di salita o discesa.

Il PDF anticipa l’idea di aggiornamento sul fronte del clock: tale comportamento appartiene propriamente ai flip-flop, ottenibili con strutture ulteriori rispetto al semplice SRC.
    

---

### **16. Ruolo del controllo nel latch SRC**

Nel SRC:

- quando $C = 0$ → il circuito **ignora $S$ e $R$**
    
- quando $C = 1$ → il circuito **accetta $S$ e $R$**
    

Questo introduce la **logica sequenziale temporizzata**, base di:

- flip-flop,
    
- registri,
    
- memorie,
    
- CPU.

---

### **17. Realizzazione alternativa con porte NAND**

Due porte NAND incrociate realizzano un latch SR con ingressi **attivi bassi**, indicati $\overline S$ e $\overline R$. L’ingresso è attivo quando vale $0$, perciò la tabella è duale rispetto a quella NOR:

| $\overline S$ | $\overline R$ | $Q$ successivo | operazione |
| ---: | ---: | --- | --- |
| 1 | 1 | $Q$ precedente | memoria |
| 0 | 1 | 1 | set |
| 1 | 0 | 0 | reset |
| 0 | 0 | non valido | entrambe le uscite a 1; rilascio indeterminato |

Le equazioni sono

$$
\begin{cases}
Q=\overline{\overline S\cdot\overline Q},\\
\overline Q=\overline{\overline R\cdot Q}.
\end{cases}
$$

La differenza operativa essenziale è quindi la polarità: nel latch NOR lo stato di memoria corrisponde a $S=R=0$; nel latch NAND corrisponde a $\overline S=\overline R=1$.

---

### **18. Stabilità, metastabilità e uso architetturale**

Uno stato è **stabile** quando, mantenuti fissi gli ingressi, la retroazione conserva le uscite. La **metastabilità** è invece una condizione fisica transitoria nella quale il circuito impiega un tempo non prevedibile a risolversi verso $0$ o $1$; può essere favorita da ingressi proibiti o dalla violazione dei vincoli temporali vicino al clock. Non equivale a un terzo valore booleano permanente, ma ricorda che il modello $0/1$ è un’astrazione di tensioni continue.

Latch e flip-flop sono i mattoni dai quali si costruiscono registri, contatori e macchine a stati. Il singolo bistabile memorizza un bit; mettendone $n$ in parallelo si ottiene un registro da $n$ bit.

> ✅ La memoria nasce dalla retroazione: due stati autoconsistenti persistono nel tempo. Set e reset selezionano lo stato; enable e clock controllano quando può cambiare; combinazioni proibite e vincoli temporali devono essere rispettati per evitare risultati indeterminati.
    

---
