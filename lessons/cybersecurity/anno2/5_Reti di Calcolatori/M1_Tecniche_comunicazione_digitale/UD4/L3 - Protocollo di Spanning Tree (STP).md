# **Lezione 3: Protocollo di Spanning Tree (STP)**

### **1. Il problema dei loop di livello 2**

A differenza dei protocolli di rete come **IP**, i protocolli di livello 2 (come **Ethernet**) **non possiedono un campo TTL (Time To Live)** all’interno dell’intestazione dei frame.  
Questo significa che, se nella topologia esiste un **ciclo logico**, un frame può **circolare indefinitamente** nella rete, generando un **loop** e causando un **broadcast storm**.

La soluzione non è eliminare i collegamenti ridondanti — che servono a garantire affidabilità — ma piuttosto **consentire loop fisici** e costruire una **topologia logica priva di loop**.  
Questa topologia logica prende il nome di **albero di copertura** o **spanning tree**.

---

### **2. Cos’è uno spanning tree**

Uno **spanning tree** è una **struttura ad albero** che:

- collega **tutti i dispositivi della rete**,
    
- **evita qualsiasi ciclo**,
    
- e mantiene **un solo percorso attivo** tra due nodi.

![](imgs/Pasted%20image%2020260213015932.png)

In altre parole, anche se la rete fisica ha collegamenti ridondanti, lo **spanning tree** seleziona un **sottoinsieme di link attivi** tali da mantenere la rete connessa ma **senza loop**.  
Gli altri collegamenti vengono **temporaneamente disattivati (bloccati)** e **riattivati** solo se necessario, ad esempio in caso di guasto.

---

### **3. L’algoritmo di Spanning Tree**

Per costruire questa topologia logica senza cicli, si utilizza un **algoritmo di spanning tree**, implementato nel **protocollo STP (Spanning Tree Protocol)**, standardizzato in **IEEE 802.1D**.

L’algoritmo funziona in modo **distribuito**:  
ogni switch partecipa al processo scambiandosi informazioni e **collaborando alla costruzione dell’albero**.  
Questo procedimento può richiedere un certo tempo per **convergere** — cioè per far sì che tutti gli switch abbiano la stessa visione della topologia.

Per ridurre i tempi di convergenza, è stata introdotta una versione più moderna chiamata **Rapid Spanning Tree Protocol (RSTP)**, standardizzata come **IEEE 802.1w**, che reagisce molto più rapidamente ai cambiamenti nella rete.

![](imgs/Pasted%20image%2020260213020249.png)

---

### **4. Il principio di funzionamento**

L’**STP** lavora selezionando un nodo centrale, detto **bridge radice** (_root bridge_), a partire dal quale si costruisce l’intero albero logico.

Le regole fondamentali sono:

1. Ogni switch deve poter raggiungere il **bridge radice** seguendo un percorso a **costo minimo**.
    
2. I **costi dei collegamenti** sono **proporzionali alla loro velocità**:  
    collegamenti più lenti → costo più alto; collegamenti più veloci → costo più basso.
    
3. I **collegamenti non necessari** (quelli che formano cicli) vengono **disattivati automaticamente**.
    

Il risultato è una rete con **un solo percorso attivo** tra ogni coppia di switch, ma con la possibilità di **riattivare i percorsi alternativi** in caso di guasto.

---

### **5. Messaggi BPDU**

Il coordinamento tra gli switch avviene attraverso messaggi chiamati **BPDU (Bridge Protocol Data Unit)**.  

![](imgs/Pasted%20image%2020260213020648.png)

Le BPDU vengono inviate periodicamente da ogni switch per:

- eleggere il **bridge radice**;
    
- calcolare il **percorso più breve** verso la radice;
    
- e mantenere aggiornato lo stato della rete.
    

Ogni BPDU contiene:

- l’**identificatore del bridge mittente**;
    
- il **costo cumulativo del percorso** verso la radice;
    
- e altre informazioni di controllo necessarie all’algoritmo.
    

---

### **6. Il bridge radice**

Il **bridge radice** è lo switch che si trova **al vertice dello spanning tree**.  
Viene scelto in base al **Bridge ID**, un valore formato da:  
$$  
\text{Bridge ID} = \text{Priorità} + \text{Indirizzo MAC}  
$$

- Lo switch con **priorità più bassa** (e, in caso di parità, con **MAC più basso**) viene eletto come radice.
    
- Tutti gli altri switch calcolano i percorsi più brevi per raggiungerlo.
    

L’albero logico risultante **origina dal bridge radice** e si propaga a tutti gli altri nodi della rete.

---

### **7. Porte designate e porte bloccate**

Dopo aver eletto la radice, ogni switch esegue tre scelte fondamentali:

1. **Porta radice (Root Port)**
    
    - È la porta dello switch che fornisce il **percorso più breve verso la radice**.
        
    - Ogni switch (eccetto la radice) ne ha una sola.
        
2. **Porte designate (Designated Ports)**
    
    - Su ciascun segmento LAN, viene scelto **uno switch designato**, cioè quello più vicino alla radice (con costo minore).
        
    - La porta designata è quella che **gestisce la comunicazione** tra il segmento e la radice.
        
3. **Porte non designate (Blocked Ports)**
    
    - Tutte le altre porte vengono **bloccate**, per **interrompere eventuali cicli**.
        
    - I frame ricevuti su queste porte **non vengono inoltrati**, ma le BPDU continuano a essere ricevute, così da poter **riattivare il collegamento** in caso di guasto su un percorso primario.

![](imgs/Pasted%20image%2020260213021242.png)

---

### **8. Evoluzione e rapidità di convergenza**

Una delle principali limitazioni dello STP classico è la **lentezza nella convergenza**: quando cambia la topologia (ad esempio, un link cade o viene aggiunto uno switch), possono servire **fino a 30–50 secondi** per ricalcolare un nuovo albero.

Per questo motivo è stato introdotto l’**RSTP (Rapid Spanning Tree Protocol)**, che:

- riduce drasticamente i tempi di convergenza (a pochi secondi);
    
- introduce **nuovi stati di porta** e **meccanismi di negoziazione più rapidi**;
    
- mantiene la compatibilità con STP classico.
    

---

### **9. Sintesi concettuale**

- Ethernet **non ha un TTL**, quindi i **loop di livello 2** devono essere evitati tramite **una topologia logica ad albero**.
    
- Lo **Spanning Tree Protocol (STP)** costruisce automaticamente un **albero privo di cicli** selezionando una **radice** e **bloccando i collegamenti ridondanti**.
    
- Le **BPDU** servono per coordinare il calcolo e mantenere la stabilità della rete.
    
- Ogni switch:
    
    - elegge una **root port** (verso la radice),
        
    - identifica le **designated ports** (attive),
        
    - e blocca le altre (**blocked ports**).
        
- Lo **RSTP** è la versione moderna che garantisce **maggiore velocità di riconfigurazione** in caso di variazioni topologiche.