La tecnica di progettazione **"Divide et Impera"** (letteralmente “dividi e conquista”) è uno dei principi fondamentali dell’informatica e dell’analisi algoritmica. L’idea alla base è semplice ma potentissima: **un problema complesso può essere risolto più facilmente suddividendolo in sottoproblemi più piccoli**, risolvendo ciascuno di essi in modo indipendente e poi **combinando** le soluzioni parziali per ottenere la soluzione finale.

Questo approccio consente di affrontare in modo sistematico problemi di grandi dimensioni, riducendo la complessità del ragionamento e permettendo spesso di ottenere **algoritmi più efficienti**. Molti algoritmi celebri, come **Merge Sort**, **Quick Sort**, **Binary Search** e **Fast Fourier Transform (FFT)**, si basano su questo schema.

In generale, un algoritmo “divide et impera” segue tre fasi principali:

1. **Divide**: il problema viene suddiviso in sottoproblemi di dimensioni minori.
    
2. **Impera (Conquer)**: ciascun sottoproblema viene risolto, spesso ricorsivamente.
    
3. **Combina**: le soluzioni dei sottoproblemi vengono unite per costruire la soluzione complessiva.
    

Nel corso del modulo verranno analizzati:

- lo **schema generale** di “divide et impera” e le sue varianti,
    
- gli **elementi caratteristici** che differenziano i vari algoritmi basati su questa tecnica,
    
- gli **ambiti di applicazione** più comuni,
    
- e infine la **valutazione della complessità** di tali algoritmi, spesso descritta tramite **relazioni di ricorrenza**.
    

L’obiettivo è comprendere come questa strategia, apparentemente intuitiva, costituisca in realtà una **struttura formale potente e generalizzabile**, capace di adattarsi a molti problemi computazionali.

---

## **Lezione 1: Complessità di algoritmi ricorsivi**

### **1. Introduzione**

Prima di analizzare nel dettaglio la tecnica di progetto **Divide et Impera**, è necessario introdurre due strumenti matematici fondamentali: i **teoremi per il calcolo della complessità** di algoritmi ricorsivi.

Finora, l’unico metodo a nostra disposizione per determinare la complessità di tali algoritmi era quello delle **sostituzioni successive**, che consiste nello sviluppare passo dopo passo la relazione di ricorrenza.

Per esempio, consideriamo un algoritmo descritto da:

$$  
T(n) = T(n - 1) + d  
$$

Sviluppando per sostituzioni successive otteniamo:

$$  
T(n) = T(1) + (n - 1)d = dn + (c - d)  
$$

Da questa forma si ricava facilmente che la complessità cresce linearmente con $n$.

---

### **2. Relazioni di ricorrenza**

Quando analizziamo algoritmi ricorsivi, il loro comportamento può essere espresso tramite **relazioni di ricorrenza**, cioè equazioni che legano il tempo di esecuzione $T(n)$ al tempo richiesto per risolvere istanze di dimensione minore.

Una relazione di ricorrenza generale può essere scritta nella forma:

$$  
T(n) = a_i \, T(n - i) + c \, n^{\beta}  
$$

dove:

- $a_i \ge 1$: indica che viene effettuata almeno una chiamata ricorsiva
    
- $i \ge 1$: rappresenta la riduzione della dimensione dell’input
    
- $c > 0$ e $\beta \ge 0$: sono costanti reali che rappresentano il costo addizionale (non ricorsivo) del problema
    

In generale, si considerano relazioni in cui i coefficienti $a_1, a_2, \ldots, a_h$ sono **interi non negativi** e $h$ è una costante positiva.

$$ T(n) = \sum_{i=1}^{h} a_i T(n-i) + cn^\beta $$

Tali relazioni vengono dette:

- **lineari**, poiché $n$ ha grado 1 nei termini $T(n - i)$
    
- **a coefficienti costanti**, perché i valori $a_i$ non variano
    
- **di ordine costante**, dato che il numero di termini $h$ è fissato
    

---

### **3. Teorema delle ricorrenze lineari di ordine costante**

