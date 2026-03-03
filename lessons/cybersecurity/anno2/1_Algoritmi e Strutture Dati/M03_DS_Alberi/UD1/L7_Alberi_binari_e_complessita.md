## **Lezione 7: Alberi binari e complessità di problemi**

---

### **1. Introduzione**

Nelle lezioni precedenti abbiamo imparato a usare gli **alberi** come strumenti per rappresentare e manipolare dati.  
Ora li utilizzeremo come **strumenti di analisi**: gli **alberi di decisione** ci permettono di rappresentare **il processo logico** seguito da un algoritmo e di stimarne la **complessità minima possibile**.

I metodi classici per studiare la complessità — come quello della **dimensione dell’input** o degli **eventi contabili** — risultano infatti **limitati**, poiché analizzano solo singoli aspetti del problema.  
L’**albero di decisione**, invece, visualizza **tutte le scelte possibili** di un algoritmo, fornendo un quadro completo e profondo della sua logica interna.

---

### **2. Definizione di albero di decisione**

Un **albero di decisione** è un **albero binario** che rappresenta un processo decisionale nel quale, ad ogni **nodo interno**, si effettua un **test o confronto** tra due elementi, e i **rami** rappresentano i due **possibili esiti**:

- ramo sinistro → esito **vero** (SI);
    
- ramo destro → esito **falso** (NO).
    

Alla fine del processo, ogni **foglia** corrisponde a una **soluzione possibile** del problema.

#### **Struttura logica**

- **Nodi interni** → confronti o condizioni decisionali.
    
- **Archi** → esiti dei confronti (vero/falso).
    
- **Foglie** → risultati o soluzioni finali.
    

In termini computazionali, ogni **cammino dalla radice a una foglia** rappresenta **una sequenza di scelte** necessarie per arrivare alla soluzione.

---

### **3. Esempio – Ordinamento di tre numeri**

Consideriamo il problema di ordinare tre numeri distinti ( a, b, c ).

Ogni confronto produce due rami:

- **sinistra (SI)** se la condizione è vera;
    
- **destra (NO)** se è falsa.
    

L’albero di decisione sarà strutturato come segue:

```
           a < b ?
          /       \
      b < c ?     c < b ?
      /    \       /    \
   a < c?  a < c? a < c?  a < c?
   /  \     /  \   /  \    /  \
 a<b<c ... ... ... ... ... ...
```

Ogni **foglia** rappresenta una possibile **permutazione ordinata** dei tre numeri, ad esempio:

- ( a < b < c )
    
- ( b < c < a )
    
- ( c < a < b )  
    ecc.
    

#### **Legenda**

- Nodo con “?” → confronto tra due valori.
    
- Ramo sinistro → test positivo (vero).
    
- Ramo destro → test negativo (falso).
    

---

### **4. Interpretazione dell’albero**

Ogni **percorso radice → foglia** indica la **sequenza di confronti** che l’algoritmo esegue per ottenere una determinata permutazione ordinata.

Il **livello massimo** delle foglie corrisponde al **numero di confronti** nel **caso peggiore**, cioè quando il percorso è il più lungo possibile.

👉 Questo livello rappresenta la **complessità asintotica** dell’algoritmo nel **worst case**.

---

### **5. Concetto chiave**

> Un albero di decisione che minimizza il livello massimo delle foglie  
> fornisce una **limitazione inferiore** al numero di decisioni che qualunque algoritmo  
> deve compiere per risolvere un determinato problema nel caso pessimo.

In altre parole:  
qualsiasi algoritmo, per quanto ottimizzato, **non potrà mai** superare il limite inferiore stabilito dall’albero di decisione del problema.

---

### **6. Applicazione – Complessità del problema di ordinamento**

Un algoritmo di ordinamento deve distinguere fra tutte le ( n! ) permutazioni dei dati di ingresso.

#### **Osservazioni fondamentali**

1. Ogni **foglia** dell’albero di decisione rappresenta **una permutazione ordinata** possibile → quindi ci sono **almeno $n!$** foglie.
    
2. Ad ogni livello, i nodi **al più raddoppiano** (due rami per ogni confronto).
    
3. Pertanto, la **profondità minima** dell’albero deve essere almeno:
    

$$  
\log_2(n!)  
$$

---

### **7. Espressione della complessità**

Usando l’approssimazione di Stirling per ( n! ):

$$  
n! \approx \left( \frac{n}{e} \right)^n \sqrt{2\pi n}  
$$

Applicando il logaritmo in base 2:

$$  
\log_2(n!) \approx n \log_2 n - n \log_2 e + \frac{1}{2}\log_2(2\pi n)  
$$

Il termine dominante è quindi **proporzionale a $n \log n$**.

$$  
\Omega(n \log n)  
$$

---

### **8. Risultato fondamentale**

> **Il problema dell’ordinamento ha complessità inferiore limitata da ( \Omega(n \log n) ).**

In altre parole, **nessun algoritmo di ordinamento basato su confronti** può essere più veloce, nel caso peggiore, di una funzione proporzionale a ( n \log n ).

---

### **9. Sintesi conclusiva**

|Concetto|Descrizione|
|---|---|
|**Strumento introdotto**|Albero di decisione|
|**Tipo di struttura**|Albero binario|
|**Significato**|Modello di un processo decisionale|
|**Utilizzo**|Determinazione della complessità inferiore di un problema|
|**Risultato chiave**|Complessità minima per ordinamento: ( \Omega(n \log n) )|

---

> 🧠 **In sintesi:**  
> Gli **alberi di decisione** uniscono la logica degli **alberi binari** con la potenza dell’**analisi della complessità**.  
> Sono lo strumento che traduce la teoria in intuizione visiva: ogni scelta, ogni ramo, ogni foglia racconta la storia di come un algoritmo _pensa_.
