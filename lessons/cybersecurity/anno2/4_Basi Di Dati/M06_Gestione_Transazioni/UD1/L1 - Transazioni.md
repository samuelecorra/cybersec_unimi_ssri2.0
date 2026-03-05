# **M6 UD1 Lezione 1 - Transazioni**

### **1. Introduzione**

Una **transazione** è l’unità elementare di lavoro svolta da un’applicazione all’interno di un sistema di basi di dati.  
A una transazione sono associate precise **proprietà di correttezza, robustezza e isolamento**, che garantiscono la coerenza logica del database anche in presenza di guasti o accessi concorrenti.

Un **sistema transazionale** è un sistema che mette a disposizione un meccanismo per la **definizione e l’esecuzione di transazioni** da parte di più applicazioni che possono agire contemporaneamente sulla stessa base di dati.

---

### **2. Struttura sintattica di una transazione**

Sintatticamente, una transazione è una procedura racchiusa tra due comandi principali:

```
begin transaction   (o bot)
...
end transaction     (o eot)
```

All’interno di una transazione deve comparire **esattamente uno** dei seguenti comandi:

- `commit work` → indica la **conclusione con successo** della transazione
    
- `rollback work` → indica la **cancellazione (undo)** di tutte le operazioni eseguite fino a quel punto

---

### **3. Transazione ben formata**

Una **transazione ben formata** (well-formed) deve rispettare le seguenti condizioni:

1. Inizia con il comando `begin transaction` (o equivalente, a seconda del linguaggio)
    
2. Termina con il comando `end transaction`
    
3. Contiene **uno ed un solo** comando tra `commit` o `rollback`
    
4. Dopo l’esecuzione del comando `commit` o `rollback` **non devono avvenire altre operazioni** di accesso (lettura o scrittura) sulla base di dati

---

### **4. Esempi di transazioni**

**Esempio 1 – Transazione logica**

```sql
begin transaction
x := x - 10
y := y + 10
z := z - y
if z < 50
    then commit work
    else rollback
end transaction
```

**Esempio 2 – Transazione SQL**

```sql
begin transaction
update ContoCorrente
set Saldo = Saldo - 100
where NumConto = '123456'

update ContoCorrente
set Saldo = Saldo + 100
where NumConto = '123457'

commit work
end transaction
```

---

### **5. Proprietà delle transazioni: ACIDe**

Ogni transazione deve rispettare le **proprietà ACIDe**:

- **A – Atomicità**
    
- **C – Consistenza**
    
- **I – Isolamento**
    
- **D – Durabilità (Persistenza)**

---

### **6. Atomicità**

Una transazione è un’**unità atomica di lavoro**: non può lasciare la base di dati in uno stato intermedio.

- Se avviene un **guasto o un errore prima del commit**, il sistema esegue un **UNDO** delle operazioni effettuate fino a quel momento
    
- Se avviene un **guasto o un errore dopo il commit**, può essere necessario un **REDO** del lavoro fatto, per garantire che i suoi effetti siano effettivamente memorizzati in modo permanente

**Possibili comportamenti di una transazione:**

- **Commit:** comportamento normale (≈ 99,9 % dei casi)
    
- **Rollback richiesto dall’applicazione:** “suicidio” della transazione
    
- **Rollback richiesto dal sistema:** “omicidio” dovuto a errori o conflitti

---

### **7. Consistenza**

L’esecuzione di una transazione non deve **violare i vincoli di integrità** definiti sulla base di dati.

Il controllo del mantenimento dell’integrità può essere:

- **Immediato:** durante l’esecuzione della transazione (l’operazione che causa la violazione viene rifiutata subito)
    
- **Differito:** alla fine della transazione (se, al termine, risultano violati dei vincoli, l’intera transazione viene rifiutata)

---

### **8. Isolamento**

L’esecuzione di una transazione deve essere **indipendente** da quella di tutte le altre transazioni concorrenti.  
L’esecuzione concorrente di più transazioni deve produrre lo **stesso risultato** che si otterrebbe eseguendole in modo **sequenziale**, in qualsiasi ordine.

---

### **9. Durabilità (Persistenza)**

Gli effetti di una transazione che ha eseguito **commit** non devono mai andare persi.  
Il sistema deve garantire la **persistenza dei dati**, anche in presenza di malfunzionamenti, crash o guasti hardware.

---

### **10. Gestione delle proprietà ACIDe nel DBMS**

Le proprietà ACIDe sono garantite da specifici **moduli interni del DBMS**:

|**Proprietà**|**Modulo responsabile**|
|---|---|
|**Atomicità**|Gestore dell’affidabilità|
|**Consistenza**|Compilatore del DDL (genera le verifiche di integrità eseguite durante le transazioni)|
|**Isolamento**|Gestore della concorrenza|
|**Durabilità**|Gestore dell’affidabilità|

---

### **11. Moduli di sistema coinvolti**

- **Gestore delle transazioni**  
    Coordina tutte le operazioni connesse alle transazioni, eseguendo i comandi `begin transaction`, `commit` e `rollback`.
    
- **Gestore dell’affidabilità**  
    Garantisce **atomicità e persistenza**, interagendo con:
    
    - il **gestore dei metodi di accesso**, che tiene traccia delle operazioni richieste;
        
    - il **gestore del buffer**, che si occupa di scrivere i dati su disco quando necessario.
    
- **Gestore della concorrenza**  
    Garantisce l’**isolamento**, filtrando o ripianificando gli accessi concorrenti provenienti dal gestore degli accessi.

---

### **12. Architettura complessiva**

L’insieme dei moduli del DBMS che cooperano nella gestione delle transazioni può essere rappresentato così:

```
Gestore delle interrogazioni e aggiornamenti
Gestore dei metodi d’accesso
Gestore del buffer
Gestore della memoria secondaria
Memoria secondaria
Gestore delle transazioni
Gestore della concorrenza
Gestore dell’affidabilità
```

---

### **13. In sintesi**

In questa lezione sono stati introdotti i concetti fondamentali relativi alle transazioni:

- La **definizione** e la **struttura** di una transazione
    
- Le **proprietà ACIDe** che ne garantiscono correttezza e affidabilità
    
- I **moduli del DBMS** responsabili della loro gestione e controllo

Questi principi costituiscono la base per comprendere le unità successive, dedicate al **controllo della concorrenza** e alle **procedure di ripristino**.

---


![](imgs/Pasted%20image%2020251125051456.png)

