# **M6 UD3 Lezione 6 - Timestamp**

### **1. Introduzione**

I sistemi di gestione delle transazioni **non valutano la serializzabilità a posteriori**, ma operano in modo da **garantirla dinamicamente** durante l’esecuzione.  
Oltre al **locking a due fasi (2PL)**, una delle tecniche più diffuse è quella basata sui **timestamp**, che può essere implementata in due varianti:

- **Monoversione**
    
- **Multiversione**

Entrambe le versioni utilizzano un **ordinamento temporale globale** per decidere l’accettazione o il rifiuto delle operazioni delle transazioni.

---

### **2. Cos’è un timestamp**

Un **timestamp (TS)** è un **identificatore univoco** che definisce un **ordinamento totale** tra gli eventi del sistema.  
In altre parole:

- Ogni evento possiede un **timestamp maggiore** di quello di tutti gli eventi che lo hanno preceduto.
    
- Non esistono **due eventi con lo stesso timestamp**.

#### **Implementazioni possibili**

- **Contatore progressivo:** incrementato a ogni nuovo evento.
    
- **Clock di sistema:** l’istante temporale corrente viene usato come valore di timestamp.

---

### **3. Transazioni e timestamp**

Ogni transazione $T_i$ ha associato un **timestamp $ts_i$**, assegnato dal sistema **all’inizio della transazione**.  
Questo valore rappresenta il **momento di inizio** della transazione e serve a stabilire un **ordine seriale logico** tra di esse.

> Uno schedule viene accettato solo se rispetta l’ordine seriale delle transazioni determinato dai rispettivi timestamp.

---

### **4. Timestamp monoversione**

Nel metodo **monoversione**, ogni oggetto $x$ del database ha associati due indicatori:

- **RTM(x)** – _Read Time Maximum_: il massimo timestamp tra le transazioni che hanno letto $x$.
    
- **WTM(x)** – _Write Time Maximum_: il massimo timestamp tra le transazioni che hanno scritto $x$.

Questi due indicatori vengono aggiornati dinamicamente dallo scheduler per garantire che le operazioni rispettino l’ordine dei timestamp.

---

### **5. Regole del metodo monoversione**

#### **Operazione di lettura**

```
read(x, ts)
```

- Se `ts < WTM(x)` → ❌ la richiesta è **rifiutata**, la transazione **viene abortita**.  
    (significa che la transazione tenta di leggere un valore che è già stato sovrascritto da una più recente)
    
- Altrimenti → ✅ la richiesta è **accettata**, e  
    $$RTM(x) := \max(RTM(x), ts)$$

---

#### **Operazione di scrittura**

```
write(x, ts)
```

- Se `ts < WTM(x)` oppure `ts < RTM(x)` → ❌ la richiesta è **rifiutata**, la transazione **viene abortita**.  
    (significa che la transazione è troppo “vecchia” rispetto a chi ha già letto o scritto $x$)
    
- Altrimenti → ✅ la richiesta è **accettata**, e  
    $$WTM(x) := ts$$

---

### **6. Esempio di funzionamento (monoversione)**

|**Richiesta**|**Risposta**|**RTM(x)**|**WTM(x)**|
|---|---|---|---|
|(iniziale)|—|2|2|
|read(x, 6)|ok|6|2|
|read(x, 8)|ok|8|2|
|read(x, 9)|ok|9|2|
|write(x, 8)|no (t8 uccisa)|9|2|
|write(x, 11)|ok|9|11|
|read(x, 10)|no (t10 uccisa)|9|11|

---

### **7. Vantaggi e svantaggi del controllo con timestamp**

#### **Vantaggi**

- Non richiede **alcun lock** sulle risorse.  
    → Il sistema è **libero da deadlock**.

#### **Svantaggi**

- Può causare **l’abort di molte transazioni** (specialmente in sistemi molto attivi).
    
- Funziona correttamente solo assumendo la **commit-proiezione**, ossia che si considerino solo le transazioni concluse con successo.

Per rimuovere questa assunzione, è necessario:

- **bufferizzare le scritture**, cioè posticiparle fino al commit;
    
- sospendere le **letture** su dati bufferizzati, finché la transazione scrivente non ha confermato il commit.

---

### **8. Timestamp multiversione**

Nel metodo **multiversione**, ogni scrittura di un oggetto **genera una nuova copia (versione)**.  
Questo permette di **evitare gli abort** delle transazioni “vecchie”, che possono leggere **versioni precedenti** dei dati.

