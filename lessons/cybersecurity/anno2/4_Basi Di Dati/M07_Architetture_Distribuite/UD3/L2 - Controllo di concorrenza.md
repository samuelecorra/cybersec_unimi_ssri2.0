# **M7 UD3 Lezione 2 - Controllo di concorrenza**

### **1. Introduzione**

Il **controllo di concorrenza** in un sistema distribuito è il meccanismo che garantisce che le transazioni eseguite in parallelo su nodi diversi producano un risultato **equivalente a un’esecuzione seriale**.  
Tuttavia, come già visto, la **serializzabilità locale** (cioè garantita su ogni nodo) **non assicura la serializzabilità globale** dell’intero sistema, poiché i conflitti tra transazioni possono propagarsi attraverso i nodi.

Questa lezione approfondisce:

- le **condizioni di serializzabilità globale**,
    
- l’uso dei **timestamp distribuiti** (Metodo di Lamport),
    
- e i **meccanismi di rilevazione dei deadlock distribuiti**.

---

### **2. Serializzabilità globale**

Per definizione, una **serializzazione globale** esiste solo se:

> è possibile individuare uno schedule seriale $S$ **equivalente a tutti gli schedule locali** $S_i$ eseguiti sui singoli nodi.

In altre parole, la **proiezione** dello schedule globale $S$ su ciascun nodo $i$ deve coincidere con lo schedule locale $S_i$:

$$  
S|_i = S_i  
$$

#### **Esempio**

- **Nodo 1:**  
    $S_1$: `r₁₁(x) w₁₁(x) r₂₁(x) w₂₁(x)`
    
- **Nodo 2:**  
    $S_2$: `r₂₂(y) w₂₂(y) r₁₂(y) w₁₂(y)`

Questi schedule sono:

- **localmente seriali** (ognuno rispetta l’ordine delle operazioni);
    
- ma **globalmente non serializzabili**, perché l’ordine dei conflitti cambia da un nodo all’altro, generando **un ciclo nel grafo dei conflitti**.

---

### **3. Proprietà di serializzabilità globale**

Ci sono due modi principali per garantire la serializzabilità a livello distribuito:

#### **a) 2PL (Two-Phase Locking) stretto e commit atomico**

Se ogni **scheduler locale** utilizza il **protocollo 2PL stretto** (blocchi rilasciati solo dopo il commit)  
e il **commit è atomico** su tutti i nodi,  
allora lo schedule globale è **conflict-serializzabile**.

#### **b) Timestamp globale**

Se ogni transazione distribuita riceve **un unico timestamp globale**,  
e tale timestamp viene utilizzato in **tutte le sue richieste** ai vari nodi,  
allora si garantisce **una serializzazione temporale coerente**.

Per far ciò serve un **meccanismo di assegnamento di timestamp globali**, come il **Metodo di Lamport**.

---

### **4. Metodo di Lamport**

Il **metodo di Lamport** consente di generare **timestamp coerenti tra nodi diversi**, rispettando la precedenza logica degli eventi.

Ogni timestamp è composto da **due parti**:

1. **Parte più significativa** → indica il **numero progressivo dell’evento** locale (contatore interno del nodo).
    
2. **Parte meno significativa** → identifica il **nodo** da cui proviene l’evento.

#### **Regole di sincronizzazione**

- Ogni nodo mantiene un **contatore locale**.
    
- Quando un nodo invia un messaggio, include il suo timestamp.
    
- Il nodo ricevente:
    
    - confronta il timestamp ricevuto con il proprio contatore locale;
        
    - imposta il suo contatore a un valore **maggiore o uguale** del timestamp del mittente;
        
    - incrementa poi il contatore per l’evento successivo.

In questo modo, i timestamp **riflettono l’ordine causale** degli eventi nel sistema distribuito.

---

### **5. Deadlock distribuiti**

Un **deadlock distribuito** si verifica quando più transazioni, operanti su nodi diversi, restano **bloccate in attesa circolare** di risorse detenute reciprocamente.

#### **Possibili soluzioni**

- **Timeout**  
    Dopo un certo tempo di attesa, una transazione viene interrotta.  
    È la soluzione più semplice e usata nei DBMS distribuiti, ma non sempre efficiente.
    
- **Rilevazione e risoluzione del deadlock**  
    Il sistema rileva cicli di attesa e interviene abortendo una o più transazioni coinvolte.  
    Questo richiede protocolli **asincroni e distribuiti** di comunicazione tra i nodi.

---

### **6. Esempio di deadlock distribuito**

Supponiamo quattro sotto-transazioni:

- `t11` attende `t12` (attivata via **RPC**, Remote Procedure Call);
    
- `t12` attende una risorsa controllata da `t22`;
    
- `t22` attende `t21` (anch’essa attivata via RPC);
    
- `t21` attende una risorsa controllata da `t11`.

Si genera così un ciclo di attesa tra due DBMS distinti:

|Nodo|Transazioni attive|Tipo di attesa|
|---|---|---|
|DBMS1|t11, t21|attesa (lock)|
|DBMS2|t12, t22|attesa (lock)|

Questa catena circolare produce un **deadlock distribuito**.

---

### **7. Rappresentazione delle condizioni di attesa**

Le condizioni di attesa (anche transitive) sono rappresentate con la notazione:

$$  
E_{in} \rightarrow t_i \rightarrow t_j \rightarrow E_{out}  
$$

**Esempio:**

- DBMS1: $E_2 \rightarrow t_2 \rightarrow t_1 \rightarrow E_2$
    
- DBMS2: $E_1 \rightarrow t_1 \rightarrow t_2 \rightarrow E_1$

Queste sequenze descrivono le catene di blocco tra eventi (E) e transazioni (t) su nodi diversi.

---

### **8. Rilevazione di deadlock distribuiti**

Il processo di rilevazione avviene **periodicamente** su ciascun DBMS, che:

1. **Integra** le nuove sequenze di attesa con le condizioni locali.
    
2. **Analizza** il grafo risultante per individuare cicli di deadlock.
    
3. **Comunica** le proprie sequenze agli altri DBMS per estendere la conoscenza globale.

Per evitare ridondanza, la comunicazione avviene:

- **solo “in avanti”**, verso i nodi coinvolti nella sotto-transazione in attesa;
    
- **solo se $i > j$**, cioè da un nodo con indice maggiore a uno con indice minore.

#### **Esempio di propagazione**

- DBMS1 comunica a DBMS2: $(E_3 \rightarrow t_3 \rightarrow t_1 \rightarrow E_2)$
    
- DBMS2 comunica a DBMS3: $(E_3 \rightarrow t_3 \rightarrow t_2 \rightarrow E_3)$

Alla fine, un **ciclo chiuso nel grafo delle attese** indica la presenza di un **deadlock globale**.

---

### **9. Sintesi finale**

In questa lezione abbiamo visto che:

- La **serializzabilità locale** non implica **serializzabilità globale**.
    
- La coerenza globale può essere garantita mediante:
    
    - **2PL stretto e commit atomico**, oppure
        
    - **timestamp globali** (Metodo di Lamport).
    
- I **deadlock distribuiti** sono inevitabili nei sistemi concorrenti e devono essere:
    
    - **rilevati** tramite analisi dei grafi di attesa,
        
    - oppure **risolti** tramite timeout o abort selettivo.

In sintesi, il controllo di concorrenza in ambienti distribuiti richiede **coordinamento, sincronizzazione e comunicazione costante tra i nodi**, per mantenere la consistenza e l’efficienza del sistema globale.

---


![](imgs/Pasted%20image%2020251125053250.png)

