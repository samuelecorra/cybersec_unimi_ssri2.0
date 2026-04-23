## ***Lezione 2: Rappresentazione Analogica dell’Informazione***

---

### **1. Perché nasce la rappresentazione analogica**

Nella Lezione 1 abbiamo visto che:

- l’informazione è **astratta**,
    
- la macchina opera solo su **grandezze fisiche**,
    
- serve quindi una **rappresentazione fisica dell’informazione**.
    

Il **primo grande metodo storico** usato per rappresentare fisicamente l’informazione è stato quello **analogico**.

La rappresentazione analogica nasce in modo naturale perché **il mondo fisico è continuo**:  
temperatura, luce, suono, movimento, pressione, tensione variano senza “salti”, ma con **infiniti valori intermedi**.

---

### **2. Che cosa significa rappresentare un’informazione**

Rappresentare un’informazione significa:

> **creare una corrispondenza tra un contenuto astratto e una grandezza fisica misurabile.**

Per costruire una rappresentazione servono sempre tre elementi:

1. **Il rappresentato** → l’informazione astratta.
    
2. **Il rappresentante** → la grandezza fisica.
    
3. **La legge di corrispondenza** → la regola che lega i due.
    

Per definire correttamente questa legge dobbiamo conoscere:

- il **campo di variabilità del rappresentato**,
    
- il **campo di variabilità del rappresentante**.
    

Solo dopo possiamo costruire una mappatura coerente tra i due insiemi di valori.

---

### **3. Esempio fondamentale: temperatura rappresentata tramite tensione**

Supponiamo:

- **Rappresentato**: temperatura $T$  
    $$T \in [0^\circ C, 50^\circ C]$$
    
- **Rappresentante**: tensione elettrica $V$  
    $$V \in [0V, 5V]$$
    

Definiamo una **legge di corrispondenza lineare**:

- $0^\circ C \rightarrow 0V$
    
- $25^\circ C \rightarrow 2.5V$
    
- $50^\circ C \rightarrow 5V$
    

![](imgs/Pasted%20image%2020251208082935.png)

Ogni valore della temperatura viene associato a un valore **proporzionale** di tensione.

Questo significa che:

- se la temperatura aumenta in modo continuo,
    
- anche la tensione varia in modo continuo,
    
- senza salti,
    
- senza livelli discreti.
    

Questa è **la rappresentazione analogica** nel suo significato più puro.

---

### **4. Caratteristiche matematiche della rappresentazione analogica**

Una buona rappresentazione analogica è:

- **biunivoca**  
    a ogni valore del rappresentato corrisponde **un solo valore del rappresentante**, e viceversa;
    
- **continua**  
    tra due valori esistono **infiniti valori intermedi**;
    
- **generalmente lineare**  
    il grafico è spesso una retta, per semplicità di calcolo e prevedibilità.
    

![](imgs/Pasted%20image%2020251208083003.png)

---

### **5. Vantaggi della rappresentazione analogica**

#### **5.1 Fedeltà**

Ogni piccolissima variazione del fenomeno reale si riflette nel rappresentante fisico.

Esempio:

- $+0.1^\circ C$ → piccola variazione di tensione,
    
- nessuna perdita di continuità.
    

Questo rende l’analogico **estremamente fedele** nella descrizione dei fenomeni fisici.

---

#### **5.2 Intuitività**

Il valore si **legge direttamente** osservando la grandezza fisica:

- altezza del mercurio → temperatura,
    
- posizione della lancetta → velocità,
    
- ampiezza dell’onda → intensità del segnale.
    

Non serve decodificare numeri: **l’informazione è visibile**.

---

### **6. Il problema fondamentale dell’analogico: la vulnerabilità**

La rappresentazione analogica accetta **infiniti valori intermedi**.  
Questo la rende **intrinsecamente fragile**.

---

#### **6.1 Sensibilità ai disturbi**

Poiché ogni valore è ammesso, anche una **minima alterazione** viene interpretata come informazione valida.

I disturbi possono essere:

- elettrici,
    
- elettromagnetici,
    
- termici,
    
- meccanici,
    
- ambientali.
    

Una variazione microscopica di tensione può generare **un errore di misura**.

In informatica questo è **inaccettabile**, perché il calcolo deve essere:

- stabile,
    
- ripetibile,
    
