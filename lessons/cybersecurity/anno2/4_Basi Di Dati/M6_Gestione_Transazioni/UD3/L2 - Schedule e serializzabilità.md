# **M6 UD3 Lezione 2 - Schedule e serializzabilità**

### **1. Introduzione alla teoria del controllo della concorrenza**

Il **controllo della concorrenza** si basa su una formalizzazione matematica delle transazioni e dei loro effetti quando vengono eseguite in parallelo.  
Per analizzare la correttezza dell’esecuzione concorrente, è necessario descrivere il comportamento delle transazioni in termini di **sequenze di operazioni**.

Formalmente, una **transazione** è definita come una **sequenza di operazioni di lettura e scrittura** sui dati del database, identificata da un **numero univoco** assegnato dal sistema.

Ogni transazione:

- inizia con un comando **`begin transaction`**,
    
- termina con **`end transaction`**,  
    (che, per semplicità, verranno omessi nelle rappresentazioni).

**Esempio:**

```
t1: r1(x) r1(y) w1(x) w1(y)
```

La transazione `t1` legge e scrive sui dati `x` e `y`.

---

### **2. Definizione di schedule**

Uno **schedule** è una **sequenza di operazioni di input/output** provenienti da **più transazioni concorrenti**.  
Descrive l’ordine in cui le operazioni di lettura e scrittura vengono effettivamente eseguite nel sistema.

Affinché uno schedule sia valido, deve rispettare due condizioni fondamentali:

1. **Completezza:**  
    Tutte le operazioni di ogni transazione che ha eseguito **commit** devono comparire nello schedule.
    
2. **Ordine interno:**  
    Per ciascuna transazione, le sue operazioni devono apparire nello schedule **nello stesso ordine** in cui compaiono nella transazione originale.

Per semplificare lo studio:

- si considera la **commit-proiezione**, ignorando le transazioni che abortiscono.
    
- Questa è una **semplificazione teorica**: nei sistemi reali, gli abort vanno comunque gestiti (sarà trattato successivamente).

---

### **3. Esempio di schedule**

Consideriamo due transazioni:

```
t1: r1(x) w1(x) r1(y) w1(y)
t2: r2(y) w2(y)
```

Alcuni possibili **schedule concorrenti** sono:

- **S1:** `r1(x) w1(x) r1(y) w1(y) r2(y) w2(y)`  
    (prima si completa `t1`, poi `t2`)
    
- **S2:** `r2(y) w2(y) r1(x) w1(x) r1(y) w1(y)`  
    (prima `t2`, poi `t1`)
    
- **S3:** `r1(x) r2(y) w1(x) r1(y) w2(y) w1(y)`  
    (operazioni di `t1` e `t2` intrecciate)
    
- **S4:** `r2(y) r1(x) w1(x) r1(y) w1(y) w2(y)`  
    (altro esempio di esecuzione interleaved)

---

### **4. Scopo del controllo di concorrenza**

Il modulo di **controllo della concorrenza** ha il compito di **evitare schedule anomali**, cioè esecuzioni che potrebbero produrre risultati incoerenti.

Per farlo, il DBMS include un componente chiamato **scheduler**, che:

- accetta o rifiuta le operazioni richieste dalle transazioni;
    
- determina un **ordine di esecuzione sicuro** delle operazioni concorrenti.

L’obiettivo dello scheduler è **accettare solo classi di schedule corretti**, definiti in base a criteri di **equivalenza** con le esecuzioni seriali.

---

### **5. Tipologie di schedule**

#### **Schedule seriale**

Uno **schedule seriale** è uno schedule in cui **tutte le operazioni** di ciascuna transazione vengono eseguite **una dopo l’altra**, senza alcuna interleaving.

In uno schedule seriale, quindi, le transazioni non si sovrappongono nel tempo.

Se abbiamo `n` transazioni, esistono **n! possibili schedule seriali** (una per ogni possibile ordine di esecuzione).

Esempio:

```
S1: tutte le operazioni di t1, poi tutte quelle di t2
S2: tutte le operazioni di t2, poi tutte quelle di t1
```

---

#### **Schedule serializzabile**

Uno **schedule serializzabile** è uno schedule **non seriale** (cioè con operazioni intrecciate) che produce **lo stesso risultato finale** di uno **schedule seriale** equivalente.

Questo concetto è fondamentale:

> la correttezza di un’esecuzione concorrente non richiede che le transazioni siano eseguite in modo strettamente seriale, ma solo che il risultato sia **equivalente a una serializzazione logica**.

Per verificare la serializzabilità, è necessario definire **criteri di equivalenza tra schedule**, come:

- **View-equivalenza**
    
- **Conflict-equivalenza**
    
- **Two-phase locking (2PL)**
    
- **Controllo basato su timestamp**

Questi concetti verranno introdotti progressivamente nelle lezioni successive.

---

### **6. Esempio di schedule seriali e non seriali**

Consideriamo le stesse due transazioni di prima:

```
t1: r1(x) w1(x) r1(y) w1(y)
t2: r2(y) w2(y)
```

**Schedule seriali:**

- **S1:** `r1(x) w1(x) r1(y) w1(y) r2(y) w2(y)`
    
- **S2:** `r2(y) w2(y) r1(x) w1(x) r1(y) w1(y)`

**Altri schedule (non seriali):**

- **S3:** `r1(x) r2(y) w1(x) r1(y) w2(y) w1(y)`
    
- **S4:** `r2(y) r1(x) w1(x) r1(y) w1(y) w2(y)`

Gli schedule **S3** e **S4** sono **concorrenti**, perché le operazioni di `t1` e `t2` si sovrappongono.  
Essi possono o meno essere **serializzabili**, a seconda che producano un risultato equivalente a uno schedule seriale.

---

### **7. In sintesi**

In questa lezione abbiamo introdotto i concetti fondamentali per la teoria del controllo della concorrenza:

- **Schedule:** sequenza di operazioni di transazioni concorrenti.
    
- **Schedule seriale:** esecuzione senza sovrapposizioni.
    
- **Schedule serializzabile:** esecuzione concorrente che produce lo stesso risultato di uno schedule seriale.

Abbiamo anche visto che:

- Con `n` transazioni, esistono **n! schedule seriali** possibili.
    
- La **serializzabilità** dipende dal tipo di **equivalenza** adottata per confrontare gli schedule.

Nelle lezioni successive analizzeremo **le definizioni di equivalenza** (view e conflict) e i **metodi concreti di controllo della concorrenza** utilizzati dai DBMS per garantire l’esecuzione corretta delle transazioni.

---


![](imgs/Pasted%20image%2020251125051907.png)

