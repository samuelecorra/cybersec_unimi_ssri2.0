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
T(n) = a_i , T(n - i) + c,n^{\beta}  
$$

dove:

- $a_i \ge 1$: indica che viene effettuata almeno una chiamata ricorsiva
    
- $i \ge 1$: rappresenta la riduzione della dimensione dell’input
    
- $c > 0$ e $\beta \ge 0$: sono costanti reali che rappresentano il costo addizionale (non ricorsivo) del problema
    

In generale, si considerano relazioni in cui i coefficienti $a_1, a_2, \ldots, a_h$ sono **interi non negativi** e $h$ è una costante positiva.  
Tali relazioni vengono dette:

- **lineari**, poiché $n$ ha grado 1 nei termini $T(n - i)$
    
- **a coefficienti costanti**, perché i valori $a_i$ non variano
    
- **di ordine costante**, dato che il numero di termini $h$ è fissato
    

---

### **3. Teorema delle ricorrenze lineari di ordine costante**

Siano $a_1, a_2, \ldots, a_h$ costanti intere non negative, con $h$ costante positiva, e siano $c > 0$ e $\beta \ge 0$ costanti reali.  
Il **teorema delle ricorrenze lineari di ordine costante** permette di determinare direttamente la complessità di relazioni di questo tipo.

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
T(n) = a,T!\left(\frac{n}{b}\right) + c,n^{\beta}  
$$

dove:

- $a \ge 1$: numero di chiamate ricorsive
    
- $b \ge 2$: numero di parti in cui viene suddiviso l’input
    
- $c > 0$ e $\beta \ge 0$: costanti reali che rappresentano il costo extra della fase non ricorsiva
    

---

### **6. Teorema delle ricorrenze lineari con partizione bilanciata**

Siano $a \ge 1$ e $b \ge 2$ costanti intere, $c > 0$, $d \ge 0$ e $\beta \ge 0$ costanti reali.  
Posta la relazione:

$$  
T(n) = a,T!\left(\frac{n}{b}\right) + c,n^{\beta}  
$$

si applica il **teorema delle ricorrenze lineari con partizione bilanciata**, che generalizza il caso precedente e sarà essenziale per analizzare gli algoritmi _Divide et Impera_.

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
T(n) = T!\left(\frac{n}{2}\right) + c  
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