Siano $a_1, a_2, \ldots, a_h$ costanti intere non negative, con $h$ costante positiva, e siano $c > 0$ e $\beta \ge 0$ costanti reali.  
Il **teorema delle ricorrenze lineari di ordine costante** permette di determinare direttamente la complessità di relazioni di questo tipo.

Consideriamo una relazione di ricorrenza della forma: 

$$ T(n) = \begin{cases} C, & \text{se } n \le m, \\[4pt] \displaystyle\sum_{i=1}^{h} a_iT(n-i) + cn^\beta, & \text{se } n > m, \end{cases} $$

dove $C$ rappresenta il costo costante dei casi base, mentre $m$ indica la soglia al di sotto della quale la ricorrenza non viene ulteriormente sviluppata. Per determinare l'ordine di crescita di $T(n)$, si considera la somma dei coefficienti che moltiplicano i termini ricorsivi: 

$$ a = \sum_{i=1}^{h} a_i. $$ 
Il **teorema delle ricorrenze lineari di ordine costante** permette quindi di determinare direttamente la complessità della relazione distinguendo due casi:

$$ T(n) = \begin{cases} O\left(n^{\beta+1}\right), & \text{se } a = 1, \\[4pt] O\left(a^n n^\beta\right), & \text{se } a \ge 2. \end{cases} $$

In particolare: - se la somma dei coefficienti ricorsivi è $a=1$, la crescita è **polinomiale** e il grado aumenta di uno rispetto al termine non ricorsivo $cn^\beta$; - se la somma dei coefficienti ricorsivi è $a\ge 2$, la crescita è invece **esponenziale**, con fattore principale $a^n$, moltiplicato per il termine polinomiale $n^\beta$.

#### **Esempio: funzione minima ricorsiva**

```c
int min_r(int *a, int j, int k) {
    int m;
    if (j == k)
        m = a[j];
    else {
        m = min_r(a, j + 1, k);
        m = (a[j] < m ? a[j] : m);
    }
    return m;
}
```

Per questo algoritmo ricorsivo, che calcola il minimo di un array, la relazione di ricorrenza è:

$$  
T(n) = T(n - 1) + c  
$$

Da cui segue che $a = 1$ e $\beta = 0$.  
Applicando il teorema, si ottiene che:

$$  
T(n) = O(n)  
$$

cioè la complessità è lineare.

---

### **4. Interpretazione del teorema**

**Osservazione 1.**  
Nel caso di ricorrenze lineari di ordine costante, la complessità polinomiale è garantita dal fatto che esiste **una sola chiamata ricorsiva** per ogni livello, ossia $a = 1$.

**Osservazione 2.**  
Il risultato finale **non dipende dal valore di $c$**.  
Ciò significa che, anche se all’interno della chiamata ricorsiva eseguiamo più di un ciclo o un’analisi aggiuntiva, l’ordine di complessità resta invariato.

---

### **5. Partizioni bilanciate**

Esiste una classe particolare di relazioni di ricorrenza, chiamate **partizioni bilanciate**, in cui il problema di dimensione $n$ viene diviso in $a$ sottoproblemi di dimensione $n/b$, ciascuno risolto ricorsivamente.

In questi casi, se la fase di divisione e quella di combinazione dei risultati richiedono **tempo polinomiale**, la funzione di complessità assume la forma:

$$  
T(n) = a\,T\!\left(\frac{n}{b}\right) + c\,n^{\beta}  
$$

dove:

- $a \ge 1$: numero di chiamate ricorsive
    
- $b \ge 2$: numero di parti in cui viene suddiviso l’input
    
- $c > 0$ e $\beta \ge 0$: costanti reali che rappresentano il costo extra della fase non ricorsiva
    

---

### **6. Teorema delle ricorrenze lineari con partizione bilanciata**

Siano $a \ge 1$ e $b \ge 2$ costanti intere, e siano $c > 0$, $d \ge 0$ e $\beta \ge 0$ costanti reali.

Consideriamo una relazione di ricorrenza della forma:

$$
T(n)=
\begin{cases}
d, & \text{se } n=1, \\[4pt]
a\,T\!\left(\dfrac{n}{b}\right)+cn^\beta, & \text{se } n>1.
\end{cases}
$$

