# **M1 UD3 Lezione 2 - Tecniche di sostituzione della pagina (parte 2)**

### **1. Introduzione**

Quando la memoria è piena e un processo richiede una pagina non presente in RAM, il sistema operativo deve scegliere **quale pagina rimuovere** per far posto a quella nuova.  
Questo meccanismo prende il nome di **sostituzione della pagina**, e la pagina da eliminare viene detta **pagina vittima**.

La qualità della scelta influisce direttamente sulle prestazioni: una politica di sostituzione efficace riduce il numero di _page fault_ e ottimizza il tempo di accesso medio alla memoria.

---

### **2. Concetto di sostituzione della pagina**

La **sostituzione** avviene ogni volta che si deve **scaricare una pagina** per **caricarne un’altra**.

$$  
\text{Scaricamento (pagina vittima)} \Rightarrow \text{Caricamento (pagina richiesta)}  
$$

Durante questo processo, il sistema operativo:

1. Seleziona la **pagina vittima** secondo una determinata politica;
    
2. Se la pagina è stata modificata, la **salva su disco**;
    
3. Aggiorna la **tabella delle pagine** del processo;
    
4. Carica la **nuova pagina richiesta** nel frame liberato.

---

### **3. Sostituzione locale e globale**

#### **a. Sostituzione locale**

- Il processo può scaricare solo una pagina **tra quelle dei frame a lui assegnati**.
    
- Garantisce **isolamento**: ogni processo gestisce le proprie risorse.
    
- Tuttavia, può portare a **inefficienze**, se un processo ha poche pagine ma un alto tasso di _page fault_.

#### **b. Sostituzione globale**

- Il processo può scegliere come vittima **qualsiasi frame** in memoria, anche appartenente ad altri processi.
    
- Aumenta la **flessibilità** e riduce i _fault_ complessivi, ma può causare **interferenze** tra processi (un processo può sottrarre frame a un altro).

---

### **4. Meccanismo generale di sostituzione**

Il processo di sostituzione implica **due trasferimenti**:

1. **Scaricamento** della pagina vittima su disco (se modificata);
    
2. **Caricamento** della pagina richiesta nella memoria fisica.

La **tabella delle pagine** viene aggiornata di conseguenza:

- la voce della pagina vittima è **marcata come invalida**,
    
- la nuova pagina è **associata al frame liberato**.

---

### **5. Politiche di sostituzione della pagina**

Le principali politiche di sostituzione sono:

1. **FIFO (First In, First Out)**
    
2. **Sostituzione ottima (Optimal)**
    
3. **LRU (Least Recently Used)**
    
4. **Reference Bits (RB)**
    
5. **Second Chance (SC)**
    
6. **LFU (Least Frequently Used)**
    
7. **MFU (Most Frequently Used)**

Ciascuna adotta un criterio diverso per selezionare la pagina da rimuovere.

---

### **6. Politica FIFO (First In / First Out)**

Il **frame da scaricare** è quello **caricato per primo** tra quelli attualmente presenti in memoria.

#### **Principio**

La pagina più “vecchia” viene considerata meno utile e quindi rimossa per prima.

#### **Esempio**

```
Stringa di riferimento:  1 2 3 4 1 2 5 1 2 3 4 5
Numero di frame: 3
```

Risultato: molti _page fault_ iniziali, poi sostituzioni cicliche → può causare **anomalie di Belady**, in cui aumentando il numero di frame i _fault_ aumentano invece di diminuire.

---

### **7. Politica Ottima (Optimal)**

Sostituisce la pagina che **non verrà usata per il periodo di tempo più lungo nel futuro**.

#### **Principio**

Minimizza il numero di _page fault_ possibile, ma **richiede conoscenza futura** del flusso di accessi → utilizzata solo come **riferimento teorico** per valutare altre politiche.

---

### **8. Politica LRU (Least Recently Used)**

Sostituisce la pagina che **non è stata usata da più tempo**.

#### **Principio**

Si basa sull’assunzione che le pagine usate di recente saranno probabilmente riutilizzate presto.

#### **Implementazione**

- **Contatore o orologio:** registra l’ordine temporale degli accessi.
    
