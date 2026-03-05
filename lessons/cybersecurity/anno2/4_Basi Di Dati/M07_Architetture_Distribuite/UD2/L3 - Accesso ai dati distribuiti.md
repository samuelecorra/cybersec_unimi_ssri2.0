# **M7 UD2 Lezione 3 - Accesso ai dati distribuiti**

### **1. Introduzione**

Quando si lavora con basi di dati distribuite, è fondamentale gestire **il livello di conoscenza** che il programmatore deve avere riguardo alla **frammentazione e all’allocazione** dei dati.  
Questa conoscenza determina i **livelli di trasparenza**, cioè quanto la distribuzione fisica dei dati è nascosta o visibile all’applicazione.

---

### **2. Livelli di trasparenza**

La distinzione tra **frammentazione** e **allocazione** consente di definire **diversi livelli di astrazione** nell’accesso ai dati, dal più trasparente (indipendente dalla distribuzione) al più concreto (dipendente dalla collocazione fisica).

#### **a) Trasparenza di frammentazione**

- Il programmatore **non conosce** né la frammentazione né l’allocazione dei dati.
    
- Il sistema si occupa di determinare **quali frammenti e server** coinvolgere nell’esecuzione della query.

Esempio:

```sql
procedure Query1(:fnum, :nome);
select Nome into :nome
from Fornitore
where Fnum = :fnum;
end procedure;
```

L’applicazione interroga la relazione logica `Fornitore` come se fosse unica, anche se è fisicamente distribuita.

---

#### **b) Trasparenza di allocazione**

- Il programmatore **conosce la struttura dei frammenti**, ma **non l’allocazione fisica** sui server.
    
- Se esistono **repliche** dei dati, il sistema sceglie automaticamente quale copia utilizzare.

Esempio:

```sql
procedure Query2(:fnum, :nome);
select Nome into :nome
from Fornitore1
where Fnum = :fnum;

if :empty then
select Nome into :nome
from Fornitore2
where Fnum = :fnum;
end procedure;
```

Qui il programmatore sa che esistono più frammenti (`Fornitore1`, `Fornitore2`) ma non deve specificare dove si trovano.

---

#### **c) Trasparenza di linguaggio**

- Il programmatore deve conoscere **sia la struttura dei frammenti** sia la loro **allocazione fisica**.
    
- In presenza di copie ridondanti, deve anche **specificare quale copia** utilizzare.
    
- È il livello **meno trasparente**, ma offre **maggior controllo e ottimizzazione**.

Esempio:

```sql
procedure Query3(:fnum, :nome);
select Nome into :nome
from Fornitore1@ditta.milano.it
where Fnum = :fnum;

if :empty then
select Nome into :nome
from Fornitore2@ditta.roma1.it
where Fnum = :fnum;
end procedure;
```

In questo caso, il codice include **riferimenti espliciti ai server remoti** su cui si trovano i frammenti.

---

#### **d) Assenza di trasparenza**

Quando non esiste alcun livello di astrazione comune:

- ogni DBMS utilizza il proprio **dialetto SQL**;
    
- il sistema è **eterogeneo** e **non supporta protocolli di interoperabilità standard**.

---

### **3. Esempio generale**

Consideriamo la relazione:

$$  
FORNITORE(Fnum, Nome, Città)  
$$

Frammentazione orizzontale:

$$  
FORNITORE_1 = \sigma_{Città='Milano'}(FORNITORE)  
$$  
$$  
FORNITORE_2 = \sigma_{Città='Roma'}(FORNITORE)  
$$

Allocazione con replicazione:

- `FORNITORE1@ditta.milano.it`
    
- `FORNITORE2@ditta.roma1.it`
    
- `FORNITORE2@ditta.roma2.it`

Una procedura che cerca un fornitore in base al numero potrà essere scritta in modo più o meno trasparente, a seconda del livello scelto.

---

### **4. Ottimizzazione delle interrogazioni distribuite**

Le interrogazioni su basi distribuite possono essere **ottimizzate** in due modi principali:

#### **a) Parallelismo**

- Le richieste vengono eseguite **in parallelo** sui diversi server, riducendo il tempo di risposta complessivo.

#### **b) Conoscenza logica dei frammenti**

- Se il sistema conosce **dove risiedono i dati**, può inviare la query **direttamente al frammento interessato**, migliorando l’efficienza ma riducendo la flessibilità.

Esempio di ottimizzazione con _query mirata_:

```sql
procedure Query4(:fnum, :nome, :città);
case :città of
"Milano":
    select Nome into :nome
    from Fornitore1
    where Fnum = :fnum;
"Roma":
    select Nome into :nome
    from Fornitore2
    where Fnum = :fnum;
end procedure;
```

---

### **5. Classificazione delle transazioni distribuite**

I sistemi distribuiti possono eseguire diversi tipi di transazioni, con crescente complessità e coordinamento.

#### **a) Richieste remote**

- Transazioni **read-only** composte da più query SQL.
    
- Tutte le query sono inviate **a un unico DBMS remoto**.

#### **b) Transazioni remote**

- Contengono comandi SQL di qualsiasi tipo (`SELECT`, `INSERT`, `DELETE`, `UPDATE`).
    
- Tutte le operazioni sono indirizzate a **un solo DBMS remoto**.

#### **c) Transazioni distribuite**

- Costituite da comandi SQL indirizzati a **più DBMS diversi**.
    
- Ogni comando agisce su un singolo DBMS, ma la transazione globale può **aggiornare più basi di dati**.
    
- Per garantire l’**atomicità**, si utilizza il **protocollo di Two-Phase Commit (2PC)**.

#### **d) Richieste distribuite**

- Transazioni più generali, che possono contenere comandi indirizzati **a più DBMS contemporaneamente**.
    
- Richiedono un **ottimizzatore di query distribuite** per coordinare l’esecuzione.

---

### **6. Esempio di transazione distribuita**

Relazione:

$$  
CC(Num, Nome, Saldo)  
$$

Frammentazione:

$$  
CC_1 = \sigma_{Num \le 1000}(CC)  
$$  
$$  
CC_2 = \sigma_{Num > 1000}(CC)  
$$

Supponiamo di dover trasferire **100 € dal conto 354 al conto 1487**.  
Serve garantire **l’atomicità**: o entrambe le operazioni vengono eseguite, o nessuna.

```sql
begin transaction

update CC1
set Saldo = Saldo - 100
where CCNum = '354';

update CC2
set Saldo = Saldo + 100
where CCNum = '1487';

commit;
end transaction;
```

---

### **7. Sintesi finale**

In questa lezione abbiamo visto:

- i **livelli di trasparenza** nell’accesso ai dati (frammentazione, allocazione, linguaggio);
    
- le **strategie di ottimizzazione** delle interrogazioni distribuite;
    
- la **classificazione delle transazioni** e le relative esigenze di coordinamento;
    
- l’importanza del **protocollo Two-Phase Commit** per garantire l’atomicità.

**In sintesi**, livelli più bassi di trasparenza offrono **maggiore efficienza e controllo**, ma **ridotta flessibilità** nello sviluppo e nella portabilità delle applicazioni

---


![](imgs/Pasted%20image%2020251125053045.png)