- affidabile.
    

---

#### **6.2 Invecchiamento dei componenti**

Con il tempo i componenti analogici subiscono:

- ossidazione,
    
- variazioni termiche,
    
- deformazioni dei materiali,
    
- deriva dei parametri elettrici.
    

![](imgs/Pasted%20image%2020251208083028.png)

Una resistenza cambia valore →  
la relazione tensione-temperatura cambia →  
la misura diventa **progressivamente falsa**.

Il sistema non è più affidabile nel tempo.

---

#### **6.3 Errore di approssimazione e accumulo**

Nessuna misura analogica è **perfettamente precisa**.  
Ogni operazione introduce un piccolo errore.

Quando eseguiamo più operazioni in sequenza:

- gli errori **si sommano**,
    
- poi si **moltiplicano**,
    
- e alla fine **esplodono**.
    

Esempio concettuale:

- sommo due segnali leggermente errati,
    
- il risultato è più errato,
    
- ripeto 1000 volte → ottengo un valore completamente inattendibile.
    

Questo rende l’analogico **inadatto ai calcoli complessi**.

---

### **7. Perché l’analogico non può sostenere l’informatica moderna**

L’informatica richiede:

- precisione assoluta,
    
- stabilità nel tempo,
    
- ripetibilità perfetta,
    
- immunità ai disturbi,
    
- possibilità di memorizzazione fedele,
    
- capacità di eseguire miliardi di operazioni senza degradare.
    

La rappresentazione analogica:

- è continua,
    
- sensibile,
    
- instabile,
    
- soggetta a deriva,
    
- soggetta ad accumulo di errore.
    

Per questo **non è compatibile con il calcolo automatico moderno**.

---

### **8. Cenni storici: dal mondo analogico al digitale**

Per oltre un secolo l’umanità ha usato **rappresentazioni analogiche**:

- termometri a mercurio,
    
- manometri,
    
- voltmetri a lancetta,
    
- telegrafo,
    
- radio,
    
- telefoni analogici.
    

Le prime macchine di calcolo erano:

- meccaniche,
    
- poi elettromeccaniche,
    
- basate su movimenti continui.
    

Le **schede perforate di Herman Hollerith** (1890) rappresentano il primo tentativo di automatizzare l’informazione, ma erano ancora legate a meccanismi fisici e a segnali non pienamente digitali.

Durante la Seconda Guerra Mondiale, Alan Turing progettò la **Bomba**, una macchina elettromeccanica per decifrare Enigma.  
Era una macchina:

- fisicamente analogica,
    
- ma concettualmente già orientata verso la **logica discreta**.
    

La vera svolta arriva con:

- i **semiconduttori**,
    
- i **transistori**,
    
- le **porte logiche**,
    
- i **circuiti digitali**.
    

Negli anni ’50-’60 tutta l’informazione viene trasformata in:

- **due soli stati stabili**,
    
- **due soli livelli di tensione**,
    
- **due soli simboli**: $0$ e $1$.
    

Nasce così la **rappresentazione digitale**.

---

### **9. Significato architetturale della transizione analogico → digitale**

Il passaggio dall’analogico al digitale significa:

- passare dal **continuo al discreto**,
    
- eliminare gli infiniti valori intermedi,
    
- introdurre soglie nette,
    
- trasformare l’errore in **non-errore**.
    

Questo rende possibile:

- il calcolo automatico affidabile,
    
- la memoria stabile,
    
- le CPU,
    
- i sistemi operativi,
    
- le reti,
    
- l’intera civiltà digitale.
    

---

### **10. Conclusione concettuale della lezione**

La rappresentazione analogica è:

- naturale,
    
- fedele,
    
- intuitiva,
    

ma è anche:

- instabile,
    
- sensibile,
    
- soggetta a errore,
    
- inadatta all’elaborazione automatica su larga scala.
    

È stata un **passaggio storico necessario**,  
ma **non può essere il fondamento dell’informatica moderna**.

---

### **11. Collegamento diretto con la Lezione 3**

Ora siamo pronti per il passaggio decisivo:

> **come trasformare un’informazione continua in una forma stabile, immune ai disturbi e adatta al calcolo automatico?**

La risposta è:

#### **rappresentazione digitale dell’informazione**

Ed è esattamente ciò che affronteremo nella **Lezione 3**.

---