- **Struttura a stack:** mantiene la sequenza delle pagine in ordine di utilizzo, spostando in cima quella appena referenziata.

LRU è molto efficace, ma può essere **costosa da implementare** senza supporto hardware (richiede aggiornamenti frequenti di timestamp o contatori).

---

### **9. Politica con Reference Bits (RB)**

Ogni pagina è associata a un **bit di riferimento**, che indica se la pagina è stata acceduta di recente.

|Bit di riferimento|Significato|
|---|---|
|1|La pagina è stata utilizzata recentemente|
|0|La pagina non è stata utilizzata nel periodo T|

#### **Funzionamento**

- Il sistema operativo **azzera periodicamente** tutti i bit.
    
- Ogni accesso a una pagina imposta il bit a **1**.
    
- Alla sostituzione, vengono scelte preferibilmente le pagine con bit **0**.

##### **Estensione**

Si possono usare **gruppi di N bit** per tenere traccia della **storia più recente** degli accessi:  
ad ogni periodo, i bit vengono **shiftati** e il più significativo azzerato, costruendo così un “profilo temporale” dell’uso di ciascuna pagina.  
La pagina da sostituire è quella con **valore binario minore**.

---

### **10. Politica Second Chance (SC)**

Variante della FIFO che dà alle pagine **una seconda opportunità**.

#### **Funzionamento**

- Ogni pagina ha un **bit di riferimento (R)**.
    
- Quando una pagina deve essere sostituita:
    
    - se ( R = 0 ), viene rimossa;
        
    - se ( R = 1 ), il bit è azzerato e la pagina viene spostata in fondo alla coda (riceve una “seconda possibilità”).

#### **Estensione con bit di modifica (M)**

Si considerano due bit per ogni pagina:

|Bit R|Bit M|Significato|Priorità di sostituzione|
|:-:|:-:|---|---|
|0|0|Né usata né modificata|**Prima scelta**|
|0|1|Non usata di recente, ma modificata|Seconda scelta|
|1|0|Usata di recente, non modificata|Probabile riuso|
|1|1|Usata e modificata di recente|Ultima scelta|

---

### **11. Politica LFU (Least Frequently Used)**

Sostituisce la pagina **meno frequentemente utilizzata**.

#### **Funzionamento**

- Ogni frame ha un **contatore di accessi** inizializzato a zero.
    
- Il contatore viene incrementato ad ogni utilizzo.
    
- Il frame con **contatore minore** è scelto come vittima.

#### **Problema**

Una pagina molto usata in passato ma non più utile può restare in memoria a lungo.  
**Soluzione:** applicare un **decadimento periodico**, dividendo i contatori per 2 a intervalli regolari.

---

### **12. Politica MFU (Most Frequently Used)**

Contraria alla LFU: sostituisce la pagina **più frequentemente utilizzata**.

#### **Principio**

Si basa sull’ipotesi che le pagine con un alto numero di accessi recenti **abbiano già esaurito la loro utilità immediata**.  
In pratica è meno comune, ma utile in alcuni scenari di accesso ciclico ai dati.

---

### **13. Politiche di selezione delle pagine da caricare**

Durante la gestione della memoria virtuale, il sistema operativo può decidere **quali pagine caricare** proattivamente, per ridurre i _page fault_ futuri:

- **Caricamento della pagina richiesta** (on-demand);
    
- **Precaricamento predittivo**, che può includere:
    
    - le **N pagine successive** adiacenti a quella richiesta;
        
    - oppure le **N pagine previste** sulla base della _stringa di riferimento_ osservata in passato.

---

### **14. Sintesi finale**

- La **sostituzione delle pagine** serve a liberare frame per nuove pagine richieste.
    
- Le politiche di sostituzione determinano **quale pagina rimuovere** per minimizzare i _page fault_.
    
- Le principali strategie sono:  
    FIFO, Ottima, LRU, Reference Bits, Second Chance, LFU e MFU.
    
- Alcune politiche privilegiano la **semplicità**, altre la **precisione predittiva**.
    
- I sistemi moderni utilizzano versioni **ibride e dinamiche**, adattando la politica in base al carico di lavoro e al comportamento dei processi.