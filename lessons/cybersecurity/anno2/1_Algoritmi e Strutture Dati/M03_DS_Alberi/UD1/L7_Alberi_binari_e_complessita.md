# **M3 UD1 Lezione 7 - Alberi binari e complessità di problemi**

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

![](imgs/Pasted%20image%2020260425194211.png)

Ogni **foglia** rappresenta una possibile **permutazione ordinata** dei tre numeri, ad esempio:

- ( a < b < c )
- ( b < c < a )
- ( c < a < b )  
   ecc...

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

Partiamo col ricordare la definizione di fattoriale:

$$
n! = n \cdot (n-1) \cdot (n-2) \cdots 1
$$

Ora calcoliamo la cosiddetta "minorazione" per $n!$: la minorazione è un modo per stimare un limite inferiore, e in questo caso possiamo osservare che...

E' facile convincersi che questo valore è sempre maggiore o uguale a:

$$
n(n-1) \cdot (n-2) \cdots \cdot (n/2)
$$

In quanto abbiamo praticamente tolto metà della produttoria.

Quindi per ora sappiamo che:

$$
n! = n \cdot (n-1) \cdot (n-2) \cdots 1 \geq n(n-1) \cdot (n-2) \cdots \cdot (n/2)
$$

Serve fare un altro confronto:

$$
n! = n \cdot (n-1) \cdot (n-2) \cdots 1 \geq n(n-1) \cdot (n-2) \cdots \cdot (n/2) \geq \left( \frac{n}{2} \right)^{n/2}
$$

Quest'ultima disuguaglianza è vera perché ogni termine della seconda produttoria è maggiore o uguale a $\frac{n}{2}$.

Adesso applichiamo il logaritmo in base 2, ricordando che il logaritmo è l'esponente a cui dobbiamo elevare 2 per ottenere il numero in ingresso:

$$
\log_2(n!) \geq \log_2\left( \left( \frac{n}{2} \right)^{n/2} \right) = \frac{n}{2} \log_2\left( \frac{n}{2} \right)
$$

Non serve semplificare ulteriormente, poiché il termine dominante è già chiaro: è **proporzionale a $n \log n$**. Abbiamo dunque dimostrato che esiste un limite inferiore alla complessità di qualsiasi algoritmo di ordinamento basato su confronti, che è:

$$\Omega(n \log n)$$

---

Era l'unico modo per dimostrare la tesi? No! Infatti possiamo anche utilizzare l'approssimazione di Stirling per ottenere lo stesso risultato, ma in modo più diretto.

L'approssimazione di Stirling fornisce una stima asintotica per il fattoriale, ed è data da:

$$
n! \approx \left( \frac{n}{e} \right)^n \sqrt{2\pi n}
$$

Ok, ma come abbiamo ottenuto questa formula? E' una formula che si ottiene utilizzando il metodo di Laplace, che è un metodo di integrazione asintotica. Non è necessario entrare nei dettagli del metodo di Laplace, ma è importante sapere che questa formula è una stima molto precisa per il fattoriale, soprattutto per valori grandi di n.

Applicando il logaritmo in base 2:

$$
\log_2(n!) \approx n \log_2 n - n \log_2 e + \frac{1}{2}\log_2(2\pi n)
$$

Non continuiamo ulteriormente, infatti:

Il termine dominante è quindi **proporzionale a $n \log n$**.

$$
\Omega(n \log n)
$$

Stessa conclusione, ma con un percorso più diretto e meno laborioso rispetto alla minorazione.

---

### **8. Risultato fondamentale**

> **Il problema dell’ordinamento ha complessità inferiore limitata da $\Omega(n \log n)$.**

In altre parole, **nessun algoritmo di ordinamento basato su confronti** può essere più veloce, nel caso peggiore, di una funzione proporzionale a $n \log n$.

Questo risultato è fondamentale perché, come scopriremo nella prossima unità didattica con l'heap sort,e in futuro anche con algoritmi di ordinamento più avanzati, esistono algoritmi che raggiungono questa complessità, dimostrando così che è possibile ottenere il limite inferiore.

Si sa, gli informatici per un motivo o per l'altro amano ordinare i dati, e questa passione si traduce in applicazioni che vediamo ogni giorno: cronologia dei social media, risultati di ricerca, classifiche di prodotti, e molto altro. Potremmo stare qui ore a spiegare quanto sia importante l'ordinamento, ma basta pensare a quanto sia frustrante cercare qualcosa in una lista disordinata per capire l'importanza di questo processo. I motori di ricerca sono l'esempio più lampante: quando digitiamo una query, vogliamo i risultati ordinati per rilevanza, e questo richiede algoritmi di ordinamento efficienti. Se non avessimo algoritmi che raggiungono il limite inferiore di $\Omega(n \log n)$, potremmo trovarci a dover aspettare molto più tempo per ottenere i risultati desiderati.

---

### **9. Sintesi conclusiva**

| Concetto                 | Descrizione                                               |
| ------------------------ | --------------------------------------------------------- |
| **Strumento introdotto** | Albero di decisione                                       |
| **Tipo di struttura**    | Albero binario                                            |
| **Significato**          | Modello di un processo decisionale                        |
| **Utilizzo**             | Determinazione della complessità inferiore di un problema |
| **Risultato chiave**     | Complessità minima per ordinamento: ( \Omega(n \log n) )  |

---

> 🧠 **In sintesi:**  
> Gli **alberi di decisione** uniscono la logica degli **alberi binari** con la potenza dell’**analisi della complessità**.  
> Sono lo strumento che traduce la teoria in intuizione visiva: ogni scelta, ogni ramo, ogni foglia racconta la storia di come un algoritmo _pensa_.