In questa relazione:

- $a$ rappresenta il **numero di chiamate ricorsive** effettuate;
- $\dfrac{n}{b}$ rappresenta la **dimensione di ciascun sottoproblema**;
- $cn^\beta$ rappresenta il costo del lavoro svolto al di fuori delle chiamate ricorsive;
- $d$ rappresenta il costo costante del caso base.

Per applicare il teorema, si definisce:

$$
\alpha=\frac{\log a}{\log b}=\log_b a.
$$

Il valore di $\alpha$ viene quindi confrontato con l'esponente $\beta$ del termine non ricorsivo $cn^\beta$.

Il **teorema delle ricorrenze lineari con partizione bilanciata** stabilisce che:

$$
T(n)=
\begin{cases}
O\left(n^\alpha\right), & \text{se } \alpha>\beta, \\[4pt]
O\left(n^\alpha\log n\right), & \text{se } \alpha=\beta, \\[4pt]
O\left(n^\beta\right), & \text{se } \alpha<\beta.
\end{cases}
$$

Si distinguono quindi tre casi:

1. **Se $\alpha>\beta$**, prevale il costo prodotto dalle chiamate ricorsive:

   $$
   T(n)=O\left(n^\alpha\right).
   $$

2. **Se $\alpha=\beta$**, il costo ricorsivo e il costo esterno alle chiamate ricorsive hanno lo stesso ordine di crescita. Compare quindi un fattore logaritmico aggiuntivo:

   $$
   T(n)=O\left(n^\alpha\log n\right).
   $$

3. **Se $\alpha<\beta$**, prevale il lavoro svolto al di fuori delle chiamate ricorsive:

   $$
   T(n)=O\left(n^\beta\right).
   $$

Questo teorema permette di analizzare direttamente numerosi algoritmi basati sulla tecnica **Divide et Impera**, nei quali il problema iniziale viene suddiviso in $a$ sottoproblemi, ciascuno di dimensione pari a circa $\dfrac{n}{b}$.

---

### **7. Esempio: Ricerca binaria**

```c
boolean ricbin(dizionario *D, chiave k, int i, int j) {
    int m;
    if (i > j)
        return FALSE;
    else {
        m = (i + j) / 2;
        if (k == D->chiavi[m])
            return TRUE;
        else if (k < D->chiavi[m])
            return ricbin(D, k, i, m - 1);
        else
            return ricbin(D, k, m + 1, j);
    }
}
```

Per la ricerca binaria, la relazione di ricorrenza è:

$$  
T(n) = T\!\left(\frac{n}{2}\right) + c  
$$

Qui $a = 1$ e $b = 2$, da cui segue $\alpha = \beta = 0$.  
Applicando il teorema, otteniamo:

$$  
T(n) = O(\log n)  
$$

---

### **8. Interpretazione del secondo teorema**

**Osservazione 1.**  
Un _buon bilanciamento_ si ottiene quando il numero di chiamate ricorsive $a$ è pari al numero di parti $b$ in cui viene suddiviso l’input.  
In tal caso, $\alpha = 1$.  
Al variare di $\beta$, la complessità assume diversi ordini:

- $O(n)$ se $\beta = 0$
    
- $O(n \log n)$ se $\beta = 1$
    
- $O(n^{\beta})$ se $\beta > 1$
    

**Osservazione 2.**  
Anche in questo caso, il risultato non dipende dal valore di $c$: l’analisi interna dei dati o eventuali cicli aggiuntivi non modificano l’ordine di grandezza della complessità.

---

### **9. Sintesi finale**

In questa lezione abbiamo introdotto due teoremi fondamentali per lo studio degli algoritmi ricorsivi:

1. **Teorema delle ricorrenze lineari di ordine costante**, per ricorsioni con una sola chiamata.
    
2. **Teorema delle ricorrenze lineari con partizione bilanciata**, per ricorsioni che suddividono il problema in più sottoproblemi di dimensione ridotta.
    

Entrambi i teoremi derivano dal principio delle **sostituzioni successive** e saranno utilizzati estensivamente per l’analisi della complessità negli algoritmi “Divide et Impera”.