#### **Principi base**

- Per ogni oggetto $x$ esistono **diverse versioni** $x_i$, ciascuna con i propri valori di:
    
    - $RTM(x_i)$
        
    - $WTM(x_i)$
    
- Ogni **scrittura** genera una **nuova versione**.
    
- Ogni **lettura** viene eseguita sulla versione creata dall’**ultima scrittura** che precede la lettura in ordine temporale.

---

### **9. Regole del metodo multiversione**

#### **Operazione di lettura**

```
read(x, ts)
```

- Sempre accettata ✅
    
- La lettura avviene sulla versione $x_k$ tale che  
    $$WTM(x_k) = \max {WTM(x_i) \ | \ WTM(x_i) \le ts}$$
    
- Aggiornamento:  
    $$RTM(x_k) := \max(RTM(x_k), ts)$$

---

#### **Operazione di scrittura**

```
write(x, ts)
```

1. Si trova la versione $x_j$ tale che  
    $$WTM(x_j) = \max {WTM(x_i) \ | \ WTM(x_i) \le ts}$$
    
2. Se `ts < RTM(x_j)` → ❌ richiesta rifiutata, transazione abortita.
    
3. Altrimenti → ✅ richiesta accettata:
    
    - viene creata una **nuova versione $x_k$**;
        
    - si aggiornano  
        $$RTM(x_k) := ts, \quad WTM(x_k) := ts$$

---

### **10. Esempio (multiversione)**

|**Richiesta**|**Risposta**|**Versione**|**RTM(xk)**|**WTM(xk)**|
|---|---|---|---|---|
|(iniziale)|—|x₁|2|2|
|read(x,5)|ok|x₁|5|2|
|read(x,8)|ok|x₁|8|2|
|write(x,7)|no (t7 uccisa)|x₁|8|2|
|write(x,12)|ok|x₂|12|12|
|read(x,9)|ok|x₁|9|2|
|read(x,14)|ok|x₂|14|12|
|write(x,13)|no (t13 uccisa)|x₂|14|12|
|write(x,10)|ok|x₃|10|10|
|read(x,11)|ok|x₃|11|10|

---

### **11. Relazione tra TS, CSR e VSR**

Il metodo basato su timestamp garantisce anch’esso la **serializzabilità**, ma con criteri diversi dal 2PL.

$$  
TS \subseteq CSR \subseteq VSR  
$$

- Se $S \in TS$, allora $S \in CSR$
    
- Se $S \notin CSR$, allora $S \notin TS$
    
- Se $S \notin TS$, **non si può dire nulla** su $CSR$
    
- Se $S \in CSR$, **non si può dire nulla** su $TS$

---

### **12. Confronto tra 2PL e TS**

|**Aspetto**|**2PL**|**TS (timestamp)**|
|---|---|---|
|**Gestione delle operazioni rifiutate**|Le transazioni vengono **messe in attesa**|Le transazioni vengono **uccise e riavviate**|
|**Ordine di serializzazione**|Determinato dai **conflitti**|Determinato dai **timestamp**|
|**Attesa per il commit**|Sì (nel 2PL stretto)|Sì (scritture bufferizzate)|
|**Deadlock**|Possibile|Impossibile|

💡 È **più costoso** rieseguire una transazione abortita che sospenderla →  
→ Nella pratica, il **2PL risulta preferibile** in molti contesti.

---

### **13. In sintesi**

In questa lezione abbiamo visto il **controllo della concorrenza basato su timestamp**, nelle sue due varianti:

- **Monoversione:**  
    ogni oggetto ha un’unica copia; le transazioni “fuori ordine” vengono abortite.
    
- **Multiversione:**  
    ogni scrittura genera una nuova versione; le letture possono avvenire su versioni più vecchie, riducendo il numero di abort.

**Riepilogo concettuale:**

- Il timestamp definisce un **ordinamento totale** delle transazioni.
    
- Le regole di accesso garantiscono che lo schedule sia **serializzabile**.
    
- Gli approcci a timestamp sono **senza deadlock**, ma possono portare a **molti abort**.
    
- Se non si assume la commit-proiezione, è necessario **bufferizzare le scritture**.

---


![](imgs/Pasted%20image%2020251125052108.png)

![](imgs/Pasted%20image%2020251125052125.png)

![](imgs/Pasted%20image%2020251125052134.png)

